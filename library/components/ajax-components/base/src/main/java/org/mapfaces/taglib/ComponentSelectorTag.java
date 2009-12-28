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

package org.mapfaces.taglib;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentELTag;
import org.mapfaces.component.UIComponentSelector;
import org.mapfaces.share.utils.TagUtils;
import org.mapfaces.taglib.treetable.HtmlTreeTableTag;

/**
 *
 * @author Leo Pratlong (Geomatys)
 */
public class ComponentSelectorTag extends UIComponentELTag {
    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.html.ComponentSelector";
    private static final String COMP_FAMILY   = "org.mapfaces.component.ComponentSelector";

    // ---------------------------------------------------------- Additionals fields
    private ValueExpression value = null;
    private ValueExpression type = null;
    private ValueExpression mandatory = null;
    private ValueExpression hasParent = null;
    private ValueExpression maxCar = null;
    private ValueExpression selectMap = null;

    // ---------------------------------------------------------- Methods
    @Override
    // General Methods
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    @Override
    public String getComponentType() {
        return COMP_FAMILY;
    }

    @Override
    protected void setProperties(UIComponent component) {
        try {
            super.setProperties(component);

            final FacesContext context = FacesContext.getCurrentInstance();

            TagUtils.affectUIValueWithValueExpression(context, value, UIComponentSelector.class, component, "Value", String.class);
            TagUtils.affectUIValueWithValueExpression(context, type, UIComponentSelector.class, component, "Type", String.class);
            TagUtils.affectUIValueWithValueExpression(context, hasParent, UIComponentSelector.class, component, "HasParent", Boolean.class);
            TagUtils.affectUIValueWithValueExpression(context, maxCar, UIComponentSelector.class, component, "MaxCar", Integer.class);
            TagUtils.affectUIValueWithValueExpression(context, selectMap, UIComponentSelector.class, component, "SelectMap", Map.class);

            component.setValueExpression("value", value);
            component.setValueExpression("type", type);
            component.setValueExpression("mandatory", mandatory);
            component.setValueExpression("hasParent", hasParent);
            component.setValueExpression("maxCar", maxCar);
            component.setValueExpression("selectMap", selectMap);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ComponentSelectorTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ComponentSelectorTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ComponentSelectorTag.class.getName()).log(Level.SEVERE, null, ex);
        }catch (NoSuchMethodException ex) {
            Logger.getLogger(ComponentSelectorTag.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void release() {
        super.release();
        value = null;
        type = null;
        mandatory = null;
        hasParent = null;
        maxCar = null;
        selectMap = null;
    }

    // ---------------------------------------------------------- Accessors Methods
    /**
     * @return the value
     */
    public ValueExpression getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(ValueExpression value) {
        this.value = value;
    }

    /**
     * @return the type
     */
    public ValueExpression getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ValueExpression type) {
        this.type = type;
    }

    /**
     * @return the mandatory
     */
    public ValueExpression getMandatory() {
        return mandatory;
    }

    /**
     * @param mandatory the mandatory to set
     */
    public void setMandatory(ValueExpression mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * @return the hasParent
     */
    public ValueExpression getHasParent() {
        return hasParent;
    }

    /**
     * @param hasParent the hasParent to set
     */
    public void setHasParent(ValueExpression hasParent) {
        this.hasParent = hasParent;
    }

    /**
     * @return the maxCar
     */
    public ValueExpression getMaxCar() {
        return maxCar;
    }

    /**
     * @param maxCar the maxCar to set
     */
    public void setMaxCar(ValueExpression maxCar) {
        this.maxCar = maxCar;
    }

    /**
     * @return the selectMap
     */
    public ValueExpression getSelectMap() {
        return selectMap;
    }

    /**
     * @param selectMap the selectMap to set
     */
    public void setSelectMap(ValueExpression selectMap) {
        this.selectMap = selectMap;
    }
}
