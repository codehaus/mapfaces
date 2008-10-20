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
            <h1><h:outputText value="MapFaces" /></h1>                
            <br/>
            <h:form id='form'>
                <mf-model:Context  debug="true" service="data/context/ifremer.xml">
                    <mf:MapPane debug="true"></mf:MapPane>
                    <mf:ButtonBar styleClass="mfButtonBar horizontal"></mf:ButtonBar>
                    <mf:CursorTrack style="left:600px;position:absolute;" showPX="true" showXY="true" showLatLon="true" showDMS="true" showDM="true" ></mf:CursorTrack> 
                </mf-model:Context>
            </h:form>
            <a style="top: 150px; left: 650px; position: absolute;" onclick="A4J.AJAX.Poll('j_id_jsp_1066680679_0','j_id_jsp_1066680679_2',{'affected':['j_id_jsp_1066680679_2:j_id_jsp_1066680679_4_Poll'] ,'pollId':'j_id_jsp_1066680679_2:j_id_jsp_1066680679_4_Poll','single':true,'parameters':{'j_id_jsp_1066680679_2:j_id_jsp_1066680679_4_Poll':'j_id_jsp_1066680679_2:j_id_jsp_1066680679_4_Poll'} ,'pollinterval':500,'eventsQueue':'j_id_jsp_1066680679_2:j_id_jsp_1066680679_4_Poll','timeout':10000,'requestDelay':500,'actionUrl':'/mapfaces/examples/basic.jsf'} );">start</a>
<a style="top: 180px; cursor:pointer;left: 650px; position: absolute;" onclick="A4J.AJAX.StopPoll('j_id_jsp_1066680679_2:j_id_jsp_1066680679_4_Poll');">stop</a>
        </body>
    </html>
</f:view>