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
 * @author Olivier Terral (Geomatys)
 * @author Mehdi Sidhoum (Geomatys).
 */
public class EditionBarTag extends WidgetBaseTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.EditionBar";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.EditionBar";

    private ValueExpression empty = null;
    /**
     * OpenLayers.Control
     * */
    private ValueExpression drawPoint = null;
    private ValueExpression drawLine = null;
    private ValueExpression drawPolygon = null;
    private ValueExpression select = null;
    private ValueExpression modify = null;
    private ValueExpression drag = null;
    private ValueExpression resize = null;
    private ValueExpression rotate = null;
    private ValueExpression snapping = null;
    private ValueExpression floatingBar = null;
    private ValueExpression drawRegularPolygon = null;
    private ValueExpression regularPolygonSides = null;
    private ValueExpression deleteFeature = null;
    private ValueExpression split = null;

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
        component.setValueExpression("drawpoint",getDrawpoint());
        component.setValueExpression("drawLine",getDrawLine());
        component.setValueExpression("drawPolygon",getDrawPolygon());
        component.setValueExpression("select",getSelect());
        component.setValueExpression("modify",getModify());
        component.setValueExpression("drag",getDrag());
        component.setValueExpression("resize",getResize());
        component.setValueExpression("rotate",getRotate());
        component.setValueExpression("snapping",getSnapping());
        component.setValueExpression("floatingBar",getFloatingBar());
        component.setValueExpression("drawRegularPolygon",getDrawRegularPolygon());
        component.setValueExpression("regularPolygonSides",getRegularPolygonSides());
        component.setValueExpression("deleteFeature",getDeleteFeature());
        component.setValueExpression("split",getSplit());

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        setDrawpoint(null);
        setDrawLine(null);
        setDrawPolygon(null);
        setSelect(null);
        setModify(null);
        setDrag(null);
        setResize(null);
        setRotate(null);
        setSnapping(null);
        setFloatingBar(null);
        setDrawRegularPolygon(null);
        setRegularPolygonSides(null);
        setDeleteFeature(null);
        setSplit(null);
    }

    public void setEmpty(ValueExpression empty) {
        this.empty = empty;
    }

    /**
     * @return the drawpoint
     */
    public ValueExpression getDrawpoint() {
        return drawPoint;
    }

    /**
     * @param drawpoint the drawpoint to set
     */
    public void setDrawpoint(ValueExpression drawpoint) {
        this.drawPoint = drawpoint;
    }

    /**
     * @return the drawLine
     */
    public ValueExpression getDrawLine() {
        return drawLine;
    }

    /**
     * @param drawLine the drawLine to set
     */
    public void setDrawLine(ValueExpression drawLine) {
        this.drawLine = drawLine;
    }

    /**
     * @return the drawPolygon
     */
    public ValueExpression getDrawPolygon() {
        return drawPolygon;
    }

    /**
     * @param drawPolygon the drawPolygon to set
     */
    public void setDrawPolygon(ValueExpression drawPolygon) {
        this.drawPolygon = drawPolygon;
    }

    /**
     * @return the select
     */
    public ValueExpression getSelect() {
        return select;
    }

    /**
     * @param select the select to set
     */
    public void setSelect(ValueExpression select) {
        this.select = select;
    }

    /**
     * @return the modify
     */
    public ValueExpression getModify() {
        return modify;
    }

    /**
     * @param modify the modify to set
     */
    public void setModify(ValueExpression modify) {
        this.modify = modify;
    }

    /**
     * @return the drag
     */
    public ValueExpression getDrag() {
        return drag;
    }

    /**
     * @param drag the drag to set
     */
    public void setDrag(ValueExpression drag) {
        this.drag = drag;
    }

    /**
     * @return the resize
     */
    public ValueExpression getResize() {
        return resize;
    }

    /**
     * @param resize the resize to set
     */
    public void setResize(ValueExpression resize) {
        this.resize = resize;
    }

    /**
     * @return the rotate
     */
    public ValueExpression getRotate() {
        return rotate;
    }

    /**
     * @param rotate the rotate to set
     */
    public void setRotate(ValueExpression rotate) {
        this.rotate = rotate;
    }

    /**
     * @return the snapping
     */
    public ValueExpression getSnapping() {
        return snapping;
    }

    /**
     * @param snapping the snapping to set
     */
    public void setSnapping(ValueExpression snapping) {
        this.snapping = snapping;
    }

    /**
     * @return the floatingBar
     */
    public ValueExpression getFloatingBar() {
        return floatingBar;
    }

    /**
     * @param floatingBar the floatingBar to set
     */
    public void setFloatingBar(ValueExpression floatingBar) {
        this.floatingBar = floatingBar;
    }

    /**
     * @return the drawRegularPolygon
     */
    public ValueExpression getDrawRegularPolygon() {
        return drawRegularPolygon;
    }

    /**
     * @param drawRegularPolygon the drawRegularPolygon to set
     */
    public void setDrawRegularPolygon(ValueExpression drawRegularPolygon) {
        this.drawRegularPolygon = drawRegularPolygon;
    }

    /**
     * @return the regularPolygonSides
     */
    public ValueExpression getRegularPolygonSides() {
        return regularPolygonSides;
    }

    /**
     * @param regularPolygonSides the regularPolygonSides to set
     */
    public void setRegularPolygonSides(ValueExpression regularPolygonSides) {
        this.regularPolygonSides = regularPolygonSides;
    }

    /**
     * @return the deleteFeature
     */
    public ValueExpression getDeleteFeature() {
        return deleteFeature;
    }

    /**
     * @param deleteFeature the deleteFeature to set
     */
    public void setDeleteFeature(ValueExpression deleteFeature) {
        this.deleteFeature = deleteFeature;
    }

    /**
     * @return the split
     */
    public ValueExpression getSplit() {
        return split;
    }

    /**
     * @param split the split to set
     */
    public void setSplit(ValueExpression split) {
        this.split = split;
    }

}
