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
package org.mapfaces.renderkit.html.abstractTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mapfaces.component.abstractTree.UIAbstractTreeLines;
import org.mapfaces.component.abstractTree.UIAbstractTreeNodeInfo;
import org.mapfaces.component.abstractTree.UIAbstractTreePanel;
import org.mapfaces.component.abstractTree.UIAbstractTreeTable;

import org.mapfaces.models.tree.TreeModelsUtils;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.AjaxUtils;
import org.mapfaces.util.treetable.TreeTableConfig;

/**
 *
 * @author kdelfour
 */
public abstract class AbstractTreePanelRenderer extends Renderer implements AjaxRendererInterface {

    private String EXPAND_TEXT = "Expand";
    private String COLLAPSE_TEXT = "Collapse";
    private boolean debug = false;
    private static final Log log = LogFactory.getLog(AbstractTreePanelRenderer.class);
    private TreeTableConfig config = new TreeTableConfig();

    private UIAbstractTreeTable getForm(UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null) {
            if (parent instanceof UIAbstractTreeTable) {
                break;
            }
            parent = parent.getParent();
        }
        if (parent == null) {
            throw new IllegalStateException("Not nested inside a form!");
        }
        return (UIAbstractTreeTable) parent;
    }

    private String getPostbackFunctionName(UIComponent component) {
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) component;
        return treepanel.getId() + "PostBack";
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
     * 
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        //Test
        if (!(getForm(component) instanceof UIAbstractTreeTable)) {
            return;
        }
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        if (component.getAttributes().get("debug") != null) {
            debug = (Boolean) component.getAttributes().get("debug");
        }

        if (debug) {
            log.info("beforeEncodeBegin : " + AbstractTreePanelRenderer.class.getName());
        }
        beforeEncodeBegin(context, component);

        //Start encoding
        if (debug) {
            log.info("encodeBegin : " + AbstractTreePanelRenderer.class.getName());
        }

        ResponseWriter writer = context.getResponseWriter();
        UIAbstractTreeTable treetable = getForm(component);
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) component;

        if (component != null) {
            String X_PANEL_HEADER_CLASS_STYLE = "x-panel-body x-panel-body-noheader";
            writer.startElement("div", component);
            writer.writeAttribute("id", "panel:" + component.getClientId(context), null);
            
            String styleUser = "";
            if (treepanel.getStyle() != null) {
                styleUser = treepanel.getStyle();
            }
            writer.writeAttribute("style", "z-index:0; background :#CCCCCC;" + styleUser, null);
            
            if (treepanel.getStyleClass() != null) {
                writer.writeAttribute("class", treepanel.getStyleClass(), null);
            }



            //HEADER Attribute
            if ((component.getAttributes().get("header") != null) && ((Boolean) (component.getAttributes().get("header"))) || (component.getAttributes().get("header") == null)) {
                //FRAME Attribute
                if ((component.getAttributes().get("frame") != null) && ((Boolean) (component.getAttributes().get("frame")))) {
                    writer.startElement("div", component);
                    writer.writeAttribute("class", "x-panel-tl", null);
                    writer.startElement("div", component);
                    writer.writeAttribute("class", "x-panel-tr", null);
                    writer.startElement("div", component);
                    writer.writeAttribute("class", "x-panel-tc", null);
                }


                // TITLE Attribute
                if (component.getAttributes().get("title") != null) {
                    writer.startElement("div", component);
                    writer.writeAttribute("id", "panel_title:" + component.getClientId(context), null);
                    writer.writeAttribute("class", "x-panel-header x-unselectable", null);

                    if (component.getAttributes().get("collapsible") != null) {
                        if ((Boolean) (component.getAttributes().get("collapsible"))) {
                            writer.writeAttribute("onclick", "collapse('" + component.getId() + "');", null);
                        }
                    }
                    writer.startElement("span", component);
                    writer.writeAttribute("class", "x-panel-header-text", null);
                    writer.write((String) component.getAttributes().get("title"));
                    writer.endElement("span");
                    writer.endElement("div");
                    X_PANEL_HEADER_CLASS_STYLE = "x-panel-body";
                }

                if ((component.getAttributes().get("frame") != null) && ((Boolean) (component.getAttributes().get("frame")))) {
                    writer.endElement("div");
                    writer.endElement("div");
                    writer.endElement("div");
                }

                writer.startElement("div", component);
                writer.writeAttribute("id", "panel_toolbar:" + component.getClientId(context), null);
                writer.writeAttribute("class", "x-toolbar", null);

                writer.startElement("div", component);
                writer.startElement("tr", component);
                writer.startElement("td", component);
                writer.startElement("a", component);
                writer.writeAttribute("id", "panel_anchor:" + component.getClientId(context) + ":expand", null);
                writer.writeAttribute("class", "x-btn", null);
                writer.writeAttribute("onclick", "expandAll(this)", null);
                writer.write(EXPAND_TEXT);
                writer.endElement("a");
                writer.endElement("td");
                writer.write("/");
                writer.startElement("td", component);
                writer.startElement("a", component);
                writer.writeAttribute("id", "panel_anchor:" + component.getClientId(context) + ":collapse", null);
                writer.writeAttribute("class", "x-btn", null);
                writer.writeAttribute("onclick", "collapseAll(this)", null);
                writer.write(COLLAPSE_TEXT);
                writer.endElement("a");
                writer.endElement("td");
                writer.endElement("tr");
                writer.endElement("div");
                writer.endElement("div");
            }
            writer.startElement("div", component);
            writer.writeAttribute("id", "panel_content:" + component.getClientId(context), null);
            writer.writeAttribute("class", "x-panel-bwrap", null);
            writer.writeAttribute("style", "display:block;", null);

            writer.startElement("div", component);
            writer.writeAttribute("id", "panel_headers:" + component.getClientId(context), null);
            writer.writeAttribute("class", X_PANEL_HEADER_CLASS_STYLE, null);

            //Encode Columns header
            renderHeadColumn(context, component);

            writer.endElement("div");

            writer.startElement("div", component);
            writer.writeAttribute("id", "panel_lines:" + component.getClientId(context), null);
            writer.writeAttribute("class", "droppable-holder", null);
            if ((component.getAttributes().get("frame") != null) && ((Boolean) (component.getAttributes().get("frame")))) {
                writer.startElement("div", component);
                writer.writeAttribute("class", "x-panel-ml", null);
                writer.startElement("div", component);
                writer.writeAttribute("class", "x-panel-mr", null);
                writer.startElement("div", component);
                writer.writeAttribute("class", "x-panel-mc", null);
            }

            if (treepanel.getView() == null) {
                treepanel.setView(treetable.getTree());
            }

            TreeNodeModel root = treepanel.getView().getRoot();

            if (!treepanel.isInit()) {
                List<UIComponent> backup = new ArrayList<UIComponent>();
                List<UIComponent> children = component.getChildren();
                for (UIComponent tmp : children) {
                    if (!(tmp instanceof UIAbstractTreeLines)) {
                        tmp.setId(component.getId() + "_" + tmp.getId());
                        backup.add(tmp);
                    } else {
                        ((UIAbstractTreePanel) component).setInit(true);
                    }
                }
                /*            
                 * Create all Treelines to the context
                 */
                long start = System.currentTimeMillis();
                createTreeLines(((UIAbstractTreePanel) component), root, backup);
                long duree = System.currentTimeMillis() - start;
                if (debug) {
                    log.info("createTreeLines times : " + duree + " mlls");
                }
            }

            ((UIAbstractTreePanel) component).setInit(true);

            if (debug) {
                log.info("afterEncodeBegin : " + AbstractTreePanelRenderer.class.getName());
            }
            afterEncodeBegin(context, component);

        }
    }

    /**
     * 
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        if (debug) {
            log.info("encodeChildren : " + AbstractTreePanelRenderer.class.getName());
        }
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) component;

        TreeTableModel tree = treepanel.getView();
        TreeNodeModel root = tree.getRoot();
        for (int i = 0; i < root.getChildCount(); i++) {
            TreeNodeModel child = (TreeNodeModel) root.getChildAt(i);
            if (!treepanel.isShowRoot()) {
                ((UIAbstractTreeLines) (Utils.findComponent(context, treepanel.getClientId(context) + "_line_" + child.getId()))).setToRender(false);
            }

            Utils.encodeRecursive(context, (Utils.findComponent(context, treepanel.getClientId(context) + "_line_" + child.getId())));
        }
    }

    /**
     * 
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (debug) {
            log.info("beforeEncodeEnd : " + AbstractTreePanelRenderer.class.getName());
        }
        beforeEncodeEnd(context, component);

        if (debug) {
            log.info("encodeEnd : " + AbstractTreePanelRenderer.class.getName());
        }

        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
        if ((component.getAttributes().get("frame") != null) && ((Boolean) (component.getAttributes().get("frame")))) {
            writer.endElement("div");
            writer.endElement("div");
            writer.endElement("div");
            writer.startElement("div", component);
            writer.writeAttribute("class", "x-panel-bl x-panel-nofooter", null);
            writer.startElement("div", component);
            writer.writeAttribute("class", "x-panel-br", null);
            writer.startElement("div", component);
            writer.writeAttribute("class", "x-panel-bc", null);
            writer.endElement("div");
            writer.endElement("div");
            writer.endElement("div");
        }
        writer.endElement("div");
        writer.endElement("div");
        writer.startElement("div", component);
        writer.writeAttribute("class", "x-clear", null);
        writer.endElement("div");

        if (debug) {
            log.info("afterEncodeEnd : " + AbstractTreePanelRenderer.class.getName());
        }
        afterEncodeEnd(context, component);

    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        return;
    }

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    @Override
    public void handleAjaxRequest(FacesContext context, UIComponent component) {
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) component;
        if (component.getAttributes().get("debug") != null) {
            debug = (Boolean) component.getAttributes().get("debug");
        }


        AjaxUtils ajaxtools = new AjaxUtils();
        TreeModelsUtils treeTools = new TreeModelsUtils();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        String TreeLineNewParentId = request.getParameter(ajaxtools.getDND_NEW_PARENT_COMPONENT());
        String TreeLineinDragId = request.getParameter(ajaxtools.getAJAX_COMPONENT_ID_KEY());
        String Position = request.getParameter(ajaxtools.getDND_POSITION_LINE());
        UIAbstractTreeLines treeLinesToDrag = (UIAbstractTreeLines) Utils.findComponentById(context, context.getViewRoot(), TreeLineinDragId);
        UIAbstractTreeLines treeLinesToDragIn = (UIAbstractTreeLines) Utils.findComponentById(context, context.getViewRoot(), TreeLineNewParentId);
        TreeTableModel tree = treepanel.getView();

        if (debug) {
            System.out.println(AbstractTreePanelRenderer.class + " before move node");
            treeTools.printTree(tree);
        }

        int targetNode = treeLinesToDragIn.getNodeInstance().getId();
        int movedNode = treeLinesToDrag.getNodeInstance().getId();
        int parentTargetNode = ((TreeNodeModel) tree.getById(targetNode).getParent()).getId();
        int position;

        if (Position.equals("lastitem")) {
            position = treeLinesToDrag.getNodeInstance().getChildCount();
            tree = treeTools.moveTo(tree, movedNode, targetNode, position + 1);
        } else if (Position.equals("before")) {
            tree = treeTools.insertBefore(tree, movedNode, targetNode);
        } else if (Position.equals("after")) {
            tree = treeTools.insertAfter(tree, movedNode, targetNode);
        } else if (Position.equals("firstitem")) {
            tree = treeTools.moveTo(tree, movedNode, targetNode);
        } else {
            tree = treeTools.moveTo(tree, movedNode, targetNode);
        }

        if (debug) {
            System.out.println(AbstractTreePanelRenderer.class + " after move node");
            treeTools.printTree(tree);
        }

        treepanel.setView(tree);
    }

    /* ======================= OTHERS METHODS ==================================*/
    /**
     * 
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    public void renderHeadColumn(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        int id = 0;
        //RENDER CHECK COLUMN
        if (component.getAttributes().get("check") != null) {
            if ((Boolean) component.getAttributes().get("check")) {
                writer.startElement("div", component);
                writer.writeAttribute("id", "x-tree-hd:" + id, null);
                writer.writeAttribute("class", "x-tree-hd", null);
                writer.writeAttribute("style", "width: 30px;", null);
                writer.startElement("div", component);
                writer.writeAttribute("id", "x-tree-hd-text:" + id, null);
                writer.writeAttribute("class", "x-tree-hd-text", null);
                writer.startElement("input", component);
                writer.writeAttribute("id", "checkcolumn", null);
                writer.writeAttribute("type", "checkbox", null);
                writer.writeAttribute("onclick", "checkall();", null);
                writer.endElement("input");
                writer.endElement("div");
                writer.endElement("div");
            }
        }

        //RENDER ROW ID NUMBER IF IS NECESSARY
        if (component.getAttributes().get("rowId") != null) {
            if ((Boolean) component.getAttributes().get("rowId")) {
                writer.startElement("div", component);
                writer.writeAttribute("id", "x-tree-hd:" + id, null);
                writer.writeAttribute("class", "x-tree-hd", null);
                writer.writeAttribute("style", "width: 30px;", null);
                writer.startElement("div", component);
                writer.writeAttribute("id", "x-tree-hd-text:" + id, null);
                writer.writeAttribute("class", "x-tree-hd-text", null);
                writer.write("Id");
                writer.endElement("div");
                writer.endElement("div");
            }
        }

        List<UIComponent> components = component.getChildren();
        for (UIComponent uIComponent : components) {
            id++;
            if (!(uIComponent instanceof UIAbstractTreeLines)) {
                if (!(uIComponent instanceof UIAbstractTreeNodeInfo)) {
                    if (!(uIComponent.getId().contains(component.getId()))) {
                        renderHeaders(context, uIComponent, id);
                    }
                }
            }
        }

        writer.startElement("div", component);
        writer.writeAttribute("class", "x-clear", null);
        writer.endElement("div");
    }

    /**
     * Render the header of a column
     * @param context 
     * @param component
     * @param idnumbers
     * @throws java.io.IOException
     */
    public void renderHeaders(FacesContext context, UIComponent component, int idnumbers) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("div", component);
        writer.writeAttribute("id", "x-tree-hd:" + idnumbers, null);
        writer.writeAttribute("class", "x-tree-hd", null);

        String size = config.getDEFAULT_SIZE_COLUMN();
        if (component.getAttributes().get("width") != null) {
            size = String.valueOf(component.getAttributes().get("width"));
            int sizeInt = Integer.valueOf(size);
            sizeInt -= 1;
            size = String.valueOf(sizeInt);
        }
        writer.writeAttribute("style", "width:" + size + "px;", null);
        writer.startElement("div", component);
        writer.writeAttribute("id", "x-tree-hd-text:" + idnumbers, null);
        writer.writeAttribute("class", "x-tree-hd-text", null);
        writer.startElement("center", component);
        if (component.getAttributes().get("icon") != null) {
            writer.startElement("img", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, (String) component.getAttributes().get("icon"), null), null);
            writer.endElement("img");
        } else if (component.getAttributes().get("header") != null) {
            writer.write((String) component.getAttributes().get("header"));
        }
        writer.endElement("center");
        writer.endElement("div");
        writer.endElement("div");


    }

    /* ======================= ABSTRACT METHODS ==================================*/
    public abstract void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    public abstract void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    public abstract void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException;

    public abstract void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException;

    public abstract void createTreeLines(UIComponent component, TreeNodeModel node, List<UIComponent> list);
}
