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
     * 
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
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getRoot();
        root.setUserObject("node_modified");
        System.out.println("Node modfied !");
        tree.setRoot(root);
    }

    @Override
    public String getFamily() {
        return FAMILIY;
    }

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[19];
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

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
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

    public void setStyleTreeTable(String styleTreeTable) {
        this.styleTreeTable = styleTreeTable;
    }

    public String getStyleTreePanel() {
        return styleTreePanel;
    }

    public void setStyleTreePanel(String styleTreePanel) {
        this.styleTreePanel = styleTreePanel;
    }

    public String getWidthTreeColumn() {
        return widthTreeColumn;
    }

    public void setWidthTreeColumn(String widthTreeColumn) {
        this.widthTreeColumn = widthTreeColumn;
    }

    public String getWidthVisibilityColumn() {
        return widthVisibilityColumn;
    }

    public void setWidthVisibilityColumn(String widthVisibilityColumn) {
        this.widthVisibilityColumn = widthVisibilityColumn;
    }

    public String getWidthOpacityColumn() {
        return widthOpacityColumn;
    }

    public void setWidthOpacityColumn(String widthOpacityColumn) {
        this.widthOpacityColumn = widthOpacityColumn;
    }

    public String getWidthElevationColumn() {
        return widthElevationColumn;
    }

    public void setWidthElevationColumn(String widthElevationColumn) {
        this.widthElevationColumn = widthElevationColumn;
    }

    public String getWidthTimeColumn() {
        return widthTimeColumn;
    }

    public void setWidthTimeColumn(String widthTimeColumn) {
        this.widthTimeColumn = widthTimeColumn;
    }

    public String getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(String titlePanel) {
        this.titlePanel = titlePanel;
    }

    public String getHeaderTreeColumn() {
        return headerTreeColumn;
    }

    public void setHeaderTreeColumn(String headerTreeColumn) {
        this.headerTreeColumn = headerTreeColumn;
    }

    public boolean isElevationColumn() {
        return elevationColumn;
    }

    public void setElevationColumn(boolean hideElevationColumn) {
        this.elevationColumn = hideElevationColumn;
    }

    public String getStyleOddLines() {
        return styleOddLines;
    }

    public void setStyleOddLines(String styleOddLines) {
        this.styleOddLines = styleOddLines;
    }

    public String getStyleEvenLines() {
        return styleEvenLines;
    }

    public void setStyleEvenLines(String styleEvenLines) {
        this.styleEvenLines = styleEvenLines;
    }

    public boolean isMootools() {
        return mootools;
    }

    public void setMootools(boolean mootools) {
        this.mootools = mootools;
    }

    public boolean isMinifyJS() {
        return minifyJS;
    }

    public void setMinifyJS(boolean minifyJS) {
        this.minifyJS = minifyJS;
    }

    public boolean isVisibilityColumn() {
        return visibilityColumn;
    }

    public void setVisibilityColumn(boolean visibilityColumn) {
        this.visibilityColumn = visibilityColumn;
    }

    public boolean isOpacityColumn() {
        return opacityColumn;
    }

    public void setOpacityColumn(boolean opacityColumn) {
        this.opacityColumn = opacityColumn;
    }

    public boolean isTimeColumn() {
        return timeColumn;
    }

    public void setTimeColumn(boolean timeColumn) {
        this.timeColumn = timeColumn;
    }

    public boolean isLayerInfo() {
        return layerInfo;
    }

    public void setLayerInfo(boolean layerInfo) {
        this.layerInfo = layerInfo;
    }

    public boolean isColorMapEditor() {
        return colorMapEditor;
    }

    public void setColorMapEditor(boolean colorMapEditor) {
        this.colorMapEditor = colorMapEditor;
    }
}
