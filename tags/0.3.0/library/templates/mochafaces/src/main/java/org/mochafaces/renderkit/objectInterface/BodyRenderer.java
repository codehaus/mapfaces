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
package org.mochafaces.renderkit.objectInterface;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.mapfaces.share.utils.FacesUtils;
import org.mochafaces.component.objectInterface.UIBody;
import org.mochafaces.renderkit.BaseExtendRenderer;

/**
 *
 * @author kevindelfour
 */
public class BodyRenderer extends BaseExtendRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
       
        final ResponseWriter writer = context.getResponseWriter();
        final UIBody uib = (UIBody) component;
        writer.startElement("body", component);
        writeDefaultAttribute(context, component);

        /* Write attributes */
        if (uib.isDir()) {
            writer.writeAttribute("dir", "RTL", null);
        }
        
        writer.writeAttribute("id", uib.getClientId(context), null);

        if (uib.getLang() != null) {
            writer.writeAttribute("lang", uib.getLang(), null);
        }

        if (uib.getTitle() != null) {
            writer.writeAttribute("title", uib.getTitle(), null);
        }

        if (uib.getOnload() != null) {
            writer.writeAttribute("onload", uib.getOnload(), null);
        }
        
        if (uib.getOnunload() != null) {
            writer.writeAttribute("onunload", uib.getOnunload(), null);
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
        writer.endElement("body");
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }
}
