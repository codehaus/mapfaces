/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/**
 * @requires OpenCharts/Control.js
 * @requires OpenCharts/Handler/Drag.js
 */

/**
 * Class: OpenCharts.Control.DragPan
 * DragPan control.
 *
 * Inherits from:
 *  - <OpenCharts.Control>
 */
OpenCharts.Control.DragPan = OpenCharts.Class(OpenCharts.Control, {

    /** 
     * Property: type
     * {OpenCharts.Control.TYPES}
     */
    type: OpenCharts.Control.TYPE_TOOL,
    
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
        this.handler = new OpenCharts.Handler.Drag(this, {
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
    * xy - {<OpenCharts.Pixel>} Pixel of the mouse position
    */
    panMap: function(xy) {
        this.panned = true;
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
     * xy - {<OpenCharts.Pixel>} Pixel of the mouse position
     */
    panMapDone: function(xy) {
        if(this.panned) {
              this.map.renderer.panMapDone(xy);
//            if (this.map.isHTML) this.panMap(xy);
//            else if (this.map.isSVG) this.map.panToPixel(xy);
            this.panned = false;
        }
    },

    CLASS_NAME: "OpenCharts.Control.DragPan"
});
