package org.mapfaces.taglib.tree;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author kdelfour
 */
public class CheckColumnTag extends UIComponentELTag {

    private ValueExpression header = null;
    private ValueExpression icon = null;
    private ValueExpression value = null;
    private ValueExpression width = null;
    private ValueExpression debug = null;
    private static final String COLUMN_COMP_TYPE = "org.mapfaces.treetable.treepanel.CheckColumn";
    private static final String COLUMN_RENDERER_TYPE = "org.mapfaces.renderkit.treetable.treepanel.HTMLCheckColumn";

    public ValueExpression getHeader() {
        return header;
    }

    /**
     * 
     * @param header
     */
    public void setHeader(ValueExpression header) {
        this.header = header;
    }

    public ValueExpression getValue() {
        return value;
    }

    public void setValue(ValueExpression value) {
        this.value = value;
    }

    public ValueExpression getIcon() {
        return icon;
    }

    public void setIcon(ValueExpression icon) {
        this.icon = icon;
    }

    public ValueExpression getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     */
    public void setWidth(ValueExpression width) {
        this.width = width;
    }

    /**
     * @return the debug
     */
    public ValueExpression getDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(ValueExpression debug) {
        this.debug = debug;
    }

    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("header", getHeader());
        component.setValueExpression("icon", getIcon());
        component.setValueExpression("value", getValue());
        component.setValueExpression("width", getWidth());
        component.setValueExpression("debug", getDebug());
    }

    @Override
    public void release() {
        super.release();
        setValue(null);
        setIcon(null);
        setHeader(null);
        setWidth(null);
        setDebug(null);
    }

    @Override
    public String getComponentType() {
        return COLUMN_COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return COLUMN_RENDERER_TYPE;
    }
}
