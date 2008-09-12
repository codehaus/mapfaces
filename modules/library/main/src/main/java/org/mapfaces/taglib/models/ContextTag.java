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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentELTag;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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
     * The Context string value.
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

    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return RENDER_TYPE;
    }

    @Override
    protected void setProperties(UIComponent component) {
        // always call the superclass method
        super.setProperties(component);

        UIContext compContext = (UIContext) component;
        FacesContext context = FacesContext.getCurrentInstance();

        component.setValueExpression("service", service);
        component.setValueExpression("value", value);
        component.setValueExpression("styleClass", styleClass);
        component.setValueExpression("style", style);
        component.setValueExpression("debug", debug);

        //setting the abstract model for the UIContext.
        if (service != null) {
            String fileUrl = service.getExpressionString();
            // Unmarshalles the given XML file to objects
            JAXBContext Jcontext;
            try {
                Jcontext = JAXBContext.newInstance("net.opengis.owc.v030:net.opengis.context.v110");
                Unmarshaller unmarshaller = Jcontext.createUnmarshaller();
                ServletContext sc = (ServletContext) context.getExternalContext().getContext();
                JAXBElement elt = (JAXBElement) unmarshaller.unmarshal(new FileReader(sc.getRealPath(fileUrl)));
                compContext.setJAXBElt(elt);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ContextTag.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JAXBException ex) {
                Logger.getLogger(ContextTag.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            throw new IllegalArgumentException("You must indicate a path to file to read");
        }
    }

    @Override
    public void release() {
        // allways call the superclass method
        super.release();

        value = null;
        styleClass = null;
        style = null;
        debug = null;
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
}
