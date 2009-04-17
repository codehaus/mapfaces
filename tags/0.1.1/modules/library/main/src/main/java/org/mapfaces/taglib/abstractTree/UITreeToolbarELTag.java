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

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;

/**
 * @author Kevin Delfour
 */
public abstract class UITreeToolbarELTag extends UITreeComponentELTag {

    private ValueExpression styleTools = null;
    private ValueExpression styleClassTools = null;
    
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
    public void setProperties(final UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("styleTools", styleTools);
        component.setValueExpression("styleClassTools", styleClassTools);
    }

    /**
     * Release any resources allocated during the execution of this tag handler.
     */
    @Override
    public void release() {
        super.release();
        setStyleTools(null);
        setStyleClassTools(null);
    }

    /* Accessors */
    public ValueExpression getStyleTools() {
        return styleTools;
    }

    public void setStyleTools(ValueExpression styleTools) {
        this.styleTools = styleTools;
    }

    public ValueExpression getStyleClassTools() {
        return styleClassTools;
    }

    public void setStyleClassTools(ValueExpression styleClassTools) {
        this.styleClassTools = styleClassTools;
    }
    
    
}

