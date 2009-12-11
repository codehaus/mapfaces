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
            <h1>SVG Layer example</h1>
                                      <%--action="#{svgLayerBean.updateFeatures}"
                                        featureAdded="#{svgLayerBean.featureAdded}"
                                      featureRemoved="#{svgLayerBean.featureRemoved}"
                                      featureBeforeUpdate="#{svgLayerBean.featureBeforeUpdate}"
                                      featureAfterUpdate="#{svgLayerBean.featureAfterUpdate}"
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
                <mf-model:Context minifyJS="false" service="/data/context/blueMarble.xml">
                    <mf:MapPane navigation="true" debug="true">
                         <mf:SvgLayer id="svg" />
                    </mf:MapPane>
                    <mf:EditionBar  drawRegularPolygon ="true"
                                    regularPolygonSides="4" snapping="true" drag="true"
                                    select="true" layerTargetId="svg"/>
                </mf-model:Context>
                <%--h:panelGroup id="featuresDataTable">
                    <h:dataTable id="featuresTable"
                                 value="#{svgLayerBean.features}" rows="8"
                                 var="feature" styleClass="tableau"
                                 style="border:0px;" rules="none" rowClasses="odd,even">
                        <h:column>
                            <f:facet name="header"><h:outputText value="ID"/></f:facet>
                            <h:outputText value="#{feature.id}" style="text-align:center;"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header"><h:outputText value="ID"/></f:facet>
                            <h:outputText value="#{feature.name}" style="text-align:center;"/>
                        </h:column>
                        <h:column headerClass="tableOptionsCol">
                            <f:facet name="header"><h:outputText value=""/></f:facet>
                            <a4j:commandLink action="#{svgLayerBean.deleteFeature}" id="removeFeature"
                                             ajaxSingle="true"
                                             onmousedown="previousExtent=mainFormapp_Mappane.getExtent();"
                                             reRender="haiesDataTable, mainMapping"
                                             oncomplete="mainFormapp_Mappane.zoomToExtent(previousExtent);">
                                <img src="resources/images/delete.png" alt="<h:outputText value="app_haies_del_picture" />" title="<h:outputText value="app_haies_del_picture" />" />
                                <a4j:actionparam name="haieId" value="#{feature.id}" />
                                <a4j:actionparam name="forceRefresh" value="true" />
                            </a4j:commandLink>
                        </h:column>
                        <h:column headerClass="tableOptionsCol">
                            <f:facet name="header"><h:outputText value=""/></f:facet>
                            <a4j:commandLink action="#{svgLayerBean.updateSelectedFeature}"
                                             reRender="modifHaiePopup"
                                             oncomplete="if(document.getElementById('mainForm:modifHaiePopup')){document.getElementById('mainForm:modifHaiePopup').style.display='block';}">
                                <img src="resources/images/modify.png" alt="<h:outputText value="app_haies_edit_picture" />" title="<h:outputText value="app_haies_edit_picture" />" />
                                <a4j:actionparam name="haieId" value="#{feature.id}" />
                            </a4j:commandLink>
                        </h:column>
                    </h:dataTable>
                <mfb:DataScroller dataTableId="featuresTable"
                                  id="datascrollerpager"
                                  selectedStyleClass="selectedPager"
                                  showpages="5"
                                  style="margin:0pt auto;text-align:center;"
                                  styleClass="dtscroller">
                </mfb:DataScroller>
                <h:outputText value="app_haies_number" /> <h:outputText value="#{svgLayerBean.tableLength}" /><br />
                <h:outputText value="app_haies_length" /> <h:outputText value="#{svgLayerBean.totalLength} app_haies_length_unit" />
            </h:panelGroup--%>
        </h:form>
    </body>
</html>
</f:view>
