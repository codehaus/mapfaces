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

package org.mapfaces.component.datatable;

import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;

/**
 * The datascroller (or pager) is a companion to a datatable,
 * you specify the ID of the datatable and it renders all links to display the pages.
 * 
 * @author Mehdi Sidhoum (Geomatys).
 * @since 0.3
 */
public class UIDataScroller extends UICommand {

    private static final String FAMILY = "org.mapfaces.datatable.DataScroller";
    /**
     * The target datatable ID to display the pager
     */
    private String dataTableId;
    /**
     * Attribute style of the wrapper div
     */
    private String style;
    /**
     * Attribute class of the wrapper div
     */
    private String styleClass;
    /**
     * The style class that will be applyed on the selected link
     */
    private String selectedStyleClass;
    /**
     * The number of pages that will be displayed in one batch. Default is first 20 pages.
     */
    private int showpages = 20;
    /**
     * Flag specified by the user to display logs in the renderer
     */
    private boolean debug;

    /**
     * Creates a new instance of component UIDataScroller
     */
    public UIDataScroller() {
        super();
        setRendererType("org.mapfaces.renderkit.datatable.HTMLDataScroller"); // this component has a renderer
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
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[7];
        values[0] = super.saveState(context);
        values[1] = dataTableId;
        values[2] = style;
        values[3] = styleClass;
        values[4] = selectedStyleClass;
        values[5] = showpages;
        values[6] = debug;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        dataTableId = (String) values[1];
        style = (String) values[2];
        styleClass = (String) values[3];
        selectedStyleClass = (String) values[4];
        showpages = (Integer) values[5];
        debug = (Boolean) values[6];

    }

    /**
     * @return the datatableId
     */
    public String getDataTableId() {
        return dataTableId;
    }

    /**
     * @param datatableId the datatableId to set
     */
    public void setDataTableId(String datatableId) {
        this.dataTableId = datatableId;
    }

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * @return the styleClass
     */
    public String getStyleClass() {
        return styleClass;
    }

    /**
     * @param styleClass the styleClass to set
     */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @return the selectedStyleClass
     */
    public String getSelectedStyleClass() {
        return selectedStyleClass;
    }

    /**
     * @param selectedStyleClass the selectedStyleClass to set
     */
    public void setSelectedStyleClass(String selectedStyleClass) {
        this.selectedStyleClass = selectedStyleClass;
    }

    /**
     * @return the showpages
     */
    public int getShowpages() {
        return showpages;
    }

    /**
     * @param showpages the showpages to set
     */
    public void setShowpages(int showpages) {
        this.showpages = showpages;
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}

