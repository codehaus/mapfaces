
/**
 * Class: OpenCharts.Renderer.Elements
 * This is another virtual class in that it should never be instantiated by 
 *  itself as a Renderer. It exists because there is *tons* of shared 
 *  functionality between different vector libraries which use nodes/elements
 *  as a base for rendering vectors. 
 * 
 * The highlevel bits of code that are implemented here are the adding and 
 *  removing of geometries, which is essentially the same for any 
 *  element-based renderer. The details of creating each node and drawing the
 *  paths are of course different, but the machinery is the same. 
 * 
 * Inherits:
 *  - <OpenCharts.Renderer>
 */
OpenCharts.Renderer.Elements = OpenCharts.Class(OpenCharts.Renderer.Elements, {
    /**
     * Constructor: OpenCharts.Renderer.Elements
     * 
     * Parameters:
     * containerID - {String}
     * options - {Object} options for this renderer. Supported options are:
     *     * yOrdering - {Boolean} Whether to use y-ordering
     *     * zIndexing - {Boolean} Whether to use z-indexing. Will be ignored
     *         if yOrdering is set to true.
     */
    initialize: function(containerID, options) {
        OpenCharts.Renderer.prototype.initialize.apply(this, arguments);

//        this.rendererRoot = this.createRenderRoot();
//        this.root = this.createRoot();
//        
//        this.rendererRoot.appendChild(this.root);
//        this.container.appendChild(this.rendererRoot);
        
        if(options && (options.zIndexing || options.yOrdering)) {
            this.indexer = new OpenCharts.ElementsIndexer(options.yOrdering);
        }
    },
    /***********************OPENCHARTS *********************/
    assignMapElements: function() {   
        this.map.viewPortDiv = this.map.div;
        this.map.layerContainerDiv = this.map.div;    
        this.map.size = new OpenCharts.Size(this.map.div.getAttribute("width"),this.map.div.getAttribute("height"));
    },
     /***********************OPENCHARTS *********************/
    destroyMapElements: function() {   
        this.map.viewPortDiv = null;
        this.map.layerContainerDiv = null;    
        this.map.size = null;
    },
    /***********************OPENCHARTS *********************/
    /**
     * Method: wheelChange  
     *
     * Parameters:
     * evt - {Event}
     * deltaZ - {Integer}
     */
    wheelChange: function (evt, deltaZ){
        this.map.zoomToPixel(evt.xy, ( deltaZ == -1));
    }, 
    /***********************OPENCHARTS *********************/
    /**
     * Method: panMapDone
     * Finish the panning operation.  Only call setCenter (through <panMap>)
     *     if the map has actually been moved.
     *
     * Parameters:
     * xy - {<OpenCharts.Pixel>} Pixel of the mouse position
     */
    panMapDone: function(xy) {
        this.map.panToPixel(xy);
    },
    
    /***********************OPENCHARTS *********************/
    /**
     * Method: zoomBox
     *
     * Parameters:
     * position - {<OpenCharts.Bounds>} or {<OpenCharts.Pixel>}
     */
    zoomBox: function (position) {
        if (position instanceof OpenCharts.Bounds) {
            this.map.zoomToBox(position,this.out);
        } else { // it's a pixel
            this.map.zoomToPixel(position,this.out);
        }
    },
    
    /************OPENCHARTS************/
    /**
     * Method: createRenderRoot
     * 
     * Returns:
     * {DOMElement} The specific render engine's root element
     */
    createBox: function(id, geometry) {
       return this.createRectangle( id, geometry,
        {
          fillColor: "#ee9900",
          fillOpacity: 0.4, 
          hoverFillColor: "white",
          hoverFillOpacity: 0.8,
          strokeColor: "#ee9900",
          strokeOpacity: 1,
          strokeWidth: 1,
          strokeLinecap: "round",
          strokeDashstyle: "solid",
          hoverStrokeColor: "red",
          hoverStrokeOpacity: 1,
          hoverStrokeWidth: 0.2,
          pointRadius: 6,
          hoverPointRadius: 1,
          hoverPointUnit: "%",
          pointerEvents: "visiblePainted",
          cursor: "inherit"
        }, {
            isFilled:true,
            isStroked:true
        });
    },
     /************OPENCHARTS************/
    /**
     * Method: createRenderRoot
     * 
     * Returns:
     * {DOMElement} The specific render engine's root element
     */
    createRectangle: function(id, geometry, style, isFilledAndStroked) {
       return this.setStyle(
        this.nodeFactory(id, this.getNodeType(geometry, null)),
        style, 
        isFilledAndStroked, null);
    }
});