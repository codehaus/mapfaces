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
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mapfaces.component.abstractTree.UIAbstractTreeLines;
import org.mapfaces.component.abstractTree.UIAbstractTreePanel;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.AjaxUtils;

/**
 *
 * @author kevindelfour
 */
public abstract class AbstractTreeLinesRenderer extends Renderer implements AjaxRendererInterface {

    private AjaxUtils ajaxtools = new AjaxUtils();
    private boolean debug = false;
    private static final transient Log log = LogFactory.getLog(AbstractTreeLinesRenderer.class);
    private static String CLASS_NODE_LI = "x-tree-node x-tree-lines";
    private static String CLASS_LEAF_DIV = "x-tree-node-el x-tree-node-leaf x-tree-col";

    /**
     * This method returns the parent form of this element.
     * If this element is a form then it simply returns itself.
     * @param component - 
     * @return
     */
    private static UIAbstractTreePanel getForm(UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null) {
            if (parent instanceof UIAbstractTreePanel) {
                break;
            }
            parent = parent.getParent();
        }
        if (parent == null) {
            throw new IllegalStateException("Not nested inside a tree panel!");
        }
        return (UIAbstractTreePanel) parent;
    }

    /**
     * 
     * @param component
     * @return
     */
    private String getPostbackFunctionName(UIComponent component) {
        UIAbstractTreeLines treelines = (UIAbstractTreeLines) component;
        return treelines.getId() + "PostBack";
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        if (component.getAttributes().get("debug") != null) {
            debug = (Boolean) component.getAttributes().get("debug");
        }

        if (debug) {
            log.info("beforeEncodeBegin : " + AbstractTreeLinesRenderer.class.getName());
        }
        beforeEncodeBegin(context, component);

        //Start encoding
        if (debug) {
            log.info("encodeBegin : " + AbstractTreeLinesRenderer.class.getName());
        }
        UIAbstractTreeLines treeline = (UIAbstractTreeLines) component;
        ResponseWriter writer = context.getResponseWriter();

        String treepanelId = Utils.getWrappedComponent(context, treeline, UIAbstractTreePanel.class);
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) Utils.findComponent(context, treepanelId);
        /*
         * Get the node instance for rendering lines
         */
        TreeNodeModel node = treeline.getNodeInstance();
        treeline.setRow(node.getId());

        /*
         * Prepare informations for making any Ajax request
         */

        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_REQUEST_PARAM_KEY(), "true");
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_CONTAINER_ID_KEY(), treepanelId + "_line_" + node.getId());
        ajaxtools.addAjaxParameter("javax.faces.ViewState", "'+viewstate+'");
        String AJAX_SERVER = ajaxtools.getAjaxServer(request);
        String AJAX_PARAMETERS = ajaxtools.getAjaxParameters();
        String Request = ajaxtools.getRequestJs("get", AJAX_SERVER, AJAX_PARAMETERS);

        Boolean isFolder = !(node.isLeaf());
        String classStyle = "";
        if (treepanel.isEnableDragDrop()) {
            if (!isFolder) {
                classStyle = "dragable";
            } else {
                if (node.getId() > 2) {
                    classStyle = "droppable";
                } else {
                    classStyle = "droppable locked";
                }
            }
        }

        if (treeline.isToRender()) {
            TreeNodeModel root = treepanel.getView().getRoot();
            if (node.getParent().equals(root)) {
                writer.startElement("ul", component);
                writer.writeAttribute("id", "ul:" + treepanelId + ":0", null);
                if (treepanel.isEnableDragDrop()) {
                    writer.startElement("div", treeline);
                    writer.writeAttribute("id", "dnd:" + treepanelId + ":" + node.getId() + ":inList", null);
                    writer.writeAttribute("class", "droppable", null);
                    writer.writeAttribute("name", treeline.getId(), null);
                    writer.writeAttribute("style", "width:auto; height:2px; margin-left:" + (node.getDepth()) * 25 + "px", null);
                    writer.writeAttribute("dest", "ul:" + treepanelId + ":0", null);
                    writer.writeAttribute("pos", node.getId(), null);
                    writer.writeAttribute("depth", node.getDepth() + 1, null);
                    writer.writeAttribute("where", "firstitem", null);
                    writer.endElement("div");
                }
            }

            writer.startElement("div", component);
            writer.writeAttribute("id", "line:" + treepanelId + ":" + node.getId(), null);
            writer.writeAttribute("name", treeline.getId(), null);
            writer.writeAttribute("parent", treepanel.getId(), null);
            writer.writeAttribute("depth", node.getDepth(), null);

            if (treepanel.isEnableDragDrop()) {
                //First zone to drop : before the folder with the same depth or before a node item
                writer.startElement("div", treeline);
                writer.writeAttribute("id", "dnd:" + treepanelId + ":" + node.getId() + ":before", null);
                writer.writeAttribute("class", "droppable", null);
                writer.writeAttribute("style", "width:auto; height:2px; margin-left:" + (node.getDepth()) * 25 + "px", null);
                writer.writeAttribute("where", "before", null);
                writer.writeAttribute("name", treeline.getId(), null);
                writer.writeAttribute("pos", node.getId(), null);
                writer.endElement("div");
            }

            writer.startElement("li", component);
            writer.writeAttribute("id", "li:" + treepanelId + ":" + node.getId(), null);
            writer.writeAttribute("depth", node.getDepth(), null);
            writer.writeAttribute("class", CLASS_NODE_LI + " " + classStyle, null);
            writer.writeAttribute("pos", node.getId(), null);
            writer.writeAttribute("name", treeline.getId(), null);

            String styleLeafUser = "";
            if (treepanel.getStyleLeaf() != null) {
                styleLeafUser = treepanel.getStyleLeaf();
            }

            String styleNodeUser = "";
            if (treepanel.getStyleNode() != null) {
                styleNodeUser = treepanel.getStyleNode();
            }
            if (isFolder) {
                writer.writeAttribute("style", "position:relative;" + styleNodeUser, null);
            } else {
                if (treepanel.isEnableDragDrop()) {
                    writer.writeAttribute("style", "background : white; position:relative;" + styleLeafUser, null);
                } else {
                    writer.writeAttribute("style", "position:relative;" + styleLeafUser, null);
                }
            }


            if (treepanel.getAttributes().get("check") != null) {
                if ((Boolean) treepanel.getAttributes().get("check")) {
                    writer.startElement("div", component);
                    writer.writeAttribute("style", "width: 30px;  margin-left: 5px; margin-top:3px; ", null);
                    writer.writeAttribute("class", CLASS_LEAF_DIV, null);
                    writer.startElement("input", component);
                    writer.writeAttribute("id", "check:" + node.getId(), null);
                    writer.writeAttribute("type", "checkbox", null);
                    if (node.isChecked()) {
                        writer.writeAttribute("checked", "true", null);
                    }
                    /*
                     * Writing ajax request
                     */
                    writer.writeAttribute("onchange", "viewstate = document.getElementById('javax.faces.ViewState').value;" + Request, null);
                    writer.endElement("input");
                    writer.endElement("div");
                }
            }

            if (treepanel.getAttributes().get("rowId") != null) {
                if ((Boolean) treepanel.getAttributes().get("rowId")) {
                    writer.startElement("div", component);
                    writer.writeAttribute("style", "width: 30px;  margin-left: 5px;", null);
                    writer.writeAttribute("class", CLASS_LEAF_DIV, null);
                    writer.write(node.getId() + "");
                    writer.endElement("div");
                }
            }

            if (debug) {
                log.info("afterEncodeBegin : " + AbstractTreeLinesRenderer.class.getName());
            }
            afterEncodeBegin(context, component);
        }
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        if (debug) {
            log.info("encodeChildren : " + AbstractTreeLinesRenderer.class.getName());
        }

        UIAbstractTreeLines treeline = (UIAbstractTreeLines) component;
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) treeline.getParent();
        TreeTableModel tree = treepanel.getView();
        TreeNodeModel node = tree.getById(treeline.getNodeInstance().getId());

        ResponseWriter writer = context.getResponseWriter();
        String treepanelId = Utils.getWrappedComponent(context, treeline, UIAbstractTreePanel.class);

        Boolean isFolder = !(node.isLeaf());

        List<UIComponent> children = treeline.getChildren();
        if (treeline.isToRender()) {
            for (UIComponent tmp : children) {
                if (!tmp.getFamily().equals(treeline.getFamily())) {
                    Utils.encodeRecursive(context, tmp);
                }
            }
        }

        if (isFolder) {
            writer.startElement("div", treeline);
            writer.writeAttribute("class", "x-clear", null);
            writer.endElement("div");


            if (treeline.hasChildren()) {
                writer.startElement("ul", treeline);
                writer.writeAttribute("id", "ul:" + treepanelId + ":" + node.getId(), null);

                // Second zone to drop : in the list at first position
                if (treepanel.isEnableDragDrop()) {
                    if (isFolder) {
                        writer.startElement("div", treeline);
                        writer.writeAttribute("id", "dnd:" + treepanelId + ":" + node.getId() + ":inList", null);
                        writer.writeAttribute("class", "droppable", null);
                        writer.writeAttribute("style", "width:auto; height:2px; margin-left:" + (node.getDepth() + 1) * 25 + "px", null);
                        writer.writeAttribute("name", treeline.getId(), null);
                        writer.writeAttribute("dest", "ul:" + treepanelId + ":" + node.getId(), null);
                        writer.writeAttribute("pos", node.getId(), null);
                        writer.writeAttribute("depth", node.getDepth() + 1, null);
                        writer.writeAttribute("where", "firstitem", null);
                        writer.endElement("div");
                    }
                }

                //Encode child
                for (int i = 0; i < node.getChildCount(); i++) {
                    TreeNodeModel child = (TreeNodeModel) node.getChildAt(i);
                    if (child.isChecked()) {
                        Utils.encodeRecursive(context, (Utils.findComponent(context, treepanel.getClientId(context) + "_line_" + child.getId())));
                    }
                }

                writer.endElement("ul");
            }
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (debug) {
            log.info("beforeEncodeEnd : " + AbstractTreeLinesRenderer.class.getName());
        }
        beforeEncodeEnd(context, component);

        UIAbstractTreeLines treeline = (UIAbstractTreeLines) component;
        String treepanelId = Utils.getWrappedComponent(context, treeline, UIAbstractTreePanel.class);
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) Utils.findComponent(context, treepanelId);
        TreeNodeModel node = treeline.getNodeInstance();

        if (debug) {
            log.info("encodeEnd : " + AbstractTreeLinesRenderer.class.getName());
        }
        ResponseWriter writer = context.getResponseWriter();
        Boolean isFolder = !(node.isLeaf());

        if (treeline.isToRender()) {
            writer.startElement("div", treeline);
            writer.writeAttribute("class", "x-clear", null);
            writer.endElement("div");
            writer.endElement("li");

            writer.startElement("script", component);
            writer.write(addLinesEvent(context, component));
            writer.endElement("script");

            if (treepanel.isEnableDragDrop()) {
                writer.startElement("div", treeline);
                writer.writeAttribute("id", "dnd:" + treepanelId + ":" + node.getId() + ":after", null);
                writer.writeAttribute("class", "droppable", null);
                writer.writeAttribute("style", "width:auto; height:2px; margin-left:" + (node.getDepth()) * 25 + "px", null);
                writer.writeAttribute("name", treeline.getId(), null);
                writer.writeAttribute("where", "after", null);
                writer.writeAttribute("pos", node.getId(), null);
                writer.endElement("div");
            }
            writer.endElement("div");

            TreeNodeModel root = treepanel.getView().getRoot();

            if (node.getParent().equals(root)) {
                writer.endElement("ul");
            }
            if (debug) {
                log.info("afterEncodeEnd : " + AbstractTreeLinesRenderer.class.getName());
            }

            afterEncodeEnd(context, component);
        }
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
    }

    /**
     * 
     * @return 
     */
    public String getErrorString() {
        return new String("<response><message>There was a problem</message><status>ERROR</status></response>");
    }

    /* ======================= ABSTRACT METHODS ==================================*/
    public abstract void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    public abstract void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    public abstract void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException;

    public abstract void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException;

    public abstract String addLinesEvent(FacesContext context, UIComponent component);
}
