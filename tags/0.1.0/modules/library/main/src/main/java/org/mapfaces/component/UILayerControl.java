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
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class UILayerControl extends UIWidgetBase {

    public static final String FAMILIY = "org.mapfaces.LayerControl";
    /**
     * Add tree parameters
     */
    private DefaultMutableTreeNode rootnode,  node,  child,  children;
    private DefaultTreeModel tree,  tmp;
    private String styleTreeTable;
    private String styleTreePanel;
    private String widthTreeColumn;
    private String widthVisibilityColumn;
    private String widthOpacityColumn;
    private String widthElevationColumn;
    private String widthTimeColumn;
    private String titlePanel;
    private String headerTreeColumn;
    private String styleOddLines;
    private String styleEvenLines;
    private boolean mootools = false;
    private boolean minifyJS = true;
    private boolean visibilityColumn = true;
    private boolean opacityColumn  = true;
    private boolean elevationColumn = true;
    private boolean timeColumn = true;
    private boolean layerInfo = true;
    private boolean colorMapEditor = true;   //Replace Dim_Range

    public UILayerControl() {
        super();
        if (isDebug()) {
            System.out.println("[UILayerControl] constructor----------------------");
        }
        setRendererType("org.mapfaces.renderkit.html.LayerControl"); // this component has a renderer
    }

    public DefaultTreeModel getTree() {
        return this.tree;
    }

    public void setTree(DefaultTreeModel newvalue) {
        this.tree = newvalue;
    }

    public void doAction() {
        final DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getRoot();
        root.setUserObject("node_modified");
        System.out.println("Node modfied !");
        tree.setRoot(root);
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
        final Object values[] = new Object[19];
        values[0] = super.saveState(context);
        values[1] = styleTreeTable;
        values[2] = styleTreePanel;
        values[3] = widthTreeColumn;
        values[4] = widthVisibilityColumn;
        values[5] = widthOpacityColumn;
        values[6] = widthElevationColumn;
        values[7] = widthTimeColumn;
        values[8] = titlePanel;
        values[9] = headerTreeColumn;
        values[10] = elevationColumn;
        values[11] = styleOddLines;
        values[12] = styleEvenLines;
        values[13] = visibilityColumn;
        values[14] = opacityColumn ;
        values[15] = timeColumn;
        values[16] = layerInfo;
        values[17] = colorMapEditor;
        values[18] = minifyJS;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        styleTreeTable = (String) values[1];
        styleTreePanel = (String) values[2];
        widthTreeColumn = (String) values[3];
        widthVisibilityColumn = (String) values[4];
        widthOpacityColumn = (String) values[5];
        widthElevationColumn = (String) values[6];
        widthTimeColumn = (String) values[7];
        titlePanel = (String) values[8];
        headerTreeColumn = (String) values[9];
        elevationColumn = (Boolean) values[10];
        styleOddLines = (String) values[11];
        styleEvenLines = (String) values[12];
        visibilityColumn = (Boolean) values[13];
        opacityColumn = (Boolean) values[14] ;
        timeColumn = (Boolean) values[15];
        layerInfo = (Boolean) values[16];
        colorMapEditor = (Boolean) values[17];
        minifyJS = (Boolean) values[18];
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
}
