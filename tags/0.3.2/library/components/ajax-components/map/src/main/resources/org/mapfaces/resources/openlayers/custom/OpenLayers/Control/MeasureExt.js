/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/**
 * @requires OpenLayers/Control.js
 * @requires OpenLayers/Feature/Vector.js
 */

/**
 * Class: OpenLayers.Control.Measure
 * Allows for drawing of features for measurements.
 *
 * Inherits from:
 *  - <OpenLayers.Control>
 */
OpenLayers.Control.Measure = OpenLayers.Class(OpenLayers.Control.Measure, {

    outputElemId: 'output',

    handleMeasurements: function (event) {
        var geometry = event.geometry;
        var units = event.units;
        var order = event.order;
        var measure = event.measure;
        var element = document.getElementById(this.outputElemId);
        var out = "";
        
        if(order == 1) {
            out += "Distance: " + measure.toFixed(3) + " " + units;

        } else {
            out += "Distance: " + measure.toFixed(3) + " " + units + "<sup>2</sup>";
        }
        element.innerHTML = out;
    }

    
});
