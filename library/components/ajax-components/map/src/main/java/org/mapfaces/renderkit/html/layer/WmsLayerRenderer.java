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

import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.geotoolkit.wms.WebMapServer;
import org.geotoolkit.wms.map.WMSMapLayer;

import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.layer.UIWmsLayer;
import org.mapfaces.models.Context;
import org.mapfaces.models.Server;
import org.mapfaces.models.layer.DefaultWmsGetMapLayer;
import org.mapfaces.models.layer.WmsLayer;
import org.mapfaces.renderkit.html.LayerRenderer;
import org.mapfaces.util.FacesMapUtils;
import org.mapfaces.util.MapUtils;
import org.mapfaces.share.utils.RendererUtils.HTML;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class WmsLayerRenderer extends LayerRenderer {

    private static final Logger LOGGER = Logger.getLogger(WmsLayerRenderer.class.getName());

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        super.encodeBegin(context, component);
        final UIWmsLayer comp = (UIWmsLayer) component;

        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] clientId : " + comp.getClientId(context) + ", id : " + comp.getId());
        }
        final ResponseWriter writer = context.getResponseWriter();
        final String clientId = comp.getClientId(context);
        final Context model = (Context) comp.getModel();
        final WmsLayer layer = (WmsLayer) comp.getLayer();
        final UIMapPane mappane = FacesMapUtils.getParentUIMapPane(context, comp);

        if (model == null) {
            if (this.debug) {
                LOGGER.log(Level.INFO, "[DEBUG] model is null ");
            } else if (this.debug) {
                LOGGER.log(Level.INFO, "[DEBUG] model id : " + model.getId());
            }
        }

        final String opacity = (layer.getOpacity() != null) ? layer.getOpacity() : "1";
        final String styleImg = "filter:alpha(opacity=" + (Float.parseFloat(opacity) * 100) + ");opacity:" + opacity + ";";
        final String display = "display:block;";

        //Create the Layer_WMS_0 div
        writer.startElement(HTML.DIV_ELEM, comp);
        writer.writeAttribute(HTML.id_ATTRIBUTE, clientId, HTML.id_ATTRIBUTE);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "layerDiv", "styleClass");
        String style = comp.getStyle();
        if (style == null)
            style = "";
        writer.writeAttribute(HTML.style_ATTRIBUTE, display + "position: absolute; width: 100%; height: 100%; z-index: 100;" + style, HTML.style_ATTRIBUTE);

        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] layer should be displayed ?  " + 
                    (FacesMapUtils.getParentUIMapPane(context, comp).getInitDisplay()
                    && !layer.isHidden() && layer.isDisplayable() && !layer.isDisable()));        //Add layer image if not the first page loads
        }
        layer.setDisplayable(layer.isDisplayable(MapUtils.getScale(model)));

        //If the WMSLayer has a bad server with no getCapabilities, never display this layer.
        if (layer.getServer() == null || layer.getServer().getGTCapabilities() == null) {
            layer.setHidden(true);
            layer.setDisplayable(false);
            layer.setMaxScaleDenominator(new Double (0.0));
            layer.setDisable(true);
        }
        if (mappane.getInitDisplay() && (!layer.isHidden() && layer.isDisplayable())) {
            //Set to 0 by default because if the upper Layer isn't displayable at this extent,
            //MouseWheel events aren't triggered by Img element  under this Layer.
            final Dimension dim = new Dimension(0, 0);
            dim.setSize(Integer.parseInt(model.getWindowWidth()),
                    Integer.parseInt(model.getWindowHeight()));

            //Write the image DIV
            writer.startElement(HTML.DIV_ELEM, comp);
            writer.writeAttribute(HTML.style_ATTRIBUTE, "overflow: hidden; position: absolute; z-index: 1; " +
                    "left: 0px; top: 0px; width: " + dim.width + "px; " +
                    "height: " + dim.height + "px;", HTML.style_ATTRIBUTE);

            //Write the image element  -------------------------------------
            writer.startElement(HTML.IMG_ELEM, comp);
            writer.writeAttribute(HTML.id_ATTRIBUTE, comp.getId() + "_Img", HTML.style_ATTRIBUTE);
            writer.writeAttribute(HTML.class_ATTRIBUTE, "layerImg", HTML.style_ATTRIBUTE);

            if (styleImg != null) {
                writer.writeAttribute(HTML.style_ATTRIBUTE, "position:relative;" + styleImg, HTML.style_ATTRIBUTE);
            } 

            //Generate the URL contents
            // 1. recuperate the existing info
            WMSMapLayer mapLayer = null;

            if (!(layer instanceof DefaultWmsGetMapLayer)) {

                try {
                    mapLayer = createWMSMapLayer(layer);
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, "Could not create wms map layer.", ex);
                    //TODO should close divs and writer correctly is this happens
                    writer.endElement(HTML.IMG_ELEM);
                    writer.endElement(HTML.DIV_ELEM);
                    return;
                }
            }

            // 2. define the new extent
            final String srs = model.getSrs();
            final double[] imgExtentLowerCorner = {new Double(model.getMinx()), new Double(model.getMiny())};
            final double[] imgExtentUpperCorner = {new Double(model.getMaxx()), new Double(model.getMaxy())};
            URL url = new URL("http://");

            // 3. get the URL fragment
            if (mapLayer != null) {
                URL urlImg = mapLayer.query(model.getEnvelope(), dim);
                if (model.getLayerRefresh(layer.getId())) {
                    urlImg = new URL(urlImg.toString().concat("&REFRESH=").concat(Long.toString(System.currentTimeMillis())));
                }
                writer.writeAttribute(HTML.SRC_ATTRIBUTE, urlImg, HTML.SRC_ATTRIBUTE);
            }

            if (layer instanceof DefaultWmsGetMapLayer && layer.getUrlGetMap() != null) {
                String begin = layer.getUrlGetMap().substring(0, layer.getUrlGetMap().indexOf("&SLD_BODY"));
                String temp = layer.getUrlGetMap().substring(layer.getUrlGetMap().indexOf("&SLD_BODY"));
                begin += "&TRANSPARENT=TRUE";
                begin += temp;

                String completeUrl = begin;
                if (!completeUrl.contains("SRS=")) {
                    completeUrl = completeUrl.concat("&SRS=");
                }
                if (!completeUrl.contains("BBOX=")) {
                    completeUrl = completeUrl.concat("&BBOX=");
                }
                if (!completeUrl.contains("WIDTH=")) {
                    completeUrl = completeUrl.concat("&WIDTH=");
                }
                if (!completeUrl.contains("HEIGHT=")) {
                    completeUrl = completeUrl.concat("&HEIGHT=");
                }
                
                completeUrl = FacesMapUtils.setParameterValueAndGetUrl("SRS", srs, completeUrl);
                completeUrl = FacesMapUtils.setParameterValueAndGetUrl("BBOX", imgExtentLowerCorner[0] + "," + imgExtentLowerCorner[1] + "," + imgExtentUpperCorner[0] + "," + imgExtentUpperCorner[1], completeUrl);
                completeUrl = FacesMapUtils.setParameterValueAndGetUrl("WIDTH", String.valueOf(dim.getWidth()), completeUrl);
                completeUrl = FacesMapUtils.setParameterValueAndGetUrl("HEIGHT", String.valueOf(dim.getHeight()), completeUrl);
                url = new URL(completeUrl);
            }

            if (this.debug) {
                LOGGER.log(Level.INFO, "[WmsLayerRenderer] URL : " + url);
            }

            writer.endElement(HTML.IMG_ELEM);

            //@TODO this is a hack to resolve the strange behaviour when the url is too longer for getMap layers only.
            if (layer instanceof DefaultWmsGetMapLayer) {
                writer.write("<script type='text/javascript'>var url" + comp.getId() + "_ImgSrc = '" + url.toString() + "';" +
                        "document.getElementById('" + comp.getId() + "_Img').src=url" + comp.getId() + "_ImgSrc;</script>");
            }

            writer.endElement(HTML.DIV_ELEM);

        }
        writer.endElement(HTML.DIV_ELEM);
        writer.flush();
    }

    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        super.decode(context, component);
    }

    @Override
    public boolean getRendersChildren() {
        return false;
    }

    public WMSMapLayer createWMSMapLayer(final WmsLayer layer) throws IOException {

        // to avoid a NullPointerException when creating an object org.geotoolkit.data.wms.WebMapServer.
        if (layer == null || layer.getServer() == null) {
            if (layer.getUrlGetMap() == null) {
                LOGGER.log(Level.SEVERE, "[WmsLayerRenderer] Error the getcapabilities returned null !!!!!  url = " + layer.getServer().getHref());
                return null;
            } else {
                //in this case there is a getMap url attached on the Layer, then we skip the building of WMSMapLayer object.
                return null;
            }
        }
        Server server = layer.getServer();
        final WebMapServer wepmapserver = new WebMapServer(new URL(server.getHref()), server.getVersion(), server.getGTCapabilities());
        final WMSMapLayer mapLayer = new WMSMapLayer(wepmapserver, layer.getName());
        final HashMap<String, org.mapfaces.models.Dimension> dims = (HashMap) layer.getDimensionList();
        if (dims != null) {
            for (final String tmp : dims.keySet()) {
                mapLayer.dimensions().put(tmp, dims.get(tmp).getUserValue());
            }
        }
        mapLayer.setFormat(layer.getOutputFormat());
        mapLayer.setVisible(!layer.isHidden());
        mapLayer.setStyles(layer.getStyles());
        if (layer.getSld() != null) {
            mapLayer.setSld(layer.getSld());
        }
        if (layer.getSldBody() != null) {
            mapLayer.setSldBody(layer.getSldBody());
        }
        return mapLayer;
    }
}
