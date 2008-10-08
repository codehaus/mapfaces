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
import org.mapfaces.component.abstractTree.UIAbstractTreeLines;
import org.mapfaces.component.abstractTree.UIAbstractTreePanel;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.AjaxUtils;

/**
 *
 * @author kevindelfour
 */
public abstract class AbstractTreeLinesRenderer extends Renderer implements AjaxRendererInterface, CustomizeTreeComponentRenderer {

    /* Local Fields */
    private boolean debug;
    //private static final transient Log log = LogFactory.getLog(AbstractTreeLinesRenderer.class);
    private AjaxUtils ajaxtools = new AjaxUtils();
    private static String CLASS_NODE_LI = "x-tree-node x-tree-lines";
    private static String CLASS_LEAF_DIV = "x-tree-node-el x-tree-node-leaf x-tree-col";

    /**
     * <p> Render the beginning specified TreeTable Component to the output stream or writer associated 
     * with the response we are creating. If the conversion attempted in a previous call to getConvertedValue()
     * for this component failed, the state information saved during execution of decode() should be used to 
     * reproduce the incorrect input.</p>
     * <ul><li>Firstly, get the tree value from the bean</li>
     * <li>Then translate into a TreeTableModel and write Css and Js headers before all</li>
     * </ul>
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered 
     * @throws java.io.IOException if an input/output error occurs while rendering 
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        UIAbstractTreeLines treeline = (UIAbstractTreeLines) component;
        ResponseWriter writer = context.getResponseWriter();

        String treepanelId = Utils.getWrappedComponent(context, treeline, UIAbstractTreePanel.class);
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) Utils.findComponent(context, treepanelId);
        
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);

        if (treeline.isDebug()) {
            debug = treeline.isDebug();
        }

        if (debug) {
            System.out.println("[INFO] beforeEncodeBegin : " + AbstractTreeLinesRenderer.class.getName());
        }
        beforeEncodeBegin(context, component);

        //Start encoding
        if (debug) {
            System.out.println("[INFO] encodeBegin : " + AbstractTreeLinesRenderer.class.getName());
        }
        
        /* Get the node instance for rendering lines */
        TreeNodeModel node = treeline.getNodeInstance();
        treeline.setRow(node.getId());

        /* Prepare informations for making any Ajax request */
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
                System.out.println("[INFO] afterEncodeBegin : " + AbstractTreeLinesRenderer.class.getName());
            }
            afterEncodeBegin(context, component);
        }
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
        if (debug) {
            System.out.println("[INFO] encodeChildren : " + AbstractTreeLinesRenderer.class.getName());
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

    /**
     * <p>Render the ending of the current state of the specified UIComponent, following the rules described for 
     * encodeBegin() to acquire the appropriate value to be rendered.</p>
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be rendered 
     * @throws java.io.IOException if an input/output error occurs while rendering 
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (debug) {
            System.out.println("[INFO] beforeEncodeEnd : " + AbstractTreeLinesRenderer.class.getName());
        }
        beforeEncodeEnd(context, component);

        UIAbstractTreeLines treeline = (UIAbstractTreeLines) component;
        String treepanelId = Utils.getWrappedComponent(context, treeline, UIAbstractTreePanel.class);
        UIAbstractTreePanel treepanel = (UIAbstractTreePanel) Utils.findComponent(context, treepanelId);
        TreeNodeModel node = treeline.getNodeInstance();

        if (debug) {
            System.out.println("[INFO] encodeEnd : " + AbstractTreeLinesRenderer.class.getName());
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
                System.out.println("[INFO] afterEncodeEnd : " + AbstractTreeLinesRenderer.class.getName());
            }

            afterEncodeEnd(context, component);
        }
    }

    /**
     * <p>Decode any new state of the specified UIComponent  from the request contained in the specified FacesContext, 
     * and store that state on the UIComponent.</p>
     * <p>During decoding, events may be enqueued for later processing (by event listeners that have registered an interest),
     * by calling queueEvent() on the associated UIComponent. </p>
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be decoded.
     * @throws java.lang.NullPointerException if context  or component is null
     */
    @Override
    public void decode(FacesContext context, UIComponent component) {
        return;
    }

    /**
     * This method returns the parent UIAbstractTreePanel of this element.
     * If this element is a form then it simply returns itself.
     * @param component UIComponent to be rendered 
     * @return UIAbstractTreePanel the form container of the component if exist else return null
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

    /* Others methods */
    /**
     * <p>Return a flag indicating whether this Renderer is responsible for rendering the 
     * children the component it is asked to render. The default implementation returns false.</p>
     * <p>By default, getRendersChildren returns true, so encodeChildren() will be invoked</p> 
     * @return True
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /**
     * Test if context or the UICompoent exist and are not null
     * @param context FacesContext for the request we are processing
     * @param component UIComponent to be tested
     */
    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }

    }

    /**
     * This method is executed by the Ajax listener when an Ajax call for this component is detected.
     * @param context FacesContext for the request we are processing
     * @param component UIComponent who get the Ajax request
     */
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

    /* ABSTRACT Methods */
    /**
     * <p>This method can be declared in each class who extends of AbstractTreeLinesRenderer to add javascript event 
     * for each lines</p>
     * @param context  FacesContext for the request we are processing
     * @param component UIComponent base
     * @return a string 
     */
    public abstract String addLinesEvent(FacesContext context, UIComponent component);
}
