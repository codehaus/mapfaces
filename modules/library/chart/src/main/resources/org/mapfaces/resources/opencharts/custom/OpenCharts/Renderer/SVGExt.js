/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/**
 * @requires OpenCharts/Renderer/Elements.js
 */

/**
 * Class: OpenCharts.Renderer.SVG
 * 
 * Inherits:
 *  - <OpenCharts.Renderer.Elements>
 */
OpenCharts.Renderer.SVG = OpenCharts.Class(OpenCharts.Renderer.SVG, {
   
    /**
     * Method: inValidRange
     * See #669 for more information
     *
     * Parameters:
     * x      - {Integer}
     * y      - {Integer}
     * xyOnly - {Boolean} whether or not to just check for x and y, which means
     *     to not take the current translation parameters into account if true.
     * 
     * Returns:
     * {Boolean} Whether or not the 'x' and 'y' coordinates are in the  
     *           valid range.
     */ 
    inValidRange: function(x, y, xyOnly) {
        //        var left = x + (xyOnly ? 0 : this.translationParameters.x);
        //        var top = y + (xyOnly ? 0 : this.translationParameters.y);
        //        return (left >= -this.MAX_PIXEL && left <= this.MAX_PIXEL &&
        //                top >= -this.MAX_PIXEL && top <= this.MAX_PIXEL);
        return true;
    },
    /***********************OPENCHARTS *********************/
    /**
     * APIMethod: pan
     * Allows user to pan by a value of screen pixels
     * 
     * Parameters:
     * dx - {Integer}
     * dy - {Integer}
     * options - {Object} Options to configure panning:
     *  - *animate* {Boolean} Use panTo instead of setCenter. Default is true.
     *  - *dragging* {Boolean} Call setCenter with dragging true.  Default is
     *    false.
     */
    pan: function(dx, dy, options) {   
        draggingElement = this.map.div;
        var p = document.documentElement.createSVGPoint();
        p.x = 0;
        p.y = 0;
        //            
        p = p.matrixTransform(OpenCharts.Util.getScreenCTM().inverse());
        p.x -= (dx - parseFloat(this.map.div.getAttribute("x")));
        p.y -= (dy - parseFloat(this.map.div.getAttribute("y")));
            
        // at this point, we need to check if the drag is constrained 
        // (look for drag:constrainLeft, constrainTop, etc on the draggingElement)
        //            var left = draggingElement.getAttributeNS(DRAGNS, "constraintLeft");
        //            var top = draggingElement.getAttributeNS(DRAGNS, "constraintTop");
        //            var right = draggingElement.getAttributeNS(DRAGNS, "constraintRight");
        //            var bottom = draggingElement.getAttributeNS(DRAGNS, "constraintBottom");
        //            if(left && p.x < left) { p.x = left; }
        //            else if(right && p.x > right) { p.x = right; }
        //            
        //            if(top && p.y < top) { p.y = top; }
        //            else if(bottom && p.y > bottom) { p.y = bottom; }
            
        draggingElement.setAttribute("x", p.x);
        draggingElement.setAttribute("y", p.y);
        draggingElement.setAttribute("transform", "translate(" + p.x + "," + p.y + ")");
    },    
    zoomTo: function(out, parameters) {
        var  parameters = {            
            'org.mapfaces.chart.CONTAINER_SIZE': this.map.div.getAttribute("width") + "," + this.map.div.getAttribute("height"),
            'org.mapfaces.chart.ZOOMIN': (!out), 
            'org.mapfaces.chart.ZOOMOUT': out 
        };
        this.map.sendAjaxRequest(parameters);
    },
    /** 
     * APIMethod: zoomToBox:
     * Zoom to the extent specify in pixel.
     */
    zoomToBox: function(bounds, out) {
        var  parameters = {            
            'org.mapfaces.chart.BOX': bounds.left+ "," +bounds.top+ "," +bounds.right+ "," +bounds.bottom,
            'org.mapfaces.chart.CONTAINER_SIZE': this.map.div.getAttribute("width") + "," + this.map.div.getAttribute("height"),
            'org.mapfaces.chart.ZOOMIN': (!out), 
            'org.mapfaces.chart.ZOOMOUT': out 
        };
        this.map.sendAjaxRequest(parameters);
    },
    /** 
     * APIMethod: zoomToPixel:
     * Zoom to the pixel.
     */
    zoomToPixel: function(pixel, out) {
        var  parameters = {                 
            'org.mapfaces.chart.PIXEL': pixel.x+ "," +pixel.y,
            'org.mapfaces.chart.CONTAINER_SIZE': this.map.div.getAttribute("width") + "," + this.map.div.getAttribute("height"),
            'org.mapfaces.chart.ZOOMIN': (!out), 
            'org.mapfaces.chart.ZOOMOUT': out 
        };
          
        this.map.sendAjaxRequest(parameters);
    }, 
    /** 
     * APIMethod: panToPixel:
     * Pan to the pixel.
     */
    panToPixel: function(pixel) {
        var  parameters = {                 
            'org.mapfaces.chart.OFFSET': this.map.canvasContainer.getAttribute("x")+ "," +this.map.canvasContainer.getAttribute("y"),
            'org.mapfaces.chart.TRANSLATE': this.map.div.getAttribute("x")+ "," +this.map.div.getAttribute("y"),
            'org.mapfaces.chart.CONTAINER_SIZE': this.map.canvasContainer.getAttribute("width") + "," + this.map.canvasContainer.getAttribute("height"),
            'org.mapfaces.chart.PAN': true
        };
        
        this.map.sendAjaxRequest(parameters);
    },
    
    /************************************************
     * DRAWING function
     * ***********************************************/
    /**
     * Method: drawRectangle
     * This method is only called by the renderer itself.
     * 
     * Parameters: 
     * node - {DOMElement}
     * geometry - {<OpenCharts.Geometry>}
     * 
     * Returns:
     * {DOMElement} or false if the renderer could not draw the rectangle
     */ 
    drawRectangle: function(node, geometry) {
        node.setAttributeNS(null, "x", geometry.x);
        node.setAttributeNS(null, "y", geometry.y);
        node.setAttributeNS(null, "width", geometry.width);
        node.setAttributeNS(null, "height", geometry.height);
        return node;
    },
    
    createText: function( text, x, y, attributes ) {
        var elt = this.nodeFactory("", "text");
        elt.appendChild(document.createTextNode(text));    
        elt.setAttribute('x', x);
        elt.setAttribute('y', y);
        for (var i in attributes) 
            elt.setAttribute(i,attributes[i]);
        return elt;
    },
    
    createBubble: function( elt, x, y, width, height, style) {
        var textAttributes = {fill: 'black'};
      
        //1: Create the Rectangle geometry
        var geometry = new OpenCharts.Geometry.Rectangle(x, y, width, height);
        //2: Draw the geometry
        var rect = this.drawRectangle(
                      this.setStyle(
                          this.nodeFactory("", this.getNodeType(geometry, null)),
                          style, 
                          {
                              isFilled:true,
                               isStroked:true
                          }, null
                      ),
                      geometry);

        //3: Set rounded corner    
        rect.setAttribute('rx',10);
        rect.setAttribute('ry',10);
        var g  =  this.nodeFactory("bubble", "g");
        g.appendChild(rect);
        g.appendChild(this.createText("Serie : "+elt.getAttribute('serie'), x+10, y+20 , textAttributes));
        var date = new Date();
        date.setTime(parseFloat(elt.getAttribute('xValue')));
        g.appendChild(this.createText("   Date  : "+date.toGMTString(), x+10, y+40, textAttributes));
        g.appendChild(this.createText("   Value : "+elt.getAttribute('yValue'), x+10, y+60, textAttributes));
        g.setAttribute('text-rendering','geometricPrecision');
        g.setAttribute('font-family',"Arial");
        g.setAttribute('font-size',"11px");
        g.setAttribute('style','display:block;');  
        return g;
    },
    getAttribute: function( attrName,  node) {
        if (attrName == "strokeColor") {
            return node.getAttribute("stroke");
        }
        return parseFloat(node.getAttribute(attrName));
    },
    addEvents: function(node, events) {
        if (events && node && (node.nodeName == "path" || node.nodeName == "circle")){             
            for (var i in events) {
                node.addEventListener(i, events[i],false);
            }
        }
    }
});
