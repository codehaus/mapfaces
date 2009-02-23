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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.geotools.data.wms.WebMapServer;
import org.geotools.map.WMSMapLayer;
import org.geotools.ows.ServiceException;

import org.mapfaces.component.UILayer;
import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.component.layer.UIWmsLayer;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
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
        
        if (this.debug)
            LOGGER.log(Level.INFO, "[DEBUG] clientId : "+ comp.getClientId(context) + ", id : " + comp.getId());
        
        final ResponseWriter writer = context.getResponseWriter();
        final String clientId = comp.getClientId(context);       
        final Context model = (Context) comp.getModel();
        final WmsLayer layer = (WmsLayer) comp.getLayer();
        
        if (model == null)
            if (this.debug)
                LOGGER.log(Level.INFO, "[DEBUG] model is null ");
        else if (this.debug)
            LOGGER.log(Level.INFO, "[DEBUG] model id : "+ model.getId());

        final String styleImg = "filter:alpha(opacity=" + (new Float(layer.getOpacity()) * 100) + ");opacity:" + layer.getOpacity() + ";";
        final String display = (layer.isHidden()) ? "display:none" : "display:block;";
        
        //Create the Layer_WMS_0 div
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "style");
        writer.writeAttribute("class", "layerDiv", "style");
        writer.writeAttribute("style", display + "position: absolute; width: 100%; height: 100%; z-index: 100;" + comp.getStyle(), "style");

        if (this.debug) 
            LOGGER.log(Level.INFO, "[DEBUG] layer should be displayed ?  " + (FacesUtils.getParentUIMapPane(context, comp).getInitDisplay() && !layer.isHidden()));
           
        //Add layer image if not the first page loads
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
                
                //Generqte the URL contents
                // 1. recuperate the existing info
                final WMSMapLayer mapLayer;

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
                
                // 2. define the new extent
                final String srs = model.getSrs();
                double[] imgExtentLowerCorner = { new Double(model.getMinx()), new Double(model.getMiny()) };
                double[] imgExtentUpperCorner = { new Double(model.getMaxx()), new Double(model.getMaxy()) };

                // 3. get the URL fragment
                if (mapLayer != null) {
                    url = mapLayer.getURLforNewView(srs, imgExtentLowerCorner, imgExtentUpperCorner, dim);
                }
                
                if (this.debug) 
                    LOGGER.log(Level.INFO, "[WmsLayerRenderer] URL : "+ url);
                writer.writeAttribute("src", url.toString(), "src");
                writer.endElement("img");
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
        if (layer == null || layer.getServer() == null || layer.getServer().getGTCapabilities() == null) { 
            LOGGER.log(Level.SEVERE, "[WmsLayerRenderer] Error the getcapabilities returned null !!!!!");
            return null;
        }

        final WMSMapLayer mapLayer = new WMSMapLayer(new WebMapServer(layer.getServer().getGTCapabilities()), layer.getName());
        final HashMap<String, org.mapfaces.models.Dimension> dims = layer.getDimensionList();
        if (dims != null) {
            for (final String tmp : dims.keySet()) {
                mapLayer.dimensions().put(tmp, dims.get(tmp).getUserValue());
            }
        }
        mapLayer.setOutputFormat(layer.getOutputFormat());
        mapLayer.setVersion(layer.getServer().getVersion());
        mapLayer.setServerUrl(new URL(layer.getServer().getHref()));
        mapLayer.setVisible(!layer.isHidden());
        mapLayer.setStyles(layer.getStyles());
        if (layer.getSld() != null) {
            mapLayer.setSld(layer.getSld());
        }
        if (layer.getSldBody() != null) {
            mapLayer.setSld_body(layer.getSldBody());
        }
        return mapLayer;
    }
    
}
