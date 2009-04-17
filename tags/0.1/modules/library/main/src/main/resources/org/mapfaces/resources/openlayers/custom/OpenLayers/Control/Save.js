/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/**
 * @requires OpenLayers/Control.js
 */

/**
 * Class: OpenLayers.Control.ZoomToMaxExtent 
 * Imlements a very simple button control. Designed to be used with a 
 * <OpenLayers.Control.Panel>.
 * 
 * Inherits from:
 *  - <OpenLayers.Control>
 */
OpenLayers.Control.Save = OpenLayers.Class(OpenLayers.Control, {
    
    /**
     * Property: type
     * TYPE_BUTTON.
     */
    type: OpenLayers.Control.TYPE_BUTTON,
    
    /**
     * Property: url
     * TYPE_BUTTON.
     */
    name: "saveContext.xml",
    /**
     * Property: url
     * TYPE_BUTTON.
     */
    dir: "tmp",
    
    /*
     * Method: trigger
     * Save the context.
     */
    trigger: function() {
        if (this.map) {
            //TODO org.mapfaces.ajax.ACTION_SAVE_DIR param is not used in the ontext save function
            var parameters = {  
                            'org.mapfaces.ajax.NO_RERENDER' : true,
                            'org.mapfaces.ajax.ACTION' : 'save',
                            'org.mapfaces.ajax.ACTION_SAVE_DIR' : this.dir,
                            'org.mapfaces.ajax.ACTION_SAVE_FILENAME' : this.name
            }
            this.map.sendAjaxRequest(parameters);
            //TODO ADD onComplete parameter to the sendAjaxRequest
            window.Timer=function () {
               window.open("tmp/saveContext.xml");
            }
            setTimeout("Timer()",2000);
        }    
    },
//    sendAjaxRequest: function() {
//        var parameters = {    'synchronized': 'true',
//                              'refresh': this.map.layersName,
//                            'org.mapfaces.ajax.ACTION_PARAM' : this.url,
//                            'org.mapfaces.ajax.ACTION' : 'save'
//                         };
//        parameters[this.map.mfAjaxCompId] = this.map.mfAjaxCompId;
//        
//        A4J.AJAX.Submit( this.map.mfRequestId,this.map.mfFormId,
//                         null,
//                         {   
//                             'control':this,
//                             'single':true,
//                             'parameters': parameters ,
//                             'actionUrl':window.location
//                         } 
//                       );
//    },
    CLASS_NAME: "OpenLayers.Control.Save"
});
