/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/** 
 * @requires OpenLayers/Control.js
 * @requires OpenLayers/BaseTypes.js
 * @requires OpenLayers/Events.js
 */

/**
 * Class: OpenLayers.Control.OverviewMap
 * Create an overview map to display the extent of your main map and provide
 * additional navigation control.  Create a new overview map with the
 * <OpenLayers.Control.OverviewMap> constructor.
 *
 * Inerits from:
 *  - <OpenLayers.Control>
 */
OpenLayers.Control.OverviewMap = OpenLayers.Class(OpenLayers.Control.OverviewMap, {


    /**
     * Method: draw
     * Render the control in the browser.
     */    
    draw: function() {
        OpenLayers.Control.prototype.draw.apply(this, arguments);
        if(!(this.layers.length > 0)) {
            if (this.map.baseLayer) {
                var layer = this.map.baseLayer.clone();
                this.layers = [layer];
            } else {
                
                //MAPAFCES there is no Layer object in MapFaces
                //this.map.events.register("changebaselayer", this, this.baseLayerDraw);
                //return this.div;
            }
        }

        //MAPAFCES mapDiv, element and div properties point to the sam DOM element
        // create overview map DOM elements
        /*this.element = document.createElement('div');
        this.element.className = this.displayClass + 'Element';
        this.element.style.display = 'none';

        this.mapDiv = document.createElement('div');
        this.mapDiv.style.width = this.size.w + 'px';
        this.mapDiv.style.height = this.size.h + 'px';
        this.mapDiv.style.position = 'relative';
        this.mapDiv.style.overflow = 'hidden';
        this.mapDiv.id = OpenLayers.Util.createUniqueID('overviewMap');*/
        
        this.extentRectangle = document.createElement('div');
        this.extentRectangle.style.position = 'absolute';
        this.extentRectangle.style.zIndex = this.map.Z_INDEX_BASE['Control']; 
        this.extentRectangle.className = this.displayClass+'ExtentRectangle';

//        this.element.appendChild(this.mapDiv);  
//        this.div.appendChild(this.element);
        
        this.mapDiv=this.div;
        this.element=this.div;
        this.mapDiv.appendChild(this.extentRectangle);
        
        
        // Optionally add min/max buttons if the control will go in the
        // map viewport.
        if(!this.outsideViewport) {
            this.div.className += " " + this.displayClass + 'Container';
            var imgLocation = OpenLayers.Util.getImagesLocation();
            // maximize button div
            var img = imgLocation + 'layer-switcher-maximize.png';
            this.maximizeDiv = OpenLayers.Util.createAlphaImageDiv(
                                        this.displayClass + 'MaximizeButton', 
                                        null, 
                                        new OpenLayers.Size(18,18), 
                                        img, 
                                        'absolute');
            this.maximizeDiv.style.display = 'none';
            this.maximizeDiv.className = this.displayClass + 'MaximizeButton';
            OpenLayers.Event.observe(this.maximizeDiv, 'click', 
                OpenLayers.Function.bindAsEventListener(this.maximizeControl,
                                                        this)
            );
            this.div.appendChild(this.maximizeDiv);
    
            // minimize button div
            var img = imgLocation + 'layer-switcher-minimize.png';
            this.minimizeDiv = OpenLayers.Util.createAlphaImageDiv(
                                        'OpenLayers_Control_minimizeDiv', 
                                        null, 
                                        new OpenLayers.Size(18,18), 
                                        img, 
                                        'absolute');
            this.minimizeDiv.style.display = 'none';
            this.minimizeDiv.className = this.displayClass + 'MinimizeButton';
            OpenLayers.Event.observe(this.minimizeDiv, 'click', 
                OpenLayers.Function.bindAsEventListener(this.minimizeControl,
                                                        this)
            );
            this.div.appendChild(this.minimizeDiv);
            
            var eventsToStop = ['dblclick','mousedown'];
            
            for (var i = 0; i < eventsToStop.length; i++) {

                OpenLayers.Event.observe(this.maximizeDiv, 
                                         eventsToStop[i], 
                                         OpenLayers.Event.stop);

                OpenLayers.Event.observe(this.minimizeDiv,
                                         eventsToStop[i], 
                                         OpenLayers.Event.stop);
            }
            
            this.minimizeControl();
        } else {
            // show the overview map
            this.element.style.display = '';
        }
        if(this.map.getExtent()) {
            this.update();
        }
        
        this.map.events.register('moveend', this, this.update);

        return this.div;
    },
    
    



    /**
     * Method: update
     * Update the overview map after layers move.
     */
    update: function() {
        if(this.ovmap == null) {
            this.createMap();
        }
        //the overviewMap hasn't to be  updated it still at the MaxExtent 
//        if(!this.isSuitableOverview()) {
//            this.updateOverview();
//        }
//        
        // update extent rectangle
        this.updateRectToMap();
    },
    
    
    
    
    /**
     * Method: createMap
     * Construct the map that this control contains
     */
    createMap: function() {
        // create the overview map
        var options = OpenLayers.Util.extend(
                        {controls: [], maxResolution: 'auto', 
                         fallThrough: false}, this.mapOptions);
        this.ovmap = new OpenLayers.Map(this.mapDiv, options);
        
        // prevent ovmap from being destroyed when the page unloads, because
        // the OverviewMap control has to do this (and does it).
        OpenLayers.Event.stopObserving(window, 'unload', this.ovmap.unloadDestroy);
        
        //MAPFACES no layers
        //this.ovmap.addLayers(this.layers);
        
        //MAPFACES don't call the zoomToMaxExtent function, this function must be called on the body's onload event like the others map Object
        //this.ovmap.zoomToMaxExtent();
        //
        // check extent rectangle border width
        this.wComp = parseInt(OpenLayers.Element.getStyle(this.extentRectangle,
                                               'border-left-width')) +
                     parseInt(OpenLayers.Element.getStyle(this.extentRectangle,
                                               'border-right-width'));
        this.wComp = (this.wComp) ? this.wComp : 2;
        this.hComp = parseInt(OpenLayers.Element.getStyle(this.extentRectangle,
                                               'border-top-width')) +
                     parseInt(OpenLayers.Element.getStyle(this.extentRectangle,
                                               'border-bottom-width'));
        this.hComp = (this.hComp) ? this.hComp : 2;

        this.handlers.drag = new OpenLayers.Handler.Drag(
            this, {move: this.rectDrag, done: this.updateMapToRect},
            {map: this.ovmap}
        );
        this.handlers.click = new OpenLayers.Handler.Click(
            this, {
                "click": this.mapDivClick
            },{
                "single": true, "double": false,
                "stopSingle": true, "stopDouble": true,
                "pixelTolerance": 1,
                map: this.ovmap
            }
        );
        this.handlers.click.activate();
        
        this.rectEvents = new OpenLayers.Events(this, this.extentRectangle,
                                                null, true);
        this.rectEvents.register("mouseover", this, function(e) {
            if(!this.handlers.drag.active && !this.map.dragging) {
                this.handlers.drag.activate();
            }
        });
        this.rectEvents.register("mouseout", this, function(e) {
            if(!this.handlers.drag.dragging) {
                this.handlers.drag.deactivate();
            }
        });

    },

    CLASS_NAME: 'OpenLayers.Control.OverviewMap'
});
