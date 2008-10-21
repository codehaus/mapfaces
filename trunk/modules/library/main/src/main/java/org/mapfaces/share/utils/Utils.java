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
package org.mapfaces.share.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.mapfaces.models.tree.TreeNodeModel;

/**
 * @author Mehdi Sidhoum.
 * @author Olivier Terral.
 * @author kdelfour
 */
public class Utils {

    /**
     * 
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    public static void encodeRecursive(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            Logger.getLogger(Utils.class.getName()).log(Level.INFO, component + " not rendered !");
            return;
        }
        component.encodeBegin(context);
        if (component.getRendersChildren()) {
            component.encodeChildren(context);
        } else {
            Iterator kids = component.getChildren().iterator();
            while (kids.hasNext()) {
                UIComponent kid = (UIComponent) kids.next();
                encodeRecursive(context, kid);
            }
        }
        component.encodeEnd(context);
    }

    /**
     * 
     * @param context
     * @param component
     * @param node
     * @throws java.io.IOException
     */
    public static void encodeRecursive(FacesContext context, UIComponent component, TreeNodeModel node) throws IOException {

        if (!component.isRendered()) {
            Logger.getLogger(Utils.class.getName()).log(Level.INFO, component + " not rendered !");
            return;
        }

        String id = component.getParent().getId() + "_" + node.getId();
        if (findComponentById(context, context.getViewRoot(), id) == null) {
            component.setId(id);
            component.encodeBegin(context);
            if (component.getRendersChildren()) {
                component.encodeChildren(context);
            } else {
                Iterator kids = component.getChildren().iterator();
                while (kids.hasNext()) {
                    UIComponent kid = (UIComponent) kids.next();
                    encodeRecursive(context, kid, node);
                }
            }

            component.encodeEnd(context);
        }
    }

    /**
     * 
     * @param faceContext
     * @return
     */
    public static PrintWriter getResponseWriter(FacesContext faceContext) {
        PrintWriter writer = null;
        try {
            writer = getResponse(faceContext).getWriter();
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
        return writer;
    }

    /**
     * 
     * @param faceContext
     * @param clientId
     * @return
     */
    public static UIComponent findComponent(FacesContext faceContext, String clientId) {
        return faceContext.getViewRoot().findComponent(clientId);
    }

    /**
     * Useful if you don't know the clientId
     * @param faceContext
     * @param root
     * @param id
     * @return component referenced by id or null if not found
     */
    public static UIComponent findComponentById(FacesContext faceContext, UIComponent root, String id) {
        UIComponent component = null;
        for (int i = 0; i < root.getChildCount() && component == null; i++) {
            UIComponent child = (UIComponent) root.getChildren().get(i);
            component = findComponentById(faceContext, child, id);
        }
        if (root.getId() != null) {
            if (component == null && root.getId().equals(id)) {
                component = root;
            }
        }
        return component;
    }

    /**
     * 
     * @param faceContext
     * @param name
     * @return
     */
    public static String getRequestParam(FacesContext faceContext, String name) {
        Map<String, String> requestParams = faceContext.getExternalContext().getRequestParameterMap();
        return (String) requestParams.get(name);
    }

    /**
     * 
     * @param faceContext
     * @return
     */
    public static HttpServletResponse getResponse(FacesContext faceContext) {
        return (HttpServletResponse) faceContext.getExternalContext().getResponse();
    }

    /**
     * 
     * @param faceContext
     * @param component
     * @return
     */
    public static String getFormId(FacesContext faceContext, UIComponent component) {
        UIComponent parent = component;
        while (!(parent instanceof UIForm)) {
            parent = parent.getParent();
        }
        return parent.getClientId(faceContext);
    }

    /**
     * 
     * @param faceContext
     * @param component
     * @param c
     * @return
     */
    public static String getWrappedComponentId(FacesContext faceContext, UIComponent component, Class c) {
        UIComponent parent = component;
        while (!(c.isInstance(parent))) {
            parent = parent.getParent();
        }
        return parent.getClientId(faceContext);
    }

    /**
     * 
     * @param faceContext
     * @param root
     * @return
     */
    public static UIComponent showComponent(FacesContext faceContext, UIComponent root) {
        UIComponent component = null;
        for (int i = 0; i < root.getChildCount() && component == null; i++) {
            UIComponent child = (UIComponent) root.getChildren().get(i);
            component = showComponent(faceContext, child);
        }
        return component;
    }

    /**
     * 
     * @param component
     */
    public static void showArborescence(UIComponent component) {
        System.out.println("COMP :" + component.getId());
        for (UIComponent tmp : component.getChildren()) {
            System.out.println(" + CHILD >" + tmp.getId());
            if (tmp.getChildCount() > 0) {
                showArborescence(tmp);
            }
        }
    }
}
