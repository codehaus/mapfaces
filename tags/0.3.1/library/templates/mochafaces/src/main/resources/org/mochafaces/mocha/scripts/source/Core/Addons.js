/*

Script: shortcut.js
	MUI shortcut for js functions.

Copyright:
	Copyright (c) 2007-2009 Kevin Delfour, <http://kevin.delfour.eu/>.

License:
	MIT-style license.

Note:


*/

/*
 * Navigation's functions shortcut
 */
MUI.updateEl = function(el, url, loadmethod){
    if(typeof loadmethod == "undefined"){
        loadmethod = 'xhr';
    }
    MUI.updateContent({
        'element' : $(el),
        'url' : url,
        'loadMethod': loadmethod
    })
}

/*
 * Tweak's functions shortcut
 */
MUI.TweakPanel = function(el){
    if ($(el)){
        if ($(el+'_header') != null)
            $(el+'_header').style.display="none";

        $(el).style.width="100%";
        $(el).style.height="100%";
        $(el).style.padding="0px";

        if ($(el+'_pad') != null){
            $(el+'_pad').style.width="100%";
            $(el+'_pad').style.height="100%";
            $(el+'_pad').style.padding="0px";
        }
    }
}