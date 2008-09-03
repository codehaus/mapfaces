package org.mapfaces.taglib.timeline;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

public class SliderTag extends UIComponentTag {

    public SliderTag() {
    }
    String For = null;
    String maxval = null;
    String horizontal = null;
    String length = null;

    @Override
    public String getComponentType() {
        // Associates tag with the UI component name registered in the faces-config.xml
        return "SliderInput";
    }

    @Override
    public String getRendererType() {
        // Since renderer is embedded in the component, can return null.
        return null;
    }

    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        // set For
        if (For != null) {
            if (isValueReference(For)) {
                FacesContext context = FacesContext.getCurrentInstance();
                Application app = context.getApplication();
                ValueBinding vb = app.createValueBinding(For);
                component.setValueBinding("for", vb);
            } else {
                component.getAttributes().put("for", For);
            }
        }

        // set maxval
        if (maxval != null) {
            if (isValueReference(maxval)) {
                FacesContext context = FacesContext.getCurrentInstance();
                Application app = context.getApplication();
                ValueBinding vb = app.createValueBinding(maxval);
                component.setValueBinding("maxval", vb);
            } else {
                component.getAttributes().put("maxval", maxval);
            }
        }

        // set horizontal
        if (horizontal != null) {
            if (isValueReference(horizontal)) {
                FacesContext context = FacesContext.getCurrentInstance();
                Application app = context.getApplication();
                ValueBinding vb = app.createValueBinding(horizontal);
                component.setValueBinding("horizontal", vb);
            } else {
                component.getAttributes().put("horizontal", horizontal);
            }
        }

        // set length
        if (length != null) {
            if (isValueReference(length)) {
                FacesContext context = FacesContext.getCurrentInstance();
                Application app = context.getApplication();
                ValueBinding vb = app.createValueBinding(horizontal);
                component.setValueBinding("length", vb);
            } else {
                component.getAttributes().put("length", length);
            }
        }

    }

    public void setFor(String For) {
        this.For = For;
    }

    public void setMaxval(String maxval) {
        this.maxval = maxval;
    }

    @Override
    public void release() {
        super.release();
        For = null;
        maxval = null;
        horizontal = null;
        length = null;
    }

    public void setHorizontal(String horizontal) {
        this.horizontal = horizontal;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
