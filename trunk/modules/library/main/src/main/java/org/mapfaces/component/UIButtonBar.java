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
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class UIButtonBar extends UIWidgetBase {

    public static final String FAMILIY = "org.mapfaces.ButtonBar";
    private boolean zoomIn = true;
    private boolean zoomOut = true;
    private boolean pan = true;
    private boolean zoomMaxExtent = true;
    private boolean history = true;
    private boolean panEffect = false;

    /** Creates a new instance of UIButtonBar */
    public UIButtonBar() {
        super();
        setRendererType("org.mapfaces.renderkit.html.ButtonBar"); // this component has a renderer
        if (isDebug()) {
            System.out.println("[UIButtonBar] constructor----------------------");
        }
    }

    public String getFamily() {
        return FAMILIY;
    }

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[7];
        values[0] = super.saveState(context);
        values[1] = zoomIn;
        values[2] = zoomOut;
        values[3] = pan;
        values[4] = zoomMaxExtent;
        values[5] = history;
        values[6] = panEffect;
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        zoomIn = (Boolean) values[1];
        zoomOut = (Boolean) values[2];
        pan = (Boolean) values[3];
        zoomMaxExtent = (Boolean) values[4];
        history = (Boolean) values[5];
        panEffect = (Boolean) values[6];
    }

    public boolean isZoomIn() {
        return zoomIn;
    }

    public void setZoomIn(boolean zoomIn) {
        this.zoomIn = zoomIn;
    }

    public boolean isZoomOut() {
        return zoomOut;
    }

    public void setZoomOut(boolean zoomOut) {
        this.zoomOut = zoomOut;
    }

    public boolean isPan() {
        return pan;
    }

    public void setPan(boolean pan) {
        this.pan = pan;
    }

    public boolean isZoomMaxExtent() {
        return zoomMaxExtent;
    }

    public void setZoomMaxExtent(boolean zoomMaxExtent) {
        this.zoomMaxExtent = zoomMaxExtent;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }

    public boolean isPanEffect() {
        return panEffect;
    }

    public void setPanEffect(boolean panEffect) {
        this.panEffect = panEffect;
    }
}
