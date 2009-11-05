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
import com.vividsolutions.jts.io.WKTWriter;
import org.mapfaces.renderkit.html.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.geotoolkit.referencing.CRS;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.layer.UISvgLayer;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.BasicFeature;
import org.mapfaces.models.Context;
import org.mapfaces.models.DefaultFeature;
import org.mapfaces.models.Feature;
import org.mapfaces.share.utils.RendererUtils.HTML;
import org.mapfaces.util.FacesMapUtils;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

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

        super.encodeBegin(context, component);
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
        writer.endElement((String) HTML.INPUT_ELEM);
        
        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, "text/javascript", "text/javascript");
        // SVG Layer for OpenLayers is created.
        final String functionName = "addFeature_" + compId;
        final String layerName = "window." + compId;
        writer.write(new StringBuilder("").append("" +
                layerName + " = new OpenLayers.Layer.Vector('" + compId + "');" +
                layerName + ".events.register('featureadded', " + compId + "" + ", " + functionName + ");" +
                mapJsVariable + ".addLayers(" + layerName + ");" +
                "if($('"+ clientId + "')){$('" + clientId + "').value = 'a;' + " + mapJsVariable + ".getProjection();}" ).toString());

        // When a feature is created, we keep the coordinates in a hidden field.
        writer.write(new StringBuilder("").append("" +
                "function " + functionName + "(event){" + "if($('"+ clientId + "')){" +
                        "$('" + clientId + "').value=$('" + clientId + "').value" +
                        "+ ';' + event.feature.geometry;}}").toString());

        if (!comp.isCliToServOnly() &&(comp.getValue() != null) && (comp.getValue() instanceof List)) {
            final List<SimpleFeature> featList = (List) comp.getValue();
            if (featList.size() > 0) {
                writer.write("var parser_" + compId + ";var wkt_" + compId + ";var geometry_" + compId + ";var feature_" + compId + ";");
                final WKTWriter wktWriter = new WKTWriter();
                for (final SimpleFeature feature : featList) {
                    writer.write(new StringBuilder("").append("parser_" + compId + " = new OpenLayers.Format.WKT();" +
                            "wkt_" + compId + " = '" + wktWriter.write((Geometry) feature.getDefaultGeometry()) + "';" +
                            "geometry_" + compId + " = parser_" + compId + ".read(wkt_" + compId + ");" +
                            "feature_" + compId + " = new OpenLayers.Feature.Vector(geometry_" + compId + ");" +
                            layerName + ".addFeatures(feature_" + compId + ");").toString());
                }
            }
        }
        
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
            
            if (this.debug) {
                LOGGER.log(Level.INFO, "[DEBUG] SvgLayerRenderer DECODE");
            }
            final UIContext contextComp = (UIContext) FacesMapUtils.getParentUIContext(context, comp);
            final Context tmp = (Context) contextComp.getModel();

            final Map<String, String> params = context.getExternalContext().getRequestParameterMap();
            if (params != null) {
                final String value = (String) parameterMap.get(comp.getClientId(context));

                if ((value != null) && !value.isEmpty()) {
                    if (value instanceof String) {
                        final String[] featList = value.split(";");
                        final List<Feature> features = new ArrayList<Feature>();
                        if (featList.length > 2) {
                            final String operation = featList[0];
                            final WKTReader wktReader = new WKTReader();
                            final CoordinateReferenceSystem crs = CRS.decode(featList[1]);
                            for (int i = 2; i < featList.length; i++) {
                                Feature feat = new DefaultFeature();
                                feat.setAttributes(new HashMap<String, Serializable>());
                                feat.setGeometry(wktReader.read(featList[i]));                                
                                feat.getAttributes().put("geometry", wktReader.read(featList[i]));
                                feat.setCrs(crs);
                                feat.setName("feature" + i);
                                features.add(feat);
                            }

                            final FeatureCollection<SimpleFeatureType, SimpleFeature> simpleFeatures = FacesMapUtils.getSimpleFeaturesFromFeatures(features);
                            final List<SimpleFeature> sfList = new ArrayList<SimpleFeature>();
                            final FeatureIterator<SimpleFeature> featIt = simpleFeatures.features();
                            while(featIt.hasNext()) {
                                sfList.add(featIt.next());
                            }
                            final ValueExpression ve = comp.getValueExpression("value");
                            if (ve != null) {
                                ve.setValue(context.getELContext(), sfList);
                            }
                            comp.setValue(sfList);
                        }
                    }
                }
            }
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
