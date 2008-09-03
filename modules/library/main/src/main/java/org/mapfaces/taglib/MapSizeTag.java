/*
 * AbstractTag.java
 *
 * Created on 24 decembre 2007, 12:52
 */

package org.mapfaces.taglib;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;

public class MapSizeTag extends WidgetBaseTag {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.MapSize";
    
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.MapSize";
    
    private ValueExpression title = null;
    
    private ValueExpression itemsValues = null;
    
    private ValueExpression itemsLabels = null;
    
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
        title = null;
        itemsLabels = null;
        itemsValues = null;        
    }
    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);        
        component.setValueExpression("title",title);
        component.setValueExpression("itemsLabels",itemsLabels);
        component.setValueExpression("itemsValues",itemsValues);
    }

    public void setTitle(ValueExpression title) {
        this.title = title;
    }

    public void setItemsValues(ValueExpression itemsValues) {
        this.itemsValues = itemsValues;
    }

    public void setItemsLabels(ValueExpression itemsLabels) {
        this.itemsLabels = itemsLabels;
    }
    
}
