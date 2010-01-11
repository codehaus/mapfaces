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
                </h:panelGrid>
                <h:commandButton value="Submit"/>
                <br /><br />
                <h:outputText value="Text" /><br />
                <mfb:ComponentSelector id="componentSelectorText" key="#{componentSelectorBean.componentDescriptor.key}"
                                       type="#{componentSelectorBean.componentDescriptor.type}"
                                       rendered="#{componentSelectorBean.componentDescriptor.rendered}"
                                       value="#{componentSelectorBean.componentDescriptor.value}" hasParent="false" 
                                       maxCar="#{componentSelectorBean.componentDescriptor.maxCar}"
                                       mandatory="#{componentSelectorBean.componentDescriptor.mandatory}" />

                <br /><br />
                <h:outputText value="Textarea" /><br />
                <mfb:ComponentSelector id="componentSelectorTextarea" key="#{componentSelectorBean.componentDescriptor.key}"
                                       type="textarea"
                                       rendered="#{componentSelectorBean.componentDescriptor.rendered}"
                                       value="#{componentSelectorBean.componentDescriptor.value}" hasParent="false"
                                       maxCar="#{componentSelectorBean.componentDescriptor.maxCar}"
                                       mandatory="#{componentSelectorBean.componentDescriptor.mandatory}" />

                <br /><br />
                <h:outputText value="Mail" /><br />
                <mfb:ComponentSelector id="componentSelectorMail" key="#{componentSelectorBean.componentDescriptor.key}"
                                       type="mail"
                                       rendered="#{componentSelectorBean.componentDescriptor.rendered}"
                                       value="#{componentSelectorBean.componentDescriptor.value}" hasParent="false"
                                       maxCar="#{componentSelectorBean.componentDescriptor.maxCar}"
                                       mandatory="#{componentSelectorBean.componentDescriptor.mandatory}" />

                <br /><br />
                <h:outputText value="Date" /><br />
                <mfb:ComponentSelector id="componentSelectorDate" key="#{componentSelectorBean.componentDescriptor.key}"
                                       type="date"
                                       rendered="#{componentSelectorBean.componentDescriptor.rendered}"
                                       value="22/11/2009" hasParent="false"
                                       maxCar="#{componentSelectorBean.componentDescriptor.maxCar}"
                                       mandatory="#{componentSelectorBean.componentDescriptor.mandatory}" />

                <br /><br />
                <h:outputText value="Select" /><br />
                <mfb:ComponentSelector id="componentSelectorSelect" key="#{componentSelectorBean.componentDescriptor.key}"
                                       type="select"
                                       rendered="#{componentSelectorBean.componentDescriptor.rendered}"
                                       value="#{componentSelectorBean.componentDescriptor.value}" hasParent="false"
                                       maxCar="#{componentSelectorBean.componentDescriptor.maxCar}"
                                       mandatory="#{componentSelectorBean.componentDescriptor.mandatory}" />

                <br /><br />
                <h:outputText value="Read only" /><br />
                <mfb:ComponentSelector id="componentSelectorRo" key="#{componentSelectorBean.componentDescriptor.key}"
                                       type="readonly"
                                       rendered="#{componentSelectorBean.componentDescriptor.rendered}"
                                       value="#{componentSelectorBean.componentDescriptor.value}" hasParent="false"
                                       maxCar="#{componentSelectorBean.componentDescriptor.maxCar}"
                                       mandatory="#{componentSelectorBean.componentDescriptor.mandatory}" />
            </h:form>
        </body>
    </html>
</f:view>

