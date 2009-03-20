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
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.ActionSource;
import javax.faces.component.ActionSource2;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;
import org.mapfaces.component.UIDataRequest;

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
    private ValueExpression featureLayerOnly = null;
    private ValueExpression featureCount = null;
    private ValueExpression layersNames = null;
    private ValueExpression outputLatitude = null;
    private ValueExpression outputLongitude = null;
    private ValueExpression featureInfoValues = null;
    private ValueExpression requestUrlList = null;
    private MethodExpression action = null;
    private MethodExpression actionListener = null;
    private ValueExpression reRender = null;

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

        final FacesContext context = FacesContext.getCurrentInstance();
        final ExpressionFactory ef = context.getApplication().getExpressionFactory();
        final UIDataRequest datarequest = (UIDataRequest) component;

        if (layersNames != null) {
            if (layersNames.getExpressionString() != null && layersNames.getExpressionString().contains("#")) {
                final ValueExpression vex = ef.createValueExpression(context.getELContext(), layersNames.getExpressionString(), java.util.List.class);
                datarequest.setLayersNames((List) vex.getValue(context.getELContext()));
            }
        }
        component.setValueExpression("layersNames", layersNames);
        component.setValueExpression("outputFormat", outputFormat);
        component.setValueExpression("dataResult", dataResult);
        component.setValueExpression("targetPopupId", targetPopupId);
        component.setValueExpression("featureLayerOnly", featureLayerOnly);
        component.setValueExpression("featureCount", featureCount);
        component.setValueExpression("outputLatitude", outputLatitude);
        component.setValueExpression("outputLongitude", outputLongitude);
        component.setValueExpression("featureInfoValues", featureInfoValues);
        component.setValueExpression("requestUrlList", requestUrlList);
        component.setValueExpression("reRender", reRender);
        if (actionListener != null) {
            ((ActionSource) component).addActionListener(new MethodExpressionActionListener(actionListener));
        }
        if (action != null) {
            ((ActionSource2) component).setActionExpression(action);
        }
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
        featureLayerOnly = null;
        featureCount = null;
        layersNames = null;
        outputLatitude = null;
        outputLongitude = null;
        featureInfoValues = null;
        requestUrlList = null;
        action = null;
        actionListener = null;
        reRender = null;
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

    public ValueExpression getFeatureCount() {
        return featureCount;
    }

    public void setFeatureCount(ValueExpression featureCount) {
        this.featureCount = featureCount;
    }

    public ValueExpression getFeatureLayerOnly() {
        return featureLayerOnly;
    }

    public void setFeatureLayerOnly(ValueExpression featureLayerOnly) {
        this.featureLayerOnly = featureLayerOnly;
    }

    public ValueExpression getLayersNames() {
        return layersNames;
    }

    public void setLayersNames(ValueExpression layersNames) {
        this.layersNames = layersNames;
    }

    public ValueExpression getOutputLatitude() {
        return outputLatitude;
    }

    public void setOutputLatitude(ValueExpression outputLatitude) {
        this.outputLatitude = outputLatitude;
    }

    public ValueExpression getOutputLongitude() {
        return outputLongitude;
    }

    public void setOutputLongitude(ValueExpression outputLongitude) {
        this.outputLongitude = outputLongitude;
    }

    public ValueExpression getFeatureInfoValues() {
        return featureInfoValues;
    }

    public void setFeatureInfoValues(ValueExpression featureInfoValues) {
        this.featureInfoValues = featureInfoValues;
    }

    public ValueExpression getRequestUrlList() {
        return requestUrlList;
    }

    public void setRequestUrlList(ValueExpression requestUrlList) {
        this.requestUrlList = requestUrlList;
    }

    public MethodExpression getAction() {
        return action;
    }

    public void setAction(MethodExpression action) {
        this.action = action;
    }

    public MethodExpression getActionListener() {
        return actionListener;
    }

    public void setActionListener(MethodExpression actionListener) {
        this.actionListener = actionListener;
    }

    public ValueExpression getReRender() {
        return reRender;
    }

    public void setReRender(ValueExpression reRender) {
        this.reRender = reRender;
    }
}
