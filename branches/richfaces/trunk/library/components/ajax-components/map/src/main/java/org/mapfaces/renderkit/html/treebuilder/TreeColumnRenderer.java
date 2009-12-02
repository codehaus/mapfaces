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

import org.ajax4jsf.ajax.html.HtmlAjaxSupport;

import org.mapfaces.component.abstractTree.UITreeLinesBase;
import org.mapfaces.component.treebuilder.UITreeLines;
import org.mapfaces.component.treebuilder.UITreePanel;
import org.mapfaces.component.treebuilder.UITreeTable;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.renderkit.html.abstractTree.AbstractTreeColumnRenderer;
import org.mapfaces.share.interfaces.A4JRendererInterface;
import org.mapfaces.util.FacesMapUtils;

/**
 * @author Kevin Delfour
 */
public class TreeColumnRenderer extends AbstractTreeColumnRenderer implements A4JRendererInterface {

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
    public void afterEncodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        final UITreeLines treeline  = (UITreeLines) component.getParent();
        final UITreePanel treepanel = (UITreePanel) FacesMapUtils.findParentComponentByClass(component, UITreePanel.class);
        final String treepanelId    = treepanel.getClientId(context);
        final String formId         = FacesMapUtils.getFormClientId(context, component);
        final String ajaxSupportId;

        if (treepanel.getTarget() != null) {
            final String treepanelTargetId = formId + ":" + treepanel.getTarget();
            ajaxSupportId = treepanelId + "_ajax_" + treeline.getNodeInstance().getId();
            final HtmlAjaxSupport ajaxSupport = (HtmlAjaxSupport) FacesMapUtils.findComponentByClientId(context, context.getViewRoot(), ajaxSupportId);
            if (ajaxSupport == null) {
                System.out.println("[WARNING] AjaxSupport on LoadAll option can't be found for " + ajaxSupportId);
            } else {
                final StringBuilder line2ReRender = new StringBuilder(treepanelId).append("_panel_").append(treeline.getNodeInstance().getId()).append(",")
                        .append(treepanelTargetId).append("_panel_").append(treeline.getNodeInstance().getId());
                ajaxSupport.setReRender(line2ReRender);
            }
        } else {
            final UITreeTable treetable = (UITreeTable) treepanel.getParent();
            UITreePanel treepanelBase = null;

            for (final UIComponent child : treetable.getChildren()) {
                if (child instanceof UITreePanel && child.isRendered()) {
                    if (((UITreePanel) child).getTarget() != null) {
                        treepanelBase = (UITreePanel) child;
                        break;
                    }
                }
            }
            if (treepanelBase != null) {
                ajaxSupportId = treepanel.getClientId(context) + "_ajax_" + treeline.getNodeInstance().getId();
                final HtmlAjaxSupport ajaxSupport = (HtmlAjaxSupport) FacesMapUtils.findComponentByClientId(context, context.getViewRoot(), ajaxSupportId);

                if (ajaxSupport == null) {
                    System.out.println("[WARNING] AjaxSupport on LoadAll option can't be found for " + ajaxSupportId);
                } else {
                    final StringBuilder line2ReRender = new StringBuilder(treepanelBase.getClientId(context)).append("_panel_").append(treeline.getNodeInstance().getId()).append(",")
                            .append(treepanel.getClientId(context)).append("_panel_").append(treeline.getNodeInstance().getId());
                    ajaxSupport.setReRender(line2ReRender);
                }
            }
        }
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

    /**
     * {@inheritDoc }
     */
    @Override
    public void A4JPostRequest(final FacesContext context, final UIComponent component) {
        super.A4JPostRequest(context, component);
        final UITreeLinesBase treeline = (UITreeLinesBase) component.getParent();
        final TreeNodeModel node       = treeline.getNodeInstance();
        final UITreePanel treepanel = (UITreePanel) FacesMapUtils.findParentComponentByClass(component, UITreePanel.class);
        final String treepanelId    = treepanel.getClientId(context);
        final String formId         = FacesMapUtils.getFormClientId(context, component);

        if (treepanelId != null) {
            if (treepanel.getTarget() != null) {
                if (!node.isLeaf()) {
                    for (int i=0,n=node.getChildCount(); i<n ; i++) {
                        final TreeNodeModel nodeTemp      = (TreeNodeModel) node.getChildAt(i);
                        final String Line2Modify          = formId + ":" + treepanel.getTarget() + "_line_" + nodeTemp.getId();
                        final UITreeLines treeline2change = (UITreeLines) FacesMapUtils.findComponentByClientId(context, context.getViewRoot(), Line2Modify);
                        if (treeline2change != null) {
                            treeline2change.getNodeInstance().setChecked(true);
                            treeline2change.setRendered(true);
                            treeline2change.setToRender(true);
                        }
                    }
                }
            } else {
                final UITreeTable treetable = (UITreeTable) treepanel.getParent();

                for (final UIComponent child : treetable.getChildren()) {
                    if (child instanceof UITreePanel && child.isRendered()) {
                        final UITreePanel treepanelBase = (UITreePanel) child;
                        if (treepanelBase.getTarget() != null) {
                            if (!node.isLeaf()) {
                                for (int i=0,n=node.getChildCount(); i<n ; i++) {
                                    final TreeNodeModel nodeTemp = (TreeNodeModel) node.getChildAt(i);
                                    final String Line2Modify = treepanelBase.getClientId(context) + "_line_" + nodeTemp.getId();
                                    final UITreeLines treeline2change = (UITreeLines) FacesMapUtils.findComponentByClientId(context, context.getViewRoot(), Line2Modify);
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
