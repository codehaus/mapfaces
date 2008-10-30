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
import org.mapfaces.component.UIScale;
import org.mapfaces.taglib.ScaleTag;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class ScaleRenderer extends WidgetBaseRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {  
        
        super.encodeBegin(context, component);     
        UIScale comp = (UIScale) component;  
        String clientId= comp.getClientId(context);
        
                    
        writer.startElement("div", comp);        
        writer.writeAttribute("id",clientId,"id");
        
        if (styleClass == null)
            writer.writeAttribute("class","mf"+ScaleTag.COMP_TYPE.substring(ScaleTag.COMP_TYPE.lastIndexOf(".")+1,ScaleTag.COMP_TYPE.length()),"styleclass");
        
        if (style != null)
            writer.writeAttribute("style",style,"style");
        

        writer.startElement("script", comp);
        writer.writeAttribute("type", "text/javascript", "text/javascript");

        //suppression des ":" pour nommer l'objet javascript correspondant correctement      
        String jsObject = comp.getParent().getClientId(context);
        if (jsObject.contains(":")) {
            jsObject = jsObject.replace(":", "");
        }
        
        writer.write("var sc = new OpenLayers.Control.Scale(OpenLayers.Util.getElement('" + clientId + "'));\n");
        writer.write(jsObject + ".addControl(sc);\n");
        writer.endElement("script");
        writer.endElement("div");
        writer.flush();

    }

    @Override
    public boolean getRendersChildren() {
        return false;
    }
}
