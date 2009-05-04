/*
 *    Mapfaces - http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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
package org.mapfaces.taglib.datatable;

import com.sun.faces.util.FacesLogger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;
import javax.servlet.jsp.JspException;
import org.mapfaces.component.datatable.UIColumns;

/**
 *
 * @author kdelfour
 */
public class ColumnsTag extends UIComponentELTag {

    public static final String COMP_TYPE = "org.mapfaces.datatable.Columns";
    /* Fields */
    private ValueExpression axis;

    /* Accessors */
    /**
     * @return the axis
     */
    public ValueExpression getAxis() {
        return axis;
    }

    /**
     * @param axis the axis to set
     */
    public void setAxis(ValueExpression axis) {
        this.axis = axis;
    }

    // Log instance for this class
    private static final Logger LOGGER = FacesLogger.TAGLIB.getLogger();

    //
    // Instance Variables
    //
    //
    // Setter Methods
    //
    // PROPERTY: footerClass
    private ValueExpression footerClass;

    public void setFooterClass(ValueExpression footerClass) {
        this.footerClass = footerClass;
    }

    // PROPERTY: headerClass
    private ValueExpression headerClass;

    public void setHeaderClass(ValueExpression headerClass) {
        this.headerClass = headerClass;
    }

    //
    // General Methods
    //
    @Override
    public String getRendererType() {
        return null;
    }

    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);

        if(!(component instanceof UIColumns)){
            throw new IllegalStateException("Component " + component.toString() + " not expected type.  Expected: UIColumn.  Perhaps you're missing a tag?");
        }

        final UIColumns column = (UIColumns) component;

        if (footerClass != null) {
            if (!footerClass.isLiteralText()) {
                column.setValueExpression("footerClass", footerClass);
            } else {
                column.getAttributes().put("footerClass", footerClass.getExpressionString());
            }
        }
        if (headerClass != null) {
            if (!headerClass.isLiteralText()) {
                column.setValueExpression("headerClass", headerClass);
            } else {
                column.getAttributes().put("headerClass", headerClass.getExpressionString());
            }
        }
        if (axis != null) {
            if (!axis.isLiteralText()) {
                column.setValueExpression("axis", axis);
            } else {
                column.getAttributes().put("axis", axis.getExpressionString());
            }
        }
    }

    //
    // Methods From TagSupport
    //
    @Override
    public int doStartTag() throws JspException {
        try {
            return super.doStartTag();
        } catch (JspException e) {
            if (LOGGER.isLoggable(Level.WARNING)) {
                LOGGER.log(Level.WARNING, getDebugString(), e);
            }
            throw e;
        } catch (Throwable t) {
            if (LOGGER.isLoggable(Level.WARNING)) {
                LOGGER.log(Level.WARNING, getDebugString(), t);
            }
            throw new JspException(t);
        }
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            return super.doEndTag();
        } catch (JspException e) {
            if (LOGGER.isLoggable(Level.WARNING)) {
                LOGGER.log(Level.WARNING, getDebugString(), e);
            }
            throw e;
        } catch (Throwable t) {
            if (LOGGER.isLoggable(Level.WARNING)) {
                LOGGER.log(Level.WARNING, getDebugString(), t);
            }
            throw new JspException(t);
        }
    }

    // RELEASE
    @Override
    public void release() {
        super.release();
        this.headerClass = null;
        this.footerClass = null;
        this.axis = null;
    }

    public String getDebugString() {
        return "id: " + this.getId() + " class: " + this.getClass().getName();
    }
}
