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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.mapfaces.models.Layer;
import org.mapfaces.models.tree.TreeNodeModel;

/**
 * @author Mehdi Sidhoum.
 * @author Olivier Terral.
 * @author Kevin Delfour
 */
public class Utils {

    public static void encodeRecursive(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        }
        component.encodeBegin(context);
        if (component.getRendersChildren()) {
            component.encodeChildren(context);
        } else {
            final Iterator kids = component.getChildren().iterator();
            while (kids.hasNext()) {
                final UIComponent kid = (UIComponent) kids.next();
                encodeRecursive(context, kid);
            }
        }
        component.encodeEnd(context);
    }

    public static void encodeRecursive(final FacesContext context, final UIComponent component, 
            final TreeNodeModel node) throws IOException {

        if (!component.isRendered()) {
            Logger.getLogger(Utils.class.getName()).log(Level.INFO, component + " not rendered !");
            return;
        }

        final String id = component.getParent().getId() + "_" + node.getId();
        if (findComponentById(context, context.getViewRoot(), id) == null) {
            component.setId(id);
            component.encodeBegin(context);
            if (component.getRendersChildren()) {
                component.encodeChildren(context);
            } else {
                final Iterator kids = component.getChildren().iterator();
                while (kids.hasNext()) {
                    UIComponent kid = (UIComponent) kids.next();
                    encodeRecursive(context, kid, node);
                }
            }

            component.encodeEnd(context);
        }
    }

    public static PrintWriter getResponseWriter(FacesContext faceContext) {
        PrintWriter writer = null;
        try {
            writer = getResponse(faceContext).getWriter();
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
        return writer;
    }

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

    public static String getRequestParam(FacesContext faceContext, String name) {
        Map<String, String> requestParams = faceContext.getExternalContext().getRequestParameterMap();
        return (String) requestParams.get(name);
    }

    public static HttpServletResponse getResponse(FacesContext faceContext) {
        return (HttpServletResponse) faceContext.getExternalContext().getResponse();
    }

    public static String getFormId(FacesContext faceContext, UIComponent component) {
        UIComponent parent = component;
        while (!(parent instanceof UIForm)) {
            parent = parent.getParent();
        }
        return parent.getClientId(faceContext);
    }

    public static String getWrappedComponentId(final FacesContext faceContext, 
            final UIComponent component, final Class c) {
        UIComponent parent = component;
        while (!(c.isInstance(parent))) {
            parent = parent.getParent();
        }
        return parent.getClientId(faceContext);
    }

    public static UIComponent showComponent(FacesContext faceContext, UIComponent root) {
        UIComponent component = null;
        for (int i = 0; i < root.getChildCount() && component == null; i++) {
            final UIComponent child = (UIComponent) root.getChildren().get(i);
            component = showComponent(faceContext, child);
        }
        return component;
    }

    public static void showArborescence(UIComponent component) {
        System.out.println("COMP :" + component.getId());
        for (final UIComponent tmp : component.getChildren()) {
            System.out.println(" + CHILD >" + tmp.getId());
            if (tmp.getChildCount() > 0) {
                showArborescence(tmp);
            }
        }
    }
    
    /**
     * This method return the count of layer WMS from a list, it is used to separate the mflayers and real wms layers.
     * @param list
     * @return
     */
    public static int getWMSLayerscount(List<Layer> list) {
        int result = 0;
        for (Layer layer : list) {
            if (layer != null && ! layer.getType().equals("mapfaces")) {
                result++;
            }
        }
        return result;
    }
}
