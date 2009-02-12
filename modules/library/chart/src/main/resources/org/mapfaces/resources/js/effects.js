
var xmlnsSVG = "http://www.w3.org/2000/svg";
var bubbleSize = {
    width : 220,
    height : 100
};
var defaultColor = "green";
// definition de la methode
function addEvents (id) {
    
    var elt = document.getElementById(id);
    if (elt && elt.hasChildNodes() && elt.childNodes.length > 0) {
        var list = elt.childNodes;
        for(var i=1;i<list.length;i++){
            if (list[i].nodeName == "path"){ 
                var path = list[i];   
                path.addEventListener('mouseover',highlight,false);
                path.addEventListener('mouseout',unhighlight,false);
                path.addEventListener('click',addLines,false);
                //path.addEventListener('mousemove',moveText,false);
            }
        }
    }
}
function createRect(evt, position) {
    var elt = document.createElementNS(xmlnsSVG, 'rect');
    elt.setAttribute('width',bubbleSize.width); 
    elt.setAttribute('height',bubbleSize.height); 
    elt.setAttribute('x',position.x);
    elt.setAttribute('y',position.y);
    elt.setAttribute('rx',10);
    elt.setAttribute('ry',10);
    return elt;
}
function createPath(evt) {
    var elt = document.createElementNS(xmlnsSVG, 'path');
    elt.setAttribute('width','205'); 
    elt.setAttribute('height','100'); 
    elt.setAttribute('x',evt.clientX+10);
    elt.setAttribute('y',evt.clientY-10);
    return elt;
}
function createElement(type, attributes) {
    var elt = document.createElementNS(xmlnsSVG, type);
    for (var i in attributes) {
        elt.setAttribute(i,attributes[i]);
    }
    return elt;
}
function createLine(attributes) {
    return createElement('line', attributes);
}
function createVerticalLine(evt) {
    var canvasContainer = document.getElementById('canvasContainer');
    var attr = {
        x1:evt.clientX,
        x2:evt.clientX,
        y1: canvasContainer.getAttribute('y'),
        y2: parseFloat(canvasContainer.getAttribute('height'))+parseFloat(canvasContainer.getAttribute('y')),
        stroke: evt.target.getAttribute('default-stroke')
    }
    return createLine(attr);
}
function createHorizontalLine(evt) {
    var canvasContainer = document.getElementById('canvasContainer');
    var attr = {
        x1: canvasContainer.getAttribute('x'),
        x2: parseFloat(canvasContainer.getAttribute('width'))+ parseFloat(canvasContainer.getAttribute('x')),
        y1: evt.clientY,
        y2: evt.clientY,
        stroke: evt.target.getAttribute('default-stroke')
    }
    return createLine(attr);
}

function createLines(evt) {
     var attr = {
        'id': 'cursorLines',
        'stroke-width': 1,
        'stroke-linejoin': 'bevel',
        'stroke-linecap': 'butt',
        'stroke-dasharray': '2,2',
        'stroke-miterlimit': '0'
    }
    var g = createElement('g', attr);
    g.appendChild(createVerticalLine(evt));
    g.appendChild(createHorizontalLine(evt));
    return g;
}
function createText(string, x , y) {
    var elt = document.createElementNS(xmlnsSVG, 'text');
    elt.appendChild(document.createTextNode(string));    
    elt.setAttribute('x', x);
    elt.setAttribute('y', y);
    elt.setAttribute('fill','black'); 
//    if(elt.firstChild && elt.firstChild.data)
//        elt.firstChild.data = string;
//    else if(elt.firstChild && elt.firstChild.nodeValue)
//        elt.firstChild.nodeValue= string;
    return elt;
}
function calculPosition (evt) {
    //find the canvas container node
    var canvasContainerElt = document.getElementById('canvasContainer');      
    
    //mouse coordinates
    var x = evt.clientX;
    var y = evt.clientY;
    var bottom = false;
    var left = false;    
    
    //Default is on top-right;
    var position = { 
        x :  (x + 10), 
        y : (y - bubbleSize.height - 10) 
    }; 
    
    if (canvasContainerElt) {
        
        //canvas container coordinates/
        var minX = parseFloat(canvasContainerElt.getAttribute("x"));
        var maxX = minX + parseFloat(canvasContainerElt.getAttribute("width"));
        var minY = parseFloat(canvasContainerElt.getAttribute("y"));
        var maxY = minY + parseFloat(canvasContainerElt.getAttribute("height"));
        
        //calcul if the bubble shoulb be display on  bottom
        bottom = ((y - bubbleSize.height) < minY) ? true : false;
        
        //calcul if the bubble shoulb be display on  left
        left = ((x  + bubbleSize.width) > maxX) ? true : false;
        
    }    
    
    if (bottom) position.y = y + 10;
    
    if (left) position.x = x - bubbleSize.width - 10;
    
    return position;
}
function createBubble(evt) {
    var positionRect = calculPosition(evt);
    var g  = document.createElementNS(xmlnsSVG, 'g');
    g.appendChild(createRect(evt, positionRect));
    //g.appendChild(createPath(evt));
    g.appendChild(createText("Serie : "+evt.target.getAttribute('serie'), positionRect.x+10, positionRect.y+20));
    var date = new Date();
    date.setTime(parseFloat(evt.target.getAttribute('xValue')));
    g.appendChild(createText("   Date  : "+date.toGMTString(), positionRect.x+10, positionRect.y+40));
    g.appendChild(createText("   Value : "+evt.target.getAttribute('yValue'), positionRect.x+10, positionRect.y+60));
    g.setAttribute('id','bubble'); 
    g.setAttribute('fill','white'); 
    g.setAttribute('stroke',evt.target.getAttribute('default-stroke'));
    g.setAttribute('stroke-width','2');
    g.setAttribute('text-rendering','geometricPrecision');
    g.setAttribute('font-family',"Arial");
    g.setAttribute('font-size',"11");
    g.setAttribute('style','display:block;');  
    return g; 
}
function addBubble(evt) {
    evt.target.parentNode.parentNode.appendChild(createBubble(evt));
}
function addLines(evt) {
    removeLines(evt);
    evt.target.parentNode.insertBefore(createLines(evt), document.getElementById('canvasGrid'));
}
function removeBubble(evt) {
    if (document.getElementById('bubble'))
         evt.target.parentNode.parentNode.removeChild(document.getElementById('bubble'));
}
function removeLines(evt) {
    if (document.getElementById('cursorLines'))
        evt.target.parentNode.removeChild(document.getElementById('cursorLines'));
}
function highlight(evt) {
    
    if(!evt.target.getAttribute('fill') 
        || evt.target.getAttribute('fill') == "" )
        evt.target.setAttribute('default-fill',defaultColor);
    else evt.target.setAttribute('default-fill',evt.target.getAttribute('fill'));  
    evt.target.setAttribute('fill','yellow');  
    
    if(!evt.target.getAttribute('stroke') 
        || evt.target.getAttribute('stroke') == "" )
        evt.target.setAttribute('default-stroke',defaultColor);
    else evt.target.setAttribute('default-stroke',evt.target.getAttribute('stroke'));  
    evt.target.setAttribute('stroke','yellow'); 
    
    if(!evt.target.getAttribute('stroke-width') 
        || evt.target.getAttribute('stroke-width') == "" )
        evt.target.setAttribute('default-stroke-width','1');
    else evt.target.setAttribute('default-stroke-width',evt.target.getAttribute('stroke-width'));  
    evt.target.setAttribute('stroke-width','3'); 
    
    evt.target.setAttribute('cursor','pointer');   
    
    addBubble(evt);    
}
function unhighlight(evt) {
    removeBubble(evt);
    removeLines(evt);
    evt.target.setAttribute('fill',evt.target.getAttribute('default-fill')); 
    evt.target.setAttribute('stroke',evt.target.getAttribute('default-stroke')); 
    evt.target.setAttribute('stroke-width',evt.target.getAttribute('default-stroke-width'));    
}
        