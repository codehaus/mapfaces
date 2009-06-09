
<%@taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>
<%@taglib prefix="mf-model" uri="http://mapfaces.org/taglib-models"%>



<mf-model:Context id="owsVisuContext" minifyJS="true" service="map-context.xml" scriptaculous="false" mootools="false" debug="false">
    
    <%--  The main mappane is englobed with a h:panelGroup tag
    with a styleClass ="mochaMainAppColumn" to fix it in the main column  --%>
    <h:panelGroup id="mappaneBox" layout="block" styleClass="mochaMainAppColumn">
        <mf:Div style="width:100%; height:100%;position:absolute;overflow:hidden;">
            <mf:MapPane id="mappane" style="width:100%; height:100%;" debug="false" navigation="true" />
            <mf:Div styleClass="footerMap">
                <mf:ScaleBar></mf:ScaleBar>
            </mf:Div>
       </mf:Div>
    </h:panelGroup>

     <%-- The layer control is englobed with a h:panelGroup tag
        with a styleClass ="mochaPanel1" to fix it in the first top panel in the right column  --%>
    <h:panelGroup id="layerControlBox" layout="block" styleClass="mochaPanel1" style="height: 100%;width:100%;">
        <mf:LayerControl style="height:100%;opacity:1;position:relative;width:400px;"></mf:LayerControl>
    </h:panelGroup>

    <%-- Other components can be englobed with a h:panelGroup tag
    with a styleClass ="mochaPanel2" to fix it in the second bottom panel in the right column  --%>
    <h:panelGroup id="editToolBox" layout="block" styleClass="mochaPanel2" style="height: 100%;width:100%;">
        
    </h:panelGroup>
    <h:panelGroup id="toolBox" layout="block" styleClass="mochaPanel3" style="height: 100%;width:100%;font-size:12px;font-family:verdana;">
        <mf:ButtonBar id="ButtonBar" styleClass="mfButtonBar horizontal" style="height:34px;opacity:1;position:relative;width:100%;" />
         <mf:CursorTrack id="CursorTrack" style="height:34px;opacity:1;position:relative;width:100%;font-size: 1em;"
                            showDMS="true" showLatLon="false" />
          <mf:Scale id="Scale" style="height:34px;opacity:1;position:relative;width:100%;font-size: 1em;"/>
    </h:panelGroup>
    
</mf-model:Context>

