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

import org.mapfaces.component.UIPopup;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.taglib.PopupTag;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class PopupRenderer extends WidgetBaseRenderer {

    private String innerHTML = null;
    private String top = "0px";
    private String left = "0px";
    private int width = 300;
    private int height = 200;

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeBegin(context, component);
        final UIPopup comp    = (UIPopup) component;
        final String clientId = comp.getClientId(context);

        if (getStyleClass() == null)
            writer.writeAttribute("class","mf"+PopupTag.COMP_TYPE.substring(PopupTag.COMP_TYPE.lastIndexOf(".")+1,PopupTag.COMP_TYPE.length()),"styleclass");

        final int innerWidth      = width-73;
        final int innerLeft       = width-40;
        final int innerHeight     = height-75;
        final int innerTop        = height-42;
        final int innerMiddle     = (width/2)-21;
        final int leftCloseButton = width-43;

        if(innerHTML != null){
            /***********************************************/
            writer.startElement("div", comp);
            if (getStyleClass() == null)writer.writeAttribute("class","mf"+PopupTag.COMP_TYPE.substring(PopupTag.COMP_TYPE.lastIndexOf(".")+1,PopupTag.COMP_TYPE.length()),"styleclass");
            writer.writeAttribute("id",clientId,"id");
            writer.writeAttribute("style","width:"+width+"px; height: "+height+"px; position: absolute; z-index: 1500;"+top+left,"style");
            //<div style="width: 323px; height: 125px; position: absolute; z-index: 1500;"+top+";"+left+";">
            writer.startElement("div", comp);writer.writeAttribute("style","width: 100%; height: 100%; position: relative;","style");
            //<div style="width: 100%; height: 100%; position: relative;">
            writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-left.png) repeat scroll 0% 0%; left: 0px; top: 0px; position: absolute; width: 33px; height: 33px;","style");writer.endElement("div");
            // <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-left.png) repeat scroll 0% 0%; left: 0px; top: 0px; position: absolute; width: 33px; height: 33px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top.png) repeat scroll 0% 0%; left: 33px; top: 0px; position: absolute; width: "+innerWidth+"px; height: 33px; ","style");writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top.png) repeat scroll 0% 0%; left: 33px; top: 0px; position: absolute; width: 250px; height: 33px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-right.png) repeat scroll 0% 0%; left: "+innerLeft+"px; top: 0px; position: absolute; width: 40px; height: 33px;","style");writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-right.png) repeat scroll 0% 0%; left: 283px; top: 0px; position: absolute; width: 40px; height: 33px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-left.png) repeat scroll 0% 0%; left: 0px; top: 33px; position: absolute; width: 33px; height: "+innerHeight+"px;","style");writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-left.png) repeat scroll 0% 0%; left: 0px; top: 33px; position: absolute; width: 33px; height: 50px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-right.png) repeat scroll 0% 0%; left: "+innerLeft+"px; top: 33px; position: absolute; width: 40px; height: "+innerHeight+"px; ","style");writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-right.png) repeat scroll 0% 0%; left: 283px; top: 33px; position: absolute; width: 40px; height: 50px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-left.png) repeat scroll 0% 0%; left: 0px; top: "+innerTop+"px; position: absolute; width: 33px; height: 42px;","style");writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-left.png) repeat scroll 0% 0%; left: 0px; top: 83px; position: absolute; width: 33px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom.png) repeat scroll 0% 0%; left: 33px; top: "+innerTop+"px; position: absolute; width: "+innerWidth+"px; height: 42px;","style");writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom.png) repeat scroll 0% 0%; left: 33px; top: 83px; position: absolute; width: 250px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp); writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-right.png) repeat scroll 0% 0%; left: "+innerLeft+"px; top: "+innerTop+"px; position: absolute; width: 40px; height: 42px;","style");writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-right.png) repeat scroll 0% 0%; left: 283px; top: 83px; position: absolute; width: 40px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            //writer.startElement("div", comp); writer.writeAttribute("style","cursor:pointer;background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/close-button.png) repeat scroll 0% 0%; left: "+leftCloseButton+"px; top: 19px; cursor: pointer; position: absolute; width: 16px; height: 16px; ","style");
            // writer.writeAttribute("onclick", "document.getElementById('"+clientId+"').style.display=none;", "onclick");writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/close-button.png) repeat scroll 0% 0%; left: 280px; top: 19px; cursor: pointer; position: absolute; width: 16px; height: 16px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp); writer.writeAttribute("style","background: white none repeat scroll 0% 0%; overflow: auto; position: absolute; left: 33px; top: 33px; width: "+innerWidth+"px; height: "+innerHeight+"px;","style");
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
            writer.startElement("div", comp);writer.writeAttribute("style","background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-arrow.png) repeat scroll 0% 0%; left: "+innerMiddle+"px; top: "+innerTop+"px; position: absolute; width: 37px; height: 42px;","style");writer.endElement("div");
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

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getRendersChildren() {
        return false;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        final UIPopup comp = (UIPopup) component;

        if (comp.isDebug()) {
            System.out.println("        PopupRenderer decode");
        }

        if (context.getExternalContext().getRequestParameterMap() != null) {
            final Context tmp = (Context) comp.getModel();
            final Map params  = context.getExternalContext().getRequestParameterMap();
            //Layer layer = comp.getLayer();
            String X = "170";
            String Y = "160";
            if(params.get("org.mapfaces.ajax.ACTION") != null && ((String)params.get("org.mapfaces.ajax.ACTION")).equals("getFeatureInfo")){
                if(params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y") != null){
                    Y = (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y");
                    final int realTop = (new Integer(Y)) - height;
                    top = "top:"+realTop+"px;";
                }
                if(params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_X") != null){
                    X = (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_X");
                    final int realLeft = (new Integer(X)) - (width/2);
                    left = "left:"+realLeft+"px;";
                }

                final int innerWidth   = width-73;
                final int innerHeight  = height-75;
                final Layer queryLayer = tmp.getVisibleLayers().get(tmp.getVisibleLayers().size()-1);
                innerHTML = new StringBuilder("<iframe style='width:").append(innerWidth)
                        .append("px;height:").append(innerHeight)
                        .append("px;font-size:0.7em;font-family:verdana;border:none;overflow:hidden;z-index:3000;' id='popup' name='popup' src='").append(queryLayer.getServer().getHref())
                        .append("?bbox=").append(tmp.getBoundingBox())
                        .append("&styles=&format=").append(queryLayer.getOutputFormat())
                        .append("&info_format=text/html&version=").append(queryLayer.getServer().getVersion())
                        .append("&srs=epsg:4326&request=GetFeatureInfo&layers=BlueMarble&query_layers=").append(queryLayer.getName())
                        .append("&width=").append(tmp.getWindowWidth())
                        .append("&height=").append(tmp.getWindowHeight())
                        .append("&x=").append(X)
                        .append("&y=").append(Y)
                        .append("'></iframe>")
                        .toString();
            }
            comp.setModel((AbstractModelBase) tmp);

        }
        return;
    }
}
