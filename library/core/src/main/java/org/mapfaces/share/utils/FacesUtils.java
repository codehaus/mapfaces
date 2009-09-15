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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FactoryFinder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.PhaseListener;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mapfaces.component.models.UIModelBase;

/**
 *
 * @author kevindelfour
 */
public class FacesUtils {
    private static final Logger LOGGER = Logger.getLogger(FacesUtils.class.getName());

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

    /**
     * Returns the UIModelBase of the mapfaces component.
     * @param context
     * @param comp
     * @return
     */
    public static UIModelBase getParentUIModelBase(FacesContext context, UIComponent component) {
        UIComponent parent = component;
        while (!(parent instanceof UIModelBase)) {
            if (parent != null) {
                parent = parent.getParent();
            } else {
                return null;
            }
        }
        return (UIModelBase) parent;
    }

    /**
     * Returns PrintWriter from facesContext instance.
     * @param fc
     * @return
     */
    public static PrintWriter getResponseWriter(FacesContext fc) {
        PrintWriter writer = null;
        try {
            writer = getResponse(fc).getWriter();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Can't get the response writer !!!", ex);
        }
        return writer;
    }

    /**
     * Returns HttpServletResponse from facesContext
     * @param fc
     * @return
     */
    public static HttpServletResponse getResponse(FacesContext fc) {
        return (HttpServletResponse) fc.getExternalContext().getResponse();
    }

    /**
     * Returns a request parameter value located in the RequestParameterMap.
     * @param fc
     * @param name
     * @return
     */
    public static String getRequestParam(FacesContext fc, String name) {
        return (String) fc.getExternalContext().getRequestParameterMap().get(name);
    }

    /**
     * Returns the first UIForm parent of a component.
     * @param component
     * @return
     */
    public static UIForm findForm(UIComponent component) {
        return (UIForm) findParentComponentByClass(component, UIForm.class);
    }

    /**
     * Returns a prent component which match with class type.
     * @param component
     * @param c
     * @return
     */
    public static UIComponent findParentComponentByClass(final UIComponent component, final Class c) {
        UIComponent parent = component;
        while (!(c.isInstance(parent))) {
            parent = parent.getParent();
        }
        return parent;
    }

    /**
     * <p>Get container form id of the UIComponent</p>
     * @param component UIComponent to be rendered
     * @return UIForm the form container of the component if exist else return null
     */
    public static String getFormId(FacesContext context, UIComponent component) {
        return findForm(component).getId();
    }

    /**
     * Returns the form client id that wraps the component.
     * @param context
     * @param component
     * @return
     */
    public static String getFormClientId(FacesContext context, UIComponent component) {
        return findForm(component).getClientId(context);
    }

    /**
     * Find and returns a component by client id from the viewRoot of the faces context.
     * @param faceContext
     * @param clientId
     * @return
     */
    public static UIComponent findComponentByClientId(FacesContext faceContext, String clientId) {
        return findComponentByClientId(faceContext, faceContext.getViewRoot(), clientId);
    }

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
     * Returns parent component id, the parent must match Class type.
     * @param component
     * @param c
     * @return
     */
    public static String getParentComponentIdByClass(final UIComponent component, final Class c) {
        return findParentComponentByClass(component, c).getId();
    }

    /**
     * Returns parent component clientId, the parent must match Class type.
     * @param faceContext
     * @param component
     * @param c
     * @return
     */
    public static String getParentComponentClientIdByClass(final FacesContext faceContext,
            final UIComponent component, final Class c) {
        return findParentComponentByClass(component, c).getClientId(faceContext);
    }

    /**
     * Returns RenderKit from facescontext viewRoot.
     * @param context
     * @return
     */
    public static RenderKit getRenderKit(final FacesContext context) {
        String renderKitId = context.getViewRoot().getRenderKitId();
        renderKitId = (null != renderKitId) ? renderKitId : RenderKitFactory.HTML_BASIC_RENDER_KIT;
        final RenderKitFactory fact = (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
        assert null != fact;
        return fact.getRenderKit(context, renderKitId);
    }

    /**
     * Returns html ResponseWriter.
     * @param context
     * @return
     * @throws java.io.IOException
     */
    public static ResponseWriter getResponseWriter2(FacesContext context) throws IOException {
        ResponseWriter curWriter = context.getResponseWriter();
        if (null == curWriter) {
            final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            final HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            final RenderKitFactory rkf = (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
            final RenderKit renderKit = rkf.getRenderKit(context,
                    context.getViewRoot().getRenderKitId());

            curWriter = renderKit.createResponseWriter(response.getWriter(),
                    "text/html", request.getCharacterEncoding());

            context.setResponseWriter(curWriter);
        }
        return curWriter;
    }

    /**
     * Returns the parameter map of a component that contains all couple (name, value) of UIParameter children.
     * of a specific component.
     * @param component
     * @return the Map of the component.
     */
    public static Map<String, Object> getParameterMap(UIComponent component) {
        final Map result = new HashMap();
        for (final Iterator iter = component.getChildren().iterator(); iter.hasNext();) {
            final UIComponent child = (UIComponent) iter.next();
            if (child instanceof UIParameter) {
                final UIParameter uiparam = (UIParameter) child;
                final Object value = uiparam.getValue();
                if (value != null) {
                    result.put(uiparam.getName(), value);
                }
            }
        }
        return result;
    }

    /**
     * Returns the lifecylce identifier
     * @param context
     * @return the id of the life cycle.
     */
    public static String getLifecycleId(ServletContext context) {
        final String lifecycleId = context.getInitParameter(FacesServlet.LIFECYCLE_ID_ATTR);
        return lifecycleId != null ? lifecycleId
                : LifecycleFactory.DEFAULT_LIFECYCLE;
    }

    /**
     * Creates a new UIParameter component.
     * @param name
     * @param value
     * @return
     */
    public static UIParameter createFParam(final String name, final String value) {

        /* Add <f:param> component */
        final UIParameter fParam = new UIParameter();
        fParam.setName(name);
        fParam.setValue(value);
        return fParam;
    }

    /**
     * This method returns the current Session id, if context is null then {@code null} is returned.
     * @return String id
     */
    public static String getCurrentSessionId() {
        String result = null;
        final FacesContext context = FacesContext.getCurrentInstance();
        if (context != null) {
            final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            final HttpSession session = request.getSession();
            result = session.getId();
        }
        return result;
    }

    /**
     * This method returns the  current server informations ie:  Sun Java System Application Server or Apache Tomcat/6.0.13 ...
     * @return the server name.
     */
    public static String getServerInfoFromContext() {
        final FacesContext context = FacesContext.getCurrentInstance();
        if (context != null) {
            final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            final HttpSession session = request.getSession();
            return session.getServletContext().getServerInfo();
        }
        return null;
    }

    /**
     * This method puts at the sessionmap a couple key value.
     * @param context
     * @param key
     * @param value
     */
    public static void putAtSessionMap(FacesContext context, Object key, Object value) {
        Map sessionMap = context.getExternalContext().getSessionMap();
        sessionMap.put(key, value);

    }

    /**
     * Returns a clientId of the first HtmlAjaxRegion if exists, otherwise returns null.
     * @param context
     * @return
     */
    public static String findClientIdComponentClass(final FacesContext context, final UIComponent root, final Class cl) {
        String clientId = null;
        for (int i = 0; i < root.getChildCount() && clientId == null; i++) {
            final UIComponent child = (UIComponent) root.getChildren().get(i);
            clientId = findClientIdComponentClass(context, child, cl);
        }

        if (clientId == null && cl.isInstance(root)) {
            clientId = root.getClientId(context);
        }
        return clientId;
    }

    public static String getJsVariableFromClientId(String clientId) {
        String jsVariable = clientId;
        if (jsVariable.contains(":")) {
            jsVariable = clientId.replace(":", "");
        }
        return jsVariable;
    }
    /**
     * Removes all children components of the UIComponent passed in args from the contextViewRoot.
     * @param context
     * @param component
     */
    public static void removeChildren(final FacesContext context, final UIComponent component) {
        final List<UIComponent> children = component.getChildren();
        for (int i = children.size() - 1; i >= 0; i--) {
            children.remove(i);
        }
    }

}
