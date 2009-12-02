/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/**
 * @requires OpenLayers/Control/Panel.js
 * @requires OpenLayers/Control/Navigation.js
 * @requires OpenLayers/Control/DrawFeature.js
 * @requires OpenLayers/Handler/Point.js
 * @requires OpenLayers/Handler/Path.js
 * @requires OpenLayers/Handler/Polygon.js
 */

/**
 * Class: OpenLayers.Control.EditingToolbar 
 *
 * Inherits from:
 *  - <OpenLayers.Control.Panel>
 */
OpenLayers.Control.EditingToolbar = OpenLayers.Class(
  OpenLayers.Control.Panel, {

    /**
     * Constructor: OpenLayers.Control.EditingToolbar
     * Create an editing toolbar for a given layer. 
     *
     * Parameters:
     * layer - {<OpenLayers.Layer.Vector>} 
     * options - {Object} 
     */
    initialize: function(layer, options) {
        OpenLayers.Control.Panel.prototype.initialize.apply(this, [options]);
//        this.addControls(
//          [ new OpenLayers.Control.Navigation() ]
//        ); 
        var controls = [];

        if (options.drawPoint)
            controls.push(new OpenLayers.Control.DrawFeature(layer, OpenLayers.Handler.Point, {'displayClass': 'EditPoint'}));

        if (options.drawLine)
            controls.push(new OpenLayers.Control.DrawFeature(layer, OpenLayers.Handler.Path, {'displayClass': 'EditLine'}));

        if (options.drawPolygon)
            controls.push(new OpenLayers.Control.DrawFeature(layer, OpenLayers.Handler.Polygon, {'displayClass': 'EditPolygon'}));

        if (options.drawRegularPolygon) {
            if (!options.regularPolygonSides)
                options.regularPolygonSides = 4;
            controls.push(new OpenLayers.Control.DrawFeature(layer, OpenLayers.Handler.RegularPolygon, {'displayClass': 'EditRegularPolygon',handlerOptions: {sides: options.regularPolygonSides}}));
        }

        if (options.deleteFeature) {
            controls.push(new OpenLayers.Control.DeleteFeature(layer,{'displayClass': 'Delete'}));
        }

        if (options.modify)
            controls.push(new OpenLayers.Control.ModifyFeature(layer,{'displayClass': 'Modify'}));

        if (options.select)
            controls.push(new OpenLayers.Control.SelectFeature(layer, {'displayClass': 'Select',
                        clickout: false, toggle: false,
                        multiple: false, hover: false,
                        toggleKey: "ctrlKey", // ctrl key removes from selection
                        multipleKey: "shiftKey", // shift key adds to selection
                        box: true
                    }));

        if (options.drag)
            controls.push(new OpenLayers.Control.DragFeature(layer,{'displayClass': 'Drag'}));
//TODO Create an OpenLayers.Control
//        if (options.resize)
//            controls.push(new OpenLayers.Control.ResizeFeature(layer));
//        if (options.rotate)
//            controls.push(new OpenLayers.Control.RotateFeature(layer));
        for (var i=0, len=controls.length; i<len; i++) {
            controls[i].featureAdded = function(feature) { feature.state = OpenLayers.State.INSERT; };
        }
        this.addControls(controls);
    },

    /**
     * Method: draw
     * calls the default draw, and then activates mouse defaults.
     *
     * Returns:
     * {DOMElement}
     */
    draw: function() {
        var div = OpenLayers.Control.Panel.prototype.draw.apply(this, arguments);
        this.activateControl(this.controls[0]);
        return div;
    },

    CLASS_NAME: "OpenLayers.Control.EditingToolbar"
});    
