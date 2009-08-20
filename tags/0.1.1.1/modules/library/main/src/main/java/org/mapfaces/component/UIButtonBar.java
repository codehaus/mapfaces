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
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 */
public class UIButtonBar extends UIWidgetBase {

    public static final String FAMILIY = "org.mapfaces.ButtonBar";
    private boolean zoomIn = true;
    private boolean zoomOut = true;
    private boolean pan = true;
    private boolean zoomMaxExtent = true;
    private boolean history = true;
    private boolean panEffect = false;
    private boolean floatingBar = false;
    private boolean graticule = false;
    private boolean save = false;
    private boolean featureInfo = false;
    private boolean measureDistance = false;
    private boolean measureArea = false;
    private boolean selectionZoomBox = false;
    private String colorSelectionBox = "blue";
    private String focusIdSelectionBox = "";
    private String northIdSelectionBox = "";
    private String southIdSelectionBox = "";
    private String eastIdSelectionBox = "";
    private String westIdSelectionBox = "";
    private String reRender = "";
    private boolean callAjaxRegion;

    /** Creates a new instance of UIButtonBar */
    public UIButtonBar() {
        super();
        setRendererType("org.mapfaces.renderkit.html.ButtonBar"); // this component has a renderer
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
        final Object values[] = new Object[27];
        values[0] = super.saveState(context);
        values[1] = zoomIn;
        values[2] = zoomOut;
        values[3] = pan;
        values[4] = zoomMaxExtent;
        values[5] = history;
        values[6] = panEffect;
        values[7] = floatingBar;
        values[8] = graticule;
        values[9] = save;
        values[10] = featureInfo;
        values[11] = measureDistance;
        values[12] = measureArea;
        values[13] = selectionZoomBox;
        values[14] = colorSelectionBox;
        values[15] = focusIdSelectionBox;
        values[16] = northIdSelectionBox;
        values[17] = southIdSelectionBox;
        values[18] = eastIdSelectionBox;
        values[19] = westIdSelectionBox;
        values[20] = reRender;
        values[21] = callAjaxRegion;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        zoomIn = (Boolean) values[1];
        zoomOut = (Boolean) values[2];
        pan = (Boolean) values[3];
        zoomMaxExtent = (Boolean) values[4];
        history = (Boolean) values[5];
        panEffect = (Boolean) values[6];
        floatingBar = (Boolean) values[7];
        graticule = (Boolean) values[8];
        save = (Boolean) values[9];
        featureInfo = (Boolean) values[10];
        measureDistance = (Boolean) values[11];
        measureArea = (Boolean) values[12];
        selectionZoomBox = (Boolean) values[13];
        colorSelectionBox = (String) values[14];
        focusIdSelectionBox = (String) values[15];
        northIdSelectionBox = (String) values[16];
        southIdSelectionBox = (String) values[17];
        eastIdSelectionBox = (String) values[18];
        westIdSelectionBox = (String) values[19];
        reRender = (String) values[20];
        callAjaxRegion = (Boolean) values[21];
    }

    public boolean isZoomIn() {
        return zoomIn;
    }

    public void setZoomIn(final boolean zoomIn) {
        this.zoomIn = zoomIn;
    }

    public boolean isZoomOut() {
        return zoomOut;
    }

    public void setZoomOut(final boolean zoomOut) {
        this.zoomOut = zoomOut;
    }

    public boolean isPan() {
        return pan;
    }

    public void setPan(final boolean pan) {
        this.pan = pan;
    }

    public boolean isZoomMaxExtent() {
        return zoomMaxExtent;
    }

    public void setZoomMaxExtent(final boolean zoomMaxExtent) {
        this.zoomMaxExtent = zoomMaxExtent;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(final boolean history) {
        this.history = history;
    }

    public boolean isPanEffect() {
        return panEffect;
    }

    public void setPanEffect(final boolean panEffect) {
        this.panEffect = panEffect;
    }

    public boolean isFloatingBar() {
        return floatingBar;
    }

    public void setFloatingBar(final boolean floatingbar) {
        this.floatingBar = floatingbar;
    }

    public boolean isGraticule() {
        return graticule;
    }

    public void setGraticule(final boolean graticule) {
        this.graticule = graticule;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(final boolean save) {
        this.save = save;
    }

    public boolean isFeatureInfo() {
        return featureInfo;
    }

    public void setFeatureInfo(final boolean featureInfo) {
        this.featureInfo = featureInfo;
    }

    public boolean isMeasureDistance() {
        return measureDistance;
    }

    public void setMeasureDistance(final boolean measureDistance) {
        this.measureDistance = measureDistance;
    }

    public boolean isMeasureArea() {
        return measureArea;
    }

    public void setMeasureArea(final boolean measureArea) {
        this.measureArea = measureArea;
    }

    public boolean isSelectionZoomBox() {
        return selectionZoomBox;
    }

    public void setSelectionZoomBox(final boolean selectionZoomBox) {
        this.selectionZoomBox = selectionZoomBox;
    }

    public String getColorSelectionBox() {
        return colorSelectionBox;
    }

    public void setColorSelectionBox(final String colorSelectionBox) {
        this.colorSelectionBox = colorSelectionBox;
    }

    public String getFocusIdSelectionBox() {
        return focusIdSelectionBox;
    }

    public void setFocusIdSelectionBox(final String focusIdSelectionBox) {
        this.focusIdSelectionBox = focusIdSelectionBox;
    }

    public String getNorthIdSelectionBox() {
        return northIdSelectionBox;
    }

    public void setNorthIdSelectionBox(final String northIdSelectionBox) {
        this.northIdSelectionBox = northIdSelectionBox;
    }

    public String getSouthIdSelectionBox() {
        return southIdSelectionBox;
    }

    public void setSouthIdSelectionBox(final String southIdSelectionBox) {
        this.southIdSelectionBox = southIdSelectionBox;
    }

    public String getEastIdSelectionBox() {
        return eastIdSelectionBox;
    }

    public void setEastIdSelectionBox(final String eastIdSelectionBox) {
        this.eastIdSelectionBox = eastIdSelectionBox;
    }

    public String getWestIdSelectionBox() {
        return westIdSelectionBox;
    }

    public void setWestIdSelectionBox(final String westIdSelectionBox) {
        this.westIdSelectionBox = westIdSelectionBox;
    }

    public String getReRender() {
        return reRender;
    }

    public void setReRender(String reRender) {
        this.reRender = reRender;
    }

    /**
     * @return the callAjaxRegion
     */
    public boolean isCallAjaxRegion() {
        return callAjaxRegion;
    }

    /**
     * @param callAjaxRegion the callAjaxRegion to set
     */
    public void setCallAjaxRegion(boolean callAjaxRegion) {
        this.callAjaxRegion = callAjaxRegion;
    }
}
