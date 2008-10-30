/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */


/**
 * @requires OpenLayers/Control.js
 * @requires OpenLayers/Scale.js
 */

/**
 * Class: OpenLayers.Control.Scale
 * Display a small scale indicator on the map.
 * Inherits from:
 *  - <OpenLayers.Control>
 */
OpenLayers.Control.Scale = OpenLayers.Class(OpenLayers.Control.Scale, {
    
   
    /**
     * Method: updateScale
     */
    updateScale: function() {
        var scale = this.map.getScale();
        if (!scale) {
            return;
        }
        scale = Math.round(scale);        
        var scaleStr =""+scale+"";
        if (scale >= 1000 && scale < 1000000) {
           scaleStr= scaleStr.substring(0, scaleStr.length-3)+"."+scaleStr.substring(scaleStr.length-3, scaleStr.length);
        } else  if (scaleStr >= 1000000 && scaleStr < 1000000000) {
           scaleStr = scaleStr.substring(0, scaleStr.length-6)+"."+scaleStr.substring(scaleStr.length-6, scaleStr.length-3)+"."+scaleStr.substring(scaleStr.length-3, scaleStr.length);
        } else {
            scaleStr=scale;
        }    
        this.element.innerHTML = OpenLayers.i18n("scale", {'scaleDenom':scaleStr});
    }, 

    CLASS_NAME: "OpenLayers.Control.Scale"
});

