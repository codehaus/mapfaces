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
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 * @author Kevin Delfour.
 */
public class UILayerControl extends UIWidgetBase {

    /* Fields */
    private DefaultTreeModel tree;
    /* TreeTable style */
    private String styleTreeTable = "width: 100%; height: 100%;";
    private String styleClassTreeTable;
    /* TreePanel attributes */
    private String titlePanel;
    private String styleTreePanel;
    private String styleClassTreePanel;
    /* Column attributes */
    private String headerTreeColumn;
    private String widthTreeColumn;
    private String widthVisibilityColumn;
    private String widthOpacityColumn;
    private String widthElevationColumn;
    private String widthTimeColumn;
    /* TreePanel style lines attributes*/
    private String styleOddLines;
    private String styleEvenLines;
    private String styleLeafLines;
    private String styleNodeLines;
    /* Laoding js script */
    private boolean mootools = false;
    private boolean minifyJS = true;
    /* TreePanel attributes */
    private boolean displayAllLayers = true;
    private boolean displayHeader = true;
    /* Others */
    private boolean visibilityColumn = true;
    private boolean opacityColumn = true;
    private boolean elevationColumn = false;
    private boolean timeColumn = false;
    private boolean layerInfo = false;
    private boolean colorMapEditor = false;   //Replace Dim_Range
    /* Functionalities */
    private boolean activateDnd = false;
    public static final String FAMILIY = "org.mapfaces.LayerControl";

    /* Constructor */
    public UILayerControl() {
        super();
        if (isDebug()) {
            System.out.println("[DEBUG] UILayerControl constructor");
        }
        setRendererType("org.mapfaces.renderkit.html.LayerControl"); // this component has a renderer
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[28];
        values[0] = super.saveState(context);
        values[1] = tree;
        values[2] = styleTreeTable;
        values[3] = styleClassTreeTable;
        values[4] = titlePanel;
        values[5] = styleTreePanel;
        values[6] = styleClassTreePanel;
        values[7] = headerTreeColumn;
        values[8] = widthTreeColumn;
        values[9] = widthVisibilityColumn;
        values[10] = widthOpacityColumn;
        values[11] = widthElevationColumn;
        values[12] = widthTimeColumn;
        values[13] = styleOddLines;
        values[14] = styleEvenLines;
        values[15] = styleLeafLines;
        values[16] = styleNodeLines;
        values[17] = mootools;
        values[18] = minifyJS;
        values[19] = displayAllLayers;
        values[20] = displayHeader;
        values[21] = visibilityColumn;
        values[22] = opacityColumn;
        values[23] = elevationColumn;
        values[24] = timeColumn;
        values[25] = layerInfo;
        values[26] = colorMapEditor;
        values[27] = activateDnd;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        tree = (DefaultTreeModel) values[1];
        styleTreeTable = (String) values[2];
        styleClassTreeTable = (String) values[3];
        titlePanel = (String) values[4];
        styleTreePanel = (String) values[5];
        styleClassTreePanel = (String) values[6];
        headerTreeColumn = (String) values[7];
        widthTreeColumn = (String) values[8];
        widthVisibilityColumn = (String) values[9];
        widthOpacityColumn = (String) values[10];
        widthElevationColumn = (String) values[11];
        widthTimeColumn = (String) values[12];
        styleOddLines = (String) values[13];
        styleEvenLines = (String) values[14];
        styleLeafLines = (String) values[15];
        styleNodeLines = (String) values[16];
        mootools = (Boolean) values[17];
        minifyJS = (Boolean) values[18];
        displayAllLayers = (Boolean) values[19];
        displayHeader = (Boolean) values[20];
        visibilityColumn = (Boolean) values[21];
        opacityColumn = (Boolean) values[22];
        elevationColumn = (Boolean) values[23];
        timeColumn = (Boolean) values[24];
        layerInfo = (Boolean) values[25];
        colorMapEditor = (Boolean) values[26];
        activateDnd = (Boolean) values[27];
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILIY;
    }

    /* Accessors */
    public DefaultTreeModel getTree() {
        return this.tree;
    }

    public void setTree(DefaultTreeModel newvalue) {
        this.tree = newvalue;
    }

    public String getStyleTreeTable() {
        return styleTreeTable;
    }

    public void setStyleTreeTable(final String styleTreeTable) {
        this.styleTreeTable = styleTreeTable;
    }

    public String getStyleTreePanel() {
        return styleTreePanel;
    }

    public void setStyleTreePanel(final String styleTreePanel) {
        this.styleTreePanel = styleTreePanel;
    }

    public String getWidthTreeColumn() {
        return widthTreeColumn;
    }

    public void setWidthTreeColumn(final String widthTreeColumn) {
        this.widthTreeColumn = widthTreeColumn;
    }

    public String getWidthVisibilityColumn() {
        return widthVisibilityColumn;
    }

    public void setWidthVisibilityColumn(final String widthVisibilityColumn) {
        this.widthVisibilityColumn = widthVisibilityColumn;
    }

    public String getWidthOpacityColumn() {
        return widthOpacityColumn;
    }

    public void setWidthOpacityColumn(final String widthOpacityColumn) {
        this.widthOpacityColumn = widthOpacityColumn;
    }

    public String getWidthElevationColumn() {
        return widthElevationColumn;
    }

    public void setWidthElevationColumn(final String widthElevationColumn) {
        this.widthElevationColumn = widthElevationColumn;
    }

    public String getWidthTimeColumn() {
        return widthTimeColumn;
    }

    public void setWidthTimeColumn(final String widthTimeColumn) {
        this.widthTimeColumn = widthTimeColumn;
    }

    public String getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(final String titlePanel) {
        this.titlePanel = titlePanel;
    }

    public String getHeaderTreeColumn() {
        return headerTreeColumn;
    }

    public void setHeaderTreeColumn(final String headerTreeColumn) {
        this.headerTreeColumn = headerTreeColumn;
    }

    public boolean isElevationColumn() {
        return elevationColumn;
    }

    public void setElevationColumn(final boolean hideElevationColumn) {
        this.elevationColumn = hideElevationColumn;
    }

    public String getStyleOddLines() {
        return styleOddLines;
    }

    public void setStyleOddLines(final String styleOddLines) {
        this.styleOddLines = styleOddLines;
    }

    public String getStyleEvenLines() {
        return styleEvenLines;
    }

    public void setStyleEvenLines(final String styleEvenLines) {
        this.styleEvenLines = styleEvenLines;
    }

    public boolean isMootools() {
        return mootools;
    }

    public void setMootools(final boolean mootools) {
        this.mootools = mootools;
    }

    public boolean isMinifyJS() {
        return minifyJS;
    }

    public void setMinifyJS(final boolean minifyJS) {
        this.minifyJS = minifyJS;
    }

    public boolean isVisibilityColumn() {
        return visibilityColumn;
    }

    public void setVisibilityColumn(final boolean visibilityColumn) {
        this.visibilityColumn = visibilityColumn;
    }

    public boolean isOpacityColumn() {
        return opacityColumn;
    }

    public void setOpacityColumn(final boolean opacityColumn) {
        this.opacityColumn = opacityColumn;
    }

    public boolean isTimeColumn() {
        return timeColumn;
    }

    public void setTimeColumn(final boolean timeColumn) {
        this.timeColumn = timeColumn;
    }

    public boolean isLayerInfo() {
        return layerInfo;
    }

    public void setLayerInfo(final boolean layerInfo) {
        this.layerInfo = layerInfo;
    }

    public boolean isColorMapEditor() {
        return colorMapEditor;
    }

    public void setColorMapEditor(final boolean colorMapEditor) {
        this.colorMapEditor = colorMapEditor;
    }

    public String getStyleClassTreeTable() {
        return styleClassTreeTable;
    }

    public void setStyleClassTreeTable(String styleClassTreeTable) {
        this.styleClassTreeTable = styleClassTreeTable;
    }

    public String getStyleClassTreePanel() {
        return styleClassTreePanel;
    }

    public void setStyleClassTreePanel(String styleClassTreePanel) {
        this.styleClassTreePanel = styleClassTreePanel;
    }

    public String getStyleLeafLines() {
        return styleLeafLines;
    }

    public void setStyleLeafLines(String styleLeafLines) {
        this.styleLeafLines = styleLeafLines;
    }

    public String getStyleNodeLines() {
        return styleNodeLines;
    }

    public void setStyleNodeLines(String styleNodeLines) {
        this.styleNodeLines = styleNodeLines;
    }

    public boolean isDisplayAllLayers() {
        return displayAllLayers;
    }

    public void setDisplayAllLayers(boolean displayAllLayers) {
        this.displayAllLayers = displayAllLayers;
    }

    public boolean isDisplayHeader() {
        return displayHeader;
    }

    public void setDisplayHeader(boolean displayHeader) {
        this.displayHeader = displayHeader;
    }

    public boolean isActivateDnd() {
        return activateDnd;
    }

    public void setActivateDnd(boolean activateDnd) {
        this.activateDnd = activateDnd;
    }
}
