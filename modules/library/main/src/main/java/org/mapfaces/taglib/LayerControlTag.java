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

public class LayerControlTag extends WidgetBaseTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.LayerControl";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.LayerControl";
    /**
     * The style of the treeTable sub component.
     */
    private ValueExpression styleTreeTable = null;
    /**
     * The style of the treePanel sub component.
     */
    private ValueExpression styleTreePanel = null;
    /**
     * The width of the treeColumn sub component.
     */
    private ValueExpression widthTreeColumn = null;
    /**
     * The width of the VisibilityColumn sub component.
     */
    private ValueExpression widthVisibilityColumn = null;
    /**
     * The width of the OpacityColumn sub component.
     */
    private ValueExpression widthOpacityColumn = null;
    /**
     * The width of the ElevationColumn sub component.
     */
    private ValueExpression widthElevationColumn = null;
    /**
     * The width of the TimeColumn sub component.
     */
    private ValueExpression widthTimeColumn = null;
    /**
     * The panel title.
     */
    private ValueExpression titlePanel = null;
    /**
     * The header title of the treeColumn sub component.
     */
    private ValueExpression headerTreeColumn = null;
    
    /**
     * This is the style for odd treeLines sub components.
     */
    private ValueExpression styleOddLines = null;
    /**
     * This is the style for even treeLines sub components.
     */
    private ValueExpression styleEvenLines = null;
    /**
     * Boolean to load Mootools script or not.
     */
    private ValueExpression mootools = null;
    /**
     *  Boolean to load compressed TreeTable JS library or not.
     */
    private ValueExpression minifyJS = null;
    /**
     * Flag that indicates if the Elevation columns must be displayed or not.
     */
    private ValueExpression elevationColumn = null;
    /**
     * Flag that indicates if the visibility columns must be displayed or not.
     */
    private ValueExpression  visibilityColumn = null;
    /**
     * Flag that indicates if the opacity columns must be displayed or not.
     */
    private ValueExpression  opacityColumn = null;
    /**
     * Flag that indicates if the time columns must be displayed or not.
     */
    private ValueExpression  timeColumn = null;
    /**
     * Flag that indicates if the layerInfo  must be displayed or not.
     */
    private ValueExpression  layerInfo = null;
    /**
     * Flag that indicates if colorMapEditor must be displayed or not.
     */
    private ValueExpression  colorMapEditor = null;

    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return RENDER_TYPE;
    }

    @Override
    public void release() {
        super.release();
        setStyleTreeTable(null);
        setStyleTreePanel(null);
        setWidthTreeColumn(null);
        setWidthElevationColumn(null);
        setWidthOpacityColumn(null);
        setWidthTimeColumn(null);
        setWidthVisibilityColumn(null);
        setTitlePanel(null);
        setHeaderTreeColumn(null);
        setStyleEvenLines(null);
        setStyleOddLines(null);
        setMootools(null);
        setMinifyJS(null);
        setElevationColumn(null);
        setVisibilityColumn(null);
        setOpacityColumn(null);
        setTimeColumn(null);
        setLayerInfo(null);
        setColorMapEditor(null);
    }

    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("styleTreeTable", styleTreeTable);
        component.setValueExpression("styleTreePanel", styleTreePanel);
        component.setValueExpression("widthTreeColumn", widthTreeColumn);
        component.setValueExpression("widthVisibilityColumn", widthVisibilityColumn);
        component.setValueExpression("widthOpacityColumn", widthOpacityColumn);
        component.setValueExpression("widthElevationColumn", widthElevationColumn);
        component.setValueExpression("widthTimeColumn", widthTimeColumn);
        component.setValueExpression("titlePanel", titlePanel);
        component.setValueExpression("headerTreeColumn", headerTreeColumn);
        component.setValueExpression("styleOddLines", styleOddLines);
        component.setValueExpression("styleEvenLines", styleEvenLines);
        component.setValueExpression("mootools",mootools);
        component.setValueExpression("minifyJS",minifyJS);
        component.setValueExpression("elevationColumn", elevationColumn);
        component.setValueExpression("visibilityColumn", visibilityColumn);
        component.setValueExpression("opacityColumn", opacityColumn);
        component.setValueExpression("timeColumn", timeColumn);
        component.setValueExpression("layerInfo", layerInfo);
        component.setValueExpression("colorMapEditor", colorMapEditor);
    }

    public ValueExpression getStyleTreeTable() {
        return styleTreeTable;
    }

    public void setStyleTreeTable(ValueExpression styleTreeTable) {
        this.styleTreeTable = styleTreeTable;
    }

    public ValueExpression getStyleTreePanel() {
        return styleTreePanel;
    }

    public void setStyleTreePanel(ValueExpression styleTreePanel) {
        this.styleTreePanel = styleTreePanel;
    }

    public ValueExpression getWidthTreeColumn() {
        return widthTreeColumn;
    }

    public void setWidthTreeColumn(ValueExpression widthTreeColumn) {
        this.widthTreeColumn = widthTreeColumn;
    }

    public ValueExpression getWidthVisibilityColumn() {
        return widthVisibilityColumn;
    }

    public void setWidthVisibilityColumn(ValueExpression widthVisibilityColumn) {
        this.widthVisibilityColumn = widthVisibilityColumn;
    }

    public ValueExpression getWidthOpacityColumn() {
        return widthOpacityColumn;
    }

    public void setWidthOpacityColumn(ValueExpression widthOpacityColumn) {
        this.widthOpacityColumn = widthOpacityColumn;
    }

    public ValueExpression getWidthElevationColumn() {
        return widthElevationColumn;
    }

    public void setWidthElevationColumn(ValueExpression widthElevationColumn) {
        this.widthElevationColumn = widthElevationColumn;
    }

    public ValueExpression getWidthTimeColumn() {
        return widthTimeColumn;
    }

    public void setWidthTimeColumn(ValueExpression widthTimeColumn) {
        this.widthTimeColumn = widthTimeColumn;
    }

    public ValueExpression getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(ValueExpression titlePanel) {
        this.titlePanel = titlePanel;
    }

    public ValueExpression getHeaderTreeColumn() {
        return headerTreeColumn;
    }

    public void setHeaderTreeColumn(ValueExpression headerTreeColumn) {
        this.headerTreeColumn = headerTreeColumn;
    }

    public ValueExpression getStyleOddLines() {
        return styleOddLines;
    }

    public void setStyleOddLines(ValueExpression styleOddLines) {
        this.styleOddLines = styleOddLines;
    }

    public ValueExpression getStyleEvenLines() {
        return styleEvenLines;
    }

    public void setStyleEvenLines(ValueExpression styleEvenLines) {
        this.styleEvenLines = styleEvenLines;
    }

    public ValueExpression getMootools() {
        return mootools;
    }

    public void setMootools(ValueExpression mootools) {
        this.mootools = mootools;
    }

    public ValueExpression getMinifyJS() {
        return minifyJS;
    }

    public void setMinifyJS(ValueExpression minifyJS) {
        this.minifyJS = minifyJS;
    }

    public ValueExpression getElevationColumn() {
        return elevationColumn;
    }

    public void setElevationColumn(ValueExpression elevationColumn) {
        this.elevationColumn = elevationColumn;
    }

    public ValueExpression getVisibilityColumn() {
        return visibilityColumn;
    }

    public void setVisibilityColumn(ValueExpression visibilityColumn) {
        this.visibilityColumn = visibilityColumn;
    }

    public ValueExpression getOpacityColumn() {
        return opacityColumn;
    }

    public void setOpacityColumn(ValueExpression opacityColumn) {
        this.opacityColumn = opacityColumn;
    }

    public ValueExpression getTimeColumn() {
        return timeColumn;
    }

    public void setTimeColumn(ValueExpression timeColumn) {
        this.timeColumn = timeColumn;
    }

    public ValueExpression getLayerInfo() {
        return layerInfo;
    }

    public void setLayerInfo(ValueExpression layerInfo) {
        this.layerInfo = layerInfo;
    }

    public ValueExpression getColorMapEditor() {
        return colorMapEditor;
    }

    public void setColorMapEditor(ValueExpression colorMapEditor) {
        this.colorMapEditor = colorMapEditor;
    }
}
