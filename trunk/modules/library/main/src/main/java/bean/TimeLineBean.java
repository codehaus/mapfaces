/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import org.mapfaces.component.UILayer;
import org.mapfaces.util.PeriodUtilities;
import org.geotools.metadata.iso.citation.Citations;
import org.geotools.referencing.NamedIdentifier;
import org.geotools.temporal.object.DefaultCalendarDate;
import org.geotools.temporal.object.DefaultDateAndTime;
import org.geotools.temporal.object.DefaultInstant;
import org.geotools.temporal.object.DefaultJulianDate;
import org.geotools.temporal.object.DefaultOrdinalPosition;
import org.geotools.temporal.object.DefaultPeriod;
import org.geotools.temporal.object.DefaultPeriodDuration;
import org.geotools.temporal.object.DefaultPosition;
import org.geotools.temporal.object.DefaultTemporalPosition;
import org.geotools.temporal.object.Utils;
import org.geotools.temporal.reference.DefaultOrdinalEra;
import org.geotools.temporal.reference.DefaultTemporalCoordinateSystem;
import org.geotools.util.SimpleInternationalString;
import org.mapfaces.models.Layer;
import org.mapfaces.models.timeline.Event;
import org.mapfaces.models.timeline.Priority;
import org.mapfaces.models.timeline.Status;
import org.opengis.temporal.Duration;
import org.opengis.temporal.IndeterminateValue;
import org.opengis.temporal.Instant;
import org.opengis.temporal.Period;
import org.opengis.temporal.Position;
import org.opengis.temporal.RelativePosition;
import org.opengis.temporal.TemporalPrimitive;

public class TimeLineBean {
    
    private Date centerDate;
    private boolean test = true;

    private List<Event> events = new ArrayList<Event>();
    /*private List<AbstractTimeGeometricPrimitiveType> timeObjects1 = new ArrayList<AbstractTimeGeometricPrimitiveType>();
    private List<AbstractTimeGeometricPrimitiveType> timeObjects2 = new ArrayList<AbstractTimeGeometricPrimitiveType>();
     */

    /**
     * creates a new instance of TimeLineBean.     
     */
    public TimeLineBean() throws ParseException, DatatypeConfigurationException {

        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "EEE MMM d yyyy HH:mm:ss 'GMT'Z";
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);

        sdf.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        sdf.setTimeZone(TimeZone.getDefault());

        Date dateBegin = sdf.parse("Wed Apr 9 2008 08:08:50 GMT+0200");
        Date dateEnd = cal.getTime();

        Event e1 = new Event(dateBegin,
                sdf.parse("Fri May 30 2008 14:37:21 GMT+0200"),
                null,
                false,
                "Developpement of the TimeLine components renderers",
                "This is the duration of the jsf implementation for the component TimeLine",
                "http://simile.mit.edu/images/csail-logo.gif",
                "http://travel.yahoo.com/",
                "",
                Priority.NORMAL,
                "",
                Status.IN_PROGRESS,
                "",
                null);

        Event e2 = new Event(sdf.parse("Fri Apr 25 2008 18:08:50 GMT+0300"),
                null,
                null,
                false,
                "deploy the new IfremerWS", "This the new interface IfremerWS with new functionnalities",
                "http://www.developez.com/favicon.ico",
                "http://simile.mit.edu/wiki/Timeline",
                "http://www.developez.com/favicon.ico",
                Priority.HIGH,
                "",
                Status.DEFAULT,
                "",
                null);

        cal.set(2008, 4, 15);
        Event e3 = new Event(cal.getTime(),
                null,
                null,
                false,
                "TimeLine working with Event objects",
                "the timeline can load events from a list passed in the value attribute",
                "",
                "http://simile.mit.edu/wiki/Timeline",
                "",
                Priority.NORMAL,
                "",
                Status.IN_PROGRESS,
                "",
                null);

        Event e4 = new Event(sdf.parse("Wed Apr 30 2008 18:08:50 GMT+0300"),
                null,
                null,
                false,
                "Testing the timeline with many attributes for events list",
                "the timeline can load events from a list passed in the value attribute",
                "",
                "http://simile.mit.edu/wiki/Timeline",
                "",
                Priority.LOW,
                "",
                Status.DEFAULT,
                "",
                null);

        cal.set(2008, 3, 31);
        Event e5 = new Event(cal.getTime(),
                null,
                null,
                false,
                "Working with multiple Band info components",
                "the timeline can load events from a list passed in the value attribute",
                "",
                "http://simile.mit.edu/wiki/Timeline",
                "",
                Priority.HIGH,
                "",
                Status.NOT_STARTED,
                "cyan",
                null);

        //uses of ISO 19108 implementation.
        DefaultPosition position1 = new DefaultPosition(new Date());

        SimpleInternationalString dateString = new SimpleInternationalString("2008-06-19T12:28:00.000-08:00");
        DefaultPosition position2 = new DefaultPosition(dateString);

        int[] caldate = {2008, 6, 25};
        DefaultTemporalPosition temporalPosition1 = new DefaultCalendarDate(null, null, null, caldate);
        DefaultPosition position3 = new DefaultPosition(temporalPosition1);

        Number[] clock = {23, 16, 29.45};
        int[] caldate2 = {2008, 6, 14};
        DefaultTemporalPosition temporalPosition2 = new DefaultDateAndTime(null, null, null, caldate2, clock);
        DefaultPosition position4 = new DefaultPosition(temporalPosition2);

        NamedIdentifier name = new NamedIdentifier(Citations.CRS, "Julian calendar");
        Calendar calendar = Calendar.getInstance();
        cal.set(Calendar.ERA, GregorianCalendar.AD);
        calendar.set(-4713, 0, 1);
        DefaultTemporalCoordinateSystem julianSystem = new DefaultTemporalCoordinateSystem(name, null, calendar.getTime(), new SimpleInternationalString("day"));
        DefaultTemporalPosition temporalPosition3 = new DefaultJulianDate(julianSystem, null, 2454629);
        DefaultPosition position5 = new DefaultPosition(temporalPosition3);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.ERA, GregorianCalendar.AD);
        cal2.set(2100, 0, 1);
        cal.set(2012, 11, 20);
        DefaultOrdinalEra ordinalEra1 = new DefaultOrdinalEra(new SimpleInternationalString("New Era"), cal.getTime(), cal2.getTime());
        DefaultOrdinalPosition ordinalPosition6 = new DefaultOrdinalPosition(null, IndeterminateValue.UNKNOWN, ordinalEra1);
        DefaultPosition position6 = new DefaultPosition(ordinalPosition6);
        
        cal2.set(2000, 0, 1);
        cal.set(2008, 5, 20);
        DefaultOrdinalEra ordinalEra2 = new DefaultOrdinalEra(new SimpleInternationalString("2000-2008"), cal2.getTime(), cal.getTime());
        DefaultOrdinalPosition ordinalPosition7 = new DefaultOrdinalPosition(null, IndeterminateValue.UNKNOWN, ordinalEra2);
        DefaultPosition position7 = new DefaultPosition(ordinalPosition7);
        
        cal2.set(2010, 0, 1);
        cal.set(2008, 6, 20);
        DefaultOrdinalEra ordinalEra3 = new DefaultOrdinalEra(new SimpleInternationalString("2008-2010"), cal.getTime(), cal2.getTime());
        DefaultOrdinalPosition ordinalPosition8 = new DefaultOrdinalPosition(null, IndeterminateValue.UNKNOWN, ordinalEra3);
        DefaultPosition position8 = new DefaultPosition(ordinalPosition8);

        //Instant with position as a java.util.Date object
        DefaultInstant instant1 = new DefaultInstant(position1);
        //Instant with position as a String ISO-8601
        DefaultInstant instant2 = new DefaultInstant(position2);
        //Instant with temporal position as a DefaultCalendar
        DefaultInstant instant3 = new DefaultInstant(position3);
        //Instant with temporal position as a DefaultDateAndTime
        DefaultInstant instant4 = new DefaultInstant(position4);
        //Instant with temporal position as a JulianDate
        DefaultInstant instant5 = new DefaultInstant(position5);
        //Instant with temporal position as an OrdinalPosition 
        DefaultInstant instant6 = new DefaultInstant(position6);

        //Periods
        cal2.set(Calendar.ERA, GregorianCalendar.AD);
        cal.set(2007, 3, 1);
        cal2.set(2008, 2, 13);
        DefaultPeriod period1 = new DefaultPeriod(new DefaultInstant(new DefaultPosition(cal.getTime())),
                new DefaultInstant(new DefaultPosition(cal2.getTime())));
        cal.set(2007, 5, 27);
        cal2.set(2007, 8, 12);
        DefaultPeriod period2 = new DefaultPeriod(new DefaultInstant(position2),
                new DefaultInstant(position1));
        cal.set(2007, 11, 1);
        cal2.set(2007, 11, 30);
        DefaultPeriod period3 = new DefaultPeriod(new DefaultInstant(position4),
                new DefaultInstant(position3));
        cal.set(2008, 0, 13);
        cal2.set(2008, 1, 15);
        DefaultPeriod period4 = new DefaultPeriod(new DefaultInstant(position7),
                new DefaultInstant(position8));
        cal.set(2008, 3, 6);
        cal2.set(2008, 4, 23);
        DefaultPeriod period5 = new DefaultPeriod(new DefaultInstant(new DefaultPosition(cal.getTime())),
                new DefaultInstant(new DefaultPosition(cal2.getTime())));
        cal.set(2008, 5, 1);
        cal2.set(2008, 5, 25);
        DefaultPeriod period6 = new DefaultPeriod(new DefaultInstant(new DefaultPosition(cal.getTime())),
                new DefaultInstant(new DefaultPosition(cal2.getTime())));
        cal.set(2008, 2, 27);
        cal2.set(2008, 8, 12);
        DefaultPeriod period7 = new DefaultPeriod(new DefaultInstant(new DefaultPosition(cal.getTime())),
                new DefaultInstant(new DefaultPosition(cal2.getTime())));

        Event e6 = new Event(instant1, false, "today event", "This event is based on the current time ! the represented event uses an Instant object with position java.util.Date ",
                "http://www.ortech.fr/geo-evenement/images/liste_expo_cadcorp.gif", "",
                "", Priority.LOW, "", Status.IN_PROGRESS, "blue", null);

        Event e7 = new Event(instant2, false, "demo of timeline JSF component", "This event is based on an Instant object with position ISO8601 string ",
                "http://www.hewitt.ca/documents/Image/Demo_button.gif", "",
                "", Priority.NORMAL, "", Status.NOT_STARTED, "green", null);

        Event e8 = new Event(instant3, false, "This is my birthday :D", "This event is based on an Instant object with position CalendarDate ",
                "http://www.autocadre.com/forum/style_images/v2-autocad/birthday.gif", "",
                "", Priority.NORMAL, "", Status.NOT_STARTED, "red", null);

        Event e9 = new Event(instant4, false, "Independence Day", "This event is based on an Instant object with position DateAndTime ",
                "http://mairie-pierrefitte93.fr/pierrefitteactu/2007_news_07_0007/a03_feu_artifice.jpg", "",
                "", Priority.NORMAL, "", Status.NOT_STARTED, "blue", null);

        Event e10 = new Event(instant5, false, "event with a Julian Date ", "This event is based on an Instant object with position Julian Date ",
                "http://www.cameleontv.com/images/news/date_officielle.jpg", "",
                "", Priority.NORMAL, "", Status.NOT_STARTED, "blue", null);

        Event e11 = new Event(instant6, false, "event localized in the Quaternary Period", "This event is based on an Instant object with position as an ordinalPosition ",
                "http://www.jamba.de/storage/view/342/4/th/Theme_JurassicPark_Adbradl.jpg", "",
                "", Priority.NORMAL, "#CCF0F8", Status.NOT_STARTED, "", null);


        Event e12 = new Event(period1, false, "event with TemporalPeriod defined with Calendar.getTime : java.util.Date", "", "", "", "", Priority.LOW, "", Status.DEFAULT, "", null);
        Event e13 = new Event(period2, false, "event with Period defined by ISO8601 String and new java.util.Date", "", "", "", "", Priority.HIGH, "", Status.NOT_STARTED, "", null);
        Event e14 = new Event(period3, false, "event with Period defined by CalendarDate and DateAndTime objects", "", "", "", "", Priority.NORMAL, "", Status.IN_PROGRESS, "", null);
        Event e15 = new Event(period4, true, "event with Period defined with two positions as OrdinalPosition", "", "", "", "", Priority.HIGH, "#FFC4C4", Status.DEFAULT, "#f7896f", null);
        Event e16 = new Event(period5, false, "event with with java.util.Date", "", "", "", "", Priority.LOW, "", Status.IN_PROGRESS, "", null);
        Event e17 = new Event(period6, false, "event with Interval Length", "", "", "", "", Priority.NORMAL, "", Status.DEFAULT, "", null);
        Event e18 = new Event(period7, false, "event with PeriodDuration", "", "", "", "", Priority.LOW, "", Status.IN_PROGRESS, "", null);

        cal.set(2008, 5, 15);
        cal2.set(2008, 5, 29);
        Event e19 = new Event(cal.getTime(), cal2.getTime(), null, false, "adding new functionalities",
                "we would like to add a new event in the timeline via a small component or form with multiples input text components",
                "http://www.lcam.u-psud.fr/english/images/working.gif",
                "", "", Priority.HIGH, "", Status.IN_PROGRESS, "black", null);

        cal.set(2008, 5, 9);
        cal2.set(2008, 5, 23);
        Event e20 = new Event(cal.getTime(), cal2.getTime(), null, false, "Processing to render bandInfo component",
                "The band info component is rendered like a complete rich java server faces component !",
                "http://www.eclipse.org/downloads/images/jee.jpg",
                "", "", Priority.NORMAL, "", Status.DEFAULT, "black", null);

        cal.set(2008, 6, 1);
        cal2.set(2008, 6, 31);
        Event e21 = new Event(cal.getTime(), cal2.getTime(), null, false, "Working with Get capabilities into the timeline component",
                "for mapfaces project it is better to use ws objects and display several informations about getcapabilities",
                "https://booking.campeggi.com/images/map.gif",
                "", "", null, "", null, "black", null);

        cal.set(2008, 1, 1);
        cal2.set(2008, 3, 31);
        Event e22 = new Event(cal.getTime(), cal2.getTime(), null, false, "Finition of Mdweb2 Search module",
                "Application with Lucene into mdweb2 and fixing severals treetable bugs, the tree have multiples wrong values",
                "http://www.datadoctor.org/partition-recovery/images/web-services.jpg",
                "", "", Priority.HIGH, "", Status.DEFAULT, "black", null);

        cal.set(2008, 7, 1);
        cal2.set(2008, 11, 31);
        Event e23 = new Event(cal.getTime(), cal2.getTime(), null, false, "Application with Portlets",
                "Working with Portlets bridge for integration into a portlet container like ExoPlatform",
                "http://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/View-refresh.svg/50px-View-refresh.svg.png",
                "", "", Priority.HIGH, "", Status.NOT_STARTED, "black", null);



        cal2.set(2007, 3, 1);
        cal.set(2008, 11, 20);
        DefaultOrdinalEra ordinalEra4 = new DefaultOrdinalEra(new SimpleInternationalString("Begin"), cal2.getTime(), cal.getTime());
        DefaultOrdinalPosition ordinalPosition9 = new DefaultOrdinalPosition(null, IndeterminateValue.UNKNOWN, ordinalEra4);
        DefaultPosition position9 = new DefaultPosition(ordinalPosition9);
        DefaultInstant instant8 = new DefaultInstant(position9);
        Event e24 = new Event(instant8, false, "Starting works for Geomatys", "This event is based on an Instant object with position as an ordinalPosition for a small period",
                "http://www.volley-st-doulchard.net/images/icones/portail.png", "",
                "", Priority.NORMAL, "#D8FAFB", Status.NOT_STARTED, "", null);
        
        
        //adding events with PeriodDuration
        Calendar c = Calendar.getInstance();
        c.set(2008, 8, 5);
        Position p1 = new DefaultPosition(c.getTime());
        c.set(2008, 8, 15);
        Position p2 = new DefaultPosition(c.getTime());
        Instant i1 = new DefaultInstant(p1);
        Instant i2 = new DefaultInstant(p2);
        Period period = new DefaultPeriod(i1, i2);
        Duration duration = new DefaultPeriodDuration(3600000L);
        Event e25 = new Event(period, duration, false, "Event with PeriodDuration ", "this event is an example of how an event from getcapabilities can be rendered from a basic layer",
                "", "", "", Priority.HIGH, "", Status.NOT_STARTED, "", null);

        events.add(e1);
        events.add(e2);
        events.add(e3);
        events.add(e4);
        events.add(e5);
        events.add(e6);
        events.add(e7);
        events.add(e8);
        events.add(e9);
        events.add(e10);
        events.add(e11);
        events.add(e12);
        events.add(e13);
        events.add(e14);
        events.add(e15);
        events.add(e16);
        events.add(e17);
        events.add(e18);
        events.add(e19);
        events.add(e20);
        events.add(e21);
        events.add(e22);
        events.add(e23);
        events.add(e24);
        events.add(e25);
        
        cal.set(2008, 6, 25, 15, 30, 25);
        centerDate =cal.getTime();

    }
    
      /**
     * creates a new instance of TimeLineBean.     
     */
//    public TimeLineBean() throws ParseException, DatatypeConfigurationException {

//        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
//        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
//        SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
//
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
//        sdf.setTimeZone(TimeZone.getDefault());
//        
//        SortedSet<Date> dates = PeriodUtilities.getDatesFromPeriodDescription("2004-06-06T12:00:00Z/2005-06-20T12:00:00Z/P1D,2007-06-04T12:00:00Z,2007-06-03T12:00:00Z",sdf);
//        Date dateBegin = dates.first();
//        Date dateEnd = dates.last();
//        for (Iterator it = dates.iterator(); it.hasNext();){
//            Date crrt  = (Date)it.next();
//            Event e = new Event(crrt,
//                                       null,
//                                        null,
//                                        false,
//                                        "Time available for the layer : ",
//                                        "This is the duration of the jsf implementation for the component TimeLine",
//                                        "http://simile.mit.edu/images/csail-logo.gif",
//                                        "http://travel.yahoo.com/",
//                                        "",
//                                        Priority.NORMAL,
//                                        "",
//                                        Status.IN_PROGRESS,
//                                        "",
//                                        null);
//            System.out.println(">> Event = "+e);
//                    events.add(e);
//        }
//        System.out.println("eveeeeentes"+events.size());
//        System.out.println("  datebegin = "+dateBegin+"  date2 = "+sdf.parse("2007-06-29T12:00:00Z"));
//        
//        
//        
//        cal.set(2007, 5, 11, 15, 30, 25);
//        centerDate =cal.getTime();
        
        
 //   }

    public TimeLineBean(UILayer uiLayer) throws ParseException, DatatypeConfigurationException {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);

        sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        sdf.setTimeZone(TimeZone.getDefault());
        Layer layer = uiLayer.getLayer();
       SortedSet<Date> dates = PeriodUtilities.getDatesFromPeriodDescription(layer.getTimes(),sdf);
       // SortedSet<Date> dates = PeriodUtilities.getDatesFromPeriodDescription("2004-06-06T12:00:00Z/2005-06-20T12:00:00Z/P1D,2007-06-04T12:00:00Z,2007-06-03T12:00:00Z",sdf);
        Date dateBegin = dates.first();
        Date dateEnd = dates.last();
        for (Iterator it = dates.iterator(); it.hasNext();){
            Date crrt  = (Date)it.next();
            Event e = new Event(crrt,
                                       null,
                                        null,
                                        false,
                                        layer.getName()+" "+layer.getId()+" "+sdf.format(crrt),
                                        "This is the duration of the jsf implementation for the component TimeLine : "+sdf.format(crrt),
                                        sdf.format(crrt),
                                        sdf.format(crrt),
                                        "",
                                        //"http://solardev:8080/ifremerWS/WS/wms?DIM_RANGE="+layer.getUserValueDimension("dim_range")+"&TIME="+sdf.format(crrt)+"&SERVICE=WMS&LAYERS="+layer.getName()+"&EXCEPTIONS=application/vnd.ogc.se_xml&FORMAT=image/png&HEIGHT=25&TRANSPARENT=TRUE&REQUEST=GetMap&BBOX=-180,-90,180,90&WIDTH=25&SRS=EPSG:4326&STYLES=&VERSION=1.1.1",
                                        Priority.NORMAL,
                                        "",
                                        Status.IN_PROGRESS,
                                        "",
                                        layer.getMapLayer());
            System.out.println(">> Event = "+e);
                    events.add(e);
        }
        System.out.println("eveeeeentes"+events.size());
        System.out.println("  datebegin = "+dateBegin+"  date2 = "+sdf.parse("2007-06-29T12:00:00Z"));
        if(layer.getUserValueTime()==null)
            centerDate =PeriodUtilities.getDateFromString("2007-06-06T12:00:00Z");
        else
            centerDate =PeriodUtilities.getDateFromString(layer.getUserValueTime());
    }
    /*
     * In our case the varTreeProperty id the id of the layer
     * 
     * */
    public TimeLineBean(String eventsString,String userValueDate,Layer layer) throws ParseException, DatatypeConfigurationException {

        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);

        sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        sdf.setTimeZone(TimeZone.getDefault());
        
        SortedSet<Date> dates = PeriodUtilities.getDatesFromPeriodDescription(eventsString,sdf);
        Date dateBegin = dates.first();
        Date dateEnd = dates.last();
        for (Iterator it = dates.iterator(); it.hasNext();){
            Date crrt  = (Date)it.next();
            Event e = new Event(crrt,
                                       null,
                                        null,
                                        false,
                                        layer.getName(),
                                        "This is the duration of the jsf implementation for the component TimeLine : "+sdf.format(crrt),
                                        sdf.format(crrt),
                                        "http://demo.geomatys.fr/seagis/WS/wms?DIM_RANGE=-3.0,40.0&TIME=2007-06-20T12:00:00Z&SERVICE=WMS&LAYERS="+layer.getName()+"&EXCEPTIONS=application/vnd.ogc.se_xml&ELEVATION=5.0&FORMAT=image/png&HEIGHT=25&TRANSPARENT=TRUE&REQUEST=GetMap&BBOX=-3.0056260600743E7,-2.0037507067162E7,3.0056260600743E7,2.0037507067162E7&WIDTH=25&SRS=EPSG:3395&STYLES=&VERSION=1.1.1",
                                        "http://demo.geomatys.fr/seagis/WS/wms?DIM_RANGE=-3.0,40.0&TIME=2007-06-20T12:00:00Z&SERVICE=WMS&LAYERS="+layer.getName()+"&EXCEPTIONS=application/vnd.ogc.se_xml&ELEVATION=5.0&FORMAT=image/png&HEIGHT=25&TRANSPARENT=TRUE&REQUEST=GetMap&BBOX=-3.0056260600743E7,-2.0037507067162E7,3.0056260600743E7,2.0037507067162E7&WIDTH=25&SRS=EPSG:3395&STYLES=&VERSION=1.1.1",
                                        Priority.NORMAL,
                                        "",
                                        Status.IN_PROGRESS,
                                        "",
                                        layer.getMapLayer());
             events.add(e);
        }
        
        centerDate =cal.getTime();//PeriodUtilities.getDateFromString(userValueDate);
        
        
    }
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

/*    public static void main(String[] args) throws ParseException, DatatypeConfigurationException {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        String DATE_FORMAT = "EEE MMM d yyyy HH:mm:ss 'GMT'Z";
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);

        sdf.setTimeZone(TimeZone.getTimeZone("GMT+3"));

        Date dateBegin = sdf.parse("Sat Apr 29 2006 18:08:50 GMT+0300");
        String s = sdf.format(cal.getTime());
        Date dateEnd = sdf.parse(s);

        Date dateEnd2 = cal.getTime();
        Date date = new Date();
        System.out.println(">>>>> ===== dateBegin = " + dateBegin.toString() + "   sdf.format = " + sdf.format(dateBegin));
        System.out.println(">>>>> ===== dateEnd = " + dateEnd.toString() + "   sdf.format = " + sdf.format(dateEnd) + "   dateEnd2 = " + sdf.format(dateEnd2));
        System.out.println(">>>>>> date = " + sdf.format(date));


        String DATE_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
        String DATE_FORMAT3 = "yyyy-MM-dd";
        SimpleDateFormat sdf2 = new java.text.SimpleDateFormat(DATE_FORMAT2, Locale.ENGLISH);
        SimpleDateFormat sdf3 = new java.text.SimpleDateFormat(DATE_FORMAT3, Locale.ENGLISH);
        sdf2.setTimeZone(TimeZone.getTimeZone("GMT-8"));
        sdf3.setTimeZone(TimeZone.getTimeZone("GMT-8"));
        Date date1 = sdf2.parse("2003-02-13T12:28:55.456GMT-08:00");
        Date date2 = sdf3.parse("2003-02-13");
        System.out.println("   testing ISO8601 = " + sdf2.format(date1));
        System.out.println(" ============== " + sdf3.format(date2));

        cal.set(4713, 0, 1);
        //cal.set(Calendar.ERA, GregorianCalendar.AD);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> " + cal.getTime() + " ==== " + cal.toString());

        System.out.println("========== " + new Date());*/
        //TimeLineBean tm = new TimeLineBean();
        
//    }

    public Date getCenterDate() {
        return centerDate;
    }

    public void setCenterDate(Date centerDate) {
        this.centerDate = centerDate;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }
    
    public static int compareTo(Instant current, Instant that) {
        if (that==null)
            throw new IllegalArgumentException("Provided temporal object is null");
        final RelativePosition pos= current.relativePosition(that);
        if(pos==null)
            throw new ClassCastException("The provided object cannot be compared to this one");
        if(pos==RelativePosition.AFTER)
            return +1;
        if(pos==RelativePosition.BEFORE)
            return -1;
       
        else return 0;
    }
    
    public static void main(String ...s){
        Date time1 = new Date(1000L);
        Date time2 = new Date(0L);
        Date time3 = new Date(2000L);
        
        Instant instant1 = new DefaultInstant(new DefaultPosition(time1));
        Instant instant2 = new DefaultInstant(new DefaultPosition(time2));
        Instant instant3 = new DefaultInstant(new DefaultPosition(time3));
        
        System.out.println(">>>> "+TimeLineBean.compareTo(instant2, instant1));
    }

}

