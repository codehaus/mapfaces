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
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.faces.component.UIForm;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputText;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.tree.DefaultMutableTreeNode;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandLink;
import org.ajax4jsf.ajax.html.HtmlAjaxOutputPanel;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;

import org.mapfaces.component.UIDiv;
import org.mapfaces.component.abstractTree.UITreeColumnBase;
import org.mapfaces.component.abstractTree.UITreeLinesBase;
import org.mapfaces.component.abstractTree.UITreeNodeInfoBase;
import org.mapfaces.component.abstractTree.UITreePanelBase;
import org.mapfaces.component.abstractTree.UITreeTableBase;
import org.mapfaces.models.tree.TreeItem;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.share.interfaces.A4JRendererInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.AjaxUtils;

/**
 * @author Kevin Delfour
 */
public abstract class AbstractTreeColumnRenderer extends Renderer implements AjaxRendererInterface, A4JRendererInterface, CustomizeTreeComponentRenderer {

    private static final Logger LOGGER = Logger.getLogger(AbstractTreeColumnRenderer.class.getName());
    private static final String DEFAULT_SIZE_COLUMN = "250";
    private static final String DEFAULT_HEADER_COLUMN = "Tree";
    private static final String PIXEL = " floatLeft";
    private static final String NODE_LOADING = "/org/mapfaces/resources/tree/images/default/tree/loading.gif";
    private static final String CLASS_NODE_INDENT = "x-tree-node-indent";
    private static final String CLASS_NODE_LI = "x-tree-node x-tree-lines";
    private static final String CLASS_LEAF_DIV = "x-tree-node-el x-tree-node-leaf x-tree-col";
    private static final String CLASS_ICON = "x-tree-icon";
    private static final String CLASS_NODE_ICON = "x-tree-node-icon";
    private static final String CLASS_LEAF_ELBOW = "x-tree-ec-icon x-tree-elbow";
    private static final String CLASS_LEAF_ELBOW_END = "x-tree-ec-icon x-tree-elbow-end";
    private static final String CLASS_ANCHOR = "x-tree-node-anchor";
    private static final String CLASS_ANCHOR_INFO = "x-tree-ec-icon x-tree-node-info-anchor x-tree-node-info-anchor-plus";
    private static final String SHOW_MORE_INFORMATION_TITLE = "Show more informations";
    private static final int LINES_SHOW = 1;
    private static final int DEFAULT_FIRST_COLUMN_SIZE = 250;
    private String class_symbol_minus = "x-tree-ec-icon x-tree-elbow-minus"; //x-tree-elbow-end-minus
    private String class_symbol_plus = "x-tree-ec-icon x-tree-elbow-plus"; //x-tree-elbow-end-minus
    private String class_node_div = "x-tree-node-el x-tree-node-expanded x-tree-col";
    private boolean debug;

    /**
     * This method returns the parent form of this element.
     * If this element is a form then it simply returns itself.
     * @param component -
     * @return
     */
    private static UITreePanelBase getForm(final UIComponent component) {
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

    private String getPostbackFunctionName(final UIComponent component) {
        final UITreeColumnBase treecolumn = (UITreeColumnBase) component;
        return treecolumn.getId() + "PostBack";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {

        if (!component.isRendered()) {
            return;
        }

        assertValid(context, component);

        if (component.getAttributes().get("debug") != null) {
            debug = (Boolean) component.getAttributes().get("debug");
        }

        if (debug) {
            LOGGER.info("beforeEncodeBegin : " + AbstractTreeColumnRenderer.class.getName());
        }

        beforeEncodeBegin(context, component);

        //Start encoding
        if (debug) {
            LOGGER.info("encodeBegin : " + AbstractTreeColumnRenderer.class.getName());
        }

        final String treepanelId = Utils.getWrappedComponentId(context, component, UITreePanelBase.class);
        final UITreePanelBase treepanel = (UITreePanelBase) Utils.findComponent(context, treepanelId);
        final UITreeTableBase treetable = (UITreeTableBase) treepanel.getParent();
        final ResponseWriter writer = context.getResponseWriter();
        final UITreeColumnBase treecolumn = (UITreeColumnBase) component;
        final UITreeLinesBase treeline = (UITreeLinesBase) component.getParent();
        final TreeNodeModel node = treeline.getNodeInstance();
        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        final AjaxUtils ajaxtools = new AjaxUtils();

        ajaxtools.addAjaxParameter(AjaxUtils.AJAX_REQUEST_PARAM_KEY, "true");
        ajaxtools.addAjaxParameter(AjaxUtils.AJAX_RENDERCHILD_ID_KEY, "true");
        ajaxtools.addAjaxParameter(AjaxUtils.AJAX_CONTAINER_ID_KEY, component.getId());
        ajaxtools.addAjaxParameter(AjaxUtils.AJAX_NODE_ID_KEY, String.valueOf(node.getId()));
        ajaxtools.addAjaxParameter("javax.faces.ViewState", "'+viewstate+'");

        //Get width of the column
        final Object prop = component.getAttributes().get("width");
        final String size = (prop != null) ? String.valueOf(prop) : DEFAULT_SIZE_COLUMN;
        final boolean FolderType = !node.isLeaf();

        if (!treepanel.isLoadAll() || (treetable != null && treetable.isCollapsed())) {
            class_node_div = "x-tree-node-el x-tree-node-collapsed x-tree-col";
        }


        String styleUser = "";
        if (treecolumn.getStyle() != null) {
            styleUser = treecolumn.getStyle();
        }


        final int width = Integer.valueOf(size);
        writer.startElement("div", component);
        writer.writeAttribute("id", "treenode:" + treepanelId + ":" + node.getId(), null);
        writer.writeAttribute("style", "text-align:left; width:" + width + "px;" + styleUser, null);
        String classUser = "";

        if (treecolumn.getStyleClass() != null) {
            classUser = treecolumn.getStyleClass();
        }

        if (treepanel.isEnableDragDrop()) {
            if (FolderType) {
                writer.writeAttribute("class", class_node_div + " x-tree-droppable x-tree-droppable-folder " + classUser, null);
//            writer.writeAttribute("onclick", "viewstate = document.getElementById('javax.faces.ViewState').value; display(" + node.getId() + ",'get','" + AJAX_SERVER + "','" + AJAX_PARAMETERS + "', viewstate);", null);
            } else {
                writer.writeAttribute("class", CLASS_LEAF_DIV + " x-tree-dragable" + classUser, null);
            }
        } else {
            if (FolderType) {
                writer.writeAttribute("class", class_node_div + classUser, null);
            // writer.writeAttribute("onclick", "viewstate = document.getElementById('javax.faces.ViewState').value; display(" + node.getId() + ",'get','" + AJAX_SERVER + "','" + AJAX_PARAMETERS + "', viewstate);", null);
            } else {
                writer.writeAttribute("class", CLASS_LEAF_DIV + classUser, null);
            }
        }


        if (!((UITreeColumnBase) component).isAlreadyRender()) {

            //Adding Components to TreeColumn
            final List<UIComponent> children = component.getChildren();

            // 1: Add indent div with default background-image from tree.png

            for (int i = 1; i < node.getDepth(); i++) {
                final UIDiv ImgNodeIdent = new UIDiv();
                ImgNodeIdent.setStyleClass(CLASS_ICON + PIXEL);
                children.add(ImgNodeIdent);
            }


            // 2: Add non default tree image (minus, plus, elbow, end ...) from tree.png

            HtmlAjaxOutputPanel ImgNodeRep = new HtmlAjaxOutputPanel();
//TODO these lines must be used for working the loadAll attribute
//            final HtmlGraphicImage ImgNodeRep = new HtmlGraphicImage();

            if (FolderType) {
                ImgNodeRep.setId(treepanel.getId() + "_symbol_" + node.getId());

                if (treetable != null && treetable.isCollapsed() && treetable.getCollapseDepth() < node.getDepth()) {
                    ImgNodeRep.setStyleClass(class_symbol_plus + PIXEL);
                } else {
                    ImgNodeRep.setStyleClass(class_symbol_minus + PIXEL);
                }

            } else {

                if (((DefaultMutableTreeNode) node.getParent()).getLastLeaf() == node) {
                    ImgNodeRep.setStyleClass(CLASS_LEAF_ELBOW_END + PIXEL);

                } else {
                    ImgNodeRep.setStyleClass(CLASS_LEAF_ELBOW + PIXEL);
                }
            }
            //Remove default padding of the generated span
            ImgNodeRep.setStyle("padding:0px;");

            if (FolderType) {

                //In IE 8 : HtmlAjaxCommandLink works better than a classic HtmlCommandLink
                HtmlAjaxCommandLink ImgNodeRepLink = new HtmlAjaxCommandLink();
//TODO these lines must be used for working the loadAll attribute
//                HtmlAjaxSupport ImgNodeRepLink = new HtmlAjaxSupport();
//                ImgNodeRepLink.setEvent("onclick");
                
                ImgNodeRepLink.setId(treepanel.getId() + "_ajax_" + node.getId());
                ImgNodeRepLink.setReRender(treeline.getParent().getClientId(context));
                ImgNodeRepLink.setAjaxSingle(true);
                ImgNodeRepLink.setLimitToList(true);
                final String formId = Utils.getWrappedComponentId(context, component, UIForm.class);
                StringBuilder onSubmit = new StringBuilder(""). //If the current node has no child to expand, return false (so no A4J request has been sent)
                        append("if(!disp('").append(formId).append("','").append(treepanelId).append("','").append(node.getId()).append("')){").
                        append("return false;}");

                if (!treepanel.isLoadAll()) {
                    //else  replace the plus image by the loading image and execute an A4J request
                    onSubmit.append("else{").
                            append("document.getElementById('").append(formId).append(":").append(treepanel.getId()).append("_img_").append(node.getId()).append("').").
                            append("setAttribute('style','background-image:url(").append(ResourcePhaseListener.getURL(context, NODE_LOADING, null)).append(");');").
                            append("}");

                    StringBuilder onComplete = new StringBuilder("expandSymbol('").append(formId).append("','").append(treepanelId).append("',").append("'").append(node.getId()).append("');");

                    //@TODO find a way to know if the tree is drag'n drop or not
                    boolean dnd = false;

                    if (dnd) {
                        onComplete.append(" refreshDnd();");
                    }
                    ImgNodeRepLink.setOncomplete(onComplete.toString());
                }
                ImgNodeRepLink.setOnclick(onSubmit.toString());
                ImgNodeRepLink.getChildren().add(ImgNodeRep);
                children.add(ImgNodeRepLink);
//TODO these lines must be used for working the loadAll attribute
//                ImgNodeRepLink.setOnsubmit(onSubmit.toString());
//                ImgNodeRep.getFacets().put("a4jsupport", ImgNodeRepLink);
//                children.add(ImgNodeRep);
            } else {
                children.add(ImgNodeRep);
            }



            // 3 : Add UIDiv TreeItem icon  (default is folder or leaf from tree.png)

            final UIDiv ImgNodeIcon = new UIDiv();

            //setting a custom image icon if the attached treeItem have an icon specified,
            //otherwise the default treetable icons will be used.
            String styleImg = "";
            if (node.getUserObject() instanceof TreeItem) {
                TreeItem ti = (TreeItem) node.getUserObject();
                if (ti.getIcon() != null && !ti.getIcon().equals("")) {
                    styleImg = "background:url('" + ti.getIcon() + "');";
                }
            }
            if (styleImg != null && !styleImg.equals("")) {
                ImgNodeIcon.setStyle(styleImg);
            }
            ImgNodeIcon.setStyleClass(CLASS_NODE_ICON + PIXEL);
            if (FolderType) {
                ImgNodeIcon.setId(treepanel.getId() + "_img_" + node.getId());
            }

            children.add(ImgNodeIcon);


            // 4 : Add HtmlOutputText containing the name of the current treeItem

            final Object value = component.getAttributes().get("value");
            final String valueToWrite;
            if (value != null) {
                if (value.getClass().toString().contains("java.lang.String")) {
                    ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), (String) value, java.lang.Object.class);
                    valueToWrite = ((String) ve.getValue(context.getELContext()));
                } else {
                    valueToWrite = ((String) value);
                }
            } else {
                valueToWrite = (node.getText());
            }
            final HtmlOutputText LinkNodeText = new HtmlOutputText();
            LinkNodeText.setValue(valueToWrite);
            LinkNodeText.setId(treepanel.getId() + "_anchor_" + node.getId());
            LinkNodeText.setStyle("margin-left:3px;");


            children.add(LinkNodeText);


            // 5 : Add HtmlGraphicImage displaying on click the TreeItem info if it exists

            final HtmlGraphicImage ImgTreeNodeInfo = new HtmlGraphicImage();
            UITreeNodeInfoBase treenodeInfoComp = null;
            boolean treenodeinfo = false;
            for (final UIComponent comp : treeline.getChildren()) {
                if (comp instanceof UITreeNodeInfoBase) {
                    treenodeinfo = true;
                    treenodeInfoComp = (UITreeNodeInfoBase) comp;

                }
            }
            ImgTreeNodeInfo.setId(treepanel.getId() + "_anchor_info_" + node.getId());
            if (treenodeinfo) {
                if (treenodeInfoComp.getTitle() != null) {
                    ImgTreeNodeInfo.setTitle(treenodeInfoComp.getTitle());
                } else {
                    ImgTreeNodeInfo.setTitle(SHOW_MORE_INFORMATION_TITLE);
                }
            }

            ImgTreeNodeInfo.setOnclick("showInfo('" + treepanel.getClientId(context) + "','" + node.getId() + "');");
            ImgTreeNodeInfo.setStyleClass(CLASS_ANCHOR_INFO + PIXEL);
            ImgTreeNodeInfo.setStyle("margin-left:5px;");
            //ImgTreeNodeInfo.setUrl(NODE_IDENT);

            if (treenodeinfo) {
                children.add(component.getChildCount(), ImgTreeNodeInfo);
            }
            ((UITreeColumnBase) component).setAlreadyRender(true);
        }

        if (debug) {
            LOGGER.info("afterEncodeBegin : " + AbstractTreeColumnRenderer.class.getName());
        }

        afterEncodeBegin(context, component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        if (debug) {
            LOGGER.info("encodeChildren : " + AbstractTreeColumnRenderer.class.getName());
        }

        final UITreeLinesBase treeline = (UITreeLinesBase) component.getParent();
        final TreeNodeModel node = treeline.getNodeInstance();
        final ResponseWriter writer = context.getResponseWriter();

        for (final UIComponent tmp : component.getChildren()) {
            Utils.encodeRecursive(context, tmp);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        final String treepanelId = Utils.getWrappedComponentId(context, component, UITreePanelBase.class);
        final UITreePanelBase treepanel = (UITreePanelBase) Utils.findComponent(context, treepanelId);
        final UITreeColumnBase treecolumn = (UITreeColumnBase) component;
        final UITreeLinesBase treeline = (UITreeLinesBase) component.getParent();
        final TreeNodeModel node = treeline.getNodeInstance();

        if (debug) {
            LOGGER.info("beforeEncodeEnd : " + AbstractTreeColumnRenderer.class.getName());
        }

        beforeEncodeEnd(context, component);

        if (debug) {
            LOGGER.info("encodeEnd : " + AbstractTreeColumnRenderer.class.getName());
        }

        writer.endElement("div");

        if (debug) {
            LOGGER.info("afterEncodeEnd : " + AbstractTreeColumnRenderer.class.getName());
        }

        afterEncodeEnd(context, component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decode(FacesContext context, UIComponent component) {
        return;
    }

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        }
        if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleAjaxRequest(final FacesContext context, final UIComponent component) {
        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        final String nodeId = request.getParameter(AjaxUtils.AJAX_NODE_ID_KEY);
        final UITreeLinesBase treeline = (UITreeLinesBase) Utils.findComponentById(context, context.getViewRoot(), "line_" + nodeId);
        final StringBuilder sb = new StringBuilder("");
        final HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

        response.setContentType("text/xml;charset=UTF-8");
        // need to set no cache or IE will not make future requests when same URL used.
        response.setHeader("Pragma", "No-Cache");
        response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
        response.setDateHeader("Expires", 1);
        sb.append("");
        sb.append("<response>");
        sb.append("OK");
        sb.append("</response>");

        try {
            response.getWriter().write(sb.toString());
        } catch (IOException iox) {
            iox.printStackTrace();
        } finally {
            try {
                response.getWriter().flush();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void A4JPostRequest(final FacesContext context, final UIComponent component) {
        final UITreeLinesBase treeline = (UITreeLinesBase) component.getParent();
        final String treepanelId = Utils.getWrappedComponentId(context, treeline, UITreePanelBase.class);
        final UITreePanelBase treepanel = (UITreePanelBase) Utils.findComponent(context, treepanelId);
        final String formId = Utils.getWrappedComponentId(context, component, UIForm.class);
        final TreeNodeModel node = treeline.getNodeInstance();

        for (int i = 0, n = node.getChildCount(); i < n; i++) {
            final TreeNodeModel nodeTemp = (TreeNodeModel) node.getChildAt(i);
            final String Line2Modify = treepanelId + "_line_" + nodeTemp.getId();
            final UITreeLinesBase treeline2change = (UITreeLinesBase) Utils.findComponent(context, Line2Modify);
            if (treeline2change != null) {
                treeline2change.getNodeInstance().setChecked(true);
                treeline2change.setRendered(true);
                treeline2change.setToRender(true);
            }
        }

    }
}
