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
import org.mapfaces.share.utils.FacesUtils;
import org.mapfaces.share.utils.RendererUtils.HTML;
import org.mapfaces.util.FacesMapUtils;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 *
 * @author leopratlong (Geomatys)
 */
public class SvgLayerRenderer extends LayerRenderer {
 
    private static final Logger LOGGER = Logger.getLogger(EditionBarRenderer.class.getName());

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {

        super.encodeBegin(context, component);
        
        // Find UIMapPane refers to this widget.
        final String mapJsVariable;
        final UIMapPane uIMapPane = FacesMapUtils.getUIMapPane(context, component);
        if (uIMapPane != null) {
                mapJsVariable = FacesMapUtils.getJsVariableFromClientId(uIMapPane.getClientId(context));
        } else {
            LOGGER.log(Level.SEVERE, "This widget doesn't referred to an UIMapPane so it can't be rendered !!!");
            component.setRendered(false);
            mapJsVariable = null;
            return;
        }

        final UISvgLayer comp = (UISvgLayer) component;
        // Find client ID then server ID.
        final String clientId = comp.getClientId(context);
        final String compId = comp.getId();

        // We write an Input hidden element to stock the serialized features.
        writer.startElement((String) HTML.INPUT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, HTML.INPUT_TYPE_HIDDEN, HTML.TYPE_ATTR);
        writer.writeAttribute(HTML.NAME_ATTRIBUTE, clientId, HTML.NAME_ATTRIBUTE);
        writer.writeAttribute(HTML.id_ATTRIBUTE, clientId, HTML.id_ATTRIBUTE);
        writer.endElement((String) HTML.INPUT_ELEM);

        writer.startElement(HTML.DIV_ELEM, comp);
        writer.writeAttribute(HTML.style_ATTRIBUTE, "background-image:url(resource.jsf?r=/org/mapfaces/resources/img/save.gif);" +
                "float:right;height:24px;margin-left:25px;position:relative;top:2px;width:24px;", HTML.style_ATTRIBUTE);
        writer.endElement(HTML.DIV_ELEM);
        
        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, "text/javascript", "text/javascript");

        // The projection is notified once on the Input Hidden. All the features will be from the same projection for a Map.
        writer.write("if($('"+ clientId + "')){" + mapJsVariable + ".getProjection();}");
        
        final String functionAddName = "addFeature_" + compId;
        final String functionUpdateName = "updateFeature_" + compId;
        final String layerName = "window." + compId;
        writer.write(layerName + " = new OpenLayers.Layer.Vector('" + compId + "');");
        // we create the Vector Layer with OpenLayers.
        // We register an event triggered when a feature is created.
        writer.write(layerName + ".events.register('featureadded', " + compId + "" + ", " + functionAddName + ");");
        writer.write(layerName + ".events.register('featuremodified', " + compId + "" + ", " + functionUpdateName + ");");

        writer.write(new StringBuilder("").append("window.layerToAdd").append(mapJsVariable).append(".push(function() {").toString());
        // we add the layer to the mapPane.
        writer.write(mapJsVariable + ".addLayers(" + layerName + ");");
        writer.write("});");
       
        
        // When a feature is created, we keep the coordinates in an hidden field.
        writer.write(new StringBuilder("").append(
                "function " + functionAddName + "(event){").append(
                "if($('"+ clientId + "')){").append(
                "$('" + clientId + "').value=$('" + clientId + "').value + ';' + event.feature.geometry;}}").toString());

        // When a feature is modified, we keep the feature id plus the new geometry.
        writer.write(new StringBuilder("").append(
                "function " + functionAddName + "(event){").append(
                "if($('"+ clientId + "')){").append(
                "$('" + clientId + "').value=$('" + clientId + "').value + ';' + event.feature.geometry;}}").toString());

        // If we want to send Serialized features to the client, and if the Value attribute is set with a List...
        if (!comp.isCliToServOnly() &&(comp.getValue() != null) && (comp.getValue() instanceof List)) {
            final List<SimpleFeature> featList = (List) comp.getValue();
            if (featList.size() > 0) {
                writer.write("var parser_" + compId + ";var wkt_" + compId + ";var geometry_" + compId + ";var feature_" + compId + ";");
                // Creat
                final WKTWriter wktWriter = new WKTWriter();
                for (final SimpleFeature feature : featList) {
                    writer.write("parser_" + compId + " = new OpenLayers.Format.WKT();");
                    writer.write(new StringBuilder("").append("wkt_" + compId + "='").append(wktWriter.write((Geometry) feature.getDefaultGeometry()) + "';").toString());
                    writer.write(new StringBuilder("").append("geometry_" + compId + " = parser_").append(compId + ".read(wkt_" + compId + ");").toString());
                    writer.write(new StringBuilder("").append("feature_" + compId).append(" = new OpenLayers.Feature.Vector(geometry_" + compId + ");").toString());
                    writer.write(new StringBuilder("").append(layerName + ".addFeatures(feature_").append(compId + ");").toString());
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

            final Object valueObj = FacesUtils.getRequestParameterValue(context, comp.getClientId(context));
            if (valueObj instanceof String) {
                final String value = (String) valueObj;

                if (!value.isEmpty()) {
                    // ABRUTI
                        final String[] featList = value.split(";");
                        final List<Feature> features = new ArrayList<Feature>();
                        if (featList.length > 2) {
                            final String operation = featList[0];
                            final WKTReader wktReader = new WKTReader();
                            final CoordinateReferenceSystem crs = CRS.decode(featList[1]);
                            for (int i = 2; i < featList.length; i++) {
                                final Feature feat = new DefaultFeature();
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
                            final ValueExpression ve = comp.getValueExpression("featureAdded");
                            if (ve != null) {
                                ve.setValue(context.getELContext(), sfList);
                            }
                            comp.setFeaturesAdded(sfList);
                        }
                    }
                }
            }
        catch (ParseException ex) {
            Logger.getLogger(SvgLayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (NoSuchAuthorityCodeException ex) {
            Logger.getLogger(SvgLayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FactoryException ex) {
            Logger.getLogger(SvgLayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
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
