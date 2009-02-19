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
    var singleFile = true;

    /**
     * Namespace: OpenLayers
     * The OpenLayers object provides a namespace for all things OpenLayers
     */
    window.OpenLayers = {

        /**
         * Property: _scriptName
         * {String} Relative path of this script.
         */
        _scriptName: (singleFile) ? "openlayers/custom/OpenLayersLite.js" : "OpenLayersLite.js",
        //_scriptName: (!singleFile) ? "resource.jsf?r=/org/mapfaces/resources//lib/OpenLayers.js" : "resource.jsf?r=/org/mapfaces/resources/.js",

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
     * resource.jsf?r=/org/mapfaces/resources/
      */
     
    if(singleFile) {
        var jsfiles = new Array(
           "compressed/openlayers.min.js"
        );

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
    }
})();

/**
 * Constant: VERSION_NUMBER
 */
OpenLayers.VERSION_NUMBER="$Revision: 7335 $";
}
