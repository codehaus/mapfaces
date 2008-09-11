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

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.mapfaces.component.UIButtonBar;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
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
       
        //suppression des ":" pour nommer l'objet javascript correspondant correctement.
        String jsObject =  comp.getParent().getClientId(context);        
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
