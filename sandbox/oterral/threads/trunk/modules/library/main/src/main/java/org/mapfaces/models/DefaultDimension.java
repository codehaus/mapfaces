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

    

    public void setName(String name) {
        this.name = name;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setUnitSymbol(String unitSymbol) {
        this.unitSymbol = unitSymbol;
    }

    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }

    public boolean isMultipleValues() {
        return multipleValues;
    }

    public void setMultipleValues(boolean multipleValues) {
        this.multipleValues = multipleValues;
    }

    public boolean isNearestValues() {
        return nearestValues;
    }

    public void setNearestValues(boolean nearestValues) {
        this.nearestValues = nearestValues;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUnits() {
        return units;
    }

    @Override
    public String getUnitSymbol() {
        return unitSymbol;
    }

    @Override
    public String getUserValue() {
        return userValue;
    }
    
    @Override
    public String getDefault() {
        return Default;
    }
 
    public void setDefault(String Default) {
        this.Default = Default;
    }
    
    @Override
    public String getValue() {
        return value;
    }
    
    @Override
    public void setValue(String value) {
        this.value = value;
    }


    public void setAttribute(String attrName, String value) {
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
    
    public void setAttribute(String attrName, boolean bool) {
        if(attrName.equalsIgnoreCase("multipleValues")){
            setMultipleValues(Boolean.valueOf(bool));
        }else if(attrName.equalsIgnoreCase("nearestValues")){
            setNearestValues(Boolean.valueOf(bool));
        }else if(attrName.equalsIgnoreCase("current")){
            setCurrent(Boolean.valueOf(bool));
        }
    }
    
    public String getAttribute(String attrName) {
        
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