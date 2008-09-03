/*
 * MapSizeRenderer.java
 *
 * Created on 24 decembre 2007, 13:55
 */

package org.mapfaces.renderkit.html;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.mapfaces.component.UIWidget;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.AbstractContext;


public class WidgetRenderer extends WidgetBaseRenderer {
    
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {  
        
        super.encodeBegin(context, component);
        
        UIWidget comp = (UIWidget) component;
        AbstractContext model = ((UIContext) getUIModel()).getModel(); 
        
        getWriter().startElement("div", comp);
        getWriter().writeAttribute("id",getClientId(),"id");
        getWriter().writeAttribute("style",getStyle(),"style");        
        if(getStyleClass() == null)
            getWriter().writeAttribute("class","mfWidget","styleClass");
        else
            getWriter().writeAttribute("class",getStyleClass(),"styleClass");
        getWriter().endElement("div");
        getWriter().flush();
        
    }
    
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException { 
        super.encodeEnd(context, component);
    }
    
    @Override
    public void decode(FacesContext context, UIComponent component) {      
        super.decode(context, component);
    }
}
