//MooTools, <http://mootools.net>, My Object Oriented (JavaScript) Tools. Copyright (c) 2006-2008 Valerio Proietti, <http://mad4milk.net>, MIT Style License.

var MooTools={version:"1.2.0",build:""};var Native=function(J){J=J||{};var F=J.afterImplement||function(){};var G=J.generics;G=(G!==false);var H=J.legacy;
var E=J.initialize;var B=J.protect;var A=J.name;var C=E||H;C.constructor=Native;C.$family={name:"native"};if(H&&E){C.prototype=H.prototype;}C.prototype.constructor=C;
if(A){var D=A.toLowerCase();C.prototype.$family={name:D};Native.typize(C,D);}var I=function(M,K,N,L){if(!B||L||!M.prototype[K]){M.prototype[K]=N;}if(G){Native.genericize(M,K,B);
}F.call(M,K,N);return M;};C.implement=function(L,K,N){if(typeof L=="string"){return I(this,L,K,N);}for(var M in L){I(this,M,L[M],K);}return this;};C.alias=function(M,K,N){if(typeof M=="string"){M=this.prototype[M];
if(M){I(this,K,M,N);}}else{for(var L in M){this.alias(L,M[L],K);}}return this;};return C;};Native.implement=function(D,C){for(var B=0,A=D.length;B<A;B++){D[B].implement(C);
}};Native.genericize=function(B,C,A){if((!A||!B[C])&&typeof B.prototype[C]=="function"){B[C]=function(){var D=Array.prototype.slice.call(arguments);return B.prototype[C].apply(D.shift(),D);
};}};Native.typize=function(A,B){if(!A.type){A.type=function(C){return($type(C)===B);};}};Native.alias=function(E,B,A,F){for(var D=0,C=E.length;D<C;D++){E[D].alias(B,A,F);
}};(function(B){for(var A in B){Native.typize(B[A],A);}})({"boolean":Boolean,"native":Native,object:Object});(function(B){for(var A in B){new Native({name:A,initialize:B[A],protect:true});
}})({String:String,Function:Function,Number:Number,Array:Array,RegExp:RegExp,Date:Date});(function(B,A){for(var C=A.length;C--;C){Native.genericize(B,A[C],true);
}return arguments.callee;})(Array,["pop","push","reverse","shift","sort","splice","unshift","concat","join","slice","toString","valueOf","indexOf","lastIndexOf"])(String,["charAt","charCodeAt","concat","indexOf","lastIndexOf","match","replace","search","slice","split","substr","substring","toLowerCase","toUpperCase","valueOf"]);
function $chk(A){return !!(A||A===0);}function $clear(A){clearTimeout(A);clearInterval(A);return null;}function $defined(A){return(A!=undefined);}function $empty(){}function $arguments(A){return function(){return arguments[A];
};}function $lambda(A){return(typeof A=="function")?A:function(){return A;};}function $extend(C,A){for(var B in (A||{})){C[B]=A[B];}return C;}function $unlink(C){var B;
switch($type(C)){case"object":B={};for(var E in C){B[E]=$unlink(C[E]);}break;case"hash":B=$unlink(C.getClean());break;case"array":B=[];for(var D=0,A=C.length;
D<A;D++){B[D]=$unlink(C[D]);}break;default:return C;}return B;}function $merge(){var E={};for(var D=0,A=arguments.length;D<A;D++){var B=arguments[D];if($type(B)!="object"){continue;
}for(var C in B){var G=B[C],F=E[C];E[C]=(F&&$type(G)=="object"&&$type(F)=="object")?$merge(F,G):$unlink(G);}}return E;}function $pick(){for(var B=0,A=arguments.length;
B<A;B++){if(arguments[B]!=undefined){return arguments[B];}}return null;}function $random(B,A){return Math.floor(Math.random()*(A-B+1)+B);}function $splat(B){var A=$type(B);
return(A)?((A!="array"&&A!="arguments")?[B]:B):[];}var $time=Date.now||function(){return new Date().getTime();};function $try(){for(var B=0,A=arguments.length;
B<A;B++){try{return arguments[B]();}catch(C){}}return null;}function $type(A){if(A==undefined){return false;}if(A.$family){return(A.$family.name=="number"&&!isFinite(A))?false:A.$family.name;
}if(A.nodeName){switch(A.nodeType){case 1:return"element";case 3:return(/\S/).test(A.nodeValue)?"textnode":"whitespace";}}else{if(typeof A.length=="number"){if(A.callee){return"arguments";
}else{if(A.item){return"collection";}}}}return typeof A;}var Hash=new Native({name:"Hash",initialize:function(A){if($type(A)=="hash"){A=$unlink(A.getClean());
}for(var B in A){this[B]=A[B];}return this;}});Hash.implement({getLength:function(){var B=0;for(var A in this){if(this.hasOwnProperty(A)){B++;}}return B;
},forEach:function(B,C){for(var A in this){if(this.hasOwnProperty(A)){B.call(C,this[A],A,this);}}},getClean:function(){var B={};for(var A in this){if(this.hasOwnProperty(A)){B[A]=this[A];
}}return B;}});Hash.alias("forEach","each");function $H(A){return new Hash(A);}Array.implement({forEach:function(C,D){for(var B=0,A=this.length;B<A;B++){C.call(D,this[B],B,this);
}}});Array.alias("forEach","each");function $A(C){if(C.item){var D=[];for(var B=0,A=C.length;B<A;B++){D[B]=C[B];}return D;}return Array.prototype.slice.call(C);
}function $each(C,B,D){var A=$type(C);((A=="arguments"||A=="collection"||A=="array")?Array:Hash).each(C,B,D);}var Browser=new Hash({Engine:{name:"unknown",version:""},Platform:{name:(navigator.platform.match(/mac|win|linux/i)||["other"])[0].toLowerCase()},Features:{xpath:!!(document.evaluate),air:!!(window.runtime)},Plugins:{}});
if(window.opera){Browser.Engine={name:"presto",version:(document.getElementsByClassName)?950:925};}else{if(window.ActiveXObject){Browser.Engine={name:"trident",version:(window.XMLHttpRequest)?5:4};
}else{if(!navigator.taintEnabled){Browser.Engine={name:"webkit",version:(Browser.Features.xpath)?420:419};}else{if(document.getBoxObjectFor!=null){Browser.Engine={name:"gecko",version:(document.getElementsByClassName)?19:18};
}}}}Browser.Engine[Browser.Engine.name]=Browser.Engine[Browser.Engine.name+Browser.Engine.version]=true;if(window.orientation!=undefined){Browser.Platform.name="ipod";
}Browser.Platform[Browser.Platform.name]=true;Browser.Request=function(){return $try(function(){return new XMLHttpRequest();},function(){return new ActiveXObject("MSXML2.XMLHTTP");
});};Browser.Features.xhr=!!(Browser.Request());Browser.Plugins.Flash=(function(){var A=($try(function(){return navigator.plugins["Shockwave Flash"].description;
},function(){return new ActiveXObject("ShockwaveFlash.ShockwaveFlash").GetVariable("$version");})||"0 r0").match(/\d+/g);return{version:parseInt(A[0]||0+"."+A[1]||0),build:parseInt(A[2]||0)};
})();function $exec(B){if(!B){return B;}if(window.execScript){window.execScript(B);}else{var A=document.createElement("script");A.setAttribute("type","text/javascript");
A.text=B;document.head.appendChild(A);document.head.removeChild(A);}return B;}Native.UID=1;var $uid=(Browser.Engine.trident)?function(A){return(A.uid||(A.uid=[Native.UID++]))[0];
}:function(A){return A.uid||(A.uid=Native.UID++);};var Window=new Native({name:"Window",legacy:(Browser.Engine.trident)?null:window.Window,initialize:function(A){$uid(A);
if(!A.Element){A.Element=$empty;if(Browser.Engine.webkit){A.document.createElement("iframe");}A.Element.prototype=(Browser.Engine.webkit)?window["[[DOMElement.prototype]]"]:{};
}return $extend(A,Window.Prototype);},afterImplement:function(B,A){window[B]=Window.Prototype[B]=A;}});Window.Prototype={$family:{name:"window"}};new Window(window);
var Document=new Native({name:"Document",legacy:(Browser.Engine.trident)?null:window.Document,initialize:function(A){$uid(A);A.head=A.getElementsByTagName("head")[0];
A.html=A.getElementsByTagName("html")[0];A.window=A.defaultView||A.parentWindow;if(Browser.Engine.trident4){$try(function(){A.execCommand("BackgroundImageCache",false,true);
});}return $extend(A,Document.Prototype);},afterImplement:function(B,A){document[B]=Document.Prototype[B]=A;}});Document.Prototype={$family:{name:"document"}};
new Document(document);Array.implement({every:function(C,D){for(var B=0,A=this.length;B<A;B++){if(!C.call(D,this[B],B,this)){return false;}}return true;
},filter:function(D,E){var C=[];for(var B=0,A=this.length;B<A;B++){if(D.call(E,this[B],B,this)){C.push(this[B]);}}return C;},clean:function(){return this.filter($defined);
},indexOf:function(C,D){var A=this.length;for(var B=(D<0)?Math.max(0,A+D):D||0;B<A;B++){if(this[B]===C){return B;}}return -1;},map:function(D,E){var C=[];
for(var B=0,A=this.length;B<A;B++){C[B]=D.call(E,this[B],B,this);}return C;},some:function(C,D){for(var B=0,A=this.length;B<A;B++){if(C.call(D,this[B],B,this)){return true;
}}return false;},associate:function(C){var D={},B=Math.min(this.length,C.length);for(var A=0;A<B;A++){D[C[A]]=this[A];}return D;},link:function(C){var A={};
for(var E=0,B=this.length;E<B;E++){for(var D in C){if(C[D](this[E])){A[D]=this[E];delete C[D];break;}}}return A;},contains:function(A,B){return this.indexOf(A,B)!=-1;
},extend:function(C){for(var B=0,A=C.length;B<A;B++){this.push(C[B]);}return this;},getLast:function(){return(this.length)?this[this.length-1]:null;},getRandom:function(){return(this.length)?this[$random(0,this.length-1)]:null;
},include:function(A){if(!this.contains(A)){this.push(A);}return this;},combine:function(C){for(var B=0,A=C.length;B<A;B++){this.include(C[B]);}return this;
},erase:function(B){for(var A=this.length;A--;A){if(this[A]===B){this.splice(A,1);}}return this;},empty:function(){this.length=0;return this;},flatten:function(){var D=[];
for(var B=0,A=this.length;B<A;B++){var C=$type(this[B]);if(!C){continue;}D=D.concat((C=="array"||C=="collection"||C=="arguments")?Array.flatten(this[B]):this[B]);
}return D;},hexToRgb:function(B){if(this.length!=3){return null;}var A=this.map(function(C){if(C.length==1){C+=C;}return C.toInt(16);});return(B)?A:"rgb("+A+")";
},rgbToHex:function(D){if(this.length<3){return null;}if(this.length==4&&this[3]==0&&!D){return"transparent";}var B=[];for(var A=0;A<3;A++){var C=(this[A]-0).toString(16);
B.push((C.length==1)?"0"+C:C);}return(D)?B:"#"+B.join("");}});Function.implement({extend:function(A){for(var B in A){this[B]=A[B];}return this;},create:function(B){var A=this;
B=B||{};return function(D){var C=B.arguments;C=(C!=undefined)?$splat(C):Array.slice(arguments,(B.event)?1:0);if(B.event){C=[D||window.event].extend(C);
}var E=function(){return A.apply(B.bind||null,C);};if(B.delay){return setTimeout(E,B.delay);}if(B.periodical){return setInterval(E,B.periodical);}if(B.attempt){return $try(E);
}return E();};},pass:function(A,B){return this.create({arguments:A,bind:B});},attempt:function(A,B){return this.create({arguments:A,bind:B,attempt:true})();
},bind:function(B,A){return this.create({bind:B,arguments:A});},bindWithEvent:function(B,A){return this.create({bind:B,event:true,arguments:A});},delay:function(B,C,A){return this.create({delay:B,bind:C,arguments:A})();
},periodical:function(A,C,B){return this.create({periodical:A,bind:C,arguments:B})();},run:function(A,B){return this.apply(B,$splat(A));}});Number.implement({limit:function(B,A){return Math.min(A,Math.max(B,this));
},round:function(A){A=Math.pow(10,A||0);return Math.round(this*A)/A;},times:function(B,C){for(var A=0;A<this;A++){B.call(C,A,this);}},toFloat:function(){return parseFloat(this);
},toInt:function(A){return parseInt(this,A||10);}});Number.alias("times","each");(function(B){var A={};B.each(function(C){if(!Number[C]){A[C]=function(){return Math[C].apply(null,[this].concat($A(arguments)));
};}});Number.implement(A);})(["abs","acos","asin","atan","atan2","ceil","cos","exp","floor","log","max","min","pow","sin","sqrt","tan"]);String.implement({test:function(A,B){return((typeof A=="string")?new RegExp(A,B):A).test(this);
},contains:function(A,B){return(B)?(B+this+B).indexOf(B+A+B)>-1:this.indexOf(A)>-1;},trim:function(){return this.replace(/^\s+|\s+$/g,"");},clean:function(){return this.replace(/\s+/g," ").trim();
},camelCase:function(){return this.replace(/-\D/g,function(A){return A.charAt(1).toUpperCase();});},hyphenate:function(){return this.replace(/[A-Z]/g,function(A){return("-"+A.charAt(0).toLowerCase());
});},capitalize:function(){return this.replace(/\b[a-z]/g,function(A){return A.toUpperCase();});},escapeRegExp:function(){return this.replace(/([-.*+?^${}()|[\]\/\\])/g,"\\$1");
},toInt:function(A){return parseInt(this,A||10);},toFloat:function(){return parseFloat(this);},hexToRgb:function(B){var A=this.match(/^#?(\w{1,2})(\w{1,2})(\w{1,2})$/);
return(A)?A.slice(1).hexToRgb(B):null;},rgbToHex:function(B){var A=this.match(/\d{1,3}/g);return(A)?A.rgbToHex(B):null;},stripScripts:function(B){var A="";
var C=this.replace(/<script[^>]*>([\s\S]*?)<\/script>/gi,function(){A+=arguments[1]+"\n";return"";});if(B===true){$exec(A);}else{if($type(B)=="function"){B(A,C);
}}return C;},substitute:function(A,B){return this.replace(B||(/\\?\{([^}]+)\}/g),function(D,C){if(D.charAt(0)=="\\"){return D.slice(1);}return(A[C]!=undefined)?A[C]:"";
});}});Hash.implement({has:Object.prototype.hasOwnProperty,keyOf:function(B){for(var A in this){if(this.hasOwnProperty(A)&&this[A]===B){return A;}}return null;
},hasValue:function(A){return(Hash.keyOf(this,A)!==null);},extend:function(A){Hash.each(A,function(C,B){Hash.set(this,B,C);},this);return this;},combine:function(A){Hash.each(A,function(C,B){Hash.include(this,B,C);
},this);return this;},erase:function(A){if(this.hasOwnProperty(A)){delete this[A];}return this;},get:function(A){return(this.hasOwnProperty(A))?this[A]:null;
},set:function(A,B){if(!this[A]||this.hasOwnProperty(A)){this[A]=B;}return this;},empty:function(){Hash.each(this,function(B,A){delete this[A];},this);
return this;},include:function(B,C){var A=this[B];if(A==undefined){this[B]=C;}return this;},map:function(B,C){var A=new Hash;Hash.each(this,function(E,D){A.set(D,B.call(C,E,D,this));
},this);return A;},filter:function(B,C){var A=new Hash;Hash.each(this,function(E,D){if(B.call(C,E,D,this)){A.set(D,E);}},this);return A;},every:function(B,C){for(var A in this){if(this.hasOwnProperty(A)&&!B.call(C,this[A],A)){return false;
}}return true;},some:function(B,C){for(var A in this){if(this.hasOwnProperty(A)&&B.call(C,this[A],A)){return true;}}return false;},getKeys:function(){var A=[];
Hash.each(this,function(C,B){A.push(B);});return A;},getValues:function(){var A=[];Hash.each(this,function(B){A.push(B);});return A;},toQueryString:function(A){var B=[];
Hash.each(this,function(F,E){if(A){E=A+"["+E+"]";}var D;switch($type(F)){case"object":D=Hash.toQueryString(F,E);break;case"array":var C={};F.each(function(H,G){C[G]=H;
});D=Hash.toQueryString(C,E);break;default:D=E+"="+encodeURIComponent(F);}if(F!=undefined){B.push(D);}});return B.join("&");}});Hash.alias({keyOf:"indexOf",hasValue:"contains"});
var Event=new Native({name:"Event",initialize:function(A,F){F=F||window;var K=F.document;A=A||F.event;if(A.$extended){return A;}this.$extended=true;var J=A.type;
var G=A.target||A.srcElement;while(G&&G.nodeType==3){G=G.parentNode;}if(J.test(/key/)){var B=A.which||A.keyCode;var M=Event.Keys.keyOf(B);if(J=="keydown"){var D=B-111;
if(D>0&&D<13){M="f"+D;}}M=M||String.fromCharCode(B).toLowerCase();}else{if(J.match(/(click|mouse|menu)/i)){K=(!K.compatMode||K.compatMode=="CSS1Compat")?K.html:K.body;
var I={x:A.pageX||A.clientX+K.scrollLeft,y:A.pageY||A.clientY+K.scrollTop};var C={x:(A.pageX)?A.pageX-F.pageXOffset:A.clientX,y:(A.pageY)?A.pageY-F.pageYOffset:A.clientY};
if(J.match(/DOMMouseScroll|mousewheel/)){var H=(A.wheelDelta)?A.wheelDelta/120:-(A.detail||0)/3;}var E=(A.which==3)||(A.button==2);var L=null;if(J.match(/over|out/)){switch(J){case"mouseover":L=A.relatedTarget||A.fromElement;
break;case"mouseout":L=A.relatedTarget||A.toElement;}if(!(function(){while(L&&L.nodeType==3){L=L.parentNode;}return true;}).create({attempt:Browser.Engine.gecko})()){L=false;
}}}}return $extend(this,{event:A,type:J,page:I,client:C,rightClick:E,wheel:H,relatedTarget:L,target:G,code:B,key:M,shift:A.shiftKey,control:A.ctrlKey,alt:A.altKey,meta:A.metaKey});
}});Event.Keys=new Hash({enter:13,up:38,down:40,left:37,right:39,esc:27,space:32,backspace:8,tab:9,"delete":46});Event.implement({stop:function(){return this.stopPropagation().preventDefault();
},stopPropagation:function(){if(this.event.stopPropagation){this.event.stopPropagation();}else{this.event.cancelBubble=true;}return this;},preventDefault:function(){if(this.event.preventDefault){this.event.preventDefault();
}else{this.event.returnValue=false;}return this;}});var Class=new Native({name:"Class",initialize:function(B){B=B||{};var A=function(E){for(var D in this){this[D]=$unlink(this[D]);
}for(var F in Class.Mutators){if(!this[F]){continue;}Class.Mutators[F](this,this[F]);delete this[F];}this.constructor=A;if(E===$empty){return this;}var C=(this.initialize)?this.initialize.apply(this,arguments):this;
if(this.options&&this.options.initialize){this.options.initialize.call(this);}return C;};$extend(A,this);A.constructor=Class;A.prototype=B;return A;}});
Class.implement({implement:function(){Class.Mutators.Implements(this.prototype,Array.slice(arguments));return this;}});Class.Mutators={Implements:function(A,B){$splat(B).each(function(C){$extend(A,($type(C)=="class")?new C($empty):C);
});},Extends:function(self,klass){var instance=new klass($empty);delete instance.parent;delete instance.parentOf;for(var key in instance){var current=self[key],previous=instance[key];
if(current==undefined){self[key]=previous;continue;}var ctype=$type(current),ptype=$type(previous);if(ctype!=ptype){continue;}switch(ctype){case"function":if(!arguments.callee.caller){self[key]=eval("("+String(current).replace(/\bthis\.parent\(\s*(\))?/g,function(full,close){return"arguments.callee._parent_.call(this"+(close||", ");
})+")");}self[key]._parent_=previous;break;case"object":self[key]=$merge(previous,current);}}self.parent=function(){return arguments.callee.caller._parent_.apply(this,arguments);
};self.parentOf=function(descendant){return descendant._parent_.apply(this,Array.slice(arguments,1));};}};var Chain=new Class({chain:function(){this.$chain=(this.$chain||[]).extend(arguments);
return this;},callChain:function(){return(this.$chain&&this.$chain.length)?this.$chain.shift().apply(this,arguments):false;},clearChain:function(){if(this.$chain){this.$chain.empty();
}return this;}});var Events=new Class({addEvent:function(C,B,A){C=Events.removeOn(C);if(B!=$empty){this.$events=this.$events||{};this.$events[C]=this.$events[C]||[];
this.$events[C].include(B);if(A){B.internal=true;}}return this;},addEvents:function(A){for(var B in A){this.addEvent(B,A[B]);}return this;},fireEvent:function(C,B,A){C=Events.removeOn(C);
if(!this.$events||!this.$events[C]){return this;}this.$events[C].each(function(D){D.create({bind:this,delay:A,"arguments":B})();},this);return this;},removeEvent:function(B,A){B=Events.removeOn(B);
if(!this.$events||!this.$events[B]){return this;}if(!A.internal){this.$events[B].erase(A);}return this;},removeEvents:function(C){for(var D in this.$events){if(C&&C!=D){continue;
}var B=this.$events[D];for(var A=B.length;A--;A){this.removeEvent(D,B[A]);}}return this;}});Events.removeOn=function(A){return A.replace(/^on([A-Z])/,function(B,C){return C.toLowerCase();
});};var Options=new Class({setOptions:function(){this.options=$merge.run([this.options].extend(arguments));if(!this.addEvent){return this;}for(var A in this.options){if($type(this.options[A])!="function"||!(/^on[A-Z]/).test(A)){continue;
}this.addEvent(A,this.options[A]);delete this.options[A];}return this;}});Document.implement({newElement:function(A,B){if(Browser.Engine.trident&&B){["name","type","checked"].each(function(C){if(!B[C]){return ;
}A+=" "+C+'="'+B[C]+'"';if(C!="checked"){delete B[C];}});A="<"+A+">";}return $.element(this.createElement(A)).set(B);},newTextNode:function(A){return this.createTextNode(A);
},getDocument:function(){return this;},getWindow:function(){return this.defaultView||this.parentWindow;},purge:function(){var C=this.getElementsByTagName("*");
for(var B=0,A=C.length;B<A;B++){Browser.freeMem(C[B]);}}});var Element=new Native({name:"Element",legacy:window.Element,initialize:function(A,B){var C=Element.Constructors.get(A);
if(C){return C(B);}if(typeof A=="string"){return document.newElement(A,B);}return $(A).set(B);},afterImplement:function(A,B){if(!Array[A]){Elements.implement(A,Elements.multi(A));
}Element.Prototype[A]=B;}});Element.Prototype={$family:{name:"element"}};Element.Constructors=new Hash;var IFrame=new Native({name:"IFrame",generics:false,initialize:function(){var E=Array.link(arguments,{properties:Object.type,iframe:$defined});
var C=E.properties||{};var B=$(E.iframe)||false;var D=C.onload||$empty;delete C.onload;C.id=C.name=$pick(C.id,C.name,B.id,B.name,"IFrame_"+$time());B=new Element(B||"iframe",C);
var A=function(){var F=$try(function(){return B.contentWindow.location.host;});if(F&&F==window.location.host){var H=new Window(B.contentWindow);var G=new Document(B.contentWindow.document);
$extend(H.Element.prototype,Element.Prototype);}D.call(B.contentWindow,B.contentWindow.document);};(!window.frames[C.id])?B.addListener("load",A):A();return B;
}});var Elements=new Native({initialize:function(F,B){B=$extend({ddup:true,cash:true},B);F=F||[];if(B.ddup||B.cash){var G={},E=[];for(var C=0,A=F.length;
C<A;C++){var D=$.element(F[C],!B.cash);if(B.ddup){if(G[D.uid]){continue;}G[D.uid]=true;}E.push(D);}F=E;}return(B.cash)?$extend(F,this):F;}});Elements.implement({filter:function(A,B){if(!A){return this;
}return new Elements(Array.filter(this,(typeof A=="string")?function(C){return C.match(A);}:A,B));}});Elements.multi=function(A){return function(){var B=[];
var F=true;for(var D=0,C=this.length;D<C;D++){var E=this[D][A].apply(this[D],arguments);B.push(E);if(F){F=($type(E)=="element");}}return(F)?new Elements(B):B;
};};Window.implement({$:function(B,C){if(B&&B.$family&&B.uid){return B;}var A=$type(B);return($[A])?$[A](B,C,this.document):null;},$$:function(A){if(arguments.length==1&&typeof A=="string"){return this.document.getElements(A);
}var F=[];var C=Array.flatten(arguments);for(var D=0,B=C.length;D<B;D++){var E=C[D];switch($type(E)){case"element":E=[E];break;case"string":E=this.document.getElements(E,true);
break;default:E=false;}if(E){F.extend(E);}}return new Elements(F);},getDocument:function(){return this.document;},getWindow:function(){return this;}});
$.string=function(C,B,A){C=A.getElementById(C);return(C)?$.element(C,B):null;};$.element=function(A,D){$uid(A);if(!D&&!A.$family&&!(/^object|embed$/i).test(A.tagName)){var B=Element.Prototype;
for(var C in B){A[C]=B[C];}}return A;};$.object=function(B,C,A){if(B.toElement){return $.element(B.toElement(A),C);}return null;};$.textnode=$.whitespace=$.window=$.document=$arguments(0);
Native.implement([Element,Document],{getElement:function(A,B){return $(this.getElements(A,true)[0]||null,B);},getElements:function(A,D){A=A.split(",");
var C=[];var B=(A.length>1);A.each(function(E){var F=this.getElementsByTagName(E.trim());(B)?C.extend(F):C=F;},this);return new Elements(C,{ddup:B,cash:!D});
}});Element.Storage={get:function(A){return(this[A]||(this[A]={}));}};Element.Inserters=new Hash({before:function(B,A){if(A.parentNode){A.parentNode.insertBefore(B,A);
}},after:function(B,A){if(!A.parentNode){return ;}var C=A.nextSibling;(C)?A.parentNode.insertBefore(B,C):A.parentNode.appendChild(B);},bottom:function(B,A){A.appendChild(B);
},top:function(B,A){var C=A.firstChild;(C)?A.insertBefore(B,C):A.appendChild(B);}});Element.Inserters.inside=Element.Inserters.bottom;Element.Inserters.each(function(C,B){var A=B.capitalize();
Element.implement("inject"+A,function(D){C(this,$(D,true));return this;});Element.implement("grab"+A,function(D){C($(D,true),this);return this;});});Element.implement({getDocument:function(){return this.ownerDocument;
},getWindow:function(){return this.ownerDocument.getWindow();},getElementById:function(D,C){var B=this.ownerDocument.getElementById(D);if(!B){return null;
}for(var A=B.parentNode;A!=this;A=A.parentNode){if(!A){return null;}}return $.element(B,C);},set:function(D,B){switch($type(D)){case"object":for(var C in D){this.set(C,D[C]);
}break;case"string":var A=Element.Properties.get(D);(A&&A.set)?A.set.apply(this,Array.slice(arguments,1)):this.setProperty(D,B);}return this;},get:function(B){var A=Element.Properties.get(B);
return(A&&A.get)?A.get.apply(this,Array.slice(arguments,1)):this.getProperty(B);},erase:function(B){var A=Element.Properties.get(B);(A&&A.erase)?A.erase.apply(this,Array.slice(arguments,1)):this.removeProperty(B);
return this;},match:function(A){return(!A||Element.get(this,"tag")==A);},inject:function(B,A){Element.Inserters.get(A||"bottom")(this,$(B,true));return this;
},wraps:function(B,A){B=$(B,true);return this.replaces(B).grab(B,A);},grab:function(B,A){Element.Inserters.get(A||"bottom")($(B,true),this);return this;
},appendText:function(B,A){return this.grab(this.getDocument().newTextNode(B),A);},adopt:function(){Array.flatten(arguments).each(function(A){A=$(A,true);
if(A){this.appendChild(A);}},this);return this;},dispose:function(){return(this.parentNode)?this.parentNode.removeChild(this):this;},clone:function(D,C){switch($type(this)){case"element":var H={};
for(var G=0,E=this.attributes.length;G<E;G++){var B=this.attributes[G],L=B.nodeName.toLowerCase();if(Browser.Engine.trident&&(/input/i).test(this.tagName)&&(/width|height/).test(L)){continue;
}var K=(L=="style"&&this.style)?this.style.cssText:B.nodeValue;if(!$chk(K)||L=="uid"||(L=="id"&&!C)){continue;}if(K!="inherit"&&["string","number"].contains($type(K))){H[L]=K;
}}var J=new Element(this.nodeName.toLowerCase(),H);if(D!==false){for(var I=0,F=this.childNodes.length;I<F;I++){var A=Element.clone(this.childNodes[I],true,C);
if(A){J.grab(A);}}}return J;case"textnode":return document.newTextNode(this.nodeValue);}return null;},replaces:function(A){A=$(A,true);A.parentNode.replaceChild(this,A);
return this;},hasClass:function(A){return this.className.contains(A," ");},addClass:function(A){if(!this.hasClass(A)){this.className=(this.className+" "+A).clean();
}return this;},removeClass:function(A){this.className=this.className.replace(new RegExp("(^|\\s)"+A+"(?:\\s|$)"),"$1").clean();return this;},toggleClass:function(A){return this.hasClass(A)?this.removeClass(A):this.addClass(A);
},getComputedStyle:function(B){if(this.currentStyle){return this.currentStyle[B.camelCase()];}var A=this.getWindow().getComputedStyle(this,null);return(A)?A.getPropertyValue([B.hyphenate()]):null;
},empty:function(){$A(this.childNodes).each(function(A){Browser.freeMem(A);Element.empty(A);Element.dispose(A);},this);return this;},destroy:function(){Browser.freeMem(this.empty().dispose());
return null;},getSelected:function(){return new Elements($A(this.options).filter(function(A){return A.selected;}));},toQueryString:function(){var A=[];
this.getElements("input, select, textarea").each(function(B){if(!B.name||B.disabled){return ;}var C=(B.tagName.toLowerCase()=="select")?Element.getSelected(B).map(function(D){return D.value;
}):((B.type=="radio"||B.type=="checkbox")&&!B.checked)?null:B.value;$splat(C).each(function(D){if(D){A.push(B.name+"="+encodeURIComponent(D));}});});return A.join("&");
},getProperty:function(C){var B=Element.Attributes,A=B.Props[C];var D=(A)?this[A]:this.getAttribute(C,2);return(B.Bools[C])?!!D:(A)?D:D||null;},getProperties:function(){var A=$A(arguments);
return A.map(function(B){return this.getProperty(B);},this).associate(A);},setProperty:function(D,E){var C=Element.Attributes,B=C.Props[D],A=$defined(E);
if(B&&C.Bools[D]){E=(E||!A)?true:false;}else{if(!A){return this.removeProperty(D);}}(B)?this[B]=E:this.setAttribute(D,E);return this;},setProperties:function(A){for(var B in A){this.setProperty(B,A[B]);
}return this;},removeProperty:function(D){var C=Element.Attributes,B=C.Props[D],A=(B&&C.Bools[D]);(B)?this[B]=(A)?false:"":this.removeAttribute(D);return this;
},removeProperties:function(){Array.each(arguments,this.removeProperty,this);return this;}});(function(){var A=function(D,B,I,C,F,H){var E=D[I||B];var G=[];
while(E){if(E.nodeType==1&&(!C||Element.match(E,C))){G.push(E);if(!F){break;}}E=E[B];}return(F)?new Elements(G,{ddup:false,cash:!H}):$(G[0],H);};Element.implement({getPrevious:function(B,C){return A(this,"previousSibling",null,B,false,C);
},getAllPrevious:function(B,C){return A(this,"previousSibling",null,B,true,C);},getNext:function(B,C){return A(this,"nextSibling",null,B,false,C);},getAllNext:function(B,C){return A(this,"nextSibling",null,B,true,C);
},getFirst:function(B,C){return A(this,"nextSibling","firstChild",B,false,C);},getLast:function(B,C){return A(this,"previousSibling","lastChild",B,false,C);
},getParent:function(B,C){return A(this,"parentNode",null,B,false,C);},getParents:function(B,C){return A(this,"parentNode",null,B,true,C);},getChildren:function(B,C){return A(this,"nextSibling","firstChild",B,true,C);
},hasChild:function(B){B=$(B,true);return(!!B&&$A(this.getElementsByTagName(B.tagName)).contains(B));}});})();Element.Properties=new Hash;Element.Properties.style={set:function(A){this.style.cssText=A;
},get:function(){return this.style.cssText;},erase:function(){this.style.cssText="";}};Element.Properties.tag={get:function(){return this.tagName.toLowerCase();
}};Element.Properties.href={get:function(){return(!this.href)?null:this.href.replace(new RegExp("^"+document.location.protocol+"//"+document.location.host),"");
}};Element.Properties.html={set:function(){return this.innerHTML=Array.flatten(arguments).join("");}};Native.implement([Element,Window,Document],{addListener:function(B,A){if(this.addEventListener){this.addEventListener(B,A,false);
}else{this.attachEvent("on"+B,A);}return this;},removeListener:function(B,A){if(this.removeEventListener){this.removeEventListener(B,A,false);}else{this.detachEvent("on"+B,A);
}return this;},retrieve:function(B,A){var D=Element.Storage.get(this.uid);var C=D[B];if($defined(A)&&!$defined(C)){C=D[B]=A;}return $pick(C);},store:function(B,A){var C=Element.Storage.get(this.uid);
C[B]=A;return this;},eliminate:function(A){var B=Element.Storage.get(this.uid);delete B[A];return this;}});Element.Attributes=new Hash({Props:{html:"innerHTML","class":"className","for":"htmlFor",text:(Browser.Engine.trident)?"innerText":"textContent"},Bools:["compact","nowrap","ismap","declare","noshade","checked","disabled","readonly","multiple","selected","noresize","defer"],Camels:["value","accessKey","cellPadding","cellSpacing","colSpan","frameBorder","maxLength","readOnly","rowSpan","tabIndex","useMap"]});
Browser.freeMem=function(A){if(!A){return ;}if(Browser.Engine.trident&&(/object/i).test(A.tagName)){for(var B in A){if(typeof A[B]=="function"){A[B]=$empty;
}}Element.dispose(A);}if(A.uid&&A.removeEvents){A.removeEvents();}};(function(B){var C=B.Bools,A=B.Camels;B.Bools=C=C.associate(C);Hash.extend(Hash.combine(B.Props,C),A.associate(A.map(function(D){return D.toLowerCase();
})));B.erase("Camels");})(Element.Attributes);window.addListener("unload",function(){window.removeListener("unload",arguments.callee);document.purge();
if(Browser.Engine.trident){CollectGarbage();}});Element.Properties.events={set:function(A){this.addEvents(A);}};Native.implement([Element,Window,Document],{addEvent:function(E,G){var H=this.retrieve("events",{});
H[E]=H[E]||{keys:[],values:[]};if(H[E].keys.contains(G)){return this;}H[E].keys.push(G);var F=E,A=Element.Events.get(E),C=G,I=this;if(A){if(A.onAdd){A.onAdd.call(this,G);
}if(A.condition){C=function(J){if(A.condition.call(this,J)){return G.call(this,J);}return false;};}F=A.base||F;}var D=function(){return G.call(I);};var B=Element.NativeEvents[F]||0;
if(B){if(B==2){D=function(J){J=new Event(J,I.getWindow());if(C.call(I,J)===false){J.stop();}};}this.addListener(F,D);}H[E].values.push(D);return this;},removeEvent:function(D,C){var B=this.retrieve("events");
if(!B||!B[D]){return this;}var G=B[D].keys.indexOf(C);if(G==-1){return this;}var A=B[D].keys.splice(G,1)[0];var F=B[D].values.splice(G,1)[0];var E=Element.Events.get(D);
if(E){if(E.onRemove){E.onRemove.call(this,C);}D=E.base||D;}return(Element.NativeEvents[D])?this.removeListener(D,F):this;},addEvents:function(A){for(var B in A){this.addEvent(B,A[B]);
}return this;},removeEvents:function(B){var A=this.retrieve("events");if(!A){return this;}if(!B){for(var C in A){this.removeEvents(C);}A=null;}else{if(A[B]){while(A[B].keys[0]){this.removeEvent(B,A[B].keys[0]);
}A[B]=null;}}return this;},fireEvent:function(D,B,A){var C=this.retrieve("events");if(!C||!C[D]){return this;}C[D].keys.each(function(E){E.create({bind:this,delay:A,"arguments":B})();
},this);return this;},cloneEvents:function(D,A){D=$(D);var C=D.retrieve("events");if(!C){return this;}if(!A){for(var B in C){this.cloneEvents(D,B);}}else{if(C[A]){C[A].keys.each(function(E){this.addEvent(A,E);
},this);}}return this;}});Element.NativeEvents={click:2,dblclick:2,mouseup:2,mousedown:2,contextmenu:2,mousewheel:2,DOMMouseScroll:2,mouseover:2,mouseout:2,mousemove:2,selectstart:2,selectend:2,keydown:2,keypress:2,keyup:2,focus:2,blur:2,change:2,reset:2,select:2,submit:2,load:1,unload:1,beforeunload:2,resize:1,move:1,DOMContentLoaded:1,readystatechange:1,error:1,abort:1,scroll:1};
(function(){var A=function(B){var C=B.relatedTarget;if(C==undefined){return true;}if(C===false){return false;}return($type(this)!="document"&&C!=this&&C.prefix!="xul"&&!this.hasChild(C));
};Element.Events=new Hash({mouseenter:{base:"mouseover",condition:A},mouseleave:{base:"mouseout",condition:A},mousewheel:{base:(Browser.Engine.gecko)?"DOMMouseScroll":"mousewheel"}});
})();Element.Properties.styles={set:function(A){this.setStyles(A);}};Element.Properties.opacity={set:function(A,B){if(!B){if(A==0){if(this.style.visibility!="hidden"){this.style.visibility="hidden";
}}else{if(this.style.visibility!="visible"){this.style.visibility="visible";}}}if(!this.currentStyle||!this.currentStyle.hasLayout){this.style.zoom=1;}if(Browser.Engine.trident){this.style.filter=(A==1)?"":"alpha(opacity="+A*100+")";
}this.style.opacity=A;this.store("opacity",A);},get:function(){return this.retrieve("opacity",1);}};Element.implement({setOpacity:function(A){return this.set("opacity",A,true);
},getOpacity:function(){return this.get("opacity");},setStyle:function(B,A){switch(B){case"opacity":return this.set("opacity",parseFloat(A));case"float":B=(Browser.Engine.trident)?"styleFloat":"cssFloat";
}B=B.camelCase();if($type(A)!="string"){var C=(Element.Styles.get(B)||"@").split(" ");A=$splat(A).map(function(E,D){if(!C[D]){return"";}return($type(E)=="number")?C[D].replace("@",Math.round(E)):E;
}).join(" ");}else{if(A==String(Number(A))){A=Math.round(A);}}this.style[B]=A;return this;},getStyle:function(G){switch(G){case"opacity":return this.get("opacity");
case"float":G=(Browser.Engine.trident)?"styleFloat":"cssFloat";}G=G.camelCase();var A=this.style[G];if(!$chk(A)){A=[];for(var F in Element.ShortStyles){if(G!=F){continue;
}for(var E in Element.ShortStyles[F]){A.push(this.getStyle(E));}return A.join(" ");}A=this.getComputedStyle(G);}if(A){A=String(A);var C=A.match(/rgba?\([\d\s,]+\)/);
if(C){A=A.replace(C[0],C[0].rgbToHex());}}if(Browser.Engine.presto||(Browser.Engine.trident&&!$chk(parseInt(A)))){if(G.test(/^(height|width)$/)){var B=(G=="width")?["left","right"]:["top","bottom"],D=0;
B.each(function(H){D+=this.getStyle("border-"+H+"-width").toInt()+this.getStyle("padding-"+H).toInt();},this);return this["offset"+G.capitalize()]-D+"px";
}if(Browser.Engine.presto&&String(A).test("px")){return A;}if(G.test(/(border(.+)Width|margin|padding)/)){return"0px";}}return A;},setStyles:function(B){for(var A in B){this.setStyle(A,B[A]);
}return this;},getStyles:function(){var A={};Array.each(arguments,function(B){A[B]=this.getStyle(B);},this);return A;}});Element.Styles=new Hash({left:"@px",top:"@px",bottom:"@px",right:"@px",width:"@px",height:"@px",maxWidth:"@px",maxHeight:"@px",minWidth:"@px",minHeight:"@px",backgroundColor:"rgb(@, @, @)",backgroundPosition:"@px @px",color:"rgb(@, @, @)",fontSize:"@px",letterSpacing:"@px",lineHeight:"@px",clip:"rect(@px @px @px @px)",margin:"@px @px @px @px",padding:"@px @px @px @px",border:"@px @ rgb(@, @, @) @px @ rgb(@, @, @) @px @ rgb(@, @, @)",borderWidth:"@px @px @px @px",borderStyle:"@ @ @ @",borderColor:"rgb(@, @, @) rgb(@, @, @) rgb(@, @, @) rgb(@, @, @)",zIndex:"@",zoom:"@",fontWeight:"@",textIndent:"@px",opacity:"@"});
Element.ShortStyles={margin:{},padding:{},border:{},borderWidth:{},borderStyle:{},borderColor:{}};["Top","Right","Bottom","Left"].each(function(G){var F=Element.ShortStyles;
var B=Element.Styles;["margin","padding"].each(function(H){var I=H+G;F[H][I]=B[I]="@px";});var E="border"+G;F.border[E]=B[E]="@px @ rgb(@, @, @)";var D=E+"Width",A=E+"Style",C=E+"Color";
F[E]={};F.borderWidth[D]=F[E][D]=B[D]="@px";F.borderStyle[A]=F[E][A]=B[A]="@";F.borderColor[C]=F[E][C]=B[C]="rgb(@, @, @)";});(function(){Element.implement({scrollTo:function(H,I){if(B(this)){this.getWindow().scrollTo(H,I);
}else{this.scrollLeft=H;this.scrollTop=I;}return this;},getSize:function(){if(B(this)){return this.getWindow().getSize();}return{x:this.offsetWidth,y:this.offsetHeight};
},getScrollSize:function(){if(B(this)){return this.getWindow().getScrollSize();}return{x:this.scrollWidth,y:this.scrollHeight};},getScroll:function(){if(B(this)){return this.getWindow().getScroll();
}return{x:this.scrollLeft,y:this.scrollTop};},getScrolls:function(){var I=this,H={x:0,y:0};while(I&&!B(I)){H.x+=I.scrollLeft;H.y+=I.scrollTop;I=I.parentNode;
}return H;},getOffsetParent:function(){var H=this;if(B(H)){return null;}if(!Browser.Engine.trident){return H.offsetParent;}while((H=H.parentNode)&&!B(H)){if(D(H,"position")!="static"){return H;
}}return null;},getOffsets:function(){var I=this,H={x:0,y:0};if(B(this)){return H;}while(I&&!B(I)){H.x+=I.offsetLeft;H.y+=I.offsetTop;if(Browser.Engine.gecko){if(!F(I)){H.x+=C(I);
H.y+=G(I);}var J=I.parentNode;if(J&&D(J,"overflow")!="visible"){H.x+=C(J);H.y+=G(J);}}else{if(I!=this&&(Browser.Engine.trident||Browser.Engine.webkit)){H.x+=C(I);
H.y+=G(I);}}I=I.offsetParent;if(Browser.Engine.trident){while(I&&!I.currentStyle.hasLayout){I=I.offsetParent;}}}if(Browser.Engine.gecko&&!F(this)){H.x-=C(this);
H.y-=G(this);}return H;},getPosition:function(K){if(B(this)){return{x:0,y:0};}var L=this.getOffsets(),I=this.getScrolls();var H={x:L.x-I.x,y:L.y-I.y};var J=(K&&(K=$(K)))?K.getPosition():{x:0,y:0};
return{x:H.x-J.x,y:H.y-J.y};},getCoordinates:function(J){if(B(this)){return this.getWindow().getCoordinates();}var H=this.getPosition(J),I=this.getSize();
var K={left:H.x,top:H.y,width:I.x,height:I.y};K.right=K.left+K.width;K.bottom=K.top+K.height;return K;},computePosition:function(H){return{left:H.x-E(this,"margin-left"),top:H.y-E(this,"margin-top")};
},position:function(H){return this.setStyles(this.computePosition(H));}});Native.implement([Document,Window],{getSize:function(){var I=this.getWindow();
if(Browser.Engine.presto||Browser.Engine.webkit){return{x:I.innerWidth,y:I.innerHeight};}var H=A(this);return{x:H.clientWidth,y:H.clientHeight};},getScroll:function(){var I=this.getWindow();
var H=A(this);return{x:I.pageXOffset||H.scrollLeft,y:I.pageYOffset||H.scrollTop};},getScrollSize:function(){var I=A(this);var H=this.getSize();return{x:Math.max(I.scrollWidth,H.x),y:Math.max(I.scrollHeight,H.y)};
},getPosition:function(){return{x:0,y:0};},getCoordinates:function(){var H=this.getSize();return{top:0,left:0,bottom:H.y,right:H.x,height:H.y,width:H.x};
}});var D=Element.getComputedStyle;function E(H,I){return D(H,I).toInt()||0;}function F(H){return D(H,"-moz-box-sizing")=="border-box";}function G(H){return E(H,"border-top-width");
}function C(H){return E(H,"border-left-width");}function B(H){return(/^(?:body|html)$/i).test(H.tagName);}function A(H){var I=H.getDocument();return(!I.compatMode||I.compatMode=="CSS1Compat")?I.html:I.body;
}})();Native.implement([Window,Document,Element],{getHeight:function(){return this.getSize().y;},getWidth:function(){return this.getSize().x;},getScrollTop:function(){return this.getScroll().y;
},getScrollLeft:function(){return this.getScroll().x;},getScrollHeight:function(){return this.getScrollSize().y;},getScrollWidth:function(){return this.getScrollSize().x;
},getTop:function(){return this.getPosition().y;},getLeft:function(){return this.getPosition().x;}});Native.implement([Document,Element],{getElements:function(H,G){H=H.split(",");
var C,E={};for(var D=0,B=H.length;D<B;D++){var A=H[D],F=Selectors.Utils.search(this,A,E);if(D!=0&&F.item){F=$A(F);}C=(D==0)?F:(C.item)?$A(C).concat(F):C.concat(F);
}return new Elements(C,{ddup:(H.length>1),cash:!G});}});Element.implement({match:function(B){if(!B){return true;}var D=Selectors.Utils.parseTagAndID(B);
var A=D[0],E=D[1];if(!Selectors.Filters.byID(this,E)||!Selectors.Filters.byTag(this,A)){return false;}var C=Selectors.Utils.parseSelector(B);return(C)?Selectors.Utils.filter(this,C,{}):true;
}});var Selectors={Cache:{nth:{},parsed:{}}};Selectors.RegExps={id:(/#([\w-]+)/),tag:(/^(\w+|\*)/),quick:(/^(\w+|\*)$/),splitter:(/\s*([+>~\s])\s*([a-zA-Z#.*:\[])/g),combined:(/\.([\w-]+)|\[(\w+)(?:([!*^$~|]?=)["']?(.*?)["']?)?\]|:([\w-]+)(?:\(["']?(.*?)?["']?\)|$)/g)};
Selectors.Utils={chk:function(B,C){if(!C){return true;}var A=$uid(B);if(!C[A]){return C[A]=true;}return false;},parseNthArgument:function(F){if(Selectors.Cache.nth[F]){return Selectors.Cache.nth[F];
}var C=F.match(/^([+-]?\d*)?([a-z]+)?([+-]?\d*)?$/);if(!C){return false;}var E=parseInt(C[1]);var B=(E||E===0)?E:1;var D=C[2]||false;var A=parseInt(C[3])||0;
if(B!=0){A--;while(A<1){A+=B;}while(A>=B){A-=B;}}else{B=A;D="index";}switch(D){case"n":C={a:B,b:A,special:"n"};break;case"odd":C={a:2,b:0,special:"n"};
break;case"even":C={a:2,b:1,special:"n"};break;case"first":C={a:0,special:"index"};break;case"last":C={special:"last-child"};break;case"only":C={special:"only-child"};
break;default:C={a:(B-1),special:"index"};}return Selectors.Cache.nth[F]=C;},parseSelector:function(E){if(Selectors.Cache.parsed[E]){return Selectors.Cache.parsed[E];
}var D,H={classes:[],pseudos:[],attributes:[]};while((D=Selectors.RegExps.combined.exec(E))){var I=D[1],G=D[2],F=D[3],B=D[4],C=D[5],J=D[6];if(I){H.classes.push(I);
}else{if(C){var A=Selectors.Pseudo.get(C);if(A){H.pseudos.push({parser:A,argument:J});}else{H.attributes.push({name:C,operator:"=",value:J});}}else{if(G){H.attributes.push({name:G,operator:F,value:B});
}}}}if(!H.classes.length){delete H.classes;}if(!H.attributes.length){delete H.attributes;}if(!H.pseudos.length){delete H.pseudos;}if(!H.classes&&!H.attributes&&!H.pseudos){H=null;
}return Selectors.Cache.parsed[E]=H;},parseTagAndID:function(B){var A=B.match(Selectors.RegExps.tag);var C=B.match(Selectors.RegExps.id);return[(A)?A[1]:"*",(C)?C[1]:false];
},filter:function(F,C,E){var D;if(C.classes){for(D=C.classes.length;D--;D){var G=C.classes[D];if(!Selectors.Filters.byClass(F,G)){return false;}}}if(C.attributes){for(D=C.attributes.length;
D--;D){var B=C.attributes[D];if(!Selectors.Filters.byAttribute(F,B.name,B.operator,B.value)){return false;}}}if(C.pseudos){for(D=C.pseudos.length;D--;D){var A=C.pseudos[D];
if(!Selectors.Filters.byPseudo(F,A.parser,A.argument,E)){return false;}}}return true;},getByTagAndID:function(B,A,D){if(D){var C=(B.getElementById)?B.getElementById(D,true):Element.getElementById(B,D,true);
return(C&&Selectors.Filters.byTag(C,A))?[C]:[];}else{return B.getElementsByTagName(A);}},search:function(J,I,O){var B=[];var C=I.trim().replace(Selectors.RegExps.splitter,function(Z,Y,X){B.push(Y);
return":)"+X;}).split(":)");var K,F,E,V;for(var U=0,Q=C.length;U<Q;U++){var T=C[U];if(U==0&&Selectors.RegExps.quick.test(T)){K=J.getElementsByTagName(T);
continue;}var A=B[U-1];var L=Selectors.Utils.parseTagAndID(T);var W=L[0],M=L[1];if(U==0){K=Selectors.Utils.getByTagAndID(J,W,M);}else{var D={},H=[];for(var S=0,R=K.length;
S<R;S++){H=Selectors.Getters[A](H,K[S],W,M,D);}K=H;}var G=Selectors.Utils.parseSelector(T);if(G){E=[];for(var P=0,N=K.length;P<N;P++){V=K[P];if(Selectors.Utils.filter(V,G,O)){E.push(V);
}}K=E;}}return K;}};Selectors.Getters={" ":function(H,G,I,A,E){var D=Selectors.Utils.getByTagAndID(G,I,A);for(var C=0,B=D.length;C<B;C++){var F=D[C];if(Selectors.Utils.chk(F,E)){H.push(F);
}}return H;},">":function(H,G,I,A,F){var C=Selectors.Utils.getByTagAndID(G,I,A);for(var E=0,D=C.length;E<D;E++){var B=C[E];if(B.parentNode==G&&Selectors.Utils.chk(B,F)){H.push(B);
}}return H;},"+":function(C,B,A,E,D){while((B=B.nextSibling)){if(B.nodeType==1){if(Selectors.Utils.chk(B,D)&&Selectors.Filters.byTag(B,A)&&Selectors.Filters.byID(B,E)){C.push(B);
}break;}}return C;},"~":function(C,B,A,E,D){while((B=B.nextSibling)){if(B.nodeType==1){if(!Selectors.Utils.chk(B,D)){break;}if(Selectors.Filters.byTag(B,A)&&Selectors.Filters.byID(B,E)){C.push(B);
}}}return C;}};Selectors.Filters={byTag:function(B,A){return(A=="*"||(B.tagName&&B.tagName.toLowerCase()==A));},byID:function(A,B){return(!B||(A.id&&A.id==B));
},byClass:function(B,A){return(B.className&&B.className.contains&&B.className.contains(A," "));},byPseudo:function(A,D,C,B){return D.call(A,C,B);},byAttribute:function(C,D,B,E){var A=Element.prototype.getProperty.call(C,D);
if(!A){return false;}if(!B||E==undefined){return true;}switch(B){case"=":return(A==E);case"*=":return(A.contains(E));case"^=":return(A.substr(0,E.length)==E);
case"$=":return(A.substr(A.length-E.length)==E);case"!=":return(A!=E);case"~=":return A.contains(E," ");case"|=":return A.contains(E,"-");}return false;
}};Selectors.Pseudo=new Hash({empty:function(){return !(this.innerText||this.textContent||"").length;},not:function(A){return !Element.match(this,A);},contains:function(A){return(this.innerText||this.textContent||"").contains(A);
},"first-child":function(){return Selectors.Pseudo.index.call(this,0);},"last-child":function(){var A=this;while((A=A.nextSibling)){if(A.nodeType==1){return false;
}}return true;},"only-child":function(){var B=this;while((B=B.previousSibling)){if(B.nodeType==1){return false;}}var A=this;while((A=A.nextSibling)){if(A.nodeType==1){return false;
}}return true;},"nth-child":function(G,E){G=(G==undefined)?"n":G;var C=Selectors.Utils.parseNthArgument(G);if(C.special!="n"){return Selectors.Pseudo[C.special].call(this,C.a,E);
}var F=0;E.positions=E.positions||{};var D=$uid(this);if(!E.positions[D]){var B=this;while((B=B.previousSibling)){if(B.nodeType!=1){continue;}F++;var A=E.positions[$uid(B)];
if(A!=undefined){F=A+F;break;}}E.positions[D]=F;}return(E.positions[D]%C.a==C.b);},index:function(A){var B=this,C=0;while((B=B.previousSibling)){if(B.nodeType==1&&++C>A){return false;
}}return(C==A);},even:function(B,A){return Selectors.Pseudo["nth-child"].call(this,"2n+1",A);},odd:function(B,A){return Selectors.Pseudo["nth-child"].call(this,"2n",A);
}});Element.Events.domready={onAdd:function(A){if(Browser.loaded){A.call(this);}}};(function(){var B=function(){if(Browser.loaded){return ;}Browser.loaded=true;
window.fireEvent("domready");document.fireEvent("domready");};switch(Browser.Engine.name){case"webkit":(function(){(["loaded","complete"].contains(document.readyState))?B():arguments.callee.delay(50);
})();break;case"trident":var A=document.createElement("div");(function(){($try(function(){A.doScroll("left");return $(A).inject(document.body).set("html","temp").dispose();
}))?B():arguments.callee.delay(50);})();break;default:window.addEvent("load",B);document.addEvent("DOMContentLoaded",B);}})();var JSON=new Hash({encode:function(B){switch($type(B)){case"string":return'"'+B.replace(/[\x00-\x1f\\"]/g,JSON.$replaceChars)+'"';
case"array":return"["+String(B.map(JSON.encode).filter($defined))+"]";case"object":case"hash":var A=[];Hash.each(B,function(E,D){var C=JSON.encode(E);if(C){A.push(JSON.encode(D)+":"+C);
}});return"{"+A+"}";case"number":case"boolean":return String(B);case false:return"null";}return null;},$specialChars:{"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"},$replaceChars:function(A){return JSON.$specialChars[A]||"\\u00"+Math.floor(A.charCodeAt()/16).toString(16)+(A.charCodeAt()%16).toString(16);
},decode:function(string,secure){if($type(string)!="string"||!string.length){return null;}if(secure&&!(/^[,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t]*$/).test(string.replace(/\\./g,"@").replace(/"[^"\\\n\r]*"/g,""))){return null;
}return eval("("+string+")");}});Native.implement([Hash,Array,String,Number],{toJSON:function(){return JSON.encode(this);}});var Cookie=new Class({Implements:Options,options:{path:false,domain:false,duration:false,secure:false,document:document},initialize:function(B,A){this.key=B;
this.setOptions(A);},write:function(B){B=encodeURIComponent(B);if(this.options.domain){B+="; domain="+this.options.domain;}if(this.options.path){B+="; path="+this.options.path;
}if(this.options.duration){var A=new Date();A.setTime(A.getTime()+this.options.duration*24*60*60*1000);B+="; expires="+A.toGMTString();}if(this.options.secure){B+="; secure";
}this.options.document.cookie=this.key+"="+B;return this;},read:function(){var A=this.options.document.cookie.match("(?:^|;)\\s*"+this.key.escapeRegExp()+"=([^;]*)");
return(A)?decodeURIComponent(A[1]):null;},dispose:function(){new Cookie(this.key,$merge(this.options,{duration:-1})).write("");return this;}});Cookie.write=function(B,C,A){return new Cookie(B,A).write(C);
};Cookie.read=function(A){return new Cookie(A).read();};Cookie.dispose=function(B,A){return new Cookie(B,A).dispose();};var Swiff=new Class({Implements:[Options],options:{id:null,height:1,width:1,container:null,properties:{},params:{quality:"high",allowScriptAccess:"always",wMode:"transparent",swLiveConnect:true},callBacks:{},vars:{}},toElement:function(){return this.object;
},initialize:function(L,M){this.instance="Swiff_"+$time();this.setOptions(M);M=this.options;var B=this.id=M.id||this.instance;var A=$(M.container);Swiff.CallBacks[this.instance]={};
var E=M.params,G=M.vars,F=M.callBacks;var H=$extend({height:M.height,width:M.width},M.properties);var K=this;for(var D in F){Swiff.CallBacks[this.instance][D]=(function(N){return function(){return N.apply(K.object,arguments);
};})(F[D]);G[D]="Swiff.CallBacks."+this.instance+"."+D;}E.flashVars=Hash.toQueryString(G);if(Browser.Engine.trident){H.classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000";
E.movie=L;}else{H.type="application/x-shockwave-flash";H.data=L;}var J='<object id="'+B+'"';for(var I in H){J+=" "+I+'="'+H[I]+'"';}J+=">";for(var C in E){if(E[C]){J+='<param name="'+C+'" value="'+E[C]+'" />';
}}J+="</object>";this.object=((A)?A.empty():new Element("div")).set("html",J).firstChild;},replaces:function(A){A=$(A,true);A.parentNode.replaceChild(this.toElement(),A);
return this;},inject:function(A){$(A,true).appendChild(this.toElement());return this;},remote:function(){return Swiff.remote.apply(Swiff,[this.toElement()].extend(arguments));
}});Swiff.CallBacks={};Swiff.remote=function(obj,fn){var rs=obj.CallFunction('<invoke name="'+fn+'" returntype="javascript">'+__flash__argumentsToXML(arguments,2)+"</invoke>");
return eval(rs);};var Fx=new Class({Implements:[Chain,Events,Options],options:{fps:50,unit:false,duration:500,link:"ignore",transition:function(A){return -(Math.cos(Math.PI*A)-1)/2;
}},initialize:function(A){this.subject=this.subject||this;this.setOptions(A);this.options.duration=Fx.Durations[this.options.duration]||this.options.duration.toInt();
var B=this.options.wait;if(B===false){this.options.link="cancel";}},step:function(){var A=$time();if(A<this.time+this.options.duration){var B=this.options.transition((A-this.time)/this.options.duration);
this.set(this.compute(this.from,this.to,B));}else{this.set(this.compute(this.from,this.to,1));this.complete();}},set:function(A){return A;},compute:function(C,B,A){return Fx.compute(C,B,A);
},check:function(A){if(!this.timer){return true;}switch(this.options.link){case"cancel":this.cancel();return true;case"chain":this.chain(A.bind(this,Array.slice(arguments,1)));
return false;}return false;},start:function(B,A){if(!this.check(arguments.callee,B,A)){return this;}this.from=B;this.to=A;this.time=0;this.startTimer();
this.onStart();return this;},complete:function(){if(this.stopTimer()){this.onComplete();}return this;},cancel:function(){if(this.stopTimer()){this.onCancel();
}return this;},onStart:function(){this.fireEvent("start",this.subject);},onComplete:function(){this.fireEvent("complete",this.subject);if(!this.callChain()){this.fireEvent("chainComplete",this.subject);
}},onCancel:function(){this.fireEvent("cancel",this.subject).clearChain();},pause:function(){this.stopTimer();return this;},resume:function(){this.startTimer();
return this;},stopTimer:function(){if(!this.timer){return false;}this.time=$time()-this.time;this.timer=$clear(this.timer);return true;},startTimer:function(){if(this.timer){return false;
}this.time=$time()-this.time;this.timer=this.step.periodical(Math.round(1000/this.options.fps),this);return true;}});Fx.compute=function(C,B,A){return(B-C)*A+C;
};Fx.Durations={"short":250,normal:500,"long":1000};Fx.CSS=new Class({Extends:Fx,prepare:function(D,E,B){B=$splat(B);var C=B[1];if(!$chk(C)){B[1]=B[0];
B[0]=D.getStyle(E);}var A=B.map(this.parse);return{from:A[0],to:A[1]};},parse:function(A){A=$lambda(A)();A=(typeof A=="string")?A.split(" "):$splat(A);
return A.map(function(C){C=String(C);var B=false;Fx.CSS.Parsers.each(function(F,E){if(B){return ;}var D=F.parse(C);if($chk(D)){B={value:D,parser:F};}});
B=B||{value:C,parser:Fx.CSS.Parsers.String};return B;});},compute:function(D,C,B){var A=[];(Math.min(D.length,C.length)).times(function(E){A.push({value:D[E].parser.compute(D[E].value,C[E].value,B),parser:D[E].parser});
});A.$family={name:"fx:css:value"};return A;},serve:function(C,B){if($type(C)!="fx:css:value"){C=this.parse(C);}var A=[];C.each(function(D){A=A.concat(D.parser.serve(D.value,B));
});return A;},render:function(A,D,C,B){A.setStyle(D,this.serve(C,B));},search:function(A){if(Fx.CSS.Cache[A]){return Fx.CSS.Cache[A];}var B={};Array.each(document.styleSheets,function(E,D){var C=E.href;
if(C&&C.contains("://")&&!C.contains(document.domain)){return ;}var F=E.rules||E.cssRules;Array.each(F,function(I,G){if(!I.style){return ;}var H=(I.selectorText)?I.selectorText.replace(/^\w+/,function(J){return J.toLowerCase();
}):null;if(!H||!H.test("^"+A+"$")){return ;}Element.Styles.each(function(K,J){if(!I.style[J]||Element.ShortStyles[J]){return ;}K=String(I.style[J]);B[J]=(K.test(/^rgb/))?K.rgbToHex():K;
});});});return Fx.CSS.Cache[A]=B;}});Fx.CSS.Cache={};Fx.CSS.Parsers=new Hash({Color:{parse:function(A){if(A.match(/^#[0-9a-f]{3,6}$/i)){return A.hexToRgb(true);
}return((A=A.match(/(\d+),\s*(\d+),\s*(\d+)/)))?[A[1],A[2],A[3]]:false;},compute:function(C,B,A){return C.map(function(E,D){return Math.round(Fx.compute(C[D],B[D],A));
});},serve:function(A){return A.map(Number);}},Number:{parse:parseFloat,compute:Fx.compute,serve:function(B,A){return(A)?B+A:B;}},String:{parse:$lambda(false),compute:$arguments(1),serve:$arguments(0)}});
Fx.Tween=new Class({Extends:Fx.CSS,initialize:function(B,A){this.element=this.subject=$(B);this.parent(A);},set:function(B,A){if(arguments.length==1){A=B;
B=this.property||this.options.property;}this.render(this.element,B,A,this.options.unit);return this;},start:function(C,E,D){if(!this.check(arguments.callee,C,E,D)){return this;
}var B=Array.flatten(arguments);this.property=this.options.property||B.shift();var A=this.prepare(this.element,this.property,B);return this.parent(A.from,A.to);
}});Element.Properties.tween={set:function(A){var B=this.retrieve("tween");if(B){B.cancel();}return this.eliminate("tween").store("tween:options",$extend({link:"cancel"},A));
},get:function(A){if(A||!this.retrieve("tween")){if(A||!this.retrieve("tween:options")){this.set("tween",A);}this.store("tween",new Fx.Tween(this,this.retrieve("tween:options")));
}return this.retrieve("tween");}};Element.implement({tween:function(A,C,B){this.get("tween").start(arguments);return this;},fade:function(C){var E=this.get("tween"),D="opacity",A;
C=$pick(C,"toggle");switch(C){case"in":E.start(D,1);break;case"out":E.start(D,0);break;case"show":E.set(D,1);break;case"hide":E.set(D,0);break;case"toggle":var B=this.retrieve("fade:flag",this.get("opacity")==1);
E.start(D,(B)?0:1);this.store("fade:flag",!B);A=true;break;default:E.start(D,arguments);}if(!A){this.eliminate("fade:flag");}return this;},highlight:function(C,A){if(!A){A=this.retrieve("highlight:original",this.getStyle("background-color"));
A=(A=="transparent")?"#fff":A;}var B=this.get("tween");B.start("background-color",C||"#ffff88",A).chain(function(){this.setStyle("background-color",this.retrieve("highlight:original"));
B.callChain();}.bind(this));return this;}});Fx.Morph=new Class({Extends:Fx.CSS,initialize:function(B,A){this.element=this.subject=$(B);this.parent(A);},set:function(A){if(typeof A=="string"){A=this.search(A);
}for(var B in A){this.render(this.element,B,A[B],this.options.unit);}return this;},compute:function(E,D,C){var A={};for(var B in E){A[B]=this.parent(E[B],D[B],C);
}return A;},start:function(B){if(!this.check(arguments.callee,B)){return this;}if(typeof B=="string"){B=this.search(B);}var E={},D={};for(var C in B){var A=this.prepare(this.element,C,B[C]);
E[C]=A.from;D[C]=A.to;}return this.parent(E,D);}});Element.Properties.morph={set:function(A){var B=this.retrieve("morph");if(B){B.cancel();}return this.eliminate("morph").store("morph:options",$extend({link:"cancel"},A));
},get:function(A){if(A||!this.retrieve("morph")){if(A||!this.retrieve("morph:options")){this.set("morph",A);}this.store("morph",new Fx.Morph(this,this.retrieve("morph:options")));
}return this.retrieve("morph");}};Element.implement({morph:function(A){this.get("morph").start(A);return this;}});(function(){var A=Fx.prototype.initialize;
Fx.prototype.initialize=function(B){A.call(this,B);var C=this.options.transition;if(typeof C=="string"&&(C=C.split(":"))){var D=Fx.Transitions;D=D[C[0]]||D[C[0].capitalize()];
if(C[1]){D=D["ease"+C[1].capitalize()+(C[2]?C[2].capitalize():"")];}this.options.transition=D;}};})();Fx.Transition=function(B,A){A=$splat(A);return $extend(B,{easeIn:function(C){return B(C,A);
},easeOut:function(C){return 1-B(1-C,A);},easeInOut:function(C){return(C<=0.5)?B(2*C,A)/2:(2-B(2*(1-C),A))/2;}});};Fx.Transitions=new Hash({linear:$arguments(0)});
Fx.Transitions.extend=function(A){for(var B in A){Fx.Transitions[B]=new Fx.Transition(A[B]);}};Fx.Transitions.extend({Pow:function(B,A){return Math.pow(B,A[0]||6);
},Expo:function(A){return Math.pow(2,8*(A-1));},Circ:function(A){return 1-Math.sin(Math.acos(A));},Sine:function(A){return 1-Math.sin((1-A)*Math.PI/2);
},Back:function(B,A){A=A[0]||1.618;return Math.pow(B,2)*((A+1)*B-A);},Bounce:function(D){var C;for(var B=0,A=1;1;B+=A,A/=2){if(D>=(7-4*B)/11){C=-Math.pow((11-6*B-11*D)/4,2)+A*A;
break;}}return C;},Elastic:function(B,A){return Math.pow(2,10*--B)*Math.cos(20*B*Math.PI*(A[0]||1)/3);}});["Quad","Cubic","Quart","Quint"].each(function(B,A){Fx.Transitions[B]=new Fx.Transition(function(C){return Math.pow(C,[A+2]);
});});var Request=new Class({Implements:[Chain,Events,Options],options:{url:"",data:"",headers:{"X-Requested-With":"XMLHttpRequest",Accept:"text/javascript, text/html, application/xml, text/xml, */*"},async:true,format:false,method:"post",link:"ignore",isSuccess:null,emulation:true,urlEncoded:true,encoding:"utf-8",evalScripts:false,evalResponse:false},initialize:function(A){this.xhr=new Browser.Request();
this.setOptions(A);this.options.isSuccess=this.options.isSuccess||this.isSuccess;this.headers=new Hash(this.options.headers);},onStateChange:function(){if(this.xhr.readyState!=4||!this.running){return ;
}this.running=false;this.status=0;$try(function(){this.status=this.xhr.status;}.bind(this));if(this.options.isSuccess.call(this,this.status)){this.response={text:this.xhr.responseText,xml:this.xhr.responseXML};
this.success(this.response.text,this.response.xml);}else{this.response={text:null,xml:null};this.failure();}this.xhr.onreadystatechange=$empty;},isSuccess:function(){return((this.status>=200)&&(this.status<300));
},processScripts:function(A){if(this.options.evalResponse||(/(ecma|java)script/).test(this.getHeader("Content-type"))){return $exec(A);}return A.stripScripts(this.options.evalScripts);
},success:function(B,A){this.onSuccess(this.processScripts(B),A);},onSuccess:function(){this.fireEvent("complete",arguments).fireEvent("success",arguments).callChain();
},failure:function(){this.onFailure();},onFailure:function(){this.fireEvent("complete").fireEvent("failure",this.xhr);},setHeader:function(A,B){this.headers.set(A,B);
return this;},getHeader:function(A){return $try(function(){return this.xhr.getResponseHeader(A);}.bind(this));},check:function(A){if(!this.running){return true;
}switch(this.options.link){case"cancel":this.cancel();return true;case"chain":this.chain(A.bind(this,Array.slice(arguments,1)));return false;}return false;
},send:function(I){if(!this.check(arguments.callee,I)){return this;}this.running=true;var G=$type(I);if(G=="string"||G=="element"){I={data:I};}var D=this.options;
I=$extend({data:D.data,url:D.url,method:D.method},I);var E=I.data,B=I.url,A=I.method;switch($type(E)){case"element":E=$(E).toQueryString();break;case"object":case"hash":E=Hash.toQueryString(E);
}if(this.options.format){var H="format="+this.options.format;E=(E)?H+"&"+E:H;}if(this.options.emulation&&["put","delete"].contains(A)){var F="_method="+A;
E=(E)?F+"&"+E:F;A="post";}if(this.options.urlEncoded&&A=="post"){var C=(this.options.encoding)?"; charset="+this.options.encoding:"";this.headers.set("Content-type","application/x-www-form-urlencoded"+C);
}if(E&&A=="get"){B=B+(B.contains("?")?"&":"?")+E;E=null;}this.xhr.open(A.toUpperCase(),B,this.options.async);this.xhr.onreadystatechange=this.onStateChange.bind(this);
this.headers.each(function(K,J){if(!$try(function(){this.xhr.setRequestHeader(J,K);return true;}.bind(this))){this.fireEvent("exception",[J,K]);}},this);
this.fireEvent("request");this.xhr.send(E);if(!this.options.async){this.onStateChange();}return this;},cancel:function(){if(!this.running){return this;
}this.running=false;this.xhr.abort();this.xhr.onreadystatechange=$empty;this.xhr=new Browser.Request();this.fireEvent("cancel");return this;}});(function(){var A={};
["get","post","put","delete","GET","POST","PUT","DELETE"].each(function(B){A[B]=function(){var C=Array.link(arguments,{url:String.type,data:$defined});
return this.send($extend(C,{method:B.toLowerCase()}));};});Request.implement(A);})();Element.Properties.send={set:function(A){var B=this.retrieve("send");
if(B){B.cancel();}return this.eliminate("send").store("send:options",$extend({data:this,link:"cancel",method:this.get("method")||"post",url:this.get("action")},A));
},get:function(A){if(A||!this.retrieve("send")){if(A||!this.retrieve("send:options")){this.set("send",A);}this.store("send",new Request(this.retrieve("send:options")));
}return this.retrieve("send");}};Element.implement({send:function(A){var B=this.get("send");B.send({data:this,url:A||B.options.url});return this;}});Request.HTML=new Class({Extends:Request,options:{update:false,evalScripts:true,filter:false},processHTML:function(C){var B=C.match(/<body[^>]*>([\s\S]*?)<\/body>/i);
C=(B)?B[1]:C;var A=new Element("div");return $try(function(){var D="<root>"+C+"</root>",G;if(Browser.Engine.trident){G=new ActiveXObject("Microsoft.XMLDOM");
G.async=false;G.loadXML(D);}else{G=new DOMParser().parseFromString(D,"text/xml");}D=G.getElementsByTagName("root")[0];for(var F=0,E=D.childNodes.length;
F<E;F++){var H=Element.clone(D.childNodes[F],true,true);if(H){A.grab(H);}}return A;})||A.set("html",C);},success:function(D){var C=this.options,B=this.response;
B.html=D.stripScripts(function(E){B.javascript=E;});var A=this.processHTML(B.html);B.tree=A.childNodes;B.elements=A.getElements("*");if(C.filter){B.tree=B.elements.filter(C.filter);
}if(C.update){$(C.update).empty().adopt(B.tree);}if(C.evalScripts){$exec(B.javascript);}this.onSuccess(B.tree,B.elements,B.html,B.javascript);}});Element.Properties.load={set:function(A){var B=this.retrieve("load");
if(B){send.cancel();}return this.eliminate("load").store("load:options",$extend({data:this,link:"cancel",update:this,method:"get"},A));},get:function(A){if(A||!this.retrieve("load")){if(A||!this.retrieve("load:options")){this.set("load",A);
}this.store("load",new Request.HTML(this.retrieve("load:options")));}return this.retrieve("load");}};Element.implement({load:function(){this.get("load").send(Array.link(arguments,{data:Object.type,url:String.type}));
return this;}});Request.JSON=new Class({Extends:Request,options:{secure:true},initialize:function(A){this.parent(A);this.headers.extend({Accept:"application/json","X-Request":"JSON"});
},success:function(A){this.response.json=JSON.decode(A,this.options.secure);this.onSuccess(this.response.json,A);}});//MooTools More, <http://mootools.net/more>. Copyright (c) 2006-2008 Valerio Proietti, <http://mad4milk.net>, MIT Style License.

Fx.Slide=new Class({Extends:Fx,options:{mode:"vertical"},initialize:function(B,A){this.addEvent("complete",function(){this.open=(this.wrapper["offset"+this.layout.capitalize()]!=0);
if(this.open&&Browser.Engine.webkit419){this.element.dispose().inject(this.wrapper);}},true);this.element=this.subject=$(B);this.parent(A);var C=this.element.retrieve("wrapper");
this.wrapper=C||new Element("div",{styles:$extend(this.element.getStyles("margin","position"),{overflow:"hidden"})}).wraps(this.element);this.element.store("wrapper",this.wrapper).setStyle("margin",0);
this.now=[];this.open=true;},vertical:function(){this.margin="margin-top";this.layout="height";this.offset=this.element.offsetHeight;},horizontal:function(){this.margin="margin-left";
this.layout="width";this.offset=this.element.offsetWidth;},set:function(A){this.element.setStyle(this.margin,A[0]);this.wrapper.setStyle(this.layout,A[1]);
return this;},compute:function(E,D,C){var B=[];var A=2;A.times(function(F){B[F]=Fx.compute(E[F],D[F],C);});return B;},start:function(B,E){if(!this.check(arguments.callee,B,E)){return this;
}this[E||this.options.mode]();var D=this.element.getStyle(this.margin).toInt();var C=this.wrapper.getStyle(this.layout).toInt();var A=[[D,C],[0,this.offset]];
var G=[[D,C],[-this.offset,0]];var F;switch(B){case"in":F=A;break;case"out":F=G;break;case"toggle":F=(this.wrapper["offset"+this.layout.capitalize()]==0)?A:G;
}return this.parent(F[0],F[1]);},slideIn:function(A){return this.start("in",A);},slideOut:function(A){return this.start("out",A);},hide:function(A){this[A||this.options.mode]();
this.open=false;return this.set([-this.offset,0]);},show:function(A){this[A||this.options.mode]();this.open=true;return this.set([0,this.offset]);},toggle:function(A){return this.start("toggle",A);
}});Element.Properties.slide={set:function(B){var A=this.retrieve("slide");if(A){A.cancel();}return this.eliminate("slide").store("slide:options",$extend({link:"cancel"},B));
},get:function(A){if(A||!this.retrieve("slide")){if(A||!this.retrieve("slide:options")){this.set("slide",A);}this.store("slide",new Fx.Slide(this,this.retrieve("slide:options")));
}return this.retrieve("slide");}};Element.implement({slide:function(D,E){D=D||"toggle";var B=this.get("slide"),A;switch(D){case"hide":B.hide(E);break;case"show":B.show(E);
break;case"toggle":var C=this.retrieve("slide:flag",B.open);B[(C)?"slideOut":"slideIn"](E);this.store("slide:flag",!C);A=true;break;default:B.start(D,E);
}if(!A){this.eliminate("slide:flag");}return this;}});Fx.Scroll=new Class({Extends:Fx,options:{offset:{x:0,y:0},wheelStops:true},initialize:function(B,A){this.element=this.subject=$(B);
this.parent(A);var D=this.cancel.bind(this,false);if($type(this.element)!="element"){this.element=$(this.element.getDocument().body);}var C=this.element;
if(this.options.wheelStops){this.addEvent("start",function(){C.addEvent("mousewheel",D);},true);this.addEvent("complete",function(){C.removeEvent("mousewheel",D);
},true);}},set:function(){var A=Array.flatten(arguments);this.element.scrollTo(A[0],A[1]);},compute:function(E,D,C){var B=[];var A=2;A.times(function(F){B.push(Fx.compute(E[F],D[F],C));
});return B;},start:function(C,H){if(!this.check(arguments.callee,C,H)){return this;}var E=this.element.getSize(),F=this.element.getScrollSize();var B=this.element.getScroll(),D={x:C,y:H};
for(var G in D){var A=F[G]-E[G];if($chk(D[G])){D[G]=($type(D[G])=="number")?D[G].limit(0,A):A;}else{D[G]=B[G];}D[G]+=this.options.offset[G];}return this.parent([B.x,B.y],[D.x,D.y]);
},toTop:function(){return this.start(false,0);},toLeft:function(){return this.start(0,false);},toRight:function(){return this.start("right",false);},toBottom:function(){return this.start(false,"bottom");
},toElement:function(B){var A=$(B).getPosition(this.element);return this.start(A.x,A.y);}});Fx.Elements=new Class({Extends:Fx.CSS,initialize:function(B,A){this.elements=this.subject=$$(B);
this.parent(A);},compute:function(G,H,I){var C={};for(var D in G){var A=G[D],E=H[D],F=C[D]={};for(var B in A){F[B]=this.parent(A[B],E[B],I);}}return C;
},set:function(B){for(var C in B){var A=B[C];for(var D in A){this.render(this.elements[C],D,A[D],this.options.unit);}}return this;},start:function(C){if(!this.check(arguments.callee,C)){return this;
}var H={},I={};for(var D in C){var F=C[D],A=H[D]={},G=I[D]={};for(var B in F){var E=this.prepare(this.elements[D],B,F[B]);A[B]=E.from;G[B]=E.to;}}return this.parent(H,I);
}});var Drag=new Class({Implements:[Events,Options],options:{snap:6,unit:"px",grid:false,style:true,limit:false,handle:false,invert:false,preventDefault:false,modifiers:{x:"left",y:"top"}},initialize:function(){var B=Array.link(arguments,{options:Object.type,element:$defined});
this.element=$(B.element);this.document=this.element.getDocument();this.setOptions(B.options||{});var A=$type(this.options.handle);this.handles=(A=="array"||A=="collection")?$$(this.options.handle):$(this.options.handle)||this.element;
this.mouse={now:{},pos:{}};this.value={start:{},now:{}};this.selection=(Browser.Engine.trident)?"selectstart":"mousedown";this.bound={start:this.start.bind(this),check:this.check.bind(this),drag:this.drag.bind(this),stop:this.stop.bind(this),cancel:this.cancel.bind(this),eventStop:$lambda(false)};
this.attach();},attach:function(){this.handles.addEvent("mousedown",this.bound.start);return this;},detach:function(){this.handles.removeEvent("mousedown",this.bound.start);
return this;},start:function(C){if(this.options.preventDefault){C.preventDefault();}this.fireEvent("beforeStart",this.element);this.mouse.start=C.page;
var A=this.options.limit;this.limit={x:[],y:[]};for(var D in this.options.modifiers){if(!this.options.modifiers[D]){continue;}if(this.options.style){this.value.now[D]=this.element.getStyle(this.options.modifiers[D]).toInt();
}else{this.value.now[D]=this.element[this.options.modifiers[D]];}if(this.options.invert){this.value.now[D]*=-1;}this.mouse.pos[D]=C.page[D]-this.value.now[D];
if(A&&A[D]){for(var B=2;B--;B){if($chk(A[D][B])){this.limit[D][B]=$lambda(A[D][B])();}}}}if($type(this.options.grid)=="number"){this.options.grid={x:this.options.grid,y:this.options.grid};
}this.document.addEvents({mousemove:this.bound.check,mouseup:this.bound.cancel});this.document.addEvent(this.selection,this.bound.eventStop);},check:function(A){if(this.options.preventDefault){A.preventDefault();
}var B=Math.round(Math.sqrt(Math.pow(A.page.x-this.mouse.start.x,2)+Math.pow(A.page.y-this.mouse.start.y,2)));if(B>this.options.snap){this.cancel();this.document.addEvents({mousemove:this.bound.drag,mouseup:this.bound.stop});
this.fireEvent("start",this.element).fireEvent("snap",this.element);}},drag:function(A){if(this.options.preventDefault){A.preventDefault();}this.mouse.now=A.page;
for(var B in this.options.modifiers){if(!this.options.modifiers[B]){continue;}this.value.now[B]=this.mouse.now[B]-this.mouse.pos[B];if(this.options.invert){this.value.now[B]*=-1;
}if(this.options.limit&&this.limit[B]){if($chk(this.limit[B][1])&&(this.value.now[B]>this.limit[B][1])){this.value.now[B]=this.limit[B][1];}else{if($chk(this.limit[B][0])&&(this.value.now[B]<this.limit[B][0])){this.value.now[B]=this.limit[B][0];
}}}if(this.options.grid[B]){this.value.now[B]-=(this.value.now[B]%this.options.grid[B]);}if(this.options.style){this.element.setStyle(this.options.modifiers[B],this.value.now[B]+this.options.unit);
}else{this.element[this.options.modifiers[B]]=this.value.now[B];}}this.fireEvent("drag",this.element);},cancel:function(A){this.document.removeEvent("mousemove",this.bound.check);
this.document.removeEvent("mouseup",this.bound.cancel);if(A){this.document.removeEvent(this.selection,this.bound.eventStop);this.fireEvent("cancel",this.element);
}},stop:function(A){this.document.removeEvent(this.selection,this.bound.eventStop);this.document.removeEvent("mousemove",this.bound.drag);this.document.removeEvent("mouseup",this.bound.stop);
if(A){this.fireEvent("complete",this.element);}}});Element.implement({makeResizable:function(A){return new Drag(this,$merge({modifiers:{x:"width",y:"height"}},A));
}});Drag.Move=new Class({Extends:Drag,options:{droppables:[],container:false},initialize:function(C,B){this.parent(C,B);this.droppables=$$(this.options.droppables);
this.container=$(this.options.container);if(this.container&&$type(this.container)!="element"){this.container=$(this.container.getDocument().body);}C=this.element;
var D=C.getStyle("position");var A=(D!="static")?D:"absolute";if(C.getStyle("left")=="auto"||C.getStyle("top")=="auto"){C.position(C.getPosition(C.offsetParent));
}C.setStyle("position",A);this.addEvent("start",function(){this.checkDroppables();},true);},start:function(B){if(this.container){var D=this.element,J=this.container,E=J.getCoordinates(D.offsetParent),F={},A={};
["top","right","bottom","left"].each(function(K){F[K]=J.getStyle("padding-"+K).toInt();A[K]=D.getStyle("margin-"+K).toInt();},this);var C=D.offsetWidth+A.left+A.right,I=D.offsetHeight+A.top+A.bottom;
var H=[E.left+F.left,E.right-F.right-C];var G=[E.top+F.top,E.bottom-F.bottom-I];this.options.limit={x:H,y:G};}this.parent(B);},checkAgainst:function(B){B=B.getCoordinates();
var A=this.mouse.now;return(A.x>B.left&&A.x<B.right&&A.y<B.bottom&&A.y>B.top);},checkDroppables:function(){var A=this.droppables.filter(this.checkAgainst,this).getLast();
if(this.overed!=A){if(this.overed){this.fireEvent("leave",[this.element,this.overed]);}if(A){this.overed=A;this.fireEvent("enter",[this.element,A]);}else{this.overed=null;
}}},drag:function(A){this.parent(A);if(this.droppables.length){this.checkDroppables();}},stop:function(A){this.checkDroppables();this.fireEvent("drop",[this.element,this.overed]);
this.overed=null;return this.parent(A);}});Element.implement({makeDraggable:function(A){return new Drag.Move(this,A);}});Hash.Cookie=new Class({Extends:Cookie,options:{autoSave:true},initialize:function(B,A){this.parent(B,A);
this.load();},save:function(){var A=JSON.encode(this.hash);if(!A||A.length>4096){return false;}if(A=="{}"){this.dispose();}else{this.write(A);}return true;
},load:function(){this.hash=new Hash(JSON.decode(this.read(),true));return this;}});Hash.Cookie.implement((function(){var A={};Hash.each(Hash.prototype,function(C,B){A[B]=function(){var D=C.apply(this.hash,arguments);
if(this.options.autoSave){this.save();}return D;};});return A;})());var Color=new Native({initialize:function(B,C){if(arguments.length>=3){C="rgb";B=Array.slice(arguments,0,3);
}else{if(typeof B=="string"){if(B.match(/rgb/)){B=B.rgbToHex().hexToRgb(true);}else{if(B.match(/hsb/)){B=B.hsbToRgb();}else{B=B.hexToRgb(true);}}}}C=C||"rgb";
switch(C){case"hsb":var A=B;B=B.hsbToRgb();B.hsb=A;break;case"hex":B=B.hexToRgb(true);break;}B.rgb=B.slice(0,3);B.hsb=B.hsb||B.rgbToHsb();B.hex=B.rgbToHex();
return $extend(B,this);}});Color.implement({mix:function(){var A=Array.slice(arguments);var C=($type(A.getLast())=="number")?A.pop():50;var B=this.slice();
A.each(function(D){D=new Color(D);for(var E=0;E<3;E++){B[E]=Math.round((B[E]/100*(100-C))+(D[E]/100*C));}});return new Color(B,"rgb");},invert:function(){return new Color(this.map(function(A){return 255-A;
}));},setHue:function(A){return new Color([A,this.hsb[1],this.hsb[2]],"hsb");},setSaturation:function(A){return new Color([this.hsb[0],A,this.hsb[2]],"hsb");
},setBrightness:function(A){return new Color([this.hsb[0],this.hsb[1],A],"hsb");}});function $RGB(C,B,A){return new Color([C,B,A],"rgb");}function $HSB(C,B,A){return new Color([C,B,A],"hsb");
}function $HEX(A){return new Color(A,"hex");}Array.implement({rgbToHsb:function(){var B=this[0],C=this[1],J=this[2];var G,F,H;var I=Math.max(B,C,J),E=Math.min(B,C,J);
var K=I-E;H=I/255;F=(I!=0)?K/I:0;if(F==0){G=0;}else{var D=(I-B)/K;var A=(I-C)/K;var L=(I-J)/K;if(B==I){G=L-A;}else{if(C==I){G=2+D-L;}else{G=4+A-D;}}G/=6;
if(G<0){G++;}}return[Math.round(G*360),Math.round(F*100),Math.round(H*100)];},hsbToRgb:function(){var C=Math.round(this[2]/100*255);if(this[1]==0){return[C,C,C];
}else{var A=this[0]%360;var E=A%60;var F=Math.round((this[2]*(100-this[1]))/10000*255);var D=Math.round((this[2]*(6000-this[1]*E))/600000*255);var B=Math.round((this[2]*(6000-this[1]*(60-E)))/600000*255);
switch(Math.floor(A/60)){case 0:return[C,B,F];case 1:return[D,C,F];case 2:return[F,C,B];case 3:return[F,D,C];case 4:return[B,F,C];case 5:return[C,F,D];
}}return false;}});String.implement({rgbToHsb:function(){var A=this.match(/\d{1,3}/g);return(A)?hsb.rgbToHsb():null;},hsbToRgb:function(){var A=this.match(/\d{1,3}/g);
return(A)?A.hsbToRgb():null;}});var Group=new Class({initialize:function(){this.instances=Array.flatten(arguments);this.events={};this.checker={};},addEvent:function(B,A){this.checker[B]=this.checker[B]||{};
this.events[B]=this.events[B]||[];if(this.events[B].contains(A)){return false;}else{this.events[B].push(A);}this.instances.each(function(C,D){C.addEvent(B,this.check.bind(this,[B,C,D]));
},this);return this;},check:function(C,A,B){this.checker[C][B]=true;var D=this.instances.every(function(F,E){return this.checker[C][E]||false;},this);if(!D){return ;
}this.checker[C]={};this.events[C].each(function(E){E.call(this,this.instances,A);},this);}});var Asset=new Hash({javascript:function(F,D){D=$extend({onload:$empty,document:document,check:$lambda(true)},D);
var B=new Element("script",{src:F,type:"text/javascript"});var E=D.onload.bind(B),A=D.check,G=D.document;delete D.onload;delete D.check;delete D.document;
B.addEvents({load:E,readystatechange:function(){if(["loaded","complete"].contains(this.readyState)){E();}}}).setProperties(D);if(Browser.Engine.webkit419){var C=(function(){if(!$try(A)){return ;
}$clear(C);E();}).periodical(50);}return B.inject(G.head);},css:function(B,A){return new Element("link",$merge({rel:"stylesheet",media:"screen",type:"text/css",href:B},A)).inject(document.head);
},image:function(C,B){B=$merge({onload:$empty,onabort:$empty,onerror:$empty},B);var D=new Image();var A=$(D)||new Element("img");["load","abort","error"].each(function(E){var F="on"+E;
var G=B[F];delete B[F];D[F]=function(){if(!D){return ;}if(!A.parentNode){A.width=D.width;A.height=D.height;}D=D.onload=D.onabort=D.onerror=null;G.delay(1,A,A);
A.fireEvent(E,A,1);};});D.src=A.src=C;if(D&&D.complete){D.onload.delay(1);}return A.setProperties(B);},images:function(D,C){C=$merge({onComplete:$empty,onProgress:$empty},C);
if(!D.push){D=[D];}var A=[];var B=0;D.each(function(F){var E=new Asset.image(F,{onload:function(){C.onProgress.call(this,B,D.indexOf(F));B++;if(B==D.length){C.onComplete();
}}});A.push(E);});return new Elements(A);}});var Sortables=new Class({Implements:[Events,Options],options:{snap:4,opacity:1,clone:false,revert:false,handle:false,constrain:false},initialize:function(A,B){this.setOptions(B);
this.elements=[];this.lists=[];this.idle=true;this.addLists($$($(A)||A));if(!this.options.clone){this.options.revert=false;}if(this.options.revert){this.effect=new Fx.Morph(null,$merge({duration:250,link:"cancel"},this.options.revert));
}},attach:function(){this.addLists(this.lists);return this;},detach:function(){this.lists=this.removeLists(this.lists);return this;},addItems:function(){Array.flatten(arguments).each(function(A){this.elements.push(A);
var B=A.retrieve("sortables:start",this.start.bindWithEvent(this,A));(this.options.handle?A.getElement(this.options.handle)||A:A).addEvent("mousedown",B);
},this);return this;},addLists:function(){Array.flatten(arguments).each(function(A){this.lists.push(A);this.addItems(A.getChildren());},this);return this;
},removeItems:function(){var A=[];Array.flatten(arguments).each(function(B){A.push(B);this.elements.erase(B);var C=B.retrieve("sortables:start");(this.options.handle?B.getElement(this.options.handle)||B:B).removeEvent("mousedown",C);
},this);return $$(A);},removeLists:function(){var A=[];Array.flatten(arguments).each(function(B){A.push(B);this.lists.erase(B);this.removeItems(B.getChildren());
},this);return $$(A);},getClone:function(B,A){if(!this.options.clone){return new Element("div").inject(document.body);}if($type(this.options.clone)=="function"){return this.options.clone.call(this,B,A,this.list);
}return A.clone(true).setStyles({margin:"0px",position:"absolute",visibility:"hidden",width:A.getStyle("width")}).inject(this.list).position(A.getPosition(A.getOffsetParent()));
},getDroppables:function(){var A=this.list.getChildren();if(!this.options.constrain){A=this.lists.concat(A).erase(this.list);}return A.erase(this.clone).erase(this.element);
},insert:function(C,B){var A="inside";if(this.lists.contains(B)){this.list=B;this.drag.droppables=this.getDroppables();}else{A=this.element.getAllPrevious().contains(B)?"before":"after";
}this.element.inject(B,A);this.fireEvent("sort",[this.element,this.clone]);},start:function(B,A){if(!this.idle){return ;}this.idle=false;this.element=A;
this.opacity=A.get("opacity");this.list=A.getParent();this.clone=this.getClone(B,A);this.drag=new Drag.Move(this.clone,{snap:this.options.snap,container:this.options.constrain&&this.element.getParent(),droppables:this.getDroppables(),onSnap:function(){B.stop();
this.clone.setStyle("visibility","visible");this.element.set("opacity",this.options.opacity||0);this.fireEvent("start",[this.element,this.clone]);}.bind(this),onEnter:this.insert.bind(this),onCancel:this.reset.bind(this),onComplete:this.end.bind(this)});
this.clone.inject(this.element,"before");this.drag.start(B);},end:function(){this.drag.detach();this.element.set("opacity",this.opacity);if(this.effect){var A=this.element.getStyles("width","height");
var B=this.clone.computePosition(this.element.getPosition(this.clone.offsetParent));this.effect.element=this.clone;this.effect.start({top:B.top,left:B.left,width:A.width,height:A.height,opacity:0.25}).chain(this.reset.bind(this));
}else{this.reset();}},reset:function(){this.idle=true;this.clone.destroy();this.fireEvent("complete",this.element);},serialize:function(){var C=Array.link(arguments,{modifier:Function.type,index:$defined});
var B=this.lists.map(function(D){return D.getChildren().map(C.modifier||function(E){return E.get("id");},this);},this);var A=C.index;if(this.lists.length==1){A=0;
}return $chk(A)&&A>=0&&A<this.lists.length?B[A]:B;}});var Tips=new Class({Implements:[Events,Options],options:{onShow:function(A){A.setStyle("visibility","visible");
},onHide:function(A){A.setStyle("visibility","hidden");},showDelay:100,hideDelay:100,className:null,offsets:{x:16,y:16},fixed:false},initialize:function(){var C=Array.link(arguments,{options:Object.type,elements:$defined});
this.setOptions(C.options||null);this.tip=new Element("div").inject(document.body);if(this.options.className){this.tip.addClass(this.options.className);
}var B=new Element("div",{"class":"tip-top"}).inject(this.tip);this.container=new Element("div",{"class":"tip"}).inject(this.tip);var A=new Element("div",{"class":"tip-bottom"}).inject(this.tip);
this.tip.setStyles({position:"absolute",top:0,left:0,visibility:"hidden"});if(C.elements){this.attach(C.elements);}},attach:function(A){$$(A).each(function(D){var G=D.retrieve("tip:title",D.get("title"));
var F=D.retrieve("tip:text",D.get("rel")||D.get("href"));var E=D.retrieve("tip:enter",this.elementEnter.bindWithEvent(this,D));var C=D.retrieve("tip:leave",this.elementLeave.bindWithEvent(this,D));
D.addEvents({mouseenter:E,mouseleave:C});if(!this.options.fixed){var B=D.retrieve("tip:move",this.elementMove.bindWithEvent(this,D));D.addEvent("mousemove",B);
}D.store("tip:native",D.get("title"));D.erase("title");},this);return this;},detach:function(A){$$(A).each(function(C){C.removeEvent("mouseenter",C.retrieve("tip:enter")||$empty);
C.removeEvent("mouseleave",C.retrieve("tip:leave")||$empty);C.removeEvent("mousemove",C.retrieve("tip:move")||$empty);C.eliminate("tip:enter").eliminate("tip:leave").eliminate("tip:move");
var B=C.retrieve("tip:native");if(B){C.set("title",B);}});return this;},elementEnter:function(B,A){$A(this.container.childNodes).each(Element.dispose);
var D=A.retrieve("tip:title");if(D){this.titleElement=new Element("div",{"class":"tip-title"}).inject(this.container);this.fill(this.titleElement,D);}var C=A.retrieve("tip:text");
if(C){this.textElement=new Element("div",{"class":"tip-text"}).inject(this.container);this.fill(this.textElement,C);}this.timer=$clear(this.timer);this.timer=this.show.delay(this.options.showDelay,this);
this.position((!this.options.fixed)?B:{page:A.getPosition()});},elementLeave:function(A){$clear(this.timer);this.timer=this.hide.delay(this.options.hideDelay,this);
},elementMove:function(A){this.position(A);},position:function(D){var B=window.getSize(),A=window.getScroll();var E={x:this.tip.offsetWidth,y:this.tip.offsetHeight};
var C={x:"left",y:"top"};for(var F in C){var G=D.page[F]+this.options.offsets[F];if((G+E[F]-A[F])>B[F]){G=D.page[F]-this.options.offsets[F]-E[F];}this.tip.setStyle(C[F],G);
}},fill:function(A,B){(typeof B=="string")?A.set("html",B):A.adopt(B);},show:function(){this.fireEvent("show",this.tip);},hide:function(){this.fireEvent("hide",this.tip);
}});var SmoothScroll=new Class({Extends:Fx.Scroll,initialize:function(B,C){C=C||document;var E=C.getDocument(),D=C.getWindow();this.parent(E,B);this.links=(this.options.links)?$$(this.options.links):$$(E.links);
var A=D.location.href.match(/^[^#]*/)[0]+"#";this.links.each(function(G){if(G.href.indexOf(A)!=0){return ;}var F=G.href.substr(A.length);if(F&&$(F)){this.useLink(G,F);
}},this);if(!Browser.Engine.webkit419){this.addEvent("complete",function(){D.location.hash=this.anchor;},true);}},useLink:function(B,A){B.addEvent("click",function(C){this.anchor=A;
this.toElement(A);C.stop();}.bind(this));}});var Slider=new Class({Implements:[Events,Options],options:{onTick:function(A){if(this.options.snap){A=this.toPosition(this.step);
}this.knob.setStyle(this.property,A);},snap:false,offset:0,range:false,wheel:false,steps:100,mode:"horizontal"},initialize:function(E,A,D){this.setOptions(D);
this.element=$(E);this.knob=$(A);this.previousChange=this.previousEnd=this.step=-1;this.element.addEvent("mousedown",this.clickedElement.bind(this));if(this.options.wheel){this.element.addEvent("mousewheel",this.scrolledElement.bindWithEvent(this));
}var F,B={},C={x:false,y:false};switch(this.options.mode){case"vertical":this.axis="y";this.property="top";F="offsetHeight";break;case"horizontal":this.axis="x";
this.property="left";F="offsetWidth";}this.half=this.knob[F]/2;this.full=this.element[F]-this.knob[F]+(this.options.offset*2);this.min=$chk(this.options.range[0])?this.options.range[0]:0;
this.max=$chk(this.options.range[1])?this.options.range[1]:this.options.steps;this.range=this.max-this.min;this.steps=this.options.steps||this.full;this.stepSize=Math.abs(this.range)/this.steps;
this.stepWidth=this.stepSize*this.full/Math.abs(this.range);this.knob.setStyle("position","relative").setStyle(this.property,-this.options.offset);C[this.axis]=this.property;
B[this.axis]=[-this.options.offset,this.full-this.options.offset];this.drag=new Drag(this.knob,{snap:0,limit:B,modifiers:C,onDrag:this.draggedKnob.bind(this),onStart:this.draggedKnob.bind(this),onComplete:function(){this.draggedKnob();
this.end();}.bind(this)});if(this.options.snap){this.drag.options.grid=Math.ceil(this.stepWidth);this.drag.options.limit[this.axis][1]=this.full;}},set:function(A){if(!((this.range>0)^(A<this.min))){A=this.min;
}if(!((this.range>0)^(A>this.max))){A=this.max;}this.step=Math.round(A);this.checkStep();this.end();this.fireEvent("tick",this.toPosition(this.step));return this;
},clickedElement:function(C){var B=this.range<0?-1:1;var A=C.page[this.axis]-this.element.getPosition()[this.axis]-this.half;A=A.limit(-this.options.offset,this.full-this.options.offset);
this.step=Math.round(this.min+B*this.toStep(A));this.checkStep();this.end();this.fireEvent("tick",A);},scrolledElement:function(A){var B=(this.options.mode=="horizontal")?(A.wheel<0):(A.wheel>0);
this.set(B?this.step-this.stepSize:this.step+this.stepSize);A.stop();},draggedKnob:function(){var B=this.range<0?-1:1;var A=this.drag.value.now[this.axis];
A=A.limit(-this.options.offset,this.full-this.options.offset);this.step=Math.round(this.min+B*this.toStep(A));this.checkStep();},checkStep:function(){if(this.previousChange!=this.step){this.previousChange=this.step;
this.fireEvent("change",this.step);}},end:function(){if(this.previousEnd!==this.step){this.previousEnd=this.step;this.fireEvent("complete",this.step+"");
}},toStep:function(A){var B=(A+this.options.offset)*this.stepSize/this.full*this.steps;return this.options.steps?Math.round(B-=B%this.stepSize):B;},toPosition:function(A){return(this.full*Math.abs(this.min-A))/(this.steps*this.stepSize)-this.options.offset;
}});var Scroller=new Class({Implements:[Events,Options],options:{area:20,velocity:1,onChange:function(A,B){this.element.scrollTo(A,B);}},initialize:function(B,A){this.setOptions(A);
this.element=$(B);this.listener=($type(this.element)!="element")?$(this.element.getDocument().body):this.element;this.timer=null;this.coord=this.getCoords.bind(this);
},start:function(){this.listener.addEvent("mousemove",this.coord);},stop:function(){this.listener.removeEvent("mousemove",this.coord);this.timer=$clear(this.timer);
},getCoords:function(A){this.page=(this.listener.get("tag")=="body")?A.client:A.page;if(!this.timer){this.timer=this.scroll.periodical(50,this);}},scroll:function(){var B=this.element.getSize(),A=this.element.getScroll(),E=this.element.getPosition(),D={x:0,y:0};
for(var C in this.page){if(this.page[C]<(this.options.area+E[C])&&A[C]!=0){D[C]=(this.page[C]-this.options.area-E[C])*this.options.velocity;}else{if(this.page[C]+this.options.area>(B[C]+E[C])&&B[C]+B[C]!=A[C]){D[C]=(this.page[C]-B[C]+this.options.area-E[C])*this.options.velocity;
}}}if(D.y||D.x){this.fireEvent("change",[A.x+D.x,A.y+D.y]);}}});var Accordion=new Class({Extends:Fx.Elements,options:{display:0,show:false,height:true,width:false,opacity:true,fixedHeight:false,fixedWidth:false,wait:false,alwaysHide:false},initialize:function(){var C=Array.link(arguments,{container:Element.type,options:Object.type,togglers:$defined,elements:$defined});
this.parent(C.elements,C.options);this.togglers=$$(C.togglers);this.container=$(C.container);this.previous=-1;if(this.options.alwaysHide){this.options.wait=true;
}if($chk(this.options.show)){this.options.display=false;this.previous=this.options.show;}if(this.options.start){this.options.display=false;this.options.show=false;
}this.effects={};if(this.options.opacity){this.effects.opacity="fullOpacity";}if(this.options.width){this.effects.width=this.options.fixedWidth?"fullWidth":"offsetWidth";
}if(this.options.height){this.effects.height=this.options.fixedHeight?"fullHeight":"scrollHeight";}for(var B=0,A=this.togglers.length;B<A;B++){this.addSection(this.togglers[B],this.elements[B]);
}this.elements.each(function(E,D){if(this.options.show===D){this.fireEvent("active",[this.togglers[D],E]);}else{for(var F in this.effects){E.setStyle(F,0);
}}},this);if($chk(this.options.display)){this.display(this.options.display);}},addSection:function(E,C,G){E=$(E);C=$(C);var F=this.togglers.contains(E);
var B=this.togglers.length;this.togglers.include(E);this.elements.include(C);if(B&&(!F||G)){G=$pick(G,B-1);E.inject(this.togglers[G],"before");C.inject(E,"after");
}else{if(this.container&&!F){E.inject(this.container);C.inject(this.container);}}var A=this.togglers.indexOf(E);E.addEvent("click",this.display.bind(this,A));
if(this.options.height){C.setStyles({"padding-top":0,"border-top":"none","padding-bottom":0,"border-bottom":"none"});}if(this.options.width){C.setStyles({"padding-left":0,"border-left":"none","padding-right":0,"border-right":"none"});
}C.fullOpacity=1;if(this.options.fixedWidth){C.fullWidth=this.options.fixedWidth;}if(this.options.fixedHeight){C.fullHeight=this.options.fixedHeight;}C.setStyle("overflow","hidden");
if(!F){for(var D in this.effects){C.setStyle(D,0);}}return this;},display:function(A){A=($type(A)=="element")?this.elements.indexOf(A):A;if((this.timer&&this.options.wait)||(A===this.previous&&!this.options.alwaysHide)){return this;
}this.previous=A;var B={};this.elements.each(function(E,D){B[D]={};var C=(D!=A)||(this.options.alwaysHide&&(E.offsetHeight>0));this.fireEvent(C?"background":"active",[this.togglers[D],E]);
for(var F in this.effects){B[D][F]=C?0:E[this.effects[F]];}},this);return this.start(B);}});/* 

Script: Core.js
	MochaUI - A Web Applications User Interface Framework.

Copyright:
	Copyright (c) 2007-2008 Greg Houston, <http://greghoustondesign.com/>.

License:
	MIT-style license.

Contributors:
	- Scott F. Frederick
	- Joel Lindau

Note:
	This documentation is taken directly from the javascript source files. It is built using Natural Docs.

Todo:
	Consider making title tooltips optional and using them more often.

*/


var MochaUI = new Hash({
	options: new Hash({
		useEffects: false  // Toggles the majority of window fade and move effects.
	}),
	Columns: {
		instances:      new Hash()
	},
	Panels: {
		instances:      new Hash()
	},		
	Windows: {	  
		instances:      new Hash(),
		indexLevel:     100,          // Used for z-Index
		windowIDCount:  0,	          // Used for windows without an ID defined by the user
		windowsVisible: true          // Ctrl-Alt-Q to toggle window visibility
	},	
	ieSupport:  'excanvas',   // Makes it easier to switch between Excanvas and Moocanvas for testing
	focusingWindow: 'false',
	/*
	
	Function: updateContent
		Replace the content of a window or panel.
		
	Arguments:
		element - The parent window or panel.
		childElement - The child element of the window or panel recieving the content.
		method - ('get', or 'post') The way data is transmitted. Defaults to 'get'.
		data - (hash) Data to be transmitted
		title - (string) Change this if you want to change the title of the window or panel.
		content - (string or element) An html loadMethod option.
		loadMethod - ('html', 'xhr', or 'iframe') Defaults to 'html'.
		url - Used if loadMethod is set to 'xhr' or 'iframe'.
		padding - (object)

	*/	
	updateContent: function(updateOptions){

		var options = {
			'element':      null,
			'childElement': null,
			'method':	    null,
			'data':		    null,
			'title':        null,
			'content':      null,
			'loadMethod':   null,
			'url':          null,
			'padding':      null
		};
		$extend(options, updateOptions);

		if (!options.element) return;
		var element = options.element;

		if (MochaUI.Windows.instances.get(element.id)) {
			var recipient = 'window';
			var currentInstance = MochaUI.Windows.instances.get(element.id);
			var spinnerEl = currentInstance.spinnerEl;
			if (options.title) {
				currentInstance.titleEl.set('html', options.title);
			}
		}
		else {
			var recipient = 'panel';
			var currentInstance = MochaUI.Panels.instances.get(element.id);
			if (options.title) {
				currentInstance.titleEl.set('html', options.title);
			}
		}

		var contentEl = currentInstance.contentEl;
		if (options.childElement != null) {
			var contentContainer = options.childElement;
		}
		else {
			var contentContainer = currentInstance.contentEl;
		}
		
		var loadMethod = options.loadMethod != null ? options.loadMethod : currentInstance.options.loadMethod;
		var method = options.method != null ? options.method : "get";
		
		// Set scrollbars if loading content in main content container.
		// Always use 'hidden' for iframe windows
		if (contentContainer == currentInstance.contentEl) {
			currentInstance.contentWrapperEl.setStyles({
				'overflow': currentInstance.options.scrollbars == true && loadMethod != 'iframe' ? 'auto' : 'hidden'
			});
		}

		var contentWrapperEl = currentInstance.contentWrapperEl;
		
		if (options.padding != null) {
			contentEl.setStyles({
				'padding-top': options.padding.top,
				'padding-bottom': options.padding.bottom,
				'padding-left': options.padding.left,
				'padding-right': options.padding.right
			});
		}

		// Remove old content.
		if (contentContainer == contentEl){
			contentEl.empty();
		}

		// Load new content.
		switch(loadMethod){
			case 'xhr':
				var data = options.data != null ? new Hash(options.data).toQueryString() : "";
				new Request.HTML({
					url: options.url,
					update: contentContainer,
					method: method,
					data: data, 
					evalScripts: currentInstance.options.evalScripts,
					evalResponse: currentInstance.options.evalResponse,
					onRequest: function(){
						if (recipient == 'window' && contentContainer == contentEl){
							currentInstance.showSpinner(spinnerEl);
						}
						else if (recipient == 'panel' && contentContainer == contentEl && $('spinner')){
							$('spinner').setStyle('visibility','visible');	
						}
					}.bind(this),
					onFailure: function(){
						if (contentContainer == contentEl){
							contentContainer.set('html','<p><strong>Error Loading XMLHttpRequest</strong></p>');
							if (recipient == 'window') {
								currentInstance.hideSpinner(spinnerEl);
							}
							else if (recipient == 'panel' && $('spinner')) {
								$('spinner').setStyle('visibility', 'hidden');
							}
						}
					}.bind(this),
					onException: function(){}.bind(this),
					onSuccess: function(){
						if (contentContainer == contentEl){
							if (recipient == 'window'){
								currentInstance.hideSpinner(spinnerEl);
							}
							else if (recipient == 'panel' && $('spinner')){
								$('spinner').setStyle('visibility', 'hidden');
							}
							currentInstance.fireEvent('onContentLoaded', element);
						}
					}.bind(this),
					onComplete: function(){}.bind(this)
				}).send();
				break;
			case 'iframe': // May be able to streamline this if the iframe already exists.
				if ( currentInstance.options.contentURL == '' || contentContainer != contentEl) {
					break;
				}
				currentInstance.iframeEl = new Element('iframe', {
					'id': currentInstance.options.id + '_iframe',
					'name':  currentInstance.options.id + '_iframe',
					'class': 'mochaIframe',
					'src': options.url,
					'marginwidth':  0,
					'marginheight': 0,
					'frameBorder':  0,
					'scrolling':    'auto',
					'styles': {
						'height': contentWrapperEl.offsetHeight - contentWrapperEl.getStyle('border-top').toInt() - contentWrapperEl.getStyle('border-bottom').toInt(),
						'width': currentInstance.panelEl ? contentWrapperEl.offsetWidth - contentWrapperEl.getStyle('border-left').toInt() - contentWrapperEl.getStyle('border-right').toInt() : '100%'	
					}
				}).injectInside(contentEl);

				// Add onload event to iframe so we can hide the spinner and run onContentLoaded()
				currentInstance.iframeEl.addEvent('load', function(e) {
					if (recipient == 'window') {
						currentInstance.hideSpinner(spinnerEl);
					}
					else if (recipient == 'panel' && contentContainer == contentEl && $('spinner')) {
						$('spinner').setStyle('visibility', 'hidden');
					}
					currentInstance.fireEvent('onContentLoaded', element);
				}.bind(this));
				if (recipient == 'window') {
					currentInstance.showSpinner(spinnerEl);
				}
				else if (recipient == 'panel' && contentContainer == contentEl && $('spinner')){
					$('spinner').setStyle('visibility', 'visible');	
				}
				break;
			case 'html':
			default:
				// Need to test injecting elements as content.
				var elementTypes = new Array('element', 'textnode', 'whitespace', 'collection');
				if (elementTypes.contains($type(options.content))){
					options.content.inject(contentContainer);
				} else {
					contentContainer.set('html', options.content);
				}
				currentInstance.fireEvent('onContentLoaded', element);
				break;
		}

	},
	/*
	
	Function: reloadIframe
		Reload an iframe. Fixes an issue in Firefox when trying to use location.reload on an iframe that has been destroyed and recreated.

	Arguments:
		iframe - This should be both the name and the id of the iframe.

	Syntax:
		(start code)
		MochaUI.reloadIframe(element);
		(end)

	Example:
		To reload an iframe from within another iframe:
		(start code)
		parent.MochaUI.reloadIframe('myIframeName');
		(end)

	*/
	reloadIframe: function(iframe){
		if (Browser.Engine.gecko) {
			$(iframe).src = $(iframe).src;
		}
		else {
			top.frames[iframe].location.reload(true);
		}
	},
	collapseToggle: function(windowEl){
		var instances = MochaUI.Windows.instances;
		var currentInstance = instances.get(windowEl.id);
		var handles = currentInstance.windowEl.getElements('.handle');
		if (currentInstance.isMaximized == true) return;		
		if (currentInstance.isCollapsed == false) {
			currentInstance.isCollapsed = true;
			handles.setStyle('display', 'none');
			if ( currentInstance.iframeEl ) {
				currentInstance.iframeEl.setStyle('visibility', 'hidden');
			}
			currentInstance.contentBorderEl.setStyles({
				visibility: 'hidden',
				position: 'absolute',
				top: -10000,
				left: -10000
			});
			if(currentInstance.toolbarWrapperEl){
				currentInstance.toolbarWrapperEl.setStyles({
					visibility: 'hidden',
					position: 'absolute',
					top: -10000,
					left: -10000
				});
			}
			currentInstance.drawWindowCollapsed(windowEl);
		}
		else {
			currentInstance.isCollapsed = false;
			currentInstance.drawWindow(windowEl);
			currentInstance.contentBorderEl.setStyles({
				visibility: 'visible',
				position: null,
				top: null,
				left: null
			});
			if(currentInstance.toolbarWrapperEl){
				currentInstance.toolbarWrapperEl.setStyles({
					visibility: 'visible',
					position: null,
					top: null,
					left: null
				});
			}
			if ( currentInstance.iframeEl ) {
				currentInstance.iframeEl.setStyle('visibility', 'visible');
			}
			handles.setStyle('display', 'block');
		}
	},
	/*

	Function: closeWindow
		Closes a window.

	Syntax:
	(start code)
		MochaUI.closeWindow();
	(end)

	Arguments: 
		windowEl - the ID of the window to be closed

	Returns:
		true - the window was closed
		false - the window was not closed

	*/
	closeWindow: function(windowEl){
		// Does window exist and is not already in process of closing ?

		var instances = MochaUI.Windows.instances;
		var currentInstance = instances.get(windowEl.id);
		if (windowEl != $(windowEl) || currentInstance.isClosing) return;
			
		currentInstance.isClosing = true;
		currentInstance.fireEvent('onClose', windowEl);
		if (currentInstance.check) currentInstance.check.destroy();

		if ((currentInstance.options.type == 'modal' || currentInstance.options.type == 'modal2') && Browser.Engine.trident4){
				$('modalFix').setStyle('display', 'none');
		}

		if (MochaUI.options.useEffects == false){
			if (currentInstance.options.type == 'modal' || currentInstance.options.type == 'modal2'){
				$('modalOverlay').setStyle('opacity', 0);
			}
			MochaUI.closingJobs(windowEl);
			return true;	
		}
		else {
			// Redraws IE windows without shadows since IE messes up canvas alpha when you change element opacity
			if (Browser.Engine.trident) currentInstance.drawWindow(windowEl, false);
			if (currentInstance.options.type == 'modal' || currentInstance.options.type == 'modal2'){
				MochaUI.Modal.modalOverlayCloseMorph.start({
					'opacity': 0
				});
			}
			var closeMorph = new Fx.Morph(windowEl, {
				duration: 120,
				onComplete: function(){
					MochaUI.closingJobs(windowEl);
					return true;
				}.bind(this)
			});
			closeMorph.start({
				'opacity': .4
			});
		}

	},
	closingJobs: function(windowEl){

		var instances = MochaUI.Windows.instances;
		var currentInstance = instances.get(windowEl.id);
		windowEl.setStyle('visibility', 'hidden');
		windowEl.destroy();
		currentInstance.fireEvent('onCloseComplete');
		
		if (currentInstance.options.type != 'notification'){
			var newFocus = this.getWindowWithHighestZindex();
			this.focusWindow(newFocus);
		}

		instances.erase(currentInstance.options.id);
		if (this.loadingWorkspace == true) {
			this.windowUnload();
		}

		if (MochaUI.Dock && $(MochaUI.options.dock) && currentInstance.options.type == 'window') {
			var currentButton = $(currentInstance.options.id + '_dockTab');
			if (currentButton != null) {
				MochaUI.Dock.dockSortables.removeItems(currentButton).destroy();
			}
			// Need to resize everything in case the dock becomes smaller when a tab is removed
			MochaUI.Desktop.setDesktopSize();
		}
	},
	/*
	
	Function: closeAll	
		Close all open windows.

	*/
	closeAll: function() {		
		$$('div.mocha').each(function(windowEl){
			this.closeWindow(windowEl);
		}.bind(this));
	},
	/*

	Function: toggleWindowVisibility
		Toggle window visibility with Ctrl-Alt-Q.

	*/	
	toggleWindowVisibility: function(){
		MochaUI.Windows.instances.each(function(instance){
			if (instance.options.type == 'modal' || instance.options.type == 'modal2' || instance.isMinimized == true) return;									
			var id = $(instance.options.id);
			if (id.getStyle('visibility') == 'visible'){
				if (instance.iframe){
					instance.iframeEl.setStyle('visibility', 'hidden');
				}
				if (instance.toolbarEl){
					instance.toolbarWrapperEl.setStyle('visibility', 'hidden');
				}
				instance.contentBorderEl.setStyle('visibility', 'hidden');
				id.setStyle('visibility', 'hidden');
				MochaUI.Windows.windowsVisible = false;
			}
			else {
				id.setStyle('visibility', 'visible');
				instance.contentBorderEl.setStyle('visibility', 'visible');
				if (instance.iframe){
					instance.iframeEl.setStyle('visibility', 'visible');
				}
				if (instance.toolbarEl){
					instance.toolbarWrapperEl.setStyle('visibility', 'visible');
				}
				MochaUI.Windows.windowsVisible = true;
			}
		}.bind(this));

	},
	focusWindow: function(windowEl, fireEvent){

		// This is used with blurAll
		MochaUI.focusingWindow = 'true';
		var windowClicked = function(){
			MochaUI.focusingWindow = 'false';
		};		
		windowClicked.delay(170, this);

		// Only focus when needed
		if ($$('.mocha').length == 0) return;
		if (windowEl != $(windowEl) || windowEl.hasClass('isFocused')) return;

		var instances =  MochaUI.Windows.instances;
		var currentInstance = instances.get(windowEl.id);
	
		if (currentInstance.options.type == 'notification') return;

		MochaUI.Windows.indexLevel += 2;
		windowEl.setStyle('zIndex', MochaUI.Windows.indexLevel);

		// Used when dragging and resizing windows
		$('windowUnderlay').setStyle('zIndex', MochaUI.Windows.indexLevel - 1).inject($(windowEl),'after');

		// Fire onBlur for the window that lost focus.
		instances.each(function(instance){
			if (instance.windowEl.hasClass('isFocused')){
				instance.fireEvent('onBlur', instance.windowEl);
			}
			instance.windowEl.removeClass('isFocused');
		});

		if (MochaUI.Dock && $(MochaUI.options.dock) && currentInstance.options.type == 'window') {
			MochaUI.Dock.makeActiveTab();
		}
		currentInstance.windowEl.addClass('isFocused');

		if (fireEvent != false){
			currentInstance.fireEvent('onFocus', windowEl);
		}

	},
	getWindowWithHighestZindex: function(){
		this.highestZindex = 0;
		$$('div.mocha').each(function(element){
			this.zIndex = element.getStyle('zIndex');
			if (this.zIndex >= this.highestZindex) {
				this.highestZindex = this.zIndex;
			}	
		}.bind(this));
		$$('div.mocha').each(function(element){
			if (element.getStyle('zIndex') == this.highestZindex) {
				this.windowWithHighestZindex = element;
			}
		}.bind(this));
		return this.windowWithHighestZindex;
	},
	blurAll: function(){
		if (MochaUI.focusingWindow == 'false') {
			$$('.mocha').each(function(windowEl){
				var instances =  MochaUI.Windows.instances;
				var currentInstance = instances.get(windowEl.id);
				if (currentInstance != null && currentInstance.options.type != 'modal' && currentInstance.options.type != 'modal2'){
					windowEl.removeClass('isFocused');
				}
			});
			$$('div.dockTab').removeClass('activeDockTab');
		}
	},
	roundedRect: function(ctx, x, y, width, height, radius, rgb, a){
		ctx.fillStyle = 'rgba(' + rgb.join(',') + ',' + a + ')';
		ctx.beginPath();
		ctx.moveTo(x, y + radius);
		ctx.lineTo(x, y + height - radius);
		ctx.quadraticCurveTo(x, y + height, x + radius, y + height);
		ctx.lineTo(x + width - radius, y + height);
		ctx.quadraticCurveTo(x + width, y + height, x + width, y + height - radius);
		ctx.lineTo(x + width, y + radius);
		ctx.quadraticCurveTo(x + width, y, x + width - radius, y);
		ctx.lineTo(x + radius, y);
		ctx.quadraticCurveTo(x, y, x, y + radius);
		ctx.fill(); 
	},
	triangle: function(ctx, x, y, width, height, rgb, a){
		ctx.beginPath();
		ctx.moveTo(x + width, y);
		ctx.lineTo(x, y + height);
		ctx.lineTo(x + width, y + height);
		ctx.closePath();
		ctx.fillStyle = 'rgba(' + rgb.join(',') + ',' + a + ')';
		ctx.fill();
	},
	circle: function(ctx, x, y, diameter, rgb, a){
		ctx.beginPath();
		ctx.moveTo(x, y);
		ctx.arc(x, y, diameter, 0, Math.PI*2, true);
		ctx.fillStyle = 'rgba(' + rgb.join(',') + ',' + a + ')';
		ctx.fill();
	},
	/*

	Function: centerWindow
		Center a window in it's container. If windowEl is undefined it will center the window that has focus.

	*/
	centerWindow: function(windowEl){
		
		if(!windowEl){
			MochaUI.Windows.instances.each(function(instance){
				if (instance.windowEl.hasClass('isFocused')){
					windowEl = instance.windowEl;
				}
			});
		}

		var currentInstance = MochaUI.Windows.instances.get(windowEl.id);
		var options = currentInstance.options;
		var dimensions = options.container.getCoordinates();
				
		var windowPosTop = window.getScroll().y + (window.getSize().y * .5) - (windowEl.offsetHeight * .5);
		if (windowPosTop < 0) {
			windowPosTop = 0;
		}
		var windowPosLeft =	(dimensions.width * .5) - (windowEl.offsetWidth * .5);
		if (windowPosLeft < 0) {
			windowPosLeft = 0;
		}
		if (MochaUI.options.useEffects == true){
			currentInstance.morph.start({
				'top': windowPosTop,
				'left': windowPosLeft
			});
		}
		else {
			windowEl.setStyles({
				'top': windowPosTop,
				'left': windowPosLeft
			});
		}
	},
	notification: function(message){
			new MochaUI.Window({
				loadMethod: 'html',
				closeAfter: 1500,
				type: 'notification',
				addClass: 'notification',
				content: message,
				width: 220,
				height: 40,
				y: 53,
				padding:  { top: 10, right: 12, bottom: 10, left: 12 },
				shadowBlur: 5,
				bodyBgColor: [255, 255, 255]	
			});
	},
	/*

	Function: dynamicResize
		Use with a timer to resize a window as the window's content size changes, such as with an accordian.

	*/
	dynamicResize: function(windowEl){

		var currentInstance = MochaUI.Windows.instances.get(windowEl.id);
		var contentWrapperEl = currentInstance.contentWrapperEl;
		var contentEl = currentInstance.contentEl;
		
		contentWrapperEl.setStyle('height', contentEl.offsetHeight);
		contentWrapperEl.setStyle('width', contentEl.offsetWidth);			
		currentInstance.drawWindow(windowEl);
	},	
	/*

	Function: garbageCleanUp
		Empties all windows of their children, and removes and garbages the windows. It is does not trigger onClose() or onCloseComplete(). This is useful to clear memory before the pageUnload.

	Syntax:
	(start code)
		MochaUI.garbageCleanUp();
	(end)
	
	*/
	garbageCleanUp: function(){
		$$('div.mocha').each(function(el){
			el.destroy();
		}.bind(this));
	},
	/*
	
	The underlay is inserted directly under windows when they are being dragged or resized
	so that the cursor is not captured by iframes or other plugins (such as Flash)
	underneath the window.
	
	*/
	underlayInitialize: function(){
		var windowUnderlay = new Element('div', {
			'id': 'windowUnderlay',
			'styles': {
				'height': parent.getCoordinates().height,
				'opacity': .01,
				'display': 'none'
			}
		}).inject(document.body);
	},
	setUnderlaySize: function(){
	   if( $('windowUnderlay') != null ) {
		$('windowUnderlay').setStyle('height', parent.getCoordinates().height);
	   }
	}
});

/* 

function: fixPNG
	Bob Osola's PngFix for IE6.

example:
	(begin code)
	<img src="xyz.png" alt="foo" width="10" height="20" onload="fixPNG(this)">
	(end)

note:
	You must have the image height and width attributes specified in the markup.

*/

function fixPNG(myImage){
	if (Browser.Engine.trident4 && document.body.filters){
		var imgID = (myImage.id) ? "id='" + myImage.id + "' " : "";
		var imgClass = (myImage.className) ? "class='" + myImage.className + "' " : "";
		var imgTitle = (myImage.title) ? "title='" + myImage.title  + "' " : "title='" + myImage.alt + "' ";
		var imgStyle = "display:inline-block;" + myImage.style.cssText;
		var strNewHTML = "<span " + imgID + imgClass + imgTitle
			+ " style=\"" + "width:" + myImage.width
			+ "px; height:" + myImage.height
			+ "px;" + imgStyle + ";"
			+ "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
			+ "(src=\'" + myImage.src + "\', sizingMethod='scale');\"></span>";
		myImage.outerHTML = strNewHTML;		
	}
}

// Toggle window visibility with Ctrl-Alt-Q
document.addEvent('keydown', function(event){
	if (event.key == 'q' && event.control && event.alt) {
		MochaUI.toggleWindowVisibility();
	}
});

// Blur all windows if user clicks anywhere else on the page
document.addEvent('mousedown', function(event){
	MochaUI.blurAll.delay(50);
});

document.addEvent('domready', function(){
	MochaUI.underlayInitialize();
});

window.addEvent('resize', function(){
		MochaUI.setUnderlaySize();
});
/*

Script: Window.js
	Build windows.

Copyright:
	Copyright (c) 2007-2008 Greg Houston, <http://greghoustondesign.com/>.

License:
	MIT-style license.	

Requires:
	Core.js

*/

/*
Class: Window
	Creates a single MochaUI window.
	
Syntax:
	(start code)
	new MochaUI.Window(options);
	(end)	

Arguments:
	options

Options:
	id - The ID of the window. If not defined, it will be set to 'win' + windowIDCount.
	title - The title of the window.
	icon - Place an icon in the window's titlebar. This is either set to false or to the url of the icon. It is set up for icons that are 16 x 16px.
	type - ('window', 'modal', 'modal2', or 'notification') Defaults to 'window'.
	loadMethod - ('html', 'xhr', or 'iframe') Defaults to 'html'.
	contentURL - Used if loadMethod is set to 'xhr' or 'iframe'.
	closeAfter - Either false or time in milliseconds. Closes the window after a certain period of time in milliseconds. This is particularly useful for notifications.
	evalScripts - (boolean) An xhr loadMethod option. Defaults to true.
	evalResponse - (boolean) An xhr loadMethod option. Defaults to false.
	content - (string or element) An html loadMethod option.
	toolbar - (boolean) Create window toolbar. Defaults to false. This can be used for tabs, media controls, and so forth.
	toolbarPosition - ('top' or 'bottom') Defaults to top.
	toolbarHeight - (number)
	toolbarURL - (url) Defaults to 'pages/lipsum.html'.
	toolbarContent - (string)
	container - (element ID) Element the window is injected in. The container defaults to 'desktop'. If no desktop then to document.body. Use 'pageWrapper' if you don't want the windows to overlap the toolbars.
	restrict - (boolean) Restrict window to container when dragging.
	shape - ('box' or 'gauge') Shape of window. Defaults to 'box'.
	collapsible - (boolean) Defaults to true.
	minimizable - (boolean) Requires MochaUI.Desktop and MochaUI.Dock. Defaults to true if dependenices are met. 
	maximizable - (boolean) Requires MochaUI.Desktop. Defaults to true if dependenices are met.
	closable - (boolean) Defaults to true. 
	draggable - (boolean) Defaults to false for modals; otherwise true.
	draggableGrid - (false or number) Distance in pixels for snap-to-grid dragging. Defaults to false. 
	draggableLimit - (false or number) An object with x and y properties used to limit the movement of the Window. Defaults to false.
	draggableSnap - (boolean) The distance to drag before the Window starts to respond to the drag. Defaults to false.
	resizable - (boolean) Defaults to false for modals, notifications and gauges; otherwise true.
	resizeLimit - (object) Minimum and maximum width and height of window when resized.
	addClass - (string) Add a class to the window for more control over styling.	
	width - (number) Width of content area.	
	height - (number) Height of content area.
	x - (number) If x and y are left undefined the window is centered on the page.
	y - (number)
	scrollbars - (boolean)
	padding - (object)
	shadowBlur - (number) Width of shadows.
	shadowOffset - Should be positive and not be greater than the ShadowBlur.
	controlsOffset - Change this if you want to reposition the window controls.
	useCanvas - (boolean) Set this to false if you don't want a canvas body.
	useCanvasControls - (boolean) Set this to false if you wish to use images for the buttons.
	headerHeight - (number) Height of window titlebar.
	footerHeight - (number) Height of window footer.
	cornerRadius - (number)
	contentBgColor - (hex) Body background color
	headerStartColor - ([r,g,b,]) Titlebar gradient's top color
	headerStopColor - ([r,g,b,]) Titlebar gradient's bottom color
	bodyBgColor - ([r,g,b,]) Background color of the main canvas shape
	minimizeBgColor - ([r,g,b,]) Minimize button background color
	minimizeColor - ([r,g,b,]) Minimize button color
	maximizeBgColor - ([r,g,b,]) Maximize button background color
	maximizeColor - ([r,g,b,]) Maximize button color
	closeBgColor - ([r,g,b,]) Close button background color
	closeColor - ([r,g,b,]) Close button color
	resizableColor - ([r,g,b,]) Resizable icon color
	onBeforeBuild - (function) Fired just before the window is built.
	onContentLoaded - (function) Fired when content is successfully loaded via XHR or Iframe.
	onFocus - (function)  Fired when the window is focused.
	onBlur - (function) Fired when window loses focus.
	onResize - (function) Fired when the window is resized.
	onMinimize - (function) Fired when the window is minimized.
	onMaximize - (function) Fired when the window is maximized.
	onRestore - (function) Fired when a window is restored from minimized or maximized.
	onClose - (function) Fired just before the window is closed.
	onCloseComplete - (function) Fired after the window is closed.

Returns:
	Window object.

Example:
	Define a window. It is suggested you name the function the same as your window ID + "Window".
	(start code)
	var mywindowWindow = function(){
		new MochaUI.Window({
			id: 'mywindow',
			title: 'My Window',
			loadMethod: 'xhr',
			contentURL: 'pages/lipsum.html',
			width: 340,
			height: 150
		});
	}
	(end)

Example:
	Create window onDomReady.
	(start code)	
	window.addEvent('domready', function(){
		mywindow();
	});
	(end)

Example:
	Add link events to build future windows. It is suggested you give your anchor the same ID as your window + "WindowLink" or + "WindowLinkCheck". Use the latter if it is a link in the menu toolbar.

	If you wish to add links in windows that open other windows remember to add events to those links when the windows are created.

	(start code)
	// Javascript:
	if ($('mywindowLink')){
		$('mywindowLink').addEvent('click', function(e) {
			new Event(e).stop();
			mywindow();
		});
	}

	// HTML:
	<a id="mywindowLink" href="pages/lipsum.html">My Window</a>	
	(end)


	Loading Content with an XMLHttpRequest(xhr):
		For content to load via xhr all the files must be online and in the same domain. If you need to load content from another domain or wish to have it work offline, load the content in an iframe instead of using the xhr option.
	
	Iframes:
		If you use the iframe loadMethod your iframe will automatically be resized when the window it is in is resized. If you want this same functionality when using one of the other load options simply add class="mochaIframe" to those iframes and they will be resized for you as well.

*/

// Having these options outside of the Class allows us to add, change, and remove
// individual options without rewriting all of them.

MochaUI.Windows.windowOptions = {
	id:                null,
	title:             'New Window',
	icon:              false,
	type:              'window',

	loadMethod:        'html',
	method:	           'get',
	contentURL:        'pages/lipsum.html',
	data:              null,

	closeAfter:        false,

	// xhr options
	evalScripts:       true,
	evalResponse:      false,

	// html options
	content:           'Window content',

	// Toolbar
	toolbar:           false,
	toolbarPosition:   'top',
	toolbarHeight:     29,
	toolbarURL:        'pages/lipsum.html',
	toolbarData:	   null,
	toolbarContent:    '',

	// Toolbar
	toolbar2:           false,
	toolbar2Position:   'bottom',
	toolbar2Height:     29,
	toolbar2URL:        'pages/lipsum.html',
	toolbar2Data:	    null,
	toolbar2Content:    '',	

	// Container options
	container:         null,
	restrict:          true,
	shape:             'box',

	// Window Controls
	collapsible:       true,
	minimizable:       true,
	maximizable:       true,
	closable:          true,

	// Draggable
	draggable:         null,
	draggableGrid:     false,
	draggableLimit:    false,
	draggableSnap:     false,

	// Resizable
	resizable:         null,
	resizeLimit:       {'x': [250, 2500], 'y': [125, 2000]},
	
	// Style options:
	addClass:          '',
	width:             300,
	height:            125,
	x:                 null,
	y:                 null,
	scrollbars:        true,
	padding:   		   { top: 10, right: 12, bottom: 10, left: 12 },
	shadowBlur:        5,
	shadowOffset:      {'x': 0, 'y': 1},
	controlsOffset:    {'right': 6, 'top': 6},
	useCanvas:         true,
	useCanvasControls: true,
	useSpinner:        true,    // Toggles whether or not the ajax spinners are displayed in window footers.

	// Color options:		
	headerHeight:      25,
	footerHeight:      25,
	cornerRadius:      8,
	contentBgColor:    '#fff',
	headerStartColor:  [250, 250, 250],
	headerStopColor:   [229, 229, 229],
	bodyBgColor:       [229, 229, 229],
	minimizeBgColor:   [255, 255, 255],
	minimizeColor:     [0, 0, 0],
	maximizeBgColor:   [255, 255, 255],
	maximizeColor:     [0, 0, 0],
	closeBgColor:      [255, 255, 255],
	closeColor:        [0, 0, 0],
	resizableColor:    [254, 254, 254],

	// Events
	onBeforeBuild:     $empty,
	onContentLoaded:   $empty,
	onFocus:           $empty,
	onBlur:            $empty,
	onResize:          $empty,
	onMinimize:        $empty,
	onMaximize:        $empty,
	onRestore:         $empty,
	onClose:           $empty,
	onCloseComplete:   $empty
};

MochaUI.Window = new Class({
	options: MochaUI.Windows.windowOptions,
	initialize: function(options){
		this.setOptions(options);

		// Shorten object chain
		var options = this.options;

		$extend(this, {
			mochaControlsWidth: 0,
			minimizebuttonX:  0,  // Minimize button horizontal position
			maximizebuttonX: 0,  // Maximize button horizontal position
			closebuttonX: 0,  // Close button horizontal position
			headerFooterShadow: options.headerHeight + options.footerHeight + (options.shadowBlur * 2),
			oldTop: 0,
			oldLeft: 0,
			isMaximized: false,
			isMinimized: false,
			isCollapsed: false,
			timestamp: $time()
		});
		
		// May be better to use if type != window
		if (options.type != 'window'){
			options.container = document.body;
			options.minimizable = false;
		}
		if (!options.container){
			options.container = MochaUI.Desktop && MochaUI.Desktop.desktop ? MochaUI.Desktop.desktop : document.body;
		}

		// Set this.options.resizable to default if it was not defined
		if (options.resizable == null){
			if (options.type != 'window' || options.shape == 'gauge'){
				options.resizable = false;
			}
			else {
				options.resizable = true;	
			}
		}

		// Set this.options.draggable if it was not defined
		if (options.draggable == null){
			if (options.type != 'window'){
				options.draggable = false;
			}
			else {
				options.draggable = true;
			}
		}

		// Gauges are not maximizable or resizable
		if (options.shape == 'gauge' || options.type == 'notification'){
			options.collapsible = false;
			options.maximizable = false;
			options.contentBgColor = 'transparent';
			options.scrollbars = false;
			options.footerHeight = 0;
		}
		if (options.type == 'notification'){
			options.closable = false;
			options.headerHeight = 0;
		}
		
		// Minimizable, dock is required and window cannot be modal
		if (MochaUI.Dock && $(MochaUI.options.dock)){
			if (MochaUI.Dock.dock && options.type != 'modal' && options.type != 'modal2'){
				options.minimizable = options.minimizable;
			}
		}
		else {
			options.minimizable = false;
		}

		// Maximizable, desktop is required
		options.maximizable = MochaUI.Desktop && MochaUI.Desktop.desktop && options.maximizable && options.type != 'modal' && options.type != 'modal2';

		if (this.options.type == 'modal2') {
			this.options.shadowBlur = 0;
			this.options.shadowOffset = {'x': 0, 'y': 0};
			this.options.useSpinner = false;
			this.options.useCanvas = false;
			this.options.footerHeight = 0;
			this.options.headerHeight = 0;
		}

		// If window has no ID, give it one.
		if (options.id == null){
			options.id = 'win' + (++MochaUI.Windows.windowIDCount);
		}
		this.windowEl = $(options.id);
		
		this.newWindow();
		
		// Return window object
		return this;
	},
	saveValues: function(){	
		var coordinates = this.windowEl.getCoordinates();
		this.options.x = coordinates.left.toInt();
		this.options.y = coordinates.top.toInt();
	},
	/*

	Internal Function: newWindow
	
	Arguments: 
		properties

	*/
	newWindow: function(properties){ // options is not doing anything

		// Shorten object chain
		var instances = MochaUI.Windows.instances;
		var instanceID = instances.get(this.options.id);
	
		// Here we check to see if there is already a class instance for this window
		if (instanceID){
			var currentInstance = instanceID;
		}

		// Check if window already exists and is not in progress of closing
		if ( this.windowEl && !this.isClosing ){
			 // Restore if minimized
			if (currentInstance.isMinimized){
				MochaUI.Dock.restoreMinimized(this.windowEl);
			}
			// Expand and focus if collapsed
			if (currentInstance.isCollapsed){
				MochaUI.collapseToggle(this.windowEl);
				setTimeout(MochaUI.focusWindow.pass(this.windowEl, this),10);
			}
			// Else focus
			else {
				var coordinates = document.getCoordinates();
				if (this.windowEl.getStyle('left').toInt() > coordinates.width || this.windowEl.getStyle('top').toInt() > coordinates.height){
					MochaUI.centerWindow(this.windowEl);	
				}
				setTimeout(MochaUI.focusWindow.pass(this.windowEl, this),10);
			}
			return;
		}
		else {
			instances.set(this.options.id, this);
		}

		this.isClosing = false;
		this.fireEvent('onBeforeBuild');

		// Create window div
		MochaUI.Windows.indexLevel++;
		this.windowEl = new Element('div', {
			'class': 'mocha',
			'id':    this.options.id,
			'styles': {
				'width':   this.options.width,
				'height':  this.options.height,
				'display': 'block',
				'opacity': 0,
				'zIndex': MochaUI.Windows.indexLevel += 2
			}
		});

		this.windowEl.addClass(this.options.addClass);
		
		if (this.options.type == 'modal2') {
			this.windowEl.addClass('modal2');
		}

		// Fix a mouseover issue with gauges in IE7
		if ( Browser.Engine.trident && this.options.shape == 'gauge') {
			this.windowEl.setStyle('background', 'url(../img/spacer.gif)');
		}

		if ((this.options.type == 'modal' || this.options.type == 'modal2' ) && Browser.Platform.mac && Browser.Engine.gecko){
			if (/Firefox[\/\s](\d+\.\d+)/.test(navigator.userAgent)) {
				var ffversion = new Number(RegExp.$1);
				if (ffversion < 3) {
					this.windowEl.setStyle('position', 'fixed');
				}
			}
		}

		if (this.options.loadMethod == 'iframe') {
			this.options.padding = { top: 0, right: 0, bottom: 0, left: 0 };
		}

		// Insert sub elements inside windowEl
		this.insertWindowElements();

		// Set title
		this.titleEl.set('html',this.options.title);

		// Set scrollbars, always use 'hidden' for iframe windows
		this.contentWrapperEl.setStyles({
			'overflow': 'hidden',
			'background': this.options.contentBgColor
		});

		this.contentEl.setStyles({
			'padding-top': this.options.padding.top,
			'padding-bottom': this.options.padding.bottom,
			'padding-left': this.options.padding.left,
			'padding-right': this.options.padding.right
		});


		if (this.options.shape == 'gauge'){
			if (this.options.useCanvasControls){
				this.canvasControlsEl.setStyle('display', 'none');
			}
			else {
				this.controlsEl.setStyle('display', 'none');
			}
			this.windowEl.addEvent('mouseover', function(){
				this.mouseover = true;
				var showControls = function(){
					if (this.mouseover != false){
						if (this.options.useCanvasControls){
							this.canvasControlsEl.setStyle('display', 'block');
						}
						else {
							this.controlsEl.setStyle('display', 'block');
						}
						this.canvasHeaderEl.setStyle('display', 'block');
						this.titleEl.setStyle('display', 'block');
					}
				};
				showControls.delay(150, this);

			}.bind(this));
			this.windowEl.addEvent('mouseleave', function(){
				this.mouseover = false;
				if (this.options.useCanvasControls){
					this.canvasControlsEl.setStyle('display', 'none');
				}
				else {
					this.controlsEl.setStyle('display', 'none');
				}
				this.canvasHeaderEl.setStyle('display', 'none');
				this.titleEl.setStyle('display', 'none');
			}.bind(this));
		}

		// Inject window into DOM
		this.windowEl.injectInside(this.options.container);

		if (this.options.type != 'notification'){
			this.setMochaControlsWidth();
		}		

		// Add content to window.
		MochaUI.updateContent({
			'element': this.windowEl,
			'content':  this.options.content,
			'method': this.options.method,
			'url':      this.options.contentURL,
			'data': this.options.data
		});	
		
		// Add content to window toolbar.
		if (this.options.toolbar == true){
			MochaUI.updateContent({
				'element':       this.windowEl,
				'childElement':  this.toolbarEl,
				'content':       this.options.toolbarContent,
				'loadMethod':    'xhr',
				'method':	 this.options.method,
				'url':           this.options.toolbarURL,
				'data':		 this.options.toolbarData
			});
		}

		// Add content to window toolbar.
		if (this.options.toolbar2 == true){
			MochaUI.updateContent({
				'element':       this.windowEl,
				'childElement':  this.toolbar2El,
				'content':       this.options.toolbar2Content,
				'loadMethod':    'xhr',
				'method':	 this.options.method,
				'url':           this.options.toolbar2URL,
				'data':		 this.options.toolbar2Data
			});
		}
		
		this.drawWindow(this.windowEl);
		
		// Attach events to the window
		this.attachDraggable(this.windowEl);
		this.attachResizable(this.windowEl);
		this.setupEvents(this.windowEl);
		
		if (this.options.resizable){
			this.adjustHandles();
		}

		// Position window. If position not specified by user then center the window on the page.
		if (this.options.container == document.body || this.options.container == MochaUI.Desktop.desktop){
			var dimensions = window.getSize();
		}
		else {
			var dimensions = $(this.options.container).getSize();
		}

		if (!this.options.y) {
			if (MochaUI.Desktop.desktop) {
				var y = (dimensions.y * .5) - (this.windowEl.offsetHeight * .5);			
			}
			else {
				var y = window.getScroll().y + (window.getSize().y * .5) - (this.windowEl.offsetHeight * .5);
			}
		}
		else {
			var y = this.options.y - this.options.shadowBlur;
		}

		if (!this.options.x) {
			var x =	(dimensions.x * .5) - (this.windowEl.offsetWidth * .5);
		}
		else {
			var x = this.options.x - this.options.shadowBlur;
		}

		this.windowEl.setStyles({
			'top': y,
			'left': x
		});

		// Create opacityMorph
		if (MochaUI.options.useEffects == true){
			// IE cannot handle both element opacity and VML alpha at the same time.
			if (Browser.Engine.trident){
				this.drawWindow(this.windowEl, false);
			}
			this.opacityMorph = new Fx.Morph(this.windowEl, {
				'duration': 350,
				transition: Fx.Transitions.Sine.easeInOut,
				onComplete: function(){
					if (Browser.Engine.trident){
						this.drawWindow(this.windowEl);
					}
				}.bind(this)
			});
		}

		if (this.options.type == 'modal' || this.options.type == 'modal2') {
			MochaUI.currentModal = this.windowEl;
			if (Browser.Engine.trident4){
				$('modalFix').setStyle('display', 'block');
			}
			$('modalOverlay').setStyle('display', 'block');
			if (MochaUI.options.useEffects == false){
				$('modalOverlay').setStyle('opacity', .6);
				this.windowEl.setStyles({
					'zIndex': 11000,
					'opacity': 1
				});
			}
			else {
				MochaUI.Modal.modalOverlayCloseMorph.cancel();
				MochaUI.Modal.modalOverlayOpenMorph.start({
					'opacity': .6
				});
				this.windowEl.setStyles({
					'zIndex': 11000
				});
				this.opacityMorph.start({
					'opacity': 1
				});
			}

			$$('.dockTab').removeClass('activeDockTab');
			$$('.mocha').removeClass('isFocused');
			this.windowEl.addClass('isFocused');
			
		}
		else if (MochaUI.options.useEffects == false){
			this.windowEl.setStyle('opacity', 1);
			setTimeout(MochaUI.focusWindow.pass(this.windowEl, this), 10);
		}
		else {
			this.opacityMorph.start({
				'opacity': 1
			});
			setTimeout(MochaUI.focusWindow.pass(this.windowEl, this), 10);
		}

		// This is a generic morph that can be reused later by functions like centerWindow()
		this.morph = new Fx.Morph(this.windowEl, {
			'duration': 200
		});

		// Add check mark to menu if link exists in menu
		// Need to make sure the check mark is not added to links not in menu
	
		if ($(this.windowEl.id + 'LinkCheck')){
			this.check = new Element('div', {
				'class': 'check',
				'id': this.options.id + '_check'
			}).inject(this.windowEl.id + 'LinkCheck');
		}
		
		if (this.options.closeAfter != false){
			MochaUI.closeWindow.delay(this.options.closeAfter, this, this.windowEl);
		}

		if (MochaUI.Dock && $(MochaUI.options.dock) && this.options.type == 'window' ){
			MochaUI.Dock.createDockTab(this.windowEl);
		}

	},
	setupEvents: function(windowEl) {

		// Set events
		// Note: if a button does not exist, its due to properties passed to newWindow() stating otherwice
		if (this.closeButtonEl){
			this.closeButtonEl.addEvent('click', function(e) {
				new Event(e).stop();
				MochaUI.closeWindow(windowEl);
			}.bind(this));
		}

		if (this.options.type == 'window'){
			windowEl.addEvent('mousedown', function() {
				MochaUI.focusWindow(windowEl);
			}.bind(this));
		}

		if (this.minimizeButtonEl) {
			this.minimizeButtonEl.addEvent('click', function(e) {
				new Event(e).stop();
				MochaUI.Dock.minimizeWindow(windowEl);
		}.bind(this));
		}

		if (this.maximizeButtonEl) {
			this.maximizeButtonEl.addEvent('click', function(e) {
				new Event(e).stop(); 
				if (this.isMaximized) {
					MochaUI.Desktop.restoreWindow(windowEl);
				} else {
					MochaUI.Desktop.maximizeWindow(windowEl);
				}
			}.bind(this));
		}

		if (this.options.collapsible == true){
			// Keep titlebar text from being selected on double click in Safari.
			this.titleEl.addEvent('selectstart', function(e) {
				e = new Event(e).stop();
			}.bind(this));
			// Keep titlebar text from being selected on double click in Opera.
			this.titleBarEl.addEvent('mousedown', function(e) {
				if (Browser.Engine.trident) {
					this.titleEl.setCapture();
				}
			}.bind(this));
			this.titleBarEl.addEvent('mouseup', function(e) {
				if (Browser.Engine.trident) {
					this.titleEl.releaseCapture();
				}
			}.bind(this));
			this.titleBarEl.addEvent('dblclick', function(e) {
				e = new Event(e).stop();
				MochaUI.collapseToggle(this.windowEl);
			}.bind(this));
		}

	},
	/*

	Internal Function: attachDraggable()
		Make window draggable.

	Arguments:
		windowEl
		
	*/
	attachDraggable: function(windowEl){
		if (!this.options.draggable) return;
		this.windowDrag = new Drag.Move(windowEl, {
			handle: this.titleBarEl,
			container: this.options.restrict == true ? $(this.options.container) : false,
			grid: this.options.draggableGrid,
			limit: this.options.draggableLimit,
			snap: this.options.draggableSnap,
			onStart: function() {
				if (this.options.type != 'modal' && this.options.type != 'modal2'){ 
					MochaUI.focusWindow(windowEl);
					$('windowUnderlay').setStyle('display','block');
				}
				if ( this.iframeEl )
					this.iframeEl.setStyle('visibility', 'hidden');
			}.bind(this),
			onComplete: function() {
				if (this.options.type != 'modal' && this.options.type != 'modal2') {
					$('windowUnderlay').setStyle('display', 'none');
				}
				if ( this.iframeEl ){
					this.iframeEl.setStyle('visibility', 'visible');
				}
				// Store new position in options.
				this.saveValues();
			}.bind(this)
		});
	},
	/*

	Internal Function: attachResizable
		Make window resizable.

	Arguments:
		windowEl

	*/
	attachResizable: function(windowEl){
		if (!this.options.resizable) return;
		this.resizable1 = this.windowEl.makeResizable({
			handle: [this.n, this.ne, this.nw],
			limit: {
				y: [
					function(){
						return this.windowEl.getStyle('top').toInt() + this.windowEl.getStyle('height').toInt() - this.options.resizeLimit.y[1];
					}.bind(this),
					function(){
						return this.windowEl.getStyle('top').toInt() + this.windowEl.getStyle('height').toInt() - this.options.resizeLimit.y[0];
					}.bind(this)
				]
			},
			modifiers: {x: false, y: 'top'},
			onStart: function(){
				this.resizeOnStart();
				this.coords = this.contentWrapperEl.getCoordinates();
				this.y2 = this.coords.top.toInt() + this.contentWrapperEl.offsetHeight;
			}.bind(this),
			onDrag: function(){
				this.coords = this.contentWrapperEl.getCoordinates();
				this.contentWrapperEl.setStyle('height', this.y2 - this.coords.top.toInt());
				this.drawWindow(windowEl);
				this.adjustHandles();
			}.bind(this),
			onComplete: function(){
				this.resizeOnComplete();
			}.bind(this)
		});

		this.resizable2 = this.contentWrapperEl.makeResizable({
			handle: [this.e, this.ne],
			limit: {
				x: [this.options.resizeLimit.x[0] - (this.options.shadowBlur * 2), this.options.resizeLimit.x[1] - (this.options.shadowBlur * 2) ]
			},	
			modifiers: {x: 'width', y: false},
			onStart: function(){
				this.resizeOnStart();
			}.bind(this),
			onDrag: function(){
				this.drawWindow(windowEl);
				this.adjustHandles();
			}.bind(this),
			onComplete: function(){
				this.resizeOnComplete();
			}.bind(this)
		});

		this.resizable3 = this.contentWrapperEl.makeResizable({
			container: this.options.restrict == true ? $(this.options.container) : false,
			handle: this.se,
			limit: {
				x: [this.options.resizeLimit.x[0] - (this.options.shadowBlur * 2), this.options.resizeLimit.x[1] - (this.options.shadowBlur * 2) ],
				y: [this.options.resizeLimit.y[0] - this.headerFooterShadow, this.options.resizeLimit.y[1] - this.headerFooterShadow]
			},
			modifiers: {x: 'width', y: 'height'},
			onStart: function(){
				this.resizeOnStart();
			}.bind(this),
			onDrag: function(){
				this.drawWindow(windowEl);	
				this.adjustHandles();
			}.bind(this),
			onComplete: function(){
				this.resizeOnComplete();
			}.bind(this)	
		});

		this.resizable4 = this.contentWrapperEl.makeResizable({
			handle: [this.s, this.sw],
			limit: {
				y: [this.options.resizeLimit.y[0] - this.headerFooterShadow, this.options.resizeLimit.y[1] - this.headerFooterShadow]
			},
			modifiers: {x: false, y: 'height'},
			onStart: function(){
				this.resizeOnStart();
			}.bind(this),
			onDrag: function(){
				this.drawWindow(windowEl);
				this.adjustHandles();
			}.bind(this),
			onComplete: function(){
				this.resizeOnComplete();
			}.bind(this)
		});

		this.resizable5 = this.windowEl.makeResizable({
			handle: [this.w, this.sw, this.nw],
			limit: {
				x: [
					function(){
						return this.windowEl.getStyle('left').toInt() + this.windowEl.getStyle('width').toInt() - this.options.resizeLimit.x[1];
					}.bind(this),
				   function(){
					   return this.windowEl.getStyle('left').toInt() + this.windowEl.getStyle('width').toInt() - this.options.resizeLimit.x[0];
					}.bind(this)
				]
			},
			modifiers: {x: 'left', y: false},
			onStart: function(){
				this.resizeOnStart();
				this.coords = this.contentWrapperEl.getCoordinates();
				this.x2 = this.coords.left.toInt() + this.contentWrapperEl.offsetWidth;
			}.bind(this),
			onDrag: function(){
				this.coords = this.contentWrapperEl.getCoordinates();
				this.contentWrapperEl.setStyle('width', this.x2 - this.coords.left.toInt());
				this.drawWindow(windowEl);
				this.adjustHandles();
			}.bind(this),
			onComplete: function(){
				this.resizeOnComplete();
			}.bind(this)
		});

	},
	resizeOnStart: function(){
		$('windowUnderlay').setStyle('display','block');
		if (this.iframeEl){
			this.iframeEl.setStyle('visibility', 'hidden');
		}	
	},	
	resizeOnComplete: function(){
		$('windowUnderlay').setStyle('display','none');
		if (this.iframeEl){
			this.iframeEl.setStyle('visibility', 'visible');
		}
		this.fireEvent('onResize', this.windowEl);
	},
	adjustHandles: function(){

		var shadowBlur = this.options.shadowBlur;
		var shadowBlur2x = shadowBlur * 2;
		var shadowOffset = this.options.shadowOffset;
		var top = shadowBlur - shadowOffset.y - 1;
		var right = shadowBlur + shadowOffset.x - 1;
		var bottom = shadowBlur + shadowOffset.y - 1;
		var left = shadowBlur - shadowOffset.x - 1;
		
		var coordinates = this.windowEl.getCoordinates();
		var width = coordinates.width - shadowBlur2x + 2;
		var height = coordinates.height - shadowBlur2x + 2;

		this.n.setStyles({
			'top': top,
			'left': left + 10,
			'width': width - 20
		});
		this.e.setStyles({
			'top': top + 10,
			'right': right,
			'height': height - 30
		});
		this.s.setStyles({
			'bottom': bottom,
			'left': left + 10,
			'width': width - 30
		});
		this.w.setStyles({
			'top': top + 10,
			'left': left,
			'height': height - 20
		});
		this.ne.setStyles({
			'top': top,
			'right': right	
		});
		this.se.setStyles({
			'bottom': bottom,
			'right': right
		});
		this.sw.setStyles({
			'bottom': bottom,
			'left': left
		});
		this.nw.setStyles({
			'top': top,
			'left': left
		});
	},
	detachResizable: function(){
			this.resizable1.detach();
			this.resizable2.detach();
			this.resizable3.detach();
			this.resizable4.detach();
			this.resizable5.detach();
			this.windowEl.getElements('.handle').setStyle('display', 'none');
	},
	reattachResizable: function(){
			this.resizable1.attach();
			this.resizable2.attach();
			this.resizable3.attach();
			this.resizable4.attach();
			this.resizable5.attach();
			this.windowEl.getElements('.handle').setStyle('display', 'block');
	},
	/*

	Internal Function: insertWindowElements

	Arguments:
		windowEl

	*/
	insertWindowElements: function(){
		
		var options = this.options;
		var height = options.height;
		var width = options.width;
		var id = options.id;

		var cache = {};

		if (Browser.Engine.trident4){
			cache.zIndexFixEl = new Element('iframe', {
				'id': id + '_zIndexFix',
				'class': 'zIndexFix',
				'scrolling': 'no',
				'marginWidth': 0,
				'marginHeight': 0,
				'src': ''
			}).inject(this.windowEl);
		}

		cache.overlayEl = new Element('div', {
			'id': id + '_overlay',
			'class': 'mochaOverlay'
		}).inject(this.windowEl);

		cache.titleBarEl = new Element('div', {
			'id': id + '_titleBar',
			'class': 'mochaTitlebar',
			'styles': {
				'cursor': options.draggable ? 'move' : 'default'
			}
		}).inject(cache.overlayEl, 'top');

		cache.titleEl = new Element('h3', {
			'id': id + '_title',
			'class': 'mochaTitle'
		}).inject(cache.titleBarEl);

		if (options.icon != false){
			cache.titleBarEl.setStyles({
				'padding-left': 15,
				'background': 'url(' + options.icon + ') 5px 5px no-repeat'
			});
		}
		
		cache.contentBorderEl = new Element('div', {
			'id': id + '_contentBorder',
			'class': 'mochaContentBorder'
		}).inject(cache.overlayEl);

		if (options.toolbar){
			cache.toolbarWrapperEl = new Element('div', {
				'id': id + '_toolbarWrapper',
				'class': 'mochaToolbarWrapper'
			}).inject(cache.contentBorderEl, options.toolbarPosition == 'bottom' ? 'after' : 'before');

			if (options.toolbarPosition == 'bottom') {
				cache.toolbarWrapperEl.addClass('bottom');
			}
			cache.toolbarEl = new Element('div', {
				'id': id + '_toolbar',
				'class': 'mochaToolbar'
			}).inject(cache.toolbarWrapperEl);
		}

		if (options.toolbar2){
			cache.toolbar2WrapperEl = new Element('div', {
				'id': id + '_toolbar2Wrapper',
				'class': 'mochaToolbarWrapper'
			}).inject(cache.contentBorderEl, options.toolbar2Position == 'bottom' ? 'after' : 'before');

			if (options.toolbar2Position == 'bottom') {
				cache.toolbar2WrapperEl.addClass('bottom');
			}
			cache.toolbar2El = new Element('div', {
				'id': id + '_toolbar2',
				'class': 'mochaToolbar'
			}).inject(cache.toolbar2WrapperEl);
		}

		cache.contentWrapperEl = new Element('div', {
			'id': id + '_contentWrapper',
			'class': 'mochaContentWrapper',
			'styles': {
				'width': width + 'px',
				'height': height + 'px'
			}
		}).inject(cache.contentBorderEl);
		
		if (this.options.shape == 'gauge'){
			cache.contentBorderEl.setStyle('borderWidth', 0);
		}

		cache.contentEl = new Element('div', {
			'id': id + '_content',
			'class': 'mochaContent'
		}).inject(cache.contentWrapperEl);

		if (this.options.useCanvas == true && Browser.Engine.trident != true) {
			cache.canvasEl = new Element('canvas', {
				'id': id + '_canvas',
				'class': 'mochaCanvas',
				'width': 10,
				'height': 10
			}).inject(this.windowEl);
		}
		
		if (this.options.useCanvas == true && Browser.Engine.trident) {
			cache.canvasEl = new Element('canvas', {
				'id': id + '_canvas',
				'class': 'mochaCanvas',
				'width': 50000, // IE8 excanvas requires these large numbers
				'height': 20000,
				'styles': {
					'position': 'absolute',
					'top': 0,
					'left': 0
				}
			}).inject(this.windowEl);

			if (MochaUI.ieSupport == 'excanvas'){
				G_vmlCanvasManager.initElement(cache.canvasEl);
				cache.canvasEl = this.windowEl.getElement('.mochaCanvas');
			}
		}		

		cache.controlsEl = new Element('div', {
			'id': id + '_controls',
			'class': 'mochaControls'
		}).inject(cache.overlayEl, 'after');

		if (options.useCanvasControls == true){
			cache.canvasControlsEl = new Element('canvas', {
				'id': id + '_canvasControls',
				'class': 'mochaCanvasControls',
				'width': 14,
				'height': 14
			}).inject(this.windowEl);

			if (Browser.Engine.trident && MochaUI.ieSupport == 'excanvas'){
				G_vmlCanvasManager.initElement(cache.canvasControlsEl);
				cache.canvasControlsEl = this.windowEl.getElement('.mochaCanvasControls');
			}
		}

		if (options.closable){
			cache.closeButtonEl = new Element('div', {
				'id': id + '_closeButton',
				'class': 'mochaCloseButton',
				'title': 'Close'
			}).inject(cache.controlsEl);
			if (options.useCanvasControls == true){
				cache.closeButtonEl.setStyle('background', 'none');
			}
		}

		if (options.maximizable){
			cache.maximizeButtonEl = new Element('div', {
				'id': id + '_maximizeButton',
				'class': 'mochaMaximizeButton',
				'title': 'Maximize'
			}).inject(cache.controlsEl);
			if (options.useCanvasControls == true){
				cache.maximizeButtonEl.setStyle('background', 'none');
			}
		}

		if (options.minimizable){
			cache.minimizeButtonEl = new Element('div', {
				'id': id + '_minimizeButton',
				'class': 'mochaMinimizeButton',
				'title': 'Minimize'
			}).inject(cache.controlsEl);
			if (options.useCanvasControls == true){
				cache.minimizeButtonEl.setStyle('background', 'none');
			}
		}

		if (options.useSpinner == true && options.shape != 'gauge' && options.type != 'notification'){
			cache.spinnerEl = new Element('div', {
				'id': id + '_spinner',
				'class': 'mochaSpinner',
				'width': 16,
				'height': 16
			}).inject(this.windowEl, 'bottom');
		}

		if (this.options.shape == 'gauge'){
			cache.canvasHeaderEl = new Element('canvas', {
				'id': id + '_canvasHeader',
				'class': 'mochaCanvasHeader',
				'width': this.options.width,
				'height': 26
			}).inject(this.windowEl, 'bottom');
		
			if (Browser.Engine.trident && MochaUI.ieSupport == 'excanvas'){
				G_vmlCanvasManager.initElement(cache.canvasHeaderEl);
				cache.canvasHeaderEl = this.windowEl.getElement('.mochaCanvasHeader');
			}
		}

		if ( Browser.Engine.trident ){
			cache.overlayEl.setStyle('zIndex', 2);
		}

		// For Mac Firefox 2 to help reduce scrollbar bugs in that browser
		if (Browser.Platform.mac && Browser.Engine.gecko){
			if (/Firefox[\/\s](\d+\.\d+)/.test(navigator.userAgent)){
				var ffversion = new Number(RegExp.$1);
				if (ffversion < 3){
					cache.overlayEl.setStyle('overflow', 'auto');
				}
			}
		}

		if (options.resizable){
			cache.n = new Element('div', {
				'id': id + '_resizeHandle_n',
				'class': 'handle',
				'styles': {
					'top': 0,
					'left': 10,
					'cursor': 'n-resize'
				}
			}).inject(cache.overlayEl, 'after');

			cache.ne = new Element('div', {
				'id': id + '_resizeHandle_ne',
				'class': 'handle corner',
				'styles': {
					'top': 0,
					'right': 0,
					'cursor': 'ne-resize'
				}
			}).inject(cache.overlayEl, 'after');
			
			cache.e = new Element('div', {
				'id': id + '_resizeHandle_e',
				'class': 'handle',		
				'styles': {
					'top': 10,
					'right': 0,
					'cursor': 'e-resize'
				}
			}).inject(cache.overlayEl, 'after');
			
			cache.se = new Element('div', {
				'id': id + '_resizeHandle_se',
				'class': 'handle cornerSE',
				'styles': {
					'bottom': 0,
					'right': 0,
					'cursor': 'se-resize'
				}
			}).inject(cache.overlayEl, 'after');

			cache.s = new Element('div', {
				'id': id + '_resizeHandle_s',
				'class': 'handle',
				'styles': {
					'bottom': 0,
					'left': 10,
					'cursor': 's-resize'
				}
			}).inject(cache.overlayEl, 'after');
			
			cache.sw = new Element('div', {
				'id': id + '_resizeHandle_sw',
				'class': 'handle corner',
				'styles': {
					'bottom': 0,
					'left': 0,
					'cursor': 'sw-resize'
				}
			}).inject(cache.overlayEl, 'after');
			
			cache.w = new Element('div', {
				'id': id + '_resizeHandle_w',
				'class': 'handle',		
				'styles': {
					'top': 10,
					'left': 0,
					'cursor': 'w-resize'
				}
			}).inject(cache.overlayEl, 'after');
			
			cache.nw = new Element('div', {
				'id': id + '_resizeHandle_nw',
				'class': 'handle corner',		
				'styles': {
					'top': 0,
					'left': 0,
					'cursor': 'nw-resize'
				}
			}).inject(cache.overlayEl, 'after');
		}
		$extend(this, cache);
		
	},
	/*

	Internal function: drawWindow
		This is where we create the canvas GUI	

	Arguments: 
		windowEl: the $(window)
		shadows: (boolean) false will draw a window without shadows

	*/	
	drawWindow: function(windowEl, shadows) {
				
		if (this.isCollapsed){
			this.drawWindowCollapsed(windowEl, shadows);
			return;
		}

		var options = this.options;
		var shadowBlur = options.shadowBlur;
		var shadowBlur2x = shadowBlur * 2;
		var shadowOffset = this.options.shadowOffset;

		this.overlayEl.setStyles({
			'width': this.contentWrapperEl.offsetWidth
		});

		// Resize iframe when window is resized
		if (this.iframeEl) {
			this.iframeEl.setStyles({
				'height': this.contentWrapperEl.offsetHeight
			});
		}

		var borderHeight = this.contentBorderEl.getStyle('border-top').toInt() + this.contentBorderEl.getStyle('border-bottom').toInt();
		var toolbarHeight = this.toolbarWrapperEl ? this.toolbarWrapperEl.getStyle('height').toInt() + this.toolbarWrapperEl.getStyle('border-top').toInt() : 0;
		var toolbar2Height = this.toolbar2WrapperEl ? this.toolbar2WrapperEl.getStyle('height').toInt() + this.toolbar2WrapperEl.getStyle('border-top').toInt() : 0;

		this.headerFooterShadow = options.headerHeight + options.footerHeight + shadowBlur2x;
		var height = this.contentWrapperEl.getStyle('height').toInt() + this.headerFooterShadow + toolbarHeight + toolbar2Height + borderHeight;
		var width = this.contentWrapperEl.getStyle('width').toInt() + shadowBlur2x;
		this.windowEl.setStyles({
			'height': height,
			'width': width
		});

		this.overlayEl.setStyles({
			'height': height,
			'top': shadowBlur - shadowOffset.y,
			'left': shadowBlur - shadowOffset.x
		});		

		if (this.options.useCanvas == true) {
			if (Browser.Engine.trident) {
				this.canvasEl.height = 20000;
				this.canvasEl.width = 50000;
			}
			this.canvasEl.height = height;
			this.canvasEl.width = width;
		}

		// Part of the fix for IE6 select z-index bug
		if (Browser.Engine.trident4){
			this.zIndexFixEl.setStyles({
				'width': width,
				'height': height
			})
		}

		this.titleBarEl.setStyles({
			'width': width - shadowBlur2x,
			'height': options.headerHeight
		});

		// Make sure loading icon is placed correctly.
		if (options.useSpinner == true && options.shape != 'gauge' && options.type != 'notification'){
			this.spinnerEl.setStyles({
				'left': shadowBlur - shadowOffset.x + 3,
				'bottom': shadowBlur + shadowOffset.y +  4
			});
		}
		
		if (this.options.useCanvas != false) {
		
			// Draw Window
			var ctx = this.canvasEl.getContext('2d');
			ctx.clearRect(0, 0, width, height);
			
			switch (options.shape) {
				case 'box':
					this.drawBox(ctx, width, height, shadowBlur, shadowOffset, shadows);
					break;
				case 'gauge':
					this.drawGauge(ctx, width, height, shadowBlur, shadowOffset, shadows);
					break;
			}


			if (options.resizable){ 
				MochaUI.triangle(
					ctx,
					width - (shadowBlur + shadowOffset.x + 17),
					height - (shadowBlur + shadowOffset.y + 18),
					11,
					11,
					options.resizableColor,
					1.0
				);
			}

			// Invisible dummy object. The last element drawn is not rendered consistently while resizing in IE6 and IE7
			if (Browser.Engine.trident){
				MochaUI.triangle(ctx, 0, 0, 10, 10, options.resizableColor, 0);
			}
		}
		
		if (options.type != 'notification' && options.useCanvasControls == true){
			this.drawControls(width, height, shadows);
		}

	},
	drawWindowCollapsed: function(windowEl, shadows) {
		
		var options = this.options;
		var shadowBlur = options.shadowBlur;
		var shadowBlur2x = shadowBlur * 2;
		var shadowOffset = options.shadowOffset;
		
		var headerShadow = options.headerHeight + shadowBlur2x + 2;
		var height = headerShadow;
		var width = this.contentWrapperEl.getStyle('width').toInt() + shadowBlur2x;
		this.windowEl.setStyle('height', height);
		
		this.overlayEl.setStyles({
			'height': height,
			'top': shadowBlur - shadowOffset.y,
			'left': shadowBlur - shadowOffset.x
		});		

		this.canvasEl.height = height;
		this.canvasEl.width = width;

		// Part of the fix for IE6 select z-index bug
		if (Browser.Engine.trident4){
			this.zIndexFixEl.setStyles({
				'width': width,
				'height': height
			});
		}

		// Set width
		this.windowEl.setStyle('width', width);
		this.overlayEl.setStyle('width', width);
		this.titleBarEl.setStyles({
			'width': width - shadowBlur2x,
			'height': options.headerHeight
		});
	
		// Draw Window
		if (this.options.useCanvas != false) {
			var ctx = this.canvasEl.getContext('2d');
			ctx.clearRect(0, 0, width, height);
			
			this.drawBoxCollapsed(ctx, width, height, shadowBlur, shadowOffset, shadows);
			if (options.useCanvasControls == true) {
				this.drawControls(width, height, shadows);
			}
			
			// Invisible dummy object. The last element drawn is not rendered consistently while resizing in IE6 and IE7
			if (Browser.Engine.trident){
				MochaUI.triangle(ctx, 0, 0, 10, 10, options.resizableColor, 0);
			}
		}

	},	
	drawControls : function(width, height, shadows){
		var options = this.options;
		var shadowBlur = options.shadowBlur;
		var shadowOffset = options.shadowOffset;
		var controlsOffset = options.controlsOffset;
		
		// Make sure controls are placed correctly.
		this.controlsEl.setStyles({
			'right': shadowBlur + shadowOffset.x + controlsOffset.right,
			'top': shadowBlur - shadowOffset.y + controlsOffset.top
		});

		this.canvasControlsEl.setStyles({
			'right': shadowBlur + shadowOffset.x + controlsOffset.right,
			'top': shadowBlur - shadowOffset.y + controlsOffset.top
		});

		// Calculate X position for controlbuttons
		//var mochaControlsWidth = 52;
		this.closebuttonX = options.closable ? this.mochaControlsWidth - 7 : this.mochaControlsWidth + 12;
		this.maximizebuttonX = this.closebuttonX - (options.maximizable ? 19 : 0);
		this.minimizebuttonX = this.maximizebuttonX - (options.minimizable ? 19 : 0);
		
		var ctx2 = this.canvasControlsEl.getContext('2d');
		ctx2.clearRect(0, 0, 100, 100);

		if (this.options.closable){
			this.closebutton(
				ctx2,
				this.closebuttonX,
				7,
				options.closeBgColor,
				1.0,
				options.closeColor,
				1.0
			);
		}
		if (this.options.maximizable){
			this.maximizebutton(
				ctx2,
				this.maximizebuttonX,
				7,
				options.maximizeBgColor,
				1.0,
				options.maximizeColor,
				1.0
			);
		}
		if (this.options.minimizable){
			this.minimizebutton(
				ctx2,
				this.minimizebuttonX,
				7,
				options.minimizeBgColor,
				1.0,
				options.minimizeColor,
				1.0
			);
		}
					// Invisible dummy object. The last element drawn is not rendered consistently while resizing in IE6 and IE7
			if (Browser.Engine.trident){
				MochaUI.circle(ctx2, 0, 0, 3, this.options.resizableColor, 0);
			}
		
	},
	drawBox: function(ctx, width, height, shadowBlur, shadowOffset, shadows){

		var shadowBlur2x = shadowBlur * 2;
		var cornerRadius = this.options.cornerRadius;

		// This is the drop shadow. It is created onion style.
		if ( shadows != false ) {	
			for (var x = 0; x <= shadowBlur; x++){
				MochaUI.roundedRect(
					ctx,
					shadowOffset.x + x,
					shadowOffset.y + x,
					width - (x * 2) - shadowOffset.x,
					height - (x * 2) - shadowOffset.y,
					cornerRadius + (shadowBlur - x),
					[0, 0, 0],
					x == shadowBlur ? .29 : .065 + (x * .01)
				);
			}
		}
		// Window body.
		this.bodyRoundedRect(
			ctx,                          // context
			shadowBlur - shadowOffset.x,  // x
			shadowBlur - shadowOffset.y,  // y
			width - shadowBlur2x,         // width
			height - shadowBlur2x,        // height
			cornerRadius,                 // corner radius
			this.options.bodyBgColor      // Footer color
		);

		if (this.options.type != 'notification'){
		// Window header.
			this.topRoundedRect(
				ctx,                            // context
				shadowBlur - shadowOffset.x,    // x
				shadowBlur - shadowOffset.y,    // y
				width - shadowBlur2x,           // width
				this.options.headerHeight,      // height
				cornerRadius,                   // corner radius
				this.options.headerStartColor,  // Header gradient's top color
				this.options.headerStopColor    // Header gradient's bottom color
			);
		}	
	},
	drawBoxCollapsed: function(ctx, width, height, shadowBlur, shadowOffset, shadows){

		var options = this.options;
		var shadowBlur2x = shadowBlur * 2;
		var cornerRadius = options.cornerRadius;
	
		// This is the drop shadow. It is created onion style.
		if ( shadows != false ){
			for (var x = 0; x <= shadowBlur; x++){
				MochaUI.roundedRect(
					ctx,
					shadowOffset.x + x,
					shadowOffset.y + x,
					width - (x * 2) - shadowOffset.x,
					height - (x * 2) - shadowOffset.y,
					cornerRadius + (shadowBlur - x),
					[0, 0, 0],
					x == shadowBlur ? .3 : .06 + (x * .01)
				);
			}
		}

		// Window header
		this.topRoundedRect2(
			ctx,                          // context
			shadowBlur - shadowOffset.x,  // x
			shadowBlur - shadowOffset.y,  // y
			width - shadowBlur2x,         // width
			options.headerHeight + 2,     // height
			cornerRadius,                 // corner radius
			options.headerStartColor,     // Header gradient's top color
			options.headerStopColor       // Header gradient's bottom color
		);

	},	
	drawGauge: function(ctx, width, height, shadowBlur, shadowOffset, shadows){
		var options = this.options;
		var radius = (width * .5) - (shadowBlur) + 16;
		if (shadows != false) {	
			for (var x = 0; x <= shadowBlur; x++){
				MochaUI.circle(
					ctx,
					width * .5 + shadowOffset.x,
					(height  + options.headerHeight) * .5 + shadowOffset.x,
					(width *.5) - (x * 2) - shadowOffset.x,
					[0, 0, 0],
					x == shadowBlur ? .75 : .075 + (x * .04)
				);
			}
		}
		MochaUI.circle(
			ctx,
			width * .5  - shadowOffset.x,
			(height + options.headerHeight) * .5  - shadowOffset.y,
			(width *.5) - shadowBlur,
			options.bodyBgColor,
			1
		);

		// Draw gauge header
		this.canvasHeaderEl.setStyles({
			'top': shadowBlur - shadowOffset.y,
			'left': shadowBlur - shadowOffset.x
		});		
		var ctx = this.canvasHeaderEl.getContext('2d');
		ctx.clearRect(0, 0, width, 100);
		ctx.beginPath();
		ctx.lineWidth = 24;
		ctx.lineCap = 'round';
		ctx.moveTo(13, 13);
		ctx.lineTo(width - (shadowBlur*2) - 13, 13);
		ctx.strokeStyle = 'rgba(0, 0, 0, .65)';
		ctx.stroke();
	},
	bodyRoundedRect: function(ctx, x, y, width, height, radius, rgb){
		ctx.fillStyle = 'rgba(' + rgb.join(',') + ', 100)';
		ctx.beginPath();
		ctx.moveTo(x, y + radius);
		ctx.lineTo(x, y + height - radius);
		ctx.quadraticCurveTo(x, y + height, x + radius, y + height);
		ctx.lineTo(x + width - radius, y + height);
		ctx.quadraticCurveTo(x + width, y + height, x + width, y + height - radius);
		ctx.lineTo(x + width, y + radius);
		ctx.quadraticCurveTo(x + width, y, x + width - radius, y);
		ctx.lineTo(x + radius, y);
		ctx.quadraticCurveTo(x, y, x, y + radius);
		ctx.fill();

	},
	topRoundedRect: function(ctx, x, y, width, height, radius, headerStartColor, headerStopColor){
		var lingrad = ctx.createLinearGradient(0, 0, 0, height);
		lingrad.addColorStop(0, 'rgba(' + headerStartColor.join(',') + ', 1)');
		lingrad.addColorStop(1, 'rgba(' + headerStopColor.join(',') + ', 1)');		
		ctx.fillStyle = lingrad;
		ctx.beginPath();
		ctx.moveTo(x, y);
		ctx.lineTo(x, y + height);
		ctx.lineTo(x + width, y + height);
		ctx.lineTo(x + width, y + radius);
		ctx.quadraticCurveTo(x + width, y, x + width - radius, y);
		ctx.lineTo(x + radius, y);
		ctx.quadraticCurveTo(x, y, x, y + radius);
		ctx.fill();

	},
	topRoundedRect2: function(ctx, x, y, width, height, radius, headerStartColor, headerStopColor){
		var lingrad = ctx.createLinearGradient(0, this.options.shadowBlur - 1, 0, height + this.options.shadowBlur + 3);
		lingrad.addColorStop(0, 'rgba(' + headerStartColor.join(',') + ', 1)');
		lingrad.addColorStop(1, 'rgba(' + headerStopColor.join(',') + ', 1)');
		ctx.fillStyle = lingrad;
		ctx.beginPath();
		ctx.moveTo(x, y + radius);
		ctx.lineTo(x, y + height - radius);
		ctx.quadraticCurveTo(x, y + height, x + radius, y + height);
		ctx.lineTo(x + width - radius, y + height);
		ctx.quadraticCurveTo(x + width, y + height, x + width, y + height - radius);
		ctx.lineTo(x + width, y + radius);
		ctx.quadraticCurveTo(x + width, y, x + width - radius, y);
		ctx.lineTo(x + radius, y);
		ctx.quadraticCurveTo(x, y, x, y + radius);
		ctx.fill();	
	},
	maximizebutton: function(ctx, x, y, rgbBg, aBg, rgb, a){
		// Circle
		ctx.beginPath();
		ctx.moveTo(x, y);
		ctx.arc(x, y, 7, 0, Math.PI*2, true);
		ctx.fillStyle = 'rgba(' + rgbBg.join(',') + ',' + aBg + ')';
		ctx.fill();
		// X sign
		ctx.strokeStyle = 'rgba(' + rgb.join(',') + ',' + a + ')';
		ctx.beginPath();
		ctx.moveTo(x, y - 4);
		ctx.lineTo(x, y + 4);
		ctx.stroke();
		ctx.beginPath();
		ctx.moveTo(x - 4, y);
		ctx.lineTo(x + 4, y);
		ctx.stroke();
	},
	closebutton: function(ctx, x, y, rgbBg, aBg, rgb, a){
		// Circle
		ctx.beginPath();
		ctx.moveTo(x, y);
		ctx.arc(x, y, 7, 0, Math.PI*2, true);
		ctx.fillStyle = 'rgba(' + rgbBg.join(',') + ',' + aBg + ')';
		ctx.fill();
		// Plus sign
		ctx.strokeStyle = 'rgba(' + rgb.join(',') + ',' + a + ')';
		ctx.beginPath();
		ctx.moveTo(x - 3, y - 3);
		ctx.lineTo(x + 3, y + 3);
		ctx.stroke();
		ctx.beginPath();
		ctx.moveTo(x + 3, y - 3);
		ctx.lineTo(x - 3, y + 3);
		ctx.stroke();
	},
	minimizebutton: function(ctx, x, y, rgbBg, aBg, rgb, a){
		// Circle
		ctx.beginPath();
		ctx.moveTo(x,y);
		ctx.arc(x,y,7,0,Math.PI*2,true);
		ctx.fillStyle = 'rgba(' + rgbBg.join(',') + ',' + aBg + ')';
		ctx.fill();
		// Minus sign
		ctx.strokeStyle = 'rgba(' + rgb.join(',') + ',' + a + ')';
		ctx.beginPath();
		ctx.moveTo(x - 4, y);
		ctx.lineTo(x + 4, y);
		ctx.stroke();
	},
	/*

	Function: hideSpinner
		Hides the spinner.
		
	*/	
	hideSpinner: function(spinner) {
		if ($(spinner))	$(spinner).setStyle('visibility', 'hidden');
	},
	/*

	Function: showSpinner
		Shows the spinner.
	
	*/	
	showSpinner: function(spinner){
		if (!this.options.useSpinner || this.options.shape == 'gauge' || this.options.type == 'notification') return;
		$(spinner).setStyles({
			'visibility': 'visible'
		});
	},
	setMochaControlsWidth: function(){
		this.mochaControlsWidth = 0;
		var options = this.options;
		if (options.minimizable){
			this.mochaControlsWidth += (this.minimizeButtonEl.getStyle('margin-left').toInt() + this.minimizeButtonEl.getStyle('width').toInt());
		}
		if (options.maximizable){
			this.mochaControlsWidth += (this.maximizeButtonEl.getStyle('margin-left').toInt() + this.maximizeButtonEl.getStyle('width').toInt());
		}
		if (options.closable){
			this.mochaControlsWidth += (this.closeButtonEl.getStyle('margin-left').toInt() + this.closeButtonEl.getStyle('width').toInt());
		}
		this.controlsEl.setStyle('width', this.mochaControlsWidth);
		if (options.useCanvasControls == true){
			this.canvasControlsEl.setProperty('width', this.mochaControlsWidth);
		}
	}
});
MochaUI.Window.implement(new Options, new Events);
/*

Script: Modal.js
	Create modal dialog windows.

Copyright:
	Copyright (c) 2007-2008 Greg Houston, <http://greghoustondesign.com/>.	

License:
	MIT-style license.	

Requires:
	Core.js, Window.js

See Also:
	<Window>	
	
*/

MochaUI.Modal = new Class({

	Extends: MochaUI.Window,

	Implements: [Events, Options],

	initialize: function(options){

		this.modalInitialize();
		
		window.addEvent('resize', function(){
			this.setModalSize();
		}.bind(this));

	},
	modalInitialize: function(){
		var modalOverlay = new Element('div', {
			'id': 'modalOverlay',
			'styles': {
				'height': document.getCoordinates().height,
				'opacity': .6
			}
		}).inject(document.body);
		
		modalOverlay.addEvent('click', function(e){
			MochaUI.closeWindow(MochaUI.currentModal);
		});
		
		if (Browser.Engine.trident4){
			var modalFix = new Element('iframe', {
				'id': 'modalFix',
				'scrolling': 'no',
				'marginWidth': 0,
				'marginHeight': 0,
				'src': '',
				'styles': {
					'height': document.getCoordinates().height
				}
			}).inject(document.body);
		}

		this.modalOverlayOpenMorph = new Fx.Morph($('modalOverlay'), {
				'duration': 150
				});
		this.modalOverlayCloseMorph = new Fx.Morph($('modalOverlay'), {
			'duration': 150,
			onComplete: function(){
				$('modalOverlay').setStyle('display', 'none');
				if (Browser.Engine.trident4){
					$('modalFix').setStyle('display', 'none');
				}
			}.bind(this)
		});
	},
	setModalSize: function(){
		$('modalOverlay').setStyle('height', document.getCoordinates().height);
		if (Browser.Engine.trident4){
			$('modalFix').setStyle('height', document.getCoordinates().height);
		}
	}
});
MochaUI.Modal.implement(new Options, new Events);
/*

Script: Windows-from-html.js
	Create windows from html markup in page.

Copyright:
	Copyright (c) 2007-2008 Greg Houston, <http://greghoustondesign.com/>.	

License:
	MIT-style license.	

Requires:
	Core.js, Window.js

Example:
	HTML markup.
	(start code)
<div class="mocha" id="mywindow" style="width:300px;height:255px;top:50px;left:350px">
	<h3 class="mochaTitle">My Window</h3>
	<p>My Window Content</p>
</div>	
	(end)

See Also:
	<Window>

*/

MochaUI.extend({
	NewWindowsFromHTML: function(){
		$$('div.mocha').each(function(el) {
			// Get the window title and destroy that element, so it does not end up in window content
			if ( Browser.Engine.presto || Browser.Engine.trident5 ){
				el.setStyle('display','block'); // Required by Opera, and probably IE7
			}
			var title = el.getElement('h3.mochaTitle');
			var elDimensions = el.getStyles('height', 'width');
			var properties = {
				id: el.getProperty('id'),
				height: elDimensions.height.toInt(),
				width: elDimensions.width.toInt(),
				x: el.getStyle('left').toInt(),
				y: el.getStyle('top').toInt()
			};
			// If there is a title element, set title and destroy the element so it does not end up in window content
			if ( title ) {
				properties.title = title.innerHTML;
				title.destroy();
			}
		
			// Get content and destroy the element
			properties.content = el.innerHTML;
			el.destroy();
			
			// Create window
			new MochaUI.Window(properties, true);
		}.bind(this));
	}
});
/*

Script: Windows-from-json.js
	Create one or more windows from JSON data. You can define all the same properties as you can for new MochaUI.Window(). Undefined properties are set to their defaults.

Copyright:
	Copyright (c) 2007-2008 Greg Houston, <http://greghoustondesign.com/>.	

License:
	MIT-style license.	

Syntax:
	(start code)
	MochaUI.newWindowsFromJSON(properties);
	(end)

Example:
	(start code)
	MochaUI.jsonWindows = function(){
		var url = 'data/json-windows-data.js';
		var request = new Request.JSON({
			url: url,
			method: 'get',
			onComplete: function(properties) {
				MochaUI.newWindowsFromJSON(properties.windows);
			}
		}).send();
	}
	(end)

Note: 
	Windows created from JSON are not compatible with the current cookie based version
	of Save and Load Workspace.  	

See Also:
	<Window>

*/

MochaUI.extend({	
	newWindowsFromJSON: function(properties){
		properties.each(function(properties) {
				new MochaUI.Window(properties);
		}.bind(this));
	}
});
/*

Script: Arrange-cascade.js
	Cascade windows.

Copyright:
	Copyright (c) 2007-2008 Greg Houston, <http://greghoustondesign.com/>.	

License:
	MIT-style license.	

Requires:
	Core.js, Window.js

Syntax:
	(start code)
	MochaUI.arrangeCascade();
	(end)

*/

MochaUI.options.extend({
	viewportTopOffset:  30,    // Use a negative number if neccessary to place first window where you want it
	viewportLeftOffset: 20,
	windowTopOffset:    50,    // Initial vertical spacing of each window
	windowLeftOffset:   40     // Initial horizontal spacing of each window	
});

MochaUI.extend({   
	arrangeCascade: function(){
		// See how much space we have to work with
		var coordinates = document.getCoordinates();
		
		var openWindows = 0;
		MochaUI.Windows.instances.each(function(instance){
			if (!instance.isMinimized) openWindows ++; 
		});
		
		if ((this.options.windowTopOffset * (openWindows + 1)) >= (coordinates.height - this.options.viewportTopOffset)) {
			var topOffset = (coordinates.height - this.options.viewportTopOffset) / (openWindows + 1);
		}
		else {
			var topOffset = this.options.windowTopOffset;
		}
		
		if ((this.options.windowLeftOffset * (openWindows + 1)) >= (coordinates.width - this.options.viewportLeftOffset - 20)) {
			var leftOffset = (coordinates.width - this.options.viewportLeftOffset - 20) / (openWindows + 1);
		}
		else {
			var leftOffset = this.options.windowLeftOffset;
		}

		var x = this.options.viewportLeftOffset;
		var y = this.options.viewportTopOffset;
		$$('div.mocha').each(function(windowEl){
			var currentWindowClass = MochaUI.Windows.instances.get(windowEl.id);
			if (!currentWindowClass.isMinimized && !currentWindowClass.isMaximized){
				id = windowEl.id;
				MochaUI.focusWindow(windowEl);
				x += leftOffset;
				y += topOffset;

				if (MochaUI.options.useEffects == false){
					windowEl.setStyles({
						'top': y,
						'left': x
					});
				}
				else {
					var cascadeMorph = new Fx.Morph(windowEl, {
						'duration': 550
					});
					cascadeMorph.start({
						'top': y,
						'left': x
					});
				}
			}
		}.bind(this));
	}
});
/*

Script: Arrange-tile.js
	Cascade windows.

Authors:
	Harry Roberts and Greg Houston

License:
	MIT-style license.	

Requires:
	Core.js, Window.js

Syntax:
	(start code)
	MochaUI.arrangeTile();
	(end)

*/
 
MochaUI.extend({
	arrangeTile: function(){
		var x = 10;
		var y = 10;
	
		var instances =  MochaUI.Windows.instances;

		var windowsNum = 0;

		instances.each(function(instance){
			if (!instance.isMinimized && !instance.isMaximized){
				windowsNum++;
			}
		});

		var cols = 3;
		var rows = Math.ceil(windowsNum / cols);
		
		var coordinates = document.getCoordinates();
	
		var col_width = ((coordinates.width - this.options.viewportLeftOffset) / cols);
		var col_height = ((coordinates.height - this.options.viewportTopOffset) / rows);
		
		var row = 0;
		var col = 0;
		
		instances.each(function(instance){
			if (!instance.isMinimized && !instance.isMaximized){
				
				var content = instance.contentWrapperEl;
				var content_coords = content.getCoordinates();
				var window_coords = instance.windowEl.getCoordinates();
				
				// Calculate the amount of padding around the content window
				var padding_top = content_coords.top - window_coords.top;
				var padding_bottom = window_coords.height - content_coords.height - padding_top;
				var padding_left = content_coords.left - window_coords.left;
				var padding_right = window_coords.width - content_coords.width - padding_left;

				/*

				// This resizes the windows
				if (instance.options.shape != 'gauge' && instance.options.resizable == true){
					var width = (col_width - 3 - padding_left - padding_right);
					var height = (col_height - 3 - padding_top - padding_bottom);

					if (width > instance.options.resizeLimit.x[0] && width < instance.options.resizeLimit.x[1]){
						content.setStyle('width', width);
					}
					if (height > instance.options.resizeLimit.y[0] && height < instance.options.resizeLimit.y[1]){
						content.setStyle('height', height);
					}

				}*/

				var left = (x + (col * col_width));
				var top = (y + (row * col_height));

				instance.windowEl.setStyles({
					'left': left,
					'top': top
				});

				instance.drawWindow(instance.windowEl);

				MochaUI.focusWindow(instance.windowEl);

				if (++col === cols) {
					row++;
					col = 0;
				}
			}
		}.bind(this));
	}
});/*

Script: Tabs.js
	Functionality for window tabs.

Copyright:
	Copyright (c) 2007-2008 Greg Houston, <http://greghoustondesign.com/>.	

License:
	MIT-style license.

Requires:
	Core.js, Window.js (for tabbed windows) or Layout.js (for tabbed panels)

*/

MochaUI.extend({
	/*

	Function: initializeTabs
		Add click event to each list item that fires the selected function.

	*/
	initializeTabs: function(el){
		$(el).getElements('li').each(function(listitem){
			listitem.addEvent('click', function(e){
				MochaUI.selected(this, el);
			});
		});
	},
	/*

	Function: selected
		Add "selected" class to current list item and remove it from sibling list items.

	Syntax:
		(start code)
			selected(el, parent);
		(end)

Arguments:
	el - the list item
	parent - the ul

	*/
	selected: function(el, parent){
		$(parent).getChildren().each(function(listitem){
			listitem.removeClass('selected');
		});
		el.addClass('selected');
	}
});

/*

Script: Layout.js
	Create web application layouts. Enables window maximize.
	
Copyright:
	Copyright (c) 2007-2008 Greg Houston, <http://greghoustondesign.com/>.

License:
	MIT-style license.	

Requires:
	Core.js, Window.js
	
*/

MochaUI.Desktop = new Class({

	Extends: MochaUI.Window,

	Implements: [Events, Options],
	
	options: {
		// Naming options:
		// If you change the IDs of the Mocha Desktop containers in your HTML, you need to change them here as well.
		desktop:             'desktop',
		desktopHeader:       'desktopHeader',
		desktopFooter:       'desktopFooter',
		desktopNavBar:       'desktopNavbar',
		pageWrapper:         'pageWrapper',
		page:                'page',
		desktopFooter:       'desktopFooterWrapper'
	},	
	initialize: function(options){
		this.setOptions(options);
		this.desktop         = $(this.options.desktop);
		this.desktopHeader   = $(this.options.desktopHeader);
		this.desktopNavBar   = $(this.options.desktopNavBar);
		this.pageWrapper     = $(this.options.pageWrapper);
		this.page            = $(this.options.page);
		this.desktopFooter   = $(this.options.desktopFooter);		
	
		// This is run on dock initialize so no need to do it twice.
		if (!MochaUI.Dock.dockWrapper){
			this.setDesktopSize();
		}
		this.menuInitialize();		

		// Resize desktop, page wrapper, modal overlay, and maximized windows when browser window is resized
		window.addEvent('resize', function(e){
			this.onBrowserResize();
		}.bind(this));
	},
	menuInitialize: function(){
		// Fix for dropdown menus in IE6
		if (Browser.Engine.trident4 && this.desktopNavBar){
			this.desktopNavBar.getElements('li').each(function(element) {
				element.addEvent('mouseenter', function(){
					this.addClass('ieHover');
				});
				element.addEvent('mouseleave', function(){
					this.removeClass('ieHover');
				});
			});
		};
	},
	onBrowserResize: function(){
		this.setDesktopSize();
		// Resize maximized windows to fit new browser window size
		setTimeout( function(){
			MochaUI.Windows.instances.each(function(instance){
				if (instance.isMaximized){

					// Hide iframe while resize for better performance
					if ( instance.iframeEl ){
						instance.iframeEl.setStyle('visibility', 'hidden');
					}

					var coordinates = document.getCoordinates();
					var borderHeight = instance.contentBorderEl.getStyle('border-top').toInt() + instance.contentBorderEl.getStyle('border-bottom').toInt();
					var toolbarHeight = instance.toolbarWrapperEl ? instance.toolbarWrapperEl.getStyle('height').toInt() + instance.toolbarWrapperEl.getStyle('border-top').toInt() : 0;
					instance.contentWrapperEl.setStyles({
						'height': coordinates.height - instance.options.headerHeight - instance.options.footerHeight - borderHeight - toolbarHeight,
						'width': coordinates.width
					});

					instance.drawWindow($(instance.options.id));
					if ( instance.iframeEl ){
						instance.iframeEl.setStyles({
							'height': instance.contentWrapperEl.getStyle('height')
						});
						instance.iframeEl.setStyle('visibility', 'visible');
					}

				}
			}.bind(this));
		}.bind(this), 100);
	},
	setDesktopSize: function(){
		var windowDimensions = window.getCoordinates();

		// var dock = $(MochaUI.options.dock);
		var dockWrapper = $(MochaUI.options.dockWrapper);
		
		// Setting the desktop height may only be needed by IE7
		if (this.desktop){
			this.desktop.setStyle('height', windowDimensions.height);
		}

		// Set pageWrapper height so the dock doesn't cover the pageWrapper scrollbars.
		if (this.pageWrapper) {

			var dockOffset = MochaUI.dockVisible ? dockWrapper.offsetHeight : 0;
			var pageWrapperHeight = windowDimensions.height;
			pageWrapperHeight -= this.pageWrapper.getStyle('border-top').toInt();
			pageWrapperHeight -= this.pageWrapper.getStyle('border-bottom').toInt();
			if (this.desktopHeader){ pageWrapperHeight -= this.desktopHeader.offsetHeight; }
			if (this.desktopFooter){ pageWrapperHeight -= this.desktopFooter.offsetHeight; }
			pageWrapperHeight -= dockOffset;

			if (pageWrapperHeight < 0){
				pageWrapperHeight = 0;
			}
			this.pageWrapper.setStyle('height', pageWrapperHeight);
		}

		if (MochaUI.Columns.instances.getKeys().length > 0){ // Conditional is a fix for a bug in IE6 in the no toolbars demo.
			MochaUI.Desktop.resizePanels();
		}		
	},
	resizePanels: function(){
		if (Browser.Engine.trident4){
			$$('.pad').setStyle('display', 'none');
			$$('.rHeight').setStyle('height', 1);
		}
		MochaUI.panelHeight();
		MochaUI.rWidth();
		if (Browser.Engine.trident4) $$('.pad').setStyle('display', 'block');
	},
	/*
	
	Function: maximizeWindow
		Maximize a window.
	
	Syntax:
		(start code)
		MochaUI.Desktop.maximizeWindow(windowEl);
		(end)	

	*/	
	maximizeWindow: function(windowEl){

		var currentInstance = MochaUI.Windows.instances.get(windowEl.id);
		var options = currentInstance.options;
		var windowDrag = currentInstance.windowDrag;

		// If window no longer exists or is maximized, stop
		if (windowEl != $(windowEl) || currentInstance.isMaximized ) return;
		
		if (currentInstance.isCollapsed){
			MochaUI.collapseToggle(windowEl);	
		}

		currentInstance.isMaximized = true;
		
		// If window is restricted to a container, it should not be draggable when maximized.
		if (currentInstance.options.restrict){
			windowDrag.detach();
			if (options.resizable) {
				currentInstance.detachResizable();
			}
			currentInstance.titleBarEl.setStyle('cursor', 'default');
		}	

		// If the window has a container that is not the desktop
		// temporarily move the window to the desktop while it is minimized.
		if (options.container != this.desktop){
			this.desktop.grab(windowEl);
			if (this.options.restrict){
			windowDrag.container = this.desktop;
			}
		}		

		// Save original position
		currentInstance.oldTop = windowEl.getStyle('top');
		currentInstance.oldLeft = windowEl.getStyle('left');

		var contentWrapperEl = currentInstance.contentWrapperEl;

		// Save original dimensions
		contentWrapperEl.oldWidth = contentWrapperEl.getStyle('width');
		contentWrapperEl.oldHeight = contentWrapperEl.getStyle('height');

		// Hide iframe
		// Iframe should be hidden when minimizing, maximizing, and moving for performance and Flash issues
		if ( currentInstance.iframeEl ) {
			currentInstance.iframeEl.setStyle('visibility', 'hidden');
		}

		var windowDimensions = document.getCoordinates();
		var options = currentInstance.options;
		var shadowBlur = options.shadowBlur;
		var shadowOffset = options.shadowOffset;
		var newHeight = windowDimensions.height - options.headerHeight - options.footerHeight;
		newHeight -= currentInstance.contentBorderEl.getStyle('border-top').toInt();
		newHeight -= currentInstance.contentBorderEl.getStyle('border-bottom').toInt();
		newHeight -= (  currentInstance.toolbarWrapperEl ? currentInstance.toolbarWrapperEl.getStyle('height').toInt() + currentInstance.toolbarWrapperEl.getStyle('border-top').toInt() : 0);

		if (MochaUI.options.useEffects == false){
			windowEl.setStyles({
				'top': shadowOffset.y - shadowBlur,
				'left': shadowOffset.x - shadowBlur
			});
			currentInstance.contentWrapperEl.setStyles({
				'height': newHeight,
				'width':  windowDimensions.width
			});
			currentInstance.drawWindow(windowEl);
			// Show iframe
			if ( currentInstance.iframeEl ) {
				currentInstance.iframeEl.setStyle('visibility', 'visible');
			}
			currentInstance.fireEvent('onMaximize', windowEl);
		}
		else {

			// Todo: Initialize the variables for these morphs once in an initialize function and reuse them

			var maximizeMorph = new Fx.Elements([contentWrapperEl, windowEl], { 
				duration: 100,
				transition: Fx.Transitions.Sine.easeInOut,
				onStart: function(windowEl){
					currentInstance.maximizeAnimation = currentInstance.drawWindow.periodical(20, currentInstance, windowEl);
				}.bind(this),
				onComplete: function(windowEl){
					$clear(currentInstance.maximizeAnimation);
					currentInstance.drawWindow(windowEl);
					// Show iframe
					if ( currentInstance.iframeEl ) {
						currentInstance.iframeEl.setStyle('visibility', 'visible');
					}
					currentInstance.fireEvent('onMaximize', windowEl);	
				}.bind(this)
			});
			maximizeMorph.start({
				'0': {	'height': newHeight,
						'width':  windowDimensions.width
				},
				'1': {	'top': shadowOffset.y - shadowBlur,
						'left': shadowOffset.x - shadowBlur 
				}
			});		
		}
		currentInstance.maximizeButtonEl.setProperty('title', 'Restore');
		MochaUI.focusWindow(windowEl);

	},
	/*

	Function: restoreWindow
		Restore a maximized window.

	Syntax:
		(start code)
		MochaUI.Desktop.restoreWindow(windowEl);
		(end)	

	*/	
	restoreWindow: function(windowEl){	
	
		var currentInstance = MochaUI.Windows.instances.get(windowEl.id);
		
		// Window exists and is maximized ?
		if (windowEl != $(windowEl) || !currentInstance.isMaximized) return;
			
		var options = currentInstance.options;
		currentInstance.isMaximized = false;
		
		if (options.restrict){
			currentInstance.windowDrag.attach();
			if (options.resizable){
				currentInstance.reattachResizable();
			}			
			currentInstance.titleBarEl.setStyle('cursor', 'move');
		}		
		
		// Hide iframe
		// Iframe should be hidden when minimizing, maximizing, and moving for performance and Flash issues
		if ( currentInstance.iframeEl ) {
			currentInstance.iframeEl.setStyle('visibility', 'hidden');
		}
		
		var contentWrapperEl = currentInstance.contentWrapperEl;
		
		if (MochaUI.options.useEffects == false){
			contentWrapperEl.setStyles({
				'width':  contentWrapperEl.oldWidth,
				'height': contentWrapperEl.oldHeight
			});
			currentInstance.drawWindow(windowEl);
			windowEl.setStyles({
				'top': currentInstance.oldTop,
				'left': currentInstance.oldLeft
			});
			if ( currentInstance.iframeEl ) {
				currentInstance.iframeEl.setStyle('visibility', 'visible');
			}			
			if (options.container != this.desktop){
				$(options.container).grab(windowEl);
				if (options.restrict){
					currentInstance.windowDrag.container = $(options.container);
				}
			}
			currentInstance.fireEvent('onRestore', windowEl);
		}
		else {
			var restoreMorph = new Fx.Elements([contentWrapperEl, windowEl], { 
				'duration':   200,
				transition: Fx.Transitions.Sine.easeInOut,
				'onStart': function(windowEl){
					currentInstance.maximizeAnimation = currentInstance.drawWindow.periodical(20, currentInstance, windowEl);
				}.bind(this),
				'onComplete': function(el){
					$clear(currentInstance.maximizeAnimation);
					currentInstance.drawWindow(windowEl);
					if (currentInstance.iframeEl){
						currentInstance.iframeEl.setStyle('visibility', 'visible');
					}
					if (options.container != this.desktop){
						$(options.container).grab(windowEl);
						if (options.restrict){	
							currentInstance.windowDrag.container = $(options.container);
						}
					}
					currentInstance.fireEvent('onRestore', windowEl);
				}.bind(this)
			});
			restoreMorph.start({ 
				'0': {	'height': contentWrapperEl.oldHeight,
						'width':  contentWrapperEl.oldWidth
				},
				'1': {	'top':  currentInstance.oldTop,
						'left': currentInstance.oldLeft
				}
			});
		}
		currentInstance.maximizeButtonEl.setProperty('title', 'Maximize');
	}
});
MochaUI.Desktop.implement(new Options, new Events);

/*

Class: Column
	Create a column. Columns should be created from left to right.

Syntax:
(start code)
	MochaUI.Panel();
(end)

Arguments:
	options

Options:
	id - The ID of the column. This must be set when creating the column.	
	placement - Can be 'right', 'main', or 'left'. There must be at least one column with the 'main' option.
	width - 'main' column is fluid and should not be given a width.
	resizeLimit - resizelimit of a 'right' or 'left' column.
	onResize - (function) Fired when the column is resized.
	onCollapse - (function) Fired when the column is collapsed.
	onExpand - (function) Fired when the column is expanded.
		
*/
MochaUI.Column = new Class({

	Extends: MochaUI.Desktop,

	Implements: [Events, Options],

	options: {
		id:            null, 
		placement:     null, 
		width:         null,
		resizeLimit:   [],

		// Events
		onResize:     $empty, 
		onCollapse:   $empty,
		onExpand:     $empty

	},
	initialize: function(options){
		this.setOptions(options);
		
		$extend(this, {
			timestamp: $time(),
			isCollapsed: false,
			oldWidth: 0
		});

		// Shorten object chain
		var options = this.options;
		var instances = MochaUI.Columns.instances;
		var instanceID = instances.get(options.id);

		// Check to see if there is already a class instance for this Column
		if (instanceID){
			var currentInstance = instanceID;
		}

		// Check if column already exists
		if ( this.columnEl ){
			return;
		}
		else {			
			instances.set(options.id, this);
		}		
				
		this.columnEl = new Element('div', {
			'id': this.options.id,
			'class': 'column expanded',
			'styles': {
				'width': options.placement == 'main' ? null : options.width
			}
		}).inject($(MochaUI.Desktop.pageWrapper));

		var parent = this.columnEl.getParent();
		var columnHeight = parent.getStyle('height').toInt();
		this.columnEl.setStyle('height', columnHeight);

		if (options.placement == 'main'){
			this.columnEl.addClass('rWidth');
		}

		this.spacerEl = new Element('div', {
			'id': this.options.id + '_spacer',
			'class': 'horizontalHandle'
		}).inject(this.columnEl);

		switch (this.options.placement) {
			case 'left':
				this.handleEl = new Element('div', {
					'id': this.options.id + '_handle',
					'class': 'columnHandle'
				}).inject(this.columnEl, 'after');

				this.handleIconEl = new Element('div', {
					'id': options.id + '_handle_icon',
					'class': 'handleIcon'
				}).inject(this.handleEl);

				addResizeRight(this.columnEl, options.resizeLimit[0], options.resizeLimit[1]);
				break;
			case 'right':
				this.handleEl = new Element('div', {
					'id': this.options.id + '_handle',
					'class': 'columnHandle'
				}).inject(this.columnEl, 'before');

				this.handleIconEl = new Element('div', {
					'id': options.id + '_handle_icon',
					'class': 'handleIcon'
				}).inject(this.handleEl);
				addResizeLeft(this.columnEl, options.resizeLimit[0], options.resizeLimit[1]);
				break;
		}

		if (this.handleEl != null){
			this.handleEl.addEvent('dblclick', function(){
				this.columnToggle();
			}.bind(this));
		}

		MochaUI.rWidth();

	},
	columnToggle: function(){
		var column= this.columnEl;
		
		// Collapse
		if (this.isCollapsed == false){
			this.oldWidth = column.getStyle('width').toInt();

			this.resize.detach();
			this.handleEl.removeEvents('dblclick');
			this.handleEl.addEvent('click', function(){
				this.columnToggle();
			}.bind(this));
			this.handleEl.setStyle('cursor', 'pointer').addClass('detached');
			
			column.setStyle('width', 0);
			this.isCollapsed = true;
			column.addClass('collapsed');
			column.removeClass('expanded');

			MochaUI.rWidth();
			this.fireEvent('onCollapse');
		}
		// Expand
		else {
			column.setStyle('width', this.oldWidth);
			this.isCollapsed = false;
			column.addClass('expanded');
			column.removeClass('collapsed');

			this.handleEl.removeEvents('click');
			this.handleEl.addEvent('dblclick', function(){
				this.columnToggle();
			}.bind(this));
			this.resize.attach();
			this.handleEl.setStyle('cursor', 'e-resize').addClass('attached');

			MochaUI.rWidth();
			this.fireEvent('onExpand');
		}
	}
});
MochaUI.Column.implement(new Options, new Events);

/*

Class: Panel
	Create a panel. Panels go one on top of another in columns. Create your columns first and then add your panels. Panels should be created from top to bottom, left to right.

Syntax:
(start code)
	MochaUI.Panel();
(end)

Arguments:
	options

Options:
	id - The ID of the panel. This must be set when creating the panel.
	column - Where to inject the panel. This must be set when creating the panel.
	loadMethod - ('html', 'xhr', or 'iframe')
	contentURL - Used if loadMethod is set to 'xhr' or 'iframe'.
	method - ('get', or 'post') The method used to get the data. Defaults to 'get'.
	data - (hash) Data to send with the URL. Defaults to null.
	evalScripts - (boolean) An xhr loadMethod option. Defaults to true.
	evalResponse - (boolean) An xhr loadMethod option. Defaults to false.
	content - (string or element) An html loadMethod option.
	tabsURL - (url)	
	tabsData - (hash) Data to send with the URL. Defaults to null.
	footer - (boolean)
	footerURL - (url)
	footerData - (hash) Data to send with the URL. Defaults to null.
	height - (number) Height of content area.
	addClass - (string) Add a class to the panel.
	scrollbars - (boolean)
	padding - (object)
	panelBackground - CSS background property for the panel.
	onBeforeBuild - (function) Fired before the panel is created.
	onContentLoaded - (function) Fired after the panel's conten is loaded.
	onResize - (function) Fired when the panel is resized.
	onCollapse - (function) Fired when the panel is collapsed.
	onExpand - (function) Fired when the panel is expanded.
		
*/
MochaUI.Panel = new Class({
							
	Extends: MochaUI.Desktop,
	
	Implements: [Events, Options],
	
	options: {
		id:               null,
		title:            'New Panel',
		column:           null,
		loadMethod:       'html',
		contentURL:       'pages/lipsum.html',
	
		// xhr options
		method:		  'get',
		data:		  null,
		evalScripts:      true,
		evalResponse:     false,
	
		// html options
		content:          '',
		
		// Tabs
		tabsURL:          null,
		tabsData:	  null,

		footer:           false,
		footerURL:        'pages/lipsum.html',
		footerData:	  null,
		
		// Style options:
		height:           125,
		addClass:         '',
		scrollbars:       true,
		padding:   		  { top: 8, right: 8, bottom: 8, left: 8 },

		// Color options:		
		panelBackground:   '#f8f8f8',

		// Events
		onBeforeBuild:     $empty,
		onContentLoaded:   $empty,
		onResize:          $empty,
		onCollapse:        $empty,
		onExpand:          $empty

	},	
	initialize: function(options){
		this.setOptions(options);

		$extend(this, {
			timestamp: $time(),
			isCollapsed: false, // This is probably redundant since we can check for the class
			oldHeight: 0,
			partner: null
		});

		// Shorten object chain
		var instances = MochaUI.Panels.instances;
		var instanceID = instances.get(this.options.id);
	
		// Check to see if there is already a class instance for this panel
		if (instanceID){
			var currentInstance = instanceID;
		}

		// Check if panel already exists
		if ( this.panelEl ){
			return;
		}
		else {			
			instances.set(this.options.id, this);
		}

		this.fireEvent('onBeforeBuild');
		
		if (this.options.loadMethod == 'iframe') {
			// Iframes have their own scrollbars and padding.
			this.options.scrollbars = false;
			this.options.padding = { top: 0, right: 0, bottom: 0, left: 0 };
		}

		this.showHandle = true;
		if ($(this.options.column).getChildren().length == 0){
			this.showHandle = false;
		}

		this.panelEl = new Element('div', {
			'id': this.options.id,
			'class': 'panel expanded',
			'styles': {
				'height': this.options.height,
				'background': this.options.panelBackground
			}
		}).inject($(this.options.column));

		this.panelEl.addClass(this.options.addClass);

		this.contentEl = new Element('div', {
			'id': this.options.id + '_pad',
			'class': 'pad'
		}).inject(this.panelEl);

		if (this.options.footer){
			this.footerWrapperEl = new Element('div', {
				'id': this.options.id + '_panelFooterWrapper',
				'class': 'panel-footerWrapper'
			}).inject(this.panelEl);
			
			this.footerEl = new Element('div', {
				'id': this.options.id + '_panelFooter',
				'class': 'panel-footer'
			}).inject(this.footerWrapperEl);


			MochaUI.updateContent({
				'element':       this.panelEl,
				'childElement':  this.footerEl,
				'loadMethod':    'xhr',
				'data':		 this.options.footerData,
				'url':           this.options.footerURL
			});
			
		}

		// This is in order to use the same variable as the windows do in updateContent.
		// May rethink this.
		this.contentWrapperEl = this.panelEl;
		
		// Set scrollbars, always use 'hidden' for iframe windows
		this.contentWrapperEl.setStyles({
			'overflow': this.options.scrollbars && !this.iframeEl ? 'auto' : 'hidden'
		});

		this.contentEl.setStyles({
			'padding-top': this.options.padding.top,
			'padding-bottom': this.options.padding.bottom,
			'padding-left': this.options.padding.left,
			'padding-right': this.options.padding.right
		});			
		
		this.panelHeaderEl = new Element('div', {
			'id': this.options.id + '_header',
			'class': 'panel-header'
		}).inject(this.panelEl, 'before');
		
		this.panelHeaderToolboxEl = new Element('div', {
			'id': this.options.id + '_headerToolbox',
			'class': 'panel-header-toolbox'
		}).inject(this.panelHeaderEl);

		this.collapseToggleEl = new Element('div', {
			'id': this.options.id + '_minmize', // !!! Type. Rename this collapseToggle
			'class': 'panel-collapse icon16',
			'styles': {
				'width': 16,
				'height': 16
			},
			'title': 'Collapse Panel'
		}).inject(this.panelHeaderToolboxEl);

		this.collapseToggleEl.addEvent('click', function(event){
			var panel = this.panelEl;
			
			// Get siblings and make sure they are not all collapsed.
			var instances = MochaUI.Panels.instances;
			var expandedSiblings = [];
			panel.getAllPrevious('.panel').each(function(sibling){
				var currentInstance = instances.get(sibling.id);
				if (currentInstance.isCollapsed == false){
					expandedSiblings.push(sibling);
				}
			});
			panel.getAllNext('.panel').each(function(sibling){
				var currentInstance = instances.get(sibling.id);
				if (currentInstance.isCollapsed == false){
					expandedSiblings.push(sibling);
				}
			});

			if (this.isCollapsed == false) {
				var currentColumn = MochaUI.Columns.instances.get($(this.options.column).id);

				if (expandedSiblings.length == 0 && currentColumn.options.placement != 'main'){
					var currentColumn = MochaUI.Columns.instances.get($(this.options.column).id);
					currentColumn.columnToggle();
					return;
				}
				else if (expandedSiblings.length == 0 && currentColumn.options.placement == 'main'){
					return;
				}
				this.oldHeight = panel.getStyle('height').toInt();
				if (this.oldHeight < 10) this.oldHeight = 20;
				panel.setStyle('height', 0);
				this.isCollapsed = true;
				panel.addClass('collapsed');
				panel.removeClass('expanded');
				MochaUI.panelHeight(this.options.column, panel, 'collapsing');
				this.collapseToggleEl.removeClass('panel-collapsed');
				this.collapseToggleEl.addClass('panel-expand');
				this.collapseToggleEl.setProperty('title','Expand Panel');
				this.fireEvent('onCollapse');
			}
			else {
				panel.setStyle('height', this.oldHeight);
				this.isCollapsed = false;
				panel.addClass('expanded');
				panel.removeClass('collapsed');
				MochaUI.panelHeight(this.options.column, panel, 'expanding');
				this.collapseToggleEl.removeClass('panel-expand');
				this.collapseToggleEl.addClass('panel-collapsed');
				this.collapseToggleEl.setProperty('title','Collapse Panel');
				this.fireEvent('onExpand');
			}
		}
		.bind(this));
		
		this.panelHeaderContentEl = new Element('div', {
			'id': this.options.id + '_headerContent',
			'class': 'panel-headerContent'
		}).inject(this.panelHeaderEl);

		this.titleEl = new Element('h2', {
			'id': this.options.id + '_title'
		}).inject(this.panelHeaderContentEl);

		if (this.options.tabsURL == null){
			this.titleEl.set('html', this.options.title);
		}		
		else {
			this.panelHeaderContentEl.addClass('tabs');
			MochaUI.updateContent({
				'element':      this.panelEl,
				'childElement': this.panelHeaderContentEl,
				'loadMethod':   'xhr',
				'url':          this.options.tabsURL,
				'data':		this.options.tabsData
			});
		}

		this.handleEl = new Element('div', {
			'id': this.options.id + '_handle',
			'class': 'horizontalHandle',
			'styles': {
				'display': this.showHandle == true ? 'block' : 'none'
			}
		}).inject(this.panelEl, 'after');
		
		this.handleIconEl = new Element('div', {
			'id': this.options.id + '_handle_icon',
			'class': 'handleIcon'
		}).inject(this.handleEl);

		addResizeBottom(this.options.id);

		// Add content to panel.
		MochaUI.updateContent({
			'element': this.panelEl,
			'content':  this.options.content,
			'method':	this.options.method,
			'data':		this.options.data,
			'url':      this.options.contentURL
		});

		MochaUI.panelHeight(this.options.column, this.panelEl, 'new');

	}
});
MochaUI.Panel.implement(new Options, new Events);


MochaUI.extend({
	// Panel Height	
	panelHeight: function(column, changing, action){

		if (column != null) {
			MochaUI.panelHeight2($(column), changing, action);
		}
		else {
			$$('.column').each(function(column){
				MochaUI.panelHeight2(column);
			}.bind(this));
		}



	},
	/*

	actions can be new, collapsing or expanding.

	*/
	panelHeight2: function(column, changing, action){

			var instances = MochaUI.Panels.instances;
			
			var parent = column.getParent();
			var columnHeight = parent.getStyle('height').toInt();
			if (Browser.Engine.trident4){
				columnHeight -= 1;
			}
			column.setStyle('height', columnHeight);

			var panels = column.getChildren('.panel');            // All the panels in the column.
			var panelsExpanded = column.getChildren('.expanded'); // All the expanded panels in the column.
			var panelsToResize = [];    // All the panels in the column whose height will be effected.
			var tallestPanel;           // The panel with the greatest height
			var tallestPanelHeight = 0;

			this.panelsHeight = 0;		// Height of all the panels in the column	
			this.height = 0;            // Height of all the elements in the column	

			// Set panel resize partners
			panels.each(function(panel){
				currentInstance = instances.get(panel.id);
				if (panel.hasClass('expanded') && panel.getNext('.expanded')){
					currentInstance.partner = panel.getNext('.expanded');
					currentInstance.resize.attach();
					currentInstance.handleEl.setStyles({
						'display': 'block',
						'cursor': 'n-resize'
					}).removeClass('detached');
				}
				else {
					currentInstance.resize.detach();
					currentInstance.handleEl.setStyle('cursor', null).addClass('detached');
				}
				if (panel.getNext('.panel') == null){
					currentInstance.handleEl.setStyle('display', 'none');
				}
			}.bind(this));
			
			// Get the total height of all the column's children
			column.getChildren().each(function(el){

				if (el.hasClass('panel')){
					var currentInstance = instances.get(el.id);

					// Are any next siblings Expanded?
					areAnyNextSiblingsExpanded = function(el){
						var test;
						el.getAllNext('.panel').each(function(sibling){
							var siblingInstance = instances.get(sibling.id);
							if (siblingInstance.isCollapsed == false){
								test = true;
							}
						}.bind(this));
						return test;
					}.bind(this);

					// If a next sibling is expanding, are any of the nexts siblings of the expanding sibling Expanded?
					areAnyExpandingNextSiblingsExpanded = function(){
						var test;
						changing.getAllNext('.panel').each(function(sibling){
							var siblingInstance = instances.get(sibling.id);
							if (siblingInstance.isCollapsed == false){
								test = true;
							}
						}.bind(this));
						return test;
					}.bind(this);
					
					// Resize panels that are not collapsed or "new"
					if (action == 'new' ) {
						if (currentInstance.isCollapsed != true && el != changing) {
							panelsToResize.push(el);
						}
						
						// Height of panels that can be resized
						if (currentInstance.isCollapsed != true && el != changing) {
							this.panelsHeight += el.offsetHeight.toInt();
						}
					}
					// Resize panels that are not collapsed. If a panel is collapsing
					// resize any expanded panels below. If there are no expanded panels
					// below it, resize the expanded panels above it.
					else if (action == null || action == 'collapsing' ){
						if (currentInstance.isCollapsed != true && (el.getAllNext('.panel').contains(changing) != true || areAnyNextSiblingsExpanded(el) != true)){
							panelsToResize.push(el);
						}
						
						// Height of panels that can be resized
						if (currentInstance.isCollapsed != true && (el.getAllNext('.panel').contains(changing) != true || areAnyNextSiblingsExpanded(el) != true)){
							this.panelsHeight += el.offsetHeight.toInt();
						}
					}
					// Resize panels that are not collapsed and are not expanding.
					// Resize any expanded panels below the expanding panel. If there are no expanded panels
					// below it, resize the first expanded panel above it.
					else if (action == 'expanding'){
						   
						if (currentInstance.isCollapsed != true && (el.getAllNext('.panel').contains(changing) != true || (areAnyExpandingNextSiblingsExpanded() != true && el.getNext('.expanded') == changing)) && el != changing){
							panelsToResize.push(el);
						}
						// Height of panels that can be resized
						if (currentInstance.isCollapsed != true && (el.getAllNext('.panel').contains(changing) != true || (areAnyExpandingNextSiblingsExpanded() != true && el.getNext('.expanded') == changing)) && el != changing){
							this.panelsHeight += el.offsetHeight.toInt();
						}
					}

					if (el.style.height){
						this.height += el.getStyle('height').toInt();
					}
				}
				else {
					this.height += el.offsetHeight.toInt();
				}
			}.bind(this));

			// Get the remaining height
			var remainingHeight = column.offsetHeight.toInt() - this.height;
			
			this.height = 0;

			// Get height of all the column's children
			column.getChildren().each(function(el){
				this.height += el.offsetHeight.toInt();
			}.bind(this));
				
			var remainingHeight = column.offsetHeight.toInt() - this.height;

			panelsToResize.each(function(panel){
				var ratio = this.panelsHeight / panel.offsetHeight.toInt();
				var newPanelHeight = panel.getStyle('height').toInt() + (remainingHeight / ratio);
				if (newPanelHeight < 1){
					newPanelHeight = 0;
				}
				panel.setStyle('height', newPanelHeight);
			}.bind(this));	

			// Make sure the remaining height is 0. If not add/subtract the
			// remaining height to the tallest panel. This makes up for browser resizing,
			// off ratios, and users trying to give panels too much height.
			
			// Get height of all the column's children
			this.height = 0;
			column.getChildren().each(function(el){
				this.height += el.offsetHeight.toInt();
				if (el.hasClass('panel') && el.getStyle('height').toInt() > tallestPanelHeight){
					tallestPanel = el;
					tallestPanelHeight = el.getStyle('height').toInt();
				}
			}.bind(this));

			var remainingHeight = column.offsetHeight.toInt() - this.height;

			if ((remainingHeight > 0 || remainingHeight < 0) && tallestPanelHeight > 0){
				tallestPanel.setStyle('height', tallestPanel.getStyle('height').toInt() + remainingHeight );
				if (tallestPanel.getStyle('height') < 1){
					tallestPanel.setStyle('height', 0 );
				}
			}

			$$('.columnHandle').each(function(handle){
				var handleHeight = parent.getStyle('height').toInt() - handle.getStyle('border-top').toInt() - handle.getStyle('border-bottom').toInt();
				if (Browser.Engine.trident4){
					handleHeight -= 1;
				}
				handle.setStyle('height', handleHeight);
			});
			
			panelsExpanded.each(function(panel){
				MochaUI.resizeChildren(panel);
			}.bind(this));
	},
	// May rename this resizeIframeEl()
	resizeChildren: function(panel){

		var instances = MochaUI.Panels.instances;
		var currentInstance = instances.get(panel.id);
		var contentWrapperEl = currentInstance.contentWrapperEl;

		if (currentInstance.iframeEl){
			currentInstance.iframeEl.setStyles({
				'height': contentWrapperEl.getStyle('height'),
				'width': contentWrapperEl.offsetWidth - contentWrapperEl.getStyle('border-left').toInt() - contentWrapperEl.getStyle('border-right').toInt()
			});
		}
	},
	// Remaining Width
	rWidth: function(){	


		$$('.rWidth').each(function(column){
			var currentWidth = column.offsetWidth.toInt();
			currentWidth -= column.getStyle('border-left').toInt();
			currentWidth -= column.getStyle('border-right').toInt();

			var parent = column.getParent();
			this.width = 0;
			
			// Get the total width of all the parent element's children
			parent.getChildren().each(function(el){
				if (el.hasClass('mocha') != true){
					this.width += el.offsetWidth.toInt();
				}
			}.bind(this));
		
			// Add the remaining width to the current element
			var remainingWidth = parent.offsetWidth.toInt() - this.width;
			var newWidth =	currentWidth + remainingWidth;
			if (newWidth < 1) newWidth = 0;
			column.setStyle('width', newWidth);
			column.getChildren('.panel').each(function(panel){
				panel.setStyle('width', newWidth - panel.getStyle('border-left').toInt() - panel.getStyle('border-right').toInt());
				MochaUI.resizeChildren(panel);
			}.bind(this));
		});
	}

});

function addResizeRight(element, min, max){
	if (!$(element)) return;
	element = $(element);

	var instances = MochaUI.Columns.instances;
	var currentInstance = instances.get(element.id);

	var handle = element.getNext('.columnHandle');
	handle.setStyle('cursor', 'e-resize');	
	if (!min) min = 50;
	if (!max) max = 250;
	if (Browser.Engine.trident){
		handle.addEvents({
			'mousedown': function(){
				handle.setCapture();
			},
			'mouseup': function(){
				handle.releaseCapture();
			}
		});
	}
	currentInstance.resize = element.makeResizable({
		handle: handle,
		modifiers: {x: 'width', y: false},
		limit: { x: [min, max] },
		onStart: function(){
			element.getElements('iframe').setStyle('visibility','hidden');
			element.getNext('.column').getElements('iframe').setStyle('visibility','hidden');
		}.bind(this),
		onDrag: function(){
			MochaUI.rWidth();
			if (Browser.Engine.trident4){
				element.getChildren().each(function(el){
					var width = $(element).getStyle('width').toInt();
					width -= el.getStyle('border-right').toInt();
					width -= el.getStyle('border-left').toInt();
					width -= el.getStyle('padding-right').toInt();
					width -= el.getStyle('padding-left').toInt();
					el.setStyle('width', width);
				}.bind(this));
			}						
		}.bind(this),
		onComplete: function(){
			MochaUI.rWidth();
			element.getElements('iframe').setStyle('visibility','visible');
			element.getNext('.column').getElements('iframe').setStyle('visibility','visible');
			currentInstance.fireEvent('onResize');
		}.bind(this)
	});	
}

function addResizeLeft(element, min, max){
	if (!$(element)) return;
	element = $(element);

	var instances = MochaUI.Columns.instances;
	var currentInstance = instances.get(element.id);

	var handle = element.getPrevious('.columnHandle');
	handle.setStyle('cursor', 'e-resize');
	var partner = element.getPrevious('.column');
	if (!min) min = 50;
	if (!max) max = 250;
	if (Browser.Engine.trident){	
		handle.addEvents({
			'mousedown': function(){
				handle.setCapture();
			},	
			'mouseup': function(){
				handle.releaseCapture();
			}
		});
	}
	currentInstance.resize = element.makeResizable({
		handle: handle,
		modifiers: {x: 'width' , y: false},
		invert: true,
		limit: { x: [min, max] },
		onStart: function(){
			$(element).getElements('iframe').setStyle('visibility','hidden');
			partner.getElements('iframe').setStyle('visibility','hidden');
		}.bind(this),
		onDrag: function(){
			MochaUI.rWidth();
		}.bind(this),
		onComplete: function(){
			MochaUI.rWidth();
			$(element).getElements('iframe').setStyle('visibility','visible');
			partner.getElements('iframe').setStyle('visibility','visible');
			currentInstance.fireEvent('onResize');
		}.bind(this)
	});
}

function addResizeBottom(element){

	if (!$(element)) return;
	var element = $(element);
	
	var instances = MochaUI.Panels.instances;
	var currentInstance = instances.get(element.id);
	var handle = currentInstance.handleEl;
	handle.setStyle('cursor', 'n-resize');
	partner = currentInstance.partner;
	min = 0;
	max = function(){
		return element.getStyle('height').toInt() + partner.getStyle('height').toInt();
	}.bind(this);
	
	if (Browser.Engine.trident){	
		handle.addEvents({
			'mousedown': function(){
				handle.setCapture();
			},	
			'mouseup': function(){
				handle.releaseCapture();
			}
		});
	}
	currentInstance.resize = element.makeResizable({
		handle: handle,
		modifiers: {x: false, y: 'height'},
		limit: { y: [min, max] },
		invert: false,
		onBeforeStart: function(){
			partner = currentInstance.partner;
			this.originalHeight = element.getStyle('height').toInt();
			this.partnerOriginalHeight = partner.getStyle('height').toInt();
		}.bind(this),
		onStart: function(){
			if (currentInstance.iframeEl) {
				currentInstance.iframeEl.setStyle('visibility', 'hidden');
			}
			partner.getElements('iframe').setStyle('visibility','hidden');
		}.bind(this),
		onDrag: function(){
			partnerHeight = partnerOriginalHeight + (this.originalHeight - element.getStyle('height').toInt());
			partner.setStyle('height', partnerHeight);
			MochaUI.resizeChildren(element, element.getStyle('height').toInt());
			MochaUI.resizeChildren(partner, partnerHeight);
		}.bind(this),
		onComplete: function(){
			partnerHeight = partnerOriginalHeight + (this.originalHeight - element.getStyle('height').toInt());
			partner.setStyle('height', partnerHeight);
			MochaUI.resizeChildren(element, element.getStyle('height').toInt());
			MochaUI.resizeChildren(partner, partnerHeight);
			if (currentInstance.iframeEl) {
				currentInstance.iframeEl.setStyle('visibility', 'visible');
			}
			partner.getElements('iframe').setStyle('visibility','visible');
			currentInstance.fireEvent('onResize');
		}.bind(this)
	});
}
/*

Script: Dock.js
	Implements the dock/taskbar. Enables window minimize.

Copyright:
	Copyright (c) 2007-2008 Greg Houston, <http://greghoustondesign.com/>.	

License:
	MIT-style license.

Requires:
	Core.js, Window.js, Layout.js	

Todo:
	- Make it so the dock requires no initial html markup.

*/

MochaUI.options.extend({
		// Naming options:
		// If you change the IDs of the Mocha Desktop containers in your HTML, you need to change them here as well.
		dockWrapper: 'dockWrapper',
		dock:        'dock'
});

// Used by Desktop.js before MochaUI.Dock is initialized.
window.addEvent('domready', function(){	
	if ($('dockWrapper')) {
		MochaUI.dockVisible = true;
	}
});

MochaUI.extend({
	/*

	Function: minimizeAll
		Minimize all windows that are minimizable.

	*/	
	minimizeAll: function() {
		$$('div.mocha').each(function(windowEl){
		var currentInstance = MochaUI.Windows.instances.get(windowEl.id);
			if (!currentInstance.isMinimized && currentInstance.options.minimizable == true){
				MochaUI.Dock.minimizeWindow(windowEl);
			}
		}.bind(this));
	}
});

MochaUI.Dock = new Class({
	Extends: MochaUI.Window,

	Implements: [Events, Options],

	options: {
		useControls:          true,      // Toggles autohide and dock placement controls.
		dockPosition:         'top',     // Position the dock starts in, top or bottom.
		// Style options
		dockTabColor:         [255, 255, 255],
		trueButtonColor:      [70, 245, 70],     // Color for autohide on
		enabledButtonColor:   [125, 208, 250], 
		disabledButtonColor:  [170, 170, 170]
	},
	initialize: function(options){
		// Stops if MochaUI.Desktop is not implemented
		if (!MochaUI.Desktop) return;
		this.setOptions(options);
		
		this.dockWrapper   = $(MochaUI.options.dockWrapper);
		this.dock          = $(MochaUI.options.dock);
		this.autoHideEvent = null;		
		this.dockAutoHide  = false;  // True when dock autohide is set to on, false if set to off

		if (!this.dockWrapper) return;

		if (!this.options.useControls){
			if($('dockPlacement')){
				$('dockPlacement').setStyle('cursor', 'default');
			}
			if($('dockAutoHide')){
				$('dockAutoHide').setStyle('cursor', 'default');
			}
		}

		this.dockWrapper.setStyles({
			'display':  'block',
			'position': 'absolute',
			'top':      null,
			'bottom':   MochaUI.Desktop.desktopFooter ? MochaUI.Desktop.desktopFooter.offsetHeight : 0,
			'left':     0
		});
		
		if (this.options.useControls){
			this.initializeDockControls();
		}

		// Add check mark to menu if link exists in menu
		if ($('dockLinkCheck')){
			this.sidebarCheck = new Element('div', {
				'class': 'check',
				'id': 'dock_check'
			}).inject($('dockLinkCheck'));
		}

		this.dockSortables = new Sortables('#dockSort', {
			opacity: Browser.Engine.trident ? 1 : .5,
			constrain: true,
			clone: false,
			revert: false
		});

		MochaUI.Desktop.setDesktopSize();
	},
	initializeDockControls: function(){
		
		if (this.options.useControls){
			// Insert canvas
			var canvas = new Element('canvas', {
				'id':     'dockCanvas',
				'width':  '15',
				'height': '18'
			}).inject(this.dock);

			// Dynamically initialize canvas using excanvas. This is only required by IE
			if (Browser.Engine.trident && MochaUI.ieSupport == 'excanvas'){
				G_vmlCanvasManager.initElement(canvas);
			}
		}
		
		var dockPlacement = $('dockPlacement');
		var dockAutoHide = $('dockAutoHide');

		// Position top or bottom selector
		dockPlacement.setProperty('title','Position Dock Top');

		// Attach event
		dockPlacement.addEvent('click', function(){
			this.moveDock();
		}.bind(this));

		// Auto Hide toggle switch
		dockAutoHide.setProperty('title','Turn Auto Hide On');
		
		// Attach event Auto Hide 
		dockAutoHide.addEvent('click', function(event){
			if ( this.dockWrapper.getProperty('dockPosition') == 'top' )
				return false;

			var ctx = $('dockCanvas').getContext('2d');
			this.dockAutoHide = !this.dockAutoHide;	// Toggle
			if (this.dockAutoHide){
				$('dockAutoHide').setProperty('title', 'Turn Auto Hide Off');
				//ctx.clearRect(0, 11, 100, 100);
				MochaUI.circle(ctx, 5 , 14, 3, this.options.trueButtonColor, 1.0);

				// Define event
				this.autoHideEvent = function(event) {
					if (!this.dockAutoHide)
						return;
					if (!MochaUI.Desktop.desktopFooter) {
						var dockHotspotHeight = this.dockWrapper.offsetHeight;
						if (dockHotspotHeight < 25) dockHotspotHeight = 25;
					}
					else if (MochaUI.Desktop.desktopFooter) {
						var dockHotspotHeight = this.dockWrapper.offsetHeight + MochaUI.Desktop.desktopFooter.offsetHeight;
						if (dockHotspotHeight < 25) dockHotspotHeight = 25;
					}						
					if (!MochaUI.Desktop.desktopFooter && event.client.y > (document.getCoordinates().height - dockHotspotHeight)){
						if (!MochaUI.dockVisible){
							this.dockWrapper.setStyle('display', 'block');
							MochaUI.dockVisible = true;
							MochaUI.Desktop.setDesktopSize();
						}
					}
					else if (MochaUI.Desktop.desktopFooter && event.client.y > (document.getCoordinates().height - dockHotspotHeight)){
						if (!MochaUI.dockVisible){
							this.dockWrapper.setStyle('display', 'block');
							MochaUI.dockVisible = true;
							MochaUI.Desktop.setDesktopSize();
						}
					}
					else if (MochaUI.dockVisible){
						this.dockWrapper.setStyle('display', 'none');
						MochaUI.dockVisible = false;
						MochaUI.Desktop.setDesktopSize();
						
					}
				}.bind(this);

				// Add event
				document.addEvent('mousemove', this.autoHideEvent);

			} else {
				$('dockAutoHide').setProperty('title', 'Turn Auto Hide On');
				//ctx.clearRect(0, 11, 100, 100);
				MochaUI.circle(ctx, 5 , 14, 3, this.options.enabledButtonColor, 1.0);
				// Remove event
				document.removeEvent('mousemove', this.autoHideEvent);
			}

		}.bind(this));

		// Draw dock controls
		var ctx = $('dockCanvas').getContext('2d');
		ctx.clearRect(0, 0, 100, 100);
		MochaUI.circle(ctx, 5 , 4, 3, this.options.enabledButtonColor, 1.0);
		MochaUI.circle(ctx, 5 , 14, 3, this.options.enabledButtonColor, 1.0);
		
		if (this.options.dockPosition == 'top'){
			this.moveDock();
		}

	},
	moveDock: function(){
			var ctx = $('dockCanvas').getContext('2d');
			// Move dock to top position
			if (this.dockWrapper.getStyle('position') != 'relative'){
				this.dockWrapper.setStyles({
					'position': 'relative',
					'bottom':   null
				});
				this.dockWrapper.addClass('top');
				MochaUI.Desktop.setDesktopSize();
				this.dockWrapper.setProperty('dockPosition','top');
				ctx.clearRect(0, 0, 100, 100);
				MochaUI.circle(ctx, 5, 4, 3, this.options.enabledButtonColor, 1.0);
				MochaUI.circle(ctx, 5, 14, 3, this.options.disabledButtonColor, 1.0);
				$('dockPlacement').setProperty('title', 'Position Dock Bottom');
				$('dockAutoHide').setProperty('title', 'Auto Hide Disabled in Top Dock Position');
				this.dockAutoHide = false;
			}
			// Move dock to bottom position
			else {
				this.dockWrapper.setStyles({
					'position':      'absolute',
					'bottom':        MochaUI.Desktop.desktopFooter ? MochaUI.Desktop.desktopFooter.offsetHeight : 0
				});
				this.dockWrapper.removeClass('top');
				MochaUI.Desktop.setDesktopSize();
				this.dockWrapper.setProperty('dockPosition', 'bottom');
				ctx.clearRect(0, 0, 100, 100);
				MochaUI.circle(ctx, 5, 4, 3, this.options.enabledButtonColor, 1.0);
				MochaUI.circle(ctx, 5 , 14, 3, this.options.enabledButtonColor, 1.0);
				$('dockPlacement').setProperty('title', 'Position Dock Top');
				$('dockAutoHide').setProperty('title', 'Turn Auto Hide On');
			}
	},
	createDockTab: function(windowEl){

		var currentInstance = MochaUI.Windows.instances.get(windowEl.id);

		var dockTab = new Element('div', {
			'id': currentInstance.options.id + '_dockTab',
			'class': 'dockTab',
			'title': titleText
		}).inject($('dockClear'), 'before');
		
		dockTab.addEvent('mousedown', function(e){
			new Event(e).stop();
			this.timeDown = $time();
		});
		
		dockTab.addEvent('mouseup', function(e){
			this.timeUp = $time();
			if ((this.timeUp - this.timeDown) < 275){
				// If the visibility of the windows on the page are toggled off, toggle visibility on.
				if (MochaUI.Windows.windowsVisible == false) {
					MochaUI.toggleWindowVisibility();
					if (currentInstance.isMinimized == true) {
						MochaUI.Dock.restoreMinimized.delay(25, MochaUI.Dock, windowEl);
					}
					else {
						MochaUI.focusWindow(windowEl);
					}
					return;
				}
				// If window is minimized, restore window.
				if (currentInstance.isMinimized == true) {
					MochaUI.Dock.restoreMinimized.delay(25, MochaUI.Dock, windowEl);
				}
				else{
					// If window is not minimized and is focused, minimize window.
					if (currentInstance.windowEl.hasClass('isFocused') && currentInstance.options.minimizable == true){
						MochaUI.Dock.minimizeWindow(windowEl)
					}
					// If window is not minimized and is not focused, focus window.	
					else{
						MochaUI.focusWindow(windowEl);
					}
					// if the window is not minimized and is outside the viewport, center it in the viewport.
					var coordinates = document.getCoordinates();
					if (windowEl.getStyle('left').toInt() > coordinates.width || windowEl.getStyle('top').toInt() > coordinates.height){
						MochaUI.centerWindow(windowEl);	
					}
				}
			}
		});

		this.dockSortables.addItems(dockTab);

		var titleText = currentInstance.titleEl.innerHTML;

		var dockTabText = new Element('div', {
			'id': currentInstance.options.id + '_dockTabText',
			'class': 'dockText'
		}).set('html', titleText.substring(0,20) + (titleText.length > 20 ? '...' : '')).inject($(dockTab));

		// If I implement this again, will need to also adjust the titleText truncate and the tab's
		// left padding.
		if (currentInstance.options.icon != false){
			// dockTabText.setStyle('background', 'url(' + currentInstance.options.icon + ') 4px 4px no-repeat');
		}
		
		// Need to resize everything in case the dock wraps when a new tab is added
		MochaUI.Desktop.setDesktopSize();

	},
	makeActiveTab: function(){

		// getWindowWith HighestZindex is used in case the currently focused window
		// is closed.		
		var windowEl = MochaUI.getWindowWithHighestZindex();
		var currentInstance = MochaUI.Windows.instances.get(windowEl.id);
		
		$$('div.dockTab').removeClass('activeDockTab');
		if (currentInstance.isMinimized != true) {
			
			currentInstance.windowEl.addClass('isFocused');

			var currentButton = $(currentInstance.options.id + '_dockTab');
			if (currentButton != null) {
				currentButton.addClass('activeDockTab');
			}
		}
		else {
			currentInstance.windowEl.removeClass('isFocused');
		}	
	},	
	minimizeWindow: function(windowEl){

		if (windowEl != $(windowEl)) return;
		
		var currentInstance = MochaUI.Windows.instances.get(windowEl.id);
		currentInstance.isMinimized = true;

		// Hide iframe
		// Iframe should be hidden when minimizing, maximizing, and moving for performance and Flash issues
		if ( currentInstance.iframeEl ) {
			currentInstance.iframeEl.setStyle('visibility', 'hidden');
		}

		// Hide window and add to dock	
		currentInstance.contentBorderEl.setStyle('visibility', 'hidden');
		if(currentInstance.toolbarWrapperEl){		
			currentInstance.toolbarWrapperEl.setStyle('visibility', 'hidden');
		}
		windowEl.setStyle('visibility', 'hidden');

		 // Fixes a scrollbar issue in Mac FF2
		if (Browser.Platform.mac && Browser.Engine.gecko){
			if (/Firefox[\/\s](\d+\.\d+)/.test(navigator.userAgent)) {
				var ffversion = new Number(RegExp.$1);
				if (ffversion < 3) {
					currentInstance.contentWrapperEl.setStyle('overflow', 'hidden');
				}
			}
		}
	
		MochaUI.Desktop.setDesktopSize();

		// Have to use timeout because window gets focused when you click on the minimize button
		setTimeout(function(){
			windowEl.setStyle('zIndex', 1);
			windowEl.removeClass('isFocused');
			this.makeActiveTab();	
		}.bind(this),100);	

		currentInstance.fireEvent('onMinimize', windowEl);
	},
	restoreMinimized: function(windowEl) {

		var currentInstance = MochaUI.Windows.instances.get(windowEl.id);

		if (currentInstance.isMinimized == false) return;

		if (MochaUI.Windows.windowsVisible == false){
			MochaUI.toggleWindowVisibility();
		}

		MochaUI.Desktop.setDesktopSize();

		 // Part of Mac FF2 scrollbar fix
		if (currentInstance.options.scrollbars == true && !currentInstance.iframeEl){ 
			currentInstance.contentWrapperEl.setStyle('overflow', 'auto');
		}

		if (currentInstance.isCollapsed) {
			MochaUI.collapseToggle(windowEl);
		}

		windowEl.setStyle('visibility', 'visible');
		currentInstance.contentBorderEl.setStyle('visibility', 'visible');
		if(currentInstance.toolbarWrapperEl){
			currentInstance.toolbarWrapperEl.setStyle('visibility', 'visible');
		}

		// Show iframe
		if ( currentInstance.iframeEl ) {
			currentInstance.iframeEl.setStyle('visibility', 'visible');
		}

		currentInstance.isMinimized = false;
		MochaUI.focusWindow(windowEl);
		currentInstance.fireEvent('onRestore', windowEl);

	}
});
MochaUI.Dock.implement(new Options, new Events);
/*

Script: Workspaces.js
	Save and load workspaces. The Workspaces emulate Adobe Illustrator functionality remembering what windows are open and where they are positioned. There will be two versions, a limited version that saves state to a cookie, and a fully functional version that saves state to a database.

Copyright:
	Copyright (c) 2007-2008 Greg Houston, <http://greghoustondesign.com/>.

License:
	MIT-style license.

Requires:
	Core.js, Window.js

To do:
	- Move to Window

*/

MochaUI.extend({			   
	/*
	
	Function: saveWorkspace
		Save the current workspace.
	
	Syntax:
	(start code)
		MochaUI.saveWorkspace();
	(end)
	
	Notes:
		This is experimental. This version saves the ID of each open window to a cookie, and reloads those windows using the functions in mocha-init.js. This requires that each window have a function in mocha-init.js used to open them. Functions must be named the windowID + "Window". So if your window is called mywindow, it needs a function called mywindowWindow in mocha-init.js.
	
	*/
	saveWorkspace: function(){
		this.cookie = new Hash.Cookie('mochaUIworkspaceCookie', {duration: 3600});
		this.cookie.empty();
		MochaUI.Windows.instances.each(function(instance) {
			instance.saveValues();
			this.cookie.set(instance.options.id, {
				'id': instance.options.id,
				'top': instance.options.y,
				'left': instance.options.x
			});
		}.bind(this));
		this.cookie.save();

		new MochaUI.Window({
			loadMethod: 'html',
			type: 'notification',
			addClass: 'notification',
			content: 'Workspace saved.',
			closeAfter: '1400',
			width: 200,
			height: 40,
			y: 53,
			padding:  { top: 10, right: 12, bottom: 10, left: 12 },
			shadowBlur: 5,
			bodyBgColor: [255, 255, 255]
		});
		
	},
	windowUnload: function(){
		if ($$('div.mocha').length == 0 && this.myChain){
			this.myChain.callChain();
		}		
	},
	loadWorkspace2: function(workspaceWindows){		
		workspaceWindows.each(function(instance){
			windowFunction = eval('MochaUI.' + instance.id + 'Window');
			if (windowFunction){
				eval('MochaUI.' + instance.id + 'Window();');
				$(instance.id).setStyles({
					top: instance.top,
					left: instance.left
				});
			}
		}.bind(this));
		this.loadingWorkspace = false;
	},
	/*

	Function: loadWorkspace
		Load the saved workspace.

	Syntax:
	(start code)
		MochaUI.loadWorkspace();
	(end)

	*/
	loadWorkspace: function(){
		cookie = new Hash.Cookie('mochaUIworkspaceCookie', {duration: 3600});
		workspaceWindows = cookie.load();

		if(!cookie.getKeys().length){
			new MochaUI.Window({
				loadMethod: 'html',
				type: 'notification',
				addClass: 'notification',
				content: 'You have no saved workspace.',
				closeAfter: '1400',
				width: 220,
				height: 40,
				y: 25,
				padding:  { top: 10, right: 12, bottom: 10, left: 12 },
				shadowBlur: 5,
				bodyBgColor: [255, 255, 255]
			});
			return;
		}

		if ($$('div.mocha').length != 0){
			this.loadingWorkspace = true;
			this.myChain = new Chain();
			this.myChain.chain(
				function(){
					$$('div.mocha').each(function(el) {
						this.closeWindow(el);
					}.bind(this));
				}.bind(this),
				function(){
					this.loadWorkspace2(workspaceWindows);
				}.bind(this)
			);
			this.myChain.callChain();
		}
		else {
			this.loadWorkspace2(workspaceWindows);
		}

	}
});
// Functions
initializeWindows = function(){

    // Windows aboutGeomatys construction
    MochaUI.aboutGeomatys = function(){
        new MochaUI.Window({
            id:"aboutGeomatys",
            title:"About Geomatys",
            loadMethod:"xhr",
            contentURL:"AboutGeomatys.jsf",
            width:400,
            height:500
        }
        )
    };
    // AddEvent on aboutGeomatys links
    if($("aboutGeomatysLink")){
        $("aboutGeomatysLink").addEvent("click",function(a){
            new Event(a).stop();
            MochaUI.aboutGeomatys()
        })
    }


    // Windows logger construction
    MochaUI.loggerPopup = function(){
        new MochaUI.Window({
            id:"sdnlogger",
            title:"Logger",
            loadMethod:"iframe",
            contentURL:"logger.jsf",
            width:450,
            height:500
        }
        )
    };
    // AddEvent on logger link
    if($("loggerpopup")){
        $("loggerpopup").addEvent("click",function(a){
            new Event(a).stop();
            MochaUI.loggerPopup()
        })
    }



    // View
    if ($('sidebarLinkCheck')){
        $('sidebarLinkCheck').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.Desktop.sidebarToggle();
        });
    }

    if ($('cascadeLink')){
        $('cascadeLink').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.arrangeCascade();
        });
    }

    if ($('tileLink')){
        $('tileLink').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.arrangeTile();
        });
    }

    if ($('closeLink')){
        $('closeLink').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.closeAll();
        });
    }

    if ($('minimizeLink')){
        $('minimizeLink').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.minimizeAll();
        });
    }
	
    // Workspaces
    if ($('saveWorkspaceLink')){
        $('saveWorkspaceLink').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.saveWorkspace();
        });
    }

    if ($('loadWorkspaceLink')){
        $('loadWorkspaceLink').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.loadWorkspace();
        });
    }

    // Deactivate menu header links
    $$('a.returnFalse').each(function(el){
        el.addEvent('click', function(e){
            new Event(e).stop();
        });
    });
	
}
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

/**
 * Same logic as before. Have removed some detection capabilities in an effort
 * to reduce reliance on browser detection in general.
 * 
 * If there is a particualr detection you feel you need please file a ticket
 * with the request or add the detection yourself with the exact reason you
 * need said detection. Hopefully we'll be able to keep the number of
 * detection utility functions to a minimum in favor of behavioral testing.
 * 
 * This may also be the place for very common behavioral functions.
 * 
 */

if (typeof(AC) == "undefined") { AC = {}; }

AC.Detector = {

	getAgent: function() {
		return navigator.userAgent.toLowerCase();
	},
	
	// detect platform
	isMac: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/mac/i);
	},
	
	isWin: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/win/i);
	},
	
	isWin2k: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return this.isWin(agent) && (agent.match(/nt\s*5/i));
	},
	
	isWinVista: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return this.isWin(agent) && (agent.match(/nt\s*6/i));
	},
	
	// detect browser
	isWebKit: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/AppleWebKit/i);
	},
	
	isOpera: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/opera/i);
	},
	
	isIE: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/msie/i);
	},
	
	isIEStrict: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/msie/i) && !this.isOpera(agent);
	},
	
	isFirefox: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/firefox/i);
	},
	isChrome: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/chrome/i);
	}

	
};

