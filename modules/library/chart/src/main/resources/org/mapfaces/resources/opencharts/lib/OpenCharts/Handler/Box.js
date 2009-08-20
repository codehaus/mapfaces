/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/**
 * @requires OpenCharts/Handler.js
 * @requires OpenCharts/Handler/Drag.js
 */

/**
 * Class: OpenCharts.Handler.Box
 * Handler for dragging a rectangle across the map.  Box is displayed 
 * on mouse down, moves on mouse move, and is finished on mouse up.
 *
 * Inherits from:
 *  - <OpenCharts.Handler> 
 */
OpenCharts.Handler.Box = OpenCharts.Class(OpenCharts.Handler, {

    /** 
     * Property: dragHandler 
     * {<OpenCharts.Handler.Drag>} 
     */
    dragHandler: null,

    /**
     * APIProperty: boxDivClassName
     * {String} The CSS class to use for drawing the box. Default is
     *     olHandlerBoxZoomBox
     */
    boxDivClassName: 'olHandlerBoxZoomBox',
    
    /**
     * Property: boxCharacteristics
     * {Object} Caches some box characteristics from css. This is used
     *     by the getBoxCharacteristics method.
     */
    boxCharacteristics: null,

    /**
     * Constructor: OpenCharts.Handler.Box
     *
     * Parameters:
     * control - {<OpenCharts.Control>} 
     * callbacks - {Object} An object containing a single function to be
     *                          called when the drag operation is finished.
     *                          The callback should expect to recieve a single
     *                          argument, the point geometry.
     * options - {Object} 
     */
    initialize: function(control, callbacks, options) {
        OpenCharts.Handler.prototype.initialize.apply(this, arguments);
        var callbacks = {
            "down": this.startBox, 
            "move": this.moveBox, 
            "out":  this.removeBox,
            "up":   this.endBox
        };
        this.dragHandler = new OpenCharts.Handler.Drag(
        this, callbacks, {keyMask: this.keyMask});
    },

    /**
     * Method: setMap
     */
    setMap: function (map) {
        OpenCharts.Handler.prototype.setMap.apply(this, arguments);
        if (this.dragHandler) {
            this.dragHandler.setMap(map);
        }
    },

    /**
     * Method: startBox
     *
     * Parameters:
     * evt - {Event} 
     */
    startBox: function (xy) {
        
        // 1 : Create the geometry
        this.geometry = new OpenCharts.Geometry.Rectangle(this.dragHandler.start.x,
                              this.dragHandler.start.y,1,1);
                              
        // 2 : Draw the geometry to the node corresponding  
        this.zoomBox = this.map.renderer.drawRectangle(this.map.renderer.createBox('zoomBox', this.geometry),this.geometry);
       
        // 3 : Attach it to the map
        this.map.div.appendChild(this.zoomBox);
        
    },

    /**
     * Method: moveBox
     */
    moveBox: function (xy) {
        
        var startX = this.dragHandler.start.x;
        var startY = this.dragHandler.start.y;
        var deltaX = Math.abs(startX - xy.x);
        var deltaY = Math.abs(startY - xy.y);
        
        this.geometry.width = Math.max(1, deltaX);
        this.geometry.height = Math.max(1, deltaY);
        this.geometry.x = xy.x < startX ? xy.x : startX;
        this.geometry.y = xy.y < startY ? xy.y : startY;
        
        this.zoomBox = this.map.renderer.drawRectangle(this.zoomBox,this.geometry);
    },

    /**
     * Method: endBox
     */
    endBox: function(end) {
        var result;
        
        if (Math.abs(this.dragHandler.start.x - end.x) > 5 ||    
            Math.abs(this.dragHandler.start.y - end.y) > 5) {   
            var start = this.dragHandler.start;
            var top = Math.min(start.y, end.y);
            var bottom = Math.max(start.y, end.y);
            var left = Math.min(start.x, end.x);
            var right = Math.max(start.x, end.x);
            result = new OpenCharts.Bounds(left, bottom, right, top);
        } else {
            result = this.dragHandler.start.clone(); // i.e. OL.Pixel
        } 
        this.removeBox();
        this.callback("done", [result]);
    },

    /**
     * Method: removeBox
     * Remove the zoombox from the screen and nullify our reference to it.
     */
    removeBox: function() {
//        this.map.viewPortDiv.removeChild(this.zoomBox);
//        this.zoomBox = null;
//        this.boxCharacteristics = null;
    },

    /**
     * Method: activate
     */
    activate: function () {
        if (OpenCharts.Handler.prototype.activate.apply(this, arguments)) {
            this.dragHandler.activate();
            return true;
        } else {
            return false;
        }
    },

    /**
     * Method: deactivate
     */
    deactivate: function () {
        if (OpenCharts.Handler.prototype.deactivate.apply(this, arguments)) {
            this.dragHandler.deactivate();
            return true;
        } else {
            return false;
        }
    },
    
    getBoxCharacteristics: function(dx, dy) {
        if (!this.boxCharacteristics) {
            var xOffset = parseInt(OpenCharts.Element.getStyle(this.zoomBox,
            "border-left-width")) + parseInt(OpenCharts.Element.getStyle(
            this.zoomBox, "border-right-width")) + 1;
            var yOffset = parseInt(OpenCharts.Element.getStyle(this.zoomBox,
            "border-top-width")) + parseInt(OpenCharts.Element.getStyle(
            this.zoomBox, "border-bottom-width")) + 1;
            // all browsers use the new box model, except IE in quirks mode
            var newBoxModel = OpenCharts.Util.getBrowserName() == "msie" ?
                document.compatMode != "BackCompat" : true;
            this.boxCharacteristics = {
                xOffset: xOffset,
                yOffset: yOffset,
                newBoxModel: newBoxModel
            };
        }
        return this.boxCharacteristics;
    },

    CLASS_NAME: "OpenCharts.Handler.Box"
});
