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
            <title>MapFaces Treetable demo</title>
	    <script type="text/javascript" src="../../resource.jsf?r=/org/mapfaces/resources/js/mootools/mootools-1.2.4-core-yc.js"></script>
            <script type="text/javascript" src="../../resource.jsf?r=/org/mapfaces/resources/js/mootools/mootools-1.2.4.1-more-yc.js"></script>

        </head>
        <body>
            <h1><h:outputText value="MapFaces Treetable demo"/></h1>
            <h:form id="mainform">
                <mfb:treetable  id="sensorTreetableConsult"
                                    value="#{treetableBean.treemodel}"
                                    var="treeNode"
                                    style="width:500px;"
                                    collapse="true"
                                    collapseDepth="1"
                                    nodeStyle="font-size:11px;font-family:Verdana,Helvetica,sans-serif;background-color:#fff;color:#6a6f74;font-weight:bold;"
                                    leafStyle="font-size:10px;background-color:#f6faff; color:#2D2DA4;"
                                    oddLineStyle=""
                                    evenLineStyle=""
                                    activateAjaxLoading="false"
                                    loadCss="true"
                                    loadJs="true">

                    <mfb:treecolumn viewControls="true" width="90%">
                        <f:facet name="header">
                            <h:outputText id="outputheaderId" value="name" />
                        </f:facet>
                        <h:outputText id="outputName" value="#{treeNode.userObject.title}"/>
                    </mfb:treecolumn>

                    <mfb:treecolumn width="10%">
                        <f:facet name="header">
                            <h:outputText value="value" id="outputheaderId2" />
                        </f:facet>
                        
                    </mfb:treecolumn>

                </mfb:treetable>
                <%/*mfb:ComponentSelector id="truc" type="#{treetableBean.type}" value="#{treetableBean.test.value}" /*/%>
                <mfb:Test id="test" name="#{treetableBean.test.title}" />
            </h:form>
        </body>
    </html>
</f:view>

