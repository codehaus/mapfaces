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
public class GregorianEtherPainter implements Serializable {
    
    private static final long serialVersionUID = 7526471155622776147L;

    private String theme;

    private int unit;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(final int unit) {
        this.unit = unit;
    }

}
