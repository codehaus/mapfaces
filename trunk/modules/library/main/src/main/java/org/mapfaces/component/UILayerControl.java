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
    private boolean hideElevationColumn;

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
        Object values[] = new Object[12];
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
        values[10] = hideElevationColumn;
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
        hideElevationColumn = (Boolean) values[10];
        
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

    public boolean isHideElevationColumn() {
        return hideElevationColumn;
    }

    public void setHideElevationColumn(boolean hideElevationColumn) {
        this.hideElevationColumn = hideElevationColumn;
    }
}
