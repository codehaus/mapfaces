/*
 * 
 */
try{
    if (MooTools != null){
        //console.log('Mootools already loaded');
    }
}catch(err){
    //console.log('No mootools');
    document.write('<script type="text/javascript" src="resource.jsf?r=/org/mapfaces/resources/treetable/js/moo1.2.js"></script>');
}

/*
 * Add Events
 */

/* Fonctions*/
/**
 *
 */

var dispEffectNone = function(div){
	div.setStyles({
		display:'block',
		opacity: 0
	});
	div.tween('opacity',1);
};

var dispEffectBlock = function(div){
	div.setStyles({
		display:'none',
		opacity: 1
	});
	div.tween('opacity',0);
};


function disp(formId, panelId, nodeId){
    var lineUl = document.getElementById("ul:"+panelId+":"+nodeId);
    var lineSymbol = document.getElementById(panelId+"_symbol_"+nodeId);
    var lineTreenode = document.getElementById("treenode:"+panelId+":"+nodeId);
            
    if (lineUl.childNodes.length > 0){
        if (lineUl.style.display == "none"){
            dispEffectNone(lineUl);
            if (lineSymbol) {
                lineSymbol.setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-minus");
            }
            if (lineTreenode) {
                lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col");
            }
            
        }
        else  {
            dispEffectBlock(lineUl);
            if (lineSymbol) {
                lineSymbol.setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-plus");
            }
            if (lineTreenode) {
                lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-collapsed x-tree-col");
            }
        }
        return false;
    }
    return true;
}

/**
 *
 */
function expAll(panelId){
    uls = $$('li');
    for(i=1;i<uls.length;i++){
        str = uls[i].id.split(":",4);
        jsf_id = str[1];
        panel_id = str[2];
        line_id = str[3];
        id = jsf_id + ":" + panel_id + ":" + line_id;
        idsymbol = jsf_id + ":" +panel_id+"_symbol_"+line_id;
        if (document.getElementById("ul:"+id)!= null){
            if (document.getElementById("ul:"+id).childNodes.length > 0) {
                document.getElementById("ul:"+id).style.display="block";
            
                if (document.getElementById(idsymbol)) {
                    document.getElementById(idsymbol).setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-minus");
                }
                if (document.getElementById("treenode:"+id)) {
                    document.getElementById("treenode:"+id).setAttribute("class", "x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col");
                }
            }
        
        }
    }
}
 
/**
 *
 */
function collAll(panelId){
    uls = $$('li');
    for(i=0;i<uls.length;i++){
        str = uls[i].id.split(":",4);
        jsf_id = str[1];
        panel_id = str[2];
        line_id = str[3];
        id = jsf_id + ":" + panel_id + ":" + line_id;
        idsymbol = jsf_id + ":" +panel_id+"_symbol_"+line_id;
        if (document.getElementById("ul:"+id)!= null){
            if (document.getElementById("ul:"+id).childNodes.length > 0) {
                document.getElementById("ul:"+id).style.display="none";
                if (document.getElementById(idsymbol)) {
                    document.getElementById(idsymbol).setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-plus");
                }
                if (document.getElementById("treenode:"+id)) {
                    document.getElementById("treenode:"+id).setAttribute("class", "x-tree-node-el x-tree-node-collapsed x-tree-col");
                }
            }
        }
    }
}

function showInfo(panelId,nodeId){
    thisDiv = document.getElementById("info:"+panelId+":"+nodeId);
    thisAnchor = document.getElementById(panelId+"_anchor_info_"+nodeId);
    if (thisDiv.style.display == "none") {
        thisDiv.style.display="block";
        thisAnchor.style.backgroundPosition = "-15px";
    }
    else {
        thisDiv.style.display="none";
        thisAnchor.style.backgroundPosition = "0";
    }
}
