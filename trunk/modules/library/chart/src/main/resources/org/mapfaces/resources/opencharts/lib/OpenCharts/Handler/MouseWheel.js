/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/**
 * @requires OpenCharts/Handler.js
 */

/**
 * Class: OpenCharts.Handler.MouseWheel
 * Handler for wheel up/down events.
 * 
 * Inherits from:
 *  - <OpenCharts.Handler>
 */
OpenCharts.Handler.MouseWheel = OpenCharts.Class(OpenCharts.Handler, {
    /** 
     * Property: wheelListener 
     * {function} 
     */
    wheelListener: null,

    /** 
     * Property: mousePosition
     * {<OpenCharts.Pixel>} mousePosition is necessary because
     * evt.clientX/Y is buggy in Moz on wheel events, so we cache and use the
     * value from the last mousemove.
     */
    mousePosition: null,

    /**
     * Constructor: OpenCharts.Handler.MouseWheel
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
        this.wheelListener = OpenCharts.Function.bindAsEventListener(
            this.onWheelEvent, this
        );
    },

    /**
     * Method: destroy
     */    
    destroy: function() {
        OpenCharts.Handler.prototype.destroy.apply(this, arguments);
        this.wheelListener = null;
    },

    /**
     *  Mouse ScrollWheel code thanks to http://adomas.org/javascript-mouse-wheel/
     */

    /** 
     * Method: onWheelEvent
     * Catch the wheel event and handle it xbrowserly
     * 
     * Parameters:
     * e - {Event} 
     */
    onWheelEvent: function(e){
        
        // make sure we have a map and check keyboard modifiers
        if (!this.map || !this.checkModifiers(e)) {
            return;
        }
        
        // Ride up the element's DOM hierarchy to determine if it or any of 
        //  its ancestors was: 
        //   * specifically marked as scrollable
        //   * one of our layer divs
        //   * the map div
        //
        var overScrollableDiv = false;
        var overLayerDiv = false;
        var overMapDiv = false;
        
        var elem = OpenCharts.Event.element(e);
        while((elem != null) && !overMapDiv && !overScrollableDiv) {

            if (!overScrollableDiv) {
                try {
                    if (elem.currentStyle) {
                        overflow = elem.currentStyle["overflow"];
                    } else {
                        var style = 
                            document.defaultView.getComputedStyle(elem, null);
                        var overflow = style.getPropertyValue("overflow");
                    }
                    overScrollableDiv = ( overflow && 
                        (overflow == "auto") || (overflow == "scroll") );
                } catch(err) {
                    //sometimes when scrolling in a popup, this causes 
                    // obscure browser error
                }
            }

            if (!overLayerDiv) {
                for(var i=0, len=this.map.layers.length; i<len; i++) {
                    // Are we in the layer div? Note that we have two cases
                    // here: one is to catch EventPane layers, which have a 
                    // pane above the layer (layer.pane)
                    if (elem == this.map.layers[i].div 
                        || elem == this.map.layers[i].pane) { 
                        overLayerDiv = true;
                        break;
                    }
                }
            }
            overMapDiv = (elem == this.map.div);

            elem = elem.parentNode;
        }
        
        // Logic below is the following:
        //
        // If we are over a scrollable div or not over the map div:
        //  * do nothing (let the browser handle scrolling)
        //
        //    otherwise 
        // 
        //    If we are over the layer div: 
        //     * zoom/in out
        //     then
        //     * kill event (so as not to also scroll the page after zooming)
        //
        //       otherwise
        //
        //       Kill the event (dont scroll the page if we wheel over the 
        //        layerswitcher or the pan/zoom control)
        //
        if (!overScrollableDiv && overMapDiv) {
            if (overLayerDiv) {
                this.wheelZoom(e);
            }
            OpenCharts.Event.stop(e);
        }
    },

    /**
     * Method: wheelZoom
     * Given the wheel event, we carry out the appropriate zooming in or out,
     *     based on the 'wheelDelta' or 'detail' property of the event.
     * 
     * Parameters:
     * e - {Event}
     */
    wheelZoom: function(e) {
        
        var delta = 0;
        if (!e) {
            e = window.event;
        }
        if (e.wheelDelta) {
            delta = e.wheelDelta/120; 
            if (window.opera && window.opera.version() < 9.2) {
                delta = -delta;
            }
        } else if (e.detail) {
            delta = -e.detail / 3;
        }
        if (delta) {
            // add the mouse position to the event because mozilla has 
            // a bug with clientX and clientY (see 
            // https://bugzilla.mozilla.org/show_bug.cgi?id=352179)
            // getLonLatFromViewPortPx(e) returns wrong values
            if (this.mousePosition) {
                e.xy = this.mousePosition;
            } 
            if (!e.xy) {
                // If the mouse hasn't moved over the map yet, then
                // we don't have a mouse position (in FF), so we just
                // act as if the mouse was at the center of the map.
                // Note that we can tell we are in the map -- and 
                // this.map is ensured to be true above.
                e.xy = this.map.getPixelFromLonLat(
                    this.map.getCenter()
                );
            }
            if (delta < 0) {
               this.callback("down", [e, delta]);
            } else {
               this.callback("up", [e, delta]);
            }
        }
    },
    
    /**
     * Method: mousemove
     * Update the stored mousePosition on every move.
     * 
     * Parameters:
     * evt - {Event} The browser event
     *
     * Returns: 
     * {Boolean} Allow event propagation
     */
    mousemove: function (evt) {
        this.mousePosition = evt.xy;
    },

    /**
     * Method: activate 
     */
    activate: function (evt) {
        if (OpenCharts.Handler.prototype.activate.apply(this, arguments)) {
            //register mousewheel events specifically on the window and document
            var wheelListener = this.wheelListener;
            OpenCharts.Event.observe(window, "DOMMouseScroll", wheelListener);
            OpenCharts.Event.observe(window, "mousewheel", wheelListener);
            OpenCharts.Event.observe(document, "mousewheel", wheelListener);
            return true;
        } else {
            return false;
        }
    },

    /**
     * Method: deactivate 
     */
    deactivate: function (evt) {
        if (OpenCharts.Handler.prototype.deactivate.apply(this, arguments)) {
            // unregister mousewheel events specifically on the window and document
            var wheelListener = this.wheelListener;
            OpenCharts.Event.stopObserving(window, "DOMMouseScroll", wheelListener);
            OpenCharts.Event.stopObserving(window, "mousewheel", wheelListener);
            OpenCharts.Event.stopObserving(document, "mousewheel", wheelListener);
            return true;
        } else {
            return false;
        }
    },

    CLASS_NAME: "OpenCharts.Handler.MouseWheel"
});
