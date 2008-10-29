package org.mapfaces.component.abstractTree;

import javax.faces.context.FacesContext;

/**
 *
 * @author kdelfour
 */
public abstract class UITreeToolbarBase extends UITreeBase {

    private String styleClassTools;
    private String styleTools;

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[3];
        values[0] = super.saveState(context);
        values[1] = getStyleTools();
        values[2] = getStyleClassTools();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setStyleTools((String) values[1]);
        setStyleClassTools((String)values[2]);
    }

    @Override
    public abstract String getFamily();

    @Override
    public abstract String getRendererType();

    public String getStyleClassTools() {
        return styleClassTools;
    }

    public void setStyleClassTools(String styleClassTools) {
        this.styleClassTools = styleClassTools;
    }

    public String getStyleTools() {
        return styleTools;
    }

    public void setStyleTools(String styleTools) {
        this.styleTools = styleTools;
    }
}
