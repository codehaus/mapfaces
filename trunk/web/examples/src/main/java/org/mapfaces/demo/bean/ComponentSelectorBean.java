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

package org.mapfaces.demo.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;
import org.mapfaces.model.ComponentDescriptor;
import org.mapfaces.model.DefaultComponentDescriptor;

/**
 * Bean to initialize properties of the component. It's just a bean example.
 * @author Leo Pratlong (Geomatys)
 * @since 0.3
 */
public class ComponentSelectorBean {

    private ComponentDescriptor componentDescriptor;
    private List<SelectItem> typeList;
    private List<ComponentDescriptor> valueList;
    private Object selectedKey;
    private Map<Object, String> selectMap;

    /**
     * Creates a new instance of DatePickerBean.
     */
    public ComponentSelectorBean(){
        typeList = new ArrayList<SelectItem>();
        typeList.add(new SelectItem("text"));
        typeList.add(new SelectItem("mail"));
        typeList.add(new SelectItem("textarea"));
        typeList.add(new SelectItem("date"));
        typeList.add(new SelectItem("select"));
        typeList.add(new SelectItem("readonly"));

        componentDescriptor = new DefaultComponentDescriptor();
        componentDescriptor.setKey("key");
        componentDescriptor.setTitle("title");
        componentDescriptor.setValue("Valeur");
        componentDescriptor.setMandatory(false);
        componentDescriptor.setType("text");
        componentDescriptor.setMaxCar(100);
        componentDescriptor.setRendered(true);

        valueList = new ArrayList<ComponentDescriptor>();
        ComponentDescriptor value = new DefaultComponentDescriptor();
        value.setTitle("title1");
        value.setKey("key1");
        value.setType("text");
        value.setValue("Valeur1");
        value.setMaxCar(125);
        value.setMandatory(true);
        valueList.add(value);
        value = new DefaultComponentDescriptor();
        value.setTitle("title2");
        value.setKey("key2");
        value.setType("mail");
        value.setValue("gerard@mail.com");
        value.setMandatory(false);
        valueList.add(value);
        value = new DefaultComponentDescriptor();
        value.setTitle("title3");
        value.setKey("key3");
        value.setType("textarea");
        value.setValue("azertyuiop qsdfghjklm wxcvbn 0123456789");
        value.setMaxCar(150);
        valueList.add(value);

        selectMap = new HashMap<Object, String>();
        selectMap.put("val1", "Valeur 1");
        selectMap.put("val2", "Valeur 2");
        selectMap.put("val3", "Valeur 3");
        selectMap.put("val4", "Valeur 4");
        selectMap.put("val5", "Valeur 5");
        selectMap.put("val6", "Valeur 6");
        selectMap.put("val7", "Valeur 7");
        selectMap.put("val8", "Valeur 8");
        selectedKey = "val4";
        value = new DefaultComponentDescriptor();
        value.setTitle("title4");
        value.setKey("key4");
        value.setType("select");
        value.setValue(selectedKey);
        value.setSelectMap(selectMap);
        valueList.add(value);
        value = new DefaultComponentDescriptor();
        value.setTitle("title5");
        value.setKey("key5");
        value.setType("date");
        value.setValue("21/11/2009");
        valueList.add(value);
    }

    /**
     * @param componentDescriptor the componentDescriptor to set
     */
    public void setComponentDescriptor(ComponentDescriptor componentDescriptor) {
        this.setComponentDescriptor(componentDescriptor);
    }

    /**
     * @param componentDescriptor the componentDescriptor to set
     */
    public ComponentDescriptor getComponentDescriptor() {
        return componentDescriptor;
    }

    /**
     * @return the typeList
     */
    public List<SelectItem> getTypeList() {
        return typeList;
    }

    /**
     * @param typeList the typeList to set
     */
    public void setTypeList(List<SelectItem> typeList) {
        this.typeList = typeList;
    }

    /**
     * @return the selectedKey
     */
    public Object getSelectedKey() {
        return selectedKey;
    }

    /**
     * @param selectedKey the selectedKey to set
     */
    public void setSelectedKey(Object selectedKey) {
        this.selectedKey = selectedKey;
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
     * @return the valueList
     */
    public List<ComponentDescriptor> getValueList() {
        return valueList;
    }

    /**
     * @param valueList the valueList to set
     */
    public void setValueList(List<ComponentDescriptor> valueList) {
        this.valueList = valueList;
    }
}