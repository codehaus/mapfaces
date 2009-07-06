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

OpenLayers.Util.reRender = function(jsObject, reqId, formId, parameters) {
        A4J.AJAX.Submit( reqId, formId,
            null,
            {
                'control':jsObject,
                'single':true,
                'parameters': parameters,
                'actionUrl': window.location.href//,
                //'oncomplete': jsObject.onComplete
            }
            );

       /* A4J.AJAX.Submit('j_id_jsp_928084566_0','j_id_jsp_928084566_1',event,
           {
            'affected':['j_id_jsp_928084566_1:C_Mp_MapFaces_Layer_WMS_1','j_id_jsp_928084566_1:Lc_TreeTable_TreePanel_Visible_5_hidden'] ,
            'control':this,
            'single':true,
            'oncomplete':function(request,event,data){
                            MapFaces_Layer_WMS_1.visibility = false;
                         },
            'parameters':{
                'j_id_jsp_928084566_1:check_Lc_TreeTable_TreePanel_Visible_5_hidden_Ajax':'j_id_jsp_928084566_1:check_Lc_TreeTable_TreePanel_Visible_5_hidden_Ajax',
                'org.mapfaces.ajax.AJAX_LAYER_ID':'j_id_jsp_928084566_1:C_Mp_MapFaces_Layer_WMS_1',
                'org.mapfaces.ajax.AJAX_CONTAINER_ID':'j_id_jsp_928084566_1:check_Lc_TreeTable_TreePanel_Visible_5_hidden'
            } ,
            'actionUrl':'/mf/tests/scale.jsf'
        } )*/

};