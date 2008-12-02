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
import java.util.List;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.ServletContext;

import org.mapfaces.component.UIDiv;
import org.mapfaces.component.UILayer;
import org.mapfaces.component.UIMFLayer;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
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
        
        boolean containsMFLayers = FacesUtils.containsMFLayers(comp);
        
        UIDiv jsfdiv = new UIDiv();
        jsfdiv.setId(comp.getId()+"_layersDivContainer");
                
        for (final Layer temp : layers) {
            if (temp != null && temp.getType() != null &&  ! temp.getType().equals("mapfaces_type")) {

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
                
                comp.removeLayer(layer);
                if (containsMFLayers) {
                    jsfdiv.getChildren().add(0,layer);
                }else {
                    jsfdiv.getChildren().add(layer);
                }
                
                
                temp.setCompId(layer.getClientId(context));
                System.out.println("[DEBUG] " + layer.getClientId(context));
                layer.setLayer(temp);
                if (debug) {
                    System.out.println("\t UILayer  ClientId" + layer.getClientId(context));
                }
            }
        }
        comp.getChildren().add(jsfdiv);

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
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        final UIMapPane comp = (UIMapPane) component;
        final List<UIComponent> childrens = component.getChildren().get(0).getChildren();
        if (comp.isDebug()) {
            System.out.println("[MapPane encodeChildren] " + component.getFamily() + " have " + childrens.size() + " children. ");
        }
        
        final Context model;
        if (comp.getModel() != null && comp.getModel() instanceof Context) {
            model = (Context) comp.getModel();
        } else {
            //The model context is null or not a Context instance
            throw new UnsupportedOperationException("The model context is null or not supported yet !");
        }
        final ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        final String ctxPath = sc.getContextPath();
        final File dstDir = new File(sc.getRealPath("tmp"));
        if ( ! dstDir.exists() ) {
            dstDir.mkdir();
        }
            
        comp.getChildren().get(0).encodeBegin(context);
        for (final UIComponent tmp : childrens) {
            if (comp.isDebug()) {
                System.out.println("[MapPane encodeChildren] child family component  : " + tmp.getFamily());
            }

            
            //if the child component is a UIMFLayer then setting the contextmodel and file directory.
            if (tmp instanceof UIMFLayer) {
                UIMFLayer mfLayer = (UIMFLayer) tmp;
                mfLayer.setModel((AbstractModelBase) model);
                mfLayer.setDir(dstDir);
                mfLayer.setContextPath(ctxPath);
                FacesUtils.encodeRecursive(context, mfLayer);
            } else {
                FacesUtils.encodeRecursive(context, tmp);
            }
        }
        comp.getChildren().get(0).encodeEnd(context);
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
        final String[] srsCode = model.getSrs().split(":");
                
        writer.write(new StringBuilder("               ").append("    var mapOptions = {\n").
                                                          append("                       id:'").append(jsObject).append("',\n").
                                                          append("                       controls:[],\n").
                                                          append("                       projection: new OpenLayers.Projection('EPSG:").
                                                          append(srsCode[srsCode.length - 1]).append("'),\n").
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
                                                          append("                   };\n").append("    window.").append(jsObject).
                                                          append(" = new OpenLayers.Map('").append(comp.getClientId(context)).append("',mapOptions);\n").
                                                          append("    if(!window.maps){window.maps = {};}\n").append("    window.maps.").
                                                          append(jsObject).append(" = window.").append(jsObject).append(";\n").
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
