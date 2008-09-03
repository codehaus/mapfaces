
package org.mapfaces.taglib;

import javax.faces.component.UIComponent;

public class WidgetTag extends WidgetBaseTag {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.Widget";
    
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.Widget";
    
    /**
     * Add extra parameter like this
     * 
     */
    //private ValueExpression value = null;
    
    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return RENDER_TYPE;
    }
    
    @Override
    public void release() {       
        super.release();
        //value = null;
    }
    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);        
        //component.setValueExpression("value",value);
    }
    
    /**
     *Add setters for extra parameters here 
     */
     
}
