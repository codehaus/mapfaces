/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/**
 * @requires OpenCharts/Control.js
 * @requires OpenCharts/Handler/Box.js
 */

/**
 * Class: OpenCharts.Control.ZoomBox
 *
 * Inherits from:
 *  - <OpenCharts.Control>
 */
OpenCharts.Control.ZoomBox = OpenCharts.Class(OpenCharts.Control, {
    /**
     * Property: type
     * {OpenCharts.Control.TYPE}
     */
    type: OpenCharts.Control.TYPE_TOOL,

    /**
     * Property: out
     * {Boolean} Should the control be used for zooming out?
     */
    out: false,

    /**
     * Method: draw
     */    
    draw: function() {
        this.handler = new OpenCharts.Handler.Box( this,
        {done: this.zoomBox}, {keyMask: this.keyMask} );
    },

    /**
     * Method: zoomBox
     *
     * Parameters:
     * position - {<OpenCharts.Bounds>} or {<OpenCharts.Pixel>}
     */
    zoomBox: function (position) {
         this.map.renderer.zoomBox(position);
    },

    CLASS_NAME: "OpenCharts.Control.ZoomBox"
});
