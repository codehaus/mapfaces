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
public class DurationEventPainter implements Serializable {
    
    private boolean showText;
    
    private String theme;
    
    private double trackHeight;
    
    private double trackGap;
    
    private StaticTrackBasedLayout layout;

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

    public double getTrackHeight() {
        return trackHeight;
    }

    public void setTrackHeight(final double trackHeight) {
        this.trackHeight = trackHeight;
    }

    public double getTrackGap() {
        return trackGap;
    }

    public void setTrackGap(final double trackGap) {
        this.trackGap = trackGap;
    }

    public StaticTrackBasedLayout getLayout() {
        return layout;
    }

    public void setLayout(final StaticTrackBasedLayout layout) {
        this.layout = layout;
    }

}
