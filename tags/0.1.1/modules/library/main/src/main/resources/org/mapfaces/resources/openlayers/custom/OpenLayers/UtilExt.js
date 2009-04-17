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