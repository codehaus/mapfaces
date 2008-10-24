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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import java.util.List;
import javax.el.ValueExpression;
import javax.faces.component.UIForm;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputLink;
import javax.faces.component.html.HtmlOutputText;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.tree.DefaultMutableTreeNode;

import org.ajax4jsf.ajax.html.HtmlAjaxSupport;

import org.mapfaces.component.abstractTree.UITreeColumnBase;
import org.mapfaces.component.abstractTree.UITreeLinesBase;
import org.mapfaces.component.abstractTree.UITreeNodeInfoBase;
import org.mapfaces.component.abstractTree.UITreePanelBase;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.share.interfaces.A4JRendererInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.AjaxUtils;

/**
 *
 * @author kevindelfour
 */
public abstract class AbstractTreeColumnRenderer extends Renderer implements AjaxRendererInterface, A4JRendererInterface, CustomizeTreeComponentRenderer {

    /* Local Fields */
    private static final transient Log log = LogFactory.getLog(AbstractTreeColumnRenderer.class);
    private boolean debug;
    private static String DEFAULT_SIZE_COLUMN = "250";
    private static String DEFAULT_HEADER_COLUMN = "Tree";
    private static int LINES_SHOW = 1;
    private static int DEFAULT_FIRST_COLUMN_SIZE = 250;
    private static String NODE_IDENT = "/resource.jsf?r=/org/mapfaces/resources/tree/images/default/s.gif";
    private static String CLASS_NODE_DIV = "x-tree-node-el x-tree-node-expanded x-tree-col";
    private static String CLASS_NODE_INDENT = "x-tree-node-indent";
    private static String CLASS_NODE_LI = "x-tree-node x-tree-lines";
    private static String CLASS_LEAF_DIV = "x-tree-node-el x-tree-node-leaf x-tree-col";
    private static String CLASS_ICON = "x-tree-icon";
    private static String CLASS_SYMBOL = "x-tree-ec-icon x-tree-elbow-end-minus";
    private static String CLASS_NODE_ICON = "x-tree-node-icon";
    private static String CLASS_LEAF_ELBOW = "x-tree-ec-icon x-tree-elbow";
    private static String CLASS_LEAF_ELBOW_END = "x-tree-ec-icon x-tree-elbow-end";
    private static String CLASS_ANCHOR = "x-tree-node-anchor";
    private static String CLASS_ANCHOR_INFO = "x-tree-ec-icon x-tree-node-info-anchor x-tree-node-info-anchor-plus";
    private static String SHOW_MORE_INFORMATION_TITLE = "Show more informations";

    /**
     * This method returns the parent form of this element.
     * If this element is a form then it simply returns itself.
     * @param component - 
     * @return
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

    /**
     * 
     * @param component
     * @return
     */
    private String getPostbackFunctionName(UIComponent component) {
        UITreeColumnBase treecolumn = (UITreeColumnBase) component;
        return treecolumn.getId() + "PostBack";
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
            log.info("beforeEncodeBegin : " + AbstractTreeColumnRenderer.class.getName());
        }
        beforeEncodeBegin(context, component);

        //Start encoding
        if (debug) {
            log.info("encodeBegin : " + AbstractTreeColumnRenderer.class.getName());
        }
        String treepanelId = Utils.getWrappedComponentId(context, component, UITreePanelBase.class);
        UITreePanelBase treepanel = (UITreePanelBase) Utils.findComponent(context, treepanelId);
        ResponseWriter writer = context.getResponseWriter();
        UITreeColumnBase treecolumn = (UITreeColumnBase) component;
        UITreeLinesBase treeline = (UITreeLinesBase) component.getParent();
        TreeNodeModel node = treeline.getNodeInstance();

        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        AjaxUtils ajaxtools = new AjaxUtils();
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_REQUEST_PARAM_KEY(), "true");
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_RENDERCHILD_ID_KEY(), "true");
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_CONTAINER_ID_KEY(), component.getId());
        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_NODE_ID_KEY(), String.valueOf(node.getId()));
        ajaxtools.addAjaxParameter("javax.faces.ViewState", "'+viewstate+'");
        String AJAX_SERVER = ajaxtools.getAjaxServer(request);
        String AJAX_PARAMETERS = ajaxtools.getAjaxParameters();


        //Get width of the column
        String size = DEFAULT_SIZE_COLUMN;
        if (component.getAttributes().get("width") != null) {
            size = String.valueOf(component.getAttributes().get("width"));
        }

        Boolean FolderType = true;
        if (node.isLeaf()) {
            FolderType = false;
        }


        String styleUser = "";
        if (treecolumn.getStyle() != null) {
            styleUser = treecolumn.getStyle();
        }
        int indentStyle;
        if (!treepanel.isShowRoot()) {
            indentStyle = (node.getDepth() - 2) * 12;
        } else {
            indentStyle = (node.getDepth() - 1) * 12;
        }
        int width = Integer.valueOf(size) - indentStyle;
        writer.startElement("div", component);
        writer.writeAttribute("id", "treenode:" + treepanelId + ":" + node.getId(), null);
        writer.writeAttribute("style", "width:" + width + "px; padding-left :" + indentStyle + "px;" + styleUser, null);
        String classUser = "";
        if (treecolumn.getStyleClass() != null) {
            classUser = treecolumn.getStyleClass();
        }
        if (FolderType) {
            writer.writeAttribute("class", CLASS_NODE_DIV + " " + classUser, null);
//            writer.writeAttribute("onclick", "viewstate = document.getElementById('javax.faces.ViewState').value; display(" + node.getId() + ",'get','" + AJAX_SERVER + "','" + AJAX_PARAMETERS + "', viewstate);", null);
        } else {
            writer.writeAttribute("class", CLASS_LEAF_DIV + " " + classUser, null);
        }


        if (!((UITreeColumnBase) component).isAlreadyRender()) {

            HtmlOutputLabel NodeIdent = new HtmlOutputLabel();
            NodeIdent.setStyleClass(CLASS_NODE_INDENT);

            HtmlGraphicImage ImgNodeIdent = new HtmlGraphicImage();
            ImgNodeIdent.setUrl(NODE_IDENT);
            ImgNodeIdent.setStyleClass(CLASS_ICON);


            // Node representation
            HtmlGraphicImage ImgNodeRep = new HtmlGraphicImage();
            ImgNodeRep.setUrl(NODE_IDENT);

            if (FolderType) {
                ImgNodeRep.setId(treepanel.getId() + "_symbol_" + node.getId());
                ImgNodeRep.setStyleClass(CLASS_SYMBOL);
            } else {
                if (((DefaultMutableTreeNode) node.getParent()).getLastLeaf() == node) {
                    ImgNodeRep.setStyleClass(CLASS_LEAF_ELBOW_END);
                } else {
                    ImgNodeRep.setStyleClass(CLASS_LEAF_ELBOW);
                }
            }

            HtmlGraphicImage ImgNodeIcon = new HtmlGraphicImage();
            ImgNodeIcon.setUrl(NODE_IDENT);
            ImgNodeIcon.setStyleClass(CLASS_NODE_ICON);
            if (FolderType) {
                ImgNodeIcon.setId(treepanel.getId() + "_img_" + node.getId());
            }

            //WRITING NODE NAME
            HtmlOutputLink LinkNode = new HtmlOutputLink();
            LinkNode.setId(treepanel.getId() + "_anchor_" + node.getId());
            LinkNode.setStyleClass(CLASS_ANCHOR);
            LinkNode.setStyle("margin-top:2px;");
            LinkNode.setValue("#");
            LinkNode.setTabindex("1");

            Object value = component.getAttributes().get("value");
            String valueToWrite = "";
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
            HtmlOutputLabel LinkNodeLabel = new HtmlOutputLabel();
            HtmlOutputText LinkNodeText = new HtmlOutputText();
            LinkNodeText.setValue(valueToWrite);
            LinkNode.getChildren().add(LinkNodeText);
            LinkNode.setStyle("position:relative;width:auto;white-space:normal;");

            HtmlGraphicImage ImgTreeNodeInfo = new HtmlGraphicImage();
            UITreeNodeInfoBase treenodeInfoComp = null;
            List<UIComponent> components = treeline.getChildren();
            Boolean treenodeinfo = false;
            for (UIComponent comp : components) {
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

            HtmlAjaxSupport AjaxSupport = new HtmlAjaxSupport();
            AjaxSupport.setId(treepanel.getId() + "_ajax_" + node.getId());
            AjaxSupport.setReRender(treeline.getParent().getClientId(context));
            AjaxSupport.setEvent("onclick");
            AjaxSupport.setAjaxSingle(true);
            AjaxSupport.setLimitToList(true);
            String formId = Utils.getWrappedComponentId(context, component, UIForm.class);
            AjaxSupport.setOnsubmit("" +
                    "if(disp('" + formId + "','" + treepanelId + "','" + node.getId() + "')==false){" +
                    "   return false;" +
                    "}" +
                    "else {" +
                    "   console.log('try to change loading');" +
                    "   document.getElementById('"+formId+":"+treepanel.getId() + "_img_" + node.getId()+"').setAttribute('class','x-tree-node-loading x-tree-node-icon');" +
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

//            AjaxSupport.setOncomplete("disp('"+formId+"'," +
//                    "'"+treepanelId+"'," +
//                    "'"+node.getId()+"'," +
//                    "'"+request.getRequestURI()+"');");

            //Adding Components to TreeColumn
            if (treepanel.isShowRoot() && node.getDepth() > 1) {
                component.getChildren().add(ImgNodeIdent);
            }
            component.getChildren().add(ImgNodeRep);
            component.getChildren().add(ImgNodeIcon);
            component.getChildren().add(LinkNode);
            if (treenodeinfo) {
                component.getChildren().add(component.getChildCount(), ImgTreeNodeInfo);
            }
            ImgNodeRep.getChildren().add(AjaxSupport);
            ImgNodeRep.getFacets().put("a4jsupport_" + node.getId(), AjaxSupport);
            ((UITreeColumnBase) component).setAlreadyRender(true);
        }

        if (debug) {
            log.info("afterEncodeBegin : " + AbstractTreeColumnRenderer.class.getName());
        }
        afterEncodeBegin(context, component);

    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        if (debug) {
            log.info("encodeChildren : " + AbstractTreeColumnRenderer.class.getName());
        }

        UITreeLinesBase treeline = (UITreeLinesBase) component.getParent();
        TreeNodeModel node = treeline.getNodeInstance();
        ResponseWriter writer = context.getResponseWriter();

        if (component.getChildCount() != 0) {
            List<UIComponent> children = component.getChildren();
            for (UIComponent tmp : children) {
                Utils.encodeRecursive(context, tmp);
            }
        }

    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        if (debug) {
            log.info("beforeEncodeEnd : " + AbstractTreeColumnRenderer.class.getName());
        }
        beforeEncodeEnd(context, component);

        if (debug) {
            log.info("encodeEnd : " + AbstractTreeColumnRenderer.class.getName());
        }
        writer.endElement("div");

        if (debug) {
            log.info("afterEncodeEnd : " + AbstractTreeColumnRenderer.class.getName());
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
        AjaxUtils ajaxtools = new AjaxUtils();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String nodeId = request.getParameter(ajaxtools.getAJAX_NODE_ID_KEY());
        UITreeLinesBase treeline = (UITreeLinesBase) Utils.findComponentById(context, context.getViewRoot(), "line_" + nodeId);

//        List<UIComponent> children = treeline.getChildren();
//        for (UIComponent child : children) {
//            if (child instanceof UIAbstractTreeLines) {
//                ((UIAbstractTreeLines) child).setToRender(true);
//                child.setRendered(true);
//            }
//        }

        StringBuffer sb = new StringBuffer();
        sb.append("");

        HttpServletResponse response = null;
        try {
            response = (HttpServletResponse) context.getExternalContext().getResponse();

            response.setContentType("text/xml;charset=UTF-8");
            // need to set no cache or IE will not make future requests when same URL used.
            response.setHeader("Pragma", "No-Cache");
            response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
            response.setDateHeader("Expires", 1);
            sb.append("");
            sb.append("<response>");
            sb.append("OK");
            sb.append("</response>");
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

    @Override
    public void A4JPostRequest(FacesContext context, UIComponent component) {
    }
}