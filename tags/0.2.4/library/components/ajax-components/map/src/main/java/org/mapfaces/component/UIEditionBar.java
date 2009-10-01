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
public class UIEditionBar extends UIWidgetBase {

    public static final String FAMILIY = "org.mapfaces.EditionBar";
    private boolean drawPoint = true;
    private boolean drawLine = true;
    private boolean drawPolygon = true;
    private boolean modify = true;
    private boolean deleteFeature = true;
    private boolean select = false;
    private boolean drag = false;
    private boolean resize = false;
    private boolean rotate = false;
    private boolean snapping = false;
    private boolean floatingBar = false;
    private boolean drawRegularPolygon = false;
    private int regularPolygonSides = 4;
    private boolean split = false;

    /** Creates a new instance of UIEditionBar */
    public UIEditionBar() {
        super();
        setRendererType("org.mapfaces.renderkit.html.EditionBar"); // this component has a renderer
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
        final Object values[] = new Object[15];
        values[0] = super.saveState(context);
        values[1] = isDrawPoint();
        values[2] = isDrawLine();
        values[3] = isDrawPolygon();
        values[4] = isModify();
        values[5] = isSelect();
        values[6] = isDrag();
        values[7] = isResize();
        values[8] = isRotate();
        values[9] = isSnapping();
        values[10] = isFloatingBar();
        values[11] = isDrawRegularPolygon();
        values[12] = getRegularPolygonSides();
        values[13] = isDeleteFeature();
        values[14] = isSplit();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setDrawPoint((boolean) (Boolean) values[1]);
        setDrawLine((boolean) (Boolean) values[2]);
        setDrawPolygon((boolean) (Boolean) values[3]);
        setModify((boolean) (Boolean) values[4]);
        setSelect((boolean) (Boolean) values[5]);
        setDrag((boolean) (Boolean) values[6]);
        setResize((boolean) (Boolean) values[7]);
        setRotate((boolean) (Boolean) values[8]);
        setSnapping((boolean) (Boolean) values[9]);
        setFloatingBar((boolean) (Boolean) values[10]);
        setDrawRegularPolygon((boolean) (Boolean) values[11]);
        setRegularPolygonSides((Integer) values[12]);
        setDeleteFeature((boolean) (Boolean) values[13]);
        setSplit((boolean) (Boolean) values[14]);
    }

    /**
     * @return the drawPoint
     */
    public boolean isDrawPoint() {
        return drawPoint;
    }

    /**
     * @param drawPoint the drawPoint to set
     */
    public void setDrawPoint(boolean drawPoint) {
        this.drawPoint = drawPoint;
    }

    /**
     * @return the drawLine
     */
    public boolean isDrawLine() {
        return drawLine;
    }

    /**
     * @param drawLine the drawLine to set
     */
    public void setDrawLine(boolean drawLine) {
        this.drawLine = drawLine;
    }

    /**
     * @return the drawPolygon
     */
    public boolean isDrawPolygon() {
        return drawPolygon;
    }

    /**
     * @param drawPolygon the drawPolygon to set
     */
    public void setDrawPolygon(boolean drawPolygon) {
        this.drawPolygon = drawPolygon;
    }

    /**
     * @return the modify
     */
    public boolean isModify() {
        return modify;
    }

    /**
     * @param modify the modify to set
     */
    public void setModify(boolean modify) {
        this.modify = modify;
    }

    /**
     * @return the select
     */
    public boolean isSelect() {
        return select;
    }

    /**
     * @param select the select to set
     */
    public void setSelect(boolean select) {
        this.select = select;
    }

    /**
     * @return the drag
     */
    public boolean isDrag() {
        return drag;
    }

    /**
     * @param drag the drag to set
     */
    public void setDrag(boolean drag) {
        this.drag = drag;
    }

    /**
     * @return the resize
     */
    public boolean isResize() {
        return resize;
    }

    /**
     * @param resize the resize to set
     */
    public void setResize(boolean resize) {
        this.resize = resize;
    }

    /**
     * @return the rotate
     */
    public boolean isRotate() {
        return rotate;
    }

    /**
     * @param rotate the rotate to set
     */
    public void setRotate(boolean rotate) {
        this.rotate = rotate;
    }

    /**
     * @return the snapping
     */
    public boolean isSnapping() {
        return snapping;
    }

    /**
     * @param snapping the snapping to set
     */
    public void setSnapping(boolean snapping) {
        this.snapping = snapping;
    }

    /**
     * @return the floatingBar
     */
    public boolean isFloatingBar() {
        return floatingBar;
    }

    /**
     * @param floatingBar the floatingBar to set
     */
    public void setFloatingBar(boolean floatingBar) {
        this.floatingBar = floatingBar;
    }

    /**
     * @return the drawRegularPolygon
     */
    public boolean isDrawRegularPolygon() {
        return drawRegularPolygon;
    }

    /**
     * @param drawRegularPolygon the drawRegularPolygon to set
     */
    public void setDrawRegularPolygon(boolean drawRegularPolygon) {
        this.drawRegularPolygon = drawRegularPolygon;
    }

    /**
     * @return the regularPolygonSides
     */
    public int getRegularPolygonSides() {
        return regularPolygonSides;
    }

    /**
     * @param regularPolygonSides the regularPolygonSides to set
     */
    public void setRegularPolygonSides(int regularPolygonSides) {
        this.regularPolygonSides = regularPolygonSides;
    }

    /**
     * @return the deleteFeature
     */
    public boolean isDeleteFeature() {
        return deleteFeature;
    }

    /**
     * @param deleteFeature the deleteFeature to set
     */
    public void setDeleteFeature(boolean deleteFeature) {
        this.deleteFeature = deleteFeature;
    }

    /**
     * @return the split
     */
    public boolean isSplit() {
        return split;
    }

    /**
     * @param split the split to set
     */
    public void setSplit(boolean split) {
        this.split = split;
    }
}