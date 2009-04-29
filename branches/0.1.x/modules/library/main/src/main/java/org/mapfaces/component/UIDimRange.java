/*
 * UICursorTrack.java
 *
 * Created on 24 decembre 2007, 13:55
 */

package org.mapfaces.component;

import javax.el.ValueExpression;
import javax.faces.component.StateHolder;
import javax.faces.context.FacesContext;

/**
 * @author OLivier Terral.
 * @author Mehdi Sidhoum.
 */
public class UIDimRange extends UIWidgetBase implements StateHolder {

    public static final String FAMILIY = "org.mapfaces.DimRange";

    private String layerCompId;

    /** Creates a new instance of UICursorTrack */
    public UIDimRange() {
        super();
        setRendererType("org.mapfaces.renderkit.html.DimRange"); // this component has a renderer
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILIY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
     public Object saveState(final FacesContext context) {
        final Object values[] = new Object[3];
        values[0] = super.saveState(context);
        values[1] = layerCompId;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        layerCompId = (String) values[1];
    }

    public String getLayerCompId() {
        return layerCompId;
    }

    public void setLayerCompId(final String layerCompId) {

        if (layerCompId.contains("#")) {
            final FacesContext context = FacesContext.getCurrentInstance();
            final ValueExpression ve = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), layerCompId, String.class);
            this.layerCompId = (String) ve.getValue(context.getELContext());
        }else
            this.layerCompId = layerCompId;
    }

}
