/*
 * CursorTrackRenderer.java
 *
 * Created on 24 decembre 2007, 13:55
 */

package org.mapfaces.renderkit.html;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.mapfaces.component.UICursorTrack;
import org.mapfaces.taglib.CursorTrackTag;

/**
 *
 * @author Mehdi Sidhoum
 */
public class CursorTrackRenderer extends WidgetBaseRenderer {
    
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {  
        
        super.encodeBegin(context, component);     
        UICursorTrack comp = (UICursorTrack) component;        
        
        
                    
        writer.startElement("div", comp);        
        writer.writeAttribute("id",clientId,"id");
        
        if (styleClass == null)
            writer.writeAttribute("class","mf"+CursorTrackTag.COMP_TYPE.substring(CursorTrackTag.COMP_TYPE.lastIndexOf(".")+1,CursorTrackTag.COMP_TYPE.length()),"styleclass");
        
        if (style != null)
            writer.writeAttribute("style",style,"style");
        
        writer.startElement("script", comp);
        writer.writeAttribute("type","text/javascript","text/javascript");
       
        //suppression des ":" pour nommer l'objet javascript correspondant correctement      
        String jsObject = UIModel.getClientId(context);        
        if(jsObject.contains(":"))                 
            jsObject = jsObject.replace(":","");        
        
            writer.write("var mp = new OpenLayers.Control.MousePosition({'div':OpenLayers.Util.getElement('"+clientId+"')"); 
            
            if(comp.isShowPX())
                writer.write(",\nPX: true");
        
            if(comp.isShowXY())
                writer.write(",\nXY: true");
            
            if(comp.isShowLatLon())
                writer.write(",\nLatLon: true");
            
            if(comp.isShowDMS())
                writer.write(",\nDMS: true");
            
            if(comp.isShowDM())
                writer.write(",\nDM: true");
            
        
        writer.write("\n});\n");
        writer.write(jsObject+".addControl(mp);\n");
        writer.endElement("script");
        writer.endElement("div");
        writer.flush();
        
    }
    @Override
    public boolean getRendersChildren() {   
        return false;
    }
}
