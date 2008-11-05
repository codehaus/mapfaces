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
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
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

    /** Creates a new instance of UIButtonBar */
    public UIButtonBar() {
        super();
        setRendererType("org.mapfaces.renderkit.html.ButtonBar"); // this component has a renderer
        if (isDebug()) {
            System.out.println("[UIButtonBar] constructor----------------------");
        }
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
        final Object values[] = new Object[14];
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
        save = (boolean) (Boolean) values[9];
        featureInfo = (boolean) (Boolean) values[10];
        measureDistance = (boolean) (Boolean) values[11];
        measureArea = (boolean) (Boolean) values[12];
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

}
