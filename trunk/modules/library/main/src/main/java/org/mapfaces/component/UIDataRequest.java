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

import javax.faces.context.FacesContext;

/**
 * This component can render a response of getFeatureInfo or GetCoverage in multiple output component like popup or outputText ....etc.
 * @author Mehdi Sidhoum (Geomatys)
 */
public class UIDataRequest extends UIWidgetBase {

    public static final String FAMILIY = "org.mapfaces.DataRequest";
    /**
     * This is the output format of the request. default is text/html, you can use text/xml or simple text.
     */
    private String outputFormat = "text/html";
    /**
     * This is the generated url.
     */
    private String url = "";
    /**
     * This is a serializable object witch is containing in a Feature from MFlayer component. Note: it can be used for wms or no wms layers, this object is String for wms layers.
     */
    private Object dataResult;
    
    /**
     * an id for a target popup to display the data response.
     */
    private String targetPopupId = "";
    

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
        final Object values[] = new Object[10];
        values[0] = super.saveState(context);
        values[1] = outputFormat;
        values[2] = url;
        values[3] = dataResult;
        values[4] = targetPopupId;

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
        url = (String) values[2];
        dataResult = values[3];
        targetPopupId = (String) values[4];
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
