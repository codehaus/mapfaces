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

    initialize: function(clientId, options) {
        //OpenLayers.Layer.MapFaces.prototype.initialize.apply(this, arguments);
        OpenLayers.Layer.Vector.prototype.initialize.apply(this, arguments);
        this.id = clientId + "_layer";
        this.clientId = this.formId + ':' + this.id;
        this.compId = clientId;
        this.events.register('featureadded', null, this.onFeatureAdded);
        this.events.register('beforefeaturemodified', null, this.onBeforeFeatureModified);
        this.events.register('afterfeaturemodified', null, this.onAfterFeatureModified);
        this.events.register('featureremoved', null, this.onFeatureRemoved);
    },

    activeEvents: function(active) {
        this.eventsActived = active;
    },

    initReRender: function(_reRender) {
        this.reRender = _reRender;
    },

    onFeatureAdded: function(event) {
        if (this.eventsActived) {
            var requestParams = {
                'org.mapfaces.ajax.AJAX_COMPONENT_VALUE': event.feature.id + ';' + event.feature.geometry,
                'org.mapfaces.ajax.AJAX_CONTAINER_ID': 'featureAdded',
                'org.mapfaces.ajax.NO_RERENDER': true,
                'refresh': this.reRender,
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
            'refresh': this.reRender,
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
        this.clientId = null;
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