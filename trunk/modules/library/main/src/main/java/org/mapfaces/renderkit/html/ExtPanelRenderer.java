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
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.mapfaces.util.RendererUtils.HTML;
import org.mapfaces.component.UIExtPanel;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.util.FacesUtils;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class ExtPanelRenderer extends Renderer {

    //private static final Logger LOGGER = Logger.getLogger(ExtPanelRenderer.class.getName());

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);
        
        final UIExtPanel comp = (UIExtPanel) component;
        //final boolean debug = comp.isDebug();
        final String clientId = comp.getClientId(context);
        final String title = (comp.getTitle() != null)? comp.getTitle()  : "";
        final String style = (comp.getStyle() != null)? comp.getStyle() : "";
        final String styleClass = (comp.getStyleClass() != null)? comp.getStyleClass() : "";
        final String headerStyle = (comp.getHeaderStyle() != null)? comp.getHeaderStyle() : "";
        final String headerStyleClass = (comp.getHeaderStyleClass() != null)? comp.getHeaderStyleClass() : "";
        final String bodyStyle = (comp.getBodyStyle() != null)? comp.getBodyStyle() : "";
        final String bodyStyleClass = (comp.getBodyStyleClass() != null)? comp.getBodyStyleClass() : "";
        final int width = (comp.getWidth() != 0)? comp.getWidth() : 200;
        final int height = ( comp.getHeight() != 0)? comp.getHeight() : 200;

        //begin to render the component.
        final ResponseWriter writer = context.getResponseWriter();
        
        //Write the css once per page
        final ExternalContext extContext = context.getExternalContext();
        if (! extContext.getRequestMap().containsKey("cssflag.ExtPanel")) {
            extContext.getRequestMap().put("cssflag.ExtPanel", Boolean.TRUE);
            writer.write("<link rel='stylesheet' type='text/css' href='" + ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/css/ext-widgets.css", null) + "'/>");
        }
        
        
        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.id_ATTRIBUTE, clientId, "clientId");
        writer.writeAttribute(HTML.style_ATTRIBUTE, "left: 0px; top: 0px; width: "+width+"px; " + style, HTML.style_ATTRIBUTE);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "x-panel x-border-panel " + styleClass, "styleclass");

        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "x-panel-tl", HTML.class_ATTRIBUTE);

        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "x-panel-tr", HTML.class_ATTRIBUTE);

        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "x-panel-tc", HTML.class_ATTRIBUTE);

        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.id_ATTRIBUTE, clientId + "-ext-panel-head", HTML.id_ATTRIBUTE);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "x-panel-header x-unselectable " + headerStyleClass, HTML.class_ATTRIBUTE);
        writer.writeAttribute(HTML.style_ATTRIBUTE, "height:15px; "+headerStyle, HTML.style_ATTRIBUTE);

        writer.startElement(HTML.SPAN_ELEM, component);
        writer.writeAttribute(HTML.id_ATTRIBUTE, component.getClientId(context) + "-ext-panel-title", HTML.id_ATTRIBUTE);
        writer.write(title);
        writer.endElement(HTML.SPAN_ELEM);
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM); //end of head divs
        
        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.id_ATTRIBUTE, clientId + "-ext-panel-body", HTML.id_ATTRIBUTE);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "x-panel-bwrap "+bodyStyleClass, HTML.class_ATTRIBUTE);
        writer.writeAttribute(HTML.style_ATTRIBUTE, "width: "+width+"px;height:"+height+"px;"+bodyStyle, HTML.style_ATTRIBUTE);
        
        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "x-panel-ml", HTML.class_ATTRIBUTE);
                
        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "x-panel-mr", HTML.class_ATTRIBUTE);
        
        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "x-panel-mc", HTML.class_ATTRIBUTE);
        
        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.id_ATTRIBUTE, clientId + "-ext-panel-content", HTML.id_ATTRIBUTE);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "x-panel-body " + bodyStyleClass, HTML.class_ATTRIBUTE);
        writer.writeAttribute(HTML.style_ATTRIBUTE, "height: "+(height - 15)+"px; width: 100%; "+bodyStyle, HTML.style_ATTRIBUTE);
        
        writer.flush();
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        
        final ResponseWriter writer = context.getResponseWriter();
        
        
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM); //end of body divs
        
        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "x-panel-bl x-panel-nofooter", HTML.class_ATTRIBUTE);
        
        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "x-panel-br", HTML.class_ATTRIBUTE);
        
        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "x-panel-bc", HTML.class_ATTRIBUTE);
        writer.endElement(HTML.DIV_ELEM);
        
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM);
        writer.flush();
    }

    
    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        final List<UIComponent> childrens = component.getChildren();
        for (final UIComponent tmp : childrens) {
            FacesUtils.encodeRecursive(context, tmp);
        }
    }
    
    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null) {
            throw new NullPointerException("context should not be null");
        }
        if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

}
