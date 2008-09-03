package org.mapfaces.component.treelayout;

import javax.faces.context.FacesContext;
import org.mapfaces.component.abstractTree.UIAbstractColumn;

public class UICheckColumn extends UIAbstractColumn {

    private String icon;
    private final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLCheckColumn";
    private final String FAMILY = "org.mapfaces.treelayout.Column";


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

   /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }


    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        icon = ((String) values[1]);
    }

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[2];
        values[0] = super.saveState(context);
        values[1] = getIcon();
        return values;
    }

}
