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

package org.mapfaces.models;

/**
 *
 * @author Olivier Terral.
 */
public interface Dimension {

    String getDefault();

    String getName();

    String getUnitSymbol();

    String getUnits();

    String getUserValue();

    String getValue();

    boolean isCurrent();

    boolean isMultipleValues();

    boolean isNearestValues();

    void setCurrent(boolean current);

    void setDefault(String Default);

    void setMultipleValues(boolean multipleValues);

    void setName(String name);

    void setNearestValues(boolean nearestValues);

    void setUnitSymbol(String unitSymbol);

    void setUnits(String units);

    void setUserValue(String userValue);

    void setValue(String value);

}
