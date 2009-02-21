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
public class UIPopup extends UIWidgetBase{

    public static final String FAMILIY = "org.mapfaces.Popup";
    
    private String innerHTML = null;
    private String top = "0px";
    private String left = "0px";
    private int width = 300;
    private int height = 200;
    private boolean iframe;
    private boolean hidden = true;

    public UIPopup() {
        super();
        setRendererType("org.mapfaces.renderkit.html.Popup"); // this component has a renderer
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
        final Object values[] = new Object[10];
        values[0] = super.saveState(context);
        values[1] = top;
        values[2] = left;
        values[3] = width;
        values[4] = height;
        values[5] = innerHTML;
        values[6] = iframe;
        values[7] = hidden;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        top = (String) values[1];
        left = (String) values[2];
        width = (Integer) values[3];
        height = (Integer) values[4];
        innerHTML = (String) values[5];
        iframe = (Boolean) values[6];
        hidden = (Boolean) values[7];
    }

    public String getInnerHTML() {
        return innerHTML;
    }

    public void setInnerHTML(String innerHTML) {
        this.innerHTML = innerHTML;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isIframe() {
        return iframe;
    }

    public void setIframe(boolean iframe) {
        this.iframe = iframe;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

}
