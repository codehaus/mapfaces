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

import java.awt.Dimension;
import java.io.File;
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
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.WMSMapLayer;
import org.geotools.ows.ServiceException;
import org.geotools.referencing.CRS;

import org.mapfaces.component.UILayer;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.util.FacesUtils;

import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class LayerRenderer extends WidgetBaseRenderer {

    private static final Logger LOGGER = Logger.getLogger("org.mapfaces.renderkit.html.LayerRenderer");
        
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        assertValid(context, component);
        final UILayer comp  = (UILayer) component;
        final boolean debug = comp.isDebug();
        
        if (debug) System.out.println("[LayerRenderer] encodeBegin"+ comp.getClientId(context)+" "+comp.getId());
        
        // suppress rendering if "rendered" property on the component is false.
        if (!comp.isRendered()) return;
        
        final ResponseWriter writer = context.getResponseWriter();
        final String clientId       = comp.getClientId(context);
        final String id             = comp.getAttributes().get("id").toString();
        final Context model         = (Context) comp.getModel();
        final Layer layer           = comp.getLayer();
        final Dimension dim         = new Dimension(
                                        Integer.parseInt(model.getWindowWidth()),
                                        Integer.parseInt(model.getWindowHeight()));
        
        if (model == null && debug) System.out.println("[LayerRenderer] model is null");
        
        if (debug) System.out.println("Layer encode begin width height "+model.getId()+" "+ layer.getId() +" "+ model.getWindowHeight()+" "+model.getWindowWidth());
        
        final String styleImg = "filter:alpha(opacity="+(new Float(layer.getOpacity())*100)+");opacity:"+layer.getOpacity()+";";
        final String display  = (layer.isHidden()) ? "display:none" : "display:block;" ;
        
        writer.startElement("div", comp);
        writer.writeAttribute("id", clientId, "style");
        writer.writeAttribute("class", "layerDiv", "style");
        writer.writeAttribute("style", display + "position: absolute; width: 100%; height: 100%; z-index: 100;" +  comp.getStyle(), "style");
        
        
        //Add layer image if not the first page loads                        
        if (FacesUtils.getParentUIMapPane(context, comp).getInitDisplay() && !layer.isHidden()) {

            writer.startElement("div", comp);
            writer.writeAttribute("style", "overflow: hidden; position: absolute; z-index: 1; left: 0px; top: 0px; width: " + dim.width + "px; height: " + dim.height + "px;"+styleImg + display, "style");
            
            final String srs = model.getSrs();
            final CoordinateReferenceSystem crs;
            try{
                crs = CRS.decode(srs);
            }catch(FactoryException ex){
                LOGGER.log(Level.SEVERE, "Invalide SRS definition : " + srs, ex);
                //TODO should close divs and writer correctly is this happens
                return;
            }
            
            final ReferencedEnvelope env = new ReferencedEnvelope(
                    new Double(model.getMinx()), new Double(model.getMaxx()), 
                    new Double(model.getMiny()), new Double(model.getMaxy()), 
                    crs);
            final WMSMapLayer mapLayer;
            
            try{
                mapLayer = createWMSLayer(layer);
            }catch(IOException ex){
                LOGGER.log(Level.SEVERE,"Could not create wms map layer.",ex);
                //TODO should close divs and writer correctly is this happens
                return;
            }catch(ServiceException ex){
                LOGGER.log(Level.SEVERE,"Could not create wms map layer.",ex);
                //TODO should close divs and writer correctly is this happens
                return;
            }
            
            if (debug) System.out.println("[PORTRAYING] for envelope : " + env + "and size : " + dim);
            
            writer.startElement("img", comp);
            writer.writeAttribute("id", id + "_Img", "style");
            writer.writeAttribute("class", "layerImg", "style");
            
            if (styleImg != null) {
                writer.writeAttribute("style", "position:relative;", "style");
            }
            
            final URL url = mapLayer.query(env, dim); 
            writer.writeAttribute("src", url.toString(), "src");
            writer.endElement("img");
            writer.endElement("div");
            
            if (debug) System.out.println("\tLayer removed");
        }
        writer.endElement("div");
        writer.flush();
    }

    public WMSMapLayer createWMSLayer(final Layer layer) throws IOException, ServiceException {
 
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

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        final UILayer comp = (UILayer) component;
        if (comp.isDebug()) System.out.println("\tLayerRenderer encodeEnd");
    }

    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        
        final UILayer comp              = (UILayer) component;
        final Context model             = (Context) comp.getModel();
        final Map<String,String> params = context.getExternalContext().getRequestParameterMap();
        final Layer layer               = comp.getLayer();
        final String formId             = FacesUtils.getFormId(context, comp);
        
        final StringBuilder sb = new StringBuilder("Layer decode :");
        sb.append(" Model Id = ").append(model.getId());
        sb.append(" Layer Id = ").append(layer.getId());
        sb.append(" Window Width = ").append(model.getWindowHeight());
        sb.append(" Window Height = ").append(model.getWindowWidth());
        
        if (context.getExternalContext().getRequestParameterMap() == null){
            System.out.println(sb.toString());
            return;
        }
                
        if( params.get("refresh") != null && params.get("refresh").equals(comp.getClientId(context))){
            final String bbox = params.get("bbox");
            sb.append("\n BBox = "+ bbox);
            if (bbox != null && !bbox.equals(model.getMinx() + "," + model.getMiny().toString() + "," + model.getMaxx() + "," + model.getMaxy())) {
                model.setMinx(bbox.split(",")[0]);
                model.setMiny(bbox.split(",")[1]);
                model.setMaxx(bbox.split(",")[2]);
                model.setMaxy(bbox.split(",")[3]);
            }
            String win = params.get("window");
            if(win==null)                
                win = params.get(formId + ":window");
            if (win != null) {
                final String[] window = win.split(",");
                model.setWindowWidth(window[0]);
                model.setWindowHeight(window[1]);
            }
        }
            
        String value         = params.get("org.mapfaces.ajax.AJAX_COMPONENT_VALUE");
        final String layerId = params.get("org.mapfaces.ajax.AJAX_LAYER_ID");
        if (value == null && params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID") != null) {
            value = params.get( params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID"));
            //layerId = params.get("refresh");
        }

        if (layerId != null) {
            final String layerProperty = params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID");
            if (layerId.equals(comp.getClientId(context))) {

                //Modify Context property
                if (layerProperty.contains("hidden")) {
                    final boolean test = !(value != null && value.equals("on"));                    
//                    tmp.setHidden(layer.getId(), test);
                    layer.setHidden(test);
                    if (isDebug()) System.out.println("La propriÃ©tÃ© hidden du layer " + layer.getId() + " Ã  Ã©tÃ© modifiÃ©e :" + model.isHidden(layer.getId()));
                } else if (layerProperty.contains("Opacity")) {
                    model.setOpacity(layer.getId(), value);
                    if (isDebug()) System.out.println("La propriÃ©tÃ© opacity du layer " + layer.getId() + " Ã  Ã©tÃ© modifiÃ©e :" + model.getOpacity(layer.getId()));
                } else if (layerProperty.contains("Time")) {
                    if (value == null) value = "";
                    model.setLayerAttrDimension(layer.getId(), "time", "userValue", value);
                    System.out.println(model.getLayerAttrDimension(layer.getId(), "time", "userValue"));
                    if (isDebug()) System.out.println("La propriÃ©tÃ© time du layer " + layer.getId() + " Ã  Ã©tÃ© modifiÃ©e :" + model.getLayerAttrDimension(layer.getId(), "time", "userValue"));                    
                } else if (layerProperty.contains("Elevation")) {
                    if (value == null) value = "";
                    model.setLayerAttrDimension(layer.getId(), "elevation", "userValue", value);                          
                    if (isDebug()) System.out.println("La propriÃ©tÃ© elevation du layer " + layer.getId() + " Ã  Ã©tÃ© modifiÃ©e :" + model.getLayerAttrDimension(layer.getId(), "elevation", "userValue"));                    
                } else if (layerProperty.contains("DimRange")) {
                    model.setLayerAttrDimension(layer.getId(), "dim_range", "userValue", value);
                    if (isDebug()) System.out.println("La propriÃ©tÃ© dim_range du layer " + layer.getId() + " Ã  Ã©tÃ© modifiÃ©e :" + model.getLayerAttrDimension(layer.getId(), "dim_range", "userValue"));
                }

            }
        }
        
        //Modify Component property
        if (params.get("org.mapfaces.ajax.LAYER_CONTAINER_STYLE") != null) {
            comp.setStyle(params.get("org.mapfaces.ajax.LAYER_CONTAINER_STYLE"));
        }
        /* try {
        ctx.saveModel(context);
        } catch (JAXBException ex) {
        Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        System.out.println(sb.toString());
        comp.setModel((AbstractModelBase) model);
        comp.setLayer(model.getLayerFromId(layer.getId()));

        return;
    }

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null)   throw new NullPointerException("context should not be null");
        if (component == null) throw new NullPointerException("component should not be null");
    }
    
    private File getTempFile(File folder) throws IOException{
        //Check if the length of the tmp folder is greather than 30 files and delete all files if it occurs.
        if (folder.listFiles().length > 30) {
            for (final File file : folder.listFiles()) {
                file.delete();
            }
        }

        return File.createTempFile("img", "", folder);
    }
    
}
