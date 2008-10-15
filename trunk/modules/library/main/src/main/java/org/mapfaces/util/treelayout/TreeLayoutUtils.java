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
package org.mapfaces.util.treelayout;

import org.mapfaces.share.requestmap.RequestMapUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.mapfaces.component.abstractTree.UIAbstractTreePanel;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.util.tree.TreeUtils;
import org.mapfaces.util.treetable.TreeTableConfig;

/**
 *
 * @author kdelfour
 */
public class TreeLayoutUtils {

     /**
     * 
     * @param component
     * @param node
     * @param list
     * @throws java.io.IOException
     */
    public void createTreeLines(UIComponent component, TreeNodeModel node, List<UIComponent> list, boolean LoadingOption) throws IOException {
        System.out.println("Loading =" + LoadingOption);
        FacesContext context = FacesContext.getCurrentInstance();
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) component;

        if (!((UIAbstractTreePanel) component).isInit()) {
            for (int i = 0; i < node.getChildCount(); i++) {
                TreeNodeModel currentNode = (TreeNodeModel) node.getChildAt(i);
                RequestMapUtils.put("org.treetable.NodeInstance", currentNode);

                //Create a new treeline and get all component to make a backup
                UITreeLines treelines = new UITreeLines();
                HtmlPanelGroup panelgroup = new HtmlPanelGroup();

                String idLine = treepanel.getId() + "_line_";
                String idPanel = treepanel.getId() + "_panel_";
                int idnode = currentNode.getId();
//                System.out.println("[DEBUG] createTreeLines ID to add " + id + idnode);
                treelines.setId(idLine + idnode);
                panelgroup.setId(idPanel + idnode);

                treelines.setNodeId(currentNode.getId());
                treelines.setNodeInstance(currentNode);

                List<UIComponent> tocopy = TreeUtils.duplicate(list, currentNode);

                treelines.getChildren().addAll(tocopy);
                if (!currentNode.isLeaf()) {
                    treelines.setHasChildren(true);
                    createTreeLinesRecurs(treepanel, currentNode, list, LoadingOption);
                }

                panelgroup.getChildren().add(treelines);
                treepanel.getChildren().add(panelgroup);
            }
        }
    }

     /**
     * 
     * @param treepanel 
     * @param component
     * @param node
     * @param list
     * @throws java.io.IOException
     */
    @SuppressWarnings("unchecked")
    private void createTreeLinesRecurs(UIAbstractTreePanel treepanel, TreeNodeModel node, List<UIComponent> list, boolean LoadingOption) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        UITreeLines treelines = null;

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map requestMap = ec.getRequestMap();
        requestMap.put("Elresolver.called", false);

        for (int i = 0; i < node.getChildCount(); i++) {
            TreeNodeModel currentNode = (TreeNodeModel) node.getChildAt(i);
            RequestMapUtils.put("org.treetable.NodeInstance", currentNode);

            treelines = new UITreeLines();
            HtmlPanelGroup panelgroup = new HtmlPanelGroup();

            String idLine = treepanel.getId() + "_line_";
            String idPanel = treepanel.getId() + "_panel_";

            int idnode = currentNode.getId();
//            System.out.println("[DEBUG] createTreeLinesRecurs ID to add " + id + idnode);
            treelines.setId(idLine + idnode);
            panelgroup.setId(idPanel + idnode);

            treelines.setNodeId(currentNode.getId());
            treelines.setNodeInstance(currentNode);


            List<UIComponent> tocopy = TreeUtils.duplicate(list, currentNode);
            treelines.getChildren().addAll(tocopy);
            treelines.setToRender(true);

            if (!treepanel.isInit()) {
                if (!LoadingOption) {
                    if (node.getDepth() >= TreeTableConfig.getDEFAULT_DEPTH_VIEW()) {
                        treelines.getNodeInstance().setChecked(false);
//                    treelines.setToRender(false);
                        treelines.setRendered(false);
                    }
                }
            }
            if (!currentNode.isLeaf()) {
                treelines.setHasChildren(true);
                createTreeLinesRecurs(treepanel, currentNode, list, LoadingOption);
            }

            panelgroup.getChildren().add(treelines);
            treepanel.getChildren().add(panelgroup);
        }

    }
}

