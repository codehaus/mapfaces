<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="mf" uri="http://mapfaces.org/taglib" %>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        </head>
        <body>
            <h:form id="form">   
                <h:commandButton value="Submit"/>
                <mf:TimeLine minifyJS="false" style="top: 100px;width:100%;height: 150px;border: 1px solid #aaa;position: absolute; font-size:0.8em;" value="#{timelineBean.events}" >
                           <mf:HotZoneBandInfo id="band0" width="70" intervalUnit="WEEK" intervalPixels="100" showEventText="true" date="#{timelineBean.centerDate}" timeZone="-5" theme="OLanceTheme" inputInterval="true"/>
                           <mf:HotZoneBandInfo id="band1" width="30" intervalUnit="YEAR" style="height:100px;" intervalPixels="200" date="#{timelineBean.centerDate}" showEventText="false" trackGap="0.2" trackHeight="0.5" inputInterval="true"/>
                </mf:TimeLine>
            </h:form>
        </body>
    </html>
</f:view>