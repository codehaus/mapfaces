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
package org.mochafaces.renderkit.resource;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.mochafaces.component.resource.UICustomResource;
import org.mochafaces.component.resource.UIResourceLoader;

/**
 *
 * @author kevindelfour
 */
public class CustomResourceRenderer extends Renderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
             
        final UICustomResource crl = (UICustomResource) component;

        if (crl.getParent() instanceof UIResourceLoader) {
            final String src = crl.getSrc();
            if (src != null) {
                if (src.substring(src.length() - 3).equals(".js") || src.substring(src.length() - 3).equals(".gz")) {
                    writeScript(context, component);
                }
                if (src.substring(src.length() - 4).equals(".css")) {
                    writeLink(context, component);
                }
            }
        } else {
            Logger.getLogger(CustomResourceRenderer.class.getName()).log(Level.SEVERE, null, "The parent component must be a ResourceLoader component");
            return;
        }
    }

    /**
     * Write headers css with the resource
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    protected void writeLink(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final UICustomResource crl = (UICustomResource) component;

        if (crl.isRendered()) {
            writer.startElement("link", component);
            writer.writeAttribute("type", "text/css", null);
            writer.writeAttribute("rel", "stylesheet", null);

            if (crl.getMedia() != null) {
                writer.writeAttribute("media", crl.getMedia(), null);
            }

            writer.writeAttribute("href", crl.getSrc(), null);
            writer.endElement("link");
        }
    }

    /**
     * Write headers js with the resource
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    protected void writeScript(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final UICustomResource crl = (UICustomResource) component;

        if (crl.isRendered()) {
                writer.startElement("script", component);
                writer.writeAttribute("type", "text/javascript", null);
                writer.writeAttribute("src", crl.getSrc(), null);
                writer.endElement("script");
            }
    }
}
