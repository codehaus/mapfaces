/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */

package org.mapfaces.renderkit.html.layer;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.mapfaces.renderkit.html.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.geotoolkit.data.collection.FeatureCollection;
import org.geotoolkit.data.collection.FeatureIterator;
import org.geotoolkit.feature.simple.SimpleFeatureBuilder;
import org.geotoolkit.feature.simple.SimpleFeatureTypeBuilder;
import org.geotoolkit.feature.type.DefaultGeometryType;
import org.geotoolkit.metadata.iso.extent.DefaultGeographicDescription;
import org.geotoolkit.referencing.CRS;
import org.geotoolkit.referencing.crs.DefaultGeographicCRS;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.layer.UISvgLayer;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.BasicFeature;
import org.mapfaces.models.Context;
import org.mapfaces.models.Feature;
import org.mapfaces.share.utils.RendererUtils.HTML;
import org.mapfaces.util.FacesMapUtils;
import org.opengis.feature.FeatureFactory;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.metadata.extent.GeographicDescription;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.GeographicCRS;

/**
 *
 * @author leopratlong (Geomatys)
 */
public class SvgLayerRenderer extends WidgetBaseRenderer {
 
    private static final Logger LOGGER = Logger.getLogger(EditionBarRenderer.class.getName());
    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {

        System.out.println("===========> ENTREE DANS LE ENCODE");
        super.encodeBegin(context, component);

        System.out.println("===========> APRES SUPER");
        //Find UIMapPane refers to this widget
        String mapJsVariable = null ;
        final UIMapPane uIMapPane = FacesMapUtils.getUIMapPane(context, component);
        if (uIMapPane != null) {
                mapJsVariable = FacesMapUtils.getJsVariableFromClientId(uIMapPane.getClientId(context));
        } else {
            LOGGER.log(Level.SEVERE, "This widget doesn't referred to an UIMapPane so it can't be rendered !!!");
            component.setRendered(false);
            return;
        }
        
        System.out.println("===========> Map JS = " + mapJsVariable);
        System.out.println("===========> CONTINUE");
        final UISvgLayer comp = (UISvgLayer) component;
        final String clientId = comp.getClientId(context);
        final String compId;
        String formId = FacesMapUtils.getFormId(context, component);
        if (clientId.contains(":")) {
            if (formId == null) {
                formId = clientId.substring(0, clientId.indexOf(':'));
            }
            compId = clientId.substring(clientId.indexOf(':') + 1);
        } else {
            compId = clientId;
        }

        writer.startElement((String) HTML.INPUT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, HTML.INPUT_TYPE_HIDDEN, HTML.TYPE_ATTR);
        writer.writeAttribute(HTML.NAME_ATTRIBUTE, clientId, HTML.NAME_ATTRIBUTE);
        writer.writeAttribute(HTML.id_ATTRIBUTE, clientId, HTML.id_ATTRIBUTE);
        writer.writeAttribute(HTML.value_ATTRIBUTE, comp.getValue(), HTML.value_ATTRIBUTE);
        writer.endElement((String) HTML.INPUT_ELEM);
        
        System.out.println("========>  COMPID = " + compId);
        
        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, "text/javascript", "text/javascript");
        // SVG Layer for OpenLayers is created.
        final String functionName = "addFeature_" + compId;
        writer.write(new StringBuilder("").append("" +
                "window." + compId + " = new OpenLayers.Layer.Vector('" + compId + "');" +
                "window." + compId + ".events.register('featureadded', " + compId + "" + ", " + functionName + ");" +
                mapJsVariable + ".addLayers(window." + compId + ");" +
                "if($('"+ clientId + "')){$('" + clientId + "').value = 'a;' + " + mapJsVariable + ".getProjection();}" ).toString());

        // When a feature is created, we keep the coordinates in a hidden field.
        writer.write(new StringBuilder("").append("" +
                "function " + functionName + "(event){" + "if($('"+ clientId + "')){" +
                        "$('" + clientId + "').value=$('" + clientId + "').value" +
                        "+ ';' + event.feature.geometry;}}").toString());
        writer.endElement(HTML.SCRIPT_ELEM);
        writer.flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        try {
            super.decode(context, component);

            final ExternalContext ext = context.getExternalContext();
            final UISvgLayer comp = (UISvgLayer) component;
            final Map parameterMap = ext.getRequestParameterMap();
            
            System.out.println("DECODE");
            if (this.debug) {
                LOGGER.log(Level.INFO, "[DEBUG] ScgLayerRenderer DECODE");
            }
            final UIContext contextComp = (UIContext) FacesMapUtils.getParentUIContext(context, comp);
            final Context tmp = (Context) contextComp.getModel();

            final Map<String, String> params = context.getExternalContext().getRequestParameterMap();
            if (params != null) {
                System.out.println("==> PREMIER IF");
                for (Entry e : params.entrySet()) {
                    System.out.println("--------" + e.getKey() + " ; " + e.getValue());
                }
                final String value = (String) parameterMap.get(comp.getClientId(context));

                if ((value != null) && !value.isEmpty()) {
                    System.out.println("==> SECOND IF");
                    System.out.println("==> VALEUR = " + value);
                    final String[] featList = value.split(";");
                    final List<Feature> features = new ArrayList<Feature>();
                    if (featList.length > 2) {
                        System.out.println("==> TROISIEME IF");
                        final String operation = featList[0];
                        WKTReader wktReader = new WKTReader();
                        final CoordinateReferenceSystem crs = CRS.decode(featList[1]);
                        for (int i = 2; i < featList.length; i++) {
                            System.out.println("++++++ A");
                            Feature feat = new BasicFeature();
                            System.out.println("++++++ B");
                            feat.setGeometry(wktReader.read(featList[i]));
                            System.out.println("++++++ C");
                            feat.setCrs(crs);
                            System.out.println("++++++ D");
                            feat.setName("feature" + i);
                            System.out.println("++++++ E");
                            features.add(feat);
                        }

                        FeatureCollection<SimpleFeatureType, SimpleFeature> simpleFeatures = FacesMapUtils.getSimpleFeaturesFromFeatures(features);
                        System.out.println("++++++ F");
                        List<SimpleFeature> sfList = new ArrayList<SimpleFeature>();
                        FeatureIterator<SimpleFeature> featIt = simpleFeatures.features();
                        while(featIt.hasNext()) {
                            sfList.add(featIt.next());
                        }
                        System.out.println("Taille collection Dans mapfaces : " + simpleFeatures.size());
                        final ValueExpression ve = comp.getValueExpression("value");
                        if (ve != null) {
                            if (ve.getValue(context.getELContext()) instanceof Object) {
                                ve.setValue(context.getELContext(), sfList);
                            }
                        }
                        comp.setValue(sfList);
                    }
                }
            }
            System.out.println("FIN DECODE");
        } catch (ParseException ex) {
            Logger.getLogger(SvgLayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FactoryException ex) {
            LOGGER.log(Level.SEVERE, "Invalid SRS definition : " + ex);
            return;
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getRendersChildren() {
        return false;
    }
}
