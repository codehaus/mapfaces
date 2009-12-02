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

package org.mapfaces.taglib.treetable;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;
import javax.servlet.jsp.JspException;
import org.mapfaces.component.treetable.UITreeData;

/**
 *
 * @author kevindelfour
 */
public class HtmlTreeTableTag extends UIComponentELTag {

    private static final String COMP_TYPE = "org.mapfaces.component.tree.HtmlTreeTable";
    private static final String RENDER_TYPE = "org.mapfaces.renderkit..tree.HtmlTreeTable";
    // PROPERTIES
    private ValueExpression first;
    private ValueExpression rows;
    private ValueExpression value;
    private String _var;
    private ValueExpression bgcolor;
    private ValueExpression border;
    private ValueExpression captionClass;
    private ValueExpression captionStyle;
    private ValueExpression columnClasses;
    private ValueExpression footerClass;
    private ValueExpression headerClass;
    private ValueExpression onclick;
    private ValueExpression ondblclick;
    private ValueExpression onkeydown;
    private ValueExpression onkeypress;
    private ValueExpression onkeyup;
    private ValueExpression onmousedown;
    private ValueExpression onmousemove;
    private ValueExpression onmouseout;
    private ValueExpression onmouseover;
    private ValueExpression onmouseup;
    private ValueExpression rowClasses;
    private ValueExpression style;
    private ValueExpression styleClass;
    private ValueExpression summary;
    private ValueExpression width;

    public void setFirst(ValueExpression first) {
        this.first = first;
    }

    public void setRows(ValueExpression rows) {
        this.rows = rows;
    }

    public void setValue(ValueExpression value) {
        this.value = value;
    }

    public void setVar(java.lang.String _var) {
        this._var = _var;
    }

    public void setBgcolor(ValueExpression bgcolor) {
        this.bgcolor = bgcolor;
    }

    public void setBorder(ValueExpression border) {
        this.border = border;
    }

    public void setCaptionClass(ValueExpression captionClass) {
        this.captionClass = captionClass;
    }

    public void setCaptionStyle(ValueExpression captionStyle) {
        this.captionStyle = captionStyle;
    }

    public void setColumnClasses(ValueExpression columnClasses) {
        this.columnClasses = columnClasses;
    }

    public void setFooterClass(ValueExpression footerClass) {
        this.footerClass = footerClass;
    }

    public void setHeaderClass(ValueExpression headerClass) {
        this.headerClass = headerClass;
    }

    public void setOnclick(ValueExpression onclick) {
        this.onclick = onclick;
    }

    public void setOndblclick(ValueExpression ondblclick) {
        this.ondblclick = ondblclick;
    }

    public void setOnkeydown(ValueExpression onkeydown) {
        this.onkeydown = onkeydown;
    }

    public void setOnkeypress(ValueExpression onkeypress) {
        this.onkeypress = onkeypress;
    }

    public void setOnkeyup(ValueExpression onkeyup) {
        this.onkeyup = onkeyup;
    }

    public void setOnmousedown(ValueExpression onmousedown) {
        this.onmousedown = onmousedown;
    }

    public void setOnmousemove(ValueExpression onmousemove) {
        this.onmousemove = onmousemove;
    }

    public void setOnmouseout(ValueExpression onmouseout) {
        this.onmouseout = onmouseout;
    }

    public void setOnmouseover(ValueExpression onmouseover) {
        this.onmouseover = onmouseover;
    }

    public void setOnmouseup(ValueExpression onmouseup) {
        this.onmouseup = onmouseup;
    }

    public void setRowClasses(ValueExpression rowClasses) {
        this.rowClasses = rowClasses;
    }

    public void setStyle(ValueExpression style) {
        this.style = style;
    }

    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    public void setSummary(ValueExpression summary) {
        this.summary = summary;
    }

    public void setWidth(ValueExpression width) {
        this.width = width;
    }

    @Override
    // General Methods
    public String getRendererType() {
        return RENDER_TYPE;
    }

    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);

        if (!(component instanceof UITreeData)) {
            throw new IllegalStateException("Component " + component.toString() + " not expected type.  Expected: org.mapfaces.components.treetable.UITreeData.  Perhaps you're missing a tag?");
        }

        final UITreeData data = (UITreeData) component;
        data.setVar(_var);

        if (first != null) {
            data.setValueExpression("first", first);
        }

        if (rows != null) {
            data.setValueExpression("rows", rows);
        }

        if (value != null) {
            data.setValueExpression("value", value);
        }

        if (bgcolor != null) {
            data.setValueExpression("bgcolor", bgcolor);
        }
        if (border != null) {
            data.setValueExpression("border", border);
        }
        if (captionClass != null) {
            data.setValueExpression("captionClass", captionClass);
        }
        if (captionStyle != null) {
            data.setValueExpression("captionStyle", captionStyle);
        }
        if (columnClasses != null) {
            data.setValueExpression("columnClasses", columnClasses);
        }
        if (footerClass != null) {
            data.setValueExpression("footerClass", footerClass);
        }
        if (headerClass != null) {
            data.setValueExpression("headerClass", headerClass);
        }
        if (onclick != null) {
            data.setValueExpression("onclick", onclick);
        }
        if (ondblclick != null) {
            data.setValueExpression("ondblclick", ondblclick);
        }
        if (onkeydown != null) {
            data.setValueExpression("onkeydown", onkeydown);
        }
        if (onkeypress != null) {
            data.setValueExpression("onkeypress", onkeypress);
        }
        if (onkeyup != null) {
            data.setValueExpression("onkeyup", onkeyup);
        }
        if (onmousedown != null) {
            data.setValueExpression("onmousedown", onmousedown);
        }
        if (onmousemove != null) {
            data.setValueExpression("onmousemove", onmousemove);
        }
        if (onmouseout != null) {
            data.setValueExpression("onmouseout", onmouseout);
        }
        if (onmouseover != null) {
            data.setValueExpression("onmouseover", onmouseover);
        }
        if (onmouseup != null) {
            data.setValueExpression("onmouseup", onmouseup);
        }
        if (rowClasses != null) {
            data.setValueExpression("rowClasses", rowClasses);
        }
        if (style != null) {
            data.setValueExpression("style", style);
        }
        if (styleClass != null) {
            data.setValueExpression("styleClass", styleClass);
        }
        if (summary != null) {
            data.setValueExpression("summary", summary);
        }
        if (width != null) {
            data.setValueExpression("width", width);
        }
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            return super.doStartTag();
        } catch (Exception e) {
            Throwable root = e;
            while (root.getCause() != null) {
                root = root.getCause();
            }
            throw new JspException(root);
        }
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            return super.doEndTag();
        } catch (Exception e) {
            Throwable root = e;
            while (root.getCause() != null) {
                root = root.getCause();
            }
            throw new JspException(root);
        }
    }

    @Override
    public void release() {
        super.release();

        // component properties
        this.first = null;
        this.rows = null;
        this.value = null;
        this._var = null;

        // rendered attributes
        this.bgcolor = null;
        this.border = null;
        this.captionClass = null;
        this.captionStyle = null;
        this.columnClasses = null;
        this.footerClass = null;
        this.headerClass = null;
        this.onclick = null;
        this.ondblclick = null;
        this.onkeydown = null;
        this.onkeypress = null;
        this.onkeyup = null;
        this.onmousedown = null;
        this.onmousemove = null;
        this.onmouseout = null;
        this.onmouseover = null;
        this.onmouseup = null;
        this.rowClasses = null;
        this.style = null;
        this.styleClass = null;
        this.summary = null;
        this.width = null;
    }

    public String getDebugString() {
        return "id: " + this.getId() + " class: " + this.getClass().getName();
    }
}
