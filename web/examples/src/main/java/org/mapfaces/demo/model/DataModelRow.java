/*
 *    Mapfaces -
 *    http://www.mapfaces.org
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

package org.mapfaces.demo.model;

import java.io.Serializable;

/**
 * this is a model for datatable rows
 * @author Mehdi Sidhoum (Geomatys)
 * @since 0.3
 */
public class DataModelRow implements Serializable {

    private double doubleValue;
    private String value;

    /**
     * Creates a new instance of DataModelRow object
     * @param doubleValue
     * @param val
     */
    public DataModelRow(double doubleValue, String val) {
        this.doubleValue = doubleValue;
        this.value = val;
    }

    /**
     * @return the doubleValue
     */
    public double getDoubleValue() {
        return doubleValue;
    }

    /**
     * @param doubleValue the doubleValue to set
     */
    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }




}