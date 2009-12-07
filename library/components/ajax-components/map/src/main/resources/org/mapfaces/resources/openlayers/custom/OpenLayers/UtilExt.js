/**
 * Function: trim
 * 
 * Parameters:
 * str - {String}
 * 
 * Returns:
 * {String} A String whitout leading/trailing whitespaces
 */
OpenLayers.Util.trim = function(str) {
    var str = str.replace(/^\s\s*/, ''),
        ws = /\s/,
        i = str.length;
    while (ws.test(str.charAt(--i)));
    return str.slice(0, i + 1);
};

OpenLayers.Util.sendA4JRequest = function(requestId, formId, options) {
        A4J.AJAX.Submit(
            requestId,
            formId,
            null,
            options);

};

/**
 * This method returns false to wrong extent defined with a projection.
 * @TODO only CRS:84 and EPSG:4326 are tested. this method should be complete for a most of crs. ie: epsg:3394 mercator.
 */
OpenLayers.Util.isvalidExtent = function(projection, extent) {
    if(extent && (projection == 'CRS:84' || projection == 'EPSG:4326')) {
        if (extent.left < -180 && extent.bottom < -90 && extent.right > 180 && extent.top > 90) {
            return false;
        }else {
            return true;
        }
    }
    return true; //in other cases it return true for an unknown projection
}
