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
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.http.HttpServletResponse;


import org.mapfaces.component.abstractTree.UIColumnBase;
import org.mapfaces.component.abstractTree.UITreeLinesBase;
import org.mapfaces.models.tree.TreeItem;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.util.treetable.TreeTableConfig;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.share.utils.WebContainerUtils;
import org.mapfaces.util.FacesMapUtils;

/**
 * @author Kevin Delfour.
 */
public abstract class AbstractColumnRenderer extends Renderer implements AjaxRendererInterface, CustomizeTreeComponentRenderer {

    private boolean debug;
    private static final Logger LOGGER = Logger.getLogger(AbstractColumnRenderer.class.getName());

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
        debug = (Boolean) component.getAttributes().get("debug");

        if (debug)
            LOGGER.info("beforeEncodeBegin : " + AbstractColumnRenderer.class.getName());

        beforeEncodeBegin(context, component);

        if (debug)
            LOGGER.info("encodeBegin : " + AbstractColumnRenderer.class.getName());

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

        writer.startElement("div", component);
        writer.writeAttribute("id",  component.getClientId(context), null);
        writer.writeAttribute("class", "x-tree-col " + classUser, null);
        writer.writeAttribute("style", "width:" + size + "; " + styleUser, null);

        if (debug) 
            LOGGER.info("afterEncodeBegin : " + AbstractColumnRenderer.class.getName());
        
        afterEncodeBegin(context, component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        if (component.getChildCount() != 0) {            
            for (final UIComponent tmp : component.getChildren()) {
                FacesMapUtils.encodeRecursive(context, tmp);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        beforeEncodeEnd(context, component);

        if (debug)
            LOGGER.info("beforeEncodeEnd : " + AbstractColumnRenderer.class.getName());

        final ResponseWriter writer     = context.getResponseWriter();
        writer.endElement("div");

        if (debug) 
            LOGGER.info("afterEncodeEnd : " + AbstractColumnRenderer.class.getName());

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
//        final UITreeLinesBase treeline   = (UITreeLinesBase) component.getParent();
//        final Object userObject          = treeline.getNodeInstance().getUserObject();
//        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//        final String new_value           = request.getParameter(AjaxUtils.AJAX_COMPONENT_VALUE_KEY);
//        final String[] targetNameSplit   = component.getId().split("_");
//        final String propName            = StringUtils.capitalize( targetNameSplit[targetNameSplit.length - 1] );
//        final Method method              = ReflectionUtils.lookupSetter(userObject.getClass(), propName);
//
//        boolean haveBeenResolved = false;
//
//        if (method != null) {
//            try {
//                method.invoke(userObject, new_value);
//                haveBeenResolved = true;
//            } catch (IllegalAccessException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//            } catch (IllegalArgumentException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//            } catch (InvocationTargetException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//            }
//        }
//
//        final HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//        response.setContentType("text/xml;charset=UTF-8");
//        // need to set no cache or IE will not make future requests when same URL used.
//        response.setHeader("Pragma", "No-Cache");
//        response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
//        response.setDateHeader("Expires", 1);
//
//        final StringBuilder sb = new StringBuilder();
//        sb.append("<response>");
//        sb.append((haveBeenResolved) ? "OK" : "FAILED");
//        sb.append("</response>");
//
//        try {
//            response.getWriter().write(sb.toString());
//        } catch (IOException iox) {
//            LOGGER.log(Level.SEVERE, "",iox);
//        }

    }

    /**
     * Function to  factorize the code of a column rendereer
     * TBD: this function should be deleted because it's never used
     */
    public Object createResponse(final FacesContext context, final boolean haveBeenResolved) throws IOException {
//        final HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//        final StringBuilder sb = new StringBuilder();
//        response.setContentType("text/xml;charset=UTF-8");
//        // need to set no cache or IE will not make future requests when same URL used.
//        response.setHeader("Pragma", "No-Cache");
//        response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
//        response.setDateHeader("Expires", 1);
//        sb.append("<response>");
//        sb.append( (haveBeenResolved)? "OK" : "FAILED");
//        sb.append("</response>");
//        response.getWriter().write(sb.toString());
//        LOGGER.log(Level.INFO, "Response : " +sb.toString());
        return context.getExternalContext().getResponse();
    }

    public String getVarId(final FacesContext context, final UIColumnBase comp) {
        final UITreeLinesBase parent = (UITreeLinesBase) comp.getParent();

        if (parent.getNodeInstance().isLeaf()) {
            String idresult = "";
            final Object obj = parent.getNodeInstance().getUserObject();
            if (obj instanceof TreeItem) {
                final TreeItem treeitem = (TreeItem) obj;
                idresult = treeitem.getCompId();
            }
            parent.setVarId(idresult);
            if (parent.getVarId() == null) {
                throw new NullPointerException("Var compId is null so we can't update the context doc");
            }
            return parent.getVarId();
        }

        return null;
    }

    /* ======================= ABSTRACT METHODS ==================================*/
    public abstract String addBeforeRequestScript(FacesContext context, UIComponent component) throws IOException;

    public abstract void addRequestScript(FacesContext context, UIComponent component, String event) throws IOException;

    public abstract String addAfterRequestScript(FacesContext context, UIComponent component) throws IOException;
}
