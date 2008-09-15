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
           <iframe id='target_upload' name='target_upload' src='' style='display: none'></iframe>
               <%--a4j:log/--%>
               <%--a4j:actionparam  name="w" value="screen.width" 
                        assignTo="#{userBean.screenWidth}" noEscape="true" />
                <a4j:actionparam name="h" value="screen.height"
                        assignTo="#{userBean.screenHeight}" noEscape="true" /--%>
               <h1><h:outputText value="MapFaces" /></h1>                
               <br/>
               <h:form id="form"  >
               <%--h:outputText   value="Map size :"/>
                <h:selectOneMenu id="window">
                       <f:selectItem itemLabel="300,150" itemValue="300,150"></f:selectItem>
                       <f:selectItem itemLabel="300,300" itemValue="300,300"></f:selectItem>
                       <f:selectItem itemLabel="600,300" itemValue="600,300"></f:selectItem>
                       <f:selectItem itemLabel="600,600" itemValue="600,600"></f:selectItem>
                       <f:selectItem itemLabel="1000,500" itemValue="1000,500"></f:selectItem>
                       <f:selectItem itemLabel="1000,1000" itemValue="1000,1000"></f:selectItem>
                        <a4j:support id="ajaxWindow" event="onchange" ajaxSingle="true" >
                            <f:param name="synchronized" value="true"/>
                            <f:param name="refresh" value="mappane"/>
                        </a4j:support>
                   </h:selectOneMenu--%>
                 <%--  <br/>
               <h:outputText   value="Zoom to  : "/>
                <h:selectOneMenu id="bbox">
                       <f:selectItem itemLabel="worldMercator" itemValue="-20037507.067161847,-20037507.067161847,20037507.067161847,20037507.067161847"></f:selectItem>
                       <f:selectItem itemLabel="World" itemValue="-179,-89,179,89"></f:selectItem>
                       <f:selectItem itemLabel="France" itemValue="-5.29,40.65,10.4,51.82"></f:selectItem>
                       <f:selectItem itemLabel="Europe" itemValue="-25.1,34.9,35,71.3"></f:selectItem>
                       <f:selectItem itemLabel="Africa" itemValue="-20.2,-37.6,53.4,35.75"></f:selectItem>
                       <f:selectItem itemLabel="Asia" itemValue="40,-10,180,83.5"></f:selectItem>
                       <f:selectItem itemLabel="Australia" itemValue="111.22,-45.73,155.72,-8.88"></f:selectItem>
                       <f:selectItem itemLabel="Tasmania" itemValue="143.83482400000003,-43.648056,148.47914100000003,-39.573891"></f:selectItem> 
                       <f:selectItem itemLabel="North America" itemValue="-168.5,18,-50.4,85.7"></f:selectItem>
                       <f:selectItem itemLabel="South America" itemValue="-84.9,-57.6,-32.4,13.7"></f:selectItem>
                       <a4j:support id="ajaxZoomTo"  ajaxSingle="true" >
                            <f:param name="synchronized" value="true"/>
                            <f:param name="refresh" value="MapFaces_Layer_WMS_1,MapFaces_Layer_WMS_0"/>
                       </a4j:support>
                   </h:selectOneMenu>--%>
                <%--h:selectOneMenu id="bbox">
                    <f:selectItem itemLabel="world" itemValue="-20037507.067161847,-20037507.067161847,20037507.067161847,20037507.067161847"></f:selectItem>
                    <f:selectItem itemLabel="North West" itemValue="-20037507.067161847,0,0,20037507.067161847"></f:selectItem>
                    <f:selectItem itemLabel="North East" itemValue="0,0,20037507.067161847,20037507.067161847"></f:selectItem>
                    <f:selectItem itemLabel="South East" itemValue="0,-20037507.067161847,20037507.067161847,0"></f:selectItem>
                    <f:selectItem itemLabel="South West" itemValue="-20037507.067161847,-20037507.067161847,0,0"></f:selectItem>
                    <f:selectItem itemLabel="France" itemValue="-639194.5721,5216040.26,939224.678,6716190.79"></f:selectItem>
                    <a4j:support event="onchange" reRender="mappane"/> 
               </h:selectOneMenu--%>
               <%--<br/>
               <h:outputText   value="Update  elevation :  :"/>
                   <h:selectOneMenu id="elevation">
                       <f:selectItem itemLabel="0" itemValue="0"></f:selectItem>
                       <f:selectItem itemLabel="100" itemValue="100"></f:selectItem>
                       <f:selectItem itemLabel="200" itemValue="200"></f:selectItem>
                       <f:selectItem itemLabel="250" itemValue="250"></f:selectItem>
                       <f:selectItem itemLabel="300" itemValue="300"></f:selectItem>
                       <f:selectItem itemLabel="400" itemValue="400"></f:selectItem>
                       <f:selectItem itemLabel="500" itemValue="500"></f:selectItem>
                       <f:selectItem itemLabel="1000" itemValue="1000"></f:selectItem>
                       <f:selectItem itemLabel="5000" itemValue="5000"></f:selectItem>
                       <a4j:support  id="ajaxElevation" event="onchange" reRender="MapFaces_Layer_WMS_1" ajaxSingle="true"/> 
                   </h:selectOneMenu>     
                   <br/>
               <h:outputText   value="Modify title :  :"/>
                   <h:inputText id="title" value="World map">
                       <a4j:support id="jeanclaude" event="onkeyup" reRender="abstract" ajaxSingle="true"  /> 
                   </h:inputText--%>
           <%--mf-model:Context id="owsContext" service="data/context/coriolisMercator.xml" debug="true">
                   <mf:Abstract id="abstract" debug="true" styleClass="abstract"></mf:Abstract>
                   <mf:MapPane id="mappane" styleClass="mappane" navigation="true" debug="true"></mf:MapPane>                             
                   <mf:ButtonBar id="bar" debug="true" zoomIn="true" zoomOut="true"></mf:ButtonBar>
           </mf-model:Context--%>           
           <%--mf-model:Context id="owsContext" service="data/context/tasmaniaWfsOwc.xml" debug="true">
                   <mf:Abstract id="abstract" debug="true" styleClass="abstract"></mf:Abstract>
                   <mf:MapPane id="mappane" styleClass="mappane" navigation="true" debug="true"></mf:MapPane>                   
           </mf-model:Context--%>
           <%--mf-model:Context id="owsContext" service="data/context/blueMarble.xml" debug="true">
                   <mf:Abstract id="abstract" debug="true" styleClass="abstract">
                   </mf:Abstract>
                   <mf:MapPane id="mappane" styleClass="mappane" navigation="true" debug="true" >
                   </mf:MapPane>                   
                   <mf:ButtonBar id="bar"  styleClass="mfButtonBar horizontal" debug="true" zoomIn="false" zoomOut="false" pan="false" zoomMaxExtent="false" history="false" ></mf:ButtonBar>
           </mf-model:Context--%>
           <%--mf-model:Context id="owsContext" service="data/context/owc030.xml" debug="true" >
                   <mf:Abstract id="abstract" debug="true" styleClass="abstract"></mf:Abstract>
                   <mf:MapPane id="mappane"   navigation="true" debug="true"> <a4j:status>
               <f:facet name="face">
                <h:graphicImage  value="img/Loading.gif"/>
                </f:facet>
           </a4j:status></mf:MapPane>                   
                   <mf:ButtonBar id="bar" panEffect="true"  styleClass="mfButtonBar horizontal"  ></mf:ButtonBar>
                   <mf:CursorTrack id="cursorTrack" style="position:absolute;left:500px;" debug="true" showPX="true" showXY="true" showLatLon="true" showDMS="true" showDM="true" ></mf:CursorTrack>  
           </mf-model:Context--%>
           <mf-model:Context id="owsContext" debug="true" service="data/context/coriolisMercator.xml">
                   <%--mf:Abstract id="abstract" debug="true" ></mf:Abstract--%>
                   <mf:MapPane id="mappane"  debug="true"  navigation="true" style="left:50%;position:absolute;" ></mf:MapPane>
                   <mf:ButtonBar id="bar" debug="true" styleClass="mfButtonBar horizontal" style="position:relative;"></mf:ButtonBar>
                   <%--mf:CursorTrack id="cursorTrack" debug="true" style="position:absolute;left:400px;" showPX="true" showXY="true" showLatLon="true" showDMS="true" showDM="true" ></mf:CursorTrack--%>
                   <%--mf:MapSize id="mapSize"  debug="true" title=" Map size : " itemsLabels="300,150/300,300/600,300/600,600/1000,500/1000,1000" itemsValues="300,150/300,300/600,300/600,600/1000,500/1000,1000" style="position:absolute;left:800px;"></mf:MapSize>
                   --%>
                   <mf:LayerControl id="lcc" debug="true" style="position: absolute; top: 150px;" ></mf:LayerControl>
                   <mf:TimeLine id="timeline" style="height: 300px; border: 1px solid #aaa;width:1000px;margin:0pt auto;position: relative; font-size:0.8em;top: 510px;" >
                        <mf:HotZoneBandInfo id="band0" width="75" intervalUnit="WEEK" intervalPixels="100" showEventText="true" date="#{timelineBean.centerDate}" timeZone="-5" theme="OLanceTheme" inputInterval="true"/>
                        <mf:HotZoneBandInfo id="band1" width="25" intervalUnit="YEAR" intervalPixels="200" date="#{timelineBean.centerDate}" showEventText="false" trackGap="0.2" trackHeight="0.5" inputInterval="true"/>
                   </mf:TimeLine>
            </mf-model:Context>
          
           </h:form>        
          </body>
       </html>
   </f:view>