<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>
<%@taglib prefix="mf-model" uri="http://mapfaces.org/taglib-models"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
   <html> 
       <head>
           <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       </head>
       <body>   
       <h:form>
            <mf-model:Context minifyJS="false" debug="true" service="data/context/owc030.xml">
                   <mf:Abstract></mf:Abstract>
                   <mf:MapPane  style="position:relative;" ></mf:MapPane>
                   <mf:ButtonBar styleClass="mfButtonBar horizontal"  style="left:550px;"></mf:ButtonBar>
                   <mf:CursorTrack style="position:relative;" showPX="true" showXY="true" showLatLon="true" showDMS="true" showDM="true" ></mf:CursorTrack>
                   <mf:MapSize title=" Map size : " itemsLabels="300,150/300,300/600,300/600,600/1000,500/1000,1000" itemsValues="300,150/300,300/600,300/600,600/1000,500/1000,1000" style="position:relative;"></mf:MapSize>
                   <mf:LayerControl minifyJS="false" style="position: relative;width:500px;height: 400px;" ></mf:LayerControl>
                   <mf:TimeLine  minifyJS="false" style="height: 300px; border: 1px solid #aaa;width:55%;position: relative; font-size:0.8em;top:0px;left:0px;" >
                        <mf:HotZoneBandInfo id="band0" width="75" intervalUnit="WEEK" intervalPixels="100" showEventText="false" date="#{timelineBean.centerDate}" timeZone="-5" theme="OLanceTheme" inputInterval="true"/>
                        <mf:HotZoneBandInfo id="band1" width="25" intervalUnit="YEAR" intervalPixels="200" date="#{timelineBean.centerDate}" showEventText="false" trackGap="0.2" trackHeight="0.5" inputInterval="true"/>
                   </mf:TimeLine>
            </mf-model:Context>
       </h:form>        
      </body>
   </html>
</f:view>