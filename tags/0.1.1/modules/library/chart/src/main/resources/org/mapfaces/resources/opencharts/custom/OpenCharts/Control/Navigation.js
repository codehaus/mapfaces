/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/**
 * @requires OpenCharts/Control/ZoomBox.js
 * @requires OpenCharts/Control/DragPan.js
 * @requires OpenCharts/Handler/MouseWheel.js
 * @requires OpenCharts/Handler/Click.js
 */

/**
 * Class: OpenCharts.Control.Navigation
 * The navigation control handles map browsing with mouse events (dragging,
 *     double-clicking, and scrolling the wheel).  Create a new navigation 
 *     control with the <OpenCharts.Control.Navigation> control.  
 * 
 *     Note that this control is added to the map by default (if no controls 
 *     array is sent in the options object to the <OpenCharts.Map> 
 *     constructor).
 * 
 * Inherits:
 *  - <OpenCharts.Control>
 */
OpenCharts.Control.Navigation = OpenCharts.Class(OpenCharts.Control, {

    /** 
     * Property: dragPan
     * {<OpenCharts.Control.DragPan>} 
     */
    dragPan: null,

    /**
     * APIProprety: dragPanOptions
     * {Object} Options passed to the DragPan control.
     */
    dragPanOptions: null,

    /** 
     * Property: zoomBox
     * {<OpenCharts.Control.ZoomBox>}
     */
    zoomBox: null,

    /**
     * APIProperty: zoomWheelEnabled
     * {Boolean} Whether the mousewheel should zoom the map
     */
    zoomWheelEnabled: true, 

    /**
     * APIProperty: handleRightClicks
     * {Boolean} Whether or not to handle right clicks. Default is false.
     */
    handleRightClicks: true,
    
    /**
     * Constructor: OpenCharts.Control.Navigation
     * Create a new navigation control
     * 
     * Parameters:
     * options - {Object} An optional object whose properties will be set on
     *                    the control
     */
    initialize: function(options) {
        this.handlers = {};
        OpenCharts.Control.prototype.initialize.apply(this, arguments);
    },

    /**
     * Method: destroy
     * The destroy method is used to perform any clean up before the control
     * is dereferenced.  Typically this is where event listeners are removed
     * to prevent memory leaks.
     */
    destroy: function() {
        this.deactivate();

        if (this.dragPan) {
            this.dragPan.destroy();
        }
        this.dragPan = null;

        if (this.zoomBox) {
            this.zoomBox.destroy();
        }
        this.zoomBox = null;
        OpenCharts.Control.prototype.destroy.apply(this,arguments);
    },
    
    /**
     * Method: activate
     */
    activate: function() {
        this.dragPan.activate();
        if (this.zoomWheelEnabled) {
            this.handlers.wheel.activate();
        }    
        this.handlers.click.activate();
        this.zoomBox.activate();
        return OpenCharts.Control.prototype.activate.apply(this,arguments);
    },

    /**
     * Method: deactivate
     */
    deactivate: function() {
        this.zoomBox.deactivate();
        this.dragPan.deactivate();
        this.handlers.click.deactivate();
        this.handlers.wheel.deactivate();
        return OpenCharts.Control.prototype.deactivate.apply(this,arguments);
    },
    
    /**
     * Method: draw
     */
    draw: function() {
        // disable right mouse context menu for support of right click events
        if (this.handleRightClicks) {
            this.map.div.oncontextmenu = function () { return false;};
        }

        var clickCallbacks = { 
            'dblclick': this.defaultDblClick, 
            'dblrightclick': this.defaultDblRightClick 
        };
        var clickOptions = {
            'double': true, 
            'stopDouble': true
        };
        this.handlers.click = new OpenCharts.Handler.Click(
            this, clickCallbacks, clickOptions
        );
        this.dragPan = new OpenCharts.Control.DragPan(
            OpenCharts.Util.extend({map: this.map}, this.dragPanOptions)
        );
        this.zoomBox = new OpenCharts.Control.ZoomBox(
                    {map: this.map, keyMask: OpenCharts.Handler.MOD_SHIFT});
        this.dragPan.draw();
        this.zoomBox.draw();
        this.handlers.wheel = new OpenCharts.Handler.MouseWheel(
                                    this, {"up"  : this.wheelUp,
                                           "down": this.wheelDown} );
        this.activate();
    },

    /**
     * Method: defaultDblClick 
     * 
     * Parameters:
     * evt - {Event} 
     */
    defaultDblClick: function (evt) {
        var newCenter = this.map.getLonLatFromViewPortPx( evt.xy ); 
        this.map.setCenter(newCenter, this.map.zoom + 1);
    },

    /**
     * Method: defaultRightDblClick 
     * 
     * Parameters:
     * evt - {Event} 
     */
    defaultDblRightClick: function (evt) {
        var newCenter = this.map.getLonLatFromViewPortPx( evt.xy ); 
        this.map.setCenter(newCenter, this.map.zoom - 1);
    },
    
    /**
     * Method: wheelChange  
     *
     * Parameters:
     * evt - {Event}
     * deltaZ - {Integer}
     */
    wheelChange: function (evt, deltaZ){
        this.map.renderer.wheelChange(evt, deltaZ);
//        if (this.map.isHTML) {
//          var newZoom = this.map.getZoom() + deltaZ;
//          if (!this.map.isValidZoomLevel(newZoom)) {
//              return;
//          }
//          var size    = this.map.getSize();
//          var deltaX  = size.w/2 - evt.xy.x;
//          var deltaY  = evt.xy.y - size.h/2;
//          var newRes  = this.map.baseLayer.getResolutionForZoom(newZoom);
//          var zoomPoint = this.map.getLonLatFromPixel(evt.xy);
//          var newCenter = new OpenCharts.LonLat(
//                              zoomPoint.lon + deltaX * newRes,
//                              zoomPoint.lat + deltaY * newRes );
//          this.map.setCenter( newCenter, newZoom );
//        } else if (this.map.isSVG || this.map.isVML) {
//          this.map.zoomToPixel(evt.xy, (deltaZ == -1));
//        }
    },

    /** 
     * Method: wheelUp
     * User spun scroll wheel up
     * 
     * Parameters:
     * evt - {Event}
     */
    wheelUp: function(evt) {
        this.wheelChange(evt, 1);
    },

    /** 
     * Method: wheelDown
     * User spun scroll wheel down
     * 
     * Parameters:
     * evt - {Event}
     */
    wheelDown: function(evt) {
        this.wheelChange(evt, -1);
    },
    
    /**
     * Method: disableZoomWheel
     */
    
    disableZoomWheel : function() {
        this.zoomWheelEnabled = false;
        this.handlers.wheel.deactivate();       
    },
    
    /**
     * Method: enableZoomWheel
     */
    
    enableZoomWheel : function() {
        this.zoomWheelEnabled = true;
        if (this.active) {
            this.handlers.wheel.activate();
        }    
    },

    CLASS_NAME: "OpenCharts.Control.Navigation"
});
