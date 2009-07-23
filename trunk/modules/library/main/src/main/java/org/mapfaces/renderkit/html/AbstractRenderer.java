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

import org.mapfaces.util.RendererUtils.HTML;
import org.mapfaces.component.UIAbstract;
import org.mapfaces.models.Context;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class AbstractRenderer extends WidgetBaseRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {

        super.encodeBegin(context, component);
        final UIAbstract comp = (UIAbstract) component;
        final Context model   = (Context) comp.getModel();
        final String clientId = comp.getClientId(context);

        writer.startElement(HTML.DIV_ELEM, comp);
        writer.writeAttribute(HTML.id_ATTRIBUTE, clientId, HTML.id_ATTRIBUTE);
        writer.writeAttribute(HTML.style_ATTRIBUTE,getStyle(), HTML.style_ATTRIBUTE);
        if (getStyleClass() == null) {
            writer.writeAttribute(HTML.class_ATTRIBUTE, "mfMapTitle", "styleClass");
        } else {
            writer.writeAttribute(HTML.class_ATTRIBUTE,getStyleClass(), "styleClass");
        }
        writer.startElement("h3", comp);

        final String title = model.getTitle();
        if (title != null) {
            writer.write(title);
        }

        writer.endElement("h3");
        writer.endElement(HTML.DIV_ELEM);
        writer.flush();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getRendersChildren() {
        return false;
    }
}
