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
import javax.faces.webapp.UIComponentELTag;

/**
 * <p>UITreeLinesELTag is the base class for all JSP tags that correspond to a Tree Lines Component instance in the view.</p>
 * @author Kevin Delfour
 */
public abstract class UITreeLinesELTag extends UIComponentELTag {

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

    /* Methods*/
    /**
     * <p>Override properties and attributes of the specified component, 
     * if the corresponding properties of this tag handler instance were explicitly set.</p>
     * <p>This method must be called ONLY  if the specified UIComponent was in fact created during 
     * the execution of this tag handler instance, and this call will occur BEFORE the 
     * UIComponent is added to the view.</p>
     * @param component UIComponent whose properties are to be overridden
     */
    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
    }

    /**
     * Release any resources allocated during the execution of this tag handler.
     */
    @Override
    public void release() {
        super.release();
    }
}
