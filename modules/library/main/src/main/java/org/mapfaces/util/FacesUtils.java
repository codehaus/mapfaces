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
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.component.models.UIModelBase;
import org.mapfaces.component.treelayout.UITreeLines;

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
            }
            else {
                return null;
            }
        }
        return (UIModelBase)  parent;
    }
    /**
     * Returns the UIMapPane of the mapfaces component.
     * @param context
     * @param comp
     * @return
     */
    public static UIMapPane getParentUIMapPane(FacesContext context, UIComponent component) {
        UIComponent parent = component;
        while (!(parent instanceof UIMapPane)) {
            parent = parent.getParent();
        }
        return (UIMapPane)  parent;
    }
    /**
     * Returns the UITreeLines of the mapfaces component.
     * @param context
     * @param comp
     * @return
     */
    public static UITreeLines getParentUITreeLines(FacesContext context, UIComponent component) {
        UIComponent parent = component;
        while (!(parent instanceof  UITreeLines)) {
            parent = parent.getParent();
        }
        return ( UITreeLines)  parent;
    }
    /**
     * Returns the UIContext of the mapfaces component.
     * @param context
     * @param comp
     * @return
     */
    public static UIContext getParentUIContext(FacesContext context, UIComponent comp) {
        UIComponent parent = comp;
        while (!(parent instanceof UIContext)) {
            parent = parent.getParent();
        }
        return (UIContext) parent;
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
    public static Map<String, Object> getParameterMap(UIComponent component) {
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
    
    /**
     * Function to create a <a4j:support> component with extra parameters based on the "var" attribute of the treepanel component 
     * This function should used only on a child of treePanel component (see LayerControl component for details).
     * @param context           FacesContext  
     * @param comp              UIComponent Parent of the <a4j:support> component
     * @param event             String  Click Event (onclick,....)
     * @param varId             String  id of the bean defined in "var" attribute of the treepanel component
     * @param idsToReRender     String  id of components to reRender , if null the varId is set
     * @return  ajaxComp        the <a4j:support> component 
     */
     
    public static HtmlAjaxSupport createTreeAjaxSupport(FacesContext context, UIComponent comp, String event, String varId, String idsToReRender) {
        
        /* To use the synchronized parameter the reRender attribute must be null*/
        AjaxUtils ajaxUtils = new AjaxUtils();
        HashMap<String,String> extraParams = new HashMap<String, String>();
        extraParams.put(ajaxUtils.getAJAX_LAYER_ID(), varId);
        extraParams.put(ajaxUtils.getAJAX_CONTAINER_ID_KEY(), comp.getClientId(context));
        /*if we don't want to reRender another component than the "var" component */
        if(idsToReRender == null)
            idsToReRender = varId;
        return createExtraAjaxSupport(context, comp, event, idsToReRender, extraParams);
    }
    
    /**
     * Function to create a <a4j:support> component with extra parameters, where components would be refreshed synchronously
     * 
     * @param context           FacesContext  
     * @param comp              UIComponent Parent of the <a4j:support> component
     * @param event             String  Click Event (onclick,....)
     * @param idsToReRender     String  Id of components to refresh
     * @param extraParams       HashMap<String,String>  Extra param to add to the ajax request 
     * @return  ajaxComp        the <a4j:support> component 
     */
    public static HtmlAjaxSupport createSynchronizedAjaxSupport(FacesContext context, UIComponent comp, String event, String idsToReRender, HashMap<String,String> extraParams) {
        
        /* To use the synchronized parameter the reRender attribute must be null*/
        extraParams.put("synchronized", "true");
        extraParams.put("refresh", idsToReRender);
        idsToReRender = null;
        return createExtraAjaxSupport(context, comp, event, idsToReRender, extraParams);
    }
    
    /**
     * Function to create a <a4j:support> component with extra parameters
     * 
     * @param context           FacesContext  
     * @param comp              UIComponent Parent of the <a4j:support> component
     * @param event             String  Click Event (onclick,....)
     * @param idsToReRender     String  Id of components to refresh
     * @param extraParams       HashMap<String,String>  Extra param to add to the ajax request 
     * @return  ajaxComp        the <a4j:support> component 
     */
    public static HtmlAjaxSupport createExtraAjaxSupport(FacesContext context, UIComponent comp, String event, String idsToReRender, HashMap<String,String> extraParams) {

        /* Add <a4j:support> component */
        HtmlAjaxSupport ajaxComp = createBasicAjaxSupport(context, comp, event , idsToReRender);
        for(String tmp : extraParams.keySet()){            
             ajaxComp.getChildren().add(createFParam(tmp,extraParams.get(tmp)));
        }
        return ajaxComp;
    }
    
    /**
     * Function to create a classic <a4j:support> component 
     * 
     * @param context           FacesContext  
     * @param comp              UIComponent Parent of the <a4j:support> component
     * @param event             String  Click Event (onclick,....)
     * @param idsToReRender     String  Id of components to refresh
     * @param extraParams       HashMap<String,String>  Extra param to add to the ajax request 
     * @return  ajaxComp        the <a4j:support> component  
     */
    public static HtmlAjaxSupport createBasicAjaxSupport(FacesContext context, UIComponent comp, String event, String idsToReRender) {

        /* Add <a4j:support> component */
        HtmlAjaxSupport ajaxComp = new HtmlAjaxSupport();
        ajaxComp.setId(comp.getId() + "_Ajax");
        ajaxComp.setEvent(event);
        ajaxComp.setAjaxSingle(true);
        ajaxComp.setLimitToList(true);
        ajaxComp.setReRender(idsToReRender);
        return ajaxComp;
    }
    
    
    private static UIParameter createFParam(String name, String value) {

        /* Add <f:param> component */
        UIParameter fParam = new UIParameter();
        fParam.setName(name);
        fParam.setValue(value);
        return fParam;
    }
}
