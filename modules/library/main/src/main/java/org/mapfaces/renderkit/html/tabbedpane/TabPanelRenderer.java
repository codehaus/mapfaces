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

package org.mapfaces.renderkit.html.tabbedpane;

import java.io.IOException;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.mapfaces.component.tabbedpane.UITabItem;
import org.mapfaces.component.tabbedpane.UITabPanel;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.Utils;

/**
 * @author Mehdi Sidhoum (Geomatys).
 * @author Kevin Delfour.
 */
public class TabPanelRenderer extends Renderer {

    private static final Logger LOGGER = Logger.getLogger(TabPanelRenderer.class.getName());
    private static final String TABCSS_CSS = "/org/mapfaces/resources/tabbedpane/css/domtab.css";
    private static final String TABJS = "/org/mapfaces/resources/tabbedpane/js/domtab.js";

    private UIForm getForm(UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null && !(parent instanceof UIForm)) parent = parent.getParent();

        if (parent == null) {
            throw new IllegalStateException("Not nested inside a form!");
        }
        return (UIForm) parent;
    }

    private String getPostbackFunctionName(final UIComponent component) {
        final UITabPanel tabpanel = (UITabPanel) component;
        return tabpanel.getId() + "PostBack";
    }

    /**
     *   By default, getRendersChildren returns true, so encodeChildren() will be invoked
     * @return True
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /**
     * Firstly, get the tree value from the bean
     * Then translate into a TreeTableModel
     * and write Css and Js headers before all
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);
        //Start encoding
        LOGGER.info("encodeBegin : " + TabPanelRenderer.class.getName());

        final UITabPanel tabpanel   = (UITabPanel) component;
        final ResponseWriter writer = context.getResponseWriter();

        String dimensionsW = "width:auto";
        String dimensionsH = "height:auto";

        if (tabpanel.getAttributes().get("width") != null) {
            dimensionsW = "width :" + tabpanel.getAttributes().get("width") + "px";
        }
        if (tabpanel.getAttributes().get("height") != null) {
            dimensionsH = "height : " + tabpanel.getAttributes().get("height") + "px";
        }
        writeHeaders(context, component);

        writer.startElement("div", tabpanel);
        writer.writeAttribute("id", "tabs:" + tabpanel.getClientId(context), null);
        writer.writeAttribute("class", "tabs", null);
        writer.writeAttribute("style", dimensionsW + ";" + dimensionsH + ";", null);

        writer.startElement("div", tabpanel);
        writer.writeAttribute("class", "tabs_container", null);
        boolean active = true;
        writer.startElement("ul", tabpanel);
        writer.writeAttribute("id", tabpanel.getClientId(context), null);
        writer.writeAttribute("class", "tabs_title", null);

        for (final UIComponent child : tabpanel.getChildren()) {
            if (child instanceof UITabItem) {
                UITabItem tabItem = (UITabItem) child;
                writer.startElement("li", tabpanel);
                writer.writeAttribute("id", "item:"+tabItem.getClientId(context), null);
                writer.writeAttribute("onclick", "display('"+"tabs:" +tabpanel.getClientId(context)+"','"+tabItem.getClientId(context)+"');", null);
                if (active) {
                    writer.writeAttribute("class", "active", null);
                    tabItem.setActive(true);
                    active = false;
                } else {
                    tabItem.setActive(false);
                }
                writer.startElement("a", tabpanel);
                if(tabItem.getValueExpression("title") != null && tabItem.getValueExpression("title").getValue(context.getELContext()) != null) {
                    writer.write(tabItem.getValueExpression("title").getValue(context.getELContext()).toString());
                }else if (tabItem.getTitle() != null) {
                    writer.write(tabItem.getTitle());
                }
                writer.endElement("a");
                writer.endElement("li");
            }
        }
        writer.endElement("ul");
        writer.endElement("div");
    }

    /**
     * Encode all children of TreeTable component
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        final UITabPanel tabpanel = (UITabPanel) component;

        for (final UIComponent tmp : tabpanel.getChildren()) {
            Utils.encodeRecursive(context, tmp);
        }

    }

    /**
     * Close tree generated div
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
    }

    @Override
    public void decode(final FacesContext context, final UIComponent component) {
    }

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    /* ======================= OTHERS METHODS ==================================*/
    /**
     * Write headers css and js with the resource
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    private void writeHeaders(final FacesContext context, final UIComponent component) throws IOException {
        final UITabPanel tabpanel = (UITabPanel) component;
        final ResponseWriter writer = context.getResponseWriter();

//        writer.startElement("link", tabpanel);
//        writer.writeAttribute("type", "text/css", null);
//        writer.writeAttribute("rel", "stylesheet", null);
//        writer.writeAttribute("href", ResourcePhaseListener.getURL(context, TABCSS_CSS, null), null);
//        writer.endElement("link");

        writer.startElement("script", tabpanel);
        writer.writeAttribute("type", "text/javascript", null);
        writer.writeAttribute("src", ResourcePhaseListener.getURL(context, TABJS, null), null);
        writer.endElement("script");
    }
}
