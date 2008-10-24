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
package org.mapfaces.share.elresolver;

import java.beans.FeatureDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ELResolver;
import javax.el.PropertyNotFoundException;
import javax.el.PropertyNotWritableException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.lang.model.element.Name;
import javax.naming.Context;
import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;
import org.mapfaces.models.tree.TreeNodeModel;

/**
 * Resolve variable names known to this resolver;
 * otherwise, delegate to the ELResolver chain
 * 
 * Note :   you have to declare your CustomELResolver
 *              in your faces-config.xml add like this :
 * 
 *      <application>
 *          <el-resolver>
 *              faces.utils.CustomELResolver
 *          </el-resolver>
 *      </application>
 * 
 * @author kdelfour
 */
public class CustomELResolver extends ELResolver {

    /**
     * Evaluates the expression relative to the provided context, and returns the resulting value.
     * Attempts to resolve the given property object on the given base object.
     * @param elcontext - The context of this evaluation.
     * @param base - The base object whose property value is to be returned, or null to resolve a top-level variable.
     * @param property - The property or variable to be resolved.
     * @return If the propertyResolved property of ELContext was set to true, then the result of the variable or property resolution; otherwise undefined.
     * @throws javax.el.ELException - if context is null
     */
    @Override
    public Object getValue(ELContext elcontext, Object base, Object property) throws ELException {
        Logger logger = Logger.getLogger(CustomELResolver.class.getName());
        Object result = null;

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map requestMap = ec.getRequestMap();

        String varName = null;

        if (base == null) {
            if (property.toString().equals(requestMap.get("org.treetable.varName"))) {
                // If the varname correspond to the property name invoke
                // return the node 
                result = (TreeNodeModel) requestMap.get("org.treetable.NodeInstance");
                elcontext.setPropertyResolved(true);
            }
        } else if (base instanceof TreeNodeModel) {
            requestMap.put("property", property.toString());
            // if the base is an instance of TreeNodeModel then
            // we try to find the getter access method for the property
            TreeNodeModel node = (TreeNodeModel) base;
            Method methode = getMethod(node.getUserObject(), property);
            // if the Node UserObject isn't null  and the method have been found
            //  then we invoke the method and return the result on this object
            if (node.getUserObject() != null) {
                try {
                    if (methode != null) {
                        result = methode.invoke(node.getUserObject());
                        if (result == null) {
                            result = ".";
                        }
                    } else {
                        result = "No method found for this attribute!";
                    }
                } catch (IllegalAccessException ex) {
                    logger.log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    System.out.println("[WARNING] " + Level.SEVERE + " - " + ex);
                // logger.log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
                elcontext.setPropertyResolved(true);
            }
        }
        return result;
    }

    /**
     * 	Research getter method corresponding to attribute property
     * @param base - The base object whose property value is to be returned, or null to resolve a top-level variable.
     * @param property - The property or variable to be resolved.
     * @return if the getter method exist, return this method else return null 
     */
    public Method getMethod(Object base, Object property) {
        // Fisrt capitalize PropName
        String propName = StringUtils.capitalize(property.toString());
        Class classe = base.getClass();
        // Search in base class methods the getter correspond to the attribut
        for (Method method : classe.getMethods()) {
            if ((method.getName().equals("get" + propName)) || (method.getName().equals("is" + propName))) {
                return method;
            }
        }
        return null;
    }

    @Override
    public Class<?> getType(ELContext elContext, Object base, Object property) throws NullPointerException, PropertyNotFoundException, ELException {
        if (null != base && base instanceof Context) {
            elContext.setPropertyResolved(true);
            return Object.class;
        }
        return null;
    }

    @Override
    public void setValue(ELContext elContext, Object base, Object property, Object value) throws NullPointerException, PropertyNotFoundException, PropertyNotWritableException, ELException {
        if (null != base && base instanceof Context) {
            Context context = (Context) base;
            elContext.setPropertyResolved(true);
            try {
                if (property instanceof Name) {
                    context.rebind(((Name) property).toString(), value);
                } else {
                    context.rebind(property.toString(), value);
                }

            } catch (NamingException e) {
                throw new ELException(e);
            }
        }
    }

    @Override
    public boolean isReadOnly(ELContext arg0, Object arg1, Object arg2) throws NullPointerException, PropertyNotFoundException, ELException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext arg0, Object arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Class<?> getCommonPropertyType(ELContext arg0, Object arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
