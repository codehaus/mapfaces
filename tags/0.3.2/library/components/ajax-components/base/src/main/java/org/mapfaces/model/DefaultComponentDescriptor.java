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
 * Model for the ComponentSelector. This class describes all the properties we
 * need to build a component. It will be usefull for the UIComponentSelector.
 * @author Leo Pratlong (Geomatys)
 * @since 0.3
 */
public class DefaultComponentDescriptor implements ComponentDescriptor {
    /**
     * Title of the component.
     */
    private String title;
    /**
     * This key is used to build the client ID of the component.
     */
    private String key;
    /**
     * Value of the component.
     */
    private Object value;
    /**
     * Type of the component. Could be "text, textarea, mail, web, date, select, readonly".
     */
    private String type;
    /**
     * Number max of caracter for the value of the component.
     */
    private int maxCar;
    /**
     * This map is used for the Select component. It will not be used for the other.
     */
    private Map<Object, String> selectMap;
    /**
     * This attribute indicates if the component is a mandatory field. In this case,
     * specifics css class will be setted.
     */
    private boolean mandatory;
    /**
     * Indicates the number max of occurence (for example, in an iterative use,
     * we should need to display more than 1 mail adress and the user could add
     * new mail adress. This attribute indicates that we render a "more" button).
     */
    private int maxOccurence;
    /**
     * Render the component or not.
     */
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
