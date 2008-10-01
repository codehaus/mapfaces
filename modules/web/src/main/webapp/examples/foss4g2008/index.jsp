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
            <link rel="stylesheet" href="../../resource/skin/foss4g2008/style.css" type="text/css"  />
            <style type="text/css">
                <!--
                body {
                    margin-left: 0px;
                    margin-top: 0px;
                    margin-right: 0px;
                    margin-bottom: 0px;
                    background-color: #F8F4F1;
                }
                a:link {
                    color: #333333;
                }
                a:visited {
                    color: #333333;
                }
                -->
            </style>
        </head>
        <body>   
            <iframe id='target_upload' name='target_upload' src='' style='display: none'></iframe>
            <div id="wrap">
            <div id="header-main">
                <div id="header-left"></div>
                <div id="header-right">
                    <h2 id="logo-slogan">Free and Open Source Software for Geospatial</h2>
                </div>
            </div>
            <div id="separate"></div>
            <div id="content-wrap">
                <h:form id="form">
                    <mf-model:Context id="owsContext" service="data/context/ifremer.xml"> 
                       <mf:MapPane id="mappane" debug="true" style="left:500px;width:69%;height:500px;position:absolute;"></mf:MapPane>
                       <mf:ButtonBar id="bar" styleClass="mfButtonBar horizontal"  style="left:550px;"></mf:ButtonBar>                            
                       <mf:CursorTrack id="cursorTrack" style="position:absolute;left:500px; top:450px; background-color:white;padding:5px;opacity:0.7;margin:5px;" showDMS="true" showLatLon="false" ></mf:CursorTrack>
                       <mf:LayerControl id="lcc" style="position: absolute;left:0px;width:auto;"></mf:LayerControl>
                       <mf:TimeLine id="timeline" style="top: 643px;width:100%;height: 150px;border: 1px solid #aaa;position: absolute; font-size:0.8em;" >
                           <mf:HotZoneBandInfo id="band0" width="70" intervalUnit="WEEK" intervalPixels="100" showEventText="true" date="#{timelineBean.centerDate}" timeZone="-5" trackHeight="1" theme="OLanceTheme" inputInterval="true"/>
                           <mf:HotZoneBandInfo id="band1" width="30" intervalUnit="YEAR"  intervalPixels="200" date="#{timelineBean.centerDate}" showEventText="false" trackGap="0.2" trackHeight="0.5" inputInterval="true"/>
                       </mf:TimeLine>
                    </mf-model:Context>
                </h:form>   
            </div>
            </div>
        </body>
    </html>
</f:view>