/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/* 
 * @requires OpenLayers/BaseTypes.js
 * @requires OpenLayers/Lang/en.js
 */ 
if(!window.OpenLayers){
    (function() {
        /**
     * Before creating the OpenLayers namespace, check to see if
     * OpenLayers.singleFile is true.  This occurs if the
     * OpenLayers/SingleFile.js script is included before this one - as is the
     * case with single file builds.
     */
        var singleFile = (typeof OpenLayers == "object" && OpenLayers.singleFile);
    
        /**
     * Namespace: OpenLayers
     * The OpenLayers object provides a namespace for all things OpenLayers
     */
        window.OpenLayers = {
        
            /**
         * Property: _scriptName
         * {String} Relative path of this script.
         */
            _scriptName: (!singleFile) ? "openlayers/custom/OpenLayers.js" : "OpenLayers.js",
            //_scriptName: (!singleFile) ? "openlayers/lib/OpenLayers.js" : "OpenLayers.js",
        
            /**
         * Function: _getScriptLocation
         * Return the path to this script.
         *fappendchild
         
         * Returns:
         * {String} Path to this script
         */
            _getScriptLocation: function () {
                var scriptLocation = "";
                var scriptName = OpenLayers._scriptName;
         
                var scripts = document.getElementsByTagName('script');
                for (var i = 0; i < scripts.length; i++) {
                    var src = scripts[i].getAttribute('src');
                    if (src) {
                    
                        var index = src.lastIndexOf(scriptName);
                        // set path length for src up to a query string
                        var pathLength = -1;
                        if (pathLength < 0) {
                            pathLength = src.length;
                        }
                        // is it found, at the end of the URL?
                        if ((index > -1) && (index + scriptName.length == pathLength)) {
                            scriptLocation = src.slice(0, pathLength - scriptName.length);
                            break;
                        }
                    }
                }
                return scriptLocation;
            }
        };
        /**
     * OpenLayers.singleFile is a flag indicating this file is being included
     * in a Single File Library build of the OpenLayers Library.
     * 
     * When we are *not* part of a SFL build we dynamically include the
     * OpenLayers library code.
     * 
     * When we *are* part of a SFL build we do not dynamically include the 
     * OpenLayers library code as it will be appended at the end of this file.
      */
        var jsfiles;
        if(!singleFile) {
            jsfiles = new Array(
                "openlayers/lib/OpenLayers/Util.js",
                "openlayers/lib/OpenLayers/BaseTypes.js",
                "openlayers/lib/OpenLayers/BaseTypes/Class.js",
                "openlayers/lib/OpenLayers/BaseTypes/Bounds.js",
                "openlayers/lib/OpenLayers/BaseTypes/Element.js",
                "openlayers/lib/OpenLayers/BaseTypes/LonLat.js",
                "openlayers/lib/OpenLayers/BaseTypes/Pixel.js",
                "openlayers/lib/OpenLayers/BaseTypes/Size.js",
                "openlayers/lib/OpenLayers/Console.js",
                "openlayers/lib/OpenLayers/Tween.js",
                //            "openlayers/lib/Rico/Corner.js",
                //            "openlayers/lib/Rico/Color.js",
                "openlayers/lib/OpenLayers/Ajax.js",
                "openlayers/lib/OpenLayers/Events.js",
                "openlayers/lib/OpenLayers/Request.js",
                "openlayers/lib/OpenLayers/Request/XMLHttpRequest.js",
                "openlayers/lib/OpenLayers/Projection.js",
                "openlayers/lib/OpenLayers/Map.js",
                "openlayers/lib/OpenLayers/Layer.js",
                "openlayers/lib/OpenLayers/Icon.js",
                "openlayers/lib/OpenLayers/Marker.js",
                "openlayers/lib/OpenLayers/Marker/Box.js",
                "openlayers/lib/OpenLayers/Popup.js",
                "openlayers/lib/OpenLayers/Tile.js",
                "openlayers/lib/OpenLayers/Tile/Image.js",
                //            "openlayers/lib/OpenLayers/Tile/WFS.js",
                //            "openlayers/lib/OpenLayers/Layer/Image.js",
                //            "openlayers/lib/OpenLayers/Layer/SphericalMercator.js",
                //            "openlayers/lib/OpenLayers/Layer/EventPane.js",
                //            "openlayers/lib/OpenLayers/Layer/FixedZoomLevels.js",
                //            "openlayers/lib/OpenLayers/Layer/Google.js",
                //            "openlayers/lib/OpenLayers/Layer/VirtualEarth.js",
                //            "openlayers/lib/OpenLayers/Layer/Yahoo.js",
                //            "openlayers/lib/OpenLayers/Layer/HTTPRequest.js",
                            "openlayers/lib/OpenLayers/Layer/Grid.js",
                //            "openlayers/lib/OpenLayers/Layer/MapGuide.js",
                //            "openlayers/lib/OpenLayers/Layer/MapServer.js",
                //            "openlayers/lib/OpenLayers/Layer/MapServer/Untiled.js",
                //            "openlayers/lib/OpenLayers/Layer/KaMap.js",
                //            "openlayers/lib/OpenLayers/Layer/KaMapCache.js",
                //            "openlayers/lib/OpenLayers/Layer/MultiMap.js",
                            "openlayers/lib/OpenLayers/Layer/Markers.js",
                //            "openlayers/lib/OpenLayers/Layer/Text.js",
                //            "openlayers/lib/OpenLayers/Layer/WorldWind.js",
                //            "openlayers/lib/OpenLayers/Layer/ArcGIS93Rest.js",
                //            "openlayers/lib/OpenLayers/Layer/WMS.js",
                //            "openlayers/lib/OpenLayers/Layer/WMS/Untiled.js",
                //            "openlayers/lib/OpenLayers/Layer/GeoRSS.js",
                "openlayers/lib/OpenLayers/Layer/Boxes.js",
                //            "openlayers/lib/OpenLayers/Layer/XYZ.js",
                //            "openlayers/lib/OpenLayers/Layer/TMS.js",
                //            "openlayers/lib/OpenLayers/Layer/TileCache.js",
                //            "openlayers/lib/OpenLayers/Popup/Anchored.js",
                //            "openlayers/lib/OpenLayers/Popup/AnchoredBubble.js",
                //            "openlayers/lib/OpenLayers/Popup/Framed.js",
                //            "openlayers/lib/OpenLayers/Popup/FramedCloud.js",
                "openlayers/lib/OpenLayers/Feature.js",
                "openlayers/lib/OpenLayers/Feature/Vector.js",
                //            "openlayers/lib/OpenLayers/Feature/WFS.js",
                "openlayers/lib/OpenLayers/Handler.js",
                "openlayers/lib/OpenLayers/Handler/Click.js",
                "openlayers/lib/OpenLayers/Handler/Hover.js",
                "openlayers/lib/OpenLayers/Handler/Point.js",
                "openlayers/lib/OpenLayers/Handler/Path.js",
                "openlayers/lib/OpenLayers/Handler/Polygon.js",
                "openlayers/lib/OpenLayers/Handler/Feature.js",
                "openlayers/lib/OpenLayers/Handler/Drag.js",
                "openlayers/lib/OpenLayers/Handler/RegularPolygon.js",
                "openlayers/lib/OpenLayers/Handler/Box.js",
                //             "openlayers/lib/OpenLayers/Handler/MouseWheel.js",
                "openlayers/lib/OpenLayers/Handler/Keyboard.js",
                "openlayers/lib/OpenLayers/Lang.js",
                "openlayers/custom/OpenLayers/LangExt.js",
                "openlayers/lib/OpenLayers/Lang/en.js",
                "openlayers/lib/OpenLayers/Lang/fr.js",
                "openlayers/custom/OpenLayers/Lang/enExt.js",
                "openlayers/custom/OpenLayers/Lang/frExt.js",
                "openlayers/lib/OpenLayers/Control.js",
                "openlayers/lib/OpenLayers/Control/Attribution.js",
                "openlayers/lib/OpenLayers/Control/Button.js",
                "openlayers/lib/OpenLayers/Control/ZoomBox.js",
                "openlayers/lib/OpenLayers/Control/ZoomToMaxExtent.js",
                "openlayers/lib/OpenLayers/Control/DragPan.js",
                "openlayers/lib/OpenLayers/Control/Navigation.js",
                "openlayers/lib/OpenLayers/Control/MouseDefaults.js",
                //"openlayers/lib/OpenLayers/Control/MousePosition.js",
                "openlayers/lib/OpenLayers/Control/OverviewMap.js",
                "openlayers/lib/OpenLayers/Control/KeyboardDefaults.js",
                //            "openlayers/lib/OpenLayers/Control/PanZoom.js",
                //            "openlayers/lib/OpenLayers/Control/PanZoomBar.js",
                "openlayers/lib/OpenLayers/Control/ArgParser.js",
                //            "openlayers/lib/OpenLayers/Control/Permalink.js",
                "openlayers/lib/OpenLayers/Control/Scale.js",
                "openlayers/lib/OpenLayers/Control/ScaleLine.js",
                "openlayers/lib/OpenLayers/Control/Snapping.js",
                "openlayers/lib/OpenLayers/Control/Split.js",
                //            "openlayers/lib/OpenLayers/Control/LayerSwitcher.js",
                "openlayers/lib/OpenLayers/Control/DrawFeature.js",
                "openlayers/lib/OpenLayers/Control/DragFeature.js",
                "openlayers/lib/OpenLayers/Control/ModifyFeature.js",
                "openlayers/lib/OpenLayers/Control/Panel.js",
                "openlayers/lib/OpenLayers/Control/SelectFeature.js",
                "openlayers/lib/OpenLayers/Control/NavigationHistory.js",
                "openlayers/lib/OpenLayers/Control/Measure.js",
                //              "openlayers/lib/OpenLayers/Control/WMSGetFeatureInfo.js",
                "openlayers/lib/OpenLayers/Geometry.js",
                "openlayers/lib/OpenLayers/Geometry/Rectangle.js",
                "openlayers/lib/OpenLayers/Geometry/Collection.js",
                "openlayers/lib/OpenLayers/Geometry/Point.js",
                "openlayers/lib/OpenLayers/Geometry/MultiPoint.js",
                "openlayers/lib/OpenLayers/Geometry/Curve.js",
                "openlayers/lib/OpenLayers/Geometry/LineString.js",
                "openlayers/lib/OpenLayers/Geometry/LinearRing.js",
                "openlayers/lib/OpenLayers/Geometry/Polygon.js",
                "openlayers/lib/OpenLayers/Geometry/MultiLineString.js",
                "openlayers/lib/OpenLayers/Geometry/MultiPolygon.js",
                "openlayers/lib/OpenLayers/Geometry/Surface.js",
                "openlayers/lib/OpenLayers/Renderer.js",
                "openlayers/lib/OpenLayers/Renderer/Elements.js",
                "openlayers/lib/OpenLayers/Renderer/SVG.js",
                "openlayers/lib/OpenLayers/Renderer/Canvas.js",
                "openlayers/lib/OpenLayers/Renderer/VML.js",
                "openlayers/lib/OpenLayers/Layer/Vector.js",
                "openlayers/lib/OpenLayers/Layer/Vector/RootContainer.js",
                //            "openlayers/lib/OpenLayers/Strategy.js",
                //            "openlayers/lib/OpenLayers/Strategy/Fixed.js",
                //            "openlayers/lib/OpenLayers/Strategy/Cluster.js",
                //            "openlayers/lib/OpenLayers/Strategy/Paging.js",
                //            "openlayers/lib/OpenLayers/Strategy/BBOX.js",
                //            "openlayers/lib/OpenLayers/Strategy/Save.js",
                "openlayers/lib/OpenLayers/Protocol.js",
                "openlayers/lib/OpenLayers/Protocol/HTTP.js",
                //            "openlayers/lib/OpenLayers/Protocol/SQL.js",
                //            "openlayers/lib/OpenLayers/Protocol/SQL/Gears.js",
                //            "openlayers/lib/OpenLayers/Protocol/WFS.js",
                //            "openlayers/lib/OpenLayers/Protocol/WFS/v1.js",
                //            "openlayers/lib/OpenLayers/Protocol/WFS/v1_0_0.js",
                //            "openlayers/lib/OpenLayers/Protocol/WFS/v1_1_0.js",
                //            "openlayers/lib/OpenLayers/Layer/PointTrack.js",
                //            "openlayers/lib/OpenLayers/Layer/GML.js",
                "openlayers/lib/OpenLayers/Style.js",
                "openlayers/lib/OpenLayers/StyleMap.js",
                //            "openlayers/lib/OpenLayers/Rule.js",
                //            "openlayers/lib/OpenLayers/Filter.js",
                //            "openlayers/lib/OpenLayers/Filter/FeatureId.js",
                //            "openlayers/lib/OpenLayers/Filter/Logical.js",
                //            "openlayers/lib/OpenLayers/Filter/Comparison.js",
                //            "openlayers/lib/OpenLayers/Filter/Spatial.js",
                "openlayers/lib/OpenLayers/Format.js",
                //            "openlayers/lib/OpenLayers/Format/XML.js",
                //            "openlayers/libOpenLayers/Format/ArcXML.js",
                //            "openlayers/libOpenLayers/Format/ArcXML/Features.js",
                //            "openlayers/lib/OpenLayers/Format/GML.js",
                //            "openlayers/lib/OpenLayers/Format/GML/Base.js",
                //            "openlayers/lib/OpenLayers/Format/GML/v2.js",
                //            "openlayers/lib/OpenLayers/Format/GML/v3.js",
                //            "openlayers/lib/OpenLayers/Format/KML.js",
                //            "openlayers/lib/OpenLayers/Format/GeoRSS.js",
                //            "openlayers/lib/OpenLayers/Format/WFS.js",
                //            "openlayers/lib/OpenLayers/Format/WFSCapabilities.js",
                //            "openlayers/lib/OpenLayers/Format/WFSCapabilities/v1.js",
                //            "openlayers/lib/OpenLayers/Format/WFSCapabilities/v1_0_0.js",
                //            "openlayers/lib/OpenLayers/Format/WFSCapabilities/v1_1_0.js",
                //            "openlayers/lib/OpenLayers/Format/WFSDescribeFeatureType.js",
                //            "openlayers/lib/OpenLayers/Format/WMSDescribeLayer.js",
                //            "openlayers/lib/OpenLayers/Format/WMSDescribeLayer/v1_1.js",
                "openlayers/lib/OpenLayers/Format/WKT.js",
                //            "openlayers/lib/OpenLayers/Format/OSM.js",
                //            "openlayers/lib/OpenLayers/Format/GPX.js",
                //            "openlayers/lib/OpenLayers/Format/Filter.js",
                //            "openlayers/lib/OpenLayers/Format/Filter/v1.js",
                //            "openlayers/lib/OpenLayers/Format/Filter/v1_0_0.js",
                //            "openlayers/lib/OpenLayers/Format/Filter/v1_1_0.js",
                //            "openlayers/lib/OpenLayers/Format/SLD.js",
                //            "openlayers/lib/OpenLayers/Format/SLD/v1.js",
                //            "openlayers/lib/OpenLayers/Format/SLD/v1_0_0.js",
                //            "openlayers/lib/OpenLayers/Format/SLD/v1.js",
                //            "openlayers/lib/OpenLayers/Format/WFST.js",
                //            "openlayers/lib/OpenLayers/Format/WFST/v1.js",
                //            "openlayers/lib/OpenLayers/Format/WFST/v1_0_0.js",
                //            "openlayers/lib/OpenLayers/Format/WFST/v1_1_0.js",
                //            "openlayers/lib/OpenLayers/Format/Text.js",
                "openlayers/lib/OpenLayers/Format/JSON.js",
                "openlayers/lib/OpenLayers/Format/GeoJSON.js",
                //            "openlayers/lib/OpenLayers/Format/WMC.js",
                //            "openlayers/lib/OpenLayers/Format/WMC/v1.js",
                //            "openlayers/lib/OpenLayers/Format/WMC/v1_0_0.js",
                //            "openlayers/lib/OpenLayers/Format/WMC/v1_1_0.js",
                //            "openlayers/lib/OpenLayers/Format/WMSCapabilities.js",
                //            "openlayers/lib/OpenLayers/Format/WMSCapabilities/v1_1.js",
                //            "openlayers/lib/OpenLayers/Format/WMSCapabilities/v1_1_0.js",
                //            "openlayers/lib/OpenLayers/Format/WMSCapabilities/v1_1_1.js",
                //            "openlayers/lib/OpenLayers/Format/WMSGetFeatureInfo.js",
                //            "openlayers/lib/OpenLayers/Layer/WFS.js",
                //            "openlayers/lib/OpenLayers/Control/GetFeature.js",
                "openlayers/lib/OpenLayers/Control/MouseToolbar.js",
                //            "openlayers/lib/OpenLayers/Control/NavToolbar.js",
                //            "openlayers/lib/OpenLayers/Control/PanPanel.js",
                //            "openlayers/lib/OpenLayers/Control/Pan.js",
                "openlayers/lib/OpenLayers/Control/ZoomIn.js",
                "openlayers/lib/OpenLayers/Control/ZoomOut.js",
                //            "openlayers/lib/OpenLayers/Control/ZoomPanel.js",
                //"openlayers/lib/OpenLayers/Control/EditingToolbar.js",
            
                //File added or modified for MapFaces
                "proj4js/lib/proj4js-combined.js",
                "proj4js/custom/proj4jsExt.js",
                "openlayers/custom/OpenLayers/Map.js",
                "openlayers/custom/OpenLayers/Layer/A4JRequest.js",
                "openlayers/custom/OpenLayers/Layer/MapFaces.js",
                "openlayers/custom/OpenLayers/Layer/mapfaces/Vector.js",
                "openlayers/custom/OpenLayers/Layer.js",
                "openlayers/custom/OpenLayers/Handler/FeatureExt.js",
                "openlayers/custom/OpenLayers/UtilExt.js",
                "openlayers/custom/OpenLayers/Handler/MouseWheel.js",
                "openlayers/custom/OpenLayers/Control/ZoomBoxOut.js",
                "openlayers/custom/OpenLayers/Control/SelectionZoomBox.js",
                "openlayers/custom/OpenLayers/Control/Graticule.js",
                "openlayers/custom/OpenLayers/Control/Save.js",
                "openlayers/custom/OpenLayers/Control/GetFeatureInfo.js",
                "openlayers/custom/OpenLayers/Control/GetCoverageMatrix.js",
                "openlayers/custom/OpenLayers/Control/Navigation.js",
                "openlayers/custom/OpenLayers/Control/MousePosition.js",
                "openlayers/custom/OpenLayers/Control/OverviewMapExt.js",
                "openlayers/custom/OpenLayers/Control/MouseWheelDefaults.js",
                "openlayers/custom/OpenLayers/Control/ScaleExt.js",
                "openlayers/custom/OpenLayers/Control/ScaleBar.js",
                "openlayers/custom/OpenLayers/Control/MeasureArea.js",
                "openlayers/custom/OpenLayers/Control/DeleteFeature.js",
                "openlayers/custom/OpenLayers/Control/PanelExt.js",
                "openlayers/custom/OpenLayers/Control/NavToolbar.js",
                "openlayers/custom/OpenLayers/Control/EditingToolbar.js",
                "openlayers/custom/OpenLayers/Control/DragPan.js",
                "openlayers/mfUtils.js"
                ); // etc.
        }else{
            jsfiles = new Array(
                "compressed/openlayers.min.js"
                );
        }
        var agent = navigator.userAgent;
        var docWrite = (agent.match("MSIE") || agent.match("Safari"));
        if(docWrite) {
            var allScriptTags = new Array(jsfiles.length);
        }
        var host = OpenLayers._getScriptLocation();     
        for (var i = 0; i < jsfiles.length; i++) {
            if (docWrite) {
                allScriptTags[i] = "<script src='" + host + jsfiles[i] +"'></script>"; 
            } else {
                var s = document.createElement("script");
                s.src = host + jsfiles[i];
                var h = document.getElementsByTagName("head").length ? 
                document.getElementsByTagName("head")[0] :
                document.body;
                h.appendChild(s);
            }
        }
        if (docWrite) {
            document.write(allScriptTags.join(""));
        }
    
    })();

    /**
 * Constant: VERSION_NUMBER
 */
    OpenLayers.VERSION_NUMBER="$Revision: 7335 $";
}