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

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.geotools.data.wms.WebMapServer;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.MapLayer;
import org.geotools.map.WMSMapLayer;
import org.geotools.ows.ServiceException;
import org.geotools.referencing.CRS;
import org.mapfaces.component.UILayer;
import org.mapfaces.models.AbstractModelBase;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.util.FacesUtils;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class LayerRenderer extends WidgetBaseRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        assertValid(context, component);
        UILayer comp = (UILayer) component;

        try {
            if (comp.isDebug()) {
                System.out.println("[LayerRenderer] encodeBegin");
            }
            // suppress rendering if "rendered" property on the component is false.
            if (!component.isRendered()) {
                return;
            }

            ResponseWriter writer = context.getResponseWriter();
            String clientId = component.getClientId(context);
            String id = (String) comp.getAttributes().get("id");

            Context model = (Context) comp.getModel();
            if (model == null) {
                if (comp.isDebug()) {
                    System.out.println("[LayerRenderer] model is null");
                }
            }
            String height = model.getWindowHeight();
            String width = model.getWindowWidth();

            //DefaultMapContext defaultMapContext = comp.getMapPane().getDefaultMapContext(); 

            Layer layer = comp.getLayer();
            
            String styleImg = "filter:alpha(opacity="+(new Float(layer.getOpacity())*100)+");opacity:"+layer.getOpacity()+";";
            String display;
            if (layer.isHidden()) {
                display = "display:none;";
            } else {
                display = "display:block;";
            }
            writer.startElement("div", comp);
            writer.writeAttribute("id", clientId, "style");
            writer.writeAttribute("class", "layerDiv", "style");
            writer.writeAttribute("style", display + "position: absolute; width: 100%; height: 100%; z-index: 100;" +  comp.getStyle(), "style");

            //Add layer image if not the first page loads                        
            if (FacesUtils.getParentUIMapPane(context, component).getInitDisplay() && !layer.isHidden()) {

                writer.startElement("div", comp);
                writer.writeAttribute("style", "overflow: hidden; position: absolute; z-index: 1; left: 0px; top: 0px; width: " + width + "px; height: " + height + "px;"+styleImg + display, "style");
                
                //Check if the length of the tmp folder is greather than 30 files and delete all files if it occurs.
                if (comp.getDir().listFiles().length > 30) {
                    for (File file : comp.getDir().listFiles()) {
                        file.delete();
                    }
                }
                
                File dst = File.createTempFile("img", "", comp.getDir());
                if (isDebug()) {
                    System.out.println("            Layer updated " + dst.getName());
                }
                String srs = model.getSrs();
                DefaultMapContext defaultMapContext = new DefaultMapContext(CRS.decode(srs));
                ReferencedEnvelope env = new ReferencedEnvelope(new Double(model.getMinx()), new Double(model.getMaxx()), new Double(model.getMiny()), new Double(model.getMaxy()), CRS.decode(srs));
                MapLayer mapLayer = LayerToWMSMapLayer(context, component, layer, env);
                if (defaultMapContext.layers().add(mapLayer)) {
                    /**
                     * If bbox from model and bbox from MapContext are different , set the bbox in the model doc
                     * normally it doesn't
                     */
                    if (!defaultMapContext.getBounds().equals(env)) {
                        model.setMinx(String.valueOf(env.getMinX()));
                        model.setMiny(String.valueOf(env.getMinY()));
                        model.setMaxx(String.valueOf(env.getMaxX()));
                        model.setMaxy(String.valueOf(env.getMaxY()));
                    }
                    System.out.println("[PORTRAYING] for envelope " + env);
                    Dimension dim = new Dimension(new Integer(width), new Integer(height));
                    try {
                        Date testBegin = new Date();
                        FacesUtils.getParentUIMapPane(context, component).getPortray().portray(defaultMapContext, env, dst, layer.getOutputFormat(), dim, false);
                        Date testEnd = new Date();
                        Long timeout = testEnd.getTime() - testBegin.getTime();
                    } catch (Exception e) {
                        try {
                            System.out.println("[PORTRAYING] Catched Exception : " + e.getMessage());
                            Exception exp = e;
                            Date begin = new Date();
                            Robot robocop = new Robot();
                            while (exp != null) {
                                try {                                    
                                    robocop.delay(1000);
                                    FacesUtils.getParentUIMapPane(context, component).getPortray().portray(defaultMapContext, env, dst, layer.getOutputFormat(), new Dimension(new Integer(width), new Integer(height)), false);
                                    exp = null;
                                } catch (Exception exception) {
                                    exp = exception;
                                    System.out.println("[PORTRAYING] Exception : " + exp.getMessage());
                                }
                                Date end = new Date();
                                Long timeout = end.getTime() - begin.getTime();
                                System.out.println("[PORTRAYING] timeout = " + timeout);
                                if (timeout > 10000) {
                                    break;
                                }
                            }
                        } catch (AWTException ex) {
                            Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.println("            Layer generate file finish " + layer.getName());
                    writer.startElement("img", comp);
                    writer.writeAttribute("id", id + "_Img", "style");
                    writer.writeAttribute("class", "layerImg", "style");
                    if (styleImg != null) {
                        writer.writeAttribute("style", "position:relative;", "style");
                    }
                    writer.writeAttribute("src", comp.getContextPath() + "/" + comp.getDir().getName() + "/" + dst.getName(), "src");
                    writer.endElement("img");
                    writer.endElement("div");
                }
                if (defaultMapContext.layers().remove(mapLayer)) {
                    if (comp.isDebug()) {
                        System.out.println("            Layer removed");
                    }
                }
            }
            writer.endElement("div");
            writer.flush();
        } catch (ServiceException ex) {
            Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAuthorityCodeException ex) {
            Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FactoryException ex) {
            Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public MapLayer LayerToWMSMapLayer(FacesContext context, UIComponent component, Layer layer, ReferencedEnvelope env) throws IOException, ServiceException {
//        System.out.println("=============== " + layer.getServer().getGTCapabilities().getRequest().getGetCapabilities().getGet());
        WMSMapLayer mapLayer = new WMSMapLayer(new WebMapServer(layer.getServer().getGTCapabilities()), env);
        HashMap<String, org.mapfaces.models.Dimension> dims = layer.getDimensionList();
        HashMap<String, String> dims2 = new HashMap<String, String>();
        if (dims != null) {
            for (String tmp : dims.keySet()) {
                dims2.put(tmp, dims.get(tmp).getUserValue());
            }
            mapLayer.setDimensions(dims2);
        }
        mapLayer.setName(layer.getName());
        mapLayer.setOutputFormat(layer.getOutputFormat());
        mapLayer.setVersion(layer.getServer().getVersion());
        mapLayer.setServerUrl(layer.getServer().getHref());
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
        UILayer comp = (UILayer) component;
        if (comp.isDebug()) {
            System.out.println("        LayerRenderer encodeEnd");
        }
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        UILayer comp = (UILayer) component;
        if (comp.isDebug()) {
            System.out.println("        LayerRenderer decode");
        }

        if (context.getExternalContext().getRequestParameterMap() != null) {
            Context tmp = (Context) comp.getModel();
            Map params = context.getExternalContext().getRequestParameterMap();
            Layer layer = comp.getLayer();
            String formId = FacesUtils.getFormId(context, comp);

            String bbox = (String) params.get("bbox");
            if (bbox != null && !bbox.equals(tmp.getMinx() + "," + tmp.getMiny().toString() + "," + tmp.getMaxx() + "," + tmp.getMaxy())) {
                tmp.setMinx(bbox.split(",")[0]);
                tmp.setMiny(bbox.split(",")[1]);
                tmp.setMaxx(bbox.split(",")[2]);
                tmp.setMaxy(bbox.split(",")[3]);
            }
            String win = (String) params.get("window");
            if (win != null) {
                String[] window = win.split(",");
                tmp.setWindowWidth(window[0]);
                tmp.setWindowHeight(window[1]);
            }
            win = (String) params.get(formId + ":window");
            if (win != null) {
                String[] window = win.split(",");
                tmp.setWindowWidth(window[0]);
                tmp.setWindowHeight(window[1]);
            }

            String value = (String) params.get("org.mapfaces.ajax.AJAX_COMPONENT_VALUE");
            String layerId = (String) params.get("org.mapfaces.ajax.AJAX_LAYER_ID");
            if (value == null && params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID") != null) {
                value = (String) params.get((String) params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID"));
            //layerId = (String) params.get("refresh");
            }

            if (layerId != null) {
                String layerProperty = ((String) params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID"));
                if (layerId.equals(FacesUtils.getFormId(context, component) + ":" + layer.getId())) {

                    //Modify Context property
                    System.out.println("layerproperty" + layerProperty);
                    if (layerProperty.contains("Visible")) {
                        boolean test;
                        if (value != null && value.equals("on")) {
                            test = false;
                        } else {
                            test = true;
                        }
////                        tmp.setHidden(layer.getId(), test);
                        layer.setHidden(test);
                        if (isDebug()) {
                            System.out.println("La propriété hidden du layer " + layer.getId() + " à été modifiée :" + tmp.isHidden(layer.getId()));
                        }
                    } else if (layerProperty.contains("Opacity")) {
                        tmp.setOpacity(layer.getId(), value);
                        if (isDebug()) {
                            System.out.println("La propriété opacity du layer " + layer.getId() + " à été modifiée :" + tmp.getOpacity(layer.getId()));
                        }
                    } else if (layerProperty.contains("Time")) {
                        if (value == null)
                            value = "";
                        tmp.setLayerAttrDimension(layer.getId(), "time", "userValue", value);
                        System.out.println(tmp.getLayerAttrDimension(layer.getId(), "time", "userValue"));
                        if (isDebug()) {
                            System.out.println("La propriété time du layer " + layer.getId() + " à été modifiée :" + tmp.getLayerAttrDimension(layer.getId(), "time", "userValue"));
                        }
                    } else if (layerProperty.contains("Elevation")) {
                        if (value == null)
                            value = "";
                        tmp.setLayerAttrDimension(layer.getId(), "elevation", "userValue", value);
                        if (isDebug()) {
                            System.out.println("La propriété elevation du layer " + layer.getId() + " à été modifiée :" + tmp.getLayerAttrDimension(layer.getId(), "elevation", "userValue"));
                        }
                    } else if (layerProperty.contains("DimRange")) {
                        tmp.setLayerAttrDimension(layer.getId(), "dim_range", "userValue", value);
                        if (isDebug()) {
                            System.out.println("La propriété dim_range du layer " + layer.getId() + " à été modifiée :" + tmp.getLayerAttrDimension(layer.getId(), "dim_range", "userValue"));
                        }
                    } else {
                    }

                }
            }
            //Modify Component property
            if ((String) params.get("org.mapfaces.ajax.LAYER_CONTAINER_STYLE") != null) {
                comp.setStyle((String) params.get("org.mapfaces.ajax.LAYER_CONTAINER_STYLE"));
            }
            /* try {
            ctx.saveModel(context);
            } catch (JAXBException ex) {
            Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            comp.setModel((AbstractModelBase) tmp);
            comp.setLayer(tmp.getLayerFromId(layer.getId()));

        }
        return;
    }

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("context should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }
}
