/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.share.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * This class regroups all the methods which can be usefull in the component tag Classes.
 * @author Leo Pratlong (Geomatys)
 * @since 0.3
 */
public final class TagUtils {

    private TagUtils() {}
    
    /**
     * Set the properties of a component with the ValueExpression (using Reflection).
     * @param expToAdd Value expression to set in the UITimePicker.
     * @param className Name of the class of the property (including the lib path).
     * @param propertyName Name of the property (with an uppercase at the first letter);
     * @param context Faces context.
     * @param component The UIComponent on which we want to set the value.
     * @throws ClassNotFoundException Error in the definition of class name.
     * @throws IllegalAccessException Illegal access.
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static void setPropertiesWithValueExpression(ValueExpression expToAdd, Class typeExp,
            String propertyName, FacesContext context, UIComponent component, Class componentClass)
            throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if ((expToAdd != null) && !expToAdd.isLiteralText()) {
            Object valueObject = expToAdd.getValue(context.getELContext());
            if (typeExp.isInstance(valueObject)) {
                invokeSetterForProperty(componentClass, propertyName, component, typeExp, valueObject);
            }
        }
    }

    /**
     * This method allows to affect a value from a ValueExpression to an UI property (must be used in some
     * case like for the List in the DataRequestTag) . It uses reflection to
     * be a generic method for all types of property. Opposing to the setPropertiesWithValueExpression
     * method, this one recreate a ValueExpression with a non interprate EL reference.
     * @param context FacesContext.
     * @param ve Non interprate value expression in the Tag class.
     * @param componentClass Class type of the component.
     * @param component The UIComponent on which we want to set the value.
     * @param propertyName Name of the property (with an uppercase at the first letter).
     * @param valueClass Class type of the Value to set.
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static void affectUIValueWithValueExpression(FacesContext context, ValueExpression ve, Class componentClass,
            UIComponent component, String propertyName, Class valueClass) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("POINT 0");
        if (ve != null) {
            System.out.println("POINT A");
            final ExpressionFactory ef = context.getApplication().getExpressionFactory();
            if (ve.getExpressionString() != null && ve.getExpressionString().contains("#")) {
                System.out.println("point B");
                final ValueExpression vex = ef.createValueExpression(context.getELContext(), ve.getExpressionString(), valueClass);
                invokeSetterForProperty(componentClass, propertyName, component, valueClass, vex.getValue(context.getELContext()));
            }
        }
    }

    /**
     * This method invokes a specified Setter method in an UIComponent, and affect it a value.
     * @param componentClass Class type of the component.
     * @param propertyName Name of the property (with an uppercase at the first letter).
     * @param component The UIComponent on which we want to set the value.
     * @param typeExp Class type of the Value to set.
     * @param valueObject Value to set.
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    private static void invokeSetterForProperty(Class componentClass, String propertyName, 
            UIComponent component, Class typeExp, Object valueObject)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Method[] methodList = componentClass.getMethods();
        int i = 0;
        boolean findMethod = false;
        while ((i < methodList.length) && !findMethod) {
            if (methodList[i].getName().equals("set" + propertyName)) {
                System.out.println("POINT C");
                findMethod = true;
                methodList[i].invoke(component, typeExp.cast(valueObject));
            }
            i++;
        }
    }
}
