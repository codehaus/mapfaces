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
 * @author Olivier Terral.
 */
public class DefaultDimension implements Dimension {

    private String name;
    private String units;
    private String unitSymbol;
    private String userValue;
    private String Default;
    private String value;
    private boolean multipleValues;
    private boolean nearestValues;
    private boolean current;

    /**
     * {@inheritDoc }
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setUnits(final String units) {
        this.units = units;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setUnitSymbol(final String unitSymbol) {
        this.unitSymbol = unitSymbol;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setUserValue(final String userValue) {
        this.userValue = userValue;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isMultipleValues() {
        return multipleValues;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setMultipleValues(final boolean multipleValues) {
        this.multipleValues = multipleValues;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isNearestValues() {
        return nearestValues;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setNearestValues(final boolean nearestValues) {
        this.nearestValues = nearestValues;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isCurrent() {
        return current;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setCurrent(final boolean current) {
        this.current = current;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getUnits() {
        return units;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getUnitSymbol() {
        return unitSymbol;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getUserValue() {
        return userValue;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getDefault() {
        return Default;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setDefault(final String Default) {
        this.Default = Default;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setAttribute(final String attrName, final String value) {
        if(attrName.equalsIgnoreCase("userValue")){
            setUserValue(value);
        }else if(attrName.equalsIgnoreCase("default")){
            setDefault(value);
        }else if(attrName.equalsIgnoreCase("units")){
            setUnits(value);
        }else if(attrName.equalsIgnoreCase("unitSymbol")){
            setUnitSymbol(value);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setAttribute(final String attrName, final boolean bool) {
        if(attrName.equalsIgnoreCase("multipleValues")){
            setMultipleValues(Boolean.valueOf(bool));
        }else if(attrName.equalsIgnoreCase("nearestValues")){
            setNearestValues(Boolean.valueOf(bool));
        }else if(attrName.equalsIgnoreCase("current")){
            setCurrent(Boolean.valueOf(bool));
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getAttribute(final String attrName) {

        if(attrName.equalsIgnoreCase("userValue")){
            return getUserValue();
        }else if(attrName.equalsIgnoreCase("default")){
            return getDefault();
        }else if(attrName.equalsIgnoreCase("units")){
            return getUnits();
        }else if(attrName.equalsIgnoreCase("unitSymbol")){
            return getUnitSymbol();
        }else if(attrName.equalsIgnoreCase("multipleValues")){
            return String.valueOf(isMultipleValues());
        }else if(attrName.equalsIgnoreCase("nearestValues")){
            return  String.valueOf(isNearestValues());
        }else if(attrName.equalsIgnoreCase("current")){
            return String.valueOf(isCurrent());
        }else   return null;
    }

}