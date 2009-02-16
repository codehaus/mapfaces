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
        lineSymbol.setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-minus");
        if (lineTreenode.className.contains("x-tree-droppable")){
            lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col x-tree-droppable x-tree-droppable-folder");
        }else{
            lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col");
        }
    }
    return true;
}

function collapseSymbol(formId, panelId, nodeId){
    var lineSymbol = document.getElementById(panelId+"_symbol_"+nodeId);
    var lineTreenode = document.getElementById("treenode:"+panelId+":"+nodeId);
    
    lineSymbol.setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-plus");
    if (lineTreenode.className.contains("x-tree-droppable")){
        lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-collapsed x-tree-node-node-over x-tree-col x-tree-droppable x-tree-droppable-folder");
    }else {
        lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-collapsed x-tree-node-node-over x-tree-col");
    }

    return true;
}

/**
 *
 */
function expAll(panelId){
    $$('.collapsible').each(function(div) {
        if(div.id.contains(panelId)){
            div.style.display="block";
            div.style.opacity="1";
            dispEffectNone(div);
            if (div.childNodes.length > 0){
                var reg = new RegExp("(ul)","g");
                var lineTreenode =$(div.id.replace(reg,"treenode"));
                if  (lineTreenode!=null){
                    lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col");
                    lineTreenode.firstChild.setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-minus");
                }
            }
            
        }
    });
  
    return false;
}
function expandAll(){
    $$('.collapsible').each(function(div) {
        div.style.display="block";
        div.style.opacity="1";
        dispEffectNone(div);
        if (div.childNodes.length > 0){
            var reg = new RegExp("(ul)","g");
            var lineTreenode =$(div.id.replace(reg,"treenode"));
            if  (lineTreenode!=null){
                lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col");
                lineTreenode.firstChild.setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-minus");
            }
        }
    });
  
    return false;
}

function collAll(panelId){
    $$('.collapsible').each(function(div) {
        if(div.id.contains(panelId)){
            div.style.display="none";
            div.style.opacity="0";
            if (div.childNodes.length > 0){
                var reg = new RegExp("(ul)","g");
                var lineTreenode =$(div.id.replace(reg,"treenode"));
                if  (lineTreenode!=null){
                    lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-collapsed x-tree-col");
                    lineTreenode.firstChild.setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-plus");
                }
            }
            
        }
    });
    return false;
}
function collapseAll(){
    $$('.collapsible').each(function(div) {
        div.style.display="none";
        div.style.opacity="0";
        if (div.childNodes.length > 0){
            var reg = new RegExp("(ul)","g");
            var lineTreenode =$(div.id.replace(reg,"treenode"));
            if  (lineTreenode!=null){
                lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-collapsed x-tree-col");
                lineTreenode.firstChild.setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-plus");
            }
        }
            
    });
    return false;
}

function showInfo(panelId,nodeId){
    thisDiv = document.getElementById("info:"+panelId+":"+nodeId);
    thisAnchor = document.getElementById(panelId+"_anchor_info_"+nodeId);
    if (thisDiv.style.display == "none") {
        thisDiv.style.display="block";
        if(thisAnchor)
            thisAnchor.className = "x-tree-ec-icon x-tree-node-info-anchor x-tree-node-info-anchor-minus";
    }
    else {
        thisDiv.style.display="none";
        if(thisAnchor)
            thisAnchor.className = "x-tree-ec-icon x-tree-node-info-anchor x-tree-node-info-anchor-plus" ;
    }
}

