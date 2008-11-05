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

import java.util.Date;

/**
 * @author Mehdi Sidhoum
 */
public class HighlightDecorator extends Event {

    public HighlightDecorator(Date start, Date end, String title, String period, String color) {
        super(start, end, null, false, title, period, null, null, null, null, color, null, null, null);
    }
}
