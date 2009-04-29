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
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.faces.component.UIForm;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputLink;
import javax.faces.component.html.HtmlOutputText;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;

import org.mapfaces.component.abstractTree.UITreeColumnBase;
import org.mapfaces.component.abstractTree.UITreeLinesBase;
import org.mapfaces.component.abstractTree.UITreeNodeInfoBase;
import org.mapfaces.component.abstractTree.UITreePanelBase;
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

    private static final transient Log log = LogFactory.getLog(AbstractTreeColumnRenderer.class);
    private static final String DEFAULT_SIZE_COLUMN = "250";
    private static final String DEFAULT_HEADER_COLUMN = "Tree";
    private static final String NODE_IDENT = "/resource.jsf?r=/org/mapfaces/resources/tree/images/default/s.gif";
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

    private String class_symbol = "x-tree-ec-icon x-tree-elbow-end-minus";
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

        if (!component.isRendered()) return;

        assertValid(context, component);

        if (component.getAttributes().get("debug") != null) {
            debug = (Boolean) component.getAttributes().get("debug");
        }

        if (debug) log.info("beforeEncodeBegin : " + AbstractTreeColumnRenderer.class.getName());

        beforeEncodeBegin(context, component);

        //Start encoding
        if (debug) log.info("encodeBegin : " + AbstractTreeColumnRenderer.class.getName());

        final String treepanelId            = Utils.getWrappedComponentId(context, component, UITreePanelBase.class);
        final UITreePanelBase treepanel     = (UITreePanelBase) Utils.findComponent(context, treepanelId);
        final ResponseWriter writer         = context.getResponseWriter();
        final UITreeColumnBase treecolumn   = (UITreeColumnBase) component;
        final UITreeLinesBase treeline      = (UITreeLinesBase) component.getParent();
        final TreeNodeModel node            = treeline.getNodeInstance();
        final HttpServletRequest request    = (HttpServletRequest) context.getExternalContext().getRequest();
        final AjaxUtils ajaxtools           = new AjaxUtils();

        ajaxtools.addAjaxParameter(AjaxUtils.AJAX_REQUEST_PARAM_KEY, "true");
        ajaxtools.addAjaxParameter(AjaxUtils.AJAX_RENDERCHILD_ID_KEY, "true");
        ajaxtools.addAjaxParameter(AjaxUtils.AJAX_CONTAINER_ID_KEY, component.getId());
        ajaxtools.addAjaxParameter(AjaxUtils.AJAX_NODE_ID_KEY, String.valueOf(node.getId()));
        ajaxtools.addAjaxParameter("javax.faces.ViewState", "'+viewstate+'");

        //Get width of the column
        final Object prop        = component.getAttributes().get("width");
        final String size        = (prop != null) ? String.valueOf(prop) : DEFAULT_SIZE_COLUMN;
        final boolean FolderType = !node.isLeaf() ;

        if (!treepanel.isLoadAll()) {
            class_symbol = "x-tree-ec-icon x-tree-elbow-end-plus";
            class_node_div = "x-tree-node-el x-tree-node-collapsed x-tree-col";
        }

        String styleUser = "";
        if (treecolumn.getStyle() != null) {
            styleUser = treecolumn.getStyle();
        }

        final int indentStyle;
        if (!treepanel.isShowRoot()) {
            indentStyle = (node.getDepth() - 2) * 12;
        } else {
            indentStyle = (node.getDepth() - 1) * 12;
        }

        final int width = Integer.valueOf(size) - indentStyle;
        writer.startElement("div", component);
        writer.writeAttribute("id", "treenode:" + treepanelId + ":" + node.getId(), null);
        writer.writeAttribute("style", "text-align:left; width:" + width + "px; padding-left :" + indentStyle + "px;" + styleUser, null);
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
        }else{
            if (FolderType) {
                writer.writeAttribute("class", class_node_div + classUser, null);
//            writer.writeAttribute("onclick", "viewstate = document.getElementById('javax.faces.ViewState').value; display(" + node.getId() + ",'get','" + AJAX_SERVER + "','" + AJAX_PARAMETERS + "', viewstate);", null);
            } else {
                writer.writeAttribute("class", CLASS_LEAF_DIV + classUser, null);
            }
        }


        if (!((UITreeColumnBase) component).isAlreadyRender()) {

            final HtmlOutputLabel NodeIdent = new HtmlOutputLabel();
            NodeIdent.setStyleClass(CLASS_NODE_INDENT);

            final HtmlGraphicImage ImgNodeIdent = new HtmlGraphicImage();
            ImgNodeIdent.setUrl(NODE_IDENT);
            ImgNodeIdent.setStyleClass(CLASS_ICON);

            // Node representation
            final HtmlGraphicImage ImgNodeRep = new HtmlGraphicImage();
            ImgNodeRep.setUrl(NODE_IDENT);
            ImgNodeRep.setAlt("");

            if (FolderType) {
                ImgNodeRep.setId(treepanel.getId() + "_symbol_" + node.getId());
                ImgNodeRep.setStyleClass(class_symbol);
            } else {
                if (((DefaultMutableTreeNode) node.getParent()).getLastLeaf() == node) {
                    ImgNodeRep.setStyleClass(CLASS_LEAF_ELBOW_END);
                } else {
                    ImgNodeRep.setStyleClass(CLASS_LEAF_ELBOW);
                }
            }

            final HtmlGraphicImage ImgNodeIcon = new HtmlGraphicImage();
            ImgNodeIcon.setUrl(NODE_IDENT);
            ImgNodeIcon.setAlt("");
            ImgNodeIcon.setStyleClass(CLASS_NODE_ICON);
            if (FolderType) {
                ImgNodeIcon.setId(treepanel.getId() + "_img_" + node.getId());
            }

            //WRITING NODE NAME
            final HtmlOutputLink LinkNode = new HtmlOutputLink();
            LinkNode.setId(treepanel.getId() + "_anchor_" + node.getId());
            LinkNode.setStyleClass(CLASS_ANCHOR);
            LinkNode.setStyle("margin-top:2px;");
            LinkNode.setValue("#");
            LinkNode.setTabindex("1");

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
            final HtmlOutputLabel LinkNodeLabel = new HtmlOutputLabel();
            final HtmlOutputText LinkNodeText = new HtmlOutputText();
            LinkNodeText.setValue(valueToWrite);
            LinkNode.getChildren().add(LinkNodeText);
            LinkNode.setStyle("position:relative;width:auto;white-space:normal;");

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
            ImgTreeNodeInfo.setStyleClass(CLASS_ANCHOR_INFO);
            ImgTreeNodeInfo.setStyle("margin-left:5px;");
            ImgTreeNodeInfo.setUrl(NODE_IDENT);

            final HtmlAjaxSupport AjaxSupport = new HtmlAjaxSupport();
            AjaxSupport.setId(treepanel.getId() + "_ajax_" + node.getId());
            AjaxSupport.setReRender(treeline.getParent().getClientId(context));
            AjaxSupport.setEvent("onclick");
            AjaxSupport.setAjaxSingle(true);
            AjaxSupport.setLimitToList(true);
            final String formId = Utils.getWrappedComponentId(context, component, UIForm.class);
            AjaxSupport.setOnsubmit("" +
                    "if(disp('" + formId + "','" + treepanelId + "','" + node.getId() + "')==false){" +
                    "   return false;" +
                    "}" +
                    "else {" +
                    "   document.getElementById('" + formId + ":" + treepanel.getId() + "_img_" + node.getId() + "')." +
                    "       setAttribute('style','background-image:url(" + ResourcePhaseListener.getURL(context, NODE_LOADING, null) + ");');" +
                    "}");


//                    "A4J.AJAX.Submit('','"+formId+"',null,{'affected':['"+treepanel.getClientId(context)+"'],'parameters':{'"+ajaxtools.getAJAX_REQUEST_PARAM_KEY()+"':'true'," +
//                    "'"+ajaxtools.getAJAX_RENDERCHILD_ID_KEY()+"':'true'," +
//                    "'"+ajaxtools.getAJAX_CONTAINER_ID_KEY()+"':'"+treeline.getClientId(context)+"'" +
//                    "},'actionUrl':'"+request.getRequestURI()+"'})" +

//                    "A4J.AJAX.SubmitRequest('','"+formId+"',null,{'parameters':{'"+ajaxtools.getAJAX_REQUEST_PARAM_KEY()+"':'true'," +
//                    "'"+ajaxtools.getAJAX_RENDERCHILD_ID_KEY()+"':'true'," +
//                    "'"+ajaxtools.getAJAX_CONTAINER_ID_KEY()+"':'"+treeline.getClientId(context)+"'" +
//                    "},'actionUrl':'"+request.getRequestURI()+"'})" +
//                    formId+".reset(); return false;" +

            AjaxSupport.setOncomplete("expandSymbol('" + formId + "'," +
                    "'" + treepanelId + "'," +
                    "'" + node.getId() + "'); refreshDnd();");

            //Adding Components to TreeColumn
            final List<UIComponent> children = component.getChildren();
            if (treepanel.isShowRoot() && node.getDepth() > 2) {
                children.add(ImgNodeIdent);
            }
            children.add(ImgNodeRep);
            children.add(ImgNodeIcon);
            children.add(LinkNode);
            if (treenodeinfo) {
                children.add(component.getChildCount(), ImgTreeNodeInfo);
            }
            ImgNodeRep.getChildren().add(AjaxSupport);
            ImgNodeRep.getFacets().put("a4jsupport_" + node.getId(), AjaxSupport);
            ((UITreeColumnBase) component).setAlreadyRender(true);
        }

        if (debug) log.info("afterEncodeBegin : " + AbstractTreeColumnRenderer.class.getName());

        afterEncodeBegin(context, component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        if (debug) log.info("encodeChildren : " + AbstractTreeColumnRenderer.class.getName());

        final UITreeLinesBase treeline = (UITreeLinesBase) component.getParent();
        final TreeNodeModel node       = treeline.getNodeInstance();
        final ResponseWriter writer    = context.getResponseWriter();

        for (final UIComponent tmp : component.getChildren()) {
            Utils.encodeRecursive(context, tmp);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        final ResponseWriter writer         = context.getResponseWriter();
        final String treepanelId            = Utils.getWrappedComponentId(context, component, UITreePanelBase.class);
        final UITreePanelBase treepanel     = (UITreePanelBase) Utils.findComponent(context, treepanelId);
        final UITreeColumnBase treecolumn   = (UITreeColumnBase) component;
        final UITreeLinesBase treeline      = (UITreeLinesBase) component.getParent();
        final TreeNodeModel node            = treeline.getNodeInstance();

        if (debug) log.info("beforeEncodeEnd : " + AbstractTreeColumnRenderer.class.getName());

        beforeEncodeEnd(context, component);

        if (debug) log.info("encodeEnd : " + AbstractTreeColumnRenderer.class.getName());

        writer.endElement("div");

        if (debug) log.info("afterEncodeEnd : " + AbstractTreeColumnRenderer.class.getName());

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
        if (context == null)   throw new NullPointerException("FacesContext should not be null");
        if (component == null) throw new NullPointerException("component should not be null");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleAjaxRequest(final FacesContext context, final UIComponent component) {
        final HttpServletRequest request   = (HttpServletRequest) context.getExternalContext().getRequest();
        final String nodeId                = request.getParameter(AjaxUtils.AJAX_NODE_ID_KEY);
        final UITreeLinesBase treeline     = (UITreeLinesBase) Utils.findComponentById(context, context.getViewRoot(), "line_" + nodeId);
        final StringBuffer sb              = new StringBuffer("");
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
            System.out.println("Response : " + sb.toString());
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
        final UITreeLinesBase treeline  = (UITreeLinesBase) component.getParent();
        final String treepanelId        = Utils.getWrappedComponentId(context, treeline, UITreePanelBase.class);
        final UITreePanelBase treepanel = (UITreePanelBase) Utils.findComponent(context, treepanelId);
        final String formId             = Utils.getWrappedComponentId(context, component, UIForm.class);
        final TreeNodeModel node        = treeline.getNodeInstance();

        for (int i=0,n=node.getChildCount(); i<n; i++) {
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
