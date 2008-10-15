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
package org.mapfaces.taglib.abstractTree;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;

/**
 * <p>UITreeColumnELTag is the base class for all JSP tags that correspond to a TreeColumn Component instance in the view.</p>
 * <p> Attributes are :<ul>
 * <li>border</li>
 * <li>check</li>
 * <li>collapsible</li>
 * <li>enableDragDrop</li>
 * <li>frame</li>
 * <li>header</li>
 * <li>height</li>
 * <li>width</li>
 * <li>rowId</li>
 * <li>showLines</li>
 * <li>showRoot</li>
 * <li>title</li>
 * <li>styleLeaf</li>
 * <li>styleNode</li>
 * </ul>
 * @author kdelfour
 */
public abstract class UITreePanelELTag extends UITreeComponentELTag {

    /* Fields */
    private ValueExpression border = null;
    private ValueExpression check = null;
    private ValueExpression collapsible = null;
    private ValueExpression enableDragDrop = null;
    private ValueExpression frame = null;
    private ValueExpression header = null;
    private ValueExpression height = null;
    private ValueExpression width = null;
    private ValueExpression rowId = null;
    private ValueExpression showLines = null;
    private ValueExpression showRoot = null;
    private ValueExpression title = null;
    private ValueExpression styleLeaf = null;
    private ValueExpression styleNode = null;
    private ValueExpression loadAll = null;

    /* Abstracts methods*/
    /**
     * <p>Subclasses must override this method to return the appropriate value.</p>
     * @return the component type for the component that is or will be bound to this tag.
     */
    @Override
    public abstract String getComponentType();

    /**
     * <p>Subclasses must override this method to return the appropriate value.</p>
     * @return the rendererType property that selects the Renderer to be used for encoding this component, 
     * or null to ask the component to render itself directly
     */
    @Override
    public abstract String getRendererType();


    /* Accessors */
    /**
     * Accessor for border
     * @return border size
     */
    public ValueExpression getBorder() {
        return border;
    }

    /**
     * Mutator for border
     * @param header New value for border
     */
    public void setBorder(ValueExpression border) {
        this.border = border;
    }

    /**
     * Accessor for check
     * @return if default checkColumn must be integrated
     */
    public ValueExpression getCheck() {
        return check;
    }

    /**
     * Mutator for check
     * @param header New value for check
     */
    public void setCheck(ValueExpression checked) {
        this.check = checked;
    }

    /**
     * Accessor for collapsible
     * @return collapsible value
     */
    public ValueExpression getCollapsible() {
        return collapsible;
    }

    /**
     * Mutator for collapsible
     * @param header New value for collapsible
     */
    public void setCollapsible(ValueExpression collapsible) {
        this.collapsible = collapsible;
    }

    /**
     * Accessor for frame
     * @return frame value
     */
    public ValueExpression getFrame() {
        return frame;
    }

    /**
     * Mutator for frame
     * @param header New value for frame
     */
    public void setFrame(ValueExpression frame) {
        this.frame = frame;
    }

    /**
     * Accessor for header
     * @return header title value
     */
    public ValueExpression getHeader() {
        return header;
    }

    /**
     * Mutator for header
     * @param header New value for header
     */
    public void setHeader(ValueExpression header) {
        this.header = header;
    }

    /**
     * Accessor for height
     * @return height size
     */
    public ValueExpression getHeight() {
        return height;
    }

    /**
     * Mutator for height
     * @param header New value for height
     */
    public void setHeight(ValueExpression height) {
        this.height = height;
    }

    /**
     * Accessor for rowId
     * @return if RowId must be integrated
     */
    public ValueExpression getRowId() {
        return rowId;
    }

    /**
     * Mutator for rowId
     * @param header New value for rowId
     */
    public void setRowId(ValueExpression rowId) {
        this.rowId = rowId;
    }

    /**
     * Accessor for showLines
     * @return showLines value
     */
    public ValueExpression getShowLines() {
        return showLines;
    }

    /**
     * Mutator for showLines
     * @param header New value for showLines
     */
    public void setShowLines(ValueExpression showLines) {
        this.showLines = showLines;
    }

    /**
     * Accessor for title
     * @return title value
     */
    public ValueExpression getTitle() {
        return title;
    }

    /**
     * Mutator for title
     * @param header New value for title
     */
    public void setTitle(ValueExpression title) {
        if (title != null) {
            this.title = title;
        }
    }

    /**
     * Accessor for width
     * @return width size value
     */
    public ValueExpression getWidth() {
        return width;
    }

    /**
     * Mutator for width
     * @param header New value for width
     */
    public void setWidth(ValueExpression width) {
        this.width = width;
    }

    /**
     * Accessor for showRoot
     * @return if showRoot must be visible or not
     */
    public ValueExpression getShowRoot() {
        return showRoot;
    }

    /**
     * Mutator for showRoot
     * @param header New value for showRoot
     */
    public void setShowRoot(ValueExpression showRoot) {
        this.showRoot = showRoot;
    }

    /**
     * Accessor for enableDragDrop
     * @return enableDragDrop value
     */
    public ValueExpression getEnableDragDrop() {
        return enableDragDrop;
    }

    /**
     * Mutator for enableDragDrop
     * @param header New value for enableDragDrop
     */
    public void setEnableDragDrop(ValueExpression enableDragDrop) {
        this.enableDragDrop = enableDragDrop;
    }

    /**
     * Accessor for styleLeaf
     * @return styleLeaf value
     */
    public ValueExpression getStyleLeaf() {
        return styleLeaf;
    }

    /**
     * Mutator for styleLeaf
     * @param header New value for styleLeaf
     */
    public void setStyleLeaf(ValueExpression styleLeaf) {
        this.styleLeaf = styleLeaf;
    }

    /**
     * Accessor for styleNode
     * @return styleNode value
     */
    public ValueExpression getStyleNode() {
        return styleNode;
    }

    /**
     * Mutator for styleNode
     * @param styleNode New value for styleNode
     */
    public void setStyleNode(ValueExpression styleNode) {
        this.styleNode = styleNode;
    }

      /**
     * Accessor for loadAll
     * @return loadAll value
     */
    public ValueExpression getLoadAll() {
        return loadAll;
    }

    /**
     * Mutator for loadAll
     * @param loadAll New value for loadAll
     */
    public void setLoadAll(ValueExpression loadAll) {
        this.loadAll = loadAll;
    }

    /* Methods*/
    /**
     * <p>Override properties and attributes of the specified component, 
     * if the corresponding properties of this tag handler instance were explicitly set.</p>
     * <p>This method must be called ONLY  if the specified UIComponent was in fact created during 
     * the execution of this tag handler instance, and this call will occur BEFORE the 
     * UIComponent is added to the view.</p>
     * @param component UIComponent whose properties are to be overridden
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("title", title);
        component.setValueExpression("border", border);
        component.setValueExpression("check", check);
        component.setValueExpression("collapsible", collapsible);
        component.setValueExpression("frame", frame);
        component.setValueExpression("header", header);
        component.setValueExpression("height", height);
        component.setValueExpression("rowId", rowId);
        component.setValueExpression("showLines", showLines);
        component.setValueExpression("width", width);
        component.setValueExpression("showRoot", showRoot);
        component.setValueExpression("enableDragDrop", enableDragDrop);
        component.setValueExpression("styleLeaf", styleLeaf);
        component.setValueExpression("styleNode", styleNode);
        component.setValueExpression("loadAll", loadAll);
    }

    /**
     * Release any resources allocated during the execution of this tag handler.
     */
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
        setWidth(null);
        setShowRoot(null);
        setEnableDragDrop(null);
        setStyleLeaf(null);
        setStyleNode(null);
        setLoadAll(null);

    }
}
