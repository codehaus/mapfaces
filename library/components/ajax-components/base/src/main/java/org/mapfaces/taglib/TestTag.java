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

package org.mapfaces.taglib;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentELTag;
import org.mapfaces.component.UITest;
import org.mapfaces.share.utils.TagUtils;

/**
 *
 * @author Mehdi Sidhoum (Geomatys).
 * @since 0.2
 */
public class TestTag extends UIComponentELTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.Test";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.Test";

    private ValueExpression value = null;
    private ValueExpression name = null;

    /**
     * {@inheritDoc }
     */
    @Override
    protected void setProperties(final UIComponent component) {
        // always call the superclass method
        super.setProperties(component);

        final FacesContext context = FacesContext.getCurrentInstance();
        try {
            TagUtils.affectUIValueWithValueExpression(context, value, UITest.class, component, "Value", String.class);
            TagUtils.affectUIValueWithValueExpression(context, name, UITest.class, component, "Name", String.class);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TestTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TestTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TestTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(TestTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        component.setValueExpression("value",value);
        component.setValueExpression("name",name);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        value = null;
        name = null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return RENDER_TYPE;
    }

    /**
     * @return the value
     */
    public ValueExpression getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(ValueExpression value) {
        this.value = value;
    }

    /**
     * @return the name
     */
    public ValueExpression getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(ValueExpression name) {
        this.name = name;
    }
}
