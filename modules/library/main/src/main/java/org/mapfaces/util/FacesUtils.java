package org.mapfaces.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.faces.FactoryFinder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.lifecycle.LifecycleFactory;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mehdi Sidhoum.
 * @author Olivier Terral.
 */
public class FacesUtils {

    public static void encodeRecursive(FacesContext context, UIComponent component) throws IOException {
        if (!component.isRendered()) {
            return;
        // Render this component and its children recursively
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

    public static PrintWriter getResponseWriter(FacesContext fc) {
        PrintWriter writer = null;
        try {
            writer = getResponse(fc).getWriter();
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
        return writer;
    }

    public static UIComponent findComponent(FacesContext fc, String clientId) {
        return fc.getViewRoot().findComponent(clientId);
    }

    public static String getRequestParam(FacesContext fc, String name) {
        Map<String, String> requestParams = fc.getExternalContext().getRequestParameterMap();
        return (String) requestParams.get(name);
    }

    public static HttpServletResponse getResponse(FacesContext fc) {
        return (HttpServletResponse) fc.getExternalContext().getResponse();
    }

    public static String getFormId(FacesContext context, UIComponent component) {
        UIComponent parent = component;
        while (!(parent instanceof UIForm)) {
            parent = parent.getParent();
        }
        return parent.getClientId(context);
    }

    /**
     * Returns a component referenced by his clientId.
     *
     * @param context
     * @param root
     * @param clientId
     * @return component referenced by clientId or null if not found
     */
    public static UIComponent findComponentByClientId(FacesContext context,
            UIComponent root, String clientId) {
        UIComponent component = null;
        for (int i = 0; i < root.getChildCount() && component == null; i++) {
            UIComponent child = (UIComponent) root.getChildren().get(i);
            component = findComponentByClientId(context, child, clientId);
        }
        if (root.getId() != null) {
            if (component == null && root.getClientId(context).equals(clientId)) {
                component = root;
            }
        }
        return component;
    }

    /** 
     * Returns a component referenced by his id.
     * @param context
     * @param root
     * @param id
     * @return component referenced by id or null if not found
     */
    public static UIComponent findComponentById(FacesContext context, UIComponent root, String id) {
        UIComponent component = null;
        for (int i = 0; i < root.getChildCount() && component == null; i++) {
            UIComponent child = (UIComponent) root.getChildren().get(i);
            component = findComponentById(context, child, id);
        }
        if (root.getId() != null) {
            if (component == null && root.getId().equals(id)) {
                component = root;
            }
        }
        return component;
    }

    public static RenderKit getRenderKit(FacesContext context) {
        String renderKitId = context.getViewRoot().getRenderKitId();
        renderKitId = (null != renderKitId) ? renderKitId : RenderKitFactory.HTML_BASIC_RENDER_KIT;
        RenderKitFactory fact = (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
        assert (null != fact);

        RenderKit curRenderKit = fact.getRenderKit(context, renderKitId);
        return curRenderKit;
    }

    public static ResponseWriter getResponseWriter2(FacesContext context) throws IOException {
        ResponseWriter curWriter = context.getResponseWriter();
        if (null == curWriter) {
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            RenderKitFactory rkf = (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
            RenderKit renderKit = rkf.getRenderKit(context,
                    context.getViewRoot().getRenderKitId());

            curWriter = renderKit.createResponseWriter(response.getWriter(),
                    "text/html", request.getCharacterEncoding());

            context.setResponseWriter(curWriter);
        }
        return curWriter;
    }

    /**
     * The getParameterMap() is used for getting the parameters
     * of a specific component.
     * @param component
     * @return the Map of the component.
     */
    public static Map getParameterMap(UIComponent component) {
        Map result = new HashMap();
        for (Iterator iter = component.getChildren().iterator(); iter.hasNext();) {
            UIComponent child = (UIComponent) iter.next();
            if (child instanceof UIParameter) {
                UIParameter uiparam = (UIParameter) child;
                Object value = uiparam.getValue();
                if (value != null) {
                    result.put(uiparam.getName(), value);
                }
            }
        }
        return result;
    }

    /**
     * The getLifecycleId() is used for getting the id of 
     * the Lifecycle from the ServletContext.
     * @param context
     * @return the id of the life cycle.
     */
    public static String getLifecycleId(ServletContext context) {
        String lifecycleId = context.getInitParameter(FacesServlet.LIFECYCLE_ID_ATTR);
        return lifecycleId != null ? lifecycleId
                : LifecycleFactory.DEFAULT_LIFECYCLE;
    }
}
