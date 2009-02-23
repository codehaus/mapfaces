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
    active: false,
                defaultHandlerOptions: {
                    'single': true,
                    'double': false,
                    'pixelTolerance': 0,
                    'stopSingle': false,
                    'stopDouble': false
                },

                initialize: function(options) {
                    this.handlerOptions = OpenLayers.Util.extend(
                        {}, this.defaultHandlerOptions
                    );
                    OpenLayers.Control.prototype.initialize.apply(
                        this, arguments
                    ); 
                    this.handler = new OpenLayers.Handler.Click(
                        this, {
                            'click': this.onClick
                        }, this.handlerOptions
                    );
                }, 

                onClick: function(evt) {
                    if (this.map && evt != null) {
                    //TODO org.mapfaces.ajax.ACTION_SAVE_DIR param is not used in the ontext save function
                    //http://demo.geomatys.fr/constellation/WS/wms?bbox=-130,24,-66,50&styles=&format=image/png&info_format=text/plain&version=1.1.1&srs=epsg:4326&request=GetFeatureInfo&layers=BlueMarble&query_layers=BlueMarble&width=550&height=250&x=170&y=160
                    var parameters = {  
                                      'refresh' : this.idToRefresh,
                                      'synchronized' : 'true',
                                      'org.mapfaces.ajax.ACTION' : 'getFeatureInfo',
                                      'org.mapfaces.ajax.ACTION_GETFEATUREINFO_X' : evt.xy.x,
                                      'org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y' : evt.xy.y
                    }
                    this.map.sendAjaxRequest(parameters);
                    //alert(this.map.getLonLatFromPixel(new OpenLayers.Pixel(evt.xy.x, evt.xy.y)));
                  }    
                },
    activate: function () {

        if (this.active) {
            return false;
        }
        if (this.handler) {
            this.handler.activate();
this.map.div.style.cursor='pointer';
        }
        this.active = true;
        this.events.triggerEvent("activate");
        return true;
    },
    /**
     * Method: deactivate
     */
    deactivate: function() {

        if (this.active) {
 	    this.map.div.style.cursor='default';
            if (this.handler) {
                this.handler.deactivate();
            }
            this.active = false;
            this.events.triggerEvent("deactivate");
            return true;
        }
        return false;
    },
/**
     * Method: deactivate
     */
//    deactivate: function() {
//       //if(OpenLayers.Control.prototype.deactivate.apply(this,arguments)){
//          if(document.getElementById('form:getFeatureInfo'))document.getElementById('form:getFeatureInfo').style.display='none';
//          //return true;
//       //}else{
//         // return false;
//      // }
//    },
    CLASS_NAME: "OpenLayers.Control.GetFeatureInfo"
});                
//OpenLayers.Class(OpenLayers.Control, {
//    
//    
//    /**
//     * Property: type
//     * {OpenLayers.Control.TYPE}
//     */
//    type: OpenLayers.Control.TYPE_TOOL,
//
//    /**
//     * APIProperty: handleRightClicks
//     * {Boolean} Whether or not to handle right clicks. Default is false.
//     */
//    handleRightClicks: false,
//    
//    /**
//     * Constructor: OpenLayers.Control.Navigation
//     * Create a new navigation control
//     * 
//     * Parameters:
//     * options - {Object} An optional object whose properties will be set on
//     *                    the control
//     */
//    initialize: function(options) {
//        this.handlers = {};
//        OpenLayers.Control.prototype.initialize.apply(this, arguments);
//    },
//
//    /**
//     * Method: destroy
//     * The destroy method is used to perform any clean up before the control
//     * is dereferenced.  Typically this is where event listeners are removed
//     * to prevent memory leaks.
//     */
//    destroy: function() {
//        this.deactivate();
//        OpenLayers.Control.prototype.destroy.apply(this,arguments);
//    },
//    
//    /**
//     * Method: activate
//     */
//    activate: function() {
//        this.handlers.click.activate();
//        return OpenLayers.Control.prototype.activate.apply(this,arguments);
//    },
//
//    /**
//     * Method: deactivate
//     */
//    deactivate: function() {
//        this.handlers.click.deactivate();
//        return OpenLayers.Control.prototype.deactivate.apply(this,arguments);
//    },
//    
//    /**
//     * Method: draw
//     */
//    draw: function() {
//        // disable right mouse context menu for support of right click events
//        if (this.handleRightClicks) {
//            this.map.div.oncontextmenu = function () { return false;};
//        }
//
//        var clickCallbacks = { 
//            'click': this.defaultClick
//            /*, 
//            'dblclick': this.defaultDblClick, 
//            'dblrightclick': this.defaultDblRightClick */
//        };
//        var clickOptions = {
//            'single': true
//        };
//        this.handlers.click = new OpenLayers.Handler.Click(
//            this, clickCallbacks, clickOptions
//        );
//        
//        //this.activate();
//    },
//    /**
//     * Method: defaultClick 
//     * 
//     * Parameters:
//     * evt - {Event} 
//     */
//    defaultClick: function (evt) {
//        if (this.map && evt != null) {
//            //TODO org.mapfaces.ajax.ACTION_SAVE_DIR param is not used in the ontext save function
//            //http://demo.geomatys.fr/constellation/WS/wms?bbox=-130,24,-66,50&styles=&format=image/png&info_format=text/plain&version=1.1.1&srs=epsg:4326&request=GetFeatureInfo&layers=BlueMarble&query_layers=BlueMarble&width=550&height=250&x=170&y=160
//            var parameters = {  
//                              'refresh' : 'form:getFeatureInfo',
//                              'synchronized' : 'true',
//                              'org.mapfaces.ajax.ACTION' : 'getFeatureInfo',
//                              'org.mapfaces.ajax.ACTION_GETFEATUREINFO_X' : evt.xy.x,
//                              'org.mapfaces.ajax.ACTION_GETFEATUREINFO_Y' : evt.xy.y
//            }
//            alert('ici');
//            this.map.sendAjaxRequest(parameters);
//        }    
//    },
//    
//    
//    CLASS_NAME: "OpenLayers.Control.GetFeatureInfo"
//});
