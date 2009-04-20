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
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.geotools.ows.ServiceException;

import org.geotoolkit.wms.GetMapRequest;
import org.geotoolkit.wms.WebMapServer;
import org.geotoolkit.wms.map.WMSMapLayer;

import org.mapfaces.component.layer.UIWmsLayer;
import org.mapfaces.models.Context;
import org.mapfaces.models.layer.DefaultWmsGetMapLayer;
import org.mapfaces.models.layer.WmsLayer;
import org.mapfaces.util.FacesUtils;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class WmsLayerRenderer extends LayerRenderer {

    private static final Logger LOGGER = Logger.getLogger("org.mapfaces.renderkit.html.WmsLayerRenderer");

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

        if (model == null) {
            if (this.debug) {
                LOGGER.log(Level.INFO, "[DEBUG] model is null ");
            } else if (this.debug) {
                LOGGER.log(Level.INFO, "[DEBUG] model id : " + model.getId());
            }
        }
        final String opacity = (layer.getOpacity() != null) ? layer.getOpacity() : "1";

        final String styleImg = "filter:alpha(opacity=" + ( Float.parseFloat(opacity) * 100) + ");opacity:" + opacity + ";";
        final String display = (layer.isHidden()) ? "display:none" : "display:block;";

        //Create the Layer_WMS_0 div
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "style");
        writer.writeAttribute("class", "layerDiv", "style");
        writer.writeAttribute("style", display + "position: absolute; width: 100%; height: 100%; z-index: 100;" + comp.getStyle(), "style");

        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] layer should be displayed ?  " + (FacesUtils.getParentUIMapPane(context, comp).getInitDisplay() && !layer.isHidden()));        //Add layer image if not the first page loads
        }
        if (FacesUtils.getParentUIMapPane(context, comp).getInitDisplay() && !layer.isHidden()) {

            final Dimension dim = new Dimension(
                    Integer.parseInt(model.getWindowWidth()),
                    Integer.parseInt(model.getWindowHeight()));

            //Write the image DIV
            writer.startElement("div", comp);
            writer.writeAttribute("style", "overflow: hidden; position: absolute; z-index: 1; left: 0px; top: 0px; width: " + dim.width + "px; height: " + dim.height + "px;" + styleImg + display, "style");

            //Write the image element  -------------------------------------
            writer.startElement("img", comp);
            writer.writeAttribute("id", comp.getId() + "_Img", "style");
            writer.writeAttribute("class", "layerImg", "style");

            if (styleImg != null) {
                writer.writeAttribute("style", "position:relative;", "style");
            }

            URL url = new URL("http://");

            //Generate the URL contents
            // 1. recuperate the existing info
            WMSMapLayer mapLayer = null;
            if (!(layer instanceof DefaultWmsGetMapLayer)) {

                try {
                    mapLayer = createWMSMapLayer(layer);
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, "Could not create wms map layer.", ex);
                    //TODO should close divs and writer correctly is this happens
                    writer.writeAttribute("src", url.toString(), "src");
                    writer.endElement("img");
                    writer.endElement("div");
                    return;
                } catch (ServiceException ex) {
                    LOGGER.log(Level.SEVERE, "Could not create wms map layer.", ex);
                    //TODO should close divs and writer correctly is this happens
                    writer.writeAttribute("src", url.toString(), "src");
                    writer.endElement("img");
                    writer.endElement("div");
                    return;
                }
            }

            // 2. define the new extent
            final String srs = model.getSrs();
            final double[] imgExtentLowerCorner = {new Double(model.getMinx()), new Double(model.getMiny())};
            final double[] imgExtentUpperCorner = {new Double(model.getMaxx()), new Double(model.getMaxy())};

            // 3. get the URL fragment
            if (mapLayer != null) {
                GetMapRequest request = mapLayer.createGetMapRequest();
                request.setDimension(dim);
                request.setEnvelope(model.getEnvelope());
                url = request.getURL();
            }

            if (layer instanceof DefaultWmsGetMapLayer && layer.getUrlGetMap() != null) {
                
                String begin = layer.getUrlGetMap().substring(0, layer.getUrlGetMap().indexOf("&SLD_BODY"));
                String temp = layer.getUrlGetMap().substring(layer.getUrlGetMap().indexOf("&SLD_BODY"));
                begin += "&TRANSPARENT=TRUE";
                begin += temp;
                
                String completeUrl = begin;
                if (! completeUrl.contains("SRS=")) {
                    completeUrl = completeUrl.concat("&SRS=");
                }
                if (! completeUrl.contains("BBOX=")) {
                    completeUrl = completeUrl.concat("&BBOX=");
                }
                if (! completeUrl.contains("WIDTH=")) {
                    completeUrl = completeUrl.concat("&WIDTH=");
                }
                if (! completeUrl.contains("HEIGHT=")) {
                    completeUrl = completeUrl.concat("&HEIGHT=");
                }
                
                completeUrl = FacesUtils.setParameterValueAndGetUrl("SRS", srs, completeUrl);
                completeUrl = FacesUtils.setParameterValueAndGetUrl("BBOX", imgExtentLowerCorner[0] + "," + imgExtentLowerCorner[1] + "," + imgExtentUpperCorner[0] + "," + imgExtentUpperCorner[1], completeUrl);
                completeUrl = FacesUtils.setParameterValueAndGetUrl("WIDTH", String.valueOf(dim.getWidth()), completeUrl);
                completeUrl = FacesUtils.setParameterValueAndGetUrl("HEIGHT", String.valueOf(dim.getHeight()), completeUrl);
                
                url = new URL(completeUrl);
            }

            if (this.debug) {
                LOGGER.log(Level.INFO, "[WmsLayerRenderer] URL : " + url);
            }
            
            if ( ! (layer instanceof DefaultWmsGetMapLayer) ) {
                writer.writeAttribute("src", url.toString(), "src");
            }else {
                /* if this layer is an instance of DefaultWmsGetMapLayer then the src attribute must be an empty string due to a strange behaviour on web browser with too longer urls.
                 * a temporary solution is to store the getMap url into a javascript variable and then set this src attribute by the js method document.getElementById( this img tag id ).
                */
                writer.writeAttribute("src", "", "src");
            }
            writer.endElement("img");
            
            //@TODO this is a hack to resolve the strange behaviour when the url is too longer for getMap layers only.
            if (layer instanceof DefaultWmsGetMapLayer) {
                writer.write("<script>var url"+comp.getId() + "_ImgSrc = '"+url.toString()+"';\n document.getElementById('" + comp.getId() + "_Img').src=url"+comp.getId() + "_ImgSrc;\n </script>\n");
            }
            
            writer.endElement("div");

        }
        writer.endElement("div");
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

    public WMSMapLayer createWMSMapLayer(final WmsLayer layer) throws IOException, ServiceException {

        // to avoid a NullPointerException when creating an object org.geotools.data.wms.WebMapServer.
        if (layer == null || layer.getServer() == null ) {
            if (layer.getUrlGetMap() == null) {
                LOGGER.log(Level.SEVERE, "[WmsLayerRenderer] Error the getcapabilities returned null !!!!!  url = " + layer.getServer().getHref());
                return null;
            }else {
                //in this case there is a getMap url attached on the Layer, then we skip the building of WMSMapLayer object.
                return null;
            }
        }

        final WebMapServer server = new WebMapServer(new URL(layer.getServer().getHref()), layer.getServer().getVersion());
        final WMSMapLayer mapLayer = new WMSMapLayer(server, layer.getName());
        final HashMap<String, org.mapfaces.models.Dimension> dims = layer.getDimensionList();
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
