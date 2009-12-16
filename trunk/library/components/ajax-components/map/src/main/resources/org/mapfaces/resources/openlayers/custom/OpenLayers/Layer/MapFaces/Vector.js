/**
 * @requires OpenLayers/Layer.js
 * @requires OpenLayers/Renderer.js
 * @requires OpenLayers/StyleMap.js
 * @requires OpenLayers/Feature/MapFaces.js
 * @requires OpenLayers/window.console.js
 */

/**
 * Class: OpenLayers.Layer.mapfaces.Vector
 * Instances of OpenLayers.Layer.MapFaces are used to render MapFaces data from
 *     a variety of sources. Create a new MapFaces layer with the
 *     <OpenLayers.Layer.mapfaces.Vector> constructor.
 *
 * Inherits from:
 *  - <OpenLayers.Layer.MapFaces>
 */
OpenLayers.Layer.MapFaces.Vector = OpenLayers.Class(OpenLayers.Layer.MapFaces, OpenLayers.Layer.Vector, {

    featureBeforeModified: null,
    eventsActived: false,
    reRender: null,
    contextCompId: null,
    reRenderComplete: null,

    initialize: function(options) {
        //OpenLayers.Layer.MapFaces.prototype.initialize.apply(this, arguments);
        this.addOptions(options);
        
        //TODO this creation of a div should not be here, it's in the SvgLayerRender we should do that
        this.div = OpenLayers.Util.getElement(this.compClientId);
        if (this.div == null) {
            this.div = OpenLayers.Util.createDiv(this.compClientId);
            this.div.style.width = "100%";
            this.div.style.height = "100%";
            this.div.dir = "ltr";
        }

        this.events = new OpenLayers.Events(this, this.div,
                                                this.EVENT_TYPES);
        if(this.eventListeners instanceof Object) {
            this.events.on(this.eventListeners);
        }
        
        //this.div = OpenLayers.Util.getElement(this.compClientId);
        OpenLayers.Layer.Vector.prototype.initialize.apply(this, arguments);
        
        this.events.register('featureadded', null, this.onFeatureAdded);
        this.events.register('beforefeaturemodified', null, this.onBeforeFeatureModified);
        this.events.register('afterfeaturemodified', null, this.onAfterFeatureModified);
        this.events.register('featureremoved', null, this.onFeatureRemoved);
    },

    activeEvents: function(active) {
        this.eventsActived = active;
    },

    onFeatureAdded: function(event) {
        if (this.eventsActived) {
            var requestParams = {
                'org.mapfaces.ajax.AJAX_COMPONENT_VALUE': event.feature.id + ';' + event.feature.geometry,
                'org.mapfaces.ajax.AJAX_CONTAINER_ID': 'featureAdded',
                'org.mapfaces.ajax.NO_RERENDER': true,
                'crs': this.map.getProjection()
            };

            this.submit(requestParams);
        }
    },

    onFeatureRemoved: function(event) {
        var requestParams = {
            'org.mapfaces.ajax.AJAX_COMPONENT_VALUE': event.feature.id + ';' + event.feature.geometry,
            'org.mapfaces.ajax.AJAX_CONTAINER_ID': 'featureRemoved',
            'org.mapfaces.ajax.NO_RERENDER': true,
            'crs': this.map.getProjection()
        };
        this.submit(requestParams);
    },

    onBeforeFeatureModified: function(event) {
        this.featureBeforeModified = event.feature.id + ';' + event.feature.geometry;
    },

    onAfterFeatureModified: function(event) {
        if (this.featureBeforeModified != null) {
            var requestParams = {
                'org.mapfaces.ajax.AJAX_COMPONENT_VALUE': this.featureBeforeModified + ';' + event.feature.geometry,
                'org.mapfaces.ajax.AJAX_CONTAINER_ID': 'featureUpdated',
                'org.mapfaces.ajax.NO_RERENDER': true,
                'crs': this.map.getProjection()
            };
            this.featureBeforeModified = null;
            this.submit(requestParams);
        }
    },

    /**
     * APIMethod: onComplete
     * The client side script method to be called after the request is completed
     *
     * Parameters:
     * request - {A4J.AJAX.XMLHttpRequest}  A4J request object.
     * event - {}  DOMEvent.
     * data - {}  JSON representation of the result.
     */
    onComplete: function(request, event, data) {

        if(request && request.options && request.options.parameters && request.options.parameters.refresh
                && (request.options.parameters.refresh.indexOf(this.compClientId) != -1
                || request.options.parameters.refresh.indexOf(this.compId) != -1)) {
            this.div  = document.getElementById(this.compClientId);

            if (this.div && this.div.childNodes[0]) {
                this.imgDiv  = this.div.childNodes[0];
            }
            //TODO load and error events on .imgDiv.childNodes[0] doesn't works on Opera
            //so we triggered the loadend event directly
            //but the good way is to triggered events on image (success or error) events

            this.events.triggerEvent("loadend");
            //this.registerEvents();
        }
        
        //TODO remove the "!= "null"" the value should be null and not "null"
        if (this.reRender != "null" && this.reRender != "" && this.contextCompId != "null" && (this.contextCompId != "")) {
            /* forceRefresh is used to reRender a WMS Layer to deallocate the image from the browser cache. */
            var requestParamsReRender = {'refresh':this.reRender,'forceRefresh':'true'};
            requestParamsReRender[this.contextCompId] = this.contextCompId;
            OpenLayers.Util.sendA4JRequest(this.formClientId,
                {
                    'single':'true',
                    'parameters':requestParamsReRender,
                    'oncomplete':this.reRenderComplete,
                    'actionUrl':this.defaultOptions.actionUrl
                });
        }
    },

    onMoveEnd: function(parameters) {
    },

    onZoomChanged: function(requestParams) {
    },

    onVisibilityChanged: function(parameters) {
    },

    onOpacityChanged: function(parameters) {
    },

    onElevationChanged: function(parameters) {
    },

    onTimeChanged: function(parameters) {
    },

    onDimRangeChanged: function(parameters) {
    },

    /**
     * APIMethod: destroy
     * Destroy this layer
     */
    destroy: function() {
        this.drawn = null;
        this.compClientId = null;
        OpenLayers.Layer.MapFaces.prototype.destroy.apply(this, arguments);
    },

   /*
    *Send A4J request to refrsh layers when a moveend event is triggered
    */
    onRefresh: function(requestParams) {
    },

    _reRender: function(requestParams) {
    },

    /**
     * APIMethod: setOpacity
     * Sets the opacity for the entire layer (all images)
     *
     * Parameter:
     * opacity - {Float}
     */
    setOpacity: function(opacity) {
    },
    /**
     * APIMethod: setElevation
     * Sets the elevation for the layer.
     *
     * Parameter:
     * elevation - {Float}
     */
    setElevation: function(elevation) {
    },

    /**
     * APIMethod: setTime
     * Sets the time parameter for the layer.
     *
     * Parameter:
     * time - {Date}
     */
    setTime: function(time) {
    },

    /**
     * APIMethod: setDimRange
     * Sets the dimrange parameter for the layer.
     *
     * Parameter:
     * dimrange - {Float,Float}
     */
    setDimRange: function(dimRange) {
    },
    /*************************************** functions triggerred on  loading  events******************************/
    onLoadStart: function() {
    },

    onLoadSuccess: function() {
    },

    onLoadFailed: function() {
    },

    onLoadEnd: function() {
    },

    CLASS_NAME: "OpenLayers.Layer.MapFaces.Vector"
});