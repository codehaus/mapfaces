/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/**
 * @requires OpenLayers/Control.js
 * @requires OpenLayers/Handler/Drag.js
 * @requires OpenLayers/Control/DragPan.js
 */

/**
 * Class: OpenLayers.Control.DragPan
 * The DragPan control pans the map with a drag of the mouse.
 *
 * Inherits from:
 *  - <OpenLayers.Control>
 */
OpenLayers.Control.DragPan = OpenLayers.Class(OpenLayers.Control.DragPan, {
    /**
     * Method: activate
     */
    activate: function () {

        if (this.active) {
            return false;
        }
        if (this.handler) {
            this.handler.activate();
            this.map.div.style.cursor='move';
        }
        this.active = true;
        this.events.triggerEvent("activate");
        return true;
    },
    /**
     * Method: deactivate
     */
    deactivate: function() {

        if (this.active) {
            this.map.div.style.cursor='default';
            if (this.handler) {
                this.handler.deactivate();
            }
            this.active = false;
            this.events.triggerEvent("deactivate");
            return true;
        }
        return false;
    }
});
