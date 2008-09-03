/**
 * JavaScript slider implementation.
 */
function JSSlider()
{
  this._id = undefined;
  this._horizontalLayout = undefined; // boolean
  this._startPercent = undefined; // 0 to 100
  this._endPercent = undefined; // 0 to 100 or undefined
  this._rangeSlider = undefined; // boolean
  this._numTicks = undefined; // non-negative integer
  this._widgetWidth = undefined; // pixels
  this._widgetHeight = undefined; // pixels
  this._customImage = undefined; // will be stretched to fill ticks area
  this._changeListener = undefined; // the name of the static function called when marker positions change
  this._trackHidden = false; // if hidden, the ticks and image will consume the full space
  
  var _imagelocation = "resource.jsf?r=/org/mapfaces/resources/timeline/slider/js/sliderimages";   // set image location
  
  this._MaxVal = 100;
  this._spacerImage = _imagelocation + "/spacer.gif";
  this._ticksThickness = 4; // pixels
  this._ticksMargin = 3; // pixels
  this._tickColor = "#B7B6B3"; // style color
  this._widgetMargin = 3; // pixels
  this._ticksBeforeTrack = true; // boolean // TODO test this for custom images
  this._horizontalMarkerImage = _imagelocation + "/slider-marker-h-point.gif";
  this._horizontalMarkerWidth = 11; // pixels
  this._horizontalMarkerHeight = 12; // pixels
  this._verticalMarkerImage = _imagelocation + "/slider-marker-v-point.gif";
  this._verticalMarkerWidth = 12; // pixels
  this._verticalMarkerHeight = 11; // pixels
  this._trackImageDimension = 6; // pixels (widths and heights, horiz. and vert.)
  this._horizontalTrackStartImage = _imagelocation + "/slider-track-h-start.gif";
  this._horizontalTrackMidImage = _imagelocation + "/slider-track-h-mid.gif";
  this._horizontalTrackEndImage = _imagelocation + "/slider-track-h-end.gif";
  this._horizontalTrackRangeImage = _imagelocation + "/slider-track-h-range.gif";
  this._verticalTrackStartImage = _imagelocation + "/slider-track-v-start.gif";
  this._verticalTrackMidImage = _imagelocation + "/slider-track-v-mid.gif";
  this._verticalTrackEndImage = _imagelocation + "/slider-track-v-end.gif";
  this._verticalTrackRangeImage = _imagelocation + "/Slider/slider-track-v-range.gif";
  
  // event variable storage
  this._dragInProgress = false;
  this._dragX = undefined;
  this._dragY = undefined;
  this._dragLeft = undefined;
  this._dragTop = undefined;
  this._dragElement = undefined;
  this._oldOnmousemove = undefined;
  this._oldOnmouseup = undefined;
}

/**
 * Renders a slider.
 * @param id the unique ID used to identify this widget
 * @param horizontalLayout true if a horizontal slider, false if a vertical slider
 * @param numTicks a non-negative integer representing the number of tick marks that should be displayed
 * @param widgetLength the number of pixels the widget consumes (slightly larger than the actual slider track size to account for marker margins)
 * @param startPercent a defined number between 0 and 100
 * @param endPercent either a defined number between startPercent and 100 or undefined if this isn't a range slider
 * @param customImage optional URL to an image that will serve as a background for the tick marks (tick marks are not required for this to render)
 * @param changeListener the name of the static function called when marker positions change (first parameter is a 0 to 100 value for the start marker, second parameter is undefined or 0 to 100 value for the end marker)
 * @param trackHidden if hidden, the ticks and image will consume the full space (defaults to false)
 */
JSSlider.getInstance = function(id, horizontalLayout, numTicks, widgetLength, startPercent, endPercent, customImage, changeListener, trackHidden)
{
  var newPeer = new JSSlider();
  newPeer._id = id;
  newPeer._horizontalLayout = horizontalLayout;
  newPeer._numTicks = numTicks;
  newPeer._startPercent = startPercent;
  newPeer._endPercent = endPercent;
  newPeer._rangeSlider = (endPercent != null);
  newPeer._customImage = customImage;
  newPeer._changeListener = changeListener;
  newPeer._trackHidden = trackHidden;

  if (newPeer._rangeSlider && (endPercent < startPercent) )
  {
    newPeer._endPercent = startPercent;
  }
  
  newPeer._initializeWidgetDimensions(widgetLength);
  
  window[id] = newPeer; // register the object on the window for internal event access
  return newPeer;
}

JSSlider.prototype.render = function()
{
  var sb = new DhtmlStringBuffer();
  this._appendTickMarks(sb);
  if (!this._trackHidden)
  {
    this._appendTrackOutline(sb);
  }
  this._appendMarker(sb, this._startPercent, this._id + JSSlider._ID_START_MARKER);
  if (this._endPercent)
  {
    this._appendMarker(sb, this._endPercent, this._id + JSSlider._ID_END_MARKER);
  }
  
  var rootElement = document.createElement("div");
  rootElement.id = this._id + JSSlider._ID_WIDGET;
  rootElement.style.position = "relative";
  rootElement.style.width = this._widgetWidth + "px";
  rootElement.style.height = this._widgetHeight + "px";

  rootElement.innerHTML = sb.toString();
  return rootElement;
}

JSSlider.prototype.destroy = function()
{
  window[this._id] = undefined; // unregister the object from the window (was used for internal event access)
  var element = document.getElementById(this._id + JSSlider._ID_WIDGET);
  if (element != null)
  {
    element.style.display = "none";
    var parent = element.parentNode;
    if (parent != null)
    {
      parent.removeChild(element);
    }
  }
}

/**
 * Changes the marker position(s).
 * @param newStartPercent a defined number between 0 and 100
 * @param newEndPercent either a defined number between startPercent and 100 or undefined if this isn't a range slider
 * @param broadcastChange true if the change should be broacasted to the listener or not
 */
JSSlider.prototype.setMarkerPercent = function(newStartPercent, newEndPercent, broadcastChange)
{
  var startPercent = newStartPercent;
  var endPercent = newEndPercent;
  if (this._rangeSlider && (endPercent != null) && (endPercent < startPercent) )
  {
    startPercent = newEndPercent;
    endPercent = newStartPercent;
  }
  
  // move the start marker
  this._startPercent = startPercent;
  var top = this._getMarkerTop(this._startPercent);
  var left = this._getMarkerLeft(this._startPercent);
  var startMarker =
    document.getElementById(this._id + JSSlider._ID_START_MARKER);
  startMarker.style.top = top + "px";
  startMarker.style.left = left + "px";
  
  if (this._rangeSlider && (endPercent != null) )
  {
    // move the end marker
    this._endPercent = endPercent;
    top = this._getMarkerTop(this._endPercent);
    left = this._getMarkerLeft(this._endPercent);
    var endMarker =
      document.getElementById(this._id + JSSlider._ID_END_MARKER);
    endMarker.style.top = top + "px";
    endMarker.style.left = left + "px";
  }
  
  if (this._rangeSlider)
  {
    // update the range bar
    this._repositionRangeBar();
  }
  
  if (broadcastChange)
  {
    this._broadcastPositionChange();
  }
}

JSSlider.prototype.markerKeyDown = function(element, event)
{
  var trapEvent = false;
  var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
  var markerPercentSmaller, markerPercentLarger;
  
  var pixelPercentage = 1; // the number of percentage points a pixel occupies
  var startMarkerInFocus = true;
  if (this._rangeSlider)
  {
    if (element.id.indexOf(JSSlider._ID_START_MARKER) == -1)
    {
      startMarkerInFocus = false;
    }
  }
  if (this._horizontalLayout)
  {
    if (document.documentElement.dir == "rtl")
    {
      markerPercentSmaller = JSSlider._KEY_CODE_ARROW_RIGHT;
      markerPercentLarger = JSSlider._KEY_CODE_ARROW_LEFT;
    }
    else
    {
      markerPercentSmaller = JSSlider._KEY_CODE_ARROW_LEFT;
      markerPercentLarger = JSSlider._KEY_CODE_ARROW_RIGHT;
    }
  }
  else
  {
    markerPercentSmaller = JSSlider._KEY_CODE_ARROW_DOWN;
    markerPercentLarger = JSSlider._KEY_CODE_ARROW_UP;
  }
  switch (keyCode)
  {
    case markerPercentSmaller:
    {
      var currentStart = this._startPercent;
      var currentEnd = this._endPercent;
      var desiredStart = currentStart;
      var desiredEnd = currentEnd;
      if (startMarkerInFocus || !this._rangeSlider)
      {
        desiredStart = Math.max(0, desiredStart - pixelPercentage);
      }
      else
      {
        if (desiredEnd - pixelPercentage < desiredStart)
        {
          desiredStart = Math.max(0, desiredStart - pixelPercentage);
          document.getElementById(
            this._id + JSSlider._ID_START_MARKER).focus(); // swap focus
        }
        else
        {
          desiredEnd = Math.max(0, desiredEnd - pixelPercentage);
        }
      }
      this.setMarkerPercent(desiredStart, desiredEnd, true);
      trapEvent = true;
      break;
    }
    case markerPercentLarger:
    {
      var currentStart = this._startPercent;
      var currentEnd = this._endPercent;
      var desiredStart = currentStart;
      var desiredEnd = currentEnd;
      if (startMarkerInFocus || !this._rangeSlider)
      {
        if (this._rangeSlider && (desiredStart + pixelPercentage > desiredEnd) )
        {
          desiredEnd = Math.min(100, desiredEnd + pixelPercentage);
          document.getElementById(
            this._id + JSSlider._ID_END_MARKER).focus(); // swap focus
        }
        else
        {
          desiredStart = Math.min(100, desiredStart + pixelPercentage);
        }
      }
      else
      {
        desiredEnd = Math.min(100, desiredEnd + pixelPercentage);
      }
      this.setMarkerPercent(desiredStart, desiredEnd, true);
      trapEvent = true;
      break;
    }
  }
  if (trapEvent)
  {
    if (event.cancelable) 
    {
      event.stopPropagation();
      event.preventDefault();
    }
    return false;
  }
  return true;
}

JSSlider.prototype.markerDragged = function(element, event)
{
  this._dragInProgress = true;
  this._dragX = event.clientX;
  this._dragY = event.clientY;
  this._dragLeft = parseInt(element.style.left);
  this._dragTop = parseInt(element.style.top);
  this._dragElement = element;
  element.focus(); // so further keyboard navigation is allowed
  if (document.all)
  {
    element.setCapture();
  }
  else
  {
    JSSlider.prototype.currentInstance = this;
    window.sliderEventId = this._id;
    window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);
    this._oldOnmousemove = document["onmousemove"];
    document["onmousemove"] = this.markerMoved;
    this._oldOnmouseup = document["onmouseup"];
    document["onmouseup"] = this.markerReleased;
    if (event.cancelable)
    {
      // or else the browser thinks you are dragging links or images around
      event.stopPropagation();
      event.preventDefault();
    }
  }
  return false;
}

JSSlider.prototype.markerMoved = function(event)
{
  var peer = this;
  if (!document.all)
  {
    peer = JSSlider.prototype.currentInstance;
  }
  var element = peer._dragElement;
  if (peer._dragInProgress)
  {
    if (peer._horizontalLayout)
    {
      element.style.left = peer._ensureMarkerLeftInRange(
        peer._dragLeft + event.clientX - peer._dragX) + "px";
    }
    else
    {
      element.style.top = peer._ensureMarkerTopInRange(
        peer._dragTop + event.clientY - peer._dragY) + "px";
    }
    if (event.cancelable)
    {
      // or else the browser thinks you are dragging links or images around
      event.stopPropagation();
      event.preventDefault();
    }
    peer._recalculatePercentages();
    peer._broadcastPositionChange();
    return false;
  }
}

JSSlider.prototype.markerReleased = function(event)
{
  var peer = this;
  if (!document.all)
  {
    peer = JSSlider.prototype.currentInstance;
  }
  if (document.all)
  {
    peer._dragElement.releaseCapture();
  }
  else
  {
    window.releaseEvents(Event.MOUSEMOVE | Event.MOUSEUP);
    document["onmousemove"] = peer._oldOnmousemove;
    document["onmouseup"] = peer._oldOnmouseup;
  }
  var dragElementID = peer._dragElement.id;
  peer._dragElement = undefined;
  peer._dragX = undefined;
  peer._dragY = undefined;
  peer._dragLeft = undefined;
  peer._dragTop = undefined;
  if (!document.all)
  {
    JSSlider.prototype.currentInstance = false;
  }
  peer._dragInProgress = false;
  if (event.cancelable)
  {
    // or else the browser thinks you are dragging links or images around
    event.stopPropagation();
    event.preventDefault();
  }
  peer._correctMarkerOrdering(dragElementID);
}

JSSlider.prototype._correctMarkerOrdering = function(dragElementID)
{
  if (this._rangeSlider)
  {
    var startMarkerID = this._id + JSSlider._ID_START_MARKER;
    var endMarkerID = this._id + JSSlider._ID_END_MARKER;
    var startMarker = document.getElementById(startMarkerID);
    var endMarker = document.getElementById(endMarkerID);
    var markersReversed = false;
    if (this._horizontalLayout)
    {
      var startLeft = parseInt(startMarker.style.left);
      var endLeft = parseInt(endMarker.style.left);
      if (endLeft < startLeft)
      {
        markersReversed = true;
      }
    }
    else
    {
      var startTop = parseInt(startMarker.style.top);
      var endTop = parseInt(endMarker.style.top);
      if (endTop > startTop) // remember that zero is at the bottom
      {
        markersReversed = true;
      }
    }
    
    if (markersReversed)
    {
      // correct the tab order of the markers in case they are reversed
      this.setMarkerPercent(this._startPercent, this._endPercent, false);
      
      // put the correct marker in focus
      if (dragElementID == startMarkerID)
      {
        endMarker.focus();
      }
      else
      {
        startMarker.focus();
      }
    }
  }
}

JSSlider.prototype._recalculatePercentages = function()
{
  var min, max, length, newStartPercent0To100, newEndPercent0To100;
  if (this._horizontalLayout)
  {
    min = this._widgetMargin + this._trackImageDimension;
    max = this._widgetWidth - min;
    length = max - min;
    newStartPercent0To100 = ( parseInt(document.getElementById(this._id +
      JSSlider._ID_START_MARKER).style.left) - min +
      Math.floor(0.5*this._horizontalMarkerWidth) ) * 100.0 / length;
    if (this._rangeSlider)
    {
      newEndPercent0To100 = ( parseInt(document.getElementById(this._id +
        JSSlider._ID_END_MARKER).style.left) - min +
        Math.floor(0.5*this._horizontalMarkerWidth) ) * 100.0 / length;
    }
  }
  else
  {
    min = this._widgetMargin + this._trackImageDimension;
    max = this._widgetHeight - min;
    length = max - min;
    newStartPercent0To100 = 100.0 - 100.0 * ( parseInt(
      document.getElementById(this._id +
      JSSlider._ID_START_MARKER).style.top) - min +
      Math.floor(0.5*this._verticalMarkerHeight) ) / length;
    if (this._rangeSlider)
    {
      newEndPercent0To100 = 100.0 - 100.0 * ( parseInt(
        document.getElementById(this._id +
        JSSlider._ID_END_MARKER).style.top) - min +
        Math.floor(0.5*this._verticalMarkerHeight) ) / length;
    }
  }
  
  // respect that the start and end markers might cross over in updating the range track
  if (this._rangeSlider)
  {
    if (newStartPercent0To100 > newEndPercent0To100)
    {
      var swapTemp = newStartPercent0To100;
      newStartPercent0To100 = newEndPercent0To100;
      newEndPercent0To100 = swapTemp;
    }
  }
  
  this._startPercent = newStartPercent0To100;
  this._endPercent = newEndPercent0To100;
  this._repositionRangeBar();
}

JSSlider.prototype._repositionRangeBar = function()
{
  if (this._rangeSlider)
  {
    var rangeBar = document.getElementById(
      this._id + JSSlider._ID_RANGE_BAR);
    var startAndSize = this._getRangeBarStartAndSize();
    if (this._horizontalLayout)
    {
      rangeBar.style.left = startAndSize["start"] + "px";
      rangeBar.style.width = startAndSize["size"] + "px";
    }
    else
    {
      rangeBar.style.top = startAndSize["start"] + "px";
      rangeBar.style.height = startAndSize["size"] + "px";
    }
  }
}

JSSlider.prototype._broadcastPositionChange = function()
{
  if (this._changeListener)
  {
    try
    {
      eval(this._changeListener + "(" + this._startPercent + "," + this._endPercent + ")", 1);

     }
    catch (problem)
    {
      //TODO log problems
    }
  }
}

JSSlider.prototype._initializeWidgetDimensions = function(widgetLength)
{
  var widgetThickness = 2*this._widgetMargin + this._trackImageDimension;
  if ( (this._numTicks > 0) || (this._customImage != null) )
  {
    widgetThickness += this._ticksThickness + this._ticksMargin;
  }
  
  if (this._horizontalLayout)
  {
    this._widgetWidth = widgetLength;
    this._widgetHeight = widgetThickness;
  }
  else // vertical layout
  {
    this._widgetWidth = widgetThickness;
    this._widgetHeight = widgetLength;
  }
}

JSSlider.prototype._getMinimumMarkerHorizontalLeft = function()
{
  var minimumLeft = this._widgetMargin + this._trackImageDimension -
    Math.floor(0.5*this._horizontalMarkerWidth);
  return minimumLeft;
}

JSSlider.prototype._getMaximumMarkerHorizontalLeft = function()
{
  var maximumLeft = this._widgetWidth - this._widgetMargin -
    this._trackImageDimension - Math.floor(0.5*this._horizontalMarkerWidth);
  return maximumLeft;
}

JSSlider.prototype._getMinimumMarkerVerticalTop = function()
{
  var minimumTop = this._widgetMargin + this._trackImageDimension -
    Math.floor(0.5*this._verticalMarkerHeight);
  return minimumTop;
}

JSSlider.prototype._getMaximumMarkerVerticalTop = function()
{
  var maximumTop = this._widgetHeight - this._widgetMargin -
    this._trackImageDimension - Math.floor(0.5*this._verticalMarkerHeight);
  return maximumTop;
}

/**
 * Only call this method if in a horizontal layout.
 */
JSSlider.prototype._ensureMarkerLeftInRange = function(desiredLeft)
{
  var minimumLeft = this._getMinimumMarkerHorizontalLeft();
  if (desiredLeft < minimumLeft)
  {
    return minimumLeft;
  }
  
  var maximumLeft = this._getMaximumMarkerHorizontalLeft();
  if (desiredLeft > maximumLeft)
  {
    return maximumLeft;
  }
  
  return desiredLeft;
}

/**
 * Only call this method if in a vertical layout.
 */
JSSlider.prototype._ensureMarkerTopInRange = function(desiredTop)
{
  var minimumTop = this._getMinimumMarkerVerticalTop();
  if (desiredTop < minimumTop)
  {
    return minimumTop;
  }
  
  var maximumTop = this._getMaximumMarkerVerticalTop();
  if (desiredTop > maximumTop)
  {
    return maximumTop;
  }
  
  return desiredTop;
}

JSSlider.prototype._getMarkerLeft = function(percent0To100)
{
  if (this._horizontalLayout) // variable
  {
    var min = this._widgetMargin + this._trackImageDimension;
    var max = this._widgetWidth - min;
    var length = max - min;
    var position = min + length*percent0To100/100.0 -
      Math.floor(0.5*this._horizontalMarkerWidth);
    return position;
  }
  else // constant
  {
    var effectiveTickThickness = 0;
    if ( (this._numTicks > 0) || (this._customImage != null) )
    {
      effectiveTickThickness = this._ticksThickness + this._ticksMargin;
    }
    var trackInset; // the track is the center line along the track
    if (this._ticksBeforeTrack)
    {
      trackInset = this._widgetMargin + effectiveTickThickness +
                   0.5*this._trackImageDimension;
    }
    else
    {
      trackInset = this._widgetMargin + 0.5*this._trackImageDimension;
    }
    return trackInset - Math.floor(0.5*this._verticalMarkerWidth);
  }
}

JSSlider.prototype._getMarkerTop = function(percent0To100)
{
  if (this._horizontalLayout) // constant
  {
    var effectiveTickThickness = 0;
    if ( (this._numTicks > 0) || (this._customImage != null) )
    {
      effectiveTickThickness = this._ticksThickness + this._ticksMargin;
    }
    var trackInset; // the track is the center line along the track
    if (this._ticksBeforeTrack)
    {
      trackInset = this._widgetMargin + effectiveTickThickness +
                   0.5*this._trackImageDimension;
    }
    else
    {
      trackInset = this._widgetMargin + 0.5*this._trackImageDimension;
    }
    return trackInset - Math.floor(0.5*this._horizontalMarkerHeight);
  }
  else // variable
  {
    var min = this._widgetMargin + this._trackImageDimension;
    var max = this._widgetHeight - min;
    var length = max - min;
    var position = min + length*(100.0-percent0To100)/100.0 -
      Math.floor(0.5*this._verticalMarkerHeight);
    return position;
  }
}

JSSlider.prototype._getRangeBarStartAndSize = function()
{
  var start, size;
  if (this._horizontalLayout)
  {
    var width = this._widgetWidth - 2*this._widgetMargin;
    var min = this._trackImageDimension;
    var max = width - this._trackImageDimension;
    var length = max - min;
    start = min + length*this._startPercent/100.0;
    var other = min + length*this._endPercent/100.0;
    size = other - start;
  }
  else
  {
    var height = this._widgetHeight - 2*this._widgetMargin;
    var min = this._trackImageDimension;
    var max = height - this._trackImageDimension;
    var length = max - min;
    var other = min + length - length*this._startPercent/100.0;
    start = min + length - length*this._endPercent/100.0;
    size = other - start;
  }
  return {"start":start, "size":size};
}

JSSlider.prototype._appendTickMarks = function(sb)
{
  var numberOfTicks = this._numTicks;
  if (numberOfTicks > 0 || (this._customImage != null) )
  {
    var ticksLength, outerTop, outerLeft, outerWidth, outerHeight,
        tickWidth, tickHeight;
    if (this._horizontalLayout)
    {
      if (this._ticksBeforeTrack)
      {
        outerTop = this._widgetMargin;
      }
      else
      {
        outerTop = this._widgetMargin + this._trackImageDimension + this._ticksMargin;
      }
      outerLeft = this._widgetMargin + this._trackImageDimension;
      ticksLength = this._widgetWidth - 2*this._widgetMargin -
        2*this._trackImageDimension;
      outerWidth = ticksLength;
      outerHeight = this._ticksThickness;
      if (this._trackHidden)
      {
        outerHeight += this._ticksMargin + this._trackImageDimension;
      }
      tickWidth = 1;
      tickHeight = outerHeight;
    }
    else
    {
      outerTop = this._widgetMargin + this._trackImageDimension;
      if (this._ticksBeforeTrack)
      {
        outerLeft = this._widgetMargin;
      }
      else
      {
        outerLeft = this._widgetMargin + this._trackImageDimension + this._ticksMargin;
      }
      ticksLength = this._widgetHeight - 2*this._widgetMargin -
        2*this._trackImageDimension;
      outerWidth = this._ticksThickness;
      if (this._trackHidden)
      {
        outerWidth += this._ticksMargin + this._trackImageDimension;
      }
      outerHeight = ticksLength;
      tickWidth = outerWidth;
      tickHeight = 1;
    }
    if (numberOfTicks > 1)
    {
      if (ticksLength < numberOfTicks)
      {
        numberOfTicks = ticksLength; // unable to draw any more than this
      }
    }
    if (this._customImage != null)
    {
      sb.append("<img style=\"position: absolute; top: ");
      sb.append(outerTop);
      sb.append("px; left: ");
      sb.append(outerLeft);
      sb.append("px; width: ");
      sb.append(outerWidth);
      sb.append("px; height: ");
      sb.append(outerHeight);
      sb.append("px;\" src=\"");
      sb.append(this._customImage);
      sb.append("\"/>");
    }
    sb.append("<div style=\"position: absolute; top: ");
    sb.append(outerTop);
    sb.append("px; left: ");
    sb.append(outerLeft);
    sb.append("px; width: ");
    sb.append(outerWidth);
    sb.append("px; height: ");
    sb.append(outerHeight);
    sb.append("px;\">");
    for (var t=0; t<numberOfTicks; t++)
    {
      sb.append("<div style=\"position: absolute; top: ");
      if (this._horizontalLayout)
      {
        sb.append("0px; left: ");
        var tickLeft = ticksLength*t/(numberOfTicks-1);
        sb.append(tickLeft);
      }
      else
      {
        var tickTop;
        if (t == 0 && numberOfTicks == 1)
        {
          tickTop = ticksLength;
        }
        else
        {
          tickTop = ticksLength*t/(numberOfTicks-1);
        }
        sb.append(tickTop);
        sb.append("px; left: 0");
      }
      sb.append("px; width: ");
      sb.append(tickWidth);
      sb.append("px; height: ");
      sb.append(tickHeight);
      sb.append("px; background-color: ");
      sb.append(this._tickColor);
      sb.append(";\"><img src=\"");
      sb.append(this._spacerImage);
      sb.append("\" style=\"width: 0px; height: 0px;\"/></div>");
    }
    sb.append("</div>");
  }
}

JSSlider.prototype._appendTrackOutline = function(sb)
{
  var top, left, width, height, midTop, midLeft, midWidth, midHeight, midImage,
      rangeTop, rangeLeft, rangeWidth, rangeHeight, rangeImage,
      startTop, startLeft, startImage, endTop, endLeft, endImage;
  var effectiveTickThickness = 0;
  if ( (this._numTicks > 0) || (this._customImage != null) )
  {
    effectiveTickThickness = this._ticksThickness + this._ticksMargin;
  }
  if (this._horizontalLayout)
  {
    if (this._ticksBeforeTrack)
    {
      top = this._widgetMargin + effectiveTickThickness;
    }
    else
    {
      top = this._widgetMargin;
    }
    left = this._widgetMargin;
    width = this._widgetWidth - 2*this._widgetMargin;
    height = this._trackImageDimension;
    startTop = 0;
    startLeft = 0;
    startImage = this._horizontalTrackStartImage;
    midTop = 0;
    midLeft = this._trackImageDimension;
    midWidth = width - 2*this._trackImageDimension;
    midHeight = this._trackImageDimension;
    midImage = this._horizontalTrackMidImage;
    endTop = 0;
    endLeft = width - this._trackImageDimension;
    endImage = this._horizontalTrackEndImage;
    if (this._rangeSlider)
    {
      var startAndSize = this._getRangeBarStartAndSize();
      rangeTop = 0;
      rangeLeft = startAndSize["start"]; // variable
      rangeWidth = startAndSize["size"]; // variable
      rangeHeight = this._trackImageDimension;
      rangeImage = this._horizontalTrackRangeImage;
    }
  }
  else
  {
    top = this._widgetMargin;
    if (this._ticksBeforeTrack)
    {
      left = this._widgetMargin + effectiveTickThickness;
    }
    else
    {
      left = this._widgetMargin;
    }
    width = this._trackImageDimension;
    height = this._widgetHeight - 2*this._widgetMargin;
    startTop = height - this._trackImageDimension;
    startLeft = 0;
    startImage = this._verticalTrackStartImage;
    midTop = this._trackImageDimension;
    midLeft = 0;
    midWidth = this._trackImageDimension;
    midHeight = height - 2*this._trackImageDimension;
    midImage = this._verticalTrackMidImage;
    endTop = 0;
    endLeft = 0;
    endImage = this._verticalTrackEndImage;
    if (this._rangeSlider)
    {
      var startAndSize = this._getRangeBarStartAndSize();
      rangeTop = startAndSize["start"]; // variable
      rangeLeft = 0;
      rangeWidth = this._trackImageDimension;
      rangeHeight = startAndSize["size"]; // variable
      rangeImage = this._verticalTrackRangeImage;
    }
  }
  sb.append("<div style=\"position: absolute; top: ");
  sb.append(top);
  sb.append("px; left: ");
  sb.append(left);
  sb.append("px; width: ");
  sb.append(width);
  sb.append("px; height: ");
  sb.append(height);
  sb.append("px;\">");
  
  // start track image
  sb.append("<img style=\"position: absolute; top: ");
  sb.append(startTop);
  sb.append("px; left: ");
  sb.append(startLeft);
  sb.append("px; width: ");
  sb.append(this._trackImageDimension);
  sb.append("px; height: ");
  sb.append(this._trackImageDimension);
  sb.append("px;\" src=\"");
  sb.append(startImage);
  sb.append("\"/>");
  
  // mid track image (stretches)
  sb.append("<img style=\"position: absolute; top: ");
  sb.append(midTop);
  sb.append("px; left: ");
  sb.append(midLeft);
  sb.append("px; width: ");
  sb.append(midWidth);
  sb.append("px; height: ");
  sb.append(midHeight);
  sb.append("px; background-image: url('");
  sb.append(midImage);
  sb.append("');\" src=\"");
  sb.append(this._spacerImage);
  sb.append("\"/>");
  
  // end track image
  sb.append("<img style=\"position: absolute; top: ");
  sb.append(endTop);
  sb.append("px; left: ");
  sb.append(endLeft);
  sb.append("px; width: ");
  sb.append(this._trackImageDimension);
  sb.append("px; height: ");
  sb.append(this._trackImageDimension);
  sb.append("px;\" src=\"");
  sb.append(endImage);
  sb.append("\"/>");
  
  // range bar (stetches)
  if (this._rangeSlider)
  {
    sb.append("<img id=\"");
    sb.append(this._id);
    sb.append(JSSlider._ID_RANGE_BAR);
    sb.append("\" style=\"position: absolute; top: ");
    sb.append(rangeTop);
    sb.append("px; left: ");
    sb.append(rangeLeft);
    sb.append("px; width: ");
    sb.append(rangeWidth);
    sb.append("px; height: ");
    sb.append(rangeHeight);
    sb.append("px; background-image: url('");
    sb.append(rangeImage);
    sb.append("');\" src=\"");
    sb.append(this._spacerImage);
    sb.append("\"/>");
  }
  
  sb.append("</div>");
}

JSSlider.prototype._appendMarker = function(sb, percent0To100, markerId)
{
  var width, height, image, cursor;
  if (this._horizontalLayout)
  {
    width = this._horizontalMarkerWidth;
    height = this._horizontalMarkerHeight;
    image = this._horizontalMarkerImage;
    cursor = "pointer";
  }
  else
  {
    width = this._verticalMarkerWidth;
    height = this._verticalMarkerHeight;
    image = this._verticalMarkerImage;
    cursor = "pointer";
  }
  var top = this._getMarkerTop(percent0To100);
  var left = this._getMarkerLeft(percent0To100);
  
  sb.append("<a id=\"");
  sb.append(markerId);
  sb.append("\" href=\"javascript:;\" style=\"position: absolute; top: ");
  sb.append(top);
  sb.append("px; left: ");
  sb.append(left);
  sb.append("px; cursor: ");
  sb.append(cursor);
  sb.append(";\" onkeydown=\"return window['");
  sb.append(this._id);
  sb.append("'].markerKeyDown(this,event)\" onmousedown=\"window['");
  sb.append(this._id);
  sb.append("'].markerDragged(this,event)\" onmousemove=\"if(document.all){window['");
  sb.append(this._id);
  sb.append("'].markerMoved(event);}\" onmouseup=\"if(document.all){window['");
  sb.append(this._id);
  sb.append("'].markerReleased(event)}\"><img align=\"top\" border=0 src=\"");
  sb.append(image);
  sb.append("\" style=\"width: ");
  sb.append(width);
  sb.append("px; height: ");
  sb.append(height);
  sb.append("px;\"/></a>");
}


function DhtmlStringBuffer()
{
  this.maxStreamLength = (document.all?100:10000);
  this.data = new Array(100);
  this.iStr = 0;
  this.append = function(obj)
  {
    this.data[this.iStr++] = obj;
    if (this.data.length > this.maxStreamLength)
    {
      this.data = [this.data.join("")];
      this.data.length = 100;
      this.iStr = 1;
    }
    return this;
  };
  this.toString = function()
  {
    return this.data.join("");
  };
}

DhtmlStringBuffer._joinFunc = Array.prototype.join;

DhtmlStringBuffer.concat = function()
{
  arguments.join = this._joinFunc;
  return arguments.join("")
}



      function reposRangeBarHChanged(newStartPercent0To100, newEndPercent0To100)
      {
        reposRangeSliderV.setMarkerPercent(newStartPercent0To100, newEndPercent0To100);
      }

      function reposRangeBarVChanged(newStartPercent0To100, newEndPercent0To100)
      {
        reposRangeSliderH.setMarkerPercent(newStartPercent0To100, newEndPercent0To100);
      }



      function DefaultValChangedListener(ElementId, newStartPercent0To100, newEndPercent0To100, MaxVal)
      {
        var slideVal = Math.round(100*newStartPercent0To100)/100;
        document.getElementById("slideval").value = slideVal
      }





JSSlider._ID_WIDGET = "sliderWidget";
JSSlider._ID_RANGE_BAR = "sliderRangeBar";
JSSlider._ID_START_MARKER = "sliderStartMarker";
JSSlider._ID_END_MARKER = "sliderEndMarker";
JSSlider._KEY_CODE_ARROW_RIGHT = 39;
JSSlider._KEY_CODE_ARROW_LEFT = 37;
JSSlider._KEY_CODE_ARROW_UP = 38;
JSSlider._KEY_CODE_ARROW_DOWN = 40;