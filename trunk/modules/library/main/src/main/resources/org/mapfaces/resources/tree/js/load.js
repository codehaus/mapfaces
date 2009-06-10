/*
Script: load.js
License:
Copyright:
	(C) 2007 - 2008, Geomatys
 */

var headID = document.getElementsByTagName("head")[0];
var browser = navigator.appName;
var script;
/*
 * Test if Mootools is present on the page
 */
try{
    if (MooTools != null){
        //console.log('Mootools already loaded');
    }
}catch(err){
    script = document.createElement('script');
    script.setAttribute('type', 'text/javascript');
    script.setAttribute('src', 'resource.jsf?r=/org/mapfaces/resources/tree/js/moo1.2.js');
    headID.appendChild(script);
    //    document.write('<script type="text/javascript" src="resource.jsf?r=/org/mapfaces/resources/tree/js/moo1.2.js"></script>');
}

/*
 * Test if utils.js is present on the page
 */
try{
    if (UtilsJs != null){
        //console.log('Treetable.js already loaded');
    }
}catch(err){
    script = document.createElement('script');
    script.setAttribute('type', 'text/javascript');
    script.setAttribute('src', 'resource.jsf?r=/org/mapfaces/resources/tree/js/utils.js');
    headID = document.getElementsByTagName("head")[0];
    headID.appendChild(script);
//    document.write('<script type="text/javascript" src="resource.jsf?r=/org/mapfaces/resources/tree/js/treeline.js"></script>');
}

/*
 * Test if treetable.js is present on the page
 */
try{
    if (TreeTableJs != null){
        //console.log('Treetable.js already loaded');
    }
}catch(err){
    script = document.createElement('script');
    script.setAttribute('type', 'text/javascript');
    script.setAttribute('src', 'resource.jsf?r=/org/mapfaces/resources/tree/js/treetable.js');
    headID = document.getElementsByTagName("head")[0];
    headID.appendChild(script);
    //    document.write('<script type="text/javascript" src="resource.jsf?r=/org/mapfaces/resources/tree/js/treetable.js"></script>');
}

/*
 * Test if treeline.js is present on the page
 */
try{
    if (TreeLineJs != null){
        //console.log('Treetable.js already loaded');
    }
}catch(err){
    script = document.createElement('script');
    script.setAttribute('type', 'text/javascript');
    script.setAttribute('src', 'resource.jsf?r=/org/mapfaces/resources/tree/js/treeline.js');
    headID = document.getElementsByTagName("head")[0];
    headID.appendChild(script);
//    document.write('<script type="text/javascript" src="resource.jsf?r=/org/mapfaces/resources/tree/js/treeline.js"></script>');
}

