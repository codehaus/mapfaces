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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.request.ServletUtils;
import org.mapfaces.share.utils.FacesUtils;
import org.mochafaces.component.resource.UIMetaResource;

/**
 *
 * @author kevindelfour
 */
public class ResourceLoaderRenderer extends Renderer {

    private final static String CSS_content = "/org/mochafaces/mocha/themes/default/css/Content.css";
    private final static String CSS_core = "/org/mochafaces/mocha/themes/default/css/Core.css";
    private final static String CSS_layout = "/org/mochafaces/mocha/themes/default/css/Layout.css";
    private final static String CSS_dock = "/org/mochafaces/mocha/themes/default/css/Dock.css";
    private final static String CSS_tabs = "/org/mochafaces/mocha/themes/default/css/Tabs.css";
    private final static String CSS_window = "/org/mochafaces/mocha/themes/default/css/Window.css";
    private final static String JS_IE = "/org/mochafaces/mocha/scripts/excanvas.js";
    private final static String JS_core = "/org/mochafaces/mocha/scripts/source/Core/Core.js";
    private final static String JS_layout = "/org/mochafaces/mocha/scripts/source/Layout/Layout.js";
    private final static String JS_dock = "/org/mochafaces/mocha/scripts/source/Layout/Dock.js";
    private final static String JS_window = "/org/mochafaces/mocha/scripts/source/Window/Window.js";
    private final static String JS_modal = "/org/mochafaces/mocha/scripts/source/Window/Modal.js";
    private final static String JS_tabs = "/org/mochafaces/mocha/scripts/source/Components/Tabs.js";
    private final static String JS_themes = "/org/mochafaces/mocha/scripts/source/Utilities/Themes.js";
    private final static String JS_addons = "/org/mochafaces/mocha/scripts/source/Core/Addons.js";
    private final static String JS_mootoolsC = "/org/mapfaces/resources/js/mootools/mootools-1.2.4-core-yc.js";
    private final static String JS_mootoolsM = "/org/mapfaces/resources/js/mootools/mootools-1.2.4.1-more-yc.js";

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
               
        /* Encode first Meta keyword resources */
        for (final UIComponent tmp : component.getChildren()) {
            if (tmp instanceof UIMetaResource) {
                FacesUtils.encodeRecursive(context, tmp);
                tmp.setRendered(false);
            }
        }

        /* Then add resources for interface module */
        writeHeaders(context, component);
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        for (final UIComponent tmp : component.getChildren()) {
            FacesUtils.encodeRecursive(context, tmp);
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        super.encodeEnd(context, component);

        /* Reinit the context */
        for (final UIComponent tmp : component.getChildren()) {
            tmp.setRendered(true);
        }
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /**
     * Write headers css and js with the resource
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     */
    public void writeHeaders(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();

        writer.startElement("link", component);
        writer.writeAttribute("type", "text/css", null);
        writer.writeAttribute("rel", "stylesheet", null);
        writer.writeAttribute("href", ResourcePhaseListener.getURL(context, CSS_content, null), null);
        writer.endElement("link");

        writer.startElement("link", component);
        writer.writeAttribute("type", "text/css", null);
        writer.writeAttribute("rel", "stylesheet", null);
        writer.writeAttribute("href", ResourcePhaseListener.getURL(context, CSS_core, null), null);
        writer.endElement("link");

        writer.startElement("link", component);
        writer.writeAttribute("type", "text/css", null);
        writer.writeAttribute("rel", "stylesheet", null);
        writer.writeAttribute("href", ResourcePhaseListener.getURL(context, CSS_layout, null), null);
        writer.endElement("link");

        writer.startElement("link", component);
        writer.writeAttribute("type", "text/css", null);
        writer.writeAttribute("rel", "stylesheet", null);
        writer.writeAttribute("href", ResourcePhaseListener.getURL(context, CSS_dock, null), null);
        writer.endElement("link");

        writer.startElement("link", component);
        writer.writeAttribute("type", "text/css", null);
        writer.writeAttribute("rel", "stylesheet", null);
        writer.writeAttribute("href", ResourcePhaseListener.getURL(context, CSS_tabs, null), null);
        writer.endElement("link");

        writer.startElement("link", component);
        writer.writeAttribute("type", "text/css", null);
        writer.writeAttribute("rel", "stylesheet", null);
        writer.writeAttribute("href", ResourcePhaseListener.getURL(context, CSS_window, null), null);
        writer.endElement("link");

        if (ServletUtils.isExplorer()){
            writer.startElement("script", component);
            writer.writeAttribute("type", "text/javascript", null);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, JS_IE, null), null);
            writer.endElement("script");
        }
        
        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, JS_mootoolsC, null), null);
        writer.endElement("script");

        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, JS_mootoolsM, null), null);
        writer.endElement("script");

        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, JS_core, null), null);
        writer.endElement("script");

        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, JS_layout, null), null);
        writer.endElement("script");

        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, JS_dock, null), null);
        writer.endElement("script");

        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, JS_window, null), null);
        writer.endElement("script");

        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, JS_modal, null), null);
        writer.endElement("script");

        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, JS_tabs, null), null);
        writer.endElement("script");

        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, JS_themes, null), null);
        writer.endElement("script");

        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, JS_addons, null), null);
        writer.endElement("script");
    }
}
