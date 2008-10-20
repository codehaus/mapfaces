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
            <h:form>               
                <mf-model:Context service="data/context/owc030Cut.xml">                       
                    <mf:MapPane ></mf:MapPane>
                    <mf:ButtonBar styleClass="mfButtonBar horizontal"></mf:ButtonBar>
                </mf-model:Context>
            </h:form>
             <a style="top: 150px; cursor:pointer;left: 650px; position: absolute;" onclick="A4J.AJAX.Poll('j_id_jsp_1409544346_0','j_id_jsp_1409544346_2',{'affected':['j_id_jsp_1409544346_2:j_id_jsp_1409544346_4_Poll'] ,'pollId':'j_id_jsp_1409544346_2:j_id_jsp_1409544346_4_Poll','ignoreDupResponses':true,'single':true,'parameters':{'j_id_jsp_1409544346_2:j_id_jsp_1409544346_4_Poll':'j_id_jsp_1409544346_2:j_id_jsp_1409544346_4_Poll'} ,'pollinterval':400,'eventsQueue':'j_id_jsp_1409544346_2:j_id_jsp_1409544346_4_Poll','timeout':10000,'actionUrl':'/mapfaces/examples/multipleWmsLayers.jsf'} );
3//">start</a><a style="top: 180px; cursor:pointer;left: 650px; position: absolute;" onclick="A4J.AJAX.StopPoll('j_id_jsp_1409544346_2:j_id_jsp_1409544346_4_Poll');">stop</a>
        </body>
    </html>
</f:view>