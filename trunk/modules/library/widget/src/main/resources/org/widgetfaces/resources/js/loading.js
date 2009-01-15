/*
Script: loading.js
License:
Copyright:
	(C) 2007 - 2008, Geomatys
 */

var headID = document.getElementsByTagName("head")[0];
var browser = navigator.appName;
var script, link;
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
    script.setAttribute('src', 'resource.jsf?r=/org/widgetfaces/resources/js/mootools-1.2-core.js');
    headID.appendChild(script);
}

try{
    if (MooToolsMore != null){
        //console.log('Mootools already loaded');
    }
}catch(err){
    script = document.createElement('script');
    script.setAttribute('type', 'text/javascript');
    script.setAttribute('src', 'resource.jsf?r=/org/widgetfaces/resources/js/mootools-1.2-more.js');
    headID.appendChild(script);
}