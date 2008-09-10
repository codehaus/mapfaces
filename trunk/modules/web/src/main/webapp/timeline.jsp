<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="mf" uri="http://mapfaces.org/taglib" %>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TimeLine example</title>
        
        <style>
            body{margin:0px; background:#f3f4e9;padding:10pt}
            
            
            
        </style>
    </head>
    <body>
        <f:view>
            
            <h:form id="form">
                <h1><h:outputText value="JSF Component TimeLine Demo" /></h1>                      
                
                <h:commandButton value="Submit"/>
                <mf:TimeLine id="mytimeline" style="height: 500px; border: 1px solid #aaa;width:1200px;margin:0pt auto;" value="#{mybean.events}">

                    <mf:HotZoneBandInfo id="band0" width="75" intervalUnit="WEEK" intervalPixels="100" showEventText="true" timeZone="-5" theme="OLanceTheme" date="#{mybean.centerDate}" inputInterval="true"/>
                    <mf:HotZoneBandInfo id="band1" width="25" intervalUnit="YEAR" intervalPixels="200" showEventText="false" trackGap="0.2" trackHeight="0.5" date="#{mybean.centerDate}" inputInterval="true"/>

                </mf:TimeLine>
                <br/>
                <br/>
                <%--mf:TimeLine style="height: 300px; border: 1px solid #aaa;width:600px;margin:0pt auto;" value="#{mybean.events}" >
                    <mf:BandInfo width="75" intervalUnit="WEEK" intervalPixels="100" showEventText="true" timeZone="0" theme="OLanceTheme" date="#{mybean.centerDate}" inputInterval="#{mybean.test}"/>
                    <mf:BandInfo width="25" intervalUnit="YEAR" intervalPixels="200" showEventText="false" trackGap="0.2" trackHeight="0.5" date="#{mybean.centerDate}" inputInterval="#{mybean.test}"/>
                </mf:TimeLine--%>


<%--h:inputText id="inputdate" value="2008-04-05T16:15:30" onchange="centerToDate();"/>
<script>
        function centerToDate(){
        var valdate = document.getElementById('form:inputdate').value;
        var dateInput = Timeline.DateTime.parseIso8601DateTime(valdate);
if (dateInput instanceof Date) {
        mytimeline_tl.getBand(0).setCenterVisibleDate(dateInput);

}
</script--%>

<%--h:commandButton value="show max" onclick="alert(tl.getBand(0).getMaxVisibleDate());return false;"/>
                <h:commandButton value="show min" onclick="alert(tl.getBand(0).getMinVisibleDate());return false;"/--%>
                
                
                <!--script>
                
eventSource._events._events.elementAt(0)._text='je teste un titre2';
eventSource._events._events.elementAt(0)._description='description2';

var dateEvent = new Date();
//dateEvent.setTime(dateEvent.getTime() + ((Math.floor(Math.random()*41) - 20) * 24 * 60 * 60 * 1000));

//alert(Timeline.DateTime.parseGregorianDateTime(dateEvent.toUTCString()));

var date = Timeline.DateTime.parseGregorianDateTime("Thu Jun 19 2008 08:08:50 GMT+0200");

var date2 = Timeline.DateTime.parseIso8601DateTime("2008-04-05T16:15:30");
//date2.setTime(Date.now());

</script-->

<!--script>
        var eventsource = mytimeline_tl.getBand(0).getEventSource();
        var iterator = eventsource.getAllEventIterator();
        //while(iterator.hasNext()){
                var event = iterator.next();	
alert(event.getStart());
        //}
</script-->

            </h:form>
        </f:view>
    </body>
</html>