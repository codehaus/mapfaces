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
import javax.faces.component.UIForm;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.mapfaces.component.treebuilder.UICheckColumn;
import org.mapfaces.component.treebuilder.UITreeLines;
import org.mapfaces.component.treebuilder.UITreePanel;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.abstractTree.AbstractColumnRenderer;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.share.utils.Utils;


public class CheckColumnRenderer extends AbstractColumnRenderer implements CustomizeTreeComponentRenderer {

    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        UICheckColumn checkColumn = (UICheckColumn) component;
        UITreeLines treeline = (UITreeLines) component.getParent();
        TreeNodeModel node = treeline.getNodeInstance();
        String treepanelTargetId = null;

        String treepanelId = Utils.getWrappedComponentId(context, component, UITreePanel.class);
        String formId = Utils.getWrappedComponentId(context, component, UIForm.class);
        UITreePanel treepanel = (UITreePanel) Utils.findComponent(context, treepanelId);

        if (treepanel.getTarget() != null) {
            treepanelTargetId = formId + ":" + treepanel.getTarget();
        }

        ResponseWriter writer = context.getResponseWriter();
        if (checkColumn.isDebug()) {
            System.out.println("[INFO] afterEncodeBegin " + CheckColumnRenderer.class.getCanonicalName());
        }
        if (checkColumn.getChildCount() == 0) {
            HtmlSelectBooleanCheckbox checkbox = new HtmlSelectBooleanCheckbox();
            String checkcolumnId = "check_" + checkColumn.getId();
            checkbox.setId("check_" + checkColumn.getId());
            checkbox.setStyle("cursor:pointer;margin-left:-15px;");
            /* @todo add javascript to make change between to panel */
            checkbox.setOnclick("checkNode('" + formId + "','" + treepanel.getTarget() + "','" + node.getId() + "','" + checkcolumnId + "');");
            checkColumn.getChildren().add(checkbox);
        }

        writer.startElement("center", checkColumn);

    }

    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException {
        addRequestScript(context, component, "change");
    }

    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public String addBeforeRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }

    @Override
    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {
        /*
         * Script to update others treepanels
         */
    }

    @Override
    public String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException {
        return "";
    }
}
