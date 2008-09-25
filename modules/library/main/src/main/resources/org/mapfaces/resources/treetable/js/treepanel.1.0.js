/*
 *
 *@author kdelfour
 */

/**
 *
 */
function display(divId, panelId, methode, server, paramaters, viewstate){
    thisDiv = document.getElementById("ul:"+panelId+":"+divId);
    thisSymbol = document.getElementById(panelId+"_symbol_"+divId);
    thisTreenode = document.getElementById("treenode:"+panelId+":"+divId);

    if (thisDiv.childNodes.length > 0){
        if (thisDiv.style.display == "none"){
            thisDiv.style.display="block";
            if (thisSymbol) {
                thisSymbol.setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-minus");
            }
            if (thisTreenode) {
                thisTreenode.setAttribute("class", "x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col");
            }
        }
        else  {
            thisDiv.style.display="none";
            if (thisSymbol) {
                thisSymbol.setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-plus");
            }
            if (thisTreenode) {
                thisTreenode.setAttribute("class", "x-tree-node-el x-tree-node-collapsed x-tree-col");
            }
        }
        return false;
    }
    else {
        // Sending AJAX Request
        if (thisDiv.style.display == "none"){
            thisDiv.style.display="block";
            if (thisSymbol) {
                thisSymbol.setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-minus");
            }
            if (thisTreenode) {
                thisTreenode.setAttribute("class", "x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col");
            }
        }
        
        var req = new Request.HTML({
            method:methode,
            url:server,
            //Our request will most likely succeed, but just in case, we'll add an
            //onFailure method which will let the user know what happened.
            onFailure: function(responseText) {
                alert("Request.HTML.Status: " + responseText.status +" - "+responseText.statusText);
            }
        }).send(paramaters);
        //        var request = new Request.HTML({method:methode, url:server}).send(paramaters);
        //        request.isSuccess(appendXMLResponse(divId, request));
        
        return true;
    }
}

function expandAll(panelId){
    uls = document.getElementsByTagName("ul");
    for(i=1;i<uls.length;i++){
       str = uls[i].id.split(":",4);
        jsf_id = str[1];
        panel_id = str[2];
        line_id = str[3];
        id = jsf_id + ":" + panel_id + ":" + line_id;
        idsymbol = jsf_id + ":" +panel_id+"_symbol_"+line_id;
        if (document.getElementById("ul:"+id)) {
            document.getElementById("ul:"+id).style.display="block";
        }
        if (document.getElementById(idsymbol)) {
            document.getElementById(idsymbol).setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-minus");
        }
        if (document.getElementById("treenode:"+id)) {
            document.getElementById("treenode:"+id).setAttribute("class", "x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col");
        }
    }
}
 
/**
 *
 */
function collapseAll(panelId){
    uls = document.getElementsByTagName("ul");
    for(i=1;i<uls.length;i++){
        str = uls[i].id.split(":",4);
        jsf_id = str[1];
        panel_id = str[2];
        line_id = str[3];
        id = jsf_id + ":" + panel_id + ":" + line_id;
        idsymbol = jsf_id + ":" +panel_id+"_symbol_"+line_id;
        if (document.getElementById("ul:"+id)) {
            document.getElementById("ul:"+id).style.display="none";
        }
        if (document.getElementById(idsymbol)) {
            document.getElementById(idsymbol).setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-plus");
        }
        if (document.getElementById("treenode:"+id)) {
            document.getElementById("treenode:"+id).setAttribute("class", "x-tree-node-el x-tree-node-collapsed x-tree-col");
        }
    }
}

/**
 * Functions for checkbox
 */
function checkall(){
    var inputs = document.getElementsByTagName("input");
    for (i = 0; i < inputs.length; i++) {
        if ((inputs[i].id).split(":", "1")[0] =="check"){
            inputs[i].checked = document.getElementById("checkcolumn").checked;
        }
    }
}

/**
 * Functions for collapse panel
 */
function collapse(panelId){
    thisDiv = document.getElementById("panel:"+panelId);
    if (thisDiv.style.display == "none") {
        thisDiv.style.display="block";
    }
    else {
        thisDiv.style.display="none";
    }
}


/**
 * 
 */
function showMore(div_id){
    thisDiv = document.getElementById("info:"+div_id);
    thisAnchor = document.getElementById("anchor:info:"+div_id);
    if (thisDiv.style.display == "none") {
        thisDiv.style.display="block";
        thisAnchor.style.backgroundPosition = "50px";
    }
    else {
        thisDiv.style.display="none";
        thisAnchor.style.backgroundPosition = "60px";
    }
}