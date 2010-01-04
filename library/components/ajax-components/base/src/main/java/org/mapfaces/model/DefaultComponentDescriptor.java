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


package org.mapfaces.model;

import java.util.Map;

/**
 * Model for the ComponentSelector.
 * @author Leo Pratlong (Geomatys)
 * @since 0.3
 */
public class DefaultComponentDescriptor implements ComponentDescriptor {
    private String title;
    private String key;
    private Object value;
    private String type;
    private int maxCar;
    private Map<Object, String> selectMap;
    private boolean mandatory;
    private int maxOccurence;
    private boolean rendered;


    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the maxCar
     */
    public int getMaxCar() {
        return maxCar;
    }

    /**
     * @param maxCar the maxCar to set
     */
    public void setMaxCar(int maxCar) {
        this.maxCar = maxCar;
    }

    /**
     * @return the selectMap
     */
    public Map<Object, String> getSelectMap() {
        return selectMap;
    }

    /**
     * @param selectMap the selectMap to set
     */
    public void setSelectMap(Map<Object, String> selectMap) {
        this.selectMap = selectMap;
    }

    /**
     * @return the mandatory
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * @param mandatory the mandatory to set
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the maxOccurence
     */
    public int getMaxOccurence() {
        return maxOccurence;
    }

    /**
     * @param maxOccurence the maxOccurence to set
     */
    public void setMaxOccurence(int maxOccurence) {
        this.maxOccurence = maxOccurence;
    }

    /**
     * @return the rendered
     */
    public boolean isRendered() {
        return rendered;
    }

    /**
     * @param rendered the rendered to set
     */
    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }
}
