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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;

import org.mapfaces.component.abstractTree.UIColumnBase;
import org.mapfaces.component.abstractTree.UITreeLinesBase;
import org.mapfaces.component.abstractTree.UITreePanelBase;
import org.mapfaces.models.tree.TreeItem;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.util.AjaxUtils;
import org.mapfaces.util.treetable.TreeTableConfig;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.share.utils.Utils;

/**
 * @author Kevin Delfour.
 */
public abstract class AbstractColumnRenderer extends Renderer implements AjaxRendererInterface, CustomizeTreeComponentRenderer {

    private TreeTableConfig config = new TreeTableConfig();
    private boolean debug;
    private static final transient Log log = LogFactory.getLog(AbstractColumnRenderer.class);

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

    private String getPostbackFunctionName(UIComponent component) {
        UIColumnBase column = (UIColumnBase) component;
        return column.getId() + "PostBack";
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

        //Method to apply before encodeBegin
        final Boolean obj = (Boolean) component.getAttributes().get("debug");
        if(obj != null) debug = obj;

        if (debug) log.info("beforeEncodeBegin : " + AbstractColumnRenderer.class.getName());

        beforeEncodeBegin(context, component);

        //Start encodeBegin
        if (debug) log.info("encodeBegin : " + AbstractColumnRenderer.class.getName());

        final ResponseWriter writer     = context.getResponseWriter();
        final UITreeLinesBase treeline  = (UITreeLinesBase) component.getParent();
        final UIColumnBase column       = (UIColumnBase) component;
        final TreeNodeModel node        = treeline.getNodeInstance();

        String size = TreeTableConfig.DEFAULT_SIZE_COLUMN;
        final String width = (String) component.getAttributes().get("width");
        if ( width != null) size = String.valueOf(Integer.parseInt(width) - 2) + "px;";

        String styleUser = "";
        if (column.getStyle() != null) {
            styleUser = column.getStyle();
        }

        String classUser = "";
        if (column.getStyleClass() != null) {
            classUser = column.getStyleClass();
        }

        final String treepanelId            = Utils.getWrappedComponentId(context, component, UITreePanelBase.class);
        final UITreePanelBase treepanel     = (UITreePanelBase) Utils.findComponent(context, treepanelId);

        final int indentStyle;
        if (!treepanel.isShowRoot()) {
            indentStyle = (node.getDepth() - 2) * 12;
        } else {
            indentStyle = (node.getDepth() - 1) * 12;
        }

        writer.startElement("div", component);
        writer.writeAttribute("id", "treecol:" + component.getId() + ":" + node.getId(), null);
        writer.writeAttribute("class", "x-tree-col " + classUser, null);
        writer.writeAttribute("style", "width:" + size + "; margin-left: -"+indentStyle+"px;" + styleUser, null);

        //Method to apply before encodeBegin
        if (debug) {
            log.info("afterEncodeBegin : " + AbstractColumnRenderer.class.getName());
        }
        afterEncodeBegin(context, component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        final ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
        final ResponseWriter writer         = context.getResponseWriter();
        final UITreeLinesBase treeline      = (UITreeLinesBase) component.getParent();
        final TreeNodeModel node            = treeline.getNodeInstance();
        
        if (component.getChildCount() != 0) {
            for (final UIComponent tmp : component.getChildren()) {
                Utils.encodeRecursive(context, tmp);
            }
        }
//        else{
//            writer.startElement("div", component);
//            writer.writeAttribute("style", "height:1px;", null);
//            writer.endElement("div");
//        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        final UITreeLinesBase treeline  = (UITreeLinesBase) component.getParent();
        final TreeNodeModel node        = treeline.getNodeInstance();
        final ResponseWriter writer     = context.getResponseWriter();

        if (debug) log.info("beforeEncodeEnd : " + AbstractColumnRenderer.class.getName());

        beforeEncodeEnd(context, component);

        writer.endElement("div");

        if (debug) log.info("afterEncodeEnd : " + AbstractColumnRenderer.class.getName());

        afterEncodeEnd(context, component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        return;
    }

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null)   throw new NullPointerException("FacesContext should not be null");
        if (component == null) throw new NullPointerException("component should not be null");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleAjaxRequest(final FacesContext context, final UIComponent component) {
        final UITreeLinesBase treeline   = (UITreeLinesBase) component.getParent();
        final Object userObject          = treeline.getNodeInstance().getUserObject();
        final TreeNodeModel node         = treeline.getNodeInstance();
        final Object item                = node.getUserObject();
        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        final String new_value           = request.getParameter(AjaxUtils.AJAX_COMPONENT_VALUE_KEY);
        final String targetId            = request.getParameter(AjaxUtils.AJAX_TARGET_ID_KEY);
        final String[] targetNameSplit   = component.getId().split("_");
        final String targetProperty      = targetNameSplit[targetNameSplit.length - 1];
        final String propName            = StringUtils.capitalize(targetProperty.toString());
        final Method methode             = getMethod(userObject, propName);

        boolean haveBeenResolved = false;

        // Search in base class methods the getter correspond to the attribut
        if (methode != null) {
            try {
                methode.invoke(userObject, new_value);
                methode.invoke(item, new_value);
                treeline.getNodeInstance().setUserObject(userObject);
                haveBeenResolved = true;
            } catch (IllegalAccessException ex) {
                Logger.getLogger(AbstractColumnRenderer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(AbstractColumnRenderer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(AbstractColumnRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        HttpServletResponse response = null;
        try {
            response = (HttpServletResponse) context.getExternalContext().getResponse();
            StringBuffer sb = new StringBuffer();
            response.setContentType("text/xml;charset=UTF-8");
            // need to set no cache or IE will not make future requests when same URL used.
            response.setHeader("Pragma", "No-Cache");
            response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
            response.setDateHeader("Expires", 1);
            sb.append("<response>");
            if (haveBeenResolved) {
                sb.append("OK");
            } else {
                sb.append("FAILED");
            }
            sb.append("</response>");
            response.getWriter().write(sb.toString());
            System.out.println("Response : " + sb.toString());
        } catch (IOException iox) {
            iox.printStackTrace();
        }

    }

    /**
     * Function to  factorize the code of a column rendereer
     */
    public HttpServletResponse createResponse(final FacesContext context, final boolean haveBeenResolved) throws IOException {

        final HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        final StringBuilder sb = new StringBuilder();
        response.setContentType("text/xml;charset=UTF-8");
        // need to set no cache or IE will not make future requests when same URL used.
        response.setHeader("Pragma", "No-Cache");
        response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
        response.setDateHeader("Expires", 1);
        sb.append("<response>");
        sb.append( (haveBeenResolved)? "OK" : "FAILED");
        sb.append("</response>");
        response.getWriter().write(sb.toString());
        System.out.println("Response : " + sb.toString());
        return response;
    }

    /**
     * 	Research getter method corresponding to attribute property
     * @param base - The base object whose property value is to be returned, or null to resolve a top-level variable.
     * @param property - The property or variable to be resolved.
     * @return if the getter method exist, return this method else return null
     */
    public Method getMethod(final Object base, final Object property) {
        // Fisrt capitalize PropName
        final String propName = "set" + StringUtils.capitalize(property.toString());
        final Class Classe = base.getClass();
        // Search in base class methods the getter correspond to the attribut
        for (final Method method : Classe.getMethods()) {
            if (method.getName().equals(propName)) {
                return method;
            }
        }
        return null;
    }

//    public void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException {
//
//        ResponseWriter writer = context.getResponseWriter();
//        /*
//         * Prepare informations for making any Ajax request
//         */
//        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//        AjaxUtils ajaxtools = new AjaxUtils();
//        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_REQUEST_PARAM_KEY(), "true");
//        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_CONTAINER_ID_KEY(), component.getId());
//        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_COMPONENT_VALUE_KEY(), "'+value+'");
//        ajaxtools.addAjaxParameter(ajaxtools.getAJAX_TARGET_ID_KEY(), "'+target+'");
//        ajaxtools.addAjaxParameter("javax.faces.ViewState", "'+viewstate+'");
//        String AJAX_SERVER = ajaxtools.getAjaxServer(request);
//        String AJAX_PARAMETERS = ajaxtools.getAjaxParameters();
//        String Request = ajaxtools.getRequestJs("get", AJAX_SERVER, AJAX_PARAMETERS);

//        writer.startElement("script", component);
//        writer.write(
//                "document.getElementById('" + component.getClientId(context) + "').addEvent('" + event + "', function(event){" +
//                addBeforeRequestScript(context, component) +
//                "value = event.target.value;" +
//                "target = event.target.name;" +
//                "viewstate = document.getElementById('javax.faces.ViewState').value;" +
//                Request +
//                addAfterRequestScript(context, component) +
//                "});");
//        writer.endElement("script");
//    }
    public String getVarId(final FacesContext context, final UIColumnBase comp) {
        if (((UITreeLinesBase) (comp.getParent())).getNodeInstance().isLeaf()) {
            String idresult = "";
            final Object obj = ((UITreeLinesBase) (comp.getParent())).getNodeInstance().getUserObject();
            if (obj instanceof TreeItem) {
                final TreeItem treeitem = (TreeItem) obj;
                idresult = treeitem.getCompId();
            }
            ((UITreeLinesBase) (comp.getParent())).setVarId(idresult);
            if (((UITreeLinesBase) (comp.getParent())).getVarId() == null) {
                throw new NullPointerException("Var compId is null so we can't update the context doc");
            }
            return ((UITreeLinesBase) (comp.getParent())).getVarId();
        }
        return null;
    }

    /* ======================= ABSTRACT METHODS ==================================*/
    public abstract String addBeforeRequestScript(FacesContext context, UIComponent component) throws IOException;

    public abstract void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException;

    public abstract String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException;
}
