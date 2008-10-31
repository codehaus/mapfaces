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
import org.mapfaces.component.UIAbstract;
import org.mapfaces.models.Context;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class AbstractRenderer extends WidgetBaseRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {

        super.encodeBegin(context, component);
        UIAbstract comp = (UIAbstract) component;
        Context model = (Context) comp.getModel();
        
        String clientId= comp.getClientId(context);
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "id");
        writer.writeAttribute("style",getStyle(), "style");
        if (getStyleClass() == null) {
            writer.writeAttribute("class", "mfMapTitle", "styleClass");
        } else {
            writer.writeAttribute("class",getStyleClass(), "styleClass");
        }
        writer.startElement("h3", comp);

        String title = model.getTitle();
        if (title != null) {
            writer.write(title);
        }

        writer.endElement("h3");
        writer.endElement("div");
        writer.flush();

    }

    @Override
    public boolean getRendersChildren() {
        return false;
    }
}
