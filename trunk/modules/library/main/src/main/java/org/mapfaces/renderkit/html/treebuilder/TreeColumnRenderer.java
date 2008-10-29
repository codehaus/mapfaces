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

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;


import org.ajax4jsf.ajax.html.HtmlAjaxSupport;
import org.mapfaces.component.abstractTree.UITreeLinesBase;
import org.mapfaces.component.treebuilder.UITreeLines;
import org.mapfaces.component.treebuilder.UITreePanel;
import org.mapfaces.component.treebuilder.UITreeTable;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.abstractTree.AbstractTreeColumnRenderer;
import org.mapfaces.share.interfaces.A4JRendererInterface;
import org.mapfaces.share.utils.Utils;

/**
 *
 * @author kevindelfour
 */
public class TreeColumnRenderer extends AbstractTreeColumnRenderer implements A4JRendererInterface {

    @Override
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException {
        UITreeLines treeline = (UITreeLines) component.getParent();
        String treepanelId = Utils.getWrappedComponentId(context, component, UITreePanel.class);
        String formId = Utils.getWrappedComponentId(context, component, UIForm.class);
        UITreePanel treepanel = (UITreePanel) Utils.findComponent(context, treepanelId);
        String treepanelTargetId = null, ajaxSupportId = null;

        if (treepanel.getTarget() != null) {
            treepanelTargetId = formId + ":" + treepanel.getTarget();
            ajaxSupportId = treepanelId + "_ajax_" + treeline.getNodeInstance().getId();
            HtmlAjaxSupport ajaxSupport = (HtmlAjaxSupport) Utils.findComponent(context, ajaxSupportId);
            if (ajaxSupport == null) {
                System.out.println("[WARNING] AjaxSupport on LoadAll option can't be found for " + ajaxSupportId);
            } else {
                String line2ReRender = treepanelId + "_panel_" + treeline.getNodeInstance().getId() + "," +
                        treepanelTargetId + "_panel_" + treeline.getNodeInstance().getId();
                ajaxSupport.setReRender(line2ReRender);
            }
        } else {
            UITreeTable treetable = (UITreeTable) treepanel.getParent();
            List<UIComponent> listTreepanel = treetable.getChildren();
            UITreePanel treepanelBase = null;
            for (UIComponent child : listTreepanel) {
                if (child instanceof UITreePanel) {
                    if (((UITreePanel) child).getTarget() != null) {
                        treepanelBase = (UITreePanel) child;
                    }
                }
            }
            if (treepanelBase != null) {
                ajaxSupportId = treepanel.getClientId(context) + "_ajax_" + treeline.getNodeInstance().getId();

                HtmlAjaxSupport ajaxSupport = (HtmlAjaxSupport) Utils.findComponent(context, ajaxSupportId);
                if (ajaxSupport == null) {
                    System.out.println("[WARNING] AjaxSupport on LoadAll option can't be found for " + ajaxSupportId);
                } else {
                    String line2ReRender = treepanelBase.getClientId(context) + "_panel_" + treeline.getNodeInstance().getId() + "," +
                            treepanel.getClientId(context) + "_panel_" + treeline.getNodeInstance().getId();
                    ajaxSupport.setReRender(line2ReRender);
                }
            }
        }
    }

    @Override
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public void A4JPostRequest(FacesContext context, UIComponent component) {
        super.A4JPostRequest(context, component);
        UITreeLinesBase treeline = (UITreeLinesBase) component.getParent();
        TreeNodeModel node = treeline.getNodeInstance(),
                nodeTemp;
        String treepanelId = Utils.getWrappedComponentId(context, component, UITreePanel.class);
        String formId = Utils.getWrappedComponentId(context, component, UIForm.class);
        UITreePanel treepanel = (UITreePanel) Utils.findComponent(context, treepanelId);

        if (treepanelId != null) {
            if (treepanel.getTarget() != null) {
                if (!node.isLeaf()) {
                    for (int i = 0; i < node.getChildCount(); i++) {
                        nodeTemp = (TreeNodeModel) node.getChildAt(i);
                        String Line2Modify = formId + ":" + treepanel.getTarget() + "_line_" + nodeTemp.getId();
                        UITreeLines treeline2change = (UITreeLines) Utils.findComponent(context, Line2Modify);
                        if (treeline2change != null) {
                            treeline2change.getNodeInstance().setChecked(true);
                            treeline2change.setRendered(true);
                            treeline2change.setToRender(true);
                        }
                    }
                }
            } else {
                UITreeTable treetable = (UITreeTable) treepanel.getParent();
                List<UIComponent> listTreepanel = treetable.getChildren();
                for (UIComponent child : listTreepanel) {
                    if (child instanceof UITreePanel) {
                        UITreePanel treepanelBase = (UITreePanel) child;
                        if (treepanelBase.getTarget() != null) {
                            if (!node.isLeaf()) {
                                for (int i = 0; i < node.getChildCount(); i++) {
                                    nodeTemp = (TreeNodeModel) node.getChildAt(i);
                                    String Line2Modify = treepanelBase.getClientId(context) + "_line_" + nodeTemp.getId();
                                    UITreeLines treeline2change = (UITreeLines) Utils.findComponent(context, Line2Modify);
                                    if (treeline2change != null) {
                                        treeline2change.getNodeInstance().setChecked(true);
                                        treeline2change.setRendered(true);
                                        treeline2change.setToRender(true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
