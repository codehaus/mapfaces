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
                <mf-model:Context id="geoserverStyledWMS111" debug="true" service="data/context/geosTasmaniaStyled.xml">
                    <mf:MapPane debug="true"></mf:MapPane>
                </mf-model:Context>
                 <mf-model:Context id="constellationStyledWMS111" debug="true" service="data/context/cstlTasmaniaStyled.xml">
                    <mf:MapPane debug="true"></mf:MapPane>
                </mf-model:Context>
                <%--mf-model:Context id="geoserverStyledWMS130" debug="true" service="data/context/geosTasmaniaStyled130.xml">
                    <mf:MapPane debug="true"></mf:MapPane>
                </mf-model:Context--%>
                 <mf-model:Context id="constellationStyledWMS130" debug="true" service="data/context/cstlTasmaniaStyled130.xml">
                    <mf:MapPane debug="true"></mf:MapPane>
                </mf-model:Context>
            </h:form>
        </body>
    </html>
</f:view>