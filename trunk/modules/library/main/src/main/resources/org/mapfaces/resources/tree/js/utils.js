/*
Script: treeline.js
License:
Copyright:
	(C) 2007 - 2008, Geomatys
 */

var UtilsJs = {
    'version': '1.0.0',
    'build': ''
};
/*
 * String Utilities
 */
function gettheSize(str){
    if (str!=null){
        if (str =='auto'){
            return 0;
        }
        if (str.contains('px')){
            return  parseInt(str.substring(0,str.length - 2));
        }
        if (str.contains('em')){
            return  parseInt(str.substring(0,str.length - 2));
        }
        return partseInt(str);
    }else{
        return 0;
    }
}

/*
 * DOM Utilities
 */
function insertAfter(parent, node, referenceNode)
{
    parent.insertBefore(node, referenceNode.nextSibling);
}