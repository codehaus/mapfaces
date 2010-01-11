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
            <h1>All basic components in one page</h1>
            <br/>
            <h:form>
                <h:commandButton value="submit"/>
                <mf-model:Context service="/data/context/blueMarble.xml">
                    <mf:MapPane navigation="true">
                        <mf:SvgLayer id="svg" />
                    </mf:MapPane>
                    <mf:ButtonBar graticule="true" featureInfo="true" reRender="datarequest"></mf:ButtonBar>
                    <mf:EditionBar  drawRegularPolygon ="true"
                        regularPolygonSides="4" snapping="true" drag="true"
                        select="true" layerTargetId="svg"/>
                    <mf:CursorTrack showDM="true" showLatLon="true"
                                    showPX="true" showXY="true">
                    </mf:CursorTrack>
                    <mf:Scale></mf:Scale>
                    <mf:ScaleBar></mf:ScaleBar>
                    <mf:LayerControl id="lc"></mf:LayerControl>
                    <mf:DataRequest id="datarequest" targetPopupId="popupfeatureInfo"
                                    outputFormat="text/plain" featureCount="0">
                        <mf:Popup id="popupfeatureInfo" iframe="false"></mf:Popup>
                    </mf:DataRequest>
                </mf-model:Context>
            </h:form>
        </body>
    </html>
</f:view>