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

OpenLayers.Util.reRender = function(jsObject, reqId, formClientId, parameters) {
        //If jsObject is the map object
         var options = null;
        parameters[jsObject.mfAjaxCompId] = jsObject.mfAjaxCompId;

        if (this.mfAjaxDefaultOptions != null) {
            options = {};
            OpenLayers.Util.extend(options, jsObject.defaultOptions);

            OpenLayers.Util.extend(options, {
                'control': jsObject,
                'single': true
            });

            if (parameters != null) {

                if (options.parameters == null) {
                    options.parameters = {};
                }
                OpenLayers.Util.extend(options.parameters, parameters);
            }
        }

        A4J.AJAX.Submit(
            formClientId,
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
