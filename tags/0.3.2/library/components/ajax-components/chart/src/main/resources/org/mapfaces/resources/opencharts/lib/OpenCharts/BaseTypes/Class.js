/* Copyright (c) 2006-2008 MetaCarta, Inc., published under the Clear BSD
 * license.  See http://svn.OpenCharts.org/trunk/OpenCharts/license.txt for the
 * full text of the license. */

/**
 * Constructor: OpenCharts.Class
 * Base class used to construct all other classes. Includes support for 
 *     multiple inheritance. 
 *     
 * This constructor is new in OpenCharts 2.5.  At OpenCharts 3.0, the old 
 *     syntax for creating classes and dealing with inheritance 
 *     will be removed.
 * 
 * To create a new OpenCharts-style class, use the following syntax:
 * > var MyClass = OpenCharts.Class(prototype);
 *
 * To create a new OpenCharts-style class with multiple inheritance, use the
 *     following syntax:
 * > var MyClass = OpenCharts.Class(Class1, Class2, prototype);
 *
 */
OpenCharts.Class = function() {
    var Class = function() {
        /**
         * This following condition can be removed at 3.0 - this is only for
         * backwards compatibility while the Class.inherit method is still
         * in use.  So at 3.0, the following three lines would be replaced with
         * simply:
         * this.initialize.apply(this, arguments);
         */
        if (arguments && arguments[0] != OpenCharts.Class.isPrototype) {
            this.initialize.apply(this, arguments);
        }
    };
    var extended = {};
    var parent;
    for(var i=0, len=arguments.length; i<len; ++i) {
        if(typeof arguments[i] == "function") {
            // get the prototype of the superclass
            parent = arguments[i].prototype;
        } else {
            // in this case we're extending with the prototype
            parent = arguments[i];
        }
        OpenCharts.Util.extend(extended, parent);
    }
    Class.prototype = extended;
    return Class;
};

/**
 * Property: isPrototype
 * *Deprecated*.  This is no longer needed and will be removed at 3.0.
 */
OpenCharts.Class.isPrototype = function () {};

/**
 * APIFunction: OpenCharts.create
 * *Deprecated*.  Old method to create an OpenCharts style class.  Use the
 *     <OpenCharts.Class> constructor instead.
 *
 * Returns:
 * An OpenCharts class
 */
OpenCharts.Class.create = function() {
    return function() {
        if (arguments && arguments[0] != OpenCharts.Class.isPrototype) {
            this.initialize.apply(this, arguments);
        }
    };
};


/**
 * APIFunction: inherit
 * *Deprecated*.  Old method to inherit from one or more OpenCharts style
 *     classes.  Use the <OpenCharts.Class> constructor instead.
 *
 * Parameters:
 * class - One or more classes can be provided as arguments
 *
 * Returns:
 * An object prototype
 */
OpenCharts.Class.inherit = function () {
    var superClass = arguments[0];
    var proto = new superClass(OpenCharts.Class.isPrototype);
    for (var i=1, len=arguments.length; i<len; i++) {
        if (typeof arguments[i] == "function") {
            var mixin = arguments[i];
            arguments[i] = new mixin(OpenCharts.Class.isPrototype);
        }
        OpenCharts.Util.extend(proto, arguments[i]);
    }
    return proto;
};
