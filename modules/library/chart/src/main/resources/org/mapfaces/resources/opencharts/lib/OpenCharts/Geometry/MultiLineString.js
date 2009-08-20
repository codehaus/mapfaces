/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/**
 * @requires OpenCharts/Geometry/Collection.js
 */

/**
 * Class: OpenCharts.Geometry.MultiLineString
 * A MultiLineString is a geometry with multiple <OpenCharts.Geometry.LineString>
 * components.
 * 
 * Inherits from:
 *  - <OpenCharts.Geometry.Collection>
 *  - <OpenCharts.Geometry> 
 */
OpenCharts.Geometry.MultiLineString = OpenCharts.Class(
  OpenCharts.Geometry.Collection, {

    /**
     * Property: componentTypes
     * {Array(String)} An array of class names representing the types of
     * components that the collection can include.  A null value means the
     * component types are not restricted.
     */
    componentTypes: ["OpenCharts.Geometry.LineString"],

    /**
     * Constructor: OpenCharts.Geometry.MultiLineString
     * Constructor for a MultiLineString Geometry.
     *
     * Parameters: 
     * components - {Array(<OpenCharts.Geometry.LineString>)} 
     *
     */
    initialize: function(components) {
        OpenCharts.Geometry.Collection.prototype.initialize.apply(this, 
                                                                  arguments);        
    },

    CLASS_NAME: "OpenCharts.Geometry.MultiLineString"
});
