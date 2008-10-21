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
package org.mapfaces.taglib.abstractTree;

import javax.faces.component.UIComponent;

/**
 * <p>UITreeColumnELTag is the base class for all JSP tags that correspond to a TreeColumn Component instance in the view.</p>
 * <p> Attributes are :<ul>
 * <li>headerTitle</li>
 * <li>width</li>
 * <li>icon</li>
 * <li>styleHeader</li>
 * </ul>
 * @author kdelfour
 */
public abstract class UITreeColumnELTag extends UIColumnELTag {

    /* Methods*/
    /**
     * @override setProperties in class TreeELTag 
     * @param component
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
    }

    /**
     * @override release in class TreeELTag 
     */
    @Override
    public void release() {
        super.release();
    }

    /* Abstracts methods*/
    /**
     * <p>Subclasses must override this method to return the appropriate value.</p>
     * @return the component type for the component that is or will be bound to this tag.
     */
    @Override
    public abstract String getComponentType();

    /**
     * <p>Subclasses must override this method to return the appropriate value.</p>
     * @return the rendererType property that selects the Renderer to be used for encoding this component, 
     * or null to ask the component to render itself directly
     */
    @Override
    public abstract String getRendererType();

}
