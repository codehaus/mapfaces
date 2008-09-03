package org.mapfaces.taglib.treelayout;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author kdelfour
 */
public class TreeNodeInfoTag extends UIComponentELTag {

    private ValueExpression header = null;
    private ValueExpression hide = null;
    
    private final String TREENODEINFO_COMP_TYPE = "org.mapfaces.treelayout.treetable.treepanel.TreeNodeInfo";
    private final String TREENODEINFO_RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.treetable.treepanel.HTMLTreeNodeInfo";

    public ValueExpression getHeader() {
        return header;
    }

    public void setHeader(ValueExpression header) {
        this.header = header;
    }

    public ValueExpression getHide() {
        return hide;
    }

    public void setHide(ValueExpression hide) {
        this.hide = hide;
    }

    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("header", getHeader());
        component.setValueExpression("hide", getHide());
    }

    @Override
    public void release() {
        super.release();
        setHeader(null);
        setHide(null);
    }

    @Override
    public String getComponentType() {
        return TREENODEINFO_COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return TREENODEINFO_RENDERER_TYPE;
    }
}
