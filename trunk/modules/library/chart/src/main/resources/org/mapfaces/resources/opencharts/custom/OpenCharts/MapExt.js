/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/**
 * @requires OpenCharts/Util.js
 * @requires OpenCharts/Events.js
 * @requires OpenCharts/Tween.js
 */

/**
 * Class: OpenCharts.Map
 * Instances of OpenCharts.Map are interactive maps embedded in a web page.
 * Create a new map with the <OpenCharts.Map> constructor.
 * 
 * On their own maps do not provide much functionality.  To extend a map
 * it's necessary to add controls (<OpenCharts.Control>) and 
 * layers (<OpenCharts.Layer>) to the map. 
 */

function setMfIds(chartId, formId, ajaxCompId) {
    window.mfChartId = chartId;
    window.mfFormId = formId;
    window.mfAjaxCompId = ajaxCompId;
}
top.setMfIds = setMfIds;

OpenCharts.Map = OpenCharts.Class(OpenCharts.Map,{
    
    /**
     * Property: panMethod
     * {Function} The Easing function to be used for tweening.  Default is
     * OpenCharts.Easing.Expo.easeOut. Setting this to 'null' turns off
     * animated panning.
     */
    panMethod: null,//OpenCharts.Easing.Expo.easeOut,
    
    
    isSVG : true,
    isHTML : false,
    isVML : false,
    
    /**
     * Property: renderers
     * {Array(String)} List of supported Renderer classes. Add to this list to
     * add support for additional renderers. This list is ordered:
     * the first renderer which returns true for the  'supported()'
     * method will be used, if not defined in the 'renderer' option.
     */
    renderers: ['SVG', 'VML', 'Canvas', 'HTML'],
    
    /** 
     * Property: renderer
     * {<OpenCharts.Renderer>}
     */
    renderer: null,
    
    rendererOptions:{'resolution':1},
    /** 
     * Method: assignRenderer
     * Iterates through the available renderer implementations and selects 
     * and assigns the first one whose "supported()" function returns true.
     */    
    assignRenderer: function()  {
        for (var i=0, len=this.renderers.length; i<this.renderers.length; i++) {
            var rendererClass = OpenCharts.Renderer[this.renderers[i]];
            if (rendererClass && rendererClass.prototype.supported()) {
                this.renderer = new rendererClass(this.div,
                this.rendererOptions);
                break;
            }  
        }  
    },
    getResolution: function() {
        return 1;
    },
    /*************************************** MF extra properties***************************************/
    
    /**
     * Property:currentExtent
     * {<OpenCharts.Bounds>} Save the current extent of the map, use for resize map at the good extent
     */
    currentExtent:null,
    
    /**
     * Property: mfAjaxCompId
     * {String} Unique identifier for the mapafaces ajax component to call for refreh layers
     */
    mfAjaxCompId: null,
    
    
    /*************************************** end MF extra properties***************************************/
    
    /**
     * Constructor: OpenCharts.Map
     * Constructor for a new OpenCharts.Map instance.
     *
     * Parameters:
     * div - {String} Id of an element in your page that will contain the map.
     * options - {Object} Optional object with properties to tag onto the map.
     *
     * Examples:
     * (code)
     * // create a map with default options in an element with the id "map1"
     * var map = new OpenCharts.Map("map1");
     *
     * // create a map with non-default options in an element with id "map2"
     * var options = {
     *     maxExtent: new OpenCharts.Bounds(-200000, -200000, 200000, 200000),
     *     maxResolution: 156543,
     *     units: 'm',
     *     projection: "EPSG:41001"
     * };
     * var map = new OpenCharts.Map("map2", options);
     * (end)
     */    
    initialize: function (div, options) {

        // Simple-type defaults are set in class definition. 
        //  Now set complex-type defaults 
        this.tileSize = new OpenCharts.Size(OpenCharts.Map.TILE_WIDTH,
        OpenCharts.Map.TILE_HEIGHT);
        
        this.maxExtent = new OpenCharts.Bounds(-180, -90, 180, 90);
        this.center = new OpenCharts.LonLat(0,0);
        
        this.paddingForPopups = new OpenCharts.Bounds(15, 15, 15, 15);

        /* this.theme = OpenCharts._getScriptLocation() + 
                             'theme/default/style.css'; */

        // now override default options 
        OpenCharts.Util.extend(this, options);
        
        //this.id = OpenCharts.Util.createUniqueID("OpenCharts.Map_");
        
        this.div = OpenCharts.Util.getElement(div);
        
        this.assignRenderer();
        
        this.renderer.map = this;         
        
        this.renderer.assignMapElements();
        
       
        
        //        if (this.isHTML) {
        //            //Deletee the Form: if it's an id and not an DOM element  else take the div.id
        //            if (typeof div == 'string')div=div.split(":")[1];
        //            else div=div.id; 
        //            if (div.indexOf(':')!=-1)div=div.split(":")[1];
        //
        //            this.viewPortDiv = OpenCharts.Util.getElement(div+"_MapFaces_Viewport");
        //            // the layerContainerDiv is the one that holds all the layers
        //            this.layerContainerDiv =  OpenCharts.Util.getElement(div+"_MapFaces_Container");
        //            
        //        } else if (this.isSVG) {
        //            
        //            this.viewPortDiv = this.div;
        //            this.layerContainerDiv = this.div;    
        //            this.size = new OpenCharts.Size(this.div.getAttribute("width"),this.div.getAttribute("height"));
        //        }
        
        this.events = new OpenCharts.Events(this, 
        this.div, 
        this.EVENT_TYPES, 
        this.fallThrough, 
        {includeXY: true});
         
        this.popups = [];                                   
        this.layers = [];
        this.initResolutions();     
        
        var size = this.getSize();
        
        this.center = this.getLonLatFromViewPortPx(new OpenCharts.Pixel(size.w /2, size.h / 2));
        
        this.updateSize();
        if(this.eventListeners instanceof Object) {
            this.events.on(this.eventListeners);
        }
 
        // update the map size and location before the map moves
        this.events.register("movestart", this, this.updateSize);

        // Because Mozilla does not support the "resize" event for elements 
        // other than "window", we need to put a hack here. 
        if (OpenCharts.String.contains(navigator.appName, "Microsoft")) {
            // If IE, register the resize on the div
            this.events.register("resize", this, this.updateSize);
        } else {
            // Else updateSize on catching the window's resize
            //  Note that this is ok, as updateSize() does nothing if the 
            //  map's size has not actually changed.
            this.updateSizeDestroy = OpenCharts.Function.bind(this.updateSize, 
            this);
            OpenCharts.Event.observe(window, 'resize',
            this.updateSizeDestroy);
        }
        
      

        if (this.controls == null) {
            this.controls = [];
            if (OpenCharts.Control != null) { // running full or lite?
                this.controls.push(new OpenCharts.Control.Navigation());
                //this.controls.push(new OpenCharts.Control.PanZoom());
            } else {
                
            }
        }
        //this.controls.push(new OpenCharts.Control.MouseWheelDefaults());
        //@Todo desactivate the keyboard temporary to wait the coming soon implementation of the defaultKeyboard attribute in the MapPane component.        
        //        this.controls.push(new OpenCharts.Control.KeyboardDefaults());
        //this.controls.push(new OpenCharts.Control.Attribution());
        

        for(var i=0; i < this.controls.length; i++) {
            this.addControlToMap(this.controls[i]);
        }


        this.unloadDestroy = OpenCharts.Function.bind(this.destroy, this);
        
        // always call map.destroy()
        OpenCharts.Event.observe(window, 'unload', this.unloadDestroy);
        
        //Refresh layers on each moveend event
        this.events.register("moveend", null, this.sendAjaxRequest); 
        this.events.register("moveend", null, this.setCurrentExtent);  
        
        
    },
    /** 
     * Method: initResolutions
     * This method's responsibility is to set up the 'resolutions' array 
     *     for the layer -- this array is what the layer will use to interface
     *     between the zoom levels of the map and the resolution display 
     *     of the layer.
     * 
     * The user has several options that determine how the array is set up.
     *  
     * For a detailed explanation, see the following wiki from the 
     *     OpenCharts.org homepage:
     *     http://trac.OpenCharts.org/wiki/SettingZoomLevels
     */
    initResolutions: function() {

        // These are the relevant options which are used for calculating 
        //  resolutions information.
        //
        var props = new Array(
        'projection', 'units',
        'scales', 'resolutions',
        'maxScale', 'minScale', 
        'maxResolution', 'minResolution', 
        'minExtent', 'maxExtent',
        'numZoomLevels', 'maxZoomLevel'
    );
        
        // First we create a new object where we will store all of the 
        //  resolution-related properties that we find in either the layer's
        //  'options' array or from the map.
        //
        if(this.options==null)
            this.options={};
        var confProps = {};        
        for(var i=0; i < props.length; i++) {
            var property = props[i];
            confProps[property] = this.options[property] || this[property];
        }

        // Do not use the scales/resolutions at the map level if
        // minScale/minResolution and maxScale/maxResolution are
        // specified at the layer level
        if (this.options.minScale != null &&
            this.options.maxScale != null &&
            this.options.scales == null) {
            confProps.scales = null;
        }
        if (this.options.minResolution != null &&
            this.options.maxResolution != null &&
            this.options.resolutions == null) {
            confProps.resolutions = null;
        }

        // If numZoomLevels hasn't been set and the maxZoomLevel *has*, 
        //  then use maxZoomLevel to calculate numZoomLevels
        //
        if ( (!confProps.numZoomLevels) && (confProps.maxZoomLevel) ) {
            confProps.numZoomLevels = confProps.maxZoomLevel + 1;
        }

        // First off, we take whatever hodge-podge of values we have and 
        //  calculate/distill them down into a resolutions[] array
        //
        if ((confProps.scales != null) || (confProps.resolutions != null)) {
            //preset levels
            if (confProps.scales != null) {
                confProps.resolutions = [];
                for(var i = 0; i < confProps.scales.length; i++) {
                    var scale = confProps.scales[i];
                    confProps.resolutions[i] = 
                        OpenCharts.Util.getResolutionFromScale(scale, 
                    confProps.units);
                }
            }
            confProps.numZoomLevels = confProps.resolutions.length;

        } else {
            //maxResolution and numZoomLevels based calculation

            // determine maxResolution
            if (confProps.minScale) {
                confProps.maxResolution = 
                    OpenCharts.Util.getResolutionFromScale(confProps.minScale, 
                confProps.units);
            } else if (confProps.maxResolution == "auto") {
                var viewSize = this.getSize();
                var wRes = confProps.maxExtent.getWidth() / viewSize.w;
                var hRes = confProps.maxExtent.getHeight()/ viewSize.h;
                confProps.maxResolution = Math.max(wRes, hRes);
            } 

            // determine minResolution
            if (confProps.maxScale != null) {           
                confProps.minResolution = 
                    OpenCharts.Util.getResolutionFromScale(confProps.maxScale, 
                confProps.units);
            } else if ( (confProps.minResolution == "auto") && 
                (confProps.minExtent != null) ) {
                var viewSize = this.getSize();
                var wRes = confProps.minExtent.getWidth() / viewSize.w;
                var hRes = confProps.minExtent.getHeight()/ viewSize.h;
                confProps.minResolution = Math.max(wRes, hRes);
            } 

            // determine numZoomLevels if not already set on the layer
            // this gives numZoomLevels assuming approximately base 2 scaling
            if (confProps.minResolution != null &&
                this.options.numZoomLevels == undefined) {
                var ratio = confProps.maxResolution / confProps.minResolution;
                confProps.numZoomLevels = 
                    Math.floor(Math.log(ratio) / Math.log(2)) + 1;
            }
            
            // now we have numZoomLevels and maxResolution, 
            //  we can populate the resolutions array
            confProps.resolutions = new Array(confProps.numZoomLevels);
            var base = 2;
            if(typeof confProps.minResolution == "number" &&
                confProps.numZoomLevels > 1) {
                /**
                 * If maxResolution and minResolution are set (or related
                 * scale properties), we calculate the base for exponential
                 * scaling that starts at maxResolution and ends at
                 * minResolution in numZoomLevels steps.
                 */
                base = Math.pow(
                (confProps.maxResolution / confProps.minResolution),
                (1 / (confProps.numZoomLevels - 1))
            );
            }
            for (var i=0; i < confProps.numZoomLevels; i++) {
                var res = confProps.maxResolution / Math.pow(base, i);
                confProps.resolutions[i] = res;
            }
        }
        
        //sort resolutions array ascendingly
        //
        confProps.resolutions.sort( function(a, b) { return(b-a); } );

        // now set our newly calculated values back to the layer 
        //  Note: We specifically do *not* set them to layer.options, which we 
        //        will preserve as it was when we added this layer to the map. 
        //        this way cloned layers reset themselves to new map div 
        //        dimensions)
        //

        this.resolutions = confProps.resolutions;
        this.maxResolution = confProps.resolutions[0];
        var lastIndex = confProps.resolutions.length - 1;
        this.minResolution = confProps.resolutions[lastIndex];
        
        this.scales = [];
        for(var i = 0; i < confProps.resolutions.length; i++) {
            this.scales[i] = 
                OpenCharts.Util.getScaleFromResolution(confProps.resolutions[i], 
            confProps.units);
        }
        this.minScale = this.scales[0];
        this.maxScale = this.scales[this.scales.length - 1];
        
        this.numZoomLevels = confProps.numZoomLevels;
    },
   
    
    /**
     * APIMethod: getCurrentExtent
     * 
     * Returns:
     * {<OpenCharts.Bounds>}
     */
    getCurrentExtent: function () {
        return this.currentExtent;
    },
    /**
     * APIMethod: setCurrentExtent
     * 
     * Parameters:
     * {<OpenCharts.Bounds>}
     */
    setCurrentExtent: function () {
        this.currentExtent = this.getExtent();
        
    },
    
    /********************************************************/
    /*                                                      */
    /*                  Zooming Functions                   */
    /*                                                      */
    /*    The following functions, all publicly exposed     */
    /*       in the API, are all merely wrappers to the     */
    /*               the setCenter() function               */
    /*                                                      */
    /********************************************************/
    /*
     *Send A4J request to refrsh layers when a moveend event is triggered
     */
    
    sendAjaxRequest: function(parameters) {      
        this.mfChartId = window.mfChartId;  
        this.mfFormId = window.mfFormId;
        this.mfAjaxCompId = window.mfAjaxCompId;
        var tag = top.document.getElementById(this.mfChartId);
        parameters[this.mfAjaxCompId] = this.mfAjaxCompId;           
        parameters['refresh'] = this.mfChartId;
        var str = "";
        if (top.A4J) {
//            DEBUG IE
//            for (var i in parameters)
//                if (typeof parameters[i] != "function")
//                    str += i+" = "+parameters[i]+"\n";
//            alert(str);
            top.A4J.AJAX.Submit( "updateChart", this.mfFormId,
                null,
                {   
                    'control':this,
                    'parameters': parameters ,
                    'actionUrl' : top.location.href
                }
            );
            //Clone the tag containing the SVg document 
            if (tag){   
                var elt = top.document.createElement('div'); 
                elt.setAttribute('id', this.mfChartId );
                elt.setAttribute('style','display:none;width:0px;height:0px;');
                elt.setAttribute('width','0');
                elt.setAttribute('height','0');
                tag.id=this.mfChartId +'clone';
                tag.parentNode.insertBefore(elt,tag);
            } else {
                alert("The element with the id '"+this.mfChartId+"' doesn't exist !!!!!");
            }
        } else {
            alert('A4J is not defined');
        }
    },  
    /** 
     * APIMethod: zoomToBox:
     * Zoom to the extent specify in pixel.
     */
    zoomToBox: function(bounds, out) {
        if (!isNaN(bounds.left) || !isNaN(bounds.bottom) || !isNaN(bounds.right) || !isNaN(bounds.top))
            this.renderer.zoomToBox(bounds, out);
    },
    /** 
     * APIMethod: zoomToPixel:
     * Zoom to the pixel.
     */
    zoomToPixel: function(pixel, out) {        
        if (!isNaN(pixel.x) || !isNaN(pixel.y))
            this.renderer.zoomToPixel(pixel, out);   
    }, 
    /** 
     * APIMethod: pan
     * Allows user to pan by a value of screen pixels
     * 
     * Parameters:
     * dx - {Integer}
     * dy - {Integer}
     * options - {Object} Options to configure panning:
     *  - *animate* {Boolean} Use panTo instead of setCenter. Default is true.
     *  - *dragging* {Boolean} Call setCenter with dragging true.  Default is
     *    false.
     */
    pan: function(dx, dy, options) {
        this.renderer.pan(dx, dy, options);
    },
    /** 
     * APIMethod: panToPixel:
     * Pan to the pixel.
     */
    panToPixel: function(pixel) {
        if (!isNaN(pixel.x) || !isNaN(pixel.y))
            this.renderer.panToPixel(pixel);
    },
     /**
     * APIMethod: destroy
     * Destroy this map
     */
    destroy:function() {
        // if unloadDestroy is null, we've already been destroyed
        if (!this.unloadDestroy) {
            return false;
        }

        // map has been destroyed. dont do it again!
        OpenCharts.Event.stopObserving(window, 'unload', this.unloadDestroy);
        this.unloadDestroy = null;

        if (this.updateSizeDestroy) {
            OpenCharts.Event.stopObserving(window, 'resize', 
                                           this.updateSizeDestroy);
        } else {
            this.events.unregister("resize", this, this.updateSize);
        }    
        
        this.paddingForPopups = null;    

        if (this.controls != null) {
            for (var i = this.controls.length - 1; i>=0; --i) {
                this.controls[i].destroy();
            } 
            this.controls = null;
        }
        if (this.layers != null) {
            for (var i = this.layers.length - 1; i>=0; --i) {
                //pass 'false' to destroy so that map wont try to set a new 
                // baselayer after each baselayer is removed
                this.layers[i].destroy(false);
            } 
            this.layers = null;
        }
        this.renderer.destroyMapElements(); 
        this.renderer = null;
//        if (this.viewPortDiv) {
//            this.div.removeChild(this.viewPortDiv);
//        }
//        this.viewPortDiv = null;

        if(this.eventListeners) {
            this.events.un(this.eventListeners);
            this.eventListeners = null;
        }
        this.events.destroy();
        this.events = null;

    }
});
