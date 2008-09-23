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
package org.mapfaces.taglib.treelayout;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author kdelfour
 */
public class TreePanelTag extends UIComponentELTag {

    private ValueExpression border = null;
    private ValueExpression check = null;
    private ValueExpression collapsible = null;
    private ValueExpression enableDragDrop = null;
    private ValueExpression frame = null;
    private ValueExpression header = null;
    private ValueExpression height = null;
    private ValueExpression rowId = null;
    private ValueExpression showLines = null;
    private ValueExpression showRoot = null;
    private ValueExpression title = null;
    private ValueExpression debug = null;
    private final String TREEPANEL_COMP_TYPE = "org.mapfaces.treelayout.treetable.TreePanel";
    private final String TREEPANEL_RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.treetable.HTMLTreePanel";

    @Override
    public String getComponentType() {
        return TREEPANEL_COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return TREEPANEL_RENDERER_TYPE;
    }

    public ValueExpression getBorder() {
        return border;
    }

    public void setBorder(ValueExpression border) {
        this.border = border;
    }

    public ValueExpression getCheck() {
        return check;
    }

    public void setCheck(ValueExpression checked) {
        this.check = checked;
    }

    public ValueExpression getCollapsible() {
        return collapsible;
    }

    public void setCollapsible(ValueExpression collapsible) {
        this.collapsible = collapsible;
    }

    public ValueExpression getFrame() {
        return frame;
    }

    public void setFrame(ValueExpression frame) {
        this.frame = frame;
    }

    public ValueExpression getHeader() {
        return header;
    }

    public void setHeader(ValueExpression header) {
        this.header = header;
    }

    public ValueExpression getHeight() {
        return height;
    }

    public void setHeight(ValueExpression height) {
        this.height = height;
    }

    public ValueExpression getRowId() {
        return rowId;
    }

    public void setRowId(ValueExpression rowId) {
        this.rowId = rowId;
    }

    public ValueExpression getShowLines() {
        return showLines;
    }

    public void setShowLines(ValueExpression showLines) {
        this.showLines = showLines;
    }

    public ValueExpression getTitle() {
        return title;
    }

    public void setTitle(ValueExpression title) {
        if (title != null) {
            this.title = title;
        }
    }

    /**
     * @return the debug
     */
    public ValueExpression getDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(ValueExpression debug) {
        this.debug = debug;
    }

    public ValueExpression getShowRoot() {
        return showRoot;
    }

    public void setShowRoot(ValueExpression showRoot) {
        this.showRoot = showRoot;
    }

    public ValueExpression getEnableDragDrop() {
        return enableDragDrop;
    }

    public void setEnableDragDrop(ValueExpression enableDragDrop) {
        this.enableDragDrop = enableDragDrop;
    }

    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("border", border);
        component.setValueExpression("check", check);
        component.setValueExpression("collapsible", collapsible);
        component.setValueExpression("frame", frame);
        component.setValueExpression("header", header);
        component.setValueExpression("height", height);
        component.setValueExpression("rowId", rowId);
        component.setValueExpression("showLines", showLines);
        component.setValueExpression("title", title);
        component.setValueExpression("debug", getDebug());
        component.setValueExpression("showRoot", showRoot);
        component.setValueExpression("enableDragDrop", enableDragDrop);
    }

    @Override
    public void release() {
        super.release();
        setBorder(null);
        setCheck(null);
        setCollapsible(null);
        setFrame(null);
        setHeader(null);
        setHeight(null);
        setRowId(null);
        setShowLines(null);
        setTitle(null);
        setDebug(null);
        setShowRoot(null);
        setEnableDragDrop(null);
    }
}
