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

package org.mapfaces.taglib.layer;

import org.mapfaces.taglib.*;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.ActionSource2;
import javax.faces.component.UIComponent;

/**
 *
 * @author leopratlong (Geomatys)
 */
public class SvgLayerTag extends LayerTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.SvgLayer";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.SvgLayer";

    private ValueExpression value = null;
    private ValueExpression cliToServOnly = null;
    private ValueExpression featureAdded = null;
    private ValueExpression featureRemoved = null;
    private ValueExpression featureBeforeUpdate = null;
    private ValueExpression featureAfterUpdate = null;
    private ValueExpression title = null;
    private ValueExpression opacity = null;
    private ValueExpression reRender = null;
    private ValueExpression width = null;
    private ValueExpression fillColor = null;
    private ValueExpression strokeColor = null;
    private ValueExpression selFillColor = null;
    private ValueExpression selStrokeColor = null;
    private MethodExpression action = null;

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
        component.setValueExpression("value", value);
        component.setValueExpression("cliToServOnly", cliToServOnly);
        component.setValueExpression("featureAdded", featureAdded);
        component.setValueExpression("featureRemoved", featureRemoved);
        component.setValueExpression("featureBeforeUpdate", featureBeforeUpdate);
        component.setValueExpression("featureAfterUpdate", featureAfterUpdate);
        component.setValueExpression("title", title);
        component.setValueExpression("opacity", opacity);
        component.setValueExpression("reRender", reRender);
        component.setValueExpression("width", width);
        component.setValueExpression("fillColor", fillColor);
        component.setValueExpression("strokeColor", strokeColor);
        component.setValueExpression("selFillColor", selFillColor);
        component.setValueExpression("selStrokeColor", selStrokeColor);

        if (getAction() != null) {
            ((ActionSource2) component).setActionExpression(getAction());
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        value = null;
        cliToServOnly = null;
        featureAdded = null;
        featureRemoved = null;
        featureBeforeUpdate = null;
        featureAfterUpdate = null;
        action = null;
        title = null;
        opacity = null;
        reRender = null;
        width = null;
        fillColor = null;
        strokeColor = null;
        selFillColor = null;
        selStrokeColor = null;
    }

    /**
     * @return the value
     */
    public ValueExpression getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(ValueExpression value) {
        this.value = value;
    }

    /**
     * @return the cliToServOnly
     */
    public ValueExpression getCliToServOnly() {
        return cliToServOnly;
    }

    /**
     * @param cliToServOnly the cliToServOnly to set
     */
    public void setCliToServOnly(ValueExpression cliToServOnly) {
        this.cliToServOnly = cliToServOnly;
    }

    /**
     * @return the featureAdded
     */
    public ValueExpression getFeatureAdded() {
        return featureAdded;
    }

    /**
     * @param featureAdded the featureAdded to set
     */
    public void setFeatureAdded(ValueExpression featureAdded) {
        this.featureAdded = featureAdded;
    }

    /**
     * @return the featureRemoved
     */
    public ValueExpression getFeatureRemoved() {
        return featureRemoved;
    }

    /**
     * @param featureRemoved the featureRemoved to set
     */
    public void setFeatureRemoved(ValueExpression featureRemoved) {
        this.featureRemoved = featureRemoved;
    }

    /**
     * @return the featureBeforeUpdate
     */
    public ValueExpression getFeatureBeforeUpdate() {
        return featureBeforeUpdate;
    }

    /**
     * @param featureBeforeUpdate the featureBeforeUpdate to set
     */
    public void setFeatureBeforeUpdate(ValueExpression featureBeforeUpdate) {
        this.featureBeforeUpdate = featureBeforeUpdate;
    }

    /**
     * @return the featureAfterUpdate
     */
    public ValueExpression getFeatureAfterUpdate() {
        return featureAfterUpdate;
    }

    /**
     * @param featureAfterUpdate the featureAfterUpdate to set
     */
    public void setFeatureAfterUpdate(ValueExpression featureAfterUpdate) {
        this.featureAfterUpdate = featureAfterUpdate;
    }

    /**
     * @return the action
     */
    public MethodExpression getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(MethodExpression action) {
        this.action = action;
    }

    /**
     * @return the title
     */
    public ValueExpression getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(ValueExpression title) {
        this.title = title;
    }

    /**
     * @return the opacity
     */
    public ValueExpression getOpacity() {
        return opacity;
    }

    /**
     * @param opacity the opacity to set
     */
    public void setOpacity(ValueExpression opacity) {
        this.opacity = opacity;
    }

    /**
     * @return the reRender
     */
    public ValueExpression getReRender() {
        return reRender;
    }

    /**
     * @param reRender the reRender to set
     */
    public void setReRender(ValueExpression reRender) {
        this.reRender = reRender;
    }

    /**
     * @return the width
     */
    public ValueExpression getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(ValueExpression width) {
        this.width = width;
    }

    /**
     * @return the fillColor
     */
    public ValueExpression getFillColor() {
        return fillColor;
    }

    /**
     * @param fillColor the fillColor to set
     */
    public void setFillColor(ValueExpression fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * @return the strokeColor
     */
    public ValueExpression getStrokeColor() {
        return strokeColor;
    }

    /**
     * @param strokeColor the strokeColor to set
     */
    public void setStrokeColor(ValueExpression strokeColor) {
        this.strokeColor = strokeColor;
    }

    /**
     * @return the selFillColor
     */
    public ValueExpression getSelFillColor() {
        return selFillColor;
    }

    /**
     * @param selFillColor the selFillColor to set
     */
    public void setSelFillColor(ValueExpression selFillColor) {
        this.selFillColor = selFillColor;
    }

    /**
     * @return the selStrokeColor
     */
    public ValueExpression getSelStrokeColor() {
        return selStrokeColor;
    }

    /**
     * @param selStrokeColor the selStrokeColor to set
     */
    public void setSelStrokeColor(ValueExpression selStrokeColor) {
        this.selStrokeColor = selStrokeColor;
    }
}
