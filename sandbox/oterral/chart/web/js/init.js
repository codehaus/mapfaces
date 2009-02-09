function loadOpenCharts(id) {
   
    var canvas = new OpenLayers.Map(id, {canvasContainer : document.getElementById(id+'Container')});
}

if (document.getElementById('canvas') && window.addEvents) {
    addEvents('canvas');
    if (window.OpenLayers && document.getElementById('canvasContainer') && window.loadOpenCharts)
        loadOpenCharts('canvas')
}