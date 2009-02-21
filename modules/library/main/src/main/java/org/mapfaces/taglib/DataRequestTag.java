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
 * @author Mehdi Sidhoum (Geomatys).
 */
public class DataRequestTag extends WidgetBaseTag {
    
    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.DataRequest";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.DataRequest";
    
    private ValueExpression outputFormat = null;
    private ValueExpression dataResult = null;
    private ValueExpression targetPopupId = null;
    private ValueExpression mfLayersOnly = null;
    private ValueExpression featureCount = null;

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
    protected void setProperties(UIComponent component) {
        // always call the superclass method
        super.setProperties(component);
        component.setValueExpression("outputFormat", outputFormat);
        component.setValueExpression("dataResult", dataResult);
        component.setValueExpression("targetPopupId", targetPopupId);
        component.setValueExpression("mfLayersOnly", mfLayersOnly);
        component.setValueExpression("featureCount", featureCount);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        outputFormat = null;
        dataResult = null;
        targetPopupId = null;
        mfLayersOnly = null;
        featureCount = null;
    }

    public ValueExpression getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(ValueExpression outputFormat) {
        this.outputFormat = outputFormat;
    }

    public ValueExpression getDataResult() {
        return dataResult;
    }

    public void setDataResult(ValueExpression dataResult) {
        this.dataResult = dataResult;
    }

    public ValueExpression getTargetPopupId() {
        return targetPopupId;
    }

    public void setTargetPopupId(ValueExpression targetPopupId) {
        this.targetPopupId = targetPopupId;
    }

    public ValueExpression getMfLayersOnly() {
        return mfLayersOnly;
    }

    public void setMfLayersOnly(ValueExpression mfLayersOnly) {
        this.mfLayersOnly = mfLayersOnly;
    }

    public ValueExpression getFeatureCount() {
        return featureCount;
    }

    public void setFeatureCount(ValueExpression featureCount) {
        this.featureCount = featureCount;
    }

}
