/*
Script: loading.js
License:
Copyright:
	(C) 2007 - 2008, Geomatys
 */

var headID = document.getElementsByTagName("head")[0];
var browser = navigator.appName;
var scr_mootools,scr_mootools_more, link;
/*
 * Test if Mootools is present on the page
 */
try{
    if (MooTools != null){
//console.log('Mootools already loaded');
}
}catch(err){
    scr_mootools = document.createElement('script');
    scr_mootools.setAttribute('type', 'text/javascript');
    scr_mootools.setAttribute('src', 'resource.jsf?r=/org/widgetfaces/resources/js/mootools-1.2-core.js');
    document.getElementsByTagName('head')[0].appendChild(scr_mootools);
}

try{
    if (MooToolsMore != null){
//console.log('Mootools already loaded');
}
}catch(err){
    scr_mootools_more = document.createElement('script');
    scr_mootools_more.setAttribute('type', 'text/javascript');
    scr_mootools_more.setAttribute('src', 'resource.jsf?r=/org/widgetfaces/resources/js/mootools-1.2-more.js');
    document.getElementsByTagName('head')[0].appendChild(scr_mootools_more);
}