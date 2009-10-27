/*
 * MDweb - Open Source tool for cataloging and locating environmental resources
 *         http://mdweb.codehaus.org
 *
 *   Copyright (c) 2007-2009, Institut de Recherche pour le Développement (IRD)
 *   Copyright (c)      2009, Geomatys
 *
 * This file is part of MDweb.
 *
 * MDweb is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation;
 *   version 3.0 of the License.
 *
 * MDweb is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *   Lesser General Public License for more details:
 *         http://www.gnu.org/licenses/lgpl-3.0.html
 *
 */
package org.mochafaces.component;

import javax.faces.context.FacesContext;

/**
 *
 * @author kevindelfour
 */
public abstract class UIBaseExtend extends UIBase{

    /* Fields */
    private String onclick;
    private String ondblclick;
    private String onkeydown;
    private String onkeypress;
    private String onkeyup;
    private String onmousedown;
    private String onmousemove;
    private String onmouseout;
    private String onmouseover;
    private String onmouseup;

    /* Methods */
    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[11];
        values[0] = super.saveState(context);
        values[1] = getOnclick();
        values[2] = getOndblclick();
        values[3] = getOnkeydown();
        values[4] = getOnkeypress();
        values[5] = getOnkeyup();
        values[6] = getOnmousedown();
        values[7] = getOnmousemove();
        values[8] = getOnmouseout();
        values[9] = getOnmouseover();
        values[10]= getOnmouseup();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setOnclick((String) values[1]);
        setOndblclick((String)values[2]);
        setOnkeydown((String)values[3]);
        setOnkeypress((String)values[4]);
        setOnkeyup((String)values[5]);
        setOnmousedown((String) values[6]);
        setOnmousemove((String)values[7]);
        setOnmouseout((String)values[8]);
        setOnmouseover((String)values[9]);
        setOnmouseup((String)values[10]);
    }

    /**
     * @return the onkeypress
     */
    public String getOnkeypress() {
        return onkeypress;
    }

    /**
     * @param onkeypress the onkeypress to set
     */
    public void setOnkeypress(String onkeypress) {
        this.onkeypress = onkeypress;
    }

    /**
     * @return the onkeyup
     */
    public String getOnkeyup() {
        return onkeyup;
    }

    /**
     * @param onkeyup the onkeyup to set
     */
    public void setOnkeyup(String onkeyup) {
        this.onkeyup = onkeyup;
    }

    /**
     * @return the onmousedown
     */
    public String getOnmousedown() {
        return onmousedown;
    }

    /**
     * @param onmousedown the onmousedown to set
     */
    public void setOnmousedown(String onmousedown) {
        this.onmousedown = onmousedown;
    }

    /**
     * @return the onmousemove
     */
    public String getOnmousemove() {
        return onmousemove;
    }

    /**
     * @param onmousemove the onmousemove to set
     */
    public void setOnmousemove(String onmousemove) {
        this.onmousemove = onmousemove;
    }

    /**
     * @return the onmouseout
     */
    public String getOnmouseout() {
        return onmouseout;
    }

    /**
     * @param onmouseout the onmouseout to set
     */
    public void setOnmouseout(String onmouseout) {
        this.onmouseout = onmouseout;
    }

    /**
     * @return the onmouseover
     */
    public String getOnmouseover() {
        return onmouseover;
    }

    /**
     * @param onmouseover the onmouseover to set
     */
    public void setOnmouseover(String onmouseover) {
        this.onmouseover = onmouseover;
    }

    /**
     * @return the onmouseup
     */
    public String getOnmouseup() {
        return onmouseup;
    }

    /**
     * @param onmouseup the onmouseup to set
     */
    public void setOnmouseup(String onmouseup) {
        this.onmouseup = onmouseup;
    }

    /**
     * @return the onclick
     */
    public String getOnclick() {
        return onclick;
    }

    /**
     * @param onclick the onclick to set
     */
    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    /**
     * @return the ondblclick
     */
    public String getOndblclick() {
        return ondblclick;
    }

    /**
     * @param ondblclick the ondblclick to set
     */
    public void setOndblclick(String ondblclick) {
        this.ondblclick = ondblclick;
    }

    /**
     * @return the onkeydown
     */
    public String getOnkeydown() {
        return onkeydown;
    }

    /**
     * @param onkeydown the onkeydown to set
     */
    public void setOnkeydown(String onkeydown) {
        this.onkeydown = onkeydown;
    }
}
