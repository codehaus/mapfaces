/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/**
 * @requires OpenCharts/Renderer/Elements.js
 */

/**
 * Class: OpenCharts.Renderer.VML
 * 
 * Inherits:
 *  - <OpenCharts.Renderer.Elements>
 */
OpenCharts.Renderer.VML = OpenCharts.Class(OpenCharts.Renderer.VML, {
    resolution : 1,
    
    calculOffset : function(elt) {
         if (!elt.scrolls) {
            elt.scrolls = [];
            elt.scrolls[0] = (document.documentElement.scrollLeft
                         || document.body.scrollLeft);
            elt.scrolls[1] = (document.documentElement.scrollTop
                         || document.body.scrollTop);
        }

        if (!elt.lefttop) {
            elt.lefttop = [];
            elt.lefttop[0] = (document.documentElement.clientLeft || 0);
            elt.lefttop[1] = (document.documentElement.clientTop || 0);
        }
        
        if (!elt.offsets) {
            elt.offsets = OpenCharts.Util.pagePosition(elt);
            elt.offsets[0] += elt.scrolls[0];
            elt.offsets[1] += elt.scrolls[1];
        }
        return new OpenCharts.Pixel(elt.offsets[0],elt.offsets[1]); 
    },
    /**
     * Constructor: OpenCharts.Renderer.VML
     * Create a new VML renderer.
     *
     * Parameters:
     * containerID - {String} The id for the element that contains the renderer
     */
    initialize: function(containerID) {
        if (!this.supported()) { 
            return; 
        }
        OpenCharts.Renderer.Elements.prototype.initialize.apply(this,arguments);
        if ((typeof containerID) == "string")
            this.containerId = containerID;
        else 
            this.containerId = containerID.id;
        this.offset = null;
                    
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
        var p = new OpenCharts.Pixel((parseFloat(this.map.div.style.left) - dx),
                                      (parseFloat(this.map.div.style.top) - dy));
            
        this.map.div.style.left = p.x;
        this.map.div.style.top = p.y;
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
        var resolution = this.getResolution();
        evt.xy.x -= this.offset.x;
        evt.xy.y -= this.offset.y;
//          DEBUG IE
//          var str = "wheelChange :\n";
//          str += "evt.xy = "+ evt.xy+"\n";
//          str += "evt.xy.x,evt.xy.y = "+evt.xy.x+", "+evt.xy.y+"\n";
//          str += "deltaZ = "+deltaZ+"\n";
//          alert(str);
        this.map.zoomToPixel(evt.xy, (deltaZ == -1));
    }, 
    
    /** 
     * APIMethod: zoomToBox:
     * Zoom to the extent specify in pixel.
     */
    zoomToBox: function(bounds, out) {
          bounds.left -= this.offset.x;
          bounds.top -= this.offset.y;
          bounds.right -= this.offset.x;
          bounds.bottom -= this.offset.y;
//          DEBUG IE
//          var str = "zoomToBox :\n";
//          str += "bounds = "+ bounds+"\n";
//          str += "bounds.left,bounds.top,bounds.right,bounds.bottom = "+bounds.left+ "," +bounds.top+ "," +bounds.right+ "," +bounds.bottom+"\n";
//          str += "this.map.div = "+this.map.div+"\n";
//          str += "parseFloat(this.map.div.style.width) = "+parseFloat(this.map.div.style.width)+"\n";
//          str += "parseFloat(this.map.div.style.height) = "+parseFloat(this.map.div.style.height)+"\n";
//          alert(str);
          
            var  parameters = {            
                'org.mapfaces.chart.BOX': bounds.left+ "," +bounds.top+ "," +bounds.right+ "," +bounds.bottom,
                'org.mapfaces.chart.CONTAINER_SIZE': parseFloat(this.map.div.style.width) + "," + parseFloat(this.map.div.style.height),
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
//          DEBUG IE
//          var str = "zoomToPixel : \n";
//          str += "pixel = "+ pixel+"\n";
//          str += "pixel.x,pixel.y = "+pixel.x+ "," +pixel.y+"\n";
//          str += "this.map.div = "+this.map.div+"\n";
//          str += "parseFloat(this.map.div.style.width) = "+parseFloat(this.map.div.style.width)+"\n";
//          str += "parseFloat(this.map.div.style.height) = "+parseFloat(this.map.div.style.height)+"\n";
//          alert(str);
            var  parameters = {                 
                'org.mapfaces.chart.PIXEL': pixel.x+ "," +pixel.y,
                'org.mapfaces.chart.CONTAINER_SIZE': parseFloat(this.map.div.style.width) + "," + parseFloat(this.map.div.style.height),
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
//          DEBUG IE
//          var str = "panToPixel : \n";
//          str += "this.canvasContainer = "+this.canvasContainer+"\n";
//          str += "parseFloat(this.map.canvasContainer.style.left) = "+parseFloat(this.map.canvasContainer.style.left)+"\n";
//          str += "parseFloat(this.map.canvasContainer.style.top) = "+parseFloat(this.map.canvasContainer.style.top)+"\n";
//          str += "parseFloat(this.map.canvasContainer.style.width) = "+parseFloat(this.map.canvasContainer.style.width)+"\n";
//          str += "parseFloat(this.map.canvasContainer.style.height) = "+parseFloat(this.map.canvasContainer.style.height)+"\n";
//          str += "this.div = "+this.div+"\n";
//          str += "parseFloat(this.map.div.style.left) = "+parseFloat(this.map.div.style.left)+"\n";
//          str += "parseFloat(this.map.div.style.top) = "+parseFloat(this.map.div.style.top)+"\n";
//          str += "parseFloat(this.map.canvasContainer.style.width) = "+parseFloat(this.map.canvasContainer.style.width)+"\n";
//          str += "parseFloat(this.map.canvasContainer.style.height) = "+parseFloat(this.map.canvasContainer.style.height)+"\n";
//          alert(str);
            var  parameters = {                 
                'org.mapfaces.chart.OFFSET': parseFloat(this.map.canvasContainer.style.left)+ "," +parseFloat(this.map.canvasContainer.style.top),
                'org.mapfaces.chart.TRANSLATE': parseFloat(this.map.div.style.left)+ "," + parseFloat(this.map.div.style.top),
                'org.mapfaces.chart.CONTAINER_SIZE': parseFloat(this.map.canvasContainer.style.width)+ "," +parseFloat(this.map.canvasContainer.style.height),
                'org.mapfaces.chart.PAN': true
            };
              
            this.map.sendAjaxRequest(parameters);
    },
    
    /**
     * Method: getNodeType
     * Get the node type for a geometry and style
     *
     * Parameters:
     * geometry - {<OpenCharts.Geometry>}
     * style - {Object}
     *
     * Returns:
     * {String} The corresponding node type for the specified geometry
     */
    getNodeType: function(geometry, style) {
        var nodeType = null;
        switch (geometry.CLASS_NAME) {
            case "OpenCharts.Geometry.Point":
                if (style.externalGraphic) {
                    nodeType = "v:rect";
                } else if (this.isComplexSymbol(style.graphicName)) {
                    nodeType = "v:shape";
                } else {
                    nodeType = "v:oval";
                }
                break;
            case "OpenCharts.Geometry.Rectangle":
                nodeType = "v:rect";
                break;
            case "OpenCharts.Geometry.LineString":
            case "OpenCharts.Geometry.LinearRing":
            case "OpenCharts.Geometry.Polygon":
            case "OpenCharts.Geometry.Curve":
            case "OpenCharts.Geometry.Surface":
                nodeType = "v:shape";
                break;
            default:
                break;
        }
        return nodeType;
    },

    /**
     * Method: setStyle
     * Use to set all the style attributes to a VML node.
     *
     * Parameters:
     * node - {DOMElement} An VML element to decorate
     * style - {Object}
     * options - {Object} Currently supported options include 
     *                              'isFilled' {Boolean} and
     *                              'isStroked' {Boolean}
     * geometry - {<OpenCharts.Geometry>}
     */
    setStyle: function(node, style, options, geometry) {
        style = style  || node._style;
        options = options || node._options;
        var widthFactor = 1;
        
        if (node._geometryClass == "OpenCharts.Geometry.Point") {
            if (style.externalGraphic) {
                var width = style.graphicWidth || style.graphicHeight;
                var height = style.graphicHeight || style.graphicWidth;
                width = width ? width : style.pointRadius*2;
                height = height ? height : style.pointRadius*2;

                var resolution = this.getResolution();
                var xOffset = (style.graphicXOffset != undefined) ?
                    style.graphicXOffset : -(0.5 * width);
                var yOffset = (style.graphicYOffset != undefined) ?
                    style.graphicYOffset : -(0.5 * height);
                
                node.style.left = ((geometry.x/resolution - this.offset.x)+xOffset).toFixed();
                node.style.top = ((geometry.y/resolution - this.offset.y)-(yOffset+height)).toFixed();
                node.style.width = width + "px";
                node.style.height = height + "px";
                node.style.flip = "y";
                
                // modify style/options for fill and stroke styling below
                style.fillColor = "none";
                options.isStroked = false;
            } else if (this.isComplexSymbol(style.graphicName)) {
                var cache = this.importSymbol(style.graphicName);
                var symbolExtent = cache.extent;
                var width = symbolExtent.getWidth();
                var height = symbolExtent.getHeight();
                node.setAttribute("path", cache.path);
                node.setAttribute("coordorigin", symbolExtent.left + "," +
                                                                symbolExtent.bottom);
                node.setAttribute("coordsize", width + "," + height);
                node.style.left = symbolExtent.left + "px";
                node.style.top = symbolExtent.bottom + "px";
                node.style.width = width + "px";
                node.style.height = height + "px";
        
                this.drawCircle(node, geometry, style.pointRadius);
                node.style.flip = "y";
            } else {
                this.drawCircle(node, geometry, style.pointRadius);
            }
        }

        // fill 
        if (options.isFilled) { 
            node.setAttribute("fillcolor", style.fillColor); 
        } else { 
            node.setAttribute("filled", "false"); 
        }
        var fills = node.getElementsByTagName("fill");
        var fill = (fills.length == 0) ? null : fills[0];
        if (!options.isFilled) {
            if (fill) {
                node.removeChild(fill);
            }
        } else {
            if (!fill) {
                fill = this.createNode('v:fill', node.id + "_fill");
            }
            fill.setAttribute("opacity", style.fillOpacity);

            if (node._geometryClass == "OpenCharts.Geometry.Point" &&
                    style.externalGraphic) {

                // override fillOpacity
                if (style.graphicOpacity) {
                    fill.setAttribute("opacity", style.graphicOpacity);
                }
                
                fill.setAttribute("src", style.externalGraphic);
                fill.setAttribute("type", "frame");
                
                if (!(style.graphicWidth && style.graphicHeight)) {
                  fill.aspect = "atmost";
                }                
            }
            if (fill.parentNode != node) {
                node.appendChild(fill);
            }
        }

        // additional rendering for rotated graphics or symbols
        if (typeof style.rotation != "undefined") {
            if (style.externalGraphic) {
                this.graphicRotate(node, xOffset, yOffset);
                // make the fill fully transparent, because we now have
                // the graphic as imagedata element. We cannot just remove
                // the fill, because this is part of the hack described
                // in graphicRotate
                fill.setAttribute("opacity", 0);
            } else {
                node.style.rotation = style.rotation;
            }
        }

        // stroke 
        if (options.isStroked) { 
            node.setAttribute("strokecolor", style.strokeColor); 
            node.setAttribute("strokeweight", style.strokeWidth + "px"); 
        } else { 
            node.setAttribute("stroked", "false"); 
        }
        var strokes = node.getElementsByTagName("stroke");
        var stroke = (strokes.length == 0) ? null : strokes[0];
        if (!options.isStroked) {
            if (stroke) {
                node.removeChild(stroke);
            }
        } else {
            if (!stroke) {
                stroke = this.createNode('v:stroke', node.id + "_stroke");
                node.appendChild(stroke);
            }
            stroke.setAttribute("opacity", style.strokeOpacity);
            stroke.setAttribute("endcap", !style.strokeLinecap || style.strokeLinecap == 'butt' ? 'flat' : style.strokeLinecap);
            stroke.setAttribute("dashstyle", this.dashStyle(style));
        }
        
        if (style.cursor != "inherit" && style.cursor != null) {
            node.style.cursor = style.cursor;
        }
        return node;
    },
    setAttr: function( node, attr) {
        for (var i in attr)
           node.setAttribute( i, attr[i]);
        return node;
    },
    createNodeWithAttr: function( type, attr){
        return this.setAttr( this.nodeFactory("", type), attr);
    },
    createRoundrectNode: function(attr) {
       return this.createNodeWithAttr( 'v:roundrect', attr);
    },
    createRoundrect: function(attr, style, options) {
       return this.setStyle(
                  this.createRoundrectNode(attr),
                  style, 
                  options, 
                  null
              );
    },
    createShapeNode: function(attr) {
       return this.createNodeWithAttr( 'v:shape', attr);
    },
    createShape: function(attr, style, options) {
       return this.setStyle(
                  this.createShapeNode(attr),
                  style, 
                  options, 
                  null
              );
    },
    createPathNode: function(attr) {
       return this.createNodeWithAttr( 'v:path', attr);
    },
    
    createTextpathNode: function(attr) {
       return this.createNodeWithAttr( 'v:textpath', attr);
    },
    createTextboxNode: function(attr) {
       return this.createNodeWithAttr( 'v:textbox', attr);
    },
   
    createTextbox: function(html) {
        var elt = this.createTextboxNode();
        elt.innerHTML = html;
        return elt;
    },
    
    createBubble: function( elt, x, y, width, height, style, offset) {
        if (offset) {
           x+=offset.x;
           y+=offset.y;
        }
      
      //1: Create the Rectangle geometry
      var geometry = new OpenCharts.Geometry.Rectangle(x, y, width, height);      
      var rect = 
          this.drawRectangle(
              this.createRoundrect(
                  {
                    id:"bubble",
                    arcsize:0.2
                  },
                  style,
                  {
                    isFilled:true,
                    isStroked:true
                  }
              ),
              geometry
          );
              
      var date = new Date();
      date.setTime(parseFloat(elt.getAttribute('xValue')));   
      
      var htmlContent = "Serie : " + elt.getAttribute('serie') + "<br/>" +
                        "    Date : " + date + "<br/>" +
                        "    Value : " + elt.getAttribute('yValue') + "<br/>";
                        
      rect.appendChild(this.createTextbox(htmlContent));   
      rect.style.fontSize = "11px";  
      rect.style.fontFamily = "Arial";
      
      return rect;
    },

    getAttribute: function( attrName,  node) {
        if (attrName == "x")
            return parseFloat(node.style.left);
        else if (attrName == "y")
            return parseFloat(node.style.top);
        else if (attrName == "width")
            return parseFloat(node.style.width);
        else if (attrName == "height")
            return parseFloat(node.style.height);
        else if (attrName == "strokeColor") 
            return node.getAttribute("strokecolor");        
        else
            return "none";
    },
    addEvents: function(node, events) {
        if (events && node && (node.nodeName == "shape" || node.nodeName == "oval")){             
            for (var i in events) {
                node.attachEvent('on' +i, events[i],false);
            }
        }
    }
});
