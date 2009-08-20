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
package org.mapfaces.component.treelayout;

import org.mapfaces.component.abstractTree.UIColumnBase;

/**
 * @author Kevin Delfour (Geomatys)
 */
public class UISelectOneMenuColumn extends UIColumnBase{

    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLSelectOneMenuColumn";
    private static final String FAMILY = "org.mapfaces.treelayout.Column";

    private String title = null;
    private String itemsLabels = "300*150/300*300/600*300";
    private String itemsValues = "300,150/300,300/600,300";
    private String separator="/";

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
     * {@inheritDoc }
     */
    @Override
    public String getHeaderTitle() {
        return title;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setHeaderTitle(final String title) {
        this.title = title;
    }

    public String getItemsLabels() {
        return itemsLabels;
    }

    public void setItemsLabels(final String itemsLabels) {
        this.itemsLabels = itemsLabels;
    }

    public String getItemsValues() {
        return itemsValues;
    }

    public void setItemsValues(final String itemsValues) {
        this.itemsValues = itemsValues;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(final String separator) {
        this.separator = separator;
    }
}
