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

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author Mehdi Sidhoum (Geomatys)
 */
public class BubbleTag extends UIComponentELTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.Bubble";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.HTMLBubble";
    private ValueExpression height = null;
    private ValueExpression width = null;
    private ValueExpression topArrow = null;
    private ValueExpression bottomArrow = null;
    private ValueExpression spryEffect = null;
    private ValueExpression loadSpryJs = null;
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
        component.setValueExpression("width", width);
        component.setValueExpression("height", height);
        component.setValueExpression("topArrow", topArrow);
        component.setValueExpression("bottomArrow", bottomArrow);
        component.setValueExpression("style", style);
        component.setValueExpression("styleClass", styleClass);
        component.setValueExpression("spryEffect", spryEffect);
        component.setValueExpression("loadSpryJs", loadSpryJs);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();

        width = null;
        height = null;
        topArrow = null;
        bottomArrow = null;
        style = null;
        styleClass = null;
        spryEffect = null;
        loadSpryJs = null;
    }

    public ValueExpression getHeight() {
        return height;
    }

    public void setHeight(ValueExpression height) {
        this.height = height;
    }

    public ValueExpression getWidth() {
        return width;
    }

    public void setWidth(ValueExpression width) {
        this.width = width;
    }

    public ValueExpression getTopArrow() {
        return topArrow;
    }

    public void setTopArrow(ValueExpression topArrow) {
        this.topArrow = topArrow;
    }

    public ValueExpression getBottomArrow() {
        return bottomArrow;
    }

    public void setBottomArrow(ValueExpression bottomArrow) {
        this.bottomArrow = bottomArrow;
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

    public ValueExpression getSpryEffect() {
        return spryEffect;
    }

    public void setSpryEffect(ValueExpression spryEffect) {
        this.spryEffect = spryEffect;
    }

    public ValueExpression getLoadSpryJs() {
        return loadSpryJs;
    }

    public void setLoadSpryJs(ValueExpression loadSpryJs) {
        this.loadSpryJs = loadSpryJs;
    }
}
