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
            <h:commandButton value="submit" style="margin-left:60px;z-index:1000;position:relative;"/>
            <mf-model:Context id="mainMap" minifyJS="false" debug="true" service="data/context/owc030.xml">
                   <mf:Abstract></mf:Abstract>
                   <mf:MapPane  style="position:relative;" ></mf:MapPane>
                   <mf:ButtonBar styleClass="mfButtonBar horizontal"  style="position:relative;"></mf:ButtonBar>
                   <mf:CursorTrack style="position:relative;margin-top: 50px; " showPX="true" showXY="true"
                   showLatLon="true" showDMS="true" showDM="true" ></mf:CursorTrack>
                   <mf:MapSize title=" Map size : " itemsLabels="300,150/300,300/600,300/600,600/1000,500/1000,1000"
                   itemsValues="300,150/300,300/600,300/600,600/1000,500/1000,1000" style="position:relative;"></mf:MapSize>
                   <mf:LayerControl  style="position: relative;width:500px;height: 400px;" ></mf:LayerControl>
                   <mf:TimeLine id="timeline"  height="126" inputDate="true" theme="ArcheoTheme" synchronizeBands="true" dynamicBands="true" activeControl="true" styleControlPanel="border: 3px ridge rgb(214, 227, 242); border-right:none; background: white; height: 60px; float: left; width: 200px;" minifyJS="false" enableBandsInput="true">
                   </mf:TimeLine>
            </mf-model:Context>
            <mf-model:Context scriptaculous="false" mootools="false" minifyJS="false"  debug="true" service="data/context/ifremer.xml">
                            <mf:LocatorMap targetContextCompId="mainMap"  style="width:300px;height:150px;"/>              
            </mf-model:Context>
       </h:form>        
      </body>
   </html>
</f:view>