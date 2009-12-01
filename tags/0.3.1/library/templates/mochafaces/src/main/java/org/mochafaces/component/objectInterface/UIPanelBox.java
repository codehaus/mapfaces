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
package org.mochafaces.component.objectInterface;

import javax.faces.context.FacesContext;
import org.mochafaces.component.UIBaseExtend;

/**
 *
 * @author kevindelfour
 */
public class UIPanelBox extends UIBaseExtend {

    private static final String FAMILY = "org.mochafaces.PanelBox";
    private static final String RENDERER_TYPE = "org.mochafaces.renderkit.HTMLPanelBox";
    private String title;
    private int height;
    private int width;
    private boolean collapsible;
    private String headerStyle;
    private String headerStyleClass;
    private String contentStyle;
    private String contentStyleClass;
    private boolean collapseByDefault;

    /* Methods */
    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[10];
        values[0] = super.saveState(context);
        values[1] = getTitle();
        values[2] = getHeight();
        values[3] = getWidth();
        values[4] = isCollapsible();
        values[5] = getHeaderStyle();
        values[6] = getHeaderStyleClass();
        values[7] = getContentStyle();
        values[8] = getContentStyleClass();
        values[9] = isCollapseByDefault();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setTitle((String) values[1]);
        setHeight((Integer) values[2]);
        setWidth((Integer) values[3]);
        setCollapsible((Boolean) values[4]);
        setHeaderStyle((String) values[5]);
        setHeaderStyleClass((String) values[6]);
        setContentStyle((String) values[7]);
        setContentStyleClass((String) values[8]);
        setCollapseByDefault((Boolean)values[9]);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the collapsible
     */
    public boolean isCollapsible() {
        return collapsible;
    }

    /**
     * @param collapsible the collapsible to set
     */
    public void setCollapsible(boolean collapsible) {
        this.collapsible = collapsible;
    }

    /**
     * @return the headerStyle
     */
    public String getHeaderStyle() {
        return headerStyle;
    }

    /**
     * @param headerStyle the headerStyle to set
     */
    public void setHeaderStyle(String headerStyle) {
        this.headerStyle = headerStyle;
    }

    /**
     * @return the headerStyleClass
     */
    public String getHeaderStyleClass() {
        return headerStyleClass;
    }

    /**
     * @param headerStyleClass the headerStyleClass to set
     */
    public void setHeaderStyleClass(String headerStyleClass) {
        this.headerStyleClass = headerStyleClass;
    }

    /**
     * @return the contentStyle
     */
    public String getContentStyle() {
        return contentStyle;
    }

    /**
     * @param contentStyle the contentStyle to set
     */
    public void setContentStyle(String contentStyle) {
        this.contentStyle = contentStyle;
    }

    /**
     * @return the contentStyleClass
     */
    public String getContentStyleClass() {
        return contentStyleClass;
    }

    /**
     * @param contentStyleClass the contentStyleClass to set
     */
    public void setContentStyleClass(String contentStyleClass) {
        this.contentStyleClass = contentStyleClass;
    }

    /**
     * @return the collapseByDefault
     */
    public boolean isCollapseByDefault() {
        return collapseByDefault;
    }

    /**
     * @param collapseByDefault the collapseByDefault to set
     */
    public void setCollapseByDefault(boolean collapseByDefault) {
        this.collapseByDefault = collapseByDefault;
    }
}
