/*
 * ButtonBarRenderer.java
 *
 * Created on 24 decembre 2007, 13:55
 */

package org.mapfaces.renderkit.html;

import java.io.IOException;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.ajax4jsf.ajax.html.HtmlAjaxSupport;
import org.mapfaces.component.UIButtonBar;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.component.models.UIModelBase;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.models.AbstractContext;
import org.mapfaces.util.FacesUtils;

/**
 *
 * @author Mehdi Sidhoum
 */
public class ButtonBarRenderer extends WidgetBaseRenderer {
    
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {  
               
        super.encodeBegin(context, component);
        UIButtonBar comp = (UIButtonBar) component;     
        
        writer.startElement("div", comp);        
        writer.writeAttribute("id",clientId,"id");
        
        String style = (String) comp.getAttributes().get("style");
        if (style != null)
            writer.writeAttribute("style",style,"style");
        else
            writer.writeAttribute("style","position:absolute;z-index:1000;","style");
        
        String styleclass = (String) comp.getAttributes().get("styleClass");
        if (styleclass != null)
            writer.writeAttribute("class",styleclass,"styleclass");
        else
             writer.writeAttribute("class","mfButtonBar","styleclass");
        
        writer.startElement("script", comp);
        writer.writeAttribute("type","text/javascript","text/javascript");
       
        //suppression des ":" pour nommer l'objet javascript correspondant correctement      
        String jsObject = UIModel.getClientId(context);        
        if(jsObject.contains(":"))                 
            jsObject = jsObject.replace(":","");        
        
        if(comp.isHistory()){
            writer.write("var nav = new OpenLayers.Control.NavigationHistory();\n"); 
            writer.write(jsObject+".addControl(nav);\n");
        }
        if(comp.isZoomIn() || comp.isHistory() || comp.isZoomOut() || comp.isPan() || comp.isZoomMaxExtent()){
            writer.write(jsObject+".addControl(new OpenLayers.Control.NavToolbar({'div':OpenLayers.Util.getElement('"+comp.getClientId(context)+"')");
        
            if(comp.isZoomIn())
                writer.write(",\nzoomIn: true");
        
            if(comp.isZoomOut())
                writer.write(",\nzoomOut: true");
        
            if(comp.isPan())
                writer.write(",\npan: true");
        
            if(comp.isZoomMaxExtent())
                writer.write(",\nzoomMaxExtent: true");      
              
            if(comp.isHistory())
                writer.write(",\nhistory: true");
            
            if(comp.isPan() && comp.isPanEffect())
                writer.write(",\npanEffect: true");
            
            writer.write("\n}));\n");
        }
        writer.endElement("script");          
        writer.endElement("div");
        writer.flush();
        
    }
    
    @Override
    public boolean getRendersChildren() {   
        return false;
    }
}
