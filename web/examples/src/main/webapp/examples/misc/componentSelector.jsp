<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mfb" uri="http://mapfaces.org/taglib-base"%>
<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>MapFaces ComponentSelector demo</title>
	    <script type="text/javascript" src="../../resource.jsf?r=/org/mapfaces/resources/js/mootools/mootools-1.2.4-core-yc.js"></script>
            <script type="text/javascript" src="../../resource.jsf?r=/org/mapfaces/resources/js/mootools/mootools-1.2.4.1-more-yc.js"></script>

        </head>
        <body>
            <h1><h:outputText value="MapFaces ComponentSelector demo"/></h1>
            <h:form id="mainform">
                <h:panelGrid columns="2">
                    <h:selectBooleanCheckbox value="#{componentSelectorBean.componentDescriptor.rendered}" />
                    <h:outputText value="render the component"/>
                    <h:selectBooleanCheckbox value="#{componentSelectorBean.componentDescriptor.mandatory}"/>
                    <h:outputText value="value is mandatory or not"/>
                    <h:selectOneMenu id="typeMenu" value="#{componentSelectorBean.componentDescriptor.type}">
                        <f:selectItems value="#{componentSelectorBean.typeList}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Component type"/>
                    <h:inputText value="#{componentSelectorBean.componentDescriptor.maxCar}" 
                                 rendered="#{componentSelectorBean.componentDescriptor.type == 'textarea'}" />
                    <h:outputText value="Max caracters" rendered="#{componentSelectorBean.componentDescriptor.type == 'textarea'}"/>
                </h:panelGrid>
                <h:commandButton value="Submit"/>
                <br />
                <mfb:ComponentSelector id="componentSelectorId" key="#{componentSelectorBean.componentDescriptor.key}"
                                       type="#{componentSelectorBean.componentDescriptor.type}"
                                       rendered="#{componentSelectorBean.componentDescriptor.rendered}"
                                       value="#{componentSelectorBean.componentDescriptor.value}" hasParent="false" 
                                       maxCar="#{componentSelectorBean.componentDescriptor.maxCar}"
                                       mandatory="#{componentSelectorBean.componentDescriptor.mandatory}" />
            </h:form>
        </body>
    </html>
</f:view>

