/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.models.timeline;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class StaticTrackBasedLayout {
    
    private Event eventSource;
    
    private LinearEther ether;
    
    private boolean showText;
    
    private String theme;

    public Event getEventSource() {
        return eventSource;
    }

    public void setEventSource(Event eventSource) {
        this.eventSource = eventSource;
    }

    public LinearEther getEther() {
        return ether;
    }

    public void setEther(LinearEther ether) {
        this.ether = ether;
    }

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
}
