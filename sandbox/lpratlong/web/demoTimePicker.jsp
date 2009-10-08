<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="tp" uri="http://timepicker.com"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
            <link href="css/timepicker_style.css" type="text/css" rel="stylesheet" />
        </head>
        <body>
            <h1><h:outputText value="JavaServer Faces"/></h1>
            <h:form id="main_form">
                <tp:timepicker id="monTp" value="#{horloge.date}" loadMootools="#{horloge.loadMootools}" loadJs="#{horloge.loadJs}" rendered="true" outputTop="#{horloge.outputTop}" />
            </h:form>
        </body>
    </html>
</f:view>
