<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>
<%@taglib prefix="mf-model" uri="http://mapfaces.org/taglib-models"%>
<f:view>
   <html>
       <head>
           <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
               
            <link rel="stylesheet" href="../theme/default/style.css" type="text/css" />
            <link rel="stylesheet" href="style.css" type="text/css" />
            <script src="../lib/OpenLayers.js"></script>
           <script type="text/javascript">
                var lon = 5;
                var lat = 40;
                var zoom = 5;
                var map, layer;

                function init(){
                    map = new OpenLayers.Map( 'map' );
                    layer = new OpenLayers.Layer.WMS(
                        "OpenLayers WMS",
                        "http://labs.metacarta.com/wms/vmap0",
                        {layers: 'basic',format:"image/png",projection:"EPSG:3395"},
                        {singleTile: true}
                    );
                    map.addLayer(layer);

                    map.zoomToMaxExtent();
                    map.addControl( new OpenLayers.Control.LayerSwitcher() );
                }
            </script>
       </head>
       <body onload="init();">   
            <h1><h:outputText value="MapFaces" /></h1>                
            <br/>
            <h:form>
                 <mf-model:Context id="constellationStyledWMS111" debug="true" service="data/context/owc030Cut.xml">
                    <mf:MapPane debug="true"></mf:MapPane>
                </mf-model:Context>               
            </h:form>
            <div id="map" style="width:600;height:300px;"></div>
            <a onclick="init();">Load OpenLayers.Map</a>
        </body>
    </html>
</f:view>