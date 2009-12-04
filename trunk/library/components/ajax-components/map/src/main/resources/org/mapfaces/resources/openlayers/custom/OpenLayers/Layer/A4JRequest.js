/**
 * @requires OpenLayers/Layer.js
 */

/**
 * Class: OpenLayers.Layer.A4JRequest
 *
 * Inherits from:
 *  - <OpenLayers.Layer>
 */
OpenLayers.Layer.A4JRequest = OpenLayers.Class(OpenLayers.Layer, {

    /**
     * Property: requestId
     * {String} Required attribute , it represents the identifier of the A4J
     * request
     *
     */
    requestId: null,

     /**
     * Property: compId
     * {String} Required attribute , it represents the id of the layer component
     *  where we send the request
     *
     */
    compId: null,

    /**
     * Property: compClientId
     * {String} Required attribute , it represents the clientId of the layer component
     *  where we send the request
     *
     */
    compClientId: null,

    /**
     * Property: formClientId
     * {String} Required attribute , it represents the clientId of UIForm
     * component
     *
     */
    formClientId: null,

    /**
     * Property: targetAjaxCompId
     * {String} Required attribute , it represents the id of A4J component
     *  where we send the request
     *
     */
    targetAjaxCompId: null,
    
    /**
     * Property: defaultOptions
     * {Object} Required parmaters to make an a4j request independently of the web container (Portlet or Servlet)
     * component
     *
     */
    defaultOptions: null,

    /**
     * Property: affected
     * {Array} ids of MapFaces JSF components to rerender
     *
     */
    affected: [],
    
    /**
     * Property: ajaxSingle
     * {Boolean} boolean attribute which provides possibility to limit JSF tree
     * processing(decoding, conversion/validation, value applying) to the
     * component
     * which send the request only.
     * Default to true.
     *
     */
    ajaxSingle: true,

    /**
     * Property: bypassUpdates
     * {Boolean} If "true", after process validations phase it skips updates of
     * model beans on a force render response. It can be used for validating
     * components input.
     * Default to false.
     *
     */
    bypassUpdates: false,

    /**
     * Property: limitToList
     * {Boolean} If "true", then of all AJAX-rendered on the page components
     * only those will be updated, which ID's are passed to the "reRender"
     * attribute of the describable component. "false"-the default value-means
     * that all components with ajaxRendered="true" will be updated.
     * Default to false.
     *
     */
    limitToList: false,

    /**
     * Property: ignoreDupResponses
     * {Boolean} Attribute allows to ignore an Ajax Response produced by a
     * request if the newest 'similar' request is in a queue already.
     * ignoreDupResponses="true" does not cancel the request while it is
     * processed on the server, but just allows to avoid unnecessary updates on
     * the client side if the response isn't actual now.
     * Default to false.
     *
     */
    ignoreDupResponses: false,

    /**
     * Property: immediate
     * {Boolean} True means, that the default ActionListener should be executed 
     * immediately (i.e. during Apply Request Values phase of the request 
     * processing lifecycle), rather than waiting until the Invoke Application 
     * phase. 
     * Default to false
     *
     */
    immediate: false,


    /**
     * Property: requestDelay
     * {Int} Attribute defines the time (in ms.) that the request will be
     *  wait in the queue before it is ready to send. When the delay time is
     *  over, the request will be sent to the server or removed if the newest
     *  'similar' request is in a queue already
     * Default to 0.
     *
     */
    requestDelay: 0,


    /**
     * Property: timeOut
     * {Int} Timeout (in ms) for request
     * Default to 100000.
     *
     */
    timeOut: 100000,

    /**
     * Property: requestParams
     * {Object} Represents KVP to send with the request
     *
     */
    requestParams: {},

    initialize: function() {
        OpenLayers.Layer.prototype.initialize.apply(this, arguments);
    },
    /**
     * APIMethod: onbeforedomupdate
     * Default function executed before sending of the A4J request
     *
     * Parameters:
     * request - {A4J.AJAX.XMLHttpRequest}  A4J request object.
     * event - {}  DOMEvent.
     * data - {}  JSON representation of the result.
     */
    onBeforeDomUpdate: function(request, event, data) {

//        if(requestParams.refresh.indexOf(this.clientId) != -1)
//        window.console.debug(this.clientId + " onBeforeDomUpdate");
    },

    /**
     * APIMethod: onSubmit
     * Default function executed before sending of the A4J request
     *
     * Parameters:
     * request - {A4J.AJAX.XMLHttpRequest}  A4J request object.
     * event - {}  DOMEvent.
     * data - {}  JSON representation of the result.
     */
    onSubmit: function(requestParams) {
        if(requestParams && requestParams.refresh && requestParams.refresh.indexOf(this.clientId) != -1)
            this.events.triggerEvent("loadstart");
    },

    /**
     * APIMethod: submit
     * 	JavaScript code for call before submission of ajax event
     *
     */
    submit: function(requestParams) {
        this.onSubmit(requestParams);

        //This is an id for the request, it can be the id of the ajaxregion
        //to activate the ajax loader handling if exists
        this.mfRequestId = this.map.mfRequestId;
        if ((this.requestParams.ajaxRegionClientId)) {
            this.mfRequestId = parameters.ajaxRegionClientId;
        }

        this.targetAjaxCompId = this.map.mfAjaxCompId;
        this.formClientId = this.map.mfFormClientId;
        this.defaultOptions = this.map.mfAjaxDefaultOptions;

        var options = null;
        requestParams[this.targetAjaxCompId] = this.targetAjaxCompId;

        if (this.defaultOptions != null) {
            options = {};
            OpenLayers.Util.extend(options, this.defaultOptions);

            OpenLayers.Util.extend(options, {
                'control':this,
                'single':this.ajaxSingle,
                'oncomplete': OpenLayers.Function.bind(this.onComplete, this)
            });

            if (requestParams != null) {

                if (options.parameters == null) {
                    options.parameters = {};
                }
                OpenLayers.Util.extend(options.parameters, requestParams);
            }


        }

        A4J.AJAX.Submit(
            this.requestId,
            this.formClientId,
            null,
            options);

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
        //this is the A4JRequest object
        //this.control is the Layer object

        if(request && request.options && request.options.parameters && request.options.parameters.refresh && request.options.parameters.refresh.indexOf(this.clientId) != -1) {
            this.div  = document.getElementById(this.clientId);
            if (this.div && this.div.childNodes[0]) {
                this.imgDiv  = this.div.childNodes[0];
            }
            //TODO load and error events on .imgDiv.childNodes[0] doesn't works on Opera
            //so we triggered the loadend event directly
            //but the good way is to triggered events on image (success or error) events

            this.events.triggerEvent("loadend");
            //this.registerEvents();
        }
    },
    /**
     * APIMethod: onLoad
     * Function called when layer img is well-loaded
     *
     * Parameters:
     * request - {A4J.AJAX.XMLHttpRequest}  A4J request object.
     * event - {}  DOMEvent.
     * data - {}  JSON representation of the result.
     */
    onLoad: function(request, event, data) {
        //this.events.triggerEvent("loadsuccess");
        this.events.triggerEvent("loadend");
    },
     /**
     * APIMethod: onError     *
     * Function called when layer img has failed
     *
     * Parameters:
     * request - {A4J.AJAX.XMLHttpRequest}  A4J request object.
     * event - {}  DOMEvent.
     * data - {}  JSON representation of the result.
     */
    onError: function(request, event, data) {
        //this.events.triggerEvent("loadfailed");

        this.events.triggerEvent("loadend");
    },

    registerEvents: function() {
    var test = function() {alert("test l241 : " +this.id)};
        if (this.imgDiv) {
            OpenLayers.Event.observe(this.imgDiv.childNodes[0], 'load',
                OpenLayers.Function.bind(test, this.imgDiv.childNodes[0]));
            OpenLayers.Event.observe(this.imgDiv.childNodes[0], 'error',
                OpenLayers.Function.bind(test, this.imgDiv.childNodes[0]));
        }
    }, 
    
    unregisterEvents: function() {
        OpenLayers.Event.stopObservingElement(this.imgDiv.childNodes[0]);
    },
    CLASS_NAME: "OpenLayers.Layer.A4JRequest"
});
