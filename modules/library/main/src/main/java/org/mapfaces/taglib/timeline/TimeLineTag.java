/*
 * TimeLineTag.java
 *
 * Created on 10 avril 2008, 17:31
 */

package org.mapfaces.taglib.timeline;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class TimeLineTag extends UIComponentELTag {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.TimeLine";
    
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.HTMLTimeLine";
    
    /**
     * The Data in JSON format or List<TimeLineEvent> its represented by java.lang.Object .
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
        component.setValueExpression("value",value);
        component.setValueExpression("styleClass",styleClass);
        component.setValueExpression("style",style);
    }
    
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        
        value = null;
        styleClass = null;
        style = null;
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
}
