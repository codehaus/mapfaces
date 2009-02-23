/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */

package org.mapfaces.taglib;

import java.util.List;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.mapfaces.component.layer.UIFeatureLayer;

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
     * The Data FeatureCollection.
     */
    private ValueExpression value = null;
    /**
     * Size of the image.
     */
    private ValueExpression sizeImg = null;
    /**
     * Rotation of the image.
     */
    private ValueExpression rotation = null;

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
        component.setValueExpression("value", value);
        component.setValueExpression("sizeImg", sizeImg);
        component.setValueExpression("rotation", rotation);

        final UIFeatureLayer mflayer = (UIFeatureLayer) component;

        if (value != null) {
            final FacesContext context = FacesContext.getCurrentInstance();
            final ExpressionFactory ef = context.getApplication().getExpressionFactory();
            final ValueExpression vex = ef.createValueExpression(context.getELContext(), value.getExpressionString(), List.class);
            mflayer.setFeatures((List) vex.getValue(context.getELContext()));
        }
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
        setValue(null);
        setSizeImg(null);
        setRotation(null);
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

    public ValueExpression getValue() {
        return value;
    }

    public void setValue(ValueExpression value) {
        this.value = value;
    }

    public ValueExpression getSizeImg() {
        return sizeImg;
    }

    public void setSizeImg(ValueExpression sizeImg) {
        this.sizeImg = sizeImg;
    }

    public ValueExpression getRotation() {
        return rotation;
    }

    public void setRotation(ValueExpression rotation) {
        this.rotation = rotation;
    }
}
