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
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

import java.awt.Color;
import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.swing.ImageIcon;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.ajax4jsf.component.html.HtmlAjaxSupport;

import org.geotoolkit.data.FeatureCollectionUtilities;
import org.geotoolkit.data.collection.FeatureCollection;
import org.mapfaces.component.UILayer;
import org.mapfaces.component.abstractTree.UITreePanelBase;
import org.mapfaces.component.models.UIContext;
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
import org.geotoolkit.feature.simple.DefaultSimpleFeature;
import org.geotoolkit.feature.simple.SimpleFeatureTypeBuilder;
import org.geotoolkit.filter.identity.DefaultFeatureId;
import org.geotoolkit.referencing.CRS;
import org.geotoolkit.referencing.crs.DefaultGeographicCRS;
import org.geotoolkit.sld.MutableStyledLayerDescriptor;
import org.geotoolkit.sld.xml.Specification.StyledLayerDescriptor;
import org.geotoolkit.sld.xml.XMLUtilities;
import org.geotoolkit.style.MutableFeatureTypeStyle;
import org.geotoolkit.style.MutableRule;
import org.geotoolkit.style.MutableStyle;
import org.geotoolkit.style.MutableStyleFactory;

import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.share.utils.AjaxUtils;
import org.mapfaces.share.utils.FacesUtils;
import org.mapfaces.share.utils.Utilities;
import org.mapfaces.share.utils.WebContainerUtils;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory;
import org.opengis.filter.expression.Expression;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.style.AnchorPoint;
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
public class FacesMapUtils extends FacesUtils {

    private static final Logger LOGGER = Logger.getLogger(FacesMapUtils.class.getName());
    /**
     * this array of colors have a binding with the png markers witih index 0 to 9.
     */
    protected static final Color[] COLORS = {Color.CYAN, Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLUE, Color.ORANGE, Color.WHITE, Color.PINK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.BLACK};
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * This is a recursive method to encode all component's children who refering to a TreeNodeModel.
     * @param context
     * @param component
     * @param node
     * @throws java.io.IOException
     */
    public static void encodeRecursiveTreeNode(final FacesContext context, final UIComponent component,
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
                    final UIComponent kid = (UIComponent) kids.next();
                    encodeRecursiveTreeNode(context, kid, node);
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

    /**
     * Returns a new index for existing layers.
     * @param ctx
     * @return
     */
    public static int getNewLayerIndex(Context ctx) {
        if (ctx == null) {
            return 1;
        } else {
            return ctx.getLayers().size();
        }
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

       UIMapPane mappane = null;
        if (component instanceof UIMapPane) {
            return (UIMapPane) component;

        } else {
            final Iterator kids = component.getChildren().iterator();

            while (kids.hasNext()) {
                final UIComponent kid = (UIComponent) kids.next();
                mappane = getChildUIMapPane(context, kid);
                if (mappane != null) {
                    return mappane;
                }
            }
        }
        return mappane;
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
            LOGGER.log(Level.FINE, null, ex);
        } catch (IllegalAccessException ex) {
            LOGGER.log(Level.FINE, null, ex);
        } catch (NoSuchFieldException ex) {
            LOGGER.log(Level.FINE, "");
        } catch (SecurityException ex) {
            LOGGER.log(Level.FINE, null, ex);
        }
        //If component has no property to refers to A UIMapPane, it is include in a UIContext tag
        //so we find this UIContext component and we go through its childs to find UIMapPane who displays its layers
        if (component instanceof UIContext) {
            //If the component is a UIContext , we search recursively the UIMaPane corresponding into its childs 
            return getChildUIMapPane(context, component);
        } else {
            final UIComponent parent = FacesMapUtils.getParentUIContext(context, component);

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
        return (parent == null) ? null : (UIContext) parent;
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
        idsToReRender += "," + varId;
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
            final String event, String idsToReRender, final Map<String, String> extraParams) {

        /* To use the synchronized parameter the reRender attribute must be null*/
        extraParams.put("synchronized", "true");
        extraParams.put("refresh", idsToReRender);
        return createExtraAjaxSupport(context, comp, event, null, extraParams);
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
            final String event, final String idsToReRender, final Map<String, String> extraParams) {

        /* Add <a4j:support> component */
        final HtmlAjaxSupport ajaxComp = createBasicAjaxSupport(context, comp, event, idsToReRender);
        for (final Entry tmp : extraParams.entrySet()) {
            ajaxComp.getChildren().add(createFParam((String) tmp.getKey(), extraParams.get(tmp.getKey())));
        }
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
            final Map<String, String> params, final String onSubmitJS, String onCompleteJS) {

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
        final HtmlAjaxSupport ajaxComp = createCompleteAjaxSupport(context, comp.getId(), event, idsToReRender, onSubmitJS, onCompleteJS, true, true);
        for (final Entry tmp : extraParams.entrySet()) {
            ajaxComp.getChildren().add(createFParam((String) tmp.getKey(), extraParams.get(tmp.getKey())));
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
            final String event, final String idsToReRender, final String onSubmitJS, final String onCompleteJS, boolean ajaxSingle, boolean limitTolist) {

        /* Add <a4j:support> component */
        final HtmlAjaxSupport ajaxComp = new HtmlAjaxSupport();
        ajaxComp.setId(parentId + "_Ajax");
        if(event != null && ! event.equals("")) {
            ajaxComp.setEvent(event);
        }
        ajaxComp.setAjaxSingle(ajaxSingle);
        ajaxComp.setLimitToList(limitTolist);
        if(idsToReRender != null && ! idsToReRender.equals("")) {
            ajaxComp.setReRender(idsToReRender);
        }

        if (onSubmitJS != null && !onSubmitJS.equals("")) {
            ajaxComp.setOnsubmit(onSubmitJS);
        }
        if (onCompleteJS != null && !onCompleteJS.equals("")) {
            ajaxComp.setOncomplete(onCompleteJS);
        }
        return ajaxComp;
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
        final List<UIHotZoneBandInfo> result = new ArrayList<UIHotZoneBandInfo>();
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
        final MutableStyleFactory styleFactory = (MutableStyleFactory) org.geotoolkit.factory.FactoryFinder.getStyleFactory(new Hints(Hints.STYLE_FACTORY, MutableStyleFactory.class));
        final MutableStyle style = styleFactory.style();
        final MutableFeatureTypeStyle fts = styleFactory.featureTypeStyle();
        final MutableRule rulePoint = styleFactory.rule();
        final MutableRule rulePolygon = styleFactory.rule();


        final ImageIcon icon = new ImageIcon(new URL(urlImage));

        final ExternalGraphic external = styleFactory.externalGraphic(icon, null);

        final List<GraphicalSymbol> symbols = new ArrayList<GraphicalSymbol>();
        final Expression opacity = filterFactory.literal(1d);
        symbols.add(external);

        final Expression expSize = filterFactory.literal(size);
        final Expression expRotation = filterFactory.literal(rotation);

        final AnchorPoint anchor = styleFactory.anchorPoint(0.5, 1); //for markers we need to move the anchor point to the img bottom.
        //final Displacement disp = null ;
        final Graphic graphic = styleFactory.graphic(symbols, opacity, expSize, expRotation, anchor, null);
        final String typePropertyKey = "type";
        final Filter filterPoint = filterFactory.equals(filterFactory.property(typePropertyKey), filterFactory.literal(Feature.POINT));
        final PointSymbolizer pointSymbol = styleFactory.pointSymbolizer(graphic, "geometry");

        rulePoint.symbolizers().add(pointSymbol);
        rulePoint.setFilter(filterPoint);

        final Filter filterPolygon = filterFactory.equals(filterFactory.property(typePropertyKey), filterFactory.literal(Feature.POLYGON));
        final Stroke stroke = styleFactory.stroke(styleFactory.literal(COLORS[indexLayer]),
                filterFactory.literal(2),
                filterFactory.literal(0.8));
        final Fill fill = styleFactory.fill(styleFactory.literal(COLORS[indexLayer]), filterFactory.literal(0.1));
        final PolygonSymbolizer polygonSymbol = styleFactory.polygonSymbolizer(stroke, fill, "geometry");

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
        final Pattern patternParam = Pattern.compile("(?i)" + param + "=");
        final Matcher matcherParam = patternParam.matcher(url);
        if (matcherParam.find()) {
            final String subst = url.substring(url.lastIndexOf(matcherParam.group()));
            String result;
            if (subst.contains("&")) {
                result = subst.substring(subst.indexOf('=') + 1, subst.indexOf('&'));
            } else {
                result = subst.substring(subst.indexOf('=') + 1);
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
            final Pattern patternParam = Pattern.compile("(?i)" + param + "=");
            final Matcher matcherParam = patternParam.matcher(url);
            if (matcherParam.find()) {
                String subst = url.substring(0, matcherParam.end());
                final String temp = url.substring(matcherParam.end());
                String endStr;
                if (temp.contains("&")) {
                    endStr = temp.substring(temp.indexOf('&'));
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
        final DefaultFeature feature = new DefaultFeature();
        feature.setId(identifier);
        try {
            feature.setCrs(CRS.decode(srs));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        final Map<String, Serializable> attributes = new HashMap<String, Serializable>();
        final double minx = bbox[0];
        final double miny = bbox[1];
        final double maxx = bbox[2];
        final double maxy = bbox[3];

        final Coordinate[] coords = new Coordinate[]{
            new Coordinate(minx, miny),
            new Coordinate(minx, maxy),
            new Coordinate(maxx, maxy),
            new Coordinate(maxx, miny),
            new Coordinate(minx, miny),};

        final LinearRing linear = geomBuilder.createLinearRing(coords);
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

    public static MutableStyledLayerDescriptor getSLDfromGetmapUrl(String url) {
        MutableStyledLayerDescriptor result = null;
        final String sldbodyKey = "SLD_BODY";
        if (url == null || FacesMapUtils.getParameterValue(sldbodyKey, url) == null || FacesMapUtils.getParameterValue(sldbodyKey, url).equals("")) {
            return result;
        }
        String sldbody = "<?xml version=\"1.0\" encoding=\"" + DEFAULT_CHARSET + "\"?>" +
                FacesMapUtils.getParameterValue(sldbodyKey, url);
        sldbody = Utilities.convertSpecialCharsToValidXml(sldbody);

        final XMLUtilities xmlUtils = new XMLUtilities();
        try {
            byte[] arrayByte = null;
            try {
                arrayByte = sldbody.getBytes(DEFAULT_CHARSET);
            } catch (UnsupportedEncodingException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            final ByteArrayInputStream inputStream = new ByteArrayInputStream(arrayByte);
            result = xmlUtils.readSLD(inputStream, StyledLayerDescriptor.V_1_0_0);
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return result;
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
            final double envWidth = Math.abs(envelope.getUpperCorner().getCoordinate()[0] - envelope.getLowerCorner().getCoordinate()[0]);
            final double envHeight = Math.abs(envelope.getUpperCorner().getCoordinate()[1] - envelope.getLowerCorner().getCoordinate()[1]);
            final double objX = envelope.getLowerCorner().getCoordinate()[0] + (x * envWidth) / dimension.width;
            final double objY = -(envelope.getLowerCorner().getCoordinate()[1] + (y * envHeight) / dimension.height);
            lat = objY;
            lon = objX;
        }
        return new Double[]{lon, lat};
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
        final URL source = new URL(sourceURL);
        final URLConnection conec = source.openConnection();
        Object harvested = null;

        try {
            // for a POST request
            if (request != null) {

                conec.setDoOutput(true);
                conec.setRequestProperty("Content-Type", "text/xml");
                final OutputStreamWriter wr = new OutputStreamWriter(conec.getOutputStream());
                final StringWriter sw = new StringWriter();
                try {
                    marshaller.marshal(request, sw);
                } catch (JAXBException ex) {
                    LOGGER.log(Level.SEVERE, "Unable to marshall the request: ", ex);
                }
                final String xmlRequest = sw.toString();
                wr.write(xmlRequest);
                wr.flush();
                wr.close();
            }

            // we get the response document
            final InputStream in = conec.getInputStream();
            final StringWriter out = new StringWriter();
            final byte[] buffer = new byte[1024];
            int size;

            while ((size = in.read(buffer, 0, 1024)) > 0) {
                out.write(new String(buffer, 0, size));
            }

            //we convert the brut String value into UTF-8 encoding
            String brutString = out.toString();
            final String percentKey = "percent";
            //we need to replace % character by "percent because they are reserved char for url encoding
            brutString = brutString.replaceAll("%", percentKey);
            String decodedString = URLDecoder.decode(brutString, "UTF-8");

            try {
                decodedString = decodedString.replaceAll(percentKey, "%");
                if (unmarshaller == null) {
                    return decodedString;
                } else {
                    harvested = unmarshaller.unmarshal(new StringReader(decodedString));
                    if (harvested instanceof JAXBElement) {
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
     * Put an Abstract model base to the session map from a UIWidgetBase component.
     * @param facesContext
     * @param comp
     */
    public static void setModelAtSession(FacesContext facesContext, UIWidgetBase comp) {
        final Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        final String compClientId = comp.getClientId(facesContext);
        session.put(compClientId + "_model", comp.getModel());
        if (comp.isDebug()) {
            LOGGER.log(Level.INFO, "Model saved at the  session map for the component,  clientId : " + compClientId + "");
        }
    }

    /**
     * Return a geotk SimpleFeature Collection from a Feature List.
     * @param featList List of feature objects.
     * @return FeatureCollection of geotk SimpleFeature.
     * 
     */
    public static FeatureCollection<SimpleFeatureType, SimpleFeature> getSimpleFeaturesFromFeatures(List<Feature> featList) {
        //building a FeatureCollection for this layer.
        FeatureCollection<SimpleFeatureType, SimpleFeature> simpleFeatures = FeatureCollectionUtilities.createCollection();
        if (featList != null) {
            long featureId = 0;
            final SimpleFeatureTypeBuilder builder;

            if (featList.size() != 0) {
                builder = getSimpleFeatureTypeBuilderFromFeature(featList.get(0));
                SimpleFeatureType sft = builder.buildFeatureType();
                for (Feature f : featList) {
                    simpleFeatures.add(getSimpleFeatureFromFeature(f, sft, featureId));
                    featureId++;
                }
            }
        }
        return simpleFeatures;
    }
 /*
     *
     */
    public static Feature getFeatureFromWKT(String id, String name, String wkt, CoordinateReferenceSystem crs) throws ParseException {
        final Feature feat = new DefaultFeature();
        feat.setId(id);
        feat.setName(name);
        final Geometry geom = (new WKTReader()).read(wkt);
        feat.setGeometry(geom);
        feat.setAttributes(new HashMap<String, Serializable>());
        feat.getAttributes().put("geometry", geom);
        feat.setCrs(crs);
        return feat;
    }
    /*
     *
     */
    public static Feature getFeatureFromWKT(String name, String wkt, CoordinateReferenceSystem crs) throws ParseException, NoSuchAuthorityCodeException, FactoryException {
        final Feature feat = new DefaultFeature();
        feat.setId(name + ";" + wkt);
        feat.setName(name);
        final Geometry geom = (new WKTReader()).read(wkt);
        feat.setGeometry(geom);
        feat.setAttributes(new HashMap<String, Serializable>());
        feat.getAttributes().put("geometry", geom);
        if (crs == null)
            crs = CRS.decode(MapUtils.DEFAULT_EPSG_CODE);
        feat.setCrs(crs);
        return feat;
    }
    
    public static SimpleFeature getSimpleFeatureFromFeature(Feature feature, long featureId) {
        SimpleFeatureTypeBuilder builder = getSimpleFeatureTypeBuilderFromFeature(feature);
        return getSimpleFeatureFromFeature(feature, builder.buildFeatureType(), featureId);
    }

    private static SimpleFeature getSimpleFeatureFromFeature(Feature feature, SimpleFeatureType sft, long featureId) {
        List<Object> objects = new ArrayList<Object>();
        for (String key : feature.getAttributes().keySet()) {
            objects.add(feature.getAttributes().get(key));
        }
        return new DefaultSimpleFeature(objects, sft, new DefaultFeatureId(String.valueOf(featureId)));
    }

    private static SimpleFeatureTypeBuilder getSimpleFeatureTypeBuilderFromFeature(Feature feature) {
        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName(feature.getName());
        for (String key : feature.getAttributes().keySet()) {
            if (key.equals("geometry")) {
                builder.add(key, Geometry.class,feature.getCrs());
            } else {
                builder.add(key, feature.getAttributes().get(key).getClass());
            }
        }

        return builder;
    }

    public static Context getContext(final FacesContext facesContext, final String fileUrl, final boolean debug) throws FileNotFoundException, MalformedURLException {
        String filePath = null;

        if (fileUrl.startsWith("file://")) {

            if (fileUrl.startsWith("file://.sicade")) {
                filePath = fileUrl.replaceFirst("file://", System.getProperty("user.home"));

                if (System.getProperty("os.name", "").startsWith("Windows")) {
                    filePath = filePath.replaceFirst(".sicade", "\\Application Data\\Sicade");

                } else {
                    filePath = filePath.replaceFirst(".sicade", "/.sicade");
                }

            } else {
                // remove the "file://" prefix
                filePath = fileUrl.replaceFirst("file://", "");
            }

            if (debug) {
                LOGGER.log(Level.INFO, "[DEBUG] [Try to load mapcontext file] path = " + filePath);
            }

            try {
                return XMLContextUtilities.readContext(new FileReader(new File(filePath)));

            } catch (JAXBException ex) {
                LOGGER.log(Level.SEVERE, null, ex);

            } catch (UnsupportedEncodingException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

        } else {
            //if fileUrl is an URL

            //if fileUrl is a complete URL
            if (fileUrl.startsWith("http://")) {
                filePath = fileUrl;

            } else {
                //if fileUrl is a relative path URL

                //TODO: probably doesn't work with portlet container, use function WebContainerUtils.getRequestURL
                filePath = WebContainerUtils.getRequestURL(facesContext);

                if (fileUrl.startsWith("/")) {
                    filePath += fileUrl;

                } else {
                    final String servletPath = facesContext.getExternalContext().getRequestServletPath();
                    filePath += servletPath.substring(0, servletPath.lastIndexOf("/") + 1) + fileUrl;
                }
            }

            if (debug) {
                LOGGER.log(Level.INFO, "[DEBUG] [Try to load mapcontext file] url = " + filePath);
            }

            try {
                return XMLContextUtilities.readContext(new URL(filePath));

            } catch (JAXBException ex) {
                LOGGER.log(Level.SEVERE, null, ex);

            } catch (UnsupportedEncodingException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
