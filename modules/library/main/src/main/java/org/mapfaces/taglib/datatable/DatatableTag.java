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

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.webapp.UIComponentELTag;
import javax.servlet.jsp.JspException;

/**
 *
 * @author Kevin Delfour (IRD)
 */
public class DatatableTag extends UIComponentELTag {

    private static final String COMP_TYPE = "org.mapfaces.Datatable";
    private static final String RENDER_TYPE = "org.mapfaces.renderkit.HTMLDatatable";
    /* Fields */
    // Setter Methods
    // PROPERTY: overCls
    private ValueExpression overCls;

    public ValueExpression getOverCls() {
        return overCls;
    }

    public void setOverCls(ValueExpression overCls) {
        this.overCls = overCls;
    }

    // PROPERTY: sortOn
    private ValueExpression sortOn;

    public ValueExpression getSortOn() {
        return sortOn;
    }

    public void setSortOn(ValueExpression sortOn) {
        this.sortOn = sortOn;
    }

    //PROPERTY: sortBy
    private ValueExpression sortBy;

    public ValueExpression getSortBy() {
        return sortBy;
    }

    public void setSortBy(ValueExpression sortBy) {
        this.sortBy = sortBy;
    }

    //PROPERTY; scrolling
    private ValueExpression scrolling;

    public ValueExpression getScrolling() {
        return scrolling;
    }

    public void setScrolling(ValueExpression scrolling) {
        this.scrolling = scrolling;
    }

    //PROPERTY: sortable
    private ValueExpression sortable;

    public ValueExpression getSortable() {
        return sortable;
    }

    public void setSortable(ValueExpression sortable) {
        this.sortable = sortable;
    }
    // PROPERTY: first
    private ValueExpression first;

    public void setFirst(ValueExpression first) {
        this.first = first;
    }

    // PROPERTY: rows
    private ValueExpression rows;

    public void setRows(ValueExpression rows) {
        this.rows = rows;
    }

    // PROPERTY: value
    private ValueExpression value;

    public void setValue(ValueExpression value) {
        this.value = value;
    }

    // PROPERTY: var
    private java.lang.String _var;

    public void setVar(java.lang.String _var) {
        this._var = _var;
    }

    // PROPERTY: bgcolor
    private ValueExpression bgcolor;

    public void setBgcolor(ValueExpression bgcolor) {
        this.bgcolor = bgcolor;
    }

    // PROPERTY: border
    private ValueExpression border;

    public void setBorder(ValueExpression border) {
        this.border = border;
    }

    // PROPERTY: captionClass
    private ValueExpression captionClass;

    public void setCaptionClass(ValueExpression captionClass) {
        this.captionClass = captionClass;
    }

    // PROPERTY: captionStyle
    private ValueExpression captionStyle;

    public void setCaptionStyle(ValueExpression captionStyle) {
        this.captionStyle = captionStyle;
    }

    // PROPERTY: cellpadding
    private ValueExpression cellpadding;

    public void setCellpadding(ValueExpression cellpadding) {
        this.cellpadding = cellpadding;
    }

    // PROPERTY: cellspacing
    private ValueExpression cellspacing;

    public void setCellspacing(ValueExpression cellspacing) {
        this.cellspacing = cellspacing;
    }

    // PROPERTY: columnClasses
    private ValueExpression columnClasses;

    public void setColumnClasses(ValueExpression columnClasses) {
        this.columnClasses = columnClasses;
    }

    // PROPERTY: dir
    private ValueExpression dir;

    public void setDir(ValueExpression dir) {
        this.dir = dir;
    }

    // PROPERTY: footerClass
    private ValueExpression footerClass;

    public void setFooterClass(ValueExpression footerClass) {
        this.footerClass = footerClass;
    }

    // PROPERTY: frame
    private ValueExpression frame;

    public void setFrame(ValueExpression frame) {
        this.frame = frame;
    }

    // PROPERTY: headerClass
    private ValueExpression headerClass;

    public void setHeaderClass(ValueExpression headerClass) {
        this.headerClass = headerClass;
    }

    // PROPERTY: lang
    private ValueExpression lang;

    public void setLang(ValueExpression lang) {
        this.lang = lang;
    }

    // PROPERTY: onclick
    private ValueExpression onclick;

    public void setOnclick(ValueExpression onclick) {
        this.onclick = onclick;
    }

    // PROPERTY: ondblclick
    private ValueExpression ondblclick;

    public void setOndblclick(ValueExpression ondblclick) {
        this.ondblclick = ondblclick;
    }

    // PROPERTY: onkeydown
    private ValueExpression onkeydown;

    public void setOnkeydown(ValueExpression onkeydown) {
        this.onkeydown = onkeydown;
    }

    // PROPERTY: onkeypress
    private ValueExpression onkeypress;

    public void setOnkeypress(ValueExpression onkeypress) {
        this.onkeypress = onkeypress;
    }

    // PROPERTY: onkeyup
    private ValueExpression onkeyup;

    public void setOnkeyup(ValueExpression onkeyup) {
        this.onkeyup = onkeyup;
    }

    // PROPERTY: onmousedown
    private ValueExpression onmousedown;

    public void setOnmousedown(ValueExpression onmousedown) {
        this.onmousedown = onmousedown;
    }

    // PROPERTY: onmousemove
    private ValueExpression onmousemove;

    public void setOnmousemove(ValueExpression onmousemove) {
        this.onmousemove = onmousemove;
    }

    // PROPERTY: onmouseout
    private ValueExpression onmouseout;

    public void setOnmouseout(ValueExpression onmouseout) {
        this.onmouseout = onmouseout;
    }

    // PROPERTY: onmouseover
    private ValueExpression onmouseover;

    public void setOnmouseover(ValueExpression onmouseover) {
        this.onmouseover = onmouseover;
    }

    // PROPERTY: onmouseup
    private ValueExpression onmouseup;

    public void setOnmouseup(ValueExpression onmouseup) {
        this.onmouseup = onmouseup;
    }

    // PROPERTY: rowClasses
    private ValueExpression rowClasses;

    public void setRowClasses(ValueExpression rowClasses) {
        this.rowClasses = rowClasses;
    }

    // PROPERTY: rules
    private ValueExpression rules;

    public void setRules(ValueExpression rules) {
        this.rules = rules;
    }

    // PROPERTY: style
    private ValueExpression style;

    public void setStyle(ValueExpression style) {
        this.style = style;
    }

    // PROPERTY: styleClass
    private ValueExpression styleClass;

    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }

    // PROPERTY: summary
    private ValueExpression summary;

    public void setSummary(ValueExpression summary) {
        this.summary = summary;
    }

    // PROPERTY: title
    private ValueExpression title;

    public void setTitle(ValueExpression title) {
        this.title = title;
    }

    // PROPERTY: width
    private ValueExpression width;

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

        if(!(component instanceof UIData)){
            throw new IllegalStateException("Component " + component.toString() + " not expected type.  Expected: javax.faces.component.UIData.  Perhaps you're missing a tag?");
        }


        final UIData data = (UIData) component;

        if (first != null) {
            data.setValueExpression("first", first);
        }

        if (rows != null) {
            data.setValueExpression("rows", rows);
        }

        if (value != null) {
            data.setValueExpression("value", value);
        }

        data.setVar(_var);
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
        if (cellpadding != null) {
            data.setValueExpression("cellpadding", cellpadding);
        }
        if (cellspacing != null) {
            data.setValueExpression("cellspacing", cellspacing);
        }
        if (columnClasses != null) {
            data.setValueExpression("columnClasses", columnClasses);
        }
        if (dir != null) {
            data.setValueExpression("dir", dir);
        }
        if (footerClass != null) {
            data.setValueExpression("footerClass", footerClass);
        }
        if (frame != null) {
            data.setValueExpression("frame", frame);
        }
        if (headerClass != null) {
            data.setValueExpression("headerClass", headerClass);
        }
        if (lang != null) {
            data.setValueExpression("lang", lang);
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
        if (rules != null) {
            data.setValueExpression("rules", rules);
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
        if (title != null) {
            data.setValueExpression("title", title);
        }
        if (width != null) {
            data.setValueExpression("width", width);
        }
        if (overCls != null) {
            component.setValueExpression("overCls", getOverCls());
        }
        if (sortOn != null) {
            component.setValueExpression("sortOn", getSortOn());
        }
        if (sortBy != null) {
            component.setValueExpression("sortBy", getSortBy());
        }
        if (scrolling != null) {
            component.setValueExpression("scrolling", getScrolling());
        }
        if (sortable != null) {
            component.setValueExpression("sortable", getSortable());
        }
    }

    // Methods From TagSupport
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

    // RELEASE
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
        this.cellpadding = null;
        this.cellspacing = null;
        this.columnClasses = null;
        this.dir = null;
        this.footerClass = null;
        this.frame = null;
        this.headerClass = null;
        this.lang = null;
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
        this.rules = null;
        this.style = null;
        this.styleClass = null;
        this.summary = null;
        this.title = null;
        this.width = null;

        this.overCls = null;
        this.sortOn = null;
        this.sortBy = null;
        this.scrolling = null;
        this.sortable = null;
    }

    public String getDebugString() {
        return "id: " + this.getId() + " class: " + this.getClass().getName();
    }
}
