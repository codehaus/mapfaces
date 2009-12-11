/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */


/**
 * @requires OpenLayers/Handler.js
 */

/**
 * Class: OpenLayers.Handler.Feature
 * Handler to respond to mouse events related to a drawn feature.  Callbacks
 *     with the following keys will be notified of the following events
 *     associated with features: click, clickout, over, out, and dblclick.
 *
 * This handler stops event propagation for mousedown and mouseup if those
 *     browser events target features that can be selected.
 */
OpenLayers.Handler.Feature = OpenLayers.Class(OpenLayers.Handler.Feature, {



    /**
     * Method: moveLayerBack
     * Moves the layer back to the position determined by the map's layers
     * array.
     */
    moveLayerBack: function() {
            var index = this.layer.getZIndex() - 1;
            if (index >= this.map.Z_INDEX_BASE['Feature']) {
                this.layer.setZIndex(index);
            } else {
                this.map.setLayerZIndex(this.layer,
                    this.map.getLayerIndex(this.layer));
            }
    }
});
