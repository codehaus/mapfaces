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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.geotoolkit.data.collection.FeatureCollection;
import org.geotoolkit.data.collection.FeatureIterator;
import org.geotoolkit.map.MapBuilder;
import org.geotoolkit.map.MapContext;
import org.geotoolkit.referencing.CRS;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.layer.UISvgLayer;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.Context;
import org.mapfaces.models.DefaultContext;
import org.mapfaces.models.DefaultFeature;
import org.mapfaces.models.Feature;
import org.mapfaces.models.layer.SvgLayer;
import org.mapfaces.share.utils.FacesUtils;
import org.mapfaces.share.utils.RendererUtils.HTML;
import org.mapfaces.util.ContextFactory;
import org.mapfaces.util.DefaultContextFactory;
import org.mapfaces.util.FacesMapUtils;
import org.mapfaces.util.Utils;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 *
 * @author leopratlong (Geomatys)
 * @since 0.3
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
        // Find server ID.
        final String compId = comp.getId();
        
        final String reRender = comp.getReRender();
        final String idsToRefresh;
        if (reRender != null) idsToRefresh = Utils.buildRerenderStringFromString(FacesUtils.getFormId(context, comp), reRender);
        else idsToRefresh = "";

        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, "text/javascript", "text/javascript");
        final StringBuilder stringBuilder = new StringBuilder(uiMapPane.getAddLayersScript());
        stringBuilder.append("window.layerToAdd").append(mapJsVariable).append(".push(function() {");
        final String layerName = "window." + compId;
        stringBuilder.append(layerName + " = new OpenLayers.Layer.MapFaces.Vector('" + compId + "', {formId:'" + FacesUtils.getFormId(context, comp) + "'});");
        // If we want to send Serialized features to the client, and if the Value attribute is set with a List...
        if (!comp.isCliToServOnly() && (comp.getValue() != null) && (comp.getValue() instanceof List)) {
            final List<SimpleFeature> featList = (List) comp.getValue();
            if (featList.size() > 0) {
                stringBuilder.append("var parser_" + compId + ";");
                // WKTWriter read a geometry and write WKT string.
                final WKTWriter wktWriter = new WKTWriter();
                for (final SimpleFeature feature : featList) {
                    stringBuilder.append("parser_" + compId + " = new OpenLayers.Format.WKT();")
                            .append(layerName + ".addFeatures(parser_" + compId + ".read('" + wktWriter.write((Geometry) feature.getDefaultGeometry()) + "'));");
                }
            }
        }
        stringBuilder.append(mapJsVariable).append(".addLayer(" + layerName + ");");
        stringBuilder.append(layerName + ".initReRender('" + idsToRefresh + "');");
        stringBuilder.append(layerName + ".activeEvents(true);").append("});");
        uiMapPane.setAddLayersScript(stringBuilder.toString());
        writer.endElement(HTML.SCRIPT_ELEM);
        writer.flush();

        if (comp.getModel() instanceof DefaultContext) {
            final Context ctx = (DefaultContext) comp.getModel();
            if(comp.getLayer() == null){
                final SvgLayer svgLayer = (SvgLayer) new DefaultContextFactory().createDefaultSvgLayer();
                svgLayer.setName("svgLayer_" + compId);
                svgLayer.setCompId(compId);
                svgLayer.setId(compId);
                final String layerTitle;
                if (comp.getTitle() != null) {
                    layerTitle = comp.getTitle();
                } else {
                    layerTitle = "SVG Layer";
                }
                svgLayer.setTitle(layerTitle);
                Double opacity = comp.getOpacity();
                if ((opacity > 1) && (opacity < 0)) {
                    opacity = 0.0;
                }
                svgLayer.setOpacity(opacity.toString());
                ctx.addLayer(svgLayer);
            }
        }
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

                    //invoke methodBinding on action and actionListener if not null.
                    if (comp.getActionExpression() != null) {
                        comp.getActionExpression().invoke(context.getELContext(), null);
                    }
                    
                } catch (ParseException ex) {
                    Logger.getLogger(SvgLayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAuthorityCodeException ex) {
                        Logger.getLogger(SvgLayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FactoryException ex) {
                Logger.getLogger(SvgLayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
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
