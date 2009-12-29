/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.model;

import java.util.Map;

/**
 *
 * @author leopratlong
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
}
