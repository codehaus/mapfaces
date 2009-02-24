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

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javax.servlet.http.HttpSession;

import javax.swing.ImageIcon;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;

import org.geotools.data.wms.backend.AbstractKeyword;

import org.geotools.factory.CommonFactoryFinder;
import org.geotools.style.MutableFeatureTypeStyle;
import org.geotools.style.MutableRule;
import org.geotools.style.MutableStyle;
import org.geotools.style.StyleFactory;
import org.mapfaces.component.UILayer;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.layer.UIFeatureLayer;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.component.models.UIModelBase;
import org.mapfaces.component.timeline.UIHotZoneBandInfo;
import org.mapfaces.component.timeline.UITimeLine;
import org.mapfaces.component.treelayout.UITreeLines;
import org.mapfaces.models.Layer;
import org.opengis.filter.FilterFactory2;
import org.opengis.style.ExternalGraphic;
import org.opengis.style.GraphicalSymbol;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.vecmath.Color3b;
import org.geotools.display.exception.PortrayalException;
import org.geotools.display.service.DefaultPortrayalService;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureCollections;
import org.geotools.feature.simple.SimpleFeatureImpl;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.filter.identity.FeatureIdImpl;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureMapLayer;
import org.geotools.map.MapBuilder;
import org.geotools.map.MapContext;
import org.geotools.map.MapLayer;
import org.geotools.referencing.CRS;

import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.style.MutableFeatureTypeStyle;
import org.geotools.style.MutableRule;
import org.geotools.style.MutableStyle;
import org.geotools.style.StyleFactory;
import org.mapfaces.component.layer.UIFeatureLayer;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Feature;
import org.mapfaces.models.Layer;
import org.mapfaces.util.FacesUtils;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.expression.Expression;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
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
 * @author Mehdi Sidhoum.
 * @author Olivier Terral.
 */
public class FacesUtils {
    
    private final static Color colors[] = { Color.BLACK, Color.CYAN, Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLUE, Color.ORANGE, Color.WHITE, Color.PINK, Color.DARK_GRAY, Color.LIGHT_GRAY};

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

    public static int getNewIndex(Context ctx) {
        if (ctx == null ) {
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
            if (parent != null) {
                parent = parent.getParent();
            }else {
                throw new IllegalStateException("You must specify a form for the mapfaces components.");
            }
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
    public static UIComponent findComponentByClientId(final FacesContext context,
            final UIComponent root, final String clientId) {
        UIComponent component = null;
        for (int i = 0; i < root.getChildCount() && component == null; i++) {
            final UIComponent child = (UIComponent) root.getChildren().get(i);
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
        if (idsToReRender == null) {
            idsToReRender = varId;
        }
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
        ajaxComp.setEvent(event);
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

        final FilterFactory2 filterFactory = CommonFactoryFinder.getFilterFactory2(null);
        final StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory(null);
        final MutableStyle style = styleFactory.createStyle();
        final MutableFeatureTypeStyle fts = styleFactory.createFeatureTypeStyle();
        final MutableRule rulePoint = styleFactory.createRule();
        final MutableRule rulePolygon = styleFactory.createRule();


        String format = "image/png";
        ImageIcon icon = new ImageIcon(new URL(urlImage));

        ExternalGraphic external = styleFactory.createExternalGraphic(icon, format, null);

        List<GraphicalSymbol> symbols = new ArrayList<GraphicalSymbol>();
        Expression opacity = styleFactory.literalExpression(1d);
        symbols.add(external);

        Expression expSize = styleFactory.literalExpression(size);
        Expression expRotation = styleFactory.literalExpression(rotation);

        AnchorPoint anchor = styleFactory.createAnchorPoint(0.5, 1); //for markers we need to move the anchor point to the img bottom.
        Displacement disp = null;
        Graphic graphic = styleFactory.createGraphic(symbols, opacity, expSize, expRotation, anchor, disp);

        Filter filterPoint = filterFactory.equals(filterFactory.property("type"), filterFactory.literal(Feature.POINT));
        PointSymbolizer pointSymbol = styleFactory.createPointSymbolizer(graphic, "");

        rulePoint.symbolizers().add(pointSymbol);
        rulePoint.setFilter(filterPoint);

        Filter filterPolygon = filterFactory.equals(filterFactory.property("type"), filterFactory.literal(Feature.POLYGON));
        Stroke stroke = styleFactory.createStroke(styleFactory.colorExpression(colors[indexLayer]),
                styleFactory.literalExpression(2),
                styleFactory.literalExpression(0.8));
        Fill fill = styleFactory.createFill(styleFactory.colorExpression(colors[indexLayer]), styleFactory.literalExpression(0.1));
        PolygonSymbolizer polygonSymbol = styleFactory.createPolygonSymbolizer(stroke, fill, "marker");

        rulePolygon.symbolizers().add(polygonSymbol);
        rulePolygon.setFilter(filterPolygon);

        fts.rules().add(rulePolygon);
        fts.rules().add(rulePoint);
        style.featureTypeStyles().add(fts);


        return style;
    }
}
