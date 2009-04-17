/*
Script: loading.js
License:
Copyright:
	(C) 2007 - 2008, Geomatys
 */

var headID = document.getElementsByTagName("head")[0];
var browser = navigator.appName;
var scr_mootools;

try{
    if (MooTools != null){
//console.log('Mootools already loaded');
}
}catch(err){
    scr_mootools = document.createElement('script');
    scr_mootools.setAttribute('type', 'text/javascript');
    scr_mootools.setAttribute('src', 'resource.jsf?r=/org/widgetfaces/resources/compressed/mootools.min.js');
    document.getElementsByTagName('head')[0].appendChild(scr_mootools);
}