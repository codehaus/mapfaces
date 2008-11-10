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

package org.mapfaces.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 * @author Guilhem Legal
 * @author Mehdi Sidhoum
 */
public class PeriodUtilities {

    private static final Logger LOGGER = Logger.getLogger("org.mapfaces.util");
    /**
     * The number of millisecond in one year.
     */
    private final static long yearMS = 31536000000L;
    /**
     * The number of millisecond in one month.
     */
    private final static long monthMS = 2628000000L;
    /**
     * The number of millisecond in one week.
     */
    private final static long weekMS = 604800000L;
    /**
     * The number of millisecond in one day.
     */
    private final static long dayMS = 86400000L;
    /**
     * The number of millisecond in one hour.
     */
    private final static long hourMS = 3600000L;
    /**
     * The number of millisecond in one minute.
     */
    private final static long minMS = 60000;
    /**
     * The number of millisecond in one second.
     */
    private final static long secondMS = 1000;
    /**
     * The format of the dates.
     */
    private DateFormat dateFormat;

    /**
     * Build a new period worker with the specified DateFormat
     */
    public PeriodUtilities(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Evaluate the periodical gap between the different available time.
     * Return a String concatening periods and isolated date.
     *
     * @param dates a sorted set of date (ordered by time).
     */
    public String getDatesRespresentation(final SortedSet<Date> dates) {
        if (dates.comparator() != null) {
            throw new IllegalArgumentException();
        }

        final StringBuffer response = new StringBuffer();

        if (dates.isEmpty()) {
            return "";
        }

        Date first = dates.first();
        Date previousDate = first;
        long previousGap = 0;
        long gap = 0;
        int nbDataInGap = 0;

        for (final Date d : dates) {
            previousGap = gap;
            gap = d.getTime() - previousDate.getTime();

            if (previousGap != gap) {
                if (nbDataInGap >= 3) {
                    String firstDate = dateFormat.format(first) + "";
                    if (response.indexOf(firstDate + ',') != -1) {
                        int pos = response.indexOf(firstDate);
                        response.delete(pos, pos + firstDate.length() + 1);
                    }
                    response.append(getPeriodDescription(dates.subSet(first, d), previousGap)).append(',');
                    nbDataInGap = 1;
                } else {
                    if (nbDataInGap > 0) {
                        dateFormat.format(previousDate, response, new FieldPosition(0)).append(',');
                        nbDataInGap = 1;
                    }
                }
                first = previousDate;

            } else {
                nbDataInGap++;
            }

            previousDate = d;
        }

        if (nbDataInGap > 0) {
            if (nbDataInGap >= 3) {
                String firstDate = dateFormat.format(first) + "";
                if (response.indexOf(firstDate + ',') != -1) {
                    int pos = response.indexOf(firstDate);
                    response.delete(pos, pos + firstDate.length() + 1);
                }
                response.append(getPeriodDescription(dates.tailSet(first), gap));
                nbDataInGap = 1;
            } else {
                if (nbDataInGap > 0) {
                    dateFormat.format(previousDate, response, new FieldPosition(0));
                    nbDataInGap = 1;
                }
            }
        }

        return response.toString();
    }

    /**
     * Return a String for a range of date (or just one)
     *
     * @param first
     * @param last
     * @param gap
     * @return
     */
    public String getPeriodDescription(SortedSet<Date> dates, long gap) {
        StringBuffer response = new StringBuffer();
        dateFormat.format(dates.first(), response, new FieldPosition(0));
        response.append('/');

        dateFormat.format(dates.last(), response, new FieldPosition(0));
        response.append("/P");

        //we look if the gap is more than one year (31536000000 ms)
        long temp = gap / yearMS;
        if (temp > 1) {
            response.append(temp).append("Y");
            gap -= temp * yearMS;
        }

        //we look if the gap is more than one month (2628000000 ms)
        temp = gap / monthMS;
        if (temp >= 1) {
            response.append(temp).append("M");
            gap -= temp * monthMS;
        }
        //we look if the gap is more than one week (604800000 ms)
        temp = gap / weekMS;
        if (temp >= 1) {
            response.append(temp).append("W");
            gap -= temp * weekMS;
        }

        //we look if the gap is more than one day (86400000 ms)
        temp = gap / dayMS;
        if (temp >= 1) {
            response.append(temp).append("D");
            gap -= temp * dayMS;
        }

        //if the gap is not over we pass to the hours by adding 'T'
        if (gap != 0) {
            response.append('T');
        }

        //we look if the gap is more than one hour (3600000 ms)
        temp = gap / hourMS;
        if (temp >= 1) {
            response.append(temp).append("H");
            gap -= temp * hourMS;
        }

        //we look if the gap is more than one min (60000 ms)
        temp = gap / minMS;
        if (temp >= 1) {
            response.append(temp).append("M");
            gap -= temp * minMS;
        }

        //we look if the gap is more than one week (1000 ms)
        temp = gap / secondMS;
        if (temp >= 1) {
            response.append(temp).append("S");
            gap -= temp * secondMS;
        }
        if (gap != 0) {
            throw new IllegalArgumentException("TimePeriod can't be found a the Millisecond precision");
        }
        return response.toString();
    }

    /**
     * Returns a sorted set from a string description.
     *
     * @param periods
     * @param dateFormat
     * @return
     * @throws java.text.ParseException
     */
    public static SortedSet<Date> getDatesFromPeriodDescription(String periods, DateFormat dateFormat) throws ParseException, DatatypeConfigurationException {
        SortedSet<Date> response = new TreeSet<Date>();
        final StringTokenizer tokens = new StringTokenizer(periods, ",");
        while (tokens.hasMoreTokens()) {
            String dates = tokens.nextToken().trim();

            if (dates.indexOf('/') == -1) {
                response.add(dateFormat.parse(dates));
            } else {

                //we get the begin position
                String begin = dates.substring(0, dates.indexOf('/'));
                Date first = dateFormat.parse(begin);
                dates = dates.substring(dates.indexOf('/') + 1);

                //we get the end position
                String end = dates.substring(0, dates.indexOf('/'));
                Date last = dateFormat.parse(end);
                dates = dates.substring(dates.indexOf('/') + 1);

                //then we get the period Description
                long gap = getTimeFromPeriodDescription(dates);
                Date currentDate = first;
                while (currentDate.before(last)) {
                    Date nextDate = new Date(currentDate.getTime() + gap);
                    response.add(currentDate);
                    currentDate = nextDate;
                }
                response.add(last);
            }
        }
        return response;
    }

    /**
     * Returns the length of the duration in milli-seconds.
     * @param duration
     * @param startInstant
     * @return
     */
    /**
     * Return a Date (long time) from a String description
     *
     * @param periodDescription
     * @return
     */
    public static long getTimeFromPeriodDescription(String periodDescription) {

        long time = 0;
        //we remove the 'P'
        periodDescription = periodDescription.substring(1);

        //we look if the period contains years (31536000000 ms)
        if (periodDescription.indexOf('Y') != -1) {
            int nbYear = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('Y')));
            time += nbYear * yearMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('Y') + 1);
        }

        //we look if the period contains months (2628000000 ms)
        if (periodDescription.indexOf('M') != -1 &&
                (periodDescription.indexOf("T") == -1 || periodDescription.indexOf("T") > periodDescription.indexOf('M'))) {
            int nbMonth = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('M')));
            time += nbMonth * monthMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('M') + 1);
        }

        //we look if the period contains weeks (604800000 ms)
        if (periodDescription.indexOf('W') != -1) {
            int nbWeek = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('W')));
            time += nbWeek * weekMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('W') + 1);
        }

        //we look if the period contains days (86400000 ms)
        if (periodDescription.indexOf('D') != -1) {
            int nbDay = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('D')));
            time += nbDay * dayMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('D') + 1);
        }

        //if the periodDescription is not over we pass to the hours by removing 'T'
        if (periodDescription.indexOf('T') != -1) {
            periodDescription = periodDescription.substring(1);
        }

        //we look if the period contains hours (3600000 ms)
        if (periodDescription.indexOf('H') != -1) {
            int nbHour = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('H')));
            time += nbHour * hourMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('H') + 1);
        }

        //we look if the period contains minutes (60000 ms)
        if (periodDescription.indexOf('M') != -1) {
            int nbMin = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('M')));
            time += nbMin * minMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('M') + 1);
        }

        //we look if the period contains seconds (1000 ms)
        if (periodDescription.indexOf('S') != -1) {
            int nbSec = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('S')));
            time += nbSec * secondMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('S') + 1);
        }

        if (periodDescription.length() != 0) {
            throw new IllegalArgumentException("The period descritpion is malformed");
        }
        return time;
    }

    /**
     * Returns a Date object from an ISO-8601 representation string. (String defined with pattern yyyy-MM-dd'T'HH:mm:ss.SSSZ or yyyy-MM-dd).
     * @param dateString
     * @return
     */
    public static Date getDateFromString(String dateString) throws ParseException {
        System.out.println(dateString);
        Date response = null;
        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);

        if (dateString.contains("T")) {
            //set the timezone if exists.
            String timezone = getTimeZone(dateString);
            sdf.setTimeZone(TimeZone.getTimeZone(timezone));

            response = sdf.parse(dateString);
        }
        return response;
    }

    public static String getTimeZone(String dateString) {
        int index = dateString.lastIndexOf("+");
        if (index == -1) {
            index = dateString.lastIndexOf("-");
        }
        return "GMT" + dateString.substring(index);
    }

    /**
     * Return a Date (long time) from a String description
     *
     * @param periodDescription
     * @return
     */
    public static long getTimeInMillis(String periodDescription) {

        long time = 0;
        //we remove the 'P'
        periodDescription = periodDescription.substring(1);

        //we look if the period contains years (31536000000 ms)
        if (periodDescription.indexOf('Y') != -1) {
            int nbYear = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('Y')));
            time += nbYear * yearMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('Y') + 1);
        }

        //we look if the period contains months (2628000000 ms)
        if (periodDescription.indexOf('M') != -1 &&
                (periodDescription.indexOf("T") == -1 || periodDescription.indexOf("T") > periodDescription.indexOf('M'))) {
            int nbMonth = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('M')));
            time += nbMonth * monthMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('M') + 1);
        }

        //we look if the period contains weeks (604800000 ms)
        if (periodDescription.indexOf('W') != -1) {
            int nbWeek = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('W')));
            time += nbWeek * weekMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('W') + 1);
        }

        //we look if the period contains days (86400000 ms)
        if (periodDescription.indexOf('D') != -1) {
            int nbDay = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('D')));
            time += nbDay * dayMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('D') + 1);
        }

        //if the periodDescription is not over we pass to the hours by removing 'T'
        if (periodDescription.indexOf('T') != -1) {
            periodDescription = periodDescription.substring(1);
        }

        //we look if the period contains hours (3600000 ms)
        if (periodDescription.indexOf('H') != -1) {
            int nbHour = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('H')));
            time += nbHour * hourMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('H') + 1);
        }

        //we look if the period contains minutes (60000 ms)
        if (periodDescription.indexOf('M') != -1) {
            int nbMin = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('M')));
            time += nbMin * minMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('M') + 1);
        }

        //we look if the period contains seconds (1000 ms)
        if (periodDescription.indexOf('S') != -1) {
            int nbSec = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('S')));
            time += nbSec * secondMS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('S') + 1);
        }

        if (periodDescription.length() != 0) {
            throw new IllegalArgumentException("The period descritpion is malformed");
        }
        return time;
    }

    public static Date getFirstDateFromPeriodDescription (String periodDescription) throws ParseException, DatatypeConfigurationException {
        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        Date result = new Date();
        SortedSet dates = getDatesFromPeriodDescription(periodDescription, dateFormat);
        if (dates != null && dates.first() != null) {
            return (Date) dates.first();
        }
        return result;
    }

//    public static void main(String[] args) throws DatatypeConfigurationException, ParseException {
//
//        /*PeriodUtilities pu = new PeriodUtilities(null);
//        long response = pu.getTimeFromDuration("PT1M", new Date());
//        System.out.println(">>>>>>>>>>>>>>>>> response = " + response);
//
//        String begin = "2003-02-13T12:28:55.456-0800";
//        String end = "2004-02-13T12:28:55.456-0800";
//        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
//        SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
//        dateFormat.setTimeZone(TimeZone.getTimeZone(PeriodUtilities.getTimeZone(begin)));
//        SortedSet dates = pu.getDatesFromPeriodDescription(begin+"/"+end+"/P1DT23H", dateFormat);
//        System.out.println(">>>>> dates size = " + dates.size());
//
//        Date d = PeriodUtilities.getDateFromString(begin);
//        System.out.println(">>>>>>>>> begin = "+d.toString());
//
//
//        System.out.println(">>>>>>>> long = "+PeriodUtilities.getTimeInMillis("P1MT1M"));
//         */
//        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
//        SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
//        Date d = new Date();
//        SortedSet dates = PeriodUtilities.getDatesFromPeriodDescription("2007-06-06T10:00:00Z/2007-06-20T10:00:00Z/P1D", dateFormat);
//        System.out.println(">>>>>> " + dateFormat.format(d));
//        System.out.println("dates = " + dates.size()+"   get(0) = "+dates.first());
//        for (Iterator it = dates.iterator(); it.hasNext();) {
//            // System.out.println("ici"+((Date)it).toString());
//            Date crrt = (Date) it.next();
//        /* events.add(new Event((Date)it.next(),
//        (Date)it.next(),
//        null,
//        false,
//        "Developpement of the TimeLine components renderers",
//        "This is the duration of the jsf implementation for the component TimeLine",
//        "http://simile.mit.edu/images/csail-logo.gif",
//        "http://travel.yahoo.com/",
//        "",
//        Priority.NORMAL,
//        "",
//        Status.IN_PROGRESS,
//        "",
//        null));
//        }*/
//        }
//    }
}