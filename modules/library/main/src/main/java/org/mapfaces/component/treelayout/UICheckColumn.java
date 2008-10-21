package org.mapfaces.component.treelayout;

import javax.faces.context.FacesContext;
import org.mapfaces.component.abstractTree.UIColumnBase;

public class UICheckColumn extends UIColumnBase {

    private final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLCheckColumn";
    private final String FAMILY = "org.mapfaces.treelayout.CheckColumn";


    /**
     * @return the icon
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
    }

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[1];
        values[0] = super.saveState(context);
        return values;
    }

}
