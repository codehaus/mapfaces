/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.share.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
                final Class uiClass = componentClass;
                boolean findMethod = false;
                int i = 0;
                Method[] methodList = uiClass.getMethods();
                while ((i < methodList.length) && !findMethod) {
                    if (methodList[i].getName().equals("set" + propertyName)) {
                        findMethod = true;
                        methodList[i].invoke(component, typeExp.cast(valueObject));
                    }
                    i++;
                }
            }
        }
    }
}
