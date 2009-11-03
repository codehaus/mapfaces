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

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 * This is the tag class for the component datascroller
 *
 * @author Mehdi Sidhoum (Geomatys).
 * @since 0.3
 */
public class DataScrollerTag extends UIComponentELTag {

    private static final String COMP_TYPE = "org.mapfaces.datatable.DataScroller";
    private static final String RENDER_TYPE = "org.mapfaces.renderkit.datatable.HTMLDataScroller";
    
    private ValueExpression showpages;
    private ValueExpression dataTableId;
    private ValueExpression style;
    private ValueExpression styleClass;
    private ValueExpression selectedStyleClass;
    private ValueExpression debug;

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
        return RENDER_TYPE;
    }

    
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        if (component == null) {
            return;
        }

        component.setValueExpression("showpages", showpages);
        component.setValueExpression("dataTableId", dataTableId);
        component.setValueExpression("style", style);
        component.setValueExpression("styleClass", styleClass);
        component.setValueExpression("selectedStyleClass", selectedStyleClass);
        component.setValueExpression("debug", debug);
    }

    @Override
    public void release() {
        super.release();
        showpages = null;
        dataTableId = null;
        style = null;
        styleClass = null;
        selectedStyleClass = null;
        debug = null;
    }

    /**
     * @return the showpages
     */
    public ValueExpression getShowpages() {
        return showpages;
    }

    /**
     * @param showpages the showpages to set
     */
    public void setShowpages(ValueExpression showpages) {
        this.showpages = showpages;
    }

    /**
     * @return the dataTableId
     */
    public ValueExpression getDataTableId() {
        return dataTableId;
    }

    /**
     * @param dataTableId the dataTableId to set
     */
    public void setDataTableId(ValueExpression dataTableId) {
        this.dataTableId = dataTableId;
    }

    /**
     * @return the styleClass
     */
    public ValueExpression getStyleClass() {
        return styleClass;
    }

    /**
     * @param styleClass the styleClass to set
     */
    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @return the selectedStyleClass
     */
    public ValueExpression getSelectedStyleClass() {
        return selectedStyleClass;
    }

    /**
     * @param selectedStyleClass the selectedStyleClass to set
     */
    public void setSelectedStyleClass(ValueExpression selectedStyleClass) {
        this.selectedStyleClass = selectedStyleClass;
    }

    /**
     * @return the style
     */
    public ValueExpression getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(ValueExpression style) {
        this.style = style;
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
}
