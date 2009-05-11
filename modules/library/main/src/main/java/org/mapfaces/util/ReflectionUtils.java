/*
 *    Mapfaces - http://www.mapfaces.org
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

package org.mapfaces.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

/**
 * Utility class to regroup methods for reflection.
 *
 * @author Johann Sorel (Geomatys)
 */
public class ReflectionUtils{

    private static final Logger LOGGER = Logger.getLogger(ReflectionUtils.class.getName());
    

    /**
     * Private constructor to avoid class creation.
     */
    private ReflectionUtils(){}

    /**
     * 	Research a getter method corresponding to attribute property
     * @param base - The base object whose property value is to be returned, or null to resolve a top-level variable.
     * @param property - The property or variable to be resolved.
     * @return if the getter method exist, return this method else return null
     */
    public static Method lookupGetter(final Class base, final String property) {
        if (base == null || property == null){
            throw new NullPointerException("Base class and property name must not be null");
        }
        // First capitalize PropName
        final String propName = StringUtils.capitalize(property);
        // Search in base class methods the getter correspond to the attribut
        for (Method method : base.getMethods()) {
            if ((method.getName().equals("get" + propName)) || (method.getName().equals("is" + propName))) {
                return method;
            }
        }
        return null;
    }

    public static Method lookupSetter(final Class base, final String property) {
        // Fisrt capitalize PropName
        final String methodName = "Set" + StringUtils.capitalize(property);
        // Search in base class methods the getter correspond to the attribut
        for (final Method method : base.getMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

    public static <T> T invokeGetter(final Object userObject, final String property, final Class<T> type, boolean create){
        if (userObject == null)
            //if the userobject is null then force to return NULL value
            return null;
        
        final Method method = lookupGetter(userObject.getClass(), property);

        if(method != null){
            try {
                final T result = (T) method.invoke(userObject);
                if(result != null) return result;
            } catch (IllegalAccessException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }

        if(create){
            try {
                //try to return a default instance
                return type.newInstance();
            } catch (InstantiationException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public static <T> T invokeGetter(final Object userObject, final String property, final Class<T> type, T fallback){
        final Method method = lookupGetter(userObject.getClass(), property);

        if(method != null){
            try {
                final T result = (T) method.invoke(userObject);
                if(result != null) return result;
            } catch (IllegalAccessException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }

        return fallback;
    }

    public static void copyAttributes(final Object source, final Object target, final Collection<String> ignoredProperties) {

        if(source == null || target == null){
            throw new NullPointerException("Source and target must not be null");
        }

        final Class sourceClass = source.getClass();
        final Class targetClass = target.getClass();

        for (final Method setter : targetClass.getMethods()) {
            if (setter.getName().startsWith("set")) {
                final String propertyName = setter.getName().substring(3);

                if (!ignoredProperties.contains(propertyName)) {
                    final Method getter = lookupGetter(sourceClass, propertyName);

                    if(getter != null){
                        //copy the property
                        try {
                            if (getter.getParameterTypes().length != 0) {
                                //TODO because this is a custom duplication it is not a perfect clone of every child components in treeTable component.
                                LOGGER.log(Level.WARNING, "Could'nt invoke getter for property : "+getter.getName()+"  wicth contains "+getter.getParameterTypes().length+" arguments.");
                                continue;
                            }
                            Object resultGet = getter.invoke(source);
                            Class<?> returnType = getter.getReturnType();
                            if ( !(setter.getParameterTypes().length == 1 && (setter.getParameterTypes()[0]).isAssignableFrom(returnType)) ) {
                                LOGGER.log(Level.WARNING, "Could'nt invoke setter for property : "+setter.getName()+"  wicth contains "+setter.getParameterTypes().length+" arguments and types does not match : "+returnType+" and "+setter.getParameterTypes()[0]);
                                continue;
                            }
                            setter.invoke(target, resultGet);
                        } catch (IllegalAccessException ex) {
                            LOGGER.log(Level.SEVERE, null, ex);
                        } catch (IllegalArgumentException ex) {
                            LOGGER.log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            LOGGER.log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }            
        
    }

}
