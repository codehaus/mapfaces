/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */

package org.mapfaces.taglib;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;

/**
 * @author Olivier Terral
 */
public class CursorTrackTag extends WidgetBaseTag {

    /**
     * <p>The standard component type for this component.</p>
     */
    public static final String COMP_TYPE = "org.mapfaces.component.CursorTrack";
    /**
     * <p>The standard renderer type for this component.</p>
     */
    public static final String RENDER_TYPE = "org.mapfaces.renderkit.html.CursorTrack";
    /**
     * OpenLayers.Control
     * */
    private ValueExpression showPX = null;
    private ValueExpression showXY = null;
    private ValueExpression showLatLon = null;
    private ValueExpression showDMS = null;
    private ValueExpression showDM = null;

    /**
     * {@inheritDoc }
     */
    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getRendererType() {
        return RENDER_TYPE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void setProperties(final UIComponent component) {
        // always call the superclass method
        super.setProperties(component);
        component.setValueExpression("showPX", showPX);
        component.setValueExpression("showXY", showXY);
        component.setValueExpression("showLatLon", showLatLon);
        component.setValueExpression("showDMS", showDMS);
        component.setValueExpression("showDM", showDM);

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void release() {
        // allways call the superclass method
        super.release();
        showPX = null;
        showXY = null;
        showLatLon = null;
        showDMS = null;
        showDM = null;

    }

    public ValueExpression getShowPX() {
        return showPX;
    }

    public void setShowPX(ValueExpression showPX) {
        this.showPX = showPX;
    }

    public ValueExpression getShowXY() {
        return showXY;
    }

    public void setShowXY(ValueExpression showXY) {
        this.showXY = showXY;
    }

    public ValueExpression getShowLatLon() {
        return showLatLon;
    }

    public void setShowLatLon(ValueExpression showLatLon) {
        this.showLatLon = showLatLon;
    }

    public ValueExpression getShowDMS() {
        return showDMS;
    }

    public void setShowDMS(ValueExpression showDMS) {
        this.showDMS = showDMS;
    }

    public ValueExpression getShowDM() {
        return showDM;
    }

    public void setShowDM(ValueExpression showDM) {
        this.showDM = showDM;
    }
}
