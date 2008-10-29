/*
 * 
 */
try{
    if (MooTools != null){
        //console.log('Mootools already loaded');
    }
}catch(err){
    document.write('<script type="text/javascript" src="resource.jsf?r=/org/mapfaces/resources/tree/js/moo1.2.js"></script>');
}

/*
 * Add Events
 */

/*
 *  Fonctions
 */

var dispEffectNone = function(div){
    /*if(div.setStyles){
	div.setStyles({
		display:'block',
		opacity: 0
	});
        if(div.tween)
            div.tween('opacity',1);
    }else{*/
    div.style.display="block";
    div.style.opacity="1";
    div.style.filter="alpha(opacity=100)";
    // }
};

var dispEffectBlock = function(div){
    /*if(div.setStyles){
	div.setStyles({
		display:'none',
		opacity: 1
	});
        if(div.tween)
          div.tween('opacity',0);
    }else{*/
    div.style.display="none";
    div.style.opacity="0";
    div.style.filter="alpha(opacity=0)";
    // }
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
        lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col");
    }
    return true;
}

function collapseSymbol(formId, panelId, nodeId){
    var lineSymbol = document.getElementById(panelId+"_symbol_"+nodeId);
    var lineTreenode = document.getElementById("treenode:"+panelId+":"+nodeId);
    
    lineSymbol.setAttribute("class", "x-tree-ec-icon x-tree-elbow-end-plus");
    lineTreenode.setAttribute("class", "x-tree-node-el x-tree-node-collapsed x-tree-node-node-over x-tree-col");

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

function expAll(){
    $$('.collapsible').each(function(div) {
        div.style.display="block";
        div.style.opacity="1";
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
/**
 *
 */
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


function checkAllInputs(panelId){
    var inputs = document.getElementsByTagName("input");
    for (i = 0; i < inputs.length; i++) {
        if ((inputs[i].id).split("_", "1")[0] == panelId){
            inputs[i].checked = !inputs[i].checked;
        }
    }
}
