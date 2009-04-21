/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
    private static final Logger logger = FacesLogger.TAGLIB.getLogger();

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
        UIColumns column;

        try {
            column = (UIColumns) component;
        } catch (ClassCastException cce) {
            throw new IllegalStateException("Component " + component.toString() + " not expected type.  Expected: UIColumn.  Perhaps you're missing a tag?");
        }
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
            if (logger.isLoggable(Level.WARNING)) {
                logger.log(Level.WARNING, getDebugString(), e);
            }
            throw e;
        } catch (Throwable t) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.log(Level.WARNING, getDebugString(), t);
            }
            throw new JspException(t);
        }
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            return super.doEndTag();
        } catch (JspException e) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.log(Level.WARNING, getDebugString(), e);
            }
            throw e;
        } catch (Throwable t) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.log(Level.WARNING, getDebugString(), t);
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
        return "id: " + this.getId() + " class: " +
                this.getClass().getName();
    }
}
