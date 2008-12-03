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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.geotools.referencing.CRS;

import org.geotools.style.MutableStyle;
import org.geotools.style.RandomStyleFactory;
import org.geotools.style.StyleConstants;
import org.geotools.style.StyleFactory;
import org.mapfaces.component.UIMFLayer;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Feature;
import org.mapfaces.models.Layer;
import org.mapfaces.util.FacesUtils;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.expression.Expression;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.style.AnchorPoint;
import org.opengis.style.Displacement;
import org.opengis.style.ExternalGraphic;
import org.opengis.style.Fill;
import org.opengis.style.Graphic;
import org.opengis.style.GraphicalSymbol;
import org.opengis.style.Mark;
import org.opengis.style.PointSymbolizer;
import org.opengis.style.Stroke;

/**
 * @author Mehdi Sidhoum.
 */
public class MFLayerRenderer extends WidgetBaseRenderer {

    private static final Logger LOGGER = Logger.getLogger("org.mapfaces.renderkit.html.MFLayerRenderer");

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
        final Dimension dim = new Dimension(
                Integer.parseInt(model.getWindowWidth()),
                Integer.parseInt(model.getWindowHeight()));

        final String styleImg = "filter:alpha(opacity=" + (new Float(layer.getOpacity()) * 100) + ");opacity:" + layer.getOpacity() + ";";
        final String display = (layer.isHidden()) ? "display:none" : "display:block;";
        final int size = (comp.getSize() != 0) ? comp.getSize() : 16;
        final double rotation = comp.getRotation();

        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "style");
        writer.writeAttribute("class", "layerDiv", "style");
        writer.writeAttribute("style", display + "position: absolute; width: 100%; height: 100%; z-index: 100;" + comp.getStyle(), "style");


        //Add layer image if not the first page loads
        if (FacesUtils.getParentUIMapPane(context, comp).getInitDisplay() && !layer.isHidden()) {
            writer.startElement("div", comp);
            writer.writeAttribute("style", "overflow: hidden; position: absolute; z-index: 1; left: 0px; top: 0px; width: " + dim.width + "px; height: " + dim.height + "px;" + styleImg + display, "style");

            final String srs = model.getSrs();
            final CoordinateReferenceSystem crs;
            try {
                crs = CRS.decode(srs);
            } catch (FactoryException ex) {
                LOGGER.log(Level.SEVERE, "Invalid SRS definition : " + srs, ex);
                writer.endElement("div");
                return;
            }

            final ReferencedEnvelope env = new ReferencedEnvelope(
                    new Double(model.getMinx()), new Double(model.getMaxx()),
                    new Double(model.getMiny()), new Double(model.getMaxy()),
                    crs);
            final FeatureMapLayer mapLayer;


            String urlImage = comp.getImage();
            StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory(null);
//            RandomStyleFactory randomfactStyle = new RandomStyleFactory();
//            OnLineResource rsc = null;
//            try {
//                System.out.println(">>>>>>>>>>>>>   urlImage = "+urlImage);
//                rsc = new OnLineResourceImpl(new URI("http://demo.geomatys.fr/mdweb2/resources/img/pushpins/1.png"));
//                
//            } catch (URISyntaxException ex) {
//                Logger.getLogger(MFLayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
//            }
            String format = "image/png";
            ImageIcon icon = new ImageIcon(new URL(urlImage));
            
            ExternalGraphic external = styleFactory.createExternalGraphic(icon, format, null);
            
//            Expression fillColor = styleFactory.colorExpression(Color.RED);
//            Expression opacity = styleFactory.literalExpression(0.4d);
//            Fill fill = styleFactory.createFill(null, fillColor, opacity);
//
//            Expression color = styleFactory.colorExpression(Color.WHITE);
//            Expression width = styleFactory.literalExpression(0.5d);
//            Expression join = styleFactory.literalExpression("bevel");
//            Expression cap = styleFactory.literalExpression("round");
//            float[] dashes = null;
//            Expression strokeOffset = styleFactory.literalExpression(0d);
//
//            Stroke stroke = styleFactory.createStroke(color, opacity, width, join, cap, dashes, strokeOffset);
//
//            Mark marker = styleFactory.createMark(StyleConstants.MARK_CIRCLE, fill, stroke);

            List<GraphicalSymbol> symbols = new ArrayList<GraphicalSymbol>();
            Expression opacity = styleFactory.literalExpression(1d);
            symbols.add(external);
//            symbols.add(marker);

            Expression expSize = styleFactory.literalExpression(size);
            Expression expRotation = styleFactory.literalExpression(rotation);

            AnchorPoint anchor = styleFactory.createAnchorPoint(0.5, 1); //for markers we need to move the anchor point to the img bottom.
            Displacement disp = null;
            Graphic graphic = styleFactory.createGraphic(symbols, opacity, expSize, expRotation, anchor, disp);

            PointSymbolizer symbol = styleFactory.createPointSymbolizer(graphic, "");
            MutableStyle mutableStyle = styleFactory.createStyle(symbol);
            
            //List of rules for this style.
            // mutableStyle.featureTypeStyles().get(0).rules();

            //building a FeatureCollection for this layer.
            FeatureCollection<SimpleFeatureType, SimpleFeature> features = FeatureCollections.newCollection();
            long featureId = 0;
            for (Feature f : comp.getFeatures()) {
                SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
                builder.setName(f.getName());
                builder.setCRS(f.getCrs());

                List<Object> objects = new ArrayList<Object>();

                for (String key : f.getAttributes().keySet()) {
                    builder.add(key, f.getAttributes().get(key).getClass());
                    objects.add(f.getAttributes().get(key));
                }

                SimpleFeatureType sft = builder.buildFeatureType();


                SimpleFeature sf = new SimpleFeatureImpl(objects, sft, new FeatureIdImpl(String.valueOf(featureId)));
                features.add(sf);
                featureId++;
            }


            mapLayer = MapBuilder.getInstance().createFeatureLayer(features, mutableStyle);

            MapContext mapContext = MapBuilder.getInstance().createContext(crs);
            mapContext.layers().add(mapLayer);

            BufferedImage bufferImage;
            try {
                System.out.println("[PORTRAYING] mapContext = " + mapContext + "   env = " + env + "   dim = " + dim);
                bufferImage = DefaultPortrayalService.getInstance().portray(mapContext, env, dim, true);
                File dst = File.createTempFile("img", "", comp.getDir());
                ImageIO.write(bufferImage, "png", dst);

                String generatedImage = comp.getContextPath() + "/" + comp.getDir().getName() + "/" + dst.getName();

                writer.startElement("img", comp);
                writer.writeAttribute("id", id + "_Img", "style");
                writer.writeAttribute("class", "layerImg", "style");

                if (styleImg != null) {
                    writer.writeAttribute("style", "position:relative;", "style");
                }

                writer.writeAttribute("src", generatedImage, "src");
                writer.endElement("img");

            } catch (PortrayalException ex) {
                Logger.getLogger(MFLayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.endElement("div");

        }
        writer.endElement("div");
        writer.flush();
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

        final StringBuilder sb = new StringBuilder("Layer decode :");
        sb.append(" Model Id = ").append(model.getId());
        sb.append(" MFLayer Id = ").append(layer.getId());
        sb.append(" Window Height = ").append(model.getWindowHeight());
        sb.append(" Window Width = ").append(model.getWindowWidth());

        if (context.getExternalContext().getRequestParameterMap() == null) {
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

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null) {
            throw new NullPointerException("context should not be null");
        }
        if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }
}
