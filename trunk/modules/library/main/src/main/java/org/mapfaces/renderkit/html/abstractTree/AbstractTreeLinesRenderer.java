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
import java.util.Enumeration;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.http.HttpServletRequest;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;
import org.mapfaces.component.abstractTree.UITreeLinesBase;
import org.mapfaces.component.abstractTree.UITreePanelBase;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.share.interfaces.A4JRendererInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.AjaxUtils;
import org.mapfaces.util.tree.TreeStyle;

/**
 *
 * @author kevindelfour
 */
public abstract class AbstractTreeLinesRenderer extends Renderer implements AjaxRendererInterface, A4JRendererInterface, CustomizeTreeComponentRenderer {

    /* Local Fields */
    private boolean debug;
    //private static final transient Log log = LogFactory.getLog(AbstractTreeLinesRenderer.class);
    private AjaxUtils ajaxtools = new AjaxUtils();
    private static String DEFAULT_STYLE_LINE;
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
        UITreeLinesBase treeline = (UITreeLinesBase) component;
        ResponseWriter writer = context.getResponseWriter();

        String treepanelId = Utils.getWrappedComponentId(context, treeline, UITreePanelBase.class);
        UITreePanelBase treepanel = (UITreePanelBase) Utils.findComponent(context, treepanelId);

        int countLine = treepanel.getOddEvenCountLine();
        treepanel.setOddEvenCountLine(countLine + 1);

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
            System.out.println("[INFO] encodeBegin : " + AbstractTreeLinesRenderer.class.getName() + " Component Id : " + component.getId());
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
                    writer.writeAttribute("style", "width:auto; margin-left:" + (node.getDepth()) * 25 + "px", null);
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
                writer.writeAttribute("style", "width:auto; margin-left:" + (node.getDepth()) * 25 + "px", null);
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

            String styleLeafUser = "",
                    styleNodeUser = "",
                    styleOddLine = "",
                    styleEvenLine = "";

            if (treepanel.getStyleLeaf() != null) {
                styleLeafUser = treepanel.getStyleLeaf();
            }

            if (treepanel.getStyleNode() != null) {
                styleNodeUser = treepanel.getStyleNode();
            }

            if (treepanel.getStyleOdd() != null) {
                styleOddLine = treepanel.getStyleOdd();
            }

            if (treepanel.getStyleEven() != null) {
                styleEvenLine = treepanel.getStyleEven();
            }

            DEFAULT_STYLE_LINE = TreeStyle.getRowStyle();

            if (countLine % 2 == 0) {
                DEFAULT_STYLE_LINE += styleOddLine;
            } else {
                DEFAULT_STYLE_LINE += styleEvenLine;
            }

            if (isFolder) {
                writer.writeAttribute("style", DEFAULT_STYLE_LINE + styleNodeUser, null);
            } else {
                writer.writeAttribute("style", DEFAULT_STYLE_LINE + styleLeafUser, null);
            }

            if (treepanel.isRowId()) {
                writer.startElement("div", component);
                writer.writeAttribute("style", "width: 30px; text-align:center;", null);
                writer.writeAttribute("class", CLASS_LEAF_DIV, null);
                writer.write(node.getId() + "");
                writer.endElement("div");
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
        UITreeLinesBase treeline = (UITreeLinesBase) component;
        ResponseWriter writer = context.getResponseWriter();
        String treepanelId = Utils.getWrappedComponentId(context, treeline, UITreePanelBase.class);
        UITreePanelBase treepanel = (UITreePanelBase) Utils.findComponent(context, treepanelId);
        TreeTableModel tree = treepanel.getView();
        TreeNodeModel node = tree.getById(treeline.getNodeInstance().getId());

        /* Initialisation */
        Boolean isFolder = !(node.isLeaf());

        if (debug) {
            System.out.println("[INFO] encodeChildren : " + AbstractTreeLinesRenderer.class.getName());
        }

        List<UIComponent> children = treeline.getChildren();
        if (treeline.isToRender()) {
            if (debug) {
                System.out.println("[INFO] encodeChildren : Encode line !");
            }
            for (UIComponent tmp : children) {
                if (!(tmp instanceof HtmlPanelGroup)) {
                    Utils.encodeRecursive(context, tmp);
                }
            }
        }

        if (isFolder) {
            if (debug) {
                System.out.println("[INFO] encodeChildren : Encode folder !");
            }
            writer.startElement("div", treeline);
            writer.writeAttribute("class", "x-clear", null);
            writer.endElement("div");

            if (treeline.hasChildren()) {
                if (debug) {
                    System.out.println("[INFO] encodeChildren : Encode children !");
                }
                writer.startElement("ul", treeline);
                writer.writeAttribute("id", "ul:" + treepanelId + ":" + node.getId(), null);
                writer.writeAttribute("style", "margin-left: 0.2em;", null);
                // Second zone to drop : in the list at first position
                if (treepanel.isEnableDragDrop()) {
                    if (isFolder) {
                        writer.startElement("div", treeline);
                        writer.writeAttribute("id", "dnd:" + treepanelId + ":" + node.getId() + ":inList", null);
                        writer.writeAttribute("class", "droppable", null);
                        writer.writeAttribute("style", "width:auto; margin-left:" + (node.getDepth() + 1) * 25 + "px", null);
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
                    if (debug) {
                        System.out.println("[INFO] encodeChildren : (TreeNodeModel) node.getChildAt(" + i + ")");
                    }
                    if (child.isChecked()) {
                        if (debug) {
                            System.out.println("[INFO] encodeChildren : Encode this child : " + treepanel.getClientId(context) + "_panel_" + child.getId());
                        }
                        Utils.encodeRecursive(context, (Utils.findComponent(context, treepanel.getClientId(context) + "_panel_" + child.getId())));
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

        UITreeLinesBase treeline = (UITreeLinesBase) component;
        String treepanelId = Utils.getWrappedComponentId(context, treeline, UITreePanelBase.class);
        UITreePanelBase treepanel = (UITreePanelBase) Utils.findComponent(context, treepanelId);
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
                writer.writeAttribute("style", "width:auto; margin-left:" + (node.getDepth()) * 25 + "px", null);
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
        UITreeLinesBase treeline = (UITreeLinesBase) component;
        String treepanelId = Utils.getWrappedComponentId(context, treeline, UITreePanelBase.class);
        UITreePanelBase treepanel = (UITreePanelBase) Utils.findComponent(context, treepanelId);
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String treelineParent = null;

        /*
         * Get id of Ajax component who launch the request
         */

        Enumeration<String> listParameters = request.getParameterNames();
        while (listParameters.hasMoreElements()) {
            String param = listParameters.nextElement();
            if (!((param.equals("AJAXREQUEST")) || (param.equals("ajax.server.request.URL")) || (param.equals("javax.ViewState")) || (param.contains("SUBMIT")))) {
                ExternalContext extContext = context.getExternalContext();
                extContext.getRequestMap().put("ajaxSupportOn", param);
            }
        }

        /*  */
        TreeNodeModel node = treeline.getNodeInstance();
        node.setChecked(true);
        treeline.setToRender(true);
        ExternalContext extContext = context.getExternalContext();

        if (extContext.getRequestMap().containsKey("ajaxSupportOn")) {
            UIComponent ajaxcomp = Utils.findComponent(context, (String) extContext.getRequestMap().get("ajaxSupportOn"));
            if (ajaxcomp instanceof HtmlAjaxSupport) {
                HtmlAjaxSupport ajaxSupport = (HtmlAjaxSupport) Utils.findComponent(context, (String) extContext.getRequestMap().get("ajaxSupportOn"));
                if (ajaxSupport != null) {
                    treelineParent = Utils.getWrappedComponentId(context, ajaxSupport, UITreeLinesBase.class);
                }
            }
            if (component.getClientId(context).equals(treelineParent)) {
                treeline.getNodeInstance().setChecked(true);
                for (int i = 0; i < node.getChildCount(); i++) {
                    TreeNodeModel child = (TreeNodeModel) node.getChildAt(i);
                    UITreeLinesBase treelineChild = ((UITreeLinesBase) Utils.findComponent(context, treepanel.getClientId(context) + "_line_" + child.getId()));
                    treelineChild.getNodeInstance().setChecked(true);
                    treelineChild.setRendered(true);
                    treelineChild.setToRender(true);
                }
            }
        }

    }

    /**
     * This method returns the parent UIAbstractTreePanel of this element.
     * If this element is a form then it simply returns itself.
     * @param component UIComponent to be rendered 
     * @return UIAbstractTreePanel the form container of the component if exist else return null
     */
    private static UITreePanelBase getForm(UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null) {
            if (parent instanceof UITreePanelBase) {
                break;
            }
            parent = parent.getParent();
        }
        if (parent == null) {
            throw new IllegalStateException("Not nested inside a tree panel!");
        }
        return (UITreePanelBase) parent;
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
        UITreeLinesBase treeline = (UITreeLinesBase) component;
        UITreePanelBase treepanel = (UITreePanelBase) treeline.getParent();

    }

    @Override
    public void A4JPostRequest(FacesContext context, UIComponent component) {
        UITreeLinesBase treeline = (UITreeLinesBase) component;
        UITreePanelBase treepanel = (UITreePanelBase) treeline.getParent();
        System.out.println("TESTONSSSS");

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
