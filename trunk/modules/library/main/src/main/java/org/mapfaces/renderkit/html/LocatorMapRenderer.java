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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.mapfaces.component.UILocatorMap;
import org.mapfaces.models.Context;
import org.mapfaces.taglib.LocatorMapTag;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class LocatorMapRenderer extends MapPaneRenderer {
    
    
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if((String) component.getAttributes().get("styleClass")==null)
            setStyleClass("mf" + LocatorMapTag.COMP_TYPE.substring(LocatorMapTag.COMP_TYPE.lastIndexOf(".") + 1, LocatorMapTag.COMP_TYPE.length()));
        super.encodeBegin(context, component);
    }
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        UILocatorMap comp = (UILocatorMap) component;  
        String clientId= comp.getClientId(context);   
        ResponseWriter writer = context.getResponseWriter();
        Context model;
        if (comp.getModel() != null && comp.getModel() instanceof Context) {
            model = (Context) comp.getModel();
        } else {
            //The model context is null or not an Context instance
            throw new UnsupportedOperationException("The model context is null or not supported yet !");
        }
        writer.endElement("div");
        writer.endElement("div");              
        
        if(comp.getTargetContextCompId() != null){
            writer.startElement("script", comp);
            writer.writeAttribute("type", "text/javascript", "text/javascript");

            //suppression des ":" pour nommer l'objet javascript correspondant correctement   String jsObject = component.getClientId(context);
            String jsObject = comp.getClientId(context);
            if (jsObject.contains(":")) {
                jsObject = jsObject.replace(":", "");
            }     
            String mapJsObject = comp.getClientId(context);
            if (mapJsObject.contains(":")) {
                mapJsObject = mapJsObject.split(":")[0]+comp.getTargetContextCompId();
            }else{
                mapJsObject = comp.getTargetContextCompId();
            }
            String[] srsCode = model.getSrs().split(":");
            writer.write("    var ovmapOptions = {\n" +
                    "                       id:'" + jsObject + "',\n" +
                    "                       outsideViewport:true," +
                    "                       controls:[],\n" +
                    "                       projection: new OpenLayers.Projection('EPSG:" + srsCode[srsCode.length - 1] + "'),\n" +
                    "                       size: new OpenLayers.Size('300','150'),\n" +
                    "                       maxExtent: new OpenLayers.Bounds(" + comp.getMaxExtent() + "),\n" +
                    "                       currentExtent: new OpenLayers.Bounds(" + model.getMinx().toString() + "," + model.getMiny().toString() + "," + model.getMaxx().toString() + "," + model.getMaxy().toString() + "),\n" +
                    "                       maxResolution: 'auto',\n" +
                    "                       theme:  null ,\n" +
                    "                       fractionnalZoom:  true ,\n" +
                    "                       layersName:  '" + model.getLayersCompId().split(",")[0]+ "' ,\n" +
                    "                       mfAjaxCompId:'" + comp.getAjaxCompId()  + "',\n" +
                    "                       mfFormId:'" + FacesUtils.getFormId(context, component) + "',\n" +
                    "                       mfRequestId:'updateBboxOrWindow'\n" +
                    "                   };\n");
            writer.write("var " + jsObject + " = new OpenLayers.Control.OverviewMap({div: OpenLayers.Util.getElement('" + clientId + "'),mapOptions:ovmapOptions});\n");
            writer.write(mapJsObject + ".addControl(" + jsObject + ");\n" +
                "    if(!window.maps){window.maps = {};}\n" +
                "    window.maps." + jsObject + " = " + jsObject + ".ovmap;\n");
            writer.endElement("script");
        }
        writer.endElement("div");
        writer.flush();
    }
    @Override
    public boolean getRendersChildren() {
        return false;
    }
}
