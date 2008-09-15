/*
 * MapPaneTag.java
 *
 * Created on 27 decembre 2007, 16:28
 */

package org.mapfaces.taglib;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;

public class DimRangeTag extends WidgetBaseTag {
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.DimRange";
    
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.DimRange";
     
    /**
     * The WidgetBase string value.
     */
    private ValueExpression layerCompId = null;
    
        
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
        component.setValueExpression("layerCompId",layerCompId);
        
    }
    
    @Override
    public void release() {
        // allways call the superclass method
        super.release();     
        layerCompId = null;
        
    }

    public ValueExpression getLayerCompId() {
        return layerCompId;
    }

    public void setLayerCompId(ValueExpression layerCompId) {
        this.layerCompId = layerCompId;
    }

    
}
