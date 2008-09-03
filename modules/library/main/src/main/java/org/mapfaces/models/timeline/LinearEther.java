/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.models.timeline;

import java.util.Date;

/**
 *
 * @author Sidhoum Mehdi.
 */
public class LinearEther {
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

    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * interval pixels.
     */
    public int getPixelsPerInterval() {
        return pixelsPerInterval;
    }

    public void setPixelsPerInterval(int pixelsPerInterval) {
        this.pixelsPerInterval = pixelsPerInterval;
    }

    /**
     * a date to center the band or the current date by default.
     */
    public Date getCentersOn() {
        return centersOn;
    }

    public void setCentersOn(Date centersOn) {
        this.centersOn = centersOn;
    }

}
