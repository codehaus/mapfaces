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
        <head>
            <style type="text/css">
                table{
                    font-size:12px;
                    font-family:Arial;
                }
                .table { 
                    border: 0px;
                    text-align:center;
                    width:100%
                }

                .table th {
                    border: 0px;
                    font-weight: bold;
                }

                .table tr {
                    border: 0px;
                }

                .odd {
                    background:#c6d5ee;
                }

                .even {
                    background:#d4dbe6;
                }

            </style>

        </head>
        <body>
            <h1>SVG Layer example</h1>
            <%--action="#{svglayerbean.updateFeatures}"
              featureAdded="#{svglayerbean.featureAdded}"
            featureRemoved="#{svglayerbean.featureRemoved}"
            featureBeforeUpdate="#{svglayerbean.featureBeforeUpdate}"
            featureAfterUpdate="#{svglayerbean.featureAfterUpdate}"
            cliToServOnly="false"
            title="Basic SVG layer"
            opacity="0.9"
            reRender="featuresDataTable"
            width="2"
            fillColor="#00ff00"
            strokeColor="#55ff44"
            selFillColor="#fffb31"
            selStrokeColor="#fffb31"
            targetContextCompId="owsAContext"
            reRenderComplete="alert('reRender completed !!!');"--%>
            <br/>
            <h:form>
                <h:commandButton value="submit"/>
                <mf-model:Context id="context" minifyJS="false" service="/data/context/blueMarble.xml">
                    <mf:MapPane id="map" navigation="true" debug="false">
                        <mf:SvgLayer id="svg" value="#{svglayerbean.simpleFeatures}"
                                     action="#{svglayerbean.updateFeatures}"
                                     featureAdded="#{svglayerbean.featureAdded}"
                                     featureRemoved="#{svglayerbean.featureRemoved}"
                                     featureBeforeUpdate="#{svglayerbean.featureBeforeUpdate}"
                                     featureAfterUpdate="#{svglayerbean.featureAfterUpdate}"
                                     cliToServOnly="false"
                                     reRender="simpleFeaturesDataTable"
                                     targetContextCompId="context"/>
                    </mf:MapPane>
                    <mf:EditionBar  drawRegularPolygon ="true"
                                    regularPolygonSides="4" snapping="true" drag="true"
                                    select="true" layerTargetId="svg"/>
                </mf-model:Context>

                <h:panelGroup id="simpleFeaturesDataTable" layout="block" style="position:absolute;left:700px;top: 200px;">
                    <h:dataTable id="simpleFeaturesTable"
                                 value="#{svglayerbean.simpleFeatures}" rows="6"
                                 var="feature" styleClass="tableau"
                                 style="border:0px;" rules="none" rowClasses="odd,even">
                        <h:column>
                            <f:facet name="header"><h:outputText value="Id"/></f:facet>
                            <h:outputText value="#{feature.ID}" style="text-align:center;"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><h:outputText value="Geometry"/></f:facet>
                            <h:outputText value="#{feature.defaultGeometry}" style="text-align:center;"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><h:outputText value="Bounds"/></f:facet>
                            <h:outputText value="#{feature.bounds}" style="text-align:center;"/>
                        </h:column>
                        <%--h:column headerClass="tableOptionsCol">
                            <f:facet name="header"><h:outputText value="Remove"/></f:facet>
                            <a4j:commandLink action="#{svglayerbean.deleteFeature}" id="removeFeature"
                                             ajaxSingle="true"
                                             reRender="svg">
                                <img src="../resources/skin/default/img/delete.gif" alt="<h:outputText value="Remove" />" title="<h:outputText value="Remove" />" />
                                <a4j:actionparam name="forceRefresh" value="true" />
                                <a4j:actionparam name="simpleFeatureId" value="#{feature.ID}" />
                            </a4j:commandLink>
                        </h:column--%>
                    </h:dataTable>

                    <%--mfb:DataScroller dataTableId="featuresTable"
                                      id="datascrollerpager"
                                      selectedStyleClass="selectedPager"
                                      showpages="5"
                                      style="margin:0pt auto;text-align:center;"
                                      styleClass="dtscroller">
                    </mfb:DataScroller--%>
                </h:panelGroup>
                    <%--a4j:log popup="false" width="1000" height="600"/--%>
            </h:form>
        </body>
    </html>
</f:view>
