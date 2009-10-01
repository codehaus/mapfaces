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
        <head></head>
        <body>
            <h1>Map example</h1>
            <br/>
            <h:form id="form">
                <h:panelGrid columns="2">
                    <h:outputText value="Layer name"/>
                    <h:inputText value="#{mfbean.layerName}" onkeyup="if(event.keyCode == 13) document.getElementById('form:submit').onclick();"/>
                    <h:outputText value="Format"/>
                    <h:inputText value="#{mfbean.format}" onkeyup="if(event.keyCode == 13) document.getElementById('form:submit').onclick();"/>
                    <h:outputText value="west"/>
                    <h:inputText value="#{mfbean.west}" onkeyup="if(event.keyCode == 13) document.getElementById('form:submit').onclick();"/>
                    <h:outputText value="south"/>
                    <h:inputText value="#{mfbean.south}" onkeyup="if(event.keyCode == 13) document.getElementById('form:submit').onclick();"/>
                    <h:outputText value="est"/>
                    <h:inputText value="#{mfbean.est}" onkeyup="if(event.keyCode == 13) document.getElementById('form:submit').onclick();"/>
                    <h:outputText value="north"/>
                    <h:inputText value="#{mfbean.north}" onkeyup="if(event.keyCode == 13) document.getElementById('form:submit').onclick();"/>
                </h:panelGrid>

                <h:panelGrid columns="2">
                    <h:outputText value="width"/>
                    <h:inputText value="#{mfbean.width}" onkeyup="if(event.keyCode == 13) document.getElementById('form:submit').onclick();"/>
                    <h:outputText value="height"/>
                    <h:inputText value="#{mfbean.height}" onkeyup="if(event.keyCode == 13) document.getElementById('form:submit').onclick();"/>
                    <a4j:commandLink id="submit" value="Apply changes" action="#{mfbean.applyChanges}" reRender="mappanel"/>
                </h:panelGrid>


                <h:panelGroup id="mappanel">
                    <h:graphicImage id="map_image" value="#{mfbean.url}"/>
                </h:panelGroup>
            </h:form>
        </body>
    </html>
</f:view>