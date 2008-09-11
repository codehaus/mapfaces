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
