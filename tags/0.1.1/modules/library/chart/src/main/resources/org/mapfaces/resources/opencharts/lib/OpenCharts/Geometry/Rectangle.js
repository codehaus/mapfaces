/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/**
 * @requires OpenCharts/Geometry.js
 */

/**
 * Class: OpenCharts.Geometry.Rectangle
 * This class is *not supported*, and probably isn't what you're looking for.
 *     Instead, most users probably want something like:
 *     (code)
 *     var poly = new OpenCharts.Bounds(0,0,10,10).toGeometry();
 *     (end)
 *     This will create a rectangular Polygon geometry. 
 * 
 * Inherits:
 *  - <OpenCharts.Geometry>
 */

OpenCharts.Geometry.Rectangle = OpenCharts.Class(OpenCharts.Geometry, {

    /** 
     * Property: x
     * {Float}
     */
    x: null,

    /** 
     * Property: y
     * {Float}
     */
    y: null,

    /** 
     * Property: width
     * {Float}
     */
    width: null,

    /** 
     * Property: height
     * {Float}
     */
    height: null,

    /**
     * Constructor: OpenCharts.Geometry.Rectangle
     * 
     * Parameters:
     * points - {Array(<OpenCharts.Geometry.Point>}
     */
    initialize: function(x, y, width, height) {
        OpenCharts.Geometry.prototype.initialize.apply(this, arguments);
        
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
    },
    
    /**
     * Method: calculateBounds
     * Recalculate the bounds for the geometry.
     */
    calculateBounds: function() {
        this.bounds = new OpenCharts.Bounds(this.x, this.y,
                                            this.x + this.width, 
                                            this.y + this.height);
    },
    
    
    /**
     * APIMethod: getLength
     * 
     * Returns:
     * {Float} The length of the geometry
     */
    getLength: function() {
        var length = (2 * this.width) + (2 * this.height);
        return length;
    },

    /**
     * APIMethod: getArea
     * 
     * Returns:
     * {Float} The area of the geometry
     */
    getArea: function() {
        var area = this.width * this.height;
        return area;
    },    

    CLASS_NAME: "OpenCharts.Geometry.Rectangle"
});
