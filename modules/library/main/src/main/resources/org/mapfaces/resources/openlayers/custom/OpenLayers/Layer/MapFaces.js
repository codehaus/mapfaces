/**
 * @requires OpenLayers/Layer.js
 * @requires OpenLayers/Renderer.js
 * @requires OpenLayers/StyleMap.js
 * @requires OpenLayers/Feature/MapFaces.js
 * @requires OpenLayers/Console.js
 */

/**
 * Class: OpenLayers.Layer.MapFaces
 * Instances of OpenLayers.Layer.MapFaces are used to render MapFaces data from
 *     a variety of sources. Create a new MapFaces layer with the
 *     <OpenLayers.Layer.MapFaces> constructor.
 *
 * Inherits from:
 *  - <OpenLayers.Layer>
 */
OpenLayers.Layer.MapFaces = OpenLayers.Class(OpenLayers.Layer.A4JRequest, {

    /**
     * APIProperty: clientId
     * The clientId corresponds to a org.mapfaces.component.UILayer MapFaces
     * component
     * {String}
     */
    clientId: null,

    moveend:[],
    
    zoomchanged:[],

    visibilitychanged: [],

    opacitychanged: [],
    
    /**
     * Constant: EVENT_TYPES
     * {Array(String)} Supported application event types.  Register a listener
     *     for a particular event with the following syntax:
     * (code)
     * layer.events.register(type, obj, listener);
     * (end)
     *
     * Listeners will be called with a reference to an event object.  The
     *     properties of this event depends on exactly what happened.
     *
     * All event objects have at least the following properties:
     * object - {Object} A reference to layer.events.object.
     * element - {DOMElement} A reference to layer.events.element.
     *
     * Supported map event types (in addition to those from <OpenLayers.Layer>):
     * refresh - Triggered when something wants a strategy to ask the protocol
     *      for a new set of features.
     */
    EVENT_TYPES: ["beforeRefresh", "afterRefresh","refresh", "opacitychanged",
    "timechanged", "elevationchanged"],

    /**
     * APIProperty: isBaseLayer
     * {Boolean} The layer is a base layer.  Default is true.  Set this property
     * in the layer options
     */
    isBaseLayer: false,

    /**
     * APIProperty: isFixed
     * {Boolean} Whether the layer remains in one place while dragging the
     * map.
     */
    isFixed: false,

    /**
     * APIProperty: isMapFaces
     * {Boolean} Whether the layer is a MapFaces layer.
     */
    isMapFaces: true,

    /**
     * APIProperty: reportError
     * {Boolean} report friendly error message when loading of renderer
     * fails.
     */
    reportError: true,

    /**
     * Property: drawn
     * {Boolean} Whether the MapFaces Layer  have been drawn yet.
     */
    drawn: false,

    /**
     * Constructor: OpenLayers.Layer.MapFaces
     * Create a new MapFaces layer
     *
     * Parameters:
     * name - {String} A name for the layer
     * options - {Object} Optional object with non-default properties to set on
     *           the layer.
     *
     * Returns:
     * {<OpenLayers.Layer.MapFaces>} A new MapFaces layer
     */

    initialize: function(clientId, options) {

        // concatenate events specific to MapFaces with those from the base
        this.EVENT_TYPES =
        OpenLayers.Layer.MapFaces.prototype.EVENT_TYPES.concat(
            OpenLayers.Layer.prototype.EVENT_TYPES
            );
        this.events = new OpenLayers.Events(this, this.div,
            this.EVENT_TYPES);
        if(this.eventListeners instanceof Object) {
            this.events.on(this.eventListeners);
        }
        this.addOptions(options);
        this.clientId = clientId;
        this.div = OpenLayers.Util.getElement(this.clientId);
        OpenLayers.Layer.A4JRequest.prototype.initialize.apply(this, arguments);
        this.events.register("moveend", null, this.onMoveEnd);
        this.events.register("visibilitychanged", null, this.onVisibilityChanged);
        this.events.register("opacitychanged", null, this.onOpacityChanged);
        this.events.register("elevationchanged", null, this.onElevationChanged);
        this.events.register("timechanged", null, this.onTimeChanged);
        this.events.register("dimrangechanged", null, this.onDimRangeChanged);
        this.events.register("loadstart", null, this.onLoadStart);
        this.events.register("loadend", null, this.onLoadEnd);
        this.events.register("loadsuccess", null, this.onLoadSuccess);
        this.events.register("loadfailed", null, this.onLoadFailed);
    },

    onMoveEnd: function(parameters) {
        this.requestParams = {
            'refresh': this.moveend
        };
        if (parameters.zoomChanged)
            this.onZoomChanged(parameters);
        else
            this.onRefresh(parameters);
    },

    onZoomChanged: function(parameters) {
        this.requestParams = {
            'refresh': this.zoomchanged
        };
        this.onRefresh(parameters);
    },

    onVisibilityChanged: function(parameters) {
        
        this.requestParams = {
            'refresh':this.visibilitychanged,
            'org.mapfaces.ajax.AJAX_COMPONENT_VALUE': !this.visibility,
            'org.mapfaces.ajax.AJAX_CONTAINER_ID': 'hidden'
        };
        this._reRender();
    },

    onOpacityChanged: function(parameters) {
        this.requestParams = {
            'org.mapfaces.ajax.AJAX_COMPONENT_VALUE': this.opacity,
            'org.mapfaces.ajax.AJAX_CONTAINER_ID': 'opacity',
            'org.mapfaces.ajax.NO_RERENDER': true
        };
        this._reRender();
    },

    onElevationChanged: function(parameters) {
        this.requestParams = {
            'org.mapfaces.ajax.AJAX_COMPONENT_VALUE': this.elevation,
            'org.mapfaces.ajax.AJAX_CONTAINER_ID': 'elevation'
        };
        this.onRefresh(parameters);
    },

    onTimeChanged: function(parameters) {
        this.requestParams = {
            'org.mapfaces.ajax.AJAX_COMPONENT_VALUE': this.time,
            'org.mapfaces.ajax.AJAX_CONTAINER_ID': 'time'
        };
        this.onRefresh(parameters);
    },

    onDimRangeChanged: function(parameters) {
        this.requestParams = {
            'org.mapfaces.ajax.AJAX_COMPONENT_VALUE': this.dimRange,
            'org.mapfaces.ajax.AJAX_CONTAINER_ID': 'dimRange'
        };
        this.onRefresh(parameters);
    },
    /**
     * APIMethod: destroy
     * Destroy this layer
     */
    destroy: function() {
        this.drawn = null;
        this.clientId = null;
        OpenLayers.Layer.prototype.destroy.apply(this, arguments);
    },

    /*
    *Send A4J request to refrsh layers when a moveend event is triggered
    */
    onRefresh: function() {
        var refresh = this.clientId;
        var window = this.map.getSize();
        var bbox=this.map.getExtent().toBBOX();
        OpenLayers.Util.extend(this.requestParams, {
            'bbox': bbox,
            'window': window.w+','+window.h,
            'synchronized': 'false',
            'refresh': refresh
        });
        this._reRender();

    },

    _reRender: function() {
        OpenLayers.Util.extend(this.requestParams, {
            //render the layers, always set to true after the first page loads
            'render': 'true',
            'org.mapfaces.ajax.AJAX_LAYER_ID': this.clientId,
            'org.mapfaces.ajax.LAYER_CONTAINER_STYLE':"top:"+
            (-parseInt(this.map.layerContainerDiv.style.top))+"px;"+
            "left:"+(-parseInt(this.map.layerContainerDiv.style.left)+"px;")
        });
        //This is an id for the request, it can be the id of the ajaxregion
        //to activate the ajax loader handling if exists
        this.mfRequestId = this.map.mfRequestId;
        if ((this.requestParams.ajaxRegionClientId)) {
            this.mfRequestId = parameters.ajaxRegionClientId;
        }

        this.compId = this.map.mfAjaxCompId;
        this.formId = this.map.mfFormId;
        this.submit();
    },

    /**
     * APIMethod: setOpacity
     * Sets the opacity for the entire layer (all images)
     *
     * Parameter:
     * opacity - {Float}
     */
    setOpacity: function(opacity) {
        if (opacity != this.opacity) {
            this.opacity = opacity;
            for(var i=0, len=this.div.childNodes.length; i<len; ++i) {
                var element = this.div.childNodes[i].firstChild;
                OpenLayers.Util.modifyDOMElement(element, null, null, null,
                    null, null, null, opacity);
            }
            if (this.map != null) {
                this.map.events.triggerEvent("changelayer", {
                    layer: this,
                    property: "opacity"
                });
            }
            this.events.triggerEvent("opacitychanged");
        }
    },
    /**
     * APIMethod: setElevation
     * Sets the elevation for the layer.
     *
     * Parameter:
     * elevation - {Float}
     */
    setElevation: function(elevation) {
        if (elevation != this.elevation) {
            this.elevation = elevation;
//            if (this.map != null) {
//                this.map.events.triggerEvent("changelayer", {
//                    layer: this,
//                    property: "elevation"
//                });
//            }
            this.events.triggerEvent("elevationchanged");
        }
    },

    /**
     * APIMethod: setTime
     * Sets the time parameter for the layer.
     *
     * Parameter:
     * time - {Date}
     */
    setTime: function(time) {
        if (time != this.time) {
            this.time = time;
//            if (this.map != null) {
//                this.map.events.triggerEvent("changelayer", {
//                    layer: this,
//                    property: "elevation"
//                });
//            }
            this.events.triggerEvent("timechanged");
        }
    },

    /**
     * APIMethod: setDimRange
     * Sets the dimrange parameter for the layer.
     *
     * Parameter:
     * dimrange - {Float,Float}
     */
    setDimRange: function(dimRange) {
        if (dimRange != this.dimRange) {
            this.dimRange = dimRange;
//            if (this.map != null) {
//                this.map.events.triggerEvent("changelayer", {
//                    layer: this,
//                    property: "elevation"
//                });
//            }
            this.events.triggerEvent("dimrangechanged");
        }
    },
    /*************************************** functions triggerred on  loading  events******************************/
    onLoadStart: function() {
        this.div.style.display = "none";
    },

    onLoadEnd: function() {
        this.div.style.display = "block";
    },

    onLoadSuccess: function() {
    },
    
    onLoadFailed: function() {
        this.div.style.backgroundColor = "red";

    },

    CLASS_NAME: "OpenLayers.Layer.MapFaces"
});
