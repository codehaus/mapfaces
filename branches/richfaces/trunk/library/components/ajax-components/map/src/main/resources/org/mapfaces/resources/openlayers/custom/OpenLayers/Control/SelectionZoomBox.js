/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/**
 * @requires OpenLayers/Control.js
 * @requires OpenLayers/Handler/Box.js
 */

/**
 * Class: OpenLayers.Control.SelectionZoomBox
 *
 * Inherits from:
 *  - <OpenLayers.Control>
 */
OpenLayers.Control.SelectionZoomBox = OpenLayers.Class(OpenLayers.Control, {
    /**
     * Property: type
     * {OpenLayers.Control.TYPE}
     */
    type: OpenLayers.Control.TYPE_TOOL,
    searchFormBox : null,
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
            var minXY = this.map.getLonLatFromPixel(
                            new OpenLayers.Pixel(position.left, position.bottom));
            var maxXY = this.map.getLonLatFromPixel(
                            new OpenLayers.Pixel(position.right, position.top));
            var bounds = new OpenLayers.Bounds(minXY.lon, minXY.lat,
                                               maxXY.lon, maxXY.lat);
            //this.map.zoomToExtent(bounds);
            if (document.getElementById(this.north)) {
             document.getElementById(this.north).value= maxXY.lat;   
            }
            if (document.getElementById(this.south)) {
             document.getElementById(this.south).value=minXY.lat;   
            }
            if (document.getElementById(this.east)) {
              document.getElementById(this.east).value=maxXY.lon;
            }
            if (document.getElementById(this.west)) {
              document.getElementById(this.west).value=minXY.lon;
            }
            
            if(this.searchFormBox){
                 this.map.removeLayer(this.searchFormBox);
                 this.searchFormBox=null;
            }
                
            this.searchFormBox  = new OpenLayers.Layer.Boxes("Selection bounding box"); 
            var tmpBox=new OpenLayers.Marker.Box(bounds);
             tmpBox.setBorder(this.color);                                        
             this.searchFormBox.addMarker(tmpBox);
             this.map.addLayer(this.searchFormBox);
            //set focus on target element if specified focusId
             if(document.getElementById(this.focusId)) {
              document.getElementById(this.focusId).focus();
             }
         
        } /*else { // it's a pixel
            this.map.setCenter(this.map.getLonLatFromPixel(position),
                               this.map.getZoom() + 1);
        }*/
    },

    /**
     * Method: updateZoomBox
     *
     * Parameters:
     * position - {<OpenLayers.Bounds>}
     */
     updateZoomBox: function (position) {
        if (position instanceof OpenLayers.Bounds) {

            if(this.searchFormBox){
                 this.map.removeLayer(this.searchFormBox);
                 this.searchFormBox=null;
            }

            this.searchFormBox  = new OpenLayers.Layer.Boxes("Selection bounding box");
            var tmpBox=new OpenLayers.Marker.Box(position);
             tmpBox.setBorder(this.color);
             this.searchFormBox.addMarker(tmpBox);
             this.map.addLayer(this.searchFormBox);
             if(document.getElementById(this.focusId)) {
              document.getElementById(this.focusId).focus();
             }

        }
    },

    CLASS_NAME: "OpenLayers.Control.SelectionZoomBox"
});
