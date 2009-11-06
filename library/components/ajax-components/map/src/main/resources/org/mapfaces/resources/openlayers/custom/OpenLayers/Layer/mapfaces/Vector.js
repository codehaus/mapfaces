/**
 * @requires OpenLayers/Layer.js
 * @requires OpenLayers/Renderer.js
 * @requires OpenLayers/StyleMap.js
 * @requires OpenLayers/Feature/MapFaces.js
 * @requires OpenLayers/window.console.js
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
OpenLayers.Layer.MapFaces.Vector = OpenLayers.Class(OpenLayers.Layer.Vector, {

    featureBeforeModified: null,
    mapPane: new OpenLayers.Layer.MapFaces(),

    initialize: function(clientId, options) {
        /* OpenLayers.Layer.MapFaces.prototype.initialize.apply(this, options); */
        OpenLayers.Layer.Vector.prototype.initialize.apply(this, options);
        this.mapPane.formId = options[0];
        this.events.register('featureadded', null, this.onFeatureAdded);
        this.events.register('beforefeaturemodified', null, this.onBeforeFeatureModified);
        this.events.register('afterfeaturemodified', null, this.onAfterFeatureModified);
        this.events.register('featureremoved', null, this.onFeatureRemoved);
    },

    onFeatureAdded: function(event) {
        var requestParams = {
            'org.mapfaces.ajax.AJAX_COMPONENT_VALUE': event.feature.id + ';' + event.feature.geometry,
            'org.mapfaces.ajax.AJAX_CONTAINER_ID': 'featureAdded',
            'org.mapfaces.ajax.NO_RERENDER': true,
            'crs': this.map.getProjection()
        };
        this.mapPane.submit(requestParams);
    },

    onFeatureRemoved: function(event) {
        var requestParams = {
            'org.mapfaces.ajax.AJAX_COMPONENT_VALUE': event.feature.id + ';' + event.feature.geometry,
            'org.mapfaces.ajax.AJAX_CONTAINER_ID': 'featureRemoved',
            'org.mapfaces.ajax.NO_RERENDER': true,
            'crs': this.map.getProjection()
        };
        this.mapPane.submit(requestParams);
    },

    onBeforeFeatureModified: function(event) {
        this.featureBeforeModified = event.feature.id + ';' + event.feature.geometry;
    },

    onAfterFeatureModified: function(event) {
        if (this.featureBeforeModified != null) {
            var requestParams = {
                'org.mapfaces.ajax.AJAX_COMPONENT_VALUE': this.featureBeforeModified + ';' + event.feature.geometry,
                'org.mapfaces.ajax.AJAX_CONTAINER_ID': 'featureAdded',
                'org.mapfaces.ajax.NO_RERENDER': true,
                'crs': this.map.getProjection()
            };
            this.featureBeforeModified = null;
            this.mapPane.submit(requestParams);
        }
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

    CLASS_NAME: "OpenLayers.Layer.MapFaces.Vector"
});