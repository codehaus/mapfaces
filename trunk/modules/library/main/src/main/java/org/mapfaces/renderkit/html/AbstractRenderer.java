/*
 * AbstractRenderer.java
 *
 * Created on 24 decembre 2007, 13:55
 */

package org.mapfaces.renderkit.html;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import org.mapfaces.component.UIAbstract;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.component.models.UIModelBase;
import org.mapfaces.models.AbstractContext;

/**
 *
 * @author Mehdi Sidhoum
 */
public class AbstractRenderer extends WidgetBaseRenderer {
    
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {  
        
        super.encodeBegin(context, component);
        UIAbstract abstractComp = (UIAbstract) component;
        AbstractContext model = ((UIContext) UIModel).getModel(); 
        
        writer.startElement("div", abstractComp);
        writer.writeAttribute("id",clientId,"id");
        writer.writeAttribute("style",style,"style");        
        if(styleClass == null)
            writer.writeAttribute("class","mfMapTitle","styleClass");
        else
           writer.writeAttribute("class",styleClass,"styleClass");
        writer.startElement("h3", abstractComp);
        
        String title =model.getTitle();
        if (title != null){
            writer.write(title);
        }
        
        writer.endElement("h3");
        writer.endElement("div");
        writer.flush();
        
    }
    
    @Override
    public boolean getRendersChildren() {   
        return false;
    }
   
}
