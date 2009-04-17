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
import org.mapfaces.models.Context;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class WidgetRenderer extends WidgetBaseRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {

        super.encodeBegin(context, component);

        final UIWidget comp         = (UIWidget) component;
        final Context model         = (Context) comp.getModel();
        final ResponseWriter writer = getWriter();

        writer.startElement("div", comp);
        writer.writeAttribute("id", getClientId(), "id");
        writer.writeAttribute("style", getStyle(), "style");
        if (getStyleClass() == null) {
            writer.writeAttribute("class", "mfWidget", "styleClass");
        } else {
            writer.writeAttribute("class", getStyleClass(), "styleClass");
        }
        writer.endElement("div");
        writer.flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        super.encodeEnd(context, component);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(FacesContext context, UIComponent component) {
        super.decode(context, component);
    }
}
