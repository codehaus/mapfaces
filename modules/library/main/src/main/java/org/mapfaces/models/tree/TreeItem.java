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

package org.mapfaces.models.tree;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import org.mapfaces.models.Context;
import org.mapfaces.models.Dimension;
import org.mapfaces.models.Layer;

/**
 * @author Mehdi Sidhoum (Geomatys).
 * @author Kevin Delfour (Geomatys).
 */
public class TreeItem implements Serializable {

    private Object userObject;
    private String title = "",  name = "";

    /**
     * Creates a new instance of treeItem by passing a serializable object.
     * @param obj
     */
    public TreeItem(final Object obj) {
        userObject = obj;
        if (obj != null) {
            if (obj instanceof Layer) {
                // do something
            } else if (obj instanceof Context) {
                //do something
            }
        }
    }

    /**
     * This is a simple constructor of treeItem with just a title and a name.
     * @param title
     * @param name
     */
    public TreeItem(String title, String name) {
        setTitle(title);
        setName(name);
    }

    /**
     * 	Research a getter method corresponding to attribute property
     * @param base - The base object whose property value is to be returned, or null to resolve a top-level variable.
     * @param property - The property or variable to be resolved.
     * @return if the getter method exist, return this method else return null 
     */
    public Method getMethod(final Object base, final Object property) {
        // Fisrt capitalize PropName
        final String propName = StringUtils.capitalize(property.toString());
        final Class classe = base.getClass();
        // Search in base class methods the getter correspond to the attribut
        for (Method method : classe.getMethods()) {
            if ((method.getName().equals("get" + propName)) || (method.getName().equals("is" + propName))) {
                return method;
            }
        }
        return null;
    }

    /**
     * This is a getter for dataUrl when the userObject is an instance of Layer.
     */
    public String getDataUrl() {
        final Method methode = getMethod(userObject, "DataUrl");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (String) methode.invoke(userObject) : "";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * This is a getter for DimensionList when the userObject is an instance of Layer.
     */
    public HashMap<String, Dimension> getDimensionList() {
        final Method methode = getMethod(userObject, "DimensionList");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (HashMap) methode.invoke(userObject) : null;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * This is a getter for Group when the userObject is an instance of Layer.
     */
    public String getGroup() {
        final Method methode = getMethod(userObject, "Group");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (String) methode.invoke(userObject) : "";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * This is a getter for Group when the userObject is an instance of Layer or Context or other object which contains an id property with the getter method.
     */
    public String getId() {
        final Method methode = getMethod(userObject, "Id");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (String) methode.invoke(userObject) : "";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * This is a getter for Name when the userObject is an instance of Layer.
     */
    public String getName() {
        final Method methode = getMethod(userObject, "Name");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (String) methode.invoke(userObject) : name;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * This is a getter for Opacity when the userObject is an instance of Layer.
     */
    public String getOpacity() {
        final Method methode = getMethod(userObject, "Opacity");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (String) methode.invoke(userObject) : "";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * This is a getter for OutputFormat when the userObject is an instance of Layer.
     */
    public String getOutputFormat() {
        final Method methode = getMethod(userObject, "OutputFormat");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (String) methode.invoke(userObject) : "";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * This is a getter for Title when the userObject is an instance of Layer or Context or another bean object that contains the property title .
     */
    public String getTitle() {
        final Method methode = getMethod(userObject, "Title");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (String) methode.invoke(userObject) : title;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * This is a getter for Hidden when the userObject is an instance of Layer or Context or another bean object that contains the property hidden .
     */
    public boolean isHidden() {
        final Method methode = getMethod(userObject, "Hidden");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (Boolean) methode.invoke(userObject) : false;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * This is a getter for LegendUrl when the userObject is an instance of Layer.
     */
    public String getLegendUrl() {
        final Method methode = getMethod(userObject, "LegendUrl");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (String) methode.invoke(userObject) : "";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * This is a getter for UserValueElevation when the userObject is an instance of Layer.
     */
    public String getUserValueElevation() {
        final Method methode = getMethod(userObject, "UserValueElevation");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (String) methode.invoke(userObject) : "";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * This is a getter for UserValueDimRange when the userObject is an instance of Layer.
     */
    public String getUserValueDimRange() {
        final Method methode = getMethod(userObject, "UserValueDimRange");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (String) methode.invoke(userObject) : "";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * This is a getter for Elevation when the userObject is an instance of Layer.
     */
    public Dimension getElevation() {
        final Method methode = getMethod(userObject, "Elevation");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (Dimension) methode.invoke(userObject) : null;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * This is a getter for Time when the userObject is an instance of Layer.
     */
    public Dimension getTime() {
        final Method methode = getMethod(userObject, "Time");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (Dimension) methode.invoke(userObject) : null;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * This is a getter for CompId when the userObject is an instance of Layer.
     */
    public String getCompId() {
        final Method methode = getMethod(userObject, "CompId");
        try {
            return (methode != null && methode.invoke(userObject) != null) ? (String) methode.invoke(userObject) : "";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TreeItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
