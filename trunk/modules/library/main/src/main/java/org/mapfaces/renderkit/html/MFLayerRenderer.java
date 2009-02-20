
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
package org.mapfaces.renderkit.html;


import com.vividsolutions.jts.geom.Geometry;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
import org.mapfaces.component.UIMFLayer;
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

public class MFLayerRenderer extends WidgetBaseRenderer {

    private static final Logger LOGGER = Logger.getLogger("org.mapfaces.renderkit.html.MFLayerRenderer");
    private final static Color colors[] = {Color.CYAN, Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLUE, Color.ORANGE, Color.WHITE, Color.PINK};

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        assertValid(context, component);
        final UIMFLayer comp = (UIMFLayer) component;
        
        // suppress rendering if "rendered" property on the component is false.
        if (!comp.isRendered()) {
            return;
        }
        final ResponseWriter writer = context.getResponseWriter();
        final String clientId = comp.getClientId(context);
        final String id = comp.getAttributes().get("id").toString();
        final Layer layer = comp.getLayer();
        final Context model = (Context) comp.getModel();
        setModelAtSession(context, comp, model);
        
        final String srs = model.getSrs();
        final CoordinateReferenceSystem crs;
        try {
            crs = CRS.decode(srs);
        } catch (FactoryException ex) {
            LOGGER.log(Level.SEVERE, "Invalid SRS definition : " + srs, ex);
            return;
        }
        
        final Dimension dim = new Dimension(
                Integer.parseInt(model.getWindowWidth()),
                Integer.parseInt(model.getWindowHeight()));

        // test if Dimension is not valid, width and height must be > 0.
        if (dim.width <= 0) {
            dim.width = 1;
        }
        if (dim.height <= 0) {
            dim.height = 1;
        }

        final boolean hidden;
        final String opacity;
        if (layer != null) {
            hidden = layer.isHidden();
            opacity = layer.getOpacity();
        } else {
            hidden = false;
            opacity = "1";
        }

        final String styleImg = "filter:alpha(opacity=" + (new Float(opacity) * 100) + ");opacity:" + opacity + ";";
        final String display = (hidden) ? "display:none" : "display:block;";
        final int size = (comp.getSize() != 0) ? comp.getSize() : 16;
        final double rotation = comp.getRotation();

        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "style");
        writer.writeAttribute("class", "layerDiv", "style");
        writer.writeAttribute("style", display + "position: absolute; width: 100%; height: 100%; z-index: 100;" + comp.getStyle(), "style");



        UIMapPane mappane = FacesUtils.getParentUIMapPane(context, comp);
        MapContext mapContext;
        if (mappane.getValueExpression("value") != null) {
            ValueExpression ve = mappane.getValueExpression("value");
            mapContext = (MapContext) ve.getValue(context.getELContext());
        } else {

            final int indexLayer = comp.getBindingIndex();
            final MutableStyle mutableStyle = createStyle(comp.getImage(), size, rotation, indexLayer);

            //building a FeatureCollection for this layer.
            FeatureCollection<SimpleFeatureType, SimpleFeature> features = FeatureCollections.newCollection();
            long featureId = 0;
            SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
            if (comp.getFeatures() != null && comp.getFeatures().size() != 0) {
                Feature f = comp.getFeatures().get(0);
                builder.setName(f.getName());
                builder.setCRS(f.getCrs());
                for (String key : f.getAttributes().keySet()) {
                    if (key.equals("geometry")) {
                        builder.add(key, Geometry.class);
                    } else {
                        builder.add(key, f.getAttributes().get(key).getClass());
                    }
                }
            }
            SimpleFeatureType sft = builder.buildFeatureType();
            for (Feature f : comp.getFeatures()) {
                List<Object> objects = new ArrayList<Object>();
                for (String key : f.getAttributes().keySet()) {
                    objects.add(f.getAttributes().get(key));
                }
                
                String simpleFeatureId = String.valueOf(featureId);
                if (f.getId() != null) {
                    simpleFeatureId = f.getId();
                }
                
                SimpleFeature sf = new SimpleFeatureImpl(objects, sft, new FeatureIdImpl(simpleFeatureId));
                features.add(sf);
                featureId++;
            }


            final FeatureMapLayer mapLayer = MapBuilder.getInstance().createFeatureLayer(features, mutableStyle);
            mapContext = MapBuilder.getInstance().createContext(crs);
            mapContext.layers().add(mapLayer);
            
        }
        
        //if we want to load just one layer of the mapContext
        if (mapContext.layers().size() > 1 &&  comp.getIndex() > -1) {            
            // draw a single layer from the map context
            MapLayer maplayer = mapContext.layers().get(comp.getIndex());
            mapContext.layers().removeAll(mapContext.layers());
            mapContext.layers().add(maplayer);
        }
        
        setMapContextAtSession(context, comp, mapContext);
        
        //Add layer image if not the first page loads
        if (mappane.getInitDisplay() && !hidden) {
            writer.startElement("div", comp);
            writer.writeAttribute("style", "overflow: hidden; position: absolute; z-index: 1; left: 0px; top: 0px; width: " + dim.width + "px; height: " + dim.height + "px;" + styleImg + display, "style");

            //Set the chartURL
            String url = null;
            String viewId = context.getViewRoot().getViewId();
            String actionURL = context.getApplication().getViewHandler().getActionURL(context, viewId);            
            url = actionURL + "?ts=" + System.currentTimeMillis() + "&mfLayerId=" + clientId;
            writer.startElement("img", comp);
            writer.writeAttribute("id", id + "_Img", "style");
            writer.writeAttribute("class", "layerImg", "style");
            if (styleImg != null) {
                writer.writeAttribute("style", "position:relative;", "style");
            }
            writer.writeAttribute("src", url, "src");
            writer.endElement("img");
            writer.endElement("div");

        }
        writer.endElement("div");
        writer.flush();
    }

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

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        return;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {

        final UIMFLayer comp = (UIMFLayer) component;
        final Context model = (Context) comp.getModel();
        final Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        final Layer layer = comp.getLayer();
        final String formId = FacesUtils.getFormId(context, comp);
        UIMapPane mappane = FacesUtils.getParentUIMapPane(context, comp);

        final StringBuilder sb = new StringBuilder("Layer decode :");
        sb.append(" Model Id = ").append(model.getId());
        sb.append(" MFLayer Id = ").append(layer.getId());
        sb.append(" Window Height = ").append(model.getWindowHeight());
        sb.append(" Window Width = ").append(model.getWindowWidth());

        if (context.getExternalContext().getRequestParameterMap() == null && mappane.isDebug()) {
            System.out.println(sb.toString());
            return;
        }

        if (params.get("refresh") != null && params.get("refresh").equals(comp.getClientId(context))) {
            final String bbox = params.get("bbox");
            sb.append("\n BBox = " + bbox);
            if (bbox != null && !bbox.equals(model.getMinx() + "," + model.getMiny().toString() + "," + model.getMaxx() + "," + model.getMaxy())) {
                model.setMinx(bbox.split(",")[0]);
                model.setMiny(bbox.split(",")[1]);
                model.setMaxx(bbox.split(",")[2]);
                model.setMaxy(bbox.split(",")[3]);
            }
            String win = params.get("window");
            if (win == null) {
                win = params.get(formId + ":window");
            }
            if (win != null) {
                final String[] window = win.split(",");
                model.setWindowWidth(window[0]);
                model.setWindowHeight(window[1]);
            }
        }

        String value = params.get("org.mapfaces.ajax.AJAX_COMPONENT_VALUE");
        final String layerId = params.get("org.mapfaces.ajax.AJAX_LAYER_ID");
        if (value == null && params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID") != null) {
            value = params.get(params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID"));
        //layerId = params.get("refresh");
        }

        if (layerId != null) {
            final String layerProperty = params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID");
            if (layerId.equals(comp.getClientId(context))) {

                //Modify Context property
                if (layerProperty.contains("hidden")) {
                    final boolean test = !(value != null && value.equals("on"));
                    layer.setHidden(test);
                } else if (layerProperty.contains("Opacity")) {
                    model.setOpacity(layer.getId(), value);
                } else if (layerProperty.contains("Time")) {
                    if (value == null) {
                        value = "";
                    }
//                    model.setLayerAttrDimension(layer.getId(), "time", "userValue", value);
                } else if (layerProperty.contains("Elevation")) {
                    if (value == null) {
                        value = "";
                    }
//                    model.setLayerAttrDimension(layer.getId(), "elevation", "userValue", value);
                } else if (layerProperty.contains("DimRange")) {
//                    model.setLayerAttrDimension(layer.getId(), "dim_range", "userValue", value);
                }

            }
        }

        //Modify Component property
        if (params.get("org.mapfaces.ajax.LAYER_CONTAINER_STYLE") != null) {
            System.out.println("LAYER_CONTAINER_STYLE changed =" + params.get("org.mapfaces.ajax.LAYER_CONTAINER_STYLE"));
            comp.setStyle(params.get("org.mapfaces.ajax.LAYER_CONTAINER_STYLE"));
        }

        System.out.println(sb.toString());
        comp.setModel((AbstractModelBase) model);
//        comp.setLayer(model.getLayerFromId(layer.getId()));

        return;
    }
    // creates and puts the chart data to session for this chart object
    private void setModelAtSession(FacesContext facesContext, UIMFLayer comp, Context context) {
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String clientId = comp.getClientId(facesContext);
        session.put(clientId + "_model", context);
        LOGGER.log(Level.WARNING, "Model saved in  session map for the layer\n id: " + clientId + "\n");
    }

    private void setMapContextAtSession(FacesContext facesContext, UIMFLayer comp, MapContext context) {
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String clientId = comp.getClientId(facesContext);
        session.put(clientId + "_mapContext", context);
        LOGGER.log(Level.WARNING, "MapContext saved in  session map for the layer\n id: " + clientId + "\n");
    }

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null) {
            throw new NullPointerException("context should not be null");
        }
        if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }
}
