/** 
 * Variable: bubble
 * 
 *     Define the size and the events of the bubble .
 *     this bubble display plot information on mouseover
 *     
 */
var bubble = 
    {
        size: {
                width : 220,
                height : 75
              },
        events: {
                  'mouseover': highlight,
                  'mouseout':unhighlight
                }
    
    };
    
/** 
 * Variable: defaultColor
 * 
 *     Default ciolor of the chart plot
 *     
 */    
var defaultColor = "black";

/** 
 * Method: addEvents
 * 
 *    Add events on the chart plot (childs of canavas element), see OpenCharts.Renderer.xxxExt.js to see on 
 *    which type of element the events are added.
 * 
 * @param id        <String>  id of the canvas selement
 * @param renderer  <OpenCharts.Renderer>  Renderer to use for displaying DOM element
 * @param events    <Object{event1:callback1,...}>  Events to add to the plot
 *     
 */ 
function addEvents (id, renderer, events) {
    var elt = document.getElementById(id);
    if (elt && elt.hasChildNodes() && elt.childNodes.length > 0) {
        var list = elt.childNodes;
        for (var i=1; i<list.length; i++) {
            renderer.addEvents(list[i], events);
        }
    }
}

/** 
 * Method: createChart
 * 
 *    Create the chart and its navgation control
 * 
 * @param id        <String>  id of the canvas selement
 *     
 */ 
function createChart(id) {      
    document.chart = new OpenCharts.Map(id, 
        {
            canvasContainer : document.getElementById(id+'Container')
        }
    );
}

/** 
 * Method: init
 * 
 *    Function who initialize  the chart and all the events on it. It is executed 
 *    on body.onload event on IE and when this script is loading on other 
 *    browsers.
 *     
 */ 
function init(){
    var id = 'canvas';
    if (document.getElementById(id)) {
        
        //Create the chart
        if (window.OpenCharts && document.getElementById(id + 'Container') && window.createChart) {
            createChart(id);    
            
            //Attach the renderer to the window
            window.renderer = document.chart.renderer;
            
            //Add mouse events on the chart plot
            if (window.addEvents)
                addEvents(id, window.renderer, window.bubble.events);
            
        }
        
    }
}
init();