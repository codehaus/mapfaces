

function loadMap(id) {
    if (id.indexOf(":") != -1) {
        id = id.replace(":","");
    }
    eval("if(window.loadMap"+id+")window.loadMap"+id+"();"); 
}
function loadMapAndZoom(id) {
    alert("je load " + window.maps[id]);
    loadMap(id);
    addControlsToMap(id);
    alert("je zoom " + window.maps[id]);
    window.maps[id].zoomToExtent(window.maps[id].currentExtent);
}
function addControlsToMap(id) {
    var controls = eval("if(window.controlToAdd"+id+")window.controlToAdd"+id);    
    if (controls) {
        for (var i = 0; i < controls.length; i++) {
            controls[i]();
        }
    }
}
function reloadAllMaps() {
    if (window.maps) {
        for (var i in window.maps) {
            var map = window.maps[i];
            if (map && map.div && map.div.style.display != 'none') {
                if (map.currentExtent)
                  map.zoomToExtent(map.currentExtent);
                else
                  map.zoomToMaxExtent();
            } else {
                loadMapAndZoom(i);    
            }
        }
    }
}

////Add onload function to window to zoom the map to the maxExtent
OpenLayers.Event.observe(window, 'load', reloadAllMaps);
//window.reloadAllMaps();

//window.test = function(){
//    alert('ici');
//}
//window.test();
