
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>
<%@taglib prefix="mf-base" uri="http://mapfaces.org/taglib-base"%>
<%@taglib prefix="mf-model" uri="http://mapfaces.org/taglib-models"%>
<%@taglib prefix="mf-widget" uri="http://widget-mapfaces.org/taglib"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<f:view>
    <html xmlns="http://www.w3.org/1999/xhtml">

        <head>
            <link rel="icon" type="image/x-icon" href="" />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

                <title><h:outputText value="Template ExtJS #{init.locale}"/></title>

                <link rel="stylesheet" type="text/css" href="resources/js/ext-3.0.3/resources/css/ext-all.css" />

                <style type="text/css">
                    html, body {
                        font:normal 12px verdana;
                        margin:0;
                        padding:0;
                        border:0 none;
                        overflow:hidden;
                        height:100%;
                    }
                    p {
                        margin:5px;
                    }
                    .settings {
                        background-image:url(../shared/icons/fam/folder_wrench.png);
                    }
                    .nav {
                        background-image:url(../shared/icons/fam/folder_go.png);
                    }
                </style>

                <!-- GC -->
                <!-- LIBS -->
                <script type="text/javascript" src="resources/js/ext-3.0.3/adapter/ext/ext-base.js"></script>
                <!-- ENDLIBS -->

                <script type="text/javascript" src="resources/js/ext-3.0.3/ext-all.js"></script>

                <!-- EXAMPLES -->
                <script type="text/javascript" src="resources/js/ext-3.0.3/examples/shared/examples.js"></script>

                <link rel="stylesheet" type="text/css" href="resources/skin/default/all.css" />
                <script src="resource.jsf?r=/org/mapfaces/resources/openlayers/custom/OpenLayers.js" type="text/javascript"></script>

                <script type="text/javascript" src="resources/js/start.js"></script>
        </head>

        <body>
            <h:form id="main_form">
                <a4j:region id="stat1">
                    <mf-model:Context id="owsVisuContext" minifyJS="true"  service="map-context.xml"
                                      scriptaculous="false" mootools="false" openlayers="false" debug="false">

                        <div id="center2" class="x-hide-display">
                            <mf-base:Div style="width:100%; height:100%;position:absolute;overflow:hidden;">
                                <mf:MapPane id="mappane" numZoomLevels="18" style="width:100%; height:100%;"
                                            debug="false" navigation="true" />

                                <mf:DataRequest id="datarequest"
                                                targetPopupId="popupfeatureInfo"
                                                outputFormat="text/html"
                                                featureCount="0">
                                    <mf:Popup id="popupfeatureInfo" iframe="true" style="width:500px;"></mf:Popup>
                                </mf:DataRequest>
                                <mf-base:Div styleClass="footerMap">
                                    <mf:ScaleBar ></mf:ScaleBar>
                                </mf-base:Div>
                            </mf-base:Div>
                        </div>

                        <div id="center1" class="x-hide-display">
                        </div>

                        <!-- use class="x-hide-display" to prevent a brief flicker of the content -->
                        <div id="west" class="x-hide-display">
                            <mf:LayerControl id="lc"  displayHeader="false"  layerInfo="false"
                                             style="height:100%;opacity:1;position:relative;width:400px;" >
                            </mf:LayerControl>
                        </div>

                        <div id="east" class="x-hide-display">
                            <mf:ButtonBar id="ButtonBar" reRender="datarequest"
                                          styleClass="mfButtonBar horizontal"
                                          style="height:34px;opacity:1;position:relative;width:100%;"
                                          featureInfo="true" measureDistance="true" />
                            <%--mf-base:Div>
                                <mf:Autocompletion services="http://cronos.geomatys.com/wts/WS/thesaurus/"
                                                   loadMootools="false" enableAjax="true" multiple="true"
                                                   maxChoices="5" id="autocompleteZoom">
                                </mf:Autocompletion>
                            </mf-base:Div--%>
                            <%--mf:CursorTrack id="CursorTrack"
                                            style="height:15px;opacity:1;position:relative;font-size: 1em;margin:5px;"
                                            showDMS="true" showLatLon="false" showXY="false" />
                            <mf:Scale id="Scale"
                                      style="height:15px;opacity:1;position:relative;width:100%;font-size: 1em;padding:0px;"/>

                            <div id="output" style="height:15px;margin:5px;"></div--%>

                        </div>

                        <div id="props-panel" class="x-hide-display" style="width:200px;height:200px;overflow:hidden;">
                        </div>

                        <div id="south" class="x-hide-display">

                        </div>

                    <%--div id="desktop">

                        <%@ include file="desktopNavbar.jsp" %>

                        <div id="pageWrapper"></div>

                        <div id="bindingWrapper" style="height:300px;width:600px;display:none;">
                            <h:panelGroup id="toRefresh" layout="block">

                                <h:panelGroup id="homePanel" rendered="#{switchTab.displayHome}" layout="block">
                                    <%@ include file="home.jsp" %>
                                </h:panelGroup>

                                <h:panelGroup id="webPanel" rendered="#{switchTab.displayApp}" layout="block">
                                    <%@ include file="application.jsp" %>
                                </h:panelGroup>

                            </h:panelGroup>
                        </div>

                        <!-- footer -->
                        <div id="desktopFooterWrapper">
                            <div id="desktopFooter">
                                &copy; 2008-2009 <a target="_blank" href="#">Geomatys</a>
                            </div>
                        </div>

                    </div--%><!-- desktop end -->


                    </mf-model:Context>
                </a4j:region>
                <%--a4j:log popup="false" width="600" height="300"></a4j:log--%>
            </h:form>

            <a4j:status for="main_form:stat1" forceId="true" id="ajaxStatus">
                <f:facet name="start">
                    <t:div id="panelloadingstatus">
                        <h:graphicImage id="loading_img" value="resources/skin/default/img/loadinfo_spinner.gif" style="position:fixed;top:50%;left:50%;z-index:1001;" />
                        <div id="loading-modal">
                        </div>
                    </t:div>
                </f:facet>
            </a4j:status>

            <%@ include file="footer.jsp"%>
        </body>
    </html>
</f:view>
