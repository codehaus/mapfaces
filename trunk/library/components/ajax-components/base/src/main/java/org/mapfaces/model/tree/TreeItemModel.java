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

package org.mapfaces.model.tree;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * 
 * @author Kevin Delfour (Geomatys).
 */
public class TreeItemModel implements Serializable {

    protected static final Logger LOGGER = Logger.getLogger(TreeItemModel.class.getName());
    private Object userObject;
    private String title = "";
    private String name = "";
    /**
     * This property is used to specify an image by uri path for this TreeItem model..
     */
    private String icon;

    /**
     * Creates a new instance of treeItem by passing a serializable object.
     * @param obj
     */
    public TreeItemModel(final Object obj) {
        userObject = obj;
    }

    /**
     * This is a simple constructor of treeItem with just a title and a name.
     * @param title
     * @param name
     */
    public TreeItemModel(String title, String name) {
        setTitle(title);
        setName(name);
    }

    /**
     * This is a getter for Name when the userObject is an instance of Layer.
     */
    public String getName() {
        if (userObject instanceof String) {
            return (String) userObject;
        }
        return null;
    }

    /**
     * This is a getter for Title when the userObject is an instance of Layer or Context or another bean object that contains the property title .
     */
    public String getTitle() {
        if (userObject instanceof String) {
            return (String) userObject;
        }
        return null;
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

    /**
     * This getter does not use the reflection, it is just an attribute for TreeItem model.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This setter does not use the reflection
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}
