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

package org.mapfaces.component;

import javax.faces.context.FacesContext;

/**
 * @author OLivier Terral.
 * @author Mehdi Sidhoum.
 */
public class UICursorTrack extends UIWidgetBase {

    public static final String FAMILIY = "org.mapfaces.CursorTrack";

    private boolean showPX = false;
    private boolean showXY = false;
    private boolean showLatLon = true;
    private boolean showDMS = false;
    private boolean showDM = false;

    /** Creates a new instance of UICursorTrack */
    public UICursorTrack() {
        super();
        setRendererType("org.mapfaces.renderkit.html.CursorTrack"); // this component has a renderer
        if (isDebug()) {
            System.out.println("[UICursorTrack] constructor----------------------");
        }
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
        final Object values[] = new Object[6];
        values[0] = super.saveState(context);
        values[1] = isShowPX();
        values[2] = isShowXY();
        values[3] = isShowLatLon();
        values[4] = isShowDMS();
        values[5] = isShowDM();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setShowPX((boolean) (Boolean) values[1]);
        setShowXY((boolean) (Boolean) values[2]);
        setShowLatLon((boolean) (Boolean) values[3]);
        setShowDMS((boolean) (Boolean) values[4]);
        setShowDM((boolean) (Boolean) values[5]);
    }

    public boolean isShowPX() {
        return showPX;
    }

    public void setShowPX(final boolean showPX) {
        this.showPX = showPX;
    }

    public boolean isShowXY() {
        return showXY;
    }

    public void setShowXY(final boolean showXY) {
        this.showXY = showXY;
    }

    public boolean isShowLatLon() {
        return showLatLon;
    }

    public void setShowLatLon(final boolean showLatLon) {
        this.showLatLon = showLatLon;
    }

    public boolean isShowDMS() {
        return showDMS;
    }

    public void setShowDMS(final boolean showDMS) {
        this.showDMS = showDMS;
    }

    public boolean isShowDM() {
        return showDM;
    }

    public void setShowDM(final boolean showDM) {
        this.showDM = showDM;
    }
}
