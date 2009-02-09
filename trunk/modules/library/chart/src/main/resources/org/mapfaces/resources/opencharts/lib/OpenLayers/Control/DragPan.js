/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/**
 * @requires OpenLayers/Control.js
 * @requires OpenLayers/Handler/Drag.js
 */

/**
 * Class: OpenLayers.Control.DragPan
 * DragPan control.
 *
 * Inherits from:
 *  - <OpenLayers.Control>
 */
OpenLayers.Control.DragPan = OpenLayers.Class(OpenLayers.Control, {

    /** 
     * Property: type
     * {OpenLayers.Control.TYPES}
     */
    type: OpenLayers.Control.TYPE_TOOL,
    
    /**
     * Property: panned
     * {Boolean} The map moved.
     */
    panned: false,
    
    /**
     * Property: interval
     * {Integer} The number of milliseconds that should ellapse before
     *     panning the map again. Set this to increase dragging performance.
     *     Defaults to 25 milliseconds.
     */
    interval: 25, 
    
    /**
     * Method: draw
     * Creates a Drag handler, using <panMap> and
     * <panMapDone> as callbacks.
     */    
    draw: function() {
        this.handler = new OpenLayers.Handler.Drag(this, {
                "move": this.panMap,
                "done": this.panMapDone
            }, {
                interval: this.interval
            }
        );
    },

    /**
    * Method: panMap
    *
    * Parameters:
    * xy - {<OpenLayers.Pixel>} Pixel of the mouse position
    */
    panMap: function(xy) {
        this.panned = true;
//        if(this.map.isSVG) {
//            var p = document.documentElement.createSVGPoint();
//            // p now contains the mouse position in browser client area in pixels
//            p.x = xy.x;
//            p.y = xy.y;
//
//            screenCTMInv = getScreenCTM().inverse();
//            // p now contains the mouse position in SVG user coords
//            p = p.matrixTransform(screenCTMInv);
//
//            // nMouseOffsetX keeps track of how far the mouse dragged 
//            // since the last movement
//            nMouseOffsetX = p.x - parseFloat(this.map.getAttribute("x"));
//            nMouseOffsetY = p.y - parseFloat(this.map.getAttribute("y"));
//        }
        this.map.pan(
            this.handler.last.x - xy.x,
            this.handler.last.y - xy.y,
            {dragging: this.handler.dragging, animate: false}
        );
    },
    
    /**
     * Method: panMapDone
     * Finish the panning operation.  Only call setCenter (through <panMap>)
     *     if the map has actually been moved.
     *
     * Parameters:
     * xy - {<OpenLayers.Pixel>} Pixel of the mouse position
     */
    panMapDone: function(xy) {
        if(this.panned) {
            if (this.map.isHTML) this.panMap(xy);
            else if (this.map.isSVG) this.map.panToPixel(xy);
            this.panned = false;
        }
    },

    CLASS_NAME: "OpenLayers.Control.DragPan"
});
