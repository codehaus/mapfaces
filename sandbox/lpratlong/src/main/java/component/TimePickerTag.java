/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentELTag;

/**
 * This class defines the structure of our component and allows to link the
 * View with the class definition.
 * @author leopratlong
 */
public class TimePickerTag extends UIComponentELTag {

    /**
     * Properties of the component.
     */
    private ValueExpression value = null;
    private ValueExpression loadMootools = null;
    private ValueExpression loadJs = null;
    private ValueExpression style = null;
    private ValueExpression styleClass = null;
    private ValueExpression targetInput = null;

    /**
     * @return String Return the Renderer Type of the component.
     */
    @Override
    public String getRendererType() {
        return "renderer.timepickers";
    }

    /**
     * @return String Return the Component Type of the component.
     */
    @Override
    public String getComponentType() {
        return "components.timepickers";
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
            component.setValueExpression("targetInput", this.targetInput);
            
            UITimePicker timepicker = (UITimePicker) component;
            FacesContext context = FacesContext.getCurrentInstance();

            final String booleanPath = "java.lang.Boolean";
            final String stringPath = "java.lang.String";
            final String datePath = "java.util.Date";

            this.setPropertiesInComponent(this.value, datePath, "Value", context, timepicker);
            this.setPropertiesInComponent(this.loadMootools, "java.lang.Boolean", "LoadMootools", context, timepicker);
            this.setPropertiesInComponent(this.loadJs, booleanPath, "LoadJs", context, timepicker);
            this.setPropertiesInComponent(this.targetInput, stringPath, "targetInput", context, timepicker);
            this.setPropertiesInComponent(this.style, stringPath, "Style", context, timepicker);
            this.setPropertiesInComponent(this.styleClass, stringPath, "StyleClass", context, timepicker);

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

        this.value = null;
        this.loadMootools = null;
        this.loadJs = null;
        this.style = null;
        this.styleClass = null;
        this.targetInput = null;
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
    private void setPropertiesInComponent(ValueExpression expToAdd, String className, String propertyName, FacesContext context, UITimePicker timepicker) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if ((expToAdd != null) && !expToAdd.isLiteralText()) {
            Object valueObject = expToAdd.getValue(context.getELContext());
            final Class typeExp = Class.forName(className);
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
    public ValueExpression getTargetInput() {
        return targetInput;
    }

    /**
     * @param outputTop the outputTop to set
     */
    public void setTargetInput(ValueExpression targetInput) {
        this.targetInput = targetInput;
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
}
