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
    Integer width = 300;
    Integer height = 200;
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {  
        
        super.encodeBegin(context, component);     
        UIPopup comp = (UIPopup) component;  
        String clientId= comp.getClientId(context);
        
                          
        
        if (getStyleClass() == null)
            writer.writeAttribute("class","mf"+PopupTag.COMP_TYPE.substring(PopupTag.COMP_TYPE.lastIndexOf(".")+1,PopupTag.COMP_TYPE.length()),"styleclass");

        Integer innerWidth = width-73;
        Integer innerLeft = width-40;
        Integer innerHeight = height-75;
        Integer innerTop = height-42;
        Integer innerMiddle = (width/2)-21;
        Integer leftCloseButton = width-43;
        
        if(innerHTML != null){
         /***********************************************/
 writer.startElement("div", comp);
        if (getStyleClass() == null)writer.writeAttribute("class","mf"+PopupTag.COMP_TYPE.substring(PopupTag.COMP_TYPE.lastIndexOf(".")+1,PopupTag.COMP_TYPE.length()),"styleclass");        
        writer.writeAttribute("id",clientId,"id");
        writer.writeAttribute("style","width:"+width.toString()+"px; height: "+height.toString()+"px; position: absolute; z-index: 1500;"+top+left,"style");  
 //<div style="width: 323px; height: 125px; position: absolute; z-index: 1500;"+top+";"+left+";">     
      writer.startElement("div", comp);writer.writeAttribute("style","width: 100%; height: 100%; position: relative;","style");           
      //<div style="width: 100%; height: 100%; position: relative;">
         writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-left.png) repeat scroll 0% 0%; left: 0px; top: 0px; position: absolute; width: 33px; height: 33px;","style");writer.endElement("div");                 
         // <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-left.png) repeat scroll 0% 0%; left: 0px; top: 0px; position: absolute; width: 33px; height: 33px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
         writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top.png) repeat scroll 0% 0%; left: 33px; top: 0px; position: absolute; width: "+innerWidth.toString()+"px; height: 33px; ","style");writer.endElement("div");                
         //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top.png) repeat scroll 0% 0%; left: 33px; top: 0px; position: absolute; width: 250px; height: 33px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
         writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-right.png) repeat scroll 0% 0%; left: "+innerLeft.toString()+"px; top: 0px; position: absolute; width: 40px; height: 33px;","style");writer.endElement("div");                
         //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-right.png) repeat scroll 0% 0%; left: 283px; top: 0px; position: absolute; width: 40px; height: 33px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
         writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-left.png) repeat scroll 0% 0%; left: 0px; top: 33px; position: absolute; width: 33px; height: "+innerHeight.toString()+"px;","style");writer.endElement("div");                
         //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-left.png) repeat scroll 0% 0%; left: 0px; top: 33px; position: absolute; width: 33px; height: 50px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
         writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-right.png) repeat scroll 0% 0%; left: "+innerLeft.toString()+"px; top: 33px; position: absolute; width: 40px; height: "+innerHeight.toString()+"px; ","style");writer.endElement("div");                
         //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-right.png) repeat scroll 0% 0%; left: 283px; top: 33px; position: absolute; width: 40px; height: 50px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
         writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-left.png) repeat scroll 0% 0%; left: 0px; top: "+innerTop.toString()+"px; position: absolute; width: 33px; height: 42px;","style");writer.endElement("div");                
         //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-left.png) repeat scroll 0% 0%; left: 0px; top: 83px; position: absolute; width: 33px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
         writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom.png) repeat scroll 0% 0%; left: 33px; top: "+innerTop.toString()+"px; position: absolute; width: "+innerWidth.toString()+"px; height: 42px;","style");writer.endElement("div");                
         //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom.png) repeat scroll 0% 0%; left: 33px; top: 83px; position: absolute; width: 250px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
         writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-right.png) repeat scroll 0% 0%; left: "+innerLeft.toString()+"px; top: "+innerTop.toString()+"px; position: absolute; width: 40px; height: 42px;","style");writer.endElement("div");                
         //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-right.png) repeat scroll 0% 0%; left: 283px; top: 83px; position: absolute; width: 40px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
         //writer.startElement("div", comp); writer.writeAttribute("style","cursor:pointer;background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/close-button.png) repeat scroll 0% 0%; left: "+leftCloseButton.toString()+"px; top: 19px; cursor: pointer; position: absolute; width: 16px; height: 16px; ","style");
        // writer.writeAttribute("onclick", "document.getElementById('"+clientId+"').style.display=none;", "onclick");writer.endElement("div");                
         //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/close-button.png) repeat scroll 0% 0%; left: 280px; top: 19px; cursor: pointer; position: absolute; width: 16px; height: 16px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
         writer.startElement("div", comp); writer.writeAttribute("style","background: white none repeat scroll 0% 0%; overflow: auto; position: absolute; left: 33px; top: 33px; width: "+innerWidth.toString()+"px; height: "+innerHeight.toString()+"px;","style");                
         // <div style="background: white none repeat scroll 0% 0%; overflow: auto; position: absolute; left: 33px; top: 33px; width: 250px; height: 50px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;">
                        /*<img src="2007-05-17T12:00:00Z" class="timeline-event-bubble-image"/>
                        <div class="timeline-event-bubble-title">
                            <a href="2007-05-17T12:00:00Z">MapFaces_Layer_WMS_4 2007-05-17T12:00:00Z</a>
                        </div>
                        <div class="timeline-event-bubble-body">CHL Reunion fronts</div>
                        <div class="timeline-event-bubble-time">Thu, 17 May 2007 10:00:00 GMT</div>
                        <div style="display: none;" class="timeline-event-bubble-wiki"/>*/     
                        
            writer.write(innerHTML);
         writer.endElement("div"); 
         // </div>
    writer.startElement("div", comp);writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-arrow.png) repeat scroll 0% 0%; left: "+innerMiddle.toString()+"px; top: "+innerTop.toString()+"px; position: absolute; width: 37px; height: 42px;","style");writer.endElement("div");
    //<div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-arrow.png) repeat scroll 0% 0%; left: 140px; top: 83px; position: absolute; width: 37px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>

    writer.endElement("div");               // </div>

writer.endElement("div");         //</div>
        
        }else{
            writer.startElement("div", comp);
            if (getStyleClass() == null)writer.writeAttribute("class","mf"+PopupTag.COMP_TYPE.substring(PopupTag.COMP_TYPE.lastIndexOf(".")+1,PopupTag.COMP_TYPE.length()),"styleclass");        
            writer.writeAttribute("id",clientId,"id");
        writer.writeAttribute("style","display:none;","style");  
            writer.endElement("div");
        }
             
        
        
        
        
        
        
        
        
        
        /***********************************************/
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
                    Integer realTop = (new Integer(Y)) - height;
                    top = "top:"+realTop.toString()+"px;";
               }
               if(params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_X") != null){
                    X = (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_X");
                    Integer realLeft = (new Integer(X)) - (width/2);
                    left = "left:"+realLeft.toString()+"px;";
               }
               
                Integer innerWidth = width-73;
                Integer innerHeight = height-75;
               Layer queryLayer = tmp.getVisibleLayers().get(tmp.getVisibleLayers().size()-1);
               innerHTML = "<iframe style='width:"+innerWidth.toString()+"px;height:"+innerHeight.toString()+"px;font-size:0.7em;font-family:verdana;border:none;overflow:hidden;z-index:3000;' id='popup' name='popup' src='"+queryLayer.getServer().getHref()+"?bbox="+tmp.getBoundingBox()+"&styles=&format="+queryLayer.getOutputFormat()+"&info_format=text/html&version="+queryLayer.getServer().getVersion()+"&srs=epsg:4326&request=GetFeatureInfo&layers=BlueMarble&query_layers="+queryLayer.getName()+"&width="+tmp.getWindowWidth()+"&height="+tmp.getWindowHeight()+"&x="+X+"&y="+Y+"'></iframe>";
            }
            comp.setModel((AbstractModelBase) tmp);

        }
        return;
    }
}
