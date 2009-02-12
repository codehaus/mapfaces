function loadOpenCharts(id) {      
   document.chart = new OpenLayers.Map(id, {
       canvasContainer : document.getElementById(id+'Container')});
}

if (document.getElementById('canvas')) {
    if (window.addEvents)
        addEvents('canvas');
    if (window.OpenLayers && document.getElementById('canvasContainer') && window.loadOpenCharts)
        loadOpenCharts('canvas')
}