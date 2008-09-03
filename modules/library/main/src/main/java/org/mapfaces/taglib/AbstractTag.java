/*
 * AbstractTag.java
 *
 * Created on 24 decembre 2007, 12:52
 */

package org.mapfaces.taglib;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author Mehdi Sidhoum
 */
public class AbstractTag extends WidgetBaseTag {
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.Abstract";
    
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.Abstract";
    
        
    public String getComponentType() {
        return COMP_TYPE;
    }

    public String getRendererType() {
        return RENDER_TYPE;
    }

}
