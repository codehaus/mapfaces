/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/**
 * Class: OpenCharts.LonLat
 * This class represents a longitude and latitude pair
 */
OpenCharts.LonLat = OpenCharts.Class({

    /** 
     * APIProperty: lon
     * {Float} The x-axis coodinate in map units
     */
    lon: 0.0,
    
    /** 
     * APIProperty: lat
     * {Float} The y-axis coordinate in map units
     */
    lat: 0.0,

    /**
     * Constructor: OpenCharts.LonLat
     * Create a new map location.
     *
     * Parameters:
     * lon - {Number} The x-axis coordinate in map units.  If your map is in
     *     a geographic projection, this will be the Longitude.  Otherwise,
     *     it will be the x coordinate of the map location in your map units.
     * lat - {Number} The y-axis coordinate in map units.  If your map is in
     *     a geographic projection, this will be the Latitude.  Otherwise,
     *     it will be the y coordinate of the map location in your map units.
     */
    initialize: function(lon, lat) {
        this.lon = parseFloat(lon);
        this.lat = parseFloat(lat);
    },
    
    /**
     * Method: toString
     * Return a readable string version of the lonlat
     *
     * Returns:
     * {String} String representation of OpenCharts.LonLat object. 
     *           (ex. <i>"lon=5,lat=42"</i>)
     */
    toString:function() {
        return ("lon=" + this.lon + ",lat=" + this.lat);
    },

    /** 
     * APIMethod: toShortString
     * 
     * Returns:
     * {String} Shortened String representation of OpenCharts.LonLat object. 
     *         (ex. <i>"5, 42"</i>)
     */
    toShortString:function() {
        return (this.lon + ", " + this.lat);
    },

    /** 
     * APIMethod: clone
     * 
     * Returns:
     * {<OpenCharts.LonLat>} New OpenCharts.LonLat object with the same lon 
     *                       and lat values
     */
    clone:function() {
        return new OpenCharts.LonLat(this.lon, this.lat);
    },

    /** 
     * APIMethod: add
     * 
     * Parameters:
     * lon - {Float}
     * lat - {Float}
     * 
     * Returns:
     * {<OpenCharts.LonLat>} A new OpenCharts.LonLat object with the lon and 
     *                       lat passed-in added to this's. 
     */
    add:function(lon, lat) {
        if ( (lon == null) || (lat == null) ) {
            var msg = OpenCharts.i18n("lonlatAddError");
            OpenCharts.Console.error(msg);
            return null;
        }
        return new OpenCharts.LonLat(this.lon + lon, this.lat + lat);
    },

    /** 
     * APIMethod: equals
     * 
     * Parameters:
     * ll - {<OpenCharts.LonLat>}
     * 
     * Returns:
     * {Boolean} Boolean value indicating whether the passed-in 
     *           <OpenCharts.LonLat> object has the same lon and lat 
     *           components as this.
     *           Note: if ll passed in is null, returns false
     */
    equals:function(ll) {
        var equals = false;
        if (ll != null) {
            equals = ((this.lon == ll.lon && this.lat == ll.lat) ||
                      (isNaN(this.lon) && isNaN(this.lat) && isNaN(ll.lon) && isNaN(ll.lat)));
        }
        return equals;
    },

    /**
     * APIMethod: transform
     * Transform the LonLat object from source to dest. This transformation is
     *    *in place*: if you want a *new* lonlat, use .clone() first.
     *
     * Parameters: 
     * source - {<OpenCharts.Projection>} Source projection. 
     * dest   - {<OpenCharts.Projection>} Destination projection. 
     *
     * Returns:
     * {<OpenCharts.LonLat>} Itself, for use in chaining operations.
     */
    transform: function(source, dest) {
        var point = OpenCharts.Projection.transform(
            {'x': this.lon, 'y': this.lat}, source, dest);
        this.lon = point.x;
        this.lat = point.y;
        return this;
    },
    
    /**
     * APIMethod: wrapDateLine
     * 
     * Parameters:
     * maxExtent - {<OpenCharts.Bounds>}
     * 
     * Returns:
     * {<OpenCharts.LonLat>} A copy of this lonlat, but wrapped around the 
     *                       "dateline" (as specified by the borders of 
     *                       maxExtent)
     */
    wrapDateLine: function(maxExtent) {    

        var newLonLat = this.clone();
    
        if (maxExtent) {
            //shift right?
            while (newLonLat.lon < maxExtent.left) {
                newLonLat.lon +=  maxExtent.getWidth();
            }    
           
            //shift left?
            while (newLonLat.lon > maxExtent.right) {
                newLonLat.lon -= maxExtent.getWidth();
            }    
        }
                
        return newLonLat;
    },

    CLASS_NAME: "OpenCharts.LonLat"
});

/** 
 * Function: fromString
 * Alternative constructor that builds a new <OpenCharts.LonLat> from a 
 *     parameter string
 * 
 * Parameters:
 * str - {String} Comma-separated Lon,Lat coordinate string. 
 *                 (ex. <i>"5,40"</i>)
 * 
 * Returns:
 * {<OpenCharts.LonLat>} New <OpenCharts.LonLat> object built from the 
 *                       passed-in String.
 */
OpenCharts.LonLat.fromString = function(str) {
    var pair = str.split(",");
    return new OpenCharts.LonLat(parseFloat(pair[0]), 
                                 parseFloat(pair[1]));
};
