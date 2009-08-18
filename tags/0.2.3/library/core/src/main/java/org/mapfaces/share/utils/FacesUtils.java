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
import java.util.Iterator;
import java.util.Locale;
import javax.faces.FactoryFinder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseListener;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author kevindelfour
 */
public class FacesUtils {

    /**
     * Returns a component referenced by his id.
     * @param context
     * @param root
     * @param id
     * @return component referenced by id or null if not found
     */
    public static UIComponent findComponentById(final FacesContext context,
            final UIComponent root, final String id) {
        UIComponent component = null;
        for (int i = 0; i < root.getChildCount() && component == null; i++) {
            final UIComponent child = (UIComponent) root.getChildren().get(i);
            component = findComponentById(context, child, id);
        }
        if (root.getId() != null) {
            if (component == null && root.getId().equals(id)) {
                component = root;
            }
        }
        return component;
    }

    /**
     * Returns a component referenced by his clientId.
     *
     * @param context
     * @param root
     * @param clientId
     * @return component referenced by clientId or null if not found
     */
    public static UIComponent findComponentByClientId(final FacesContext context,
            final UIComponent root, final String clientId) {
        return root.findComponent(clientId);
    }

    /**
     * Returns a flag that indicates if the browser is Internet Explorer.
     * @param context
     * @return
     */
    public static boolean isIEBrowser(FacesContext context) {
        final HttpServletRequest servletReq = (HttpServletRequest) context.getExternalContext().getRequest();
        final String useragent = servletReq.getHeader("User-Agent");
        boolean isIE = false;
        if (useragent != null) {
            final String user = useragent.toLowerCase(Locale.getDefault());
            if (user.indexOf("msie") != -1) {
                isIE = true;
            }
        }
        return isIE;
    }

    /**
     * This method returns a PhaseListener which is an instance of Class<?> c passed in argument.
     * @param Class<?> c
     * @return PhaseListener
     */
    public static PhaseListener getListenerFromLifeCycle(Class<?> c) {
        //getting the DetectBrowserListener if is exists, else uses the FacesUtils method.
        final LifecycleFactory factory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
        final Lifecycle lifecycle = factory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
        final PhaseListener[] listeners = lifecycle.getPhaseListeners();
        for (int i = 0; i < listeners.length; i++) {
            final PhaseListener listener = listeners[i];
            if (c.isInstance(listener)) {
                return listener;
            }
        }
        return null;
    }

    /**
     * Returns the host url from the current container.
     * @return String
     */
    public static String getHostUrl() {
        //ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        final String url = request.getRequestURL().toString();
        final String uri = request.getRequestURI();
        return url.substring(0, url.indexOf(uri));
    }

        /**
     * This is a recursive method to encode all component's children.
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    public static void encodeRecursive(final FacesContext context,
            final UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        // Render this component and its children recursively
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
}
