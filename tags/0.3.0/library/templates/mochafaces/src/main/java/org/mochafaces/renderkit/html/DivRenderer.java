/*
 * MDweb - Open Source tool for cataloging and locating environmental resources
 *         http://mdweb.codehaus.org
 *
 *   Copyright (c) 2007-2009, Institut de Recherche pour le Développement (IRD)
 *   Copyright (c)      2009, Geomatys
 *
 * This file is part of MDweb.
 *
 * MDweb is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation;
 *   version 3.0 of the License.
 *
 * MDweb is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *   Lesser General Public License for more details:
 *         http://www.gnu.org/licenses/lgpl-3.0.html
 *
 */
package org.mochafaces.renderkit.html;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.mapfaces.share.utils.FacesUtils;
import org.mochafaces.component.html.UIDiv;
import org.mochafaces.renderkit.BaseExtendRenderer;


/**
 *
 * @author kevindelfour
 */
public class DivRenderer extends BaseExtendRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {

        final ResponseWriter writer = context.getResponseWriter();
        final UIDiv div = (UIDiv) component;
        writer.startElement("div", component);
        writeDefaultAttribute(context, component);

        /* Write attributes */
        if (div.isDir()) {
            writer.writeAttribute("dir", "RTL", null);
        }

        writer.writeAttribute("id", div.getId(), null);

        if (div.getLang() != null) {
            writer.writeAttribute("lang", div.getLang(), null);
        }

        if (div.getTitle() != null) {
            writer.writeAttribute("title", div.getTitle(), null);
        }

    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        for (final UIComponent tmp : component.getChildren()) {
            FacesUtils.encodeRecursive(context, tmp);
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }
}
