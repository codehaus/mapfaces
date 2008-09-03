package org.mapfaces.component.tabbedpane;

import java.io.Serializable;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

/**
 *
 * @author kdelfour
 */
public class UITabItem extends UIComponentBase implements Serializable {

    private static final long serialVersionUID = 4054363322134169900L;
    private final String TABITEM_RENDERER_TYPE = "org.mapfaces.renderkit.tabpanel.HTMLTabItem";
    private final String TABITEM_COMP_FAMILY = "javax.faces.Output";
    // =========== ATTRIBUTES ================================================== //
    private String title;
    private boolean active;
    // =========== ATTRIBUTES ACCESSORS ======================================== //
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getFamily() {
        return TABITEM_COMP_FAMILY;
    }

    @Override
    public String getRendererType() {
        return TABITEM_RENDERER_TYPE;
    }

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[3];
        values[0] = super.saveState(context);
        values[1] = getTitle();
        values[2] = isActive();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setTitle((String) values[1]);
        setActive((Boolean) values[2]);
    }
}
