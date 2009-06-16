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
package org.mapfaces.renderkit.html.treebuilder;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.mapfaces.component.treebuilder.UITreePanel;
import org.mapfaces.renderkit.html.abstractTree.AbstractTreeLinesRenderer;
import org.mapfaces.util.FacesUtils;
import org.mapfaces.util.tree.TreeStyle;

/**
 * @author Kevin Delfour
 */
public class TreeLinesRenderer extends AbstractTreeLinesRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public String addLinesEvent(FacesContext context, UIComponent component) {
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void beforeEncodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        final UITreePanel treepanel = (UITreePanel) FacesUtils.findParentComponentByClass(component, UITreePanel.class);

        if (treepanel == null) {
            throw new IOException("No treepanel parent have been found for this treeline.");
        } else {
            if (treepanel.isTemplate()) {
                //Template view
                TreeStyle.initRowStyle();
            } else if (treepanel.isEmptyView()) {
                //Empty view
                TreeStyle.setRowStyle("display:none;");
            } else if (treepanel.isCloneView()) {
                //Clone View
                TreeStyle.setRowStyle("filter:alpha(opacity=20);-moz-opacity:0.2;opacity:0.2;color:white;");
            } else {
                //default
                TreeStyle.initRowStyle();
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException {
    }
}
