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

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.ServletContext;

import net.opengis.owc.v030.LayerType;
import org.geotools.map.MapContext;
import org.geotools.map.MapLayer;
import org.mapfaces.component.UILayer;
import org.mapfaces.component.UIMFLayer;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.models.Server;
import org.mapfaces.util.ContextFactory;
import org.mapfaces.util.DefaultContextFactory;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class MapPaneRenderer extends WidgetBaseRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeBegin(context, component);

        final UIMapPane comp = (UIMapPane) component;
        final boolean debug = comp.isDebug();
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

        if (debug) {
            System.out.println("\t the style property of the MapPane is " + style);
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
        writer.writeAttribute("style", "top:0px;left:0px;position:absolute;z-index: 749;", "style");

        final List<Layer> layers = model.getLayers();
        final ServletContext sc = (ServletContext) context.getExternalContext().getContext();

        final File dstDir = new File(sc.getRealPath("tmp"));
        if (!dstDir.exists()) {
            dstDir.mkdir();
        }

        final String ctxPath = sc.getContextPath();
        final String srs = model.getSrs();

        if (debug) {
            System.out.println("\t The model context of the Mappane contains " + layers.size() + " layers.");
        }
        comp.setAjaxCompId(FacesUtils.getParentUIModelBase(context, component).getAjaxCompId());

        removeChildren(context, component);
        
            for (final Layer temp : layers) {
                if (temp.getId().contains("_MF_")) {
                    continue;
                }
                if (temp != null && temp.getType() != null) {
                    if (!temp.getType().equals("mapfaces_type")) {
                        final UILayer layer = new UILayer();
                        layer.setModel((AbstractModelBase) model);
                        if (temp.getId() != null) {
                            layer.getAttributes().put("id", FacesUtils.getParentUIModelBase(context, component).getId() + "_" + comp.getId() + "_" + temp.getId());
                        } else {
                            temp.setId(layer.getId());
                        }

                        if (debug) {
                            layer.getAttributes().put("debug", true);
                        }
                        layer.setDir(dstDir);
                        layer.setContextPath(ctxPath);

                        comp.getChildren().add(layer);


                        temp.setCompId(layer.getClientId(context));
                        System.out.println("[DEBUG] Layer " + layer.getClientId(context));
                        layer.setLayer(temp);
                        if (debug) {
                            System.out.println("\t UILayer  ClientId" + layer.getClientId(context));
                        }
                    } else {
                        UIMFLayer mfLayer = new UIMFLayer();
                        mfLayer.setModel((AbstractModelBase) model);
                        mfLayer.setImage(temp.getImage());
                        mfLayer.setFeatures(temp.getFeatures());
                        mfLayer.setRotation(temp.getRotation());
                        mfLayer.setSize(temp.getSize());

                        if (temp.getId() != null) {
                            mfLayer.getAttributes().put("id", FacesUtils.getParentUIModelBase(context, component).getId() + "_" + comp.getId() + "_" + temp.getId());
                        } else {
                            temp.setId(mfLayer.getId());
                        }
                        if (debug) {
                            mfLayer.getAttributes().put("debug", true);
                        }
                        mfLayer.setDir(dstDir);
                        mfLayer.setContextPath(ctxPath);

                        comp.getChildren().add(mfLayer);

                        temp.setCompId(mfLayer.getClientId(context));
                        System.out.println("[DEBUG] MFLayer " + mfLayer.getClientId(context));
                        mfLayer.setLayer(temp);
                        if (debug) {
                            System.out.println("\t UIMFLayer  ClientId" + mfLayer.getClientId(context));
                        }
                    }
                }
            }

        MapContext mapcontext = (MapContext) comp.getAttributes().get("value");
        if (mapcontext != null) {

            //one layer for the entire mpacontext ------------------------------
            int idlayer = -1;
            //adding the feature collection into the new layer.
            final ContextFactory contextFactory = new DefaultContextFactory();
            Server wms = contextFactory.createDefaultServer();
            wms.setHref("");
            wms.setService("mapfaces_service");
            wms.setVersion("1.0");

            LayerType layerType = new LayerType();
            layerType.setId("MapFaces_Layer_MF_" + idlayer);
            layerType.setGroup("mapfaces_group");
            layerType.setName("abstractlayer");
            layerType.setHidden(false);
            layerType.setOpacity(BigDecimal.ONE);

            Layer layermodel = contextFactory.createDefaultLayer();
            layermodel.setId(layerType.getId());
            layermodel.setGroup(layerType.getGroup());
            layermodel.setName(layerType.getName());
            layermodel.setHidden(layerType.isHidden());
            layermodel.setOpacity(layerType.getOpacity().toString());
            layermodel.setTitle("mapfaces_title");
            layermodel.setServer(wms);
            layermodel.setType("mapfaces_abstracttype");
            layermodel.setOutputFormat("image/gif");
            layermodel.setQueryable(true);

            layermodel.setCompId(FacesUtils.getParentUIModelBase(context, comp).getId() + "_" + comp.getId() + "_" + layermodel.getId());

            UIMFLayer mfLayer = new UIMFLayer();
            mfLayer.setIndex(idlayer);
            mfLayer.setModel((AbstractModelBase) model);


            mfLayer.getAttributes().put("id", FacesUtils.getParentUIModelBase(context, component).getId() + "_" + comp.getId() + "_" + layermodel.getId());


            if (debug) {
                mfLayer.getAttributes().put("debug", true);
            }
            mfLayer.setDir(dstDir);
            mfLayer.setContextPath(ctxPath);

            comp.getChildren().add(mfLayer);

            layermodel.setCompId(mfLayer.getClientId(context));
            System.out.println("[DEBUG] MFLayer from Mapcontext :  " + mfLayer.getClientId(context));
            mfLayer.setLayer(layermodel);

            if (debug) {
                System.out.println("\t UIMFLayer  ClientId" + mfLayer.getClientId(context));
            }

            model.removeLayerFromId(layermodel.getId());
            model.addLayer(layermodel);



            //one layer by maplayer --------------------------------------------
//            for (MapLayer layer : mapcontext.layers()) {
//
//
//                int idlayer = mapcontext.layers().indexOf(layer);
//
//                //adding the feature collection into the new layer.
//                final ContextFactory contextFactory = new DefaultContextFactory();
//                Server wms = contextFactory.createDefaultServer();
//                wms.setHref("");
//                wms.setService("mapfaces_service");
//                wms.setVersion("1.0");
//
//                LayerType layerType = new LayerType();
//                layerType.setId("MapFaces_Layer_MF_" + idlayer);
//                layerType.setGroup("mapfaces_group");
//                layerType.setName("abstractlayer");
//                layerType.setHidden(false);
//                layerType.setOpacity(BigDecimal.ONE);
//
//                Layer layermodel = contextFactory.createDefaultLayer();
//                layermodel.setId(layerType.getId());
//                layermodel.setGroup(layerType.getGroup());
//                layermodel.setName(layerType.getName());
//                layermodel.setHidden(layerType.isHidden());
//                layermodel.setOpacity(layerType.getOpacity().toString());
//                layermodel.setTitle("mapfaces_title");
//                layermodel.setServer(wms);
//                layermodel.setType("mapfaces_abstracttype");
//                layermodel.setOutputFormat("image/gif");
//                layermodel.setQueryable(true);
//
//                layermodel.setCompId(FacesUtils.getParentUIModelBase(context, comp).getId() + "_" + comp.getId() + "_" + layermodel.getId());
//
//                UIMFLayer mfLayer = new UIMFLayer();
//                mfLayer.setIndex(idlayer);
//                mfLayer.setModel((AbstractModelBase) model);
//
//
//                mfLayer.getAttributes().put("id", FacesUtils.getParentUIModelBase(context, component).getId() + "_" + comp.getId() + "_" + layermodel.getId());
//
//
//                if (debug) {
//                    mfLayer.getAttributes().put("debug", true);
//                }
//                mfLayer.setDir(dstDir);
//                mfLayer.setContextPath(ctxPath);
//
//                comp.getChildren().add(mfLayer);
//
//                layermodel.setCompId(mfLayer.getClientId(context));
//                System.out.println("[DEBUG] MFLayer from Mapcontext :  " + mfLayer.getClientId(context));
//                mfLayer.setLayer(layermodel);
//
//                if (debug) {
//                    System.out.println("\t UIMFLayer  ClientId" + mfLayer.getClientId(context));
//                }
//
//                model.removeLayerFromId(layermodel.getId());
//                model.addLayer(layermodel);
//
//
//            }
        comp.setInitDisplay(true);
        }

        writer.flush();

        //Setting the model to all children of the MapPane component
        for (final UIComponent tmp : comp.getChildren()) {
            if (tmp instanceof UIWidgetBase) {
                ((UIWidgetBase) tmp).setModel((AbstractModelBase) model);
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

        writer.write(new StringBuilder(" if (typeof " + jsObject + "_mapOptions == 'undefined') { \n").append("    var " + jsObject + "_mapOptions = {\n").
                append("                       id:'").append(jsObject).append("',\n").
                append("                       controls:[],\n").
                append("                       projection: new OpenLayers.Projection('").
                append(model.getSrs().toUpperCase()).append("'),\n").
                append("                       size: new OpenLayers.Size('").
                append(model.getWindowWidth()).append("','").
                append(model.getWindowHeight()).append("'),\n").
                append("                       maxExtent: new OpenLayers.Bounds(").
                append(comp.getMaxExtent()).append("),\n").
                append("                       currentExtent: new OpenLayers.Bounds(").
                append(model.getMinx()).append(",").append(model.getMiny()).append(",").
                append(model.getMaxx()).append(",").append(model.getMaxy()).append("),\n").
                append("                       maxResolution: 'auto',\n").
                append("                       theme:  null ,\n").
                append("                       fractionnalZoom:  true ,\n").
                append("                       layersName:  '").append(model.getLayersCompId()).append("' ,\n").
                append("                       mfAjaxCompId:'").
                append(FacesUtils.getParentUIModelBase(context, component).getAjaxCompId()).append("',\n").
                append("                       mfFormId:'").append(FacesUtils.getFormId(context, component)).append("',\n").
                append("                       mfRequestId:'updateBboxOrWindow'\n").
                append("                   }; \n }\n").
                append(" else { " + jsObject + "_mapOptions.layersName = '").append(model.getLayersCompId()).append("' ;} \n").
                append("    " + jsObject + "_mapOptions.controls=[]; window.").append(jsObject).append(" = new OpenLayers.Map('").append(comp.getClientId(context)).append("'," + jsObject + "_mapOptions);\n").
                append("    if(!window.maps){window.maps = {};}\n").
                append("    window.maps.").append(jsObject).append(" = window.").append(jsObject).append(";\n").
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
