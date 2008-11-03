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
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.mapfaces.component.UIPopup;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.taglib.PopupTag;


public class PopupRenderer extends WidgetBaseRenderer {
    String innerHTML = null;
    String top = "0px";
    String left = "0px";
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {  
        
        super.encodeBegin(context, component);     
        UIPopup comp = (UIPopup) component;  
        String clientId= comp.getClientId(context);
        
                    
        writer.startElement("div", comp);        
        writer.writeAttribute("id",clientId,"id");
        
        if (getStyleClass() == null)
            writer.writeAttribute("class","mf"+PopupTag.COMP_TYPE.substring(PopupTag.COMP_TYPE.lastIndexOf(".")+1,PopupTag.COMP_TYPE.length()),"styleclass");
        
        if (getStyle() != null)
            writer.writeAttribute("style","top:"+top+";left:"+left+";"+getStyle(),"style");
        if(innerHTML != null)
         writer.write(innerHTML);

        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", "text/javascript");

        //suppression des ":" pour nommer l'objet javascript correspondant correctement      
        String jsObject = comp.getParent().getClientId(context);
        if (jsObject.contains(":")) {
            jsObject = jsObject.replace(":", "");
        }
        
        writer.write("if("+jsObject+comp.getId()+" == null){var "+jsObject+comp.getId()+" = new OpenLayers.Control.GetFeatureInfo(OpenLayers.Util.getElement('" + clientId + "'));\n");
        writer.write(jsObject + ".addControl("+jsObject+comp.getId()+");}\n");
        writer.endElement("script");
        writer.endElement("div");
        writer.flush();

    }

    @Override
    public boolean getRendersChildren() {
        return false;
    }
     @Override
    public void decode(FacesContext context, UIComponent component) {
        UIPopup comp = (UIPopup) component;
        
        if (comp.isDebug()) {
            System.out.println("        PopupRenderer decode");
        }

        if (context.getExternalContext().getRequestParameterMap() != null) {
            Context tmp = (Context) comp.getModel();
            
            Map params = context.getExternalContext().getRequestParameterMap();
            //Layer layer = comp.getLayer();
            String X="170";
            String Y="160";
            if(params.get("org.mapfaces.ajax.ACTION") != null && ((String)params.get("org.mapfaces.ajax.ACTION")).equals("getFeatureInfo")){
               if(params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y") != null){
                    Y = (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y");
                    //top = Y+"px";
               }
               if(params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_X") != null){
                    X = (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_X");
                    //left = X+"px";
               }
               ServletContext sc= (ServletContext) context.getExternalContext().getContext();
               Layer test = tmp.getLayers().get(0);
            System.out.println(sc.getContextPath()+" "+sc.getServletContextName()+" ");
                innerHTML = "<iframe style='height:40px;' id='popup' name='popup' src='"+test.getServer().getHref()+"?bbox="+tmp.getBoundingBox()+"&styles=&format="+test.getOutputFormat()+"&info_format=text/plain&version="+test.getServer().getVersion()+"&srs="+tmp.getSrs()+"&request=GetFeatureInfo&layers=BlueMarble&query_layers=BlueMarble&width="+tmp.getWindowWidth()+"&height="+tmp.getWindowHeight()+"&x="+X+"&y="+Y+"'></iframe>";
            }
            comp.setModel((AbstractModelBase) tmp);

        }
        return;
    }
}
