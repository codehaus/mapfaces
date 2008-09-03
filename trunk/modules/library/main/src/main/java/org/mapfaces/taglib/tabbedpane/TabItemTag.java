package org.mapfaces.taglib.tabbedpane;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

/**
 *
 * @author kdelfour
 */
public class TabItemTag extends UIComponentELTag {

    private ValueExpression title = null;
    private final String TABITEM_COMP_TYPE = "org.mapfaces.tabbedpane.tabpanel.TabItem";
    private final String TABITEM_RENDERER_TYPE = "org.mapfaces.renderkit.tabpanel.HTMLTabItem";

  
    public ValueExpression getTitle() {
        return title;
    }

    public void setTitle(ValueExpression title) {
        this.title = title;
    }
    
    @Override
    public String getComponentType() {
        return TABITEM_COMP_TYPE;
    }

    @Override
    public String getRendererType() {
        return TABITEM_RENDERER_TYPE;
    }

    @Override
    public void setProperties(UIComponent component) {
        super.setProperties(component);
        component.setValueExpression("title", title);
      }

    @Override
    public void release() {
        super.release();
        setTitle(null);
    }
}

