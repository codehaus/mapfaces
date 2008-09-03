/*
 * WidgetBaseRenderer.java
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
import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.component.models.UIModelBase;
import org.mapfaces.util.FacesUtils;

public class WidgetBaseRenderer extends Renderer {
    
    UIModelBase UIModel;
    ResponseWriter  writer;    
    boolean debug;
    String clientId ;
    String style;
    String styleClass;
    
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException { 
             
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);
        writer = context.getResponseWriter();
        if(clientId==null)
            clientId = component.getClientId(context);   
        System.out.println(clientId);
        UIWidgetBase comp = (UIWidgetBase) component;
        
        debug = comp.isDebug();
        if(debug){            
            System.out.println("    Le composant "+comp.getFamily()+" entre dans encodeBegin de widgetBaseRenderer");
        }        
        
        if(comp.getParent()==null)
            throw new NullPointerException("UIModelBase should not be null");
        else if(comp.getUIModel()==null)
            comp.setUIModel((UIModelBase) comp.getParent());
        
        UIModel = comp.getUIModel();   
        style = (String) comp.getAttributes().get("style"); 
        
        if(styleClass == null)
            styleClass = (String) comp.getAttributes().get("styleClass");
        
    }
    
    @Override
    public boolean getRendersChildren() {   
        return true;
    }
    
    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        List<UIComponent> childrens  = component.getChildren();
        if(isDebug())
             System.out.println("        Le composant "+component.getFamily()+" a "+childrens.size()+" enfants");
        for(UIComponent tmp : childrens){
            if(isDebug())
                System.out.println("        Famille des enfants : "+tmp.getFamily());
            FacesUtils.encodeRecursive(context,tmp);
        }
    }     
    
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if(isDebug())
             System.out.println("    Le composant "+component.getFamily()+" entre dans encodeEnd");
    }
    
    @Override
    public void decode(FacesContext context, UIComponent component){
        if(isDebug())
             System.out.println("    Le composant "+component.getFamily()+" entre dans decode");
    }
    private void assertValid(FacesContext context, UIComponent component) {
        /*if(isDebug())   
            System.out.println("     Le composant "+component.getFamily()+" entre dans AssertValid");*/
        if (context == null) {
            throw new NullPointerException("context should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }
    
    void removeChildren(FacesContext context, UIComponent component) {
         List<UIComponent> childrens  = component.getChildren();
         for(int i=childrens.size()-1;i>=0;i--){
                childrens.remove(i);
         }
    }

    public UIModelBase getUIModel() {
        return UIModel;
    }

    public void setUIModel(UIModelBase UIModel) {
        this.UIModel = UIModel;
    }

    public ResponseWriter getWriter() {
        return writer;
    }

    public void setWriter(ResponseWriter writer) {
        this.writer = writer;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }
   
}
