/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.models;

/**
 *
 * @author olivier
 */
public class DefaultDimension implements Dimension{
    
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
    
    @Override
    public String getValue() {
        return value;
    }
    
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    public void setDefault(String Default) {
        this.Default = Default;
    }
   

}
