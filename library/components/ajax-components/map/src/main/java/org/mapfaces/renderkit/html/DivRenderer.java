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
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.mapfaces.component.UIDiv;
import org.mapfaces.share.utils.RendererUtils.HTML;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class DivRenderer extends Renderer {
    private static final Logger LOGGER = Logger.getLogger(DivRenderer.class.getName());
    
    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        assertValid(context, component);
        final UIDiv comp = (UIDiv) component;
        // suppress rendering if "rendered" property on the component is false.
        if (!comp.isRendered()) {
            return;
        }
        
        final ResponseWriter writer = context.getResponseWriter();
        writer.startElement(HTML.DIV_ELEM, component);
        writer.writeAttribute(HTML.id_ATTRIBUTE, component.getClientId(context), "clientId");
        writer.writeAttribute(HTML.style_ATTRIBUTE, comp.getStyle(), HTML.style_ATTRIBUTE);
        writer.writeAttribute(HTML.class_ATTRIBUTE,  comp.getStyleClass(), "styleClass");
        writer.flush();
    }

    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        writer.endElement(HTML.DIV_ELEM);
        writer.flush();
    }

    public void decode(FacesContext context, UIComponent component) {
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
