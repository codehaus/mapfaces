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
import java.util.Date;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mapfaces.component.abstractTree.UIAbstractColumn;
import org.mapfaces.component.abstractTree.UIAbstractTreeColumn;
import org.mapfaces.component.abstractTree.UIAbstractTreeLines;
import org.mapfaces.component.abstractTree.UIAbstractTreeNodeInfo;
import org.mapfaces.component.abstractTree.UIAbstractTreePanel;
import org.mapfaces.component.abstractTree.UIAbstractTreeTable;

import org.mapfaces.models.tree.TreeModelsUtils;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.AjaxUtils;
import org.mapfaces.util.treetable.TreeTableConfig;

/**
 *
 * @author kdelfour
 */
public abstract class AbstractTreePanelRenderer extends Renderer implements AjaxRendererInterface, CustomizeTreeComponentRenderer {

    /* Local Fiels */
    private boolean debug = true;
    //private static final Log log = LogFactory.getLog(AbstractTreePanelRenderer.class);
    private TreeTableConfig config = new TreeTableConfig();
    private Date renderStart,  renderEnd;
    private long encodeBeginTime,  encodeChildrenTime,  encodeEndTime;

    /* Local Constants */
    private String EXPAND_TEXT = "Expand";
    private String COLLAPSE_TEXT = "Collapse";
    private String X_PANEL_HEADER_CLASS_STYLE = "x-panel-body x-panel-body-noheader";

    /**
     * <p> Render the beginning specified TreeTable Component to the output stream or writer associated 
     * with the response we are creating. If the conversion attempted in a previous call to getConvertedValue()
     * for this component failed, the state information saved during execution of decode() should be used to 
     * reproduce the incorrect input.</p>
     * <ul><li>Firstly, get the TreeTableModel from the treetable</li>
     * <li>Then create Treelines Component with the View present in each Treepanel </li>
     * </ul>
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered 
     * @throws java.io.IOException if an input/output error occurs while rendering 
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        UIAbstractTreeTable treetable = getForm(component);
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) component;
        Date phaseStart, phaseEnd;
        Boolean renderHeader, renderFrame, makeCollapsible;
        String title, styleUser, styleClass, clientId;

        /* Initialisation */
        clientId = component.getClientId(context);
        renderHeader = treepanel.isHeader();
        renderFrame = treepanel.isFrame();
        makeCollapsible = treepanel.isCollapsible();
        title = "";
        phaseStart = new Date();
        renderStart = new Date();
        styleUser = "";
        styleClass = "";

        /* 
         * Tests
         * Firstly, test before rendering if the component is present in a UIForm
         * Then verify if the component haven't been rendered yet
         */
        if (!(getForm(component) instanceof UIAbstractTreeTable)) {
            return;
        }
        /* Is the component haven't been rendered yet ? */
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        /* GetAttributes from the UIComponent  */
        if (treepanel.isDebug()) {
            debug = treepanel.isDebug();
        }
        if (treepanel.getStyle() != null) {
            styleUser = treepanel.getStyle();
        }
        if (treepanel.getStyleClass() != null) {
            styleClass = treepanel.getStyleClass();
        }
        if (treepanel.getTitle() != null) {
            title = treepanel.getTitle();
        }


        /* Before encodeBegin, any method declared in a component extends this class can be launch here*/
        if (debug) {
            System.out.println("[INFO] beforeEncodeBegin : " + AbstractTreePanelRenderer.class.getName());
        }
        beforeEncodeBegin(context, component);

        /* Start encoding */
        if (debug) {
            System.out.println("[INFO] encodeBegin : " + AbstractTreePanelRenderer.class.getName());
        }

        if (component != null) {
            // DIV Start
            writer.startElement("div", component);
            writer.writeAttribute("id", "panel:" + clientId, null);
            writer.writeAttribute("style", "z-index:0; background :#CCCCCC;" + styleUser, null);
            if (!styleClass.isEmpty()) {
                writer.writeAttribute("class", styleClass, null);
            }

            //HEADER Attribute
            if (renderHeader) {
                //FRAME Attribute
                if (renderFrame) {
                    writer.startElement("div", component);
                    writer.writeAttribute("class", "x-panel-tl", null);
                    writer.startElement("div", component);
                    writer.writeAttribute("class", "x-panel-tr", null);
                    writer.startElement("div", component);
                    writer.writeAttribute("class", "x-panel-tc", null);
                }


                // TITLE Attribute
                if (!title.isEmpty()) {
                    writer.startElement("div", component);
                    writer.writeAttribute("id", "panel_title:" + clientId, null);
                    writer.writeAttribute("class", "x-panel-header x-unselectable", null);

                    if (makeCollapsible) {
                        writer.writeAttribute("onclick", "collapse('" + component.getId() + "');", null);
                    }
                    writer.startElement("span", component);
                    writer.writeAttribute("class", "x-panel-header-text", null);
                    writer.write(title);
                    writer.endElement("span");
                    writer.endElement("div");
                    X_PANEL_HEADER_CLASS_STYLE = "x-panel-body";
                }

                if (renderFrame) {
                    writer.endElement("div");
                    writer.endElement("div");
                    writer.endElement("div");
                }

                // DIV Toolbar
                writer.startElement("div", component);
                writer.writeAttribute("id", "panel_toolbar:" + clientId, null);
                writer.writeAttribute("class", "x-toolbar", null);

                writer.startElement("div", component);
                writer.startElement("tr", component);
                writer.startElement("td", component);
                writer.startElement("a", component);
                writer.writeAttribute("id", "panel_anchor:" + clientId + ":expand", null);
                writer.writeAttribute("class", "x-btn", null);
                writer.writeAttribute("onclick", "expandAll(this)", null);
                writer.write(EXPAND_TEXT);
                writer.endElement("a");
                writer.endElement("td");
                writer.write("/");
                writer.startElement("td", component);
                writer.startElement("a", component);
                writer.writeAttribute("id", "panel_anchor:" + clientId + ":collapse", null);
                writer.writeAttribute("class", "x-btn", null);
                writer.writeAttribute("onclick", "collapseAll(this)", null);
                writer.write(COLLAPSE_TEXT);
                writer.endElement("a");
                writer.endElement("td");
                writer.endElement("tr");
                writer.endElement("div");
                writer.endElement("div");
            }

            //DIV Content
            writer.startElement("div", component);
            writer.writeAttribute("id", "panel_content:" + component.getClientId(context), null);
            writer.writeAttribute("class", "x-panel-bwrap", null);
            writer.writeAttribute("style", "display:block;", null);

            //DIV Headers
            writer.startElement("div", component);
            writer.writeAttribute("id", "panel_headers:" + component.getClientId(context), null);
            writer.writeAttribute("class", X_PANEL_HEADER_CLASS_STYLE, null);

            //Encode Columns header
            renderHeadColumn(context, component);

            writer.endElement("div");

            //DIV Lines
            writer.startElement("div", component);
            writer.writeAttribute("id", "panel_lines:" + component.getClientId(context), null);
            writer.writeAttribute("class", "droppable-holder", null);

            writer.writeAttribute("style", "overflow:auto; max-height: 430px;", null);

            if ((component.getAttributes().get("frame") != null) && ((Boolean) (component.getAttributes().get("frame")))) {
                writer.startElement("div", component);
                writer.writeAttribute("class", "x-panel-ml", null);
                writer.startElement("div", component);
                writer.writeAttribute("class", "x-panel-mr", null);
                writer.startElement("div", component);
                writer.writeAttribute("class", "x-panel-mc", null);
            }

            /* Tree view ans Tree lines Component contruction 
             * At this state, we are getting the TreeTable model store in the TreeTable Component
             * and we store it ine the TreePanel Component.
             * We can apply some methods to modify Treetable model in View.
             * After that, we take this model to create Treelines Component recursively
             * All Treelines will be children of this Treepanel
             */
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
                    }
                }
                
                /* Create all Treelines for the context */
                long start = System.currentTimeMillis();
                createTreeLines(((UIAbstractTreePanel) component), root, backup);
                long time = System.currentTimeMillis() - start;
                if (debug) {
                    System.out.println("[INFO] createTreeLines times in " + time + " mlls");
                }
                treepanel.setInit(true);
            }

            /* After encodeBegin, any method declared in a component extends this class can be launch here*/
            if (debug) {
                System.out.println("[INFO] afterEncodeBegin : " + AbstractTreePanelRenderer.class.getName());
            }
            afterEncodeBegin(context, component);
        }

        phaseEnd = new Date();
        encodeBeginTime = phaseStart.getTime() - phaseEnd.getTime();
    }

   /**
     * <p>Render the child components of this UIComponent, following the rules described for encodeBegin()
     * to acquire the appropriate value to be rendered.</p>
     * <p>This method will only be called if the rendersChildren property of this component is true.</p>
     * @param context FacesContext for the response we are creating
     * @param component UIComponent whose children are to be rendered 
     * @throws java.io.IOException if an input/output error occurs while rendering 
     */
    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) component;
        Date phaseStart, phaseEnd;

        /* Initialisation */
        phaseStart = new Date();

        if (debug) {
            System.out.println("[INFO] encodeChildren : " + AbstractTreePanelRenderer.class.getName());
        }

        TreeTableModel tree = treepanel.getView();
        TreeNodeModel root = tree.getRoot();
        for (int i = 0; i < root.getChildCount(); i++) {
            TreeNodeModel child = (TreeNodeModel) root.getChildAt(i);
            if (!treepanel.isShowRoot()) {
                if (child.isChecked()) {
                    ((UIAbstractTreeLines) (Utils.findComponent(context, treepanel.getClientId(context) + "_line_" + child.getId()))).setToRender(false);
                }
            }

            Utils.encodeRecursive(context, (Utils.findComponent(context, treepanel.getClientId(context) + "_line_" + child.getId())));
        }

        phaseEnd = new Date();
        encodeChildrenTime = phaseStart.getTime() - phaseEnd.getTime();
    }

    /**
     * 
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        Date phaseStart, phaseEnd;

        /* Initialisation */
        phaseStart = new Date();

        if (debug) {
            System.out.println("[INFO] beforeEncodeEnd : " + AbstractTreePanelRenderer.class.getName());
        }
        beforeEncodeEnd(context, component);

        if (debug) {
            System.out.println("[INFO] encodeEnd : " + AbstractTreePanelRenderer.class.getName());
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
            System.out.println("[INFO] afterEncodeEnd : " + AbstractTreePanelRenderer.class.getName());
        }
        afterEncodeEnd(context, component);

        phaseEnd = new Date();
        if (debug) {
            renderEnd = new Date();
            long timeEncode = renderEnd.getTime() - renderStart.getTime();
            encodeEndTime = phaseStart.getTime() - phaseEnd.getTime();
            System.out.println("[INFO] encodeBegin have been rendered in " + encodeBeginTime + " mlls");
            System.out.println("[INFO] encodeChildren have been rendered in " + encodeChildrenTime + " mlls");
            System.out.println("[INFO] encodeEnd have been rendered in " + encodeEndTime + " mlls");
            System.out.println("[INFO] encode TreeTable have been rendered in " + timeEncode + " mlls");
        }
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        return;
    }

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
        String styleHeader = "";
        if (component instanceof UIAbstractTreeColumn) {
            if (((UIAbstractTreeColumn) component).getStyleHeader() != null) {
                styleHeader = ((UIAbstractTreeColumn) component).getStyleHeader();
            }
        } else if (component instanceof UIAbstractColumn) {
            if (((UIAbstractColumn) component).getStyleHeader() != null) {
                styleHeader = ((UIAbstractColumn) component).getStyleHeader();
            }
        }
        writer.startElement("div", component);
        writer.writeAttribute("id", "x-tree-hd:" + idnumbers, null);
        writer.writeAttribute("class", "x-tree-hd", null);

        String size = config.getDEFAULT_SIZE_COLUMN();
        if (component.getAttributes().get("width") != null) {
            size = String.valueOf(component.getAttributes().get("width"));
            int sizeInt = Integer.valueOf(size);
            sizeInt -= 1;
            size = String.valueOf(sizeInt);
            styleHeader += "width:" + size + "px";
        }

        writer.writeAttribute("style", styleHeader, null);

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
    /**
     * 
     * @param component
     * @param node
     * @param list
     */
    public abstract void createTreeLines(UIComponent component, TreeNodeModel node, List<UIComponent> list);
}
