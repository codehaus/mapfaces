/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */


/**
 * @requires OpenLayers/Control.js
 */

/**
 * Class: OpenLayers.Control.MousePosition
 */
OpenLayers.Control.MousePosition = OpenLayers.Class(OpenLayers.Control, {
    
    /** 
     * Property: element
     * {DOMElement} 
     */
    element: null,
    
    /** 
     * APIProperty: prefix
     * {String}
     */
    prefix: '<div><span>',
    
    
    /** 
     * APIProperty: prefixX
     * {String}
     */
    prefixX: ' X : ',
    
    /** 
     * APIProperty: prefixY
     * {String}
     */
    prefixY: ' Y : ',
    
    /** 
     * APIProperty: prefixPixelX
     * {String}
     */
    prefixPixelX: ' pixel X : ',
    
    /** 
     * APIProperty: prefixPixelY
     * {String}
     */
    prefixPixelY: ' pixel Y : ',
    /** 
     * APIProperty: prefixLat
     * {String}
     */
    prefixLat: ' Lat:',
    
    /** 
     * APIProperty: prefixLon
     * {String}
     */
    prefixLon: ' Lon:',
    
    /** 
     * APIProperty: separator
     * {String}
     */
    separator: '</span><span>',
    
    /** 
     * APIProperty: separatorDM
     * {String}
     */
    separatorDM: '&#176 ' ,
    
    /** 
     * APIProperty: separatorMS
     * {String}
     */
    separatorMS: '\' ',
    
    /** 
     * APIProperty: separatorMSHemi
     * {String} separator betweenn the seconds and the name of hemisphere
     */
    separatorSHemi: '\" ',
    
    /** 
     * APIProperty: suffix
     * {String}
     */
    suffix: '</span></div>',
    
    /** 
     * APIProperty: suffixDMS
     * {String}
     */
    suffixDMS: '\" ',
    
    /** 
     * APIProperty: numDigits
     * {Integer}
     */
    numDigits: 3,
    
    /** 
     * APIProperty: granularity
     * {Integer} 
     */
    granularity: 10,
    
    /** 
     * Property: lastXy
     * {<OpenLayers.LonLat>}
     */
    lastXy: null,

    /**
     * APIProperty: displayProjection
     * {<OpenLayers.Projection>} A projection that the 
     * mousecontrol will display.
     */
    displayProjection: null, 
    
    /**
     * Property: PX
     * If set to true, the outputFormat display the Pixel coords of the cursor
     * 
     **/
    PX: false,     
     
    /**
     * Property: XY
     * If set to true, the outputFormat displaying is the XY coords of the current projection
     * 
     **/
    XY: false,
     
    /**
     * Property: LatLon
     * If set to true, the outputFormat displaying is the LatLon coords with the EPSG:4326 system
     * 
     **/
    LatLon: false,
     
    /**
     * Property: DMS
     * If set to true, the outputFormat displaying is the LatLon coords in DD MM SS.S format  (EPSG:4326 system)
     * 
     **/
    DMS: false,
     
    /**
     * Property: DM
     * If set to true, the outputFormat displaying is the LatLon coords in DD MM.MMMM format  (EPSG:4326 system)
     * 
     **/
    DM: false,
          
    /**
     * Constructor: OpenLayers.Control.MousePosition
     * 
     * Parameters:
     * options - {DOMElement} Options for control.
     */
    initialize: function(options) {
        OpenLayers.Control.prototype.initialize.apply(this, arguments);
    },

    /**
     * Method: destroy
     */
    destroy: function() {
        if (this.map) {
            this.map.events.unregister('mousemove', this, this.redraw);
        }
        OpenLayers.Control.prototype.destroy.apply(this, arguments);
    },

        /**
         * Method: draw
         * {DOMElement}
         */    
        draw: function() {            
            OpenLayers.Control.prototype.draw.apply(this, arguments);

            if (!this.element) {
                this.div.left = "";
                this.div.top = "";
                this.element = this.div;
            }
        
            this.redraw();
            return this.div;
        },
   
        /**
         * Method: redraw  
         */
        redraw: function(evt) {
            
            this.div.style.zIndex = this.map.Z_INDEX_BASE['Control'];  //HACK
            var lonLat;
            var newHtml = "";
            if (evt == null) {
                lonLat = new OpenLayers.LonLat(0, 0);
                //show Pixel coords
                if(this.PX)
                    newHtml += this.formatPX(null);
            } else {
                if (this.lastXy == null ||
                    Math.abs(evt.xy.x - this.lastXy.x) > this.granularity ||
                    Math.abs(evt.xy.y - this.lastXy.y) > this.granularity)
                {
                    this.lastXy = evt.xy;
                    return;
                }
            
                this.lastXy = evt.xy;
                //show Pixel coords
                if(this.PX)
                    newHtml += this.formatPX(evt.xy);
         
                lonLat = this.map.getLonLatFromPixel(evt.xy);
            } 
            if (!lonLat) { 
                // map has not yet been properly initialized
                return;
            }    
            //show XY coords
            if(this.XY || (!this.PX && !this.LatLon && !this.DM && !this.DMS))
                newHtml += this.formatXY(lonLat); 
            
            if( (this.LatLon || this.DMS || this.DM) &&  this.displayProjection == null)
                this.displayProjection = new OpenLayers.Projection("EPSG:4326");
            
            if (this.displayProjection) {
                lonLat.transform(this.map.getProjectionObject(), 
                this.displayProjection );
            }      
                             
            if(this.displayProjection != null && (this.displayProjection.proj.units == null || this.displayProjection.proj.units == "degrees")){
                if(this.LatLon)
                    newHtml += this.formatLatLon(lonLat);
                if(this.DM)
                    newHtml += this.formatDM(lonLat); 
                if(this.DMS)
                    newHtml += this.formatDMS(lonLat);
            }
            
            if (newHtml != this.element.innerHTML) {
                this.element.innerHTML = newHtml;
            }
        },

        /**
         * Method: formatOutput
         * Override to provide custom display output
         *
         * Parameters:
         * lonLat - {<OpenLayers.LonLat>} Location to display
         */
        formatOutput: function(lonLat) {
            var digits = parseInt(this.numDigits);
            var newHtml =
                this.prefix +
                lonLat.lat.toFixed(digits) +
                this.separator + 
                lonLat.lon.toFixed(digits) +
                this.suffix;
            return newHtml;
        },
    
        /** 
         * Method: setMap
         */
        setMap: function() {
            OpenLayers.Control.prototype.setMap.apply(this, arguments);
            this.map.events.register( 'mousemove', this, this.redraw);
        },    
    
        /**
         * Method: formatPX
         * Provide coords in PX format
         *
         * Parameters:
         * lonLat - {<OpenLayers.Pixel>} Location to display
         */
        formatPX: function(px) {
            var digits = parseInt(this.numDigits);
            if(px == null)
                px={x:0,y:0};
            var newHtml =
                this.prefix +
                "<span class='mfCursorTrackPrefix'>"+
                this.prefixPixelX +
                "</span>"+
                px.x+
                this.separator + 
                "<span class='mfCursorTrackPrefix'>"+
                this.prefixPixelY +
                "</span>"+
                px.y +
                this.suffix;
            return newHtml;
        },
     
        /**
         * Method: formatXY
         * Provide coords in XY format
         *
         * Parameters:
         * lonLat - {<OpenLayers.LonLat>} Location to display
         */
        formatXY: function(lonLat) {
            var digits = parseInt(this.numDigits);
            var newHtml =
                this.prefix +
                "<span class='mfCursorTrackPrefix'>"+
                this.prefixX +
                "</span>"+
                lonLat.lon.toFixed(digits) +
                this.separator +
                "<span class='mfCursorTrackPrefix'>"+
                this.prefixY +           
                "</span>"+ 
                lonLat.lat.toFixed(digits) +
                this.suffix;
            return newHtml;
        },
     
        /**
         * Method: formatLatLon
         * Provide coords in Lat/Lon format
         *
         * Parameters:
         * lonLat - {<OpenLayers.LonLat>} Location to display
         */
        formatLatLon: function(lonLat) {
            var digits = parseInt(this.numDigits);
            var newHtml =
                this.prefix +
                "<span class='mfCursorTrackPrefix'>"+
                this.prefixLat +
                "</span>"+
                lonLat.lat.toFixed(digits) +
                this.separator + 
                "<span class='mfCursorTrackPrefix'>"+
                this.prefixLon +
                "</span>"+
                lonLat.lon.toFixed(digits) +            
                this.suffix;
            return newHtml;
        },
     
        /**
         * Method: DMS
         * Provide coords in DD MM SS.S format 
         *
         * Parameters:
         * lonLat - {<OpenLayers.LonLat>} Location to display
         */
        formatDMS: function(lonLat) {
            var digits = parseInt(this.numDigits);
            var lon = this.convertDMS(lonLat.lon.toFixed(digits) , 'LON');
            var lat = this.convertDMS(lonLat.lat.toFixed(digits) , 'LAT');
            var newHtml =
                this.prefix +
                "<span class='mfCursorTrackPrefix'>"+
                this.prefixLat +
                "</span>"+
                lat[0]+
                this.separatorDM +
                lat[1]+         
                this.separatorMS +   
                lat[2]+
                this.separatorSHemi+
                lat[3]+
                this.separator+
                "<span class='mfCursorTrackPrefix'>"+
                this.prefixLon +
                "</span>"+
                lon[0]+
                this.separatorDM +
                lon[1]+         
                this.separatorMS +   
                lon[2]+
                this.separatorSHemi+
                lon[3]+
                this.suffix;
            return newHtml;
        },
     
        /**
         * Method: DM
         * Provide coords in DD MM.MMMM format 
         *
         * Parameters:
         * lonLat - {<OpenLayers.LonLat>} Location to display
         */
        formatDM: function(lonLat) {
            var digits = parseInt(this.numDigits);
            var lon = this.convertDM(lonLat.lon.toFixed(digits) , 'LON');
            var lat = this.convertDM(lonLat.lat.toFixed(digits) , 'LAT');
            var newHtml =
                this.prefix +
                "<span class='mfCursorTrackPrefix'>"+
                this.prefixLat +
                "</span>"+
                lat[0]+
                this.separatorDM +
                lat[1]+         
                this.separatorMS +   
                lat[2]+
                this.separator+
                "<span class='mfCursorTrackPrefix'>"+
                this.prefixLon +
                "</span>"+
                lon[0]+
                this.separatorDM +
                lon[1]+         
                this.separatorMS +               
                lon[2]+
                this.suffix;
            return newHtml;
        },
     
        /**
         * Decimal to DMS conversion
         */
        convertDMS: function(coord, type) {
            var coords = [];
            abscoord = Math.abs(coord)
            coordDeg = Math.floor(abscoord);
            coordMin = (abscoord - coordDeg)/(1/60);
            tempcoordMin = coordMin;
            coordMin = Math.floor(coordMin);
            coordSec = (tempcoordMin - coordMin)/(1/60);
            coordSec =  Math.round(coordSec*10);
            coordSec /= 10;

            if( coordDeg < 10 )
                coordDeg = "0" + coordDeg;

            if( coordMin < 10 )
                coordMin = "0" + coordMin;

            if( coordSec < 10 )
                coordSec = "0" + coordSec;

            coords[0] = coordDeg;
            coords[1] = coordMin;
            coords[2] = coordSec;
            coords[3] = this.getHemi(coord, type);

            return coords;
        },

        /**
         * Decimal to DM (degrees plus decimal minutes) conversion
         */
        convertDM: function(coord, type) {
            var coords = [];
            abscoord = Math.abs(coord)
            coordDeg = Math.floor(abscoord);
            coordMin = (abscoord - coordDeg)*60;
            coordMin = Math.round(coordMin*1000);
            coordMin /= 1000;
            if( coordDeg < 10 )
                coordDeg = "0" + coordDeg;

            if( coordMin < 10 )
                coordMin = "0" + coordMin;

            coords[0] = coordDeg;
            coords[1] = coordMin;
            coords[2] = this.getHemi(coord, type);

            return coords;
        },

        /**
         * Return the hemisphere abbreviation for this coord.
         */
        getHemi: function(coord, type) {
            var coordhemi = "";
            if (type == 'LAT') {
                if (coord >= 0) {
                    coordhemi = "N";
                }
                else {
                    coordhemi = "S";
                }
            }
            else if (type == 'LON') {
                if (coord >= 0) {
                    coordhemi = "E";
                } else {
                    coordhemi = "W";
                }
            }
            return coordhemi;
        },
      
        CLASS_NAME: "OpenLayers.Control.MousePosition"
    });
