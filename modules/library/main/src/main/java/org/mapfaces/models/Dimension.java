package org.mapfaces.models;

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
