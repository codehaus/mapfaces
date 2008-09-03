/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.renderkit.html.models;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.models.AbstractContext;
import org.mapfaces.util.FacesUtils;

/**
 *
 * @author olivier
 */
public class ContextRenderer extends Renderer {
    
    private boolean debug;
    
    private final String WIDGET_CSS = "/org/mapfaces/resources/css/widget.css";
    private final String OPENLAYERS_JS = "/org/mapfaces/resources/openlayers/custom/OpenLayers.js";
    
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {        
        try {
            if(debug)
                System.out.println("ContextRenderer encodeBegin");
            
            
            UIContext  comp = (UIContext) component;
            assertValid(context, component);
            ResponseWriter writer = context.getResponseWriter();
            /* writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);
        writer.write("window.onload=\"alert('ici');\"");
        writer.endElement("script");*/
            
            //Add MapFaces css
            writer.startElement("link", component);
            writer.writeAttribute("rel","stylesheet","rel");
            writer.writeAttribute("href", ResourcePhaseListener.getURL(context, WIDGET_CSS, null), null);
            writer.writeAttribute("type", "text/css", null);
            
             //Add OpenLayers css
            /*writer.endElement("link");
            writer.startElement("link", component);
            writer.writeAttribute("rel","stylesheet","rel");
            writer.writeAttribute("href", ResourcePhaseListener.getURL(context, "/org/mapfaces/resources/openlayers/theme/default/style.css", null), null);
            writer.writeAttribute("type", "text/css", null);
            writer.endElement("link");*/
            
            //Add OpenLayers scripts
            writer.startElement("script", component);
            writer.writeAttribute("src", ResourcePhaseListener.getURL(context, OPENLAYERS_JS, null), null);
            writer.writeAttribute("type", "text/javascript", null);
            writer.endElement("script");
            
            //Load context file
            if(comp.getModel()==null){
                String fileUrl = (String) component.getAttributes().get("service");
                if (fileUrl == null || fileUrl.length() < 1) {
                    throw new IllegalArgumentException("You must indicate a path to file to read");
                }
                // Unmarshalles the given XML file to objects
                JAXBContext Jcontext;      
                Jcontext = JAXBContext.newInstance("net.opengis.owc.v030:net.opengis.context.v110");  
                Unmarshaller unmarshaller = Jcontext.createUnmarshaller();
                ServletContext sc = (ServletContext) context.getExternalContext().getContext();
                JAXBElement elt = (JAXBElement) unmarshaller.unmarshal(new FileReader(sc.getRealPath(fileUrl))); 
                comp.setJAXBElt(elt);
           }else{
                if(debug)
                    System.out.println("AbstractContext already exist");   
           }
            
           /* Add a4j:support component */
           HtmlAjaxSupport ajaxComp = new HtmlAjaxSupport();
           ajaxComp.setId(comp.getId()+"Ajax");
           ajaxComp.setAjaxSingle(true);
           ajaxComp.setImmediate(true);
           comp.getChildren().add(ajaxComp);  
           comp.setAjaxCompId(comp.getClientId(context)+"Ajax");
                 
        }   catch (JAXBException ex) {
            Logger.getLogger(ContextRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }   catch  (NullPointerException ex){
            Logger.getLogger(ContextRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
           
      
        
    }
    
    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        if(debug)
            System.out.println("ContextRenderer  children");
        List<UIComponent> childrens  = component.getChildren();        
        for(UIComponent tmp : childrens){
            FacesUtils.encodeRecursive(context,tmp);
        }
    }     

    @Override
    public boolean getRendersChildren() {
        
        return true;
    }
       
    @Override
     public void encodeEnd(FacesContext context, UIComponent component) throws IOException { 
        UIContext comp = (UIContext) component;   
        if(debug)
            System.out.println("ContextRenderer encodeEnd");
        ResponseWriter writer = context.getResponseWriter();
       
        writer.flush();
    }
    
    
    @Override
    public void decode(FacesContext context, UIComponent component) {  
        UIContext comp = (UIContext) component;          
        if(debug){
            System.out.println("ContextRenderer decode");
        }
        if(context.getExternalContext().getRequestParameterMap()!=null){
            AbstractContext tmp =comp.getModel();
            //tmp.setI
            Map params = context.getExternalContext().getRequestParameterMap();
           /* String bbox = (String) params.get(comp.getParent().getId()+":bbox");               
            if(bbox!=null && !bbox.equals(tmp.getMinx()+","+tmp.getMiny().toString()+","+tmp.getMaxx()+","+tmp.getMaxy())){ 
                try {
                    System.out.println("voici la nouveau bboxxxxxxxxx : "+bbox);
                    if( !tmp.getSrs().equals("urn:ogc:def:crs:EPSG:6.6:4326") )
                        bbox = (String) GeometricUtilities.reprojectBbox2DString("urn:ogc:def:crs:EPSG:6.6:4326",tmp.getSrs(),bbox);
                    System.out.println("voici la nouveau bboxxxxxxxxx : "+bbox);
                    tmp.setMinx(new Double(bbox.split(",")[0]));
                    tmp.setMiny(new Double(bbox.split(",")[1]));
                    tmp.setMaxx(new Double(bbox.split(",")[2]));
                    tmp.setMaxy(new Double(bbox.split(",")[3]));
                } catch (NoSuchAuthorityCodeException ex) {
                    Logger.getLogger(ContextRenderer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FactoryException ex) {
                    Logger.getLogger(ContextRenderer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TransformException ex) {
                    Logger.getLogger(ContextRenderer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/
            
            String title = (String) params.get(comp.getParent().getId()+":title"); 
            if(title!=null  &&  !title.equals(tmp.getTitle())){
                tmp.setTitle(title);   
            }
            
            /*String win = (String) params.get(comp.getParent().getId()+":window");  
            if( win != null){
                String[] window = win.split(",");
                tmp.setWindowWidth(new BigInteger(window[0]));
                tmp.setWindowHeight(new BigInteger(window[1]));
            }*/
            comp.setModel(tmp);
            if(debug)
                System.out.println("    Nouveaux parametres du context : "+tmp.getTitle()+" "+tmp.getMinx()+" "+tmp.getMiny().toString()+" "+tmp.getMaxx()+" "+tmp.getMaxy()+"");
        }
    }
    
    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("context should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

}