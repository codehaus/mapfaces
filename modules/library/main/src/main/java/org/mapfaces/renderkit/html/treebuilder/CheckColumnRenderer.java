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
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;

import org.mapfaces.component.treebuilder.UICheckColumn;
import org.mapfaces.component.treebuilder.UITreeLines;
import org.mapfaces.component.treebuilder.UITreePanel;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.abstractTree.AbstractColumnRenderer;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.util.FacesUtils;

/**
 * @author Kevin Delfour
 */
public class CheckColumnRenderer extends AbstractColumnRenderer implements CustomizeTreeComponentRenderer {

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterEncodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        final UICheckColumn checkColumn = (UICheckColumn) component;
        final UITreeLines treeline      = (UITreeLines) component.getParent();
        final TreeNodeModel node        = treeline.getNodeInstance();
        final String treepanelId        = FacesUtils.getParentComponentClientIdByClass(context, component, UITreePanel.class);
        final String formId             = FacesUtils.getFormClientId(context, component);
        final UITreePanel treepanel     = (UITreePanel) FacesUtils.findComponentByClientId(context, context.getViewRoot(), treepanelId);
        
        if (checkColumn.isDebug()) {
            System.out.println("[INFO] afterEncodeBegin " + CheckColumnRenderer.class.getCanonicalName());
        }
        
        if (checkColumn.getChildCount() == 0) {
            final HtmlSelectBooleanCheckbox checkbox = new HtmlSelectBooleanCheckbox();
            final String checkcolumnId               = "check_" + checkColumn.getId();
            checkbox.setId("check_" + checkColumn.getId());
            checkbox.setStyle("cursor:pointer;");
            /* @todo add javascript to make change between to panel */
            checkbox.setOnchange("checkNode('" + formId + "','" + treepanel.getTarget() + "','" + node.getId() + "','" + checkcolumnId + "');");
            checkColumn.getChildren().add(checkbox);
        }

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        addRequestScript(context, component, "change");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
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
    public String addBeforeRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {
        /*
         * Script to update others treepanels
         */
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }
}
