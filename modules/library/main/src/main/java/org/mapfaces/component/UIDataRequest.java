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

package org.mapfaces.component;

import java.util.List;
import javax.faces.context.FacesContext;

/**
 * This component can render a response of getFeatureInfo or GetCoverage in multiple output component like popup or outputText ....etc.
 * @author Mehdi Sidhoum (Geomatys)
 */
public class UIDataRequest extends UIWidgetBase {

    public static final String FAMILIY = "org.mapfaces.DataRequest";
    /**
     * Format of feature information. The default value is application/vnd.ogc.wms_xml. Other options are text/xml, text/html, and text/plain.
     */
    private String outputFormat = "application/vnd.ogc.wms_xml";
    /**
     * This is a serializable object witch is containing in a Feature from FeatureLayer model. Note: it can be used for wms or no wms layers, this object is String for wms layers.
     */
    private Object dataResult;
    
    /**
     * an id for a target popup to display the data response.
     */
    private String targetPopupId = "";
    
    /**
     * This is a flag to allow the getFeatureInfo only on MFlayers type
     */
    private boolean featureLayerOnly;
    /**
     * Number of features per layer allowed. The default is 1.
     */
    private int featureCount;
    /**
     * This is a list of interested layers indicted by the name.
     */
    private List layersNames;
    /**
     * this is the latitude value after a click on the mappane was released.
     */
    private double outputLatitude;
    /**
     * This is the longitude value after a click on the mappane was released.
     */
    private double outputLongitude;
    /**
     * This is the output list of string taht represents the featureInfo values.
     */
    private Object featureInfoValues;
    /**
     * This list contains all getFeatureInfo urls released in the server side.
     */
    private Object requestUrlList;
    

    public UIDataRequest() {
        super();
        setRendererType("org.mapfaces.renderkit.html.DataRequest"); // this component has a renderer
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILIY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[12];
        values[0] = super.saveState(context);
        values[1] = outputFormat;
        values[2] = dataResult;
        values[3] = targetPopupId;
        values[4] = featureLayerOnly;
        values[5] = featureCount;
        values[6] = layersNames;
        values[7] = outputLatitude;
        values[8] = outputLongitude;
        values[9] = featureInfoValues;
        values[10] = requestUrlList;

        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        outputFormat = (String) values[1];
        dataResult = values[2];
        targetPopupId = (String) values[3];
        featureLayerOnly = (Boolean) values[4];
        featureCount = (Integer) values[5];
        layersNames = (List) values[6];
        outputLatitude = (Double) values[7];
        outputLongitude = (Double) values[8];
        featureInfoValues = values[9];
        requestUrlList = values[10];
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public Object getDataResult() {
        return dataResult;
    }

    public void setDataResult(Object dataResult) {
        this.dataResult = dataResult;
    }

    public String getTargetPopupId() {
        return targetPopupId;
    }

    public void setTargetPopupId(String targetPopupId) {
        this.targetPopupId = targetPopupId;
    }

    public boolean isFeatureLayerOnly() {
        return featureLayerOnly;
    }

    public void setFeatureLayerOnly(boolean featureLayerOnly) {
        this.featureLayerOnly = featureLayerOnly;
    }

    public int getFeatureCount() {
        return featureCount;
    }

    public void setFeatureCount(int featureCount) {
        this.featureCount = featureCount;
    }

    public List getLayersNames() {
        return layersNames;
    }

    public void setLayersNames(List layersNames) {
        this.layersNames = layersNames;
    }

    public double getOutputLatitude() {
        return outputLatitude;
    }

    public void setOutputLatitude(double outputLatitude) {
        this.outputLatitude = outputLatitude;
    }

    public double getOutputLongitude() {
        return outputLongitude;
    }

    public void setOutputLongitude(double outputLongitude) {
        this.outputLongitude = outputLongitude;
    }

    public Object getFeatureInfoValues() {
        return featureInfoValues;
    }

    public void setFeatureInfoValues(Object featureInfoValues) {
        this.featureInfoValues = featureInfoValues;
    }

    public Object getRequestUrlList() {
        return requestUrlList;
    }

    public void setRequestUrlList(Object requestUrlList) {
        this.requestUrlList = requestUrlList;
    }
}
