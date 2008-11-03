/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */


/**
 * @requires OpenLayers/Control.js
 */

/**
 * Class: OpenLayers.Control.MousePosition
 */
OpenLayers.Control.GetFeatureInfo= OpenLayers.Class(OpenLayers.Control, {
    
    
    /**
     * Property: type
     * {OpenLayers.Control.TYPE}
     */
    type: OpenLayers.Control.TYPE_TOOL,
    /** 
     * Property: element
     * {DOMElement} 
     */
    element: null,
    
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
             this.map.events.unregister('mouseup', this, this.redraw);
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
        
        if (this.map && evt != null) {
            //TODO org.mapfaces.ajax.ACTION_SAVE_DIR param is not used in the ontext save function
//http://demo.geomatys.fr/constellation/WS/wms?bbox=-130,24,-66,50&styles=&format=image/png&info_format=text/plain&version=1.1.1&srs=epsg:4326&request=GetFeatureInfo&layers=BlueMarble&query_layers=BlueMarble&width=550&height=250&x=170&y=160
          var parameters = {  
                            'refresh' : 'form:getFeatureInfo',
                            'synchronized' : 'true',
                            'org.mapfaces.ajax.ACTION' : 'getFeatureInfo',
                            'org.mapfaces.ajax.ACTION_GETFEATUREINFO_X' : evt.xy.x,
                            'org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y' : evt.xy.y
            }
            this.map.sendAjaxRequest(parameters);
        }    
    },
    
    /** 
     * Method: setMap
     */
    setMap: function() {
        OpenLayers.Control.prototype.setMap.apply(this, arguments);
        this.map.events.register( 'mouseup', this, this.redraw);
    },    
    
  
      
      
      
    CLASS_NAME: "OpenLayers.Control.GetFeatureInfo"
});
