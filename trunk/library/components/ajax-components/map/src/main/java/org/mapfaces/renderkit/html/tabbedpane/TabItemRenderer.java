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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.mapfaces.component.tabbedpane.UITabItem;
import org.mapfaces.component.tabbedpane.UITabPanel;
import org.mapfaces.util.FacesMapUtils;

/**
 *
 * @author Mehdi Sidhoum (Geomatys).
 * @author kevin Delfour.
 */
public class TabItemRenderer extends Renderer {

    private static final Logger LOGGER = Logger.getLogger(TabItemRenderer.class.getName());

    private UITabPanel getParentTabPanel(final UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null && !(parent instanceof UITabPanel)) {
            parent = parent.getParent();
        }

        if (parent == null) {
            LOGGER.log(Level.SEVERE, "Not nested inside a tab panel!");
            return null;
        }
        return (UITabPanel) parent;
    }

    private String getPostbackFunctionName(UIComponent component) {
        final UITabItem tabitem = (UITabItem) component;
        return tabitem.getId() + "_PostBack";
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
        if (getParentTabPanel(component) == null || !component.isRendered()) {
            return;
        }

        if (context == null) throw new NullPointerException("FacesContext should not be null");
        if (component == null) throw new NullPointerException("component should not be null");


        //Start encoding

        final UITabItem tabitem = (UITabItem) component;
        final ResponseWriter writer = context.getResponseWriter();

        writer.startElement("div", tabitem);
        writer.writeAttribute("id", tabitem.getClientId(context), null);
        if (tabitem.isActive()) {
            writer.writeAttribute("class", "tabs_panel active", null);
        } else {
            writer.writeAttribute("class", "tabs_panel", null);
        }
        writer.startElement("div", tabitem);
        writer.writeAttribute("class", "tab_content", null);

    }

    /**
     * Encode all children of TreeTable component
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        final UITabItem tabitem = (UITabItem) component;

        for (final UIComponent tmp : tabitem.getChildren()) {
            FacesMapUtils.encodeRecursive(context, tmp);
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
        writer.endElement("div");

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
    }

}
