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
package org.mapfaces.component.datatable;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

/**
 * <p>This Datatabe component using script for MooTools provides the
 * functionality for display a datatable with scroller and sortable function.</p>
 * <p>
 * List of attributes :
 * <ul>
 * <i>Attributes added :</i>
 * <li><b>overCls</b> - the name of the class added when the mouse is over the tr.</li>
 * <li><b>sortable</b> - Define if the tabl is sortable or not.</li>
 * <li><b>sortOn</b> - the number of the column to initially sort on (zero based).</li>
 * <li><b>sortBy</b> - the direction of the sort (ASC / DESC) (default: ASC).</li>
 * <li><b>scrolling</b> - provide a scrolling capability out of the box
 * </ul>
 * @author Kevin Delfour (IRD)
 */
public class UIDatatable extends HtmlDataTable {

    private static final String FAMILY = "org.mapfaces.Datatable";
    /* Fields */
    private String overCls;
    private int sortOn = 0;
    private String sortBy = "ASC";
    private boolean scrolling = false;
    private boolean sortable = false;

    /* Accessors */
    /**
     * @return the overCls
     */
    public String getOverCls() {
        return overCls;
    }

    /**
     * @param overCls the overCls to set
     */
    public void setOverCls(String overCls) {
        this.overCls = overCls;
    }

    /**
     * @return the sortOn
     */
    public int getSortOn() {
        return sortOn;
    }

    /**
     * @param sortOn the sortOn to set
     */
    public void setSortOn(int sortOn) {
        this.sortOn = sortOn;
    }

    /**
     * @return the sortBy
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * @param sortBy the sortBy to set
     */
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * @return the scrolling
     */
    public boolean isScrolling() {
        return scrolling;
    }

    /**
     * @param scrolling the scrolling to set
     */
    public void setScrolling(boolean scrolling) {
        this.scrolling = scrolling;
    }

    /**
     * @return the sortable
     */
    public boolean isSortable() {
        return sortable;
    }

    /**
     * @param sortable the sortable to set
     */
    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    /* Methods */
    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[6];
        values[0] = super.saveState(context);
        values[1] = getOverCls();
        values[2] = getSortOn();
        values[3] = getSortBy();
        values[4] = isScrolling();
        values[5] = isSortable();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setOverCls((String) values[1]);
        setSortOn((Integer) values[2]);
        setSortBy((String) values[3]);
        setScrolling((Boolean) values[4]);
        setSortable((Boolean)values[5]);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }
}
