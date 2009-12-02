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
import org.mochafaces.component.resource.UIMetaResource;
import org.mochafaces.component.resource.UIResourceLoader;

/**
 *
 * @author kevindelfour
 */
public class MetaResourceRenderer extends Renderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        
        final UIMetaResource mrl = (UIMetaResource) component;

        if (mrl.getParent() instanceof UIResourceLoader) {
            if (mrl.getName() != null) {
                writeMetaName(context, component);
            } else {
                writeMetaHttpEquiv(context, component);
            }
        } else {
            Logger.getLogger(MetaResourceRenderer.class.getName()).log(Level.SEVERE, "The parent component must be a ResourceLoader component");
            return;
        }
    }

    /**
     * Write headers css and js with the resource
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    protected void writeMetaName(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final UIMetaResource mrl = (UIMetaResource) component;
        final String content;

        if (mrl.isRendered()) {
            if (mrl.getName() != null) {
                writer.startElement("meta", component);
                writer.writeAttribute("name", mrl.getName(), null);
                if (mrl.getLang() != null) {
                    writer.writeAttribute("lang", mrl.getLang(), null);
                }
                if (mrl.getContent() != null) {
                    content = mrl.getContent();
                } else {
                    content = "";
                }
                writer.writeAttribute("content", content, null);

                writer.endElement("meta");
            }

        }
    }

    protected void writeMetaHttpEquiv(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final UIMetaResource mrl = (UIMetaResource) component;
        final String content;

        if (mrl.isRendered()) {
            if (mrl.getHttpEquiv() != null) {
                writer.startElement("meta", component);
                writer.writeAttribute("http-equiv", mrl.getHttpEquiv(), null);
                if (mrl.getLang() != null) {
                    writer.writeAttribute("lang", mrl.getLang(), null);
                }
                if (mrl.getContent() != null) {
                    content = mrl.getContent();
                } else {
                    content = "";
                }
                writer.writeAttribute("content", content, null);

                writer.endElement("meta");
            }

        }
    }

    @Override
    public boolean getRendersChildren() {
        return false;
    }


}
