/*
 * UIAbstract.java
 *
 * Created on 24 decembre 2007, 13:55
 */

package org.mapfaces.component;

import javax.faces.context.FacesContext;

/**
 *
 * @author Mehdi Sidhoum
 */
public class UIAbstract extends UIWidgetBase {
    
    public static final String FAMILIY = "javax.faces.Output";
       
    /** Creates a new instance of UIAbstract */
    public UIAbstract() {
        super();
        if(isDebug())
            System.out.println("    UIAbstract constructor----------------------");
        setRendererType("org.mapfaces.renderkit.html.Abstract"); // this component has a renderer
    }
    
    public String getFamily() {
        return FAMILIY;
    }
    
    @Override
     public Object saveState(FacesContext context) {
        Object values[] = new Object[1];
        values[0] = super.saveState(context); 
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]); 
    }
    
}
