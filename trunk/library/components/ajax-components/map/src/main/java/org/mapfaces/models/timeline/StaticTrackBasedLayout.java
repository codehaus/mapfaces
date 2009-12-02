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

package org.mapfaces.models.timeline;

import java.io.Serializable;

/**
 * @author Mehdi Sidhoum.
 */
public class StaticTrackBasedLayout implements Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private Event eventSource;

    private LinearEther ether;

    private boolean showText;

    private String theme;

    public Event getEventSource() {
        return eventSource;
    }

    public void setEventSource(final Event eventSource) {
        this.eventSource = eventSource;
    }

    public LinearEther getEther() {
        return ether;
    }

    public void setEther(final LinearEther ether) {
        this.ether = ether;
    }

    public boolean isShowText() {
        return showText;
    }

    public void setShowText(final boolean showText) {
        this.showText = showText;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(final String theme) {
        this.theme = theme;
    }
}
