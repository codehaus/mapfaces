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

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;

import java.awt.Color;
import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javax.swing.ImageIcon;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.ajax4jsf.ajax.html.HtmlAjaxSupport;

import org.mapfaces.component.UILayer;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.component.models.UIModelBase;
import org.mapfaces.component.timeline.UIHotZoneBandInfo;
import org.mapfaces.component.timeline.UITimeLine;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.component.layer.UIFeatureLayer;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.models.Context;
import org.mapfaces.models.DefaultFeature;
import org.mapfaces.models.Feature;
import org.mapfaces.models.Layer;
import org.mapfaces.models.layer.DefaultWmsGetMapLayer;

import org.geotoolkit.factory.Hints;
import org.geotoolkit.referencing.CRS;
import org.geotoolkit.referencing.crs.DefaultGeographicCRS;
import org.geotoolkit.sld.MutableStyledLayerDescriptor;
import org.geotoolkit.sld.xml.Specification.StyledLayerDescriptor;
import org.geotoolkit.sld.xml.XMLUtilities;
import org.geotoolkit.style.MutableFeatureTypeStyle;
import org.geotoolkit.style.MutableRule;
import org.geotoolkit.style.MutableStyle;
import org.geotoolkit.style.MutableStyleFactory;
import org.geotoolkit.wms.xml.AbstractKeyword;

import org.mapfaces.models.tree.TreeNodeModel;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.spatial.Equals;
import org.opengis.geometry.Envelope;
import org.opengis.style.AnchorPoint;
import org.opengis.style.Displacement;
import org.opengis.style.ExternalGraphic;
import org.opengis.style.Fill;
import org.opengis.style.Graphic;
import org.opengis.style.GraphicalSymbol;
import org.opengis.style.PointSymbolizer;
import org.opengis.style.PolygonSymbolizer;
import org.opengis.style.Stroke;

/**
 * @author Mehdi Sidhoum (Geomatys).
 * @author Olivier Terral (Geomatys).
 */
public class FacesUtils {

    private static final Logger LOGGER = Logger.getLogger(FacesUtils.class.getName());
    
    /**
     * this array of colors have a binding with the png markers witih index 0 to 9.
     */
    public final static Color colors[] = {Color.CYAN, Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLUE, Color.ORANGE, Color.WHITE, Color.PINK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.BLACK};

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
                UIComponent kid = (UIComponent) kids.next();
                encodeRecursive(context, kid);
            }
        }
        component.encodeEnd(context);
    }

    /**
     * This is a recursive method to encode all component's children who referring to a TreeNodeModel.
     * @param context
     * @param component
     * @param node
     * @throws java.io.IOException
     */
    public static void encodeRecursive(final FacesContext context, final UIComponent component,
            final TreeNodeModel node) throws IOException {

        if (!component.isRendered()) {
            LOGGER.log(Level.INFO, component + " not rendered !");
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

    /**
     * This method returns the count of wmsGetmapLayers contained in a context model.
     * @param ctx
     * @return
     */
    public static int getCountWMSGetMapLayers(Context ctx) {
        if (ctx == null) {
            return 0;
        }
        int result = 0;
        for (Layer layer : ctx.getLayers()) {
            if (layer instanceof DefaultWmsGetMapLayer) {
                result++;
            }
        }
        return result;
    }

    public static int getNewIndex(Context ctx) {
        if (ctx == null) {
            return 1;
        } else {
            return ctx.getLayers().size();
        }
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
        return (UIMapPane) parent;
    }
    
    /**
     * Returns the corresponding UIMapPane of a specific UIContext
     * @param context
     * @param comp
     * @return
     */
    public static UIMapPane getChildUIMapPane(FacesContext context, UIComponent component) {
        if (component instanceof UIMapPane) {
            return (UIMapPane) component;
        } else {
            final Iterator kids = component.getChildren().iterator();
            while (kids.hasNext()) {
                UIComponent kid = (UIComponent) kids.next();
                return getChildUIMapPane(context, kid);
            }
        }
        return null;
    }
    /**
     * Returns the corresponding UIMapPane of a specific UIWidget 
     * @param context
     * @param comp
     * @return
     */
    public static UIMapPane getUIMapPane(FacesContext context, UIComponent component) {
        try {
            try {
                //If the component has a property who refers to a result of UIMapPane.getId() function
                if (component.getClass().getField("uiMapPaneId") != null) {
                    final Field field = component.getClass().getField("uiMapPaneId");
                    if (field.get(component) != null && !((String) field.get(component)).equals("")) {
                        return (UIMapPane) findComponentById(context, context.getViewRoot(), (String) field.get(component));
                    }
                }
            } catch (NoSuchFieldException ex) {
                //If the component has a property who refers to a result of  UIMapPane.getClientId() function
                if (component.getClass().getField("uiMapPaneClientId") != null) {
                    final Field field = component.getClass().getField("uiMapPaneClientId");
                    if (field.get(component) != null && !((String) field.get(component)).equals("")) {
                        return (UIMapPane) findComponentByClientId(context, context.getViewRoot(), (String) field.get(component));
                    }
                }
            }

        } catch (IllegalArgumentException ex) {
        } catch (IllegalAccessException ex) {
        } catch (NoSuchFieldException ex) {
        } catch (SecurityException ex) {
        }
        //If component has no property to refers to A UIMapPane, it is include in a UIContext tag
        //so we find this UIContext component and we go through its childs to find UIMapPane who displays its layers
        if (component instanceof UIContext) {
            //If the component is a UIContext , we search recursively the UIMaPane corresponding into its childs 
            return getChildUIMapPane(context, component);
        } else {
            UIComponent parent = FacesUtils.getParentUIContext(context, component);

            if (parent == null) {
                return null;
            } else {
                return getUIMapPane(context, parent);
            }
        }
    }

    /**
     * Returns the UITreeLines of the mapfaces component.
     * @param context
     * @param comp
     * @return
     */
    public static UITreeLines getParentUITreeLines(FacesContext context, UIComponent component) {
        UIComponent parent = component;
        while (!(parent instanceof UITreeLines)) {
            parent = parent.getParent();
        }
        return (UITreeLines) parent;
    }

    /**
     * Returns the UIContext of the mapfaces component.
     * @param context
     * @param comp
     * @return
     */
    public static UIContext getParentUIContext(FacesContext context, UIComponent comp) {
        UIComponent parent = comp;
        while (parent != null && !(parent instanceof UIContext)) {
            parent = parent.getParent();
        }
        return ((parent == null) ? null : (UIContext) parent);
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

     /************************************* UIForm functions *********************************/
    



     /************************************* find functions *********************************/

    public static UIForm findForm(UIComponent component) {
        return (UIForm) findParentComponentByClass(component, UIForm.class);
    }
    /**
     * <p>Get container form of the UIComponent</p>
     * @param component UIComponent to be rendered
     * @return UIForm the form container of the component if exist else return null
     */

    public static String getFormId(FacesContext context, UIComponent component) {
        return findForm(component).getId();
    }

    public static String getFormClientId(FacesContext context, UIComponent component) {
        return findForm(component).getClientId(context);
    }

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
    
    public static UIComponent findParentComponentByClass(final UIComponent component, final Class c) {
        UIComponent parent = component;
        while (!(c.isInstance(parent))) {
            parent = parent.getParent();
        }
        return parent;
    }
    public static String getParentComponentIdByClass(final UIComponent component, final Class c) {
        return findParentComponentByClass(component, c).getId();
    }
    public static String getParentComponentClientIdByClass(final FacesContext faceContext,
            final UIComponent component, final Class c) {
        return findParentComponentByClass(component, c).getClientId(faceContext);
    }



    
    public static RenderKit getRenderKit(final FacesContext context) {
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
    public static HtmlAjaxSupport createTreeAjaxSupport(final FacesContext context,
            final UIComponent comp, final String event, final String varId, String idsToReRender) {

        /* To use the synchronized parameter the reRender attribute must be null*/
        final HashMap<String, String> extraParams = new HashMap<String, String>();
        extraParams.put(AjaxUtils.AJAX_LAYER_ID, varId);
        extraParams.put(AjaxUtils.AJAX_CONTAINER_ID_KEY, comp.getClientId(context));
        /*if we don't want to reRender another component than the "var" component */
        //if (idsToReRender == null) {
            idsToReRender += "," +varId ;
       // }
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
    public static HtmlAjaxSupport createSynchronizedAjaxSupport(final FacesContext context, final UIComponent comp,
            final String event, String idsToReRender, final HashMap<String, String> extraParams) {

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
    public static HtmlAjaxSupport createExtraAjaxSupport(final FacesContext context, final UIComponent comp,
            final String event, final String idsToReRender, final HashMap<String, String> extraParams) {

        /* Add <a4j:support> component */
        final HtmlAjaxSupport ajaxComp = createBasicAjaxSupport(context, comp, event, idsToReRender);
        for (final String tmp : extraParams.keySet()) {
            ajaxComp.getChildren().add(createFParam(tmp, extraParams.get(tmp)));
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
    public static HtmlAjaxSupport createBasicAjaxSupport(final FacesContext context,
            final UIComponent comp, final String event, final String idsToReRender) {

        /* Add <a4j:support> component */
        final HtmlAjaxSupport ajaxComp = new HtmlAjaxSupport();
        ajaxComp.setId(comp.getId() + "_Ajax");
        if (event != null && !event.equals("")) {
            ajaxComp.setEvent(event);
        }
        ajaxComp.setAjaxSingle(true);
        ajaxComp.setLimitToList(true);
        ajaxComp.setReRender(idsToReRender);
        return ajaxComp;
    }

    /**
     * This method creates a new HtmlAjaxSupport component with parameters and js code if necessary.
     * @param context
     * @param comp
     * @param event
     * @param varId
     * @param idsToReRender
     * @param params
     * @param onSubmitJS
     * @param onCompleteJS
     * @return
     */
    public static HtmlAjaxSupport createTreeAjaxSupportWithParameters(final FacesContext context,
            final UIComponent comp, final String event, final String varId, String idsToReRender,
            final HashMap<String, String> params, final String onSubmitJS, String onCompleteJS) {

        if (comp == null) {
            return null;
        }
        final HashMap<String, String> extraParams = new HashMap<String, String>();
        extraParams.put(AjaxUtils.AJAX_LAYER_ID, varId);
        extraParams.put(AjaxUtils.AJAX_CONTAINER_ID_KEY, comp.getClientId(context));
        extraParams.putAll(params);

        if (idsToReRender == null) {
            idsToReRender = varId;
        }

        /* Add <a4j:support> component */
        final HtmlAjaxSupport ajaxComp = createCompleteAjaxSupport(context, comp.getId(), event, idsToReRender, onSubmitJS, onCompleteJS);
        for (final String tmp : extraParams.keySet()) {
            ajaxComp.getChildren().add(createFParam(tmp, extraParams.get(tmp)));
        }
        return ajaxComp;
    }

    /**
     * This method creates an a4j support component by passing the parentId and onSubmit/onComplete js code.
     * @param context
     * @param parentId
     * @param event
     * @param idsToReRender
     * @param onSubmitJS
     * @param onCompleteJS
     * @return
     */
    public static HtmlAjaxSupport createCompleteAjaxSupport(final FacesContext context, final String parentId,
            final String event, final String idsToReRender, final String onSubmitJS, final String onCompleteJS) {

        /* Add <a4j:support> component */
        final HtmlAjaxSupport ajaxComp = new HtmlAjaxSupport();
        ajaxComp.setId(parentId + "_Ajax");
        ajaxComp.setEvent(event);
        ajaxComp.setAjaxSingle(true);
        ajaxComp.setLimitToList(true);
        System.out.println("idsToReRender : " + idsToReRender);
        ajaxComp.setReRender(idsToReRender);

        if (onSubmitJS != null && !onSubmitJS.equals("")) {
            ajaxComp.setOnsubmit(onSubmitJS);
        }
        if (onCompleteJS != null && !onCompleteJS.equals("")) {
            ajaxComp.setOncomplete(onCompleteJS);
        }
        return ajaxComp;
    }

    private static UIParameter createFParam(final String name, final String value) {

        /* Add <f:param> component */
        final UIParameter fParam = new UIParameter();
        fParam.setName(name);
        fParam.setValue(value);
        return fParam;
    }

    /**
     * Returns true if the list contains a string in one of the list elements.
     * @param list
     * @param str
     * @return
     */
    public static boolean matchesStringfromList(final List<String> list, final String str) {
        boolean str_available = false;
        for (final String s : list) {
            final Pattern pattern = Pattern.compile(str, Pattern.CASE_INSENSITIVE | Pattern.CANON_EQ);
            final Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                str_available = true;
            }
        }
        return str_available;
    }

    /**
     * Returns true if the list contains a string in one of the list elements.
     * @param list
     * @param str
     * @return
     */
    public static boolean matchesKeywordfromList(List<? extends AbstractKeyword> list, String str) {
        boolean str_available = false;
        for (AbstractKeyword k : list) {
            Pattern pattern = Pattern.compile(str, Pattern.CASE_INSENSITIVE | Pattern.CANON_EQ);
            Matcher matcher = pattern.matcher(k.getValue());
            if (matcher.find()) {
                str_available = true;
            }
        }
        return str_available;
    }

    /**
     * This method returns the number of temporal layers in a list.
     * @param layers
     * @return
     */
    public static int getCountTemporalLayers(List<Layer> layers) {
        int result = 0;
        for (Layer layer : layers) {
            if (layer.getDimensionList() != null && layer.getTime() != null) {
                result++;
            }
        }
        return result;
    }

    /**
     * This method returns the number of layers UILayers as children of a mappane.
     * @param layers
     * @return
     */
    public static int getCountUILayers(UIMapPane mappane) {
        int result = 0;
        for (UIComponent child : mappane.getChildren()) {
            if (child instanceof UILayer) {
                result++;
            }
        }
        return result;
    }

    /**
     * This method returns the number of layers UIFeatureLayers as children of a mappane container.
     * @param layers
     * @return
     */
    public static int getCountUIFeatureLayers(UIComponent component) {
        int result = 0;
        for (UIComponent child : component.getChildren()) {
            if (child instanceof UIFeatureLayer) {
                result++;
            }
        }
        return result;
    }

    /**
     * Returns a list that contains all bandinfos components as child of a timeline component.
     * @param context
     * @param timeline
     * @return
     */
    public static List<UIHotZoneBandInfo> getBandInfoTimelineChildren(FacesContext context, UITimeLine timeline) {
        List<UIHotZoneBandInfo> result = new ArrayList<UIHotZoneBandInfo>();
        if (context != null && timeline != null) {
            for (UIComponent child : timeline.getChildren()) {
                if (child instanceof UIHotZoneBandInfo) {
                    result.add((UIHotZoneBandInfo) child);
                }
            }
        }
        return result;
    }

    /**
     * This method returns the current Session id, if context is null then {@code null} is returned.
     * @return String id
     */
    public static String getCurrentSessionId() {
        String result = null;
        FacesContext context = FacesContext.getCurrentInstance();
        if (context != null) {
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession session = request.getSession();
            result = session.getId();
        }
        return result;
    }

    /**
     * This method returns the  current server informations ie:  Sun Java System Application Server or Apache Tomcat/6.0.13 ...
     * @return the server name.
     */
    public static String getServerInfoFromContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context != null) {
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession session = request.getSession();
            return session.getServletContext().getServerInfo();
        }
        return null;
    }

    /**
     * This method returns true if the mappane contains MFLayers.
     * @param mappane
     * @return
     */
    public static boolean containsMFLayers(UIMapPane mappane) {
        if (mappane != null) {
            for (UIComponent child : mappane.getChildren()) {
                if (child instanceof UIFeatureLayer) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method return a MutableStyle for a FeatureLayer .
     * @param urlImage image to display if the feature is a point
     * @param size 
     * @param rotation
     * @param indexLayer
     * @return
     */
    public static MutableStyle createStyle(String urlImage, int size, double rotation, int indexLayer) throws MalformedURLException {

        final FilterFactory filterFactory = org.geotoolkit.factory.FactoryFinder.getFilterFactory(null);
        final MutableStyleFactory styleFactory = (MutableStyleFactory) org.geotoolkit.factory.FactoryFinder.
                getStyleFactory(new Hints(Hints.STYLE_FACTORY, MutableStyleFactory.class));
        final MutableStyle style = styleFactory.style();
        final MutableFeatureTypeStyle fts = styleFactory.featureTypeStyle();
        final MutableRule rulePoint = styleFactory.rule();
        final MutableRule rulePolygon = styleFactory.rule();


        final String format = "image/png";
        final ImageIcon icon = new ImageIcon(new URL(urlImage));

        final ExternalGraphic external = styleFactory.externalGraphic(icon, null);

        final List<GraphicalSymbol> symbols = new ArrayList<GraphicalSymbol>();
        final Expression opacity = filterFactory.literal(1d);
        symbols.add(external);

        final Expression expSize = filterFactory.literal(size);
        final Expression expRotation = filterFactory.literal(rotation);

        final AnchorPoint anchor = styleFactory.anchorPoint(0.5, 1); //for markers we need to move the anchor point to the img bottom.
        final Displacement disp = null;
        final Graphic graphic = styleFactory.graphic(symbols, opacity, expSize, expRotation, anchor, disp);

        final Filter filterPoint = filterFactory.equals(filterFactory.property("type"), filterFactory.literal(Feature.POINT));
        final PointSymbolizer pointSymbol = styleFactory.pointSymbolizer(graphic, "");

        rulePoint.symbolizers().add(pointSymbol);
        rulePoint.setFilter(filterPoint);

        final Filter filterPolygon = filterFactory.equals(filterFactory.property("type"), filterFactory.literal(Feature.POLYGON));
        final Stroke stroke = styleFactory.stroke(styleFactory.literal(colors[indexLayer]),
                filterFactory.literal(2),
                filterFactory.literal(0.8));
        final Fill fill = styleFactory.fill(styleFactory.literal(colors[indexLayer]), filterFactory.literal(0.1));
        final PolygonSymbolizer polygonSymbol = styleFactory.polygonSymbolizer(stroke, fill, "marker");

        rulePolygon.symbolizers().add(polygonSymbol);
        rulePolygon.setFilter(filterPolygon);

        fts.rules().add(rulePolygon);
        fts.rules().add(rulePoint);
        style.featureTypeStyles().add(fts);

        return style;
    }

    /**
     * This method get a value of parameter from an url, it is used for getMap wms requests. 
     * @param param
     * @param url
     * @return
     */
    public static String getParameterValue(String param, String url) {
        if (param == null || url == null || url.equals("")) {
            return null;
        }
        Pattern patternParam = Pattern.compile("(?i)" + param + "=");
        Matcher matcherParam = patternParam.matcher(url);
        if (matcherParam.find()) {
            String subst = url.substring(url.lastIndexOf(matcherParam.group()));
            String result;
            if (subst.contains("&")) {
                result = subst.substring(subst.indexOf("=") + 1, subst.indexOf("&"));
            } else {
                result = subst.substring(subst.indexOf("=") + 1);
            }
            return result;
        }
        return "";
    }

    /**
     * this method set a new value for a parameter in url, it is commonly used for wms getmap requests.
     * @param param
     * @param value
     * @param url
     * @return
     */
    public static String setParameterValueAndGetUrl(String param, String value, String url) {
        if (param == null || param.equals("") || url == null) {
            return url;
        } else {
            Pattern patternParam = Pattern.compile("(?i)" + param + "=");
            Matcher matcherParam = patternParam.matcher(url);
            if (matcherParam.find()) {
                String subst = url.substring(0, matcherParam.end());
                String temp = url.substring(matcherParam.end());
                String endStr;
                if (temp.contains("&")) {
                    endStr = temp.substring(temp.indexOf("&"));
                } else {
                    endStr = "";
                }

                subst = subst.concat(value);
                subst = subst.concat(endStr);
                return subst;
            } else {
                return url;
            }
        }
    }

    /**
     * This method builds a feature.
     */
    public static DefaultFeature buildFeature(String identifier, String srs, Double[] bbox, GeometryFactory geomBuilder, String toponym, String title, String resume, Serializable obj) {
        DefaultFeature feature = new DefaultFeature();
        feature.setId(identifier);
        try {
            feature.setCrs((DefaultGeographicCRS) CRS.decode(srs));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        Map<String, Serializable> attributes = new HashMap<String, Serializable>();
        double minx = bbox[0];
        double miny = bbox[1];
        double maxx = bbox[2];
        double maxy = bbox[3];

        Coordinate[] coords = new Coordinate[]{
            new Coordinate(minx, miny),
            new Coordinate(minx, maxy),
            new Coordinate(maxx, maxy),
            new Coordinate(maxx, miny),
            new Coordinate(minx, miny),};

        LinearRing linear = geomBuilder.createLinearRing(coords);
        Geometry geometry = geomBuilder.createPolygon(linear, new LinearRing[0]);

        final String featuretype;
        if (geometry.getArea() == 0) {
            featuretype = Feature.POINT;
            geometry = geomBuilder.createPoint(coords[0]);
        } else {
            featuretype = Feature.POLYGON;
        }

        attributes.put("geometry", geometry);
        attributes.put("type", featuretype);
        attributes.put("toponym", toponym);
        attributes.put("title", title);
        attributes.put("abstract", resume);
        attributes.put("result", obj);
        feature.setUserObject(obj);
        feature.setAttributes(attributes);
        feature.setGeometry(geometry);

        return feature;
    }

    /**
     * This method replace all special characters encoded by urlFormat to valid XML which can be marshalled by JAXB.
     * @param str
     * @return
     */
    public static String convertSpecialCharsToValidXml(String str) {
        if (str != null) {
            str = str.replaceAll("%3C", "<");
            str = str.replaceAll("%3E", ">");
            str = str.replaceAll("%3D", "=");
            str = str.replaceAll("%22", "\"");
            str = str.replaceAll("%2F", "/");
            str = str.replaceAll("%23", "#");
            str = str.replaceAll("%20", " ");
            str = str.replace('+', ' ');
        }
        return str;
    }

    /**
     * Encode a string from xml to a valid Url format.
     * @param str
     * @return
     */
    public static String convertSpecialCharsToUrlXml(String str) {
        if (str != null) {
            str = str.replaceAll("<", "%3C");
            str = str.replaceAll(">", "%3E");
            str = str.replaceAll("=", "%3D");
            str = str.replaceAll("\"", "%22");
            str = str.replaceAll("/", "%2F");
            str = str.replaceAll("#", "%23");
            str = str.replaceAll(" ", "%20");
            str = str.replace(' ', '+');
        }
        return str;
    }

    public static MutableStyledLayerDescriptor getSLDfromGetmapUrl(String url) {
        MutableStyledLayerDescriptor result = null;
        if (url == null || FacesUtils.getParameterValue("SLD_BODY", url) == null || FacesUtils.getParameterValue("SLD_BODY", url).equals("")) {
            return result;
        }
        String sldbody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + FacesUtils.getParameterValue("SLD_BODY", url);
        sldbody = convertSpecialCharsToValidXml(sldbody);

        XMLUtilities xmlUtils = new XMLUtilities();
        try {
            byte[] arrayByte = null;
            try {
                arrayByte = sldbody.getBytes("UTF-8");
            } catch (UnsupportedEncodingException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            ByteArrayInputStream inputStream = new ByteArrayInputStream(arrayByte);
            result = xmlUtils.readSLD(inputStream, StyledLayerDescriptor.V_1_0_0);
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * This method returns a number of occurences occ in the string s.
     */
    public static int getOccurence(String s, String occ) {
        if (!s.contains(occ)) {
            return 0;
        } else {
            int nbocc = 0;
            while (s.indexOf(occ) != -1) {
                s = s.substring(s.indexOf(occ) + 1);
                nbocc++;
            }
            return nbocc;
        }
    }

    /**
     * This method returns the lattitude and longitude from pixels x y with the envelope and dimension.
     * @param x
     * @param y
     * @param envelope
     * @param dimension
     * @return
     */
    public static Double[] getLonLatFromPixel(double x, double y, Envelope envelope, Dimension dimension) {
        double lat = 0;
        double lon = 0;
        if (envelope != null && dimension != null && envelope.getLowerCorner() != null && envelope.getUpperCorner() != null) {
            double envWidth = Math.abs(envelope.getUpperCorner().getCoordinate()[0] - envelope.getLowerCorner().getCoordinate()[0]);
            double envHeight = Math.abs(envelope.getUpperCorner().getCoordinate()[1] - envelope.getLowerCorner().getCoordinate()[1]);
            double objX = envelope.getLowerCorner().getCoordinate()[0] + (x * envWidth) / dimension.width;
            double objY = -(envelope.getLowerCorner().getCoordinate()[1] + (y * envHeight) / dimension.height);
            lat = objY;
            lon = objX;
        }
        return new Double[]{lon, lat};
    }

    /**
     * Returns the host url from the current container.
     * @return String
     */
    public static String getHostUrl() {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        return url.substring(0, url.indexOf(uri));
    }

    /**
     * Send a request and add logs in a list.
     * @param sourceURL
     * @param request
     * @param marshaller
     * @param unmarshaller
     * @param logs
     * @return
     * @throws java.net.MalformedURLException
     * @throws java.io.IOException
     */
    public static Object sendRequest(String sourceURL, Object request, Marshaller marshaller, Unmarshaller unmarshaller, List<String> logs) throws MalformedURLException, IOException {

        if (logs != null) {
            Date d = new Date();
            logs.add("["+d+"] Trying to connect to URL = " + sourceURL);
        }

        URL source = new URL(sourceURL);
        URLConnection conec = source.openConnection();
        Object harvested = null;

        try {
            // for a POST request
            if (request != null) {

                conec.setDoOutput(true);
                conec.setRequestProperty("Content-Type", "text/xml");
                OutputStreamWriter wr = new OutputStreamWriter(conec.getOutputStream());
                StringWriter sw = new StringWriter();
                try {
                    marshaller.marshal(request, sw);
                } catch (JAXBException ex) {
                    LOGGER.log(Level.SEVERE, "Unable to marshall the request: " + ex.getMessage());
                }
                String XMLRequest = sw.toString();
                if (logs != null) {
                    logs.add("Post request URL = " + sourceURL);
                    logs.add("XMLRequest = " + XMLRequest);
                }
                wr.write(XMLRequest);
                wr.flush();
            }

            // we get the response document
            InputStream in = conec.getInputStream();
            StringWriter out = new StringWriter();
            byte[] buffer = new byte[1024];
            int size;

            while ((size = in.read(buffer, 0, 1024)) > 0) {
                out.write(new String(buffer, 0, size));
            }

            //we convert the brut String value into UTF-8 encoding
            String brutString = out.toString();

            //we need to replace % character by "percent because they are reserved char for url encoding
            brutString = brutString.replaceAll("%", "percent");
            String decodedString = java.net.URLDecoder.decode(brutString, "UTF-8");

            try {
                decodedString = decodedString.replaceAll("percent", "%");
                if (unmarshaller == null) {
                    return decodedString;
                } else {
                    harvested = unmarshaller.unmarshal(new StringReader(decodedString));
                    if (harvested != null && harvested instanceof JAXBElement) {
                        harvested = ((JAXBElement) harvested).getValue();
                    }
                }
            } catch (JAXBException ex) {
                LOGGER.log(Level.SEVERE, "The distant service does not respond correctly: unable to unmarshall response document." + '\n' +
                        "cause: " + ex.getMessage(),ex);
                if (logs != null) {
                    logs.add("The distant service does not respond correctly: unable to unmarshall response document." + '\n' +
                            "cause: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "The Distant service have made an error ! "+ex.getStackTrace());

            if (logs != null) {
                logs.add("The Distant service has made an error ! url = " + sourceURL);
                logs.add(ex.getMessage());
            }
            return null;
        }
        return harvested;
    }

    /**
     * Send a request to a service.
     * 
     * @param sourceURL the url of the distant web-service
     * @param request The XML object to send in POST mode (if null the request is GET)
     * 
     * @return The object correspounding to the XML response of the distant web-service
     * 
     * @throws java.net.MalformedURLException
     * @throws java.io.IOException
     * @throws org.constellation.coverage.web.WebServiceException
     */
    public static Object sendRequest(String sourceURL, Object request,
            Marshaller marshaller, Unmarshaller unmarshaller) throws MalformedURLException, IOException {
       URL source = new URL(sourceURL);
        URLConnection conec = source.openConnection();
        Object harvested = null;

        try {
            // for a POST request
            if (request != null) {

                conec.setDoOutput(true);
                conec.setRequestProperty("Content-Type", "text/xml");
                OutputStreamWriter wr = new OutputStreamWriter(conec.getOutputStream());
                StringWriter sw = new StringWriter();
                try {
                    marshaller.marshal(request, sw);
                } catch (JAXBException ex) {
                    LOGGER.log(Level.SEVERE, "Unable to marshall the request: ", ex);
                }
                String XMLRequest = sw.toString();
                wr.write(XMLRequest);
                wr.flush();
            }

            // we get the response document
            InputStream in = conec.getInputStream();
            StringWriter out = new StringWriter();
            byte[] buffer = new byte[1024];
            int size;

            while ((size = in.read(buffer, 0, 1024)) > 0) {
                out.write(new String(buffer, 0, size));
            }

            //we convert the brut String value into UTF-8 encoding
            String brutString = out.toString();

            //we need to replace % character by "percent because they are reserved char for url encoding
            brutString = brutString.replaceAll("%", "percent");
            String decodedString = URLDecoder.decode(brutString, "UTF-8");

            try {
                decodedString = decodedString.replaceAll("percent", "%");
                if (unmarshaller == null) {
                    return decodedString;
                } else {
                    harvested = unmarshaller.unmarshal(new StringReader(decodedString));
                    if (harvested != null && harvested instanceof JAXBElement) {
                        harvested = ((JAXBElement) harvested).getValue();
                    }
                }
            } catch (JAXBException ex) {
                LOGGER.log(Level.SEVERE, "The distant service does not respond correctly: unable to unmarshall response document." + '\n' +
                        "cause: " + ex.getMessage());
                LOGGER.log(Level.SEVERE, ex.toString());
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "The Distant service have made an error ! \n", ex);
            return null;
        }
        return harvested;
    }

    /**
     * Returns a flag that indicates if the browser is Internet Explorer.
     * @param context
     * @return
     */
    public static boolean isIEBrowser(FacesContext context) {
        HttpServletRequest servletReq = (HttpServletRequest) context.getExternalContext().getRequest();
        String useragent = servletReq.getHeader("User-Agent");
        boolean isIE = false;
        if (useragent != null) {
            String user = useragent.toLowerCase();
            if ((user.indexOf("msie") != -1)) {
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
        LifecycleFactory factory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
        Lifecycle lifecycle = factory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
        PhaseListener[] listeners = lifecycle.getPhaseListeners();
        for (int i = 0; i < listeners.length; i++) {
            PhaseListener listener = listeners[i];
            if (c.isInstance(listener)) {
                return listener;
            }
        }
        return null;
    }

    /**
     * Returns a clientId of the first HtmlAjaxRegion if exists, otherwise returns null.
     * @param context
     * @return
     */
    public static String findClientIdComponentClass(final FacesContext context, final UIComponent root,final Class cl) {
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
}
