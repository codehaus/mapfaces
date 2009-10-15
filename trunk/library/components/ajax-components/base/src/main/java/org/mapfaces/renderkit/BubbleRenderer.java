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

package org.mapfaces.renderkit;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.mapfaces.component.UIBubble;
import org.mapfaces.share.utils.FacesUtils;

/**
 *
 * @author Mehdi Sidhoum (Geomatys)
 */
public class BubbleRenderer extends Renderer {

    /**
     * {@inheritDoc }
     */
    @Override
    @SuppressWarnings("BubbleRenderer")
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);
        //casting the component.
        final UIBubble comp = (UIBubble) component;
        final String clientId = comp.getClientId(context);
        final int width = (comp.getWidth() == 0) ? 300 : comp.getWidth();
        final int height = (comp.getHeight() == 0) ? 300 : comp.getHeight();
        final String style = (comp.getStyle() == null) ? "" : comp.getStyle();
        final String styleClass = (comp.getStyleClass() == null) ? "" : comp.getStyleClass();
        String id = comp.getId().replace("-", "_");
        ResponseWriter writer = context.getResponseWriter();

        if (comp.isSpryEffect() && comp.isLoadSpryJs()) {
            final ExternalContext extContext = context.getExternalContext();
            if (!extContext.getRequestMap().containsKey("spryEffectBubble")) {
                extContext.getRequestMap().put("spryEffectBubble", Boolean.TRUE);
                writer.startElement("script", component);
                writer.writeAttribute("src", "resource.jsf?r=/org/mapfaces/resources/js/spry/SpryEffects.js", null);
                writer.writeAttribute("type", "text/javascript", null);
                writer.endElement("script");
            }
        }

        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "id");
        writer.writeAttribute("style", style + "width:" + width + "px; height: " + height + "px; position: absolute; z-index: 1000;", "style");

        writer.startElement("div", comp);
        writer.writeAttribute("style", "width: 100%; height: 100%; position: relative;", "style");

        //top divs
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId + "-divtopleft", "id");
        writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/img/bubble/bubble-top-left.png) repeat scroll 0% 0%; left: 0px; top: 0px; position: absolute; width: 33px; height: 33px;", "style");
        writer.endElement("div");
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId + "-divtop", "id");
        writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/img/bubble/bubble-top.png) repeat scroll 0% 0%; left: 33px; top: 0px; position: absolute; width: " + (width - 66) + "px; height: 33px;", "style");
        writer.endElement("div");
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId + "-divtopright", "id");
        writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/img/bubble/bubble-top-right.png) repeat scroll 0% 0%; top: 0px; position: absolute; width: 33px; height: 33px; left: " + (width - 33) + "px;", "style");
        writer.endElement("div");

        //middle divs
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId + "-leftdiv", "id");
        writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/img/bubble/bubble-left.png) repeat scroll 0% 0%; left: 0px; top: 33px; position: absolute; width: 33px; height: " + (height - 66) + "px;", "style");
        writer.endElement("div");
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId + "-rightdiv", "id");
        writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/img/bubble/bubble-right.png) repeat scroll 0% 0%; top: 33px; position: absolute; width: 33px; height: " + (height - 66) + "px; left: " + (width - 33) + "px; ", "style");
        writer.endElement("div");

        //bottom divs
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId + "-bottomleftdiv", "id");
        writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/img/bubble/bubble-bottom-left.png) repeat scroll 0% 0%; left: 0px; top: " + (height - 33) + "px; position: absolute; width: 33px; height: 33px;", "style");
        writer.endElement("div");
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId + "-bottomdiv", "id");
        writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/img/bubble/bubble-bottom.png) repeat scroll 0% 0%; top: " + (height - 33) + "px; position: absolute; height: 33px; left: 33px; width: " + (width - 66) + "px;", "style");
        writer.endElement("div");
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId + "-bottomrightdiv", "id");
        writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/img/bubble/bubble-bottom-right.png) repeat scroll 0% 0%; position: absolute; width: 33px; top: " + (height - 33) + "px; height: 33px; left: " + (width - 33) + "px;", "style");
        writer.endElement("div");

        //close div
        String onclick = "document.getElementById('" + clientId + "').style.display='none';";
        if (comp.isSpryEffect()) {
            onclick = id + "closeBubble();";
        }
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId + "-closediv", "id");
        writer.writeAttribute("onclick", onclick, "onclick");
        writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/img/bubble/close-button.png) repeat scroll 0% 0%; left: " + (width - 36) + "px; top: 19px; cursor: pointer; position: absolute; width: 16px; height: 16px; ", "style");
        writer.endElement("div");

        //content div
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId + "-contentdiv", "id");
        writer.writeAttribute("style", "background: white none repeat scroll 0% 0%; overflow: auto; position: absolute; left: 33px; top: 33px; width: " + (width - 66) + "px; height: " + (height - 66) + "px;", "style");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        if (writer == null) {
            writer = FacesUtils.getResponseWriter2(context);
        }
        final UIBubble comp = (UIBubble) component;
        final String clientId = comp.getClientId(context);
        final int width = (comp.getWidth() == 0) ? 300 : comp.getWidth();
        final int height = (comp.getHeight() == 0) ? 300 : comp.getHeight();


        writer.endElement("div");

        //arrows divs
        if (!comp.isTopArrow()) {
            writer.startElement("div", comp);
            writer.writeAttribute("id", clientId + "-bottomarrowdiv", "id");
            writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/img/bubble/bubble-bottom-arrow.png) repeat scroll 0% 0%; top: " + (height - 33) + "px; position: absolute; height: 33px; left: " + Math.round((width - 33) / 2) + "px; width: 37px;", "style");
            writer.endElement("div");
        } else {
            writer.startElement("div", comp);
            writer.writeAttribute("id", clientId + "-toparrowdiv", "id");
            writer.writeAttribute("style", "background: transparent url(resource.jsf?r=/org/mapfaces/resources/img/bubble/bubble-top-arrow.png) repeat scroll 0% 0%; top: 0; position: absolute; height: 33px; left: " + Math.round((width - 33) / 2) + "px; width: 37px;", "style");
            writer.endElement("div");
        }

        writer.endElement("div");
        writer.endElement("div"); //close the main div

        String id = comp.getId().replace("-", "_");
        if (comp.isSpryEffect()) {
            writer.write("<script> \n" +
                    "function " + id + "_startFadeBubble(){\n" +
                    "var list=document.getElementById('" + clientId + "');\n" +
                    "if(list.style.opacity==0)list.style.display='block';\n" +
                    "}\n" +
                    "function " + id + "_finishFadeBubble(){\n" +
                    "var list=document.getElementById('" + clientId + "');\n" +
                    "if(list.style.opacity==0) {list.style.display='none';}\n" +
                    "if(typeof AC != 'undefined' && AC.Detector.isIE() && list.style.opacity==0){list.style.display='block';}\n" +
                    "}\n" +
                    "if (document.getElementById('" + clientId + "')) {\n" +
                    "var " + id + "_BubbleFade = new Spry.Effect.Fade('" + clientId + "', {toggle:true,duration:700, setup:" + id + "_startFadeBubble, finish:" + id + "_finishFadeBubble});\n" +
                    "}\n" +
                    "function " + id + "closeBubble(){if (" + id + "_BubbleFade.direction == false){" + id + "_BubbleFade.doToggle(); " + id + "_BubbleFade.start();} else if (" + id + "_BubbleFade.direction == 1) { " + id + "_BubbleFade.start();}}" +
                    "</script>");
        }
    }

    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        final UIBubble comp = (UIBubble) component;
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        }
        if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        if (component.getChildCount() == 0) {
            return;
        }
        for (final UIComponent tmp : component.getChildren()) {
            FacesUtils.encodeRecursive(context, tmp);
        }
    }
}
