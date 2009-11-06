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
        final UIMapPane uiMapPane = FacesMapUtils.getUIMapPane(context, component);
        if (uiMapPane != null) {
                mapJsVariable = FacesMapUtils.getJsVariableFromClientId(uiMapPane.getClientId(context));
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

//        writer.startElement(HTML.DIV_ELEM, comp);
//        writer.writeAttribute(HTML.style_ATTRIBUTE, "background-image:url(resource.jsf?r=/org/mapfaces/resources/img/save.gif);" +
//                "float:right;height:24px;margin-left:25px;position:relative;top:2px;width:24px;", HTML.style_ATTRIBUTE);
//        writer.endElement(HTML.DIV_ELEM);
        
        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, "text/javascript", "text/javascript");

        final StringBuilder stringBuilder = new StringBuilder(uiMapPane.getAddLayersScript());

        stringBuilder.append("window.layerToAdd").append(mapJsVariable).append(".push(function() {");
        // The projection is notified once on the Input Hidden. All the features will be from the same projection for a Map.
        /* stringBuilder.append("if($('"+ clientId + "')){" + mapJsVariable + ".getProjection();}"); */

        final String functionAddName = "addFeature_" + compId;
        final String functionUpdateName = "updateFeature_" + compId;
        final String functionRemoveName = "removeFeature_" + compId;
        final String layerName = "window." + compId;
        stringBuilder.append(layerName + " = new OpenLayers.Layer.MapFaces.Vector('" + compId + "', ['mainForm']);");
        // we create the Vector Layer with OpenLayers.
        // We register an event triggered when a feature is created.
     /*   stringBuilder.append(layerName + ".events.register('featureadded', " + compId + "" + ", " + functionAddName + ");");
        stringBuilder.append(layerName + ".events.register('featuremodified', " + compId + "" + ", " + functionUpdateName + ");");
        stringBuilder.append(layerName + ".events.register('featureremoved', " + compId + "" + ", " + functionRemoveName + ");"); */
        
        // we add the layer to the mapPane.
        stringBuilder.append(mapJsVariable + ".addLayer(" + layerName + ");");
                
        
        // If we want to send Serialized features to the client, and if the Value attribute is set with a List...
        if (!comp.isCliToServOnly() &&(comp.getValue() != null) && (comp.getValue() instanceof List)) {
           /* final List<SimpleFeature> featList = (List) comp.getValue();
            if (featList.size() > 0) {
                stringBuilder.append("var parser_" + compId + ";var wkt_" + compId + ";var geometry_" + compId + ";var feature_" + compId + ";");
                // Creat
                final WKTWriter wktWriter = new WKTWriter();
                for (final SimpleFeature feature : featList) {
                    stringBuilder.append("parser_" + compId + " = new OpenLayers.Format.WKT();");
                    stringBuilder.append("wkt_" + compId + "='").append(wktWriter.write((Geometry) feature.getDefaultGeometry()) + "';");
                    stringBuilder.append("geometry_" + compId + " = parser_").append(compId + ".read(wkt_" + compId + ");");
                    stringBuilder.append("feature_" + compId).append(" = new OpenLayers.Feature.Vector(geometry_" + compId + ");");
                    stringBuilder.append(layerName + ".addFeatures(feature_").append(compId + ");");

                    // TODO: delete Hidden Field content.
                }
            } */
        }
        stringBuilder.append("});");
        uiMapPane.setAddLayersScript(stringBuilder.toString());
        writer.endElement(HTML.SCRIPT_ELEM);
        writer.flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
    
            super.decode(context, component);

            final ExternalContext ext = context.getExternalContext();
            final UISvgLayer comp = (UISvgLayer) component;
            final Map parameterMap = ext.getRequestParameterMap();
            
            if (this.debug) {
                LOGGER.log(Level.INFO, "[DEBUG] SvgLayerRenderer DECODE");
            }
            final UIContext contextComp = (UIContext) FacesMapUtils.getParentUIContext(context, comp);
            final Context tmp = (Context) contextComp.getModel();

            final Object valueObj = FacesUtils.getRequestParameterValue(context, "org.mapfaces.ajax.AJAX_COMPONENT_VALUE");
            final Object operationObj = FacesUtils.getRequestParameterValue(context, "org.mapfaces.ajax.AJAX_CONTAINER_ID");
            final Object crsObj = FacesUtils.getRequestParameterValue(context, "crs");

            
            if ((valueObj instanceof String) && (operationObj instanceof String) && (crsObj instanceof String)) {
                try {
                    final String value = (String) valueObj;
                    final String operation = (String) operationObj;
                    final String[] valueParse = value.split(";");
                    if ("featureAdded".equals(operation) && (valueParse.length == 2)) {
                        final Feature feature = createFeature(valueParse[0], valueParse[1], CRS.decode((String) crsObj));
                        final SimpleFeature sFeature = FacesMapUtils.getSimpleFeatureFromFeature(feature, 0);
                        setValueExpression(comp, context, "featureAdded", sFeature);
                    } else if ("featureRemoved".equals(operation) && (valueParse.length == 2)) {
                        final Feature feature = createFeature(valueParse[0], valueParse[1], CRS.decode((String) crsObj));
                        final SimpleFeature sFeature = FacesMapUtils.getSimpleFeatureFromFeature(feature, 0);
                        setValueExpression(comp, context, "featureRemoved", sFeature);
                    } else if ("featureUpdated".equals(operation) && (valueParse.length == 3)) {
                        final Feature featureBefore = createFeature(valueParse[0], valueParse[1], CRS.decode((String) crsObj));
                        final SimpleFeature sFeatureBefore = FacesMapUtils.getSimpleFeatureFromFeature(featureBefore, 0);
                        setValueExpression(comp, context, "featureBeforeUpdate", sFeatureBefore);

                        final Feature featureAfter = createFeature(valueParse[0], valueParse[2], CRS.decode((String) crsObj));
                        final SimpleFeature sFeatureAfter = FacesMapUtils.getSimpleFeatureFromFeature(featureAfter, 1);
                        setValueExpression(comp, context, "featureAfterUpdate", sFeatureAfter);
                    }
                
                /*   if (!value.isEmpty()) {
                // ABRUTI !!!
                final String[] featList = value.split(";");
                final List<Feature> featuresAdded = new ArrayList<Feature>();
                final List<Feature> featuresUpdated = new ArrayList<Feature>();
                final List<Feature> featuresRemoved = new ArrayList<Feature>();
                if ((featList.length > 1) && ((featList.length - 1) % 3 == 0)) {
                final WKTReader wktReader = new WKTReader();
                final CoordinateReferenceSystem crs = CRS.decode(featList[1]);
                int i = 1;
                String operation;
                String featString;
                String featId;
                while(i < featList.length) {
                operation = featList[i];
                featId = featList[i++];
                featString = featList[i++];
                final Feature feat = new DefaultFeature();
                feat.setAttributes(new HashMap<String, Serializable>());
                feat.setGeometry(wktReader.read(featString));
                feat.getAttributes().put("geometry", wktReader.read(featString));
                feat.setCrs(crs);
                feat.setName(featId);
                if ("a".equals(operation)) featuresAdded.add(feat);
                else if("d".equals(operation)) featuresRemoved.add(feat);
                else featuresUpdated.add(feat);
                }
                final List<SimpleFeature> sfAddedList = getListSimpleFeaturesFromFeatures(featuresAdded);
                final List<SimpleFeature> sfUpdatedList = getListSimpleFeaturesFromFeatures(featuresUpdated);
                final List<SimpleFeature> sfRemovedList = getListSimpleFeaturesFromFeatures(featuresRemoved);
                setValueExpression(comp, context, "featuresAdded", sfAddedList);
                comp.setFeaturesAdded(sfAddedList);
                setValueExpression(comp, context, "featuresUpdated", sfUpdatedList);
                comp.setFeaturesAdded(sfUpdatedList);
                setValueExpression(comp, context, "featuresRemoved", sfRemovedList);
                comp.setFeaturesAdded(sfRemovedList);
                }
                }
                } */
                } catch (ParseException ex) {
                    Logger.getLogger(SvgLayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAuthorityCodeException ex) {
                        Logger.getLogger(SvgLayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FactoryException ex) {
                Logger.getLogger(SvgLayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
             /*   if (!value.isEmpty()) {
                    // ABRUTI
                        final String[] featList = value.split(";");
                        final List<Feature> featuresAdded = new ArrayList<Feature>();
                        final List<Feature> featuresUpdated = new ArrayList<Feature>();
                        final List<Feature> featuresRemoved = new ArrayList<Feature>();
                        if ((featList.length > 1) && ((featList.length - 1) % 3 == 0)) {
                            final WKTReader wktReader = new WKTReader();
                            final CoordinateReferenceSystem crs = CRS.decode(featList[1]);
                            int i = 1;
                            String operation;
                            String featString;
                            String featId;
                            while(i < featList.length) {
                                operation = featList[i];
                                featId = featList[i++];
                                featString = featList[i++];

                                final Feature feat = new DefaultFeature();
                                feat.setAttributes(new HashMap<String, Serializable>());
                                feat.setGeometry(wktReader.read(featString));
                                feat.getAttributes().put("geometry", wktReader.read(featString));
                                feat.setCrs(crs);
                                feat.setName(featId);
                                
                                if ("a".equals(operation)) featuresAdded.add(feat);
                                else if("d".equals(operation)) featuresRemoved.add(feat);
                                else featuresUpdated.add(feat);
                            }

                            final List<SimpleFeature> sfAddedList = getListSimpleFeaturesFromFeatures(featuresAdded);
                            final List<SimpleFeature> sfUpdatedList = getListSimpleFeaturesFromFeatures(featuresUpdated);
                            final List<SimpleFeature> sfRemovedList = getListSimpleFeaturesFromFeatures(featuresRemoved);

                            setValueExpression(comp, context, "featuresAdded", sfAddedList);
                            comp.setFeaturesAdded(sfAddedList);
                            setValueExpression(comp, context, "featuresUpdated", sfUpdatedList);
                            comp.setFeaturesAdded(sfUpdatedList);
                            setValueExpression(comp, context, "featuresRemoved", sfRemovedList);
                            comp.setFeaturesAdded(sfRemovedList);
                        }
                    }
                } */
            }
       
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getRendersChildren() {
        return false;
    }

    private Feature createFeature(String featId, String geometry, CoordinateReferenceSystem crs) throws ParseException {
        WKTReader wktReader = new WKTReader();
        final Feature feat = new DefaultFeature();
        feat.setAttributes(new HashMap<String, Serializable>());
        feat.setGeometry(wktReader.read(geometry));
        feat.getAttributes().put("geometry", wktReader.read(geometry));
        feat.setCrs(crs);
        feat.setName(featId);

        return feat;
    }

    /**
     * Return a Geotk SimpleFeatures List from a List of MapFaces Features.
     * @param featList The list of MapFaces Features.
     * @return List of Geotk SimpleFeatures.
     */
    private List<SimpleFeature> getListSimpleFeaturesFromFeatures(List<Feature> featList) {
        final FeatureCollection<SimpleFeatureType, SimpleFeature> simpleFeatures = FacesMapUtils.getSimpleFeaturesFromFeatures(featList);
        final FeatureIterator<SimpleFeature> featIt = simpleFeatures.features();
        final List<SimpleFeature> sfList = new ArrayList<SimpleFeature>();
        while(featIt.hasNext()) {
            sfList.add(featIt.next());
        }

        return sfList;
    }

    /**
     * Set the value expression for the key parameter.
     * @param comp The UISvgLayer component.
     * @param context FacesContext.
     * @param key Key of the Value Expression
     * @param value Value for the ValueExpression.
     */
    private void setValueExpression(UISvgLayer comp, FacesContext context, String key, Object value) {
        final ValueExpression ve = comp.getValueExpression(key);
        if (ve != null) {
            ve.setValue(context.getELContext(), value);
        }
    }
}
