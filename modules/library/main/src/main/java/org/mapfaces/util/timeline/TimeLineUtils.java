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

package org.mapfaces.util.timeline;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import org.mapfaces.models.Layer;
import org.mapfaces.models.timeline.Event;
import org.mapfaces.models.timeline.Priority;
import org.mapfaces.models.timeline.Status;
import org.mapfaces.util.PeriodUtilities;

/**
 *
 * @author Mehdi Sidhoum
 */
public class TimeLineUtils {

    public static List<Event> getEventsFromLayer(Layer layer) throws ParseException, DatatypeConfigurationException {
        List<Event> result = new ArrayList<Event>();
        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        sdf.setTimeZone(TimeZone.getDefault());
        SortedSet<Date> dates = PeriodUtilities.getDatesFromPeriodDescription(layer.getTimes(), sdf);
        Date dateBegin = dates.first();
        Date dateEnd = dates.last();
        for (Iterator it = dates.iterator(); it.hasNext();) {
            Date crrt = (Date) it.next();
            Event e = new Event(crrt,
                    null,
                    null,
                    false,
                    layer.getId() + " " + sdf.format(crrt),//layer.getName()+" "+layer.getId()+" "+sdf.format(crrt),
                    layer.getTitle(),//This is the duration of the jsf implementation for the component TimeLine : "+sdf.format(crrt),
                    sdf.format(crrt),
                    sdf.format(crrt),
                    "",
                    Priority.NORMAL,
                    "",
                    Status.IN_PROGRESS,
                    "",
                    layer);
            result.add(e);
        }
        return result;
        
    }
    
    /**
     * This method returns a default date from a mapfaces Layer model.
     * @param layer
     * @return
     * @throws java.text.ParseException
     */
    public static Date getDefaultDateFromLayer (Layer layer) throws ParseException {
        if (layer.getUserValueTime() == null) {
            return new Date();
        } else {
            return PeriodUtilities.getDateFromString(layer.getUserValueTime());
        }
    }
}
