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
import java.util.HashMap;
import java.util.logging.Logger;

import org.mapfaces.models.Context;
import org.mapfaces.models.Dimension;
import org.mapfaces.models.Layer;
import org.mapfaces.util.ReflectionUtils;

/**
 * @author Mehdi Sidhoum (Geomatys).
 * @author Kevin Delfour (Geomatys).
 */
public class TreeItem implements Serializable {

    protected static final Logger LOGGER = Logger.getLogger(TreeItem.class.getName());

    private Object userObject;
    private String title = "";
    private String name = "";

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
     * This is a getter for dataUrl when the userObject is an instance of Layer.
     */
    public String getDataUrl() {
        return ReflectionUtils.invokeGetter(userObject, "DataUrl", String.class, true);
    }

    /**
     * This is a getter for DimensionList when the userObject is an instance of Layer.
     */
    public HashMap<String, Dimension> getDimensionList() {
        return ReflectionUtils.invokeGetter(userObject, "DimensionList", HashMap.class, false);
    }

    /**
     * This is a getter for Group when the userObject is an instance of Layer.
     */
    public String getGroup() {
        return ReflectionUtils.invokeGetter(userObject, "Group", String.class, true);
    }

    /**
     * This is a getter for Group when the userObject is an instance of Layer or Context or other object which contains an id property with the getter method.
     */
    public String getId() {
        return ReflectionUtils.invokeGetter(userObject, "Id", Object.class, true).toString();
    }

    /**
     * This is a getter for Name when the userObject is an instance of Layer.
     */
    public String getName() {
        if(userObject instanceof String){
            return (String)userObject;
        }else{
            Object str = ReflectionUtils.invokeGetter(userObject, "Name", Object.class, false);
            return (str != null) ? str.toString() : name;
        }
    }

    /**
     * This is a getter for Opacity when the userObject is an instance of Layer.
     */
    public String getOpacity() {
        return ReflectionUtils.invokeGetter(userObject, "Opacity", String.class, true);
    }

    /**
     * This is a getter for OutputFormat when the userObject is an instance of Layer.
     */
    public String getOutputFormat() {
        return ReflectionUtils.invokeGetter(userObject, "OutputFormat", String.class, true);
    }

    /**
     * This is a getter for Title when the userObject is an instance of Layer or Context or another bean object that contains the property title .
     */
    public String getTitle() {
        if(userObject instanceof String){
            return (String)userObject;
        }else{
            String str = ReflectionUtils.invokeGetter(userObject, "Title", String.class, false);
            return (str != null) ? str : title;
        }
    }

    /**
     * This is a getter for Hidden when the userObject is an instance of Layer or Context or another bean object that contains the property hidden .
     */
    public boolean isHidden() {
        return ReflectionUtils.invokeGetter(userObject, "Hidden", Boolean.class, Boolean.FALSE);
    }

    /**
     * This is a getter for LegendUrl when the userObject is an instance of Layer.
     */
    public String getLegendUrl() {
        return ReflectionUtils.invokeGetter(userObject, "LegendUrl", String.class, true);
    }

    /**
     * This is a getter for UserValueElevation when the userObject is an instance of Layer.
     */
    public String getUserValueElevation() {
        return ReflectionUtils.invokeGetter(userObject, "UserValueElevation", String.class, true);
    }

    /**
     * This is a getter for UserValueDimRange when the userObject is an instance of Layer.
     */
    public String getUserValueDimRange() {
        return ReflectionUtils.invokeGetter(userObject, "UserValueDimRange", String.class, true);
    }

    /**
     * This is a getter for Elevation when the userObject is an instance of Layer.
     */
    public Dimension getElevation() {
        return ReflectionUtils.invokeGetter(userObject, "Elevation", Dimension.class, false);
    }

    /**
     * This is a getter for Time when the userObject is an instance of Layer.
     */
    public Dimension getTime() {
        return ReflectionUtils.invokeGetter(userObject, "Time", Dimension.class, false);
    }

    /**
     * This is a getter for CompId when the userObject is an instance of Layer.
     */
    public String getCompId() {
        return ReflectionUtils.invokeGetter(userObject, "CompId", String.class, true);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TreeItem :").append('\n');
        if (name != null) {
            sb.append("name: ").append(name).append('\n');
        }
        sb.append("getName: ").append(getName()).append('\n');
        if (title != null) {
            sb.append("title: ").append(title).append('\n');
        }
        if (userObject != null) {
            sb.append("userObject: ").append(userObject);
        }
        return sb.toString();
    }

    public Object getUserObject() {
        return userObject;
    }

    public void setUserObject(Object userObject) {
        this.userObject = userObject;
    }
    
}
