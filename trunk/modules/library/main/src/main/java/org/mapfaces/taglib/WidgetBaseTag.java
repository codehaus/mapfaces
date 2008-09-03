/*
 * WidgetBaseTag.java
 *
 * Created on 24 decembre 2007, 12:52
 */

package org.mapfaces.taglib;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

public class WidgetBaseTag extends UIComponentELTag {
    
    /**
     * The WidgetBase string value.
     */
    private ValueExpression value = null;
    
    /**
     * The style class of the overall div that surrounds this component.
     */
    private ValueExpression styleClass = null;
    
    /**
     * The style of the overall div that surrounds this component.
     */
    private ValueExpression style = null;
    
    /**
     * Option to see debug messages.
     */
    private ValueExpression debug = null;

    
    @Override
    protected void setProperties(UIComponent component) {
        // always call the superclass method
        super.setProperties(component);
        
        component.setValueExpression("value",value);
        component.setValueExpression("styleClass",styleClass);
        component.setValueExpression("style",style);
        component.setValueExpression("debug",debug);
    }
    
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        
        value = null;
        styleClass = null;
        style = null;
        debug = null;
    }

    public void setValue(ValueExpression value) {
        this.value = value;
    }

    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    public void setStyle(ValueExpression style) {
        this.style = style;
    }

    public ValueExpression getDebug() {
        return debug;
    }

    public void setDebug(ValueExpression debug) {
        this.debug = debug;
    }

    @Override
    public String getComponentType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getRendererType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
