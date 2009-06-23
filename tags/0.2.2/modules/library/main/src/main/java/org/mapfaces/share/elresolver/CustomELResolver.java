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

import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.util.ReflectionUtils;

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
 * @author Kevin Delfour
 */
public class CustomELResolver extends ELResolver {

    private static final Logger LOGGER = Logger.getLogger(CustomELResolver.class.getName());
    
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
    public Object getValue(final ELContext elcontext, final Object base, final Object property) throws ELException {
        
        Object result = null;

        final ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        final Map requestMap     = ec.getRequestMap();

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
            final TreeNodeModel node = (TreeNodeModel) base;
            // if the Node UserObject isn't null  and the method have been found
            //  then we invoke the method and return the result on this object
            if (node.getUserObject() != null) {
                final Method method = ReflectionUtils.lookupGetter(node.getUserObject().getClass(), property.toString());

                if (method != null) {
                    try {
                        result = method.invoke(node.getUserObject());
                    } catch (IllegalAccessException ex) {
                        LOGGER.log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        LOGGER.log(Level.SEVERE, null, ex);
                    }
                    if (result == null) {
                        result = ".";
                    }
                } else {
                    result = "No method found for this attribute!";
                }

                elcontext.setPropertyResolved(true);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Class<?> getType(final ELContext elContext, final Object base, final Object property) 
            throws NullPointerException, PropertyNotFoundException, ELException {
        if (base instanceof Context) {
            elContext.setPropertyResolved(true);
            return Object.class;
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setValue(final ELContext elContext, final Object base, final Object property, final Object value) 
            throws NullPointerException, PropertyNotFoundException, PropertyNotWritableException, ELException {
        if (base instanceof Context) {
            final Context context = (Context) base;
            elContext.setPropertyResolved(true);
            try {
                context.rebind(property.toString(), value);
            } catch (NamingException e) {
                throw new ELException(e);
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isReadOnly(ELContext arg0, Object arg1, Object arg2) throws NullPointerException, PropertyNotFoundException, ELException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext arg0, Object arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Class<?> getCommonPropertyType(ELContext arg0, Object arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
