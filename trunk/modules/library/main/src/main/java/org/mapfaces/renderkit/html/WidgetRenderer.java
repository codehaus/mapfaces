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
import org.mapfaces.component.UIWidget;
import org.mapfaces.util.RendererUtils.HTML;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 */
public class WidgetRenderer extends WidgetBaseRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {

        super.encodeBegin(context, component);

        final UIWidget comp         = (UIWidget) component;
        final ResponseWriter writer = getWriter();

        writer.startElement(HTML.DIV_ELEM, comp);
        writer.writeAttribute(HTML.id_ATTRIBUTE, getClientId(), HTML.id_ATTRIBUTE);
        writer.writeAttribute(HTML.style_ATTRIBUTE, getStyle(), HTML.style_ATTRIBUTE);
        if (getStyleClass() == null) {
            writer.writeAttribute(HTML.class_ATTRIBUTE, "mfWidget", "styleClass");
        } else {
            writer.writeAttribute(HTML.class_ATTRIBUTE, getStyleClass(), "styleClass");
        }
        writer.endElement(HTML.DIV_ELEM);
        writer.flush();
    }

}
