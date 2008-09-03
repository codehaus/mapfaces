/*
 * MapPaneRenderer.java
 *
 * Created on 27 decembre 2007, 16:20
 */

package org.mapfaces.renderkit.html;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.xml.bind.JAXBException;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.MapLayer;
import org.geotools.referencing.CRS;
import org.mapfaces.component.UILayer;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.AbstractContext;

import org.mapfaces.models.Layer;
import org.mapfaces.util.AjaxUtils;
import org.mapfaces.util.FacesUtils;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.operation.TransformException;

/**
 *
 * @author Mehdi Sidhoum
 */
public class LayerRenderer extends WidgetBaseRenderer {
    
    
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        try {
            if(isDebug())
                System.out.println("        LayerRenderer encodeBegin");
            // suppress rendering if "rendered" property on the component is false.
            if (!component.isRendered()) {
                return;
            }
            assertValid(context, component);
            ResponseWriter writer = context.getResponseWriter();
            String clientId = component.getClientId(context);

            UILayer comp = (UILayer) component;

            String id = (String) comp.getAttributes().get("id");
           /* if(!clientId.equals(id)){
                comp.setId(id);
            }*/
            clientId = component.getClientId(context);
            if (comp == null) {
                if(isDebug())
                    System.out.println("comp is null");
            }
            if (comp.getUIModel() == null) {
                if(isDebug())
                    System.out.println("comp.getModel() is null");
            }
            if ((AbstractContext) ((UIContext) comp.getUIModel()).getModel() == null) {
                if(isDebug())
                    System.out.println("(AbstractContext) ((UIContext) comp.getModel()).getModel() is null");
            }
            AbstractContext model = (AbstractContext) ((UIContext) comp.getUIModel()).getModel();
            if (model == null) {
                if(isDebug())
                    System.out.println("model is null");
            }
            BigInteger height = model.getWindowHeight();
            BigInteger width = model.getWindowWidth();

            //DefaultMapContext defaultMapContext = comp.getMapPane().getDefaultMapContext(); 
            String srs = model.getSrs();
            System.out.println(srs);
            DefaultMapContext defaultMapContext = new DefaultMapContext(CRS.decode(srs));
            ReferencedEnvelope env = new ReferencedEnvelope(model.getMinx().doubleValue(), model.getMaxx().doubleValue(), model.getMiny().doubleValue(), model.getMaxy().doubleValue(), CRS.decode(srs));
         

            Layer layer = comp.getLayer();

            if(layer.getDimensionList()==null){
                if(isDebug())
                    System.out.println("            Pas de dimensions pour ce layer");
            }else{
                if(isDebug())
                    System.out.println("            La dimension elevation a  pour valeur : "+ layer.getDimensionList().get("elevation"));
                
            //model.save();
            }
            String styleImg = "opacity:" + layer.getOpacity() + ";";
            
           
            //defaultMapContext.clearLayerList();
            if (defaultMapContext.layers().add(layer.getMapLayer())) {
                    writer.startElement("div", comp);
                    writer.writeAttribute("id", clientId , "style");
                    writer.writeAttribute("class", "layerDiv", "style");
                    writer.writeAttribute("style","position: absolute; width: 100%; height: 100%; z-index: 100;"+styleImg, "style");
                    //Add layer image if not the first page loads
                    if (((UIMapPane) comp.getParent()).getInitDisplay() && !layer.isHidden()) {
                        writer.startElement("div", comp);
                        writer.writeAttribute("style", "overflow: hidden; position: absolute; z-index: 1; left: 0px; top: 0px; width: "+width+"px; height: "+height+"px;", "style");
                        File dst = File.createTempFile("img", ".png", comp.getDir()); 
                        if(isDebug())
                            System.out.println("            Layer updated " + dst.getName());
                    try {
                        if (((UIMapPane) comp.getParent()).getPortray().portray(defaultMapContext, env, dst, "image/png", new Dimension(width.intValue(), height.intValue()))) {
                            /**
                             * If bbox from model and bbox from MapContext are different , set the bbox in the model doc
                             * normally it doesn't
                             */
                            if (!defaultMapContext.getBounds().equals(env)) {
                                if(isDebug()){
                                    System.out.println("            Aoi du context : "+env.toString());
                                    System.out.println("             != ");
                                    System.out.println("            Aoi affiché par le portrayal : "+defaultMapContext.getBounds().toString());
                                }
                                model.setMinx(env.getMinX());
                                model.setMiny(env.getMinY());
                                model.setMaxx(env.getMaxX());
                                model.setMaxy(env.getMaxY());                                
                            }
                            
                            writer.startElement("img", comp);
                            writer.writeAttribute("id", id + "_Img", "style");
                            writer.writeAttribute("class", "layerImg", "style");
                            if (styleImg != null) {
                                writer.writeAttribute("style", "position:relative;", "style");
                            }
                            writer.writeAttribute("src", comp.getContextPath() + "/" + comp.getDir().getName() + "/" + dst.getName(), "src");
                            writer.endElement("img");
                        } else {
                            writer.writeAttribute("style", "overflow: hidden; position: absolute; z-index: 1; left: 0px; top: 0px; width: " + width + "px; height: " + height + "px;", "style");
                            writer.startElement("img", comp);
                            writer.writeAttribute("id", id + "_Img", "style");
                            writer.writeAttribute("class", "layerImg", "style");
                            if (styleImg != null) {
                                writer.writeAttribute("style", styleImg, "style");
                            }
                            writer.writeAttribute("src", comp.getContextPath() + "/resource.jsf?r=/org/mapfaces/resources/img/Spacer.gif", "src");
                            writer.endElement("img");
                            System.out.println("            Layer not loaded");
                        }
                    } catch (TransformException ex) {
                        Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        writer.endElement("div");
                    } else {
                        if(isDebug())
                            System.out.println("            Layer not added");
                    }
                    writer.endElement("div");
                    if (defaultMapContext.layers().remove(layer.getMapLayer())) {
                        if(isDebug())
                            System.out.println("            Layer removed");
                    }
            } else {
                if(isDebug())
                    System.out.println("            Layer not added");
            }
            writer.flush();
        } catch (NoSuchAuthorityCodeException ex) {
            Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FactoryException ex) {
            Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if(isDebug())
            System.out.println("        LayerRenderer encodeEnd");
    }
    
    @Override
    public void decode(FacesContext context, UIComponent component) {
        if(isDebug())
            System.out.println("        LayerRenderer decode"); 
        UILayer comp = (UILayer) component;
        if(context.getExternalContext().getRequestParameterMap()!=null){
            UIContext ctx = (UIContext)comp.getUIModel();
            AbstractContext tmp=(AbstractContext) ctx.getModel();
            Map params = context.getExternalContext().getRequestParameterMap();
            Layer layer = comp.getLayer();
            String formId =  FacesUtils.getFormId(context, comp);
            AjaxUtils ajaxtools = new AjaxUtils();
            String elevation = (String) params.get(formId+":elevation");         
            if(elevation!=null && layer.getDimensionList()!=null && layer.getDimensionList().containsKey("elevation") && !layer.getDimensionList().get("elevation").equals(elevation)){
                //layer.setDimension("elevation",elevation);
            }
            /*String elevation = (String) params.get(comp.getParent().getParent().getParent().getId()+":jid6elevation");         
            if(elevation!=null && layer.getDimensions()!=null && layer.getDimensions().containsKey("elevation") && !layer.getDimensions().get("elevation").equals(elevation)){
                tmp.setLayerAttrDimension(layer.getName(),"elevation","UserValue",elevation);
            }*/
            //if(elevation!=null || tmp.getLayerFromName(elevation).)
                
            //System.out.println("eleeeeeeeeeeevationnnnnnnnnnnn : " + layer.getDimensions().get("elevation"));    
            String bbox = (String) params.get("bbox");
            if(bbox!=null && !bbox.equals(tmp.getMinx()+","+tmp.getMiny().toString()+","+tmp.getMaxx()+","+tmp.getMaxy())){ 
               // try {
                    /*if (!tmp.getSrs().equals("urn:ogc:def:crs:EPSG:6.6:4326")) {
                        bbox = (String) GeometricUtilities.reprojectBbox2DString("urn:ogc:def:crs:EPSG:6.6:4326",tmp.getSrs(),bbox);
                    }
                     * */
                    tmp.setMinx(new Double(bbox.split(",")[0]));
                    tmp.setMiny(new Double(bbox.split(",")[1]));
                    tmp.setMaxx(new Double(bbox.split(",")[2]));
                    tmp.setMaxy(new Double(bbox.split(",")[3]));
                /*} catch (NoSuchAuthorityCodeException ex) {
                    Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FactoryException ex) {
                    Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TransformException ex) {
                    Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            }
            String win = (String) params.get("window") ;  
            if( win != null){
                String[] window = win.split(",");
                tmp.setWindowWidth(new BigInteger(window[0]));
                tmp.setWindowHeight(new BigInteger(window[1]));
            }
            win = (String) params.get(formId+":window") ;  
            if( win != null){
                String[] window = win.split(",");
                tmp.setWindowWidth(new BigInteger(window[0]));
                tmp.setWindowHeight(new BigInteger(window[1]));
            }
            String value = (String) params.get("org.mapfaces.ajax.AJAX_COMPONENT_VALUE") ;  
            String layerId = (String) params.get("org.mapfaces.ajax.AJAX_LAYER_ID") ; 
            System.out.println("org.mapfaces.ajax.AJAX_COMPONENT_VALUE "+tmp.isHidden(layer.getId()));
            if(value==null && params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID")!=null){
                value = (String) params.get((String) params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID"));
                //layerId = (String) params.get("refresh");
            }
            System.out.println("valuuuuuuuuuuuuuuuuuuuue " +value+" "+layerId);
            if( value != null && layerId != null){                
                String layerProperty = ((String) params.get("org.mapfaces.ajax.AJAX_CONTAINER_ID"));

                if(layerId.equals(FacesUtils.getFormId(context, component)+":"+layer.getId())){ 
                    System.out.println("");
                    if(layerProperty.contains("Visible")){
                        tmp.setHidden(layer.getId(),!Boolean.valueOf(value));
                        layer.setHidden(!Boolean.valueOf(value));
                        System.out.println("La propriété hidden du layer "+layer.getId()+" à été modifiée :"+ tmp.isHidden(layer.getId()));
                    }else if(layerProperty.contains("Opacity")){
                        tmp.setOpacity(layer.getId(),Double.valueOf(value));
                        layer.setOpacity(value);
                        System.out.println("La propriété opacity du layer "+layer.getId()+" à été modifiée :"+ tmp.getOpacity(layer.getId()));
                    }else if(layerProperty.contains("Time")){
                        tmp.setLayerAttrDimensionFromId(layer.getId(),"time","userValue",value);
                        layer.setUserValue("time",value);
                    }else if(layerProperty.contains("Elevation")){
                        tmp.setLayerAttrDimensionFromId(layer.getId(),"elevation","userValue",value);
                        layer.setUserValue("elevation",value);
                    }else if(layerProperty.contains("Dim_range")){
                        tmp.setLayerAttrDimensionFromId(layer.getId(),"dim_range","userValue",value);
                        layer.setUserValue("dim_range",value);
                    }
                   
                }
            }
            ctx.setModel(tmp);
            try {
                ctx.saveModel(context);
            } catch (JAXBException ex) {
                Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
            comp.setUIModel(ctx);
            comp.setLayer(layer);
            /*String  render = (String) params.get("render") ;
            if(render==null || render.equals("true"))
                comp.setInitDisplay(true);
           */
            if(isDebug())
                System.out.println("            Nouveaux parametres du layer : "+tmp.getTitle()+" "+tmp.getWindowWidth()+" "+tmp.getWindowHeight()+" "+tmp.getMinx()+" "+tmp.getMiny().toString()+" "+tmp.getMaxx()+" "+tmp.getMaxy()+"");
            
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
