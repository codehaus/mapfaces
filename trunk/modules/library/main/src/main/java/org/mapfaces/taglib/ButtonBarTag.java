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
 * @author Olivier Terral.
 */
public class ButtonBarTag extends WidgetBaseTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.ButtonBar";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.ButtonBar";
    private ValueExpression empty = null;
    /**
     * OpenLayers.Control
     * */
    private ValueExpression zoomIn = null;
    private ValueExpression zoomOut = null;
    private ValueExpression pan = null;
    private ValueExpression zoomMaxExtent = null;
    private ValueExpression history = null;
    private ValueExpression panEffect = null;
    private ValueExpression floatingBar = null;
    private ValueExpression graticule = null;
    private ValueExpression save = null;
    private ValueExpression featureInfo = null;
    private ValueExpression measureDistance = null;
    private ValueExpression measureArea = null;
    private ValueExpression selectionZoomBox = null;
    private ValueExpression colorSelectionBox = null;
    private ValueExpression focusIdSelectionBox = null;
    private ValueExpression northIdSelectionBox = null;
    private ValueExpression southIdSelectionBox = null;
    private ValueExpression eastIdSelectionBox = null;
    private ValueExpression westIdSelectionBox = null;

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
        component.setValueExpression("empty", empty);
        component.setValueExpression("zoomIn", zoomIn);
        component.setValueExpression("zoomOut", zoomOut);
        component.setValueExpression("pan", pan);
        component.setValueExpression("zoomMaxExtent", zoomMaxExtent);
        component.setValueExpression("history", history);
        component.setValueExpression("panEffect", panEffect);
        component.setValueExpression("floatingBar",floatingBar);
        component.setValueExpression("graticule",graticule);
        component.setValueExpression("save",save);
        component.setValueExpression("featureInfo",featureInfo);
        component.setValueExpression("measureDistance",measureDistance);
        component.setValueExpression("measureArea",measureArea);
        component.setValueExpression("selectionZoomBox",selectionZoomBox);
        component.setValueExpression("colorSelectionBox",colorSelectionBox);
        component.setValueExpression("focusIdSelectionBox",focusIdSelectionBox);
        component.setValueExpression("northIdSelectionBox",northIdSelectionBox);
        component.setValueExpression("southIdSelectionBox",southIdSelectionBox);
        component.setValueExpression("eastIdSelectionBox",eastIdSelectionBox);
        component.setValueExpression("westIdSelectionBox",westIdSelectionBox);

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        setEmpty(null);
        setZoomIn(null);
        setZoomOut(null);
        setPan(null);
        setZoomMaxExtent(null);
        setHistory(null);
        setPanEffect(null);
        setFloatingBar(null);
        setGraticule(null);
        setSave(null);
        setFeatureInfo(null);
        setMeasureDistance(null);
        setMeasureArea(null);
        setSelectionZoomBox(null);
        setColorSelectionBox(null);
        setFocusIdSelectionBox(null);
        setNorthIdSelectionBox(null);
        setSouthIdSelectionBox(null);
        setEastIdSelectionBox(null);
        setWestIdSelectionBox(null);
    }

    public void setEmpty(ValueExpression empty) {
        this.empty = empty;
    }

    public ValueExpression getZoomIn() {
        return zoomIn;
    }

    public ValueExpression getZoomOut() {
        return zoomOut;
    }

    public ValueExpression getPan() {
        return pan;
    }

    public void setZoomIn(ValueExpression zoomIn) {
        this.zoomIn = zoomIn;
    }

    public void setZoomOut(ValueExpression zoomOut) {
        this.zoomOut = zoomOut;
    }

    public void setPan(ValueExpression pan) {
        this.pan = pan;
    }

    public ValueExpression getZoomMaxExtent() {
        return zoomMaxExtent;
    }

    public void setZoomMaxExtent(ValueExpression zoomMaxExtent) {
        this.zoomMaxExtent = zoomMaxExtent;
    }

    public ValueExpression getHistory() {
        return history;
    }

    public void setHistory(ValueExpression history) {
        this.history = history;
    }

    public ValueExpression getPanEffect() {
        return panEffect;
    }

    public void setPanEffect(ValueExpression panEffect) {
        this.panEffect = panEffect;
    }

    public ValueExpression getFloatingBar() {
        return floatingBar;
    }

    public void setFloatingBar(ValueExpression floatingBar) {
        this.floatingBar = floatingBar;
    }

    public void setGraticule(ValueExpression exp) {
        this.graticule = exp;
    }

    public ValueExpression getGraticule(){
        return graticule;
    }

    public ValueExpression getSave() {
        return save;
    }

    public void setSave(ValueExpression save) {
        this.save = save;
    }

    public ValueExpression getFeatureInfo() {
        return featureInfo;
    }

    public void setFeatureInfo(ValueExpression featureInfo) {
        this.featureInfo = featureInfo;
    }

    public ValueExpression getMeasureDistance() {
        return measureDistance;
    }

    public void setMeasureDistance(ValueExpression measureDistance) {
        this.measureDistance = measureDistance;
    }

    public ValueExpression getMeasureArea() {
        return measureArea;
    }

    public void setMeasureArea(ValueExpression measureArea) {
        this.measureArea = measureArea;
    }

    public ValueExpression getSelectionZoomBox() {
        return selectionZoomBox;
    }

    public void setSelectionZoomBox(ValueExpression selectionZoomBox) {
        this.selectionZoomBox = selectionZoomBox;
    }

    public ValueExpression getColorSelectionBox() {
        return colorSelectionBox;
    }

    public void setColorSelectionBox(ValueExpression colorSelectionBox) {
        this.colorSelectionBox = colorSelectionBox;
    }

    public ValueExpression getFocusIdSelectionBox() {
        return focusIdSelectionBox;
    }

    public void setFocusIdSelectionBox(ValueExpression focusIdSelectionBox) {
        this.focusIdSelectionBox = focusIdSelectionBox;
    }

    public ValueExpression getNorthIdSelectionBox() {
        return northIdSelectionBox;
    }

    public void setNorthIdSelectionBox(ValueExpression northIdSelectionBox) {
        this.northIdSelectionBox = northIdSelectionBox;
    }

    public ValueExpression getSouthIdSelectionBox() {
        return southIdSelectionBox;
    }

    public void setSouthIdSelectionBox(ValueExpression southIdSelectionBox) {
        this.southIdSelectionBox = southIdSelectionBox;
    }

    public ValueExpression getEastIdSelectionBox() {
        return eastIdSelectionBox;
    }

    public void setEastIdSelectionBox(ValueExpression eastIdSelectionBox) {
        this.eastIdSelectionBox = eastIdSelectionBox;
    }

    public ValueExpression getWestIdSelectionBox() {
        return westIdSelectionBox;
    }

    public void setWestIdSelectionBox(ValueExpression westIdSelectionBox) {
        this.westIdSelectionBox = westIdSelectionBox;
    }
}
