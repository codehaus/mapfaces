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
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.mapfaces.component.UIExtPanel;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.util.FacesUtils;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class ExtPanelRenderer extends Renderer {

    private static final Logger logger = Logger.getLogger("org.mapfaces.renderkit.html.ExtPanelRenderer");

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

        final boolean debug = comp.isDebug();
        final String clientId = comp.getClientId(context);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%   title = "+comp.getTitle());
        final String title = (comp.getTitle() != null)? comp.getTitle()  : "";
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> title = "+title);
        final String style = comp.getStyle();
        final String styleClass = comp.getStyleClass();
        final String headerStyle = comp.getHeaderStyle();
        final String headerStyleClass = comp.getHeaderStyleClass();
        final String bodyStyle = comp.getBodyStyle();
        final String bodyStyleClass = comp.getBodyStyleClass();
        final int width = (comp.getWidth() != 0)? comp.getWidth() : 200;
        final int height = ( comp.getHeight() != 0)? comp.getHeight() : 200;

        //begin to render the component.
        ResponseWriter writer = context.getResponseWriter();
        
        writer.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/css/ext-widgets.css", null) + "\"/>");
        
        writer.startElement("div", component);
        writer.writeAttribute("id", clientId, "clientId");
        writer.writeAttribute("style", "left: 0px; top: 271px; width: "+width+"px; " + style, "style");
        writer.writeAttribute("class", "x-panel x-border-panel " + styleClass, "styleclass");

        writer.startElement("div", component);
        writer.writeAttribute("class", "x-panel-tl", "class");

        writer.startElement("div", component);
        writer.writeAttribute("class", "x-panel-tr", "class");

        writer.startElement("div", component);
        writer.writeAttribute("class", "x-panel-tc", "class");

        writer.startElement("div", component);
        writer.writeAttribute("id", clientId + "-ext-panel-head", "id");
        writer.writeAttribute("class", "x-panel-header x-unselectable " + headerStyleClass, "class");
        writer.writeAttribute("style", headerStyle, "style");

        writer.startElement("span", component);
        writer.writeAttribute("id", component.getClientId(context) + "-ext-panel-title", "id");
        writer.write(title);
        writer.endElement("span");
        writer.endElement("div");
        writer.endElement("div");
        writer.endElement("div");
        writer.endElement("div"); //end of head divs
        
        writer.startElement("div", component);
        writer.writeAttribute("id", clientId + "-ext-panel-body", "id");
        writer.writeAttribute("class", "x-panel-bwrap", "class");
        writer.writeAttribute("style", "width: "+width+"px;height:"+height+"px", "style");
        
        writer.startElement("div", component);
        writer.writeAttribute("class", "x-panel-ml", "class");
                
        writer.startElement("div", component);
        writer.writeAttribute("class", "x-panel-mr", "class");
        
        writer.startElement("div", component);
        writer.writeAttribute("class", "x-panel-mc", "class");
        
        writer.startElement("div", component);
        writer.writeAttribute("id", clientId + "-ext-panel-content", "id");
        writer.writeAttribute("class", "x-panel-body " + bodyStyleClass, "class");
        writer.writeAttribute("style", "height: "+(height - 15)+"px; width: 100%; "+bodyStyle, "style");
        
        writer.flush();
    }

    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        
        ResponseWriter writer = context.getResponseWriter();
        
        
        writer.endElement("div");
        writer.endElement("div");
        writer.endElement("div");
        writer.endElement("div"); //end of body divs
        
        writer.startElement("div", component);
        writer.writeAttribute("class", "x-panel-bl x-panel-nofooter", "class");
        
        writer.startElement("div", component);
        writer.writeAttribute("class", "x-panel-br", "class");
        
        writer.startElement("div", component);
        writer.writeAttribute("class", "x-panel-bc", "class");
        writer.endElement("div");
        
        writer.endElement("div");
        writer.endElement("div");
        writer.endElement("div");
        writer.endElement("div");
        writer.flush();
    }

    public void decode(FacesContext context, UIComponent component) {
        return;
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
