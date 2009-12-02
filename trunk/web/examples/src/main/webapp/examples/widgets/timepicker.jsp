<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="wf" uri="http://widget-mapfaces.org/taglib"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>MapFaces TimePicker demo</title>
        </head>
        <body>
            <h1><h:outputText value="MapFaces Timepicker widget demo"/></h1>
            <h:form id="formId">
                <h:panelGrid columns="2">
                    <h:selectBooleanCheckbox value="#{timepickerbean.loadMootools}"/>
                    <h:outputText value="load mootools scripts"/>
                    <h:selectBooleanCheckbox value="#{timepickerbean.loadJs}"/>
                    <h:outputText value="load timepicker scripts"/>
                    <h:selectBooleanCheckbox value="#{timepickerbean.rendered}"/>
                    <h:outputText value="render the component"/>
                    <h:selectBooleanCheckbox value="#{timepickerbean.targetInputActif}"/>
                    <h:outputText value="targetInput"/>
                </h:panelGrid>
                <h:commandButton value="Submit"/>
                <wf:timepicker id="timepickerId"
                                value="#{timepickerbean.date}"
                                loadMootools="#{timepickerbean.loadMootools}"
                                loadJs="#{timepickerbean.loadJs}"
                                rendered="#{timepickerbean.rendered}"
                                targetInput="formId:inputTargeted"/>

                <h:panelGroup style="margin-top:30px;" rendered="#{timepickerbean.targetInputActif}">
                    <h:inputText id="inputTargeted" />
                </h:panelGroup>
            </h:form>
        </body>
    </html>
</f:view>