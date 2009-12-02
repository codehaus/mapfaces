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

package org.mapfaces.renderkit.html.layer;

import com.vividsolutions.jts.geom.Geometry;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.geotoolkit.data.collection.FeatureCollection;
import org.geotoolkit.data.FeatureCollectionUtilities;
import org.geotoolkit.feature.simple.DefaultSimpleFeature;
import org.geotoolkit.feature.simple.SimpleFeatureTypeBuilder;
import org.geotoolkit.filter.identity.DefaultFeatureId;
import org.geotoolkit.map.FeatureMapLayer;
import org.geotoolkit.map.MapBuilder;
import org.geotoolkit.map.MapContext;
import org.geotoolkit.referencing.CRS;
import org.geotoolkit.style.MutableStyle;

import org.mapfaces.component.layer.UIFeatureLayer;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.models.Context;
import org.mapfaces.models.Feature;
import org.mapfaces.models.layer.FeatureLayer;
import org.mapfaces.renderkit.html.LayerRenderer;
import org.mapfaces.util.FacesMapUtils;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * @author Mehdi Sidhoum (Geomatys).
 * @author Olivier Terral (Geomatys).
 */
public class FeatureLayerRenderer extends LayerRenderer {

    private static final Logger LOGGER = Logger.getLogger(FeatureLayerRenderer.class.getName());

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {

        super.encodeBegin(context, component);

        final UIFeatureLayer comp = (UIFeatureLayer) component;
        if (debug) {
            LOGGER.log(Level.INFO, "[DEBUG] ENCODE BEGIN  clientId : " + comp.getClientId(context) + ", id : " + comp.getId());
        }

        final ResponseWriter writer = context.getResponseWriter();
        final String clientId = comp.getClientId(context);
        final String id = comp.getAttributes().get("id").toString();
        final FeatureLayer layer = (FeatureLayer) comp.getLayer();
        final Context model = (Context) comp.getModel();
        FacesMapUtils.setModelAtSession(context, comp);

        final String srs = model.getSrs();
        final CoordinateReferenceSystem crs;
        try {
            crs = CRS.decode(srs);
        } catch (FactoryException ex) {
            LOGGER.log(Level.SEVERE, "Invalid SRS definition : " + srs, ex);
            return;
        }

        //fix case when width and height are null, default is dim (1,1)
        //because openlayers lib will recalculate the good dimension;
        String width="1";
        String height="1";
        if(model.getWindowWidth() != null &&  model.getWindowWidth().matches("[0-9]+")) {
            width = model.getWindowWidth();
        }
        if(model.getWindowHeight() != null && model.getWindowHeight().matches("[0-9]+")) {
            height = model.getWindowHeight();
        }
        final Dimension dim = new Dimension(
                Integer.parseInt(width),
                Integer.parseInt(height));

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
            LOGGER.log(Level.WARNING, "Layer is null ");
            hidden = false;
            opacity = "1";
        }

        final String styleImg = "filter:alpha(opacity=" + (new Float(opacity) * 100) + ");opacity:" + opacity + ";";
        final String display = (hidden) ? "display:none" : "display:block;";
        final int size = (comp.getSize() != 0) ? comp.getSize() : 16;
        final double rotation = comp.getRotation();

        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "id");
        writer.writeAttribute("class", "layerDiv", "style");
        writer.writeAttribute("style", display + "position: absolute; width: 100%; height: 100%; z-index: 100;" + comp.getStyle(), "style");

        UIMapPane mappane = FacesMapUtils.getParentUIMapPane(context, comp);
        MapContext mapContext;

        final int indexLayer = comp.getBindingIndex();
        final MutableStyle mutableStyle = FacesMapUtils.createStyle(comp.getImage(), size, rotation, indexLayer);

        FeatureCollection<SimpleFeatureType, SimpleFeature> simpleFeatures = FacesMapUtils.getSimpleFeaturesFromFeatures(comp.getFeatures());
        
        final FeatureMapLayer mapLayer = MapBuilder.createFeatureLayer(simpleFeatures, mutableStyle);
        
        mapContext = MapBuilder.createContext(crs);
        mapContext.layers().add(mapLayer);

        if (this.debug) {
            LOGGER.log(Level.INFO, "mapContext.layers().size() = " + mapContext.layers().size());
        }

        //if we want to load just one layer of the mapContext
//        if (mapContext.layers().size() > 1 && comp.getIndex() > -1) {
//            // draw a single layer from the map context
//            MapLayer maplayer = mapContext.layers().get(comp.getIndex());
//            mapContext.layers().removeAll(mapContext.layers());
//            mapContext.layers().add(maplayer);
//        }

        setMapContextAtSession(context, comp, mapContext);

        //Add layer image if not the first page loads
        if (mappane.getInitDisplay() && !hidden) {
            writer.startElement("div", comp);
            writer.writeAttribute("style", "overflow: hidden; position: absolute; z-index: 1; left: 0px; top: 0px; width: " + dim.width + "px; height: " + dim.height + "px;" + styleImg + display, "style");

            //Set the img url
            String url = null;
            String viewId = context.getViewRoot().getViewId();
            String actionURL = context.getApplication().getViewHandler().getActionURL(context, viewId);
            url = actionURL + "?ts=" + System.currentTimeMillis() + "&mfLayerId=" + clientId + "&tmp=" + Math.random();
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

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        super.encodeEnd(context, component);
    }

     @Override
    public void decode(final FacesContext context, final UIComponent component) {
        super.decode(context, component);
     }    

    @Override
    public boolean getRendersChildren() {
        return false;//This component does not have any children.
    }
}
