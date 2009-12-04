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
     * Property: defaultOptions
     * {Object} Required parmaters to make an a4j request independently of the web conatiner (Portlet or Servlet)
     * component
     *
     */
    defaultOptions: null,

    /**
     * Property: targetAjaxCompId
     * {String} Required attribute , it represents the id of A4J component
     *  where we send the request     *
     */
    targetAjaxCompId: null,

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
            this.formClientId,
            null,
            options);


//        A4J.AJAX.Submit(
//            '_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0',
//            null,
//            {   //'affected': this.affected,
//                'containerId': '_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot',
//                'namespace' : "jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj",
//                'control':this,
//                'single':this.ajaxSingle,
//                'parameters': requestParams,
//                //'actionUrl': actionUrl,
//                'actionUrl': "/richfaces\x2Dbasic/faces/pages/echo.xhtml?javax.portlet.faces.DirectLink=true",
////                'onbeforedomupdate': OpenLayers.Function.bind(this.onBeforeDomUpdate, this),
//                'oncomplete': OpenLayers.Function.bind(this.onComplete, this),
//                'similarityGroupingId': "_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0:j_id5"
//            }
//            );

//A4J.AJAX.Submit(
//        '_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0',
//        event,
//        {
//            'parameters': {
//                    '_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0:j_id8':'_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0:j_id8',
//                    'org.ajax4jsf.portlet.NAMESPACE':'jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj',
//                    'javax.faces.portletbridge.STATE_ID':'0d1ca5ca\x2Dd961\x2D4961\x2D82c3\x2D0717bdf523a4:view:jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj'
//            },
//            'containerId':'_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot',
//            'namespace':'jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj',
//            'actionUrl':'/richfaces\x2Dbasic/faces/pages/echo.xhtml?javax.portlet.faces.DirectLink=true',
//            'similarityGroupingId':'_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0:j_id8'
//
//        }
//);
//                AJAXREQUEST	_viewRoot
//_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0	_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0
//_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0:j_id7_Ajax	_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0:j_id7_Ajax
//_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0_link_hidden_	_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj_viewRootj_id0j_id7_j_id8_MapFaces_Layer_WMS_0
//bbox	-180,-90,180,90
//javax.faces.ViewState	j_id1
//org.mapfaces.ajax.AJAX_COMPONENT_VALUE	false
//org.mapfaces.ajax.AJAX_CONTAINER_ID	hidden
//org.mapfaces.ajax.AJAX_LAYER_ID	_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0:j_id7_j_id8_MapFaces_Layer_WMS_0
//org.mapfaces.ajax.LAYER_CONTAINER_STYLE	top:0px;left:0px;
//refresh	_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0:j_id7_j_id8_MapFaces_Layer_WMS_0
//render	true
//synchronized	false
//window	600,300
//
//        A4J.AJAX.Submit(
//            '_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0',
//            null,
//            {   //'affected': this.affected,
//                'containerId': "_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot",
//                'namespace' : "jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj",
//                'control':this,
//                'single':this.ajaxSingle,
//                'parameters': requestParams,
//                //'actionUrl': actionUrl,
//                'actionUrl': "/richfaces-basic/faces/pages/echo.xhtml?javax.portlet.faces.DirectLink=true",
////                'onbeforedomupdate': OpenLayers.Function.bind(this.onBeforeDomUpdate, this),
//                'oncomplete': OpenLayers.Function.bind(this.onComplete, this),
//                'similarityGroupingId': "_jbpns_2fdefault_2fRichFacesEchoPortlet_2fRichFacesEchoPortletWindowsnpbj:_viewRoot:j_id0:j_id5"
//            }
//            );

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
