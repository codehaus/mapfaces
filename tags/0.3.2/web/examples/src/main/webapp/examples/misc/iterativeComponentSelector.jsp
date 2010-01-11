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
            <title>MapFaces Iterative ComponentSelector demo</title>
	    <script type="text/javascript" src="../../resource.jsf?r=/org/mapfaces/resources/js/mootools/mootools-1.2.4-core-yc.js"></script>
            <script type="text/javascript" src="../../resource.jsf?r=/org/mapfaces/resources/js/mootools/mootools-1.2.4.1-more-yc.js"></script>

        </head>
        <body>
            <h1><h:outputText value="MapFaces Iterative ComponentSelector demo"/></h1>
            <h:form id="mainform">
                <h:dataTable id="demo" styleClass="tableStyle"
                             value="#{componentSelectorBean.valueList}" rows="8"
                             var="_var"
                             style="border:0px;float:left;" rules="none" rowClasses="odd,even">
                    <h:column>
                        <f:facet name="header"><h:outputText value="ID"/></f:facet>
                        <h:outputText value="#{_var.title}" style="text-align:center;"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header"><h:outputText value="Composants"/></f:facet>
                        <mfb:ComponentSelector id="demoComponentSelector" key="#{_var.key}" type="#{_var.type}"
                                               value="#{_var.value}" mandatory="#{_var.mandatory}"
                                               selectMap="#{_var.selectMap}" hasParent="false" maxCar="100" />
                    </h:column>
                </h:dataTable>
                <h:commandButton value="Ok" />
            </h:form>
        </body>
        <style>
            .tableStyle {
                border: 0px;
                text-align:left;
            }

            .tableStyle th {
                border: 0px;
                font-weight: bold;
                background:#e8e7ef;
            }

            .tableStyle tr {
                border: 0px;
            }

            .odd {
                background:#c6d5ee;
            }

            .even {
                background:#d4dbe6;
            }
        </style>
    </html>
</f:view>

