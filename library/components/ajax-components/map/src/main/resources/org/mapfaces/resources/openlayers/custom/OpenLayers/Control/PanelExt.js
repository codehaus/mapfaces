/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/**
 * @requires OpenLayers/Control.js
 */

/**
 * Class: OpenLayers.Control.Panel
 *
 * Inherits from:
 *  - <OpenLayers.Control>
 */
OpenLayers.Control.Panel = OpenLayers.Class(OpenLayers.Control.Panel, {
    /**
     * Constructor: OpenLayers.Control.Panel
     * Create a new control panel.
     * 
     * Parameters:
     * options - {Object} An optional object whose properties will be used
     *     to extend the control.
     */
    initialize: function(options) {
        OpenLayers.Control.prototype.initialize.apply(this, [options]);
        this.controls = [];
        if (!window.panels) 
            window.panels = [];        
         window.panels.push(this);
    },

    /**
     * APIMethod: activateControl
     * 
     * Parameters:
     * control - {<OpenLayers.Control>}
     */
    activateControl: function (control) {
         if (window.panels) {
            for(var i = 0 ; i < window.panels.length; i++) {
                var panel = window.panels[i];
                if (panel.id != this.id) {
                    var controls = panel.controls;
                    for (var j=0, len=controls.length; j<len; j++) {
                        if (controls[j] != control) {
                            if (controls[j].type != OpenLayers.Control.TYPE_TOGGLE) {
                                controls[j].deactivate();
                            }
                        }
                    }
                }
            }
        }
        if (!this.active) { return false; }
        if (control.type == OpenLayers.Control.TYPE_BUTTON) {
            control.trigger();
            this.redraw();
            return;
        }
        if (control.type == OpenLayers.Control.TYPE_TOGGLE) {
            if (control.active) {
                control.deactivate();
            } else {
                control.activate();
            }
            this.redraw();
            return;
        }
        for (var i=0, len=this.controls.length; i<len; i++) {
            if (this.controls[i] != control) {
                if (this.controls[i].type != OpenLayers.Control.TYPE_TOGGLE) {
                    this.controls[i].deactivate();
                }
            }
        }
        control.activate();
    }

});

