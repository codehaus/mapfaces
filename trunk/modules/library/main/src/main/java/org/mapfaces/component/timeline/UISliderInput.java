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

import javax.faces.context.FacesContext;
import javax.faces.component.UICommand;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class UISliderInput extends UICommand {

    public static final String FAMILIY = "org.mapfaces.component.TimeLine.SliderInput";
    private String maxval = "100";
    private String forid = "0";
    private String horizontal = "true";
    private String length = "300";
    final static String intervalNames[] = {"MILLENNIUM", "CENTURY", "DECADE", "YEAR", "MONTH", "WEEK",
        "DAY", "HOUR", "MINUTE", "SECOND", "MILLISECOND"
    };

    public String getFamily() {
        return FAMILIY;
    }

    public UISliderInput() {
        super();
        setRendererType("org.mapfaces.renderkit.TimeLine.HTMLSliderInput");
    }

    private String getBaseClientId(FacesContext context) {
        // Simple method to return "base" of ClientId
        String clientId = getClientId(context);
        return clientId.substring(0, clientId.indexOf(":") + 1);
    }

    public String getMaxval() {
        return maxval;
    }

    public void setMaxval(String maxval) {
        this.maxval = maxval;
    }

    public String getForid() {
        return forid;
    }

    public void setForid(String forid) {
        this.forid = forid;
    }

    public String getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(String horizontal) {
        this.horizontal = horizontal;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getIndexFromUnit(String intervalUnit) {
        for (int i = 0; i < intervalNames.length; i++) {
            if (intervalNames[i].equals(intervalUnit)) {
                return intervalNames.length - i - 1;
            }
        }
        return -1;
    }
}
