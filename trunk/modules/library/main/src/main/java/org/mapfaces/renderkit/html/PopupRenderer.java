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
import javax.faces.context.ResponseWriter;
import org.mapfaces.component.UIPopup;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 */
public class PopupRenderer extends WidgetBaseRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeBegin(context, component);
        final UIPopup comp = (UIPopup) component;
        final String clientId = comp.getClientId(context);

        final int innerWidth = comp.getWidth() - 73;
        final int innerLeft = comp.getWidth() - 40;
        final int innerHeight = comp.getHeight() - 75;
        final int innerTop = comp.getHeight() - 42;
        final int innerMiddle = (comp.getWidth() / 2) - 21;
        final int leftCloseButton = comp.getWidth() - 43;
        
        ResponseWriter responseWriter = context.getResponseWriter();

        responseWriter.startElement("div", comp);
        
        responseWriter.writeAttribute("id", clientId, "id");
        
        
        if (comp.getInnerHTML() != null) {
            if (getStyleClass() == null) {
                responseWriter.writeAttribute("class", "mfPopup", "styleclass");
            }
            responseWriter.writeAttribute("style", "width:" + comp.getWidth() + "px; height: " + comp.getHeight() + "px; position: absolute; z-index: 1500;" + comp.getTop() + comp.getLeft(), "style");
            //<div style="width: 323px; height: 125px; position: absolute; z-index: 1500;"+top+";"+left+";">
            writer.startElement("div", comp);
            writer.writeAttribute("style", "width: 100%; height: 100%; position: relative;", "style");
            //<div style="width: 100%; height: 100%; position: relative;">
            writer.startElement("div", comp);
            writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-left.png) repeat scroll 0% 0%; left: 0px; top: 0px; position: absolute; width: 33px; height: 33px;", "style");
            writer.endElement("div");
            // <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-left.png) repeat scroll 0% 0%; left: 0px; top: 0px; position: absolute; width: 33px; height: 33px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp);
            writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top.png) repeat scroll 0% 0%; left: 33px; top: 0px; position: absolute; width: " + innerWidth + "px; height: 33px; ", "style");
            writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top.png) repeat scroll 0% 0%; left: 33px; top: 0px; position: absolute; width: 250px; height: 33px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp);
            writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-right.png) repeat scroll 0% 0%; left: " + innerLeft + "px; top: 0px; position: absolute; width: 40px; height: 33px;", "style");
            writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-right.png) repeat scroll 0% 0%; left: 283px; top: 0px; position: absolute; width: 40px; height: 33px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp);
            writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-left.png) repeat scroll 0% 0%; left: 0px; top: 33px; position: absolute; width: 33px; height: " + innerHeight + "px;", "style");
            writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-left.png) repeat scroll 0% 0%; left: 0px; top: 33px; position: absolute; width: 33px; height: 50px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp);
            writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-right.png) repeat scroll 0% 0%; left: " + innerLeft + "px; top: 33px; position: absolute; width: 40px; height: " + innerHeight + "px; ", "style");
            writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-right.png) repeat scroll 0% 0%; left: 283px; top: 33px; position: absolute; width: 40px; height: 50px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp);
            writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-left.png) repeat scroll 0% 0%; left: 0px; top: " + innerTop + "px; position: absolute; width: 33px; height: 42px;", "style");
            writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-left.png) repeat scroll 0% 0%; left: 0px; top: 83px; position: absolute; width: 33px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp);
            writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom.png) repeat scroll 0% 0%; left: 33px; top: " + innerTop + "px; position: absolute; width: " + innerWidth + "px; height: 42px;", "style");
            writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom.png) repeat scroll 0% 0%; left: 33px; top: 83px; position: absolute; width: 250px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp);
            writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-right.png) repeat scroll 0% 0%; left: " + innerLeft + "px; top: " + innerTop + "px; position: absolute; width: 40px; height: 42px;", "style");
            writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-right.png) repeat scroll 0% 0%; left: 283px; top: 83px; position: absolute; width: 40px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp);
            writer.writeAttribute("style", "cursor:pointer;background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/close-button.png) repeat scroll 0% 0%; left: " + leftCloseButton + "px; top: 19px; cursor: pointer; position: absolute; width: 16px; height: 16px; ", "style");
            writer.writeAttribute("onclick", "document.getElementById('" + clientId + "').className='mfPopupInvisible';", "onclick");
            writer.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/close-button.png) repeat scroll 0% 0%; left: 280px; top: 19px; cursor: pointer; position: absolute; width: 16px; height: 16px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            writer.startElement("div", comp);
            writer.writeAttribute("style", "background: white none repeat scroll 0% 0%; overflow: auto; position: absolute; left: 33px; top: 33px; width: " + innerWidth + "px; height: " + innerHeight + "px;", "style");
            // <div style="background: white none repeat scroll 0% 0%; overflow: auto; position: absolute; left: 33px; top: 33px; width: 250px; height: 50px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;">
                           /*<img src="2007-05-17T12:00:00Z" class="timeline-event-bubble-image"/>
            <div class="timeline-event-bubble-title">
            <a href="2007-05-17T12:00:00Z">MapFaces_Layer_WMS_4 2007-05-17T12:00:00Z</a>
            </div>
            <div class="timeline-event-bubble-body">CHL Reunion fronts</div>
            <div class="timeline-event-bubble-time">Thu, 17 May 2007 10:00:00 GMT</div>
            <div style="display: none;" class="timeline-event-bubble-wiki"/>*/

            writer.write(comp.getInnerHTML());
            writer.endElement("div");
            // </div>
            writer.startElement("div", comp);
            writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-arrow.png) repeat scroll 0% 0%; left: " + innerMiddle + "px; top: " + innerTop + "px; position: absolute; width: 37px; height: 42px;", "style");
            writer.endElement("div");
            //<div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-arrow.png) repeat scroll 0% 0%; left: 140px; top: 83px; position: absolute; width: 37px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>

            writer.endElement("div");               // </div>

            responseWriter.endElement("div");         //</div>

        } else {
            //responseWriter.writeAttribute("style", "display:block;", "style");
            if (getStyleClass() == null) {
                responseWriter.writeAttribute("class", "mfPopupInvisible", "styleclass");
            }
        }
        
        responseWriter.flush();

    }
    
        /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter responseWriter = context.getResponseWriter();
        if (responseWriter == null) {
            responseWriter = FacesUtils.getResponseWriter2(context);
        }
        responseWriter.endElement("div");
        
        responseWriter.flush();
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

//        if (context.getExternalContext().getRequestParameterMap() != null) {
//            final Context model = (Context) comp.getModel();
//            final Map params = context.getExternalContext().getRequestParameterMap();
//            
//            String X = "170";
//            String Y = "160";
//            if (params.get("org.mapfaces.ajax.ACTION") != null && ((String) params.get("org.mapfaces.ajax.ACTION")).equals("getFeatureInfo")) {
//                if (params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y") != null) {
//                    Y = (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y");
//                    final int realTop = (new Integer(Y)) - comp.getHeight();
//                    comp.setTop("top:" + realTop + "px;");
//                }
//                if (params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_X") != null) {
//                    X = (String) params.get("org.mapfaces.ajax.ACTION_GETFEATUREINFO_X");
//                    final int realLeft = (new Integer(X)) - (comp.getWidth() / 2);
//                    comp.setLeft("left:" + realLeft + "px;");
//                }
//
//                final int innerWidth = comp.getWidth() - 73;
//                final int innerHeight = comp.getHeight() - 75;
//                final Layer queryLayer = model.getVisibleLayers().get(model.getVisibleLayers().size() - 1);
//                comp.setInnerHTML(new StringBuilder("<iframe style='width:")
//                        .append(innerWidth)
//                        .append("px;height:")
//                        .append(innerHeight)
//                        .append("px;font-size:0.7em;font-family:verdana;border:none;overflow:hidden;z-index:3000;' id='popup' name='popup' src='")
//                        .append(queryLayer.getServer().getHref())
//                        .append("?BBOX=").append(model.getBoundingBox())
//                        .append("&STYLES=")
//                        .append("&FORMAT=").append(queryLayer.getOutputFormat())
//                        .append("&INFO_FORMAT=text/html")
//                        .append("&VERSION=").append(queryLayer.getServer().getVersion())
//                        .append("&SRS=").append(model.getSrs().toUpperCase())
//                        .append("&REQUEST=GetFeatureInfo")
//                        .append("&LAYERS=").append(queryLayer.getName())
//                        .append("&QUERY_LAYERS=").append(queryLayer.getName())
//                        .append("&WIDTH=").append(model.getWindowWidth())
//                        .append("&HEIGHT=").append(model.getWindowHeight())
//                        .append("&X=").append(X)
//                        .append("&Y=").append(Y)
//                        .append("'></iframe>").toString());
//            } else if (params.get("org.mapfaces.ajax.ACTION") != null && ((String) params.get("org.mapfaces.ajax.ACTION")).equals("getCoverage")) {
//
//                final Layer queryLayer = model.getVisibleLayers().get(model.getVisibleLayers().size() - 1);
//
//                String elevation = null;
//                if (queryLayer.getElevation() != null) {
//                    elevation = queryLayer.getElevation().getUserValue() + "," + queryLayer.getElevation().getUserValue();
//                }
//                String time = null;
//                if (queryLayer.getTime() != null) {
//                    time = queryLayer.getTime().getUserValue();
//                }
//                String[] windowPixel = null;
//                if ((String) params.get("org.mapfaces.ajax.ACTION_GETCOVERAGE_PIXEL") != null) {
//                    windowPixel = ((String) params.get("org.mapfaces.ajax.ACTION_GETCOVERAGE_PIXEL")).split(",");
//                }
//                String innerHTML = "<iframe style='display:none' id='popup' name='popup' src='" + queryLayer.getServer().getHref().substring(0, queryLayer.getServer().getHref().lastIndexOf("/")) + "/wcs" + 
//                        "?BBOX=" + (String) params.get("org.mapfaces.ajax.ACTION_GETCOVERAGE_AOI");
//                if (elevation != null) {
//                    innerHTML += "," + elevation;
//                }
//                innerHTML += "&STYLES=" + "&FORMAT=" + (String) params.get("org.mapfaces.ajax.ACTION_GETCOVERAGE_FORMAT") + 
//                        "&VERSION=" + "1.0.0" + "&CRS=" + model.getSrs() + 
//                        "&REQUEST=GetCoverage'" + "&COVERAGE=" + queryLayer.getName() + 
//                        "&WIDTH=" + windowPixel[0] + "&HEIGHT=" + windowPixel[1];
//                if (time != null) {
//                    innerHTML += "&TIME=" + time;
//                }
//                innerHTML += "'></iframe>";
//                // innerHTML = "<iframe style='display:none' id='popup' name='popup' src='http://demo.geomatys.fr/constellation/WS/wcs?bbox=-14978036.532703482,-5209751.837462081,-10369409.907256257,-1402625.4947013294,5.0,5.0&styles=&format=matrix&version=1.0.0&crs=EPSG:3395&request=GetCoverage&coverage=AO_Coriolis_(Temp)&width=1259&height=176&time=2007-06-20T12:00:00Z'></iframe>";
//                comp.setInnerHTML(innerHTML);
//                System.out.println("innerHTML = " + innerHTML);
//            } else {
//                //init the popup innerHtml in others decode process
//                comp.setInnerHTML(null);
//            }
//            comp.setModel((AbstractModelBase) model);
//
//        }
        return;
    }
}
