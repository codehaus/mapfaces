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
     * APIProperty: displaySystem
     * {String} Display system for scale bar - metric or english supported.
     *     Default is metric.
     */
    displaySystem: OpenLayers.i18n('displaySystem'),
    /**
     * Property: measurementProperties
     * {Object} Holds display units, abbreviations, and conversion to inches
     * (since we're using dpi) per measurement sytem.
     */
    measurementProperties: {
        english: {
            units: ['miles', 'feet', 'inches'],
            abbr: ['mi', 'ft', 'in'],
            inches: [63360, 12, 1]
        },
        metric: {
            units: [OpenLayers.i18n("scaleUnitsKilometers"), OpenLayers.i18n("scaleUnitsMeters"), OpenLayers.i18n("scaleUnitsCentimeters")],
            abbr: ['km', 'm', 'cm'],
            inches: [39370.07874, 39.370079, 0.393701]
        }
    },
    /**
     * Constructor: OpenLayers.Control.Scale
     *
     * Parameters:
     * element - {DOMElement}
     * options - {Object}
     */
    initialize: function(options) {
        OpenLayers.Control.prototype.initialize.apply(this, [options]);
    },
    /**
     * Method: updateScale
     */
    updateScale: function() {
        var scale = this.map.getScale();
         this.element.title=scale;
        if (!scale) {
            return;
        }
        scale = Math.round(scale);
        var unitsStr ="";

         if (scale >= 1000 && scale < 1000000) {
            unitsStr = " m";//this.measurementProperties[this.displaySystem].abbr[1];
            scale = Math.round(scale / 1000);
        } else if (scale >= 1000000 ) {
            unitsStr = " km";//this.measurementProperties[this.displaySystem].abbr[0];
            scale = Math.round(scale / 1000000);
        } else {
            unitsStr = " cm";//kmthis.measurementProperties[this.displaySystem].abbr[2];
            scale = Math.round(scale);
        }

        var scaleStr =""+scale+"";

        if (scale >= 1000 && scale < 1000000) {
            scaleStr= scaleStr.substring(0, scaleStr.length-3)+"."+scaleStr.substring(scaleStr.length-3, scaleStr.length);
        } else  if (scaleStr >= 1000000 && scaleStr < 1000000000) {
            scaleStr = scaleStr.substring(0, scaleStr.length-6)+"."+scaleStr.substring(scaleStr.length-6, scaleStr.length-3)+"."+scaleStr.substring(scaleStr.length-3, scaleStr.length);
        } else if (scale < 1000) {
            scaleStr=scale;
        }
        this.element.innerHTML = OpenLayers.i18n("scale", {'scaleDenom':scaleStr+" "+unitsStr});
    },
    /**
     * Method: setMap
     */
    setMap: function() {
        OpenLayers.Control.prototype.setMap.apply(this, arguments);
        this.map.events.register( 'moveend', this, this.updateScale);
    },
    CLASS_NAME: "OpenLayers.Control.Scale"
});

