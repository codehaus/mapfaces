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


 <%--mf-model:Context id="owsContext" service="data/context/map.xml">
                            <div style="border: 3px ridge rgb(214, 227, 242); left: 340px; bottom: 0pt; cursor: pointer; margin-bottom: 5px; position: absolute; right: 5px; top: 5px; z-index: 0; height:450px;">
                                <mf:MapPane id="mappane" style="width:100%;height:100%;position:absolute;z-index:0;"></mf:MapPane>
                            </div>
                            <mf:ButtonBar id="bar" styleClass="mfButtonBar horizontal"  style="left:370px;"></mf:ButtonBar>
                            
                            <mf:CursorTrack id="cursorTrack" style="position:absolute;left:370px; top:420px; background-color:white;padding:5px;opacity:0.7;margin:5px;" showDMS="true" showLatLon="false" >
                            </mf:CursorTrack>
                            <div style="border: 3px ridge rgb(214, 227, 242); width:327px;height:450px; left:5px;top:5px; bottom: 0px; margin-bottom: 0pt; position: absolute; z-index: 0; ">
                                <mf:LayerControl id="lcc" style="height:450px;position:relative;width:327px;" 
                                                 debug="false"
                                                 styleTreeTable="height:450px;width:327px;background:#ECF1F8;" 
                                                 styleTreePanel="background:#ECF1F8;height:450px;overflow:hidden;width:327px;"
                                                 widthTreeColumn="190"
                                                 widthOpacityColumn="45"
                                                 widthElevationColumn="50" 
                                                 titlePanel="Layers control panel" 
                                                 headerTreeColumn="LAYERS"
                                                 hideElevationColumn="true" styleEvenLines="background:#fff1c7;" styleOddLines="background:#d6e3f3;">
                                </mf:LayerControl>
                            </div>
                            
                            
                            <div style="border: 3px ridge rgb(214, 227, 242); height:170px; right: 5px; top: 550px;  left: 5px; bottom: 5px; margin-bottom: 0pt; position: absolute; z-index: 0; ">
                                <mf:TimeLine id="timeline" style=" position:relative; width:100%%; height:170px; font-size:0.8em; " inputDate="false" theme="ArcheoTheme" synchronizeBands="true" dynamicBands="true"></mf:TimeLine>
                            </div>
                        </mf-model:Context--%>
                        