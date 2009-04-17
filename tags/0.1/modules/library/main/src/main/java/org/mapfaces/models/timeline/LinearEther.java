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
import java.util.Date;

/**
 * @author Sidhoum Mehdi.
 */
public class LinearEther implements Serializable {
    /**
     *  the number of milliseconds for example Timeline.DateTime.MONTH.
     */
    private int interval;

    /**
     * interval pixels.
     */
    private int pixelsPerInterval;

    /**
     * a date to center the band or the current date by default.
     */
    private Date centersOn;

    /**
     *  the number of milliseconds for example Timeline.DateTime.MONTH.
     */
    public int getInterval() {
        return interval;
    }

    public void setInterval(final int interval) {
        this.interval = interval;
    }

    /**
     * interval pixels.
     */
    public int getPixelsPerInterval() {
        return pixelsPerInterval;
    }

    public void setPixelsPerInterval(final int pixelsPerInterval) {
        this.pixelsPerInterval = pixelsPerInterval;
    }

    /**
     * a date to center the band or the current date by default.
     */
    public Date getCentersOn() {
        return centersOn;
    }

    public void setCentersOn(final Date centersOn) {
        this.centersOn = centersOn;
    }

}
