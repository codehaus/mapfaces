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
     * Namespace: OpenLayers
     * The OpenLayers object provides a namespace for all things OpenLayers
     */
    window.OpenLayers = {
        /**
         * Before creating the OpenLayers namespace, check to see if
         * OpenLayers.singleFile is true.  This occurs if the
         * OpenLayers/SingleFile.js script is included before this one - as is the
         * case with single file builds.
         */
        singleFile : true,
        /**
         * Property: _scriptName
         * {String} Relative path of this script.
         */
        _scriptName: "OpenLayersLite.js",
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
  
})();

/**
 * Constant: VERSION_NUMBER
 */
OpenLayers.VERSION_NUMBER="$Revision: 7335 $";
}
