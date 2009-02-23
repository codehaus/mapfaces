
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

import org.mapfaces.renderkit.html.*;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.geotools.map.MapContext;
import org.geotools.referencing.CRS;

import org.mapfaces.component.UILayer;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.layer.UIMapContextLayer;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.util.FacesUtils;

import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class MapContextLayerRenderer extends LayerRenderer {

    private static final Logger LOGGER = Logger.getLogger("org.mapfaces.renderkit.html.layer.MapContextLayerRenderer");
    private final static Color colors[] = {Color.CYAN, Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLUE, Color.ORANGE, Color.WHITE, Color.PINK};

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        
        super.encodeBegin(context, component);        
        
        final UIMapContextLayer comp = (UIMapContextLayer) component;
        
        if (debug) {
            LOGGER.log(Level.INFO, "[DEBUG] clientId : " + comp.getClientId(context) + ", id : " + comp.getId());
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
            LOGGER.log(Level.WARNING, "[MapContextLayerRenderer] layer is null ");
            hidden = false;
            opacity = "1";
        }

        final String styleImg = "filter:alpha(opacity=" + (new Float(opacity) * 100) + ");opacity:" + opacity + ";";
        final String display = (hidden) ? "display:none" : "display:block;";
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "style");
        writer.writeAttribute("class", "layerDiv", "style");
        writer.writeAttribute("style", display + "position: absolute; width: 100%; height: 100%; z-index: 100;" + comp.getStyle(), "style");



        UIMapPane mappane = FacesUtils.getParentUIMapPane(context, comp);
        MapContext mapContext = null;

        if (mappane.getValueExpression("value") != null) {
            ValueExpression ve = mappane.getValueExpression("value");
            mapContext = (MapContext) ve.getValue(context.getELContext());
            setMapContextAtSession(context, comp, mapContext);
        }

        if (debug) {
            LOGGER.log(Level.INFO, "[DEBUG] layer should be displayed ?  " + (FacesUtils.getParentUIMapPane(context, comp).getInitDisplay() && !hidden));        //Add layer image if not the first page loads
        }
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
        return false;
    }

    public void setMapContextAtSession(FacesContext facesContext, UILayer comp, MapContext context) {
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String clientId = comp.getClientId(facesContext);
        session.put(clientId + "_mapContext", context);
        if (debug) {
            LOGGER.log(Level.INFO, "[MapContextLayerRenderer] mapcontext saved in  session map for this layer,  clientId : " + clientId + "\n");
        }
    }
}
