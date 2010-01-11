//function createElement(type, attributes) {
//    var elt = document.createElementNS(xmlnsSVG, type);
//    for (var i in attributes) {
//        elt.setAttribute(i,attributes[i]);
//    }
//    return elt;
//}
//function createLine(attributes) {
//    return createElement('line', attributes);
//}
//function createVerticalLine(evt) {
//    var canvasContainer = document.getElementById('canvasContainer');
//    var attr = {
//        x1:evt.clientX,
//        x2:evt.clientX,
//        y1: canvasContainer.getAttribute('y'),
//        y2: parseFloat(canvasContainer.getAttribute('height'))+parseFloat(canvasContainer.getAttribute('y')),
//        stroke: evt.target.getAttribute('default-stroke')
//    }
//    return createLine(attr);
//}
//function createHorizontalLine(evt) {
//    var canvasContainer = document.getElementById('canvasContainer');
//    var attr = {
//        x1: canvasContainer.getAttribute('x'),
//        x2: parseFloat(canvasContainer.getAttribute('width'))+ parseFloat(canvasContainer.getAttribute('x')),
//        y1: evt.clientY,
//        y2: evt.clientY,
//        stroke: evt.target.getAttribute('default-stroke')
//    }
//    return createLine(attr);
//}
//
//function createLines(evt) {
//     var attr = {
//        'id': 'cursorLines',
//        'stroke-width': 1,
//        'stroke-linejoin': 'bevel',
//        'stroke-linecap': 'butt',
//        'stroke-dasharray': '2,2',
//        'stroke-miterlimit': '0'
//    }
//    var g = createElement('g', attr);
//    g.appendChild(createVerticalLine(evt));
//    g.appendChild(createHorizontalLine(evt));
//    return g;
//}

/**
 * Method: calculBubblePosition
 * 
 *     This function  calculate if the bubble should be displayed on top or bottom and right or left
 *     
 * @param  evt      browser mouse event
 * @return position  <Object {x:,y:}>   The top-left pixel coordinates of bubble
 */
function calculBubblePosition (evt) {     
    
    //mouse coordinates without offsets see OpenCharts.Events.getMousePosition() for explanation
    var x = evt.clientX;
    var y = evt.clientY;
    var bottom = false;
    var left = false;   
    
    //Default is on top-right;
    var position = { 
        x :  (x + 10), 
        y : (y - bubble.size.height - 10) 
    }; 
    
    //find the canvas container node
    var canvasContainerElt = document.getElementById('canvasContainer'); 
    if (canvasContainerElt) {
//        IE DEBUG
//        var str ="";
//        str+="canvasContainer.x = "+renderer.getAttribute("x",canvasContainerElt)+"\n";
//        str+="canvasContainer.y = "+renderer.getAttribute("y",canvasContainerElt)+"\n";
//        str+="canvasContainer.width = "+renderer.getAttribute("width",canvasContainerElt)+"\n";
//        str+="canvasContainer.height = "+renderer.getAttribute("height",canvasContainerElt)+"\n";
//        alert(str);
//        
        //Get the canvas container coordinates
        var minX = window.renderer.getAttribute("x",canvasContainerElt);
        var maxX = minX + window.renderer.getAttribute("width",canvasContainerElt);
        var minY =window.renderer.getAttribute("y",canvasContainerElt);
        
        //calcul if the bubble should be display on  bottom
        bottom = ((y - bubble.size.height) < minY) ? true : false;
        
        //calcul if the bubble should be display on  left
        left = ((x  + bubble.size.width) > maxX) ? true : false;
        
    }        
    if (bottom) position.y = y + 10;    
    if (left) position.x = x - bubble.size.width - 10;    
    return position;
}


/** 
 * Method: addBubble
 * 
 *     Add the bubble to the canvas element
 *     
 * @param  evt      browser mouse event
 */
function addBubble(evt) {
    
    var position = calculBubblePosition(evt);
    var elt = OpenCharts.Event.element(evt); 
    
    elt.parentNode.appendChild(
        window.renderer.createBubble(
            elt, 
            position.x, 
            position.y, 
            bubble.size.width, 
            bubble.size.height, 
            {
                fillColor: "white",
                fillOpacity: 1, 
                strokeColor: renderer.getAttribute('strokeColor', elt),
                strokeOpacity: 1,
                strokeWidth: 2,
                strokeLinecap: "round",
                strokeDashstyle: "solid"
            },
            window.renderer.offset
        )
    );
}

/** 
 * Method: highlight
 * 
 *     Function executed on mouseover event on chart plot. This function add 
 *     a bubble to the canvas element for displaying coordinates and value of 
 *     the plot and change the style of the plot.
 *     
 * @param  evt      browser mouse event
 */
function highlight(evt) {
    
    var elt = OpenCharts.Event.element(evt);
    elt.style.zIndex = elt.style.zIndex - elt.style.zIndex; 
    window.renderer.setStyle(
        elt, 
        {
             strokeColor: renderer.getAttribute('strokeColor', elt),
             strokeOpacity: 1,
             strokeWidth: 6,
             strokeLinecap: "round",
             strokeDashstyle: "solid",
             cursor: "pointer"
        },
        { 
            isFilled:false,
            isStroked:true
        }, 
        new OpenCharts.Geometry.Surface()
    );
    
    //Add the bubble
    addBubble(evt);
    
    //Add line
//    if ( document.getElementById('canvasGrid') )
//        elt.parentNode.insertBefore(createLines(evt), document.getElementById('canvasGrid')); 

}

/** 
 * Method: highlight
 * 
 *     Function executed on mouseout event on chart plot. This function remove 
 *     the bubble of the canvas element. And set the default plot's style.
 *     
 * @param  evt      browser mouse event
 */
function unhighlight(evt) {
    var elt = OpenCharts.Event.element(evt);
    window.renderer.setStyle(elt, 
        {
             strokeColor: renderer.getAttribute('strokeColor', elt),
             strokeOpacity: 1,
             strokeWidth: 1,
             strokeLinecap: "round",
             strokeDashstyle: "solid",
             cursor: "pointer"
        },
        { 
            isFilled:false,
            isStroked:true
        }, 
        new OpenCharts.Geometry.Surface()
    );
        
    //Remove bubble
    if (document.getElementById('bubble')) {
        elt.parentNode.removeChild(document.getElementById('bubble'));
    }
//    //Remove lines
//    if (document.getElementById('cursorLines'))
//        elt.parentNode.removeChild(document.getElementById('cursorLines'));
//    
//    var attr = elt.getAttribute('default-stroke');
//    if (attr) elt.setAttribute('fill',elt.getAttribute('default-fill')); 
//    
//    attr = null;
//    attr = elt.getAttribute('default-stroke');
//    if (attr) elt.setAttribute('stroke', attr); 
//    
//    attr = null;
//    attr = elt.getAttribute('default-stroke-width');
//    if (attr)  elt.setAttribute('stroke-width', attr);    
}
        