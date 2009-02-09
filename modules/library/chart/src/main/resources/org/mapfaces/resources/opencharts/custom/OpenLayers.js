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
        var jsfiles = new Array(
            "openlayers/lib/OpenLayers/Util.js",
            "openlayers/lib/OpenLayers/BaseTypes.js",
            "openlayers/lib/OpenLayers/BaseTypes/Class.js",
            "openlayers/lib/OpenLayers/BaseTypes/Bounds.js",
            "openlayers/lib/OpenLayers/BaseTypes/Element.js",
            "openlayers/lib/OpenLayers/BaseTypes/LonLat.js",
            "openlayers/lib/OpenLayers/BaseTypes/Pixel.js",
            "openlayers/lib/OpenLayers/BaseTypes/Size.js",
            "openlayers/lib/OpenLayers/Ajax.js",
            "openlayers/lib/OpenLayers/Request.js",
            "openlayers/lib/OpenLayers/Request/XMLHttpRequest.js",
            "openlayers/lib/OpenLayers/Events.js",
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
            "openlayers/lib/OpenLayers/Handler/MouseWheel.js",
            "openlayers/lib/OpenLayers/Handler/Keyboard.js",
            "openlayers/lib/OpenLayers/Control.js",
            "openlayers/lib/OpenLayers/Control/Attribution.js",
            "openlayers/lib/OpenLayers/Control/Navigation.js",
            "openlayers/lib/OpenLayers/Control/MouseDefaults.js",
            "openlayers/lib/OpenLayers/Control/KeyboardDefaults.js",
            "openlayers/lib/OpenLayers/Control/ArgParser.js",
            "openlayers/lib/OpenLayers/Control/NavigationHistory.js",
    //File addes or modified for MapFaces
           "openlayers/custom/OpenLayers/Map.js",
           "openlayers/custom/OpenLayers/Handler/MouseWheel.js",
           "openlayers/custom/OpenLayers/Control/Navigation.js",
           "openlayers/custom/OpenLayers/Control/MousePosition.js",
           "openlayers/custom/OpenLayers/Control/MouseWheelDefaults.js"
        ); // etc.

        var agent = navigator.userAgent;
        var docWrite = (agent.match("MSIE") || agent.match("Safari"));
        if(docWrite) {
            var allScriptTags = new Array(jsfiles.length);
        }
        var host = OpenLayers._getScriptLocation();     
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
OpenLayers.VERSION_NUMBER="$Revision: 7335 $";
}