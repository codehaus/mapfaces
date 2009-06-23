/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/**
 * @requires OpenLayers/Control.js
 * @requires OpenLayers/Handler/Box.js
 */

/**
 * Class: OpenLayers.Control.ZoomBox
 *
 * Inherits from:
 *  - <OpenLayers.Control>
 */
OpenLayers.Control.GetCoverageMatrix = OpenLayers.Class(OpenLayers.Control, {
    /**
     * Property: type
     * {OpenLayers.Control.TYPE}
     */
    type: OpenLayers.Control.TYPE_TOOL,

    /**
     * Property: out
     * {Boolean} Should the control be used for zooming out?
     */
    out: false,

    /**
     * Method: draw
     */    
    draw: function() {
        this.handler = new OpenLayers.Handler.Box( this,
                            {done: this.zoomBox}, {keyMask: this.keyMask} );
    },

    /**
     * Method: zoomBox
     *
     * Parameters:
     * position - {<OpenLayers.Bounds>} or {<OpenLayers.Pixel>}
     */
    zoomBox: function (position) {
        if (position instanceof OpenLayers.Bounds) {
            if (!this.out) {
                var minXY = this.map.getLonLatFromPixel(
                            new OpenLayers.Pixel(position.left, position.bottom));
                var maxXY = this.map.getLonLatFromPixel(
                            new OpenLayers.Pixel(position.right, position.top));
                var bounds = new OpenLayers.Bounds(minXY.lon, minXY.lat,
                                               maxXY.lon, maxXY.lat); 
                if (this.map) { 
                  //TODO org.mapfaces.ajax.ACTION_SAVE_DIR param is not used in the ontext save function
                  //http://demo.geomatys.fr/constellation/WS/wms?bbox=-130,24,-66,50&styles=&format=image/png&info_format=text/plain&version=1.1.1&srs=epsg:4326&request=GetFeatureInfo&layers=BlueMarble&query_layers=BlueMarble&width=550&height=250&x=170&y=160
                  var parameters = {  
                                        'refresh' : 'form:getCoverage',
                                        'synchronized' : 'true',
                                        'org.mapfaces.ajax.ACTION' : 'getCoverage',
                                        'org.mapfaces.ajax.ACTION_GETCOVERAGE_AOI' : minXY.lon+","+minXY.lat+","+maxXY.lon+","+maxXY.lat,
                                        'org.mapfaces.ajax.ACTION_GETCOVERAGE_PIXEL' :  (position.right-position.left)+","+(position.bottom-position.top),
                                        'org.mapfaces.ajax.ACTION_GETCOVERAGE_FORMAT' : "matrix"
                  }
                this.map.sendAjaxRequest(parameters);
                }
            }    
        } else { // it's a pixel
            if (!this.out) {
                this.map.setCenter(this.map.getLonLatFromPixel(position),
                               this.map.getZoom() + 1);
            } else {
                this.map.setCenter(this.map.getLonLatFromPixel(position),
                               this.map.getZoom() - 1);
            }
        }
    },

    CLASS_NAME: "OpenLayers.Control.GetCoverageMatrix"
});
