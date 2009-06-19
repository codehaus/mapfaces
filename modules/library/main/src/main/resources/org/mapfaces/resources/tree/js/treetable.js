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

function dispEffectNone (div){
    div.style.display="block";
}

function dispEffectBlock(div){
    div.style.display="none";
}

function getSymbolId(formId, panelId, nodeId){
    return panelId + "_symbol_" + nodeId;
}

function getTreeNodeId(panelId, nodeId){
    return "treenode:"+panelId+":"+nodeId;
}

function getUlId(panelId, nodeId){
    return "ul:"+panelId+":"+nodeId;
}

function disp(formId, panelId, nodeId){
    var lineUl = document.getElementById(getUlId(panelId, nodeId));
            
    if (lineUl.childNodes.length > 0){
        
        if (lineUl.style.display == "none"){
            expandSymbol(formId, panelId, nodeId);
            dispEffectNone(lineUl);
            
        } else  {
            collapseSymbol(formId, panelId, nodeId);
            dispEffectBlock(lineUl);
        }
        return false;
    }
    return true;
}

function expandSymbol(formId, panelId, nodeId){
    document.getElementById(getSymbolId(formId, panelId, nodeId)).className="x-tree-ec-icon x-tree-elbow-minus floatLeft";
    var lineTreenode = document.getElementById(getTreeNodeId(panelId, nodeId));

    if (lineTreenode.className.indexOf("x-tree-droppable") != -1){
        lineTreenode.className="x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col x-tree-droppable x-tree-droppable-folder floatLeft";

    }else{
        lineTreenode.className="x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col floatLeft";
    }
}

function collapseSymbol(formId, panelId, nodeId){
    document.getElementById(getSymbolId(formId, panelId, nodeId)).className="x-tree-ec-icon x-tree-elbow-plus floatLeft";
    var lineTreenode = document.getElementById(getTreeNodeId(panelId,nodeId));

    if (lineTreenode.className.indexOf("x-tree-droppable") != -1){
        lineTreenode.className = "x-tree-node-el x-tree-node-collapsed x-tree-node-node-over x-tree-col x-tree-droppable x-tree-droppable-folder floatLeft";
    }else {
        lineTreenode.className = "x-tree-node-el x-tree-node-collapsed x-tree-node-node-over x-tree-col floatLeft";
    }
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

function collapseAll(){
    $$('.collapsible').each(function(div) {
        div.style.display="none";
        if (div.childNodes.length > 0){
            var reg = new RegExp("(ul)","g");
            var lineTreenode = document.getElementById(div.id.replace(reg,"treenode"));
            if  (lineTreenode!=null){
                lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-collapsed x-tree-node-node-over x-tree-col floatLeft");
                var listofchild = lineTreenode.childNodes;
                for(i=0;i<=listofchild.length-1;i++){
                    var childrenlist = listofchild[i].childNodes;
                    if (childrenlist.length > 0){
                        var children = childrenlist[0];
                        if (children.className){
                            if (children.className.indexOf("x-tree-elbow-minus") != -1){
                                children.setAttribute("class", "x-tree-ec-icon x-tree-elbow-plus floatLeft");
                            }
                        }
                    }
                }
            }
        }

    });
    return false;
}

function expandAll(){
    $$('.collapsible').each(function(div) {
        div.style.display="block";
        if (div.childNodes.length > 0){
            var reg = new RegExp("(ul)","g");
            var lineTreenode = document.getElementById(div.id.replace(reg,"treenode"));
            if  (lineTreenode!=null){
                lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-collapsed x-tree-node-node-over x-tree-col floatLeft");
                var listofchild = lineTreenode.childNodes;
                for(i=0;i<=listofchild.length-1;i++){
                    var childrenlist = listofchild[i].childNodes;
                    if (childrenlist.length > 0){
                        var children = childrenlist[0];
                        if (children.className){
                            if (children.className.indexOf("x-tree-elbow-plus") != -1){
                                children.setAttribute("class", "x-tree-ec-icon x-tree-elbow-minus floatLeft");
                            }
                        }
                    }
                }
            }
        }

    });
    return false;
}