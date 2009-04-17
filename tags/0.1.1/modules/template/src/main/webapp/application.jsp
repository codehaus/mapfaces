
<%@taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>
<%@taglib prefix="mf-model" uri="http://mapfaces.org/taglib-models"%>



<mf-model:Context id="owsVisuContext" minifyJS="false" service="map-context.xml" 
    scriptaculous="false" mootools="false" debug="false">
    
    <%--  The main mappane is englobed with a h:panelGroup tag
    with a styleClass ="mochaMainAppColumn" to fix it in the main column  --%>
    <h:panelGroup id="app_MapPaneBox" layout="block" styleClass="mochaMainAppColumn">
        <mf:Div style="width:100%; height:100%;position:absolute;overflow:hidden;">
            <mf:MapPane id="app_Mappane" style="width:100%; height:100%;" debug="false" navigation="true" />
            <mf:ButtonBar id="app_ButtonBar" styleClass="mfButtonBar horizontal" style="background:transparent; opacity: 1; top: 0px; right:50%;width:168px;margin-top:0;" />
            <mf:CursorTrack id="app_CursorTrack" style="background-color: threedlightshadow; opacity: 0.5; position: relative; bottom: 30px;"
                            showDMS="true" showLatLon="false" />
        </mf:Div>
    </h:panelGroup>
    
    <%-- The layer control is englobed with a h:panelGroup tag
    with a styleClass ="mochaPanel1" to fix it in the first top panel in the right column  --%>
    <h:panelGroup id="app_LayerControlBox" layout="block" styleClass="mochaPanel1" style="height: 100%;width:100%;">
        
    </h:panelGroup>
    
    <%-- Other components can be englobed with a h:panelGroup tag
    with a styleClass ="mochaPanel2" to fix it in the second bottom panel in the right column  --%>
    <h:panelGroup id="app_SomeThings" layout="block" styleClass="mochaPanel2">
        
    </h:panelGroup>
    
</mf-model:Context>

