
<%@taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>
<%@taglib prefix="mf-base" uri="http://mapfaces.org/taglib-base"%>
<%@taglib prefix="mf-model" uri="http://mapfaces.org/taglib-models"%>
<%@taglib prefix="mf-widget" uri="http://widget-mapfaces.org/taglib"%>



<mf-model:Context id="owsVisuContext" minifyJS="true"  service="map-context.xml"
                  scriptaculous="false" mootools="false" openlayers="false" debug="false">

    <%--  The main mappane is englobed with a h:panelGroup tag
    with a styleClass ="mochaMainAppColumn" to fix it in the main column  --%>
    <h:panelGroup id="mappaneBox" layout="block" styleClass="mochaMainAppColumn">
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
                <mf:ScaleBar></mf:ScaleBar>
            </mf-base:Div>

        </mf-base:Div>
    </h:panelGroup>

    <%-- The layer control is englobed with a h:panelGroup tag
        with a styleClass ="mochaPanel1" to fix it in the first top panel in the right column  --%>
    <h:panelGroup id="layerControlBox" layout="block" styleClass="mochaPanel1"
                  style="height: 100%;width:100%;">
        <mf:LayerControl id="lc"  displayHeader="false"  layerInfo="false"
                         style="height:100%;opacity:1;position:relative;width:400px;" >
        </mf:LayerControl>
    </h:panelGroup>

    <%-- Other components can be englobed with a h:panelGroup tag
    with a styleClass ="mochaPanel2" to fix it in the second bottom panel in the right column  --%>
    <h:panelGroup id="editToolBox" layout="block" styleClass="mochaPanel2"
                  style="height: 100%;width:100%;">
        <mf:EditionBar id="EditionBar"  styleClass="mfEditionBar horizontal"
                       deleteFeature="true" modify="true" select="true"
                       regularPolygonSides="4"drawRegularPolygon="true" snapping="true">
        </mf:EditionBar>
    </h:panelGroup>

    <h:panelGroup id="toolBox" layout="block" styleClass="mochaPanel3"
                  style="height: 100%;width:100%;font-size:12px;font-family:verdana;">
        <mf:ButtonBar id="ButtonBar" reRender="datarequest"
                      styleClass="mfButtonBar horizontal"
                      style="height:34px;opacity:1;position:relative;width:100%;"
                      featureInfo="true" measureDistance="true" />
        <mf-base:Div>
            <mf:Autocompletion wtsUrl="http://cronos.geomatys.com/wts/WS/thesaurus/"
                               loadMootools="false" enableAjax="true" multiple="true"
                               maxChoices="5" id="autocompleteZoom">
            </mf:Autocompletion>
        </mf-base:Div>
        <mf:CursorTrack id="CursorTrack"
                        style="height:15px;opacity:1;position:relative;font-size: 1em;margin:5px;"
                        showDMS="true" showLatLon="false" showXY="false" />
        <mf:Scale id="Scale"
                  style="height:15px;opacity:1;position:relative;width:100%;font-size: 1em;padding:0px;"/>

        <div id="output" style="height:15px;margin:5px;"></div>

        
    </h:panelGroup>
</mf-model:Context>

