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

/**
 * This is a TreeItem interface which can be implmented for basic treetable
 * component for Map treetable like UILayerControl component
 * @author Mehdi Sidhoum (Geomatys).
 */
public interface AbstractTreeItem extends Serializable {

    /**
     * All instances of TreeItem must have an identifier, invoked by reflection process.
     */
    public String getId();

    /**
     * In many case a title is the string value of the node.
     * It can be called with reflection or not.
     */
    public String getTitle();

    /**
     * Setter for the title property. It can be called by reflection too.
     * @param title
     */
    public void setTitle(String title);

    /**
     * This is the most important property of this interface.
     * The userObject is an object serializable that can be passed as a treeItem value.
     * @return
     */
    public Object getUserObject();

    /**
     * userObject setter of TreeItem
     * @param userObject
     */
    public void setUserObject(Object userObject);

    /**
     * For a complete treeitem instance, it is better to have the possibility to add an icon for each row of the treetable component
     * Note this property must be taken into a count for treetable renderers.
     * In many case the value is an URL. this method cannot use the reflection it is an internal property of TreeItem instance.
     */
    public String getIcon();

    /**
     * This setter does not use the reflection
     */
    public void setIcon(String icon);

    /**
     * The userValue is an abstract value that can be used to render or not some nodes in the treetable component.
     * The type is Serializable and the value can be boolean or integer...etc.
     * @return the userValue
     */
    public Serializable getUserValue();

    /**
     * @param userValue the userValue to set
     */
    public void setUserValue(Serializable userValue);
}
