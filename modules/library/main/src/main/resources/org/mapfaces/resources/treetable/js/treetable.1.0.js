/*
 *
 */
var _COLOR_HIGHLIGHT          = "#DDAFFF";
var _COLOR_FOLDER_INITIAL     = "#CCCCCC";
var _COLOR_NODE_INITIAL       = "#AAAFFF";
var _DROP_ZONE_INITIAL_HEIGHT = 2;
var _DROP_ZONE_TWEEN_HEIGHT   = 20;
var _DRAG_FADE                = 0.5;

/*
 *
 */
window.addEvent('domready',function() {
    document.ondragstart = function () {
        return false;
    }
    $$('.dragable').each(function(drag) {
        position = drag.getPosition();
        new Drag.Move(drag, {
            snap: 20,
            grid:20,
            onStart:function(el) {
                child = drag.getChildren();
                for(i=0;i<child.length;i++){
                    if (child[i].id != 'treenode:'+drag.id.slice(3)){
                        child[i].setStyles({'display':'none'});
                    }
                }
                drag.setStyles({
                    'top': position.x,
                    'left': position.y
                })
            },
            onSnap: function(el){
                //Dragging
            },
            droppables: '.droppable',
            onDrop: function(el,droppable) {
                newPaddingLeft = (parseInt(droppable.getParent().getAttribute('depth')))* 12;
                newWidth = parseInt($('treenode:'+drag.id.slice(3)).style.width.slice(0,- 2)) + parseInt($('treenode:'+drag.id.slice(3)).style.paddingLeft.slice(0,- 2)) - newPaddingLeft;
                $('treenode:'+drag.id.slice(3)).setAttribute('style','width :'+newWidth+'px; padding-left :'+newPaddingLeft+'px;');
                child = drag.getChildren();
                for(i=0;i<child.length;i++){
                    if (child[i].id != 'treenode:'+drag.id.slice(3)){
                        if(child[i].id != "info:"+drag.id.slice(3))
                            child[i].setStyles({'display':'block'});
                    }
                }
                var dragthis = drag.getParent();
                oldDragPosition = dragthis.getParent().getParent();
                oldDragComponent = oldDragPosition.getAttribute('name');
                var dropIn,dropTo,oldPos,newPos;
                switch (droppable.getAttribute('where')) {
                    case "before":
                        dropTo = droppable.getParent();
                        dragthis.setAttribute('depth',dropTo.getAttribute('depth'));
                        drag.setAttribute('depth',dropTo.getAttribute('depth'));
                        dragthis.inject(dropTo,"before");
                        break;
                    case "firstitem":
                        dropIn = $(droppable.getAttribute('dest'));
                        dropTo = dropIn.childNodes[0];
                        dragthis.setAttribute('depth',dropTo.getAttribute('depth'));
                        drag.setAttribute('depth',dropTo.getAttribute('depth'));
                        dragthis.inject(dropTo,"after");
                        break;
                    case "beforeitem":
                        dropTo = droppable.getParent();
                        dragthis.inject(dropTo,"before");
                        break;
                    case "afteritem":
                        dropTo = droppable.getParent();
                        dragthis.inject(dropTo,"after");
                        break;
                    case "after":
                        dropTo = droppable.getParent();
                        dragthis.inject(dropTo,"after");
                        break;
                    default:
                        dropIn =  $('ul:'+droppable.id.slice(3));
                        dropTo = dropIn.lastChild;
                        dragthis.inject(dropTo,"after");
                        break;
                }

                if (droppable.hasAttribute('where')){
                    droppable.setStyles({
                        'background':_COLOR_FOLDER_INITIAL
                    });
                    droppable.tween('height',_DROP_ZONE_INITIAL_HEIGHT);
                }else{
                    droppable.setStyles({
                        'background':_COLOR_FOLDER_INITIAL
                    });
                }
                drag.setStyles({
                    'left':0,
                    'top':0,
                    'margin':0,
                    'position':'relative'
                });
                drag.fade(1);
                if (!droppable.hasAttribute('where')){
                    where = 'lastitem';
                }
                else {
                    where = droppable.getAttribute('where');
                }
                
                //_DND_NEW_PARENT_COMPONENT = 'org.mapfaces.ajax.DND_NEW_PARENT_COMPONENT';
                //_DND_NEW_PARENT_LINE      = 'org.mapfaces.ajax.DND_NEW_PARENT_LINE';
                //_DND_OLD_PARENT_COMPONENT = 'org.mapfaces.ajax.DND_OLD_PARENT_COMPONENT';
                //_DND_OLD_PARENT_LINE      = 'org.mapfaces.ajax.DND_OLD_PARENT_LINE';
                //_DND_POSITION_LINE        = 'org.mapfaces.ajax.DND_POSITION_LINE';

                var myHTMLRequest = new Request.HTML({  
                    url: $('ajax.server.request.URL').value,  
                    data: { 
                        'javax.faces.ViewState' : $('javax.faces.ViewState').value,
                        'org.mapfaces.ajax.AJAX_COMPONENT_VALUE':'true',
                        'org.mapfaces.ajax.AJAX_REQUEST'      :'true',
                        'org.mapfaces.ajax.AJAX_CONTAINER_ID' : dragthis.getAttribute('parent'),
                        'org.mapfaces.ajax.AJAX_COMPONENT_ID' : dragthis.getAttribute('name'),
//                        'org.mapfaces.ajax.DND_OLD_PARENT_LINE': "line:"+oldDragPosition.id.slice(3),
//                        'org.mapfaces.ajax.DND_OLD_PARENT_COMPONENT': oldDragComponent,
                        'org.mapfaces.ajax.DND_NEW_PARENT_LINE': droppable.id,
                        'org.mapfaces.ajax.DND_NEW_PARENT_COMPONENT': droppable.getAttribute('name'),
                        'org.mapfaces.ajax.DND_POSITION_LINE': where
                    }
                }).send();
            },
            onEnter: function(el,droppable) {
                if (droppable.hasAttribute('where')){
                    droppable.setStyles({
                        'background':_COLOR_HIGHLIGHT
                    });
                    droppable.highlight(_COLOR_HIGHLIGHT, _COLOR_FOLDER_INITIAL);
                    droppable.tween('height', _DROP_ZONE_TWEEN_HEIGHT);
                }else{
                    droppable.setStyles({
                        'background':_COLOR_HIGHLIGHT
                    });
                }
                drag.setStyles({
                    'background':_COLOR_NODE_INITIAL,
                    'z-index': 2,
                    'position':'absolute',
                    'margin':0,
                    'top': 0,
                    'left':0
                });
                drag.fade( _DRAG_FADE );
            },
            onLeave: function(el,droppable) {
                if (droppable.hasAttribute('where')){
                    droppable.setStyles({
                        'background':_COLOR_FOLDER_INITIAL
                    });
                    droppable.tween('height', _DROP_ZONE_INITIAL_HEIGHT);
                }else{
                    droppable.setStyles({
                        'background':_COLOR_FOLDER_INITIAL
                    });
                }
            },
            onComplete:function(el,droppable) {
            }
        });
        drag.setStyles({
            'top':0,
            'left':0,
            'position':'relative'
        });
    });
});