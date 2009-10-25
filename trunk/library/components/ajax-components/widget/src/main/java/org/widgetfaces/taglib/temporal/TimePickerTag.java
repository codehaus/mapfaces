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

package org.widgetfaces.taglib.temporal;

import com.sun.faces.taglib.html_basic.InputTextTag;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.widgetfaces.component.temporal.UITimePicker;


/**
 * This class defines the structure of our component and allows to link the
 * View with the class definition.
 * @author leopratlong (Geomatys)
 * @since 0.2
 */
public class TimePickerTag extends InputTextTag {

    private static final String COMP_TYPE = "org.mapfaces.Timepicker";
    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.HTMLTimepicker";
    /**
     * Properties of the component.
     */
    private ValueExpression value = null;
    private ValueExpression loadMootools = null;
    private ValueExpression loadJs = null;
    private ValueExpression style = null;
    private ValueExpression styleClass = null;
    private ValueExpression outputTop = null;
    private ValueExpression loadCss = null;

    /**
     * @return String Return the Renderer Type of the component.
     */
    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    /**
     * @return String Return the Component Type of the component.
     */
    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    /**
     * Sets the properties of the component.
     * @param component
     */
    @Override
    public void setProperties(UIComponent component) {
        try {
            // always call the superclass method
            super.setProperties(component);
            component.setValueExpression("value", this.value);
            component.setValueExpression("loadMootools", this.loadMootools);
            component.setValueExpression("loadNogray", this.loadJs);
            component.setValueExpression("style", this.style);
            component.setValueExpression("styleClass", this.styleClass);
            component.setValueExpression("outputTop", this.outputTop);
            component.setValueExpression("loadCss", this.loadCss);

            UITimePicker timepicker = (UITimePicker) component;
            FacesContext context = FacesContext.getCurrentInstance();

            this.setPropertiesInComponent(this.value, Date.class, "Value", context, timepicker);
            this.setPropertiesInComponent(this.loadMootools, Boolean.class, "LoadMootools", context, timepicker);
            this.setPropertiesInComponent(this.loadJs, Boolean.class, "LoadJs", context, timepicker);
            this.setPropertiesInComponent(this.outputTop, Boolean.class, "OutputTop", context, timepicker);
            this.setPropertiesInComponent(this.style, String.class, "Style", context, timepicker);
            this.setPropertiesInComponent(this.styleClass, String.class, "StyleClass", context, timepicker);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TimePickerTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TimePickerTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TimePickerTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TimePickerTag.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Releases the component.
     */
    @Override
    public void release() {
        // always call the superclass method
        super.release();

        value = null;
        loadMootools = null;
        loadJs = null;
        style = null;
        styleClass = null;
        outputTop = null;
        loadCss = null;
    }

    /**
     * Set the properties of our UITimePicker by Reflection.
     * @param expToAdd Value expression to set in the UITimePicker.
     * @param className Name of the class of the property (including the lib path).
     * @param propertyName Name of the property (with an uppercase at the first letter);
     * @param context Faces context.
     * @param timepicker The UITimePicker.
     * @throws ClassNotFoundException Error in the definition of class name.
     * @throws IllegalAccessException Illegal access.
     * @throws IllegalArgumentException 
     * @throws InvocationTargetException
     */
    private void setPropertiesInComponent(ValueExpression expToAdd, Class typeExp,
            String propertyName, FacesContext context, UITimePicker timepicker)
            throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if ((expToAdd != null) && !expToAdd.isLiteralText()) {
            Object valueObject = expToAdd.getValue(context.getELContext());
            if (typeExp.isInstance(valueObject)) {
                final Class uitpClass = timepicker.getClass();
                boolean findMethod = false;
                int i = 0;
                Method[] methodList = uitpClass.getMethods();
                while ((i < methodList.length) && !findMethod) {
                    if (methodList[i].getName().equals("set" + propertyName)) {
                        findMethod = true;
                        methodList[i].invoke(timepicker, typeExp.cast(valueObject));
                    }
                    i++;
                }
            }
        }
    }

    /**
     * @param loadMootools the loadMootools to set
     */
    public void setLoadMootools(ValueExpression loadMootools) {
        this.loadMootools = loadMootools;
    }

    /**
     * @param loadJs the loadJs to set
     */
    public void setLoadJs(ValueExpression loadJs) {
        this.loadJs = loadJs;
    }

    /**
     * @param cssStyle the cssStyle to set
     */
    public void setStyle(ValueExpression cssStyle) {
        this.style = cssStyle;
    }

    /**
     * @param cssClass the cssClass to set
     */
    public void setStyleClass(ValueExpression cssClass) {
        this.styleClass = cssClass;
    }

    /**
     * @return the loadMootools
     */
    public ValueExpression getLoadMootools() {
        return loadMootools;
    }

    /**
     * @return the cssStyle
     */
    public ValueExpression getStyle() {
        return style;
    }

    /**
     * @return the cssClass
     */
    public ValueExpression getStyleClass() {
        return styleClass;
    }

    /**
     * @return the loadJs
     */
    public ValueExpression getLoadJs() {
        return loadJs;
    }

    /**
     * @return the outputTop
     */
    public ValueExpression getOutputTop() {
        return outputTop;
    }

    /**
     * @param outputTop the outputTop to set
     */
    public void setOutputTop(ValueExpression outputTop) {
        this.outputTop = outputTop;
    }

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
     * @return the loadCss
     */
    public ValueExpression getLoadCss() {
        return loadCss;
    }

    /**
     * @param loadCss the loadCss to set
     */
    public void setLoadCss(ValueExpression loadCss) {
        this.loadCss = loadCss;
    }
}
