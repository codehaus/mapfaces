/*
 * UICursorTrack.java
 *
 * Created on 24 decembre 2007, 13:55
 */

package org.mapfaces.component;

import javax.el.ValueExpression;
import javax.faces.component.StateHolder;
import javax.faces.context.FacesContext;

public class UIDimRange extends UIWidgetBase implements StateHolder {
    
    public static final String FAMILIY = "org.mapfaces.DimRange";
       
    private String layerCompId ;
    
    /** Creates a new instance of UICursorTrack */
    public UIDimRange() {
        super();
        setRendererType("org.mapfaces.renderkit.html.DimRange"); // this component has a renderer
        if(isDebug())
            System.out.println("    UIDimRange constructor----------------------");
        
    }
    
    public String getFamily() {
        return FAMILIY;
    }
    
    @Override
     public Object saveState(FacesContext context) {
        Object values[] = new Object[3];
        values[0] = super.saveState(context);
        values[1] = layerCompId;
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        layerCompId = (String) values[1];
    }

    public String getLayerCompId() {
        return layerCompId;
    }

    public void setLayerCompId(String layerCompId) {
        
        if (layerCompId.contains("#")) {
            FacesContext context = FacesContext.getCurrentInstance();
            ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), layerCompId, String.class);
            this.layerCompId = (String) ve.getValue(context.getELContext());
            System.out.println("%%%%%%%% "+this.layerCompId);
        }else
            this.layerCompId = layerCompId;
    }


    
}
