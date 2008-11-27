/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mapfaces.taglib;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;

/**
 *
 * @author Mehdi Sidhoum
 */
public class MFLayerTag extends WidgetBaseTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.MapPane.MFLayer";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.MapPane.MFLayer";
    
    private ValueExpression image = null;
    /**
     * The style class of the overall div that surrounds this component.
     */
    private ValueExpression styleClass = null;
    /**
     * The style of the overall div that surrounds this component.
     */
    private ValueExpression style = null;

    /**
     * {@inheritDoc }
     */
    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return RENDER_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void setProperties(final UIComponent component) {
        // always call the superclass method
        super.setProperties(component);
        component.setValueExpression("image", image);
        component.setValueExpression("style", style);
        component.setValueExpression("styleClass", styleClass);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        setImage(null);
        setStyle(null);
        setStyleClass(null);
    }

    public ValueExpression getImage() {
        return image;
    }

    public void setImage(ValueExpression image) {
        this.image = image;
    }

    public ValueExpression getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    public ValueExpression getStyle() {
        return style;
    }

    public void setStyle(ValueExpression style) {
        this.style = style;
    }
}
