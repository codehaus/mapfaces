/*
Script: treetable.js
License:
Copyright:
	(C) 2007 - 2008, Geomatys
 */

var TreeTableJs = {
    'version': '1.0.0',
    'build': ''
};

var dispEffectNone = function(div){
    //    if (browser=="Microsoft Internet Explorer"){
    div.style.display="block";
    div.style.opacity="1";
    div.style.filter="alpha(opacity=100)";
//    }else{
//    	div.setStyles({
//            display:'block',
//            opacity: 1
//	});
//        div.tween('opacity',1);
//    }
};

var dispEffectBlock = function(div){
    //    if (browser=="Microsoft Internet Explorer"){
    div.style.display="none";
    div.style.opacity="0";
    div.style.filter="alpha(opacity=0)";
//    }else{
//        div.setStyles({
//            display:'none',
//            opacity: 0
//	});
//        div.tween('opacity',0);
//    }
};


function disp(formId, panelId, nodeId){
    var lineUl = document.getElementById("ul:"+panelId+":"+nodeId);
    var lineSymbol = document.getElementById(panelId+"_symbol_"+nodeId);
    var lineTreenode = document.getElementById("treenode:"+panelId+":"+nodeId);
            
    if (lineUl.childNodes.length > 0){
        if (lineUl.style.display == "none"){
            expandSymbol(formId, panelId, nodeId);
            dispEffectNone(lineUl);
        }
        else  {
            collapseSymbol(formId, panelId, nodeId);
            dispEffectBlock(lineUl);
        }
        return false;
    }
    return true;
}

function expandSymbol(formId, panelId, nodeId){
    var lineUl = document.getElementById("ul:"+panelId+":"+nodeId);
    var lineSymbol = document.getElementById(panelId+"_symbol_"+nodeId);
    var lineTreenode = document.getElementById("treenode:"+panelId+":"+nodeId);
            
    if (lineUl.childNodes.length > 0){
        lineSymbol.className="x-tree-ec-icon x-tree-elbow-minus floatLeft";
        if (lineTreenode.className.indexOf("x-tree-droppable") != -1){
            lineTreenode.className="x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col x-tree-droppable x-tree-droppable-folder floatLeft";
        }else{
            lineTreenode.className="x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col floatLeft";
        }
    }
    return true;
}

function collapseSymbol(formId, panelId, nodeId){
    var lineSymbol = document.getElementById(panelId+"_symbol_"+nodeId);
    var lineTreenode = document.getElementById("treenode:"+panelId+":"+nodeId);
    
    lineSymbol.className="x-tree-ec-icon x-tree-elbow-plus floatLeft";
    if (lineTreenode.className.indexOf("x-tree-droppable") != -1){
        lineTreenode.className = "x-tree-node-el x-tree-node-collapsed x-tree-node-node-over x-tree-col x-tree-droppable x-tree-droppable-folder floatLeft";
    }else {
        lineTreenode.className = "x-tree-node-el x-tree-node-collapsed x-tree-node-node-over x-tree-col floatLeft";
    }

    return true;
}

function showInfo(panelId,nodeId){
    thisDiv = document.getElementById("info:"+panelId+":"+nodeId);
    thisAnchor = document.getElementById(panelId+"_anchor_info_"+nodeId);
    if (thisDiv.style.display == "none") {
        thisDiv.style.display="block";
        if(thisAnchor)
            thisAnchor.className = "x-tree-ec-icon x-tree-node-info-anchor x-tree-node-info-anchor-minus floatLeft";
    }
    else {
        thisDiv.style.display="none";
        if(thisAnchor)
            thisAnchor.className = "x-tree-ec-icon x-tree-node-info-anchor x-tree-node-info-anchor-plus floatLeft" ;
    }
}

