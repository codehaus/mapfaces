/*
 * Environnement
 */


//declare your var as flag for your page, it is used for navigation in client side.
var tabs = {
    '_Home_Tabs': false ,
    '_App_Tabs': false
};
var setTabs = function(idTab) {
    for (var i in tabs) {
        if (typeof i != "function") {
            if (i == idTab) {
                if (tabs[i]) break;
                else tabs[i] = true;
            } else {
                tabs[i] = false;
            }
        }
    }
}
/*
 *
 */
window.addEvent('domready', function(){
    MochaUI.Desktop = new MochaUI.Desktop();
    
    MochaUI.Dock = new MochaUI.Dock({
        dockPosition: 'bottom'
    });
    
    MochaUI.Modal = new MochaUI.Modal();
    MochaUI.NewWindowsFromHTML = new MochaUI.NewWindowsFromHTML();

    initializeWindows();    
    
    //One time the Mocha page is bought click on the "home" link 
    //Fix bug :  when we are on the map page and we do F5 the home page doesn't appears
    document.getElementById('main_form:homea4jlink').onclick();
    
    MochaUI.Desktop.desktop.setStyles({
        //'background': '#fff',
        'visibility': 'visible'
    });
});

/*
 *
 */
window.addEvent('load', function(){
    //window.reloadAllMaps();
    });


/*
 * This runs when a person leaves your page.
 */
window.addEvent('unload', function(){
    if (MochaUI) MochaUI.garbageCleanUp();
});


/*
 *
 */
function clearPageWrapper(){
    if ($('pageWrapper') != null){
        while($('pageWrapper').hasChildNodes()){
            $('pageWrapper').removeChild($('pageWrapper').lastChild);
        }
    }
    $$('.mocha').each(function(div) {
        div.style.display="block";
    });
	if ($('dockSort') != null ) {
	    $('dockSort').getChildren().each(function(div) { 
		div.style.display="block";
	    });
	}
}

/*
 *
 */
function homePageWrapper(){
    clearPageWrapper();
    
    new MochaUI.Column({
        id: 'mainHomeColumn',
        placement: 'main'
    });
    
    $$('.mochaMainHomeColumn').each(function(div) {
        div.inject($('mainHomeColumn'));
    });

    $('mainHomeColumn').style.background="";

    $$('.mocha').each(function(div) {
        div.style.display="block";
    });
    $('dockSort').getChildren().each(function(div) { 
        div.style.display="block";
    });
    
    MochaUI.Desktop.desktop.setStyles({
        //'background': '#fff',
        'visibility': 'visible'
    });
    
}



/*
 * this function is called when application page is loaded.
 */
function webAppPageWrapper(){
    
    clearPageWrapper();

    new MochaUI.Column({
        id: 'left',
        placement: 'left',
        width: 290,
        resizeLimit: [290, 500]
    });

    new MochaUI.Column({
        id: 'main',
        placement: 'main',	
        width: null
    });
    new MochaUI.Column({
        id: 'right',
        placement: 'right',
        width: 290,
        resizeLimit: [290, 500]
    });

    new MochaUI.Panel({
        id: 'panel1',
        title: 'Layer Control',
        column: 'left',
        height: 420,
        padding:{
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
        }
    });
    
   
    new MochaUI.Panel({
        id: 'panel3',
        title: 'Outils de navigation / mesure',
        column: 'right',
        padding:{
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
        }
    });
    
    new MochaUI.Panel({
        id: 'panel2',
        title: "Outils d\'édition",
        column: 'right',
        padding:{
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
        }
    });
    
    MochaUI.Desktop.desktop.setStyles({
        //'background': '#fff',
        'visibility': 'visible'
    });
    
    $$('.mochaMainAppColumn').each(function(div) {
        div.inject($('main'));
    });
    
    $$('.mochaPanel1').each(function(div) {
        div.inject($('panel1_pad'));
    });
    
    $$('.mochaPanel2').each(function(div) {
        div.inject($('panel2_pad'));
    });
    $$('.mochaPanel3').each(function(div) {
        div.inject($('panel3_pad'));
    });
    
    $('panel1_pad').setStyles({
        'height' : '100%',
        'width' : '100%'
    });
    $('panel2_pad').setStyles({
        'height' : '100%',
        'width' : '100%'
    });
    $('panel3_pad').setStyles({
        'height' : '100%',
        'width' : '100%'
    });
  if (window.reloadAllMaps)
        window.reloadAllMaps();
};

function handleMeasurements(event) {
    var geometry = event.geometry;
    var units = event.units;
    var order = event.order;
    var measure = event.measure;
    var element = document.getElementById('output');
    var out = "";
    if(order == 1) {
        out += "measure: " + measure.toFixed(3) + " " + units;

    } else {
        out += "measure: " + measure.toFixed(3) + " " + units + "<sup>2</sup>";
    }
    element.innerHTML = out;
}

