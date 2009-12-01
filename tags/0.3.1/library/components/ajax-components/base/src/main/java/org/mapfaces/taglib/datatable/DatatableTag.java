/*
 *    Mapfaces - http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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

package org.mapfaces.taglib.datatable;

import com.sun.faces.taglib.html_basic.DataTableTag;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;

/**
 * Defines the tag class of the Datatable component that can be sortable.
 * @author Kevin Delfour (IRD)
 * @since 0.3
 */
public class DatatableTag extends DataTableTag {

    private static final String COMP_TYPE = "org.mapfaces.Datatable";
    private static final String RENDER_TYPE = "org.mapfaces.renderkit.HTMLDatatable";
    /* Fields */
    // PROPERTY: overCls
    private ValueExpression overCls;

    public ValueExpression getOverCls() {
        return overCls;
    }

    public void setOverCls(ValueExpression overCls) {
        this.overCls = overCls;
    }

    // PROPERTY: sortOn
    private ValueExpression sortOn;

    public ValueExpression getSortOn() {
        return sortOn;
    }

    public void setSortOn(ValueExpression sortOn) {
        this.sortOn = sortOn;
    }

    //PROPERTY: sortBy
    private ValueExpression sortBy;

    public ValueExpression getSortBy() {
        return sortBy;
    }

    public void setSortBy(ValueExpression sortBy) {
        this.sortBy = sortBy;
    }

    //PROPERTY; scrolling
    private ValueExpression scrolling;

    public ValueExpression getScrolling() {
        return scrolling;
    }

    public void setScrolling(ValueExpression scrolling) {
        this.scrolling = scrolling;
    }

    //PROPERTY: sortable
    private ValueExpression sortable;

    public ValueExpression getSortable() {
        return sortable;
    }

    public void setSortable(ValueExpression sortable) {
        this.sortable = sortable;
    }

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

        if (!(component instanceof UIData)) {
            throw new IllegalStateException("Component " + component.toString() + " not expected type.  Expected: javax.faces.component.UIData.  Perhaps you're missing a tag?");
        }

        component.setValueExpression("overCls", getOverCls());
        component.setValueExpression("sortOn", getSortOn());
        component.setValueExpression("sortBy", getSortBy());
        component.setValueExpression("scrolling", getScrolling());
        component.setValueExpression("sortable", getSortable());
    }


    // RELEASE
    public void release() {
        super.release();

        this.overCls = null;
        this.sortOn = null;
        this.sortBy = null;
        this.scrolling = null;
        this.sortable = null;
    }

}
