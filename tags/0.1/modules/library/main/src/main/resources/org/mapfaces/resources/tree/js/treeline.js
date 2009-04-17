/*
Script: treeline.js
License:
Copyright:
	(C) 2007 - 2008, Geomatys
 */

var TreeLineJs = {
    'version': '1.0.0',
    'build': ''
};

var _COLOR_HIGHLIGHT          = "#DDAFFF";
var _COLOR_FOLDER_INITIAL     = "#CCCCCC";
var _COLOR_NODE_INITIAL       = "#AAAFFF";
var _DROP_ZONE_INITIAL_HEIGHT = 2;
var _DROP_ZONE_TWEEN_HEIGHT   = 20;
var _DRAG_FADE                = 0.5;
var _DnD_FUNCTION_PREPARE     = true;

try{
    //window.addEvent('load',function() {
    window.onDomReady(function() {
        $$('.x-tree-dragable').each(function(drag) {
            prepareDnd(drag);
        });
        _DnD_FUNCTION_PREPARE = false;
        return false;
    });
}catch(err){
}

function prepareDnd(drag){
    var position = drag.getPosition();
    new Drag.Move(drag, {
        snap:20,
        grid:0,
        // Drag.Move options
        droppables: '.x-tree-droppable',
        // Drag.Move Events
        onDrop: function(el, droppable) {
            if (droppable!=null){
                onDrop(el, droppable);
            }
        },
        onLeave: function(el, droppable) {
            onLeaveDropZone(droppable);
        },
        onEnter: function(el, droppable) {
            onEnterDropZone(droppable);
        },
        // Drag Events
        onStart: function(el) {
            drag.setStyles({
                'top': position.x,
                'left': position.y
            });
        },
        onDrag: function(el) {
            this.stop();
        },
        onComplete: function(el) {
        }
    });
    drag.setStyles({
        'top':0,
        'left':0,
        'position':'relative'
    });
}

function refreshDnd(){
    $$('.x-tree-dragable').each(function(drag) {
        prepareDnd(drag);
    });
    return false;
}

function onDropDropZone(el, droppable){
    var liEl = el.getParent();
    var lineEl = liEl.getParent();
    var spanEl = lineEl.getParent();
    el.setStyles({
        'top':0,
        'left':0,
        'position':'relative'
    });
    var lineDroppable = droppable.getParent();
    var spanDroppable = lineDroppable.getParent();
    var indentDroppable = droppable.style.paddingLeft;
    el.style.paddingLeft = gettheSize(indentDroppable)+"px";
    insertAfter(spanDroppable.getParent(), spanEl, spanDroppable);
}

function onDropDropFolder(el, droppable){
    var reg = new RegExp("(treenode)","g");
    var lineUL = $(droppable.id.replace(reg,"ul"));
    el.setStyles({
        'top':0,
        'left':0,
        'position':'relative'
    });
    var liEl = el.getParent();
    var lineEl = liEl.getParent();
    var spanEl = lineEl.getParent();
    var indentDroppable = droppable.style.paddingLeft;
    var newIndent = (gettheSize(indentDroppable) + 12);
    el.style.paddingLeft = newIndent+"px;";
    lineUL.appendChild(spanEl);
}

function onDrop(el, droppable){
    droppable.setStyles({
        'background-color':'transparent'
    });
    if (droppable.className.contains('x-tree-droppable-zone')){
        onDropDropZone(el, droppable);
        droppable.style.height = '2px';
    }
    else {
        onDropDropFolder(el, droppable);
    }
}  
    
function onEnterDropZone(droppable){
    if (droppable.className.contains('x-tree-droppable-zone')){
        droppable.style.height = '15px';
    }
    droppable.setStyles({
        'background-color':'#DDAFFF'
    });
}

function onLeaveDropZone(droppable){
    if (droppable.className.contains('x-tree-droppable-zone')){
        droppable.style.height = '2px';
    }
    droppable.setStyles({
        'background-color':'transparent'
    });
}

function createNewnode(){
    var liS = $$('.x-tree-node-el');
    var nodeTemplate = liS.getLast();
    nodeTemplate.getLast().innerHtml="New Node";
    
    var nodeli = nodeTemplate.getParent();
    var nodeline = nodeli.getParent();
    var nodeSpan = nodeline.getParent();
    nodeSpan.id("newNode");
    nodeline.getParent().appendChild(nodeSpan);
    return false;
}

function reloadTreeTableDND(){
    if (_DnD_FUNCTION_PREPARE){
        $$('.x-tree-dragable').each(function(drag) {
            prepareDnd(drag);
        });
    }
}