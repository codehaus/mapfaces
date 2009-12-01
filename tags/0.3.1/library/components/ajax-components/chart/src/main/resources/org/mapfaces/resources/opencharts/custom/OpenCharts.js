/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/* 
 * @requires OpenCharts/BaseTypes.js
 * @requires OpenCharts/Lang/en.js
 */ 
if(!window.OpenCharts){
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
        _scriptName: (!singleFile) ? "OpenCharts/custom/OpenCharts.js" : "OpenCharts.js",
        //_scriptName: (!singleFile) ? "OpenCharts/lib/OpenCharts.js" : "OpenCharts.js",
        
        /**
         * Function: _getScriptLocation
         * Return the path to this script.
         *fappendchild
         
         * Returns:
         * {String} Path to this script
         */
        _getScriptLocation: function () {
            var scriptLocation = "";
            var scriptName = OpenCharts._scriptName;
         
            var scripts = document.getElementsByTagName('script');
            for (var i = 0; i < scripts.length; i++) {
                var src = scripts[i].getAttributeNS('http://www.w3.org/1999/xlink','href');
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
            "OpenCharts/lib/OpenCharts/Util.js",
            "OpenCharts/lib/OpenCharts/BaseTypes.js",
            "OpenCharts/lib/OpenCharts/BaseTypes/Class.js",
            "OpenCharts/lib/OpenCharts/BaseTypes/Bounds.js",
            "OpenCharts/lib/OpenCharts/BaseTypes/Element.js",
            "OpenCharts/lib/OpenCharts/BaseTypes/LonLat.js",
            "OpenCharts/lib/OpenCharts/BaseTypes/Pixel.js",
            "OpenCharts/lib/OpenCharts/BaseTypes/Size.js",
            "OpenCharts/lib/OpenCharts/Ajax.js",
            "OpenCharts/lib/OpenCharts/Request.js",
            "OpenCharts/lib/OpenCharts/Request/XMLHttpRequest.js",
            "OpenCharts/lib/OpenCharts/Events.js",
            "OpenCharts/lib/OpenCharts/Map.js",
           "OpenCharts/custom/OpenCharts/MapExt.js",
            "OpenCharts/lib/OpenCharts/Handler.js",
            "OpenCharts/lib/OpenCharts/Handler/Click.js",
            "OpenCharts/lib/OpenCharts/Handler/Hover.js",
            "OpenCharts/lib/OpenCharts/Handler/Drag.js",
            "OpenCharts/lib/OpenCharts/Handler/Box.js",
            "OpenCharts/lib/OpenCharts/Handler/MouseWheel.js",
            "OpenCharts/lib/OpenCharts/Handler/Keyboard.js",
            "OpenCharts/lib/OpenCharts/Control.js",
            "OpenCharts/lib/OpenCharts/Geometry.js",
            "OpenCharts/lib/OpenCharts/Geometry/Rectangle.js",
            "OpenCharts/lib/OpenCharts/Geometry/Collection.js",
            "OpenCharts/lib/OpenCharts/Geometry/Point.js",
            "OpenCharts/lib/OpenCharts/Geometry/MultiPoint.js",
            "OpenCharts/lib/OpenCharts/Geometry/Curve.js",
            "OpenCharts/lib/OpenCharts/Geometry/LineString.js",
            "OpenCharts/lib/OpenCharts/Geometry/LinearRing.js",        
            "OpenCharts/lib/OpenCharts/Geometry/Polygon.js",
            "OpenCharts/lib/OpenCharts/Geometry/MultiLineString.js",
            "OpenCharts/lib/OpenCharts/Geometry/MultiPolygon.js",
            "OpenCharts/lib/OpenCharts/Geometry/Surface.js",
            "OpenCharts/lib/OpenCharts/Renderer.js",
            "OpenCharts/lib/OpenCharts/Renderer/Elements.js",
            "OpenCharts/custom/OpenCharts/Renderer/ElementsExt.js",
            "OpenCharts/lib/OpenCharts/Renderer/SVG.js",
           "OpenCharts/custom/OpenCharts/Renderer/SVGExt.js",
            "OpenCharts/lib/OpenCharts/Renderer/Canvas.js",
            "OpenCharts/lib/OpenCharts/Renderer/VML.js",
           "OpenCharts/custom/OpenCharts/Renderer/VMLExt.js",
    //File addes or modified for MapFaces
           "OpenCharts/custom/OpenCharts/Handler/MouseWheel.js",
            "OpenCharts/custom/OpenCharts/Control/DragPan.js",
            "OpenCharts/custom/OpenCharts/Control/ZoomBox.js",
            "OpenCharts/custom/OpenCharts/Control/Navigation.js"
        ); // etc.

        var agent = navigator.userAgent;
        var docWrite = (agent.match("MSIE") || agent.match("Safari"));
        if(docWrite) {
            var allScriptTags = new Array(jsfiles.length);
        }
        var host = OpenCharts._getScriptLocation();     
        for (var i = 0; i < jsfiles.length; i++) {
            if (docWrite) {
                allScriptTags[i] = "<script xlink:href='" + host + jsfiles[i] +"'></script>"; 
            } else {
                var s = document.createElement("script");
                s.setAttributeNS("http://www.w3.org/1999/xlink","xlink:href",host + jsfiles[i]);
                var h = document.getElementsByTagName("head").length ? 
                           document.getElementsByTagName("head")[0] : 
                           document.body;
                //CHART : if the document is an SVGDocument
                if (!h) h =document.documentElement;
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
OpenCharts.VERSION_NUMBER="$Revision: 7335 $";
}