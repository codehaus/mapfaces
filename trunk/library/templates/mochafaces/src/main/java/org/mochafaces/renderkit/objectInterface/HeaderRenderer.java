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
import javax.faces.render.Renderer;
import org.mapfaces.share.utils.FacesUtils;
import org.mochafaces.component.objectInterface.UIHeader;
import org.mochafaces.component.resource.UIResourceLoader;

/**
 *
 * @author kevindelfour
 */
public class HeaderRenderer extends Renderer {

    private final static String DEFAULT_TITLE = "DEKKA UI Library and Webapp Project";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
       
        final ResponseWriter writer = context.getResponseWriter();
        final UIHeader uih = (UIHeader) component;
        /* Test if resources needed by the web app are present in the context */
        if (uih.getChildren().size() == 0) {
            final UIResourceLoader rl = new UIResourceLoader();
            uih.getChildren().add(rl);
        }

        writer.startElement("head", component);

        /* Write attributes */
        if (uih.isDir()) {
            writer.writeAttribute("dir", "RTL", null);
        }

        writer.writeAttribute("id", uih.getClientId(context), null);

        if (uih.getLang() != null) {
            writer.writeAttribute("lang", uih.getLang(), null);
        }

        if (uih.getProfile() != null) {
            writer.writeAttribute("profile", uih.getProfile(), null);
        }

        writer.startElement("title", component);
        /* Write title */
        if (uih.getTitle() != null) {
            writer.write(uih.getTitle());
        } else {
            writer.write(DEFAULT_TITLE);
        }
        writer.endElement("title");

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

        writer.endElement("head");
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }
}
