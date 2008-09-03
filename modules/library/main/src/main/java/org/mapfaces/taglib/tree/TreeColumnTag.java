package org.mapfaces.taglib.tree;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author kdelfour
 */
public class TreeColumnTag extends UIComponentELTag {

    private ValueExpression header = null;
    private ValueExpression value = null;
    private ValueExpression width = null;
    private static final String TREECOLUMN_COMP_TYPE = "org.mapfaces.treetable.treepanel.TreeColumn";
    private static final String TREECOLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.treepanel.HTMLTreeColumn";

    public ValueExpression getHeader() {
        return header;
    }

    public void setHeader(ValueExpression aHeader) {
        header = aHeader;
    }

    public ValueExpression getWidth() {
        return width;
    }

    public void setWidth(ValueExpression Width) {
        width = Width;
    }

    public ValueExpression getValue() {
        return value;
    }

    public void setValue(ValueExpression value) {
        this.value = value;
    }

    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("header", getHeader());
        component.setValueExpression("value", getValue());
        component.setValueExpression("width", getWidth());
    }

    @Override
    public void release() {
        super.release();
        setHeader(null);
        setValue(null);
        setWidth(null);
    }

    @Override
    public String getComponentType() {
        return TREECOLUMN_COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return TREECOLUMN_RENDERER_TYPE;
    }
}
