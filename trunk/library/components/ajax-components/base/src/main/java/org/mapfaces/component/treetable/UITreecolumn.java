package org.mapfaces.component.treetable;

import javax.faces.component.UIColumn;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindelfour
 */
public class UITreecolumn extends UIColumn {

    private static final String FAMILY = "org.mapfaces.TreeTable.TreeColumn";
    private static final String RENDERER_TYPE = null;
    private boolean viewControls = false;
    private String width;

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[3];
        values[0] = super.saveState(context);
        values[1] = isViewControls();
        values[2] = getWidth();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setViewControls((Boolean) values[1]);
        setWidth((String) values[2]);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    /**
     * @return the viewControls
     */
    public boolean isViewControls() {
        return viewControls;
    }

    /**
     * @param viewControls the viewControls to set
     */
    public void setViewControls(boolean viewControls) {
        this.viewControls = viewControls;
    }

    /**
     * @return the width
     */
    public String getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(String width) {
        this.width = width;
    }
}
