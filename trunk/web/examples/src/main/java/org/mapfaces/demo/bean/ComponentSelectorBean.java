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
import java.util.List;
import javax.faces.model.SelectItem;
import org.mapfaces.model.ComponentDescriptor;
import org.mapfaces.model.DefaultComponentDescriptor;

/**
 * Bean to initialize properties of the component. It's just a bean example.
 * @author Mehdi Sidhoum (Geomatys)
 * @since 0.2
 */
public class ComponentSelectorBean {

    private ComponentDescriptor componentDescriptor;
    private List<SelectItem> typeList;

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
}
