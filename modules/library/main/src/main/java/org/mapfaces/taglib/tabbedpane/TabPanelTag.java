package org.mapfaces.taglib.tabbedpane;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author kdelfour
 */
public class TabPanelTag extends UIComponentELTag {

    private ValueExpression title = null;
    private ValueExpression width = null;
    private ValueExpression height = null;
    private final String TABPANEL_COMP_TYPE = "org.mapfaces.tabbedpane.TabPanel";
    private final String TABPANEL_RENDERER_TYPE = "org.mapfaces.renderkit.HTMLTabPanel";

  
    public ValueExpression getTitle() {
        return title;
    }

    public void setTitle(ValueExpression title) {
        this.title = title;
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

    @Override
    public String getComponentType() {
        return TABPANEL_COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return TABPANEL_RENDERER_TYPE;
    }

    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("title", title);
        component.setValueExpression("width", width);
        component.setValueExpression("height", height);
    }

    @Override
    public void release() {
        super.release();
        setTitle(null);
        setWidth(null);
        setHeight(null);
    }
}

