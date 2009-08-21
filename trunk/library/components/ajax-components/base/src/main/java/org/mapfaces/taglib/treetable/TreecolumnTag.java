package org.mapfaces.taglib.treetable;

import com.sun.faces.taglib.html_basic.ColumnTag;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import org.mapfaces.component.treetable.UITreecolumn;

/**
 *
 * @author kevindelfour
 */
public class TreecolumnTag extends ColumnTag {

    private static final String COMP_TYPE = "org.mapfaces.TreeTable.TreeColumn";
    private static final String RENDER_TYPE = null;
    private ValueExpression viewControls;
    private ValueExpression width;

    @Override
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

        if (!(component instanceof UITreecolumn)) {
            throw new IllegalStateException("Component " + component.toString() + " not expected type.  Expected: org.mapfaces.components.treetable.UITreeData.  Perhaps you're missing a tag?");
        }

        final UITreecolumn column = (UITreecolumn) component;

        if (getViewControls() != null) {
            column.setValueExpression("viewControls", getViewControls());
        }
        if (getWidth() != null) {
            column.setValueExpression("width", getWidth());
        }

    }

    @Override
    public void release() {
        super.release();
        setViewControls(null);
        setWidth(null);
    }

    /**
     * @return the viewControls
     */
    public ValueExpression getViewControls() {
        return viewControls;
    }

    /**
     * @param viewControls the viewControls to set
     */
    public void setViewControls(ValueExpression viewControls) {
        this.viewControls = viewControls;
    }

    /**
     * @return the width
     */
    public ValueExpression getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(ValueExpression width) {
        this.width = width;
    }
}
