/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/**
 * @requires OpenLayers/Control/OpenLayers.Control.ZoomBox.js
 */

/**
 * Class: OpenLayers.Control.ZoomBoxOut
 *
 * Inherits from:
 *  - <OpenLayers.Control.ZoomBox>
 */
OpenLayers.Control.ZoomBoxOut = OpenLayers.Class(OpenLayers.Control.ZoomBox, {
    
    /**
     * Property: out
     * {Boolean} Should the control be used for zooming out?
     */
    out: true,

    CLASS_NAME: "OpenLayers.Control.ZoomBoxOut"
});
