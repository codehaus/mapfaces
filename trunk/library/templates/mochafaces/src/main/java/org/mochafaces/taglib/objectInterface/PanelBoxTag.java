/*
 * MDweb - Open Source tool for cataloging and locating environmental resources
 *         http://mdweb.codehaus.org
 *
 *   Copyright (c) 2007-2009, Institut de Recherche pour le Développement (IRD)
 *   Copyright (c)      2009, Geomatys
 *
 * This file is part of MDweb.
 *
 * MDweb is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation;
 *   version 3.0 of the License.
 *
 * MDweb is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *   Lesser General Public License for more details:
 *         http://www.gnu.org/licenses/lgpl-3.0.html
 *
 */
package org.mochafaces.taglib.objectInterface;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import org.mochafaces.taglib.BaseExtendELTag;

/**
 *
 * @author kevindelfour
 */
public class PanelBoxTag extends BaseExtendELTag {

    /* Fields */
    private static final String COMP_TYPE = "org.mochafaces.PanelBox";
    private static final String RENDERER_TYPE = "org.mochafaces.renderkit.HTMLPanelBox";
    private ValueExpression title;
    private ValueExpression height;
    private ValueExpression width;
    private ValueExpression collapsible;
    private ValueExpression headerStyle;
    private ValueExpression headerStyleClass;
    private ValueExpression contentStyle;
    private ValueExpression contentStyleClass;
    private ValueExpression collapseByDefault;

    /* Methods*/
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
        return RENDERER_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("title", getTitle());
        component.setValueExpression("height", getHeight());
        component.setValueExpression("width", getWidth());
        component.setValueExpression("collapsible", getCollapsible());
        component.setValueExpression("headerStyle", getHeaderStyle());
        component.setValueExpression("headerStyleClass", getHeaderStyleClass());
        component.setValueExpression("contentStyle", getContentStyle());
        component.setValueExpression("contentStyleClass", getContentStyleClass());
        component.setValueExpression("collapseByDefault", getCollapseByDefault());
    }

    /**
     * @override release in class UITreeTableELTag
     */
    @Override
    public void release() {
        super.release();
        setTitle(null);
        setHeight(null);
        setWidth(null);
        setCollapsible(null);
        setHeaderStyle(null);
        setHeaderStyleClass(null);
        setContentStyle(null);
        setContentStyleClass(null);
        setCollapseByDefault(null);
    }

    /**
     * @return the title
     */
    public ValueExpression getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(ValueExpression title) {
        this.title = title;
    }

    /**
     * @return the height
     */
    public ValueExpression getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(ValueExpression height) {
        this.height = height;
    }

    /**
     * @return the width
     */
    public ValueExpression getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(ValueExpression width) {
        this.width = width;
    }

    /**
     * @return the collapsible
     */
    public ValueExpression getCollapsible() {
        return collapsible;
    }

    /**
     * @param collapsible the collapsible to set
     */
    public void setCollapsible(ValueExpression collapsible) {
        this.collapsible = collapsible;
    }

    /**
     * @return the headerStyle
     */
    public ValueExpression getHeaderStyle() {
        return headerStyle;
    }

    /**
     * @param headerStyle the headerStyle to set
     */
    public void setHeaderStyle(ValueExpression headerStyle) {
        this.headerStyle = headerStyle;
    }

    /**
     * @return the headerStyleClass
     */
    public ValueExpression getHeaderStyleClass() {
        return headerStyleClass;
    }

    /**
     * @param headerStyleClass the headerStyleClass to set
     */
    public void setHeaderStyleClass(ValueExpression headerStyleClass) {
        this.headerStyleClass = headerStyleClass;
    }

    /**
     * @return the contentStyle
     */
    public ValueExpression getContentStyle() {
        return contentStyle;
    }

    /**
     * @param contentStyle the contentStyle to set
     */
    public void setContentStyle(ValueExpression contentStyle) {
        this.contentStyle = contentStyle;
    }

    /**
     * @return the contentStyleClass
     */
    public ValueExpression getContentStyleClass() {
        return contentStyleClass;
    }

    /**
     * @param contentStyleClass the contentStyleClass to set
     */
    public void setContentStyleClass(ValueExpression contentStyleClass) {
        this.contentStyleClass = contentStyleClass;
    }

    /**
     * @return the collapseByDefault
     */
    public ValueExpression getCollapseByDefault() {
        return collapseByDefault;
    }

    /**
     * @param collapseByDefault the collapseByDefault to set
     */
    public void setCollapseByDefault(ValueExpression collapseByDefault) {
        this.collapseByDefault = collapseByDefault;
    }
}
