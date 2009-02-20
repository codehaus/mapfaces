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
import org.mapfaces.component.UIPopup;
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
            responseWriter.startElement("div", comp);
            responseWriter.writeAttribute("style", "width: 100%; height: 100%; position: relative;", "style");
            
            //<div style="width: 100%; height: 100%; position: relative;">
            responseWriter.startElement("div", comp);
            responseWriter.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-left.png) repeat scroll 0% 0%; left: 0px; top: 0px; position: absolute; width: 33px; height: 33px;", "style");
            responseWriter.endElement("div");
            // <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-left.png) repeat scroll 0% 0%; left: 0px; top: 0px; position: absolute; width: 33px; height: 33px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            responseWriter.startElement("div", comp);
            responseWriter.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top.png) repeat scroll 0% 0%; left: 33px; top: 0px; position: absolute; width: " + innerWidth + "px; height: 33px; ", "style");
            responseWriter.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top.png) repeat scroll 0% 0%; left: 33px; top: 0px; position: absolute; width: 250px; height: 33px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            responseWriter.startElement("div", comp);
            responseWriter.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-right.png) repeat scroll 0% 0%; left: " + innerLeft + "px; top: 0px; position: absolute; width: 40px; height: 33px;", "style");
            responseWriter.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-top-right.png) repeat scroll 0% 0%; left: 283px; top: 0px; position: absolute; width: 40px; height: 33px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            responseWriter.startElement("div", comp);
            responseWriter.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-left.png) repeat scroll 0% 0%; left: 0px; top: 33px; position: absolute; width: 33px; height: " + innerHeight + "px;", "style");
            responseWriter.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-left.png) repeat scroll 0% 0%; left: 0px; top: 33px; position: absolute; width: 33px; height: 50px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            responseWriter.startElement("div", comp);
            responseWriter.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-right.png) repeat scroll 0% 0%; left: " + innerLeft + "px; top: 33px; position: absolute; width: 40px; height: " + innerHeight + "px; ", "style");
            responseWriter.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-right.png) repeat scroll 0% 0%; left: 283px; top: 33px; position: absolute; width: 40px; height: 50px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            responseWriter.startElement("div", comp);
            responseWriter.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-left.png) repeat scroll 0% 0%; left: 0px; top: " + innerTop + "px; position: absolute; width: 33px; height: 42px;", "style");
            responseWriter.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-left.png) repeat scroll 0% 0%; left: 0px; top: 83px; position: absolute; width: 33px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            responseWriter.startElement("div", comp);
            responseWriter.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom.png) repeat scroll 0% 0%; left: 33px; top: " + innerTop + "px; position: absolute; width: " + innerWidth + "px; height: 42px;", "style");
            responseWriter.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom.png) repeat scroll 0% 0%; left: 33px; top: 83px; position: absolute; width: 250px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            responseWriter.startElement("div", comp);
            responseWriter.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-right.png) repeat scroll 0% 0%; left: " + innerLeft + "px; top: " + innerTop + "px; position: absolute; width: 40px; height: 42px;", "style");
            responseWriter.endElement("div");
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-right.png) repeat scroll 0% 0%; left: 283px; top: 83px; position: absolute; width: 40px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            responseWriter.startElement("div", comp);
            responseWriter.writeAttribute("style", "cursor:pointer;background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/close-button.png) repeat scroll 0% 0%; left: " + leftCloseButton + "px; top: 19px; cursor: pointer; position: absolute; width: 16px; height: 16px; ", "style");
            responseWriter.writeAttribute("onclick", "document.getElementById('" + clientId + "').className='mfPopupInvisible';", "onclick");
            responseWriter.endElement("div");
            
            
            
            //  <div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/close-button.png) repeat scroll 0% 0%; left: 280px; top: 19px; cursor: pointer; position: absolute; width: 16px; height: 16px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>
            responseWriter.startElement("div", comp);
            responseWriter.writeAttribute("style", "background: white none repeat scroll 0% 0%; overflow: auto; position: absolute; left: 33px; top: 33px; width: " + innerWidth + "px; height: " + innerHeight + "px;", "style");

            //writing innerHtml here
            responseWriter.write(comp.getInnerHTML());
            
            responseWriter.endElement("div");            
            // </div>
            
            responseWriter.startElement("div", comp);
            responseWriter.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-arrow.png) repeat scroll 0% 0%; left: " + innerMiddle + "px; top: " + innerTop + "px; position: absolute; width: 37px; height: 42px;", "style");
            responseWriter.endElement("div");
            //<div style="background: transparent url(resource.jsf?r=/org/mapfaces/resources/timeline/api/images/bubble-bottom-arrow.png) repeat scroll 0% 0%; left: 140px; top: 83px; position: absolute; width: 37px; height: 42px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"/>


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
        return;
    }
}
