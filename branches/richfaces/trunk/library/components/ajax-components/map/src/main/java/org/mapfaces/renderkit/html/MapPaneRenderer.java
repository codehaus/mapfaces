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
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.geotoolkit.map.MapBuilder;
import org.mapfaces.share.utils.RendererUtils.HTML;
import org.geotoolkit.map.MapContext;
import org.geotoolkit.map.MapLayer;
import org.mapfaces.component.UILayer;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.component.layer.UIFeatureLayer;
import org.mapfaces.component.layer.UIMapContextLayer;
import org.mapfaces.component.layer.UISvgLayer;
import org.mapfaces.component.layer.UIWmsLayer;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.models.layer.DefaultMapContextLayer;
import org.mapfaces.models.layer.FeatureLayer;
import org.mapfaces.models.layer.MapContextLayer;
import org.mapfaces.models.layer.WmsLayer;
import org.mapfaces.share.utils.AjaxRendererUtils;
import org.mapfaces.util.ContextFactory;
import org.mapfaces.util.DefaultContextFactory;
import org.mapfaces.util.FacesMapUtils;
import org.mapfaces.util.MapUtils;

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
        if (comp.getModel() instanceof Context) {
            model = (Context) comp.getModel();
        } else {
            //The model context is null or not a Context instance
            throw new UnsupportedOperationException("The model context is null or not supported yet !");
        }

        String style = getStyle();
        final String styleClass = getStyleClass();
        final ResponseWriter writer = context.getResponseWriter();

        final String height = model.getWindowHeight();
        final String width = model.getWindowWidth();

        if (comp.getMaxExtent() == null) {
            comp.setMaxExtent(model.getMinx() + "," + model.getMiny() + "," + model.getMaxx() + "," + model.getMaxy());
        }

        if (style == null) {
            style = "width:" + width + "px;height:" + height + "px;z-index:0;";
        } else {
            style = "width:" + width + "px;height:" + height + "px;z-index:0;" + style;
        }

        if (this.debug) {
            LOGGER.log(Level.INFO, "\t the style property of the MapPane is " + style);
        }
        writer.startElement(HTML.DIV_ELEM, comp);
        writer.writeAttribute(HTML.id_ATTRIBUTE, clientId, HTML.id_ATTRIBUTE);
        if (styleClass == null) {
            writer.writeAttribute(HTML.class_ATTRIBUTE, "mfMapPane", "styleClass");
        } else {
            writer.writeAttribute(HTML.class_ATTRIBUTE, styleClass, "styleClass");
        }
        if (style != null) {
            writer.writeAttribute(HTML.style_ATTRIBUTE, style, HTML.style_ATTRIBUTE);
        /*MapPane ViewPort div*/
        }
        writer.startElement(HTML.DIV_ELEM, comp);

            writer.writeAttribute(HTML.id_ATTRIBUTE, clientId + "_MapFaces_Viewport", HTML.id_ATTRIBUTE);
        
        writer.writeAttribute(HTML.style_ATTRIBUTE, "overflow: hidden;position:relative;width:100%;height:100%;", HTML.style_ATTRIBUTE);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "mfMapViewport", "styleClass");

        /*Layers Container div*/
        writer.startElement(HTML.DIV_ELEM, comp);

            writer.writeAttribute(HTML.id_ATTRIBUTE, clientId + "_MapFaces_Container", HTML.id_ATTRIBUTE);
        
        writer.writeAttribute(HTML.style_ATTRIBUTE, "top:0px;left:0px;position:absolute;z-index: 0;", HTML.style_ATTRIBUTE);

        //getting the mappane mapcontext stored in sessionMap by attribute value
        Map sessionMap = context.getExternalContext().getSessionMap();
        Object mapcontextObj = sessionMap.get(FacesMapUtils.getCurrentSessionId()+comp.getId()+UIMapPane.MAPCONTEXT_KEY_SUFFIX);

        if (mapcontextObj instanceof MapContext) {
            final MapContext mapcontext = (MapContext) mapcontextObj;
            //here we can separate all mapcontext layers for every layer contained into the mapcontext.
            final ContextFactory contextFactory = new DefaultContextFactory();
            model.clearMapContextLayers();

            if (!comp.isLayersGrouped()) {
                if (mapcontext.layers() != null) {
                    for (MapLayer maplayer : mapcontext.layers()) {
                        final MapContext newMapCtxt = MapBuilder.createContext(mapcontext.getCoordinateReferenceSystem());
                        //passing user properties from maplayer to the final mapcontext
                        newMapCtxt.setUserPropertie("format", maplayer.getUserPropertie("format"));
                        newMapCtxt.layers().add(maplayer);

                        final DefaultMapContextLayer mcLayer = (DefaultMapContextLayer) contextFactory.createDefaultMapContextLayer(FacesMapUtils.getNewLayerIndex(model));
                        
                        
                        String mapcontextKey = FacesMapUtils.getCurrentSessionId() +
                                               FacesMapUtils.getParentUIModelBase(context, component).getId() + "_" +
                                               comp.getId() + "_" +
                                               mcLayer.getId() +
                                               UIMapPane.MAPCONTEXT_KEY_SUFFIX;
                        //putting for each layer a new mapcontext into session map based on the original mapcontext value and set key for the layer model
                        FacesMapUtils.putAtSessionMap(context, mapcontextKey, newMapCtxt);
                        mcLayer.setMapContextKeyInSession(mapcontextKey);
                        mcLayer.setName(maplayer.getName());
                        mcLayer.setTitle(maplayer.getName());
                        mcLayer.setHidden(! maplayer.isVisible());
                        mcLayer.setQueryable(maplayer.isSelectable());
                        if(maplayer.getUserPropertie("disableOpacity") instanceof Boolean) {
                            mcLayer.setUserValueDisableOpacity( ((Boolean) maplayer.getUserPropertie("disableOpacity")).booleanValue());
                        }

                        model.addLayer((MapContextLayer) mcLayer);
                    }
                }
            } else {
                final DefaultMapContextLayer mcLayer = (DefaultMapContextLayer) contextFactory.createDefaultMapContextLayer(FacesMapUtils.getNewLayerIndex(model));
                String mapcontextKey = FacesMapUtils.getCurrentSessionId() +
                                       FacesMapUtils.getParentUIModelBase(context, component).getId() + "_" +
                                       comp.getId() + "_" +
                                       mcLayer.getId() +
                                       UIMapPane.MAPCONTEXT_KEY_SUFFIX;

                //putting an allInOne layer's mapcontext into session map and set key for the layer model
                FacesMapUtils.putAtSessionMap(context, mapcontextKey, mapcontext);
                mcLayer.setMapContextKeyInSession(mapcontextKey);
                mcLayer.setName("AllInOne");
                mcLayer.setTitle("AllInOne");
                model.addLayer((MapContextLayer) mcLayer);
            }
        }
        

        final List<Layer> layers = model.getLayers();

        comp.setAjaxCompId(FacesMapUtils.getParentUIModelBase(context, component).getAjaxCompId());

        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] The Mappane has actually  " + comp.getChildCount() + " childs.");
            LOGGER.log(Level.INFO, "[DEBUG] The context of the Mappane contains " + layers.size() + " layers.");
        }

        //Allows you to know how many childrens should be moved at the end of mappane childrens list after adding the layers from context file
        final int nbExistingLayers = comp.getChildCount();


        for (final Layer layer : layers) {
            if (this.debug) {
                LOGGER.log(Level.INFO, "[DEBUG] The current layer is a :  " + layer.getType() + " layer.");
            }
            if (layer != null && layer.getType() != null) {
                switch (layer.getType()) {
                    case WMS:
                        final UIWmsLayer uiwmsLayer = new UIWmsLayer();
                        uiwmsLayer.setModel((AbstractModelBase) model);
                        if (layer.getId() != null) {
                            uiwmsLayer.getAttributes().put(HTML.id_ATTRIBUTE, FacesMapUtils.getParentUIModelBase(context, component).getId() + "_" + comp.getId() + "_" + layer.getId());
                        } else {
                            layer.setId(uiwmsLayer.getId());
                        }
                        comp.removeLayer(uiwmsLayer);
                        comp.getChildren().add(uiwmsLayer);
                        layer.setCompId(uiwmsLayer.getClientId(context));
                        uiwmsLayer.setLayer((WmsLayer) layer);
                        break;
                    case MAPCONTEXT:
                        final UIMapContextLayer uiMCLayer = new UIMapContextLayer();
                        uiMCLayer.setModel((AbstractModelBase) model);
                        uiMCLayer.getAttributes().put(HTML.id_ATTRIBUTE, FacesMapUtils.getParentUIModelBase(context, component).getId() + "_" + comp.getId() + "_" + layer.getId());
                        final MapContextLayer mcLayer = (MapContextLayer) layer;

                        comp.removeLayer(uiMCLayer);
                        comp.getChildren().add(uiMCLayer);
                        mcLayer.setCompId(uiMCLayer.getClientId(context));
                        uiMCLayer.setLayer(mcLayer);
                        break;
                    case FEATURE:
                        final FeatureLayer tmpfeature = (FeatureLayer) layer;
                        final UIFeatureLayer uiFLayer = new UIFeatureLayer();
                        uiFLayer.setModel((AbstractModelBase) model);
                        uiFLayer.setImage(tmpfeature.getImage());
                        uiFLayer.setFeatures(tmpfeature.getFeatures());
                        uiFLayer.setRotation(tmpfeature.getRotation());
                        uiFLayer.setSize(tmpfeature.getSize());
                        uiFLayer.setBindingIndex(tmpfeature.getGroupId());

                        if (layer.getId() != null) {
                            uiFLayer.getAttributes().put(HTML.id_ATTRIBUTE, FacesMapUtils.getParentUIModelBase(context, component).getId() + "_" + comp.getId() + "_" + tmpfeature.getId());
                        } else {
                            tmpfeature.setId(uiFLayer.getId());
                        }
                        comp.removeLayer(uiFLayer);
                        comp.getChildren().add(uiFLayer);
                        tmpfeature.setCompId(uiFLayer.getClientId(context));
                        uiFLayer.setLayer(tmpfeature);
                        break;

                    case DEFAULT:
                    case WFS:
                    case WCS:
                    case SLD:
                    case FES:
                    case GML:
                    case KML:
                    default:
                        break;
                }
            }
        }

        writer.flush();
        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG]  mappane has " + comp.getChildren().size() + " childrens");
        }
        
        //TODO Probably there is a better sort of list to do that
        final List<UIComponent> children = comp.getChildren();
        
        for (int i = 0; i < nbExistingLayers; i++) {
            final UIComponent child = children.get(i);
            children.remove(i);
            children.add(child);
        }
        //Setting the model to all children of the MapPane component
        for (final UIComponent tmp : comp.getChildren()) {
            if (tmp instanceof UIWidgetBase) {
                ((UIWidgetBase) tmp).setModel((AbstractModelBase) model);
                ((UIWidgetBase) tmp).setDebug(this.debug);
            }
        }
    }

    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        final UIMapPane uiMapPane = (UIMapPane) component;
        final String jsMapVariable = FacesMapUtils.getJsVariableFromClientId(uiMapPane.getClientId(context));
        uiMapPane.setAddLayersScript("window.layerToAdd" + jsMapVariable + "=[];");
        final List<UIComponent> childrens = component.getChildren();
        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] Le composant " + component.getFamily() + " has " + childrens.size() + " children :");
        }
        for (final UIComponent tmp : childrens) {
            if (this.debug) {
                LOGGER.log(Level.INFO, "[DEBUG]  \tChild family's " + tmp.getFamily());
            }
            FacesMapUtils.encodeRecursive(context, tmp);

            if ((tmp instanceof UILayer) && !(tmp instanceof UISvgLayer)) {
                final UILayer uiLayer = (UILayer) tmp;
                final Layer layer = uiLayer.getLayer();
                if (layer != null && !layer.isDisable()) {
                    final String clientId = uiLayer.getClientId(context);
                    final String jsLayerVariable = FacesMapUtils.getJsVariableFromClientId(uiLayer.getClientId(context));

                    final StringBuilder stringBuilder = new StringBuilder(uiMapPane.getAddLayersScript());
                    //Create an array who contains all the MapFaces layers to add for a specific MapPane
                    stringBuilder.

                            append("window.layerToAdd").append(jsMapVariable).append(".push(function() {").
                            //If OpenLayers classes are correctly loaded
                            append("if (window.OpenLayers &&  window.OpenLayers.Layer && window.OpenLayers.Layer.MapFaces) {").
                            //Create a MapFaces layer
                            append("window."+jsLayerVariable).append("= new OpenLayers.Layer.MapFaces({").
                                append("id:").append("'").append(jsLayerVariable).append("'").append(",").
                                append("compId:").append("'").append(uiLayer.getId()).append("'").append(",").
                                append("compClientId:").append("'").append(clientId).append("'").append(",").
                                append("visibility:").append(!layer.isHidden()).append(",").
                                append("maxScale:").append(layer.getMinScaleDenominator()).append(",").
                                append("minScale:").append(layer.getMaxScaleDenominator()).append("").
                            append("});").
                            append(jsMapVariable).append(".addLayer(").append(jsLayerVariable).append(");").
                            append("}});");
                    uiMapPane.setAddLayersScript(stringBuilder.toString());
                }
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
        if (comp.getModel() instanceof Context) {
            model = (Context) comp.getModel();
        } else {
            //The model context is null or not an Context instance
            throw new UnsupportedOperationException("The model context is null or not supported yet !");
        }

        final ResponseWriter writer = context.getResponseWriter();
        // ... ending the started elements
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM);
        /* Construct OpenLayers Map Object */
        writer.startElement(HTML.SCRIPT_ELEM, comp);
        writer.writeAttribute(HTML.TYPE_ATTR, "text/javascript", HTML.TYPE_ATTR);

        //suppression des ":" pour nommer l'objet javascript correspondant correctement
//        String jsObject = FacesMapUtils.getParentUIModelBase(context, component).getClientId(context);
        final String jsObject = FacesMapUtils.getJsVariableFromClientId(comp.getClientId(context));

        //when srs is null the default value is CRS:84
        final String srs = (model.getSrs() != null) ? model.getSrs().toUpperCase(Locale.getDefault()) : "CRS:84";

        final StringBuilder stringBuilder = new StringBuilder("")
                /**
                 * If window.maps (list of the maps)  doesn't exist , we create it;
                 */
                .append("if(!window.maps)window.maps = {};")

                /**
                 * Add a null object to the window.maps list
                 */
                .append("window.maps.").append(jsObject).append(" = null;")

                /**
                 * Create an empty Array wich contains all controls to add to the current map
                 */
                .append("window.controlToAdd" + jsObject + " = []; ")

                /**
                 * Define a function  who will load the map;
                 */
                .append("window.loadMap" + jsObject + " = function() {")

                /**
                 * Test if map options object doesn't exist and all needed OpenLayers class has been loaded correctly
                 */
                .append("if (typeof ").append(jsObject).append("_mapOptions == 'undefined' ").
                append("&& window.OpenLayers && window.OpenLayers.Projection ").
                append("&& window.OpenLayers.Size && window.OpenLayers.Bounds) { ")

                /**
                 * Create the map options object, it contains all options needed to render a map;
                 */
                .append("var ").append(jsObject).append("_mapOptions = {");


        /**
         * OpenLayers map options
         */
        /**
         * Id of the javascript Map object
         */
        stringBuilder.append("id: '" + jsObject + "',");

        /**
         * List of Control objects to add to the map by default
         */
        stringBuilder.append("controls: [],");

        /**
         * Projection
         */
        stringBuilder.append("projection: new OpenLayers.Projection('").append(srs).append("'),");

        /**
         * Units
         */
        stringBuilder.append("units: '").append(MapUtils.getUnits(srs)).append("',");

        /**
         * Size
         */
        stringBuilder.append("size: new OpenLayers.Size('").append(model.getWindowWidth()).append("','").append(model.getWindowHeight()).append("'),");


        //@Todo Define clearly which extent is used to restrict the zoom
        /**
         * MaxExtent
         */
        stringBuilder.append("maxExtent: new OpenLayers.Bounds(").append(comp.getMaxExtent()).append("),");

        /**
         * CurrentExtent , it'as a MapFaces option not an OpenLayers one
         */
        stringBuilder.append("currentExtent: new OpenLayers.Bounds(").append(model.getMinx()).append(",").append(model.getMiny()).append(",").append(model.getMaxx()).append(",").append(model.getMaxy()).append("),");

        /**
         * RestrictedExtent
         */
        stringBuilder.append("restrictedExtent: new OpenLayers.Bounds(").append(comp.getMaxExtent()).append("),");

        /**
         * MaxResolution
         */
        stringBuilder.append("maxResolution: 'auto',");

        /**
         * NumZoomLevels
         */
        stringBuilder.append("numZoomLevels: ").append(comp.getNumZoomLevels()).append(",");

        /**
         * Theme
         */
        stringBuilder.append("theme:  null,");

        /**
         * FractionnalZoom
         */
        stringBuilder.append("fractionalZoom:  true,");


        /**
         * MapFaces map options
         */
        /**
         * moveend event
         */
        stringBuilder.append("moveend:  [],");
        /**
         * zoomend event
         */
        stringBuilder.append("zoomend:  [],");
        /**
         * LayersName
         */
        stringBuilder.append("layersName:  '").append(model.getLayersCompId()).append("',");

        /**
         * mfAjaxCompId : Id of the a4j component to call for refresh  the map
         */
        stringBuilder.append("mfAjaxCompId: '").append(FacesMapUtils.getParentUIModelBase(context, component).getAjaxCompId()).append("',");

        /**
         * mfFormId : ClientId of the parent UIForm  of the UIMapPane;
         */
        stringBuilder.append("mfFormClientId: '").append(FacesMapUtils.getFormClientId(context, component)).append("',");

        /**
         * defaultOptions : ClientId of the parent UIForm  of the UIMapPane;
         */
        stringBuilder.append("mfAjaxDefaultOptions: ").append(AjaxRendererUtils.buildDefaultOptions(component, context).toString()).append(",");

        /**
         * If we have specified an ajaxRegion for the Context component, we
         * use its ID to set the mfRequestId of the request.
         */
        final UIContext contextComp = (UIContext) FacesMapUtils.getParentUIContext(context, comp);
        if (contextComp.getAjaxRegion() != null) {
            String ajaxRegion = contextComp.getAjaxRegion();
            if (!ajaxRegion.contains(":")) ajaxRegion = FacesMapUtils.getFormId(context, component) + ":" + ajaxRegion;
            stringBuilder.append("mfRequestId: '" + ajaxRegion + "'");
        } else {
            /**
             * mfRequestId : Id of the request, a totally arbitrary attribute
             */
            stringBuilder.append("mfRequestId: 'updateBboxOrWindow'");
        }

        /**
         * Close  the map options creation
         */
        stringBuilder.append("};");

        /**
         * Else If map options object already exist and all needed OpenLayers class has been loaded correctly
         */
        stringBuilder.append("} else if (window.OpenLayers && window.OpenLayers.Bounds) {").

                /**
                 * Overwrite the current layersName
                 */
                append(jsObject + "_mapOptions.layersName = '").append(model.getLayersCompId()).append("' ;").

                /**
                 * Overwrite the current extent
                 */
                append(jsObject).append("_mapOptions.currentExtent = new OpenLayers.Bounds(").

                append(model.getMinx()).append(",").append(model.getMiny()).append(",").

                append(model.getMaxx()).append(",").append(model.getMaxy()).append(");");

        /**
         * Close the Else If
         */
        stringBuilder.append("}").
                /**
                 * If OpenLayers class are correctly loaded we create the Map object and push it into the window.maps list
                 */
                append("if (window.OpenLayers && window.OpenLayers.Map) {").
                /**
                 * Create the JS  Map object
                 */
               // append("if(window.").append(jsObject).append(")window.").append(jsObject).append(".destroy();").
                append("window.").append(jsObject).append("     = new OpenLayers.Map('").append(comp.getClientId(context)).append("'," + jsObject + "_mapOptions);").
                /**
                 * Add the MapFaces layers
                 */
                append(comp.getAddLayersScript()).
                /**
                 * Attach the Map object to the window.maps list
                 */
                append("window.maps.").append(jsObject).append("     = window.").append(jsObject).append(";").
                /**
                 * Attach OpenLayers.MapFaces.Layer(s) to the map
                 */
                append("if( window.layerToAdd" + jsObject + ")").
                append("for (var i = 0 ; i <  window.layerToAdd" + jsObject + ".length; i++) {").
                append("window.layerToAdd" + jsObject + "[i]();").
                append("}").
                /**
                 * Close the If
                 */
                append("}").
                /**
                 * Close the loadMap function declaration
                 */
                append("};").
                /**
                 * Run the loadMap function
                 */
                append("window.loadMap" + jsObject + "();");


        writer.write(stringBuilder.toString());
        writer.endElement(HTML.SCRIPT_ELEM);
        writer.endElement(HTML.DIV_ELEM);
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
        final UIContext contextComp = (UIContext) FacesMapUtils.getParentUIContext(context, comp);
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
