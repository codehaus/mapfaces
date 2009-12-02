/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/* 
 * @requires OpenCharts/BaseTypes.js
 * @requires OpenCharts/Lang/en.js
 */ 

(function() {
    /**
     * Before creating the OpenCharts namespace, check to see if
     * OpenCharts.singleFile is true.  This occurs if the
     * OpenCharts/SingleFile.js script is included before this one - as is the
     * case with single file builds.
     */
    var singleFile = (typeof OpenCharts == "object" && OpenCharts.singleFile);
    
    /**
     * Namespace: OpenCharts
     * The OpenCharts object provides a namespace for all things OpenCharts
     */
    window.OpenCharts = {
        
        /**
         * Property: _scriptName
         * {String} Relative path of this script.
         */
        _scriptName: (!singleFile) ? "lib/OpenCharts.js" : "OpenCharts.js",

        /**
         * Function: _getScriptLocation
         * Return the path to this script.
         *
         * Returns:
         * {String} Path to this script
         */
        _getScriptLocation: function () {
            var scriptLocation = "";
            var scriptName = OpenCharts._scriptName;
         
            var scripts = document.getElementsByTagName('script');
            for (var i=0, len=scripts.length; i<len; i++) {
                var src = scripts[i].getAttribute('src');
                if (src) {
                    var index = src.lastIndexOf(scriptName); 
                    // set path length for src up to a query string
                    var pathLength = src.lastIndexOf('?');
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
     * OpenCharts.singleFile is a flag indicating this file is being included
     * in a Single File Library build of the OpenCharts Library.
     * 
     * When we are *not* part of a SFL build we dynamically include the
     * OpenCharts library code.
     * 
     * When we *are* part of a SFL build we do not dynamically include the 
     * OpenCharts library code as it will be appended at the end of this file.
      */
    if(!singleFile) {
        var jsfiles = new Array(
            "OpenCharts/Util.js",
            "OpenCharts/BaseTypes.js",
            "OpenCharts/BaseTypes/Class.js",
            "OpenCharts/BaseTypes/Bounds.js",
            "OpenCharts/BaseTypes/Element.js",
            "OpenCharts/BaseTypes/LonLat.js",
            "OpenCharts/BaseTypes/Pixel.js",
            "OpenCharts/BaseTypes/Size.js",
            "OpenCharts/Console.js",
            "OpenCharts/Tween.js",
            "Rico/Corner.js",
            "Rico/Color.js",
            "Gears/gears_init.js",
            "OpenCharts/Ajax.js",
            "OpenCharts/Request.js",
            "OpenCharts/Request/XMLHttpRequest.js",
            "OpenCharts/Events.js",
            "OpenCharts/Projection.js",
            "OpenCharts/Map.js",
            "OpenCharts/Layer.js",
            "OpenCharts/Icon.js",
            "OpenCharts/Marker.js",
            "OpenCharts/Marker/Box.js",
            "OpenCharts/Popup.js",
            "OpenCharts/Tile.js",
            "OpenCharts/Tile/Image.js",
            "OpenCharts/Tile/WFS.js",
            "OpenCharts/Layer/Image.js",
            "OpenCharts/Layer/SphericalMercator.js",
            "OpenCharts/Layer/EventPane.js",
            "OpenCharts/Layer/FixedZoomLevels.js",
            "OpenCharts/Layer/Google.js",
            "OpenCharts/Layer/VirtualEarth.js",
            "OpenCharts/Layer/Yahoo.js",
            "OpenCharts/Layer/HTTPRequest.js",
            "OpenCharts/Layer/Grid.js",
            "OpenCharts/Layer/MapGuide.js",
            "OpenCharts/Layer/MapServer.js",
            "OpenCharts/Layer/MapServer/Untiled.js",
            "OpenCharts/Layer/KaMap.js",
            "OpenCharts/Layer/KaMapCache.js",
            "OpenCharts/Layer/MultiMap.js",
            "OpenCharts/Layer/Markers.js",
            "OpenCharts/Layer/Text.js",
            "OpenCharts/Layer/WorldWind.js",
            "OpenCharts/Layer/WMS.js",
            "OpenCharts/Layer/WMS/Untiled.js",
            "OpenCharts/Layer/GeoRSS.js",
            "OpenCharts/Layer/Boxes.js",
            "OpenCharts/Layer/TMS.js",
            "OpenCharts/Layer/TileCache.js",
            "OpenCharts/Popup/Anchored.js",
            "OpenCharts/Popup/AnchoredBubble.js",
            "OpenCharts/Popup/Framed.js",
            "OpenCharts/Popup/FramedCloud.js",
            "OpenCharts/Feature.js",
            "OpenCharts/Feature/Vector.js",
            "OpenCharts/Feature/WFS.js",
            "OpenCharts/Handler.js",
            "OpenCharts/Handler/Click.js",
            "OpenCharts/Handler/Hover.js",
            "OpenCharts/Handler/Point.js",
            "OpenCharts/Handler/Path.js",
            "OpenCharts/Handler/Polygon.js",
            "OpenCharts/Handler/Feature.js",
            "OpenCharts/Handler/Drag.js",
            "OpenCharts/Handler/RegularPolygon.js",
            "OpenCharts/Handler/Box.js",
            "OpenCharts/Handler/MouseWheel.js",
            "OpenCharts/Handler/Keyboard.js",
            "OpenCharts/Control.js",
            "OpenCharts/Control/Attribution.js",
            "OpenCharts/Control/Button.js",
            "OpenCharts/Control/ZoomBox.js",
            "OpenCharts/Control/ZoomToMaxExtent.js",
            "OpenCharts/Control/DragPan.js",
            "OpenCharts/Control/Navigation.js",
            "OpenCharts/Control/MouseDefaults.js",
            "OpenCharts/Control/MousePosition.js",
            "OpenCharts/Control/OverviewMap.js",
            "OpenCharts/Control/KeyboardDefaults.js",
            "OpenCharts/Control/PanZoom.js",
            "OpenCharts/Control/PanZoomBar.js",
            "OpenCharts/Control/ArgParser.js",
            "OpenCharts/Control/Permalink.js",
            "OpenCharts/Control/Scale.js",
            "OpenCharts/Control/ScaleLine.js",
            "OpenCharts/Control/LayerSwitcher.js",
            "OpenCharts/Control/DrawFeature.js",
            "OpenCharts/Control/DragFeature.js",
            "OpenCharts/Control/ModifyFeature.js",
            "OpenCharts/Control/Panel.js",
            "OpenCharts/Control/SelectFeature.js",
            "OpenCharts/Control/NavigationHistory.js",
            "OpenCharts/Control/Measure.js",
            "OpenCharts/Geometry.js",
            "OpenCharts/Geometry/Rectangle.js",
            "OpenCharts/Geometry/Collection.js",
            "OpenCharts/Geometry/Point.js",
            "OpenCharts/Geometry/MultiPoint.js",
            "OpenCharts/Geometry/Curve.js",
            "OpenCharts/Geometry/LineString.js",
            "OpenCharts/Geometry/LinearRing.js",        
            "OpenCharts/Geometry/Polygon.js",
            "OpenCharts/Geometry/MultiLineString.js",
            "OpenCharts/Geometry/MultiPolygon.js",
            "OpenCharts/Geometry/Surface.js",
            "OpenCharts/Renderer.js",
            "OpenCharts/Renderer/Elements.js",
            "OpenCharts/Renderer/SVG.js",
            "OpenCharts/Renderer/Canvas.js",
            "OpenCharts/Renderer/VML.js",
            "OpenCharts/Layer/Vector.js",
            "OpenCharts/Strategy.js",
            "OpenCharts/Strategy/Fixed.js",
            "OpenCharts/Strategy/Cluster.js",
            "OpenCharts/Strategy/Paging.js",
            "OpenCharts/Strategy/BBOX.js",
            "OpenCharts/Protocol.js",
            "OpenCharts/Protocol/HTTP.js",
            "OpenCharts/Protocol/SQL.js",
            "OpenCharts/Protocol/SQL/Gears.js",
            "OpenCharts/Layer/PointTrack.js",
            "OpenCharts/Layer/GML.js",
            "OpenCharts/Style.js",
            "OpenCharts/StyleMap.js",
            "OpenCharts/Rule.js",
            "OpenCharts/Filter.js",
            "OpenCharts/Filter/FeatureId.js",
            "OpenCharts/Filter/Logical.js",
            "OpenCharts/Filter/Comparison.js",
            "OpenCharts/Filter/Spatial.js",
            "OpenCharts/Format.js",
            "OpenCharts/Format/XML.js",
            "OpenCharts/Format/GML.js",
            "OpenCharts/Format/GML/Base.js",
            "OpenCharts/Format/GML/v2.js",
            "OpenCharts/Format/GML/v3.js",
            "OpenCharts/Format/KML.js",
            "OpenCharts/Format/GeoRSS.js",
            "OpenCharts/Format/WFS.js",
            "OpenCharts/Format/WKT.js",
            "OpenCharts/Format/OSM.js",
            "OpenCharts/Format/GPX.js",
            "OpenCharts/Format/SLD.js",
            "OpenCharts/Format/SLD/v1.js",
            "OpenCharts/Format/SLD/v1_0_0.js",
            "OpenCharts/Format/SLD/v1.js",
            "OpenCharts/Format/Filter.js",
            "OpenCharts/Format/Filter/v1.js",
            "OpenCharts/Format/Filter/v1_0_0.js",
            "OpenCharts/Format/Text.js",
            "OpenCharts/Format/JSON.js",
            "OpenCharts/Format/GeoJSON.js",
            "OpenCharts/Format/WMC.js",
            "OpenCharts/Format/WMC/v1.js",
            "OpenCharts/Format/WMC/v1_0_0.js",
            "OpenCharts/Format/WMC/v1_1_0.js",
            "OpenCharts/Layer/WFS.js",
            "OpenCharts/Control/MouseToolbar.js",
            "OpenCharts/Control/NavToolbar.js",
            "OpenCharts/Control/PanPanel.js",
            "OpenCharts/Control/Pan.js",
            "OpenCharts/Control/ZoomIn.js",
            "OpenCharts/Control/ZoomOut.js",
            "OpenCharts/Control/ZoomPanel.js",
            "OpenCharts/Control/EditingToolbar.js",
            "OpenCharts/Lang.js",
            "OpenCharts/Lang/en.js"
        ); // etc.

        var agent = navigator.userAgent;
        var docWrite = (agent.match("MSIE") || agent.match("Safari"));
        if(docWrite) {
            var allScriptTags = new Array(jsfiles.length);
        }
        var host = OpenCharts._getScriptLocation() + "lib/";    
        for (var i=0, len=jsfiles.length; i<len; i++) {
            if (docWrite) {
                allScriptTags[i] = "<script src='" + host + jsfiles[i] +
                                   "'></script>"; 
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
    }
})();

/**
 * Constant: VERSION_NUMBER
 */
OpenCharts.VERSION_NUMBER="$Revision: 8012 $";
