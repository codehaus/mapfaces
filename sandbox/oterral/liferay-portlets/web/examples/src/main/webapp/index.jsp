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
            <title>MapFaces AutoCompleter demo</title>
        </head>
        <body>
            <h1><h:outputText value="MapFaces Autocompleter widget demo"/></h1>
            <h:form id="formId">
                <h:panelGrid columns="2">
                    <h:selectBooleanCheckbox value="#{autocompleterbean.loadMootools}"/>
                    <h:outputText value="load mootools scripts"/>
                    <h:selectBooleanCheckbox value="#{autocompleterbean.loadJs}"/>
                    <h:outputText value="load timepicker scripts"/>
                    <h:selectBooleanCheckbox value="#{autocompleterbean.rendered}"/>
                    <h:outputText value="render the component"/>
                    <h:selectBooleanCheckbox value="#{autocompleterbean.loadCss}"/>
                    <h:outputText value="loadCss"/>
                    <h:selectBooleanCheckbox value="#{autocompleterbean.multiple}"/>
                    <h:outputText value="multiple"/>
                    <h:selectBooleanCheckbox value="#{autocompleterbean.overflow}"/>
                    <h:outputText value="overflow"/>
                    <h:selectBooleanCheckbox value="#{autocompleterbean.filterSubset}"/>
                    <h:outputText value="filterSubset"/>
                    <h:outputText value="overflowMargin"/>
                    <h:inputText value="#{autocompleterbean.overflowMargin}"/>
                    <h:outputText value="maxChoices"/>
                    <h:inputText value="#{autocompleterbean.maxChoices}"/>
                </h:panelGrid>
                <h:commandButton value="Submit"/>
                <br/>
                <br/>
                <h:outputText value="Autocompleter local : "/>
                <wf:Autocompletion id="keywords"
                                   services="['Alabama', 'New York', 'Paris', 'Montpellier', 'Arizona', 'Phoenix']"
                                   value="#{autocompleterbean.enteredKeyword}"
                                   multiple="#{autocompleterbean.multiple}"
                                   overflow="#{autocompleterbean.overflow}"
                                   style="width:40px;font: 14px Georgia,serif;"
                                   filterSubset="#{autocompleterbean.filterSubset}"
                                   overflowMargin="#{autocompleterbean.overflowMargin}"
                                   maxChoices="#{autocompleterbean.maxChoices}"
                                   loadMootools="#{autocompleterbean.loadMootools}"
                                   loadJs="#{autocompleterbean.loadJs}"
                                   rendered="#{autocompleterbean.rendered}"
                                   loadCss="#{autocompleterbean.loadCss}"/>
                <br/>
                <br/>
                <h:outputText value="Autocompleter using a Web Thesaurus Service : "/>
                <wf:Autocompletion id="keywords2"
                                   wtsUrl="http://cronos.geomatys.com/wts/WS/thesaurus/"
                                   value="#{autocompleterbean.enteredKeyword}"
                                   multiple="#{autocompleterbean.multiple}"
                                   overflow="#{autocompleterbean.overflow}"
                                   style="width:40px;font: 14px Georgia,serif;"
                                   filterSubset="#{autocompleterbean.filterSubset}"
                                   overflowMargin="#{autocompleterbean.overflowMargin}"
                                   maxChoices="#{autocompleterbean.maxChoices}"
                                   loadMootools="#{autocompleterbean.loadMootools}"
                                   loadJs="#{autocompleterbean.loadJs}"
                                   rendered="#{autocompleterbean.rendered}"
                                   loadCss="#{autocompleterbean.loadCss}"/>
            </h:form>
        </body>
    </html>
</f:view>