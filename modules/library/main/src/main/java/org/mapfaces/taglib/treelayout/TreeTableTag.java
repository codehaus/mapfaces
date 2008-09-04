package org.mapfaces.taglib.treelayout;

import java.util.Map;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author kdelfour
 */
public class TreeTableTag extends UIComponentELTag {

    private ValueExpression value = null;
    private ValueExpression var = null;
    private ValueExpression width = null;
    private ValueExpression height = null;
    private ValueExpression debug = null;
    private final String TREETABLE_COMP_TYPE = "org.mapfaces.treelayout.TreeTable";
    private final String TREETABLE_RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLTreeTable";

    public ValueExpression getValue() {
        return value;
    }

    public void setValue(ValueExpression value) {
        this.value = value;
    }

    public ValueExpression getVar() {
        return var;
    }

    public void setVar(ValueExpression var) {
        if (var != null) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            Map requestMap = ec.getRequestMap();
            requestMap.put("org.treetable.varName", var);
        }
        this.var = var;
    }

    public ValueExpression getWidth() {
        return width;
    }

    public void setWidth(ValueExpression width) {
        this.width = width;
    }

    public ValueExpression getHeight() {
        return height;
    }

    public void setHeight(ValueExpression height) {
        this.height = height;
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
    public String getComponentType() {
        return TREETABLE_COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return TREETABLE_RENDERER_TYPE;
    }

    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("value", value);
        component.setValueExpression("var", var);
        component.setValueExpression("width", width);
        component.setValueExpression("height", height);
        component.setValueExpression("debug", getDebug());
    }

    @Override
    public void release() {
        super.release();
        setId(null);
        setValue(null);
        setVar(null);
        setWidth(null);
        setHeight(null);
        setDebug(null);
    }
}

