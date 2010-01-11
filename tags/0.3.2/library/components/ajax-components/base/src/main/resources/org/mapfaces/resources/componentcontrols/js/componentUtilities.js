var COMPONENTUTIL = new Array();

var ERROR_STYLE = "border: red 2px solid;";
var VALID_STYLE = "border: green 1px solid;";

var validateForm = true;

/**
 *
 */
COMPONENTUTIL.checkMore = function(object){

    var parent = object.parentNode;
    var checkbox = parent.childNodes[1];
    checkbox.setAttribute('checked',true);
    
}

/**
 * Function to validate integer
 */
COMPONENTUTIL.maxcharcheck = function(object, output, maxCar) {

    var valid = false;

    if (object.value != null){
        var str = object.value;

        if (str.length > maxCar){
            valid = false;
        }else{
            valid = true;
        }

        var carLess = maxCar - str.length;
        output.value = carLess + " car.";
    }

    return COMPONENTUTIL.validateStyle(object, valid);

}

/**
 * Function to validate integer
 */
COMPONENTUTIL.datacheck = function(object) {

    var valid = false;

    if (object.value != null){
        var str = object.value;
        console.log('Not implemented yet.')
    }

    return COMPONENTUTIL.validateStyle(object, valid);

}

/**
 * Function to validate integer
 */
COMPONENTUTIL.entcheck = function(object) {

    var valid = false;

    if (object.value != null){
        var str = object.value;

        if (str != parseInt(str)){
            valid = false;
        }else{
            valid = true;
        }

    }
    
    return COMPONENTUTIL.validateStyle(object, valid);

}

/**
 * Function to validate float
 */
COMPONENTUTIL.flocheck = function(object) {

    var valid = false;

    if (object.value != null){
        var str = object.value;

        if (str != parseFloat(str)){
            valid = false;
        }else{
            valid = true;
        }

    }

    return COMPONENTUTIL.validateStyle(object, valid);

}

/**
 * Function to validate email
 */
COMPONENTUTIL.emailcheck = function(object) {

    var valid = false;
    
    if (object.value != null){
        var str = object.value;
        var at="@";
        var dot=".";
        var lat=str.indexOf(at);
        var lstr=str.length;
        
        valid = true;

        if (str.indexOf(at)==-1){
            valid = false;
        }

        if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
            valid = false;
        }

        if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
            valid = false;
        }

        if (str.indexOf(at,(lat+1))!=-1){
            valid = false;
        }

        if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
            valid = false;
        }

        if (str.indexOf(dot,(lat+2))==-1){
            valid = false;
        }

        if (str.indexOf(" ")!=-1){
            valid = false;
        }    
        
    }

    return COMPONENTUTIL.validateStyle(object, valid);

}

/**
 * Function to validate form
 */
COMPONENTUTIL.validateForm = function(){

    return validateForm;

}


/**
 * Validation style tools
 */
COMPONENTUTIL.validateStyle = function(object, valid){

    if (valid){
        object.setAttribute('style', VALID_STYLE);
        validateForm = true;
        return true;
    }else{
        object.setAttribute('style', ERROR_STYLE);
        validateForm = false;
        return false;
    }
    
}