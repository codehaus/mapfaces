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
        <body style="margin:0px;">                   
            <h:form>               
                <mf-model:Context service="data/context/blueMarble.xml">                       
                   <mf:MapPane  style="width:100%;height:100%;position:absolute;border:none;"></mf:MapPane>
                   <mf:ButtonBar  style="position:absolute;" ></mf:ButtonBar>
                </mf-model:Context>
            </h:form>
         </body>
    </html>
</f:view>