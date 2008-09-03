/*
 * ContextTag.java
 *
 * Created on 24 decembre 2007, 12:52
 */

package org.mapfaces.taglib.models;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author Mehdi Sidhoum
 */
public class ContextTag extends UIComponentELTag {
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.models.Context";
    
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.models.Context";
    
     /**
     * The url of context file.
     */
    private ValueExpression service = null;
    /**
     * The Context string value.
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
    
    
    private ValueExpression debug = null;
        
    public String getComponentType() {
        return COMP_TYPE;
    }

    public String getRendererType() {
        return RENDER_TYPE;
    }

    
    @Override
    protected void setProperties(UIComponent component) {
        // always call the superclass method
        super.setProperties(component);
        component.setValueExpression("service",service);
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
    public void setService(ValueExpression service) {
        this.service = service;
    }

    public ValueExpression getDebug() {
        return debug;
    }

    public void setDebug(ValueExpression debug) {
        this.debug = debug;
    }
}
