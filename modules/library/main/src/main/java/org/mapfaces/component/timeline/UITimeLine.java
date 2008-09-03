/*
 * UITimeLine.java
 *
 * Created on 10 avril 2008, 17:29
 */
package org.mapfaces.component.timeline;

import java.util.List;
import javax.faces.component.UICommand;
import org.mapfaces.models.timeline.Event;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class UITimeLine extends UICommand {

    private List<Event> events;
    /**
     * Name of the js object wher the timeline is defined
     * 
     */
    private String jsObject;
    
    public static final String FAMILIY = "org.mapfaces.component.TimeLine";

    public String getFamily() {
        return FAMILIY;
    }

    /** Creates a new instance of UITimeLine */
    public UITimeLine() {
        super();
        setRendererType("org.mapfaces.renderkit.HTMLTimeLine"); // this component has a renderer
    }

    public List<Event> getEvents() {
        return events;
    }

    public String getJsObject() {
        return jsObject;
    }
    public void setJsObject(String jsObject) {
        this.jsObject = jsObject;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
