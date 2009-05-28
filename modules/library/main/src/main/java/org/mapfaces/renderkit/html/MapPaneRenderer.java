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

package org.mapfaces.renderkit.html;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.geotoolkit.map.MapContext;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.component.layer.UIFeatureLayer;
import org.mapfaces.component.layer.UIMapContextLayer;
import org.mapfaces.component.layer.UIWmsLayer;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.models.layer.DefaultMapContextLayer;
import org.mapfaces.models.layer.FeatureLayer;
import org.mapfaces.models.layer.MapContextLayer;
import org.mapfaces.models.layer.WmsLayer;
import org.mapfaces.util.ContextFactory;
import org.mapfaces.util.DefaultContextFactory;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 */
public class MapPaneRenderer extends WidgetBaseRenderer {

    private static final Logger LOGGER = Logger.getLogger(WidgetBaseRenderer.class.getName());

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeBegin(context, component);
        final UIMapPane comp = (UIMapPane) component;
        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] MapPaneRenderer ENCOD BEGIN");
        }
        final String clientId = comp.getClientId(context);
        final Context model;
        if (comp.getModel() != null && comp.getModel() instanceof Context) {
            model = (Context) comp.getModel();
        } else {
            //The model context is null or not a Context instance
            throw new UnsupportedOperationException("The model context is null or not supported yet !");
        }

        String style = getStyle();
        String styleClass = getStyleClass();
        final ResponseWriter writer = context.getResponseWriter();

        final String height = model.getWindowHeight();
        final String width = model.getWindowWidth();

        if (comp.getMaxExtent() == null) {
            comp.setMaxExtent(model.getMinx() + "," + model.getMiny() + "," + model.getMaxx() + "," + model.getMaxy());
        }

        final String id = (String) comp.getAttributes().get("id");
        if (style == null) {
            style = "width:" + width + "px;height:" + height + "px;z-index:0;";
        } else {
            style = "width:" + width + "px;height:" + height + "px;z-index:0;" + style;
        }

        if (this.debug) {
            LOGGER.log(Level.INFO, "\t the style property of the MapPane is " + style);
        }
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "id");
        if (styleClass == null) {
            writer.writeAttribute("class", "mfMapPane", "styleClass");
        } else {
            writer.writeAttribute("class", styleClass, "styleClass");
        }
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        /*MapPane ViewPort div*/
        }
        writer.startElement("div", comp);

        if (id != null) {
            writer.writeAttribute("id", id + "_MapFaces_Viewport", "id");
        } else {
            writer.writeAttribute("id", clientId + "_MapFaces_Viewport", "id");
        }
        writer.writeAttribute("style", "overflow: hidden;position:relative;width:100%;height:100%;", "style");
        writer.writeAttribute("class", "mfMapViewport", "styleClass");

        /*Layers Container div*/
        writer.startElement("div", comp);

        if (id != null) {
            writer.writeAttribute("id", id + "_MapFaces_Container", "id");
        } else {
            writer.writeAttribute("id", clientId + "_MapFaces_Container", "id");
        }
        writer.writeAttribute("style", "top:0px;left:0px;position:absolute;z-index: 0;", "style");
        
        final MapContext mapcontext = (MapContext) comp.getValue();
        if (mapcontext != null) {
            //adding all the MapContext layers  into an allInOne layer.
            final ContextFactory contextFactory = new DefaultContextFactory();
            model.clearMapContextLayers();
            DefaultMapContextLayer mcLayer = (DefaultMapContextLayer) contextFactory.createDefaultMapContextLayer(FacesUtils.getNewIndex(model));
            mcLayer.setMapContext(mapcontext);
            model.addLayer((MapContextLayer) mcLayer);
        }

        final List<Layer> layers = model.getLayers();
      
        final String srs = model.getSrs();

        comp.setAjaxCompId(FacesUtils.getParentUIModelBase(context, component).getAjaxCompId());

        removeChildren(context, component);

        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] The context of the Mappane contains " + layers.size() + " layers.");
        }

        for (final Layer temp : layers) {
            if (this.debug) {
                LOGGER.log(Level.INFO, "[DEBUG] The current layer is a :  " + temp.getType() + " layer.");
            }
            if (temp != null && temp.getType() != null) {
                switch (temp.getType()) {
                    case DEFAULT:
                        break;
                    case WMS:
                        final UIWmsLayer layer = new UIWmsLayer();
                        layer.setModel((AbstractModelBase) model);
                        if (temp.getId() != null) {
                            layer.getAttributes().put("id", FacesUtils.getParentUIModelBase(context, component).getId() + "_" + comp.getId() + "_" + temp.getId());
                        } else {
                            temp.setId(layer.getId());
                        }
                        comp.getChildren().add(layer);
                        temp.setCompId(layer.getClientId(context));
                        layer.setLayer((WmsLayer) temp);
                        break;
                    case WFS:
                        break;
                    case WCS:
                        break;
                    case SLD:
                        break;
                    case FES:
                        break;
                    case GML:
                        break;
                    case KML:
                        break;
                    case MAPCONTEXT:
                        MapContextLayer tmpMContext = (MapContextLayer) temp;
                        UIMapContextLayer uiMCLayer = new UIMapContextLayer();
                        uiMCLayer.setModel((AbstractModelBase) model);
                        uiMCLayer.getAttributes().put("id", FacesUtils.getParentUIModelBase(context, component).getId() + "_" + comp.getId() + "_" + temp.getId());
                        tmpMContext.setCompId(uiMCLayer.getClientId(context));
                        uiMCLayer.setLayer(tmpMContext);
                        if (tmpMContext.getMapContext() != null ) {
                             uiMCLayer.setValue(tmpMContext.getMapContext());
                        }
                        comp.getChildren().add(uiMCLayer);
                        break;
                    case FEATURE:
                        FeatureLayer tmpfeature = (FeatureLayer) temp;
                        UIFeatureLayer uiFLayer = new UIFeatureLayer();
                        uiFLayer.setModel((AbstractModelBase) model);
                        uiFLayer.setImage(tmpfeature.getImage());
                        uiFLayer.setFeatures(tmpfeature.getFeatures());
                        uiFLayer.setRotation(tmpfeature.getRotation());
                        uiFLayer.setSize(tmpfeature.getSize());
                        uiFLayer.setBindingIndex(tmpfeature.getGroupId());

                        if (temp.getId() != null) {
                            uiFLayer.getAttributes().put("id", FacesUtils.getParentUIModelBase(context, component).getId() + "_" + comp.getId() + "_" + tmpfeature.getId());
                        } else {
                            tmpfeature.setId(uiFLayer.getId());
                        }
                        comp.getChildren().add(uiFLayer);
                        tmpfeature.setCompId(uiFLayer.getClientId(context));
                        uiFLayer.setLayer(tmpfeature);
                        break;
                    default:
                        break;
                }
            }
        }

        writer.flush();
        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] La mappane a  " + comp.getChildren().size() + " fils");
        }
        //Setting the model to all children of the MapPane component
        for (final UIComponent tmp : comp.getChildren()) {
            if (tmp instanceof UIWidgetBase) {
                ((UIWidgetBase) tmp).setModel((AbstractModelBase) model);
                ((UIWidgetBase) tmp).setDebug(this.debug);
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeEnd(context, component);
        final UIMapPane comp = (UIMapPane) component;
        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] MapPaneRenderer ENCOD END");
        }
        final Context model;
        if (comp.getModel() != null && comp.getModel() instanceof Context) {
            model = (Context) comp.getModel();
        } else {
            //The model context is null or not an Context instance
            throw new UnsupportedOperationException("The model context is null or not supported yet !");
        }

        final ResponseWriter writer = context.getResponseWriter();
        // ... ending the started elements
        writer.endElement("div");
        writer.endElement("div");
        /* Construct OpenLayers Map Object */
        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", "text/javascript");

        //suppression des ":" pour nommer l'objet javascript correspondant correctement
//        String jsObject = FacesUtils.getParentUIModelBase(context, component).getClientId(context);
        String jsObject = comp.getClientId(context);
        if (jsObject.contains(":")) {
            jsObject = jsObject.replace(":", "");
        }

        /*Logger.getLogger(MapPaneRenderer.class.getName()).log(Level.INFO, "The model is instanceof "+model);
        Logger.getLogger(MapPaneRenderer.class.getName()).log(Level.INFO, "SRS = "+model.getSrs());
        Logger.getLogger(MapPaneRenderer.class.getName()).log(Level.INFO, "LayersCompId = "+model.getLayersCompId());
        Logger.getLogger(MapPaneRenderer.class.getName()).log(Level.INFO, "Parent UIModelBase = "+FacesUtils.getParentUIModelBase(context, component));
        Logger.getLogger(MapPaneRenderer.class.getName()).log(Level.INFO, "MapPane component = "+comp);
        */
        writer.write(new StringBuilder("").append("if(!window.maps){window.maps = {};}\n").
                append("window.maps.").append(jsObject).append(" = 'vide';\n").
                append("window.controlToAdd" + jsObject + " = []; \n ").
                append("window.loadMap" + jsObject + " = function() {\n").
                append("    if (typeof " + jsObject + "_mapOptions == 'undefined' " +
                "               && window.OpenLayers && window.OpenLayers.Projection " +
                "               && window.OpenLayers.Size && window.OpenLayers.Bounds) { \n").
                append("       var " + jsObject + "_mapOptions = {\n").
                append("                           id:'").append(jsObject).append("    ',\n").
                append("                           controls:[],\n").
                append("                           projection: new OpenLayers.Projection('").
                append(model.getSrs().toUpperCase()).append("'),\n").
                append("                           size: new OpenLayers.Size('").
                append(model.getWindowWidth()).append("','").
                append(model.getWindowHeight()).append("'),\n").
                append("                           maxExtent: new OpenLayers.Bounds(").
                append(comp.getMaxExtent()).append("),\n").
                append("                           currentExtent: new OpenLayers.Bounds(").
                append(model.getMinx()).append(",").append(model.getMiny()).append(",").
                append(model.getMaxx()).append(",").append(model.getMaxy()).append("),\n").
                append("                           maxResolution: 'auto',\n").
                append("                           theme:  null ,\n").
                append("                           fractionnalZoom:  true ,\n").
                append("                           layersName:  '").append(model.getLayersCompId()).append("',\n").
                append("                           mfAjaxCompId:'").append(FacesUtils.getParentUIModelBase(context, component).getAjaxCompId()).append("',\n").
                append("                           mfFormId:'").append(FacesUtils.getFormId(context, component)).append("',\n").
                append("                           mfRequestId:'updateBboxOrWindow'\n").
                append("                       }; ").
                append("    } else if (window.OpenLayers && window.OpenLayers.Bounds) { \n").
                append("         " + jsObject + "_mapOptions.layersName = '").append(model.getLayersCompId()).append("' ;\n").
                append("         " + jsObject + "_mapOptions.currentExtent = new OpenLayers.Bounds(").
                append(model.getMinx()).append(",").append(model.getMiny()).append(",").
                append(model.getMaxx()).append(",").append(model.getMaxy()).append(");\n").
                append("    } \n ").
                append("    if (window.OpenLayers && window.OpenLayers.Map) { \n ").
                append("         window.").append(jsObject).append("     = new OpenLayers.Map('").append(comp.getClientId(context)).append("'," + jsObject + "_mapOptions); \n ").
                append("         window.maps.").append(jsObject).append("     = window.").append(jsObject).append("; \n ").
                append("    }\n").
                append("};\n").
                append("window.loadMap" + jsObject + "();\n").
                toString());

        writer.endElement("script");
        writer.endElement("div");
        writer.flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        super.decode(context, component);
        final UIMapPane comp = (UIMapPane) component;
        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] MapPaneRenderer DECODE");
        }
        final UIContext contextComp = (UIContext) FacesUtils.getParentUIContext(context, comp);
        final Context tmp = (Context) contextComp.getModel();

        if (context.getExternalContext().getRequestParameterMap() != null) {
            final Map params = context.getExternalContext().getRequestParameterMap();
            final String render = (String) params.get("render");
            if (render == null || render.equals("true")) {
                comp.setInitDisplay(true);
            }
        }

        contextComp.setModel((AbstractModelBase) tmp);
        comp.setModel((AbstractModelBase) tmp);
    }
}
