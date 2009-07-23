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

package org.mapfaces.taglib.models;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentELTag;
import org.mapfaces.component.models.UIContext;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class ContextTag extends UIComponentELTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.models.Context";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.models.Context";
    /**
     * The url of context file.
     */
    private ValueExpression service = null;
    /**
     * The value expression that represents a list of layers.
     */
    private ValueExpression value = null;
    /**
     * The style class of the overall div that surrounds this component.
     */
    private ValueExpression styleClass = null;
    /**
     * The style of the overall div that surrounds this component.
     */
    private ValueExpression style = null;
    
    private ValueExpression debug = null;
    /**
     * This is a flag that indicates if the scripts prototype, scruptaculous and mootools must be loaded.
     */
    private ValueExpression scriptaculous = null;
    /**
     * This is a flag that indicates if the scripts mootools must be loaded.
     */
    private ValueExpression mootools= null;
    /**
     * This is a flag that indicates if the scripts openlayers must be loaded.
     */
    private ValueExpression openlayers= null;
    /**
     * This is a flag that indicates if we must load compressed scripts or uncompressed.
     */
    private ValueExpression minifyJS = null;

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
     * {@inheritDoc }
     */
    @Override
    protected void setProperties(final UIComponent component) {
        // always call the superclass method
        super.setProperties(component);

        final UIContext compContext = (UIContext) component;

        component.setValueExpression("service", service);
        component.setValueExpression("value", value);
        component.setValueExpression("styleClass", styleClass);
        component.setValueExpression("style", style);
        component.setValueExpression("debug", debug);
        component.setValueExpression("scriptaculous", scriptaculous);
        component.setValueExpression("mootools", mootools);
        component.setValueExpression("openlayers", openlayers);
        component.setValueExpression("minifyJS", minifyJS);
        
        //setting the flag to load or not the prototype and scriptaculous js libs
        if (scriptaculous != null) {
            compContext.setScriptaculous((Boolean) scriptaculous.getValue(FacesContext.getCurrentInstance().getELContext()));
        }

//        //setting the abstract model for the UIContext.
//        if (service != null) {
//            String fileUrl = service.getExpressionString();
//        } else {
//            throw new IllegalArgumentException("You must indicate a path to file to read");
//        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();

        value = null;
        styleClass = null;
        style = null;
        debug = null;
        scriptaculous = null;
        mootools = null;
        openlayers = null;
        minifyJS = null;
    }

    public void setValue(ValueExpression value) {
        this.value = value;
    }

    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    public void setStyle(ValueExpression style) {
        this.style = style;
    }

    public void setService(ValueExpression service) {
        this.service = service;
    }

    public ValueExpression getDebug() {
        return debug;
    }

    public void setDebug(ValueExpression debug) {
        this.debug = debug;
    }

    public ValueExpression getScriptaculous() {
        return scriptaculous;
    }

    public void setScriptaculous(ValueExpression scriptaculous) {
        this.scriptaculous = scriptaculous;
    }

    public ValueExpression getMootools() {
        return mootools;
    }

    public void setMootools(ValueExpression mootools) {
        this.mootools = mootools;
    }

    public ValueExpression getOpenlayers() {
        return openlayers;
    }

    public void setOpenlayers(ValueExpression openlayers) {
        this.openlayers = openlayers;
    }
    public ValueExpression getMinifyJS() {
        return minifyJS;
    }

    public void setMinifyJS(ValueExpression minifyJS) {
        this.minifyJS = minifyJS;
    }
}
