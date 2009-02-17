var MooTools={version:"1.2.0",build:""};
var Native=function(O){O=O||{};
var S=O.afterImplement||function(){};
var R=O.generics;
R=(R!==false);
var Q=O.legacy;
var T=O.initialize;
var M=O.protect;
var N=O.name;
var L=T||Q;
L.constructor=Native;
L.$family={name:"native"};
if(Q&&T){L.prototype=Q.prototype
}L.prototype.constructor=L;
if(N){var K=N.toLowerCase();
L.prototype.$family={name:K};
Native.typize(L,K)
}var P=function(D,B,C,A){if(!M||A||!D.prototype[B]){D.prototype[B]=C
}if(R){Native.genericize(D,B,M)
}S.call(D,B,C);
return D
};
L.implement=function(A,B,C){if(typeof A=="string"){return P(this,A,B,C)
}for(var D in A){P(this,D,A[D],B)
}return this
};
L.alias=function(D,B,C){if(typeof D=="string"){D=this.prototype[D];
if(D){P(this,B,D,C)
}}else{for(var A in D){this.alias(A,D[A],B)
}}return this
};
return L
};
Native.implement=function(F,G){for(var H=0,E=F.length;
H<E;
H++){F[H].implement(G)
}};
Native.genericize=function(F,E,D){if((!D||!F[E])&&typeof F.prototype[E]=="function"){F[E]=function(){var A=Array.prototype.slice.call(arguments);
return F.prototype[E].apply(A.shift(),A)
}
}};
Native.typize=function(C,D){if(!C.type){C.type=function(A){return($type(A)===D)
}
}};
Native.alias=function(I,L,G,H){for(var J=0,K=I.length;
J<K;
J++){I[J].alias(L,G,H)
}};
(function(D){for(var C in D){Native.typize(D[C],C)
}})({"boolean":Boolean,"native":Native,object:Object});
(function(D){for(var C in D){new Native({name:C,initialize:D[C],protect:true})
}})({String:String,Function:Function,Number:Number,Array:Array,RegExp:RegExp,Date:Date});
(function(F,D){for(var E=D.length;
E--;
E){Native.genericize(F,D[E],true)
}return arguments.callee
})(Array,["pop","push","reverse","shift","sort","splice","unshift","concat","join","slice","toString","valueOf","indexOf","lastIndexOf"])(String,["charAt","charCodeAt","concat","indexOf","lastIndexOf","match","replace","search","slice","split","substr","substring","toLowerCase","toUpperCase","valueOf"]);
function $chk(B){return !!(B||B===0)
}function $clear(B){clearTimeout(B);
clearInterval(B);
return null
}function $defined(B){return(B!=undefined)
}function $empty(){}function $arguments(B){return function(){return arguments[B]
}
}function $lambda(B){return(typeof B=="function")?B:function(){return B
}
}function $extend(E,D){for(var F in (D||{})){E[F]=D[F]
}return E
}function $unlink(I){var J;
switch($type(I)){case"object":J={};
for(var G in I){J[G]=$unlink(I[G])
}break;
case"hash":J=$unlink(I.getClean());
break;
case"array":J=[];
for(var H=0,F=I.length;
H<F;
H++){J[H]=$unlink(I[H])
}break;
default:return I
}return J
}function $merge(){var K={};
for(var L=0,H=arguments.length;
L<H;
L++){var N=arguments[L];
if($type(N)!="object"){continue
}for(var M in N){var I=N[M],J=K[M];
K[M]=(J&&$type(I)=="object"&&$type(J)=="object")?$merge(J,I):$unlink(I)
}}return K
}function $pick(){for(var D=0,C=arguments.length;
D<C;
D++){if(arguments[D]!=undefined){return arguments[D]
}}return null
}function $random(D,C){return Math.floor(Math.random()*(C-D+1)+D)
}function $splat(D){var C=$type(D);
return(C)?((C!="array"&&C!="arguments")?[D]:D):[]
}var $time=Date.now||function(){return new Date().getTime()
};
function $try(){for(var F=0,D=arguments.length;
F<D;
F++){try{return arguments[F]()
}catch(E){}}return null
}function $type(B){if(B==undefined){return false
}if(B.$family){return(B.$family.name=="number"&&!isFinite(B))?false:B.$family.name
}if(B.nodeName){switch(B.nodeType){case 1:return"element";
case 3:return(/\S/).test(B.nodeValue)?"textnode":"whitespace"
}}else{if(typeof B.length=="number"){if(B.callee){return"arguments"
}else{if(B.item){return"collection"
}}}}return typeof B
}var Hash=new Native({name:"Hash",initialize:function(C){if($type(C)=="hash"){C=$unlink(C.getClean())
}for(var D in C){this[D]=C[D]
}return this
}});
Hash.implement({getLength:function(){var D=0;
for(var C in this){if(this.hasOwnProperty(C)){D++
}}return D
},forEach:function(F,E){for(var D in this){if(this.hasOwnProperty(D)){F.call(E,this[D],D,this)
}}},getClean:function(){var D={};
for(var C in this){if(this.hasOwnProperty(C)){D[C]=this[C]
}}return D
}});
Hash.alias("forEach","each");
function $H(B){return new Hash(B)
}Array.implement({forEach:function(G,F){for(var H=0,E=this.length;
H<E;
H++){G.call(F,this[H],H,this)
}}});
Array.alias("forEach","each");
function $A(G){if(G.item){var F=[];
for(var H=0,E=G.length;
H<E;
H++){F[H]=G[H]
}return F
}return Array.prototype.slice.call(G)
}function $each(G,H,F){var E=$type(G);
((E=="arguments"||E=="collection"||E=="array")?Array:Hash).each(G,H,F)
}var Browser=new Hash({Engine:{name:"unknown",version:""},Platform:{name:(navigator.platform.match(/mac|win|linux/i)||["other"])[0].toLowerCase()},Features:{xpath:!!(document.evaluate),air:!!(window.runtime)},Plugins:{}});
if(window.opera){Browser.Engine={name:"presto",version:(document.getElementsByClassName)?950:925}
}else{if(window.ActiveXObject){Browser.Engine={name:"trident",version:(window.XMLHttpRequest)?5:4}
}else{if(!navigator.taintEnabled){Browser.Engine={name:"webkit",version:(Browser.Features.xpath)?420:419}
}else{if(document.getBoxObjectFor!=null){Browser.Engine={name:"gecko",version:(document.getElementsByClassName)?19:18}
}}}}Browser.Engine[Browser.Engine.name]=Browser.Engine[Browser.Engine.name+Browser.Engine.version]=true;
if(window.orientation!=undefined){Browser.Platform.name="ipod"
}Browser.Platform[Browser.Platform.name]=true;
Browser.Request=function(){return $try(function(){return new XMLHttpRequest()
},function(){return new ActiveXObject("MSXML2.XMLHTTP")
})
};
Browser.Features.xhr=!!(Browser.Request());
Browser.Plugins.Flash=(function(){var B=($try(function(){return navigator.plugins["Shockwave Flash"].description
},function(){return new ActiveXObject("ShockwaveFlash.ShockwaveFlash").GetVariable("$version")
})||"0 r0").match(/\d+/g);
return{version:parseInt(B[0]||0+"."+B[1]||0),build:parseInt(B[2]||0)}
})();
function $exec(D){if(!D){return D
}if(window.execScript){window.execScript(D)
}else{var C=document.createElement("script");
C.setAttribute("type","text/javascript");
C.text=D;
document.head.appendChild(C);
document.head.removeChild(C)
}return D
}Native.UID=1;
var $uid=(Browser.Engine.trident)?function(B){return(B.uid||(B.uid=[Native.UID++]))[0]
}:function(B){return B.uid||(B.uid=Native.UID++)
};
var Window=new Native({name:"Window",legacy:(Browser.Engine.trident)?null:window.Window,initialize:function(B){$uid(B);
if(!B.Element){B.Element=$empty;
if(Browser.Engine.webkit){B.document.createElement("iframe")
}B.Element.prototype=(Browser.Engine.webkit)?window["[[DOMElement.prototype]]"]:{}
}return $extend(B,Window.Prototype)
},afterImplement:function(D,C){window[D]=Window.Prototype[D]=C
}});
Window.Prototype={$family:{name:"window"}};
new Window(window);
var Document=new Native({name:"Document",legacy:(Browser.Engine.trident)?null:window.Document,initialize:function(B){$uid(B);
B.head=B.getElementsByTagName("head")[0];
B.html=B.getElementsByTagName("html")[0];
B.window=B.defaultView||B.parentWindow;
if(Browser.Engine.trident4){$try(function(){B.execCommand("BackgroundImageCache",false,true)
})
}return $extend(B,Document.Prototype)
},afterImplement:function(D,C){document[D]=Document.Prototype[D]=C
}});
Document.Prototype={$family:{name:"document"}};
new Document(document);
Array.implement({every:function(G,F){for(var H=0,E=this.length;
H<E;
H++){if(!G.call(F,this[H],H,this)){return false
}}return true
},filter:function(H,G){var I=[];
for(var J=0,F=this.length;
J<F;
J++){if(H.call(G,this[J],J,this)){I.push(this[J])
}}return I
},clean:function(){return this.filter($defined)
},indexOf:function(G,F){var E=this.length;
for(var H=(F<0)?Math.max(0,E+F):F||0;
H<E;
H++){if(this[H]===G){return H
}}return -1
},map:function(H,G){var I=[];
for(var J=0,F=this.length;
J<F;
J++){I[J]=H.call(G,this[J],J,this)
}return I
},some:function(G,F){for(var H=0,E=this.length;
H<E;
H++){if(G.call(F,this[H],H,this)){return true
}}return false
},associate:function(G){var F={},H=Math.min(this.length,G.length);
for(var E=0;
E<H;
E++){F[G[E]]=this[E]
}return F
},link:function(I){var F={};
for(var G=0,J=this.length;
G<J;
G++){for(var H in I){if(I[H](this[G])){F[H]=this[G];
delete I[H];
break
}}}return F
},contains:function(C,D){return this.indexOf(C,D)!=-1
},extend:function(E){for(var F=0,D=E.length;
F<D;
F++){this.push(E[F])
}return this
},getLast:function(){return(this.length)?this[this.length-1]:null
},getRandom:function(){return(this.length)?this[$random(0,this.length-1)]:null
},include:function(B){if(!this.contains(B)){this.push(B)
}return this
},combine:function(E){for(var F=0,D=E.length;
F<D;
F++){this.include(E[F])
}return this
},erase:function(D){for(var C=this.length;
C--;
C){if(this[C]===D){this.splice(C,1)
}}return this
},empty:function(){this.length=0;
return this
},flatten:function(){var F=[];
for(var H=0,E=this.length;
H<E;
H++){var G=$type(this[H]);
if(!G){continue
}F=F.concat((G=="array"||G=="collection"||G=="arguments")?Array.flatten(this[H]):this[H])
}return F
},hexToRgb:function(D){if(this.length!=3){return null
}var C=this.map(function(A){if(A.length==1){A+=A
}return A.toInt(16)
});
return(D)?C:"rgb("+C+")"
},rgbToHex:function(F){if(this.length<3){return null
}if(this.length==4&&this[3]==0&&!F){return"transparent"
}var H=[];
for(var E=0;
E<3;
E++){var G=(this[E]-0).toString(16);
H.push((G.length==1)?"0"+G:G)
}return(F)?H:"#"+H.join("")
}});
Function.implement({extend:function(C){for(var D in C){this[D]=C[D]
}return this
},create:function(D){var C=this;
D=D||{};
return function(B){var F=D.arguments;
F=(F!=undefined)?$splat(F):Array.slice(arguments,(D.event)?1:0);
if(D.event){F=[B||window.event].extend(F)
}var A=function(){return C.apply(D.bind||null,F)
};
if(D.delay){return setTimeout(A,D.delay)
}if(D.periodical){return setInterval(A,D.periodical)
}if(D.attempt){return $try(A)
}return A()
}
},pass:function(C,D){return this.create({arguments:C,bind:D})
},attempt:function(C,D){return this.create({arguments:C,bind:D,attempt:true})()
},bind:function(D,C){return this.create({bind:D,arguments:C})
},bindWithEvent:function(D,C){return this.create({bind:D,event:true,arguments:C})
},delay:function(F,E,D){return this.create({delay:F,bind:E,arguments:D})()
},periodical:function(D,E,F){return this.create({periodical:D,bind:E,arguments:F})()
},run:function(C,D){return this.apply(D,$splat(C))
}});
Number.implement({limit:function(D,C){return Math.min(C,Math.max(D,this))
},round:function(B){B=Math.pow(10,B||0);
return Math.round(this*B)/B
},times:function(F,E){for(var D=0;
D<this;
D++){F.call(E,D,this)
}},toFloat:function(){return parseFloat(this)
},toInt:function(B){return parseInt(this,B||10)
}});
Number.alias("times","each");
(function(D){var C={};
D.each(function(A){if(!Number[A]){C[A]=function(){return Math[A].apply(null,[this].concat($A(arguments)))
}
}});
Number.implement(C)
})(["abs","acos","asin","atan","atan2","ceil","cos","exp","floor","log","max","min","pow","sin","sqrt","tan"]);
String.implement({test:function(C,D){return((typeof C=="string")?new RegExp(C,D):C).test(this)
},contains:function(C,D){return(D)?(D+this+D).indexOf(D+C+D)>-1:this.indexOf(C)>-1
},trim:function(){return this.replace(/^\s+|\s+$/g,"")
},clean:function(){return this.replace(/\s+/g," ").trim()
},camelCase:function(){return this.replace(/-\D/g,function(B){return B.charAt(1).toUpperCase()
})
},hyphenate:function(){return this.replace(/[A-Z]/g,function(B){return("-"+B.charAt(0).toLowerCase())
})
},capitalize:function(){return this.replace(/\b[a-z]/g,function(B){return B.toUpperCase()
})
},escapeRegExp:function(){return this.replace(/([-.*+?^${}()|[\]\/\\])/g,"\\$1")
},toInt:function(B){return parseInt(this,B||10)
},toFloat:function(){return parseFloat(this)
},hexToRgb:function(D){var C=this.match(/^#?(\w{1,2})(\w{1,2})(\w{1,2})$/);
return(C)?C.slice(1).hexToRgb(D):null
},rgbToHex:function(D){var C=this.match(/\d{1,3}/g);
return(C)?C.rgbToHex(D):null
},stripScripts:function(F){var D="";
var E=this.replace(/<script[^>]*>([\s\S]*?)<\/script>/gi,function(){D+=arguments[1]+"\n";
return""
});
if(F===true){$exec(D)
}else{if($type(F)=="function"){F(D,E)
}}return E
},substitute:function(C,D){return this.replace(D||(/\\?\{([^}]+)\}/g),function(A,B){if(A.charAt(0)=="\\"){return A.slice(1)
}return(C[B]!=undefined)?C[B]:""
})
}});
Hash.implement({has:Object.prototype.hasOwnProperty,keyOf:function(D){for(var C in this){if(this.hasOwnProperty(C)&&this[C]===D){return C
}}return null
},hasValue:function(B){return(Hash.keyOf(this,B)!==null)
},extend:function(B){Hash.each(B,function(A,D){Hash.set(this,D,A)
},this);
return this
},combine:function(B){Hash.each(B,function(A,D){Hash.include(this,D,A)
},this);
return this
},erase:function(B){if(this.hasOwnProperty(B)){delete this[B]
}return this
},get:function(B){return(this.hasOwnProperty(B))?this[B]:null
},set:function(C,D){if(!this[C]||this.hasOwnProperty(C)){this[C]=D
}return this
},empty:function(){Hash.each(this,function(D,C){delete this[C]
},this);
return this
},include:function(F,E){var D=this[F];
if(D==undefined){this[F]=E
}return this
},map:function(F,E){var D=new Hash;
Hash.each(this,function(A,B){D.set(B,F.call(E,A,B,this))
},this);
return D
},filter:function(F,E){var D=new Hash;
Hash.each(this,function(A,B){if(F.call(E,A,B,this)){D.set(B,A)
}},this);
return D
},every:function(F,E){for(var D in this){if(this.hasOwnProperty(D)&&!F.call(E,this[D],D)){return false
}}return true
},some:function(F,E){for(var D in this){if(this.hasOwnProperty(D)&&F.call(E,this[D],D)){return true
}}return false
},getKeys:function(){var B=[];
Hash.each(this,function(A,D){B.push(D)
});
return B
},getValues:function(){var B=[];
Hash.each(this,function(A){B.push(A)
});
return B
},toQueryString:function(C){var D=[];
Hash.each(this,function(A,B){if(C){B=C+"["+B+"]"
}var G;
switch($type(A)){case"object":G=Hash.toQueryString(A,B);
break;
case"array":var H={};
A.each(function(E,F){H[F]=E
});
G=Hash.toQueryString(H,B);
break;
default:G=B+"="+encodeURIComponent(A)
}if(A!=undefined){D.push(G)
}});
return D.join("&")
}});
Hash.alias({keyOf:"indexOf",hasValue:"contains"});
var Event=new Native({name:"Event",initialize:function(Q,Y){Y=Y||window;
var T=Y.document;
Q=Q||Y.event;
if(Q.$extended){return Q
}this.$extended=true;
var U=Q.type;
var X=Q.target||Q.srcElement;
while(X&&X.nodeType==3){X=X.parentNode
}if(U.test(/key/)){var P=Q.which||Q.keyCode;
var R=Event.Keys.keyOf(P);
if(U=="keydown"){var N=P-111;
if(N>0&&N<13){R="f"+N
}}R=R||String.fromCharCode(P).toLowerCase()
}else{if(U.match(/(click|mouse|menu)/i)){T=(!T.compatMode||T.compatMode=="CSS1Compat")?T.html:T.body;
var V={x:Q.pageX||Q.clientX+T.scrollLeft,y:Q.pageY||Q.clientY+T.scrollTop};
var O={x:(Q.pageX)?Q.pageX-Y.pageXOffset:Q.clientX,y:(Q.pageY)?Q.pageY-Y.pageYOffset:Q.clientY};
if(U.match(/DOMMouseScroll|mousewheel/)){var W=(Q.wheelDelta)?Q.wheelDelta/120:-(Q.detail||0)/3
}var Z=(Q.which==3)||(Q.button==2);
var S=null;
if(U.match(/over|out/)){switch(U){case"mouseover":S=Q.relatedTarget||Q.fromElement;
break;
case"mouseout":S=Q.relatedTarget||Q.toElement
}if(!(function(){while(S&&S.nodeType==3){S=S.parentNode
}return true
}).create({attempt:Browser.Engine.gecko})()){S=false
}}}}return $extend(this,{event:Q,type:U,page:V,client:O,rightClick:Z,wheel:W,relatedTarget:S,target:X,code:P,key:R,shift:Q.shiftKey,control:Q.ctrlKey,alt:Q.altKey,meta:Q.metaKey})
}});
Event.Keys=new Hash({enter:13,up:38,down:40,left:37,right:39,esc:27,space:32,backspace:8,tab:9,"delete":46});
Event.implement({stop:function(){return this.stopPropagation().preventDefault()
},stopPropagation:function(){if(this.event.stopPropagation){this.event.stopPropagation()
}else{this.event.cancelBubble=true
}return this
},preventDefault:function(){if(this.event.preventDefault){this.event.preventDefault()
}else{this.event.returnValue=false
}return this
}});
var Class=new Native({name:"Class",initialize:function(D){D=D||{};
var C=function(B){for(var G in this){this[G]=$unlink(this[G])
}for(var A in Class.Mutators){if(!this[A]){continue
}Class.Mutators[A](this,this[A]);
delete this[A]
}this.constructor=C;
if(B===$empty){return this
}var H=(this.initialize)?this.initialize.apply(this,arguments):this;
if(this.options&&this.options.initialize){this.options.initialize.call(this)
}return H
};
$extend(C,this);
C.constructor=Class;
C.prototype=D;
return C
}});
Class.implement({implement:function(){Class.Mutators.Implements(this.prototype,Array.slice(arguments));
return this
}});
Class.Mutators={Implements:function(C,D){$splat(D).each(function(A){$extend(C,($type(A)=="class")?new A($empty):A)
})
},Extends:function(self,klass){var instance=new klass($empty);
delete instance.parent;
delete instance.parentOf;
for(var key in instance){var current=self[key],previous=instance[key];
if(current==undefined){self[key]=previous;
continue
}var ctype=$type(current),ptype=$type(previous);
if(ctype!=ptype){continue
}switch(ctype){case"function":if(!arguments.callee.caller){self[key]=eval("("+String(current).replace(/\bthis\.parent\(\s*(\))?/g,function(full,close){return"arguments.callee._parent_.call(this"+(close||", ")
})+")")
}self[key]._parent_=previous;
break;
case"object":self[key]=$merge(previous,current)
}}self.parent=function(){return arguments.callee.caller._parent_.apply(this,arguments)
};
self.parentOf=function(descendant){return descendant._parent_.apply(this,Array.slice(arguments,1))
}
}};
var Chain=new Class({chain:function(){this.$chain=(this.$chain||[]).extend(arguments);
return this
},callChain:function(){return(this.$chain&&this.$chain.length)?this.$chain.shift().apply(this,arguments):false
},clearChain:function(){if(this.$chain){this.$chain.empty()
}return this
}});
var Events=new Class({addEvent:function(E,F,D){E=Events.removeOn(E);
if(F!=$empty){this.$events=this.$events||{};
this.$events[E]=this.$events[E]||[];
this.$events[E].include(F);
if(D){F.internal=true
}}return this
},addEvents:function(C){for(var D in C){this.addEvent(D,C[D])
}return this
},fireEvent:function(E,F,D){E=Events.removeOn(E);
if(!this.$events||!this.$events[E]){return this
}this.$events[E].each(function(A){A.create({bind:this,delay:D,"arguments":F})()
},this);
return this
},removeEvent:function(D,C){D=Events.removeOn(D);
if(!this.$events||!this.$events[D]){return this
}if(!C.internal){this.$events[D].erase(C)
}return this
},removeEvents:function(G){for(var F in this.$events){if(G&&G!=F){continue
}var H=this.$events[F];
for(var E=H.length;
E--;
E){this.removeEvent(F,H[E])
}}return this
}});
Events.removeOn=function(B){return B.replace(/^on([A-Z])/,function(D,A){return A.toLowerCase()
})
};
var Options=new Class({setOptions:function(){this.options=$merge.run([this.options].extend(arguments));
if(!this.addEvent){return this
}for(var B in this.options){if($type(this.options[B])!="function"||!(/^on[A-Z]/).test(B)){continue
}this.addEvent(B,this.options[B]);
delete this.options[B]
}return this
}});
Document.implement({newElement:function(C,D){if(Browser.Engine.trident&&D){["name","type","checked"].each(function(A){if(!D[A]){return 
}C+=" "+A+'="'+D[A]+'"';
if(A!="checked"){delete D[A]
}});
C="<"+C+">"
}return $.element(this.createElement(C)).set(D)
},newTextNode:function(B){return this.createTextNode(B)
},getDocument:function(){return this
},getWindow:function(){return this.defaultView||this.parentWindow
},purge:function(){var E=this.getElementsByTagName("*");
for(var F=0,D=E.length;
F<D;
F++){Browser.freeMem(E[F])
}}});
var Element=new Native({name:"Element",legacy:window.Element,initialize:function(D,F){var E=Element.Constructors.get(D);
if(E){return E(F)
}if(typeof D=="string"){return document.newElement(D,F)
}return $(D).set(F)
},afterImplement:function(C,D){if(!Array[C]){Elements.implement(C,Elements.multi(C))
}Element.Prototype[C]=D
}});
Element.Prototype={$family:{name:"element"}};
Element.Constructors=new Hash;
var IFrame=new Native({name:"IFrame",generics:false,initialize:function(){var G=Array.link(arguments,{properties:Object.type,iframe:$defined});
var I=G.properties||{};
var J=$(G.iframe)||false;
var H=I.onload||$empty;
delete I.onload;
I.id=I.name=$pick(I.id,I.name,J.id,J.name,"IFrame_"+$time());
J=new Element(J||"iframe",I);
var F=function(){var C=$try(function(){return J.contentWindow.location.host
});
if(C&&C==window.location.host){var A=new Window(J.contentWindow);
var B=new Document(J.contentWindow.document);
$extend(A.Element.prototype,Element.Prototype)
}H.call(J.contentWindow,J.contentWindow.document)
};
(!window.frames[I.id])?J.addListener("load",F):F();
return J
}});
var Elements=new Native({initialize:function(J,N){N=$extend({ddup:true,cash:true},N);
J=J||[];
if(N.ddup||N.cash){var I={},K=[];
for(var M=0,H=J.length;
M<H;
M++){var L=$.element(J[M],!N.cash);
if(N.ddup){if(I[L.uid]){continue
}I[L.uid]=true
}K.push(L)
}J=K
}return(N.cash)?$extend(J,this):J
}});
Elements.implement({filter:function(C,D){if(!C){return this
}return new Elements(Array.filter(this,(typeof C=="string")?function(A){return A.match(C)
}:C,D))
}});
Elements.multi=function(B){return function(){var J=[];
var A=true;
for(var H=0,I=this.length;
H<I;
H++){var G=this[H][B].apply(this[H],arguments);
J.push(G);
if(A){A=($type(G)=="element")
}}return(A)?new Elements(J):J
}
};
Window.implement({$:function(F,E){if(F&&F.$family&&F.uid){return F
}var D=$type(F);
return($[D])?$[D](F,E,this.document):null
},$$:function(G){if(arguments.length==1&&typeof G=="string"){return this.document.getElements(G)
}var H=[];
var K=Array.flatten(arguments);
for(var J=0,L=K.length;
J<L;
J++){var I=K[J];
switch($type(I)){case"element":I=[I];
break;
case"string":I=this.document.getElements(I,true);
break;
default:I=false
}if(I){H.extend(I)
}}return new Elements(H)
},getDocument:function(){return this.document
},getWindow:function(){return this
}});
$.string=function(E,F,D){E=D.getElementById(E);
return(E)?$.element(E,F):null
};
$.element=function(E,F){$uid(E);
if(!F&&!E.$family&&!(/^object|embed$/i).test(E.tagName)){var H=Element.Prototype;
for(var G in H){E[G]=H[G]
}}return E
};
$.object=function(F,E,D){if(F.toElement){return $.element(F.toElement(D),E)
}return null
};
$.textnode=$.whitespace=$.window=$.document=$arguments(0);
Native.implement([Element,Document],{getElement:function(C,D){return $(this.getElements(C,true)[0]||null,D)
},getElements:function(E,F){E=E.split(",");
var G=[];
var H=(E.length>1);
E.each(function(B){var A=this.getElementsByTagName(B.trim());
(H)?G.extend(A):G=A
},this);
return new Elements(G,{ddup:H,cash:!F})
}});
Element.Storage={get:function(B){return(this[B]||(this[B]={}))
}};
Element.Inserters=new Hash({before:function(D,C){if(C.parentNode){C.parentNode.insertBefore(D,C)
}},after:function(F,D){if(!D.parentNode){return 
}var E=D.nextSibling;
(E)?D.parentNode.insertBefore(F,E):D.parentNode.appendChild(F)
},bottom:function(D,C){C.appendChild(D)
},top:function(F,D){var E=D.firstChild;
(E)?D.insertBefore(F,E):D.appendChild(F)
}});
Element.Inserters.inside=Element.Inserters.bottom;
Element.Inserters.each(function(E,F){var D=F.capitalize();
Element.implement("inject"+D,function(A){E(this,$(A,true));
return this
});
Element.implement("grab"+D,function(A){E($(A,true),this);
return this
})
});
Element.implement({getDocument:function(){return this.ownerDocument
},getWindow:function(){return this.ownerDocument.getWindow()
},getElementById:function(F,G){var H=this.ownerDocument.getElementById(F);
if(!H){return null
}for(var E=H.parentNode;
E!=this;
E=E.parentNode){if(!E){return null
}}return $.element(H,G)
},set:function(F,H){switch($type(F)){case"object":for(var G in F){this.set(G,F[G])
}break;
case"string":var E=Element.Properties.get(F);
(E&&E.set)?E.set.apply(this,Array.slice(arguments,1)):this.setProperty(F,H)
}return this
},get:function(D){var C=Element.Properties.get(D);
return(C&&C.get)?C.get.apply(this,Array.slice(arguments,1)):this.getProperty(D)
},erase:function(D){var C=Element.Properties.get(D);
(C&&C.erase)?C.erase.apply(this,Array.slice(arguments,1)):this.removeProperty(D);
return this
},match:function(B){return(!B||Element.get(this,"tag")==B)
},inject:function(D,C){Element.Inserters.get(C||"bottom")(this,$(D,true));
return this
},wraps:function(D,C){D=$(D,true);
return this.replaces(D).grab(D,C)
},grab:function(D,C){Element.Inserters.get(C||"bottom")($(D,true),this);
return this
},appendText:function(D,C){return this.grab(this.getDocument().newTextNode(D),C)
},adopt:function(){Array.flatten(arguments).each(function(B){B=$(B,true);
if(B){this.appendChild(B)
}},this);
return this
},dispose:function(){return(this.parentNode)?this.parentNode.removeChild(this):this
},clone:function(M,N){switch($type(this)){case"element":var U={};
for(var V=0,X=this.attributes.length;
V<X;
V++){var O=this.attributes[V],Q=O.nodeName.toLowerCase();
if(Browser.Engine.trident&&(/input/i).test(this.tagName)&&(/width|height/).test(Q)){continue
}var R=(Q=="style"&&this.style)?this.style.cssText:O.nodeValue;
if(!$chk(R)||Q=="uid"||(Q=="id"&&!N)){continue
}if(R!="inherit"&&["string","number"].contains($type(R))){U[Q]=R
}}var S=new Element(this.nodeName.toLowerCase(),U);
if(M!==false){for(var T=0,W=this.childNodes.length;
T<W;
T++){var P=Element.clone(this.childNodes[T],true,N);
if(P){S.grab(P)
}}}return S;
case"textnode":return document.newTextNode(this.nodeValue)
}return null
},replaces:function(B){B=$(B,true);
B.parentNode.replaceChild(this,B);
return this
},hasClass:function(B){return this.className.contains(B," ")
},addClass:function(B){if(!this.hasClass(B)){this.className=(this.className+" "+B).clean()
}return this
},removeClass:function(B){this.className=this.className.replace(new RegExp("(^|\\s)"+B+"(?:\\s|$)"),"$1").clean();
return this
},toggleClass:function(B){return this.hasClass(B)?this.removeClass(B):this.addClass(B)
},getComputedStyle:function(D){if(this.currentStyle){return this.currentStyle[D.camelCase()]
}var C=this.getWindow().getComputedStyle(this,null);
return(C)?C.getPropertyValue([D.hyphenate()]):null
},empty:function(){$A(this.childNodes).each(function(B){Browser.freeMem(B);
Element.empty(B);
Element.dispose(B)
},this);
return this
},destroy:function(){Browser.freeMem(this.empty().dispose());
return null
},getSelected:function(){return new Elements($A(this.options).filter(function(B){return B.selected
}))
},toQueryString:function(){var B=[];
this.getElements("input, select, textarea").each(function(D){if(!D.name||D.disabled){return 
}var A=(D.tagName.toLowerCase()=="select")?Element.getSelected(D).map(function(C){return C.value
}):((D.type=="radio"||D.type=="checkbox")&&!D.checked)?null:D.value;
$splat(A).each(function(C){if(C){B.push(D.name+"="+encodeURIComponent(C))
}})
});
return B.join("&")
},getProperty:function(G){var H=Element.Attributes,E=H.Props[G];
var F=(E)?this[E]:this.getAttribute(G,2);
return(H.Bools[G])?!!F:(E)?F:F||null
},getProperties:function(){var B=$A(arguments);
return B.map(function(A){return this.getProperty(A)
},this).associate(B)
},setProperty:function(H,G){var I=Element.Attributes,J=I.Props[H],F=$defined(G);
if(J&&I.Bools[H]){G=(G||!F)?true:false
}else{if(!F){return this.removeProperty(H)
}}(J)?this[J]=G:this.setAttribute(H,G);
return this
},setProperties:function(C){for(var D in C){this.setProperty(D,C[D])
}return this
},removeProperty:function(F){var G=Element.Attributes,H=G.Props[F],E=(H&&G.Bools[F]);
(H)?this[H]=(E)?false:"":this.removeAttribute(F);
return this
},removeProperties:function(){Array.each(arguments,this.removeProperty,this);
return this
}});
(function(){var B=function(N,P,A,O,L,J){var M=N[A||P];
var K=[];
while(M){if(M.nodeType==1&&(!O||Element.match(M,O))){K.push(M);
if(!L){break
}}M=M[P]
}return(L)?new Elements(K,{ddup:false,cash:!J}):$(K[0],J)
};
Element.implement({getPrevious:function(D,A){return B(this,"previousSibling",null,D,false,A)
},getAllPrevious:function(D,A){return B(this,"previousSibling",null,D,true,A)
},getNext:function(D,A){return B(this,"nextSibling",null,D,false,A)
},getAllNext:function(D,A){return B(this,"nextSibling",null,D,true,A)
},getFirst:function(D,A){return B(this,"nextSibling","firstChild",D,false,A)
},getLast:function(D,A){return B(this,"previousSibling","lastChild",D,false,A)
},getParent:function(D,A){return B(this,"parentNode",null,D,false,A)
},getParents:function(D,A){return B(this,"parentNode",null,D,true,A)
},getChildren:function(D,A){return B(this,"nextSibling","firstChild",D,true,A)
},hasChild:function(A){A=$(A,true);
return(!!A&&$A(this.getElementsByTagName(A.tagName)).contains(A))
}})
})();
Element.Properties=new Hash;
Element.Properties.style={set:function(B){this.style.cssText=B
},get:function(){return this.style.cssText
},erase:function(){this.style.cssText=""
}};
Element.Properties.tag={get:function(){return this.tagName.toLowerCase()
}};
Element.Properties.href={get:function(){return(!this.href)?null:this.href.replace(new RegExp("^"+document.location.protocol+"//"+document.location.host),"")
}};
Element.Properties.html={set:function(){return this.innerHTML=Array.flatten(arguments).join("")
}};
Native.implement([Element,Window,Document],{addListener:function(D,C){if(this.addEventListener){this.addEventListener(D,C,false)
}else{this.attachEvent("on"+D,C)
}return this
},removeListener:function(D,C){if(this.removeEventListener){this.removeEventListener(D,C,false)
}else{this.detachEvent("on"+D,C)
}return this
},retrieve:function(H,E){var F=Element.Storage.get(this.uid);
var G=F[H];
if($defined(E)&&!$defined(G)){G=F[H]=E
}return $pick(G)
},store:function(F,D){var E=Element.Storage.get(this.uid);
E[F]=D;
return this
},eliminate:function(C){var D=Element.Storage.get(this.uid);
delete D[C];
return this
}});
Element.Attributes=new Hash({Props:{html:"innerHTML","class":"className","for":"htmlFor",text:(Browser.Engine.trident)?"innerText":"textContent"},Bools:["compact","nowrap","ismap","declare","noshade","checked","disabled","readonly","multiple","selected","noresize","defer"],Camels:["value","accessKey","cellPadding","cellSpacing","colSpan","frameBorder","maxLength","readOnly","rowSpan","tabIndex","useMap"]});
Browser.freeMem=function(C){if(!C){return 
}if(Browser.Engine.trident&&(/object/i).test(C.tagName)){for(var D in C){if(typeof C[D]=="function"){C[D]=$empty
}}Element.dispose(C)
}if(C.uid&&C.removeEvents){C.removeEvents()
}};
(function(F){var E=F.Bools,D=F.Camels;
F.Bools=E=E.associate(E);
Hash.extend(Hash.combine(F.Props,E),D.associate(D.map(function(A){return A.toLowerCase()
})));
F.erase("Camels")
})(Element.Attributes);
window.addListener("unload",function(){window.removeListener("unload",arguments.callee);
document.purge();
if(Browser.Engine.trident){CollectGarbage()
}});
Element.Properties.events={set:function(B){this.addEvents(B)
}};
Native.implement([Element,Window,Document],{addEvent:function(R,P){var O=this.retrieve("events",{});
O[R]=O[R]||{keys:[],values:[]};
if(O[R].keys.contains(P)){return this
}O[R].keys.push(P);
var Q=R,M=Element.Events.get(R),K=P,N=this;
if(M){if(M.onAdd){M.onAdd.call(this,P)
}if(M.condition){K=function(A){if(M.condition.call(this,A)){return P.call(this,A)
}return false
}
}Q=M.base||Q
}var J=function(){return P.call(N)
};
var L=Element.NativeEvents[Q]||0;
if(L){if(L==2){J=function(A){A=new Event(A,N.getWindow());
if(K.call(N,A)===false){A.stop()
}}
}this.addListener(Q,J)
}O[R].values.push(J);
return this
},removeEvent:function(L,M){var N=this.retrieve("events");
if(!N||!N[L]){return this
}var I=N[L].keys.indexOf(M);
if(I==-1){return this
}var H=N[L].keys.splice(I,1)[0];
var J=N[L].values.splice(I,1)[0];
var K=Element.Events.get(L);
if(K){if(K.onRemove){K.onRemove.call(this,M)
}L=K.base||L
}return(Element.NativeEvents[L])?this.removeListener(L,J):this
},addEvents:function(C){for(var D in C){this.addEvent(D,C[D])
}return this
},removeEvents:function(F){var D=this.retrieve("events");
if(!D){return this
}if(!F){for(var E in D){this.removeEvents(E)
}D=null
}else{if(D[F]){while(D[F].keys[0]){this.removeEvent(F,D[F].keys[0])
}D[F]=null
}}return this
},fireEvent:function(F,H,E){var G=this.retrieve("events");
if(!G||!G[F]){return this
}G[F].keys.each(function(A){A.create({bind:this,delay:E,"arguments":H})()
},this);
return this
},cloneEvents:function(F,E){F=$(F);
var G=F.retrieve("events");
if(!G){return this
}if(!E){for(var H in G){this.cloneEvents(F,H)
}}else{if(G[E]){G[E].keys.each(function(A){this.addEvent(E,A)
},this)
}}return this
}});
Element.NativeEvents={click:2,dblclick:2,mouseup:2,mousedown:2,contextmenu:2,mousewheel:2,DOMMouseScroll:2,mouseover:2,mouseout:2,mousemove:2,selectstart:2,selectend:2,keydown:2,keypress:2,keyup:2,focus:2,blur:2,change:2,reset:2,select:2,submit:2,load:1,unload:1,beforeunload:2,resize:1,move:1,DOMContentLoaded:1,readystatechange:1,error:1,abort:1,scroll:1};
(function(){var B=function(D){var A=D.relatedTarget;
if(A==undefined){return true
}if(A===false){return false
}return($type(this)!="document"&&A!=this&&A.prefix!="xul"&&!this.hasChild(A))
};
Element.Events=new Hash({mouseenter:{base:"mouseover",condition:B},mouseleave:{base:"mouseout",condition:B},mousewheel:{base:(Browser.Engine.gecko)?"DOMMouseScroll":"mousewheel"}})
})();
Element.Properties.styles={set:function(B){this.setStyles(B)
}};
Element.Properties.opacity={set:function(C,D){if(!D){if(C==0){if(this.style.visibility!="hidden"){this.style.visibility="hidden"
}}else{if(this.style.visibility!="visible"){this.style.visibility="visible"
}}}if(!this.currentStyle||!this.currentStyle.hasLayout){this.style.zoom=1
}if(Browser.Engine.trident){this.style.filter=(C==1)?"":"alpha(opacity="+C*100+")"
}this.style.opacity=C;
this.store("opacity",C)
},get:function(){return this.retrieve("opacity",1)
}};
Element.implement({setOpacity:function(B){return this.set("opacity",B,true)
},getOpacity:function(){return this.get("opacity")
},setStyle:function(F,D){switch(F){case"opacity":return this.set("opacity",parseFloat(D));
case"float":F=(Browser.Engine.trident)?"styleFloat":"cssFloat"
}F=F.camelCase();
if($type(D)!="string"){var E=(Element.Styles.get(F)||"@").split(" ");
D=$splat(D).map(function(A,B){if(!E[B]){return""
}return($type(A)=="number")?E[B].replace("@",Math.round(A)):A
}).join(" ")
}else{if(D==String(Number(D))){D=Math.round(D)
}}this.style[F]=D;
return this
},getStyle:function(I){switch(I){case"opacity":return this.get("opacity");
case"float":I=(Browser.Engine.trident)?"styleFloat":"cssFloat"
}I=I.camelCase();
var H=this.style[I];
if(!$chk(H)){H=[];
for(var J in Element.ShortStyles){if(I!=J){continue
}for(var K in Element.ShortStyles[J]){H.push(this.getStyle(K))
}return H.join(" ")
}H=this.getComputedStyle(I)
}if(H){H=String(H);
var M=H.match(/rgba?\([\d\s,]+\)/);
if(M){H=H.replace(M[0],M[0].rgbToHex())
}}if(Browser.Engine.presto||(Browser.Engine.trident&&!$chk(parseInt(H)))){if(I.test(/^(height|width)$/)){var N=(I=="width")?["left","right"]:["top","bottom"],L=0;
N.each(function(A){L+=this.getStyle("border-"+A+"-width").toInt()+this.getStyle("padding-"+A).toInt()
},this);
return this["offset"+I.capitalize()]-L+"px"
}if(Browser.Engine.presto&&String(H).test("px")){return H
}if(I.test(/(border(.+)Width|margin|padding)/)){return"0px"
}}return H
},setStyles:function(D){for(var C in D){this.setStyle(C,D[C])
}return this
},getStyles:function(){var B={};
Array.each(arguments,function(A){B[A]=this.getStyle(A)
},this);
return B
}});
Element.Styles=new Hash({left:"@px",top:"@px",bottom:"@px",right:"@px",width:"@px",height:"@px",maxWidth:"@px",maxHeight:"@px",minWidth:"@px",minHeight:"@px",backgroundColor:"rgb(@, @, @)",backgroundPosition:"@px @px",color:"rgb(@, @, @)",fontSize:"@px",letterSpacing:"@px",lineHeight:"@px",clip:"rect(@px @px @px @px)",margin:"@px @px @px @px",padding:"@px @px @px @px",border:"@px @ rgb(@, @, @) @px @ rgb(@, @, @) @px @ rgb(@, @, @)",borderWidth:"@px @px @px @px",borderStyle:"@ @ @ @",borderColor:"rgb(@, @, @) rgb(@, @, @) rgb(@, @, @) rgb(@, @, @)",zIndex:"@",zoom:"@",fontWeight:"@",textIndent:"@px",opacity:"@"});
Element.ShortStyles={margin:{},padding:{},border:{},borderWidth:{},borderStyle:{},borderColor:{}};
["Top","Right","Bottom","Left"].each(function(I){var J=Element.ShortStyles;
var N=Element.Styles;
["margin","padding"].each(function(B){var A=B+I;
J[B][A]=N[A]="@px"
});
var K="border"+I;
J.border[K]=N[K]="@px @ rgb(@, @, @)";
var L=K+"Width",H=K+"Style",M=K+"Color";
J[K]={};
J.borderWidth[L]=J[K][L]=N[L]="@px";
J.borderStyle[H]=J[K][H]=N[H]="@";
J.borderColor[M]=J[K][M]=N[M]="rgb(@, @, @)"
});
(function(){Element.implement({scrollTo:function(B,A){if(N(this)){this.getWindow().scrollTo(B,A)
}else{this.scrollLeft=B;
this.scrollTop=A
}return this
},getSize:function(){if(N(this)){return this.getWindow().getSize()
}return{x:this.offsetWidth,y:this.offsetHeight}
},getScrollSize:function(){if(N(this)){return this.getWindow().getScrollSize()
}return{x:this.scrollWidth,y:this.scrollHeight}
},getScroll:function(){if(N(this)){return this.getWindow().getScroll()
}return{x:this.scrollLeft,y:this.scrollTop}
},getScrolls:function(){var A=this,B={x:0,y:0};
while(A&&!N(A)){B.x+=A.scrollLeft;
B.y+=A.scrollTop;
A=A.parentNode
}return B
},getOffsetParent:function(){var A=this;
if(N(A)){return null
}if(!Browser.Engine.trident){return A.offsetParent
}while((A=A.parentNode)&&!N(A)){if(L(A,"position")!="static"){return A
}}return null
},getOffsets:function(){var B=this,C={x:0,y:0};
if(N(this)){return C
}while(B&&!N(B)){C.x+=B.offsetLeft;
C.y+=B.offsetTop;
if(Browser.Engine.gecko){if(!J(B)){C.x+=M(B);
C.y+=I(B)
}var A=B.parentNode;
if(A&&L(A,"overflow")!="visible"){C.x+=M(A);
C.y+=I(A)
}}else{if(B!=this&&(Browser.Engine.trident||Browser.Engine.webkit)){C.x+=M(B);
C.y+=I(B)
}}B=B.offsetParent;
if(Browser.Engine.trident){while(B&&!B.currentStyle.hasLayout){B=B.offsetParent
}}}if(Browser.Engine.gecko&&!J(this)){C.x-=M(this);
C.y-=I(this)
}return C
},getPosition:function(B){if(N(this)){return{x:0,y:0}
}var A=this.getOffsets(),D=this.getScrolls();
var E={x:A.x-D.x,y:A.y-D.y};
var C=(B&&(B=$(B)))?B.getPosition():{x:0,y:0};
return{x:E.x-C.x,y:E.y-C.y}
},getCoordinates:function(B){if(N(this)){return this.getWindow().getCoordinates()
}var D=this.getPosition(B),C=this.getSize();
var A={left:D.x,top:D.y,width:C.x,height:C.y};
A.right=A.left+A.width;
A.bottom=A.top+A.height;
return A
},computePosition:function(A){return{left:A.x-K(this,"margin-left"),top:A.y-K(this,"margin-top")}
},position:function(A){return this.setStyles(this.computePosition(A))
}});
Native.implement([Document,Window],{getSize:function(){var A=this.getWindow();
if(Browser.Engine.presto||Browser.Engine.webkit){return{x:A.innerWidth,y:A.innerHeight}
}var B=H(this);
return{x:B.clientWidth,y:B.clientHeight}
},getScroll:function(){var A=this.getWindow();
var B=H(this);
return{x:A.pageXOffset||B.scrollLeft,y:A.pageYOffset||B.scrollTop}
},getScrollSize:function(){var A=H(this);
var B=this.getSize();
return{x:Math.max(A.scrollWidth,B.x),y:Math.max(A.scrollHeight,B.y)}
},getPosition:function(){return{x:0,y:0}
},getCoordinates:function(){var A=this.getSize();
return{top:0,left:0,bottom:A.y,right:A.x,height:A.y,width:A.x}
}});
var L=Element.getComputedStyle;
function K(B,A){return L(B,A).toInt()||0
}function J(A){return L(A,"-moz-box-sizing")=="border-box"
}function I(A){return K(A,"border-top-width")
}function M(A){return K(A,"border-left-width")
}function N(A){return(/^(?:body|html)$/i).test(A.tagName)
}function H(B){var A=B.getDocument();
return(!A.compatMode||A.compatMode=="CSS1Compat")?A.html:A.body
}})();
Native.implement([Window,Document,Element],{getHeight:function(){return this.getSize().y
},getWidth:function(){return this.getSize().x
},getScrollTop:function(){return this.getScroll().y
},getScrollLeft:function(){return this.getScroll().x
},getScrollHeight:function(){return this.getScrollSize().y
},getScrollWidth:function(){return this.getScrollSize().x
},getTop:function(){return this.getPosition().y
},getLeft:function(){return this.getPosition().x
}});
Native.implement([Document,Element],{getElements:function(J,K){J=J.split(",");
var O,M={};
for(var N=0,P=J.length;
N<P;
N++){var I=J[N],L=Selectors.Utils.search(this,I,M);
if(N!=0&&L.item){L=$A(L)
}O=(N==0)?L:(O.item)?$A(O).concat(L):O.concat(L)
}return new Elements(O,{ddup:(J.length>1),cash:!K})
}});
Element.implement({match:function(J){if(!J){return true
}var H=Selectors.Utils.parseTagAndID(J);
var F=H[0],G=H[1];
if(!Selectors.Filters.byID(this,G)||!Selectors.Filters.byTag(this,F)){return false
}var I=Selectors.Utils.parseSelector(J);
return(I)?Selectors.Utils.filter(this,I,{}):true
}});
var Selectors={Cache:{nth:{},parsed:{}}};
Selectors.RegExps={id:(/#([\w-]+)/),tag:(/^(\w+|\*)/),quick:(/^(\w+|\*)$/),splitter:(/\s*([+>~\s])\s*([a-zA-Z#.*:\[])/g),combined:(/\.([\w-]+)|\[(\w+)(?:([!*^$~|]?=)["']?(.*?)["']?)?\]|:([\w-]+)(?:\(["']?(.*?)?["']?\)|$)/g)};
Selectors.Utils={chk:function(F,E){if(!E){return true
}var D=$uid(F);
if(!E[D]){return E[D]=true
}return false
},parseNthArgument:function(H){if(Selectors.Cache.nth[H]){return Selectors.Cache.nth[H]
}var K=H.match(/^([+-]?\d*)?([a-z]+)?([+-]?\d*)?$/);
if(!K){return false
}var I=parseInt(K[1]);
var L=(I||I===0)?I:1;
var J=K[2]||false;
var G=parseInt(K[3])||0;
if(L!=0){G--;
while(G<1){G+=L
}while(G>=L){G-=L
}}else{L=G;
J="index"
}switch(J){case"n":K={a:L,b:G,special:"n"};
break;
case"odd":K={a:2,b:0,special:"n"};
break;
case"even":K={a:2,b:1,special:"n"};
break;
case"first":K={a:0,special:"index"};
break;
case"last":K={special:"last-child"};
break;
case"only":K={special:"only-child"};
break;
default:K={a:(L-1),special:"index"}
}return Selectors.Cache.nth[H]=K
},parseSelector:function(T){if(Selectors.Cache.parsed[T]){return Selectors.Cache.parsed[T]
}var K,Q={classes:[],pseudos:[],attributes:[]};
while((K=Selectors.RegExps.combined.exec(T))){var P=K[1],R=K[2],S=K[3],M=K[4],L=K[5],O=K[6];
if(P){Q.classes.push(P)
}else{if(L){var N=Selectors.Pseudo.get(L);
if(N){Q.pseudos.push({parser:N,argument:O})
}else{Q.attributes.push({name:L,operator:"=",value:O})
}}else{if(R){Q.attributes.push({name:R,operator:S,value:M})
}}}}if(!Q.classes.length){delete Q.classes
}if(!Q.attributes.length){delete Q.attributes
}if(!Q.pseudos.length){delete Q.pseudos
}if(!Q.classes&&!Q.attributes&&!Q.pseudos){Q=null
}return Selectors.Cache.parsed[T]=Q
},parseTagAndID:function(F){var D=F.match(Selectors.RegExps.tag);
var E=F.match(Selectors.RegExps.id);
return[(D)?D[1]:"*",(E)?E[1]:false]
},filter:function(J,M,K){var L;
if(M.classes){for(L=M.classes.length;
L--;
L){var I=M.classes[L];
if(!Selectors.Filters.byClass(J,I)){return false
}}}if(M.attributes){for(L=M.attributes.length;
L--;
L){var N=M.attributes[L];
if(!Selectors.Filters.byAttribute(J,N.name,N.operator,N.value)){return false
}}}if(M.pseudos){for(L=M.pseudos.length;
L--;
L){var H=M.pseudos[L];
if(!Selectors.Filters.byPseudo(J,H.parser,H.argument,K)){return false
}}}return true
},getByTagAndID:function(H,E,F){if(F){var G=(H.getElementById)?H.getElementById(F,true):Element.getElementById(H,F,true);
return(G&&Selectors.Filters.byTag(G,E))?[G]:[]
}else{return H.getElementsByTagName(E)
}},search:function(k,l,f){var s=[];
var r=l.trim().replace(Selectors.RegExps.splitter,function(A,B,C){s.push(B);
return":)"+C
}).split(":)");
var j,o,p,Y;
for(var Z=0,d=r.length;
Z<d;
Z++){var a=r[Z];
if(Z==0&&Selectors.RegExps.quick.test(a)){j=k.getElementsByTagName(a);
continue
}var t=s[Z-1];
var i=Selectors.Utils.parseTagAndID(a);
var X=i[0],h=i[1];
if(Z==0){j=Selectors.Utils.getByTagAndID(k,X,h)
}else{var q={},m=[];
for(var b=0,c=j.length;
b<c;
b++){m=Selectors.Getters[t](m,j[b],X,h,q)
}j=m
}var n=Selectors.Utils.parseSelector(a);
if(n){p=[];
for(var e=0,g=j.length;
e<g;
e++){Y=j[e];
if(Selectors.Utils.filter(Y,n,f)){p.push(Y)
}}j=p
}}return j
}};
Selectors.Getters={" ":function(O,P,N,M,R){var J=Selectors.Utils.getByTagAndID(P,N,M);
for(var K=0,L=J.length;
K<L;
K++){var Q=J[K];
if(Selectors.Utils.chk(Q,R)){O.push(Q)
}}return O
},">":function(O,P,N,M,Q){var K=Selectors.Utils.getByTagAndID(P,N,M);
for(var R=0,J=K.length;
R<J;
R++){var L=K[R];
if(L.parentNode==P&&Selectors.Utils.chk(L,Q)){O.push(L)
}}return O
},"+":function(I,J,F,G,H){while((J=J.nextSibling)){if(J.nodeType==1){if(Selectors.Utils.chk(J,H)&&Selectors.Filters.byTag(J,F)&&Selectors.Filters.byID(J,G)){I.push(J)
}break
}}return I
},"~":function(I,J,F,G,H){while((J=J.nextSibling)){if(J.nodeType==1){if(!Selectors.Utils.chk(J,H)){break
}if(Selectors.Filters.byTag(J,F)&&Selectors.Filters.byID(J,G)){I.push(J)
}}}return I
}};
Selectors.Filters={byTag:function(D,C){return(C=="*"||(D.tagName&&D.tagName.toLowerCase()==C))
},byID:function(C,D){return(!D||(C.id&&C.id==D))
},byClass:function(D,C){return(D.className&&D.className.contains(C," "))
},byPseudo:function(E,F,G,H){return F.call(E,G,H)
},byAttribute:function(I,H,J,G){var F=Element.prototype.getProperty.call(I,H);
if(!F){return false
}if(!J||G==undefined){return true
}switch(J){case"=":return(F==G);
case"*=":return(F.contains(G));
case"^=":return(F.substr(0,G.length)==G);
case"$=":return(F.substr(F.length-G.length)==G);
case"!=":return(F!=G);
case"~=":return F.contains(G," ");
case"|=":return F.contains(G,"-")
}return false
}};
Selectors.Pseudo=new Hash({empty:function(){return !(this.innerText||this.textContent||"").length
},not:function(B){return !Element.match(this,B)
},contains:function(B){return(this.innerText||this.textContent||"").contains(B)
},"first-child":function(){return Selectors.Pseudo.index.call(this,0)
},"last-child":function(){var B=this;
while((B=B.nextSibling)){if(B.nodeType==1){return false
}}return true
},"only-child":function(){var D=this;
while((D=D.previousSibling)){if(D.nodeType==1){return false
}}var C=this;
while((C=C.nextSibling)){if(C.nodeType==1){return false
}}return true
},"nth-child":function(I,K){I=(I==undefined)?"n":I;
var M=Selectors.Utils.parseNthArgument(I);
if(M.special!="n"){return Selectors.Pseudo[M.special].call(this,M.a,K)
}var J=0;
K.positions=K.positions||{};
var L=$uid(this);
if(!K.positions[L]){var N=this;
while((N=N.previousSibling)){if(N.nodeType!=1){continue
}J++;
var H=K.positions[$uid(N)];
if(H!=undefined){J=H+J;
break
}}K.positions[L]=J
}return(K.positions[L]%M.a==M.b)
},index:function(D){var F=this,E=0;
while((F=F.previousSibling)){if(F.nodeType==1&&++E>D){return false
}}return(E==D)
},even:function(D,C){return Selectors.Pseudo["nth-child"].call(this,"2n+1",C)
},odd:function(D,C){return Selectors.Pseudo["nth-child"].call(this,"2n",C)
}});
Element.Events.domready={onAdd:function(B){if(Browser.loaded){B.call(this)
}}};
(function(){var D=function(){if(Browser.loaded){return 
}Browser.loaded=true;
window.fireEvent("domready");
document.fireEvent("domready")
};
switch(Browser.Engine.name){case"webkit":(function(){(["loaded","complete"].contains(document.readyState))?D():arguments.callee.delay(50)
})();
break;
case"trident":var C=document.createElement("div");
(function(){($try(function(){C.doScroll("left");
return $(C).inject(document.body).set("html","temp").dispose()
}))?D():arguments.callee.delay(50)
})();
break;
default:window.addEvent("load",D);
document.addEvent("DOMContentLoaded",D)
}})();
var JSON=new Hash({encode:function(D){switch($type(D)){case"string":return'"'+D.replace(/[\x00-\x1f\\"]/g,JSON.$replaceChars)+'"';
case"array":return"["+String(D.map(JSON.encode).filter($defined))+"]";
case"object":case"hash":var C=[];
Hash.each(D,function(A,B){var F=JSON.encode(A);
if(F){C.push(JSON.encode(B)+":"+F)
}});
return"{"+C+"}";
case"number":case"boolean":return String(D);
case false:return"null"
}return null
},$specialChars:{"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"},$replaceChars:function(B){return JSON.$specialChars[B]||"\\u00"+Math.floor(B.charCodeAt()/16).toString(16)+(B.charCodeAt()%16).toString(16)
},decode:function(string,secure){if($type(string)!="string"||!string.length){return null
}if(secure&&!(/^[,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t]*$/).test(string.replace(/\\./g,"@").replace(/"[^"\\\n\r]*"/g,""))){return null
}return eval("("+string+")")
}});
Native.implement([Hash,Array,String,Number],{toJSON:function(){return JSON.encode(this)
}});
var Cookie=new Class({Implements:Options,options:{path:false,domain:false,duration:false,secure:false,document:document},initialize:function(D,C){this.key=D;
this.setOptions(C)
},write:function(D){D=encodeURIComponent(D);
if(this.options.domain){D+="; domain="+this.options.domain
}if(this.options.path){D+="; path="+this.options.path
}if(this.options.duration){var C=new Date();
C.setTime(C.getTime()+this.options.duration*24*60*60*1000);
D+="; expires="+C.toGMTString()
}if(this.options.secure){D+="; secure"
}this.options.document.cookie=this.key+"="+D;
return this
},read:function(){var B=this.options.document.cookie.match("(?:^|;)\\s*"+this.key.escapeRegExp()+"=([^;]*)");
return(B)?decodeURIComponent(B[1]):null
},dispose:function(){new Cookie(this.key,$merge(this.options,{duration:-1})).write("");
return this
}});
Cookie.write=function(F,E,D){return new Cookie(F,D).write(E)
};
Cookie.read=function(B){return new Cookie(B).read()
};
Cookie.dispose=function(D,C){return new Cookie(D,C).dispose()
};
var Swiff=new Class({Implements:[Options],options:{id:null,height:1,width:1,container:null,properties:{},params:{quality:"high",allowScriptAccess:"always",wMode:"transparent",swLiveConnect:true},callBacks:{},vars:{}},toElement:function(){return this.object
},initialize:function(S,R){this.instance="Swiff_"+$time();
this.setOptions(R);
R=this.options;
var P=this.id=R.id||this.instance;
var Q=$(R.container);
Swiff.CallBacks[this.instance]={};
var Z=R.params,X=R.vars,Y=R.callBacks;
var W=$extend({height:R.height,width:R.width},R.properties);
var T=this;
for(var N in Y){Swiff.CallBacks[this.instance][N]=(function(A){return function(){return A.apply(T.object,arguments)
}
})(Y[N]);
X[N]="Swiff.CallBacks."+this.instance+"."+N
}Z.flashVars=Hash.toQueryString(X);
if(Browser.Engine.trident){W.classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000";
Z.movie=S
}else{W.type="application/x-shockwave-flash";
W.data=S
}var U='<object id="'+P+'"';
for(var V in W){U+=" "+V+'="'+W[V]+'"'
}U+=">";
for(var O in Z){if(Z[O]){U+='<param name="'+O+'" value="'+Z[O]+'" />'
}}U+="</object>";
this.object=((Q)?Q.empty():new Element("div")).set("html",U).firstChild
},replaces:function(B){B=$(B,true);
B.parentNode.replaceChild(this.toElement(),B);
return this
},inject:function(B){$(B,true).appendChild(this.toElement());
return this
},remote:function(){return Swiff.remote.apply(Swiff,[this.toElement()].extend(arguments))
}});
Swiff.CallBacks={};
Swiff.remote=function(obj,fn){var rs=obj.CallFunction('<invoke name="'+fn+'" returntype="javascript">'+__flash__argumentsToXML(arguments,2)+"</invoke>");
return eval(rs)
};
var Fx=new Class({Implements:[Chain,Events,Options],options:{fps:50,unit:false,duration:500,link:"ignore",transition:function(B){return -(Math.cos(Math.PI*B)-1)/2
}},initialize:function(C){this.subject=this.subject||this;
this.setOptions(C);
this.options.duration=Fx.Durations[this.options.duration]||this.options.duration.toInt();
var D=this.options.wait;
if(D===false){this.options.link="cancel"
}},step:function(){var C=$time();
if(C<this.time+this.options.duration){var D=this.options.transition((C-this.time)/this.options.duration);
this.set(this.compute(this.from,this.to,D))
}else{this.set(this.compute(this.from,this.to,1));
this.complete()
}},set:function(B){return B
},compute:function(E,F,D){return Fx.compute(E,F,D)
},check:function(B){if(!this.timer){return true
}switch(this.options.link){case"cancel":this.cancel();
return true;
case"chain":this.chain(B.bind(this,Array.slice(arguments,1)));
return false
}return false
},start:function(D,C){if(!this.check(arguments.callee,D,C)){return this
}this.from=D;
this.to=C;
this.time=0;
this.startTimer();
this.onStart();
return this
},complete:function(){if(this.stopTimer()){this.onComplete()
}return this
},cancel:function(){if(this.stopTimer()){this.onCancel()
}return this
},onStart:function(){this.fireEvent("start",this.subject)
},onComplete:function(){this.fireEvent("complete",this.subject);
if(!this.callChain()){this.fireEvent("chainComplete",this.subject)
}},onCancel:function(){this.fireEvent("cancel",this.subject).clearChain()
},pause:function(){this.stopTimer();
return this
},resume:function(){this.startTimer();
return this
},stopTimer:function(){if(!this.timer){return false
}this.time=$time()-this.time;
this.timer=$clear(this.timer);
return true
},startTimer:function(){if(this.timer){return false
}this.time=$time()-this.time;
this.timer=this.step.periodical(Math.round(1000/this.options.fps),this);
return true
}});
Fx.compute=function(E,F,D){return(F-E)*D+E
};
Fx.Durations={"short":250,normal:500,"long":1000};
Fx.CSS=new Class({Extends:Fx,prepare:function(H,G,J){J=$splat(J);
var I=J[1];
if(!$chk(I)){J[1]=J[0];
J[0]=H.getStyle(G)
}var F=J.map(this.parse);
return{from:F[0],to:F[1]}
},parse:function(B){B=$lambda(B)();
B=(typeof B=="string")?B.split(" "):$splat(B);
return B.map(function(A){A=String(A);
var D=false;
Fx.CSS.Parsers.each(function(C,G){if(D){return 
}var H=C.parse(A);
if($chk(H)){D={value:H,parser:C}
}});
D=D||{value:A,parser:Fx.CSS.Parsers.String};
return D
})
},compute:function(F,G,H){var E=[];
(Math.min(F.length,G.length)).times(function(A){E.push({value:F[A].parser.compute(F[A].value,G[A].value,H),parser:F[A].parser})
});
E.$family={name:"fx:css:value"};
return E
},serve:function(E,F){if($type(E)!="fx:css:value"){E=this.parse(E)
}var D=[];
E.each(function(A){D=D.concat(A.parser.serve(A.value,F))
});
return D
},render:function(E,F,G,H){E.setStyle(F,this.serve(G,H))
},search:function(C){if(Fx.CSS.Cache[C]){return Fx.CSS.Cache[C]
}var D={};
Array.each(document.styleSheets,function(B,G){var H=B.href;
if(H&&H.contains("://")&&!H.contains(document.domain)){return 
}var A=B.rules||B.cssRules;
Array.each(A,function(E,J){if(!E.style){return 
}var F=(E.selectorText)?E.selectorText.replace(/^\w+/,function(I){return I.toLowerCase()
}):null;
if(!F||!F.test("^"+C+"$")){return 
}Element.Styles.each(function(I,L){if(!E.style[L]||Element.ShortStyles[L]){return 
}I=String(E.style[L]);
D[L]=(I.test(/^rgb/))?I.rgbToHex():I
})
})
});
return Fx.CSS.Cache[C]=D
}});
Fx.CSS.Cache={};
Fx.CSS.Parsers=new Hash({Color:{parse:function(B){if(B.match(/^#[0-9a-f]{3,6}$/i)){return B.hexToRgb(true)
}return((B=B.match(/(\d+),\s*(\d+),\s*(\d+)/)))?[B[1],B[2],B[3]]:false
},compute:function(E,F,D){return E.map(function(A,B){return Math.round(Fx.compute(E[B],F[B],D))
})
},serve:function(B){return B.map(Number)
}},Number:{parse:parseFloat,compute:Fx.compute,serve:function(D,C){return(C)?D+C:D
}},String:{parse:$lambda(false),compute:$arguments(1),serve:$arguments(0)}});
Fx.Tween=new Class({Extends:Fx.CSS,initialize:function(D,C){this.element=this.subject=$(D);
this.parent(C)
},set:function(D,C){if(arguments.length==1){C=D;
D=this.property||this.options.property
}this.render(this.element,D,C,this.options.unit);
return this
},start:function(I,G,H){if(!this.check(arguments.callee,I,G,H)){return this
}var J=Array.flatten(arguments);
this.property=this.options.property||J.shift();
var F=this.prepare(this.element,this.property,J);
return this.parent(F.from,F.to)
}});
Element.Properties.tween={set:function(C){var D=this.retrieve("tween");
if(D){D.cancel()
}return this.eliminate("tween").store("tween:options",$extend({link:"cancel"},C))
},get:function(B){if(B||!this.retrieve("tween")){if(B||!this.retrieve("tween:options")){this.set("tween",B)
}this.store("tween",new Fx.Tween(this,this.retrieve("tween:options")))
}return this.retrieve("tween")
}};
Element.implement({tween:function(D,E,F){this.get("tween").start(arguments);
return this
},fade:function(I){var G=this.get("tween"),H="opacity",F;
I=$pick(I,"toggle");
switch(I){case"in":G.start(H,1);
break;
case"out":G.start(H,0);
break;
case"show":G.set(H,1);
break;
case"hide":G.set(H,0);
break;
case"toggle":var J=this.retrieve("fade:flag",this.get("opacity")==1);
G.start(H,(J)?0:1);
this.store("fade:flag",!J);
F=true;
break;
default:G.start(H,arguments)
}if(!F){this.eliminate("fade:flag")
}return this
},highlight:function(E,D){if(!D){D=this.retrieve("highlight:original",this.getStyle("background-color"));
D=(D=="transparent")?"#fff":D
}var F=this.get("tween");
F.start("background-color",E||"#ffff88",D).chain(function(){this.setStyle("background-color",this.retrieve("highlight:original"));
F.callChain()
}.bind(this));
return this
}});
Fx.Morph=new Class({Extends:Fx.CSS,initialize:function(D,C){this.element=this.subject=$(D);
this.parent(C)
},set:function(C){if(typeof C=="string"){C=this.search(C)
}for(var D in C){this.render(this.element,D,C[D],this.options.unit)
}return this
},compute:function(G,H,I){var F={};
for(var J in G){F[J]=this.parent(G[J],H[J],I)
}return F
},start:function(J){if(!this.check(arguments.callee,J)){return this
}if(typeof J=="string"){J=this.search(J)
}var G={},H={};
for(var I in J){var F=this.prepare(this.element,I,J[I]);
G[I]=F.from;
H[I]=F.to
}return this.parent(G,H)
}});
Element.Properties.morph={set:function(C){var D=this.retrieve("morph");
if(D){D.cancel()
}return this.eliminate("morph").store("morph:options",$extend({link:"cancel"},C))
},get:function(B){if(B||!this.retrieve("morph")){if(B||!this.retrieve("morph:options")){this.set("morph",B)
}this.store("morph",new Fx.Morph(this,this.retrieve("morph:options")))
}return this.retrieve("morph")
}};
Element.implement({morph:function(B){this.get("morph").start(B);
return this
}});
(function(){var B=Fx.prototype.initialize;
Fx.prototype.initialize=function(F){B.call(this,F);
var E=this.options.transition;
if(typeof E=="string"&&(E=E.split(":"))){var A=Fx.Transitions;
A=A[E[0]]||A[E[0].capitalize()];
if(E[1]){A=A["ease"+E[1].capitalize()+(E[2]?E[2].capitalize():"")]
}this.options.transition=A
}}
})();
Fx.Transition=function(D,C){C=$splat(C);
return $extend(D,{easeIn:function(A){return D(A,C)
},easeOut:function(A){return 1-D(1-A,C)
},easeInOut:function(A){return(A<=0.5)?D(2*A,C)/2:(2-D(2*(1-A),C))/2
}})
};
Fx.Transitions=new Hash({linear:$arguments(0)});
Fx.Transitions.extend=function(C){for(var D in C){Fx.Transitions[D]=new Fx.Transition(C[D])
}};
Fx.Transitions.extend({Pow:function(D,C){return Math.pow(D,C[0]||6)
},Expo:function(B){return Math.pow(2,8*(B-1))
},Circ:function(B){return 1-Math.sin(Math.acos(B))
},Sine:function(B){return 1-Math.sin((1-B)*Math.PI/2)
},Back:function(D,C){C=C[0]||1.618;
return Math.pow(D,2)*((C+1)*D-C)
},Bounce:function(F){var G;
for(var H=0,E=1;
1;
H+=E,E/=2){if(F>=(7-4*H)/11){G=-Math.pow((11-6*H-11*F)/4,2)+E*E;
break
}}return G
},Elastic:function(D,C){return Math.pow(2,10*--D)*Math.cos(20*D*Math.PI*(C[0]||1)/3)
}});
["Quad","Cubic","Quart","Quint"].each(function(D,C){Fx.Transitions[D]=new Fx.Transition(function(A){return Math.pow(A,[C+2])
})
});
var Request=new Class({Implements:[Chain,Events,Options],options:{url:"",data:"",headers:{"X-Requested-With":"XMLHttpRequest",Accept:"text/javascript, text/html, application/xml, text/xml, */*"},async:true,format:false,method:"post",link:"ignore",isSuccess:null,emulation:true,urlEncoded:true,encoding:"utf-8",evalScripts:false,evalResponse:false},initialize:function(B){this.xhr=new Browser.Request();
this.setOptions(B);
this.options.isSuccess=this.options.isSuccess||this.isSuccess;
this.headers=new Hash(this.options.headers)
},onStateChange:function(){if(this.xhr.readyState!=4||!this.running){return 
}this.running=false;
this.status=0;
$try(function(){this.status=this.xhr.status
}.bind(this));
if(this.options.isSuccess.call(this,this.status)){this.response={text:this.xhr.responseText,xml:this.xhr.responseXML};
this.success(this.response.text,this.response.xml)
}else{this.response={text:null,xml:null};
this.failure()
}this.xhr.onreadystatechange=$empty
},isSuccess:function(){return((this.status>=200)&&(this.status<300))
},processScripts:function(B){if(this.options.evalResponse||(/(ecma|java)script/).test(this.getHeader("Content-type"))){return $exec(B)
}return B.stripScripts(this.options.evalScripts)
},success:function(D,C){this.onSuccess(this.processScripts(D),C)
},onSuccess:function(){this.fireEvent("complete",arguments).fireEvent("success",arguments).callChain()
},failure:function(){this.onFailure()
},onFailure:function(){this.fireEvent("complete").fireEvent("failure",this.xhr)
},setHeader:function(C,D){this.headers.set(C,D);
return this
},getHeader:function(B){return $try(function(){return this.xhr.getResponseHeader(B)
}.bind(this))
},check:function(B){if(!this.running){return true
}switch(this.options.link){case"cancel":this.cancel();
return true;
case"chain":this.chain(B.bind(this,Array.slice(arguments,1)));
return false
}return false
},send:function(N){if(!this.check(arguments.callee,N)){return this
}this.running=true;
var P=$type(N);
if(P=="string"||P=="element"){N={data:N}
}var J=this.options;
N=$extend({data:J.data,url:J.url,method:J.method},N);
var R=N.data,L=N.url,M=N.method;
switch($type(R)){case"element":R=$(R).toQueryString();
break;
case"object":case"hash":R=Hash.toQueryString(R)
}if(this.options.format){var O="format="+this.options.format;
R=(R)?O+"&"+R:O
}if(this.options.emulation&&["put","delete"].contains(M)){var Q="_method="+M;
R=(R)?Q+"&"+R:Q;
M="post"
}if(this.options.urlEncoded&&M=="post"){var K=(this.options.encoding)?"; charset="+this.options.encoding:"";
this.headers.set("Content-type","application/x-www-form-urlencoded"+K)
}if(R&&M=="get"){L=L+(L.contains("?")?"&":"?")+R;
R=null
}this.xhr.open(M.toUpperCase(),L,this.options.async);
this.xhr.onreadystatechange=this.onStateChange.bind(this);
this.headers.each(function(A,B){if(!$try(function(){this.xhr.setRequestHeader(B,A);
return true
}.bind(this))){this.fireEvent("exception",[B,A])
}},this);
this.fireEvent("request");
this.xhr.send(R);
if(!this.options.async){this.onStateChange()
}return this
},cancel:function(){if(!this.running){return this
}this.running=false;
this.xhr.abort();
this.xhr.onreadystatechange=$empty;
this.xhr=new Browser.Request();
this.fireEvent("cancel");
return this
}});
(function(){var B={};
["get","post","put","delete","GET","POST","PUT","DELETE"].each(function(A){B[A]=function(){var D=Array.link(arguments,{url:String.type,data:$defined});
return this.send($extend(D,{method:A.toLowerCase()}))
}
});
Request.implement(B)
})();
Element.Properties.send={set:function(C){var D=this.retrieve("send");
if(D){D.cancel()
}return this.eliminate("send").store("send:options",$extend({data:this,link:"cancel",method:this.get("method")||"post",url:this.get("action")},C))
},get:function(B){if(B||!this.retrieve("send")){if(B||!this.retrieve("send:options")){this.set("send",B)
}this.store("send",new Request(this.retrieve("send:options")))
}return this.retrieve("send")
}};
Element.implement({send:function(C){var D=this.get("send");
D.send({data:this,url:C||D.options.url});
return this
}});
Request.HTML=new Class({Extends:Request,options:{update:false,evalScripts:true,filter:false},processHTML:function(E){var F=E.match(/<body[^>]*>([\s\S]*?)<\/body>/i);
E=(F)?F[1]:E;
var D=new Element("div");
return $try(function(){var J="<root>"+E+"</root>",B;
if(Browser.Engine.trident){B=new ActiveXObject("Microsoft.XMLDOM");
B.async=false;
B.loadXML(J)
}else{B=new DOMParser().parseFromString(J,"text/xml")
}J=B.getElementsByTagName("root")[0];
for(var C=0,I=J.childNodes.length;
C<I;
C++){var A=Element.clone(J.childNodes[C],true,true);
if(A){D.grab(A)
}}return D
})||D.set("html",E)
},success:function(F){var G=this.options,H=this.response;
H.html=F.stripScripts(function(A){H.javascript=A
});
var E=this.processHTML(H.html);
H.tree=E.childNodes;
H.elements=E.getElements("*");
if(G.filter){H.tree=H.elements.filter(G.filter)
}if(G.update){$(G.update).empty().adopt(H.tree)
}if(G.evalScripts){$exec(H.javascript)
}this.onSuccess(H.tree,H.elements,H.html,H.javascript)
}});
Element.Properties.load={set:function(C){var D=this.retrieve("load");
if(D){send.cancel()
}return this.eliminate("load").store("load:options",$extend({data:this,link:"cancel",update:this,method:"get"},C))
},get:function(B){if(B||!this.retrieve("load")){if(B||!this.retrieve("load:options")){this.set("load",B)
}this.store("load",new Request.HTML(this.retrieve("load:options")))
}return this.retrieve("load")
}};
Element.implement({load:function(){this.get("load").send(Array.link(arguments,{data:Object.type,url:String.type}));
return this
}});
Request.JSON=new Class({Extends:Request,options:{secure:true},initialize:function(B){this.parent(B);
this.headers.extend({Accept:"application/json","X-Request":"JSON"})
},success:function(B){this.response.json=JSON.decode(B,this.options.secure);
this.onSuccess(this.response.json,B)
}});Fx.Slide=new Class({Extends:Fx,options:{mode:"vertical"},initialize:function(F,D){this.addEvent("complete",function(){this.open=(this.wrapper["offset"+this.layout.capitalize()]!=0);
if(this.open&&Browser.Engine.webkit419){this.element.dispose().inject(this.wrapper)
}},true);
this.element=this.subject=$(F);
this.parent(D);
var E=this.element.retrieve("wrapper");
this.wrapper=E||new Element("div",{styles:$extend(this.element.getStyles("margin","position"),{overflow:"hidden"})}).wraps(this.element);
this.element.store("wrapper",this.wrapper).setStyle("margin",0);
this.now=[];
this.open=true
},vertical:function(){this.margin="margin-top";
this.layout="height";
this.offset=this.element.offsetHeight
},horizontal:function(){this.margin="margin-left";
this.layout="width";
this.offset=this.element.offsetWidth
},set:function(B){this.element.setStyle(this.margin,B[0]);
this.wrapper.setStyle(this.layout,B[1]);
return this
},compute:function(G,H,I){var J=[];
var F=2;
F.times(function(A){J[A]=Fx.compute(G[A],H[A],I)
});
return J
},start:function(N,K){if(!this.check(arguments.callee,N,K)){return this
}this[K||this.options.mode]();
var L=this.element.getStyle(this.margin).toInt();
var M=this.wrapper.getStyle(this.layout).toInt();
var H=[[L,M],[0,this.offset]];
var I=[[L,M],[-this.offset,0]];
var J;
switch(N){case"in":J=H;
break;
case"out":J=I;
break;
case"toggle":J=(this.wrapper["offset"+this.layout.capitalize()]==0)?H:I
}return this.parent(J[0],J[1])
},slideIn:function(B){return this.start("in",B)
},slideOut:function(B){return this.start("out",B)
},hide:function(B){this[B||this.options.mode]();
this.open=false;
return this.set([-this.offset,0])
},show:function(B){this[B||this.options.mode]();
this.open=true;
return this.set([0,this.offset])
},toggle:function(B){return this.start("toggle",B)
}});
Element.Properties.slide={set:function(D){var C=this.retrieve("slide");
if(C){C.cancel()
}return this.eliminate("slide").store("slide:options",$extend({link:"cancel"},D))
},get:function(B){if(B||!this.retrieve("slide")){if(B||!this.retrieve("slide:options")){this.set("slide",B)
}this.store("slide",new Fx.Slide(this,this.retrieve("slide:options")))
}return this.retrieve("slide")
}};
Element.implement({slide:function(H,G){H=H||"toggle";
var J=this.get("slide"),F;
switch(H){case"hide":J.hide(G);
break;
case"show":J.show(G);
break;
case"toggle":var I=this.retrieve("slide:flag",J.open);
J[(I)?"slideOut":"slideIn"](G);
this.store("slide:flag",!I);
F=true;
break;
default:J.start(H,G)
}if(!F){this.eliminate("slide:flag")
}return this
}});
Fx.Scroll=new Class({Extends:Fx,options:{offset:{x:0,y:0},wheelStops:true},initialize:function(H,E){this.element=this.subject=$(H);
this.parent(E);
var F=this.cancel.bind(this,false);
if($type(this.element)!="element"){this.element=$(this.element.getDocument().body)
}var G=this.element;
if(this.options.wheelStops){this.addEvent("start",function(){G.addEvent("mousewheel",F)
},true);
this.addEvent("complete",function(){G.removeEvent("mousewheel",F)
},true)
}},set:function(){var B=Array.flatten(arguments);
this.element.scrollTo(B[0],B[1])
},compute:function(G,H,I){var J=[];
var F=2;
F.times(function(A){J.push(Fx.compute(G[A],H[A],I))
});
return J
},start:function(O,J){if(!this.check(arguments.callee,O,J)){return this
}var M=this.element.getSize(),L=this.element.getScrollSize();
var P=this.element.getScroll(),N={x:O,y:J};
for(var K in N){var I=L[K]-M[K];
if($chk(N[K])){N[K]=($type(N[K])=="number")?N[K].limit(0,I):I
}else{N[K]=P[K]
}N[K]+=this.options.offset[K]
}return this.parent([P.x,P.y],[N.x,N.y])
},toTop:function(){return this.start(false,0)
},toLeft:function(){return this.start(0,false)
},toRight:function(){return this.start("right",false)
},toBottom:function(){return this.start(false,"bottom")
},toElement:function(D){var C=$(D).getPosition(this.element);
return this.start(C.x,C.y)
}});
Fx.Elements=new Class({Extends:Fx.CSS,initialize:function(D,C){this.elements=this.subject=$$(D);
this.parent(C)
},compute:function(P,O,N){var K={};
for(var J in P){var M=P[J],R=O[J],Q=K[J]={};
for(var L in M){Q[L]=this.parent(M[L],R[L],N)
}}return K
},set:function(H){for(var G in H){var E=H[G];
for(var F in E){this.render(this.elements[G],F,E[F],this.options.unit)
}}return this
},start:function(K){if(!this.check(arguments.callee,K)){return this
}var O={},N={};
for(var J in K){var Q=K[J],M=O[J]={},P=N[J]={};
for(var L in Q){var R=this.prepare(this.elements[J],L,Q[L]);
M[L]=R.from;
P[L]=R.to
}}return this.parent(O,N)
}});
var Drag=new Class({Implements:[Events,Options],options:{snap:6,unit:"px",grid:false,style:true,limit:false,handle:false,invert:false,preventDefault:false,modifiers:{x:"left",y:"top"}},initialize:function(){var D=Array.link(arguments,{options:Object.type,element:$defined});
this.element=$(D.element);
this.document=this.element.getDocument();
this.setOptions(D.options||{});
var C=$type(this.options.handle);
this.handles=(C=="array"||C=="collection")?$$(this.options.handle):$(this.options.handle)||this.element;
this.mouse={now:{},pos:{}};
this.value={start:{},now:{}};
this.selection=(Browser.Engine.trident)?"selectstart":"mousedown";
this.bound={start:this.start.bind(this),check:this.check.bind(this),drag:this.drag.bind(this),stop:this.stop.bind(this),cancel:this.cancel.bind(this),eventStop:$lambda(false)};
this.attach()
},attach:function(){this.handles.addEvent("mousedown",this.bound.start);
return this
},detach:function(){this.handles.removeEvent("mousedown",this.bound.start);
return this
},start:function(G){if(this.options.preventDefault){G.preventDefault()
}this.fireEvent("beforeStart",this.element);
this.mouse.start=G.page;
var E=this.options.limit;
this.limit={x:[],y:[]};
for(var F in this.options.modifiers){if(!this.options.modifiers[F]){continue
}if(this.options.style){this.value.now[F]=this.element.getStyle(this.options.modifiers[F]).toInt()
}else{this.value.now[F]=this.element[this.options.modifiers[F]]
}if(this.options.invert){this.value.now[F]*=-1
}this.mouse.pos[F]=G.page[F]-this.value.now[F];
if(E&&E[F]){for(var H=2;
H--;
H){if($chk(E[F][H])){this.limit[F][H]=$lambda(E[F][H])()
}}}}if($type(this.options.grid)=="number"){this.options.grid={x:this.options.grid,y:this.options.grid}
}this.document.addEvents({mousemove:this.bound.check,mouseup:this.bound.cancel});
this.document.addEvent(this.selection,this.bound.eventStop)
},check:function(C){if(this.options.preventDefault){C.preventDefault()
}var D=Math.round(Math.sqrt(Math.pow(C.page.x-this.mouse.start.x,2)+Math.pow(C.page.y-this.mouse.start.y,2)));
if(D>this.options.snap){this.cancel();
this.document.addEvents({mousemove:this.bound.drag,mouseup:this.bound.stop});
this.fireEvent("start",this.element).fireEvent("snap",this.element)
}},drag:function(C){if(this.options.preventDefault){C.preventDefault()
}this.mouse.now=C.page;
for(var D in this.options.modifiers){if(!this.options.modifiers[D]){continue
}this.value.now[D]=this.mouse.now[D]-this.mouse.pos[D];
if(this.options.invert){this.value.now[D]*=-1
}if(this.options.limit&&this.limit[D]){if($chk(this.limit[D][1])&&(this.value.now[D]>this.limit[D][1])){this.value.now[D]=this.limit[D][1]
}else{if($chk(this.limit[D][0])&&(this.value.now[D]<this.limit[D][0])){this.value.now[D]=this.limit[D][0]
}}}if(this.options.grid[D]){this.value.now[D]-=(this.value.now[D]%this.options.grid[D])
}if(this.options.style){this.element.setStyle(this.options.modifiers[D],this.value.now[D]+this.options.unit)
}else{this.element[this.options.modifiers[D]]=this.value.now[D]
}}this.fireEvent("drag",this.element)
},cancel:function(B){this.document.removeEvent("mousemove",this.bound.check);
this.document.removeEvent("mouseup",this.bound.cancel);
if(B){this.document.removeEvent(this.selection,this.bound.eventStop);
this.fireEvent("cancel",this.element)
}},stop:function(B){this.document.removeEvent(this.selection,this.bound.eventStop);
this.document.removeEvent("mousemove",this.bound.drag);
this.document.removeEvent("mouseup",this.bound.stop);
if(B){this.fireEvent("complete",this.element)
}}});
Element.implement({makeResizable:function(B){return new Drag(this,$merge({modifiers:{x:"width",y:"height"}},B))
}});
Drag.Move=new Class({Extends:Drag,options:{droppables:[],container:false},initialize:function(G,H){this.parent(G,H);
this.droppables=$$(this.options.droppables);
this.container=$(this.options.container);
if(this.container&&$type(this.container)!="element"){this.container=$(this.container.getDocument().body)
}G=this.element;
var F=G.getStyle("position");
var E=(F!="static")?F:"absolute";
if(G.getStyle("left")=="auto"||G.getStyle("top")=="auto"){G.position(G.getPosition(G.offsetParent))
}G.setStyle("position",E);
this.addEvent("start",function(){this.checkDroppables()
},true)
},start:function(M){if(this.container){var K=this.element,O=this.container,T=O.getCoordinates(K.offsetParent),S={},N={};
["top","right","bottom","left"].each(function(A){S[A]=O.getStyle("padding-"+A).toInt();
N[A]=K.getStyle("margin-"+A).toInt()
},this);
var L=K.offsetWidth+N.left+N.right,P=K.offsetHeight+N.top+N.bottom;
var Q=[T.left+S.left,T.right-S.right-L];
var R=[T.top+S.top,T.bottom-S.bottom-P];
this.options.limit={x:Q,y:R}
}this.parent(M)
},checkAgainst:function(D){D=D.getCoordinates();
var C=this.mouse.now;
return(C.x>D.left&&C.x<D.right&&C.y<D.bottom&&C.y>D.top)
},checkDroppables:function(){var B=this.droppables.filter(this.checkAgainst,this).getLast();
if(this.overed!=B){if(this.overed){this.fireEvent("leave",[this.element,this.overed])
}if(B){this.overed=B;
this.fireEvent("enter",[this.element,B])
}else{this.overed=null
}}},drag:function(B){this.parent(B);
if(this.droppables.length){this.checkDroppables()
}},stop:function(B){this.checkDroppables();
this.fireEvent("drop",[this.element,this.overed]);
this.overed=null;
return this.parent(B)
}});
Element.implement({makeDraggable:function(B){return new Drag.Move(this,B)
}});
Hash.Cookie=new Class({Extends:Cookie,options:{autoSave:true},initialize:function(D,C){this.parent(D,C);
this.load()
},save:function(){var B=JSON.encode(this.hash);
if(!B||B.length>4096){return false
}if(B=="{}"){this.dispose()
}else{this.write(B)
}return true
},load:function(){this.hash=new Hash(JSON.decode(this.read(),true));
return this
}});
Hash.Cookie.implement((function(){var B={};
Hash.each(Hash.prototype,function(A,D){B[D]=function(){var C=A.apply(this.hash,arguments);
if(this.options.autoSave){this.save()
}return C
}
});
return B
})());
var Color=new Native({initialize:function(F,E){if(arguments.length>=3){E="rgb";
F=Array.slice(arguments,0,3)
}else{if(typeof F=="string"){if(F.match(/rgb/)){F=F.rgbToHex().hexToRgb(true)
}else{if(F.match(/hsb/)){F=F.hsbToRgb()
}else{F=F.hexToRgb(true)
}}}}E=E||"rgb";
switch(E){case"hsb":var D=F;
F=F.hsbToRgb();
F.hsb=D;
break;
case"hex":F=F.hexToRgb(true);
break
}F.rgb=F.slice(0,3);
F.hsb=F.hsb||F.rgbToHsb();
F.hex=F.rgbToHex();
return $extend(F,this)
}});
Color.implement({mix:function(){var D=Array.slice(arguments);
var E=($type(D.getLast())=="number")?D.pop():50;
var F=this.slice();
D.each(function(B){B=new Color(B);
for(var A=0;
A<3;
A++){F[A]=Math.round((F[A]/100*(100-E))+(B[A]/100*E))
}});
return new Color(F,"rgb")
},invert:function(){return new Color(this.map(function(B){return 255-B
}))
},setHue:function(B){return new Color([B,this.hsb[1],this.hsb[2]],"hsb")
},setSaturation:function(B){return new Color([this.hsb[0],B,this.hsb[2]],"hsb")
},setBrightness:function(B){return new Color([this.hsb[0],this.hsb[1],B],"hsb")
}});
function $RGB(E,F,D){return new Color([E,F,D],"rgb")
}function $HSB(E,F,D){return new Color([E,F,D],"hsb")
}function $HEX(B){return new Color(B,"hex")
}Array.implement({rgbToHsb:function(){var O=this[0],N=this[1],S=this[2];
var V,W,U;
var T=Math.max(O,N,S),X=Math.min(O,N,S);
var R=T-X;
U=T/255;
W=(T!=0)?R/T:0;
if(W==0){V=0
}else{var M=(T-O)/R;
var P=(T-N)/R;
var Q=(T-S)/R;
if(O==T){V=Q-P
}else{if(N==T){V=2+M-Q
}else{V=4+P-M
}}V/=6;
if(V<0){V++
}}return[Math.round(V*360),Math.round(W*100),Math.round(U*100)]
},hsbToRgb:function(){var K=Math.round(this[2]/100*255);
if(this[1]==0){return[K,K,K]
}else{var G=this[0]%360;
var I=G%60;
var H=Math.round((this[2]*(100-this[1]))/10000*255);
var J=Math.round((this[2]*(6000-this[1]*I))/600000*255);
var L=Math.round((this[2]*(6000-this[1]*(60-I)))/600000*255);
switch(Math.floor(G/60)){case 0:return[K,L,H];
case 1:return[J,K,H];
case 2:return[H,K,L];
case 3:return[H,J,K];
case 4:return[L,H,K];
case 5:return[K,H,J]
}}return false
}});
String.implement({rgbToHsb:function(){var B=this.match(/\d{1,3}/g);
return(B)?hsb.rgbToHsb():null
},hsbToRgb:function(){var B=this.match(/\d{1,3}/g);
return(B)?B.hsbToRgb():null
}});
var Group=new Class({initialize:function(){this.instances=Array.flatten(arguments);
this.events={};
this.checker={}
},addEvent:function(D,C){this.checker[D]=this.checker[D]||{};
this.events[D]=this.events[D]||[];
if(this.events[D].contains(C)){return false
}else{this.events[D].push(C)
}this.instances.each(function(B,A){B.addEvent(D,this.check.bind(this,[D,B,A]))
},this);
return this
},check:function(G,E,H){this.checker[G][H]=true;
var F=this.instances.every(function(A,B){return this.checker[G][B]||false
},this);
if(!F){return 
}this.checker[G]={};
this.events[G].each(function(A){A.call(this,this.instances,E)
},this)
}});
var Asset=new Hash({javascript:function(J,L){L=$extend({onload:$empty,document:document,check:$lambda(true)},L);
var N=new Element("script",{src:J,type:"text/javascript"});
var K=L.onload.bind(N),H=L.check,I=L.document;
delete L.onload;
delete L.check;
delete L.document;
N.addEvents({load:K,readystatechange:function(){if(["loaded","complete"].contains(this.readyState)){K()
}}}).setProperties(L);
if(Browser.Engine.webkit419){var M=(function(){if(!$try(H)){return 
}$clear(M);
K()
}).periodical(50)
}return N.inject(I.head)
},css:function(D,C){return new Element("link",$merge({rel:"stylesheet",media:"screen",type:"text/css",href:D},C)).inject(document.head)
},image:function(G,H){H=$merge({onload:$empty,onabort:$empty,onerror:$empty},H);
var F=new Image();
var E=$(F)||new Element("img");
["load","abort","error"].each(function(C){var B="on"+C;
var A=H[B];
delete H[B];
F[B]=function(){if(!F){return 
}if(!E.parentNode){E.width=F.width;
E.height=F.height
}F=F.onload=F.onabort=F.onerror=null;
A.delay(1,E,E);
E.fireEvent(C,E,1)
}
});
F.src=E.src=G;
if(F&&F.complete){F.onload.delay(1)
}return E.setProperties(H)
},images:function(F,G){G=$merge({onComplete:$empty,onProgress:$empty},G);
if(!F.push){F=[F]
}var E=[];
var H=0;
F.each(function(A){var B=new Asset.image(A,{onload:function(){G.onProgress.call(this,H,F.indexOf(A));
H++;
if(H==F.length){G.onComplete()
}}});
E.push(B)
});
return new Elements(E)
}});
var Sortables=new Class({Implements:[Events,Options],options:{snap:4,opacity:1,clone:false,revert:false,handle:false,constrain:false},initialize:function(C,D){this.setOptions(D);
this.elements=[];
this.lists=[];
this.idle=true;
this.addLists($$($(C)||C));
if(!this.options.clone){this.options.revert=false
}if(this.options.revert){this.effect=new Fx.Morph(null,$merge({duration:250,link:"cancel"},this.options.revert))
}},attach:function(){this.addLists(this.lists);
return this
},detach:function(){this.lists=this.removeLists(this.lists);
return this
},addItems:function(){Array.flatten(arguments).each(function(C){this.elements.push(C);
var D=C.retrieve("sortables:start",this.start.bindWithEvent(this,C));
(this.options.handle?C.getElement(this.options.handle)||C:C).addEvent("mousedown",D)
},this);
return this
},addLists:function(){Array.flatten(arguments).each(function(B){this.lists.push(B);
this.addItems(B.getChildren())
},this);
return this
},removeItems:function(){var B=[];
Array.flatten(arguments).each(function(D){B.push(D);
this.elements.erase(D);
var A=D.retrieve("sortables:start");
(this.options.handle?D.getElement(this.options.handle)||D:D).removeEvent("mousedown",A)
},this);
return $$(B)
},removeLists:function(){var B=[];
Array.flatten(arguments).each(function(A){B.push(A);
this.lists.erase(A);
this.removeItems(A.getChildren())
},this);
return $$(B)
},getClone:function(D,C){if(!this.options.clone){return new Element("div").inject(document.body)
}if($type(this.options.clone)=="function"){return this.options.clone.call(this,D,C,this.list)
}return C.clone(true).setStyles({margin:"0px",position:"absolute",visibility:"hidden",width:C.getStyle("width")}).inject(this.list).position(C.getPosition(C.getOffsetParent()))
},getDroppables:function(){var B=this.list.getChildren();
if(!this.options.constrain){B=this.lists.concat(B).erase(this.list)
}return B.erase(this.clone).erase(this.element)
},insert:function(E,F){var D="inside";
if(this.lists.contains(F)){this.list=F;
this.drag.droppables=this.getDroppables()
}else{D=this.element.getAllPrevious().contains(F)?"before":"after"
}this.element.inject(F,D);
this.fireEvent("sort",[this.element,this.clone])
},start:function(D,C){if(!this.idle){return 
}this.idle=false;
this.element=C;
this.opacity=C.get("opacity");
this.list=C.getParent();
this.clone=this.getClone(D,C);
this.drag=new Drag.Move(this.clone,{snap:this.options.snap,container:this.options.constrain&&this.element.getParent(),droppables:this.getDroppables(),onSnap:function(){D.stop();
this.clone.setStyle("visibility","visible");
this.element.set("opacity",this.options.opacity||0);
this.fireEvent("start",[this.element,this.clone])
}.bind(this),onEnter:this.insert.bind(this),onCancel:this.reset.bind(this),onComplete:this.end.bind(this)});
this.clone.inject(this.element,"before");
this.drag.start(D)
},end:function(){this.drag.detach();
this.element.set("opacity",this.opacity);
if(this.effect){var C=this.element.getStyles("width","height");
var D=this.clone.computePosition(this.element.getPosition(this.clone.offsetParent));
this.effect.element=this.clone;
this.effect.start({top:D.top,left:D.left,width:C.width,height:C.height,opacity:0.25}).chain(this.reset.bind(this))
}else{this.reset()
}},reset:function(){this.idle=true;
this.clone.destroy();
this.fireEvent("complete",this.element)
},serialize:function(){var E=Array.link(arguments,{modifier:Function.type,index:$defined});
var F=this.lists.map(function(A){return A.getChildren().map(E.modifier||function(B){return B.get("id")
},this)
},this);
var D=E.index;
if(this.lists.length==1){D=0
}return $chk(D)&&D>=0&&D<this.lists.length?F[D]:F
}});
var Tips=new Class({Implements:[Events,Options],options:{onShow:function(B){B.setStyle("visibility","visible")
},onHide:function(B){B.setStyle("visibility","hidden")
},showDelay:100,hideDelay:100,className:null,offsets:{x:16,y:16},fixed:false},initialize:function(){var E=Array.link(arguments,{options:Object.type,elements:$defined});
this.setOptions(E.options||null);
this.tip=new Element("div").inject(document.body);
if(this.options.className){this.tip.addClass(this.options.className)
}var F=new Element("div",{"class":"tip-top"}).inject(this.tip);
this.container=new Element("div",{"class":"tip"}).inject(this.tip);
var D=new Element("div",{"class":"tip-bottom"}).inject(this.tip);
this.tip.setStyles({position:"absolute",top:0,left:0,visibility:"hidden"});
if(E.elements){this.attach(E.elements)
}},attach:function(B){$$(B).each(function(J){var A=J.retrieve("tip:title",J.get("title"));
var H=J.retrieve("tip:text",J.get("rel")||J.get("href"));
var I=J.retrieve("tip:enter",this.elementEnter.bindWithEvent(this,J));
var K=J.retrieve("tip:leave",this.elementLeave.bindWithEvent(this,J));
J.addEvents({mouseenter:I,mouseleave:K});
if(!this.options.fixed){var L=J.retrieve("tip:move",this.elementMove.bindWithEvent(this,J));
J.addEvent("mousemove",L)
}J.store("tip:native",J.get("title"));
J.erase("title")
},this);
return this
},detach:function(B){$$(B).each(function(A){A.removeEvent("mouseenter",A.retrieve("tip:enter")||$empty);
A.removeEvent("mouseleave",A.retrieve("tip:leave")||$empty);
A.removeEvent("mousemove",A.retrieve("tip:move")||$empty);
A.eliminate("tip:enter").eliminate("tip:leave").eliminate("tip:move");
var D=A.retrieve("tip:native");
if(D){A.set("title",D)
}});
return this
},elementEnter:function(H,E){$A(this.container.childNodes).each(Element.dispose);
var F=E.retrieve("tip:title");
if(F){this.titleElement=new Element("div",{"class":"tip-title"}).inject(this.container);
this.fill(this.titleElement,F)
}var G=E.retrieve("tip:text");
if(G){this.textElement=new Element("div",{"class":"tip-text"}).inject(this.container);
this.fill(this.textElement,G)
}this.timer=$clear(this.timer);
this.timer=this.show.delay(this.options.showDelay,this);
this.position((!this.options.fixed)?H:{page:E.getPosition()})
},elementLeave:function(B){$clear(this.timer);
this.timer=this.hide.delay(this.options.hideDelay,this)
},elementMove:function(B){this.position(B)
},position:function(L){var N=window.getSize(),H=window.getScroll();
var K={x:this.tip.offsetWidth,y:this.tip.offsetHeight};
var M={x:"left",y:"top"};
for(var J in M){var I=L.page[J]+this.options.offsets[J];
if((I+K[J]-H[J])>N[J]){I=L.page[J]-this.options.offsets[J]-K[J]
}this.tip.setStyle(M[J],I)
}},fill:function(C,D){(typeof D=="string")?C.set("html",D):C.adopt(D)
},show:function(){this.fireEvent("show",this.tip)
},hide:function(){this.fireEvent("hide",this.tip)
}});
var SmoothScroll=new Class({Extends:Fx.Scroll,initialize:function(J,I){I=I||document;
var G=I.getDocument(),H=I.getWindow();
this.parent(G,J);
this.links=(this.options.links)?$$(this.options.links):$$(G.links);
var F=H.location.href.match(/^[^#]*/)[0]+"#";
this.links.each(function(A){if(A.href.indexOf(F)!=0){return 
}var B=A.href.substr(F.length);
if(B&&$(B)){this.useLink(A,B)
}},this);
if(!Browser.Engine.webkit419){this.addEvent("complete",function(){H.location.hash=this.anchor
},true)
}},useLink:function(D,C){D.addEvent("click",function(A){this.anchor=C;
this.toElement(C);
A.stop()
}.bind(this))
}});
var Slider=new Class({Implements:[Events,Options],options:{onTick:function(B){if(this.options.snap){B=this.toPosition(this.step)
}this.knob.setStyle(this.property,B)
},snap:false,offset:0,range:false,wheel:false,steps:100,mode:"horizontal"},initialize:function(I,G,J){this.setOptions(J);
this.element=$(I);
this.knob=$(G);
this.previousChange=this.previousEnd=this.step=-1;
this.element.addEvent("mousedown",this.clickedElement.bind(this));
if(this.options.wheel){this.element.addEvent("mousewheel",this.scrolledElement.bindWithEvent(this))
}var H,L={},K={x:false,y:false};
switch(this.options.mode){case"vertical":this.axis="y";
this.property="top";
H="offsetHeight";
break;
case"horizontal":this.axis="x";
this.property="left";
H="offsetWidth"
}this.half=this.knob[H]/2;
this.full=this.element[H]-this.knob[H]+(this.options.offset*2);
this.min=$chk(this.options.range[0])?this.options.range[0]:0;
this.max=$chk(this.options.range[1])?this.options.range[1]:this.options.steps;
this.range=this.max-this.min;
this.steps=this.options.steps||this.full;
this.stepSize=Math.abs(this.range)/this.steps;
this.stepWidth=this.stepSize*this.full/Math.abs(this.range);
this.knob.setStyle("position","relative").setStyle(this.property,-this.options.offset);
K[this.axis]=this.property;
L[this.axis]=[-this.options.offset,this.full-this.options.offset];
this.drag=new Drag(this.knob,{snap:0,limit:L,modifiers:K,onDrag:this.draggedKnob.bind(this),onStart:this.draggedKnob.bind(this),onComplete:function(){this.draggedKnob();
this.end()
}.bind(this)});
if(this.options.snap){this.drag.options.grid=Math.ceil(this.stepWidth);
this.drag.options.limit[this.axis][1]=this.full
}},set:function(B){if(!((this.range>0)^(B<this.min))){B=this.min
}if(!((this.range>0)^(B>this.max))){B=this.max
}this.step=Math.round(B);
this.checkStep();
this.end();
this.fireEvent("tick",this.toPosition(this.step));
return this
},clickedElement:function(E){var F=this.range<0?-1:1;
var D=E.page[this.axis]-this.element.getPosition()[this.axis]-this.half;
D=D.limit(-this.options.offset,this.full-this.options.offset);
this.step=Math.round(this.min+F*this.toStep(D));
this.checkStep();
this.end();
this.fireEvent("tick",D)
},scrolledElement:function(C){var D=(this.options.mode=="horizontal")?(C.wheel<0):(C.wheel>0);
this.set(D?this.step-this.stepSize:this.step+this.stepSize);
C.stop()
},draggedKnob:function(){var D=this.range<0?-1:1;
var C=this.drag.value.now[this.axis];
C=C.limit(-this.options.offset,this.full-this.options.offset);
this.step=Math.round(this.min+D*this.toStep(C));
this.checkStep()
},checkStep:function(){if(this.previousChange!=this.step){this.previousChange=this.step;
this.fireEvent("change",this.step)
}},end:function(){if(this.previousEnd!==this.step){this.previousEnd=this.step;
this.fireEvent("complete",this.step+"")
}},toStep:function(C){var D=(C+this.options.offset)*this.stepSize/this.full*this.steps;
return this.options.steps?Math.round(D-=D%this.stepSize):D
},toPosition:function(B){return(this.full*Math.abs(this.min-B))/(this.steps*this.stepSize)-this.options.offset
}});
var Scroller=new Class({Implements:[Events,Options],options:{area:20,velocity:1,onChange:function(C,D){this.element.scrollTo(C,D)
}},initialize:function(D,C){this.setOptions(C);
this.element=$(D);
this.listener=($type(this.element)!="element")?$(this.element.getDocument().body):this.element;
this.timer=null;
this.coord=this.getCoords.bind(this)
},start:function(){this.listener.addEvent("mousemove",this.coord)
},stop:function(){this.listener.removeEvent("mousemove",this.coord);
this.timer=$clear(this.timer)
},getCoords:function(B){this.page=(this.listener.get("tag")=="body")?B.client:B.page;
if(!this.timer){this.timer=this.scroll.periodical(50,this)
}},scroll:function(){var J=this.element.getSize(),F=this.element.getScroll(),G=this.element.getPosition(),H={x:0,y:0};
for(var I in this.page){if(this.page[I]<(this.options.area+G[I])&&F[I]!=0){H[I]=(this.page[I]-this.options.area-G[I])*this.options.velocity
}else{if(this.page[I]+this.options.area>(J[I]+G[I])&&J[I]+J[I]!=F[I]){H[I]=(this.page[I]-J[I]+this.options.area-G[I])*this.options.velocity
}}}if(H.y||H.x){this.fireEvent("change",[F.x+H.x,F.y+H.y])
}}});
var Accordion=new Class({Extends:Fx.Elements,options:{display:0,show:false,height:true,width:false,opacity:true,fixedHeight:false,fixedWidth:false,wait:false,alwaysHide:false},initialize:function(){var E=Array.link(arguments,{container:Element.type,options:Object.type,togglers:$defined,elements:$defined});
this.parent(E.elements,E.options);
this.togglers=$$(E.togglers);
this.container=$(E.container);
this.previous=-1;
if(this.options.alwaysHide){this.options.wait=true
}if($chk(this.options.show)){this.options.display=false;
this.previous=this.options.show
}if(this.options.start){this.options.display=false;
this.options.show=false
}this.effects={};
if(this.options.opacity){this.effects.opacity="fullOpacity"
}if(this.options.width){this.effects.width=this.options.fixedWidth?"fullWidth":"offsetWidth"
}if(this.options.height){this.effects.height=this.options.fixedHeight?"fullHeight":"scrollHeight"
}for(var F=0,D=this.togglers.length;
F<D;
F++){this.addSection(this.togglers[F],this.elements[F])
}this.elements.each(function(B,C){if(this.options.show===C){this.fireEvent("active",[this.togglers[C],B])
}else{for(var A in this.effects){B.setStyle(A,0)
}}},this);
if($chk(this.options.display)){this.display(this.options.display)
}},addSection:function(K,M,I){K=$(K);
M=$(M);
var J=this.togglers.contains(K);
var N=this.togglers.length;
this.togglers.include(K);
this.elements.include(M);
if(N&&(!J||I)){I=$pick(I,N-1);
K.inject(this.togglers[I],"before");
M.inject(K,"after")
}else{if(this.container&&!J){K.inject(this.container);
M.inject(this.container)
}}var H=this.togglers.indexOf(K);
K.addEvent("click",this.display.bind(this,H));
if(this.options.height){M.setStyles({"padding-top":0,"border-top":"none","padding-bottom":0,"border-bottom":"none"})
}if(this.options.width){M.setStyles({"padding-left":0,"border-left":"none","padding-right":0,"border-right":"none"})
}M.fullOpacity=1;
if(this.options.fixedWidth){M.fullWidth=this.options.fixedWidth
}if(this.options.fixedHeight){M.fullHeight=this.options.fixedHeight
}M.setStyle("overflow","hidden");
if(!J){for(var L in this.effects){M.setStyle(L,0)
}}return this
},display:function(C){C=($type(C)=="element")?this.elements.indexOf(C):C;
if((this.timer&&this.options.wait)||(C===this.previous&&!this.options.alwaysHide)){return this
}this.previous=C;
var D={};
this.elements.each(function(B,G){D[G]={};
var H=(G!=C)||(this.options.alwaysHide&&(B.offsetHeight>0));
this.fireEvent(H?"background":"active",[this.togglers[G],B]);
for(var A in this.effects){D[G][A]=H?0:B[this.effects[A]]
}},this);
return this.start(D)
}});var MochaUI=new Hash({options:new Hash({useEffects:false}),Columns:{instances:new Hash()},Panels:{instances:new Hash()},Windows:{instances:new Hash(),indexLevel:100,windowIDCount:0,windowsVisible:true},ieSupport:"excanvas",focusingWindow:"false",updateContent:function(D){var M={element:null,childElement:null,method:null,data:null,title:null,content:null,loadMethod:null,url:null,padding:null};
$extend(M,D);
if(!M.element){return 
}var H=M.element;
if(MochaUI.Windows.instances.get(H.id)){var K="window";
var L=MochaUI.Windows.instances.get(H.id);
var J=L.spinnerEl;
if(M.title){L.titleEl.set("html",M.title)
}}else{var K="panel";
var L=MochaUI.Panels.instances.get(H.id);
if(M.title){L.titleEl.set("html",M.title)
}}var B=L.contentEl;
if(M.childElement!=null){var I=M.childElement
}else{var I=L.contentEl
}var E=M.loadMethod!=null?M.loadMethod:L.options.loadMethod;
var A=M.method!=null?M.method:"get";
if(I==L.contentEl){L.contentWrapperEl.setStyles({overflow:L.options.scrollbars==true&&E!="iframe"?"auto":"hidden"})
}var F=L.contentWrapperEl;
if(M.padding!=null){B.setStyles({"padding-top":M.padding.top,"padding-bottom":M.padding.bottom,"padding-left":M.padding.left,"padding-right":M.padding.right})
}if(I==B){B.empty()
}switch(E){case"xhr":var G=M.data!=null?new Hash(M.data).toQueryString():"";
new Request.HTML({url:M.url,update:I,method:A,data:G,evalScripts:L.options.evalScripts,evalResponse:L.options.evalResponse,onRequest:function(){if(K=="window"&&I==B){L.showSpinner(J)
}else{if(K=="panel"&&I==B&&$("spinner")){$("spinner").setStyle("visibility","visible")
}}}.bind(this),onFailure:function(){if(I==B){I.set("html","<p><strong>Error Loading XMLHttpRequest</strong></p>");
if(K=="window"){L.hideSpinner(J)
}else{if(K=="panel"&&$("spinner")){$("spinner").setStyle("visibility","hidden")
}}}}.bind(this),onException:function(){}.bind(this),onSuccess:function(){if(I==B){if(K=="window"){L.hideSpinner(J)
}else{if(K=="panel"&&$("spinner")){$("spinner").setStyle("visibility","hidden")
}}L.fireEvent("onContentLoaded",H)
}}.bind(this),onComplete:function(){}.bind(this)}).send();
break;
case"iframe":if(L.options.contentURL==""||I!=B){break
}L.iframeEl=new Element("iframe",{id:L.options.id+"_iframe",name:L.options.id+"_iframe","class":"mochaIframe",src:M.url,marginwidth:0,marginheight:0,frameBorder:0,scrolling:"auto",styles:{height:F.offsetHeight-F.getStyle("border-top").toInt()-F.getStyle("border-bottom").toInt(),width:L.panelEl?F.offsetWidth-F.getStyle("border-left").toInt()-F.getStyle("border-right").toInt():"100%"}}).injectInside(B);
L.iframeEl.addEvent("load",function(N){if(K=="window"){L.hideSpinner(J)
}else{if(K=="panel"&&I==B&&$("spinner")){$("spinner").setStyle("visibility","hidden")
}}L.fireEvent("onContentLoaded",H)
}.bind(this));
if(K=="window"){L.showSpinner(J)
}else{if(K=="panel"&&I==B&&$("spinner")){$("spinner").setStyle("visibility","visible")
}}break;
case"html":default:var C=new Array("element","textnode","whitespace","collection");
if(C.contains($type(M.content))){M.content.inject(I)
}else{I.set("html",M.content)
}L.fireEvent("onContentLoaded",H);
break
}},reloadIframe:function(A){if(Browser.Engine.gecko){$(A).src=$(A).src
}else{top.frames[A].location.reload(true)
}},collapseToggle:function(D){var C=MochaUI.Windows.instances;
var B=C.get(D.id);
var A=B.windowEl.getElements(".handle");
if(B.isMaximized==true){return 
}if(B.isCollapsed==false){B.isCollapsed=true;
A.setStyle("display","none");
if(B.iframeEl){B.iframeEl.setStyle("visibility","hidden")
}B.contentBorderEl.setStyles({visibility:"hidden",position:"absolute",top:-10000,left:-10000});
if(B.toolbarWrapperEl){B.toolbarWrapperEl.setStyles({visibility:"hidden",position:"absolute",top:-10000,left:-10000})
}B.drawWindowCollapsed(D)
}else{B.isCollapsed=false;
B.drawWindow(D);
B.contentBorderEl.setStyles({visibility:"visible",position:null,top:null,left:null});
if(B.toolbarWrapperEl){B.toolbarWrapperEl.setStyles({visibility:"visible",position:null,top:null,left:null})
}if(B.iframeEl){B.iframeEl.setStyle("visibility","visible")
}A.setStyle("display","block")
}},closeWindow:function(D){var C=MochaUI.Windows.instances;
var B=C.get(D.id);
if(D!=$(D)||B.isClosing){return 
}B.isClosing=true;
B.fireEvent("onClose",D);
if(B.check){B.check.destroy()
}if((B.options.type=="modal"||B.options.type=="modal2")&&Browser.Engine.trident4){$("modalFix").setStyle("display","none")
}if(MochaUI.options.useEffects==false){if(B.options.type=="modal"||B.options.type=="modal2"){$("modalOverlay").setStyle("opacity",0)
}MochaUI.closingJobs(D);
return true
}else{if(Browser.Engine.trident){B.drawWindow(D,false)
}if(B.options.type=="modal"||B.options.type=="modal2"){MochaUI.Modal.modalOverlayCloseMorph.start({opacity:0})
}var A=new Fx.Morph(D,{duration:120,onComplete:function(){MochaUI.closingJobs(D);
return true
}.bind(this)});
A.start({opacity:0.4})
}},closingJobs:function(E){var D=MochaUI.Windows.instances;
var C=D.get(E.id);
E.setStyle("visibility","hidden");
E.destroy();
C.fireEvent("onCloseComplete");
if(C.options.type!="notification"){var B=this.getWindowWithHighestZindex();
this.focusWindow(B)
}D.erase(C.options.id);
if(this.loadingWorkspace==true){this.windowUnload()
}if(MochaUI.Dock&&$(MochaUI.options.dock)&&C.options.type=="window"){var A=$(C.options.id+"_dockTab");
if(A!=null){MochaUI.Dock.dockSortables.removeItems(A).destroy()
}MochaUI.Desktop.setDesktopSize()
}},closeAll:function(){$$("div.mocha").each(function(A){this.closeWindow(A)
}.bind(this))
},toggleWindowVisibility:function(){MochaUI.Windows.instances.each(function(A){if(A.options.type=="modal"||A.options.type=="modal2"||A.isMinimized==true){return 
}var B=$(A.options.id);
if(B.getStyle("visibility")=="visible"){if(A.iframe){A.iframeEl.setStyle("visibility","hidden")
}if(A.toolbarEl){A.toolbarWrapperEl.setStyle("visibility","hidden")
}A.contentBorderEl.setStyle("visibility","hidden");
B.setStyle("visibility","hidden");
MochaUI.Windows.windowsVisible=false
}else{B.setStyle("visibility","visible");
A.contentBorderEl.setStyle("visibility","visible");
if(A.iframe){A.iframeEl.setStyle("visibility","visible")
}if(A.toolbarEl){A.toolbarWrapperEl.setStyle("visibility","visible")
}MochaUI.Windows.windowsVisible=true
}}.bind(this))
},focusWindow:function(E,B){MochaUI.focusingWindow="true";
var A=function(){MochaUI.focusingWindow="false"
};
A.delay(170,this);
if($$(".mocha").length==0){return 
}if(E!=$(E)||E.hasClass("isFocused")){return 
}var D=MochaUI.Windows.instances;
var C=D.get(E.id);
if(C.options.type=="notification"){return 
}MochaUI.Windows.indexLevel+=2;
E.setStyle("zIndex",MochaUI.Windows.indexLevel);
$("windowUnderlay").setStyle("zIndex",MochaUI.Windows.indexLevel-1).inject($(E),"after");
D.each(function(F){if(F.windowEl.hasClass("isFocused")){F.fireEvent("onBlur",F.windowEl)
}F.windowEl.removeClass("isFocused")
});
if(MochaUI.Dock&&$(MochaUI.options.dock)&&C.options.type=="window"){MochaUI.Dock.makeActiveTab()
}C.windowEl.addClass("isFocused");
if(B!=false){C.fireEvent("onFocus",E)
}},getWindowWithHighestZindex:function(){this.highestZindex=0;
$$("div.mocha").each(function(A){this.zIndex=A.getStyle("zIndex");
if(this.zIndex>=this.highestZindex){this.highestZindex=this.zIndex
}}.bind(this));
$$("div.mocha").each(function(A){if(A.getStyle("zIndex")==this.highestZindex){this.windowWithHighestZindex=A
}}.bind(this));
return this.windowWithHighestZindex
},blurAll:function(){if(MochaUI.focusingWindow=="false"){$$(".mocha").each(function(C){var B=MochaUI.Windows.instances;
var A=B.get(C.id);
if(A!=null&&A.options.type!="modal"&&A.options.type!="modal2"){C.removeClass("isFocused")
}});
$$("div.dockTab").removeClass("activeDockTab")
}},roundedRect:function(E,C,H,G,B,A,F,D){E.fillStyle="rgba("+F.join(",")+","+D+")";
E.beginPath();
E.moveTo(C,H+A);
E.lineTo(C,H+B-A);
E.quadraticCurveTo(C,H+B,C+A,H+B);
E.lineTo(C+G-A,H+B);
E.quadraticCurveTo(C+G,H+B,C+G,H+B-A);
E.lineTo(C+G,H+A);
E.quadraticCurveTo(C+G,H,C+G-A,H);
E.lineTo(C+A,H);
E.quadraticCurveTo(C,H,C,H+A);
E.fill()
},triangle:function(D,B,G,F,A,E,C){D.beginPath();
D.moveTo(B+F,G);
D.lineTo(B,G+A);
D.lineTo(B+F,G+A);
D.closePath();
D.fillStyle="rgba("+E.join(",")+","+C+")";
D.fill()
},circle:function(C,A,F,E,D,B){C.beginPath();
C.moveTo(A,F);
C.arc(A,F,E,0,Math.PI*2,true);
C.fillStyle="rgba("+D.join(",")+","+B+")";
C.fill()
},centerWindow:function(F){if(!F){MochaUI.Windows.instances.each(function(G){if(G.windowEl.hasClass("isFocused")){F=G.windowEl
}})
}var E=MochaUI.Windows.instances.get(F.id);
var A=E.options;
var C=A.container.getCoordinates();
var B=window.getScroll().y+(window.getSize().y*0.5)-(F.offsetHeight*0.5);
if(B<0){B=0
}var D=(C.width*0.5)-(F.offsetWidth*0.5);
if(D<0){D=0
}if(MochaUI.options.useEffects==true){E.morph.start({top:B,left:D})
}else{F.setStyles({top:B,left:D})
}},notification:function(A){new MochaUI.Window({loadMethod:"html",closeAfter:1500,type:"notification",addClass:"notification",content:A,width:220,height:40,y:53,padding:{top:10,right:12,bottom:10,left:12},shadowBlur:5,bodyBgColor:[255,255,255]})
},dynamicResize:function(D){var C=MochaUI.Windows.instances.get(D.id);
var B=C.contentWrapperEl;
var A=C.contentEl;
B.setStyle("height",A.offsetHeight);
B.setStyle("width",A.offsetWidth);
C.drawWindow(D)
},garbageCleanUp:function(){$$("div.mocha").each(function(A){A.destroy()
}.bind(this))
},underlayInitialize:function(){var A=new Element("div",{id:"windowUnderlay",styles:{height:parent.getCoordinates().height,opacity:0.01,display:"none"}}).inject(document.body)
},setUnderlaySize:function(){if($("windowUnderlay")!=null){$("windowUnderlay").setStyle("height",parent.getCoordinates().height)
}}});
function fixPNG(E){if(Browser.Engine.trident4&&document.body.filters){var B=(E.id)?"id='"+E.id+"' ":"";
var D=(E.className)?"class='"+E.className+"' ":"";
var F=(E.title)?"title='"+E.title+"' ":"title='"+E.alt+"' ";
var C="display:inline-block;"+E.style.cssText;
var A="<span "+B+D+F+' style="width:'+E.width+"px; height:"+E.height+"px;"+C+";filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+E.src+"', sizingMethod='scale');\"></span>";
E.outerHTML=A
}}document.addEvent("keydown",function(A){if(A.key=="q"&&A.control&&A.alt){MochaUI.toggleWindowVisibility()
}});
document.addEvent("mousedown",function(A){MochaUI.blurAll.delay(50)
});
document.addEvent("domready",function(){MochaUI.underlayInitialize()
});
window.addEvent("resize",function(){MochaUI.setUnderlaySize()
});
MochaUI.Windows.windowOptions={id:null,title:"New Window",icon:false,type:"window",loadMethod:"html",method:"get",contentURL:"pages/lipsum.html",data:null,closeAfter:false,evalScripts:true,evalResponse:false,content:"Window content",toolbar:false,toolbarPosition:"top",toolbarHeight:29,toolbarURL:"pages/lipsum.html",toolbarData:null,toolbarContent:"",toolbar2:false,toolbar2Position:"bottom",toolbar2Height:29,toolbar2URL:"pages/lipsum.html",toolbar2Data:null,toolbar2Content:"",container:null,restrict:true,shape:"box",collapsible:true,minimizable:true,maximizable:true,closable:true,draggable:null,draggableGrid:false,draggableLimit:false,draggableSnap:false,resizable:null,resizeLimit:{x:[250,2500],y:[125,2000]},addClass:"",width:300,height:125,x:null,y:null,scrollbars:true,padding:{top:10,right:12,bottom:10,left:12},shadowBlur:5,shadowOffset:{x:0,y:1},controlsOffset:{right:6,top:6},useCanvas:true,useCanvasControls:true,useSpinner:true,headerHeight:25,footerHeight:25,cornerRadius:8,contentBgColor:"#fff",headerStartColor:[250,250,250],headerStopColor:[229,229,229],bodyBgColor:[229,229,229],minimizeBgColor:[255,255,255],minimizeColor:[0,0,0],maximizeBgColor:[255,255,255],maximizeColor:[0,0,0],closeBgColor:[255,255,255],closeColor:[0,0,0],resizableColor:[254,254,254],onBeforeBuild:$empty,onContentLoaded:$empty,onFocus:$empty,onBlur:$empty,onResize:$empty,onMinimize:$empty,onMaximize:$empty,onRestore:$empty,onClose:$empty,onCloseComplete:$empty};
MochaUI.Window=new Class({options:MochaUI.Windows.windowOptions,initialize:function(A){this.setOptions(A);
var A=this.options;
$extend(this,{mochaControlsWidth:0,minimizebuttonX:0,maximizebuttonX:0,closebuttonX:0,headerFooterShadow:A.headerHeight+A.footerHeight+(A.shadowBlur*2),oldTop:0,oldLeft:0,isMaximized:false,isMinimized:false,isCollapsed:false,timestamp:$time()});
if(A.type!="window"){A.container=document.body;
A.minimizable=false
}if(!A.container){A.container=MochaUI.Desktop&&MochaUI.Desktop.desktop?MochaUI.Desktop.desktop:document.body
}if(A.resizable==null){if(A.type!="window"||A.shape=="gauge"){A.resizable=false
}else{A.resizable=true
}}if(A.draggable==null){if(A.type!="window"){A.draggable=false
}else{A.draggable=true
}}if(A.shape=="gauge"||A.type=="notification"){A.collapsible=false;
A.maximizable=false;
A.contentBgColor="transparent";
A.scrollbars=false;
A.footerHeight=0
}if(A.type=="notification"){A.closable=false;
A.headerHeight=0
}if(MochaUI.Dock&&$(MochaUI.options.dock)){if(MochaUI.Dock.dock&&A.type!="modal"&&A.type!="modal2"){A.minimizable=A.minimizable
}}else{A.minimizable=false
}A.maximizable=MochaUI.Desktop&&MochaUI.Desktop.desktop&&A.maximizable&&A.type!="modal"&&A.type!="modal2";
if(this.options.type=="modal2"){this.options.shadowBlur=0;
this.options.shadowOffset={x:0,y:0};
this.options.useSpinner=false;
this.options.useCanvas=false;
this.options.footerHeight=0;
this.options.headerHeight=0
}if(A.id==null){A.id="win"+(++MochaUI.Windows.windowIDCount)
}this.windowEl=$(A.id);
this.newWindow();
return this
},saveValues:function(){var A=this.windowEl.getCoordinates();
this.options.x=A.left.toInt();
this.options.y=A.top.toInt()
},newWindow:function(E){var A=MochaUI.Windows.instances;
var I=A.get(this.options.id);
if(I){var D=I
}if(this.windowEl&&!this.isClosing){if(D.isMinimized){MochaUI.Dock.restoreMinimized(this.windowEl)
}if(D.isCollapsed){MochaUI.collapseToggle(this.windowEl);
setTimeout(MochaUI.focusWindow.pass(this.windowEl,this),10)
}else{var H=document.getCoordinates();
if(this.windowEl.getStyle("left").toInt()>H.width||this.windowEl.getStyle("top").toInt()>H.height){MochaUI.centerWindow(this.windowEl)
}setTimeout(MochaUI.focusWindow.pass(this.windowEl,this),10)
}return 
}else{A.set(this.options.id,this)
}this.isClosing=false;
this.fireEvent("onBeforeBuild");
MochaUI.Windows.indexLevel++;
this.windowEl=new Element("div",{"class":"mocha",id:this.options.id,styles:{width:this.options.width,height:this.options.height,display:"block",opacity:0,zIndex:MochaUI.Windows.indexLevel+=2}});
this.windowEl.addClass(this.options.addClass);
if(this.options.type=="modal2"){this.windowEl.addClass("modal2")
}if(Browser.Engine.trident&&this.options.shape=="gauge"){this.windowEl.setStyle("background","url(../img/spacer.gif)")
}if((this.options.type=="modal"||this.options.type=="modal2")&&Browser.Platform.mac&&Browser.Engine.gecko){if(/Firefox[\/\s](\d+\.\d+)/.test(navigator.userAgent)){var C=new Number(RegExp.$1);
if(C<3){this.windowEl.setStyle("position","fixed")
}}}if(this.options.loadMethod=="iframe"){this.options.padding={top:0,right:0,bottom:0,left:0}
}this.insertWindowElements();
this.titleEl.set("html",this.options.title);
this.contentWrapperEl.setStyles({overflow:"hidden",background:this.options.contentBgColor});
this.contentEl.setStyles({"padding-top":this.options.padding.top,"padding-bottom":this.options.padding.bottom,"padding-left":this.options.padding.left,"padding-right":this.options.padding.right});
if(this.options.shape=="gauge"){if(this.options.useCanvasControls){this.canvasControlsEl.setStyle("display","none")
}else{this.controlsEl.setStyle("display","none")
}this.windowEl.addEvent("mouseover",function(){this.mouseover=true;
var J=function(){if(this.mouseover!=false){if(this.options.useCanvasControls){this.canvasControlsEl.setStyle("display","block")
}else{this.controlsEl.setStyle("display","block")
}this.canvasHeaderEl.setStyle("display","block");
this.titleEl.setStyle("display","block")
}};
J.delay(150,this)
}.bind(this));
this.windowEl.addEvent("mouseleave",function(){this.mouseover=false;
if(this.options.useCanvasControls){this.canvasControlsEl.setStyle("display","none")
}else{this.controlsEl.setStyle("display","none")
}this.canvasHeaderEl.setStyle("display","none");
this.titleEl.setStyle("display","none")
}.bind(this))
}this.windowEl.injectInside(this.options.container);
if(this.options.type!="notification"){this.setMochaControlsWidth()
}MochaUI.updateContent({element:this.windowEl,content:this.options.content,method:this.options.method,url:this.options.contentURL,data:this.options.data});
if(this.options.toolbar==true){MochaUI.updateContent({element:this.windowEl,childElement:this.toolbarEl,content:this.options.toolbarContent,loadMethod:"xhr",method:this.options.method,url:this.options.toolbarURL,data:this.options.toolbarData})
}if(this.options.toolbar2==true){MochaUI.updateContent({element:this.windowEl,childElement:this.toolbar2El,content:this.options.toolbar2Content,loadMethod:"xhr",method:this.options.method,url:this.options.toolbar2URL,data:this.options.toolbar2Data})
}this.drawWindow(this.windowEl);
this.attachDraggable(this.windowEl);
this.attachResizable(this.windowEl);
this.setupEvents(this.windowEl);
if(this.options.resizable){this.adjustHandles()
}if(this.options.container==document.body||this.options.container==MochaUI.Desktop.desktop){var B=window.getSize()
}else{var B=$(this.options.container).getSize()
}if(!this.options.y){if(MochaUI.Desktop.desktop){var F=(B.y*0.5)-(this.windowEl.offsetHeight*0.5)
}else{var F=window.getScroll().y+(window.getSize().y*0.5)-(this.windowEl.offsetHeight*0.5)
}}else{var F=this.options.y-this.options.shadowBlur
}if(!this.options.x){var G=(B.x*0.5)-(this.windowEl.offsetWidth*0.5)
}else{var G=this.options.x-this.options.shadowBlur
}this.windowEl.setStyles({top:F,left:G});
if(MochaUI.options.useEffects==true){if(Browser.Engine.trident){this.drawWindow(this.windowEl,false)
}this.opacityMorph=new Fx.Morph(this.windowEl,{duration:350,transition:Fx.Transitions.Sine.easeInOut,onComplete:function(){if(Browser.Engine.trident){this.drawWindow(this.windowEl)
}}.bind(this)})
}if(this.options.type=="modal"||this.options.type=="modal2"){MochaUI.currentModal=this.windowEl;
if(Browser.Engine.trident4){$("modalFix").setStyle("display","block")
}$("modalOverlay").setStyle("display","block");
if(MochaUI.options.useEffects==false){$("modalOverlay").setStyle("opacity",0.6);
this.windowEl.setStyles({zIndex:11000,opacity:1})
}else{MochaUI.Modal.modalOverlayCloseMorph.cancel();
MochaUI.Modal.modalOverlayOpenMorph.start({opacity:0.6});
this.windowEl.setStyles({zIndex:11000});
this.opacityMorph.start({opacity:1})
}$$(".dockTab").removeClass("activeDockTab");
$$(".mocha").removeClass("isFocused");
this.windowEl.addClass("isFocused")
}else{if(MochaUI.options.useEffects==false){this.windowEl.setStyle("opacity",1);
setTimeout(MochaUI.focusWindow.pass(this.windowEl,this),10)
}else{this.opacityMorph.start({opacity:1});
setTimeout(MochaUI.focusWindow.pass(this.windowEl,this),10)
}}this.morph=new Fx.Morph(this.windowEl,{duration:200});
if($(this.windowEl.id+"LinkCheck")){this.check=new Element("div",{"class":"check",id:this.options.id+"_check"}).inject(this.windowEl.id+"LinkCheck")
}if(this.options.closeAfter!=false){MochaUI.closeWindow.delay(this.options.closeAfter,this,this.windowEl)
}if(MochaUI.Dock&&$(MochaUI.options.dock)&&this.options.type=="window"){MochaUI.Dock.createDockTab(this.windowEl)
}},setupEvents:function(A){if(this.closeButtonEl){this.closeButtonEl.addEvent("click",function(B){new Event(B).stop();
MochaUI.closeWindow(A)
}.bind(this))
}if(this.options.type=="window"){A.addEvent("mousedown",function(){MochaUI.focusWindow(A)
}.bind(this))
}if(this.minimizeButtonEl){this.minimizeButtonEl.addEvent("click",function(B){new Event(B).stop();
MochaUI.Dock.minimizeWindow(A)
}.bind(this))
}if(this.maximizeButtonEl){this.maximizeButtonEl.addEvent("click",function(B){new Event(B).stop();
if(this.isMaximized){MochaUI.Desktop.restoreWindow(A)
}else{MochaUI.Desktop.maximizeWindow(A)
}}.bind(this))
}if(this.options.collapsible==true){this.titleEl.addEvent("selectstart",function(B){B=new Event(B).stop()
}.bind(this));
this.titleBarEl.addEvent("mousedown",function(B){if(Browser.Engine.trident){this.titleEl.setCapture()
}}.bind(this));
this.titleBarEl.addEvent("mouseup",function(B){if(Browser.Engine.trident){this.titleEl.releaseCapture()
}}.bind(this));
this.titleBarEl.addEvent("dblclick",function(B){B=new Event(B).stop();
MochaUI.collapseToggle(this.windowEl)
}.bind(this))
}},attachDraggable:function(A){if(!this.options.draggable){return 
}this.windowDrag=new Drag.Move(A,{handle:this.titleBarEl,container:this.options.restrict==true?$(this.options.container):false,grid:this.options.draggableGrid,limit:this.options.draggableLimit,snap:this.options.draggableSnap,onStart:function(){if(this.options.type!="modal"&&this.options.type!="modal2"){MochaUI.focusWindow(A);
$("windowUnderlay").setStyle("display","block")
}if(this.iframeEl){this.iframeEl.setStyle("visibility","hidden")
}}.bind(this),onComplete:function(){if(this.options.type!="modal"&&this.options.type!="modal2"){$("windowUnderlay").setStyle("display","none")
}if(this.iframeEl){this.iframeEl.setStyle("visibility","visible")
}this.saveValues()
}.bind(this)})
},attachResizable:function(A){if(!this.options.resizable){return 
}this.resizable1=this.windowEl.makeResizable({handle:[this.n,this.ne,this.nw],limit:{y:[function(){return this.windowEl.getStyle("top").toInt()+this.windowEl.getStyle("height").toInt()-this.options.resizeLimit.y[1]
}.bind(this),function(){return this.windowEl.getStyle("top").toInt()+this.windowEl.getStyle("height").toInt()-this.options.resizeLimit.y[0]
}.bind(this)]},modifiers:{x:false,y:"top"},onStart:function(){this.resizeOnStart();
this.coords=this.contentWrapperEl.getCoordinates();
this.y2=this.coords.top.toInt()+this.contentWrapperEl.offsetHeight
}.bind(this),onDrag:function(){this.coords=this.contentWrapperEl.getCoordinates();
this.contentWrapperEl.setStyle("height",this.y2-this.coords.top.toInt());
this.drawWindow(A);
this.adjustHandles()
}.bind(this),onComplete:function(){this.resizeOnComplete()
}.bind(this)});
this.resizable2=this.contentWrapperEl.makeResizable({handle:[this.e,this.ne],limit:{x:[this.options.resizeLimit.x[0]-(this.options.shadowBlur*2),this.options.resizeLimit.x[1]-(this.options.shadowBlur*2)]},modifiers:{x:"width",y:false},onStart:function(){this.resizeOnStart()
}.bind(this),onDrag:function(){this.drawWindow(A);
this.adjustHandles()
}.bind(this),onComplete:function(){this.resizeOnComplete()
}.bind(this)});
this.resizable3=this.contentWrapperEl.makeResizable({container:this.options.restrict==true?$(this.options.container):false,handle:this.se,limit:{x:[this.options.resizeLimit.x[0]-(this.options.shadowBlur*2),this.options.resizeLimit.x[1]-(this.options.shadowBlur*2)],y:[this.options.resizeLimit.y[0]-this.headerFooterShadow,this.options.resizeLimit.y[1]-this.headerFooterShadow]},modifiers:{x:"width",y:"height"},onStart:function(){this.resizeOnStart()
}.bind(this),onDrag:function(){this.drawWindow(A);
this.adjustHandles()
}.bind(this),onComplete:function(){this.resizeOnComplete()
}.bind(this)});
this.resizable4=this.contentWrapperEl.makeResizable({handle:[this.s,this.sw],limit:{y:[this.options.resizeLimit.y[0]-this.headerFooterShadow,this.options.resizeLimit.y[1]-this.headerFooterShadow]},modifiers:{x:false,y:"height"},onStart:function(){this.resizeOnStart()
}.bind(this),onDrag:function(){this.drawWindow(A);
this.adjustHandles()
}.bind(this),onComplete:function(){this.resizeOnComplete()
}.bind(this)});
this.resizable5=this.windowEl.makeResizable({handle:[this.w,this.sw,this.nw],limit:{x:[function(){return this.windowEl.getStyle("left").toInt()+this.windowEl.getStyle("width").toInt()-this.options.resizeLimit.x[1]
}.bind(this),function(){return this.windowEl.getStyle("left").toInt()+this.windowEl.getStyle("width").toInt()-this.options.resizeLimit.x[0]
}.bind(this)]},modifiers:{x:"left",y:false},onStart:function(){this.resizeOnStart();
this.coords=this.contentWrapperEl.getCoordinates();
this.x2=this.coords.left.toInt()+this.contentWrapperEl.offsetWidth
}.bind(this),onDrag:function(){this.coords=this.contentWrapperEl.getCoordinates();
this.contentWrapperEl.setStyle("width",this.x2-this.coords.left.toInt());
this.drawWindow(A);
this.adjustHandles()
}.bind(this),onComplete:function(){this.resizeOnComplete()
}.bind(this)})
},resizeOnStart:function(){$("windowUnderlay").setStyle("display","block");
if(this.iframeEl){this.iframeEl.setStyle("visibility","hidden")
}},resizeOnComplete:function(){$("windowUnderlay").setStyle("display","none");
if(this.iframeEl){this.iframeEl.setStyle("visibility","visible")
}this.fireEvent("onResize",this.windowEl)
},adjustHandles:function(){var D=this.options.shadowBlur;
var I=D*2;
var J=this.options.shadowOffset;
var E=D-J.y-1;
var G=D+J.x-1;
var A=D+J.y-1;
var C=D-J.x-1;
var F=this.windowEl.getCoordinates();
var B=F.width-I+2;
var H=F.height-I+2;
this.n.setStyles({top:E,left:C+10,width:B-20});
this.e.setStyles({top:E+10,right:G,height:H-30});
this.s.setStyles({bottom:A,left:C+10,width:B-30});
this.w.setStyles({top:E+10,left:C,height:H-20});
this.ne.setStyles({top:E,right:G});
this.se.setStyles({bottom:A,right:G});
this.sw.setStyles({bottom:A,left:C});
this.nw.setStyles({top:E,left:C})
},detachResizable:function(){this.resizable1.detach();
this.resizable2.detach();
this.resizable3.detach();
this.resizable4.detach();
this.resizable5.detach();
this.windowEl.getElements(".handle").setStyle("display","none")
},reattachResizable:function(){this.resizable1.attach();
this.resizable2.attach();
this.resizable3.attach();
this.resizable4.attach();
this.resizable5.attach();
this.windowEl.getElements(".handle").setStyle("display","block")
},insertWindowElements:function(){var D=this.options;
var A=D.height;
var E=D.width;
var F=D.id;
var B={};
if(Browser.Engine.trident4){B.zIndexFixEl=new Element("iframe",{id:F+"_zIndexFix","class":"zIndexFix",scrolling:"no",marginWidth:0,marginHeight:0,src:""}).inject(this.windowEl)
}B.overlayEl=new Element("div",{id:F+"_overlay","class":"mochaOverlay"}).inject(this.windowEl);
B.titleBarEl=new Element("div",{id:F+"_titleBar","class":"mochaTitlebar",styles:{cursor:D.draggable?"move":"default"}}).inject(B.overlayEl,"top");
B.titleEl=new Element("h3",{id:F+"_title","class":"mochaTitle"}).inject(B.titleBarEl);
if(D.icon!=false){B.titleBarEl.setStyles({"padding-left":15,background:"url("+D.icon+") 5px 5px no-repeat"})
}B.contentBorderEl=new Element("div",{id:F+"_contentBorder","class":"mochaContentBorder"}).inject(B.overlayEl);
if(D.toolbar){B.toolbarWrapperEl=new Element("div",{id:F+"_toolbarWrapper","class":"mochaToolbarWrapper"}).inject(B.contentBorderEl,D.toolbarPosition=="bottom"?"after":"before");
if(D.toolbarPosition=="bottom"){B.toolbarWrapperEl.addClass("bottom")
}B.toolbarEl=new Element("div",{id:F+"_toolbar","class":"mochaToolbar"}).inject(B.toolbarWrapperEl)
}if(D.toolbar2){B.toolbar2WrapperEl=new Element("div",{id:F+"_toolbar2Wrapper","class":"mochaToolbarWrapper"}).inject(B.contentBorderEl,D.toolbar2Position=="bottom"?"after":"before");
if(D.toolbar2Position=="bottom"){B.toolbar2WrapperEl.addClass("bottom")
}B.toolbar2El=new Element("div",{id:F+"_toolbar2","class":"mochaToolbar"}).inject(B.toolbar2WrapperEl)
}B.contentWrapperEl=new Element("div",{id:F+"_contentWrapper","class":"mochaContentWrapper",styles:{width:E+"px",height:A+"px"}}).inject(B.contentBorderEl);
if(this.options.shape=="gauge"){B.contentBorderEl.setStyle("borderWidth",0)
}B.contentEl=new Element("div",{id:F+"_content","class":"mochaContent"}).inject(B.contentWrapperEl);
if(this.options.useCanvas==true&&Browser.Engine.trident!=true){B.canvasEl=new Element("canvas",{id:F+"_canvas","class":"mochaCanvas",width:10,height:10}).inject(this.windowEl)
}if(this.options.useCanvas==true&&Browser.Engine.trident){B.canvasEl=new Element("canvas",{id:F+"_canvas","class":"mochaCanvas",width:50000,height:20000,styles:{position:"absolute",top:0,left:0}}).inject(this.windowEl);
if(MochaUI.ieSupport=="excanvas"){G_vmlCanvasManager.initElement(B.canvasEl);
B.canvasEl=this.windowEl.getElement(".mochaCanvas")
}}B.controlsEl=new Element("div",{id:F+"_controls","class":"mochaControls"}).inject(B.overlayEl,"after");
if(D.useCanvasControls==true){B.canvasControlsEl=new Element("canvas",{id:F+"_canvasControls","class":"mochaCanvasControls",width:14,height:14}).inject(this.windowEl);
if(Browser.Engine.trident&&MochaUI.ieSupport=="excanvas"){G_vmlCanvasManager.initElement(B.canvasControlsEl);
B.canvasControlsEl=this.windowEl.getElement(".mochaCanvasControls")
}}if(D.closable){B.closeButtonEl=new Element("div",{id:F+"_closeButton","class":"mochaCloseButton",title:"Close"}).inject(B.controlsEl);
if(D.useCanvasControls==true){B.closeButtonEl.setStyle("background","none")
}}if(D.maximizable){B.maximizeButtonEl=new Element("div",{id:F+"_maximizeButton","class":"mochaMaximizeButton",title:"Maximize"}).inject(B.controlsEl);
if(D.useCanvasControls==true){B.maximizeButtonEl.setStyle("background","none")
}}if(D.minimizable){B.minimizeButtonEl=new Element("div",{id:F+"_minimizeButton","class":"mochaMinimizeButton",title:"Minimize"}).inject(B.controlsEl);
if(D.useCanvasControls==true){B.minimizeButtonEl.setStyle("background","none")
}}if(D.useSpinner==true&&D.shape!="gauge"&&D.type!="notification"){B.spinnerEl=new Element("div",{id:F+"_spinner","class":"mochaSpinner",width:16,height:16}).inject(this.windowEl,"bottom")
}if(this.options.shape=="gauge"){B.canvasHeaderEl=new Element("canvas",{id:F+"_canvasHeader","class":"mochaCanvasHeader",width:this.options.width,height:26}).inject(this.windowEl,"bottom");
if(Browser.Engine.trident&&MochaUI.ieSupport=="excanvas"){G_vmlCanvasManager.initElement(B.canvasHeaderEl);
B.canvasHeaderEl=this.windowEl.getElement(".mochaCanvasHeader")
}}if(Browser.Engine.trident){B.overlayEl.setStyle("zIndex",2)
}if(Browser.Platform.mac&&Browser.Engine.gecko){if(/Firefox[\/\s](\d+\.\d+)/.test(navigator.userAgent)){var C=new Number(RegExp.$1);
if(C<3){B.overlayEl.setStyle("overflow","auto")
}}}if(D.resizable){B.n=new Element("div",{id:F+"_resizeHandle_n","class":"handle",styles:{top:0,left:10,cursor:"n-resize"}}).inject(B.overlayEl,"after");
B.ne=new Element("div",{id:F+"_resizeHandle_ne","class":"handle corner",styles:{top:0,right:0,cursor:"ne-resize"}}).inject(B.overlayEl,"after");
B.e=new Element("div",{id:F+"_resizeHandle_e","class":"handle",styles:{top:10,right:0,cursor:"e-resize"}}).inject(B.overlayEl,"after");
B.se=new Element("div",{id:F+"_resizeHandle_se","class":"handle cornerSE",styles:{bottom:0,right:0,cursor:"se-resize"}}).inject(B.overlayEl,"after");
B.s=new Element("div",{id:F+"_resizeHandle_s","class":"handle",styles:{bottom:0,left:10,cursor:"s-resize"}}).inject(B.overlayEl,"after");
B.sw=new Element("div",{id:F+"_resizeHandle_sw","class":"handle corner",styles:{bottom:0,left:0,cursor:"sw-resize"}}).inject(B.overlayEl,"after");
B.w=new Element("div",{id:F+"_resizeHandle_w","class":"handle",styles:{top:10,left:0,cursor:"w-resize"}}).inject(B.overlayEl,"after");
B.nw=new Element("div",{id:F+"_resizeHandle_nw","class":"handle corner",styles:{top:0,left:0,cursor:"nw-resize"}}).inject(B.overlayEl,"after")
}$extend(this,B)
},drawWindow:function(G,B){if(this.isCollapsed){this.drawWindowCollapsed(G,B);
return 
}var L=this.options;
var C=L.shadowBlur;
var I=C*2;
var K=this.options.shadowOffset;
this.overlayEl.setStyles({width:this.contentWrapperEl.offsetWidth});
if(this.iframeEl){this.iframeEl.setStyles({height:this.contentWrapperEl.offsetHeight})
}var D=this.contentBorderEl.getStyle("border-top").toInt()+this.contentBorderEl.getStyle("border-bottom").toInt();
var F=this.toolbarWrapperEl?this.toolbarWrapperEl.getStyle("height").toInt()+this.toolbarWrapperEl.getStyle("border-top").toInt():0;
var E=this.toolbar2WrapperEl?this.toolbar2WrapperEl.getStyle("height").toInt()+this.toolbar2WrapperEl.getStyle("border-top").toInt():0;
this.headerFooterShadow=L.headerHeight+L.footerHeight+I;
var H=this.contentWrapperEl.getStyle("height").toInt()+this.headerFooterShadow+F+E+D;
var A=this.contentWrapperEl.getStyle("width").toInt()+I;
this.windowEl.setStyles({height:H,width:A});
this.overlayEl.setStyles({height:H,top:C-K.y,left:C-K.x});
if(this.options.useCanvas==true){if(Browser.Engine.trident){this.canvasEl.height=20000;
this.canvasEl.width=50000
}this.canvasEl.height=H;
this.canvasEl.width=A
}if(Browser.Engine.trident4){this.zIndexFixEl.setStyles({width:A,height:H})
}this.titleBarEl.setStyles({width:A-I,height:L.headerHeight});
if(L.useSpinner==true&&L.shape!="gauge"&&L.type!="notification"){this.spinnerEl.setStyles({left:C-K.x+3,bottom:C+K.y+4})
}if(this.options.useCanvas!=false){var J=this.canvasEl.getContext("2d");
J.clearRect(0,0,A,H);
switch(L.shape){case"box":this.drawBox(J,A,H,C,K,B);
break;
case"gauge":this.drawGauge(J,A,H,C,K,B);
break
}if(L.resizable){MochaUI.triangle(J,A-(C+K.x+17),H-(C+K.y+18),11,11,L.resizableColor,1)
}if(Browser.Engine.trident){MochaUI.triangle(J,0,0,10,10,L.resizableColor,0)
}}if(L.type!="notification"&&L.useCanvasControls==true){this.drawControls(A,H,B)
}},drawWindowCollapsed:function(E,B){var J=this.options;
var C=J.shadowBlur;
var F=C*2;
var I=J.shadowOffset;
var D=J.headerHeight+F+2;
var G=D;
var A=this.contentWrapperEl.getStyle("width").toInt()+F;
this.windowEl.setStyle("height",G);
this.overlayEl.setStyles({height:G,top:C-I.y,left:C-I.x});
this.canvasEl.height=G;
this.canvasEl.width=A;
if(Browser.Engine.trident4){this.zIndexFixEl.setStyles({width:A,height:G})
}this.windowEl.setStyle("width",A);
this.overlayEl.setStyle("width",A);
this.titleBarEl.setStyles({width:A-F,height:J.headerHeight});
if(this.options.useCanvas!=false){var H=this.canvasEl.getContext("2d");
H.clearRect(0,0,A,G);
this.drawBoxCollapsed(H,A,G,C,I,B);
if(J.useCanvasControls==true){this.drawControls(A,G,B)
}if(Browser.Engine.trident){MochaUI.triangle(H,0,0,10,10,J.resizableColor,0)
}}},drawControls:function(G,E,H){var F=this.options;
var D=F.shadowBlur;
var C=F.shadowOffset;
var B=F.controlsOffset;
this.controlsEl.setStyles({right:D+C.x+B.right,top:D-C.y+B.top});
this.canvasControlsEl.setStyles({right:D+C.x+B.right,top:D-C.y+B.top});
this.closebuttonX=F.closable?this.mochaControlsWidth-7:this.mochaControlsWidth+12;
this.maximizebuttonX=this.closebuttonX-(F.maximizable?19:0);
this.minimizebuttonX=this.maximizebuttonX-(F.minimizable?19:0);
var A=this.canvasControlsEl.getContext("2d");
A.clearRect(0,0,100,100);
if(this.options.closable){this.closebutton(A,this.closebuttonX,7,F.closeBgColor,1,F.closeColor,1)
}if(this.options.maximizable){this.maximizebutton(A,this.maximizebuttonX,7,F.maximizeBgColor,1,F.maximizeColor,1)
}if(this.options.minimizable){this.minimizebutton(A,this.minimizebuttonX,7,F.minimizeBgColor,1,F.minimizeColor,1)
}if(Browser.Engine.trident){MochaUI.circle(A,0,0,3,this.options.resizableColor,0)
}},drawBox:function(H,A,G,C,I,B){var F=C*2;
var D=this.options.cornerRadius;
if(B!=false){for(var E=0;
E<=C;
E++){MochaUI.roundedRect(H,I.x+E,I.y+E,A-(E*2)-I.x,G-(E*2)-I.y,D+(C-E),[0,0,0],E==C?0.29:0.065+(E*0.01))
}}this.bodyRoundedRect(H,C-I.x,C-I.y,A-F,G-F,D,this.options.bodyBgColor);
if(this.options.type!="notification"){this.topRoundedRect(H,C-I.x,C-I.y,A-F,this.options.headerHeight,D,this.options.headerStartColor,this.options.headerStopColor)
}},drawBoxCollapsed:function(H,A,G,C,I,B){var J=this.options;
var F=C*2;
var D=J.cornerRadius;
if(B!=false){for(var E=0;
E<=C;
E++){MochaUI.roundedRect(H,I.x+E,I.y+E,A-(E*2)-I.x,G-(E*2)-I.y,D+(C-E),[0,0,0],E==C?0.3:0.06+(E*0.01))
}}this.topRoundedRect2(H,C-I.x,C-I.y,A-F,J.headerHeight+2,D,J.headerStartColor,J.headerStopColor)
},drawGauge:function(G,A,F,C,H,B){var I=this.options;
var D=(A*0.5)-(C)+16;
if(B!=false){for(var E=0;
E<=C;
E++){MochaUI.circle(G,A*0.5+H.x,(F+I.headerHeight)*0.5+H.x,(A*0.5)-(E*2)-H.x,[0,0,0],E==C?0.75:0.075+(E*0.04))
}}MochaUI.circle(G,A*0.5-H.x,(F+I.headerHeight)*0.5-H.y,(A*0.5)-C,I.bodyBgColor,1);
this.canvasHeaderEl.setStyles({top:C-H.y,left:C-H.x});
var G=this.canvasHeaderEl.getContext("2d");
G.clearRect(0,0,A,100);
G.beginPath();
G.lineWidth=24;
G.lineCap="round";
G.moveTo(13,13);
G.lineTo(A-(C*2)-13,13);
G.strokeStyle="rgba(0, 0, 0, .65)";
G.stroke()
},bodyRoundedRect:function(D,C,G,F,B,A,E){D.fillStyle="rgba("+E.join(",")+", 100)";
D.beginPath();
D.moveTo(C,G+A);
D.lineTo(C,G+B-A);
D.quadraticCurveTo(C,G+B,C+A,G+B);
D.lineTo(C+F-A,G+B);
D.quadraticCurveTo(C+F,G+B,C+F,G+B-A);
D.lineTo(C+F,G+A);
D.quadraticCurveTo(C+F,G,C+F-A,G);
D.lineTo(C+A,G);
D.quadraticCurveTo(C,G,C,G+A);
D.fill()
},topRoundedRect:function(I,G,F,A,H,E,C,D){var B=I.createLinearGradient(0,0,0,H);
B.addColorStop(0,"rgba("+C.join(",")+", 1)");
B.addColorStop(1,"rgba("+D.join(",")+", 1)");
I.fillStyle=B;
I.beginPath();
I.moveTo(G,F);
I.lineTo(G,F+H);
I.lineTo(G+A,F+H);
I.lineTo(G+A,F+E);
I.quadraticCurveTo(G+A,F,G+A-E,F);
I.lineTo(G+E,F);
I.quadraticCurveTo(G,F,G,F+E);
I.fill()
},topRoundedRect2:function(I,G,F,A,H,E,C,D){var B=I.createLinearGradient(0,this.options.shadowBlur-1,0,H+this.options.shadowBlur+3);
B.addColorStop(0,"rgba("+C.join(",")+", 1)");
B.addColorStop(1,"rgba("+D.join(",")+", 1)");
I.fillStyle=B;
I.beginPath();
I.moveTo(G,F+E);
I.lineTo(G,F+H-E);
I.quadraticCurveTo(G,F+H,G+E,F+H);
I.lineTo(G+A-E,F+H);
I.quadraticCurveTo(G+A,F+H,G+A,F+H-E);
I.lineTo(G+A,F+E);
I.quadraticCurveTo(G+A,F,G+A-E,F);
I.lineTo(G+E,F);
I.quadraticCurveTo(G,F,G,F+E);
I.fill()
},maximizebutton:function(D,B,G,A,F,E,C){D.beginPath();
D.moveTo(B,G);
D.arc(B,G,7,0,Math.PI*2,true);
D.fillStyle="rgba("+A.join(",")+","+F+")";
D.fill();
D.strokeStyle="rgba("+E.join(",")+","+C+")";
D.beginPath();
D.moveTo(B,G-4);
D.lineTo(B,G+4);
D.stroke();
D.beginPath();
D.moveTo(B-4,G);
D.lineTo(B+4,G);
D.stroke()
},closebutton:function(D,B,G,A,F,E,C){D.beginPath();
D.moveTo(B,G);
D.arc(B,G,7,0,Math.PI*2,true);
D.fillStyle="rgba("+A.join(",")+","+F+")";
D.fill();
D.strokeStyle="rgba("+E.join(",")+","+C+")";
D.beginPath();
D.moveTo(B-3,G-3);
D.lineTo(B+3,G+3);
D.stroke();
D.beginPath();
D.moveTo(B+3,G-3);
D.lineTo(B-3,G+3);
D.stroke()
},minimizebutton:function(D,B,G,A,F,E,C){D.beginPath();
D.moveTo(B,G);
D.arc(B,G,7,0,Math.PI*2,true);
D.fillStyle="rgba("+A.join(",")+","+F+")";
D.fill();
D.strokeStyle="rgba("+E.join(",")+","+C+")";
D.beginPath();
D.moveTo(B-4,G);
D.lineTo(B+4,G);
D.stroke()
},hideSpinner:function(A){if($(A)){$(A).setStyle("visibility","hidden")
}},showSpinner:function(A){if(!this.options.useSpinner||this.options.shape=="gauge"||this.options.type=="notification"){return 
}$(A).setStyles({visibility:"visible"})
},setMochaControlsWidth:function(){this.mochaControlsWidth=0;
var A=this.options;
if(A.minimizable){this.mochaControlsWidth+=(this.minimizeButtonEl.getStyle("margin-left").toInt()+this.minimizeButtonEl.getStyle("width").toInt())
}if(A.maximizable){this.mochaControlsWidth+=(this.maximizeButtonEl.getStyle("margin-left").toInt()+this.maximizeButtonEl.getStyle("width").toInt())
}if(A.closable){this.mochaControlsWidth+=(this.closeButtonEl.getStyle("margin-left").toInt()+this.closeButtonEl.getStyle("width").toInt())
}this.controlsEl.setStyle("width",this.mochaControlsWidth);
if(A.useCanvasControls==true){this.canvasControlsEl.setProperty("width",this.mochaControlsWidth)
}}});
MochaUI.Window.implement(new Options,new Events);
MochaUI.Modal=new Class({Extends:MochaUI.Window,Implements:[Events,Options],initialize:function(A){this.modalInitialize();
window.addEvent("resize",function(){this.setModalSize()
}.bind(this))
},modalInitialize:function(){var A=new Element("div",{id:"modalOverlay",styles:{height:document.getCoordinates().height,opacity:0.6}}).inject(document.body);
A.addEvent("click",function(C){MochaUI.closeWindow(MochaUI.currentModal)
});
if(Browser.Engine.trident4){var B=new Element("iframe",{id:"modalFix",scrolling:"no",marginWidth:0,marginHeight:0,src:"",styles:{height:document.getCoordinates().height}}).inject(document.body)
}this.modalOverlayOpenMorph=new Fx.Morph($("modalOverlay"),{duration:150});
this.modalOverlayCloseMorph=new Fx.Morph($("modalOverlay"),{duration:150,onComplete:function(){$("modalOverlay").setStyle("display","none");
if(Browser.Engine.trident4){$("modalFix").setStyle("display","none")
}}.bind(this)})
},setModalSize:function(){$("modalOverlay").setStyle("height",document.getCoordinates().height);
if(Browser.Engine.trident4){$("modalFix").setStyle("height",document.getCoordinates().height)
}}});
MochaUI.Modal.implement(new Options,new Events);
MochaUI.extend({NewWindowsFromHTML:function(){$$("div.mocha").each(function(B){if(Browser.Engine.presto||Browser.Engine.trident5){B.setStyle("display","block")
}var D=B.getElement("h3.mochaTitle");
var C=B.getStyles("height","width");
var A={id:B.getProperty("id"),height:C.height.toInt(),width:C.width.toInt(),x:B.getStyle("left").toInt(),y:B.getStyle("top").toInt()};
if(D){A.title=D.innerHTML;
D.destroy()
}A.content=B.innerHTML;
B.destroy();
new MochaUI.Window(A,true)
}.bind(this))
}});
MochaUI.extend({newWindowsFromJSON:function(A){A.each(function(B){new MochaUI.Window(B)
}.bind(this))
}});
MochaUI.options.extend({viewportTopOffset:30,viewportLeftOffset:20,windowTopOffset:50,windowLeftOffset:40});
MochaUI.extend({arrangeCascade:function(){var E=document.getCoordinates();
var B=0;
MochaUI.Windows.instances.each(function(G){if(!G.isMinimized){B++
}});
if((this.options.windowTopOffset*(B+1))>=(E.height-this.options.viewportTopOffset)){var C=(E.height-this.options.viewportTopOffset)/(B+1)
}else{var C=this.options.windowTopOffset
}if((this.options.windowLeftOffset*(B+1))>=(E.width-this.options.viewportLeftOffset-20)){var D=(E.width-this.options.viewportLeftOffset-20)/(B+1)
}else{var D=this.options.windowLeftOffset
}var A=this.options.viewportLeftOffset;
var F=this.options.viewportTopOffset;
$$("div.mocha").each(function(H){var I=MochaUI.Windows.instances.get(H.id);
if(!I.isMinimized&&!I.isMaximized){id=H.id;
MochaUI.focusWindow(H);
A+=D;
F+=C;
if(MochaUI.options.useEffects==false){H.setStyles({top:F,left:A})
}else{var G=new Fx.Morph(H,{duration:550});
G.start({top:F,left:A})
}}}.bind(this))
}});
MochaUI.extend({arrangeTile:function(){var G=10;
var E=10;
var A=MochaUI.Windows.instances;
var I=0;
A.each(function(L){if(!L.isMinimized&&!L.isMaximized){I++
}});
var F=3;
var K=Math.ceil(I/F);
var H=document.getCoordinates();
var D=((H.width-this.options.viewportLeftOffset)/F);
var C=((H.height-this.options.viewportTopOffset)/K);
var J=0;
var B=0;
A.each(function(T){if(!T.isMinimized&&!T.isMaximized){var P=T.contentWrapperEl;
var L=P.getCoordinates();
var S=T.windowEl.getCoordinates();
var O=L.top-S.top;
var R=S.height-L.height-O;
var N=L.left-S.left;
var U=S.width-L.width-N;
var M=(G+(B*D));
var Q=(E+(J*C));
T.windowEl.setStyles({left:M,top:Q});
T.drawWindow(T.windowEl);
MochaUI.focusWindow(T.windowEl);
if(++B===F){J++;
B=0
}}}.bind(this))
}});
MochaUI.extend({initializeTabs:function(A){$(A).getElements("li").each(function(B){B.addEvent("click",function(C){MochaUI.selected(this,A)
})
})
},selected:function(B,A){$(A).getChildren().each(function(C){C.removeClass("selected")
});
B.addClass("selected")
}});
MochaUI.Desktop=new Class({Extends:MochaUI.Window,Implements:[Events,Options],options:{desktop:"desktop",desktopHeader:"desktopHeader",desktopFooter:"desktopFooter",desktopNavBar:"desktopNavbar",pageWrapper:"pageWrapper",page:"page",desktopFooter:"desktopFooterWrapper"},initialize:function(A){this.setOptions(A);
this.desktop=$(this.options.desktop);
this.desktopHeader=$(this.options.desktopHeader);
this.desktopNavBar=$(this.options.desktopNavBar);
this.pageWrapper=$(this.options.pageWrapper);
this.page=$(this.options.page);
this.desktopFooter=$(this.options.desktopFooter);
if(!MochaUI.Dock.dockWrapper){this.setDesktopSize()
}this.menuInitialize();
window.addEvent("resize",function(B){this.onBrowserResize()
}.bind(this))
},menuInitialize:function(){if(Browser.Engine.trident4&&this.desktopNavBar){this.desktopNavBar.getElements("li").each(function(A){A.addEvent("mouseenter",function(){this.addClass("ieHover")
});
A.addEvent("mouseleave",function(){this.removeClass("ieHover")
})
})
}},onBrowserResize:function(){this.setDesktopSize();
setTimeout(function(){MochaUI.Windows.instances.each(function(A){if(A.isMaximized){if(A.iframeEl){A.iframeEl.setStyle("visibility","hidden")
}var D=document.getCoordinates();
var B=A.contentBorderEl.getStyle("border-top").toInt()+A.contentBorderEl.getStyle("border-bottom").toInt();
var C=A.toolbarWrapperEl?A.toolbarWrapperEl.getStyle("height").toInt()+A.toolbarWrapperEl.getStyle("border-top").toInt():0;
A.contentWrapperEl.setStyles({height:D.height-A.options.headerHeight-A.options.footerHeight-B-C,width:D.width});
A.drawWindow($(A.options.id));
if(A.iframeEl){A.iframeEl.setStyles({height:A.contentWrapperEl.getStyle("height")});
A.iframeEl.setStyle("visibility","visible")
}}}.bind(this))
}.bind(this),100)
},setDesktopSize:function(){var D=window.getCoordinates();
var B=$(MochaUI.options.dockWrapper);
if(this.desktop){this.desktop.setStyle("height",D.height)
}if(this.pageWrapper){var A=MochaUI.dockVisible?B.offsetHeight:0;
var C=D.height;
C-=this.pageWrapper.getStyle("border-top").toInt();
C-=this.pageWrapper.getStyle("border-bottom").toInt();
if(this.desktopHeader){C-=this.desktopHeader.offsetHeight
}if(this.desktopFooter){C-=this.desktopFooter.offsetHeight
}C-=A;
if(C<0){C=0
}this.pageWrapper.setStyle("height",C)
}if(MochaUI.Columns.instances.getKeys().length>0){MochaUI.Desktop.resizePanels()
}},resizePanels:function(){if(Browser.Engine.trident4){$$(".pad").setStyle("display","none");
$$(".rHeight").setStyle("height",1)
}MochaUI.panelHeight();
MochaUI.rWidth();
if(Browser.Engine.trident4){$$(".pad").setStyle("display","block")
}},maximizeWindow:function(H){var G=MochaUI.Windows.instances.get(H.id);
var J=G.options;
var D=G.windowDrag;
if(H!=$(H)||G.isMaximized){return 
}if(G.isCollapsed){MochaUI.collapseToggle(H)
}G.isMaximized=true;
if(G.options.restrict){D.detach();
if(J.resizable){G.detachResizable()
}G.titleBarEl.setStyle("cursor","default")
}if(J.container!=this.desktop){this.desktop.grab(H);
if(this.options.restrict){D.container=this.desktop
}}G.oldTop=H.getStyle("top");
G.oldLeft=H.getStyle("left");
var E=G.contentWrapperEl;
E.oldWidth=E.getStyle("width");
E.oldHeight=E.getStyle("height");
if(G.iframeEl){G.iframeEl.setStyle("visibility","hidden")
}var C=document.getCoordinates();
var J=G.options;
var F=J.shadowBlur;
var I=J.shadowOffset;
var B=C.height-J.headerHeight-J.footerHeight;
B-=G.contentBorderEl.getStyle("border-top").toInt();
B-=G.contentBorderEl.getStyle("border-bottom").toInt();
B-=(G.toolbarWrapperEl?G.toolbarWrapperEl.getStyle("height").toInt()+G.toolbarWrapperEl.getStyle("border-top").toInt():0);
if(MochaUI.options.useEffects==false){H.setStyles({top:I.y-F,left:I.x-F});
G.contentWrapperEl.setStyles({height:B,width:C.width});
G.drawWindow(H);
if(G.iframeEl){G.iframeEl.setStyle("visibility","visible")
}G.fireEvent("onMaximize",H)
}else{var A=new Fx.Elements([E,H],{duration:100,transition:Fx.Transitions.Sine.easeInOut,onStart:function(K){G.maximizeAnimation=G.drawWindow.periodical(20,G,K)
}.bind(this),onComplete:function(K){$clear(G.maximizeAnimation);
G.drawWindow(K);
if(G.iframeEl){G.iframeEl.setStyle("visibility","visible")
}G.fireEvent("onMaximize",K)
}.bind(this)});
A.start({"0":{height:B,width:C.width},"1":{top:I.y-F,left:I.x-F}})
}G.maximizeButtonEl.setProperty("title","Restore");
MochaUI.focusWindow(H)
},restoreWindow:function(E){var D=MochaUI.Windows.instances.get(E.id);
if(E!=$(E)||!D.isMaximized){return 
}var B=D.options;
D.isMaximized=false;
if(B.restrict){D.windowDrag.attach();
if(B.resizable){D.reattachResizable()
}D.titleBarEl.setStyle("cursor","move")
}if(D.iframeEl){D.iframeEl.setStyle("visibility","hidden")
}var C=D.contentWrapperEl;
if(MochaUI.options.useEffects==false){C.setStyles({width:C.oldWidth,height:C.oldHeight});
D.drawWindow(E);
E.setStyles({top:D.oldTop,left:D.oldLeft});
if(D.iframeEl){D.iframeEl.setStyle("visibility","visible")
}if(B.container!=this.desktop){$(B.container).grab(E);
if(B.restrict){D.windowDrag.container=$(B.container)
}}D.fireEvent("onRestore",E)
}else{var A=new Fx.Elements([C,E],{duration:200,transition:Fx.Transitions.Sine.easeInOut,onStart:function(F){D.maximizeAnimation=D.drawWindow.periodical(20,D,F)
}.bind(this),onComplete:function(F){$clear(D.maximizeAnimation);
D.drawWindow(E);
if(D.iframeEl){D.iframeEl.setStyle("visibility","visible")
}if(B.container!=this.desktop){$(B.container).grab(E);
if(B.restrict){D.windowDrag.container=$(B.container)
}}D.fireEvent("onRestore",E)
}.bind(this)});
A.start({"0":{height:C.oldHeight,width:C.oldWidth},"1":{top:D.oldTop,left:D.oldLeft}})
}D.maximizeButtonEl.setProperty("title","Maximize")
}});
MochaUI.Desktop.implement(new Options,new Events);
MochaUI.Column=new Class({Extends:MochaUI.Desktop,Implements:[Events,Options],options:{id:null,placement:null,width:null,resizeLimit:[],onResize:$empty,onCollapse:$empty,onExpand:$empty},initialize:function(A){this.setOptions(A);
$extend(this,{timestamp:$time(),isCollapsed:false,oldWidth:0});
var A=this.options;
var F=MochaUI.Columns.instances;
var C=F.get(A.id);
if(C){var D=C
}if(this.columnEl){return 
}else{F.set(A.id,this)
}this.columnEl=new Element("div",{id:this.options.id,"class":"column expanded",styles:{width:A.placement=="main"?null:A.width}}).inject($(MochaUI.Desktop.pageWrapper));
var B=this.columnEl.getParent();
var E=B.getStyle("height").toInt();
this.columnEl.setStyle("height",E);
if(A.placement=="main"){this.columnEl.addClass("rWidth")
}this.spacerEl=new Element("div",{id:this.options.id+"_spacer","class":"horizontalHandle"}).inject(this.columnEl);
switch(this.options.placement){case"left":this.handleEl=new Element("div",{id:this.options.id+"_handle","class":"columnHandle"}).inject(this.columnEl,"after");
this.handleIconEl=new Element("div",{id:A.id+"_handle_icon","class":"handleIcon"}).inject(this.handleEl);
addResizeRight(this.columnEl,A.resizeLimit[0],A.resizeLimit[1]);
break;
case"right":this.handleEl=new Element("div",{id:this.options.id+"_handle","class":"columnHandle"}).inject(this.columnEl,"before");
this.handleIconEl=new Element("div",{id:A.id+"_handle_icon","class":"handleIcon"}).inject(this.handleEl);
addResizeLeft(this.columnEl,A.resizeLimit[0],A.resizeLimit[1]);
break
}if(this.handleEl!=null){this.handleEl.addEvent("dblclick",function(){this.columnToggle()
}.bind(this))
}MochaUI.rWidth()
},columnToggle:function(){var A=this.columnEl;
if(this.isCollapsed==false){this.oldWidth=A.getStyle("width").toInt();
this.resize.detach();
this.handleEl.removeEvents("dblclick");
this.handleEl.addEvent("click",function(){this.columnToggle()
}.bind(this));
this.handleEl.setStyle("cursor","pointer").addClass("detached");
A.setStyle("width",0);
this.isCollapsed=true;
A.addClass("collapsed");
A.removeClass("expanded");
MochaUI.rWidth();
this.fireEvent("onCollapse")
}else{A.setStyle("width",this.oldWidth);
this.isCollapsed=false;
A.addClass("expanded");
A.removeClass("collapsed");
this.handleEl.removeEvents("click");
this.handleEl.addEvent("dblclick",function(){this.columnToggle()
}.bind(this));
this.resize.attach();
this.handleEl.setStyle("cursor","e-resize").addClass("attached");
MochaUI.rWidth();
this.fireEvent("onExpand")
}}});
MochaUI.Column.implement(new Options,new Events);
MochaUI.Panel=new Class({Extends:MochaUI.Desktop,Implements:[Events,Options],options:{id:null,title:"New Panel",column:null,loadMethod:"html",contentURL:"pages/lipsum.html",method:"get",data:null,evalScripts:true,evalResponse:false,content:"",tabsURL:null,tabsData:null,footer:false,footerURL:"pages/lipsum.html",footerData:null,height:125,addClass:"",scrollbars:true,padding:{top:8,right:8,bottom:8,left:8},panelBackground:"#f8f8f8",onBeforeBuild:$empty,onContentLoaded:$empty,onResize:$empty,onCollapse:$empty,onExpand:$empty},initialize:function(A){this.setOptions(A);
$extend(this,{timestamp:$time(),isCollapsed:false,oldHeight:0,partner:null});
var D=MochaUI.Panels.instances;
var B=D.get(this.options.id);
if(B){var C=B
}if(this.panelEl){return 
}else{D.set(this.options.id,this)
}this.fireEvent("onBeforeBuild");
if(this.options.loadMethod=="iframe"){this.options.scrollbars=false;
this.options.padding={top:0,right:0,bottom:0,left:0}
}this.showHandle=true;
if($(this.options.column).getChildren().length==0){this.showHandle=false
}this.panelEl=new Element("div",{id:this.options.id,"class":"panel expanded",styles:{height:this.options.height,background:this.options.panelBackground}}).inject($(this.options.column));
this.panelEl.addClass(this.options.addClass);
this.contentEl=new Element("div",{id:this.options.id+"_pad","class":"pad"}).inject(this.panelEl);
if(this.options.footer){this.footerWrapperEl=new Element("div",{id:this.options.id+"_panelFooterWrapper","class":"panel-footerWrapper"}).inject(this.panelEl);
this.footerEl=new Element("div",{id:this.options.id+"_panelFooter","class":"panel-footer"}).inject(this.footerWrapperEl);
MochaUI.updateContent({element:this.panelEl,childElement:this.footerEl,loadMethod:"xhr",data:this.options.footerData,url:this.options.footerURL})
}this.contentWrapperEl=this.panelEl;
this.contentWrapperEl.setStyles({overflow:this.options.scrollbars&&!this.iframeEl?"auto":"hidden"});
this.contentEl.setStyles({"padding-top":this.options.padding.top,"padding-bottom":this.options.padding.bottom,"padding-left":this.options.padding.left,"padding-right":this.options.padding.right});
this.panelHeaderEl=new Element("div",{id:this.options.id+"_header","class":"panel-header"}).inject(this.panelEl,"before");
this.panelHeaderToolboxEl=new Element("div",{id:this.options.id+"_headerToolbox","class":"panel-header-toolbox"}).inject(this.panelHeaderEl);
this.collapseToggleEl=new Element("div",{id:this.options.id+"_minmize","class":"panel-collapse icon16",styles:{width:16,height:16},title:"Collapse Panel"}).inject(this.panelHeaderToolboxEl);
this.collapseToggleEl.addEvent("click",function(H){var E=this.panelEl;
var I=MochaUI.Panels.instances;
var G=[];
E.getAllPrevious(".panel").each(function(J){var K=I.get(J.id);
if(K.isCollapsed==false){G.push(J)
}});
E.getAllNext(".panel").each(function(J){var K=I.get(J.id);
if(K.isCollapsed==false){G.push(J)
}});
if(this.isCollapsed==false){var F=MochaUI.Columns.instances.get($(this.options.column).id);
if(G.length==0&&F.options.placement!="main"){var F=MochaUI.Columns.instances.get($(this.options.column).id);
F.columnToggle();
return 
}else{if(G.length==0&&F.options.placement=="main"){return 
}}this.oldHeight=E.getStyle("height").toInt();
if(this.oldHeight<10){this.oldHeight=20
}E.setStyle("height",0);
this.isCollapsed=true;
E.addClass("collapsed");
E.removeClass("expanded");
MochaUI.panelHeight(this.options.column,E,"collapsing");
this.collapseToggleEl.removeClass("panel-collapsed");
this.collapseToggleEl.addClass("panel-expand");
this.collapseToggleEl.setProperty("title","Expand Panel");
this.fireEvent("onCollapse")
}else{E.setStyle("height",this.oldHeight);
this.isCollapsed=false;
E.addClass("expanded");
E.removeClass("collapsed");
MochaUI.panelHeight(this.options.column,E,"expanding");
this.collapseToggleEl.removeClass("panel-expand");
this.collapseToggleEl.addClass("panel-collapsed");
this.collapseToggleEl.setProperty("title","Collapse Panel");
this.fireEvent("onExpand")
}}.bind(this));
this.panelHeaderContentEl=new Element("div",{id:this.options.id+"_headerContent","class":"panel-headerContent"}).inject(this.panelHeaderEl);
this.titleEl=new Element("h2",{id:this.options.id+"_title"}).inject(this.panelHeaderContentEl);
if(this.options.tabsURL==null){this.titleEl.set("html",this.options.title)
}else{this.panelHeaderContentEl.addClass("tabs");
MochaUI.updateContent({element:this.panelEl,childElement:this.panelHeaderContentEl,loadMethod:"xhr",url:this.options.tabsURL,data:this.options.tabsData})
}this.handleEl=new Element("div",{id:this.options.id+"_handle","class":"horizontalHandle",styles:{display:this.showHandle==true?"block":"none"}}).inject(this.panelEl,"after");
this.handleIconEl=new Element("div",{id:this.options.id+"_handle_icon","class":"handleIcon"}).inject(this.handleEl);
addResizeBottom(this.options.id);
MochaUI.updateContent({element:this.panelEl,content:this.options.content,method:this.options.method,data:this.options.data,url:this.options.contentURL});
MochaUI.panelHeight(this.options.column,this.panelEl,"new")
}});
MochaUI.Panel.implement(new Options,new Events);
MochaUI.extend({panelHeight:function(A,C,B){if(A!=null){MochaUI.panelHeight2($(A),C,B)
}else{$$(".column").each(function(D){MochaUI.panelHeight2(D)
}.bind(this))
}},panelHeight2:function(E,L,F){var B=MochaUI.Panels.instances;
var I=E.getParent();
var H=I.getStyle("height").toInt();
if(Browser.Engine.trident4){H-=1
}E.setStyle("height",H);
var G=E.getChildren(".panel");
var J=E.getChildren(".expanded");
var C=[];
var D;
var A=0;
this.panelsHeight=0;
this.height=0;
G.each(function(M){currentInstance=B.get(M.id);
if(M.hasClass("expanded")&&M.getNext(".expanded")){currentInstance.partner=M.getNext(".expanded");
currentInstance.resize.attach();
currentInstance.handleEl.setStyles({display:"block",cursor:"n-resize"}).removeClass("detached")
}else{currentInstance.resize.detach();
currentInstance.handleEl.setStyle("cursor",null).addClass("detached")
}if(M.getNext(".panel")==null){currentInstance.handleEl.setStyle("display","none")
}}.bind(this));
E.getChildren().each(function(M){if(M.hasClass("panel")){var N=B.get(M.id);
areAnyNextSiblingsExpanded=function(O){var P;
O.getAllNext(".panel").each(function(Q){var R=B.get(Q.id);
if(R.isCollapsed==false){P=true
}}.bind(this));
return P
}.bind(this);
areAnyExpandingNextSiblingsExpanded=function(){var O;
L.getAllNext(".panel").each(function(P){var Q=B.get(P.id);
if(Q.isCollapsed==false){O=true
}}.bind(this));
return O
}.bind(this);
if(F=="new"){if(N.isCollapsed!=true&&M!=L){C.push(M)
}if(N.isCollapsed!=true&&M!=L){this.panelsHeight+=M.offsetHeight.toInt()
}}else{if(F==null||F=="collapsing"){if(N.isCollapsed!=true&&(M.getAllNext(".panel").contains(L)!=true||areAnyNextSiblingsExpanded(M)!=true)){C.push(M)
}if(N.isCollapsed!=true&&(M.getAllNext(".panel").contains(L)!=true||areAnyNextSiblingsExpanded(M)!=true)){this.panelsHeight+=M.offsetHeight.toInt()
}}else{if(F=="expanding"){if(N.isCollapsed!=true&&(M.getAllNext(".panel").contains(L)!=true||(areAnyExpandingNextSiblingsExpanded()!=true&&M.getNext(".expanded")==L))&&M!=L){C.push(M)
}if(N.isCollapsed!=true&&(M.getAllNext(".panel").contains(L)!=true||(areAnyExpandingNextSiblingsExpanded()!=true&&M.getNext(".expanded")==L))&&M!=L){this.panelsHeight+=M.offsetHeight.toInt()
}}}}if(M.style.height){this.height+=M.getStyle("height").toInt()
}}else{this.height+=M.offsetHeight.toInt()
}}.bind(this));
var K=E.offsetHeight.toInt()-this.height;
this.height=0;
E.getChildren().each(function(M){this.height+=M.offsetHeight.toInt()
}.bind(this));
var K=E.offsetHeight.toInt()-this.height;
C.each(function(M){var O=this.panelsHeight/M.offsetHeight.toInt();
var N=M.getStyle("height").toInt()+(K/O);
if(N<1){N=0
}M.setStyle("height",N)
}.bind(this));
this.height=0;
E.getChildren().each(function(M){this.height+=M.offsetHeight.toInt();
if(M.hasClass("panel")&&M.getStyle("height").toInt()>A){D=M;
A=M.getStyle("height").toInt()
}}.bind(this));
var K=E.offsetHeight.toInt()-this.height;
if((K>0||K<0)&&A>0){D.setStyle("height",D.getStyle("height").toInt()+K);
if(D.getStyle("height")<1){D.setStyle("height",0)
}}$$(".columnHandle").each(function(N){var M=I.getStyle("height").toInt()-N.getStyle("border-top").toInt()-N.getStyle("border-bottom").toInt();
if(Browser.Engine.trident4){M-=1
}N.setStyle("height",M)
});
J.each(function(M){MochaUI.resizeChildren(M)
}.bind(this))
},resizeChildren:function(A){var D=MochaUI.Panels.instances;
var C=D.get(A.id);
var B=C.contentWrapperEl;
if(C.iframeEl){C.iframeEl.setStyles({height:B.getStyle("height"),width:B.offsetWidth-B.getStyle("border-left").toInt()-B.getStyle("border-right").toInt()})
}},rWidth:function(){$$(".rWidth").each(function(D){var A=D.offsetWidth.toInt();
A-=D.getStyle("border-left").toInt();
A-=D.getStyle("border-right").toInt();
var C=D.getParent();
this.width=0;
C.getChildren().each(function(F){if(F.hasClass("mocha")!=true){this.width+=F.offsetWidth.toInt()
}}.bind(this));
var B=C.offsetWidth.toInt()-this.width;
var E=A+B;
if(E<1){E=0
}D.setStyle("width",E);
D.getChildren(".panel").each(function(F){F.setStyle("width",E-F.getStyle("border-left").toInt()-F.getStyle("border-right").toInt());
MochaUI.resizeChildren(F)
}.bind(this))
})
}});
function addResizeRight(C,B,A){if(!$(C)){return 
}C=$(C);
var F=MochaUI.Columns.instances;
var E=F.get(C.id);
var D=C.getNext(".columnHandle");
D.setStyle("cursor","e-resize");
if(!B){B=50
}if(!A){A=250
}if(Browser.Engine.trident){D.addEvents({mousedown:function(){D.setCapture()
},mouseup:function(){D.releaseCapture()
}})
}E.resize=C.makeResizable({handle:D,modifiers:{x:"width",y:false},limit:{x:[B,A]},onStart:function(){C.getElements("iframe").setStyle("visibility","hidden");
C.getNext(".column").getElements("iframe").setStyle("visibility","hidden")
}.bind(this),onDrag:function(){MochaUI.rWidth();
if(Browser.Engine.trident4){C.getChildren().each(function(H){var G=$(C).getStyle("width").toInt();
G-=H.getStyle("border-right").toInt();
G-=H.getStyle("border-left").toInt();
G-=H.getStyle("padding-right").toInt();
G-=H.getStyle("padding-left").toInt();
H.setStyle("width",G)
}.bind(this))
}}.bind(this),onComplete:function(){MochaUI.rWidth();
C.getElements("iframe").setStyle("visibility","visible");
C.getNext(".column").getElements("iframe").setStyle("visibility","visible");
E.fireEvent("onResize")
}.bind(this)})
}function addResizeLeft(C,B,A){if(!$(C)){return 
}C=$(C);
var G=MochaUI.Columns.instances;
var F=G.get(C.id);
var E=C.getPrevious(".columnHandle");
E.setStyle("cursor","e-resize");
var D=C.getPrevious(".column");
if(!B){B=50
}if(!A){A=250
}if(Browser.Engine.trident){E.addEvents({mousedown:function(){E.setCapture()
},mouseup:function(){E.releaseCapture()
}})
}F.resize=C.makeResizable({handle:E,modifiers:{x:"width",y:false},invert:true,limit:{x:[B,A]},onStart:function(){$(C).getElements("iframe").setStyle("visibility","hidden");
D.getElements("iframe").setStyle("visibility","hidden")
}.bind(this),onDrag:function(){MochaUI.rWidth()
}.bind(this),onComplete:function(){MochaUI.rWidth();
$(C).getElements("iframe").setStyle("visibility","visible");
D.getElements("iframe").setStyle("visibility","visible");
F.fireEvent("onResize")
}.bind(this)})
}function addResizeBottom(A){if(!$(A)){return 
}var A=$(A);
var D=MochaUI.Panels.instances;
var C=D.get(A.id);
var B=C.handleEl;
B.setStyle("cursor","n-resize");
partner=C.partner;
min=0;
max=function(){return A.getStyle("height").toInt()+partner.getStyle("height").toInt()
}.bind(this);
if(Browser.Engine.trident){B.addEvents({mousedown:function(){B.setCapture()
},mouseup:function(){B.releaseCapture()
}})
}C.resize=A.makeResizable({handle:B,modifiers:{x:false,y:"height"},limit:{y:[min,max]},invert:false,onBeforeStart:function(){partner=C.partner;
this.originalHeight=A.getStyle("height").toInt();
this.partnerOriginalHeight=partner.getStyle("height").toInt()
}.bind(this),onStart:function(){if(C.iframeEl){C.iframeEl.setStyle("visibility","hidden")
}partner.getElements("iframe").setStyle("visibility","hidden")
}.bind(this),onDrag:function(){partnerHeight=partnerOriginalHeight+(this.originalHeight-A.getStyle("height").toInt());
partner.setStyle("height",partnerHeight);
MochaUI.resizeChildren(A,A.getStyle("height").toInt());
MochaUI.resizeChildren(partner,partnerHeight)
}.bind(this),onComplete:function(){partnerHeight=partnerOriginalHeight+(this.originalHeight-A.getStyle("height").toInt());
partner.setStyle("height",partnerHeight);
MochaUI.resizeChildren(A,A.getStyle("height").toInt());
MochaUI.resizeChildren(partner,partnerHeight);
if(C.iframeEl){C.iframeEl.setStyle("visibility","visible")
}partner.getElements("iframe").setStyle("visibility","visible");
C.fireEvent("onResize")
}.bind(this)})
}MochaUI.options.extend({dockWrapper:"dockWrapper",dock:"dock"});
window.addEvent("domready",function(){if($("dockWrapper")){MochaUI.dockVisible=true
}});
MochaUI.extend({minimizeAll:function(){$$("div.mocha").each(function(B){var A=MochaUI.Windows.instances.get(B.id);
if(!A.isMinimized&&A.options.minimizable==true){MochaUI.Dock.minimizeWindow(B)
}}.bind(this))
}});
MochaUI.Dock=new Class({Extends:MochaUI.Window,Implements:[Events,Options],options:{useControls:true,dockPosition:"top",dockTabColor:[255,255,255],trueButtonColor:[70,245,70],enabledButtonColor:[125,208,250],disabledButtonColor:[170,170,170]},initialize:function(A){if(!MochaUI.Desktop){return 
}this.setOptions(A);
this.dockWrapper=$(MochaUI.options.dockWrapper);
this.dock=$(MochaUI.options.dock);
this.autoHideEvent=null;
this.dockAutoHide=false;
if(!this.dockWrapper){return 
}if(!this.options.useControls){if($("dockPlacement")){$("dockPlacement").setStyle("cursor","default")
}if($("dockAutoHide")){$("dockAutoHide").setStyle("cursor","default")
}}this.dockWrapper.setStyles({display:"block",position:"absolute",top:null,bottom:MochaUI.Desktop.desktopFooter?MochaUI.Desktop.desktopFooter.offsetHeight:0,left:0});
if(this.options.useControls){this.initializeDockControls()
}if($("dockLinkCheck")){this.sidebarCheck=new Element("div",{"class":"check",id:"dock_check"}).inject($("dockLinkCheck"))
}this.dockSortables=new Sortables("#dockSort",{opacity:Browser.Engine.trident?1:0.5,constrain:true,clone:false,revert:false});
MochaUI.Desktop.setDesktopSize()
},initializeDockControls:function(){if(this.options.useControls){var C=new Element("canvas",{id:"dockCanvas",width:"15",height:"18"}).inject(this.dock);
if(Browser.Engine.trident&&MochaUI.ieSupport=="excanvas"){G_vmlCanvasManager.initElement(C)
}}var B=$("dockPlacement");
var D=$("dockAutoHide");
B.setProperty("title","Position Dock Top");
B.addEvent("click",function(){this.moveDock()
}.bind(this));
D.setProperty("title","Turn Auto Hide On");
D.addEvent("click",function(F){if(this.dockWrapper.getProperty("dockPosition")=="top"){return false
}var E=$("dockCanvas").getContext("2d");
this.dockAutoHide=!this.dockAutoHide;
if(this.dockAutoHide){$("dockAutoHide").setProperty("title","Turn Auto Hide Off");
MochaUI.circle(E,5,14,3,this.options.trueButtonColor,1);
this.autoHideEvent=function(H){if(!this.dockAutoHide){return 
}if(!MochaUI.Desktop.desktopFooter){var G=this.dockWrapper.offsetHeight;
if(G<25){G=25
}}else{if(MochaUI.Desktop.desktopFooter){var G=this.dockWrapper.offsetHeight+MochaUI.Desktop.desktopFooter.offsetHeight;
if(G<25){G=25
}}}if(!MochaUI.Desktop.desktopFooter&&H.client.y>(document.getCoordinates().height-G)){if(!MochaUI.dockVisible){this.dockWrapper.setStyle("display","block");
MochaUI.dockVisible=true;
MochaUI.Desktop.setDesktopSize()
}}else{if(MochaUI.Desktop.desktopFooter&&H.client.y>(document.getCoordinates().height-G)){if(!MochaUI.dockVisible){this.dockWrapper.setStyle("display","block");
MochaUI.dockVisible=true;
MochaUI.Desktop.setDesktopSize()
}}else{if(MochaUI.dockVisible){this.dockWrapper.setStyle("display","none");
MochaUI.dockVisible=false;
MochaUI.Desktop.setDesktopSize()
}}}}.bind(this);
document.addEvent("mousemove",this.autoHideEvent)
}else{$("dockAutoHide").setProperty("title","Turn Auto Hide On");
MochaUI.circle(E,5,14,3,this.options.enabledButtonColor,1);
document.removeEvent("mousemove",this.autoHideEvent)
}}.bind(this));
var A=$("dockCanvas").getContext("2d");
A.clearRect(0,0,100,100);
MochaUI.circle(A,5,4,3,this.options.enabledButtonColor,1);
MochaUI.circle(A,5,14,3,this.options.enabledButtonColor,1);
if(this.options.dockPosition=="top"){this.moveDock()
}},moveDock:function(){var A=$("dockCanvas").getContext("2d");
if(this.dockWrapper.getStyle("position")!="relative"){this.dockWrapper.setStyles({position:"relative",bottom:null});
this.dockWrapper.addClass("top");
MochaUI.Desktop.setDesktopSize();
this.dockWrapper.setProperty("dockPosition","top");
A.clearRect(0,0,100,100);
MochaUI.circle(A,5,4,3,this.options.enabledButtonColor,1);
MochaUI.circle(A,5,14,3,this.options.disabledButtonColor,1);
$("dockPlacement").setProperty("title","Position Dock Bottom");
$("dockAutoHide").setProperty("title","Auto Hide Disabled in Top Dock Position");
this.dockAutoHide=false
}else{this.dockWrapper.setStyles({position:"absolute",bottom:MochaUI.Desktop.desktopFooter?MochaUI.Desktop.desktopFooter.offsetHeight:0});
this.dockWrapper.removeClass("top");
MochaUI.Desktop.setDesktopSize();
this.dockWrapper.setProperty("dockPosition","bottom");
A.clearRect(0,0,100,100);
MochaUI.circle(A,5,4,3,this.options.enabledButtonColor,1);
MochaUI.circle(A,5,14,3,this.options.enabledButtonColor,1);
$("dockPlacement").setProperty("title","Position Dock Top");
$("dockAutoHide").setProperty("title","Turn Auto Hide On")
}},createDockTab:function(E){var D=MochaUI.Windows.instances.get(E.id);
var C=new Element("div",{id:D.options.id+"_dockTab","class":"dockTab",title:A}).inject($("dockClear"),"before");
C.addEvent("mousedown",function(F){new Event(F).stop();
this.timeDown=$time()
});
C.addEvent("mouseup",function(F){this.timeUp=$time();
if((this.timeUp-this.timeDown)<275){if(MochaUI.Windows.windowsVisible==false){MochaUI.toggleWindowVisibility();
if(D.isMinimized==true){MochaUI.Dock.restoreMinimized.delay(25,MochaUI.Dock,E)
}else{MochaUI.focusWindow(E)
}return 
}if(D.isMinimized==true){MochaUI.Dock.restoreMinimized.delay(25,MochaUI.Dock,E)
}else{if(D.windowEl.hasClass("isFocused")&&D.options.minimizable==true){MochaUI.Dock.minimizeWindow(E)
}else{MochaUI.focusWindow(E)
}var G=document.getCoordinates();
if(E.getStyle("left").toInt()>G.width||E.getStyle("top").toInt()>G.height){MochaUI.centerWindow(E)
}}}});
this.dockSortables.addItems(C);
var A=D.titleEl.innerHTML;
var B=new Element("div",{id:D.options.id+"_dockTabText","class":"dockText"}).set("html",A.substring(0,20)+(A.length>20?"...":"")).inject($(C));
if(D.options.icon!=false){}MochaUI.Desktop.setDesktopSize()
},makeActiveTab:function(){var C=MochaUI.getWindowWithHighestZindex();
var B=MochaUI.Windows.instances.get(C.id);
$$("div.dockTab").removeClass("activeDockTab");
if(B.isMinimized!=true){B.windowEl.addClass("isFocused");
var A=$(B.options.id+"_dockTab");
if(A!=null){A.addClass("activeDockTab")
}}else{B.windowEl.removeClass("isFocused")
}},minimizeWindow:function(C){if(C!=$(C)){return 
}var B=MochaUI.Windows.instances.get(C.id);
B.isMinimized=true;
if(B.iframeEl){B.iframeEl.setStyle("visibility","hidden")
}B.contentBorderEl.setStyle("visibility","hidden");
if(B.toolbarWrapperEl){B.toolbarWrapperEl.setStyle("visibility","hidden")
}C.setStyle("visibility","hidden");
if(Browser.Platform.mac&&Browser.Engine.gecko){if(/Firefox[\/\s](\d+\.\d+)/.test(navigator.userAgent)){var A=new Number(RegExp.$1);
if(A<3){B.contentWrapperEl.setStyle("overflow","hidden")
}}}MochaUI.Desktop.setDesktopSize();
setTimeout(function(){C.setStyle("zIndex",1);
C.removeClass("isFocused");
this.makeActiveTab()
}.bind(this),100);
B.fireEvent("onMinimize",C)
},restoreMinimized:function(B){var A=MochaUI.Windows.instances.get(B.id);
if(A.isMinimized==false){return 
}if(MochaUI.Windows.windowsVisible==false){MochaUI.toggleWindowVisibility()
}MochaUI.Desktop.setDesktopSize();
if(A.options.scrollbars==true&&!A.iframeEl){A.contentWrapperEl.setStyle("overflow","auto")
}if(A.isCollapsed){MochaUI.collapseToggle(B)
}B.setStyle("visibility","visible");
A.contentBorderEl.setStyle("visibility","visible");
if(A.toolbarWrapperEl){A.toolbarWrapperEl.setStyle("visibility","visible")
}if(A.iframeEl){A.iframeEl.setStyle("visibility","visible")
}A.isMinimized=false;
MochaUI.focusWindow(B);
A.fireEvent("onRestore",B)
}});
MochaUI.Dock.implement(new Options,new Events);
MochaUI.extend({saveWorkspace:function(){this.cookie=new Hash.Cookie("mochaUIworkspaceCookie",{duration:3600});
this.cookie.empty();
MochaUI.Windows.instances.each(function(A){A.saveValues();
this.cookie.set(A.options.id,{id:A.options.id,top:A.options.y,left:A.options.x})
}.bind(this));
this.cookie.save();
new MochaUI.Window({loadMethod:"html",type:"notification",addClass:"notification",content:"Workspace saved.",closeAfter:"1400",width:200,height:40,y:53,padding:{top:10,right:12,bottom:10,left:12},shadowBlur:5,bodyBgColor:[255,255,255]})
},windowUnload:function(){if($$("div.mocha").length==0&&this.myChain){this.myChain.callChain()
}},loadWorkspace2:function(workspaceWindows){workspaceWindows.each(function(instance){windowFunction=eval("MochaUI."+instance.id+"Window");
if(windowFunction){eval("MochaUI."+instance.id+"Window();");
$(instance.id).setStyles({top:instance.top,left:instance.left})
}}.bind(this));
this.loadingWorkspace=false
},loadWorkspace:function(){cookie=new Hash.Cookie("mochaUIworkspaceCookie",{duration:3600});
workspaceWindows=cookie.load();
if(!cookie.getKeys().length){new MochaUI.Window({loadMethod:"html",type:"notification",addClass:"notification",content:"You have no saved workspace.",closeAfter:"1400",width:220,height:40,y:25,padding:{top:10,right:12,bottom:10,left:12},shadowBlur:5,bodyBgColor:[255,255,255]});
return 
}if($$("div.mocha").length!=0){this.loadingWorkspace=true;
this.myChain=new Chain();
this.myChain.chain(function(){$$("div.mocha").each(function(A){this.closeWindow(A)
}.bind(this))
}.bind(this),function(){this.loadWorkspace2(workspaceWindows)
}.bind(this));
this.myChain.callChain()
}else{this.loadWorkspace2(workspaceWindows)
}}});initializeWindows=function(){MochaUI.aboutGeomatys=function(){new MochaUI.Window({id:"aboutGeomatys",title:"About Geomatys",loadMethod:"xhr",contentURL:"AboutGeomatys.jsf",width:400,height:500})
};
if($("aboutGeomatysLink")){$("aboutGeomatysLink").addEvent("click",function(A){new Event(A).stop();
MochaUI.aboutGeomatys()
})
}MochaUI.loggerPopup=function(){new MochaUI.Window({id:"sdnlogger",title:"Logger",loadMethod:"iframe",contentURL:"logger.jsf",width:450,height:500})
};
if($("loggerpopup")){$("loggerpopup").addEvent("click",function(A){new Event(A).stop();
MochaUI.loggerPopup()
})
}if($("sidebarLinkCheck")){$("sidebarLinkCheck").addEvent("click",function(A){new Event(A).stop();
MochaUI.Desktop.sidebarToggle()
})
}if($("cascadeLink")){$("cascadeLink").addEvent("click",function(A){new Event(A).stop();
MochaUI.arrangeCascade()
})
}if($("tileLink")){$("tileLink").addEvent("click",function(A){new Event(A).stop();
MochaUI.arrangeTile()
})
}if($("closeLink")){$("closeLink").addEvent("click",function(A){new Event(A).stop();
MochaUI.closeAll()
})
}if($("minimizeLink")){$("minimizeLink").addEvent("click",function(A){new Event(A).stop();
MochaUI.minimizeAll()
})
}if($("saveWorkspaceLink")){$("saveWorkspaceLink").addEvent("click",function(A){new Event(A).stop();
MochaUI.saveWorkspace()
})
}if($("loadWorkspaceLink")){$("loadWorkspaceLink").addEvent("click",function(A){new Event(A).stop();
MochaUI.loadWorkspace()
})
}$$("a.returnFalse").each(function(A){A.addEvent("click",function(B){new Event(B).stop()
})
})
};var _Home_Tabs=false;
var _App_Tabs=false;
window.addEvent("domready",function(){MochaUI.Desktop=new MochaUI.Desktop();
MochaUI.Dock=new MochaUI.Dock({dockPosition:"bottom"});
MochaUI.Modal=new MochaUI.Modal();
MochaUI.NewWindowsFromHTML=new MochaUI.NewWindowsFromHTML();
initializeWindows();
homePageWrapper();
MochaUI.Desktop.desktop.setStyles({visibility:"visible"})
});
window.addEvent("load",function(){});
window.addEvent("unload",function(){if(MochaUI){MochaUI.garbageCleanUp()
}});
function clearPageWrapper(){if($("pageWrapper")!=null){while($("pageWrapper").hasChildNodes()){$("pageWrapper").removeChild($("pageWrapper").lastChild)
}}$$(".mocha").each(function(A){A.style.display="block"
});
if($("dockSort")!=null){$("dockSort").getChildren().each(function(A){A.style.display="block"
})
}}function homePageWrapper(){clearPageWrapper();
new MochaUI.Column({id:"mainHomeColumn",placement:"main"});
$$(".mochaMainHomeColumn").each(function(A){A.inject($("mainHomeColumn"))
});
$("mainHomeColumn").style.background="";
$$(".mocha").each(function(A){A.style.display="block"
});
$("dockSort").getChildren().each(function(A){A.style.display="block"
});
MochaUI.Desktop.desktop.setStyles({visibility:"visible"})
}function webAppPageWrapper(){clearPageWrapper();
new MochaUI.Column({id:"sideColumn1",placement:"left",width:290,resizeLimit:[290,500]});
new MochaUI.Column({id:"mainColumn",placement:"main",width:null});
new MochaUI.Panel({id:"panel1",title:"Layer Control",column:"sideColumn1",height:420,padding:{top:0,right:0,bottom:0,left:0}});
new MochaUI.Panel({id:"panel2",title:"Panel",column:"sideColumn1",padding:{top:0,right:0,bottom:0,left:0}});
MochaUI.Desktop.desktop.setStyles({visibility:"visible"});
$$(".mochaMainAppColumn").each(function(A){A.inject($("mainColumn"))
});
$$(".mochaPanel1").each(function(A){A.inject($("panel1_pad"))
});
$$(".mochaPanel2").each(function(A){A.inject($("panel2_pad"))
});
$("panel1_pad").setStyles({height:"100%",width:"100%"});
$("panel2_pad").setStyles({height:"100%",width:"100%"})
};if(typeof (AC)=="undefined"){AC={}
}AC.Detector={getAgent:function(){return navigator.userAgent.toLowerCase()
},isMac:function(B){var A=B||this.getAgent();
return A.match(/mac/i)
},isWin:function(B){var A=B||this.getAgent();
return A.match(/win/i)
},isWin2k:function(B){var A=B||this.getAgent();
return this.isWin(A)&&(A.match(/nt\s*5/i))
},isWinVista:function(B){var A=B||this.getAgent();
return this.isWin(A)&&(A.match(/nt\s*6/i))
},isWebKit:function(B){var A=B||this.getAgent();
return A.match(/AppleWebKit/i)
},isOpera:function(B){var A=B||this.getAgent();
return A.match(/opera/i)
},isIE:function(B){var A=B||this.getAgent();
return A.match(/msie/i)
},isIEStrict:function(B){var A=B||this.getAgent();
return A.match(/msie/i)&&!this.isOpera(A)
},isFirefox:function(B){var A=B||this.getAgent();
return A.match(/firefox/i)
},isChrome:function(B){var A=B||this.getAgent();
return A.match(/chrome/i)
}};