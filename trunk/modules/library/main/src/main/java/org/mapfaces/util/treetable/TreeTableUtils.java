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

package org.mapfaces.util.treetable;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.mapfaces.component.abstractTree.UITreePanelBase;
import org.mapfaces.component.tree.UITreeLines;
import org.mapfaces.component.tree.UITreePanel;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.share.request.RequestMapUtils;
import org.mapfaces.util.tree.TreeUtils;

/**
 * @author Kevin Delfour
 */
public class TreeTableUtils {

    public void createTreeLines(final UIComponent component, final TreeNodeModel node,
            final List<UIComponent> list, final boolean LoadingOption) throws IOException {
        final UITreePanelBase treepanel = (UITreePanelBase) component;

//        if (!((UITreePanelBase) component).isInit()) {
        for (int i = 0; i < node.getChildCount(); i++) {
            final TreeNodeModel currentNode = (TreeNodeModel) node.getChildAt(i);
            RequestMapUtils.put("org.treetable.NodeInstance", currentNode);

            //Create a new treeline and get all component to make a backup
            final UITreeLines treelines = new UITreeLines();
            final HtmlPanelGroup panelgroup = new HtmlPanelGroup();
            panelgroup.setStyle("height:auto;");
            final String idLine = treepanel.getId() + "_line_";
            final String idPanel = treepanel.getId() + "_panel_";
            final int idnode = currentNode.getId();
//            System.out.println("[DEBUG] createTreeLines ID to add " + idLine + idnode);
            treelines.setId(idLine + idnode);
            panelgroup.setId(idPanel + idnode);

            treelines.setNodeId(currentNode.getId());
            treelines.setNodeInstance(currentNode);

            final List<UIComponent> tocopy = TreeUtils.duplicate(list, currentNode);

            treelines.getChildren().addAll(tocopy);
            if (!currentNode.isLeaf()) {
                treelines.setHasChildren(true);
                createTreeLinesRecurs(treepanel, currentNode, list, LoadingOption);
            }

            //Search into panelGroup  of the treepanel if any children like the new treelines are present
            for (UIComponent child : treepanel.getChildren()) {
                if (child.getId().equals(panelgroup.getId())) {
                    treepanel.getChildren().remove(child);
                }
            }

            panelgroup.getChildren().add(treelines);
            treepanel.getChildren().add(panelgroup);
        }
//        }
    }

    private void createTreeLinesRecurs(final UITreePanelBase treepanel, final TreeNodeModel node,
            final List<UIComponent> list, final boolean LoadingOption) throws IOException {

        final ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        final Map<String, Object> requestMap = ec.getRequestMap();
        requestMap.put("Elresolver.called", false);

        for (int i = 0; i < node.getChildCount(); i++) {
            final TreeNodeModel currentNode = (TreeNodeModel) node.getChildAt(i);
            RequestMapUtils.put("org.treetable.NodeInstance", currentNode);

            final UITreeLines treelines = new UITreeLines();
            final HtmlPanelGroup panelgroup = new HtmlPanelGroup();
            panelgroup.setStyle("height:auto;");
            final String idLine = treepanel.getId() + "_line_";
            final String idPanel = treepanel.getId() + "_panel_";
            final int idnode = currentNode.getId();
//            System.out.println("[DEBUG] createTreeLinesRecurs ID to add " + idLine + idnode);
            treelines.setId(idLine + idnode);
            panelgroup.setId(idPanel + idnode);

            treelines.setNodeId(currentNode.getId());
            treelines.setNodeInstance(currentNode);

            final List<UIComponent> tocopy = TreeUtils.duplicate(list, currentNode);
            treelines.getChildren().addAll(tocopy);
            treelines.setToRender(true);

            //if (!treepanel.isInit()) {
            if (!LoadingOption) {
                if (node.getDepth() >= TreeTableConfig.DEFAULT_DEPTH_VIEW) {
                    treelines.getNodeInstance().setChecked(false);
//                    treelines.setToRender(false);
                    treelines.setRendered(false);
                }
            }
            //}
            if (!currentNode.isLeaf()) {
                treelines.setHasChildren(true);
                createTreeLinesRecurs(treepanel, currentNode, list, LoadingOption);
            }

            //Search into panelGroup  of the treepanel if any children like the new treelines are present
            for (UIComponent child : treepanel.getChildren()) {
                if (child.getId().equals(panelgroup.getId())) {
                    treepanel.getChildren().remove(child);
                }
            }

            panelgroup.getChildren().add(treelines);
            treepanel.getChildren().add(panelgroup);
        }

    }

    /**
     * Returns the first child of treetable component which is UITreePanel instance.
     * @param treetable
     * @return
     */
    public static UITreePanel getChildTreePanel(UIComponent treetable) {
        if (treetable != null && treetable.getChildren() != null && treetable.getChildCount() > 0) {
            for (UIComponent child : treetable.getChildren()) {
                if (child instanceof UITreePanel) {
                    return (UITreePanel) child;
                } else {
                    getChildTreePanel(child);
                }
            }
        }
        return null;
    }
}

