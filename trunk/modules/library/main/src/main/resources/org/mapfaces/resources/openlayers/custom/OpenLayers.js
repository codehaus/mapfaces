/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/* 
 * @requires OpenLayers/BaseTypes.js
 * @requires OpenLayers/Lang/en.js
 */ 

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
        _scriptName: (!singleFile) ? "lib/OpenLayers.js" : "OpenLayers.js",

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
     * OpenLayers.singleFile is a flag indicating this file is being included
     * in a Single File Library build of the OpenLayers Library.
     * 
     * When we are *not* part of a SFL build we dynamically include the
     * OpenLayers library code.
     * 
     * When we *are* part of a SFL build we do not dynamically include the 
     * OpenLayers library code as it will be appended at the end of this file.
      */
    if(!singleFile) {
        var host = "/mapfaces/resource.jsf?r=/org/mapfaces/resources/openlayers/";
        var jsfiles = new Array(
           "lib/OpenLayers/Util.js",
           "lib/OpenLayers/BaseTypes.js",
           "lib/OpenLayers/BaseTypes/Class.js",
           "lib/OpenLayers/BaseTypes/Bounds.js",
           "lib/OpenLayers/BaseTypes/Element.js",
           "lib/OpenLayers/BaseTypes/LonLat.js",
           "lib/OpenLayers/BaseTypes/Pixel.js",
           "lib/OpenLayers/BaseTypes/Size.js",
           "lib/OpenLayers/Console.js",
           "lib/OpenLayers/Tween.js",
           "lib/Rico/Corner.js",
           "lib/Rico/Color.js",
           "lib/OpenLayers/Ajax.js",
           "lib/OpenLayers/Request.js",
           "lib/OpenLayers/Request/XMLHttpRequest.js",
           "lib/OpenLayers/Events.js",
           "lib/OpenLayers/Projection.js",           
           "../proj4js/proj4js-combined.js",
           "custom/OpenLayers/Map.js",
           "lib/OpenLayers/Layer.js",
           "lib/OpenLayers/Icon.js",
           "lib/OpenLayers/Marker.js",
           "lib/OpenLayers/Marker/Box.js",
           "lib/OpenLayers/Popup.js",
           /*/"lib/OpenLayers/Tile.js",
           "lib/OpenLayers/Tile/Image.js",
           "lib/OpenLayers/Tile/WFS.js",*/
           /*"lib/OpenLayers/Layer/Image.js",
           "lib/OpenLayers/Layer/SphericalMercator.js",
           "lib/OpenLayers/Layer/EventPane.js",
           "lib/OpenLayers/Layer/FixedZoomLevels.js",
           "lib/OpenLayers/Layer/Google.js",
           "lib/OpenLayers/Layer/VirtualEarth.js",
           "lib/OpenLayers/Layer/Yahoo.js",
           "lib/OpenLayers/Layer/HTTPRequest.js",
           "lib/OpenLayers/Layer/Grid.js",
           "lib/OpenLayers/Layer/MapGuide.js",
           "lib/OpenLayers/Layer/MapServer.js",
           "lib/OpenLayers/Layer/MapServer/Untiled.js",
           "lib/OpenLayers/Layer/KaMap.js",
           "lib/OpenLayers/Layer/MultiMap.js",*/
           "lib/OpenLayers/Layer/Markers.js",
           "lib/OpenLayers/Layer/Text.js",
           /*"lib/OpenLayers/Layer/WorldWind.js",
           "lib/OpenLayers/Layer/WMS.js",
           "lib/OpenLayers/Layer/WMS/Untiled.js",*/
           "lib/OpenLayers/Layer/GeoRSS.js",
           "lib/OpenLayers/Layer/Boxes.js",
           /*"lib/OpenLayers/Layer/TMS.js",
           "lib/OpenLayers/Layer/TileCache.js",*/
           "lib/OpenLayers/Popup/Anchored.js",
           "lib/OpenLayers/Popup/AnchoredBubble.js",
           "lib/OpenLayers/Popup/Framed.js",
           "lib/OpenLayers/Popup/FramedCloud.js",
           "lib/OpenLayers/Feature.js",
           "lib/OpenLayers/Feature/Vector.js",
           "lib/OpenLayers/Feature/WFS.js",
           "lib/OpenLayers/Handler.js",
           "lib/OpenLayers/Handler/Click.js",
           "lib/OpenLayers/Handler/Hover.js",
           "lib/OpenLayers/Handler/Point.js",
           "lib/OpenLayers/Handler/Path.js",
           "lib/OpenLayers/Handler/Polygon.js",
           "lib/OpenLayers/Handler/Feature.js",
           "lib/OpenLayers/Handler/Drag.js",
           "lib/OpenLayers/Handler/RegularPolygon.js",
           "lib/OpenLayers/Handler/Box.js",
           "custom/OpenLayers/Handler/MouseWheel.js",
           "lib/OpenLayers/Handler/Keyboard.js",
           "lib/OpenLayers/Control.js",
           "lib/OpenLayers/Control/Attribution.js",
           "lib/OpenLayers/Control/Button.js",
           "lib/OpenLayers/Control/ZoomBox.js",
           "custom/OpenLayers/Control/ZoomBoxOut.js",
           "lib/OpenLayers/Control/ZoomToMaxExtent.js",
           "lib/OpenLayers/Control/DragPan.js",
           "custom/OpenLayers/Control/Navigation.js",
           "lib/OpenLayers/Control/MouseDefaults.js",
           "custom/OpenLayers/Control/MousePosition.js",
           "lib/OpenLayers/Control/OverviewMap.js",
           "lib/OpenLayers/Control/KeyboardDefaults.js",
           "custom/OpenLayers/Control/MouseWheelDefaults.js",
           "lib/OpenLayers/Control/PanZoom.js",
           "lib/OpenLayers/Control/PanZoomBar.js",
           "lib/OpenLayers/Control/ArgParser.js",
           "lib/OpenLayers/Control/Permalink.js",
           "lib/OpenLayers/Control/Scale.js",
           "lib/OpenLayers/Control/ScaleLine.js",
           "lib/OpenLayers/Control/LayerSwitcher.js",
           "lib/OpenLayers/Control/DrawFeature.js",
           "lib/OpenLayers/Control/DragFeature.js",
           "lib/OpenLayers/Control/ModifyFeature.js",
           "lib/OpenLayers/Control/Panel.js",
           "lib/OpenLayers/Control/SelectFeature.js",
           "lib/OpenLayers/Control/NavigationHistory.js",
           "lib/OpenLayers/Geometry.js",
           "lib/OpenLayers/Geometry/Rectangle.js",
           "lib/OpenLayers/Geometry/Collection.js",
           "lib/OpenLayers/Geometry/Point.js",
           "lib/OpenLayers/Geometry/MultiPoint.js",
           "lib/OpenLayers/Geometry/Curve.js",
           "lib/OpenLayers/Geometry/LineString.js",
           "lib/OpenLayers/Geometry/LinearRing.js",        
           "lib/OpenLayers/Geometry/Polygon.js",
           "lib/OpenLayers/Geometry/MultiLineString.js",
           "lib/OpenLayers/Geometry/MultiPolygon.js",
           "lib/OpenLayers/Geometry/Surface.js",
           "lib/OpenLayers/Renderer.js",
           "lib/OpenLayers/Renderer/Elements.js",
           "lib/OpenLayers/Renderer/SVG.js",
           "lib/OpenLayers/Renderer/VML.js",
           "lib/OpenLayers/Layer/Vector.js",
           "lib/OpenLayers/Layer/PointTrack.js",
           "lib/OpenLayers/Layer/GML.js",
           "lib/OpenLayers/Style.js",
           "lib/OpenLayers/StyleMap.js",
           "lib/OpenLayers/Rule.js",
           "lib/OpenLayers/Filter.js",
           "lib/OpenLayers/Filter/FeatureId.js",
           "lib/OpenLayers/Filter/Logical.js",
           "lib/OpenLayers/Filter/Comparison.js",
           "lib/OpenLayers/Format.js",
           "lib/OpenLayers/Format/XML.js",
           "lib/OpenLayers/Format/GML.js",
           "lib/OpenLayers/Format/KML.js",
           "lib/OpenLayers/Format/GeoRSS.js",
           //"lib/OpenLayers/Format/WFS.js",
           //"lib/OpenLayers/Format/WKT.js",
           "lib/OpenLayers/Format/OSM.js",
           "lib/OpenLayers/Format/SLD.js",
           "lib/OpenLayers/Format/SLD/v1.js",
           "lib/OpenLayers/Format/SLD/v1_0_0.js",
           "lib/OpenLayers/Format/Text.js",
           "lib/OpenLayers/Format/JSON.js",
           "lib/OpenLayers/Format/GeoJSON.js",
          /*/ "lib/OpenLayers/Format/WMC.js",
           "lib/OpenLayers/Format/WMC/v1.js",
           "lib/OpenLayers/Format/WMC/v1_0_0.js",
           "lib/OpenLayers/Format/WMC/v1_1_0.js",*/
           "lib/OpenLayers/Layer/WFS.js",
           "lib/OpenLayers/Control/MouseToolbar.js",
           "custom/OpenLayers/Control/NavToolbar.js",
           "lib/OpenLayers/Control/EditingToolbar.js",
           "lib/OpenLayers/Lang.js",
           "lib/OpenLayers/Lang/en.js"
        ); // etc.

        var agent = navigator.userAgent;
        var docWrite = (agent.match("MSIE") || agent.match("Safari"));
        if(docWrite) {
            var allScriptTags = new Array(jsfiles.length);
        }
        //var host = OpenLayers._getScriptLocation()+"lib/";    
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
    }
})();

/**
 * Constant: VERSION_NUMBER
 */
OpenLayers.VERSION_NUMBER="$Revision: 7335 $";
