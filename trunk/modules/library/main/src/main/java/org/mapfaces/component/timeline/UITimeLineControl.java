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

import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mehdi Sidhoum
 */
public class UITimeLineControl extends UICommand {
    
    public static final String FAMILIY = "org.mapfaces.component.TimeLine.TimeLineControl";
    
    private String style;
    private String styleClass;
        
    public String getFamily() {
        return FAMILIY;
    }
    
    public UITimeLineControl() {
        super();
        setRendererType("org.mapfaces.renderkit.TimeLine.HTMLTimeLineControl");
    }
    
    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[14];
        values[0] = super.saveState(context);
        values[1] = style;
        values[2] = styleClass;
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        style = (String) values[1];
        styleClass = (String) values[2];
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }
}
