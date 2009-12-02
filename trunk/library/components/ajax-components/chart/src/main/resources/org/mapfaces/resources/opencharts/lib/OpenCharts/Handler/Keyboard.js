/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/**
 * @requires OpenCharts/Handler.js
 * @requires OpenCharts/Events.js
 */

/**
 * Class: OpenCharts.handler.Keyboard
 * A handler for keyboard events.  Create a new instance with the
 *     <OpenCharts.Handler.Keyboard> constructor.
 * 
 * Inherits from:
 *  - <OpenCharts.Handler> 
 */
OpenCharts.Handler.Keyboard = OpenCharts.Class(OpenCharts.Handler, {

    /* http://www.quirksmode.org/js/keys.html explains key x-browser
        key handling quirks in pretty nice detail */

    /** 
     * Constant: KEY_EVENTS
     * keydown, keypress, keyup
     */
    KEY_EVENTS: ["keydown", "keyup"],

    /** 
    * Property: eventListener
    * {Function}
    */
    eventListener: null,

    /**
     * Constructor: OpenCharts.Handler.Keyboard
     * Returns a new keyboard handler.
     * 
     * Parameters:
     * control - {<OpenCharts.Control>} The control that is making use of
     *     this handler.  If a handler is being used without a control, the
     *     handlers setMap method must be overridden to deal properly with
     *     the map.
     * callbacks - {Object} An object containing a single function to be
     *     called when the drag operation is finished. The callback should
     *     expect to recieve a single argument, the pixel location of the event.
     *     Callbacks for 'keydown', 'keypress', and 'keyup' are supported.
     * options - {Object} Optional object whose properties will be set on the
     *     handler.
     */
    initialize: function(control, callbacks, options) {
        OpenCharts.Handler.prototype.initialize.apply(this, arguments);
        // cache the bound event listener method so it can be unobserved later
        this.eventListener = OpenCharts.Function.bindAsEventListener(
            this.handleKeyEvent, this
        );
    },
    
    /**
     * Method: destroy
     */
    destroy: function() {
        this.deactivate();
        this.eventListener = null;
        OpenCharts.Handler.prototype.destroy.apply(this, arguments);
    },

    /**
     * Method: activate
     */
    activate: function() {
        if (OpenCharts.Handler.prototype.activate.apply(this, arguments)) {
            for (var i=0, len=this.KEY_EVENTS.length; i<len; i++) {
                OpenCharts.Event.observe(
                    document, this.KEY_EVENTS[i], this.eventListener);
            }
            return true;
        } else {
            return false;
        }
    },

    /**
     * Method: deactivate
     */
    deactivate: function() {
        var deactivated = false;
        if (OpenCharts.Handler.prototype.deactivate.apply(this, arguments)) {
            for (var i=0, len=this.KEY_EVENTS.length; i<len; i++) {
                OpenCharts.Event.stopObserving(
                    document, this.KEY_EVENTS[i], this.eventListener);
            }
            deactivated = true;
        }
        return deactivated;
    },

    /**
     * Method: handleKeyEvent 
     */
    handleKeyEvent: function (evt) {
        if (this.checkModifiers(evt)) {
            this.callback(evt.type, [evt]);
        }
    },

    CLASS_NAME: "OpenCharts.Handler.Keyboard"
});
