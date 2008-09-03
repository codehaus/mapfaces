/*
 * AbstractTag.java
 *
 * Created on 24 decembre 2007, 12:52
 */

package org.mapfaces.taglib;

import javax.faces.component.UIComponent;

public class LayerControlTag extends WidgetBaseTag {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.LayerControl";
    
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.LayerControl";
    
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
