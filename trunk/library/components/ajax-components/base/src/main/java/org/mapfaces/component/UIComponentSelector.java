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
package org.mapfaces.component;

import com.sun.faces.renderkit.html_basic.BaseTableRenderer;
import com.sun.faces.renderkit.html_basic.TableRenderer;
import java.util.Map;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.el.MethodBinding;
import javax.faces.event.ValueChangeListener;
import javax.faces.validator.Validator;
import org.mapfaces.component.treetable.UITreeColumn;
import org.mapfaces.share.utils.FacesUtils;

/**
 *
 * @author leo pratlong (geomatys)
 */
public class UIComponentSelector extends UIWidgetBase implements EditableValueHolder {

    private static final String COMP_FAMILY   = "org.mapfaces.component.ComponentSelector";

    private String value;
    private String type;
    private boolean hasParent;
    private boolean mandatory;
    private Integer maxCar;
    private Map<Object, String> selectMap;
    private Integer maxOccurence;
    private String key;

    public UIComponentSelector() {
        super();
        setRendererType("org.mapfaces.renderkit.html.ComponentSelector");

        System.out.println("############   UIComponentSelector  constructor   id = "+this.getId());
        

    }

    @Override
    public String getFamily() {
        return COMP_FAMILY;
    }

    @Override
    public Object saveState(FacesContext context) {
        final Object[] values = new Object[9];
        values[0] = super.saveState(context);
        values[1] = getValue();
        values[2] = getType();
        values[3] = isHasParent();
        values[4] = isMandatory();
        values[5] = getMaxCar();
        values[6] = getSelectMap();
        values[7] = getMaxOccurence();
        values[8] = getKey();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        final Object[] values = (Object[]) state;
        super.restoreState(context, values[0]);
        setValue((String) values[1]);
        setType((String) values[2]);
        setHasParent((Boolean) values[3]);
        setMandatory((Boolean) values[4]);
        setMaxCar((Integer) values[5]);
        setSelectMap((Map) values[6]);
        setMaxOccurence((Integer) values[7]);
        setKey((String) values[8]);
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
     * @return the getParent
     */
    public boolean isHasParent() {
        return hasParent;
    }

    /**
     * @param getParent the getParent to set
     */
    public void setHasParent(boolean hasParent) {
        this.hasParent = hasParent;
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

    /**
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the maxCar
     */
    public Integer getMaxCar() {
        return maxCar;
    }

    /**
     * @param maxCar the maxCar to set
     */
    public void setMaxCar(Integer maxCar) {
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
     * @return the maxOccurence
     */
    public Integer getMaxOccurence() {
        return maxOccurence;
    }

    /**
     * @param maxOccurence the maxOccurence to set
     */
    public void setMaxOccurence(Integer maxOccurence) {
        this.maxOccurence = maxOccurence;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    public Object getSubmittedValue() {
        return value;
    }

    public void setSubmittedValue(Object submittedValue) {
        
    }

    public boolean isLocalValueSet() {
        return true;
    }

    public void setLocalValueSet(boolean localValueSet) {
        
    }

    public boolean isValid() {
        return true;
    }

    public void setValid(boolean valid) {
        
    }

    public boolean isRequired() {
        return false;
    }

    public void setRequired(boolean required) {
        
    }

    public MethodBinding getValidator() {
        return null;
    }

    public void setValidator(MethodBinding validatorBinding) {
        
    }

    public MethodBinding getValueChangeListener() {
        return null;
    }

    public void setValueChangeListener(MethodBinding valueChangeMethod) {
        
    }

    public void addValidator(Validator validator) {
        
    }

    public Validator[] getValidators() {
        return null;
    }

    public void removeValidator(Validator validator) {
        
    }

    public void addValueChangeListener(ValueChangeListener listener) {
        
    }

    public ValueChangeListener[] getValueChangeListeners() {
        return null;
    }

    public void removeValueChangeListener(ValueChangeListener listener) {
        
    }

    public Object getLocalValue() {
        
        return this.getAttributes().get("value");
    }

    public Converter getConverter() {
        return null;
    }

    public void setConverter(Converter converter) {
        
    }
}
