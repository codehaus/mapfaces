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
    private ValueExpression featuresAdded = null;
    private ValueExpression featuresRemoved = null;
    private ValueExpression featuresUpdated = null;

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
        component.setValueExpression("featuresAdded", featuresAdded);
        component.setValueExpression("featuresRemoved", featuresRemoved);
        component.setValueExpression("featuresUpdated", featuresUpdated);
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
        featuresAdded = null;
        featuresRemoved = null;
        featuresUpdated = null;
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
     * @return the featuresAdded
     */
    public ValueExpression getFeaturesAdded() {
        return featuresAdded;
    }

    /**
     * @param featuresAdded the featuresAdded to set
     */
    public void setFeaturesAdded(ValueExpression featuresAdded) {
        this.featuresAdded = featuresAdded;
    }

    /**
     * @return the featuresRemoved
     */
    public ValueExpression getFeaturesRemoved() {
        return featuresRemoved;
    }

    /**
     * @param featuresRemoved the featuresRemoved to set
     */
    public void setFeaturesRemoved(ValueExpression featuresRemoved) {
        this.featuresRemoved = featuresRemoved;
    }

    /**
     * @return the featuresUpdated
     */
    public ValueExpression getFeaturesUpdated() {
        return featuresUpdated;
    }

    /**
     * @param featuresUpdated the featuresUpdated to set
     */
    public void setFeaturesUpdated(ValueExpression featuresUpdated) {
        this.featuresUpdated = featuresUpdated;
    }
}
