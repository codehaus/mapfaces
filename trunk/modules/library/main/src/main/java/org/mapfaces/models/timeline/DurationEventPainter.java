/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.models.timeline;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class DurationEventPainter {
    
    private boolean showText;
    
    private String theme;
    
    private double trackHeight;
    
    private double trackGap;
    
    private StaticTrackBasedLayout layout;

    public boolean isShowText() {
        return showText;
    }

    public void setShowText(boolean showText) {
        this.showText = showText;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public double getTrackHeight() {
        return trackHeight;
    }

    public void setTrackHeight(double trackHeight) {
        this.trackHeight = trackHeight;
    }

    public double getTrackGap() {
        return trackGap;
    }

    public void setTrackGap(double trackGap) {
        this.trackGap = trackGap;
    }

    public StaticTrackBasedLayout getLayout() {
        return layout;
    }

    public void setLayout(StaticTrackBasedLayout layout) {
        this.layout = layout;
    }

}
