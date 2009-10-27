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

package org.mapfaces.component.html;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.context.FacesContext;

import javax.el.ValueExpression;
import org.mapfaces.component.treetable.UITreeData;

public class HtmlTreeTable extends UITreeData {

    private static final String FAMILY = "org.mapfaces.component.tree.HtmlTreetable";
    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.tree.HtmlTreetable";
    private Integer border;
    private String bgcolor;
    private String captionClass;
    private String captionStyle;
    private String columnClasses;
    private String footerClass;
    private String headerClass;
    private String onclick;
    private String ondblclick;
    private String onkeydown;
    private String onkeypress;
    private String onkeyup;
    private String onmousedown;
    private String onmousemove;
    private String onmouseout;
    private String onmouseover;
    private String onmouseup;
    private String rowClasses;
    private String style;
    private String styleClass;
    private String summary;
    private String width;

    private static final String[] OPTIMIZED_PACKAGES = {
        "javax.faces.component",
        "javax.faces.component.html"
    };

    public HtmlTreeTable() {
        super();
    }

    /**
     * <p>Return the value of the <code>bgcolor</code> property.</p>
     * <p>Contents: Name or code of the background color for this table.
     */
    public String getBgcolor() {
        if (null != this.bgcolor) {
            return this.bgcolor;
        }
        ValueExpression _ve = getValueExpression("bgcolor");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>bgcolor</code> property.</p>
     */
    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
        handleAttribute("bgcolor", bgcolor);
    }

    /**
     * <p>Return the value of the <code>border</code> property.</p>
     * <p>Contents: Width (in pixels) of the border to be drawn
     * around this table.
     */
    public int getBorder() {
        if (null != this.border) {
            return this.border;
        }
        ValueExpression _ve = getValueExpression("border");
        if (_ve != null) {
            return (java.lang.Integer) _ve.getValue(getFacesContext().getELContext());
        } else {
            return Integer.MIN_VALUE;
        }
    }

    /**
     * <p>Set the value of the <code>border</code> property.</p>
     */
    public void setBorder(int border) {
        this.border = border;
        handleAttribute("border", border);
    }

    /**
     * <p>Return the value of the <code>captionClass</code> property.</p>
     * <p>Contents: Space-separated list of CSS style class(es) that will be
     * applied to any caption generated for this table.
     */
    public String getCaptionClass() {
        if (null != this.captionClass) {
            return this.captionClass;
        }
        ValueExpression _ve = getValueExpression("captionClass");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>captionClass</code> property.</p>
     */
    public void setCaptionClass(String captionClass) {
        this.captionClass = captionClass;
    }

    /**
     * <p>Return the value of the <code>captionStyle</code> property.</p>
     * <p>Contents: CSS style(s) to be applied when this caption is rendered.
     */
    public String getCaptionStyle() {
        if (null != this.captionStyle) {
            return this.captionStyle;
        }
        ValueExpression _ve = getValueExpression("captionStyle");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>captionStyle</code> property.</p>
     */
    public void setCaptionStyle(String captionStyle) {
        this.captionStyle = captionStyle;
    }

    /**
     * <p>Return the value of the <code>columnClasses</code> property.</p>
     * <p>Contents: Comma-delimited list of CSS style classes that will be applied
     * to the columns of this table.  A space separated list of
     * classes may also be specified for any individual column.  If
     * the number of elements in this list is less than the number of
     * columns specified in the "columns" attribute, no "class"
     * attribute is output for each column greater than the number of
     * elements in the list.  If the number of elements in the list
     * is greater than the number of columns specified in the
     * "columns" attribute, the elements at the posisiton in the list
     * after the value of the "columns" attribute are ignored.
     */
    public String getColumnClasses() {
        if (null != this.columnClasses) {
            return this.columnClasses;
        }
        ValueExpression _ve = getValueExpression("columnClasses");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>columnClasses</code> property.</p>
     */
    public void setColumnClasses(String columnClasses) {
        this.columnClasses = columnClasses;
    }

    /**
     * <p>Return the value of the <code>footerClass</code> property.</p>
     * <p>Contents: Space-separated list of CSS style class(es) that will be
     * applied to any footer generated for this table.
     */
    public String getFooterClass() {
        if (null != this.footerClass) {
            return this.footerClass;
        }
        ValueExpression _ve = getValueExpression("footerClass");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>footerClass</code> property.</p>
     */
    public void setFooterClass(String footerClass) {
        this.footerClass = footerClass;
    }

    /**
     * <p>Return the value of the <code>headerClass</code> property.</p>
     * <p>Contents: Space-separated list of CSS style class(es) that will be
     * applied to any header generated for this table.
     */
    public String getHeaderClass() {
        if (null != this.headerClass) {
            return this.headerClass;
        }
        ValueExpression _ve = getValueExpression("headerClass");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>headerClass</code> property.</p>
     */
    public void setHeaderClass(String headerClass) {
        this.headerClass = headerClass;
    }

    /**
     * <p>Return the value of the <code>onclick</code> property.</p>
     * <p>Contents: Javascript code executed when a pointer button is
     * clicked over this element.
     */
    public String getOnclick() {
        if (null != this.onclick) {
            return this.onclick;
        }
        ValueExpression _ve = getValueExpression("onclick");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>onclick</code> property.</p>
     */
    public void setOnclick(String onclick) {
        this.onclick = onclick;
        handleAttribute("onclick", onclick);
    }

    /**
     * <p>Return the value of the <code>ondblclick</code> property.</p>
     * <p>Contents: Javascript code executed when a pointer button is
     * double clicked over this element.
     */
    public String getOndblclick() {
        if (null != this.ondblclick) {
            return this.ondblclick;
        }
        ValueExpression _ve = getValueExpression("ondblclick");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>ondblclick</code> property.</p>
     */
    public void setOndblclick(String ondblclick) {
        this.ondblclick = ondblclick;
        handleAttribute("ondblclick", ondblclick);
    }

    /**
     * <p>Return the value of the <code>onkeydown</code> property.</p>
     * <p>Contents: Javascript code executed when a key is
     * pressed down over this element.
     */
    public String getOnkeydown() {
        if (null != this.onkeydown) {
            return this.onkeydown;
        }
        ValueExpression _ve = getValueExpression("onkeydown");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>onkeydown</code> property.</p>
     */
    public void setOnkeydown(String onkeydown) {
        this.onkeydown = onkeydown;
        handleAttribute("onkeydown", onkeydown);
    }

    /**
     * <p>Return the value of the <code>onkeypress</code> property.</p>
     * <p>Contents: Javascript code executed when a key is
     * pressed and released over this element.
     */
    public String getOnkeypress() {
        if (null != this.onkeypress) {
            return this.onkeypress;
        }
        ValueExpression _ve = getValueExpression("onkeypress");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>onkeypress</code> property.</p>
     */
    public void setOnkeypress(String onkeypress) {
        this.onkeypress = onkeypress;
        handleAttribute("onkeypress", onkeypress);
    }

    /**
     * <p>Return the value of the <code>onkeyup</code> property.</p>
     * <p>Contents: Javascript code executed when a key is
     * released over this element.
     */
    public String getOnkeyup() {
        if (null != this.onkeyup) {
            return this.onkeyup;
        }
        ValueExpression _ve = getValueExpression("onkeyup");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>onkeyup</code> property.</p>
     */
    public void setOnkeyup(String onkeyup) {
        this.onkeyup = onkeyup;
        handleAttribute("onkeyup", onkeyup);
    }

    /**
     * <p>Return the value of the <code>onmousedown</code> property.</p>
     * <p>Contents: Javascript code executed when a pointer button is
     * pressed down over this element.
     */
    public String getOnmousedown() {
        if (null != this.onmousedown) {
            return this.onmousedown;
        }
        ValueExpression _ve = getValueExpression("onmousedown");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>onmousedown</code> property.</p>
     */
    public void setOnmousedown(String onmousedown) {
        this.onmousedown = onmousedown;
        handleAttribute("onmousedown", onmousedown);
    }

    /**
     * <p>Return the value of the <code>onmousemove</code> property.</p>
     * <p>Contents: Javascript code executed when a pointer button is
     * moved within this element.
     */
    public String getOnmousemove() {
        if (null != this.onmousemove) {
            return this.onmousemove;
        }
        ValueExpression _ve = getValueExpression("onmousemove");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>onmousemove</code> property.</p>
     */
    public void setOnmousemove(String onmousemove) {
        this.onmousemove = onmousemove;
        handleAttribute("onmousemove", onmousemove);
    }

    /**
     * <p>Return the value of the <code>onmouseout</code> property.</p>
     * <p>Contents: Javascript code executed when a pointer button is
     * moved away from this element.
     */
    public String getOnmouseout() {
        if (null != this.onmouseout) {
            return this.onmouseout;
        }
        ValueExpression _ve = getValueExpression("onmouseout");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>onmouseout</code> property.</p>
     */
    public void setOnmouseout(String onmouseout) {
        this.onmouseout = onmouseout;
        handleAttribute("onmouseout", onmouseout);
    }

    /**
     * <p>Return the value of the <code>onmouseover</code> property.</p>
     * <p>Contents: Javascript code executed when a pointer button is
     * moved onto this element.
     */
    public String getOnmouseover() {
        if (null != this.onmouseover) {
            return this.onmouseover;
        }
        ValueExpression _ve = getValueExpression("onmouseover");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>onmouseover</code> property.</p>
     */
    public void setOnmouseover(String onmouseover) {
        this.onmouseover = onmouseover;
        handleAttribute("onmouseover", onmouseover);
    }

    /**
     * <p>Return the value of the <code>onmouseup</code> property.</p>
     * <p>Contents: Javascript code executed when a pointer button is
     * released over this element.
     */
    public String getOnmouseup() {
        if (null != this.onmouseup) {
            return this.onmouseup;
        }
        ValueExpression _ve = getValueExpression("onmouseup");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>onmouseup</code> property.</p>
     */
    public void setOnmouseup(String onmouseup) {
        this.onmouseup = onmouseup;
        handleAttribute("onmouseup", onmouseup);
    }

    /**
     * <p>Return the value of the <code>rowClasses</code> property.</p>
     * <p>Contents: Comma-delimited list of CSS style classes that will be applied
     * to the rows of this table.  A space separated list of classes
     * may also be specified for any individual row.  Thes styles are
     * applied, in turn, to each row in the table.  For example, if
     * the list has two elements, the first style class in the list
     * is applied to the first row, the second to the second row, the
     * first to the third row, the second to the fourth row, etc.  In
     * other words, we keep iterating through the list until we reach
     * the end, and then we start at the beginning again.
     */
    public String getRowClasses() {
        if (null != this.rowClasses) {
            return this.rowClasses;
        }
        ValueExpression _ve = getValueExpression("rowClasses");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>rowClasses</code> property.</p>
     */
    public void setRowClasses(String rowClasses) {
        this.rowClasses = rowClasses;
    }

    /**
     * <p>Return the value of the <code>style</code> property.</p>
     * <p>Contents: CSS style(s) to be applied when this component is rendered.
     */
    public String getStyle() {
        if (null != this.style) {
            return this.style;
        }
        ValueExpression _ve = getValueExpression("style");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>style</code> property.</p>
     */
    public void setStyle(String style) {
        this.style = style;
        handleAttribute("style", style);
    }

    /**
     * <p>Return the value of the <code>styleClass</code> property.</p>
     * <p>Contents: Space-separated list of CSS style class(es) to be applied when
     * this element is rendered.  This value must be passed through
     * as the "class" attribute on generated markup.
     */
    public String getStyleClass() {
        if (null != this.styleClass) {
            return this.styleClass;
        }
        ValueExpression _ve = getValueExpression("styleClass");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>styleClass</code> property.</p>
     */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * <p>Return the value of the <code>summary</code> property.</p>
     * <p>Contents: Summary of this table's purpose and structure, for
     * user agents rendering to non-visual media such as
     * speech and Braille.
     */
    public String getSummary() {
        if (null != this.summary) {
            return this.summary;
        }
        ValueExpression _ve = getValueExpression("summary");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>summary</code> property.</p>
     */
    public void setSummary(String summary) {
        this.summary = summary;
        handleAttribute("summary", summary);
    }

    /**
     * <p>Return the value of the <code>width</code> property.</p>
     * <p>Contents: Width of the entire table, for visual user agents.
     */
    public String getWidth() {
        if (null != this.width) {
            return this.width;
        }
        ValueExpression _ve = getValueExpression("width");
        if (_ve != null) {
            return (String) _ve.getValue(getFacesContext().getELContext());
        } else {
            return null;
        }
    }

    /**
     * <p>Set the value of the <code>width</code> property.</p>
     */
    public void setWidth(String width) {
        this.width = width;
        handleAttribute("width", width);
    }
    private Object[] values;

    @Override
    public Object saveState(FacesContext _context) {
        if (values == null) {
            values = new Object[23];
        }
        values[0] = super.saveState(_context);
        values[1] = bgcolor;
        values[2] = border;
        values[3] = captionClass;
        values[4] = captionStyle;
        values[5] = columnClasses;
        values[6] = footerClass;
        values[7] = headerClass;
        values[8] = onclick;
        values[9] = ondblclick;
        values[10] = onkeydown;
        values[11] = onkeypress;
        values[12] = onkeyup;
        values[13] = onmousedown;
        values[14] = onmousemove;
        values[15] = onmouseout;
        values[16] = onmouseover;
        values[17] = onmouseup;
        values[18] = rowClasses;
        values[19] = style;
        values[20] = styleClass;
        values[21] = summary;
        values[22] = width;
        return values;
    }

    @Override
    public void restoreState(FacesContext _context, Object _state) {
        values = (Object[]) _state;
        super.restoreState(_context, values[0]);
        this.bgcolor = (String) values[1];
        this.border = (java.lang.Integer) values[2];
        this.captionClass = (String) values[3];
        this.captionStyle = (String) values[4];
        this.columnClasses = (String) values[5];
        this.footerClass = (String) values[6];
        this.headerClass = (String) values[7];
        this.onclick = (String) values[8];
        this.ondblclick = (String) values[9];
        this.onkeydown = (String) values[10];
        this.onkeypress = (String) values[11];
        this.onkeyup = (String) values[12];
        this.onmousedown = (String) values[13];
        this.onmousemove = (String) values[14];
        this.onmouseout = (String) values[15];
        this.onmouseover = (String) values[16];
        this.onmouseup = (String) values[17];
        this.rowClasses = (String) values[18];
        this.style = (String) values[19];
        this.styleClass = (String) values[20];
        this.summary = (String) values[21];
        this.width = (String) values[22];
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    private void handleAttribute(String name, Object value) {
        List<String> setAttributes = null;
        String pkg = this.getClass().getPackage().getName();
        if (Arrays.binarySearch(OPTIMIZED_PACKAGES, pkg) >= 0) {
            setAttributes = (List<String>) this.getAttributes().get("javax.faces.component.UIComponentBase.attributesThatAreSet");
            if (setAttributes == null) {
                setAttributes = new ArrayList<String>(6);
                this.getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", setAttributes);
            }
            if (value == null) {
                setAttributes.remove(name);
            } else if (!setAttributes.contains(name)) {
                setAttributes.add(name);
            }
        }
    }
}
