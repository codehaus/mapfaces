/*
 * UIWidget.java
 *
 * Created on 24 decembre 2007, 13:55
 */

package org.mapfaces.component;

import javax.faces.context.FacesContext;

public class UIWidget extends UIWidgetBase{
    
    public static final String FAMILIY = "org.mapfaces.Widget";
    
    /**
     * Add extra parameter like this
     * 
     */
    //private String value = null;
    
    public UIWidget() {
        super();
        if(isDebug())
            System.out.println( "    UIWidget constructor----------------------");
        setRendererType("org.mapfaces.renderkit.html.Widget"); // this component has a renderer
    }
    
    @Override
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
