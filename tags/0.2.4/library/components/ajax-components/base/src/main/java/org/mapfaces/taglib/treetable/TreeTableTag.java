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

package org.mapfaces.taglib.treetable;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import org.mapfaces.taglib.html.HtmlTreeTableTag;

/**
 *
 * @author Kevin Delfour
 */
public class TreeTableTag extends HtmlTreeTableTag {

    private static final String COMP_TYPE = "org.mapfaces.component.tree.TreeTable";
    private static final String RENDER_TYPE = "org.mapfaces.renderkit.tree.TreeTable";

    // ---------------------------------------------------------- Additionals fields
    // Displays attributes
    private ValueExpression collapse;
    private ValueExpression collapseDepth;
    // Styles attributes
    private ValueExpression nodeStyle;
    private ValueExpression nodeClass;
    private ValueExpression leafStyle;
    private ValueExpression leafClass;
    private ValueExpression oddLineStyle;
    private ValueExpression oddLineClass;
    private ValueExpression evenLineStyle;
    private ValueExpression evenLineClass;
    //
    private ValueExpression activateAjaxLoading;

    // ---------------------------------------------------------- Methods
    @Override
    // General Methods
    public String getRendererType() {
        return RENDER_TYPE;
    }

    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("collapse", collapse);
        component.setValueExpression("collapseDepth",collapseDepth);
        component.setValueExpression("nodeStyle",nodeStyle);
        component.setValueExpression("nodeClass",nodeClass);
        component.setValueExpression("leafStyle",leafStyle);
        component.setValueExpression("leafClass",leafClass);
        component.setValueExpression("oddLineStyle",oddLineStyle);
        component.setValueExpression("oddLineClass",oddLineClass);
        component.setValueExpression("evenLineStyle",evenLineStyle);
        component.setValueExpression("evenLineClass",evenLineClass);
        component.setValueExpression("activateAjaxLoading",activateAjaxLoading);
    }

    @Override
    public void release() {
        super.release();
        setCollapse(null);
        setCollapseDepth(null);
        setNodeStyle(null);
        setNodeClass(null);
        setLeafStyle(null);
        setLeafClass(null);
        setOddLineStyle(null);
        setOddLineClass(null);
        setEvenLineStyle(null);
        setEvenLineClass(null);
        setActivateAjaxLoading(null);
    }

    // ---------------------------------------------------------- Accessors Methods
    /**
     * @return the collapse
     */
    public ValueExpression getCollapse() {
        return collapse;
    }

    /**
     * @param collapse the collapse to set
     */
    public void setCollapse(ValueExpression collapse) {
        this.collapse = collapse;
    }

    /**
     * @return the collapseDepth
     */
    public ValueExpression getCollapseDepth() {
        return collapseDepth;
    }

    /**
     * @param collapseDepth the collapseDepth to set
     */
    public void setCollapseDepth(ValueExpression collapseDepth) {
        this.collapseDepth = collapseDepth;
    }

    /**
     * @return the nodeStyle
     */
    public ValueExpression getNodeStyle() {
        return nodeStyle;
    }

    /**
     * @param nodeStyle the nodeStyle to set
     */
    public void setNodeStyle(ValueExpression nodeStyle) {
        this.nodeStyle = nodeStyle;
    }

    /**
     * @return the nodeClass
     */
    public ValueExpression getNodeClass() {
        return nodeClass;
    }

    /**
     * @param nodeClass the nodeClass to set
     */
    public void setNodeClass(ValueExpression nodeClass) {
        this.nodeClass = nodeClass;
    }

    /**
     * @return the leafStyle
     */
    public ValueExpression getLeafStyle() {
        return leafStyle;
    }

    /**
     * @param leafStyle the leafStyle to set
     */
    public void setLeafStyle(ValueExpression leafStyle) {
        this.leafStyle = leafStyle;
    }

    /**
     * @return the leafClass
     */
    public ValueExpression getLeafClass() {
        return leafClass;
    }

    /**
     * @param leafClass the leafClass to set
     */
    public void setLeafClass(ValueExpression leafClass) {
        this.leafClass = leafClass;
    }

    /**
     * @return the oddLineStyle
     */
    public ValueExpression getOddLineStyle() {
        return oddLineStyle;
    }

    /**
     * @param oddLineStyle the oddLineStyle to set
     */
    public void setOddLineStyle(ValueExpression oddLineStyle) {
        this.oddLineStyle = oddLineStyle;
    }

    /**
     * @return the oddLineClass
     */
    public ValueExpression getOddLineClass() {
        return oddLineClass;
    }

    /**
     * @param oddLineClass the oddLineClass to set
     */
    public void setOddLineClass(ValueExpression oddLineClass) {
        this.oddLineClass = oddLineClass;
    }

    /**
     * @return the evenLineStyle
     */
    public ValueExpression getEvenLineStyle() {
        return evenLineStyle;
    }

    /**
     * @param evenLineStyle the evenLineStyle to set
     */
    public void setEvenLineStyle(ValueExpression evenLineStyle) {
        this.evenLineStyle = evenLineStyle;
    }

    /**
     * @return the evenLineClass
     */
    public ValueExpression getEvenLineClass() {
        return evenLineClass;
    }

    /**
     * @param evenLineClass the evenLineClass to set
     */
    public void setEvenLineClass(ValueExpression evenLineClass) {
        this.evenLineClass = evenLineClass;
    }

    /**
     * @return the activateAjaxLoading
     */
    public ValueExpression getActivateAjaxLoading() {
        return activateAjaxLoading;
    }

    /**
     * @param activateAjaxLoading the activateAjaxLoading to set
     */
    public void setActivateAjaxLoading(ValueExpression activateAjaxLoading) {
        this.activateAjaxLoading = activateAjaxLoading;
    }
}
