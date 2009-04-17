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
                <mf-model:Context service="data/context/blueMarble.xml">                        
                    <mf:MapPane navigation="true"></mf:MapPane>
                    <mf:MapSize title=" Map size : " itemsLabels="300,150/300,300/600,300/600,600/1000,500/1000,1000" itemsValues="300,150/300,300/600,300/600,600/1000,500/1000,1000" ></mf:MapSize>
                </mf-model:Context>
            </h:form>
        </body>
     </html>
 </f:view>