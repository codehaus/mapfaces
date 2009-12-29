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
public interface ComponentDescriptor {

    public String getTitle();

    public void setTitle(String title);

    public String getKey();

    public void setKey(String key);

    public Object getValue();

    public void setValue(Object value);

    public String getType();

    public void setType(String type);

    public int getMaxCar();

    public void setMaxCar(int maxCar);
    
    public Map<Object, String> getSelectMap();

    public void setSelectMap(Map<Object, String> selectMap);

    public boolean isMandatory();

    public void setMandatory(boolean mandatory);

    public int getMaxOccurence();

    public void setMaxOccurence(int maxOccurence);
}
