/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.openlayers.org/trunk/openlayers/license.txt for the
 * full text of the license. */

/**
 * @requires OpenLayers/Control/Panel.js
 * @requires OpenLayers/Control/Navigation.js
 * @requires OpenLayers/Control/ZoomBox.js
 */

/**
 * Class: OpenLayers.Control.NavToolbar
 * 
 * Inherits from:
 *  - <OpenLayers.Control.Panel>
 */
OpenLayers.Control.NavToolbar = OpenLayers.Class(OpenLayers.Control.Panel, {

    /**
     * Constructor: OpenLayers.Control.NavToolbar 
     * Add our two mousedefaults controls.
     *
     * Parameters:
     * options - {Object} An optional object whose properties will be used
     *     to extend the control.
     */
    initialize: function(options) { OpenLayers.Control.Panel.prototype.initialize.apply(this, [options]);
        var tab =[];        
        
        //Add zoomIn button
        if(options.zoomIn) {
           tab.push(new OpenLayers.Control.ZoomBox());
        }
        
        //Add zoomOut button
        if (options.zoomOut) {
           tab.push(new OpenLayers.Control.ZoomBox({out:true, displayClass: "olControlZoomBoxOut"}));
        }
                
        //Add pan button
        if (options.pan) {
            (options.panEffect)? tab.push(new OpenLayers.Control.Navigation({'animatedPanEnabled':true}))
            						: tab.push(new OpenLayers.Control.Navigation());
        }
        
        //Add zoom to MaxExtent button
        if (options.zoomMaxExtent) {
           tab.push(new OpenLayers.Control.ZoomToMaxExtent());
        }
        
        //Add next and previous zoom buttons
        if (options.history && nav != null) {
	        tab.push(nav.previous);
	        tab.push(nav.next);
        }        
        
        //Add graticules button
        if(options.graticule){
          tab.push(new OpenLayers.Control.Graticule());
        }
        
        //Add save button
        if(options.save){
          tab.push(new OpenLayers.Control.Save());
        }
        //Add GetFeatureInfo button
        if(options.getFeatureInfo)
          tab.push(new OpenLayers.Control.GetFeatureInfo());
      
        //Add GetCoverage button
        //if(options.getCoverage)
          tab.push(new OpenLayers.Control.GetCoverageMatrix());
      
        //Add Measure distance
        if(options.measureDistance){
           var md = new OpenLayers.Control.Measure(OpenLayers.Handler.Path);
           md.events.on({
                    'measure': handleMeasurements,
                    'measurepartial': handleMeasurements});  
           tab.push(md);
        }
        
        //Add Measure area
        if(options.measureArea){
           var ma = new OpenLayers.Control.MeasureArea(OpenLayers.Handler.Polygon);
           ma.events.on({
                    'measure': handleMeasurements,
                    'measurepartial': handleMeasurements});  
           tab.push(ma);
        }
        
        //Add SelectionZoomBox button
        if(options.selectionZoomBox) {
          tab.push(new OpenLayers.Control.SelectionZoomBox(options.selectionZoomBoxOptions));
        }
      
        if(tab.length > 0)
          this.addControls(tab);
    },

     /**
     * Method: draw 
     * calls the default draw, and then activates mouse defaults.
     */
    draw: function() {
        var div = OpenLayers.Control.Panel.prototype.draw.apply(this, arguments);
        //set the tooltip
        if (this.controls && (this.controls.length > 0)) {
	        for (var i=0; i<this.controls.length; i++) {
	        	if (this.controls[i].panel_div && !this.controls[i].panel_div.title) {
	        		var tooltipClass = this.controls[i].displayClass;
	        		if (tooltipClass.indexOf(" ") != -1) {
	        			var tmp = this.controls[i].displayClass.split(" ");
	        			tooltipClass = tmp[tmp.length-1];
	        		} 
	        		this.controls[i].panel_div.title = OpenLayers.i18n(tooltipClass+"ToolTip");
	        	}
	        }
	    }
        this.div.style.zIndex = this.map.Z_INDEX_BASE['Control'];
        this.activateControl(this.controls[0]);
        return div;
    },


    CLASS_NAME: "OpenLayers.Control.NavToolbar"
});
