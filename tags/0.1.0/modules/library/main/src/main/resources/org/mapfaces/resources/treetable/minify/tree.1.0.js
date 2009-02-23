
function display(B,D,E,G,C,A){thisDiv=document.getElementById("ul:"+D+":"+B);thisSymbol=document.getElementById(D+"_symbol_"+B);thisTreenode=document.getElementById("treenode:"+D+":"+B);if(thisDiv.childNodes.length>0){if(thisDiv.style.display=="none"){thisDiv.style.display="block";if(thisSymbol){thisSymbol.setAttribute("class","x-tree-ec-icon x-tree-elbow-end-minus")}if(thisTreenode){thisTreenode.setAttribute("class","x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col")}}else{thisDiv.style.display="none";if(thisSymbol){thisSymbol.setAttribute("class","x-tree-ec-icon x-tree-elbow-end-plus")}if(thisTreenode){thisTreenode.setAttribute("class","x-tree-node-el x-tree-node-collapsed x-tree-col")}}return false}else{if(thisDiv.style.display=="none"){thisDiv.style.display="block";if(thisSymbol){thisSymbol.setAttribute("class","x-tree-ec-icon x-tree-elbow-end-minus")}if(thisTreenode){thisTreenode.setAttribute("class","x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col")}}var F=new Request.HTML({method:E,url:G,onFailure:function(H){alert("Request.HTML.Status: "+H.status+" - "+H.statusText)}}).send(C);return true}}function expandAll(A){uls=document.getElementsByTagName("ul");for(i=1;i<uls.length;i++){str=uls[i].id.split(":",4);jsf_id=str[1];panel_id=str[2];line_id=str[3];id=jsf_id+":"+panel_id+":"+line_id;idsymbol=jsf_id+":"+panel_id+"_symbol_"+line_id;if(document.getElementById("ul:"+id)){document.getElementById("ul:"+id).style.display="block"}if(document.getElementById(idsymbol)){document.getElementById(idsymbol).setAttribute("class","x-tree-ec-icon x-tree-elbow-end-minus")}if(document.getElementById("treenode:"+id)){document.getElementById("treenode:"+id).setAttribute("class","x-tree-node-el x-tree-node-expanded x-tree-node-node-over x-tree-col")}}}function collapseAll(A){uls=document.getElementsByTagName("ul");for(i=1;i<uls.length;i++){str=uls[i].id.split(":",4);jsf_id=str[1];panel_id=str[2];line_id=str[3];id=jsf_id+":"+panel_id+":"+line_id;idsymbol=jsf_id+":"+panel_id+"_symbol_"+line_id;if(document.getElementById("ul:"+id)){document.getElementById("ul:"+id).style.display="none"}if(document.getElementById(idsymbol)){document.getElementById(idsymbol).setAttribute("class","x-tree-ec-icon x-tree-elbow-end-plus")}if(document.getElementById("treenode:"+id)){document.getElementById("treenode:"+id).setAttribute("class","x-tree-node-el x-tree-node-collapsed x-tree-col")}}}function checkall(){var A=document.getElementsByTagName("input");for(i=0;i<A.length;i++){if((A[i].id).split(":","1")[0]=="check"){A[i].checked=document.getElementById("checkcolumn").checked}}}function collapse(A){thisDiv=document.getElementById("panel:"+A);if(thisDiv.style.display=="none"){thisDiv.style.display="block"}else{thisDiv.style.display="none"}}function showMore(A){thisDiv=document.getElementById("info:"+A);thisAnchor=document.getElementById("anchor:info:"+A);if(thisDiv.style.display=="none"){thisDiv.style.display="block";thisAnchor.style.backgroundPosition="50px"}else{thisDiv.style.display="none";thisAnchor.style.backgroundPosition="60px"}}var _COLOR_HIGHLIGHT="#DDAFFF";var _COLOR_FOLDER_INITIAL="#CCCCCC";var _COLOR_NODE_INITIAL="#AAAFFF";var _DROP_ZONE_INITIAL_HEIGHT=2;var _DROP_ZONE_TWEEN_HEIGHT=20;var _DRAG_FADE=0.5;window.addEvent("domready",function(){document.ondragstart=function(){return false};$$(".dragable").each(function(A){position=A.getPosition();new Drag.Move(A,{snap:20,grid:20,onStart:function(B){child=A.getChildren();for(i=0;i<child.length;i++){if(child[i].id!="treenode:"+A.id.slice(3)){child[i].setStyles({display:"none"})}}A.setStyles({top:position.x,left:position.y})},onSnap:function(B){},droppables:".droppable",onDrop:function(G,H){newPaddingLeft=(parseInt(H.getParent().getAttribute("depth")))*12;newWidth=parseInt($("treenode:"+A.id.slice(3)).style.width.slice(0,-2))+parseInt($("treenode:"+A.id.slice(3)).style.paddingLeft.slice(0,-2))-newPaddingLeft;$("treenode:"+A.id.slice(3)).setAttribute("style","width :"+newWidth+"px; padding-left :"+newPaddingLeft+"px;");child=A.getChildren();for(i=0;i<child.length;i++){if(child[i].id!="treenode:"+A.id.slice(3)){if(child[i].id!="info:"+A.id.slice(3)){child[i].setStyles({display:"block"})}}}var F=A.getParent();oldDragPosition=F.getParent().getParent();oldDragComponent=oldDragPosition.getAttribute("name");var E,D,B,C;switch(H.getAttribute("where")){case"before":D=H.getParent();F.setAttribute("depth",D.getAttribute("depth"));A.setAttribute("depth",D.getAttribute("depth"));F.inject(D,"before");break;case"firstitem":E=$(H.getAttribute("dest"));D=E.childNodes[0];F.setAttribute("depth",D.getAttribute("depth"));A.setAttribute("depth",D.getAttribute("depth"));F.inject(D,"after");break;case"beforeitem":D=H.getParent();F.inject(D,"before");break;case"afteritem":D=H.getParent();F.inject(D,"after");break;case"after":D=H.getParent();F.inject(D,"after");break;default:E=$("ul:"+H.id.slice(3));D=E.lastChild;F.inject(D,"after");break}if(H.hasAttribute("where")){H.setStyles({background:_COLOR_FOLDER_INITIAL});H.tween("height",_DROP_ZONE_INITIAL_HEIGHT)}else{H.setStyles({background:_COLOR_FOLDER_INITIAL})}A.setStyles({left:0,top:0,margin:0,position:"relative"});A.fade(1);if(!H.hasAttribute("where")){where="lastitem"}else{where=H.getAttribute("where")}var I=new Request.HTML({url:$("ajax.server.request.URL").value,data:{"javax.faces.ViewState":$("javax.faces.ViewState").value,"org.mapfaces.ajax.AJAX_COMPONENT_VALUE":"true","org.mapfaces.ajax.AJAX_REQUEST":"true","org.mapfaces.ajax.AJAX_CONTAINER_ID":F.getAttribute("parent"),"org.mapfaces.ajax.AJAX_COMPONENT_ID":F.getAttribute("name"),"org.mapfaces.ajax.DND_NEW_PARENT_LINE":H.id,"org.mapfaces.ajax.DND_NEW_PARENT_COMPONENT":H.getAttribute("name"),"org.mapfaces.ajax.DND_POSITION_LINE":where}}).send()},onEnter:function(B,C){if(C.hasAttribute("where")){C.setStyles({background:_COLOR_HIGHLIGHT});C.highlight(_COLOR_HIGHLIGHT,_COLOR_FOLDER_INITIAL);C.tween("height",_DROP_ZONE_TWEEN_HEIGHT)}else{C.setStyles({background:_COLOR_HIGHLIGHT})}A.setStyles({background:_COLOR_NODE_INITIAL,"z-index":2,position:"absolute",margin:0,top:0,left:0});A.fade(_DRAG_FADE)},onLeave:function(B,C){if(C.hasAttribute("where")){C.setStyles({background:_COLOR_FOLDER_INITIAL});C.tween("height",_DROP_ZONE_INITIAL_HEIGHT)}else{C.setStyles({background:_COLOR_FOLDER_INITIAL})}},onComplete:function(B,C){}});A.setStyles({top:0,left:0,position:"relative"})})});

		
	
		
		
		

		
		
		

		

		

		
			
		
	
	
		
		
			
		
	
		
		
		

		
		
		

		

		

		
			
		
	
		
		
	
			
			
	
		
		
		
		
		
		
		
		

		
		
		

		

		

		
			
		
	
		
		
		
		
		
		
		
	
	
	
		


	