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
import org.mapfaces.component.UILayer;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.util.FacesUtils;

/**
 * 
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class MapPaneRenderer extends WidgetBaseRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        super.encodeBegin(context, component);

        UIMapPane comp = (UIMapPane) component;
        boolean debug = comp.isDebug();
        String clientId = comp.getClientId(context);
        Context model;
        if (comp.getModel() != null && comp.getModel() instanceof Context) {
            model = (Context) comp.getModel();
        } else {
            //The model context is null or not a Context instance
            throw new UnsupportedOperationException("The model context is null or not supported yet !");
        }

        String height = model.getWindowHeight();
        String width = model.getWindowWidth();
        if (comp.getMaxExtent() == null) {
            comp.setMaxExtent(model.getMinx() + "," + model.getMiny() + "," + model.getMaxx() + "," + model.getMaxy());
        }
        String id = (String) comp.getAttributes().get("id");

        if (style == null) {
            style = "width:" + width.toString() + "px;height:" + height.toString() + "px;z-index:0;";
        } else {
            style = "width:" + width.toString() + "px;height:" + height.toString() + "px;z-index:0;" + style;
        }
        if (debug) {
            System.out.println("        L'attribut style du MapPane est " + style);
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

        List<Layer> layers = model.getLayers();
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();

        File dstDir = new File(sc.getRealPath("tmp"));
        if (!dstDir.exists()) {
            dstDir.mkdir();
        }

        String ctxPath = sc.getContextPath();
        String srs = model.getSrs();

        if (comp.getChildren() != null) {
            removeChildren(context, component);
        }
        if (debug) {
            System.out.println("        Le MapPane contient " + layers.size() + " layers");
        }
        for (Layer temp : layers) {
            if (temp != null) {

                UILayer layer = new UILayer(comp, temp);
                layer.setModel((AbstractModelBase) model);
                if (temp.getId() != null) {
                    layer.getAttributes().put("id", temp.getId());
                } else {
                    temp.setId(layer.getClientId(context));
                }
                if (debug) {
                    layer.getAttributes().put("debug", true);
                }
                layer.setLayer(temp);
                layer.setDir(dstDir);
                layer.setContextPath(ctxPath);
                comp.getChildren().add(layer);
            }
        }

        writer.flush();

        //Setting the model to all children of the MapPane component
        List<UIComponent> childrens = comp.getChildren();
        for (UIComponent tmp : childrens) {
            if (tmp instanceof UIWidgetBase) {
                ((UIWidgetBase) tmp).setModel((AbstractModelBase) model);
            }
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        super.encodeEnd(context, component);
        UIMapPane comp = (UIMapPane) component;
        Context model;
        if (comp.getModel() != null && comp.getModel() instanceof Context) {
            model = (Context) comp.getModel();
        } else {
            //The model context is null or not an Context instance
            throw new UnsupportedOperationException("The model context is null or not supported yet !");
        }
        ResponseWriter writer = context.getResponseWriter();
        // ... ending the started elements
        writer.endElement("div");
        writer.endElement("div");
        /* Construct OpenLayers Map Object */
        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", "text/javascript");

        //suppression des ":" pour nommer l'objet javascript correspondant correctement      
        String jsObject = FacesUtils.getParentUIModelBase(context, component).getClientId(context);
        if (jsObject.contains(":")) {
            jsObject = jsObject.replace(":", "");
        }
        String[] srsCode = model.getSrs().split(":");
        writer.write("               " +
                "    var mapOptions = {\n" +
                "                       id:'" + jsObject + "',\n" +
                "                       controls:[],\n" +
                "                       projection: new OpenLayers.Projection('EPSG:" + srsCode[srsCode.length - 1] + "'),\n" +
                "                       size: new OpenLayers.Size('" + model.getWindowWidth() + "','" + model.getWindowHeight() + "'),\n" +
                "                       maxExtent: new OpenLayers.Bounds(" + comp.getMaxExtent() + "),\n" +
                "                       currentExtent: new OpenLayers.Bounds(" + model.getMinx().toString() + "," + model.getMiny().toString() + "," + model.getMaxx().toString() + "," + model.getMaxy().toString() + "),\n" +
                "                       maxResolution: 'auto',\n" +
                "                       theme:  null ,\n" +
                "                       fractionnalZoom:  true ,\n" +
                "                       layersName:  '" + model.getLayersId() + "' ,\n" +
                "                       mfAjaxCompId:'" + FacesUtils.getParentUIModelBase(context, component).getAjaxCompId() + "',\n" +
                "                       mfFormId:'" + FacesUtils.getFormId(context, component) + "',\n" +
                "                       mfRequestId:'updateBboxOrWindow'\n" +
                "                   };\n" +
                "    window." + jsObject + " = new OpenLayers.Map('" + comp.getClientId(context) + "',mapOptions);\n" +
                "   ");
        writer.endElement("script");
        writer.endElement("div");
        writer.flush();
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        super.decode(context, component);
        UIMapPane comp = (UIMapPane) component;
        UIContext contextComp = (UIContext) comp.getParent();
        Context tmp = (Context) contextComp.getModel();

        if (context.getExternalContext().getRequestParameterMap() != null) {
            Map params = context.getExternalContext().getRequestParameterMap();
            String render = (String) params.get("render");
            if (render == null || render.equals("true")) {
                comp.setInitDisplay(true);
            }
        }

        contextComp.setModel((AbstractModelBase) tmp);
        comp.setModel((AbstractModelBase) tmp);
    }
}
