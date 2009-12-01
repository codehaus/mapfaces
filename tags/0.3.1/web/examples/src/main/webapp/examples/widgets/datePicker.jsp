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
            <title>MapFaces DatePicker demo</title>
        </head>
        <body>
            <h1><h:outputText value="MapFaces Datepicker widget demo"/></h1>
            <h:form id="formId">
                <h:panelGrid columns="2">
                    <h:selectBooleanCheckbox value="#{datepickerbean.loadMootools}"/>
                    <h:outputText value="load mootools scripts"/>
                    <h:selectBooleanCheckbox value="#{datepickerbean.loadJs}"/>
                    <h:outputText value="load timepicker scripts"/>
                    <h:selectBooleanCheckbox value="#{datepickerbean.rendered}"/>
                    <h:outputText value="render the component"/>
                    <h:selectBooleanCheckbox value="#{datepickerbean.loadCss}"/>
                    <h:outputText value="loadCss"/>
                </h:panelGrid>
                <h:commandButton value="Submit"/>
                <wf:Datepicker id="calendar"
                               value="#{datepickerbean.enteredDate}"
                               style="width:150px;color:#5C6E74;font-size:13px;border:1px solid #BECCD5;"
                               rendered="#{datepickerbean.rendered}"
                               loadCss="#{datepickerbean.loadCss}"
                               loadJs="#{datepickerbean.loadJs}"
                               loadMootools="#{datepickerbean.loadMootools}"/>
            </h:form>
        </body>
    </html>
</f:view>