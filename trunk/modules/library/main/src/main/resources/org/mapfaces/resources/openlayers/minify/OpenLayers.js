var OpenLayers={singleFile:true};

(function(){
var _1=(typeof OpenLayers=="object"&&OpenLayers.singleFile);
window.OpenLayers={_scriptName:(!_1)?"openlayers/custom/OpenLayers.js":"OpenLayers.js",_getScriptLocation:function(){
var _2="";
var _3=OpenLayers._scriptName;
var _4=document.getElementsByTagName("script");
for(var i=0;i<_4.length;i++){
var _6=_4[i].getAttribute("src");
if(_6){
var _7=_6.lastIndexOf(_3);
var _8=-1;
if(_8<0){
_8=_6.length;
}
if((_7>-1)&&(_7+_3.length==_8)){
_2=_6.slice(0,_8-_3.length);
break;
}
}
}
return _2;
}};
if(!_1){
var _9=new Array("openlayers/lib/OpenLayers/Util.js","openlayers/lib/OpenLayers/BaseTypes.js","openlayers/lib/OpenLayers/BaseTypes/Class.js","openlayers/lib/OpenLayers/BaseTypes/Bounds.js","openlayers/lib/OpenLayers/BaseTypes/Element.js","openlayers/lib/OpenLayers/BaseTypes/LonLat.js","openlayers/lib/OpenLayers/BaseTypes/Pixel.js","openlayers/lib/OpenLayers/BaseTypes/Size.js","openlayers/lib/OpenLayers/Console.js","openlayers/lib/OpenLayers/Tween.js","openlayers/lib/Rico/Corner.js","openlayers/lib/Rico/Color.js","openlayers/lib/OpenLayers/Ajax.js","openlayers/lib/OpenLayers/Request.js","openlayers/lib/OpenLayers/Request/XMLHttpRequest.js","openlayers/lib/OpenLayers/Events.js","openlayers/lib/OpenLayers/Projection.js","proj4js/proj4js-combined.js","openlayers/custom/OpenLayers/Map.js","openlayers/lib/OpenLayers/Layer.js","openlayers/lib/OpenLayers/Icon.js","openlayers/lib/OpenLayers/Marker.js","openlayers/lib/OpenLayers/Marker/Box.js","openlayers/lib/OpenLayers/Popup.js","openlayers/lib/OpenLayers/Layer/Markers.js","openlayers/lib/OpenLayers/Layer/Text.js","openlayers/lib/OpenLayers/Layer/GeoRSS.js","openlayers/lib/OpenLayers/Layer/Boxes.js","openlayers/lib/OpenLayers/Popup/Anchored.js","openlayers/lib/OpenLayers/Popup/AnchoredBubble.js","openlayers/lib/OpenLayers/Popup/Framed.js","openlayers/lib/OpenLayers/Popup/FramedCloud.js","openlayers/lib/OpenLayers/Feature.js","openlayers/lib/OpenLayers/Feature/Vector.js","openlayers/lib/OpenLayers/Feature/WFS.js","openlayers/lib/OpenLayers/Handler.js","openlayers/lib/OpenLayers/Handler/Click.js","openlayers/lib/OpenLayers/Handler/Hover.js","openlayers/lib/OpenLayers/Handler/Point.js","openlayers/lib/OpenLayers/Handler/Path.js","openlayers/lib/OpenLayers/Handler/Polygon.js","openlayers/lib/OpenLayers/Handler/Feature.js","openlayers/lib/OpenLayers/Handler/Drag.js","openlayers/lib/OpenLayers/Handler/RegularPolygon.js","openlayers/lib/OpenLayers/Handler/Box.js","openlayers/custom/OpenLayers/Handler/MouseWheel.js","openlayers/lib/OpenLayers/Handler/Keyboard.js","openlayers/lib/OpenLayers/Control.js","openlayers/lib/OpenLayers/Control/Attribution.js","openlayers/lib/OpenLayers/Control/Button.js","openlayers/lib/OpenLayers/Control/ZoomBox.js","openlayers/custom/OpenLayers/Control/ZoomBoxOut.js","openlayers/lib/OpenLayers/Control/ZoomToMaxExtent.js","openlayers/lib/OpenLayers/Control/DragPan.js","openlayers/custom/OpenLayers/Control/Navigation.js","openlayers/lib/OpenLayers/Control/MouseDefaults.js","openlayers/custom/OpenLayers/Control/MousePosition.js","openlayers/lib/OpenLayers/Control/OverviewMap.js","openlayers/lib/OpenLayers/Control/KeyboardDefaults.js","openlayers/custom/OpenLayers/Control/MouseWheelDefaults.js","openlayers/lib/OpenLayers/Control/PanZoom.js","openlayers/lib/OpenLayers/Control/PanZoomBar.js","openlayers/lib/OpenLayers/Control/ArgParser.js","openlayers/lib/OpenLayers/Control/Permalink.js","openlayers/lib/OpenLayers/Control/Scale.js","openlayers/lib/OpenLayers/Control/ScaleLine.js","openlayers/lib/OpenLayers/Control/LayerSwitcher.js","openlayers/lib/OpenLayers/Control/DrawFeature.js","openlayers/lib/OpenLayers/Control/DragFeature.js","openlayers/lib/OpenLayers/Control/ModifyFeature.js","openlayers/lib/OpenLayers/Control/Panel.js","openlayers/lib/OpenLayers/Control/SelectFeature.js","openlayers/lib/OpenLayers/Control/NavigationHistory.js","openlayers/lib/OpenLayers/Geometry.js","openlayers/lib/OpenLayers/Geometry/Rectangle.js","openlayers/lib/OpenLayers/Geometry/Collection.js","openlayers/lib/OpenLayers/Geometry/Point.js","openlayers/lib/OpenLayers/Geometry/MultiPoint.js","openlayers/lib/OpenLayers/Geometry/Curve.js","openlayers/lib/OpenLayers/Geometry/LineString.js","openlayers/lib/OpenLayers/Geometry/LinearRing.js","openlayers/lib/OpenLayers/Geometry/Polygon.js","openlayers/lib/OpenLayers/Geometry/MultiLineString.js","openlayers/lib/OpenLayers/Geometry/MultiPolygon.js","openlayers/lib/OpenLayers/Geometry/Surface.js","openlayers/lib/OpenLayers/Renderer.js","openlayers/lib/OpenLayers/Renderer/Elements.js","openlayers/lib/OpenLayers/Renderer/SVG.js","openlayers/lib/OpenLayers/Renderer/VML.js","openlayers/lib/OpenLayers/Layer/Vector.js","openlayers/lib/OpenLayers/Layer/PointTrack.js","openlayers/lib/OpenLayers/Layer/GML.js","openlayers/lib/OpenLayers/Style.js","openlayers/lib/OpenLayers/StyleMap.js","openlayers/lib/OpenLayers/Rule.js","openlayers/lib/OpenLayers/Filter.js","openlayers/lib/OpenLayers/Filter/FeatureId.js","openlayers/lib/OpenLayers/Filter/Logical.js","openlayers/lib/OpenLayers/Filter/Comparison.js","openlayers/lib/OpenLayers/Format.js","openlayers/lib/OpenLayers/Format/XML.js","openlayers/lib/OpenLayers/Format/GML.js","openlayers/lib/OpenLayers/Format/KML.js","openlayers/lib/OpenLayers/Format/GeoRSS.js","openlayers/lib/OpenLayers/Format/OSM.js","openlayers/lib/OpenLayers/Format/SLD.js","openlayers/lib/OpenLayers/Format/SLD/v1.js","openlayers/lib/OpenLayers/Format/SLD/v1_0_0.js","openlayers/lib/OpenLayers/Format/Text.js","openlayers/lib/OpenLayers/Format/JSON.js","openlayers/custom/OpenLayers/Format/GeoJSON.js","openlayers/lib/OpenLayers/Layer/WFS.js","openlayers/lib/OpenLayers/Control/MouseToolbar.js","openlayers/custom/OpenLayers/Control/NavToolbar.js","openlayers/lib/OpenLayers/Control/EditingToolbar.js","openlayers/lib/OpenLayers/Lang.js","openlayers/lib/OpenLayers/Lang/en.js");
var _a=navigator.userAgent;
var _b=(_a.match("MSIE")||_a.match("Safari"));
if(_b){
var _c=new Array(_9.length);
}
var _d=OpenLayers._getScriptLocation();
for(var i=0;i<_9.length;i++){
if(_b){
_c[i]="<script src='"+_d+_9[i]+"'></script>";
}else{
var s=document.createElement("script");
s.src=_d+_9[i];
var h=document.getElementsByTagName("head").length?document.getElementsByTagName("head")[0]:document.body;
h.appendChild(s);
}
}
if(_b){
document.write(_c.join(""));
}
}
})();
OpenLayers.VERSION_NUMBER="$Revision: 7335 $";

OpenLayers.Util={};
OpenLayers.Util.getElement=function(){
var _1=[];
for(var i=0;i<arguments.length;i++){
var _3=arguments[i];
if(typeof _3=="string"){
_3=document.getElementById(_3);
}
if(arguments.length==1){
return _3;
}
_1.push(_3);
}
return _1;
};
if($==null){
var $=OpenLayers.Util.getElement;
}
OpenLayers.Util.extend=function(_4,_5){
_4=_4||{};
if(_5){
for(var _6 in _5){
var _7=_5[_6];
if(_7!==undefined){
_4[_6]=_7;
}
}
var _8=typeof window.Event=="function"&&_5 instanceof window.Event;
if(!_8&&_5.hasOwnProperty&&_5.hasOwnProperty("toString")){
_4.toString=_5.toString;
}
}
return _4;
};
OpenLayers.Util.removeItem=function(_9,_a){
for(var i=_9.length-1;i>=0;i--){
if(_9[i]==_a){
_9.splice(i,1);
}
}
return _9;
};
OpenLayers.Util.clearArray=function(_c){
OpenLayers.Console.warn(OpenLayers.i18n("methodDeprecated",{"newMethod":"array = []"}));
_c.length=0;
};
OpenLayers.Util.indexOf=function(_d,_e){
for(var i=0;i<_d.length;i++){
if(_d[i]==_e){
return i;
}
}
return -1;
};
OpenLayers.Util.modifyDOMElement=function(_10,id,px,sz,_14,_15,_16,_17){
if(id){
_10.id=id;
}
if(px){
_10.style.left=px.x+"px";
_10.style.top=px.y+"px";
}
if(sz){
_10.style.width=sz.w+"px";
_10.style.height=sz.h+"px";
}
if(_14){
_10.style.position=_14;
}
if(_15){
_10.style.border=_15;
}
if(_16){
_10.style.overflow=_16;
}
if(parseFloat(_17)>=0&&parseFloat(_17)<1){
_10.style.filter="alpha(opacity="+(_17*100)+")";
_10.style.opacity=_17;
}else{
if(parseFloat(_17)==1){
_10.style.filter="";
_10.style.opacity="";
}
}
};
OpenLayers.Util.createDiv=function(id,px,sz,_1b,_1c,_1d,_1e,_1f){
var dom=document.createElement("div");
if(_1b){
dom.style.backgroundImage="url("+_1b+")";
}
if(!id){
id=OpenLayers.Util.createUniqueID("OpenLayersDiv");
}
if(!_1c){
_1c="absolute";
}
OpenLayers.Util.modifyDOMElement(dom,id,px,sz,_1c,_1d,_1e,_1f);
return dom;
};
OpenLayers.Util.createImage=function(id,px,sz,_24,_25,_26,_27,_28){
var _29=document.createElement("img");
if(!id){
id=OpenLayers.Util.createUniqueID("OpenLayersDiv");
}
if(!_25){
_25="relative";
}
OpenLayers.Util.modifyDOMElement(_29,id,px,sz,_25,_26,null,_27);
if(_28){
_29.style.display="none";
OpenLayers.Event.observe(_29,"load",OpenLayers.Function.bind(OpenLayers.Util.onImageLoad,_29));
OpenLayers.Event.observe(_29,"error",OpenLayers.Function.bind(OpenLayers.Util.onImageLoadError,_29));
}
_29.style.alt=id;
_29.galleryImg="no";
if(_24){
_29.src=_24;
}
return _29;
};
OpenLayers.Util.setOpacity=function(_2a,_2b){
OpenLayers.Util.modifyDOMElement(_2a,null,null,null,null,null,null,_2b);
};
OpenLayers.Util.onImageLoad=function(){
if(!this.viewRequestID||(this.map&&this.viewRequestID==this.map.viewRequestID)){
this.style.backgroundColor=null;
this.style.display="";
}
};
OpenLayers.Util.onImageLoadErrorColor="pink";
OpenLayers.IMAGE_RELOAD_ATTEMPTS=0;
OpenLayers.Util.onImageLoadError=function(){
this._attempts=(this._attempts)?(this._attempts+1):1;
if(this._attempts<=OpenLayers.IMAGE_RELOAD_ATTEMPTS){
this.src=this.src;
}else{
this.style.backgroundColor=OpenLayers.Util.onImageLoadErrorColor;
}
this.style.display="";
};
OpenLayers.Util.alphaHack=function(){
var _2c=navigator.appVersion.split("MSIE");
var _2d=parseFloat(_2c[1]);
var _2e=false;
try{
_2e=!!(document.body.filters);
}
catch(e){
}
return (_2e&&(_2d>=5.5)&&(_2d<7));
};
OpenLayers.Util.modifyAlphaImageDiv=function(div,id,px,sz,_33,_34,_35,_36,_37){
OpenLayers.Util.modifyDOMElement(div,id,px,sz,_34,null,null,_37);
var img=div.childNodes[0];
if(_33){
img.src=_33;
}
OpenLayers.Util.modifyDOMElement(img,div.id+"_innerImage",null,sz,"relative",_35);
if(OpenLayers.Util.alphaHack()){
if(div.style.display!="none"){
div.style.display="inline-block";
}
if(_36==null){
_36="scale";
}
div.style.filter="progid:DXImageTransform.Microsoft"+".AlphaImageLoader(src='"+img.src+"', "+"sizingMethod='"+_36+"')";
if(parseFloat(div.style.opacity)>=0&&parseFloat(div.style.opacity)<1){
div.style.filter+=" alpha(opacity="+div.style.opacity*100+")";
}
img.style.filter="alpha(opacity=0)";
}
};
OpenLayers.Util.createAlphaImageDiv=function(id,px,sz,_3c,_3d,_3e,_3f,_40,_41){
var div=OpenLayers.Util.createDiv();
var img=OpenLayers.Util.createImage(null,null,null,null,null,null,null,false);
div.appendChild(img);
if(_41){
img.style.display="none";
OpenLayers.Event.observe(img,"load",OpenLayers.Function.bind(OpenLayers.Util.onImageLoad,div));
OpenLayers.Event.observe(img,"error",OpenLayers.Function.bind(OpenLayers.Util.onImageLoadError,div));
}
OpenLayers.Util.modifyAlphaImageDiv(div,id,px,sz,_3c,_3d,_3e,_3f,_40);
return div;
};
OpenLayers.Util.upperCaseObject=function(_44){
var _45={};
for(var key in _44){
_45[key.toUpperCase()]=_44[key];
}
return _45;
};
OpenLayers.Util.applyDefaults=function(to,_48){
to=to||{};
var _49=typeof window.Event=="function"&&_48 instanceof window.Event;
for(var key in _48){
if(to[key]===undefined||(!_49&&_48.hasOwnProperty&&_48.hasOwnProperty(key)&&!to.hasOwnProperty(key))){
to[key]=_48[key];
}
}
if(!_49&&_48.hasOwnProperty&&_48.hasOwnProperty("toString")&&!to.hasOwnProperty("toString")){
to.toString=_48.toString;
}
return to;
};
OpenLayers.Util.getParameterString=function(_4b){
var _4c=[];
for(var key in _4b){
var _4e=_4b[key];
if((_4e!=null)&&(typeof _4e!="function")){
var _4f;
if(typeof _4e=="object"&&_4e.constructor==Array){
var _50=[];
for(var _51=0;_51<_4e.length;_51++){
_50.push(encodeURIComponent(_4e[_51]));
}
_4f=_50.join(",");
}else{
_4f=encodeURIComponent(_4e);
}
_4c.push(encodeURIComponent(key)+"="+_4f);
}
}
return _4c.join("&");
};
OpenLayers.ImgPath="";
OpenLayers.Util.getImagesLocation=function(){
return OpenLayers.ImgPath||(OpenLayers._getScriptLocation()+"img/");
};
OpenLayers.Util.Try=function(){
var _52=null;
for(var i=0;i<arguments.length;i++){
var _54=arguments[i];
try{
_52=_54();
break;
}
catch(e){
}
}
return _52;
};
OpenLayers.Util.getNodes=function(p,_56){
var _57=OpenLayers.Util.Try(function(){
return OpenLayers.Util._getNodes(p.documentElement.childNodes,_56);
},function(){
return OpenLayers.Util._getNodes(p.childNodes,_56);
});
return _57;
};
OpenLayers.Util._getNodes=function(_58,_59){
var _5a=[];
for(var i=0;i<_58.length;i++){
if(_58[i].nodeName==_59){
_5a.push(_58[i]);
}
}
return _5a;
};
OpenLayers.Util.getTagText=function(_5c,_5d,_5e){
var _5f=OpenLayers.Util.getNodes(_5c,_5d);
if(_5f&&(_5f.length>0)){
if(!_5e){
_5e=0;
}
if(_5f[_5e].childNodes.length>1){
return _5f.childNodes[1].nodeValue;
}else{
if(_5f[_5e].childNodes.length==1){
return _5f[_5e].firstChild.nodeValue;
}
}
}else{
return "";
}
};
OpenLayers.Util.getXmlNodeValue=function(_60){
var val=null;
OpenLayers.Util.Try(function(){
val=_60.text;
if(!val){
val=_60.textContent;
}
if(!val){
val=_60.firstChild.nodeValue;
}
},function(){
val=_60.textContent;
});
return val;
};
OpenLayers.Util.mouseLeft=function(evt,div){
var _64=(evt.relatedTarget)?evt.relatedTarget:evt.toElement;
while(_64!=div&&_64!=null){
_64=_64.parentNode;
}
return (_64!=div);
};
OpenLayers.Util.rad=function(x){
return x*Math.PI/180;
};
OpenLayers.Util.distVincenty=function(p1,p2){
var a=6378137,b=6356752.3142,f=1/298.257223563;
var L=OpenLayers.Util.rad(p2.lon-p1.lon);
var U1=Math.atan((1-f)*Math.tan(OpenLayers.Util.rad(p1.lat)));
var U2=Math.atan((1-f)*Math.tan(OpenLayers.Util.rad(p2.lat)));
var _6c=Math.sin(U1),cosU1=Math.cos(U1);
var _6d=Math.sin(U2),cosU2=Math.cos(U2);
var _6e=L,lambdaP=2*Math.PI;
var _6f=20;
while(Math.abs(_6e-lambdaP)>1e-12&&--_6f>0){
var _70=Math.sin(_6e),cosLambda=Math.cos(_6e);
var _71=Math.sqrt((cosU2*_70)*(cosU2*_70)+(cosU1*_6d-_6c*cosU2*cosLambda)*(cosU1*_6d-_6c*cosU2*cosLambda));
if(_71==0){
return 0;
}
var _72=_6c*_6d+cosU1*cosU2*cosLambda;
var _73=Math.atan2(_71,_72);
var _74=Math.asin(cosU1*cosU2*_70/_71);
var _75=Math.cos(_74)*Math.cos(_74);
var _76=_72-2*_6c*_6d/_75;
var C=f/16*_75*(4+f*(4-3*_75));
lambdaP=_6e;
_6e=L+(1-C)*f*Math.sin(_74)*(_73+C*_71*(_76+C*_72*(-1+2*_76*_76)));
}
if(_6f==0){
return NaN;
}
var uSq=_75*(a*a-b*b)/(b*b);
var A=1+uSq/16384*(4096+uSq*(-768+uSq*(320-175*uSq)));
var B=uSq/1024*(256+uSq*(-128+uSq*(74-47*uSq)));
var _7b=B*_71*(_76+B/4*(_72*(-1+2*_76*_76)-B/6*_76*(-3+4*_71*_71)*(-3+4*_76*_76)));
var s=b*A*(_73-_7b);
var d=s.toFixed(3)/1000;
return d;
};
OpenLayers.Util.getParameters=function(url){
url=url||window.location.href;
var _7f="";
if(OpenLayers.String.contains(url,"?")){
var _80=url.indexOf("?")+1;
var end=OpenLayers.String.contains(url,"#")?url.indexOf("#"):url.length;
_7f=url.substring(_80,end);
}
var _82={};
var _83=_7f.split(/[&;]/);
for(var i=0;i<_83.length;++i){
var _85=_83[i].split("=");
if(_85[0]){
var key=decodeURIComponent(_85[0]);
var _87=_85[1]||"";
_87=_87.split(",");
for(var j=0;j<_87.length;j++){
_87[j]=decodeURIComponent(_87[j]);
}
if(_87.length==1){
_87=_87[0];
}
_82[key]=_87;
}
}
return _82;
};
OpenLayers.Util.getArgs=function(url){
OpenLayers.Console.warn(OpenLayers.i18n("methodDeprecated",{"newMethod":"OpenLayers.Util.getParameters"}));
return OpenLayers.Util.getParameters(url);
};
OpenLayers.Util.lastSeqID=0;
OpenLayers.Util.createUniqueID=function(_8a){
if(_8a==null){
_8a="id_";
}
OpenLayers.Util.lastSeqID+=1;
return _8a+OpenLayers.Util.lastSeqID;
};
OpenLayers.INCHES_PER_UNIT={"inches":1,"ft":12,"mi":63360,"m":39.3701,"km":39370.1,"dd":4374754,"yd":36};
OpenLayers.INCHES_PER_UNIT["in"]=OpenLayers.INCHES_PER_UNIT.inches;
OpenLayers.INCHES_PER_UNIT["degrees"]=OpenLayers.INCHES_PER_UNIT.dd;
OpenLayers.INCHES_PER_UNIT["nmi"]=1852*OpenLayers.INCHES_PER_UNIT.m;
OpenLayers.DOTS_PER_INCH=72;
OpenLayers.Util.normalizeScale=function(_8b){
var _8c=(_8b>1)?(1/_8b):_8b;
return _8c;
};
OpenLayers.Util.getResolutionFromScale=function(_8d,_8e){
if(_8e==null){
_8e="degrees";
}
var _8f=OpenLayers.Util.normalizeScale(_8d);
var _90=1/(_8f*OpenLayers.INCHES_PER_UNIT[_8e]*OpenLayers.DOTS_PER_INCH);
return _90;
};
OpenLayers.Util.getScaleFromResolution=function(_91,_92){
if(_92==null){
_92="degrees";
}
var _93=_91*OpenLayers.INCHES_PER_UNIT[_92]*OpenLayers.DOTS_PER_INCH;
return _93;
};
OpenLayers.Util.safeStopPropagation=function(evt){
OpenLayers.Event.stop(evt,true);
};
OpenLayers.Util.pagePosition=function(_95){
var _96=0,valueL=0;
var _97=_95;
var _98=_95;
while(_97){
if(_97==document.body){
if(_98&&_98.style&&OpenLayers.Element.getStyle(_98,"position")=="absolute"){
break;
}
}
_96+=_97.offsetTop||0;
valueL+=_97.offsetLeft||0;
_98=_97;
try{
_97=_97.offsetParent;
}
catch(e){
OpenLayers.Console.error(OpenLayers.i18n("pagePositionFailed",{"elemId":_97.id}));
break;
}
}
_97=_95;
while(_97){
_96-=_97.scrollTop||0;
valueL-=_97.scrollLeft||0;
_97=_97.parentNode;
}
return [valueL,_96];
};
OpenLayers.Util.isEquivalentUrl=function(_99,_9a,_9b){
_9b=_9b||{};
OpenLayers.Util.applyDefaults(_9b,{ignoreCase:true,ignorePort80:true,ignoreHash:true});
var _9c=OpenLayers.Util.createUrlObject(_99,_9b);
var _9d=OpenLayers.Util.createUrlObject(_9a,_9b);
for(var key in _9c){
if(_9b.test){
alert(key+"\n1:"+_9c[key]+"\n2:"+_9d[key]);
}
var _9f=_9c[key];
var _a0=_9d[key];
switch(key){
case "args":
break;
case "host":
case "port":
case "protocol":
if((_9f=="")||(_a0=="")){
break;
}
default:
if((key!="args")&&(_9c[key]!=_9d[key])){
return false;
}
break;
}
}
for(var key in _9c.args){
if(_9c.args[key]!=_9d.args[key]){
return false;
}
delete _9d.args[key];
}
for(var key in _9d.args){
return false;
}
return true;
};
OpenLayers.Util.createUrlObject=function(url,_a2){
_a2=_a2||{};
var _a3={};
if(_a2.ignoreCase){
url=url.toLowerCase();
}
var a=document.createElement("a");
a.href=url;
_a3.host=a.host;
var _a5=a.port;
if(_a5.length<=0){
var _a6=_a3.host.length-(_a5.length);
_a3.host=_a3.host.substring(0,_a6);
}
_a3.protocol=a.protocol;
_a3.port=((_a5=="80")&&(_a2.ignorePort80))?"":_a5;
_a3.hash=(_a2.ignoreHash)?"":a.hash;
var _a7=a.search;
if(!_a7){
var _a8=url.indexOf("?");
_a7=(_a8!=-1)?url.substr(_a8):"";
}
_a3.args=OpenLayers.Util.getParameters(_a7);
if(((_a3.protocol=="file:")&&(url.indexOf("file:")!=-1))||((_a3.protocol!="file:")&&(_a3.host!=""))){
_a3.pathname=a.pathname;
var _a9=_a3.pathname.indexOf("?");
if(_a9!=-1){
_a3.pathname=_a3.pathname.substring(0,_a9);
}
}else{
var _aa=OpenLayers.Util.removeTail(url);
var _ab=0;
do{
var _ac=_aa.indexOf("../");
if(_ac==0){
_ab++;
_aa=_aa.substr(3);
}else{
if(_ac>=0){
var _ad=_aa.substr(0,_ac-1);
var _ae=_ad.indexOf("/");
_ad=(_ae!=-1)?_ad.substr(0,_ae+1):"";
var _af=_aa.substr(_ac+3);
_aa=_ad+_af;
}
}
}while(_ac!=-1);
var _b0=document.createElement("a");
var _b1=window.location.href;
if(_a2.ignoreCase){
_b1=_b1.toLowerCase();
}
_b0.href=_b1;
_a3.protocol=_b0.protocol;
var _b2=(_b0.pathname.indexOf("/")!=-1)?"/":"\\";
var _b3=_b0.pathname.split(_b2);
_b3.pop();
while((_ab>0)&&(_b3.length>0)){
_b3.pop();
_ab--;
}
_aa=_b3.join("/")+"/"+_aa;
_a3.pathname=_aa;
}
if((_a3.protocol=="file:")||(_a3.protocol=="")){
_a3.host="localhost";
}
return _a3;
};
OpenLayers.Util.removeTail=function(url){
var _b5=null;
var _b6=url.indexOf("?");
var _b7=url.indexOf("#");
if(_b6==-1){
_b5=(_b7!=-1)?url.substr(0,_b7):url;
}else{
_b5=(_b7!=-1)?url.substr(0,Math.min(_b6,_b7)):url.substr(0,_b6);
}
return _b5;
};
OpenLayers.Util.getBrowserName=function(){
var _b8="";
var ua=navigator.userAgent.toLowerCase();
if(ua.indexOf("opera")!=-1){
_b8="opera";
}else{
if(ua.indexOf("msie")!=-1){
_b8="msie";
}else{
if(ua.indexOf("safari")!=-1){
_b8="safari";
}else{
if(ua.indexOf("mozilla")!=-1){
if(ua.indexOf("firefox")!=-1){
_b8="firefox";
}else{
_b8="mozilla";
}
}
}
}
}
return _b8;
};
OpenLayers.Util.getRenderedDimensions=function(_ba,_bb){
var w,h;
var _bd=document.createElement("div");
_bd.style.overflow="";
_bd.style.position="absolute";
_bd.style.left="-9999px";
if(_bb){
if(_bb.w){
w=_bb.w;
_bd.style.width=w+"px";
}else{
if(_bb.h){
h=_bb.h;
_bd.style.height=h+"px";
}
}
}
var _be=document.createElement("div");
_be.innerHTML=_ba;
_bd.appendChild(_be);
document.body.appendChild(_bd);
if(!w){
w=parseInt(_be.scrollWidth);
_bd.style.width=w+"px";
}
if(!h){
h=parseInt(_be.scrollHeight);
}
_bd.removeChild(_be);
document.body.removeChild(_bd);
return new OpenLayers.Size(w,h);
};
OpenLayers.Util.getScrollbarWidth=function(){
var _bf=OpenLayers.Util._scrollbarWidth;
if(_bf==null){
var scr=null;
var inn=null;
var _c2=0;
var _c3=0;
scr=document.createElement("div");
scr.style.position="absolute";
scr.style.top="-1000px";
scr.style.left="-1000px";
scr.style.width="100px";
scr.style.height="50px";
scr.style.overflow="hidden";
inn=document.createElement("div");
inn.style.width="100%";
inn.style.height="200px";
scr.appendChild(inn);
document.body.appendChild(scr);
_c2=inn.offsetWidth;
scr.style.overflow="scroll";
_c3=inn.offsetWidth;
document.body.removeChild(document.body.lastChild);
OpenLayers.Util._scrollbarWidth=(_c2-_c3);
_bf=OpenLayers.Util._scrollbarWidth;
}
return _bf;
};

OpenLayers.String={startsWith:function(_1,_2){
return (_1.indexOf(_2)==0);
},contains:function(_3,_4){
return (_3.indexOf(_4)!=-1);
},trim:function(_5){
return _5.replace(/^\s*(.*?)\s*$/,"$1");
},camelize:function(_6){
var _7=_6.split("-");
var _8=_7[0];
for(var i=1;i<_7.length;i++){
var s=_7[i];
_8+=s.charAt(0).toUpperCase()+s.substring(1);
}
return _8;
},format:function(_b,_c,_d){
if(!_c){
_c=window;
}
var _e=_b.split("${");
var _f,last,replacement;
for(var i=1;i<_e.length;i++){
_f=_e[i];
last=_f.indexOf("}");
if(last>0){
replacement=_c[_f.substring(0,last)];
if(typeof replacement=="function"){
replacement=_d?replacement.apply(null,_d):replacement();
}
_e[i]=replacement+_f.substring(++last);
}else{
_e[i]="${"+_f;
}
}
return _e.join("");
},numberRegEx:/^([+-]?)(?=\d|\.\d)\d*(\.\d*)?([Ee]([+-]?\d+))?$/,isNumeric:function(_11){
return OpenLayers.String.numberRegEx.test(_11);
}};
if(!String.prototype.startsWith){
String.prototype.startsWith=function(_12){
OpenLayers.Console.warn(OpenLayers.i18n("methodDeprecated",{"newMethod":"OpenLayers.String.startsWith"}));
return OpenLayers.String.startsWith(this,_12);
};
}
if(!String.prototype.contains){
String.prototype.contains=function(str){
OpenLayers.Console.warn(OpenLayers.i18n("methodDeprecated",{"newMethod":"OpenLayers.String.contains"}));
return OpenLayers.String.contains(this,str);
};
}
if(!String.prototype.trim){
String.prototype.trim=function(){
OpenLayers.Console.warn(OpenLayers.i18n("methodDeprecated",{"newMethod":"OpenLayers.String.trim"}));
return OpenLayers.String.trim(this);
};
}
if(!String.prototype.camelize){
String.prototype.camelize=function(){
OpenLayers.Console.warn(OpenLayers.i18n("methodDeprecated",{"newMethod":"OpenLayers.String.camelize"}));
return OpenLayers.String.camelize(this);
};
}
OpenLayers.Number={decimalSeparator:".",thousandsSeparator:",",limitSigDigs:function(num,sig){
var fig=0;
if(sig>0){
fig=parseFloat(num.toPrecision(sig));
}
return fig;
},format:function(num,dec,_19,_1a){
dec=(typeof dec!="undefined")?dec:0;
_19=(typeof _19!="undefined")?_19:OpenLayers.Number.thousandsSeparator;
_1a=(typeof _1a!="undefined")?_1a:OpenLayers.Number.decimalSeparator;
if(dec!=null){
num=parseFloat(num.toFixed(dec));
}
var _1b=num.toString().split(".");
if(_1b.length==1&&dec==null){
dec=0;
}
var _1c=_1b[0];
if(_19){
var _1d=/(-?[0-9]+)([0-9]{3})/;
while(_1d.test(_1c)){
_1c=_1c.replace(_1d,"$1"+_19+"$2");
}
}
var str;
if(dec==0){
str=_1c;
}else{
var rem=_1b.length>1?_1b[1]:"0";
if(dec!=null){
rem=rem+new Array(dec-rem.length+1).join("0");
}
str=_1c+_1a+rem;
}
return str;
}};
if(!Number.prototype.limitSigDigs){
Number.prototype.limitSigDigs=function(sig){
OpenLayers.Console.warn(OpenLayers.i18n("methodDeprecated",{"newMethod":"OpenLayers.String.limitSigDigs"}));
return OpenLayers.Number.limitSigDigs(this,sig);
};
}
OpenLayers.Function={bind:function(_21,_22){
var _23=Array.prototype.slice.apply(arguments,[2]);
return function(){
var _24=_23.concat(Array.prototype.slice.apply(arguments,[0]));
return _21.apply(_22,_24);
};
},bindAsEventListener:function(_25,_26){
return function(_27){
return _25.call(_26,_27||window.event);
};
}};
if(!Function.prototype.bind){
Function.prototype.bind=function(){
OpenLayers.Console.warn(OpenLayers.i18n("methodDeprecated",{"newMethod":"OpenLayers.String.bind"}));
Array.prototype.unshift.apply(arguments,[this]);
return OpenLayers.Function.bind.apply(null,arguments);
};
}
if(!Function.prototype.bindAsEventListener){
Function.prototype.bindAsEventListener=function(_28){
OpenLayers.Console.warn(OpenLayers.i18n("methodDeprecated",{"newMethod":"OpenLayers.String.bindAsEventListener"}));
return OpenLayers.Function.bindAsEventListener(this,_28);
};
}
OpenLayers.Array={filter:function(_29,_2a,_2b){
var _2c=[];
if(Array.prototype.filter){
_2c=_29.filter(_2a,_2b);
}else{
var len=_29.length;
if(typeof _2a!="function"){
throw new TypeError();
}
for(var i=0;i<len;i++){
if(i in _29){
var val=_29[i];
if(_2a.call(_2b,val,i,_29)){
_2c.push(val);
}
}
}
}
return _2c;
}};

OpenLayers.Class=function(){
var _1=function(){
if(arguments&&arguments[0]!=OpenLayers.Class.isPrototype){
this.initialize.apply(this,arguments);
}
};
var _2={};
var _3;
for(var i=0;i<arguments.length;++i){
if(typeof arguments[i]=="function"){
_3=arguments[i].prototype;
}else{
_3=arguments[i];
}
OpenLayers.Util.extend(_2,_3);
}
_1.prototype=_2;
return _1;
};
OpenLayers.Class.isPrototype=function(){
};
OpenLayers.Class.create=function(){
return function(){
if(arguments&&arguments[0]!=OpenLayers.Class.isPrototype){
this.initialize.apply(this,arguments);
}
};
};
OpenLayers.Class.inherit=function(){
var _5=arguments[0];
var _6=new _5(OpenLayers.Class.isPrototype);
for(var i=1;i<arguments.length;i++){
if(typeof arguments[i]=="function"){
var _8=arguments[i];
arguments[i]=new _8(OpenLayers.Class.isPrototype);
}
OpenLayers.Util.extend(_6,arguments[i]);
}
return _6;
};

OpenLayers.Bounds=OpenLayers.Class({left:null,bottom:null,right:null,top:null,initialize:function(_1,_2,_3,_4){
if(_1!=null){
this.left=parseFloat(_1);
}
if(_2!=null){
this.bottom=parseFloat(_2);
}
if(_3!=null){
this.right=parseFloat(_3);
}
if(_4!=null){
this.top=parseFloat(_4);
}
},clone:function(){
return new OpenLayers.Bounds(this.left,this.bottom,this.right,this.top);
},equals:function(_5){
var _6=false;
if(_5!=null){
_6=((this.left==_5.left)&&(this.right==_5.right)&&(this.top==_5.top)&&(this.bottom==_5.bottom));
}
return _6;
},toString:function(){
return ("left-bottom=("+this.left+","+this.bottom+")"+" right-top=("+this.right+","+this.top+")");
},toArray:function(){
return [this.left,this.bottom,this.right,this.top];
},toBBOX:function(_7){
if(_7==null){
_7=6;
}
var _8=Math.pow(10,_7);
var _9=Math.round(this.left*_8)/_8+","+Math.round(this.bottom*_8)/_8+","+Math.round(this.right*_8)/_8+","+Math.round(this.top*_8)/_8;
return _9;
},toGeometry:function(){
return new OpenLayers.Geometry.Polygon([new OpenLayers.Geometry.LinearRing([new OpenLayers.Geometry.Point(this.left,this.bottom),new OpenLayers.Geometry.Point(this.right,this.bottom),new OpenLayers.Geometry.Point(this.right,this.top),new OpenLayers.Geometry.Point(this.left,this.top)])]);
},getWidth:function(){
return (this.right-this.left);
},getHeight:function(){
return (this.top-this.bottom);
},getSize:function(){
return new OpenLayers.Size(this.getWidth(),this.getHeight());
},getCenterPixel:function(){
return new OpenLayers.Pixel((this.left+this.right)/2,(this.bottom+this.top)/2);
},getCenterLonLat:function(){
return new OpenLayers.LonLat((this.left+this.right)/2,(this.bottom+this.top)/2);
},add:function(x,y){
if((x==null)||(y==null)){
var _c=OpenLayers.i18n("boundsAddError");
OpenLayers.Console.error(_c);
return null;
}
return new OpenLayers.Bounds(this.left+x,this.bottom+y,this.right+x,this.top+y);
},extend:function(_d){
var _e=null;
if(_d){
switch(_d.CLASS_NAME){
case "OpenLayers.LonLat":
_e=new OpenLayers.Bounds(_d.lon,_d.lat,_d.lon,_d.lat);
break;
case "OpenLayers.Geometry.Point":
_e=new OpenLayers.Bounds(_d.x,_d.y,_d.x,_d.y);
break;
case "OpenLayers.Bounds":
_e=_d;
break;
}
if(_e){
if((this.left==null)||(_e.left<this.left)){
this.left=_e.left;
}
if((this.bottom==null)||(_e.bottom<this.bottom)){
this.bottom=_e.bottom;
}
if((this.right==null)||(_e.right>this.right)){
this.right=_e.right;
}
if((this.top==null)||(_e.top>this.top)){
this.top=_e.top;
}
}
}
},containsLonLat:function(ll,_10){
return this.contains(ll.lon,ll.lat,_10);
},containsPixel:function(px,_12){
return this.contains(px.x,px.y,_12);
},contains:function(x,y,_15){
if(_15==null){
_15=true;
}
var _16=false;
if(_15){
_16=((x>=this.left)&&(x<=this.right)&&(y>=this.bottom)&&(y<=this.top));
}else{
_16=((x>this.left)&&(x<this.right)&&(y>this.bottom)&&(y<this.top));
}
return _16;
},intersectsBounds:function(_17,_18){
if(_18==null){
_18=true;
}
var _19=(_17.bottom==this.bottom&&_17.top==this.top)?true:(((_17.bottom>this.bottom)&&(_17.bottom<this.top))||((this.bottom>_17.bottom)&&(this.bottom<_17.top)));
var _1a=(_17.bottom==this.bottom&&_17.top==this.top)?true:(((_17.top>this.bottom)&&(_17.top<this.top))||((this.top>_17.bottom)&&(this.top<_17.top)));
var _1b=(_17.right==this.right&&_17.left==this.left)?true:(((_17.right>this.left)&&(_17.right<this.right))||((this.right>_17.left)&&(this.right<_17.right)));
var _1c=(_17.right==this.right&&_17.left==this.left)?true:(((_17.left>this.left)&&(_17.left<this.right))||((this.left>_17.left)&&(this.left<_17.right)));
return (this.containsBounds(_17,true,_18)||_17.containsBounds(this,true,_18)||((_1a||_19)&&(_1c||_1b)));
},containsBounds:function(_1d,_1e,_1f){
if(_1e==null){
_1e=false;
}
if(_1f==null){
_1f=true;
}
var _20;
var _21;
var _22;
var _23;
if(_1f){
_20=(_1d.left>=this.left)&&(_1d.left<=this.right);
_21=(_1d.top>=this.bottom)&&(_1d.top<=this.top);
_22=(_1d.right>=this.left)&&(_1d.right<=this.right);
_23=(_1d.bottom>=this.bottom)&&(_1d.bottom<=this.top);
}else{
_20=(_1d.left>this.left)&&(_1d.left<this.right);
_21=(_1d.top>this.bottom)&&(_1d.top<this.top);
_22=(_1d.right>this.left)&&(_1d.right<this.right);
_23=(_1d.bottom>this.bottom)&&(_1d.bottom<this.top);
}
return (_1e)?(_21||_23)&&(_20||_22):(_21&&_20&&_23&&_22);
},determineQuadrant:function(_24){
var _25="";
var _26=this.getCenterLonLat();
_25+=(_24.lat<_26.lat)?"b":"t";
_25+=(_24.lon<_26.lon)?"l":"r";
return _25;
},transform:function(_27,_28){
var ll=OpenLayers.Projection.transform({"x":this.left,"y":this.bottom},_27,_28);
var lr=OpenLayers.Projection.transform({"x":this.right,"y":this.bottom},_27,_28);
var ul=OpenLayers.Projection.transform({"x":this.left,"y":this.top},_27,_28);
var ur=OpenLayers.Projection.transform({"x":this.right,"y":this.top},_27,_28);
this.left=Math.min(ll.x,ul.x);
this.bottom=Math.min(ll.y,lr.y);
this.right=Math.max(lr.x,ur.x);
this.top=Math.max(ul.y,ur.y);
return this;
},wrapDateLine:function(_2d,_2e){
_2e=_2e||{};
var _2f=_2e.leftTolerance||0;
var _30=_2e.rightTolerance||0;
var _31=this.clone();
if(_2d){
while(_31.left<_2d.left&&(_31.right-_30)<=_2d.left){
_31=_31.add(_2d.getWidth(),0);
}
while((_31.left+_2f)>=_2d.right&&_31.right>_2d.right){
_31=_31.add(-_2d.getWidth(),0);
}
}
return _31;
},CLASS_NAME:"OpenLayers.Bounds"});
OpenLayers.Bounds.fromString=function(str){
var _33=str.split(",");
return OpenLayers.Bounds.fromArray(_33);
};
OpenLayers.Bounds.fromArray=function(_34){
return new OpenLayers.Bounds(parseFloat(_34[0]),parseFloat(_34[1]),parseFloat(_34[2]),parseFloat(_34[3]));
};
OpenLayers.Bounds.fromSize=function(_35){
return new OpenLayers.Bounds(0,_35.h,_35.w,0);
};
OpenLayers.Bounds.oppositeQuadrant=function(_36){
var opp="";
opp+=(_36.charAt(0)=="t")?"b":"t";
opp+=(_36.charAt(1)=="l")?"r":"l";
return opp;
};

OpenLayers.Element={visible:function(_1){
return OpenLayers.Util.getElement(_1).style.display!="none";
},toggle:function(){
for(var i=0;i<arguments.length;i++){
var _3=OpenLayers.Util.getElement(arguments[i]);
var _4=OpenLayers.Element.visible(_3)?"hide":"show";
OpenLayers.Element[_4](_3);
}
},hide:function(){
for(var i=0;i<arguments.length;i++){
var _6=OpenLayers.Util.getElement(arguments[i]);
_6.style.display="none";
}
},show:function(){
for(var i=0;i<arguments.length;i++){
var _8=OpenLayers.Util.getElement(arguments[i]);
_8.style.display="";
}
},remove:function(_9){
_9=OpenLayers.Util.getElement(_9);
_9.parentNode.removeChild(_9);
},getHeight:function(_a){
_a=OpenLayers.Util.getElement(_a);
return _a.offsetHeight;
},getDimensions:function(_b){
_b=OpenLayers.Util.getElement(_b);
if(OpenLayers.Element.getStyle(_b,"display")!="none"){
return {width:_b.offsetWidth,height:_b.offsetHeight};
}
var _c=_b.style;
var _d=_c.visibility;
var _e=_c.position;
_c.visibility="hidden";
_c.position="absolute";
_c.display="";
var _f=_b.clientWidth;
var _10=_b.clientHeight;
_c.display="none";
_c.position=_e;
_c.visibility=_d;
return {width:_f,height:_10};
},getStyle:function(_11,_12){
_11=OpenLayers.Util.getElement(_11);
var _13=_11.style[OpenLayers.String.camelize(_12)];
if(!_13){
if(document.defaultView&&document.defaultView.getComputedStyle){
var css=document.defaultView.getComputedStyle(_11,null);
_13=css?css.getPropertyValue(_12):null;
}else{
if(_11.currentStyle){
_13=_11.currentStyle[OpenLayers.String.camelize(_12)];
}
}
}
var _15=["left","top","right","bottom"];
if(window.opera&&(OpenLayers.Util.indexOf(_15,_12)!=-1)&&(OpenLayers.Element.getStyle(_11,"position")=="static")){
_13="auto";
}
return _13=="auto"?null:_13;
}};

OpenLayers.LonLat=OpenLayers.Class({lon:0,lat:0,initialize:function(_1,_2){
this.lon=parseFloat(_1);
this.lat=parseFloat(_2);
},toString:function(){
return ("lon="+this.lon+",lat="+this.lat);
},toShortString:function(){
return (this.lon+", "+this.lat);
},clone:function(){
return new OpenLayers.LonLat(this.lon,this.lat);
},add:function(_3,_4){
if((_3==null)||(_4==null)){
var _5=OpenLayers.i18n("lonlatAddError");
OpenLayers.Console.error(_5);
return null;
}
return new OpenLayers.LonLat(this.lon+_3,this.lat+_4);
},equals:function(ll){
var _7=false;
if(ll!=null){
_7=((this.lon==ll.lon&&this.lat==ll.lat)||(isNaN(this.lon)&&isNaN(this.lat)&&isNaN(ll.lon)&&isNaN(ll.lat)));
}
return _7;
},transform:function(_8,_9){
var _a=OpenLayers.Projection.transform({"x":this.lon,"y":this.lat},_8,_9);
this.lon=_a.x;
this.lat=_a.y;
return this;
},wrapDateLine:function(_b){
var _c=this.clone();
if(_b){
while(_c.lon<_b.left){
_c.lon+=_b.getWidth();
}
while(_c.lon>_b.right){
_c.lon-=_b.getWidth();
}
}
return _c;
},CLASS_NAME:"OpenLayers.LonLat"});
OpenLayers.LonLat.fromString=function(_d){
var _e=_d.split(",");
return new OpenLayers.LonLat(parseFloat(_e[0]),parseFloat(_e[1]));
};

OpenLayers.Pixel=OpenLayers.Class({x:0,y:0,initialize:function(x,y){
this.x=parseFloat(x);
this.y=parseFloat(y);
},toString:function(){
return ("x="+this.x+",y="+this.y);
},clone:function(){
return new OpenLayers.Pixel(this.x,this.y);
},equals:function(px){
var _4=false;
if(px!=null){
_4=((this.x==px.x&&this.y==px.y)||(isNaN(this.x)&&isNaN(this.y)&&isNaN(px.x)&&isNaN(px.y)));
}
return _4;
},add:function(x,y){
if((x==null)||(y==null)){
var _7=OpenLayers.i18n("pixelAddError");
OpenLayers.Console.error(_7);
return null;
}
return new OpenLayers.Pixel(this.x+x,this.y+y);
},offset:function(px){
var _9=this.clone();
if(px){
_9=this.add(px.x,px.y);
}
return _9;
},CLASS_NAME:"OpenLayers.Pixel"});

OpenLayers.Size=OpenLayers.Class({w:0,h:0,initialize:function(w,h){
this.w=parseFloat(w);
this.h=parseFloat(h);
},toString:function(){
return ("w="+this.w+",h="+this.h);
},clone:function(){
return new OpenLayers.Size(this.w,this.h);
},equals:function(sz){
var _4=false;
if(sz!=null){
_4=((this.w==sz.w&&this.h==sz.h)||(isNaN(this.w)&&isNaN(this.h)&&isNaN(sz.w)&&isNaN(sz.h)));
}
return _4;
},CLASS_NAME:"OpenLayers.Size"});

OpenLayers.Console={log:function(){
},debug:function(){
},info:function(){
},warn:function(){
},error:function(){
},assert:function(){
},dir:function(){
},dirxml:function(){
},trace:function(){
},group:function(){
},groupEnd:function(){
},time:function(){
},timeEnd:function(){
},profile:function(){
},profileEnd:function(){
},count:function(){
},CLASS_NAME:"OpenLayers.Console"};
(function(){
if(window.console){
var _1=document.getElementsByTagName("script");
for(var i=0;i<_1.length;++i){
if(_1[i].src.indexOf("firebug.js")!=-1){
OpenLayers.Util.extend(OpenLayers.Console,console);
break;
}
}
}
})();

OpenLayers.Tween=OpenLayers.Class({INTERVAL:10,easing:null,begin:null,finish:null,duration:null,callbacks:null,time:null,interval:null,playing:false,initialize:function(_1){
this.easing=(_1)?_1:OpenLayers.Easing.Expo.easeOut;
},start:function(_2,_3,_4,_5){
this.playing=true;
this.begin=_2;
this.finish=_3;
this.duration=_4;
this.callbacks=_5.callbacks;
this.time=0;
if(this.interval){
window.clearInterval(this.interval);
this.interval=null;
}
if(this.callbacks&&this.callbacks.start){
this.callbacks.start.call(this,this.begin);
}
this.interval=window.setInterval(OpenLayers.Function.bind(this.play,this),this.INTERVAL);
},stop:function(){
if(!this.playing){
return;
}
if(this.callbacks&&this.callbacks.done){
this.callbacks.done.call(this,this.finish);
}
window.clearInterval(this.interval);
this.interval=null;
this.playing=false;
},play:function(){
var _6={};
for(var i in this.begin){
var b=this.begin[i];
var f=this.finish[i];
if(b==null||f==null||isNaN(b)||isNaN(f)){
OpenLayers.Console.error("invalid value for Tween");
}
var c=f-b;
_6[i]=this.easing.apply(this,[this.time,b,c,this.duration]);
}
this.time++;
if(this.callbacks&&this.callbacks.eachStep){
this.callbacks.eachStep.call(this,_6);
}
if(this.time>this.duration){
if(this.callbacks&&this.callbacks.done){
this.callbacks.done.call(this,this.finish);
this.playing=false;
}
window.clearInterval(this.interval);
this.interval=null;
}
},CLASS_NAME:"OpenLayers.Tween"});
OpenLayers.Easing={CLASS_NAME:"OpenLayers.Easing"};
OpenLayers.Easing.Linear={easeIn:function(t,b,c,d){
return c*t/d+b;
},easeOut:function(t,b,c,d){
return c*t/d+b;
},easeInOut:function(t,b,c,d){
return c*t/d+b;
},CLASS_NAME:"OpenLayers.Easing.Linear"};
OpenLayers.Easing.Expo={easeIn:function(t,b,c,d){
return (t==0)?b:c*Math.pow(2,10*(t/d-1))+b;
},easeOut:function(t,b,c,d){
return (t==d)?b+c:c*(-Math.pow(2,-10*t/d)+1)+b;
},easeInOut:function(t,b,c,d){
if(t==0){
return b;
}
if(t==d){
return b+c;
}
if((t/=d/2)<1){
return c/2*Math.pow(2,10*(t-1))+b;
}
return c/2*(-Math.pow(2,-10*--t)+2)+b;
},CLASS_NAME:"OpenLayers.Easing.Expo"};
OpenLayers.Easing.Quad={easeIn:function(t,b,c,d){
return c*(t/=d)*t+b;
},easeOut:function(t,b,c,d){
return -c*(t/=d)*(t-2)+b;
},easeInOut:function(t,b,c,d){
if((t/=d/2)<1){
return c/2*t*t+b;
}
return -c/2*((--t)*(t-2)-1)+b;
},CLASS_NAME:"OpenLayers.Easing.Quad"};

OpenLayers.Rico=new Object();
OpenLayers.Rico.Corner={round:function(e,_2){
e=OpenLayers.Util.getElement(e);
this._setOptions(_2);
var _3=this.options.color;
if(this.options.color=="fromElement"){
_3=this._background(e);
}
var _4=this.options.bgColor;
if(this.options.bgColor=="fromParent"){
_4=this._background(e.offsetParent);
}
this._roundCornersImpl(e,_3,_4);
},changeColor:function(_5,_6){
_5.style.backgroundColor=_6;
var _7=_5.parentNode.getElementsByTagName("span");
for(var _8=0;_8<_7.length;_8++){
_7[_8].style.backgroundColor=_6;
}
},changeOpacity:function(_9,_a){
var _b=_a;
var _c="alpha(opacity="+_a*100+")";
_9.style.opacity=_b;
_9.style.filter=_c;
var _d=_9.parentNode.getElementsByTagName("span");
for(var _e=0;_e<_d.length;_e++){
_d[_e].style.opacity=_b;
_d[_e].style.filter=_c;
}
},reRound:function(_f,_10){
var _11=_f.parentNode.childNodes[0];
var _12=_f.parentNode.childNodes[2];
_f.parentNode.removeChild(_11);
_f.parentNode.removeChild(_12);
this.round(_f.parentNode,_10);
},_roundCornersImpl:function(e,_14,_15){
if(this.options.border){
this._renderBorder(e,_15);
}
if(this._isTopRounded()){
this._roundTopCorners(e,_14,_15);
}
if(this._isBottomRounded()){
this._roundBottomCorners(e,_14,_15);
}
},_renderBorder:function(el,_17){
var _18="1px solid "+this._borderColor(_17);
var _19="border-left: "+_18;
var _1a="border-right: "+_18;
var _1b="style='"+_19+";"+_1a+"'";
el.innerHTML="<div "+_1b+">"+el.innerHTML+"</div>";
},_roundTopCorners:function(el,_1d,_1e){
var _1f=this._createCorner(_1e);
for(var i=0;i<this.options.numSlices;i++){
_1f.appendChild(this._createCornerSlice(_1d,_1e,i,"top"));
}
el.style.paddingTop=0;
el.insertBefore(_1f,el.firstChild);
},_roundBottomCorners:function(el,_22,_23){
var _24=this._createCorner(_23);
for(var i=(this.options.numSlices-1);i>=0;i--){
_24.appendChild(this._createCornerSlice(_22,_23,i,"bottom"));
}
el.style.paddingBottom=0;
el.appendChild(_24);
},_createCorner:function(_26){
var _27=document.createElement("div");
_27.style.backgroundColor=(this._isTransparent()?"transparent":_26);
return _27;
},_createCornerSlice:function(_28,_29,n,_2b){
var _2c=document.createElement("span");
var _2d=_2c.style;
_2d.backgroundColor=_28;
_2d.display="block";
_2d.height="1px";
_2d.overflow="hidden";
_2d.fontSize="1px";
var _2e=this._borderColor(_28,_29);
if(this.options.border&&n==0){
_2d.borderTopStyle="solid";
_2d.borderTopWidth="1px";
_2d.borderLeftWidth="0px";
_2d.borderRightWidth="0px";
_2d.borderBottomWidth="0px";
_2d.height="0px";
_2d.borderColor=_2e;
}else{
if(_2e){
_2d.borderColor=_2e;
_2d.borderStyle="solid";
_2d.borderWidth="0px 1px";
}
}
if(!this.options.compact&&(n==(this.options.numSlices-1))){
_2d.height="2px";
}
this._setMargin(_2c,n,_2b);
this._setBorder(_2c,n,_2b);
return _2c;
},_setOptions:function(_2f){
this.options={corners:"all",color:"fromElement",bgColor:"fromParent",blend:true,border:false,compact:false};
OpenLayers.Util.extend(this.options,_2f||{});
this.options.numSlices=this.options.compact?2:4;
if(this._isTransparent()){
this.options.blend=false;
}
},_whichSideTop:function(){
if(this._hasString(this.options.corners,"all","top")){
return "";
}
if(this.options.corners.indexOf("tl")>=0&&this.options.corners.indexOf("tr")>=0){
return "";
}
if(this.options.corners.indexOf("tl")>=0){
return "left";
}else{
if(this.options.corners.indexOf("tr")>=0){
return "right";
}
}
return "";
},_whichSideBottom:function(){
if(this._hasString(this.options.corners,"all","bottom")){
return "";
}
if(this.options.corners.indexOf("bl")>=0&&this.options.corners.indexOf("br")>=0){
return "";
}
if(this.options.corners.indexOf("bl")>=0){
return "left";
}else{
if(this.options.corners.indexOf("br")>=0){
return "right";
}
}
return "";
},_borderColor:function(_30,_31){
if(_30=="transparent"){
return _31;
}else{
if(this.options.border){
return this.options.border;
}else{
if(this.options.blend){
return this._blend(_31,_30);
}else{
return "";
}
}
}
},_setMargin:function(el,n,_34){
var _35=this._marginSize(n);
var _36=_34=="top"?this._whichSideTop():this._whichSideBottom();
if(_36=="left"){
el.style.marginLeft=_35+"px";
el.style.marginRight="0px";
}else{
if(_36=="right"){
el.style.marginRight=_35+"px";
el.style.marginLeft="0px";
}else{
el.style.marginLeft=_35+"px";
el.style.marginRight=_35+"px";
}
}
},_setBorder:function(el,n,_39){
var _3a=this._borderSize(n);
var _3b=_39=="top"?this._whichSideTop():this._whichSideBottom();
if(_3b=="left"){
el.style.borderLeftWidth=_3a+"px";
el.style.borderRightWidth="0px";
}else{
if(_3b=="right"){
el.style.borderRightWidth=_3a+"px";
el.style.borderLeftWidth="0px";
}else{
el.style.borderLeftWidth=_3a+"px";
el.style.borderRightWidth=_3a+"px";
}
}
if(this.options.border!=false){
el.style.borderLeftWidth=_3a+"px";
el.style.borderRightWidth=_3a+"px";
}
},_marginSize:function(n){
if(this._isTransparent()){
return 0;
}
var _3d=[5,3,2,1];
var _3e=[3,2,1,0];
var _3f=[2,1];
var _40=[1,0];
if(this.options.compact&&this.options.blend){
return _40[n];
}else{
if(this.options.compact){
return _3f[n];
}else{
if(this.options.blend){
return _3e[n];
}else{
return _3d[n];
}
}
}
},_borderSize:function(n){
var _42=[5,3,2,1];
var _43=[2,1,1,1];
var _44=[1,0];
var _45=[0,2,0,0];
if(this.options.compact&&(this.options.blend||this._isTransparent())){
return 1;
}else{
if(this.options.compact){
return _44[n];
}else{
if(this.options.blend){
return _43[n];
}else{
if(this.options.border){
return _45[n];
}else{
if(this._isTransparent()){
return _42[n];
}
}
}
}
}
return 0;
},_hasString:function(str){
for(var i=1;i<arguments.length;i++){
if(str.indexOf(arguments[i])>=0){
return true;
}
}
return false;
},_blend:function(c1,c2){
var cc1=OpenLayers.Rico.Color.createFromHex(c1);
cc1.blend(OpenLayers.Rico.Color.createFromHex(c2));
return cc1;
},_background:function(el){
try{
return OpenLayers.Rico.Color.createColorFromBackground(el).asHex();
}
catch(err){
return "#ffffff";
}
},_isTransparent:function(){
return this.options.color=="transparent";
},_isTopRounded:function(){
return this._hasString(this.options.corners,"all","top","tl","tr");
},_isBottomRounded:function(){
return this._hasString(this.options.corners,"all","bottom","bl","br");
},_hasSingleTextChild:function(el){
return el.childNodes.length==1&&el.childNodes[0].nodeType==3;
}};

OpenLayers.Rico.Color=OpenLayers.Class({initialize:function(_1,_2,_3){
this.rgb={r:_1,g:_2,b:_3};
},setRed:function(r){
this.rgb.r=r;
},setGreen:function(g){
this.rgb.g=g;
},setBlue:function(b){
this.rgb.b=b;
},setHue:function(h){
var _8=this.asHSB();
_8.h=h;
this.rgb=OpenLayers.Rico.Color.HSBtoRGB(_8.h,_8.s,_8.b);
},setSaturation:function(s){
var _a=this.asHSB();
_a.s=s;
this.rgb=OpenLayers.Rico.Color.HSBtoRGB(_a.h,_a.s,_a.b);
},setBrightness:function(b){
var _c=this.asHSB();
_c.b=b;
this.rgb=OpenLayers.Rico.Color.HSBtoRGB(_c.h,_c.s,_c.b);
},darken:function(_d){
var _e=this.asHSB();
this.rgb=OpenLayers.Rico.Color.HSBtoRGB(_e.h,_e.s,Math.max(_e.b-_d,0));
},brighten:function(_f){
var hsb=this.asHSB();
this.rgb=OpenLayers.Rico.Color.HSBtoRGB(hsb.h,hsb.s,Math.min(hsb.b+_f,1));
},blend:function(_11){
this.rgb.r=Math.floor((this.rgb.r+_11.rgb.r)/2);
this.rgb.g=Math.floor((this.rgb.g+_11.rgb.g)/2);
this.rgb.b=Math.floor((this.rgb.b+_11.rgb.b)/2);
},isBright:function(){
var hsb=this.asHSB();
return this.asHSB().b>0.5;
},isDark:function(){
return !this.isBright();
},asRGB:function(){
return "rgb("+this.rgb.r+","+this.rgb.g+","+this.rgb.b+")";
},asHex:function(){
return "#"+this.rgb.r.toColorPart()+this.rgb.g.toColorPart()+this.rgb.b.toColorPart();
},asHSB:function(){
return OpenLayers.Rico.Color.RGBtoHSB(this.rgb.r,this.rgb.g,this.rgb.b);
},toString:function(){
return this.asHex();
}});
OpenLayers.Rico.Color.createFromHex=function(_13){
if(_13.length==4){
var _14=_13;
var _13="#";
for(var i=1;i<4;i++){
_13+=(_14.charAt(i)+_14.charAt(i));
}
}
if(_13.indexOf("#")==0){
_13=_13.substring(1);
}
var red=_13.substring(0,2);
var _17=_13.substring(2,4);
var _18=_13.substring(4,6);
return new OpenLayers.Rico.Color(parseInt(red,16),parseInt(_17,16),parseInt(_18,16));
};
OpenLayers.Rico.Color.createColorFromBackground=function(_19){
var _1a=RicoUtil.getElementsComputedStyle(OpenLayers.Util.getElement(_19),"backgroundColor","background-color");
if(_1a=="transparent"&&_19.parentNode){
return OpenLayers.Rico.Color.createColorFromBackground(_19.parentNode);
}
if(_1a==null){
return new OpenLayers.Rico.Color(255,255,255);
}
if(_1a.indexOf("rgb(")==0){
var _1b=_1a.substring(4,_1a.length-1);
var _1c=_1b.split(",");
return new OpenLayers.Rico.Color(parseInt(_1c[0]),parseInt(_1c[1]),parseInt(_1c[2]));
}else{
if(_1a.indexOf("#")==0){
return OpenLayers.Rico.Color.createFromHex(_1a);
}else{
return new OpenLayers.Rico.Color(255,255,255);
}
}
};
OpenLayers.Rico.Color.HSBtoRGB=function(hue,_1e,_1f){
var red=0;
var _21=0;
var _22=0;
if(_1e==0){
red=parseInt(_1f*255+0.5);
_21=red;
_22=red;
}else{
var h=(hue-Math.floor(hue))*6;
var f=h-Math.floor(h);
var p=_1f*(1-_1e);
var q=_1f*(1-_1e*f);
var t=_1f*(1-(_1e*(1-f)));
switch(parseInt(h)){
case 0:
red=(_1f*255+0.5);
_21=(t*255+0.5);
_22=(p*255+0.5);
break;
case 1:
red=(q*255+0.5);
_21=(_1f*255+0.5);
_22=(p*255+0.5);
break;
case 2:
red=(p*255+0.5);
_21=(_1f*255+0.5);
_22=(t*255+0.5);
break;
case 3:
red=(p*255+0.5);
_21=(q*255+0.5);
_22=(_1f*255+0.5);
break;
case 4:
red=(t*255+0.5);
_21=(p*255+0.5);
_22=(_1f*255+0.5);
break;
case 5:
red=(_1f*255+0.5);
_21=(p*255+0.5);
_22=(q*255+0.5);
break;
}
}
return {r:parseInt(red),g:parseInt(_21),b:parseInt(_22)};
};
OpenLayers.Rico.Color.RGBtoHSB=function(r,g,b){
var hue;
var _2c;
var _2d;
var _2e=(r>g)?r:g;
if(b>_2e){
_2e=b;
}
var _2f=(r<g)?r:g;
if(b<_2f){
_2f=b;
}
_2d=_2e/255;
if(_2e!=0){
_2c=(_2e-_2f)/_2e;
}else{
_2c=0;
}
if(_2c==0){
hue=0;
}else{
var _30=(_2e-r)/(_2e-_2f);
var _31=(_2e-g)/(_2e-_2f);
var _32=(_2e-b)/(_2e-_2f);
if(r==_2e){
hue=_32-_31;
}else{
if(g==_2e){
hue=2+_30-_32;
}else{
hue=4+_31-_30;
}
}
hue=hue/6;
if(hue<0){
hue=hue+1;
}
}
return {h:hue,s:_2c,b:_2d};
};

OpenLayers.ProxyHost="";
OpenLayers.nullHandler=function(_1){
alert(OpenLayers.i18n("unhandledRequest",{"statusText":_1.statusText}));
};
OpenLayers.loadURL=function(_2,_3,_4,_5,_6){
if(typeof _3=="string"){
_3=OpenLayers.Util.getParameters(_3);
}
var _7=(_5)?_5:OpenLayers.nullHandler;
var _8=(_6)?_6:OpenLayers.nullHandler;
return OpenLayers.Request.GET({url:_2,params:_3,success:_7,failure:_8,scope:_4});
};
OpenLayers.parseXMLString=function(_9){
var _a=_9.indexOf("<");
if(_a>0){
_9=_9.substring(_a);
}
var _b=OpenLayers.Util.Try(function(){
var _c=new ActiveXObject("Microsoft.XMLDOM");
_c.loadXML(_9);
return _c;
},function(){
return new DOMParser().parseFromString(_9,"text/xml");
},function(){
var _d=new XMLHttpRequest();
_d.open("GET","data:"+"text/xml"+";charset=utf-8,"+encodeURIComponent(_9),false);
if(_d.overrideMimeType){
_d.overrideMimeType("text/xml");
}
_d.send(null);
return _d.responseXML;
});
return _b;
};
OpenLayers.Ajax={emptyFunction:function(){
},getTransport:function(){
return OpenLayers.Util.Try(function(){
return new XMLHttpRequest();
},function(){
return new ActiveXObject("Msxml2.XMLHTTP");
},function(){
return new ActiveXObject("Microsoft.XMLHTTP");
})||false;
},activeRequestCount:0};
OpenLayers.Ajax.Responders={responders:[],register:function(_e){
for(var i=0;i<this.responders.length;i++){
if(_e==this.responders[i]){
return;
}
}
this.responders.push(_e);
},unregister:function(_10){
OpenLayers.Util.removeItem(this.reponders,_10);
},dispatch:function(_11,_12,_13){
var _14;
for(var i=0;i<this.responders.length;i++){
_14=this.responders[i];
if(_14[_11]&&typeof _14[_11]=="function"){
try{
_14[_11].apply(_14,[_12,_13]);
}
catch(e){
}
}
}
}};
OpenLayers.Ajax.Responders.register({onCreate:function(){
OpenLayers.Ajax.activeRequestCount++;
},onComplete:function(){
OpenLayers.Ajax.activeRequestCount--;
}});
OpenLayers.Ajax.Base=OpenLayers.Class({initialize:function(_16){
this.options={method:"post",asynchronous:true,contentType:"application/xml",parameters:""};
OpenLayers.Util.extend(this.options,_16||{});
this.options.method=this.options.method.toLowerCase();
if(typeof this.options.parameters=="string"){
this.options.parameters=OpenLayers.Util.getParameters(this.options.parameters);
}
}});
OpenLayers.Ajax.Request=OpenLayers.Class(OpenLayers.Ajax.Base,{_complete:false,initialize:function(url,_18){
OpenLayers.Ajax.Base.prototype.initialize.apply(this,[_18]);
if(OpenLayers.ProxyHost&&OpenLayers.String.startsWith(url,"http")){
url=OpenLayers.ProxyHost+encodeURIComponent(url);
}
this.transport=OpenLayers.Ajax.getTransport();
this.request(url);
},request:function(url){
this.url=url;
this.method=this.options.method;
var _1a=OpenLayers.Util.extend({},this.options.parameters);
if(this.method!="get"&&this.method!="post"){
_1a["_method"]=this.method;
this.method="post";
}
this.parameters=_1a;
if(_1a=OpenLayers.Util.getParameterString(_1a)){
if(this.method=="get"){
this.url+=((this.url.indexOf("?")>-1)?"&":"?")+_1a;
}else{
if(/Konqueror|Safari|KHTML/.test(navigator.userAgent)){
_1a+="&_=";
}
}
}
try{
var _1b=new OpenLayers.Ajax.Response(this);
if(this.options.onCreate){
this.options.onCreate(_1b);
}
OpenLayers.Ajax.Responders.dispatch("onCreate",this,_1b);
this.transport.open(this.method.toUpperCase(),this.url,this.options.asynchronous);
if(this.options.asynchronous){
window.setTimeout(OpenLayers.Function.bind(this.respondToReadyState,this,1),10);
}
this.transport.onreadystatechange=OpenLayers.Function.bind(this.onStateChange,this);
this.setRequestHeaders();
this.body=this.method=="post"?(this.options.postBody||_1a):null;
this.transport.send(this.body);
if(!this.options.asynchronous&&this.transport.overrideMimeType){
this.onStateChange();
}
}
catch(e){
this.dispatchException(e);
}
},onStateChange:function(){
var _1c=this.transport.readyState;
if(_1c>1&&!((_1c==4)&&this._complete)){
this.respondToReadyState(this.transport.readyState);
}
},setRequestHeaders:function(){
var _1d={"X-Requested-With":"XMLHttpRequest","Accept":"text/javascript, text/html, application/xml, text/xml, */*","OpenLayers":true};
if(this.method=="post"){
_1d["Content-type"]=this.options.contentType+(this.options.encoding?"; charset="+this.options.encoding:"");
if(this.transport.overrideMimeType&&(navigator.userAgent.match(/Gecko\/(\d{4})/)||[0,2005])[1]<2005){
_1d["Connection"]="close";
}
}
if(typeof this.options.requestHeaders=="object"){
var _1e=this.options.requestHeaders;
if(typeof _1e.push=="function"){
for(var i=0,length=_1e.length;i<length;i+=2){
_1d[_1e[i]]=_1e[i+1];
}
}else{
for(var i in _1e){
_1d[i]=pair[i];
}
}
}
for(var _20 in _1d){
this.transport.setRequestHeader(_20,_1d[_20]);
}
},success:function(){
var _21=this.getStatus();
return !_21||(_21>=200&&_21<300);
},getStatus:function(){
try{
return this.transport.status||0;
}
catch(e){
return 0;
}
},respondToReadyState:function(_22){
var _23=OpenLayers.Ajax.Request.Events[_22];
var _24=new OpenLayers.Ajax.Response(this);
if(_23=="Complete"){
try{
this._complete=true;
(this.options["on"+_24.status]||this.options["on"+(this.success()?"Success":"Failure")]||OpenLayers.Ajax.emptyFunction)(_24);
}
catch(e){
this.dispatchException(e);
}
var _25=_24.getHeader("Content-type");
}
try{
(this.options["on"+_23]||OpenLayers.Ajax.emptyFunction)(_24);
OpenLayers.Ajax.Responders.dispatch("on"+_23,this,_24);
}
catch(e){
this.dispatchException(e);
}
if(_23=="Complete"){
this.transport.onreadystatechange=OpenLayers.Ajax.emptyFunction;
}
},getHeader:function(_26){
try{
return this.transport.getResponseHeader(_26);
}
catch(e){
return null;
}
},dispatchException:function(_27){
var _28=this.options.onException;
if(_28){
_28(this,_27);
OpenLayers.Ajax.Responders.dispatch("onException",this,_27);
}else{
var _29=false;
var _2a=OpenLayers.Ajax.Responders.responders;
for(var i=0;i<_2a.length;i++){
if(_2a[i].onException){
_29=true;
break;
}
}
if(_29){
OpenLayers.Ajax.Responders.dispatch("onException",this,_27);
}else{
throw _27;
}
}
}});
OpenLayers.Ajax.Request.Events=["Uninitialized","Loading","Loaded","Interactive","Complete"];
OpenLayers.Ajax.Response=OpenLayers.Class({status:0,statusText:"",initialize:function(_2c){
this.request=_2c;
var _2d=this.transport=_2c.transport,readyState=this.readyState=_2d.readyState;
if((readyState>2&&!(!!(window.attachEvent&&!window.opera)))||readyState==4){
this.status=this.getStatus();
this.statusText=this.getStatusText();
this.responseText=_2d.responseText==null?"":String(_2d.responseText);
}
if(readyState==4){
var xml=_2d.responseXML;
this.responseXML=xml===undefined?null:xml;
}
},getStatus:OpenLayers.Ajax.Request.prototype.getStatus,getStatusText:function(){
try{
return this.transport.statusText||"";
}
catch(e){
return "";
}
},getHeader:OpenLayers.Ajax.Request.prototype.getHeader,getResponseHeader:function(_2f){
return this.transport.getResponseHeader(_2f);
}});
OpenLayers.Ajax.getElementsByTagNameNS=function(_30,_31,_32,_33){
var _34=null;
if(_30.getElementsByTagNameNS){
_34=_30.getElementsByTagNameNS(_31,_33);
}else{
_34=_30.getElementsByTagName(_32+":"+_33);
}
return _34;
};
OpenLayers.Ajax.serializeXMLToString=function(_35){
var _36=new XMLSerializer();
var _37=_36.serializeToString(_35);
return _37;
};

OpenLayers.Request={DEFAULT_CONFIG:{method:"GET",url:window.location.href,async:true,user:undefined,password:undefined,params:null,proxy:OpenLayers.ProxyHost,headers:{},data:null,callback:function(){
},success:null,failure:null,scope:null},issue:function(_1){
var _2=OpenLayers.Util.extend(this.DEFAULT_CONFIG,{proxy:OpenLayers.ProxyHost});
_1=OpenLayers.Util.applyDefaults(_1,_2);
var _3=new OpenLayers.Request.XMLHttpRequest();
var _4=_1.url;
if(_1.params){
_4+="?"+OpenLayers.Util.getParameterString(_1.params);
}
if(_1.proxy&&(_4.indexOf("http")==0)){
_4=_1.proxy+encodeURIComponent(_4);
}
_3.open(_1.method,_4,_1.async,_1.user,_1.password);
for(var _5 in _1.headers){
_3.setRequestHeader(_5,_1.headers[_5]);
}
var _6=(_1.scope)?OpenLayers.Function.bind(_1.callback,_1.scope):_1.callback;
var _7;
if(_1.success){
_7=(_1.scope)?OpenLayers.Function.bind(_1.success,_1.scope):_1.success;
}
var _8;
if(_1.failure){
_8=(_1.scope)?OpenLayers.Function.bind(_1.failure,_1.scope):_1.failure;
}
_3.onreadystatechange=function(){
if(_3.readyState==OpenLayers.Request.XMLHttpRequest.DONE){
_6(_3);
if(_7&&_3.status>=200&&_3.status<300){
_7(_3);
}
if(_8&&(_3.status<200||_3.status>=300)){
_8(_3);
}
}
};
_3.send(_1.data);
return _3;
},GET:function(_9){
_9=OpenLayers.Util.extend(_9,{method:"GET"});
return OpenLayers.Request.issue(_9);
},POST:function(_a){
_a=OpenLayers.Util.extend(_a,{method:"POST"});
_a.headers=_a.headers?_a.headers:{};
if(!("CONTENT-TYPE" in OpenLayers.Util.upperCaseObject(_a.headers))){
_a.headers["Content-Type"]="application/xml";
}
return OpenLayers.Request.issue(_a);
},PUT:function(_b){
_b=OpenLayers.Util.extend(_b,{method:"PUT"});
_b.headers=_b.headers?_b.headers:{};
if(!("CONTENT-TYPE" in OpenLayers.Util.upperCaseObject(_b.headers))){
_b.headers["Content-Type"]="application/xml";
}
return OpenLayers.Request.issue(_b);
},DELETE:function(_c){
_c=OpenLayers.Util.extend(_c,{method:"DELETE"});
return OpenLayers.Request.issue(_c);
},HEAD:function(_d){
_d=OpenLayers.Util.extend(_d,{method:"HEAD"});
return OpenLayers.Request.issue(_d);
},OPTIONS:function(_e){
_e=OpenLayers.Util.extend(_e,{method:"OPTIONS"});
return OpenLayers.Request.issue(_e);
}};

(function(){
var _1=window.XMLHttpRequest;
var _2=!!window.controllers,bIE=window.document.all&&!window.opera;
function cXMLHttpRequest(){
this._object=_1?new _1:new window.ActiveXObject("Microsoft.XMLHTTP");
}
if(_2&&_1.wrapped){
cXMLHttpRequest.wrapped=_1.wrapped;
}
cXMLHttpRequest.UNSENT=0;
cXMLHttpRequest.OPENED=1;
cXMLHttpRequest.HEADERS_RECEIVED=2;
cXMLHttpRequest.LOADING=3;
cXMLHttpRequest.DONE=4;
cXMLHttpRequest.prototype.readyState=cXMLHttpRequest.UNSENT;
cXMLHttpRequest.prototype.responseText="";
cXMLHttpRequest.prototype.responseXML=null;
cXMLHttpRequest.prototype.status=0;
cXMLHttpRequest.prototype.statusText="";
cXMLHttpRequest.prototype.onreadystatechange=null;
cXMLHttpRequest.onreadystatechange=null;
cXMLHttpRequest.onopen=null;
cXMLHttpRequest.onsend=null;
cXMLHttpRequest.onabort=null;
cXMLHttpRequest.prototype.open=function(_3,_4,_5,_6,_7){
this._async=_5;
var _8=this,nState=this.readyState;
if(bIE){
var _9=function(){
if(_8._object.readyState!=cXMLHttpRequest.DONE){
fCleanTransport(_8);
}
};
if(_5){
window.attachEvent("onunload",_9);
}
}
this._object.onreadystatechange=function(){
if(_2&&!_5){
return;
}
_8.readyState=_8._object.readyState;
fSynchronizeValues(_8);
if(_8._aborted){
_8.readyState=cXMLHttpRequest.UNSENT;
return;
}
if(_8.readyState==cXMLHttpRequest.DONE){
fCleanTransport(_8);
if(bIE&&_5){
window.detachEvent("onunload",_9);
}
}
if(nState!=_8.readyState){
fReadyStateChange(_8);
}
nState=_8.readyState;
};
if(cXMLHttpRequest.onopen){
cXMLHttpRequest.onopen.apply(this,arguments);
}
this._object.open(_3,_4,_5,_6,_7);
if(!_5&&_2){
this.readyState=cXMLHttpRequest.OPENED;
fReadyStateChange(this);
}
};
cXMLHttpRequest.prototype.send=function(_a){
if(cXMLHttpRequest.onsend){
cXMLHttpRequest.onsend.apply(this,arguments);
}
if(_a&&_a.nodeType){
_a=window.XMLSerializer?new window.XMLSerializer().serializeToString(_a):_a.xml;
if(!this._headers["Content-Type"]){
this._object.setRequestHeader("Content-Type","application/xml");
}
}
this._object.send(_a);
if(_2&&!this._async){
this.readyState=cXMLHttpRequest.OPENED;
fSynchronizeValues(this);
while(this.readyState<cXMLHttpRequest.DONE){
this.readyState++;
fReadyStateChange(this);
if(this._aborted){
return;
}
}
}
};
cXMLHttpRequest.prototype.abort=function(){
if(cXMLHttpRequest.onabort){
cXMLHttpRequest.onabort.apply(this,arguments);
}
if(this.readyState>cXMLHttpRequest.UNSENT){
this._aborted=true;
}
this._object.abort();
fCleanTransport(this);
};
cXMLHttpRequest.prototype.getAllResponseHeaders=function(){
return this._object.getAllResponseHeaders();
};
cXMLHttpRequest.prototype.getResponseHeader=function(_b){
return this._object.getResponseHeader(_b);
};
cXMLHttpRequest.prototype.setRequestHeader=function(_c,_d){
if(!this._headers){
this._headers={};
}
this._headers[_c]=_d;
return this._object.setRequestHeader(_c,_d);
};
cXMLHttpRequest.prototype.toString=function(){
return "["+"object"+" "+"XMLHttpRequest"+"]";
};
cXMLHttpRequest.toString=function(){
return "["+"XMLHttpRequest"+"]";
};
function fReadyStateChange(_e){
if(_e.onreadystatechange){
_e.onreadystatechange.apply(_e);
}
if(cXMLHttpRequest.onreadystatechange){
cXMLHttpRequest.onreadystatechange.apply(_e);
}
}
function fGetDocument(_f){
var _10=_f.responseXML;
if(bIE&&_10&&!_10.documentElement&&_f.getResponseHeader("Content-Type").match(/[^\/]+\/[^\+]+\+xml/)){
_10=new ActiveXObject("Microsoft.XMLDOM");
_10.loadXML(_f.responseText);
}
if(_10){
if((bIE&&_10.parseError!=0)||(_10.documentElement&&_10.documentElement.tagName=="parsererror")){
return null;
}
}
return _10;
}
function fSynchronizeValues(_11){
try{
_11.responseText=_11._object.responseText;
}
catch(e){
}
try{
_11.responseXML=fGetDocument(_11._object);
}
catch(e){
}
try{
_11.status=_11._object.status;
}
catch(e){
}
try{
_11.statusText=_11._object.statusText;
}
catch(e){
}
}
function fCleanTransport(_12){
_12._object.onreadystatechange=new window.Function;
delete _12._headers;
}
if(!window.Function.prototype.apply){
window.Function.prototype.apply=function(_13,_14){
if(!_14){
_14=[];
}
_13.__func=this;
_13.__func(_14[0],_14[1],_14[2],_14[3],_14[4]);
delete _13.__func;
};
}
OpenLayers.Request.XMLHttpRequest=cXMLHttpRequest;
})();

OpenLayers.Event={observers:false,KEY_BACKSPACE:8,KEY_TAB:9,KEY_RETURN:13,KEY_ESC:27,KEY_LEFT:37,KEY_UP:38,KEY_RIGHT:39,KEY_DOWN:40,KEY_DELETE:46,element:function(_1){
return _1.target||_1.srcElement;
},isLeftClick:function(_2){
return (((_2.which)&&(_2.which==1))||((_2.button)&&(_2.button==1)));
},stop:function(_3,_4){
if(!_4){
if(_3.preventDefault){
_3.preventDefault();
}else{
_3.returnValue=false;
}
}
if(_3.stopPropagation){
_3.stopPropagation();
}else{
_3.cancelBubble=true;
}
},findElement:function(_5,_6){
var _7=OpenLayers.Event.element(_5);
while(_7.parentNode&&(!_7.tagName||(_7.tagName.toUpperCase()!=_6.toUpperCase()))){
_7=_7.parentNode;
}
return _7;
},observe:function(_8,_9,_a,_b){
var _c=OpenLayers.Util.getElement(_8);
_b=_b||false;
if(_9=="keypress"&&(navigator.appVersion.match(/Konqueror|Safari|KHTML/)||_c.attachEvent)){
_9="keydown";
}
if(!this.observers){
this.observers={};
}
if(!_c._eventCacheID){
var _d="eventCacheID_";
if(_c.id){
_d=_c.id+"_"+_d;
}
_c._eventCacheID=OpenLayers.Util.createUniqueID(_d);
}
var _e=_c._eventCacheID;
if(!this.observers[_e]){
this.observers[_e]=[];
}
this.observers[_e].push({"element":_c,"name":_9,"observer":_a,"useCapture":_b});
if(_c.addEventListener){
_c.addEventListener(_9,_a,_b);
}else{
if(_c.attachEvent){
_c.attachEvent("on"+_9,_a);
}
}
},stopObservingElement:function(_f){
var _10=OpenLayers.Util.getElement(_f);
var _11=_10._eventCacheID;
this._removeElementObservers(OpenLayers.Event.observers[_11]);
},_removeElementObservers:function(_12){
if(_12){
for(var i=_12.length-1;i>=0;i--){
var _14=_12[i];
var _15=new Array(_14.element,_14.name,_14.observer,_14.useCapture);
var _16=OpenLayers.Event.stopObserving.apply(this,_15);
}
}
},stopObserving:function(_17,_18,_19,_1a){
_1a=_1a||false;
var _1b=OpenLayers.Util.getElement(_17);
var _1c=_1b._eventCacheID;
if(_18=="keypress"){
if(navigator.appVersion.match(/Konqueror|Safari|KHTML/)||_1b.detachEvent){
_18="keydown";
}
}
var _1d=false;
var _1e=OpenLayers.Event.observers[_1c];
if(_1e){
var i=0;
while(!_1d&&i<_1e.length){
var _20=_1e[i];
if((_20.name==_18)&&(_20.observer==_19)&&(_20.useCapture==_1a)){
_1e.splice(i,1);
if(_1e.length==0){
delete OpenLayers.Event.observers[_1c];
}
_1d=true;
break;
}
i++;
}
}
if(_1d){
if(_1b.removeEventListener){
_1b.removeEventListener(_18,_19,_1a);
}else{
if(_1b&&_1b.detachEvent){
_1b.detachEvent("on"+_18,_19);
}
}
}
return _1d;
},unloadCache:function(){
if(OpenLayers.Event&&OpenLayers.Event.observers){
for(var _21 in OpenLayers.Event.observers){
var _22=OpenLayers.Event.observers[_21];
OpenLayers.Event._removeElementObservers.apply(this,[_22]);
}
OpenLayers.Event.observers=false;
}
},CLASS_NAME:"OpenLayers.Event"};
OpenLayers.Event.observe(window,"unload",OpenLayers.Event.unloadCache,false);
if(window.Event){
OpenLayers.Util.applyDefaults(window.Event,OpenLayers.Event);
}else{
var Event=OpenLayers.Event;
}
OpenLayers.Events=OpenLayers.Class({BROWSER_EVENTS:["mouseover","mouseout","mousedown","mouseup","mousemove","click","dblclick","resize","focus","blur"],listeners:null,object:null,element:null,eventTypes:null,eventHandler:null,fallThrough:null,initialize:function(_23,_24,_25,_26){
this.object=_23;
this.element=_24;
this.eventTypes=_25;
this.fallThrough=_26;
this.listeners={};
this.eventHandler=OpenLayers.Function.bindAsEventListener(this.handleBrowserEvent,this);
if(this.eventTypes!=null){
for(var i=0;i<this.eventTypes.length;i++){
this.addEventType(this.eventTypes[i]);
}
}
if(this.element!=null){
this.attachToElement(_24);
}
},destroy:function(){
if(this.element){
OpenLayers.Event.stopObservingElement(this.element);
}
this.element=null;
this.listeners=null;
this.object=null;
this.eventTypes=null;
this.fallThrough=null;
this.eventHandler=null;
},addEventType:function(_28){
if(!this.listeners[_28]){
this.listeners[_28]=[];
}
},attachToElement:function(_29){
for(var i=0;i<this.BROWSER_EVENTS.length;i++){
var _2b=this.BROWSER_EVENTS[i];
this.addEventType(_2b);
OpenLayers.Event.observe(_29,_2b,this.eventHandler);
}
OpenLayers.Event.observe(_29,"dragstart",OpenLayers.Event.stop);
},on:function(_2c){
for(var _2d in _2c){
if(_2d!="scope"){
this.register(_2d,_2c.scope,_2c[_2d]);
}
}
},register:function(_2e,obj,_30){
if(_30!=null&&((this.eventTypes&&OpenLayers.Util.indexOf(this.eventTypes,_2e)!=-1)||OpenLayers.Util.indexOf(this.BROWSER_EVENTS,_2e)!=-1)){
if(obj==null){
obj=this.object;
}
var _31=this.listeners[_2e];
if(_31!=null){
_31.push({obj:obj,func:_30});
}
}
},registerPriority:function(_32,obj,_34){
if(_34!=null){
if(obj==null){
obj=this.object;
}
var _35=this.listeners[_32];
if(_35!=null){
_35.unshift({obj:obj,func:_34});
}
}
},un:function(_36){
for(var _37 in _36){
if(_37!="scope"){
this.unregister(_37,_36.scope,_36[_37]);
}
}
},unregister:function(_38,obj,_3a){
if(obj==null){
obj=this.object;
}
var _3b=this.listeners[_38];
if(_3b!=null){
for(var i=0;i<_3b.length;i++){
if(_3b[i].obj==obj&&_3b[i].func==_3a){
_3b.splice(i,1);
break;
}
}
}
},remove:function(_3d){
if(this.listeners[_3d]!=null){
this.listeners[_3d]=[];
}
},triggerEvent:function(_3e,evt){
if(evt==null){
evt={};
}
evt.object=this.object;
evt.element=this.element;
if(!evt.type){
evt.type=_3e;
}
var _40=(this.listeners[_3e])?this.listeners[_3e].slice():null;
if((_40!=null)&&(_40.length>0)){
var _41;
for(var i=0;i<_40.length;i++){
var _43=_40[i];
_41=_43.func.apply(_43.obj,[evt]);
if((_41!=undefined)&&(_41==false)){
break;
}
}
if(!this.fallThrough){
OpenLayers.Event.stop(evt,true);
}
}
return _41;
},handleBrowserEvent:function(evt){
evt.xy=this.getMousePosition(evt);
this.triggerEvent(evt.type,evt);
},getMousePosition:function(evt){
if(!this.element.offsets){
this.element.offsets=OpenLayers.Util.pagePosition(this.element);
this.element.offsets[0]+=(document.documentElement.scrollLeft||document.body.scrollLeft);
this.element.offsets[1]+=(document.documentElement.scrollTop||document.body.scrollTop);
}
return new OpenLayers.Pixel((evt.clientX+(document.documentElement.scrollLeft||document.body.scrollLeft))-this.element.offsets[0]-(document.documentElement.clientLeft||0),(evt.clientY+(document.documentElement.scrollTop||document.body.scrollTop))-this.element.offsets[1]-(document.documentElement.clientTop||0));
},CLASS_NAME:"OpenLayers.Events"});

OpenLayers.Projection=OpenLayers.Class({proj:null,projCode:null,initialize:function(_1,_2){
OpenLayers.Util.extend(this,_2);
this.projCode=_1;
if(window.Proj4js){
this.proj=new Proj4js.Proj(_1);
}
},getCode:function(){
return this.proj?this.proj.srsCode:this.projCode;
},getUnits:function(){
return this.proj?this.proj.units:null;
},toString:function(){
return this.getCode();
},equals:function(_3){
if(_3&&_3.getCode){
return this.getCode()==_3.getCode();
}else{
return false;
}
},destroy:function(){
delete this.proj;
delete this.projCode;
},CLASS_NAME:"OpenLayers.Projection"});
OpenLayers.Projection.transforms={};
OpenLayers.Projection.addTransform=function(_4,to,_6){
if(!OpenLayers.Projection.transforms[_4]){
OpenLayers.Projection.transforms[_4]={};
}
OpenLayers.Projection.transforms[_4][to]=_6;
};
OpenLayers.Projection.transform=function(_7,_8,_9){
if(_8.proj&&_9.proj){
_7=Proj4js.transform(_8.proj,_9.proj,_7);
}else{
if(_8&&_9&&OpenLayers.Projection.transforms[_8.getCode()]&&OpenLayers.Projection.transforms[_8.getCode()][_9.getCode()]){
OpenLayers.Projection.transforms[_8.getCode()][_9.getCode()](_7);
}
}
return _7;
};

Proj4js={defaultDatum:"WGS84",proxyScript:null,defsLookupService:"http://spatialreference.org/ref",libPath:window.OpenLayers._getScriptLocation()+"proj4js/",transform:function(_1,_2,_3){
if(!_1.readyToUse||!_2.readyToUse){
this.reportError("Proj4js initialization for "+_1.srsCode+" not yet complete");
return;
}
if(_3.transformed){
this.log("point already transformed");
return;
}
if((_1.srsProjNumber=="900913"&&_2.datumCode!="WGS84")||(_2.srsProjNumber=="900913"&&_1.datumCode!="WGS84")){
var _4=Proj4js.WGS84;
this.transform(_1,_4,_3);
_3.transformed=false;
_1=_4;
}
if(_1.projName=="longlat"){
_3.x*=Proj4js.common.D2R;
_3.y*=Proj4js.common.D2R;
}else{
if(_1.to_meter){
_3.x*=_1.to_meter;
_3.y*=_1.to_meter;
}
_1.inverse(_3);
}
if(_1.from_greenwich){
_3.x+=_1.from_greenwich;
}
_3=this.datum_transform(_1.datum,_2.datum,_3);
if(_2.from_greenwich){
_3.x-=_2.from_greenwich;
}
if(_2.projName=="longlat"){
_3.x*=Proj4js.common.R2D;
_3.y*=Proj4js.common.R2D;
}else{
_2.forward(_3);
if(_2.to_meter){
_3.x/=_2.to_meter;
_3.y/=_2.to_meter;
}
}
_3.transformed=true;
return _3;
},datum_transform:function(_5,_6,_7){
if(_5.compare_datums(_6)){
return _7;
}
if(_5.datum_type==Proj4js.common.PJD_NODATUM||_6.datum_type==Proj4js.common.PJD_NODATUM){
return _7;
}
if(_5.datum_type==Proj4js.common.PJD_GRIDSHIFT){
alert("ERROR: Grid shift transformations are not implemented yet.");
}
if(_6.datum_type==Proj4js.common.PJD_GRIDSHIFT){
alert("ERROR: Grid shift transformations are not implemented yet.");
}
if(_5.es!=_6.es||_5.a!=_6.a||_5.datum_type==Proj4js.common.PJD_3PARAM||_5.datum_type==Proj4js.common.PJD_7PARAM||_6.datum_type==Proj4js.common.PJD_3PARAM||_6.datum_type==Proj4js.common.PJD_7PARAM){
_5.geodetic_to_geocentric(_7);
if(_5.datum_type==Proj4js.common.PJD_3PARAM||_5.datum_type==Proj4js.common.PJD_7PARAM){
_5.geocentric_to_wgs84(_7);
}
if(_6.datum_type==Proj4js.common.PJD_3PARAM||_6.datum_type==Proj4js.common.PJD_7PARAM){
_6.geocentric_from_wgs84(_7);
}
_6.geocentric_to_geodetic(_7);
}
if(_6.datum_type==Proj4js.common.PJD_GRIDSHIFT){
alert("ERROR: Grid shift transformations are not implemented yet.");
}
return _7;
},reportError:function(_8){
},log:function(_9){
},loadProjDefinition:function(_a){
if(this.defs[_a.srsCode]){
return this.defs[_a.srsCode];
}
var _b={method:"get",asynchronous:false,onSuccess:this.defsLoadedFromDisk.bind(this,_a.srsCode)};
var _c=this.libPath+"defs/"+_a.srsAuth.toUpperCase()+_a.srsProjNumber+".js";
new OpenLayers.Ajax.Request(_c,_b);
if(this.defs[_a.srsCode]){
return this.defs[_a.srsCode];
}
if(this.proxyScript){
var _c=this.proxyScript+this.defsLookupService+"/"+_a.srsAuth+"/"+_a.srsProjNumber+"/proj4";
_b.onSuccess=this.defsLoadedFromService.bind(this,_a.srsCode);
_b.onFailure=this.defsFailed.bind(this,_a.srsCode);
new OpenLayers.Ajax.Request(_c,_b);
}
return this.defs[_a.srsCode];
},defsLoadedFromDisk:function(_d,_e){
eval(_e.responseText);
},defsLoadedFromService:function(_f,_10){
this.defs[_f]=_10.responseText;
Proj4js.defs[_f]=_10.responseText;
},defsFailed:function(_11){
this.reportError("failed to load projection definition for: "+_11);
OpenLayers.Util.extend(this.defs[_11],this.defs["WGS84"]);
},loadProjCode:function(_12){
if(this.Proj[_12]){
return;
}
var _13={method:"get",asynchronous:false,onSuccess:this.loadProjCodeSuccess.bind(this,_12),onFailure:this.loadProjCodeFailure.bind(this,_12)};
var url=this.libPath+"projCode/"+_12+".js";
new OpenLayers.Ajax.Request(url,_13);
},loadProjCodeSuccess:function(_15,_16){
eval(_16.responseText);
if(this.Proj[_15].dependsOn){
this.loadProjCode(this.Proj[_15].dependsOn);
}
},loadProjCodeFailure:function(_17){
Proj4js.reportError("failed to find projection file for: "+_17);
}};
Proj4js.Proj=OpenLayers.Class({readyToUse:false,title:null,projName:null,units:null,datum:null,initialize:function(_18){
this.srsCode=_18.toUpperCase();
if(this.srsCode.indexOf("EPSG")==0){
this.srsCode=this.srsCode;
this.srsAuth="epsg";
this.srsProjNumber=this.srsCode.substring(5);
}else{
this.srsAuth="";
this.srsProjNumber=this.srsCode;
}
var _19=Proj4js.loadProjDefinition(this);
if(_19){
this.parseDefs(_19);
Proj4js.loadProjCode(this.projName);
this.callInit();
}
},callInit:function(){
Proj4js.log("projection script loaded for:"+this.projName);
OpenLayers.Util.extend(this,Proj4js.Proj[this.projName]);
this.init();
this.mapXYToLonLat=this.inverse;
this.lonLatToMapXY=this.forward;
this.readyToUse=true;
},parseDefs:function(_1a){
this.defData=_1a;
var _1b,paramVal;
var _1c=this.defData.split("+");
for(var _1d=0;_1d<_1c.length;_1d++){
var _1e=_1c[_1d].split("=");
_1b=_1e[0].toLowerCase();
paramVal=_1e[1];
switch(_1b.replace(/\s/gi,"")){
case "":
break;
case "title":
this.title=paramVal;
break;
case "proj":
this.projName=paramVal.replace(/\s/gi,"");
break;
case "units":
this.units=paramVal.replace(/\s/gi,"");
break;
case "datum":
this.datumCode=paramVal.replace(/\s/gi,"");
break;
case "nadgrids":
this.nagrids=paramVal.replace(/\s/gi,"");
break;
case "ellps":
this.ellps=paramVal.replace(/\s/gi,"");
break;
case "a":
this.a=parseFloat(paramVal);
break;
case "b":
this.b=parseFloat(paramVal);
break;
case "lat_0":
this.lat0=paramVal*Proj4js.common.D2R;
break;
case "lat_1":
this.lat1=paramVal*Proj4js.common.D2R;
break;
case "lat_2":
this.lat2=paramVal*Proj4js.common.D2R;
break;
case "lat_ts":
this.lat_ts=paramVal*Proj4js.common.D2R;
break;
case "lon_0":
this.long0=paramVal*Proj4js.common.D2R;
break;
case "x_0":
this.x0=parseFloat(paramVal);
break;
case "y_0":
this.y0=parseFloat(paramVal);
break;
case "k_0":
this.k0=parseFloat(paramVal);
break;
case "k":
this.k0=parseFloat(paramVal);
break;
case "R_A":
this.R=parseFloat(paramVal);
break;
case "zone":
this.zone=parseInt(paramVal);
break;
case "south":
this.utmSouth=true;
break;
case "towgs84":
this.datum_params=paramVal.split(",");
break;
case "to_meter":
this.to_meter=parseFloat(paramVal);
break;
case "from_greenwich":
this.from_greenwich=paramVal*Proj4js.common.D2R;
break;
case "pm":
paramVal=paramVal.replace(/\s/gi,"");
this.from_greenwich=Proj4js.PrimeMeridian[paramVal]?Proj4js.PrimeMeridian[paramVal]*Proj4js.common.D2R:0;
break;
case "no_defs":
break;
default:
Proj4js.log("Unrecognized parameter: "+_1b);
}
}
this.deriveConstants();
},deriveConstants:function(){
if(this.nagrids=="@null"){
this.datumCode="none";
}
if(this.datumCode&&this.datumCode!="none"){
var _1f=Proj4js.Datum[this.datumCode];
if(_1f){
this.datum_params=_1f.towgs84.split(",");
this.ellps=_1f.ellipse;
this.datumName=_1f.datumName;
}
}
if(!this.a){
var _20=Proj4js.Ellipsoid[this.ellps]?Proj4js.Ellipsoid[this.ellps]:Proj4js.Ellipsoid["WGS84"];
OpenLayers.Util.extend(this,_20);
}
if(this.rf&&!this.b){
this.b=(1-1/this.rf)*this.a;
}
if(Math.abs(this.a-this.b)<Proj4js.common.EPSLN){
this.sphere=true;
}
this.a2=this.a*this.a;
this.b2=this.b*this.b;
this.es=(this.a2-this.b2)/this.a2;
this.e=Math.sqrt(this.es);
this.ep2=(this.a2-this.b2)/this.b2;
if(!this.k0){
this.k0=1;
}
this.datum=new Proj4js.datum(this);
}});
Proj4js.Proj.longlat={init:function(){
},forward:function(pt){
return pt;
},inverse:function(pt){
return pt;
}};
Proj4js.defs={"WGS84":"+title=long/lat:WGS84 +proj=longlat +ellps=WGS84 +datum=WGS84","EPSG:4326":"+title=long/lat:WGS84 +proj=longlat +a=6378137.0 +b=6356752.31424518 +ellps=WGS84 +datum=WGS84","EPSG:4269":"+title=long/lat:NAD83 +proj=longlat +a=6378137.0 +b=6356752.31414036 +ellps=GRS80 +datum=NAD83"};
Proj4js.common={PI:Math.PI,HALF_PI:Math.PI*0.5,TWO_PI:Math.PI*2,FORTPI:0.7853981633974483,R2D:57.2957795131,D2R:0.0174532925199,SEC_TO_RAD:0.00000484813681109536,EPSLN:1e-10,MAX_ITER:20,COS_67P5:0.3826834323650898,AD_C:1.0026,PJD_UNKNOWN:0,PJD_3PARAM:1,PJD_7PARAM:2,PJD_GRIDSHIFT:3,PJD_WGS84:4,PJD_NODATUM:5,SRS_WGS84_SEMIMAJOR:6378137,msfnz:function(_23,_24,_25){
var con=_23*_24;
return _25/(Math.sqrt(1-con*con));
},tsfnz:function(_27,phi,_29){
var con=_27*_29;
var com=0.5*_27;
con=Math.pow(((1-con)/(1+con)),com);
return (Math.tan(0.5*(this.HALF_PI-phi))/con);
},phi2z:function(_2c,ts){
var _2e=0.5*_2c;
var con,dphi;
var phi=this.HALF_PI-2*Math.atan(ts);
for(i=0;i<=15;i++){
con=_2c*Math.sin(phi);
dphi=this.HALF_PI-2*Math.atan(ts*(Math.pow(((1-con)/(1+con)),_2e)))-phi;
phi+=dphi;
if(Math.abs(dphi)<=1e-10){
return phi;
}
}
alert("phi2z has NoConvergence");
return (-9999);
},qsfnz:function(_31,_32,_33){
var con;
if(_31>1e-7){
con=_31*_32;
return ((1-_31*_31)*(_32/(1-con*con)-(0.5/_31)*Math.log((1-con)/(1+con))));
}else{
return (2*_32);
}
},asinz:function(x){
if(Math.abs(x)>1){
x=(x>1)?1:-1;
}
return Math.asin(x);
},e0fn:function(x){
return (1-0.25*x*(1+x/16*(3+1.25*x)));
},e1fn:function(x){
return (0.375*x*(1+0.25*x*(1+0.46875*x)));
},e2fn:function(x){
return (0.05859375*x*x*(1+0.75*x));
},e3fn:function(x){
return (x*x*x*(35/3072));
},mlfn:function(e0,e1,e2,e3,phi){
return (e0*phi-e1*Math.sin(2*phi)+e2*Math.sin(4*phi)-e3*Math.sin(6*phi));
},srat:function(_3f,exp){
return (Math.pow((1-_3f)/(1+_3f),exp));
},sign:function(x){
if(x<0){
return (-1);
}else{
return (1);
}
},adjust_lon:function(x){
x=(Math.abs(x)<this.PI)?x:(x-(this.sign(x)*this.TWO_PI));
return x;
}};
Proj4js.datum=OpenLayers.Class({initialize:function(_43){
this.datum_type=Proj4js.common.PJD_WGS84;
if(_43.datumCode&&_43.datumCode=="none"){
this.datum_type=Proj4js.common.PJD_NODATUM;
}
if(_43&&_43.datum_params){
for(var i=0;i<_43.datum_params.length;i++){
_43.datum_params[i]=parseFloat(_43.datum_params[i]);
}
if(_43.datum_params[0]!=0||_43.datum_params[1]!=0||_43.datum_params[2]!=0){
this.datum_type=Proj4js.common.PJD_3PARAM;
}
if(_43.datum_params.length>3){
if(_43.datum_params[3]!=0||_43.datum_params[4]!=0||_43.datum_params[5]!=0||_43.datum_params[6]!=0){
this.datum_type=Proj4js.common.PJD_7PARAM;
_43.datum_params[3]*=Proj4js.common.SEC_TO_RAD;
_43.datum_params[4]*=Proj4js.common.SEC_TO_RAD;
_43.datum_params[5]*=Proj4js.common.SEC_TO_RAD;
_43.datum_params[6]=(_43.datum_params[6]/1000000)+1;
}
}
}
if(_43){
this.a=_43.a;
this.b=_43.b;
this.es=_43.es;
this.ep2=_43.ep2;
this.datum_params=_43.datum_params;
}
},compare_datums:function(_45){
if(this.datum_type!=_45.datum_type){
return false;
}else{
if(this.a!=_45.a||Math.abs(this.es-_45.es)>5e-11){
return false;
}else{
if(this.datum_type==Proj4js.common.PJD_3PARAM){
return (this.datum_params[0]==_45.datum_params[0]&&this.datum_params[1]==_45.datum_params[1]&&this.datum_params[2]==_45.datum_params[2]);
}else{
if(this.datum_type==Proj4js.common.PJD_7PARAM){
return (this.datum_params[0]==_45.datum_params[0]&&this.datum_params[1]==_45.datum_params[1]&&this.datum_params[2]==_45.datum_params[2]&&this.datum_params[3]==_45.datum_params[3]&&this.datum_params[4]==_45.datum_params[4]&&this.datum_params[5]==_45.datum_params[5]&&this.datum_params[6]==_45.datum_params[6]);
}else{
if(this.datum_type==Proj4js.common.PJD_GRIDSHIFT){
return strcmp(pj_param(this.params,"snadgrids").s,pj_param(_45.params,"snadgrids").s)==0;
}else{
return true;
}
}
}
}
}
},geodetic_to_geocentric:function(p){
var _47=p.x;
var _48=p.y;
var _49=p.z?p.z:0;
var X;
var Y;
var Z;
var _4d=0;
var Rn;
var _4f;
var _50;
var _51;
if(_48<-Proj4js.common.HALF_PI&&_48>-1.001*Proj4js.common.HALF_PI){
_48=-Proj4js.common.HALF_PI;
}else{
if(_48>Proj4js.common.HALF_PI&&_48<1.001*Proj4js.common.HALF_PI){
_48=Proj4js.common.HALF_PI;
}else{
if((_48<-Proj4js.common.HALF_PI)||(_48>Proj4js.common.HALF_PI)){
Proj4js.reportError("geocent:lat out of range:"+_48);
return null;
}
}
}
if(_47>Proj4js.common.PI){
_47-=(2*Proj4js.common.PI);
}
_4f=Math.sin(_48);
_51=Math.cos(_48);
_50=_4f*_4f;
Rn=this.a/(Math.sqrt(1-this.es*_50));
X=(Rn+_49)*_51*Math.cos(_47);
Y=(Rn+_49)*_51*Math.sin(_47);
Z=((Rn*(1-this.es))+_49)*_4f;
p.x=X;
p.y=Y;
p.z=Z;
return _4d;
},geocentric_to_geodetic:function(p){
var _53=1e-12;
var _54=(_53*_53);
var _55=30;
var P;
var RR;
var CT;
var ST;
var RX;
var RK;
var RN;
var _5d;
var _5e;
var _5f;
var _60;
var _61;
var _62;
var _63;
var X=p.x;
var Y=p.y;
var Z=p.z?p.z:0;
var _67;
var _68;
var _69;
_62=false;
P=Math.sqrt(X*X+Y*Y);
RR=Math.sqrt(X*X+Y*Y+Z*Z);
if(P/this.a<_53){
_62=true;
_67=0;
if(RR/this.a<_53){
_68=Proj4js.common.HALF_PI;
_69=-this.b;
return;
}
}else{
_67=Math.atan2(Y,X);
}
CT=Z/RR;
ST=P/RR;
RX=1/Math.sqrt(1-this.es*(2-this.es)*ST*ST);
_5d=ST*(1-this.es)*RX;
_5e=CT*RX;
_63=0;
do{
_63++;
RN=this.a/Math.sqrt(1-this.es*_5e*_5e);
_69=P*_5d+Z*_5e-RN*(1-this.es*_5e*_5e);
RK=this.es*RN/(RN+_69);
RX=1/Math.sqrt(1-RK*(2-RK)*ST*ST);
_5f=ST*(1-RK)*RX;
_60=CT*RX;
_61=_60*_5d-_5f*_5e;
_5d=_5f;
_5e=_60;
}while(_61*_61>_54&&_63<_55);
_68=Math.atan(_60/Math.abs(_5f));
p.x=_67;
p.y=_68;
p.z=_69;
return p;
},geocentric_to_geodetic_noniter:function(p){
var X=p.x;
var Y=p.y;
var Z=p.z?p.z:0;
var _6e;
var _6f;
var _70;
var W;
var W2;
var T0;
var T1;
var S0;
var S1;
var _77;
var _78;
var _79;
var _7a;
var _7b;
var Rn;
var Sum;
var _7e;
X=parseFloat(X);
Y=parseFloat(Y);
Z=parseFloat(Z);
_7e=false;
if(X!=0){
_6e=Math.atan2(Y,X);
}else{
if(Y>0){
_6e=Proj4js.common.HALF_PI;
}else{
if(Y<0){
_6e=-Proj4js.common.HALF_PI;
}else{
_7e=true;
_6e=0;
if(Z>0){
_6f=Proj4js.common.HALF_PI;
}else{
if(Z<0){
_6f=-Proj4js.common.HALF_PI;
}else{
_6f=Proj4js.common.HALF_PI;
_70=-this.b;
return;
}
}
}
}
}
W2=X*X+Y*Y;
W=Math.sqrt(W2);
T0=Z*Proj4js.common.AD_C;
S0=Math.sqrt(T0*T0+W2);
_77=T0/S0;
_79=W/S0;
_78=_77*_77*_77;
T1=Z+this.b*this.ep2*_78;
Sum=W-this.a*this.es*_79*_79*_79;
S1=Math.sqrt(T1*T1+Sum*Sum);
_7a=T1/S1;
_7b=Sum/S1;
Rn=this.a/Math.sqrt(1-this.es*_7a*_7a);
if(_7b>=Proj4js.common.COS_67P5){
_70=W/_7b-Rn;
}else{
if(_7b<=-Proj4js.common.COS_67P5){
_70=W/-_7b-Rn;
}else{
_70=Z/_7a+Rn*(this.es-1);
}
}
if(_7e==false){
_6f=Math.atan(_7a/_7b);
}
p.x=_6e;
p.y=_6f;
p.z=_70;
return p;
},geocentric_to_wgs84:function(p){
if(this.datum_type==Proj4js.common.PJD_3PARAM){
p.x+=this.datum_params[0];
p.y+=this.datum_params[1];
p.z+=this.datum_params[2];
}else{
var _80=this.datum_params[0];
var _81=this.datum_params[1];
var _82=this.datum_params[2];
var _83=this.datum_params[3];
var _84=this.datum_params[4];
var _85=this.datum_params[5];
var _86=this.datum_params[6];
var _87=_86*(p.x-_85*p.y+_84*p.z)+_80;
var _88=_86*(_85*p.x+p.y-_83*p.z)+_81;
var _89=_86*(-_84*p.x+_83*p.y+p.z)+_82;
p.x=_87;
p.y=_88;
p.z=_89;
}
},geocentric_from_wgs84:function(p){
if(this.datum_type==Proj4js.common.PJD_3PARAM){
p.x-=this.datum_params[0];
p.y-=this.datum_params[1];
p.z-=this.datum_params[2];
}else{
var _8b=this.datum_params[0];
var _8c=this.datum_params[1];
var _8d=this.datum_params[2];
var _8e=this.datum_params[3];
var _8f=this.datum_params[4];
var _90=this.datum_params[5];
var _91=this.datum_params[6];
var _92=(p.x-_8b)/_91;
var _93=(p.y-_8c)/_91;
var _94=(p.z-_8d)/_91;
p.x=_92+_90*_93-_8f*_94;
p.y=-_90*_92+_93+_8e*_94;
p.z=_8f*_92-_8e*_93+_94;
}
}});
Proj4js.Point=OpenLayers.Class({initialize:function(x,y,z){
if(typeof x=="object"){
this.x=x[0];
this.y=x[1];
this.z=x[2]||0;
}else{
this.x=x;
this.y=y;
this.z=z||0;
}
},clone:function(){
return new Proj4js.Point(this.x,this.y,this.z);
},toString:function(){
return ("x="+this.x+",y="+this.y);
},toShortString:function(){
return (this.x+", "+this.y);
}});
Proj4js.PrimeMeridian={"greenwich":0,"lisbon":-9.131906111111,"paris":2.337229166667,"bogota":-74.080916666667,"madrid":-3.687938888889,"rome":12.452333333333,"bern":7.439583333333,"jakarta":106.807719444444,"ferro":-17.666666666667,"brussels":4.367975,"stockholm":18.058277777778,"athens":23.7163375,"oslo":10.722916666667};
Proj4js.Ellipsoid={"MERIT":{a:6378137,rf:298.257,ellipseName:"MERIT 1983"},"SGS85":{a:6378136,rf:298.257,ellipseName:"Soviet Geodetic System 85"},"GRS80":{a:6378137,rf:298.257222101,ellipseName:"GRS 1980(IUGG, 1980)"},"IAU76":{a:6378140,rf:298.257,ellipseName:"IAU 1976"},"airy":{a:6377563.396,b:6356256.91,ellipseName:"Airy 1830"},"APL4.":{a:6378137,rf:298.25,ellipseName:"Appl. Physics. 1965"},"NWL9D":{a:6378145,rf:298.25,ellipseName:"Naval Weapons Lab., 1965"},"mod_airy":{a:6377340.189,b:6356034.446,ellipseName:"Modified Airy"},"andrae":{a:6377104.43,rf:300,ellipseName:"Andrae 1876 (Den., Iclnd.)"},"aust_SA":{a:6378160,rf:298.25,ellipseName:"Australian Natl & S. Amer. 1969"},"GRS67":{a:6378160,rf:298.247167427,ellipseName:"GRS 67(IUGG 1967)"},"bessel":{a:6377397.155,rf:299.1528128,ellipseName:"Bessel 1841"},"bess_nam":{a:6377483.865,rf:299.1528128,ellipseName:"Bessel 1841 (Namibia)"},"clrk66":{a:6378206.4,b:6356583.8,ellipseName:"Clarke 1866"},"clrk80":{a:6378249.145,rf:293.4663,ellipseName:"Clarke 1880 mod."},"CPM":{a:6375738.7,rf:334.29,ellipseName:"Comm. des Poids et Mesures 1799"},"delmbr":{a:6376428,rf:311.5,ellipseName:"Delambre 1810 (Belgium)"},"engelis":{a:6378136.05,rf:298.2566,ellipseName:"Engelis 1985"},"evrst30":{a:6377276.345,rf:300.8017,ellipseName:"Everest 1830"},"evrst48":{a:6377304.063,rf:300.8017,ellipseName:"Everest 1948"},"evrst56":{a:6377301.243,rf:300.8017,ellipseName:"Everest 1956"},"evrst69":{a:6377295.664,rf:300.8017,ellipseName:"Everest 1969"},"evrstSS":{a:6377298.556,rf:300.8017,ellipseName:"Everest (Sabah & Sarawak)"},"fschr60":{a:6378166,rf:298.3,ellipseName:"Fischer (Mercury Datum) 1960"},"fschr60m":{a:6378155,rf:298.3,ellipseName:"Fischer 1960"},"fschr68":{a:6378150,rf:298.3,ellipseName:"Fischer 1968"},"helmert":{a:6378200,rf:298.3,ellipseName:"Helmert 1906"},"hough":{a:6378270,rf:297,ellipseName:"Hough"},"intl":{a:6378388,rf:297,ellipseName:"International 1909 (Hayford)"},"kaula":{a:6378163,rf:298.24,ellipseName:"Kaula 1961"},"lerch":{a:6378139,rf:298.257,ellipseName:"Lerch 1979"},"mprts":{a:6397300,rf:191,ellipseName:"Maupertius 1738"},"new_intl":{a:6378157.5,b:6356772.2,ellipseName:"New International 1967"},"plessis":{a:6376523,rf:6355863,ellipseName:"Plessis 1817 (France)"},"krass":{a:6378245,rf:298.3,ellipseName:"Krassovsky, 1942"},"SEasia":{a:6378155,b:6356773.3205,ellipseName:"Southeast Asia"},"walbeck":{a:6376896,b:6355834.8467,ellipseName:"Walbeck"},"WGS60":{a:6378165,rf:298.3,ellipseName:"WGS 60"},"WGS66":{a:6378145,rf:298.25,ellipseName:"WGS 66"},"WGS72":{a:6378135,rf:298.26,ellipseName:"WGS 72"},"WGS84":{a:6378137,rf:298.257223563,ellipseName:"WGS 84"},"sphere":{a:6370997,b:6370997,ellipseName:"Normal Sphere (r=6370997)"}};
Proj4js.Datum={"WGS84":{towgs84:"0,0,0",ellipse:"WGS84",datumName:""},"GGRS87":{towgs84:"-199.87,74.79,246.62",ellipse:"GRS80",datumName:"Greek_Geodetic_Reference_System_1987"},"NAD83":{towgs84:"0,0,0",ellipse:"GRS80",datumName:"North_American_Datum_1983"},"NAD27":{nadgrids:"@conus,@alaska,@ntv2_0.gsb,@ntv1_can.dat",ellipse:"clrk66",datumName:"North_American_Datum_1927"},"potsdam":{towgs84:"606.0,23.0,413.0",ellipse:"bessel",datumName:"Potsdam Rauenberg 1950 DHDN"},"carthage":{towgs84:"-263.0,6.0,431.0",ellipse:"clark80",datumName:"Carthage 1934 Tunisia"},"hermannskogel":{towgs84:"653.0,-212.0,449.0",ellipse:"bessel",datumName:"Hermannskogel"},"ire65":{towgs84:"482.530,-130.596,564.557,-1.042,-0.214,-0.631,8.15",ellipse:"mod_airy",datumName:"Ireland 1965"},"nzgd49":{towgs84:"59.47,-5.04,187.44,0.47,-0.1,1.024,-4.5993",ellipse:"intl",datumName:"New Zealand Geodetic Datum 1949"},"OSGB36":{towgs84:"446.448,-125.157,542.060,0.1502,0.2470,0.8421,-20.4894",ellipse:"airy",datumName:"Airy 1830"}};
Proj4js.WGS84=new Proj4js.Proj("WGS84");
Proj4js.Datum["OSB36"]=Proj4js.Datum["OSGB36"];
Proj4js.Proj.sterea={dependsOn:"gauss",init:function(){
Proj4js.Proj["gauss"].init.apply(this);
if(!this.rc){
Proj4js.reportError("sterea:init:E_ERROR_0");
return;
}
this.sinc0=Math.sin(this.phic0);
this.cosc0=Math.cos(this.phic0);
this.R2=2*this.rc;
if(!this.title){
this.title="Oblique Stereographic Alternative";
}
},forward:function(p){
p.x=Proj4js.common.adjust_lon(p.x-this.long0);
Proj4js.Proj["gauss"].forward.apply(this,[p]);
sinc=Math.sin(p.y);
cosc=Math.cos(p.y);
cosl=Math.cos(p.x);
k=this.k0*this.R2/(1+this.sinc0*sinc+this.cosc0*cosc*cosl);
p.x=k*cosc*Math.sin(p.x);
p.y=k*(this.cosc0*sinc-this.sinc0*cosc*cosl);
p.x=this.a*p.x+this.x0;
p.y=this.a*p.y+this.y0;
return p;
},inverse:function(p){
var lon,lat;
p.x=(p.x-this.x0)/this.a;
p.y=(p.y-this.y0)/this.a;
p.x/=this.k0;
p.y/=this.k0;
if((rho=Math.sqrt(p.x*p.x+p.y*p.y))){
c=2*Math.atan2(rho,this.R2);
sinc=Math.sin(c);
cosc=Math.cos(c);
lat=Math.asin(cosc*this.sinc0+p.y*sinc*this.cosc0/rho);
lon=Math.atan2(p.x*sinc,rho*this.cosc0*cosc-p.y*this.sinc0*sinc);
}else{
lat=this.phic0;
lon=0;
}
p.x=lon;
p.y=lat;
Proj4js.Proj["gauss"].inverse.apply(this,[p]);
p.x=Proj4js.common.adjust_lon(p.x+this.long0);
return p;
}};
Proj4js.Proj.aea={init:function(){
if(Math.abs(this.lat1+this.lat2)<Proj4js.common.EPSLN){
Proj4js.reportError("aeaInitEqualLatitudes");
return;
}
this.temp=this.b/this.a;
this.es=1-Math.pow(this.temp,2);
this.e3=Math.sqrt(this.es);
this.sin_po=Math.sin(this.lat1);
this.cos_po=Math.cos(this.lat1);
this.t1=this.sin_po;
this.con=this.sin_po;
this.ms1=Proj4js.common.msfnz(this.e3,this.sin_po,this.cos_po);
this.qs1=Proj4js.common.qsfnz(this.e3,this.sin_po,this.cos_po);
this.sin_po=Math.sin(this.lat2);
this.cos_po=Math.cos(this.lat2);
this.t2=this.sin_po;
this.ms2=Proj4js.common.msfnz(this.e3,this.sin_po,this.cos_po);
this.qs2=Proj4js.common.qsfnz(this.e3,this.sin_po,this.cos_po);
this.sin_po=Math.sin(this.lat0);
this.cos_po=Math.cos(this.lat0);
this.t3=this.sin_po;
this.qs0=Proj4js.common.qsfnz(this.e3,this.sin_po,this.cos_po);
if(Math.abs(this.lat1-this.lat2)>Proj4js.common.EPSLN){
this.ns0=(this.ms1*this.ms1-this.ms2*this.ms2)/(this.qs2-this.qs1);
}else{
this.ns0=this.con;
}
this.c=this.ms1*this.ms1+this.ns0*this.qs1;
this.rh=this.a*Math.sqrt(this.c-this.ns0*this.qs0)/this.ns0;
},forward:function(p){
var lon=p.x;
var lat=p.y;
this.sin_phi=Math.sin(lat);
this.cos_phi=Math.cos(lat);
var qs=Proj4js.common.qsfnz(this.e3,this.sin_phi,this.cos_phi);
var rh1=this.a*Math.sqrt(this.c-this.ns0*qs)/this.ns0;
var _a0=this.ns0*Proj4js.common.adjust_lon(lon-this.long0);
var x=rh1*Math.sin(_a0)+this.x0;
var y=this.rh-rh1*Math.cos(_a0)+this.y0;
p.x=x;
p.y=y;
return p;
},inverse:function(p){
var rh1,qs,con,theta,lon,lat;
p.x-=this.x0;
p.y=this.rh-p.y+this.y0;
if(this.ns0>=0){
rh1=Math.sqrt(p.x*p.x+p.y*p.y);
con=1;
}else{
rh1=-Math.sqrt(p.x*p.x+p.y*p.y);
con=-1;
}
theta=0;
if(rh1!=0){
theta=Math.atan2(con*p.x,con*p.y);
}
con=rh1*this.ns0/this.a;
qs=(this.c-con*con)/this.ns0;
if(this.e3>=1e-10){
con=1-0.5*(1-this.es)*Math.log((1-this.e3)/(1+this.e3))/this.e3;
if(Math.abs(Math.abs(con)-Math.abs(qs))>1e-10){
lat=this.phi1z(this.e3,qs);
}else{
if(qs>=0){
lat=0.5*PI;
}else{
lat=-0.5*PI;
}
}
}else{
lat=this.phi1z(e3,qs);
}
lon=Proj4js.common.adjust_lon(theta/this.ns0+this.long0);
p.x=lon;
p.y=lat;
return p;
},phi1z:function(_a5,qs){
var con,com,dphi;
var phi=Proj4js.common.asinz(0.5*qs);
if(_a5<Proj4js.common.EPSLN){
return phi;
}
var _a9=_a5*_a5;
for(var i=1;i<=25;i++){
sinphi=Math.sin(phi);
cosphi=Math.cos(phi);
con=_a5*sinphi;
com=1-con*con;
dphi=0.5*com*com/cosphi*(qs/(1-_a9)-sinphi/com+0.5/_a5*Math.log((1-con)/(1+con)));
phi=phi+dphi;
if(Math.abs(dphi)<=1e-7){
return phi;
}
}
Proj4js.reportError("aea:phi1z:Convergence error");
return null;
}};
function phi4z(_ab,e0,e1,e2,e3,a,b,c,phi){
var _b4,sin2ph,tanph,ml,mlp,con1,con2,con3,dphi,i;
phi=a;
for(i=1;i<=15;i++){
_b4=Math.sin(phi);
tanphi=Math.tan(phi);
c=tanphi*Math.sqrt(1-_ab*_b4*_b4);
sin2ph=Math.sin(2*phi);
ml=e0*phi-e1*sin2ph+e2*Math.sin(4*phi)-e3*Math.sin(6*phi);
mlp=e0-2*e1*Math.cos(2*phi)+4*e2*Math.cos(4*phi)-6*e3*Math.cos(6*phi);
con1=2*ml+c*(ml*ml+b)-2*a*(c*ml+1);
con2=_ab*sin2ph*(ml*ml+b-2*a*ml)/(2*c);
con3=2*(a-ml)*(c*mlp-2/sin2ph)-2*mlp;
dphi=con1/(con2+con3);
phi+=dphi;
if(Math.abs(dphi)<=1e-10){
return (phi);
}
}
Proj4js.reportError("phi4z: No convergence");
return null;
}
function e4fn(x){
var con,com;
con=1+x;
com=1-x;
return (Math.sqrt((Math.pow(con,con))*(Math.pow(com,com))));
}
Proj4js.Proj.poly={init:function(){
var _b7;
if(this.lat0=0){
this.lat0=90;
}
this.temp=this.b/this.a;
this.es=1-Math.pow(this.temp,2);
this.e=Math.sqrt(this.es);
this.e0=Proj4js.common.e0fn(this.es);
this.e1=Proj4js.common.e1fn(this.es);
this.e2=Proj4js.common.e2fn(this.es);
this.e3=Proj4js.common.e3fn(this.es);
this.ml0=Proj4js.common.mlfn(this.e0,this.e1,this.e2,this.e3,this.lat0);
},forward:function(p){
var _b9,cosphi;
var al;
var c;
var con,ml;
var ms;
var x,y;
var lon=p.x;
var lat=p.y;
con=Proj4js.common.adjust_lon(lon-this.long0);
if(Math.abs(lat)<=1e-7){
x=this.x0+this.a*con;
y=this.y0-this.a*this.ml0;
}else{
_b9=Math.sin(lat);
cosphi=Math.cos(lat);
ml=Proj4js.common.mlfn(this.e0,this.e1,this.e2,this.e3,lat);
ms=Proj4js.common.msfnz(this.e,_b9,cosphi);
con=_b9;
x=this.x0+this.a*ms*Math.sin(con)/_b9;
y=this.y0+this.a*(ml-this.ml0+ms*(1-Math.cos(con))/_b9);
}
p.x=x;
p.y=y;
return p;
},inverse:function(p){
var _c2,cos_phi;
var al;
var b;
var c;
var con,ml;
var _c7;
var lon,lat;
p.x-=this.x0;
p.y-=this.y0;
al=this.ml0+p.y/this.a;
_c7=0;
if(Math.abs(al)<=1e-7){
lon=p.x/this.a+this.long0;
lat=0;
}else{
b=al*al+(p.x/this.a)*(p.x/this.a);
_c7=phi4z(this.es,this.e0,this.e1,this.e2,this.e3,this.al,b,c,lat);
if(_c7!=1){
return (_c7);
}
lon=Proj4js.common.adjust_lon((asinz(p.x*c/this.a)/Math.sin(lat))+this.long0);
}
p.x=lon;
p.y=lat;
return p;
}};
Proj4js.Proj.equi={init:function(){
if(!this.x0){
this.x0=0;
}
if(!this.y0){
this.y0=0;
}
if(!this.lat0){
this.lat0=0;
}
if(!this.long0){
this.long0=0;
}
},forward:function(p){
var lon=p.x;
var lat=p.y;
var _cc=Proj4js.common.adjust_lon(lon-this.long0);
var x=this.x0+this.a*_cc*Math.cos(this.lat0);
var y=this.y0+this.a*lat;
this.t1=x;
this.t2=Math.cos(this.lat0);
p.x=x;
p.y=y;
return p;
},inverse:function(p){
p.x-=this.x0;
p.y-=this.y0;
var lat=p.y/this.a;
if(Math.abs(lat)>Proj4js.common.HALF_PI){
Proj4js.reportError("equi:Inv:DataError");
}
var lon=Proj4js.common.adjust_lon(this.long0+p.x/(this.a*Math.cos(this.lat0)));
p.x=lon;
p.y=lat;
}};
Proj4js.Proj.merc={init:function(){
if(this.lat_ts){
if(this.sphere){
this.k0=Math.cos(this.lat_ts);
}else{
this.k0=Proj4js.common.msfnz(this.es,Math.sin(this.lat_ts),Math.cos(this.lat_ts));
}
}
},forward:function(p){
var lon=p.x;
var lat=p.y;
if(lat*Proj4js.common.R2D>90&&lat*Proj4js.common.R2D<-90&&lon*Proj4js.common.R2D>180&&lon*Proj4js.common.R2D<-180){
Proj4js.reportError("merc:forward: llInputOutOfRange: "+lon+" : "+lat);
return null;
}
var x,y;
if(Math.abs(Math.abs(lat)-Proj4js.common.HALF_PI)<=Proj4js.common.EPSLN){
Proj4js.reportError("merc:forward: ll2mAtPoles");
return null;
}else{
if(this.sphere){
x=this.x0+this.a*this.k0*Proj4js.common.adjust_lon(lon-this.long0);
y=this.y0+this.a*this.k0*Math.log(Math.tan(Proj4js.common.FORTPI+0.5*lat));
}else{
var _d6=Math.sin(lat);
var ts=Proj4js.common.tsfnz(this.e,lat,_d6);
x=this.x0+this.a*this.k0*Proj4js.common.adjust_lon(lon-this.long0);
y=this.y0-this.a*this.k0*Math.log(ts);
}
p.x=x;
p.y=y;
return p;
}
},inverse:function(p){
var x=p.x-this.x0;
var y=p.y-this.y0;
var lon,lat;
if(this.sphere){
lat=Proj4js.common.HALF_PI-2*Math.atan(Math.exp(-y/this.a*this.k0));
}else{
var ts=Math.exp(-y/(this.a*this.k0));
lat=Proj4js.common.phi2z(this.e,ts);
if(lat==-9999){
Proj4js.reportError("merc:inverse: lat = -9999");
return null;
}
}
lon=Proj4js.common.adjust_lon(this.long0+x/(this.a*this.k0));
p.x=lon;
p.y=lat;
return p;
}};
Proj4js.Proj.utm={dependsOn:"tmerc",init:function(){
if(!this.zone){
Proj4js.reportError("utm:init: zone must be specified for UTM");
return;
}
this.lat0=0;
this.long0=((6*Math.abs(this.zone))-183)*Proj4js.common.D2R;
this.x0=500000;
this.y0=this.utmSouth?10000000:0;
this.k0=0.9996;
Proj4js.Proj["tmerc"].init.apply(this);
this.forward=Proj4js.Proj["tmerc"].forward;
this.inverse=Proj4js.Proj["tmerc"].inverse;
}};
Proj4js.Proj.eqdc={init:function(){
if(!this.mode){
this.mode=0;
}
this.temp=this.b/this.a;
this.es=1-Math.pow(this.temp,2);
this.e=Math.sqrt(this.es);
this.e0=Proj4js.common.e0fn(this.es);
this.e1=Proj4js.common.e1fn(this.es);
this.e2=Proj4js.common.e2fn(this.es);
this.e3=Proj4js.common.e3fn(this.es);
this.sinphi=Math.sin(this.lat1);
this.cosphi=Math.cos(this.lat1);
this.ms1=Proj4js.common.msfnz(this.e,this.sinphi,this.cosphi);
this.ml1=Proj4js.common.mlfn(this.e0,this.e1,this.e2,this.e3,this.lat1);
if(this.mode!=0){
if(Math.abs(this.lat1+this.lat2)<Proj4js.common.EPSLN){
Proj4js.reportError("eqdc:Init:EqualLatitudes");
}
this.sinphi=Math.sin(this.lat2);
this.cosphi=Math.cos(this.lat2);
this.ms2=Proj4js.common.msfnz(this.e,this.sinphi,this.cosphi);
this.ml2=Proj4js.common.mlfn(this.e0,this.e1,this.e2,this.e3,this.lat2);
if(Math.abs(this.lat1-this.lat2)>=Proj4js.common.EPSLN){
this.ns=(this.ms1-this.ms2)/(this.ml2-this.ml1);
}else{
this.ns=this.sinphi;
}
}else{
this.ns=this.sinphi;
}
this.g=this.ml1+this.ms1/this.ns;
this.ml0=Proj4js.common.mlfn(this.e0,this.e1,this.e2,this.e3,this.lat0);
this.rh=this.a*(this.g-this.ml0);
},forward:function(p){
var lon=p.x;
var lat=p.y;
var ml=Proj4js.common.mlfn(this.e0,this.e1,this.e2,this.e3,lat);
var rh1=this.a*(this.g-ml);
var _e2=this.ns*Proj4js.common.adjust_lon(lon-this.long0);
var x=this.x0+rh1*Math.sin(_e2);
var y=this.y0+this.rh-rh1*Math.cos(_e2);
p.x=x;
p.y=y;
return p;
},inverse:function(p){
p.x-=this.x0;
p.y=this.rh-p.y+this.y0;
var con,rh1;
if(this.ns>=0){
var rh1=Math.sqrt(p.x*p.x+p.y*p.y);
var con=1;
}else{
rh1=-Math.sqrt(p.x*p.x+p.y*p.y);
con=-1;
}
var _e8=0;
if(rh1!=0){
_e8=Math.atan2(con*p.x,con*p.y);
}
var ml=this.g-rh1/this.a;
var lat=this.phi3z(this.ml,this.e0,this.e1,this.e2,this.e3);
var lon=Proj4js.common.adjust_lon(this.long0+_e8/this.ns);
p.x=lon;
p.y=lat;
return p;
},phi3z:function(ml,e0,e1,e2,e3){
var phi;
var _f2;
phi=ml;
for(var i=0;i<15;i++){
_f2=(ml+e1*Math.sin(2*phi)-e2*Math.sin(4*phi)+e3*Math.sin(6*phi))/e0-phi;
phi+=_f2;
if(Math.abs(_f2)<=1e-10){
return phi;
}
}
Proj4js.reportError("PHI3Z-CONV:Latitude failed to converge after 15 iterations");
return null;
}};
Proj4js.Proj.tmerc={init:function(){
this.e0=Proj4js.common.e0fn(this.es);
this.e1=Proj4js.common.e1fn(this.es);
this.e2=Proj4js.common.e2fn(this.es);
this.e3=Proj4js.common.e3fn(this.es);
this.ml0=this.a*Proj4js.common.mlfn(this.e0,this.e1,this.e2,this.e3,this.lat0);
},forward:function(p){
var lon=p.x;
var lat=p.y;
var _f7=Proj4js.common.adjust_lon(lon-this.long0);
var con;
var x,y;
var _fa=Math.sin(lat);
var _fb=Math.cos(lat);
if(this.sphere){
var b=_fb*Math.sin(_f7);
if((Math.abs(Math.abs(b)-1))<1e-10){
Proj4js.reportError("tmerc:forward: Point projects into infinity");
return (93);
}else{
x=0.5*this.a*this.k0*Math.log((1+b)/(1-b));
con=Math.acos(_fb*Math.cos(_f7)/Math.sqrt(1-b*b));
if(lat<0){
con=-con;
}
y=this.a*this.k0*(con-this.lat0);
}
}else{
var al=_fb*_f7;
var als=Math.pow(al,2);
var c=this.ep2*Math.pow(_fb,2);
var tq=Math.tan(lat);
var t=Math.pow(tq,2);
con=1-this.es*Math.pow(_fa,2);
var n=this.a/Math.sqrt(con);
var ml=this.a*Proj4js.common.mlfn(this.e0,this.e1,this.e2,this.e3,lat);
x=this.k0*n*al*(1+als/6*(1-t+c+als/20*(5-18*t+Math.pow(t,2)+72*c-58*this.ep2)))+this.x0;
y=this.k0*(ml-this.ml0+n*tq*(als*(0.5+als/24*(5-t+9*c+4*Math.pow(c,2)+als/30*(61-58*t+Math.pow(t,2)+600*c-330*this.ep2)))))+this.y0;
}
p.x=x;
p.y=y;
return p;
},inverse:function(p){
var con,phi;
var _106;
var i;
var _108=6;
var lat,lon;
if(this.sphere){
var f=Math.exp(p.x/(this.a*this.k0));
var g=0.5*(f-1/f);
var temp=this.lat0+p.y/(this.a*this.k0);
var h=Math.cos(temp);
con=Math.sqrt((1-h*h)/(1+g*g));
lat=Math.asinz(con);
if(temp<0){
lat=-lat;
}
if((g==0)&&(h==0)){
lon=this.long0;
}else{
lon=Proj4js.common.adjust_lon(Math.atan2(g,h)+this.long0);
}
}else{
var x=p.x-this.x0;
var y=p.y-this.y0;
con=(this.ml0+y/this.k0)/this.a;
phi=con;
for(i=0;;i++){
_106=((con+this.e1*Math.sin(2*phi)-this.e2*Math.sin(4*phi)+this.e3*Math.sin(6*phi))/this.e0)-phi;
phi+=_106;
if(Math.abs(_106)<=Proj4js.common.EPSLN){
break;
}
if(i>=_108){
Proj4js.reportError("tmerc:inverse: Latitude failed to converge");
return (95);
}
}
if(Math.abs(phi)<Proj4js.common.HALF_PI){
var _110=Math.sin(phi);
var _111=Math.cos(phi);
var _112=Math.tan(phi);
var c=this.ep2*Math.pow(_111,2);
var cs=Math.pow(c,2);
var t=Math.pow(_112,2);
var ts=Math.pow(t,2);
con=1-this.es*Math.pow(_110,2);
var n=this.a/Math.sqrt(con);
var r=n*(1-this.es)/con;
var d=x/(n*this.k0);
var ds=Math.pow(d,2);
lat=phi-(n*_112*ds/r)*(0.5-ds/24*(5+3*t+10*c-4*cs-9*this.ep2-ds/30*(61+90*t+298*c+45*ts-252*this.ep2-3*cs)));
lon=Proj4js.common.adjust_lon(this.long0+(d*(1-ds/6*(1+2*t+c-ds/20*(5-2*c+28*t-3*cs+8*this.ep2+24*ts)))/_111));
}else{
lat=Proj4js.common.HALF_PI*Proj4js.common.sign(y);
lon=this.long0;
}
}
p.x=lon;
p.y=lat;
return p;
}};
Proj4js.defs["GOOGLE"]="+proj=merc +a=6378137 +b=6378137 +lat_ts=0.0 +lon_0=0.0 +x_0=0.0 +y_0=0 +k=1.0 +units=m +nadgrids=@null +no_defs";
Proj4js.defs["EPSG:900913"]=Proj4js.defs["GOOGLE"];
Proj4js.Proj.ortho={init:function(def){
this.sin_p14=Math.sin(this.lat0);
this.cos_p14=Math.cos(this.lat0);
},forward:function(p){
var _11d,cosphi;
var dlon;
var _11f;
var ksp;
var g;
var lon=p.x;
var lat=p.y;
dlon=Proj4js.common.adjust_lon(lon-this.long0);
_11d=Math.sin(lat);
cosphi=Math.cos(lat);
_11f=Math.cos(dlon);
g=this.sin_p14*_11d+this.cos_p14*cosphi*_11f;
ksp=1;
if((g>0)||(Math.abs(g)<=Proj4js.common.EPSLN)){
var x=this.a*ksp*cosphi*Math.sin(dlon);
var y=this.y0+this.a*ksp*(this.cos_p14*_11d-this.sin_p14*cosphi*_11f);
}else{
Proj4js.reportError("orthoFwdPointError");
}
p.x=x;
p.y=y;
return p;
},inverse:function(p){
var rh;
var z;
var sinz,cosz;
var temp;
var con;
var lon,lat;
p.x-=this.x0;
p.y-=this.y0;
rh=Math.sqrt(p.x*p.x+p.y*p.y);
if(rh>this.a+1e-7){
Proj4js.reportError("orthoInvDataError");
}
z=Proj4js.common.asinz(rh/this.a);
sinz=Math.sin(z);
cosi=Math.cos(z);
lon=this.long0;
if(Math.abs(rh)<=Proj4js.common.EPSLN){
lat=this.lat0;
}
lat=Proj4js.common.asinz(cosz*this.sin_p14+(y*sinz*this.cos_p14)/rh);
con=Math.abs(lat0)-Proj4js.common.HALF_PI;
if(Math.abs(con)<=Proj4js.common.EPSLN){
if(this.lat0>=0){
lon=Proj4js.common.adjust_lon(this.long0+Math.atan2(p.x,-p.y));
}else{
lon=Proj4js.common.adjust_lon(this.long0-Math.atan2(-p.x,p.y));
}
}
con=cosz-this.sin_p14*Math.sin(lat);
if((Math.abs(con)>=Proj4js.common.EPSLN)||(Math.abs(x)>=Proj4js.common.EPSLN)){
lon=Proj4js.common.adjust_lon(this.long0+Math.atan2((p.x*sinz*this.cos_p14),(con*rh)));
}
p.x=lon;
p.y=lat;
return p;
}};
Proj4js.Proj.stere={ssfn_:function(phit,_12e,_12f){
_12e*=_12f;
return (Math.tan(0.5*(Proj4js.common.HALF_PI+phit))*Math.pow((1-_12e)/(1+_12e),0.5*_12f));
},TOL:1e-8,NITER:8,CONV:1e-10,S_POLE:0,N_POLE:1,OBLIQ:2,EQUIT:3,init:function(){
this.phits=this.lat_ts?this.lat_ts:Proj4js.common.HALF_PI;
var t=Math.abs(this.lat0);
if((Math.abs(t)-Proj4js.common.HALF_PI)<Proj4js.common.EPSLN){
this.mode=this.lat0<0?this.S_POLE:this.N_POLE;
}else{
this.mode=t>Proj4js.common.EPSLN?this.OBLIQ:this.EQUIT;
}
this.phits=Math.abs(this.phits);
if(this.es){
var X;
switch(this.mode){
case this.N_POLE:
case this.S_POLE:
if(Math.abs(this.phits-Proj4js.common.HALF_PI)<Proj4js.common.EPSLN){
this.akm1=2*this.k0/Math.sqrt(Math.pow(1+this.e,1+this.e)*Math.pow(1-this.e,1-this.e));
}else{
t=Math.sin(this.phits);
this.akm1=Math.cos(this.phits)/Proj4js.common.tsfnz(this.e,this.phits,t);
t*=this.e;
this.akm1/=Math.sqrt(1-t*t);
}
break;
case this.EQUIT:
this.akm1=2*this.k0;
break;
case this.OBLIQ:
t=Math.sin(this.lat0);
X=2*Math.atan(this.ssfn_(this.lat0,t,this.e))-Proj4js.common.HALF_PI;
t*=this.e;
this.akm1=2*this.k0*Math.cos(this.lat0)/Math.sqrt(1-t*t);
this.sinX1=Math.sin(X);
this.cosX1=Math.cos(X);
break;
}
}else{
switch(this.mode){
case this.OBLIQ:
this.sinph0=Math.sin(this.lat0);
this.cosph0=Math.cos(this.lat0);
case this.EQUIT:
this.akm1=2*this.k0;
break;
case this.S_POLE:
case this.N_POLE:
this.akm1=Math.abs(this.phits-Proj4js.common.HALF_PI)>=Proj4js.common.EPSLN?Math.cos(this.phits)/Math.tan(Proj4js.common.FORTPI-0.5*this.phits):2*this.k0;
break;
}
}
},forward:function(p){
var lon=p.x;
var lat=p.y;
var x,y;
if(this.sphere){
var _136,cosphi,coslam,sinlam;
_136=Math.sin(lat);
cosphi=Math.cos(lat);
coslam=Math.cos(lon);
sinlam=Math.sin(lon);
switch(this.mode){
case this.EQUIT:
y=1+cosphi*coslam;
if(y<=Proj4js.common.EPSLN){
F_ERROR;
}
y=this.akm1/y;
x=y*cosphi*sinlam;
y*=_136;
break;
case this.OBLIQ:
y=1+this.sinph0*_136+this.cosph0*cosphi*coslam;
if(y<=Proj4js.common.EPSLN){
F_ERROR;
}
y=this.akm1/y;
x=y*cosphi*sinlam;
y*=this.cosph0*_136-this.sinph0*cosphi*coslam;
break;
case this.N_POLE:
coslam=-coslam;
lat=-lat;
case this.S_POLE:
if(Math.abs(lat-Proj4js.common.HALF_PI)<this.TOL){
F_ERROR;
}
y=this.akm1*Math.tan(Proj4js.common.FORTPI+0.5*lat);
x=sinlam*y;
y*=coslam;
break;
}
}else{
coslam=Math.cos(lon);
sinlam=Math.sin(lon);
_136=Math.sin(lat);
if(this.mode==this.OBLIQ||this.mode==this.EQUIT){
X=2*Math.atan(this.ssfn_(lat,_136,this.e));
sinX=Math.sin(X-Proj4js.common.HALF_PI);
cosX=Math.cos(X);
}
switch(this.mode){
case this.OBLIQ:
A=this.akm1/(this.cosX1*(1+this.sinX1*sinX+this.cosX1*cosX*coslam));
y=A*(this.cosX1*sinX-this.sinX1*cosX*coslam);
x=A*cosX;
break;
case this.EQUIT:
A=2*this.akm1/(1+cosX*coslam);
y=A*sinX;
x=A*cosX;
break;
case this.S_POLE:
lat=-lat;
coslam=-coslam;
_136=-_136;
case this.N_POLE:
x=this.akm1*Proj4js.common.tsfnz(this.e,lat,_136);
y=-x*coslam;
break;
}
x=x*sinlam;
}
p.x=x*this.a+this.x0;
p.y=y*this.a+this.y0;
return p;
},inverse:function(p){
var x=(p.x-this.x0)/this.a;
var y=(p.y-this.y0)/this.a;
var lon,lat;
var _13b,sinphi,tp=0,phi_l=0,rho,halfe=0,pi2=0;
var i;
if(this.sphere){
var c,rh,sinc,cosc;
rh=Math.sqrt(x*x+y*y);
c=2*Math.atan(rh/this.akm1);
sinc=Math.sin(c);
cosc=Math.cos(c);
lon=0;
switch(this.mode){
case this.EQUIT:
if(Math.abs(rh)<=Proj4js.common.EPSLN){
lat=0;
}else{
lat=Math.asin(y*sinc/rh);
}
if(cosc!=0||x!=0){
lon=Math.atan2(x*sinc,cosc*rh);
}
break;
case this.OBLIQ:
if(Math.abs(rh)<=Proj4js.common.EPSLN){
lat=this.phi0;
}else{
lat=Math.asin(cosc*sinph0+y*sinc*cosph0/rh);
}
c=cosc-sinph0*Math.sin(lat);
if(c!=0||x!=0){
lon=Math.atan2(x*sinc*cosph0,c*rh);
}
break;
case this.N_POLE:
y=-y;
case this.S_POLE:
if(Math.abs(rh)<=Proj4js.common.EPSLN){
lat=this.phi0;
}else{
lat=Math.asin(this.mode==this.S_POLE?-cosc:cosc);
}
lon=(x==0&&y==0)?0:Math.atan2(x,y);
break;
}
}else{
rho=Math.sqrt(x*x+y*y);
switch(this.mode){
case this.OBLIQ:
case this.EQUIT:
tp=2*Math.atan2(rho*this.cosX1,this.akm1);
_13b=Math.cos(tp);
sinphi=Math.sin(tp);
if(rho==0){
phi_l=Math.asin(_13b*this.sinX1);
}else{
phi_l=Math.asin(_13b*this.sinX1+(y*sinphi*this.cosX1/rho));
}
tp=Math.tan(0.5*(Proj4js.common.HALF_PI+phi_l));
x*=sinphi;
y=rho*this.cosX1*_13b-y*this.sinX1*sinphi;
pi2=Proj4js.common.HALF_PI;
halfe=0.5*this.e;
break;
case this.N_POLE:
y=-y;
case this.S_POLE:
tp=-rho/this.akm1;
phi_l=Proj4js.common.HALF_PI-2*Math.atan(tp);
pi2=-Proj4js.common.HALF_PI;
halfe=-0.5*this.e;
break;
}
for(i=this.NITER;i--;phi_l=lat){
sinphi=this.e*Math.sin(phi_l);
lat=2*Math.atan(tp*Math.pow((1+sinphi)/(1-sinphi),halfe))-pi2;
if(Math.abs(phi_l-lat)<this.CONV){
if(this.mode==this.S_POLE){
lat=-lat;
}
lon=(x==0&&y==0)?0:Math.atan2(x,y);
p.x=lon;
p.y=lat;
return p;
}
}
}
}};
Proj4js.Proj.mill={init:function(){
},forward:function(p){
var lon=p.x;
var lat=p.y;
dlon=Proj4js.common.adjust_lon(lon-this.long0);
var x=this.x0+this.R*dlon;
var y=this.y0+this.R*Math.log(Math.tan((Proj4js.common.PI/4)+(lat/2.5)))*1.25;
p.x=x;
p.y=y;
return p;
},inverse:function(p){
p.x-=this.x0;
p.y-=this.y0;
var lon=Proj4js.common.adjust_lon(this.long0+p.x/this.R);
var lat=2.5*(Math.atan(Math.exp(p.y/this.R/1.25))-Proj4js.common.PI/4);
p.x=lon;
p.y=lat;
return p;
}};
Proj4js.Proj.sinu={init:function(){
this.R=6370997;
},forward:function(p){
var x,y,delta_lon;
var lon=p.x;
var lat=p.y;
delta_lon=Proj4js.common.adjust_lon(lon-this.long0);
x=this.R*delta_lon*Math.cos(lat)+this.x0;
y=this.R*lat+this.y0;
p.x=x;
p.y=y;
return p;
},inverse:function(p){
var lat,temp,lon;
p.x-=this.x0;
p.y-=this.y0;
lat=p.y/this.R;
if(Math.abs(lat)>Proj4js.common.HALF_PI){
Proj4js.reportError("sinu:Inv:DataError");
}
temp=Math.abs(lat)-Proj4js.common.HALF_PI;
if(Math.abs(temp)>Proj4js.common.EPSLN){
temp=this.long0+p.x/(this.R*Math.cos(lat));
lon=Proj4js.common.adjust_lon(temp);
}else{
lon=this.long0;
}
p.x=lon;
p.y=lat;
return p;
}};
var GEOCENT_LAT_ERROR=1;
var COS_67P5=0.3826834323650898;
var AD_C=1.0026;
function cs_geodetic_to_geocentric(cs,p){
var _14e=p.x;
var _14f=p.y;
var _150=p.z;
var X;
var Y;
var Z;
var _154=0;
var Rn;
var _156;
var _157;
var _158;
if(_14f<-HALF_PI&&_14f>-1.001*HALF_PI){
_14f=-HALF_PI;
}else{
if(_14f>HALF_PI&&_14f<1.001*HALF_PI){
_14f=HALF_PI;
}else{
if((_14f<-HALF_PI)||(_14f>HALF_PI)){
_154|=GEOCENT_LAT_ERROR;
}
}
}
if(!_154){
if(_14e>PI){
_14e-=(2*PI);
}
_156=Math.sin(_14f);
_158=Math.cos(_14f);
_157=_156*_156;
Rn=cs.a/(Math.sqrt(1-cs.es*_157));
X=(Rn+_150)*_158*Math.cos(_14e);
Y=(Rn+_150)*_158*Math.sin(_14e);
Z=((Rn*(1-cs.es))+_150)*_156;
}
p.x=X;
p.y=Y;
p.z=Z;
return _154;
}
function cs_geocentric_to_geodetic(cs,p){
var X=p.x;
var Y=p.y;
var Z=p.z;
var _15e;
var _15f;
var _160;
var W;
var W2;
var T0;
var T1;
var S0;
var S1;
var _167;
var _168;
var _169;
var _16a;
var _16b;
var Rn;
var Sum;
var _16e;
X=parseFloat(X);
Y=parseFloat(Y);
Z=parseFloat(Z);
_16e=false;
if(X!=0){
_15e=Math.atan2(Y,X);
}else{
if(Y>0){
_15e=HALF_PI;
}else{
if(Y<0){
_15e=-HALF_PI;
}else{
_16e=true;
_15e=0;
if(Z>0){
_15f=HALF_PI;
}else{
if(Z<0){
_15f=-HALF_PI;
}else{
_15f=HALF_PI;
_160=-cs.b;
return;
}
}
}
}
}
W2=X*X+Y*Y;
W=Math.sqrt(W2);
T0=Z*AD_C;
S0=Math.sqrt(T0*T0+W2);
_167=T0/S0;
_169=W/S0;
_168=_167*_167*_167;
T1=Z+cs.b*cs.ep2*_168;
Sum=W-cs.a*cs.es*_169*_169*_169;
S1=Math.sqrt(T1*T1+Sum*Sum);
_16a=T1/S1;
_16b=Sum/S1;
Rn=cs.a/Math.sqrt(1-cs.es*_16a*_16a);
if(_16b>=COS_67P5){
_160=W/_16b-Rn;
}else{
if(_16b<=-COS_67P5){
_160=W/-_16b-Rn;
}else{
_160=Z/_16a+Rn*(cs.es-1);
}
}
if(_16e==false){
_15f=Math.atan(_16a/_16b);
}
p.x=_15e;
p.y=_15f;
p.z=_160;
return 0;
}
function cs_geocentric_to_wgs84(defn,p){
if(defn.datum_type==PJD_3PARAM){
p.x+=defn.datum_params[0];
p.y+=defn.datum_params[1];
p.z+=defn.datum_params[2];
}else{
var _171=defn.datum_params[0];
var _172=defn.datum_params[1];
var _173=defn.datum_params[2];
var _174=defn.datum_params[3];
var _175=defn.datum_params[4];
var _176=defn.datum_params[5];
var M_BF=defn.datum_params[6];
var _178=M_BF*(p.x-_176*p.y+_175*p.z)+_171;
var _179=M_BF*(_176*p.x+p.y-_174*p.z)+_172;
var _17a=M_BF*(-_175*p.x+_174*p.y+p.z)+_173;
p.x=_178;
p.y=_179;
p.z=_17a;
}
}
function cs_geocentric_from_wgs84(defn,p){
if(defn.datum_type==PJD_3PARAM){
p.x-=defn.datum_params[0];
p.y-=defn.datum_params[1];
p.z-=defn.datum_params[2];
}else{
var _17d=defn.datum_params[0];
var _17e=defn.datum_params[1];
var _17f=defn.datum_params[2];
var _180=defn.datum_params[3];
var _181=defn.datum_params[4];
var _182=defn.datum_params[5];
var M_BF=defn.datum_params[6];
var _184=(p.x-_17d)/M_BF;
var _185=(p.y-_17e)/M_BF;
var _186=(p.z-_17f)/M_BF;
p.x=_184+_182*_185-_181*_186;
p.y=-_182*_184+_185+_180*_186;
p.z=_181*_184-_180*_185+_186;
}
}
Proj4js.Proj.vandg={init:function(){
this.R=6370997;
},forward:function(p){
var lon=p.x;
var lat=p.y;
var dlon=Proj4js.common.adjust_lon(lon-this.long0);
var x,y;
if(Math.abs(lat)<=Proj4js.common.EPSLN){
x=this.x0+this.R*dlon;
y=this.y0;
}
var _18c=Proj4js.common.asinz(2*Math.abs(lat/Proj4js.common.PI));
if((Math.abs(dlon)<=Proj4js.common.EPSLN)||(Math.abs(Math.abs(lat)-Proj4js.common.HALF_PI)<=Proj4js.common.EPSLN)){
x=this.x0;
if(lat>=0){
y=this.y0+Proj4js.common.PI*this.R*Math.tan(0.5*_18c);
}else{
y=this.y0+Proj4js.common.PI*this.R*-Math.tan(0.5*_18c);
}
}
var al=0.5*Math.abs((Proj4js.common.PI/dlon)-(dlon/Proj4js.common.PI));
var asq=al*al;
var _18f=Math.sin(_18c);
var _190=Math.cos(_18c);
var g=_190/(_18f+_190-1);
var gsq=g*g;
var m=g*(2/_18f-1);
var msq=m*m;
var con=Proj4js.common.PI*this.R*(al*(g-msq)+Math.sqrt(asq*(g-msq)*(g-msq)-(msq+asq)*(gsq-msq)))/(msq+asq);
if(dlon<0){
con=-con;
}
x=this.x0+con;
con=Math.abs(con/(Proj4js.common.PI*this.R));
if(lat>=0){
y=this.y0+Proj4js.common.PI*this.R*Math.sqrt(1-con*con-2*al*con);
}else{
y=this.y0-Proj4js.common.PI*this.R*Math.sqrt(1-con*con-2*al*con);
}
p.x=x;
p.y=y;
return p;
},inverse:function(p){
var dlon;
var xx,yy,xys,c1,c2,c3;
var al,asq;
var a1;
var m1;
var con;
var th1;
var d;
p.x-=this.x0;
p.y-=this.y0;
con=Proj4js.common.PI*this.R;
xx=p.x/con;
yy=p.y/con;
xys=xx*xx+yy*yy;
c1=-Math.abs(yy)*(1+xys);
c2=c1-2*yy*yy+xx*xx;
c3=-2*c1+1+2*yy*yy+xys*xys;
d=yy*yy/c3+(2*c2*c2*c2/c3/c3/c3-9*c1*c2/c3/c3)/27;
a1=(c1-c2*c2/3/c3)/c3;
m1=2*Math.sqrt(-a1/3);
con=((3*d)/a1)/m1;
if(Math.abs(con)>1){
if(con>=0){
con=1;
}else{
con=-1;
}
}
th1=Math.acos(con)/3;
if(p.y>=0){
lat=(-m1*Math.cos(th1+Proj4js.common.PI/3)-c2/3/c3)*Proj4js.common.PI;
}else{
lat=-(-m1*Math.cos(th1+PI/3)-c2/3/c3)*Proj4js.common.PI;
}
if(Math.abs(xx)<Proj4js.common.EPSLN){
lon=this.long0;
}
lon=Proj4js.common.adjust_lon(this.long0+Proj4js.common.PI*(xys-1+Math.sqrt(1+2*(xx*xx-yy*yy)+xys*xys))/2/xx);
p.x=lon;
p.y=lat;
return p;
}};
Proj4js.Proj.gauss={init:function(){
sphi=Math.sin(this.lat0);
cphi=Math.cos(this.lat0);
cphi*=cphi;
this.rc=Math.sqrt(1-this.es)/(1-this.es*sphi*sphi);
this.C=Math.sqrt(1+this.es*cphi*cphi/(1-this.es));
this.phic0=Math.asin(sphi/this.C);
this.ratexp=0.5*this.C*this.e;
this.K=Math.tan(0.5*this.phic0+Proj4js.common.FORTPI)/(Math.pow(Math.tan(0.5*this.lat0+Proj4js.common.FORTPI),this.C)*Proj4js.common.srat(this.e*sphi,this.ratexp));
},forward:function(p){
var lon=p.x;
var lat=p.y;
p.y=2*Math.atan(this.K*Math.pow(Math.tan(0.5*lat+Proj4js.common.FORTPI),this.C)*Proj4js.common.srat(this.e*Math.sin(lat),this.ratexp))-Proj4js.common.HALF_PI;
p.x=this.C*lon;
return p;
},inverse:function(p){
var _1a3=1e-14;
var lon=p.x/this.C;
var lat=p.y;
num=Math.pow(Math.tan(0.5*lat+Proj4js.common.FORTPI)/this.K,1/this.C);
for(var i=Proj4js.common.MAX_ITER;i>0;--i){
lat=2*Math.atan(num*Proj4js.common.srat(this.e*Math.sin(p.y),-0.5*this.e))-Proj4js.common.HALF_PI;
if(Math.abs(lat-p.y)<_1a3){
break;
}
p.y=lat;
}
if(!i){
Proj4js.reportError("gauss:inverse:convergence failed");
return null;
}
p.x=lon;
p.y=lat;
return p;
}};
Proj4js.Proj.omerc={init:function(){
if(!this.mode){
this.mode=0;
}
if(!this.lon1){
this.lon1=0;
this.mode=1;
}
if(!this.lon2){
this.lon2=0;
}
if(!this.lat2){
this.lat2=0;
}
var temp=this.b/this.a;
var es=1-Math.pow(temp,2);
var e=Math.sqrt(es);
this.sin_p20=Math.sin(this.lat0);
this.cos_p20=Math.cos(this.lat0);
this.con=1-this.es*this.sin_p20*this.sin_p20;
this.com=Math.sqrt(1-es);
this.bl=Math.sqrt(1+this.es*Math.pow(this.cos_p20,4)/(1-es));
this.al=this.a*this.bl*this.k0*this.com/this.con;
if(Math.abs(this.lat0)<Proj4js.common.EPSLN){
this.ts=1;
this.d=1;
this.el=1;
}else{
this.ts=Proj4js.common.tsfnz(this.e,this.lat0,this.sin_p20);
this.con=Math.sqrt(this.con);
this.d=this.bl*this.com/(this.cos_p20*this.con);
if((this.d*this.d-1)>0){
if(this.lat0>=0){
this.f=this.d+Math.sqrt(this.d*this.d-1);
}else{
this.f=this.d-Math.sqrt(this.d*this.d-1);
}
}else{
this.f=this.d;
}
this.el=this.f*Math.pow(this.ts,this.bl);
}
if(this.mode!=0){
this.g=0.5*(this.f-1/this.f);
this.gama=Proj4js.common.asinz(Math.sin(this.alpha)/this.d);
this.longc=this.longc-Proj4js.common.asinz(this.g*Math.tan(this.gama))/this.bl;
this.con=Math.abs(this.lat0);
if((this.con>Proj4js.common.EPSLN)&&(Math.abs(this.con-Proj4js.common.HALF_PI)>Proj4js.common.EPSLN)){
this.singam=Math.sin(this.gama);
this.cosgam=Math.cos(this.gama);
this.sinaz=Math.sin(this.alpha);
this.cosaz=Math.cos(this.alpha);
if(this.lat0>=0){
this.u=(this.al/this.bl)*Math.atan(Math.sqrt(this.d*this.d-1)/this.cosaz);
}else{
this.u=-(this.al/this.bl)*Math.atan(Math.sqrt(this.d*this.d-1)/this.cosaz);
}
}else{
Proj4js.reportError("omerc:Init:DataError");
}
}else{
this.sinphi=Math.sin(this.at1);
this.ts1=Proj4js.common.tsfnz(this.e,this.lat1,this.sinphi);
this.sinphi=Math.sin(this.lat2);
this.ts2=Proj4js.common.tsfnz(this.e,this.lat2,this.sinphi);
this.h=Math.pow(this.ts1,this.bl);
this.l=Math.pow(this.ts2,this.bl);
this.f=this.el/this.h;
this.g=0.5*(this.f-1/this.f);
this.j=(this.el*this.el-this.l*this.h)/(this.el*this.el+this.l*this.h);
this.p=(this.l-this.h)/(this.l+this.h);
this.dlon=this.lon1-this.lon2;
if(this.dlon<-Proj4js.common.PI){
this.lon2=this.lon2-2*Proj4js.common.PI;
}
if(this.dlon>Proj4js.common.PI){
this.lon2=this.lon2+2*Proj4js.common.PI;
}
this.dlon=this.lon1-this.lon2;
this.longc=0.5*(this.lon1+this.lon2)-Math.atan(this.j*Math.tan(0.5*this.bl*this.dlon)/this.p)/this.bl;
this.dlon=Proj4js.common.adjust_lon(this.lon1-this.longc);
this.gama=Math.atan(Math.sin(this.bl*this.dlon)/this.g);
this.alpha=Proj4js.common.asinz(this.d*Math.sin(this.gama));
if(Math.abs(this.lat1-this.lat2)<=Proj4js.common.EPSLN){
Proj4js.reportError("omercInitDataError");
}else{
this.con=Math.abs(this.lat1);
}
if((this.con<=Proj4js.common.EPSLN)||(Math.abs(this.con-HALF_PI)<=Proj4js.common.EPSLN)){
Proj4js.reportError("omercInitDataError");
}else{
if(Math.abs(Math.abs(this.lat0)-Proj4js.common.HALF_PI)<=Proj4js.common.EPSLN){
Proj4js.reportError("omercInitDataError");
}
}
this.singam=Math.sin(this.gam);
this.cosgam=Math.cos(this.gam);
this.sinaz=Math.sin(this.alpha);
this.cosaz=Math.cos(this.alpha);
if(this.lat0>=0){
this.u=(this.al/this.bl)*Math.atan(Math.sqrt(this.d*this.d-1)/this.cosaz);
}else{
this.u=-(this.al/this.bl)*Math.atan(Math.sqrt(this.d*this.d-1)/this.cosaz);
}
}
},forward:function(p){
var _1ab;
var _1ac,cos_phi;
var b;
var c,t,tq;
var con,n,ml;
var q,us,vl;
var ul,vs;
var s;
var dlon;
var ts1;
var lon=p.x;
var lat=p.y;
_1ac=Math.sin(lat);
dlon=Proj4js.common.adjust_lon(lon-this.longc);
vl=Math.sin(this.bl*dlon);
if(Math.abs(Math.abs(lat)-Proj4js.common.HALF_PI)>Proj4js.common.EPSLN){
ts1=Proj4js.common.tsfnz(this.e,lat,_1ac);
q=this.el/(Math.pow(ts1,this.bl));
s=0.5*(q-1/q);
t=0.5*(q+1/q);
ul=(s*this.singam-vl*this.cosgam)/t;
con=Math.cos(this.bl*dlon);
if(Math.abs(con)<1e-7){
us=this.al*this.bl*dlon;
}else{
us=this.al*Math.atan((s*this.cosgam+vl*this.singam)/con)/this.bl;
if(con<0){
us=us+Proj4js.common.PI*this.al/this.bl;
}
}
}else{
if(lat>=0){
ul=this.singam;
}else{
ul=-this.singam;
}
us=this.al*lat/this.bl;
}
if(Math.abs(Math.abs(ul)-1)<=Proj4js.common.EPSLN){
Proj4js.reportError("omercFwdInfinity");
}
vs=0.5*this.al*Math.log((1-ul)/(1+ul))/this.bl;
us=us-this.u;
var x=this.x0+vs*this.cosaz+us*this.sinaz;
var y=this.y0+us*this.cosaz-vs*this.sinaz;
p.x=x;
p.y=y;
return p;
},inverse:function(p){
var _1ba;
var _1bb;
var _1bc;
var _1bd,cos_phi;
var b;
var c,t,tq;
var con,n,ml;
var vs,us,q,s,ts1;
var vl,ul,bs;
var dlon;
var flag;
p.x-=this.x0;
p.y-=this.y0;
flag=0;
vs=p.x*this.cosaz-p.y*this.sinaz;
us=p.y*this.cosaz+p.x*this.sinaz;
us=us+this.u;
q=Math.exp(-this.bl*vs/this.al);
s=0.5*(q-1/q);
t=0.5*(q+1/q);
vl=Math.sin(this.bl*us/this.al);
ul=(vl*this.cosgam+s*this.singam)/t;
if(Math.abs(Math.abs(ul)-1)<=Proj4js.common.EPSLN){
lon=this.longc;
if(ul>=0){
lat=Proj4js.common.HALF_PI;
}else{
lat=-Proj4js.common.HALF_PI;
}
}else{
con=1/this.bl;
ts1=Math.pow((this.el/Math.sqrt((1+ul)/(1-ul))),con);
lat=Proj4js.common.phi2z(this.e,ts1);
_1bb=this.longc-Math.atan2((s*this.cosgam-vl*this.singam),con)/this.bl;
lon=Proj4js.common.adjust_lon(_1bb);
}
p.x=lon;
p.y=lat;
return p;
}};
Proj4js.Proj.lcc={init:function(){
if(!this.lat2){
this.lat2=this.lat0;
}
if(!this.k0){
this.k0=1;
}
if(Math.abs(this.lat1+this.lat2)<Proj4js.common.EPSLN){
Proj4js.reportError("lcc:init: Equal Latitudes");
return;
}
var temp=this.b/this.a;
this.e=Math.sqrt(1-temp*temp);
var sin1=Math.sin(this.lat1);
var cos1=Math.cos(this.lat1);
var ms1=Proj4js.common.msfnz(this.e,sin1,cos1);
var ts1=Proj4js.common.tsfnz(this.e,this.lat1,sin1);
var sin2=Math.sin(this.lat2);
var cos2=Math.cos(this.lat2);
var ms2=Proj4js.common.msfnz(this.e,sin2,cos2);
var ts2=Proj4js.common.tsfnz(this.e,this.lat2,sin2);
var ts0=Proj4js.common.tsfnz(this.e,this.lat0,Math.sin(this.lat0));
if(Math.abs(this.lat1-this.lat2)>Proj4js.common.EPSLN){
this.ns=Math.log(ms1/ms2)/Math.log(ts1/ts2);
}else{
this.ns=sin1;
}
this.f0=ms1/(this.ns*Math.pow(ts1,this.ns));
this.rh=this.a*this.f0*Math.pow(ts0,this.ns);
if(!this.title){
this.title="Lambert Conformal Conic";
}
},forward:function(p){
var lon=p.x;
var lat=p.y;
if(lat<=90&&lat>=-90&&lon<=180&&lon>=-180){
}else{
Proj4js.reportError("lcc:forward: llInputOutOfRange: "+lon+" : "+lat);
return null;
}
var con=Math.abs(Math.abs(lat)-Proj4js.common.HALF_PI);
var ts;
if(con>Proj4js.common.EPSLN){
ts=Proj4js.common.tsfnz(this.e,lat,Math.sin(lat));
rh1=this.a*this.f0*Math.pow(ts,this.ns);
}else{
con=lat*this.ns;
if(con<=0){
Proj4js.reportError("lcc:forward: No Projection");
return null;
}
rh1=0;
}
var _1d4=this.ns*Proj4js.common.adjust_lon(lon-this.long0);
p.x=this.k0*(rh1*Math.sin(_1d4))+this.x0;
p.y=this.k0*(this.rh-rh1*Math.cos(_1d4))+this.y0;
return p;
},inverse:function(p){
var rh1,con,ts;
var lat,lon;
x=(p.x-this.x0)/this.k0;
y=(this.rh-(p.y-this.y0)/this.k0);
if(this.ns>0){
rh1=Math.sqrt(x*x+y*y);
con=1;
}else{
rh1=-Math.sqrt(x*x+y*y);
con=-1;
}
var _1d8=0;
if(rh1!=0){
_1d8=Math.atan2((con*x),(con*y));
}
if((rh1!=0)||(this.ns>0)){
con=1/this.ns;
ts=Math.pow((rh1/(this.a*this.f0)),con);
lat=Proj4js.common.phi2z(this.e,ts);
if(lat==-9999){
return null;
}
}else{
lat=-Proj4js.common.HALF_PI;
}
lon=Proj4js.common.adjust_lon(_1d8/this.ns+this.long0);
p.x=lon;
p.y=lat;
return p;
}};
Proj4js.Proj.laea={init:function(){
this.sin_lat_o=Math.sin(this.lat0);
this.cos_lat_o=Math.cos(this.lat0);
},forward:function(p){
var lon=p.x;
var lat=p.y;
var _1dc=Proj4js.common.adjust_lon(lon-this.long0);
var _1dd=Math.sin(lat);
var _1de=Math.cos(lat);
var _1df=Math.sin(_1dc);
var _1e0=Math.cos(_1dc);
var g=this.sin_lat_o*_1dd+this.cos_lat_o*_1de*_1e0;
if(g==-1){
Proj4js.reportError("laea:fwd:Point projects to a circle of radius "+2*R);
return null;
}
var ksp=this.a*Math.sqrt(2/(1+g));
var x=ksp*_1de*_1df+this.x0;
var y=ksp*(this.cos_lat_o*_1dd-this.sin_lat_o*_1de*_1e0)+this.x0;
p.x=x;
p.y=y;
return p;
},inverse:function(p){
p.x-=this.x0;
p.y-=this.y0;
var Rh=Math.sqrt(p.x*p.x+p.y*p.y);
var temp=Rh/(2*this.a);
if(temp>1){
Proj4js.reportError("laea:Inv:DataError");
return null;
}
var z=2*Proj4js.common.asinz(temp);
var _1e9=Math.sin(z);
var _1ea=Math.cos(z);
var lon=this.long0;
if(Math.abs(Rh)>Proj4js.common.EPSLN){
var lat=Proj4js.common.asinz(this.sin_lat_o*_1ea+this.cos_lat_o*_1e9*p.y/Rh);
var temp=Math.abs(this.lat0)-Proj4js.common.HALF_PI;
if(Math.abs(temp)>Proj4js.common.EPSLN){
temp=_1ea-this.sin_lat_o*Math.sin(lat);
if(temp!=0){
lon=Proj4js.common.adjust_lon(this.long0+Math.atan2(p.x*_1e9*this.cos_lat_o,temp*Rh));
}
}else{
if(this.lat0<0){
lon=Proj4js.common.adjust_lon(this.long0-Math.atan2(-p.x,p.y));
}else{
lon=Proj4js.common.adjust_lon(this.long0+Math.atan2(p.x,-p.y));
}
}
}else{
lat=this.lat0;
}
p.x=lon;
p.y=lat;
return p;
}};
Proj4js.Proj.aeqd={init:function(){
this.sin_p12=Math.sin(this.lat0);
this.cos_p12=Math.cos(this.lat0);
},forward:function(p){
var lon=p.x;
var lat=p.y;
var ksp;
var _1f1=Math.sin(p.y);
var _1f2=Math.cos(p.y);
var dlon=Proj4js.common.adjust_lon(lon-this.long0);
var _1f4=Math.cos(dlon);
var g=this.sin_p12*_1f1+this.cos_p12*_1f2*_1f4;
if(Math.abs(Math.abs(g)-1)<Proj4js.common.EPSLN){
ksp=1;
if(g<0){
Proj4js.reportError("aeqd:Fwd:PointError");
return;
}
}else{
var z=Math.acos(g);
ksp=z/Math.sin(z);
}
p.x=this.x0+this.a*ksp*_1f2*Math.sin(dlon);
p.y=this.y0+this.a*ksp*(this.cos_p12*_1f1-this.sin_p12*_1f2*_1f4);
return p;
},inverse:function(p){
p.x-=this.x0;
p.y-=this.y0;
var rh=Math.sqrt(p.x*p.x+p.y*p.y);
if(rh>(2*Proj4js.common.HALF_PI*this.a)){
Proj4js.reportError("aeqdInvDataError");
return;
}
var z=rh/this.a;
var sinz=Math.sin(z);
var cosz=Math.cos(z);
var lon=this.long0;
var lat;
if(Math.abs(rh)<=Proj4js.common.EPSLN){
lat=this.lat0;
}else{
lat=Proj4js.common.asinz(cosz*this.sin_p12+(p.y*sinz*this.cos_p12)/rh);
var con=Math.abs(this.lat0)-Proj4js.common.HALF_PI;
if(Math.abs(con)<=Proj4js.common.EPSLN){
if(lat0>=0){
lon=Proj4js.common.adjust_lon(this.long0+Math.atan2(p.x,-p.y));
}else{
lon=Proj4js.common.adjust_lon(this.long0-Math.atan2(-p.x,p.y));
}
}else{
con=cosz-this.sin_p12*Math.sin(lat);
if((Math.abs(con)<Proj4js.common.EPSLN)&&(Math.abs(p.x)<Proj4js.common.EPSLN)){
}else{
var temp=Math.atan2((p.x*sinz*this.cos_p12),(con*rh));
lon=Proj4js.common.adjust_lon(this.long0+Math.atan2((p.x*sinz*this.cos_p12),(con*rh)));
}
}
}
p.x=lon;
p.y=lat;
return p;
}};
Proj4js.Proj.moll={init:function(){
},forward:function(p){
var lon=p.x;
var lat=p.y;
var _203=Proj4js.common.adjust_lon(lon-this.long0);
var _204=lat;
var con=Proj4js.common.PI*Math.sin(lat);
for(var i=0;;i++){
var _207=-(_204+Math.sin(_204)-con)/(1+Math.cos(_204));
_204+=_207;
if(Math.abs(_207)<Proj4js.common.EPSLN){
break;
}
if(i>=50){
Proj4js.reportError("moll:Fwd:IterationError");
}
}
_204/=2;
if(Proj4js.common.PI/2-Math.abs(lat)<Proj4js.common.EPSLN){
_203=0;
}
var x=0.900316316158*this.R*_203*Math.cos(_204)+this.x0;
var y=1.4142135623731*this.R*Math.sin(_204)+this.y0;
p.x=x;
p.y=y;
return p;
},inverse:function(p){
var _20b;
var arg;
p.x-=this.x0;
var arg=p.y/(1.4142135623731*this.R);
if(Math.abs(arg)>0.999999999999){
arg=0.999999999999;
}
var _20b=Math.asin(arg);
var lon=Proj4js.common.adjust_lon(this.long0+(p.x/(0.900316316158*this.R*Math.cos(_20b))));
if(lon<(-Proj4js.common.PI)){
lon=-Proj4js.common.PI;
}
if(lon>Proj4js.common.PI){
lon=Proj4js.common.PI;
}
arg=(2*_20b+Math.sin(2*_20b))/Proj4js.common.PI;
if(Math.abs(arg)>1){
arg=1;
}
var lat=Math.asin(arg);
p.x=lon;
p.y=lat;
return p;
}};

OpenLayers.Map=OpenLayers.Class({Z_INDEX_BASE:{BaseLayer:100,Overlay:325,Popup:750,Control:1000},EVENT_TYPES:["preaddlayer","addlayer","removelayer","changelayer","movestart","move","moveend","zoomend","popupopen","popupclose","addmarker","removemarker","clearmarkers","mouseover","mouseout","mousemove","dragstart","drag","dragend","changebaselayer"],id:null,fractionalZoom:true,events:null,div:null,dragging:false,size:null,viewPortDiv:null,layerContainerOrigin:null,layerContainerDiv:null,layers:null,controls:null,popups:null,baseLayer:null,center:null,resolution:null,zoom:0,viewRequestID:0,tileSize:null,projection:"EPSG:4326",units:"degrees",resolutions:null,maxResolution:1.40625,minResolution:null,maxScale:null,minScale:null,maxExtent:null,minExtent:null,restrictedExtent:null,numZoomLevels:16,theme:null,displayProjection:null,fallThrough:true,panTween:null,eventListeners:null,panMethod:OpenLayers.Easing.Expo.easeOut,paddingForPopups:null,currentExtent:null,mfAjaxCompId:null,initialize:function(_1,_2){
this.tileSize=new OpenLayers.Size(OpenLayers.Map.TILE_WIDTH,OpenLayers.Map.TILE_HEIGHT);
this.maxExtent=new OpenLayers.Bounds(-180,-90,180,90);
this.center=new OpenLayers.LonLat(0,0);
this.paddingForPopups=new OpenLayers.Bounds(15,15,15,15);
OpenLayers.Util.extend(this,_2);
this.div=OpenLayers.Util.getElement(_1);
_1=_1.split(":")[1];
this.viewPortDiv=OpenLayers.Util.getElement(_1+"_MapFaces_Viewport");
this.layerContainerDiv=OpenLayers.Util.getElement(_1+"_MapFaces_Container");
this.events=new OpenLayers.Events(this,this.div,this.EVENT_TYPES,this.fallThrough);
this.popups=[];
this.layers=[];
this.initResolutions();
var _3=this.getSize();
this.center=this.getLonLatFromViewPortPx(new OpenLayers.Pixel(_3.w/2,_3.h/2));
this.updateSize();
if(this.eventListeners instanceof Object){
this.events.on(this.eventListeners);
}
this.events.register("movestart",this,this.updateSize);
if(OpenLayers.String.contains(navigator.appName,"Microsoft")){
this.events.register("resize",this,this.updateSize);
}else{
this.updateSizeDestroy=OpenLayers.Function.bind(this.updateSize,this);
OpenLayers.Event.observe(window,"resize",this.updateSizeDestroy);
}
if(this.controls==null){
this.controls=[];
if(OpenLayers.Control!=null){
this.controls.push(new OpenLayers.Control.Navigation());
this.controls.push(new OpenLayers.Control.PanZoom());
}else{
}
}
this.controls.push(new OpenLayers.Control.MouseWheelDefaults());
this.controls.push(new OpenLayers.Control.KeyboardDefaults());
this.controls.push(new OpenLayers.Control.ArgParser());
this.controls.push(new OpenLayers.Control.Attribution());
for(var i=0;i<this.controls.length;i++){
this.addControlToMap(this.controls[i]);
}
this.unloadDestroy=OpenLayers.Function.bind(this.destroy,this);
OpenLayers.Event.observe(window,"unload",this.unloadDestroy);
this.events.register("moveend",null,this.sendAjaxRequest);
this.events.register("moveend",null,this.setCurrentExtent);
var sc=document.createElement("script");
sc.setAttribute("type","text/javascript");
sc.innerHTML="function zoom(){"+this.id+".zoomToMaxExtent();"+"};"+"window.onload=zoom;";
document.getElementsByTagName("head")[0].appendChild(sc);
},sendAjaxRequest:function(){
var _6=this.getSize();
var _7=this.getExtent().toBBOX();
var _8={"synchronized":"true","refresh":this.layersName,"bbox":_7,"window":_6.w+","+_6.h,"render":"true","org.mapfaces.ajax.LAYER_CONTAINER_STYLE":"top:"+(-parseInt(this.layerContainerDiv.style.top))+"px;left:"+(-parseInt(this.layerContainerDiv.style.left)+"px;")};
_8[this.mfAjaxCompId]=this.mfAjaxCompId;
A4J.AJAX.Submit(this.mfRequestId,this.mfFormId,null,{"control":this,"single":true,"parameters":_8,"actionUrl":_6.location});
},getCurrentExtent:function(){
return this.currentExtent;
},setCurrentExtent:function(){
this.currentExtent=this.getExtent();
},initResolutions:function(){
var _9=new Array("projection","units","scales","resolutions","maxScale","minScale","maxResolution","minResolution","minExtent","maxExtent","numZoomLevels","maxZoomLevel");
if(this.options==null){
this.options={};
}
var _a={};
for(var i=0;i<_9.length;i++){
var _c=_9[i];
_a[_c]=this.options[_c]||this[_c];
}
if(this.options.minScale!=null&&this.options.maxScale!=null&&this.options.scales==null){
_a.scales=null;
}
if(this.options.minResolution!=null&&this.options.maxResolution!=null&&this.options.resolutions==null){
_a.resolutions=null;
}
if((!_a.numZoomLevels)&&(_a.maxZoomLevel)){
_a.numZoomLevels=_a.maxZoomLevel+1;
}
if((_a.scales!=null)||(_a.resolutions!=null)){
if(_a.scales!=null){
_a.resolutions=[];
for(var i=0;i<_a.scales.length;i++){
var _d=_a.scales[i];
_a.resolutions[i]=OpenLayers.Util.getResolutionFromScale(_d,_a.units);
}
}
_a.numZoomLevels=_a.resolutions.length;
}else{
if(_a.minScale){
_a.maxResolution=OpenLayers.Util.getResolutionFromScale(_a.minScale,_a.units);
}else{
if(_a.maxResolution=="auto"){
var _e=this.getSize();
var _f=_a.maxExtent.getWidth()/_e.w;
var _10=_a.maxExtent.getHeight()/_e.h;
_a.maxResolution=Math.max(_f,_10);
}
}
if(_a.maxScale!=null){
_a.minResolution=OpenLayers.Util.getResolutionFromScale(_a.maxScale,_a.units);
}else{
if((_a.minResolution=="auto")&&(_a.minExtent!=null)){
var _e=this.getSize();
var _f=_a.minExtent.getWidth()/_e.w;
var _10=_a.minExtent.getHeight()/_e.h;
_a.minResolution=Math.max(_f,_10);
}
}
if(_a.minResolution!=null&&this.options.numZoomLevels==undefined){
var _11=_a.maxResolution/_a.minResolution;
_a.numZoomLevels=Math.floor(Math.log(_11)/Math.log(2))+1;
}
_a.resolutions=new Array(_a.numZoomLevels);
var _12=2;
if(typeof _a.minResolution=="number"&&_a.numZoomLevels>1){
_12=Math.pow((_a.maxResolution/_a.minResolution),(1/(_a.numZoomLevels-1)));
}
for(var i=0;i<_a.numZoomLevels;i++){
var res=_a.maxResolution/Math.pow(_12,i);
_a.resolutions[i]=res;
}
}
_a.resolutions.sort(function(a,b){
return (b-a);
});
this.resolutions=_a.resolutions;
this.maxResolution=_a.resolutions[0];
var _16=_a.resolutions.length-1;
this.minResolution=_a.resolutions[_16];
this.scales=[];
for(var i=0;i<_a.resolutions.length;i++){
this.scales[i]=OpenLayers.Util.getScaleFromResolution(_a.resolutions[i],_a.units);
}
this.minScale=this.scales[0];
this.maxScale=this.scales[this.scales.length-1];
this.numZoomLevels=_a.numZoomLevels;
},unloadDestroy:null,updateSizeDestroy:null,destroy:function(){
if(!this.unloadDestroy){
return false;
}
OpenLayers.Event.stopObserving(window,"unload",this.unloadDestroy);
this.unloadDestroy=null;
if(this.updateSizeDestroy){
OpenLayers.Event.stopObserving(window,"resize",this.updateSizeDestroy);
}else{
this.events.unregister("resize",this,this.updateSize);
}
this.paddingForPopups=null;
if(this.controls!=null){
for(var i=this.controls.length-1;i>=0;--i){
this.controls[i].destroy();
}
this.controls=null;
}
if(this.layers!=null){
for(var i=this.layers.length-1;i>=0;--i){
this.layers[i].destroy(false);
}
this.layers=null;
}
if(this.viewPortDiv){
this.div.removeChild(this.viewPortDiv);
}
this.viewPortDiv=null;
if(this.eventListeners){
this.events.un(this.eventListeners);
this.eventListeners=null;
}
this.events.destroy();
this.events=null;
},setOptions:function(_18){
OpenLayers.Util.extend(this,_18);
},getTileSize:function(){
return this.tileSize;
},getBy:function(_19,_1a,_1b){
var _1c=(typeof _1b.test=="function");
var _1d=OpenLayers.Array.filter(this[_19],function(_1e){
return _1e[_1a]==_1b||(_1c&&_1b.test(_1e[_1a]));
});
return _1d;
},getLayersBy:function(_1f,_20){
return this.getBy("layers",_1f,_20);
},getLayersByName:function(_21){
return this.getLayersBy("name",_21);
},getLayersByClass:function(_22){
return this.getLayersBy("CLASS_NAME",_22);
},getControlsBy:function(_23,_24){
return this.getBy("controls",_23,_24);
},getControlsByClass:function(_25){
return this.getControlsBy("CLASS_NAME",_25);
},getLayer:function(id){
var _27=null;
for(var i=0;i<this.layers.length;i++){
var _29=this.layers[i];
if(_29.id==id){
_27=_29;
break;
}
}
return _27;
},setLayerZIndex:function(_2a,_2b){
_2a.setZIndex(this.Z_INDEX_BASE[_2a.isBaseLayer?"BaseLayer":"Overlay"]+_2b*5);
},resetLayersZIndex:function(){
for(var i=0;i<this.layers.length;i++){
var _2d=this.layers[i];
this.setLayerZIndex(_2d,i);
}
},addLayer:function(_2e){
for(var i=0;i<this.layers.length;i++){
if(this.layers[i]==_2e){
var msg=OpenLayers.i18n("layerAlreadyAdded",{"layerName":_2e.name});
OpenLayers.Console.warn(msg);
return false;
}
}
this.events.triggerEvent("preaddlayer",{layer:_2e});
_2e.div.className="olLayerDiv";
_2e.div.style.overflow="";
this.setLayerZIndex(_2e,this.layers.length);
if(_2e.isFixed){
this.viewPortDiv.appendChild(_2e.div);
}else{
this.layerContainerDiv.appendChild(_2e.div);
}
this.layers.push(_2e);
_2e.setMap(this);
if(_2e.isBaseLayer){
if(this.baseLayer==null){
this.setBaseLayer(_2e);
}else{
_2e.setVisibility(false);
}
}else{
_2e.redraw();
}
this.events.triggerEvent("addlayer",{layer:_2e});
},addLayers:function(_31){
for(var i=0;i<_31.length;i++){
this.addLayer(_31[i]);
}
},removeLayer:function(_33,_34){
if(_34==null){
_34=true;
}
if(_33.isFixed){
this.viewPortDiv.removeChild(_33.div);
}else{
this.layerContainerDiv.removeChild(_33.div);
}
OpenLayers.Util.removeItem(this.layers,_33);
_33.removeMap(this);
_33.map=null;
if(this.baseLayer==_33){
this.baseLayer=null;
if(_34){
for(var i=0;i<this.layers.length;i++){
var _36=this.layers[i];
if(_36.isBaseLayer){
this.setBaseLayer(_36);
break;
}
}
}
}
this.resetLayersZIndex();
this.events.triggerEvent("removelayer",{layer:_33});
},getNumLayers:function(){
return this.layers.length;
},getLayerIndex:function(_37){
return OpenLayers.Util.indexOf(this.layers,_37);
},setLayerIndex:function(_38,idx){
var _3a=this.getLayerIndex(_38);
if(idx<0){
idx=0;
}else{
if(idx>this.layers.length){
idx=this.layers.length;
}
}
if(_3a!=idx){
this.layers.splice(_3a,1);
this.layers.splice(idx,0,_38);
for(var i=0;i<this.layers.length;i++){
this.setLayerZIndex(this.layers[i],i);
}
this.events.triggerEvent("changelayer",{layer:_38,property:"order"});
}
},raiseLayer:function(_3c,_3d){
var idx=this.getLayerIndex(_3c)+_3d;
this.setLayerIndex(_3c,idx);
},setBaseLayer:function(_3f){
var _40=null;
if(this.baseLayer){
_40=this.baseLayer.getExtent();
}
if(_3f!=this.baseLayer){
if(OpenLayers.Util.indexOf(this.layers,_3f)!=-1){
if(this.baseLayer!=null){
this.baseLayer.setVisibility(false);
}
this.baseLayer=_3f;
this.viewRequestID++;
this.baseLayer.visibility=true;
var _41=this.getCenter();
if(_41!=null){
var _42=(_40)?_40.getCenterLonLat():_41;
var _43=(_40)?this.getZoomForExtent(_40,true):this.getZoomForResolution(this.resolution,true);
this.setCenter(_42,_43,false,true);
}
this.events.triggerEvent("changebaselayer",{layer:this.baseLayer});
}
}
},addControl:function(_44,px){
this.controls.push(_44);
this.addControlToMap(_44,px);
},addControlToMap:function(_46,px){
_46.outsideViewport=(_46.div!=null);
if(this.displayProjection&&!_46.displayProjection){
_46.displayProjection=this.displayProjection;
}
_46.setMap(this);
var div=_46.draw(px);
if(div){
if(!_46.outsideViewport){
div.style.zIndex=this.Z_INDEX_BASE["Control"]+this.controls.length;
this.viewPortDiv.appendChild(div);
}
}
},getControl:function(id){
var _4a=null;
for(var i=0;i<this.controls.length;i++){
var _4c=this.controls[i];
if(_4c.id==id){
_4a=_4c;
break;
}
}
return _4a;
},removeControl:function(_4d){
if((_4d)&&(_4d==this.getControl(_4d.id))){
if(_4d.div&&(_4d.div.parentNode==this.viewPortDiv)){
this.viewPortDiv.removeChild(_4d.div);
}
OpenLayers.Util.removeItem(this.controls,_4d);
}
},addPopup:function(_4e,_4f){
if(_4f){
for(var i=this.popups.length-1;i>=0;--i){
this.removePopup(this.popups[i]);
}
}
_4e.map=this;
this.popups.push(_4e);
var _51=_4e.draw();
if(_51){
_51.style.zIndex=this.Z_INDEX_BASE["Popup"]+this.popups.length;
this.layerContainerDiv.appendChild(_51);
}
},removePopup:function(_52){
OpenLayers.Util.removeItem(this.popups,_52);
if(_52.div){
try{
this.layerContainerDiv.removeChild(_52.div);
}
catch(e){
}
}
_52.map=null;
},getSize:function(){
var _53=null;
if(this.size!=null){
_53=this.size.clone();
}
return _53;
},updateSize:function(){
this.events.element.offsets=null;
var _54=this.getCurrentSize();
var _55=this.getSize();
if(_55==null){
this.size=_55=_54;
}
if(!_54.equals(_55)){
this.size=_54;
if(this!=null){
var _56=new OpenLayers.Pixel(_54.w/2,_54.h/2);
var _57=this.getLonLatFromViewPortPx(_56);
var _58=this.getZoom();
this.zoom=null;
if(this.getCurrentExtent()!=null){
this.zoomToExtent(this.getCurrentExtent());
}else{
this.setCenter(this.getCenter(),_58);
}
}
}
},getCurrentSize:function(){
var _59=new OpenLayers.Size(this.div.clientWidth,this.div.clientHeight);
if(_59.w==0&&_59.h==0||isNaN(_59.w)&&isNaN(_59.h)){
var dim=OpenLayers.Element.getDimensions(this.div);
_59.w=dim.width;
_59.h=dim.height;
}
if(_59.w==0&&_59.h==0||isNaN(_59.w)&&isNaN(_59.h)){
_59.w=parseInt(this.div.style.width);
_59.h=parseInt(this.div.style.height);
}
return _59;
},calculateBounds:function(_5b,_5c){
var _5d=null;
if(_5b==null){
_5b=this.getCenter();
}
if(_5c==null){
_5c=this.getResolution();
}
if((_5b!=null)&&(_5c!=null)){
var _5e=this.getSize();
var _5f=_5e.w*_5c;
var _60=_5e.h*_5c;
_5d=new OpenLayers.Bounds(_5b.lon-_5f/2,_5b.lat-_60/2,_5b.lon+_5f/2,_5b.lat+_60/2);
}
return _5d;
},getCenter:function(){
return this.center;
},getZoom:function(){
return this.zoom;
},pan:function(dx,dy,_63){
_63=OpenLayers.Util.applyDefaults(_63,{animate:true,dragging:false});
var _64=this.getViewPortPxFromLonLat(this.getCenter());
var _65=_64.add(dx,dy);
if(!_63.dragging||!_65.equals(_64)){
var _66=this.getLonLatFromViewPortPx(_65);
if(_63.animate){
this.panTo(_66);
}else{
this.setCenter(_66,null,_63.dragging);
}
}
},panTo:function(_67){
if(this.panMethod&&this.getExtent().containsLonLat(_67)){
if(!this.panTween){
this.panTween=new OpenLayers.Tween(this.panMethod);
}
var _68=this.getCenter();
if(_67.lon==_68.lon&&_67.lat==_68.lat){
return;
}
var _69={lon:_68.lon,lat:_68.lat};
var to={lon:_67.lon,lat:_67.lat};
this.panTween.start(_69,to,50,{callbacks:{start:OpenLayers.Function.bind(function(_6b){
this.events.triggerEvent("movestart");
},this),eachStep:OpenLayers.Function.bind(function(_6c){
_6c=new OpenLayers.LonLat(_6c.lon,_6c.lat);
this.moveTo(_6c,this.zoom,{"dragging":true,"noEvent":true});
},this),done:OpenLayers.Function.bind(function(_6d){
_6d=new OpenLayers.LonLat(_6d.lon,_6d.lat);
this.moveTo(_6d,this.zoom,{"noEvent":true});
this.events.triggerEvent("moveend");
},this)}});
}else{
this.setCenter(_67);
}
},setCenter:function(_6e,_6f,_70,_71){
this.moveTo(_6e,_6f,{"dragging":_70,"forceZoomChange":_71,"caller":"setCenter"});
},moveTo:function(_72,_73,_74){
if(!_74){
_74={};
}
var _75=_74.dragging;
var _76=_74.forceZoomChange;
var _77=_74.noEvent;
if(this.panTween&&_74.caller=="setCenter"){
this.panTween.stop();
}
if(!this.center&&!this.isValidLonLat(_72)){
_72=this.maxExtent.getCenterLonLat();
}
if(this.restrictedExtent!=null){
if(_72==null){
_72=this.getCenter();
}
if(_73==null){
_73=this.getZoom();
}
var _78=this.getResolutionForZoom(_73);
var _79=this.calculateBounds(_72,_78);
if(!this.restrictedExtent.containsBounds(_79)){
var _7a=this.restrictedExtent.getCenterLonLat();
if(_79.getWidth()>this.restrictedExtent.getWidth()){
_72=new OpenLayers.LonLat(_7a.lon,_72.lat);
}else{
if(_79.left<this.restrictedExtent.left){
_72=_72.add(this.restrictedExtent.left-_79.left,0);
}else{
if(_79.right>this.restrictedExtent.right){
_72=_72.add(this.restrictedExtent.right-_79.right,0);
}
}
}
if(_79.getHeight()>this.restrictedExtent.getHeight()){
_72=new OpenLayers.LonLat(_72.lon,_7a.lat);
}else{
if(_79.bottom<this.restrictedExtent.bottom){
_72=_72.add(0,this.restrictedExtent.bottom-_79.bottom);
}else{
if(_79.top>this.restrictedExtent.top){
_72=_72.add(0,this.restrictedExtent.top-_79.top);
}
}
}
}
}
var _7b=_76||((this.isValidZoomLevel(_73))&&(_73!=this.getZoom()));
var _7c=(this.isValidLonLat(_72))&&(!_72.equals(this.center));
if(_7b||_7c||!_75){
if(!this.dragging&&!_77){
this.events.triggerEvent("movestart");
}
if(_7c){
if((!_7b)&&(this.center)){
this.centerLayerContainer(_72);
}
this.center=_72.clone();
}
if((_7b)||(this.layerContainerOrigin==null)){
this.layerContainerOrigin=this.center.clone();
this.layerContainerDiv.style.left="0px";
this.layerContainerDiv.style.top="0px";
}
if(_7b){
this.zoom=_73;
this.resolution=this.getResolutionForZoom(_73);
this.viewRequestID++;
}
var _7d=this.getExtent();
_7d=this.getExtent();
for(var i=0;i<this.layers.length;i++){
var _7f=this.layers[i];
if(!_7f.isBaseLayer){
var _80=_7f.calculateInRange();
if(_7f.inRange!=_80){
_7f.inRange=_80;
if(!_80){
_7f.display(false);
}
this.events.triggerEvent("changelayer",{layer:_7f,property:"visibility"});
}
if(_80&&_7f.visibility){
_7f.moveTo(_7d,_7b,_75);
}
}
}
if(_7b){
for(var i=0;i<this.popups.length;i++){
this.popups[i].updatePosition();
}
}
this.events.triggerEvent("move");
if(_7b){
this.events.triggerEvent("zoomend");
}
}
if(!_75&&!_77){
this.events.triggerEvent("moveend");
}
this.dragging=!!_75;
},centerLayerContainer:function(_81){
var _82=this.getViewPortPxFromLonLat(this.layerContainerOrigin);
var _83=this.getViewPortPxFromLonLat(_81);
if((_82!=null)&&(_83!=null)){
this.layerContainerDiv.style.left=Math.round(_82.x-_83.x)+"px";
this.layerContainerDiv.style.top=Math.round(_82.y-_83.y)+"px";
}
},isValidZoomLevel:function(_84){
return ((_84!=null)&&(_84>=0)&&(_84<this.getNumZoomLevels()));
},isValidLonLat:function(_85){
var _86=false;
if(_85!=null){
var _87=this.getMaxExtent();
_86=_87.containsLonLat(_85);
}
return _86;
},getProjection:function(){
var _88=this.getProjectionObject();
return _88?_88.getCode():null;
},getProjectionObject:function(){
var _89=null;
if(this.baseLayer!=null){
_89=this.baseLayer.projection;
}else{
_89=this.projection;
}
return _89;
},getMaxResolution:function(){
var _8a=null;
if(this.baseLayer!=null){
_8a=this.baseLayer.maxResolution;
}else{
_8a=this.maxResolution;
}
return _8a;
},getMaxExtent:function(){
var _8b=null;
if(this.baseLayer!=null){
_8b=this.baseLayer.maxExtent;
}else{
_8b=this.maxExtent;
}
return _8b;
},getNumZoomLevels:function(){
var _8c=null;
if(this.baseLayer!=null){
_8c=this.baseLayer.numZoomLevels;
}else{
_8c=this.numZoomLevels;
}
return _8c;
},getExtent:function(){
var _8d=null;
if(this.baseLayer!=null){
_8d=this.baseLayer.getExtent();
}else{
_8d=this.calculateBounds();
}
return _8d;
},getResolution:function(){
var _8e=null;
if(this.baseLayer!=null){
_8e=this.baseLayer.getResolution();
}else{
var _8f=this.getZoom();
_8e=this.getResolutionForZoom(_8f);
}
return _8e;
},getUnits:function(){
var _90=null;
if(this.baseLayer!=null){
_90=this.baseLayer.units;
}else{
_90=this.units;
}
return _90;
},getScale:function(){
var _91=null;
if(this.baseLayer!=null){
var res=this.getResolution();
var _93=this.baseLayer.units;
_91=OpenLayers.Util.getScaleFromResolution(res,_93);
}else{
var _94=this.getResolution();
var _95=this.units;
_91=OpenLayers.Util.getScaleFromResolution(_94,_95);
}
return _91;
},getZoomForExtent:function(_96,_97){
var _98=this.getSize();
var _99=Math.max(_96.getWidth()/_98.w,_96.getHeight()/_98.h);
return this.getZoomForResolution(_99,_97);
},getResolutionForZoom:function(_9a){
_9a=Math.max(0,Math.min(_9a,this.resolutions.length-1));
var _9b;
if(this.fractionalZoom){
var low=Math.floor(_9a);
var _9d=Math.ceil(_9a);
_9b=this.resolutions[_9d]+((_9a-low)*(this.resolutions[low]-this.resolutions[_9d]));
}else{
_9b=this.resolutions[Math.round(_9a)];
}
return _9b;
},getZoomForResolution:function(_9e,_9f){
var _a0;
if(this.fractionalZoom){
var _a1=0;
var _a2=this.resolutions.length-1;
var _a3=this.resolutions[_a1];
var _a4=this.resolutions[_a2];
var res;
for(var i=0;i<this.resolutions.length;++i){
res=this.resolutions[i];
if(res>=_9e){
_a3=res;
_a1=i;
}
if(res<=_9e){
_a4=res;
_a2=i;
break;
}
}
var _a7=_a3-_a4;
if(_a7>0){
_a0=_a1+((_9e-_a4)/_a7);
}else{
_a0=_a1;
}
}else{
var _a8;
var _a9=Number.POSITIVE_INFINITY;
for(var i=0;i<this.resolutions.length;i++){
if(_9f){
_a8=Math.abs(this.resolutions[i]-_9e);
if(_a8>_a9){
break;
}
_a9=_a8;
}else{
if(this.resolutions[i]<_9e){
break;
}
}
}
_a0=Math.max(0,i-1);
}
return _a0;
},zoomTo:function(_aa){
if(this.isValidZoomLevel(_aa)){
this.setCenter(null,_aa);
}
},zoomIn:function(){
this.zoomTo(this.getZoom()+1);
},zoomOut:function(){
this.zoomTo(this.getZoom()-1);
},zoomToExtent:function(_ab){
var _ac=_ab.getCenterLonLat();
if(this.wrapDateLine){
var _ad=this.getMaxExtent();
_ab=_ab.clone();
while(_ab.right<_ab.left){
_ab.right+=_ad.getWidth();
}
_ac=_ab.getCenterLonLat().wrapDateLine(_ad);
}
this.setCenter(_ac,this.getZoomForExtent(_ab));
},zoomToMaxExtent:function(){
this.zoomToExtent(this.getMaxExtent());
},zoomToScale:function(_ae){
var res=OpenLayers.Util.getResolutionFromScale(_ae,this.units);
var _b0=this.getSize();
var _b1=_b0.w*res;
var _b2=_b0.h*res;
var _b3=this.getCenter();
var _b4=new OpenLayers.Bounds(_b3.lon-_b1/2,_b3.lat-_b2/2,_b3.lon+_b1/2,_b3.lat+_b2/2);
this.zoomToExtent(_b4);
},getLonLatFromViewPortPx:function(_b5){
var _b6=null;
if(_b5!=null){
var _b7=this.getSize();
var _b8=this.getCenter();
if(_b8){
var res=this.getResolution();
var _ba=_b5.x-(_b7.w/2);
var _bb=_b5.y-(_b7.h/2);
_b6=new OpenLayers.LonLat(_b8.lon+_ba*res,_b8.lat-_bb*res);
}
}
return _b6;
},getViewPortPxFromLonLat:function(_bc){
var px=null;
if(_bc!=null){
var _be=this.getResolution();
var _bf=this.getExtent();
px=new OpenLayers.Pixel((1/_be*(_bc.lon-_bf.left)),(1/_be*(_bf.top-_bc.lat)));
}
return px;
},getLonLatFromPixel:function(px){
return this.getLonLatFromViewPortPx(px);
},getPixelFromLonLat:function(_c1){
var px=this.getViewPortPxFromLonLat(_c1);
px.x=Math.round(px.x);
px.y=Math.round(px.y);
return px;
},getViewPortPxFromLayerPx:function(_c3){
var _c4=null;
if(_c3!=null){
var dX=parseInt(this.layerContainerDiv.style.left);
var dY=parseInt(this.layerContainerDiv.style.top);
_c4=_c3.add(dX,dY);
}
return _c4;
},getLayerPxFromViewPortPx:function(_c7){
var _c8=null;
if(_c7!=null){
var dX=-parseInt(this.layerContainerDiv.style.left);
var dY=-parseInt(this.layerContainerDiv.style.top);
_c8=_c7.add(dX,dY);
if(isNaN(_c8.x)||isNaN(_c8.y)){
_c8=null;
}
}
return _c8;
},getLonLatFromLayerPx:function(px){
px=this.getViewPortPxFromLayerPx(px);
return this.getLonLatFromViewPortPx(px);
},getLayerPxFromLonLat:function(_cc){
var px=this.getPixelFromLonLat(_cc);
return this.getLayerPxFromViewPortPx(px);
},CLASS_NAME:"OpenLayers.Map"});
OpenLayers.Map.TILE_WIDTH=256;
OpenLayers.Map.TILE_HEIGHT=256;

OpenLayers.Layer=OpenLayers.Class({id:null,name:null,div:null,opacity:null,EVENT_TYPES:["loadstart","loadend","loadcancel","visibilitychanged"],events:null,map:null,isBaseLayer:false,alpha:false,displayInLayerSwitcher:true,visibility:true,attribution:null,inRange:false,imageSize:null,imageOffset:null,options:null,eventListeners:null,gutter:0,projection:null,units:null,scales:null,resolutions:null,maxExtent:null,minExtent:null,maxResolution:null,minResolution:null,numZoomLevels:null,minScale:null,maxScale:null,displayOutsideMaxExtent:false,wrapDateLine:false,transitionEffect:null,SUPPORTED_TRANSITIONS:["resize"],initialize:function(_1,_2){
this.addOptions(_2);
this.name=_1;
if(this.id==null){
this.id=OpenLayers.Util.createUniqueID(this.CLASS_NAME+"_");
this.div=OpenLayers.Util.createDiv(this.id);
this.div.style.width="100%";
this.div.style.height="100%";
this.events=new OpenLayers.Events(this,this.div,this.EVENT_TYPES);
if(this.eventListeners instanceof Object){
this.events.on(this.eventListeners);
}
}
if(this.wrapDateLine){
this.displayOutsideMaxExtent=true;
}
},destroy:function(_3){
if(_3==null){
_3=true;
}
if(this.map!=null){
this.map.removeLayer(this,_3);
}
this.projection=null;
this.map=null;
this.name=null;
this.div=null;
this.options=null;
if(this.events){
if(this.eventListeners){
this.events.un(this.eventListeners);
}
this.events.destroy();
}
this.eventListeners=null;
this.events=null;
},clone:function(_4){
if(_4==null){
_4=new OpenLayers.Layer(this.name,this.options);
}
OpenLayers.Util.applyDefaults(_4,this);
_4.map=null;
return _4;
},setName:function(_5){
if(_5!=this.name){
this.name=_5;
if(this.map!=null){
this.map.events.triggerEvent("changelayer",{layer:this,property:"name"});
}
}
},addOptions:function(_6){
if(this.options==null){
this.options={};
}
OpenLayers.Util.extend(this.options,_6);
OpenLayers.Util.extend(this,_6);
},onMapResize:function(){
},redraw:function(){
var _7=false;
if(this.map){
this.inRange=this.calculateInRange();
var _8=this.getExtent();
if(_8&&this.inRange&&this.visibility){
this.moveTo(_8,true,false);
_7=true;
}
}
return _7;
},moveTo:function(_9,_a,_b){
var _c=this.visibility;
if(!this.isBaseLayer){
_c=_c&&this.inRange;
}
this.display(_c);
},setMap:function(_d){
if(this.map==null){
this.map=_d;
this.maxExtent=this.maxExtent||this.map.maxExtent;
this.projection=this.projection||this.map.projection;
if(this.projection&&typeof this.projection=="string"){
this.projection=new OpenLayers.Projection(this.projection);
}
this.units=this.projection.getUnits()||this.units||this.map.units;
this.initResolutions();
if(!this.isBaseLayer){
this.inRange=this.calculateInRange();
var _e=((this.visibility)&&(this.inRange));
this.div.style.display=_e?"":"none";
}
this.setTileSize();
}
},removeMap:function(_f){
},getImageSize:function(){
return (this.imageSize||this.tileSize);
},setTileSize:function(_10){
var _11=(_10)?_10:((this.tileSize)?this.tileSize:this.map.getTileSize());
this.tileSize=_11;
if(this.gutter){
this.imageOffset=new OpenLayers.Pixel(-this.gutter,-this.gutter);
this.imageSize=new OpenLayers.Size(_11.w+(2*this.gutter),_11.h+(2*this.gutter));
}
},getVisibility:function(){
return this.visibility;
},setVisibility:function(_12){
if(_12!=this.visibility){
this.visibility=_12;
this.display(_12);
this.redraw();
if(this.map!=null){
this.map.events.triggerEvent("changelayer",{layer:this,property:"visibility"});
}
this.events.triggerEvent("visibilitychanged");
}
},display:function(_13){
var _14=this.calculateInRange();
if(_13!=(this.div.style.display!="none")){
this.div.style.display=(_13&&_14)?"block":"none";
}
},calculateInRange:function(){
var _15=false;
if(this.map){
var _16=this.map.getResolution();
_15=((_16>=this.minResolution)&&(_16<=this.maxResolution));
}
return _15;
},setIsBaseLayer:function(_17){
if(_17!=this.isBaseLayer){
this.isBaseLayer=_17;
if(this.map!=null){
this.map.events.triggerEvent("changebaselayer",{layer:this});
}
}
},initResolutions:function(){
var _18=new Array("projection","units","scales","resolutions","maxScale","minScale","maxResolution","minResolution","minExtent","maxExtent","numZoomLevels","maxZoomLevel");
var _19={};
for(var i=0;i<_18.length;i++){
var _1b=_18[i];
_19[_1b]=this.options[_1b]||this.map[_1b];
}
if(this.options.minScale!=null&&this.options.maxScale!=null&&this.options.scales==null){
_19.scales=null;
}
if(this.options.minResolution!=null&&this.options.maxResolution!=null&&this.options.resolutions==null){
_19.resolutions=null;
}
if((!_19.numZoomLevels)&&(_19.maxZoomLevel)){
_19.numZoomLevels=_19.maxZoomLevel+1;
}
if((_19.scales!=null)||(_19.resolutions!=null)){
if(_19.scales!=null){
_19.resolutions=[];
for(var i=0;i<_19.scales.length;i++){
var _1c=_19.scales[i];
_19.resolutions[i]=OpenLayers.Util.getResolutionFromScale(_1c,_19.units);
}
}
_19.numZoomLevels=_19.resolutions.length;
}else{
if(_19.minScale){
_19.maxResolution=OpenLayers.Util.getResolutionFromScale(_19.minScale,_19.units);
}else{
if(_19.maxResolution=="auto"){
var _1d=this.map.getSize();
var _1e=_19.maxExtent.getWidth()/_1d.w;
var _1f=_19.maxExtent.getHeight()/_1d.h;
_19.maxResolution=Math.max(_1e,_1f);
}
}
if(_19.maxScale!=null){
_19.minResolution=OpenLayers.Util.getResolutionFromScale(_19.maxScale,_19.units);
}else{
if((_19.minResolution=="auto")&&(_19.minExtent!=null)){
var _1d=this.map.getSize();
var _1e=_19.minExtent.getWidth()/_1d.w;
var _1f=_19.minExtent.getHeight()/_1d.h;
_19.minResolution=Math.max(_1e,_1f);
}
}
if(_19.minResolution!=null&&this.options.numZoomLevels==undefined){
var _20=_19.maxResolution/_19.minResolution;
_19.numZoomLevels=Math.floor(Math.log(_20)/Math.log(2))+1;
}
_19.resolutions=new Array(_19.numZoomLevels);
var _21=2;
if(typeof _19.minResolution=="number"&&_19.numZoomLevels>1){
_21=Math.pow((_19.maxResolution/_19.minResolution),(1/(_19.numZoomLevels-1)));
}
for(var i=0;i<_19.numZoomLevels;i++){
var res=_19.maxResolution/Math.pow(_21,i);
_19.resolutions[i]=res;
}
}
_19.resolutions.sort(function(a,b){
return (b-a);
});
this.resolutions=_19.resolutions;
this.maxResolution=_19.resolutions[0];
var _25=_19.resolutions.length-1;
this.minResolution=_19.resolutions[_25];
this.scales=[];
for(var i=0;i<_19.resolutions.length;i++){
this.scales[i]=OpenLayers.Util.getScaleFromResolution(_19.resolutions[i],_19.units);
}
this.minScale=this.scales[0];
this.maxScale=this.scales[this.scales.length-1];
this.numZoomLevels=_19.numZoomLevels;
},getResolution:function(){
var _26=this.map.getZoom();
return this.getResolutionForZoom(_26);
},getExtent:function(){
return this.map.calculateBounds();
},getZoomForExtent:function(_27,_28){
var _29=this.map.getSize();
var _2a=Math.max(_27.getWidth()/_29.w,_27.getHeight()/_29.h);
return this.getZoomForResolution(_2a,_28);
},getDataExtent:function(){
},getResolutionForZoom:function(_2b){
_2b=Math.max(0,Math.min(_2b,this.resolutions.length-1));
var _2c;
if(this.map.fractionalZoom){
var low=Math.floor(_2b);
var _2e=Math.ceil(_2b);
_2c=this.resolutions[_2e]+((_2b-low)*(this.resolutions[low]-this.resolutions[_2e]));
}else{
_2c=this.resolutions[Math.round(_2b)];
}
return _2c;
},getZoomForResolution:function(_2f,_30){
var _31;
if(this.map.fractionalZoom){
var _32=0;
var _33=this.resolutions.length-1;
var _34=this.resolutions[_32];
var _35=this.resolutions[_33];
var res;
for(var i=0;i<this.resolutions.length;++i){
res=this.resolutions[i];
if(res>=_2f){
_34=res;
_32=i;
}
if(res<=_2f){
_35=res;
_33=i;
break;
}
}
var _38=_34-_35;
if(_38>0){
_31=_32+((_2f-_35)/_38);
}else{
_31=_32;
}
}else{
var _39;
var _3a=Number.POSITIVE_INFINITY;
for(var i=0;i<this.resolutions.length;i++){
if(_30){
_39=Math.abs(this.resolutions[i]-_2f);
if(_39>_3a){
break;
}
_3a=_39;
}else{
if(this.resolutions[i]<_2f){
break;
}
}
}
_31=Math.max(0,i-1);
}
return _31;
},getLonLatFromViewPortPx:function(_3b){
var _3c=null;
if(_3b!=null){
var _3d=this.map.getSize();
var _3e=this.map.getCenter();
if(_3e){
var res=this.map.getResolution();
var _40=_3b.x-(_3d.w/2);
var _41=_3b.y-(_3d.h/2);
_3c=new OpenLayers.LonLat(_3e.lon+_40*res,_3e.lat-_41*res);
if(this.wrapDateLine){
_3c=_3c.wrapDateLine(this.maxExtent);
}
}
}
return _3c;
},getViewPortPxFromLonLat:function(_42){
var px=null;
if(_42!=null){
var _44=this.map.getResolution();
var _45=this.map.getExtent();
px=new OpenLayers.Pixel((1/_44*(_42.lon-_45.left)),(1/_44*(_45.top-_42.lat)));
}
return px;
},setOpacity:function(_46){
if(_46!=this.opacity){
this.opacity=_46;
for(var i=0;i<this.div.childNodes.length;++i){
var _48=this.div.childNodes[i].firstChild;
OpenLayers.Util.modifyDOMElement(_48,null,null,null,null,null,null,_46);
}
}
},setZIndex:function(_49){
this.div.style.zIndex=_49;
},adjustBounds:function(_4a){
if(this.gutter){
var _4b=this.gutter*this.map.getResolution();
_4a=new OpenLayers.Bounds(_4a.left-_4b,_4a.bottom-_4b,_4a.right+_4b,_4a.top+_4b);
}
if(this.wrapDateLine){
var _4c={"rightTolerance":this.getResolution()};
_4a=_4a.wrapDateLine(this.maxExtent,_4c);
}
return _4a;
},CLASS_NAME:"OpenLayers.Layer"});

OpenLayers.Icon=OpenLayers.Class({url:null,size:null,offset:null,calculateOffset:null,imageDiv:null,px:null,initialize:function(_1,_2,_3,_4){
this.url=_1;
this.size=(_2)?_2:new OpenLayers.Size(20,20);
this.offset=_3?_3:new OpenLayers.Pixel(-(this.size.w/2),-(this.size.h/2));
this.calculateOffset=_4;
var id=OpenLayers.Util.createUniqueID("OL_Icon_");
this.imageDiv=OpenLayers.Util.createAlphaImageDiv(id);
},destroy:function(){
OpenLayers.Event.stopObservingElement(this.imageDiv.firstChild);
this.imageDiv.innerHTML="";
this.imageDiv=null;
},clone:function(){
return new OpenLayers.Icon(this.url,this.size,this.offset,this.calculateOffset);
},setSize:function(_6){
if(_6!=null){
this.size=_6;
}
this.draw();
},setUrl:function(_7){
if(_7!=null){
this.url=_7;
}
this.draw();
},draw:function(px){
OpenLayers.Util.modifyAlphaImageDiv(this.imageDiv,null,null,this.size,this.url,"absolute");
this.moveTo(px);
return this.imageDiv;
},setOpacity:function(_9){
OpenLayers.Util.modifyAlphaImageDiv(this.imageDiv,null,null,null,null,null,null,null,_9);
},moveTo:function(px){
if(px!=null){
this.px=px;
}
if(this.imageDiv!=null){
if(this.px==null){
this.display(false);
}else{
if(this.calculateOffset){
this.offset=this.calculateOffset(this.size);
}
var _b=this.px.offset(this.offset);
OpenLayers.Util.modifyAlphaImageDiv(this.imageDiv,null,_b);
}
}
},display:function(_c){
this.imageDiv.style.display=(_c)?"":"none";
},CLASS_NAME:"OpenLayers.Icon"});

OpenLayers.Marker=OpenLayers.Class({icon:null,lonlat:null,events:null,map:null,initialize:function(_1,_2){
this.lonlat=_1;
var _3=(_2)?_2:OpenLayers.Marker.defaultIcon();
if(this.icon==null){
this.icon=_3;
}else{
this.icon.url=_3.url;
this.icon.size=_3.size;
this.icon.offset=_3.offset;
this.icon.calculateOffset=_3.calculateOffset;
}
this.events=new OpenLayers.Events(this,this.icon.imageDiv,null);
},destroy:function(){
this.map=null;
this.events.destroy();
this.events=null;
if(this.icon!=null){
this.icon.destroy();
this.icon=null;
}
},draw:function(px){
return this.icon.draw(px);
},moveTo:function(px){
if((px!=null)&&(this.icon!=null)){
this.icon.moveTo(px);
}
this.lonlat=this.map.getLonLatFromLayerPx(px);
},onScreen:function(){
var _6=false;
if(this.map){
var _7=this.map.getExtent();
_6=_7.containsLonLat(this.lonlat);
}
return _6;
},inflate:function(_8){
if(this.icon){
var _9=new OpenLayers.Size(this.icon.size.w*_8,this.icon.size.h*_8);
this.icon.setSize(_9);
}
},setOpacity:function(_a){
this.icon.setOpacity(_a);
},setUrl:function(_b){
this.icon.setUrl(_b);
},display:function(_c){
this.icon.display(_c);
},CLASS_NAME:"OpenLayers.Marker"});
OpenLayers.Marker.defaultIcon=function(){
var _d=OpenLayers.Util.getImagesLocation()+"marker.png";
var _e=new OpenLayers.Size(21,25);
var _f=function(_10){
return new OpenLayers.Pixel(-(_10.w/2),-_10.h);
};
return new OpenLayers.Icon(_d,_e,null,_f);
};

OpenLayers.Marker.Box=OpenLayers.Class(OpenLayers.Marker,{bounds:null,div:null,initialize:function(_1,_2,_3){
this.bounds=_1;
this.div=OpenLayers.Util.createDiv();
this.div.style.overflow="hidden";
this.events=new OpenLayers.Events(this,this.div,null);
this.setBorder(_2,_3);
},destroy:function(){
this.bounds=null;
this.div=null;
OpenLayers.Marker.prototype.destroy.apply(this,arguments);
},setBorder:function(_4,_5){
if(!_4){
_4="red";
}
if(!_5){
_5=2;
}
this.div.style.border=_5+"px solid "+_4;
},draw:function(px,sz){
OpenLayers.Util.modifyDOMElement(this.div,null,px,sz);
return this.div;
},onScreen:function(){
var _8=false;
if(this.map){
var _9=this.map.getExtent();
_8=_9.containsBounds(this.bounds,true,true);
}
return _8;
},display:function(_a){
this.div.style.display=(_a)?"":"none";
},CLASS_NAME:"OpenLayers.Marker.Box"});

OpenLayers.Popup=OpenLayers.Class({events:null,id:"",lonlat:null,div:null,size:null,contentHTML:"",backgroundColor:"",opacity:"",border:"",contentDiv:null,groupDiv:null,closeDiv:null,autoSize:false,minSize:null,maxSize:null,padding:0,fixPadding:function(){
if(typeof this.padding=="number"){
this.padding=new OpenLayers.Bounds(this.padding,this.padding,this.padding,this.padding);
}
},panMapIfOutOfView:false,map:null,initialize:function(id,_2,_3,_4,_5,_6){
if(id==null){
id=OpenLayers.Util.createUniqueID(this.CLASS_NAME+"_");
}
this.id=id;
this.lonlat=_2;
this.size=(_3!=null)?_3:new OpenLayers.Size(OpenLayers.Popup.WIDTH,OpenLayers.Popup.HEIGHT);
if(_4!=null){
this.contentHTML=_4;
}
this.backgroundColor=OpenLayers.Popup.COLOR;
this.opacity=OpenLayers.Popup.OPACITY;
this.border=OpenLayers.Popup.BORDER;
this.div=OpenLayers.Util.createDiv(this.id,null,null,null,null,null,"hidden");
this.div.className="olPopup";
var _7=this.id+"_GroupDiv";
this.groupDiv=OpenLayers.Util.createDiv(_7,null,null,null,"relative",null,"hidden");
var id=this.div.id+"_contentDiv";
this.contentDiv=OpenLayers.Util.createDiv(id,null,this.size.clone(),null,"relative");
this.contentDiv.className="olPopupContent";
this.groupDiv.appendChild(this.contentDiv);
this.div.appendChild(this.groupDiv);
if(_5){
this.addCloseBox(_6);
}
this.registerEvents();
},destroy:function(){
this.id=null;
this.lonlat=null;
this.size=null;
this.contentHTML=null;
this.backgroundColor=null;
this.opacity=null;
this.border=null;
this.events.destroy();
this.events=null;
if(this.closeDiv){
OpenLayers.Event.stopObservingElement(this.closeDiv);
this.groupDiv.removeChild(this.closeDiv);
}
this.closeDiv=null;
this.div.removeChild(this.groupDiv);
this.groupDiv=null;
if(this.map!=null){
this.map.removePopup(this);
}
this.map=null;
this.div=null;
this.autoSize=null;
this.minSize=null;
this.maxSize=null;
this.padding=null;
this.panMapIfOutOfView=null;
},draw:function(px){
if(px==null){
if((this.lonlat!=null)&&(this.map!=null)){
px=this.map.getLayerPxFromLonLat(this.lonlat);
}
}
if(OpenLayers.Util.getBrowserName()=="firefox"){
this.map.events.register("movestart",this,function(){
var _9=document.defaultView.getComputedStyle(this.contentDiv,null);
var _a=_9.getPropertyValue("overflow");
if(_a!="hidden"){
this.contentDiv._oldOverflow=_a;
this.contentDiv.style.overflow="hidden";
}
});
this.map.events.register("moveend",this,function(){
var _b=this.contentDiv._oldOverflow;
if(_b){
this.contentDiv.style.overflow=_b;
this.contentDiv._oldOverflow=null;
}
});
}
this.moveTo(px);
if(!this.autoSize){
this.setSize(this.size);
}
this.setBackgroundColor();
this.setOpacity();
this.setBorder();
this.setContentHTML();
if(this.panMapIfOutOfView){
this.panIntoView();
}
return this.div;
},updatePosition:function(){
if((this.lonlat)&&(this.map)){
var px=this.map.getLayerPxFromLonLat(this.lonlat);
if(px){
this.moveTo(px);
}
}
},moveTo:function(px){
if((px!=null)&&(this.div!=null)){
this.div.style.left=px.x+"px";
this.div.style.top=px.y+"px";
}
},visible:function(){
return OpenLayers.Element.visible(this.div);
},toggle:function(){
if(this.visible()){
this.hide();
}else{
this.show();
}
},show:function(){
OpenLayers.Element.show(this.div);
if(this.panMapIfOutOfView){
this.panIntoView();
}
},hide:function(){
OpenLayers.Element.hide(this.div);
},setSize:function(_e){
this.size=_e;
var _f=this.size.clone();
var _10=this.getContentDivPadding();
var _11=_10.left+_10.right;
var _12=_10.top+_10.bottom;
this.fixPadding();
_11+=this.padding.left+this.padding.right;
_12+=this.padding.top+this.padding.bottom;
if(this.closeDiv){
var _13=parseInt(this.closeDiv.style.width);
_11+=_13+_10.right;
}
this.size.w+=_11;
this.size.h+=_12;
if(OpenLayers.Util.getBrowserName()=="msie"){
_f.w+=_10.left+_10.right;
_f.h+=_10.bottom+_10.top;
}
if(this.div!=null){
this.div.style.width=this.size.w+"px";
this.div.style.height=this.size.h+"px";
}
if(this.contentDiv!=null){
this.contentDiv.style.width=_f.w+"px";
this.contentDiv.style.height=_f.h+"px";
}
},setBackgroundColor:function(_14){
if(_14!=undefined){
this.backgroundColor=_14;
}
if(this.div!=null){
this.div.style.backgroundColor=this.backgroundColor;
}
},setOpacity:function(_15){
if(_15!=undefined){
this.opacity=_15;
}
if(this.div!=null){
this.div.style.opacity=this.opacity;
this.div.style.filter="alpha(opacity="+this.opacity*100+")";
}
},setBorder:function(_16){
if(_16!=undefined){
this.border=_16;
}
if(this.div!=null){
this.div.style.border=this.border;
}
},setContentHTML:function(_17){
if(_17!=null){
this.contentHTML=_17;
}
if(this.autoSize){
var _18=OpenLayers.Util.getRenderedDimensions(this.contentHTML);
var _19=this.getSafeContentSize(_18);
var _1a=null;
if(_19.equals(_18)){
_1a=_18;
}else{
var _1b=new OpenLayers.Size();
_1b.w=(_19.w<_18.w)?_19.w:null;
_1b.h=(_19.h<_18.h)?_19.h:null;
if(_1b.w&&_1b.h){
_1a=_19;
}else{
var _1c=OpenLayers.Util.getRenderedDimensions(this.contentHTML,_1b);
var _1d=OpenLayers.Element.getStyle(this.contentDiv,"overflow");
if((_1d!="hidden")&&(_1c.equals(_19))){
var _1e=OpenLayers.Util.getScrollbarWidth();
if(_1b.w){
_1c.h+=_1e;
}else{
_1c.w+=_1e;
}
}
_1a=this.getSafeContentSize(_1c);
}
}
this.setSize(_1a);
}
if(this.contentDiv!=null){
this.contentDiv.innerHTML=this.contentHTML;
}
},getSafeContentSize:function(_1f){
var _20=_1f.clone();
var _21=this.getContentDivPadding();
var _22=_21.left+_21.right;
var _23=_21.top+_21.bottom;
this.fixPadding();
_22+=this.padding.left+this.padding.right;
_23+=this.padding.top+this.padding.bottom;
if(this.closeDiv){
var _24=parseInt(this.closeDiv.style.width);
_22+=_24+_21.right;
}
if(this.minSize){
_20.w=Math.max(_20.w,(this.minSize.w-_22));
_20.h=Math.max(_20.h,(this.minSize.h-_23));
}
if(this.maxSize){
_20.w=Math.min(_20.w,(this.maxSize.w-_22));
_20.h=Math.min(_20.h,(this.maxSize.h-_23));
}
if(this.map&&this.map.size){
var _25=this.map.size.h-this.map.paddingForPopups.top-this.map.paddingForPopups.bottom-_23;
var _26=this.map.size.w-this.map.paddingForPopups.left-this.map.paddingForPopups.right-_22;
_20.w=Math.min(_20.w,_26);
_20.h=Math.min(_20.h,_25);
}
return _20;
},getContentDivPadding:function(){
var _27=this._contentDivPadding;
if(!_27){
this.div.style.display="none";
document.body.appendChild(this.div);
_27=new OpenLayers.Bounds(OpenLayers.Element.getStyle(this.contentDiv,"padding-left"),OpenLayers.Element.getStyle(this.contentDiv,"padding-bottom"),OpenLayers.Element.getStyle(this.contentDiv,"padding-right"),OpenLayers.Element.getStyle(this.contentDiv,"padding-top"));
this._contentDivPadding=_27;
document.body.removeChild(this.div);
this.div.style.display="";
}
return _27;
},addCloseBox:function(_28){
this.closeDiv=OpenLayers.Util.createDiv(this.id+"_close",null,new OpenLayers.Size(17,17));
this.closeDiv.className="olPopupCloseBox";
var _29=this.getContentDivPadding();
this.closeDiv.style.right=_29.right+"px";
this.closeDiv.style.top=_29.top+"px";
this.groupDiv.appendChild(this.closeDiv);
var _2a=_28||function(e){
this.hide();
OpenLayers.Event.stop(e);
};
OpenLayers.Event.observe(this.closeDiv,"click",OpenLayers.Function.bindAsEventListener(_2a,this));
},panIntoView:function(){
var _2c=this.map.getSize();
var _2d=this.map.getViewPortPxFromLayerPx(new OpenLayers.Pixel(parseInt(this.div.style.left),parseInt(this.div.style.top)));
var _2e=_2d.clone();
if(_2d.x<this.map.paddingForPopups.left){
_2e.x=this.map.paddingForPopups.left;
}else{
if((_2d.x+this.size.w)>(_2c.w-this.map.paddingForPopups.right)){
_2e.x=_2c.w-this.map.paddingForPopups.right-this.size.w;
}
}
if(_2d.y<this.map.paddingForPopups.top){
_2e.y=this.map.paddingForPopups.top;
}else{
if((_2d.y+this.size.h)>(_2c.h-this.map.paddingForPopups.bottom)){
_2e.y=_2c.h-this.map.paddingForPopups.bottom-this.size.h;
}
}
var dx=_2d.x-_2e.x;
var dy=_2d.y-_2e.y;
this.map.pan(dx,dy);
},registerEvents:function(){
this.events=new OpenLayers.Events(this,this.div,null,true);
this.events.on({"mousedown":this.onmousedown,"mousemove":this.onmousemove,"mouseup":this.onmouseup,"click":this.onclick,"mouseout":this.onmouseout,"dblclick":this.ondblclick,scope:this});
},onmousedown:function(evt){
this.mousedown=true;
OpenLayers.Event.stop(evt,true);
},onmousemove:function(evt){
if(this.mousedown){
OpenLayers.Event.stop(evt,true);
}
},onmouseup:function(evt){
if(this.mousedown){
this.mousedown=false;
OpenLayers.Event.stop(evt,true);
}
},onclick:function(evt){
OpenLayers.Event.stop(evt,true);
},onmouseout:function(evt){
this.mousedown=false;
},ondblclick:function(evt){
OpenLayers.Event.stop(evt,true);
},CLASS_NAME:"OpenLayers.Popup"});
OpenLayers.Popup.WIDTH=200;
OpenLayers.Popup.HEIGHT=200;
OpenLayers.Popup.COLOR="white";
OpenLayers.Popup.OPACITY=1;
OpenLayers.Popup.BORDER="0px";

OpenLayers.Layer.Markers=OpenLayers.Class(OpenLayers.Layer,{isBaseLayer:false,markers:null,drawn:false,initialize:function(_1,_2){
OpenLayers.Layer.prototype.initialize.apply(this,arguments);
this.markers=[];
},destroy:function(){
this.clearMarkers();
this.markers=null;
OpenLayers.Layer.prototype.destroy.apply(this,arguments);
},setOpacity:function(_3){
if(_3!=this.opacity){
this.opacity=_3;
for(var i=0;i<this.markers.length;i++){
this.markers[i].setOpacity(this.opacity);
}
}
},moveTo:function(_5,_6,_7){
OpenLayers.Layer.prototype.moveTo.apply(this,arguments);
if(_6||!this.drawn){
for(var i=0;i<this.markers.length;i++){
this.drawMarker(this.markers[i]);
}
this.drawn=true;
}
},addMarker:function(_9){
this.markers.push(_9);
if(this.opacity!=null){
_9.setOpacity(this.opacity);
}
if(this.map&&this.map.getExtent()){
_9.map=this.map;
this.drawMarker(_9);
}
},removeMarker:function(_a){
if(this.markers&&this.markers.length){
OpenLayers.Util.removeItem(this.markers,_a);
if((_a.icon!=null)&&(_a.icon.imageDiv!=null)&&(_a.icon.imageDiv.parentNode==this.div)){
this.div.removeChild(_a.icon.imageDiv);
_a.drawn=false;
}
}
},clearMarkers:function(){
if(this.markers!=null){
while(this.markers.length>0){
this.removeMarker(this.markers[0]);
}
}
},drawMarker:function(_b){
var px=this.map.getLayerPxFromLonLat(_b.lonlat);
if(px==null){
_b.display(false);
}else{
var _d=_b.draw(px);
if(!_b.drawn){
this.div.appendChild(_d);
_b.drawn=true;
}
}
},getDataExtent:function(){
var _e=null;
if(this.markers&&(this.markers.length>0)){
var _e=new OpenLayers.Bounds();
for(var i=0;i<this.markers.length;i++){
var _10=this.markers[i];
_e.extend(_10.lonlat);
}
}
return _e;
},CLASS_NAME:"OpenLayers.Layer.Markers"});

OpenLayers.Layer.Text=OpenLayers.Class(OpenLayers.Layer.Markers,{location:null,features:null,formatOptions:null,selectedFeature:null,initialize:function(_1,_2){
OpenLayers.Layer.Markers.prototype.initialize.apply(this,arguments);
this.features=new Array();
},destroy:function(){
OpenLayers.Layer.Markers.prototype.destroy.apply(this,arguments);
this.clearFeatures();
this.features=null;
},loadText:function(){
if(!this.loaded){
if(this.location!=null){
var _3=function(e){
this.events.triggerEvent("loadend");
};
this.events.triggerEvent("loadstart");
OpenLayers.Request.GET({url:this.location,success:this.parseData,failure:_3,scope:this});
this.loaded=true;
}
}
},moveTo:function(_5,_6,_7){
OpenLayers.Layer.Markers.prototype.moveTo.apply(this,arguments);
if(this.visibility&&!this.loaded){
this.events.triggerEvent("loadstart");
this.loadText();
}
},parseData:function(_8){
var _9=_8.responseText;
var _a={};
OpenLayers.Util.extend(_a,this.formatOptions);
if(this.map&&!this.projection.equals(this.map.getProjectionObject())){
_a.externalProjection=this.projection;
_a.internalProjection=this.map.getProjectionObject();
}
var _b=new OpenLayers.Format.Text(_a);
features=_b.read(_9);
for(var i=0;i<features.length;i++){
var _d={};
var _e=features[i];
var _f;
var _10,iconOffset;
_f=new OpenLayers.LonLat(_e.geometry.x,_e.geometry.y);
if(_e.style.graphicWidth&&_e.style.graphicHeight){
_10=new OpenLayers.Size(_e.style.graphicWidth,_e.style.graphicHeight);
}
if(_e.style.graphicXOffset!==undefined&&_e.style.graphicYOffset!==undefined){
iconOffset=new OpenLayers.Pixel(_e.style.graphicXOffset,_e.style.graphicYOffset);
}
if(_e.style.externalGraphic!=null){
_d.icon=new OpenLayers.Icon(_e.style.externalGraphic,_10,iconOffset);
}else{
_d.icon=OpenLayers.Marker.defaultIcon();
if(_10!=null){
_d.icon.setSize(_10);
}
}
if((_e.attributes.title!=null)&&(_e.attributes.description!=null)){
_d["popupContentHTML"]="<h2>"+_e.attributes.title+"</h2>"+"<p>"+_e.attributes.description+"</p>";
}
_d["overflow"]=_e.attributes.overflow||"auto";
var _11=new OpenLayers.Feature(this,_f,_d);
this.features.push(_11);
var _12=_11.createMarker();
if((_e.attributes.title!=null)&&(_e.attributes.description!=null)){
_12.events.register("click",_11,this.markerClick);
}
this.addMarker(_12);
}
this.events.triggerEvent("loadend");
},markerClick:function(evt){
var _14=(this==this.layer.selectedFeature);
this.layer.selectedFeature=(!_14)?this:null;
for(var i=0;i<this.layer.map.popups.length;i++){
this.layer.map.removePopup(this.layer.map.popups[i]);
}
if(!_14){
this.layer.map.addPopup(this.createPopup());
}
OpenLayers.Event.stop(evt);
},clearFeatures:function(){
if(this.features!=null){
while(this.features.length>0){
var _16=this.features[0];
OpenLayers.Util.removeItem(this.features,_16);
_16.destroy();
}
}
},CLASS_NAME:"OpenLayers.Layer.Text"});

OpenLayers.Layer.GeoRSS=OpenLayers.Class(OpenLayers.Layer.Markers,{location:null,features:null,formatOptions:null,selectedFeature:null,icon:null,popupSize:null,useFeedTitle:true,initialize:function(_1,_2,_3){
OpenLayers.Layer.Markers.prototype.initialize.apply(this,[_1,_3]);
this.location=_2;
this.features=[];
},destroy:function(){
OpenLayers.Layer.Markers.prototype.destroy.apply(this,arguments);
this.clearFeatures();
this.features=null;
},loadRSS:function(){
if(!this.loaded){
this.events.triggerEvent("loadstart");
OpenLayers.Request.GET({url:this.location,success:this.parseData,scope:this});
this.loaded=true;
}
},moveTo:function(_4,_5,_6){
OpenLayers.Layer.Markers.prototype.moveTo.apply(this,arguments);
if(this.visibility&&!this.loaded){
this.loadRSS();
}
},parseData:function(_7){
var _8=_7.responseXML;
if(!_8||!_8.documentElement){
_8=OpenLayers.Format.XML.prototype.read(_7.responseText);
}
if(this.useFeedTitle){
var _9=null;
try{
_9=_8.getElementsByTagNameNS("*","title")[0].firstChild.nodeValue;
}
catch(e){
_9=_8.getElementsByTagName("title")[0].firstChild.nodeValue;
}
if(_9){
this.setName(_9);
}
}
var _a={};
OpenLayers.Util.extend(_a,this.formatOptions);
if(this.map&&!this.projection.equals(this.map.getProjectionObject())){
_a.externalProjection=this.projection;
_a.internalProjection=this.map.getProjectionObject();
}
var _b=new OpenLayers.Format.GeoRSS(_a);
var _c=_b.read(_8);
for(var i=0;i<_c.length;i++){
var _e={};
var _f=_c[i];
if(!_f.geometry){
continue;
}
var _10=_f.attributes.title?_f.attributes.title:"Untitled";
var _11=_f.attributes.description?_f.attributes.description:"No description.";
var _12=_f.attributes.link?_f.attributes.link:"";
var _13=_f.geometry.getBounds().getCenterLonLat();
_e.icon=this.icon==null?OpenLayers.Marker.defaultIcon():this.icon.clone();
_e.popupSize=this.popupSize?this.popupSize.clone():new OpenLayers.Size(250,120);
if(_10||_11){
_e.title=_10;
_e.description=_11;
var _14="<div class=\"olLayerGeoRSSClose\">[x]</div>";
_14+="<div class=\"olLayerGeoRSSTitle\">";
if(_12){
_14+="<a class=\"link\" href=\""+_12+"\" target=\"_blank\">";
}
_14+=_10;
if(_12){
_14+="</a>";
}
_14+="</div>";
_14+="<div style=\"\" class=\"olLayerGeoRSSDescription\">";
_14+=_11;
_14+="</div>";
_e["popupContentHTML"]=_14;
}
var _f=new OpenLayers.Feature(this,_13,_e);
this.features.push(_f);
var _15=_f.createMarker();
_15.events.register("click",_f,this.markerClick);
this.addMarker(_15);
}
this.events.triggerEvent("loadend");
},markerClick:function(evt){
var _17=(this==this.layer.selectedFeature);
this.layer.selectedFeature=(!_17)?this:null;
for(var i=0;i<this.layer.map.popups.length;i++){
this.layer.map.removePopup(this.layer.map.popups[i]);
}
if(!_17){
var _19=this.createPopup();
OpenLayers.Event.observe(_19.div,"click",OpenLayers.Function.bind(function(){
for(var i=0;i<this.layer.map.popups.length;i++){
this.layer.map.removePopup(this.layer.map.popups[i]);
}
},this));
this.layer.map.addPopup(_19);
}
OpenLayers.Event.stop(evt);
},clearFeatures:function(){
if(this.features!=null){
while(this.features.length>0){
var _1b=this.features[0];
OpenLayers.Util.removeItem(this.features,_1b);
_1b.destroy();
}
}
},CLASS_NAME:"OpenLayers.Layer.GeoRSS"});

OpenLayers.Layer.Boxes=OpenLayers.Class(OpenLayers.Layer.Markers,{initialize:function(_1,_2){
OpenLayers.Layer.Markers.prototype.initialize.apply(this,arguments);
},drawMarker:function(_3){
var _4=_3.bounds;
var _5=this.map.getLayerPxFromLonLat(new OpenLayers.LonLat(_4.left,_4.top));
var _6=this.map.getLayerPxFromLonLat(new OpenLayers.LonLat(_4.right,_4.bottom));
if(_6==null||_5==null){
_3.display(false);
}else{
var sz=new OpenLayers.Size(Math.max(1,_6.x-_5.x),Math.max(1,_6.y-_5.y));
var _8=_3.draw(_5,sz);
if(!_3.drawn){
this.div.appendChild(_8);
_3.drawn=true;
}
}
},removeMarker:function(_9){
OpenLayers.Util.removeItem(this.markers,_9);
if((_9.div!=null)&&(_9.div.parentNode==this.div)){
this.div.removeChild(_9.div);
}
},CLASS_NAME:"OpenLayers.Layer.Boxes"});

OpenLayers.Popup.Anchored=OpenLayers.Class(OpenLayers.Popup,{relativePosition:null,anchor:null,initialize:function(id,_2,_3,_4,_5,_6,_7){
var _8=new Array(id,_2,_3,_4,_6,_7);
OpenLayers.Popup.prototype.initialize.apply(this,_8);
this.anchor=(_5!=null)?_5:{size:new OpenLayers.Size(0,0),offset:new OpenLayers.Pixel(0,0)};
},destroy:function(){
this.anchor=null;
this.relativePosition=null;
OpenLayers.Popup.prototype.destroy.apply(this,arguments);
},show:function(){
this.updatePosition();
OpenLayers.Popup.prototype.show.apply(this,arguments);
},moveTo:function(px){
var _a=this.relativePosition;
this.relativePosition=this.calculateRelativePosition(px);
var _b=this.calculateNewPx(px);
var _c=new Array(_b);
OpenLayers.Popup.prototype.moveTo.apply(this,_c);
if(this.relativePosition!=_a){
this.updateRelativePosition();
}
},setSize:function(_d){
OpenLayers.Popup.prototype.setSize.apply(this,arguments);
if((this.lonlat)&&(this.map)){
var px=this.map.getLayerPxFromLonLat(this.lonlat);
this.moveTo(px);
}
},calculateRelativePosition:function(px){
var _10=this.map.getLonLatFromLayerPx(px);
var _11=this.map.getExtent();
var _12=_11.determineQuadrant(_10);
return OpenLayers.Bounds.oppositeQuadrant(_12);
},updateRelativePosition:function(){
},calculateNewPx:function(px){
var _14=px.offset(this.anchor.offset);
var top=(this.relativePosition.charAt(0)=="t");
_14.y+=(top)?-this.size.h:this.anchor.size.h;
var _16=(this.relativePosition.charAt(1)=="l");
_14.x+=(_16)?-this.size.w:this.anchor.size.w;
return _14;
},CLASS_NAME:"OpenLayers.Popup.Anchored"});

OpenLayers.Popup.AnchoredBubble=OpenLayers.Class(OpenLayers.Popup.Anchored,{rounded:false,initialize:function(id,_2,_3,_4,_5,_6,_7){
this.padding=new OpenLayers.Bounds(0,OpenLayers.Popup.AnchoredBubble.CORNER_SIZE,0,OpenLayers.Popup.AnchoredBubble.CORNER_SIZE);
OpenLayers.Popup.Anchored.prototype.initialize.apply(this,arguments);
},draw:function(px){
OpenLayers.Popup.Anchored.prototype.draw.apply(this,arguments);
this.setContentHTML();
this.setBackgroundColor();
this.setOpacity();
return this.div;
},updateRelativePosition:function(){
this.setRicoCorners();
},setSize:function(_9){
OpenLayers.Popup.Anchored.prototype.setSize.apply(this,arguments);
this.setRicoCorners();
},setBackgroundColor:function(_a){
if(_a!=undefined){
this.backgroundColor=_a;
}
if(this.div!=null){
if(this.contentDiv!=null){
this.div.style.background="transparent";
OpenLayers.Rico.Corner.changeColor(this.groupDiv,this.backgroundColor);
}
}
},setOpacity:function(_b){
OpenLayers.Popup.Anchored.prototype.setOpacity.call(this,_b);
if(this.div!=null){
if(this.groupDiv!=null){
OpenLayers.Rico.Corner.changeOpacity(this.groupDiv,this.opacity);
}
}
},setBorder:function(_c){
this.border=0;
},setRicoCorners:function(){
var _d=this.getCornersToRound(this.relativePosition);
var _e={corners:_d,color:this.backgroundColor,bgColor:"transparent",blend:false};
if(!this.rounded){
OpenLayers.Rico.Corner.round(this.div,_e);
this.rounded=true;
}else{
OpenLayers.Rico.Corner.reRound(this.groupDiv,_e);
this.setBackgroundColor();
this.setOpacity();
}
},getCornersToRound:function(){
var _f=["tl","tr","bl","br"];
var _10=OpenLayers.Bounds.oppositeQuadrant(this.relativePosition);
OpenLayers.Util.removeItem(_f,_10);
return _f.join(" ");
},CLASS_NAME:"OpenLayers.Popup.AnchoredBubble"});
OpenLayers.Popup.AnchoredBubble.CORNER_SIZE=5;

OpenLayers.Popup.Framed=OpenLayers.Class(OpenLayers.Popup.Anchored,{imageSrc:null,imageSize:null,isAlphaImage:false,positionBlocks:null,blocks:null,fixedRelativePosition:false,initialize:function(id,_2,_3,_4,_5,_6,_7){
OpenLayers.Popup.Anchored.prototype.initialize.apply(this,arguments);
if(this.fixedRelativePosition){
this.updateRelativePosition();
this.calculateRelativePosition=function(px){
return this.relativePosition;
};
}
this.contentDiv.style.position="absolute";
this.contentDiv.style.zIndex=1;
if(_6){
this.closeDiv.style.zIndex=1;
}
this.groupDiv.style.position="absolute";
this.groupDiv.style.top="0px";
this.groupDiv.style.left="0px";
this.groupDiv.style.height="100%";
this.groupDiv.style.width="100%";
},destroy:function(){
this.imageSrc=null;
this.imageSize=null;
this.isAlphaImage=null;
this.fixedRelativePosition=false;
this.positionBlocks=null;
for(var i=0;i<this.blocks.length;i++){
var _a=this.blocks[i];
if(_a.image){
_a.div.removeChild(_a.image);
}
_a.image=null;
if(_a.div){
this.groupDiv.removeChild(_a.div);
}
_a.div=null;
}
this.blocks=null;
OpenLayers.Popup.Anchored.prototype.destroy.apply(this,arguments);
},setBackgroundColor:function(_b){
},setBorder:function(){
},setOpacity:function(_c){
},setSize:function(_d){
OpenLayers.Popup.Anchored.prototype.setSize.apply(this,arguments);
this.updateBlocks();
},updateRelativePosition:function(){
this.padding=this.positionBlocks[this.relativePosition].padding;
if(this.closeDiv){
var _e=this.getContentDivPadding();
this.closeDiv.style.right=_e.right+this.padding.right+"px";
this.closeDiv.style.top=_e.top+this.padding.top+"px";
}
this.updateBlocks();
},calculateNewPx:function(px){
var _10=OpenLayers.Popup.Anchored.prototype.calculateNewPx.apply(this,arguments);
_10=_10.offset(this.positionBlocks[this.relativePosition].offset);
return _10;
},createBlocks:function(){
this.blocks=[];
var _11=null;
for(var key in this.positionBlocks){
_11=key;
break;
}
var _13=this.positionBlocks[_11];
for(var i=0;i<_13.blocks.length;i++){
var _15={};
this.blocks.push(_15);
var _16=this.id+"_FrameDecorationDiv_"+i;
_15.div=OpenLayers.Util.createDiv(_16,null,null,null,"absolute",null,"hidden",null);
var _17=this.id+"_FrameDecorationImg_"+i;
var _18=(this.isAlphaImage)?OpenLayers.Util.createAlphaImageDiv:OpenLayers.Util.createImage;
_15.image=_18(_17,null,this.imageSize,this.imageSrc,"absolute",null,null,null);
_15.div.appendChild(_15.image);
this.groupDiv.appendChild(_15.div);
}
},updateBlocks:function(){
if(!this.blocks){
this.createBlocks();
}
if(this.relativePosition){
var _19=this.positionBlocks[this.relativePosition];
for(var i=0;i<_19.blocks.length;i++){
var _1b=_19.blocks[i];
var _1c=this.blocks[i];
var l=_1b.anchor.left;
var b=_1b.anchor.bottom;
var r=_1b.anchor.right;
var t=_1b.anchor.top;
var w=(isNaN(_1b.size.w))?this.size.w-(r+l):_1b.size.w;
var h=(isNaN(_1b.size.h))?this.size.h-(b+t):_1b.size.h;
_1c.div.style.width=w+"px";
_1c.div.style.height=h+"px";
_1c.div.style.left=(l!=null)?l+"px":"";
_1c.div.style.bottom=(b!=null)?b+"px":"";
_1c.div.style.right=(r!=null)?r+"px":"";
_1c.div.style.top=(t!=null)?t+"px":"";
_1c.image.style.left=_1b.position.x+"px";
_1c.image.style.top=_1b.position.y+"px";
}
this.contentDiv.style.left=this.padding.left+"px";
this.contentDiv.style.top=this.padding.top+"px";
}
},CLASS_NAME:"OpenLayers.Popup.Framed"});

OpenLayers.Popup.FramedCloud=OpenLayers.Class(OpenLayers.Popup.Framed,{autoSize:true,panMapIfOutOfView:true,imageSize:new OpenLayers.Size(676,736),isAlphaImage:false,fixedRelativePosition:false,positionBlocks:{"tl":{"offset":new OpenLayers.Pixel(44,0),"padding":new OpenLayers.Bounds(8,40,8,9),"blocks":[{size:new OpenLayers.Size("auto","auto"),anchor:new OpenLayers.Bounds(0,51,22,0),position:new OpenLayers.Pixel(0,0)},{size:new OpenLayers.Size(22,"auto"),anchor:new OpenLayers.Bounds(null,50,0,0),position:new OpenLayers.Pixel(-638,0)},{size:new OpenLayers.Size("auto",21),anchor:new OpenLayers.Bounds(0,32,80,null),position:new OpenLayers.Pixel(0,-629)},{size:new OpenLayers.Size(22,21),anchor:new OpenLayers.Bounds(null,32,0,null),position:new OpenLayers.Pixel(-638,-629)},{size:new OpenLayers.Size(81,54),anchor:new OpenLayers.Bounds(null,0,0,null),position:new OpenLayers.Pixel(0,-668)}]},"tr":{"offset":new OpenLayers.Pixel(-45,0),"padding":new OpenLayers.Bounds(8,40,8,9),"blocks":[{size:new OpenLayers.Size("auto","auto"),anchor:new OpenLayers.Bounds(0,51,22,0),position:new OpenLayers.Pixel(0,0)},{size:new OpenLayers.Size(22,"auto"),anchor:new OpenLayers.Bounds(null,50,0,0),position:new OpenLayers.Pixel(-638,0)},{size:new OpenLayers.Size("auto",21),anchor:new OpenLayers.Bounds(0,32,22,null),position:new OpenLayers.Pixel(0,-629)},{size:new OpenLayers.Size(22,21),anchor:new OpenLayers.Bounds(null,32,0,null),position:new OpenLayers.Pixel(-638,-629)},{size:new OpenLayers.Size(81,54),anchor:new OpenLayers.Bounds(0,0,null,null),position:new OpenLayers.Pixel(-215,-668)}]},"bl":{"offset":new OpenLayers.Pixel(45,0),"padding":new OpenLayers.Bounds(8,9,8,40),"blocks":[{size:new OpenLayers.Size("auto","auto"),anchor:new OpenLayers.Bounds(0,21,22,32),position:new OpenLayers.Pixel(0,0)},{size:new OpenLayers.Size(22,"auto"),anchor:new OpenLayers.Bounds(null,21,0,32),position:new OpenLayers.Pixel(-638,0)},{size:new OpenLayers.Size("auto",21),anchor:new OpenLayers.Bounds(0,0,22,null),position:new OpenLayers.Pixel(0,-629)},{size:new OpenLayers.Size(22,21),anchor:new OpenLayers.Bounds(null,0,0,null),position:new OpenLayers.Pixel(-638,-629)},{size:new OpenLayers.Size(81,54),anchor:new OpenLayers.Bounds(null,null,0,0),position:new OpenLayers.Pixel(-101,-674)}]},"br":{"offset":new OpenLayers.Pixel(-44,0),"padding":new OpenLayers.Bounds(8,9,8,40),"blocks":[{size:new OpenLayers.Size("auto","auto"),anchor:new OpenLayers.Bounds(0,21,22,32),position:new OpenLayers.Pixel(0,0)},{size:new OpenLayers.Size(22,"auto"),anchor:new OpenLayers.Bounds(null,21,0,32),position:new OpenLayers.Pixel(-638,0)},{size:new OpenLayers.Size("auto",21),anchor:new OpenLayers.Bounds(0,0,22,null),position:new OpenLayers.Pixel(0,-629)},{size:new OpenLayers.Size(22,21),anchor:new OpenLayers.Bounds(null,0,0,null),position:new OpenLayers.Pixel(-638,-629)},{size:new OpenLayers.Size(81,54),anchor:new OpenLayers.Bounds(0,null,null,0),position:new OpenLayers.Pixel(-311,-674)}]}},minSize:new OpenLayers.Size(105,10),maxSize:new OpenLayers.Size(600,660),initialize:function(id,_2,_3,_4,_5,_6,_7){
this.imageSrc=OpenLayers.Util.getImagesLocation()+"cloud-popup-relative.png";
OpenLayers.Popup.Framed.prototype.initialize.apply(this,arguments);
this.contentDiv.className="olFramedCloudPopupContent";
},destroy:function(){
OpenLayers.Popup.Framed.prototype.destroy.apply(this,arguments);
},CLASS_NAME:"OpenLayers.Popup.FramedCloud"});

OpenLayers.Feature=OpenLayers.Class({layer:null,id:null,lonlat:null,data:null,marker:null,popupClass:OpenLayers.Popup.AnchoredBubble,popup:null,initialize:function(_1,_2,_3){
this.layer=_1;
this.lonlat=_2;
this.data=(_3!=null)?_3:{};
this.id=OpenLayers.Util.createUniqueID(this.CLASS_NAME+"_");
},destroy:function(){
if((this.layer!=null)&&(this.layer.map!=null)){
if(this.popup!=null){
this.layer.map.removePopup(this.popup);
}
}
this.layer=null;
this.id=null;
this.lonlat=null;
this.data=null;
if(this.marker!=null){
this.destroyMarker(this.marker);
this.marker=null;
}
if(this.popup!=null){
this.destroyPopup(this.popup);
this.popup=null;
}
},onScreen:function(){
var _4=false;
if((this.layer!=null)&&(this.layer.map!=null)){
var _5=this.layer.map.getExtent();
_4=_5.containsLonLat(this.lonlat);
}
return _4;
},createMarker:function(){
if(this.lonlat!=null){
this.marker=new OpenLayers.Marker(this.lonlat,this.data.icon);
}
return this.marker;
},destroyMarker:function(){
this.marker.destroy();
},createPopup:function(_6){
if(this.lonlat!=null){
var id=this.id+"_popup";
var _8=(this.marker)?this.marker.icon:null;
if(!this.popup){
this.popup=new this.popupClass(id,this.lonlat,this.data.popupSize,this.data.popupContentHTML,_8,_6);
}
if(this.data.overflow!=null){
this.popup.contentDiv.style.overflow=this.data.overflow;
}
this.popup.feature=this;
}
return this.popup;
},destroyPopup:function(){
if(this.popup){
this.popup.feature=null;
this.popup.destroy();
this.popup=null;
}
},CLASS_NAME:"OpenLayers.Feature"});

OpenLayers.State={UNKNOWN:"Unknown",INSERT:"Insert",UPDATE:"Update",DELETE:"Delete"};
OpenLayers.Feature.Vector=OpenLayers.Class(OpenLayers.Feature,{fid:null,geometry:null,attributes:null,state:null,style:null,renderIntent:"default",initialize:function(_1,_2,_3){
OpenLayers.Feature.prototype.initialize.apply(this,[null,null,_2]);
this.lonlat=null;
this.geometry=_1?_1:null;
this.state=null;
this.attributes={};
if(_2){
this.attributes=OpenLayers.Util.extend(this.attributes,_2);
}
this.style=_3?_3:null;
},destroy:function(){
if(this.layer){
this.layer.removeFeatures(this);
this.layer=null;
}
this.geometry=null;
OpenLayers.Feature.prototype.destroy.apply(this,arguments);
},clone:function(){
return new OpenLayers.Feature.Vector(this.geometry?this.geometry.clone():null,this.attributes,this.style);
},onScreen:function(_4){
var _5=false;
if(this.layer&&this.layer.map){
var _6=this.layer.map.getExtent();
if(_4){
var _7=this.geometry.getBounds();
_5=_6.intersectsBounds(_7);
}else{
var _8=_6.toGeometry();
_5=_8.intersects(this.geometry);
}
}
return _5;
},createMarker:function(){
return null;
},destroyMarker:function(){
},createPopup:function(){
return null;
},atPoint:function(_9,_a,_b){
var _c=false;
if(this.geometry){
_c=this.geometry.atPoint(_9,_a,_b);
}
return _c;
},destroyPopup:function(){
},toState:function(_d){
if(_d==OpenLayers.State.UPDATE){
switch(this.state){
case OpenLayers.State.UNKNOWN:
case OpenLayers.State.DELETE:
this.state=_d;
break;
case OpenLayers.State.UPDATE:
case OpenLayers.State.INSERT:
break;
}
}else{
if(_d==OpenLayers.State.INSERT){
switch(this.state){
case OpenLayers.State.UNKNOWN:
break;
default:
this.state=_d;
break;
}
}else{
if(_d==OpenLayers.State.DELETE){
switch(this.state){
case OpenLayers.State.INSERT:
break;
case OpenLayers.State.DELETE:
break;
case OpenLayers.State.UNKNOWN:
case OpenLayers.State.UPDATE:
this.state=_d;
break;
}
}else{
if(_d==OpenLayers.State.UNKNOWN){
this.state=_d;
}
}
}
}
},CLASS_NAME:"OpenLayers.Feature.Vector"});
OpenLayers.Feature.Vector.style={"default":{fillColor:"#ee9900",fillOpacity:0.4,hoverFillColor:"white",hoverFillOpacity:0.8,strokeColor:"#ee9900",strokeOpacity:1,strokeWidth:1,strokeLinecap:"round",hoverStrokeColor:"red",hoverStrokeOpacity:1,hoverStrokeWidth:0.2,pointRadius:6,hoverPointRadius:1,hoverPointUnit:"%",pointerEvents:"visiblePainted",cursor:"inherit"},"select":{fillColor:"blue",fillOpacity:0.4,hoverFillColor:"white",hoverFillOpacity:0.8,strokeColor:"blue",strokeOpacity:1,strokeWidth:2,strokeLinecap:"round",hoverStrokeColor:"red",hoverStrokeOpacity:1,hoverStrokeWidth:0.2,pointRadius:6,hoverPointRadius:1,hoverPointUnit:"%",pointerEvents:"visiblePainted",cursor:"pointer"},"temporary":{fillColor:"yellow",fillOpacity:0.2,hoverFillColor:"white",hoverFillOpacity:0.8,strokeColor:"yellow",strokeOpacity:1,strokeLinecap:"round",strokeWidth:4,hoverStrokeColor:"red",hoverStrokeOpacity:1,hoverStrokeWidth:0.2,pointRadius:6,hoverPointRadius:1,hoverPointUnit:"%",pointerEvents:"visiblePainted",cursor:"inherit"}};

OpenLayers.Feature.WFS=OpenLayers.Class(OpenLayers.Feature,{initialize:function(_1,_2){
var _3=arguments;
var _4=this.processXMLNode(_2);
_3=new Array(_1,_4.lonlat,_4);
OpenLayers.Feature.prototype.initialize.apply(this,_3);
this.createMarker();
this.layer.addMarker(this.marker);
},destroy:function(){
if(this.marker!=null){
this.layer.removeMarker(this.marker);
}
OpenLayers.Feature.prototype.destroy.apply(this,arguments);
},processXMLNode:function(_5){
var _6=OpenLayers.Ajax.getElementsByTagNameNS(_5,"http://www.opengis.net/gml","gml","Point");
var _7=OpenLayers.Util.getXmlNodeValue(OpenLayers.Ajax.getElementsByTagNameNS(_6[0],"http://www.opengis.net/gml","gml","coordinates")[0]);
var _8=_7.split(",");
return {lonlat:new OpenLayers.LonLat(parseFloat(_8[0]),parseFloat(_8[1])),id:null};
},CLASS_NAME:"OpenLayers.Feature.WFS"});

OpenLayers.Handler=OpenLayers.Class({id:null,control:null,map:null,keyMask:null,active:false,evt:null,initialize:function(_1,_2,_3){
OpenLayers.Util.extend(this,_3);
this.control=_1;
this.callbacks=_2;
if(_1.map){
this.setMap(_1.map);
}
OpenLayers.Util.extend(this,_3);
this.id=OpenLayers.Util.createUniqueID(this.CLASS_NAME+"_");
},setMap:function(_4){
this.map=_4;
},checkModifiers:function(_5){
if(this.keyMask==null){
return true;
}
var _6=(_5.shiftKey?OpenLayers.Handler.MOD_SHIFT:0)|(_5.ctrlKey?OpenLayers.Handler.MOD_CTRL:0)|(_5.altKey?OpenLayers.Handler.MOD_ALT:0);
return (_6==this.keyMask);
},activate:function(){
if(this.active){
return false;
}
var _7=OpenLayers.Events.prototype.BROWSER_EVENTS;
for(var i=0;i<_7.length;i++){
if(this[_7[i]]){
this.register(_7[i],this[_7[i]]);
}
}
this.active=true;
return true;
},deactivate:function(){
if(!this.active){
return false;
}
var _9=OpenLayers.Events.prototype.BROWSER_EVENTS;
for(var i=0;i<_9.length;i++){
if(this[_9[i]]){
this.unregister(_9[i],this[_9[i]]);
}
}
this.active=false;
return true;
},callback:function(_b,_c){
if(_b&&this.callbacks[_b]){
this.callbacks[_b].apply(this.control,_c);
}
},register:function(_d,_e){
this.map.events.registerPriority(_d,this,_e);
this.map.events.registerPriority(_d,this,this.setEvent);
},unregister:function(_f,_10){
this.map.events.unregister(_f,this,_10);
this.map.events.unregister(_f,this,this.setEvent);
},setEvent:function(evt){
this.evt=evt;
return true;
},destroy:function(){
this.deactivate();
this.control=this.map=null;
},CLASS_NAME:"OpenLayers.Handler"});
OpenLayers.Handler.MOD_NONE=0;
OpenLayers.Handler.MOD_SHIFT=1;
OpenLayers.Handler.MOD_CTRL=2;
OpenLayers.Handler.MOD_ALT=4;

OpenLayers.Handler.Click=OpenLayers.Class(OpenLayers.Handler,{delay:300,single:true,"double":false,pixelTolerance:0,stopSingle:false,stopDouble:false,timerId:null,down:null,initialize:function(_1,_2,_3){
OpenLayers.Handler.prototype.initialize.apply(this,arguments);
if(this.pixelTolerance!=null){
this.mousedown=function(_4){
this.down=_4.xy;
return true;
};
}
},mousedown:null,dblclick:function(_5){
if(this.passesTolerance(_5)){
if(this["double"]){
this.callback("dblclick",[_5]);
}
this.clearTimer();
}
return !this.stopDouble;
},click:function(_6){
if(this.passesTolerance(_6)){
if(this.timerId!=null){
this.clearTimer();
}else{
var _7=this.single?OpenLayers.Util.extend({},_6):null;
this.timerId=window.setTimeout(OpenLayers.Function.bind(this.delayedCall,this,_7),this.delay);
}
}
return !this.stopSingle;
},passesTolerance:function(_8){
var _9=true;
if(this.pixelTolerance!=null&&this.down){
var _a=Math.sqrt(Math.pow(this.down.x-_8.xy.x,2)+Math.pow(this.down.y-_8.xy.y,2));
if(_a>this.pixelTolerance){
_9=false;
}
}
return _9;
},clearTimer:function(){
if(this.timerId!=null){
window.clearTimeout(this.timerId);
this.timerId=null;
}
},delayedCall:function(_b){
this.timerId=null;
if(_b){
this.callback("click",[_b]);
}
},deactivate:function(){
var _c=false;
if(OpenLayers.Handler.prototype.deactivate.apply(this,arguments)){
this.clearTimer();
this.down=null;
_c=true;
}
return _c;
},CLASS_NAME:"OpenLayers.Handler.Click"});

OpenLayers.Handler.Hover=OpenLayers.Class(OpenLayers.Handler,{delay:500,pixelTolerance:null,stopMove:false,px:null,timerId:null,initialize:function(_1,_2,_3){
OpenLayers.Handler.prototype.initialize.apply(this,arguments);
},mousemove:function(_4){
if(this.passesTolerance(_4.xy)){
this.clearTimer();
this.callback("move",[_4]);
this.px=_4.xy;
_4=OpenLayers.Util.extend({},_4);
this.timerId=window.setTimeout(OpenLayers.Function.bind(this.delayedCall,this,_4),this.delay);
}
return !this.stopMove;
},mouseout:function(_5){
if(OpenLayers.Util.mouseLeft(_5,this.map.div)){
this.clearTimer();
this.callback("move",[_5]);
}
return true;
},passesTolerance:function(px){
var _7=true;
if(this.pixelTolerance&&this.px){
var _8=Math.sqrt(Math.pow(this.px.x-px.x,2)+Math.pow(this.px.y-px.y,2));
if(_8<this.pixelTolerance){
_7=false;
}
}
return _7;
},clearTimer:function(){
if(this.timerId!=null){
window.clearTimeout(this.timerId);
this.timerId=null;
}
},delayedCall:function(_9){
this.callback("pause",[_9]);
},deactivate:function(){
var _a=false;
if(OpenLayers.Handler.prototype.deactivate.apply(this,arguments)){
this.clearTimer();
_a=true;
}
return _a;
},CLASS_NAME:"OpenLayers.Handler.Hover"});

OpenLayers.Handler.Point=OpenLayers.Class(OpenLayers.Handler,{point:null,layer:null,drawing:false,mouseDown:false,lastDown:null,lastUp:null,initialize:function(_1,_2,_3){
this.style=OpenLayers.Util.extend(OpenLayers.Feature.Vector.style["default"],{});
OpenLayers.Handler.prototype.initialize.apply(this,arguments);
},activate:function(){
if(!OpenLayers.Handler.prototype.activate.apply(this,arguments)){
return false;
}
var _4={displayInLayerSwitcher:false,calculateInRange:function(){
return true;
}};
this.layer=new OpenLayers.Layer.Vector(this.CLASS_NAME,_4);
this.map.addLayer(this.layer);
return true;
},createFeature:function(){
this.point=new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point());
},deactivate:function(){
if(!OpenLayers.Handler.prototype.deactivate.apply(this,arguments)){
return false;
}
if(this.drawing){
this.cancel();
}
if(this.layer.map!=null){
this.layer.destroy(false);
}
this.layer=null;
return true;
},destroyFeature:function(){
if(this.point){
this.point.destroy();
}
this.point=null;
},finalize:function(){
this.layer.renderer.clear();
this.drawing=false;
this.mouseDown=false;
this.lastDown=null;
this.lastUp=null;
this.callback("done",[this.geometryClone()]);
this.destroyFeature();
},cancel:function(){
this.layer.renderer.clear();
this.drawing=false;
this.mouseDown=false;
this.lastDown=null;
this.lastUp=null;
this.callback("cancel",[this.geometryClone()]);
this.destroyFeature();
},click:function(_5){
OpenLayers.Event.stop(_5);
return false;
},dblclick:function(_6){
OpenLayers.Event.stop(_6);
return false;
},drawFeature:function(){
this.layer.drawFeature(this.point,this.style);
},geometryClone:function(){
return this.point.geometry.clone();
},mousedown:function(_7){
if(!this.checkModifiers(_7)){
return true;
}
if(this.lastDown&&this.lastDown.equals(_7.xy)){
return true;
}
if(this.lastDown==null){
this.createFeature();
}
this.lastDown=_7.xy;
this.drawing=true;
var _8=this.map.getLonLatFromPixel(_7.xy);
this.point.geometry.x=_8.lon;
this.point.geometry.y=_8.lat;
this.drawFeature();
return false;
},mousemove:function(_9){
if(this.drawing){
var _a=this.map.getLonLatFromPixel(_9.xy);
this.point.geometry.x=_a.lon;
this.point.geometry.y=_a.lat;
this.point.geometry.clearBounds();
this.drawFeature();
}
return true;
},mouseup:function(_b){
if(this.drawing){
this.finalize();
return false;
}else{
return true;
}
},CLASS_NAME:"OpenLayers.Handler.Point"});

OpenLayers.Handler.Path=OpenLayers.Class(OpenLayers.Handler.Point,{line:null,freehand:false,freehandToggle:"shiftKey",initialize:function(_1,_2,_3){
OpenLayers.Handler.Point.prototype.initialize.apply(this,arguments);
},createFeature:function(){
this.line=new OpenLayers.Feature.Vector(new OpenLayers.Geometry.LineString());
this.point=new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point());
},destroyFeature:function(){
OpenLayers.Handler.Point.prototype.destroyFeature.apply(this);
if(this.line){
this.line.destroy();
}
this.line=null;
},addPoint:function(){
this.line.geometry.addComponent(this.point.geometry.clone(),this.line.geometry.components.length);
this.callback("point",[this.point.geometry]);
},freehandMode:function(_4){
return (this.freehandToggle&&_4[this.freehandToggle])?!this.freehand:this.freehand;
},modifyFeature:function(){
var _5=this.line.geometry.components.length-1;
this.line.geometry.components[_5].x=this.point.geometry.x;
this.line.geometry.components[_5].y=this.point.geometry.y;
this.line.geometry.components[_5].clearBounds();
},drawFeature:function(){
this.layer.drawFeature(this.line,this.style);
this.layer.drawFeature(this.point,this.style);
},geometryClone:function(){
return this.line.geometry.clone();
},mousedown:function(_6){
if(this.lastDown&&this.lastDown.equals(_6.xy)){
return false;
}
if(this.lastDown==null){
this.createFeature();
}
this.mouseDown=true;
this.lastDown=_6.xy;
var _7=this.control.map.getLonLatFromPixel(_6.xy);
this.point.geometry.x=_7.lon;
this.point.geometry.y=_7.lat;
if((this.lastUp==null)||!this.lastUp.equals(_6.xy)){
this.addPoint();
}
this.drawFeature();
this.drawing=true;
return false;
},mousemove:function(_8){
if(this.drawing){
var _9=this.map.getLonLatFromPixel(_8.xy);
this.point.geometry.x=_9.lon;
this.point.geometry.y=_9.lat;
if(this.mouseDown&&this.freehandMode(_8)){
this.addPoint();
}else{
this.modifyFeature();
}
this.drawFeature();
}
return true;
},mouseup:function(_a){
this.mouseDown=false;
if(this.drawing){
if(this.freehandMode(_a)){
this.finalize();
}else{
if(this.lastUp==null){
this.addPoint();
}
this.lastUp=_a.xy;
}
return false;
}
return true;
},dblclick:function(_b){
if(!this.freehandMode(_b)){
var _c=this.line.geometry.components.length-1;
this.line.geometry.removeComponent(this.line.geometry.components[_c]);
this.finalize();
}
return false;
},CLASS_NAME:"OpenLayers.Handler.Path"});

OpenLayers.Handler.Polygon=OpenLayers.Class(OpenLayers.Handler.Path,{polygon:null,initialize:function(_1,_2,_3){
OpenLayers.Handler.Path.prototype.initialize.apply(this,arguments);
},createFeature:function(){
this.polygon=new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Polygon());
this.line=new OpenLayers.Feature.Vector(new OpenLayers.Geometry.LinearRing());
this.polygon.geometry.addComponent(this.line.geometry);
this.point=new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point());
},destroyFeature:function(){
OpenLayers.Handler.Path.prototype.destroyFeature.apply(this);
if(this.polygon){
this.polygon.destroy();
}
this.polygon=null;
},modifyFeature:function(){
var _4=this.line.geometry.components.length-2;
this.line.geometry.components[_4].x=this.point.geometry.x;
this.line.geometry.components[_4].y=this.point.geometry.y;
this.line.geometry.components[_4].clearBounds();
},drawFeature:function(){
this.layer.drawFeature(this.polygon,this.style);
this.layer.drawFeature(this.point,this.style);
},geometryClone:function(){
return this.polygon.geometry.clone();
},dblclick:function(_5){
if(!this.freehandMode(_5)){
var _6=this.line.geometry.components.length-2;
this.line.geometry.removeComponent(this.line.geometry.components[_6]);
this.finalize();
}
return false;
},CLASS_NAME:"OpenLayers.Handler.Polygon"});

OpenLayers.Handler.Feature=OpenLayers.Class(OpenLayers.Handler,{EVENTMAP:{"click":{"in":"click","out":"clickout"},"mousemove":{"in":"over","out":"out"},"dblclick":{"in":"dblclick","out":null},"mousedown":{"in":null,"out":null},"mouseup":{"in":null,"out":null}},feature:null,lastFeature:null,down:null,up:null,clickoutTolerance:4,geometryTypes:null,stopClick:true,stopDown:true,stopUp:true,layerIndex:null,initialize:function(_1,_2,_3,_4){
OpenLayers.Handler.prototype.initialize.apply(this,[_1,_3,_4]);
this.layer=_2;
},mousedown:function(_5){
this.down=_5.xy;
return this.handle(_5)?!this.stopDown:true;
},mouseup:function(_6){
this.up=_6.xy;
return this.handle(_6)?!this.stopUp:true;
},click:function(_7){
return this.handle(_7)?!this.stopClick:true;
},mousemove:function(_8){
this.handle(_8);
return true;
},dblclick:function(_9){
return !this.handle(_9);
},geometryTypeMatches:function(_a){
return this.geometryTypes==null||OpenLayers.Util.indexOf(this.geometryTypes,_a.geometry.CLASS_NAME)>-1;
},handle:function(_b){
var _c=_b.type;
var _d=false;
var _e=!!(this.feature);
var _f=(_c=="click"||_c=="dblclick");
this.feature=this.layer.getFeatureFromEvent(_b);
if(this.feature){
var _10=(this.feature!=this.lastFeature);
if(this.geometryTypeMatches(this.feature)){
if(_e&&_10){
this.triggerCallback(_c,"out",[this.lastFeature]);
this.triggerCallback(_c,"in",[this.feature]);
}else{
if(!_e||_f){
this.triggerCallback(_c,"in",[this.feature]);
}
}
this.lastFeature=this.feature;
_d=true;
}else{
if(_e&&_10||(_f&&this.lastFeature)){
this.triggerCallback(_c,"out",[this.lastFeature]);
}
this.feature=null;
}
}else{
if(_e||(_f&&this.lastFeature)){
this.triggerCallback(_c,"out",[this.lastFeature]);
}
}
return _d;
},triggerCallback:function(_11,_12,_13){
var key=this.EVENTMAP[_11][_12];
if(key){
if(_11=="click"&&_12=="out"&&this.up&&this.down){
var dpx=Math.sqrt(Math.pow(this.up.x-this.down.x,2)+Math.pow(this.up.y-this.down.y,2));
if(dpx<=this.clickoutTolerance){
this.callback(key,_13);
}
}else{
this.callback(key,_13);
}
}
},activate:function(){
var _16=false;
if(OpenLayers.Handler.prototype.activate.apply(this,arguments)){
this.layerIndex=this.layer.div.style.zIndex;
this.layer.div.style.zIndex=this.map.Z_INDEX_BASE["Popup"]-1;
_16=true;
}
return _16;
},deactivate:function(){
var _17=false;
if(OpenLayers.Handler.prototype.deactivate.apply(this,arguments)){
if(this.layer&&this.layer.div){
this.layer.div.style.zIndex=this.layerIndex;
}
this.feature=null;
this.lastFeature=null;
this.down=null;
this.up=null;
_17=true;
}
return _17;
},CLASS_NAME:"OpenLayers.Handler.Feature"});

OpenLayers.Handler.Drag=OpenLayers.Class(OpenLayers.Handler,{started:false,stopDown:true,dragging:false,last:null,start:null,oldOnselectstart:null,initialize:function(_1,_2,_3){
OpenLayers.Handler.prototype.initialize.apply(this,arguments);
},down:function(_4){
},move:function(_5){
},up:function(_6){
},out:function(_7){
},mousedown:function(_8){
var _9=true;
this.dragging=false;
if(this.checkModifiers(_8)&&OpenLayers.Event.isLeftClick(_8)){
this.started=true;
this.start=_8.xy;
this.last=_8.xy;
this.map.div.style.cursor="move";
this.down(_8);
this.callback("down",[_8.xy]);
OpenLayers.Event.stop(_8);
if(!this.oldOnselectstart){
this.oldOnselectstart=(document.onselectstart)?document.onselectstart:function(){
return true;
};
document.onselectstart=function(){
return false;
};
}
_9=!this.stopDown;
}else{
this.started=false;
this.start=null;
this.last=null;
}
return _9;
},mousemove:function(_a){
if(this.started){
if(_a.xy.x!=this.last.x||_a.xy.y!=this.last.y){
this.dragging=true;
this.move(_a);
this.callback("move",[_a.xy]);
if(!this.oldOnselectstart){
this.oldOnselectstart=document.onselectstart;
document.onselectstart=function(){
return false;
};
}
this.last=_a.xy;
}
}
return true;
},mouseup:function(_b){
if(this.started){
var _c=(this.start!=this.last);
this.started=false;
this.dragging=false;
this.map.div.style.cursor="";
this.up(_b);
this.callback("up",[_b.xy]);
if(_c){
this.callback("done",[_b.xy]);
}
document.onselectstart=this.oldOnselectstart;
}
return true;
},mouseout:function(_d){
if(this.started&&OpenLayers.Util.mouseLeft(_d,this.map.div)){
var _e=(this.start!=this.last);
this.started=false;
this.dragging=false;
this.map.div.style.cursor="";
this.out(_d);
this.callback("out",[]);
if(_e){
this.callback("done",[_d.xy]);
}
if(document.onselectstart){
document.onselectstart=this.oldOnselectstart;
}
}
return true;
},click:function(_f){
return (this.start==this.last);
},activate:function(){
var _10=false;
if(OpenLayers.Handler.prototype.activate.apply(this,arguments)){
this.dragging=false;
_10=true;
}
return _10;
},deactivate:function(){
var _11=false;
if(OpenLayers.Handler.prototype.deactivate.apply(this,arguments)){
this.started=false;
this.dragging=false;
this.start=null;
this.last=null;
_11=true;
}
return _11;
},CLASS_NAME:"OpenLayers.Handler.Drag"});

OpenLayers.Handler.RegularPolygon=OpenLayers.Class(OpenLayers.Handler.Drag,{sides:4,radius:null,snapAngle:null,snapToggle:"shiftKey",persist:false,irregular:false,angle:null,fixedRadius:false,feature:null,layer:null,origin:null,initialize:function(_1,_2,_3){
this.style=OpenLayers.Util.extend(OpenLayers.Feature.Vector.style["default"],{});
OpenLayers.Handler.prototype.initialize.apply(this,[_1,_2,_3]);
this.options=(_3)?_3:new Object();
},setOptions:function(_4){
OpenLayers.Util.extend(this.options,_4);
OpenLayers.Util.extend(this,_4);
},activate:function(){
var _5=false;
if(OpenLayers.Handler.prototype.activate.apply(this,arguments)){
var _6={displayInLayerSwitcher:false};
this.layer=new OpenLayers.Layer.Vector(this.CLASS_NAME,_6);
this.map.addLayer(this.layer);
_5=true;
}
return _5;
},deactivate:function(){
var _7=false;
if(OpenLayers.Handler.Drag.prototype.deactivate.apply(this,arguments)){
if(this.dragging){
this.cancel();
}
if(this.layer.map!=null){
this.layer.destroy(false);
if(this.feature){
this.feature.destroy();
}
}
this.layer=null;
this.feature=null;
_7=true;
}
return _7;
},down:function(_8){
this.fixedRadius=!!(this.radius);
var _9=this.map.getLonLatFromPixel(_8.xy);
this.origin=new OpenLayers.Geometry.Point(_9.lon,_9.lat);
if(!this.fixedRadius||this.irregular){
this.radius=this.map.getResolution();
}
if(this.persist){
this.clear();
}
this.feature=new OpenLayers.Feature.Vector();
this.createGeometry();
this.layer.addFeatures([this.feature],{silent:true});
this.layer.drawFeature(this.feature,this.style);
},move:function(_a){
var _b=this.map.getLonLatFromPixel(_a.xy);
var _c=new OpenLayers.Geometry.Point(_b.lon,_b.lat);
if(this.irregular){
var ry=Math.sqrt(2)*Math.abs(_c.y-this.origin.y)/2;
this.radius=Math.max(this.map.getResolution()/2,ry);
}else{
if(this.fixedRadius){
this.origin=_c;
}else{
this.calculateAngle(_c,_a);
this.radius=Math.max(this.map.getResolution()/2,_c.distanceTo(this.origin));
}
}
this.modifyGeometry();
if(this.irregular){
var dx=_c.x-this.origin.x;
var dy=_c.y-this.origin.y;
var _10;
if(dy==0){
_10=dx/(this.radius*Math.sqrt(2));
}else{
_10=dx/dy;
}
this.feature.geometry.resize(1,this.origin,_10);
this.feature.geometry.move(dx/2,dy/2);
}
this.layer.drawFeature(this.feature,this.style);
},up:function(evt){
this.finalize();
},out:function(evt){
this.finalize();
},createGeometry:function(){
this.angle=Math.PI*((1/this.sides)-(1/2));
if(this.snapAngle){
this.angle+=this.snapAngle*(Math.PI/180);
}
this.feature.geometry=OpenLayers.Geometry.Polygon.createRegularPolygon(this.origin,this.radius,this.sides,this.snapAngle);
},modifyGeometry:function(){
var _13,dx,dy,point;
var _14=this.feature.geometry.components[0];
if(_14.components.length!=(this.sides+1)){
this.createGeometry();
_14=this.feature.geometry.components[0];
}
for(var i=0;i<this.sides;++i){
point=_14.components[i];
_13=this.angle+(i*2*Math.PI/this.sides);
point.x=this.origin.x+(this.radius*Math.cos(_13));
point.y=this.origin.y+(this.radius*Math.sin(_13));
point.clearBounds();
}
},calculateAngle:function(_16,evt){
var _18=Math.atan2(_16.y-this.origin.y,_16.x-this.origin.x);
if(this.snapAngle&&(this.snapToggle&&!evt[this.snapToggle])){
var _19=(Math.PI/180)*this.snapAngle;
this.angle=Math.round(_18/_19)*_19;
}else{
this.angle=_18;
}
},cancel:function(){
this.callback("cancel",null);
this.finalize();
},finalize:function(){
this.origin=null;
this.radius=this.options.radius;
},clear:function(){
this.layer.renderer.clear();
this.layer.destroyFeatures();
},callback:function(_1a,_1b){
if(this.callbacks[_1a]){
this.callbacks[_1a].apply(this.control,[this.feature.geometry.clone()]);
}
if(!this.persist&&(_1a=="done"||_1a=="cancel")){
this.clear();
}
},CLASS_NAME:"OpenLayers.Handler.RegularPolygon"});

OpenLayers.Handler.Box=OpenLayers.Class(OpenLayers.Handler,{dragHandler:null,boxDivClassName:"olHandlerBoxZoomBox",initialize:function(_1,_2,_3){
OpenLayers.Handler.prototype.initialize.apply(this,arguments);
var _2={"down":this.startBox,"move":this.moveBox,"out":this.removeBox,"up":this.endBox};
this.dragHandler=new OpenLayers.Handler.Drag(this,_2,{keyMask:this.keyMask});
},setMap:function(_4){
OpenLayers.Handler.prototype.setMap.apply(this,arguments);
if(this.dragHandler){
this.dragHandler.setMap(_4);
}
},startBox:function(xy){
this.zoomBox=OpenLayers.Util.createDiv("zoomBox",this.dragHandler.start);
this.zoomBox.className=this.boxDivClassName;
this.zoomBox.style.zIndex=this.map.Z_INDEX_BASE["Popup"]-1;
this.map.viewPortDiv.appendChild(this.zoomBox);
this.map.div.style.cursor="crosshair";
},moveBox:function(xy){
var _7=this.dragHandler.start.x;
var _8=this.dragHandler.start.y;
var _9=Math.abs(_7-xy.x);
var _a=Math.abs(_8-xy.y);
this.zoomBox.style.width=Math.max(1,_9)+"px";
this.zoomBox.style.height=Math.max(1,_a)+"px";
this.zoomBox.style.left=xy.x<_7?xy.x+"px":_7+"px";
this.zoomBox.style.top=xy.y<_8?xy.y+"px":_8+"px";
},endBox:function(_b){
var _c;
if(Math.abs(this.dragHandler.start.x-_b.x)>5||Math.abs(this.dragHandler.start.y-_b.y)>5){
var _d=this.dragHandler.start;
var _e=Math.min(_d.y,_b.y);
var _f=Math.max(_d.y,_b.y);
var _10=Math.min(_d.x,_b.x);
var _11=Math.max(_d.x,_b.x);
_c=new OpenLayers.Bounds(_10,_f,_11,_e);
}else{
_c=this.dragHandler.start.clone();
}
this.removeBox();
this.map.div.style.cursor="";
this.callback("done",[_c]);
},removeBox:function(){
this.map.viewPortDiv.removeChild(this.zoomBox);
this.zoomBox=null;
},activate:function(){
if(OpenLayers.Handler.prototype.activate.apply(this,arguments)){
this.dragHandler.activate();
return true;
}else{
return false;
}
},deactivate:function(){
if(OpenLayers.Handler.prototype.deactivate.apply(this,arguments)){
this.dragHandler.deactivate();
return true;
}else{
return false;
}
},CLASS_NAME:"OpenLayers.Handler.Box"});

OpenLayers.Handler.MouseWheel=OpenLayers.Class(OpenLayers.Handler,{wheelListener:null,mousePosition:null,initialize:function(_1,_2,_3){
OpenLayers.Handler.prototype.initialize.apply(this,arguments);
this.wheelListener=OpenLayers.Function.bindAsEventListener(this.onWheelEvent,this);
},destroy:function(){
OpenLayers.Handler.prototype.destroy.apply(this,arguments);
this.wheelListener=null;
},onWheelEvent:function(e){
if(!this.map||!this.checkModifiers(e)){
return;
}
var _5=false;
var _6=false;
var _7=false;
var _8=OpenLayers.Event.element(e);
while((_8!=null)&&!_7&&!_5){
if(!_5){
try{
if(_8.currentStyle){
overflow=_8.currentStyle["overflow"];
}else{
var _9=document.defaultView.getComputedStyle(_8,null);
var _a=_9.getPropertyValue("overflow");
}
_5=(_a&&(_a=="auto")||(_a=="scroll"));
}
catch(err){
}
}
if(!_6){
var _b=this.map.div.getElementsByClassName("layerDiv");
if(_b){
for(var i=0;i<_b.length;i++){
if(_8==_b[i]){
_6=true;
break;
}
}
}
}
_7=(_8==this.map.div);
_8=_8.parentNode;
}
if(!_5&&_7){
if(_6){
this.wheelZoom(e);
}
OpenLayers.Event.stop(e);
}
},wheelZoom:function(e){
var _e=0;
if(!e){
e=window.event;
}
if(e.wheelDelta){
_e=e.wheelDelta/120;
if(window.opera&&window.opera.version()<9.2){
_e=-_e;
}
}else{
if(e.detail){
_e=-e.detail/3;
}
}
if(_e){
if(this.mousePosition){
e.xy=this.mousePosition;
}
if(!e.xy){
e.xy=this.map.getPixelFromLonLat(this.map.getCenter());
}
if(_e<0){
this.callback("down",[e,_e]);
}else{
this.callback("up",[e,_e]);
}
}
},mousemove:function(_f){
this.mousePosition=_f.xy;
},activate:function(evt){
if(OpenLayers.Handler.prototype.activate.apply(this,arguments)){
var _11=this.wheelListener;
OpenLayers.Event.observe(window,"DOMMouseScroll",_11);
OpenLayers.Event.observe(window,"mousewheel",_11);
OpenLayers.Event.observe(document,"mousewheel",_11);
return true;
}else{
return false;
}
},deactivate:function(evt){
if(OpenLayers.Handler.prototype.deactivate.apply(this,arguments)){
var _13=this.wheelListener;
OpenLayers.Event.stopObserving(window,"DOMMouseScroll",_13);
OpenLayers.Event.stopObserving(window,"mousewheel",_13);
OpenLayers.Event.stopObserving(document,"mousewheel",_13);
return true;
}else{
return false;
}
},CLASS_NAME:"OpenLayers.Handler.MouseWheel"});

OpenLayers.Handler.Keyboard=OpenLayers.Class(OpenLayers.Handler,{KEY_EVENTS:["keydown","keypress","keyup"],eventListener:null,initialize:function(_1,_2,_3){
OpenLayers.Handler.prototype.initialize.apply(this,arguments);
this.eventListener=OpenLayers.Function.bindAsEventListener(this.handleKeyEvent,this);
},destroy:function(){
this.deactivate();
this.eventListener=null;
OpenLayers.Handler.prototype.destroy.apply(this,arguments);
},activate:function(){
if(OpenLayers.Handler.prototype.activate.apply(this,arguments)){
for(var i=0;i<this.KEY_EVENTS.length;i++){
OpenLayers.Event.observe(window,this.KEY_EVENTS[i],this.eventListener);
}
return true;
}else{
return false;
}
},deactivate:function(){
var _5=false;
if(OpenLayers.Handler.prototype.deactivate.apply(this,arguments)){
for(var i=0;i<this.KEY_EVENTS.length;i++){
OpenLayers.Event.stopObserving(window,this.KEY_EVENTS[i],this.eventListener);
}
_5=true;
}
return _5;
},handleKeyEvent:function(_7){
if(this.checkModifiers(_7)){
this.callback(_7.type,[_7.charCode||_7.keyCode]);
}
},CLASS_NAME:"OpenLayers.Handler.Keyboard"});

OpenLayers.Control=OpenLayers.Class({id:null,map:null,div:null,type:null,allowSelection:false,displayClass:"",title:"",active:null,empty:true,handler:null,eventListeners:null,events:null,EVENT_TYPES:["activate","deactivate"],initialize:function(_1){
this.displayClass=this.CLASS_NAME.replace("OpenLayers.","ol").replace(/\./g,"");
OpenLayers.Util.extend(this,_1);
this.events=new OpenLayers.Events(this,null,this.EVENT_TYPES);
if(this.eventListeners instanceof Object){
this.events.on(this.eventListeners);
}
this.id=OpenLayers.Util.createUniqueID(this.CLASS_NAME+"_");
},destroy:function(){
if(this.events){
if(this.eventListeners){
this.events.un(this.eventListeners);
}
this.events.destroy();
this.events=null;
}
this.eventListeners=null;
if(this.handler){
this.handler.destroy();
this.handler=null;
}
if(this.handlers){
for(var _2 in this.handlers){
if(this.handlers.hasOwnProperty(_2)&&typeof this.handlers[_2].destroy=="function"){
this.handlers[_2].destroy();
}
}
this.handlers=null;
}
if(this.map){
this.map.removeControl(this);
this.map=null;
}
},setMap:function(_3){
this.map=_3;
if(this.handler){
this.handler.setMap(_3);
}
},draw:function(px){
if(this.div==null){
this.div=OpenLayers.Util.createDiv(this.id);
this.div.className=this.displayClass;
if(!this.allowSelection){
this.div.className+=" olControlNoSelect";
this.div.setAttribute("unselectable","on",0);
this.div.onselectstart=function(){
return (false);
};
}
if(this.title!=""){
this.div.title=this.title;
}
}
if(px!=null){
this.position=px.clone();
}
this.moveTo(this.position);
return this.div;
},moveTo:function(px){
if((px!=null)&&(this.div!=null)){
this.div.style.left=px.x+"px";
this.div.style.top=px.y+"px";
}
},activate:function(){
if(this.active){
return false;
}
if(this.handler){
this.handler.activate();
}
this.active=true;
this.events.triggerEvent("activate");
return true;
},deactivate:function(){
if(this.active){
if(this.handler){
this.handler.deactivate();
}
this.active=false;
this.events.triggerEvent("deactivate");
return true;
}
return false;
},CLASS_NAME:"OpenLayers.Control"});
OpenLayers.Control.TYPE_BUTTON=1;
OpenLayers.Control.TYPE_TOGGLE=2;
OpenLayers.Control.TYPE_TOOL=3;

OpenLayers.Control.Attribution=OpenLayers.Class(OpenLayers.Control,{separator:", ",initialize:function(_1){
OpenLayers.Control.prototype.initialize.apply(this,arguments);
},destroy:function(){
this.map.events.un({"removelayer":this.updateAttribution,"addlayer":this.updateAttribution,"changelayer":this.updateAttribution,"changebaselayer":this.updateAttribution,scope:this});
OpenLayers.Control.prototype.destroy.apply(this,arguments);
},draw:function(){
OpenLayers.Control.prototype.draw.apply(this,arguments);
this.map.events.on({"changebaselayer":this.updateAttribution,"changelayer":this.updateAttribution,"addlayer":this.updateAttribution,"removelayer":this.updateAttribution,scope:this});
this.updateAttribution();
return this.div;
},updateAttribution:function(){
var _2=[];
if(this.map&&this.map.layers){
for(var i=0;i<this.map.layers.length;i++){
var _4=this.map.layers[i];
if(_4.attribution&&_4.getVisibility()){
_2.push(_4.attribution);
}
}
this.div.innerHTML=_2.join(this.separator);
}
},CLASS_NAME:"OpenLayers.Control.Attribution"});

OpenLayers.Control.Button=OpenLayers.Class(OpenLayers.Control,{type:OpenLayers.Control.TYPE_BUTTON,trigger:function(){
},CLASS_NAME:"OpenLayers.Control.Button"});

OpenLayers.Control.ZoomBox=OpenLayers.Class(OpenLayers.Control,{type:OpenLayers.Control.TYPE_TOOL,out:false,draw:function(){
this.handler=new OpenLayers.Handler.Box(this,{done:this.zoomBox},{keyMask:this.keyMask});
},zoomBox:function(_1){
if(_1 instanceof OpenLayers.Bounds){
if(!this.out){
var _2=this.map.getLonLatFromPixel(new OpenLayers.Pixel(_1.left,_1.bottom));
var _3=this.map.getLonLatFromPixel(new OpenLayers.Pixel(_1.right,_1.top));
var _4=new OpenLayers.Bounds(_2.lon,_2.lat,_3.lon,_3.lat);
}else{
var _5=Math.abs(_1.right-_1.left);
var _6=Math.abs(_1.top-_1.bottom);
var _7=Math.min((this.map.size.h/_6),(this.map.size.w/_5));
var _8=this.map.getExtent();
var _9=this.map.getLonLatFromPixel(_1.getCenterPixel());
var _a=_9.lon-(_8.getWidth()/2)*_7;
var _b=_9.lon+(_8.getWidth()/2)*_7;
var _c=_9.lat-(_8.getHeight()/2)*_7;
var _d=_9.lat+(_8.getHeight()/2)*_7;
var _4=new OpenLayers.Bounds(_a,_c,_b,_d);
}
this.map.zoomToExtent(_4);
}else{
if(!this.out){
this.map.setCenter(this.map.getLonLatFromPixel(_1),this.map.getZoom()+1);
}else{
this.map.setCenter(this.map.getLonLatFromPixel(_1),this.map.getZoom()-1);
}
}
},CLASS_NAME:"OpenLayers.Control.ZoomBox"});

OpenLayers.Control.ZoomBoxOut=OpenLayers.Class(OpenLayers.Control.ZoomBox,{out:true,CLASS_NAME:"OpenLayers.Control.ZoomBoxOut"});

OpenLayers.Control.ZoomToMaxExtent=OpenLayers.Class(OpenLayers.Control,{type:OpenLayers.Control.TYPE_BUTTON,trigger:function(){
if(this.map){
this.map.zoomToMaxExtent();
}
},CLASS_NAME:"OpenLayers.Control.ZoomToMaxExtent"});

OpenLayers.Control.DragPan=OpenLayers.Class(OpenLayers.Control,{type:OpenLayers.Control.TYPE_TOOL,panned:false,draw:function(){
this.handler=new OpenLayers.Handler.Drag(this,{"move":this.panMap,"done":this.panMapDone});
},panMap:function(xy){
this.panned=true;
this.map.pan(this.handler.last.x-xy.x,this.handler.last.y-xy.y,{dragging:this.handler.dragging,animate:false});
},panMapDone:function(xy){
if(this.panned){
this.panMap(xy);
this.panned=false;
}
},CLASS_NAME:"OpenLayers.Control.DragPan"});

OpenLayers.Control.Navigation=OpenLayers.Class(OpenLayers.Control,{dragPan:null,zoomBox:null,zoomWheelEnabled:false,animatedPanEnabled:false,initialize:function(_1){
this.handlers={};
OpenLayers.Control.prototype.initialize.apply(this,arguments);
},destroy:function(){
this.deactivate();
if(this.dragPan){
this.dragPan.destroy();
}
this.dragPan=null;
if(this.zoomBox){
this.zoomBox.destroy();
}
this.zoomBox=null;
OpenLayers.Control.prototype.destroy.apply(this,arguments);
},activate:function(){
this.dragPan.activate();
if(this.zoomWheelEnabled){
this.handlers.wheel.activate();
}
this.handlers.click.activate();
this.zoomBox.activate();
return OpenLayers.Control.prototype.activate.apply(this,arguments);
},deactivate:function(){
this.zoomBox.deactivate();
this.dragPan.deactivate();
this.handlers.click.deactivate();
this.handlers.wheel.deactivate();
return OpenLayers.Control.prototype.deactivate.apply(this,arguments);
},draw:function(){
var _2={"dblclick":this.defaultDblClick};
var _3={"double":true,"stopDouble":true};
if(this.animatedPanEnabled){
_2["click"]=this.onClick;
_3["single"]=true;
_3["delay"]=200;
}
this.handlers.click=new OpenLayers.Handler.Click(this,_2,_3);
this.dragPan=new OpenLayers.Control.DragPan({map:this.map});
this.zoomBox=new OpenLayers.Control.ZoomBox({map:this.map,keyMask:OpenLayers.Handler.MOD_SHIFT});
this.dragPan.draw();
this.zoomBox.draw();
this.handlers.wheel=new OpenLayers.Handler.MouseWheel(this,{"up":this.wheelUp,"down":this.wheelDown});
this.activate();
},defaultDblClick:function(_4){
var _5=this.map.getLonLatFromViewPortPx(_4.xy);
this.map.setCenter(_5,this.map.zoom+1);
},onClick:function(_6){
this.map.panTo(this.map.getLonLatFromPixel(_6.xy));
},wheelChange:function(_7,_8){
var _9=this.map.getZoom()+_8;
if(!this.map.isValidZoomLevel(_9)){
return;
}
var _a=this.map.getSize();
var _b=_a.w/2-_7.xy.x;
var _c=_7.xy.y-_a.h/2;
var _d=this.map.getResolutionForZoom(_9);
var _e=this.map.getLonLatFromPixel(_7.xy);
var _f=new OpenLayers.LonLat(_e.lon+_b*_d,_e.lat+_c*_d);
this.map.setCenter(_f,_9);
},wheelUp:function(evt){
this.wheelChange(evt,1);
},wheelDown:function(evt){
this.wheelChange(evt,-1);
},disableZoomWheel:function(){
this.zoomWheelEnabled=false;
this.handlers.wheel.deactivate();
},enableZoomWheel:function(){
this.zoomWheelEnabled=true;
if(this.active){
this.handlers.wheel.activate();
}
},CLASS_NAME:"OpenLayers.Control.Navigation"});

OpenLayers.Control.MouseDefaults=OpenLayers.Class(OpenLayers.Control,{performedDrag:false,wheelObserver:null,initialize:function(){
OpenLayers.Control.prototype.initialize.apply(this,arguments);
},destroy:function(){
if(this.handler){
this.handler.destroy();
}
this.handler=null;
this.map.events.un({"click":this.defaultClick,"dblclick":this.defaultDblClick,"mousedown":this.defaultMouseDown,"mouseup":this.defaultMouseUp,"mousemove":this.defaultMouseMove,"mouseout":this.defaultMouseOut,scope:this});
OpenLayers.Event.stopObserving(window,"DOMMouseScroll",this.wheelObserver);
OpenLayers.Event.stopObserving(window,"mousewheel",this.wheelObserver);
OpenLayers.Event.stopObserving(document,"mousewheel",this.wheelObserver);
this.wheelObserver=null;
OpenLayers.Control.prototype.destroy.apply(this,arguments);
},draw:function(){
this.map.events.on({"click":this.defaultClick,"dblclick":this.defaultDblClick,"mousedown":this.defaultMouseDown,"mouseup":this.defaultMouseUp,"mousemove":this.defaultMouseMove,"mouseout":this.defaultMouseOut,scope:this});
this.registerWheelEvents();
},registerWheelEvents:function(){
this.wheelObserver=OpenLayers.Function.bindAsEventListener(this.onWheelEvent,this);
OpenLayers.Event.observe(window,"DOMMouseScroll",this.wheelObserver);
OpenLayers.Event.observe(window,"mousewheel",this.wheelObserver);
OpenLayers.Event.observe(document,"mousewheel",this.wheelObserver);
},defaultClick:function(_1){
if(!OpenLayers.Event.isLeftClick(_1)){
return;
}
var _2=!this.performedDrag;
this.performedDrag=false;
return _2;
},defaultDblClick:function(_3){
var _4=this.map.getLonLatFromViewPortPx(_3.xy);
this.map.setCenter(_4,this.map.zoom+1);
OpenLayers.Event.stop(_3);
return false;
},defaultMouseDown:function(_5){
if(!OpenLayers.Event.isLeftClick(_5)){
return;
}
this.mouseDragStart=_5.xy.clone();
this.performedDrag=false;
if(_5.shiftKey){
this.map.div.style.cursor="crosshair";
this.zoomBox=OpenLayers.Util.createDiv("zoomBox",this.mouseDragStart,null,null,"absolute","2px solid red");
this.zoomBox.style.backgroundColor="white";
this.zoomBox.style.filter="alpha(opacity=50)";
this.zoomBox.style.opacity="0.50";
this.zoomBox.style.fontSize="1px";
this.zoomBox.style.zIndex=this.map.Z_INDEX_BASE["Popup"]-1;
this.map.viewPortDiv.appendChild(this.zoomBox);
}
document.onselectstart=function(){
return false;
};
OpenLayers.Event.stop(_5);
},defaultMouseMove:function(_6){
this.mousePosition=_6.xy.clone();
if(this.mouseDragStart!=null){
if(this.zoomBox){
var _7=Math.abs(this.mouseDragStart.x-_6.xy.x);
var _8=Math.abs(this.mouseDragStart.y-_6.xy.y);
this.zoomBox.style.width=Math.max(1,_7)+"px";
this.zoomBox.style.height=Math.max(1,_8)+"px";
if(_6.xy.x<this.mouseDragStart.x){
this.zoomBox.style.left=_6.xy.x+"px";
}
if(_6.xy.y<this.mouseDragStart.y){
this.zoomBox.style.top=_6.xy.y+"px";
}
}else{
var _7=this.mouseDragStart.x-_6.xy.x;
var _8=this.mouseDragStart.y-_6.xy.y;
var _9=this.map.getSize();
var _a=new OpenLayers.Pixel(_9.w/2+_7,_9.h/2+_8);
var _b=this.map.getLonLatFromViewPortPx(_a);
this.map.setCenter(_b,null,true);
this.mouseDragStart=_6.xy.clone();
this.map.div.style.cursor="move";
}
this.performedDrag=true;
}
},defaultMouseUp:function(_c){
if(!OpenLayers.Event.isLeftClick(_c)){
return;
}
if(this.zoomBox){
this.zoomBoxEnd(_c);
}else{
if(this.performedDrag){
this.map.setCenter(this.map.center);
}
}
document.onselectstart=null;
this.mouseDragStart=null;
this.map.div.style.cursor="";
},defaultMouseOut:function(_d){
if(this.mouseDragStart!=null&&OpenLayers.Util.mouseLeft(_d,this.map.div)){
if(this.zoomBox){
this.removeZoomBox();
}
this.mouseDragStart=null;
}
},defaultWheelUp:function(_e){
if(this.map.getZoom()<=this.map.getNumZoomLevels()){
this.map.setCenter(this.map.getLonLatFromPixel(_e.xy),this.map.getZoom()+1);
}
},defaultWheelDown:function(_f){
if(this.map.getZoom()>0){
this.map.setCenter(this.map.getLonLatFromPixel(_f.xy),this.map.getZoom()-1);
}
},zoomBoxEnd:function(evt){
if(this.mouseDragStart!=null){
if(Math.abs(this.mouseDragStart.x-evt.xy.x)>5||Math.abs(this.mouseDragStart.y-evt.xy.y)>5){
var _11=this.map.getLonLatFromViewPortPx(this.mouseDragStart);
var end=this.map.getLonLatFromViewPortPx(evt.xy);
var top=Math.max(_11.lat,end.lat);
var _14=Math.min(_11.lat,end.lat);
var _15=Math.min(_11.lon,end.lon);
var _16=Math.max(_11.lon,end.lon);
var _17=new OpenLayers.Bounds(_15,_14,_16,top);
this.map.zoomToExtent(_17);
}else{
var end=this.map.getLonLatFromViewPortPx(evt.xy);
this.map.setCenter(new OpenLayers.LonLat((end.lon),(end.lat)),this.map.getZoom()+1);
}
this.removeZoomBox();
}
},removeZoomBox:function(){
this.map.viewPortDiv.removeChild(this.zoomBox);
this.zoomBox=null;
},onWheelEvent:function(e){
var _19=false;
var _1a=OpenLayers.Event.element(e);
while(_1a!=null){
if(this.map&&_1a==this.map.div){
_19=true;
break;
}
_1a=_1a.parentNode;
}
if(_19){
var _1b=0;
if(!e){
e=window.event;
}
if(e.wheelDelta){
_1b=e.wheelDelta/120;
if(window.opera&&window.opera.version()<9.2){
_1b=-_1b;
}
}else{
if(e.detail){
_1b=-e.detail/3;
}
}
if(_1b){
e.xy=this.mousePosition;
if(_1b<0){
this.defaultWheelDown(e);
}else{
this.defaultWheelUp(e);
}
}
OpenLayers.Event.stop(e);
}
},CLASS_NAME:"OpenLayers.Control.MouseDefaults"});

OpenLayers.Control.MousePosition=OpenLayers.Class(OpenLayers.Control,{element:null,prefix:"<div><span>",prefixX:" X : ",prefixY:" Y : ",prefixPixelX:" pixel X : ",prefixPixelY:" pixel Y : ",prefixLat:" Lat : ",prefixLon:" Lon : ",separator:"</span><span>",separatorDM:"&#176 ",separatorMS:"' ",separatorSHemi:"\" ",suffix:"</span></div>",suffixDMS:"\" ",numDigits:3,granularity:10,lastXy:null,displayProjection:null,PX:false,XY:false,LatLon:false,DMS:false,DM:false,initialize:function(_1){
OpenLayers.Control.prototype.initialize.apply(this,arguments);
},destroy:function(){
if(this.map){
this.map.events.unregister("mousemove",this,this.redraw);
}
OpenLayers.Control.prototype.destroy.apply(this,arguments);
},draw:function(){
OpenLayers.Control.prototype.draw.apply(this,arguments);
if(!this.element){
this.div.left="";
this.div.top="";
this.element=this.div;
}
this.redraw();
return this.div;
},redraw:function(_2){
var _3;
var _4="";
if(_2==null){
_3=new OpenLayers.LonLat(0,0);
if(this.PX){
_4+=this.formatPX(null);
}
}else{
if(this.lastXy==null||Math.abs(_2.xy.x-this.lastXy.x)>this.granularity||Math.abs(_2.xy.y-this.lastXy.y)>this.granularity){
this.lastXy=_2.xy;
return;
}
this.lastXy=_2.xy;
if(this.PX){
_4+=this.formatPX(_2.xy);
}
_3=this.map.getLonLatFromPixel(_2.xy);
}
if(!_3){
return;
}
if(this.XY||(!this.PX&&!this.LatLon&&!this.DM&&!this.DMS)){
_4+=this.formatXY(_3);
}
if((this.LatLon||this.DMS||this.DM)&&this.displayProjection==null){
this.displayProjection=new OpenLayers.Projection("EPSG:4326");
}
if(this.displayProjection){
_3.transform(this.map.getProjectionObject(),this.displayProjection);
}
if(this.displayProjection!=null&&(this.displayProjection.proj.units==null||this.displayProjection.proj.units=="degrees")){
if(this.LatLon){
_4+=this.formatLatLon(_3);
}
if(this.DM){
_4+=this.formatDM(_3);
}
if(this.DMS){
_4+=this.formatDMS(_3);
}
}
if(_4!=this.element.innerHTML){
this.element.innerHTML=_4;
}
},formatOutput:function(_5){
var _6=parseInt(this.numDigits);
var _7=this.prefix+_5.lon.toFixed(_6)+this.separator+_5.lat.toFixed(_6)+this.suffix;
return _7;
},setMap:function(){
OpenLayers.Control.prototype.setMap.apply(this,arguments);
this.map.events.register("mousemove",this,this.redraw);
},formatPX:function(px){
var _9=parseInt(this.numDigits);
if(px==null){
px={x:0,y:0};
}
var _a=this.prefix+"<span class='mfCursorTrackPrefix'>"+this.prefixPixelX+"</span>"+px.x+this.separator+"<span class='mfCursorTrackPrefix'>"+this.prefixPixelY+"</span>"+px.y+this.suffix;
return _a;
},formatXY:function(_b){
var _c=parseInt(this.numDigits);
var _d=this.prefix+"<span class='mfCursorTrackPrefix'>"+this.prefixX+"</span>"+_b.lon.toFixed(_c)+this.separator+"<span class='mfCursorTrackPrefix'>"+this.prefixY+"</span>"+_b.lat.toFixed(_c)+this.suffix;
return _d;
},formatLatLon:function(_e){
var _f=parseInt(this.numDigits);
var _10=this.prefix+"<span class='mfCursorTrackPrefix'>"+this.prefixLon+"</span>"+_e.lon.toFixed(_f)+this.separator+"<span class='mfCursorTrackPrefix'>"+this.prefixLat+"</span>"+_e.lat.toFixed(_f)+this.suffix;
return _10;
},formatDMS:function(_11){
var _12=parseInt(this.numDigits);
var lon=this.convertDMS(_11.lon.toFixed(_12),"LON");
var lat=this.convertDMS(_11.lat.toFixed(_12),"LAT");
var _15=this.prefix+"<span class='mfCursorTrackPrefix'>"+this.prefixLon+"</span>"+lon[0]+this.separatorDM+lon[1]+this.separatorMS+lon[2]+this.separatorSHemi+lon[3]+this.separator+"<span class='mfCursorTrackPrefix'>"+this.prefixLat+"</span>"+lat[0]+this.separatorDM+lat[1]+this.separatorMS+lat[2]+this.separatorSHemi+lat[3]+this.suffix;
return _15;
},formatDM:function(_16){
var _17=parseInt(this.numDigits);
var lon=this.convertDM(_16.lon.toFixed(_17),"LON");
var lat=this.convertDM(_16.lat.toFixed(_17),"LAT");
var _1a=this.prefix+"<span class='mfCursorTrackPrefix'>"+this.prefixLon+"</span>"+lon[0]+this.separatorDM+lon[1]+this.separatorMS+lon[2]+this.separator+"<span class='mfCursorTrackPrefix'>"+this.prefixLat+"</span>"+lat[0]+this.separatorDM+lat[1]+this.separatorMS+lat[2]+this.suffix;
return _1a;
},convertDMS:function(_1b,_1c){
var _1d=[];
abscoord=Math.abs(_1b);
coordDeg=Math.floor(abscoord);
coordMin=(abscoord-coordDeg)/(1/60);
tempcoordMin=coordMin;
coordMin=Math.floor(coordMin);
coordSec=(tempcoordMin-coordMin)/(1/60);
coordSec=Math.round(coordSec*10);
coordSec/=10;
if(coordDeg<10){
coordDeg="0"+coordDeg;
}
if(coordMin<10){
coordMin="0"+coordMin;
}
if(coordSec<10){
coordSec="0"+coordSec;
}
_1d[0]=coordDeg;
_1d[1]=coordMin;
_1d[2]=coordSec;
_1d[3]=this.getHemi(_1b,_1c);
return _1d;
},convertDM:function(_1e,_1f){
var _20=[];
abscoord=Math.abs(_1e);
coordDeg=Math.floor(abscoord);
coordMin=(abscoord-coordDeg)*60;
coordMin=Math.round(coordMin*1000);
coordMin/=1000;
if(coordDeg<10){
coordDeg="0"+coordDeg;
}
if(coordMin<10){
coordMin="0"+coordMin;
}
_20[0]=coordDeg;
_20[1]=coordMin;
_20[2]=this.getHemi(_1e,_1f);
return _20;
},getHemi:function(_21,_22){
var _23="";
if(_22=="LAT"){
if(_21>=0){
_23="N";
}else{
_23="S";
}
}else{
if(_22=="LON"){
if(_21>=0){
_23="E";
}else{
_23="W";
}
}
}
return _23;
},CLASS_NAME:"OpenLayers.Control.MousePosition"});

OpenLayers.Control.KeyboardDefaults=OpenLayers.Class(OpenLayers.Control,{slideFactor:75,initialize:function(){
OpenLayers.Control.prototype.initialize.apply(this,arguments);
},destroy:function(){
if(this.handler){
this.handler.destroy();
}
this.handler=null;
OpenLayers.Control.prototype.destroy.apply(this,arguments);
},draw:function(){
this.handler=new OpenLayers.Handler.Keyboard(this,{"keypress":this.defaultKeyPress});
this.activate();
},defaultKeyPress:function(_1){
switch(_1){
case OpenLayers.Event.KEY_LEFT:
this.map.pan(-this.slideFactor,0);
break;
case OpenLayers.Event.KEY_RIGHT:
this.map.pan(this.slideFactor,0);
break;
case OpenLayers.Event.KEY_UP:
this.map.pan(0,-this.slideFactor);
break;
case OpenLayers.Event.KEY_DOWN:
this.map.pan(0,this.slideFactor);
break;
case 33:
var _2=this.map.getSize();
this.map.pan(0,-0.75*_2.h);
break;
case 34:
var _2=this.map.getSize();
this.map.pan(0,0.75*_2.h);
break;
case 35:
var _2=this.map.getSize();
this.map.pan(0.75*_2.w,0);
break;
case 36:
var _2=this.map.getSize();
this.map.pan(-0.75*_2.w,0);
break;
case 43:
this.map.zoomIn();
break;
case 45:
this.map.zoomOut();
break;
case 107:
this.map.zoomIn();
break;
case 109:
this.map.zoomOut();
break;
}
},CLASS_NAME:"OpenLayers.Control.KeyboardDefaults"});

OpenLayers.Control.MouseWheelDefaults=OpenLayers.Class(OpenLayers.Control,{initialize:function(){
OpenLayers.Control.prototype.initialize.apply(this,arguments);
},destroy:function(){
if(this.handler){
this.handler.destroy();
}
this.handler=null;
OpenLayers.Control.prototype.destroy.apply(this,arguments);
},activate:function(){
this.handler.activate();
return OpenLayers.Control.prototype.activate.apply(this,arguments);
},deactivate:function(){
this.handlers.deactivate();
return OpenLayers.Control.prototype.deactivate.apply(this,arguments);
},draw:function(){
this.handler=new OpenLayers.Handler.MouseWheel(this,{"up":this.wheelUp,"down":this.wheelDown});
this.activate();
},wheelChange:function(_1,_2){
var _3=this.map.getZoom()+_2;
if(!this.map.isValidZoomLevel(_3)){
return;
}
var _4=this.map.getSize();
var _5=_4.w/2-_1.xy.x;
var _6=_1.xy.y-_4.h/2;
var _7=this.map.getResolutionForZoom(_3);
var _8=this.map.getLonLatFromPixel(_1.xy);
var _9=new OpenLayers.LonLat(_8.lon+_5*_7,_8.lat+_6*_7);
this.map.setCenter(_9,_3);
},wheelUp:function(_a){
this.wheelChange(_a,1);
},wheelDown:function(_b){
this.wheelChange(_b,-1);
},disableZoomWheel:function(){
this.zoomWheelEnabled=false;
this.handler.deactivate();
},enableZoomWheel:function(){
this.zoomWheelEnabled=true;
if(this.active){
this.handler.activate();
}
},CLASS_NAME:"OpenLayers.Control.MouseWheelDefaults"});

OpenLayers.Control.PanZoom=OpenLayers.Class(OpenLayers.Control,{slideFactor:50,buttons:null,position:null,initialize:function(_1){
this.position=new OpenLayers.Pixel(OpenLayers.Control.PanZoom.X,OpenLayers.Control.PanZoom.Y);
OpenLayers.Control.prototype.initialize.apply(this,arguments);
},destroy:function(){
OpenLayers.Control.prototype.destroy.apply(this,arguments);
while(this.buttons.length){
var _2=this.buttons.shift();
_2.map=null;
OpenLayers.Event.stopObservingElement(_2);
}
this.buttons=null;
this.position=null;
},draw:function(px){
OpenLayers.Control.prototype.draw.apply(this,arguments);
px=this.position;
this.buttons=[];
var sz=new OpenLayers.Size(18,18);
var _5=new OpenLayers.Pixel(px.x+sz.w/2,px.y);
this._addButton("panup","north-mini.png",_5,sz);
px.y=_5.y+sz.h;
this._addButton("panleft","west-mini.png",px,sz);
this._addButton("panright","east-mini.png",px.add(sz.w,0),sz);
this._addButton("pandown","south-mini.png",_5.add(0,sz.h*2),sz);
this._addButton("zoomin","zoom-plus-mini.png",_5.add(0,sz.h*3+5),sz);
this._addButton("zoomworld","zoom-world-mini.png",_5.add(0,sz.h*4+5),sz);
this._addButton("zoomout","zoom-minus-mini.png",_5.add(0,sz.h*5+5),sz);
return this.div;
},_addButton:function(id,_7,xy,sz){
var _a=OpenLayers.Util.getImagesLocation()+_7;
var _b=OpenLayers.Util.createAlphaImageDiv("OpenLayers_Control_PanZoom_"+id,xy,sz,_a,"absolute");
this.div.appendChild(_b);
OpenLayers.Event.observe(_b,"mousedown",OpenLayers.Function.bindAsEventListener(this.buttonDown,_b));
OpenLayers.Event.observe(_b,"dblclick",OpenLayers.Function.bindAsEventListener(this.doubleClick,_b));
OpenLayers.Event.observe(_b,"click",OpenLayers.Function.bindAsEventListener(this.doubleClick,_b));
_b.action=id;
_b.map=this.map;
_b.slideFactor=this.slideFactor;
this.buttons.push(_b);
return _b;
},doubleClick:function(_c){
OpenLayers.Event.stop(_c);
return false;
},buttonDown:function(_d){
if(!OpenLayers.Event.isLeftClick(_d)){
return;
}
switch(this.action){
case "panup":
this.map.pan(0,-this.slideFactor);
break;
case "pandown":
this.map.pan(0,this.slideFactor);
break;
case "panleft":
this.map.pan(-this.slideFactor,0);
break;
case "panright":
this.map.pan(this.slideFactor,0);
break;
case "zoomin":
this.map.zoomIn();
break;
case "zoomout":
this.map.zoomOut();
break;
case "zoomworld":
this.map.zoomToMaxExtent();
break;
}
OpenLayers.Event.stop(_d);
},CLASS_NAME:"OpenLayers.Control.PanZoom"});
OpenLayers.Control.PanZoom.X=4;
OpenLayers.Control.PanZoom.Y=4;

OpenLayers.Control.ArgParser=OpenLayers.Class(OpenLayers.Control,{center:null,zoom:null,layers:null,displayProjection:null,initialize:function(_1){
OpenLayers.Control.prototype.initialize.apply(this,arguments);
},setMap:function(_2){
OpenLayers.Control.prototype.setMap.apply(this,arguments);
for(var i=0;i<this.map.controls.length;i++){
var _4=this.map.controls[i];
if((_4!=this)&&(_4.CLASS_NAME=="OpenLayers.Control.ArgParser")){
if(_4.displayProjection!=this.displayProjection){
this.displayProjection=_4.displayProjection;
}
break;
}
}
if(i==this.map.controls.length){
var _5=OpenLayers.Util.getParameters();
if(_5.layers){
this.layers=_5.layers;
this.map.events.register("addlayer",this,this.configureLayers);
this.configureLayers();
}
if(_5.lat&&_5.lon){
this.center=new OpenLayers.LonLat(parseFloat(_5.lon),parseFloat(_5.lat));
if(_5.zoom){
this.zoom=parseInt(_5.zoom);
}
this.map.events.register("changebaselayer",this,this.setCenter);
this.setCenter();
}
}
},setCenter:function(){
if(this.map.baseLayer){
this.map.events.unregister("changebaselayer",this,this.setCenter);
if(this.displayProjection){
this.center.transform(this.displayProjection,this.map.getProjectionObject());
}
this.map.setCenter(this.center,this.zoom);
}
},configureLayers:function(){
if(this.layers.length==this.map.layers.length){
this.map.events.unregister("addlayer",this,this.configureLayers);
for(var i=0;i<this.layers.length;i++){
var _7=this.map.layers[i];
var c=this.layers.charAt(i);
if(c=="B"){
this.map.setBaseLayer(_7);
}else{
if((c=="T")||(c=="F")){
_7.setVisibility(c=="T");
}
}
}
}
},CLASS_NAME:"OpenLayers.Control.ArgParser"});

OpenLayers.Control.Permalink=OpenLayers.Class(OpenLayers.Control,{element:null,base:"",displayProjection:null,initialize:function(_1,_2,_3){
OpenLayers.Control.prototype.initialize.apply(this,[_3]);
this.element=OpenLayers.Util.getElement(_1);
this.base=_2||document.location.href;
},destroy:function(){
if(this.element.parentNode==this.div){
this.div.removeChild(this.element);
}
this.element=null;
this.map.events.unregister("moveend",this,this.updateLink);
OpenLayers.Control.prototype.destroy.apply(this,arguments);
},setMap:function(_4){
OpenLayers.Control.prototype.setMap.apply(this,arguments);
for(var i=0;i<this.map.controls.length;i++){
var _6=this.map.controls[i];
if(_6.CLASS_NAME=="OpenLayers.Control.ArgParser"){
if(_6.displayProjection!=this.displayProjection){
this.displayProjection=_6.displayProjection;
}
break;
}
}
if(i==this.map.controls.length){
this.map.addControl(new OpenLayers.Control.ArgParser({"displayProjection":this.displayProjection}));
}
},draw:function(){
OpenLayers.Control.prototype.draw.apply(this,arguments);
if(!this.element){
this.element=document.createElement("a");
this.element.innerHTML=OpenLayers.i18n("permalink");
this.element.href="";
this.div.appendChild(this.element);
}
this.map.events.on({"moveend":this.updateLink,"changelayer":this.updateLink,"changebaselayer":this.updateLink,scope:this});
return this.div;
},updateLink:function(){
var _7=this.map.getCenter();
if(!_7){
return;
}
var _8=OpenLayers.Util.getParameters(this.base);
_8.zoom=this.map.getZoom();
var _9=_7.lat;
var _a=_7.lon;
if(this.displayProjection){
var _b=OpenLayers.Projection.transform({x:_a,y:_9},this.map.getProjectionObject(),this.displayProjection);
_a=_b.x;
_9=_b.y;
}
_8.lat=Math.round(_9*100000)/100000;
_8.lon=Math.round(_a*100000)/100000;
_8.layers="";
for(var i=0;i<this.map.layers.length;i++){
var _d=this.map.layers[i];
if(_d.isBaseLayer){
_8.layers+=(_d==this.map.baseLayer)?"B":"0";
}else{
_8.layers+=(_d.getVisibility())?"T":"F";
}
}
var _e=this.base;
if(_e.indexOf("?")!=-1){
_e=_e.substring(0,_e.indexOf("?"));
}
_e+="?"+OpenLayers.Util.getParameterString(_8);
this.element.href=_e;
},CLASS_NAME:"OpenLayers.Control.Permalink"});

OpenLayers.Control.Scale=OpenLayers.Class(OpenLayers.Control,{element:null,initialize:function(_1,_2){
OpenLayers.Control.prototype.initialize.apply(this,[_2]);
this.element=OpenLayers.Util.getElement(_1);
},draw:function(){
OpenLayers.Control.prototype.draw.apply(this,arguments);
if(!this.element){
this.element=document.createElement("div");
this.div.appendChild(this.element);
}
this.map.events.register("moveend",this,this.updateScale);
this.updateScale();
return this.div;
},updateScale:function(){
var _3=this.map.getScale();
if(!_3){
return;
}
if(_3>=9500&&_3<=950000){
_3=Math.round(_3/1000)+"K";
}else{
if(_3>=950000){
_3=Math.round(_3/1000000)+"M";
}else{
_3=Math.round(_3);
}
}
this.element.innerHTML=OpenLayers.i18n("scale",{"scaleDenom":_3});
},CLASS_NAME:"OpenLayers.Control.Scale"});

OpenLayers.Control.ScaleLine=OpenLayers.Class(OpenLayers.Control,{maxWidth:100,topOutUnits:"km",topInUnits:"m",bottomOutUnits:"mi",bottomInUnits:"ft",eTop:null,eBottom:null,initialize:function(_1){
OpenLayers.Control.prototype.initialize.apply(this,[_1]);
},draw:function(){
OpenLayers.Control.prototype.draw.apply(this,arguments);
if(!this.eTop){
this.div.style.display="block";
this.div.style.position="absolute";
this.eTop=document.createElement("div");
this.eTop.className=this.displayClass+"Top";
var _2=this.topInUnits.length;
this.div.appendChild(this.eTop);
if((this.topOutUnits=="")||(this.topInUnits=="")){
this.eTop.style.visibility="hidden";
}else{
this.eTop.style.visibility="visible";
}
this.eBottom=document.createElement("div");
this.eBottom.className=this.displayClass+"Bottom";
this.div.appendChild(this.eBottom);
if((this.bottomOutUnits=="")||(this.bottomInUnits=="")){
this.eBottom.style.visibility="hidden";
}else{
this.eBottom.style.visibility="visible";
}
}
this.map.events.register("moveend",this,this.update);
this.update();
return this.div;
},getBarLen:function(_3){
var _4=parseInt(Math.log(_3)/Math.log(10));
var _5=Math.pow(10,_4);
var _6=parseInt(_3/_5);
var _7;
if(_6>5){
_7=5;
}else{
if(_6>2){
_7=2;
}else{
_7=1;
}
}
return _7*_5;
},update:function(){
var _8=this.map.getResolution();
if(!_8){
return;
}
var _9=this.map.getUnits();
var _a=OpenLayers.INCHES_PER_UNIT;
var _b=this.maxWidth*_8*_a[_9];
var _c;
var _d;
if(_b>100000){
_c=this.topOutUnits;
_d=this.bottomOutUnits;
}else{
_c=this.topInUnits;
_d=this.bottomInUnits;
}
var _e=_b/_a[_c];
var _f=_b/_a[_d];
var _10=this.getBarLen(_e);
var _11=this.getBarLen(_f);
_e=_10/_a[_9]*_a[_c];
_f=_11/_a[_9]*_a[_d];
var _12=_e/_8;
var _13=_f/_8;
this.eTop.style.width=Math.round(_12)+"px";
this.eBottom.style.width=Math.round(_13)+"px";
this.eTop.innerHTML=_10+" "+_c;
this.eBottom.innerHTML=_11+" "+_d;
},CLASS_NAME:"OpenLayers.Control.ScaleLine"});

OpenLayers.Control.Panel=OpenLayers.Class(OpenLayers.Control,{controls:null,defaultControl:null,initialize:function(_1){
OpenLayers.Control.prototype.initialize.apply(this,[_1]);
this.controls=[];
},destroy:function(){
OpenLayers.Control.prototype.destroy.apply(this,arguments);
for(var i=this.controls.length-1;i>=0;i--){
if(this.controls[i].events){
this.controls[i].events.un({"activate":this.redraw,"deactivate":this.redraw,scope:this});
}
OpenLayers.Event.stopObservingElement(this.controls[i].panel_div);
this.controls[i].panel_div=null;
}
},activate:function(){
if(OpenLayers.Control.prototype.activate.apply(this,arguments)){
for(var i=0;i<this.controls.length;i++){
if(this.controls[i]==this.defaultControl){
this.controls[i].activate();
}
}
this.redraw();
return true;
}else{
return false;
}
},deactivate:function(){
if(OpenLayers.Control.prototype.deactivate.apply(this,arguments)){
for(var i=0;i<this.controls.length;i++){
this.controls[i].deactivate();
}
return true;
}else{
return false;
}
},draw:function(){
OpenLayers.Control.prototype.draw.apply(this,arguments);
for(var i=0;i<this.controls.length;i++){
this.map.addControl(this.controls[i]);
this.controls[i].deactivate();
this.controls[i].events.on({"activate":this.redraw,"deactivate":this.redraw,scope:this});
}
this.activate();
return this.div;
},redraw:function(){
this.div.innerHTML="";
if(this.active){
for(var i=0;i<this.controls.length;i++){
var _7=this.controls[i].panel_div;
if(this.controls[i].active){
_7.className=this.controls[i].displayClass+"ItemActive";
}else{
_7.className=this.controls[i].displayClass+"ItemInactive";
}
this.div.appendChild(_7);
}
}
},activateControl:function(_8){
if(!this.active){
return false;
}
if(_8.type==OpenLayers.Control.TYPE_BUTTON){
_8.trigger();
return;
}
if(_8.type==OpenLayers.Control.TYPE_TOGGLE){
if(_8.active){
_8.deactivate();
}else{
_8.activate();
}
return;
}
for(var i=0;i<this.controls.length;i++){
if(this.controls[i]!=_8){
if(this.controls[i].type!=OpenLayers.Control.TYPE_TOGGLE){
this.controls[i].deactivate();
}
}
}
_8.activate();
},addControls:function(_a){
if(!(_a instanceof Array)){
_a=[_a];
}
this.controls=this.controls.concat(_a);
for(var i=0;i<_a.length;i++){
var _c=document.createElement("div");
var _d=document.createTextNode(" ");
_a[i].panel_div=_c;
if(_a[i].title!=""){
_a[i].panel_div.title=_a[i].title;
}
OpenLayers.Event.observe(_a[i].panel_div,"click",OpenLayers.Function.bind(this.onClick,this,_a[i]));
OpenLayers.Event.observe(_a[i].panel_div,"mousedown",OpenLayers.Function.bindAsEventListener(OpenLayers.Event.stop));
}
if(this.map){
for(var i=0;i<_a.length;i++){
this.map.addControl(_a[i]);
_a[i].deactivate();
_a[i].events.on({"activate":this.redraw,"deactivate":this.redraw,scope:this});
}
this.redraw();
}
},onClick:function(_e,_f){
OpenLayers.Event.stop(_f?_f:window.event);
this.activateControl(_e);
},getControlsBy:function(_10,_11){
var _12=(typeof _11.test=="function");
var _13=OpenLayers.Array.filter(this.controls,function(_14){
return _14[_10]==_11||(_12&&_11.test(_14[_10]));
});
return _13;
},getControlsByName:function(_15){
return this.getControlsBy("name",_15);
},getControlsByClass:function(_16){
return this.getControlsBy("CLASS_NAME",_16);
},CLASS_NAME:"OpenLayers.Control.Panel"});

OpenLayers.Control.SelectFeature=OpenLayers.Class(OpenLayers.Control,{multipleKey:null,toggleKey:null,multiple:false,clickout:true,toggle:false,hover:false,onSelect:function(){
},onUnselect:function(){
},geometryTypes:null,layer:null,callbacks:null,selectStyle:null,renderIntent:"select",handler:null,initialize:function(_1,_2){
OpenLayers.Control.prototype.initialize.apply(this,[_2]);
this.layer=_1;
this.callbacks=OpenLayers.Util.extend({click:this.clickFeature,clickout:this.clickoutFeature,over:this.overFeature,out:this.outFeature},this.callbacks);
var _3={geometryTypes:this.geometryTypes};
this.handler=new OpenLayers.Handler.Feature(this,_1,this.callbacks,_3);
},unselectAll:function(_4){
var _5;
for(var i=this.layer.selectedFeatures.length-1;i>=0;--i){
_5=this.layer.selectedFeatures[i];
if(!_4||_4.except!=_5){
this.unselect(_5);
}
}
},clickFeature:function(_7){
if(!this.hover){
var _8=(OpenLayers.Util.indexOf(this.layer.selectedFeatures,_7)>-1);
if(_8){
if(this.toggleSelect()){
this.unselect(_7);
}else{
if(!this.multipleSelect()){
this.unselectAll({except:_7});
}
}
}else{
if(!this.multipleSelect()){
this.unselectAll({except:_7});
}
this.select(_7);
}
}
},multipleSelect:function(){
return this.multiple||this.handler.evt[this.multipleKey];
},toggleSelect:function(){
return this.toggle||this.handler.evt[this.toggleKey];
},clickoutFeature:function(_9){
if(!this.hover&&this.clickout){
this.unselectAll();
}
},overFeature:function(_a){
if(this.hover&&(OpenLayers.Util.indexOf(this.layer.selectedFeatures,_a)==-1)){
this.select(_a);
}
},outFeature:function(_b){
if(this.hover){
this.unselect(_b);
}
},select:function(_c){
this.layer.selectedFeatures.push(_c);
var _d=this.selectStyle||this.renderIntent;
this.layer.drawFeature(_c,_d);
this.layer.events.triggerEvent("featureselected",{feature:_c});
this.onSelect(_c);
},unselect:function(_e){
this.layer.drawFeature(_e,"default");
OpenLayers.Util.removeItem(this.layer.selectedFeatures,_e);
this.layer.events.triggerEvent("featureunselected",{feature:_e});
this.onUnselect(_e);
},setMap:function(_f){
this.handler.setMap(_f);
OpenLayers.Control.prototype.setMap.apply(this,arguments);
},CLASS_NAME:"OpenLayers.Control.SelectFeature"});

OpenLayers.Control.NavigationHistory=OpenLayers.Class(OpenLayers.Control,{type:OpenLayers.Control.TYPE_TOGGLE,previous:null,previousOptions:null,next:null,nextOptions:null,limit:50,activateOnDraw:true,clearOnDeactivate:false,registry:null,nextStack:null,previousStack:null,listeners:null,restoring:false,initialize:function(_1){
OpenLayers.Control.prototype.initialize.apply(this,[_1]);
this.registry=OpenLayers.Util.extend({"moveend":function(){
return {center:this.map.getCenter(),resolution:this.map.getResolution()};
}},this.registry);
this.clear();
var _2={trigger:OpenLayers.Function.bind(this.previousTrigger,this),displayClass:this.displayClass+" "+this.displayClass+"Previous"};
OpenLayers.Util.extend(_2,this.previousOptions);
this.previous=new OpenLayers.Control.Button(_2);
var _3={trigger:OpenLayers.Function.bind(this.nextTrigger,this),displayClass:this.displayClass+" "+this.displayClass+"Next"};
OpenLayers.Util.extend(_3,this.nextOptions);
this.next=new OpenLayers.Control.Button(_3);
},onPreviousChange:function(_4,_5){
if(_4&&!this.previous.active){
this.previous.activate();
}else{
if(!_4&&this.previous.active){
this.previous.deactivate();
}
}
},onNextChange:function(_6,_7){
if(_6&&!this.next.active){
this.next.activate();
}else{
if(!_6&&this.next.active){
this.next.deactivate();
}
}
},destroy:function(){
OpenLayers.Control.prototype.destroy.apply(this);
this.previous.destroy();
this.next.destroy();
this.deactivate();
for(var _8 in this){
this[_8]=null;
}
},setMap:function(_9){
this.map=_9;
this.next.setMap(_9);
this.previous.setMap(_9);
},draw:function(){
OpenLayers.Control.prototype.draw.apply(this,arguments);
this.next.draw();
this.previous.draw();
if(this.activateOnDraw){
this.activate();
}
},previousTrigger:function(){
var _a=this.previousStack.shift();
var _b=this.previousStack.shift();
if(_b!=undefined){
this.nextStack.unshift(_a);
this.previousStack.unshift(_b);
this.restoring=true;
this.restore(_b);
this.restoring=false;
this.onNextChange(this.nextStack[0],this.nextStack.length);
this.onPreviousChange(this.previousStack[1],this.previousStack.length-1);
}else{
this.previousStack.unshift(_a);
}
return _b;
},nextTrigger:function(){
var _c=this.nextStack.shift();
if(_c!=undefined){
this.previousStack.unshift(_c);
this.restoring=true;
this.restore(_c);
this.restoring=false;
this.onNextChange(this.nextStack[0],this.nextStack.length);
this.onPreviousChange(this.previousStack[1],this.previousStack.length-1);
}
return _c;
},clear:function(){
this.previousStack=[];
this.nextStack=[];
},restore:function(_d){
var _e=this.map.getZoomForResolution(_d.resolution);
this.map.setCenter(_d.center,_e);
},setListeners:function(){
this.listeners={};
for(var _f in this.registry){
this.listeners[_f]=OpenLayers.Function.bind(function(){
if(!this.restoring){
var _10=this.registry[_f].apply(this,arguments);
this.previousStack.unshift(_10);
if(this.previousStack.length>1){
this.onPreviousChange(this.previousStack[1],this.previousStack.length-1);
}
if(this.previousStack.length>(this.limit+1)){
this.previousStack.pop();
}
if(this.nextStack.length>0){
this.nextStack=[];
this.onNextChange(null,0);
}
}
return true;
},this);
}
},activate:function(){
var _11=false;
if(this.map){
if(OpenLayers.Control.prototype.activate.apply(this)){
if(this.listeners==null){
this.setListeners();
}
for(var _12 in this.listeners){
this.map.events.register(_12,this,this.listeners[_12]);
}
_11=true;
if(this.previousStack.length==0){
this.initStack();
}
}
}
return _11;
},initStack:function(){
if(this.map.getCenter()){
this.listeners.moveend();
}
},deactivate:function(){
var _13=false;
if(this.map){
if(OpenLayers.Control.prototype.deactivate.apply(this)){
for(var _14 in this.listeners){
this.map.events.unregister(_14,this,this.listeners[_14]);
}
if(this.clearOnDeactivate){
this.clear();
}
_13=true;
}
}
return _13;
},CLASS_NAME:"OpenLayers.Control.NavigationHistory"});

OpenLayers.Geometry=OpenLayers.Class({id:null,parent:null,bounds:null,initialize:function(){
this.id=OpenLayers.Util.createUniqueID(this.CLASS_NAME+"_");
},destroy:function(){
this.id=null;
this.bounds=null;
},clone:function(){
return new OpenLayers.Geometry();
},setBounds:function(_1){
if(_1){
this.bounds=_1.clone();
}
},clearBounds:function(){
this.bounds=null;
if(this.parent){
this.parent.clearBounds();
}
},extendBounds:function(_2){
var _3=this.getBounds();
if(!_3){
this.setBounds(_2);
}else{
this.bounds.extend(_2);
}
},getBounds:function(){
if(this.bounds==null){
this.calculateBounds();
}
return this.bounds;
},calculateBounds:function(){
},atPoint:function(_4,_5,_6){
var _7=false;
var _8=this.getBounds();
if((_8!=null)&&(_4!=null)){
var dX=(_5!=null)?_5:0;
var dY=(_6!=null)?_6:0;
var _b=new OpenLayers.Bounds(this.bounds.left-dX,this.bounds.bottom-dY,this.bounds.right+dX,this.bounds.top+dY);
_7=_b.containsLonLat(_4);
}
return _7;
},getLength:function(){
return 0;
},getArea:function(){
return 0;
},toString:function(){
return OpenLayers.Format.WKT.prototype.write(new OpenLayers.Feature.Vector(this));
},CLASS_NAME:"OpenLayers.Geometry"});
OpenLayers.Geometry.segmentsIntersect=function(_c,_d,_e){
var _f=false;
var _10=_c.x1-_d.x1;
var _11=_c.y1-_d.y1;
var _12=_c.x2-_c.x1;
var _13=_c.y2-_c.y1;
var _14=_d.y2-_d.y1;
var _15=_d.x2-_d.x1;
var d=(_14*_12)-(_15*_13);
var n1=(_15*_11)-(_14*_10);
var n2=(_12*_11)-(_13*_10);
if(d==0){
if(n1==0&&n2==0){
_f=true;
}
}else{
var _19=n1/d;
var _1a=n2/d;
if(_19>=0&&_19<=1&&_1a>=0&&_1a<=1){
if(!_e){
_f=true;
}else{
var x=_c.x1+(_19*_12);
var y=_c.y1+(_19*_13);
_f=new OpenLayers.Geometry.Point(x,y);
}
}
}
return _f;
};

OpenLayers.Geometry.Rectangle=OpenLayers.Class(OpenLayers.Geometry,{x:null,y:null,width:null,height:null,initialize:function(x,y,_3,_4){
OpenLayers.Geometry.prototype.initialize.apply(this,arguments);
this.x=x;
this.y=y;
this.width=_3;
this.height=_4;
},calculateBounds:function(){
this.bounds=new OpenLayers.Bounds(this.x,this.y,this.x+this.width,this.y+this.height);
},getLength:function(){
var _5=(2*this.width)+(2*this.height);
return _5;
},getArea:function(){
var _6=this.width*this.height;
return _6;
},CLASS_NAME:"OpenLayers.Geometry.Rectangle"});

OpenLayers.Geometry.Collection=OpenLayers.Class(OpenLayers.Geometry,{components:null,componentTypes:null,initialize:function(_1){
OpenLayers.Geometry.prototype.initialize.apply(this,arguments);
this.components=[];
if(_1!=null){
this.addComponents(_1);
}
},destroy:function(){
this.components.length=0;
this.components=null;
},clone:function(){
var _2=eval("new "+this.CLASS_NAME+"()");
for(var i=0;i<this.components.length;i++){
_2.addComponent(this.components[i].clone());
}
OpenLayers.Util.applyDefaults(_2,this);
return _2;
},getComponentsString:function(){
var _4=[];
for(var i=0;i<this.components.length;i++){
_4.push(this.components[i].toShortString());
}
return _4.join(",");
},calculateBounds:function(){
this.bounds=null;
if(this.components&&this.components.length>0){
this.setBounds(this.components[0].getBounds());
for(var i=1;i<this.components.length;i++){
this.extendBounds(this.components[i].getBounds());
}
}
},addComponents:function(_7){
if(!(_7 instanceof Array)){
_7=[_7];
}
for(var i=0;i<_7.length;i++){
this.addComponent(_7[i]);
}
},addComponent:function(_9,_a){
var _b=false;
if(_9){
if(this.componentTypes==null||(OpenLayers.Util.indexOf(this.componentTypes,_9.CLASS_NAME)>-1)){
if(_a!=null&&(_a<this.components.length)){
var _c=this.components.slice(0,_a);
var _d=this.components.slice(_a,this.components.length);
_c.push(_9);
this.components=_c.concat(_d);
}else{
this.components.push(_9);
}
_9.parent=this;
this.clearBounds();
_b=true;
}
}
return _b;
},removeComponents:function(_e){
if(!(_e instanceof Array)){
_e=[_e];
}
for(var i=_e.length-1;i>=0;--i){
this.removeComponent(_e[i]);
}
},removeComponent:function(_10){
OpenLayers.Util.removeItem(this.components,_10);
this.clearBounds();
},getLength:function(){
var _11=0;
for(var i=0;i<this.components.length;i++){
_11+=this.components[i].getLength();
}
return _11;
},getArea:function(){
var _13=0;
for(var i=0;i<this.components.length;i++){
_13+=this.components[i].getArea();
}
return _13;
},move:function(x,y){
for(var i=0;i<this.components.length;i++){
this.components[i].move(x,y);
}
},rotate:function(_18,_19){
for(var i=0;i<this.components.length;++i){
this.components[i].rotate(_18,_19);
}
},resize:function(_1b,_1c,_1d){
for(var i=0;i<this.components.length;++i){
this.components[i].resize(_1b,_1c,_1d);
}
},equals:function(_1f){
var _20=true;
if(!_1f||!_1f.CLASS_NAME||(this.CLASS_NAME!=_1f.CLASS_NAME)){
_20=false;
}else{
if(!(_1f.components instanceof Array)||(_1f.components.length!=this.components.length)){
_20=false;
}else{
for(var i=0;i<this.components.length;++i){
if(!this.components[i].equals(_1f.components[i])){
_20=false;
break;
}
}
}
}
return _20;
},transform:function(_22,_23){
if(_22&&_23){
for(var i=0;i<this.components.length;i++){
var _25=this.components[i];
_25.transform(_22,_23);
}
}
return this;
},intersects:function(_26){
var _27=false;
for(var i=0;i<this.components.length;++i){
_27=_26.intersects(this.components[i]);
if(_27){
break;
}
}
return _27;
},CLASS_NAME:"OpenLayers.Geometry.Collection"});

OpenLayers.Geometry.Point=OpenLayers.Class(OpenLayers.Geometry,{x:null,y:null,initialize:function(x,y){
OpenLayers.Geometry.prototype.initialize.apply(this,arguments);
this.x=parseFloat(x);
this.y=parseFloat(y);
},clone:function(_3){
if(_3==null){
_3=new OpenLayers.Geometry.Point(this.x,this.y);
}
OpenLayers.Util.applyDefaults(_3,this);
return _3;
},calculateBounds:function(){
this.bounds=new OpenLayers.Bounds(this.x,this.y,this.x,this.y);
},distanceTo:function(_4){
var _5=0;
if((this.x!=null)&&(this.y!=null)&&(_4!=null)&&(_4.x!=null)&&(_4.y!=null)){
var _6=Math.pow(this.x-_4.x,2);
var _7=Math.pow(this.y-_4.y,2);
_5=Math.sqrt(_6+_7);
}
return _5;
},equals:function(_8){
var _9=false;
if(_8!=null){
_9=((this.x==_8.x&&this.y==_8.y)||(isNaN(this.x)&&isNaN(this.y)&&isNaN(_8.x)&&isNaN(_8.y)));
}
return _9;
},toShortString:function(){
return (this.x+", "+this.y);
},move:function(x,y){
this.x=this.x+x;
this.y=this.y+y;
this.clearBounds();
},rotate:function(_c,_d){
_c*=Math.PI/180;
var _e=this.distanceTo(_d);
var _f=_c+Math.atan2(this.y-_d.y,this.x-_d.x);
this.x=_d.x+(_e*Math.cos(_f));
this.y=_d.y+(_e*Math.sin(_f));
this.clearBounds();
},resize:function(_10,_11,_12){
_12=(_12==undefined)?1:_12;
this.x=_11.x+(_10*_12*(this.x-_11.x));
this.y=_11.y+(_10*(this.y-_11.y));
this.clearBounds();
},intersects:function(_13){
var _14=false;
if(_13.CLASS_NAME=="OpenLayers.Geometry.Point"){
_14=this.equals(_13);
}else{
_14=_13.intersects(this);
}
return _14;
},transform:function(_15,_16){
if((_15&&_16)){
OpenLayers.Projection.transform(this,_15,_16);
}
return this;
},CLASS_NAME:"OpenLayers.Geometry.Point"});

OpenLayers.Geometry.MultiPoint=OpenLayers.Class(OpenLayers.Geometry.Collection,{componentTypes:["OpenLayers.Geometry.Point"],initialize:function(_1){
OpenLayers.Geometry.Collection.prototype.initialize.apply(this,arguments);
},addPoint:function(_2,_3){
this.addComponent(_2,_3);
},removePoint:function(_4){
this.removeComponent(_4);
},CLASS_NAME:"OpenLayers.Geometry.MultiPoint"});

OpenLayers.Geometry.Curve=OpenLayers.Class(OpenLayers.Geometry.MultiPoint,{componentTypes:["OpenLayers.Geometry.Point"],initialize:function(_1){
OpenLayers.Geometry.MultiPoint.prototype.initialize.apply(this,arguments);
},getLength:function(){
var _2=0;
if(this.components&&(this.components.length>1)){
for(var i=1;i<this.components.length;i++){
_2+=this.components[i-1].distanceTo(this.components[i]);
}
}
return _2;
},CLASS_NAME:"OpenLayers.Geometry.Curve"});

OpenLayers.Geometry.LineString=OpenLayers.Class(OpenLayers.Geometry.Curve,{initialize:function(_1){
OpenLayers.Geometry.Curve.prototype.initialize.apply(this,arguments);
},removeComponent:function(_2){
if(this.components&&(this.components.length>2)){
OpenLayers.Geometry.Collection.prototype.removeComponent.apply(this,arguments);
}
},intersects:function(_3){
var _4=false;
var _5=_3.CLASS_NAME;
if(_5=="OpenLayers.Geometry.LineString"||_5=="OpenLayers.Geometry.LinearRing"||_5=="OpenLayers.Geometry.Point"){
var _6=this.getSortedSegments();
var _7;
if(_5=="OpenLayers.Geometry.Point"){
_7=[{x1:_3.x,y1:_3.y,x2:_3.x,y2:_3.y}];
}else{
_7=_3.getSortedSegments();
}
var _8,seg1x1,seg1x2,seg1y1,seg1y2,seg2,seg2y1,seg2y2;
outer:
for(var i=0;i<_6.length;++i){
_8=_6[i];
seg1x1=_8.x1;
seg1x2=_8.x2;
seg1y1=_8.y1;
seg1y2=_8.y2;
inner:
for(var j=0;j<_7.length;++j){
seg2=_7[j];
if(seg2.x1>seg1x2){
break;
}
if(seg2.x2<seg1x1){
continue;
}
seg2y1=seg2.y1;
seg2y2=seg2.y2;
if(Math.min(seg2y1,seg2y2)>Math.max(seg1y1,seg1y2)){
continue;
}
if(Math.max(seg2y1,seg2y2)<Math.min(seg1y1,seg1y2)){
continue;
}
if(OpenLayers.Geometry.segmentsIntersect(_8,seg2)){
_4=true;
break outer;
}
}
}
}else{
_4=_3.intersects(this);
}
return _4;
},getSortedSegments:function(){
var _b=this.components.length-1;
var _c=new Array(_b);
for(var i=0;i<_b;++i){
point1=this.components[i];
point2=this.components[i+1];
if(point1.x<point2.x){
_c[i]={x1:point1.x,y1:point1.y,x2:point2.x,y2:point2.y};
}else{
_c[i]={x1:point2.x,y1:point2.y,x2:point1.x,y2:point1.y};
}
}
function byX1(_e,_f){
return _e.x1-_f.x1;
}
return _c.sort(byX1);
},CLASS_NAME:"OpenLayers.Geometry.LineString"});

OpenLayers.Geometry.LinearRing=OpenLayers.Class(OpenLayers.Geometry.LineString,{componentTypes:["OpenLayers.Geometry.Point"],initialize:function(_1){
OpenLayers.Geometry.LineString.prototype.initialize.apply(this,arguments);
},addComponent:function(_2,_3){
var _4=false;
var _5=this.components.pop();
if(_3!=null||!_2.equals(_5)){
_4=OpenLayers.Geometry.Collection.prototype.addComponent.apply(this,arguments);
}
var _6=this.components[0];
OpenLayers.Geometry.Collection.prototype.addComponent.apply(this,[_6]);
return _4;
},removeComponent:function(_7){
if(this.components.length>4){
this.components.pop();
OpenLayers.Geometry.Collection.prototype.removeComponent.apply(this,arguments);
var _8=this.components[0];
OpenLayers.Geometry.Collection.prototype.addComponent.apply(this,[_8]);
}
},move:function(x,y){
for(var i=0;i<this.components.length-1;i++){
this.components[i].move(x,y);
}
},rotate:function(_c,_d){
for(var i=0;i<this.components.length-1;++i){
this.components[i].rotate(_c,_d);
}
},resize:function(_f,_10,_11){
for(var i=0;i<this.components.length-1;++i){
this.components[i].resize(_f,_10,_11);
}
},transform:function(_13,_14){
if(_13&&_14){
for(var i=0;i<this.components.length-1;i++){
var _16=this.components[i];
_16.transform(_13,_14);
}
}
return this;
},getArea:function(){
var _17=0;
if(this.components&&(this.components.length>2)){
var sum=0;
for(var i=0;i<this.components.length-1;i++){
var b=this.components[i];
var c=this.components[i+1];
sum+=(b.x+c.x)*(c.y-b.y);
}
_17=-sum/2;
}
return _17;
},containsPoint:function(_1c){
var _1d=OpenLayers.Number.limitSigDigs;
var _1e=14;
var px=_1d(_1c.x,_1e);
var py=_1d(_1c.y,_1e);
function getX(y,x1,y1,x2,y2){
return (((x1-x2)*y)+((x2*y1)-(x1*y2)))/(y1-y2);
}
var _26=this.components.length-1;
var _27,end,x1,y1,x2,y2,cx,cy;
var _28=0;
for(var i=0;i<_26;++i){
_27=this.components[i];
x1=_1d(_27.x,_1e);
y1=_1d(_27.y,_1e);
end=this.components[i+1];
x2=_1d(end.x,_1e);
y2=_1d(end.y,_1e);
if(y1==y2){
if(py==y1){
if(x1<=x2&&(px>=x1&&px<=x2)||x1>=x2&&(px<=x1&&px>=x2)){
_28=-1;
break;
}
}
continue;
}
cx=_1d(getX(py,x1,y1,x2,y2),_1e);
if(cx==px){
if(y1<y2&&(py>=y1&&py<=y2)||y1>y2&&(py<=y1&&py>=y2)){
_28=-1;
break;
}
}
if(cx<=px){
continue;
}
if(x1!=x2&&(cx<Math.min(x1,x2)||cx>Math.max(x1,x2))){
continue;
}
if(y1<y2&&(py>=y1&&py<y2)||y1>y2&&(py<y1&&py>=y2)){
++_28;
}
}
var _2a=(_28==-1)?1:!!(_28&1);
return _2a;
},intersects:function(_2b){
var _2c=false;
if(_2b.CLASS_NAME=="OpenLayers.Geometry.Point"){
_2c=this.containsPoint(_2b);
}else{
if(_2b.CLASS_NAME=="OpenLayers.Geometry.LineString"){
_2c=_2b.intersects(this);
}else{
if(_2b.CLASS_NAME=="OpenLayers.Geometry.LinearRing"){
_2c=OpenLayers.Geometry.LineString.prototype.intersects.apply(this,[_2b]);
}else{
for(var i=0;i<_2b.components.length;++i){
_2c=_2b.components[i].intersects(this);
if(_2c){
break;
}
}
}
}
}
return _2c;
},CLASS_NAME:"OpenLayers.Geometry.LinearRing"});

OpenLayers.Geometry.Polygon=OpenLayers.Class(OpenLayers.Geometry.Collection,{componentTypes:["OpenLayers.Geometry.LinearRing"],initialize:function(_1){
OpenLayers.Geometry.Collection.prototype.initialize.apply(this,arguments);
},getArea:function(){
var _2=0;
if(this.components&&(this.components.length>0)){
_2+=Math.abs(this.components[0].getArea());
for(var i=1;i<this.components.length;i++){
_2-=Math.abs(this.components[i].getArea());
}
}
return _2;
},containsPoint:function(_4){
var _5=this.components.length;
var _6=false;
if(_5>0){
_6=this.components[0].containsPoint(_4);
if(_6!==1){
if(_6&&_5>1){
var _7;
for(var i=1;i<_5;++i){
_7=this.components[i].containsPoint(_4);
if(_7){
if(_7===1){
_6=1;
}else{
_6=false;
}
break;
}
}
}
}
}
return _6;
},intersects:function(_9){
var _a=false;
var i;
if(_9.CLASS_NAME=="OpenLayers.Geometry.Point"){
_a=this.containsPoint(_9);
}else{
if(_9.CLASS_NAME=="OpenLayers.Geometry.LineString"||_9.CLASS_NAME=="OpenLayers.Geometry.LinearRing"){
for(i=0;i<this.components.length;++i){
_a=_9.intersects(this.components[i]);
if(_a){
break;
}
}
if(!_a){
for(i=0;i<_9.components.length;++i){
_a=this.containsPoint(_9.components[i]);
if(_a){
break;
}
}
}
}else{
for(i=0;i<_9.components.length;++i){
_a=this.intersects(_9.components[i]);
if(_a){
break;
}
}
}
}
if(!_a&&_9.CLASS_NAME=="OpenLayers.Geometry.Polygon"){
var _c=this.components[0];
for(i=0;i<_c.components.length;++i){
_a=_9.containsPoint(_c.components[i]);
if(_a){
break;
}
}
}
return _a;
},CLASS_NAME:"OpenLayers.Geometry.Polygon"});
OpenLayers.Geometry.Polygon.createRegularPolygon=function(_d,_e,_f,_10){
var _11=Math.PI*((1/_f)-(1/2));
if(_10){
_11+=(_10/180)*Math.PI;
}
var _12,x,y;
var _13=[];
for(var i=0;i<_f;++i){
_12=_11+(i*2*Math.PI/_f);
x=_d.x+(_e*Math.cos(_12));
y=_d.y+(_e*Math.sin(_12));
_13.push(new OpenLayers.Geometry.Point(x,y));
}
var _15=new OpenLayers.Geometry.LinearRing(_13);
return new OpenLayers.Geometry.Polygon([_15]);
};

OpenLayers.Geometry.MultiLineString=OpenLayers.Class(OpenLayers.Geometry.Collection,{componentTypes:["OpenLayers.Geometry.LineString"],initialize:function(_1){
OpenLayers.Geometry.Collection.prototype.initialize.apply(this,arguments);
},CLASS_NAME:"OpenLayers.Geometry.MultiLineString"});

OpenLayers.Geometry.MultiPolygon=OpenLayers.Class(OpenLayers.Geometry.Collection,{componentTypes:["OpenLayers.Geometry.Polygon"],initialize:function(_1){
OpenLayers.Geometry.Collection.prototype.initialize.apply(this,arguments);
},CLASS_NAME:"OpenLayers.Geometry.MultiPolygon"});

OpenLayers.Geometry.Surface=OpenLayers.Class(OpenLayers.Geometry,{initialize:function(){
OpenLayers.Geometry.prototype.initialize.apply(this,arguments);
},CLASS_NAME:"OpenLayers.Geometry.Surface"});

OpenLayers.Renderer=OpenLayers.Class({container:null,extent:null,size:null,resolution:null,map:null,initialize:function(_1){
this.container=OpenLayers.Util.getElement(_1);
},destroy:function(){
this.container=null;
this.extent=null;
this.size=null;
this.resolution=null;
this.map=null;
},supported:function(){
return false;
},setExtent:function(_2){
this.extent=_2.clone();
this.resolution=null;
},setSize:function(_3){
this.size=_3.clone();
this.resolution=null;
},getResolution:function(){
this.resolution=this.resolution||this.map.getResolution();
return this.resolution;
},drawFeature:function(_4,_5){
if(_5==null){
_5=_4.style;
}
if(_4.geometry){
this.drawGeometry(_4.geometry,_5,_4.id);
}
},drawGeometry:function(_6,_7,_8){
},clear:function(){
},getFeatureIdFromEvent:function(_9){
},eraseFeatures:function(_a){
if(!(_a instanceof Array)){
_a=[_a];
}
for(var i=0;i<_a.length;++i){
this.eraseGeometry(_a[i].geometry);
}
},eraseGeometry:function(_c){
},CLASS_NAME:"OpenLayers.Renderer"});

OpenLayers.Renderer.Elements=OpenLayers.Class(OpenLayers.Renderer,{rendererRoot:null,root:null,xmlns:null,minimumSymbolizer:{strokeLinecap:"round",strokeOpacity:1,fillOpacity:1,pointRadius:0},initialize:function(_1){
OpenLayers.Renderer.prototype.initialize.apply(this,arguments);
this.rendererRoot=this.createRenderRoot();
this.root=this.createRoot();
this.rendererRoot.appendChild(this.root);
this.container.appendChild(this.rendererRoot);
},destroy:function(){
this.clear();
this.rendererRoot=null;
this.root=null;
this.xmlns=null;
OpenLayers.Renderer.prototype.destroy.apply(this,arguments);
},clear:function(){
if(this.root){
while(this.root.childNodes.length>0){
this.root.removeChild(this.root.firstChild);
}
}
},getNodeType:function(_2,_3){
},drawGeometry:function(_4,_5,_6){
var _7=_4.CLASS_NAME;
if((_7=="OpenLayers.Geometry.Collection")||(_7=="OpenLayers.Geometry.MultiPoint")||(_7=="OpenLayers.Geometry.MultiLineString")||(_7=="OpenLayers.Geometry.MultiPolygon")){
for(var i=0;i<_4.components.length;i++){
this.drawGeometry(_4.components[i],_5,_6);
}
return;
}
if(_5.display!="none"){
var _9=this.getNodeType(_4,_5);
var _a=this.nodeFactory(_4.id,_9);
_a._featureId=_6;
_a._geometryClass=_4.CLASS_NAME;
_a._style=_5;
_a=this.drawGeometryNode(_a,_4);
if(_a.parentNode!=this.root){
this.root.appendChild(_a);
}
this.postDraw(_a);
}else{
_a=OpenLayers.Util.getElement(_4.id);
if(_a){
_a.parentNode.removeChild(_a);
}
}
},drawGeometryNode:function(_b,_c,_d){
_d=_d||_b._style;
OpenLayers.Util.applyDefaults(_d,this.minimumSymbolizer);
var _e={"isFilled":true,"isStroked":!!_d.strokeWidth};
switch(_c.CLASS_NAME){
case "OpenLayers.Geometry.Point":
this.drawPoint(_b,_c);
break;
case "OpenLayers.Geometry.LineString":
_e.isFilled=false;
this.drawLineString(_b,_c);
break;
case "OpenLayers.Geometry.LinearRing":
this.drawLinearRing(_b,_c);
break;
case "OpenLayers.Geometry.Polygon":
this.drawPolygon(_b,_c);
break;
case "OpenLayers.Geometry.Surface":
this.drawSurface(_b,_c);
break;
case "OpenLayers.Geometry.Rectangle":
this.drawRectangle(_b,_c);
break;
default:
break;
}
_b._style=_d;
_b._options=_e;
return this.setStyle(_b,_d,_e,_c);
},postDraw:function(_f){
},drawPoint:function(_10,_11){
},drawLineString:function(_12,_13){
},drawLinearRing:function(_14,_15){
},drawPolygon:function(_16,_17){
},drawRectangle:function(_18,_19){
},drawCircle:function(_1a,_1b){
},drawSurface:function(_1c,_1d){
},getFeatureIdFromEvent:function(evt){
var _1f=evt.target||evt.srcElement;
return _1f._featureId;
},eraseGeometry:function(_20){
if((_20.CLASS_NAME=="OpenLayers.Geometry.MultiPoint")||(_20.CLASS_NAME=="OpenLayers.Geometry.MultiLineString")||(_20.CLASS_NAME=="OpenLayers.Geometry.MultiPolygon")||(_20.CLASS_NAME=="OpenLayers.Geometry.Collection")){
for(var i=0;i<_20.components.length;i++){
this.eraseGeometry(_20.components[i]);
}
}else{
var _22=OpenLayers.Util.getElement(_20.id);
if(_22&&_22.parentNode){
_22.parentNode.removeChild(_22);
}
}
},nodeFactory:function(id,_24){
var _25=OpenLayers.Util.getElement(id);
if(_25){
if(!this.nodeTypeCompare(_25,_24)){
_25.parentNode.removeChild(_25);
_25=this.nodeFactory(id,_24);
}
}else{
_25=this.createNode(_24,id);
}
return _25;
},CLASS_NAME:"OpenLayers.Renderer.Elements"});

OpenLayers.Renderer.SVG=OpenLayers.Class(OpenLayers.Renderer.Elements,{xmlns:"http://www.w3.org/2000/svg",MAX_PIXEL:15000,localResolution:null,initialize:function(_1){
if(!this.supported()){
return;
}
OpenLayers.Renderer.Elements.prototype.initialize.apply(this,arguments);
},destroy:function(){
OpenLayers.Renderer.Elements.prototype.destroy.apply(this,arguments);
},supported:function(){
var _2="http://www.w3.org/TR/SVG11/feature#";
return (document.implementation&&(document.implementation.hasFeature("org.w3c.svg","1.0")||document.implementation.hasFeature(_2+"SVG","1.1")||document.implementation.hasFeature(_2+"BasicStructure","1.1")));
},inValidRange:function(x,y){
return (x>=-this.MAX_PIXEL&&x<=this.MAX_PIXEL&&y>=-this.MAX_PIXEL&&y<=this.MAX_PIXEL);
},setExtent:function(_5){
OpenLayers.Renderer.Elements.prototype.setExtent.apply(this,arguments);
var _6=this.getResolution();
if(!this.localResolution||_6!=this.localResolution){
this.left=-_5.left/_6;
this.top=_5.top/_6;
}
var _7=0;
var _8=0;
if(this.localResolution&&_6==this.localResolution){
_7=(this.left)-(-_5.left/_6);
_8=(this.top)-(_5.top/_6);
}
this.localResolution=_6;
var _9=_7+" "+_8+" "+_5.getWidth()/_6+" "+_5.getHeight()/_6;
this.rendererRoot.setAttributeNS(null,"viewBox",_9);
},setSize:function(_a){
OpenLayers.Renderer.prototype.setSize.apply(this,arguments);
this.rendererRoot.setAttributeNS(null,"width",this.size.w);
this.rendererRoot.setAttributeNS(null,"height",this.size.h);
},getNodeType:function(_b,_c){
var _d=null;
switch(_b.CLASS_NAME){
case "OpenLayers.Geometry.Point":
_d=_c.externalGraphic?"image":"circle";
break;
case "OpenLayers.Geometry.Rectangle":
_d="rect";
break;
case "OpenLayers.Geometry.LineString":
_d="polyline";
break;
case "OpenLayers.Geometry.LinearRing":
_d="polygon";
break;
case "OpenLayers.Geometry.Polygon":
case "OpenLayers.Geometry.Curve":
case "OpenLayers.Geometry.Surface":
_d="path";
break;
default:
break;
}
return _d;
},setStyle:function(_e,_f,_10){
_f=_f||_e._style;
_10=_10||_e._options;
var r=parseFloat(_e.getAttributeNS(null,"r"));
if(_e._geometryClass=="OpenLayers.Geometry.Point"&&r){
if(_f.externalGraphic){
var x=parseFloat(_e.getAttributeNS(null,"cx"));
var y=parseFloat(_e.getAttributeNS(null,"cy"));
if(_f.graphicWidth&&_f.graphicHeight){
_e.setAttributeNS(null,"preserveAspectRatio","none");
}
var _14=_f.graphicWidth||_f.graphicHeight;
var _15=_f.graphicHeight||_f.graphicWidth;
_14=_14?_14:_f.pointRadius*2;
_15=_15?_15:_f.pointRadius*2;
var _16=(_f.graphicXOffset!=undefined)?_f.graphicXOffset:-(0.5*_14);
var _17=(_f.graphicYOffset!=undefined)?_f.graphicYOffset:-(0.5*_15);
var _18=_f.graphicOpacity||_f.fillOpacity;
_e.setAttributeNS(null,"x",(x+_16).toFixed());
_e.setAttributeNS(null,"y",(y+_17).toFixed());
_e.setAttributeNS(null,"width",_14);
_e.setAttributeNS(null,"height",_15);
_e.setAttributeNS("http://www.w3.org/1999/xlink","href",_f.externalGraphic);
_e.setAttributeNS(null,"style","opacity: "+_18);
}else{
_e.setAttributeNS(null,"r",_f.pointRadius);
}
if(_f.rotation){
var _19=OpenLayers.String.format("rotate(${0} ${1} ${2})",[_f.rotation,x,y]);
_e.setAttributeNS(null,"transform",_19);
}
}
if(_10.isFilled){
_e.setAttributeNS(null,"fill",_f.fillColor);
_e.setAttributeNS(null,"fill-opacity",_f.fillOpacity);
}else{
_e.setAttributeNS(null,"fill","none");
}
if(_10.isStroked){
_e.setAttributeNS(null,"stroke",_f.strokeColor);
_e.setAttributeNS(null,"stroke-opacity",_f.strokeOpacity);
_e.setAttributeNS(null,"stroke-width",_f.strokeWidth);
_e.setAttributeNS(null,"stroke-linecap",_f.strokeLinecap);
}else{
_e.setAttributeNS(null,"stroke","none");
}
if(_f.pointerEvents){
_e.setAttributeNS(null,"pointer-events",_f.pointerEvents);
}
if(_f.cursor!=null){
_e.setAttributeNS(null,"cursor",_f.cursor);
}
return _e;
},createNode:function(_1a,id){
var _1c=document.createElementNS(this.xmlns,_1a);
if(id){
_1c.setAttributeNS(null,"id",id);
}
return _1c;
},nodeTypeCompare:function(_1d,_1e){
return (_1e==_1d.nodeName);
},createRenderRoot:function(){
return this.nodeFactory(this.container.id+"_svgRoot","svg");
},createRoot:function(){
return this.nodeFactory(this.container.id+"_root","g");
},drawPoint:function(_1f,_20){
this.drawCircle(_1f,_20,1);
},drawCircle:function(_21,_22,_23){
var _24=this.getResolution();
var x=(_22.x/_24+this.left);
var y=(this.top-_22.y/_24);
if(this.inValidRange(x,y)){
_21.setAttributeNS(null,"cx",x);
_21.setAttributeNS(null,"cy",y);
_21.setAttributeNS(null,"r",_23);
}else{
_21.setAttributeNS(null,"cx","");
_21.setAttributeNS(null,"cy","");
_21.setAttributeNS(null,"r",0);
}
},drawLineString:function(_27,_28){
_27.setAttributeNS(null,"points",this.getComponentsString(_28.components));
},drawLinearRing:function(_29,_2a){
_29.setAttributeNS(null,"points",this.getComponentsString(_2a.components));
},drawPolygon:function(_2b,_2c){
var d="";
var _2e=true;
for(var j=0;j<_2c.components.length;j++){
var _30=_2c.components[j];
d+=" M";
for(var i=0;i<_30.components.length;i++){
var _32=this.getShortString(_30.components[i]);
if(_32){
d+=" "+_32;
}else{
_2e=false;
}
}
}
d+=" z";
if(_2e){
_2b.setAttributeNS(null,"d",d);
_2b.setAttributeNS(null,"fill-rule","evenodd");
}else{
_2b.setAttributeNS(null,"d","");
}
},drawRectangle:function(_33,_34){
var _35=this.getResolution();
var x=(_34.x/_35+this.left);
var y=(this.top-_34.y/_35);
if(this.inValidRange(x,y)){
_33.setAttributeNS(null,"x",x);
_33.setAttributeNS(null,"y",y);
_33.setAttributeNS(null,"width",_34.width/_35);
_33.setAttributeNS(null,"height",_34.height/_35);
}else{
_33.setAttributeNS(null,"x","");
_33.setAttributeNS(null,"y","");
_33.setAttributeNS(null,"width",0);
_33.setAttributeNS(null,"height",0);
}
},drawSurface:function(_38,_39){
var d=null;
var _3b=true;
for(var i=0;i<_39.components.length;i++){
if((i%3)==0&&(i/3)==0){
var _3d=this.getShortString(_39.components[i]);
if(!_3d){
_3b=false;
}
d="M "+_3d;
}else{
if((i%3)==1){
var _3d=this.getShortString(_39.components[i]);
if(!_3d){
_3b=false;
}
d+=" C "+_3d;
}else{
var _3d=this.getShortString(_39.components[i]);
if(!_3d){
_3b=false;
}
d+=" "+_3d;
}
}
}
d+=" Z";
if(_3b){
_38.setAttributeNS(null,"d",d);
}else{
_38.setAttributeNS(null,"d","");
}
},getComponentsString:function(_3e){
var _3f=[];
for(var i=0;i<_3e.length;i++){
var _41=this.getShortString(_3e[i]);
if(_41){
_3f.push(_41);
}
}
return _3f.join(",");
},getShortString:function(_42){
var _43=this.getResolution();
var x=(_42.x/_43+this.left);
var y=(this.top-_42.y/_43);
if(this.inValidRange(x,y)){
return x+","+y;
}else{
return false;
}
},CLASS_NAME:"OpenLayers.Renderer.SVG"});

OpenLayers.Renderer.VML=OpenLayers.Class(OpenLayers.Renderer.Elements,{xmlns:"urn:schemas-microsoft-com:vml",initialize:function(_1){
if(!this.supported()){
return;
}
if(!document.namespaces.olv){
document.namespaces.add("olv",this.xmlns);
var _2=document.createStyleSheet();
_2.addRule("olv\\:*","behavior: url(#default#VML); "+"position: absolute; display: inline-block;");
}
OpenLayers.Renderer.Elements.prototype.initialize.apply(this,arguments);
},destroy:function(){
OpenLayers.Renderer.Elements.prototype.destroy.apply(this,arguments);
},supported:function(){
return !!(document.namespaces);
},setExtent:function(_3){
OpenLayers.Renderer.Elements.prototype.setExtent.apply(this,arguments);
var _4=this.getResolution();
var _5=_3.left/_4+" "+_3.top/_4;
this.root.setAttribute("coordorigin",_5);
var _6=_3.getWidth()/_4+" "+-_3.getHeight()/_4;
this.root.setAttribute("coordsize",_6);
},setSize:function(_7){
OpenLayers.Renderer.prototype.setSize.apply(this,arguments);
this.rendererRoot.style.width=this.size.w+"px";
this.rendererRoot.style.height=this.size.h+"px";
this.root.style.width=this.size.w+"px";
this.root.style.height=this.size.h+"px";
},getNodeType:function(_8,_9){
var _a=null;
switch(_8.CLASS_NAME){
case "OpenLayers.Geometry.Point":
_a=_9.externalGraphic?"olv:rect":"olv:oval";
break;
case "OpenLayers.Geometry.Rectangle":
_a="olv:rect";
break;
case "OpenLayers.Geometry.LineString":
case "OpenLayers.Geometry.LinearRing":
case "OpenLayers.Geometry.Polygon":
case "OpenLayers.Geometry.Curve":
case "OpenLayers.Geometry.Surface":
_a="olv:shape";
break;
default:
break;
}
return _a;
},setStyle:function(_b,_c,_d,_e){
_c=_c||_b._style;
_d=_d||_b._options;
if(_b._geometryClass=="OpenLayers.Geometry.Point"){
if(_c.externalGraphic){
var _f=_c.graphicWidth||_c.graphicHeight;
var _10=_c.graphicHeight||_c.graphicWidth;
_f=_f?_f:_c.pointRadius*2;
_10=_10?_10:_c.pointRadius*2;
var _11=this.getResolution();
var _12=(_c.graphicXOffset!=undefined)?_c.graphicXOffset:-(0.5*_f);
var _13=(_c.graphicYOffset!=undefined)?_c.graphicYOffset:-(0.5*_10);
_b.style.left=((_e.x/_11)+_12).toFixed();
_b.style.top=((_e.y/_11)-(_13+_10)).toFixed();
_b.style.width=_f+"px";
_b.style.height=_10+"px";
_b.style.flip="y";
_c.fillColor="none";
_d.isStroked=false;
}else{
this.drawCircle(_b,_e,_c.pointRadius);
}
}
if(_d.isFilled){
_b.setAttribute("fillcolor",_c.fillColor);
}else{
_b.setAttribute("filled","false");
}
var _14=_b.getElementsByTagName("fill");
var _15=(_14.length==0)?null:_14[0];
if(!_d.isFilled){
if(_15){
_b.removeChild(_15);
}
}else{
if(!_15){
_15=this.createNode("olv:fill",_b.id+"_fill");
}
_15.setAttribute("opacity",_c.fillOpacity);
if(_b._geometryClass=="OpenLayers.Geometry.Point"&&_c.externalGraphic){
if(_c.graphicOpacity){
_15.setAttribute("opacity",_c.graphicOpacity);
}
_15.setAttribute("src",_c.externalGraphic);
_15.setAttribute("type","frame");
if(!(_c.graphicWidth&&_c.graphicHeight)){
_15.aspect="atmost";
}
if(_c.rotation){
this.graphicRotate(_b,_12,_13);
_15.setAttribute("opacity",0);
}
}
if(_15.parentNode!=_b){
_b.appendChild(_15);
}
}
if(_d.isStroked){
_b.setAttribute("strokecolor",_c.strokeColor);
_b.setAttribute("strokeweight",_c.strokeWidth+"px");
}else{
_b.setAttribute("stroked","false");
}
var _16=_b.getElementsByTagName("stroke");
var _17=(_16.length==0)?null:_16[0];
if(!_d.isStroked){
if(_17){
_b.removeChild(_17);
}
}else{
if(!_17){
_17=this.createNode("olv:stroke",_b.id+"_stroke");
_b.appendChild(_17);
}
_17.setAttribute("opacity",_c.strokeOpacity);
_17.setAttribute("endcap",!_c.strokeLinecap||_c.strokeLinecap=="butt"?"flat":_c.strokeLinecap);
}
if(_c.cursor!=null){
_b.style.cursor=_c.cursor;
}
return _b;
},graphicRotate:function(_18,_19,_1a){
var _1b=_1b||_18._style;
var _1c=_18._options;
var _1d,size;
if(!(_1b.graphicWidth&&_1b.graphicHeight)){
var img=new Image();
img.onreadystatechange=OpenLayers.Function.bind(function(){
if(img.readyState=="complete"||img.readyState=="interactive"){
_1d=img.width/img.height;
size=Math.max(_1b.pointRadius*2,_1b.graphicWidth||0,_1b.graphicHeight||0);
_19=_19*_1d;
_1b.graphicWidth=size*_1d;
_1b.graphicHeight=size;
this.graphicRotate(_18,_19,_1a);
}
},this);
img.src=_1b.externalGraphic;
return;
}else{
size=Math.max(_1b.graphicWidth,_1b.graphicHeight);
_1d=_1b.graphicWidth/_1b.graphicHeight;
}
var _1f=Math.round(_1b.graphicWidth||size*_1d);
var _20=Math.round(_1b.graphicHeight||size);
_18.style.width=_1f+"px";
_18.style.height=_20+"px";
var _21=document.getElementById(_18.id+"_image");
if(!_21){
_21=this.createNode("olv:imagedata",_18.id+"_image");
_18.appendChild(_21);
}
_21.style.width=_1f+"px";
_21.style.height=_20+"px";
_21.src=_1b.externalGraphic;
_21.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader("+"src='', sizingMethod='scale')";
var _22=_1b.rotation*Math.PI/180;
var _23=Math.sin(_22);
var _24=Math.cos(_22);
var _25="progid:DXImageTransform.Microsoft.Matrix(M11="+_24+",M12="+(-_23)+",M21="+_23+",M22="+_24+",SizingMethod='auto expand')\n";
var _26=_1b.graphicOpacity||_1b.fillOpacity;
if(_26&&_26!=1){
_25+="progid:DXImageTransform.Microsoft.BasicImage(opacity="+_26+")\n";
}
_18.style.filter=_25;
var _27=new OpenLayers.Geometry.Point(-_19,-_1a);
var _28=new OpenLayers.Bounds(0,0,_1f,_20).toGeometry();
_28.rotate(_1b.rotation,_27);
var _29=_28.getBounds();
_18.style.left=Math.round(parseInt(_18.style.left)+_29.left)+"px";
_18.style.top=Math.round(parseInt(_18.style.top)-_29.bottom)+"px";
},postDraw:function(_2a){
var _2b=_2a._style.fillColor;
var _2c=_2a._style.strokeColor;
if(_2b=="none"&&_2a.getAttribute("fillcolor")!=_2b){
_2a.setAttribute("fillcolor",_2b);
}
if(_2c=="none"&&_2a.getAttribute("strokecolor")!=_2c){
_2a.setAttribute("strokecolor",_2c);
}
},setNodeDimension:function(_2d,_2e){
var _2f=_2e.getBounds();
if(_2f){
var _30=this.getResolution();
var _31=new OpenLayers.Bounds((_2f.left/_30).toFixed(),(_2f.bottom/_30).toFixed(),(_2f.right/_30).toFixed(),(_2f.top/_30).toFixed());
_2d.style.left=_31.left+"px";
_2d.style.top=_31.top+"px";
_2d.style.width=_31.getWidth()+"px";
_2d.style.height=_31.getHeight()+"px";
_2d.coordorigin=_31.left+" "+_31.top;
_2d.coordsize=_31.getWidth()+" "+_31.getHeight();
}
},createNode:function(_32,id){
var _34=document.createElement(_32);
if(id){
_34.setAttribute("id",id);
}
_34.setAttribute("unselectable","on",0);
_34.onselectstart=function(){
return (false);
};
return _34;
},nodeTypeCompare:function(_35,_36){
var _37=_36;
var _38=_37.indexOf(":");
if(_38!=-1){
_37=_37.substr(_38+1);
}
var _39=_35.nodeName;
_38=_39.indexOf(":");
if(_38!=-1){
_39=_39.substr(_38+1);
}
return (_37==_39);
},createRenderRoot:function(){
return this.nodeFactory(this.container.id+"_vmlRoot","div");
},createRoot:function(){
return this.nodeFactory(this.container.id+"_root","olv:group");
},drawFeature:function(_3a,_3b){
if(!_3a.geometry.getBounds().intersectsBounds(this.extent)){
_3b={display:"none"};
}
OpenLayers.Renderer.Elements.prototype.drawFeature.apply(this,[_3a,_3b]);
},drawPoint:function(_3c,_3d){
this.drawCircle(_3c,_3d,1);
},drawCircle:function(_3e,_3f,_40){
if(!isNaN(_3f.x)&&!isNaN(_3f.y)){
var _41=this.getResolution();
_3e.style.left=((_3f.x/_41).toFixed()-_40)+"px";
_3e.style.top=((_3f.y/_41).toFixed()-_40)+"px";
var _42=_40*2;
_3e.style.width=_42+"px";
_3e.style.height=_42+"px";
}
},drawLineString:function(_43,_44){
this.drawLine(_43,_44,false);
},drawLinearRing:function(_45,_46){
this.drawLine(_45,_46,true);
},drawLine:function(_47,_48,_49){
this.setNodeDimension(_47,_48);
var _4a=this.getResolution();
var _4b=_48.components.length;
var _4c=new Array(_4b);
var _4d,x,y;
for(var i=0;i<_4b;i++){
_4d=_48.components[i];
x=(_4d.x/_4a);
y=(_4d.y/_4a);
_4c[i]=" "+x.toFixed()+","+y.toFixed()+" l ";
}
var end=(_49)?" x e":" e";
_47.path="m"+_4c.join("")+end;
},drawPolygon:function(_50,_51){
this.setNodeDimension(_50,_51);
var _52=this.getResolution();
var _53=[];
var _54,i,comp,x,y;
for(var j=0;j<_51.components.length;j++){
_54=_51.components[j];
_53.push("m");
for(i=0;i<_54.components.length;i++){
comp=_54.components[i];
x=comp.x/_52;
y=comp.y/_52;
_53.push(" "+x.toFixed()+","+y.toFixed());
if(i==0){
_53.push(" l");
}
}
_53.push(" x ");
}
_53.push("e");
_50.path=_53.join("");
},drawRectangle:function(_56,_57){
var _58=this.getResolution();
_56.style.left=_57.x/_58+"px";
_56.style.top=_57.y/_58+"px";
_56.style.width=_57.width/_58+"px";
_56.style.height=_57.height/_58+"px";
},drawSurface:function(_59,_5a){
this.setNodeDimension(_59,_5a);
var _5b=this.getResolution();
var _5c=[];
var _5d,x,y;
for(var i=0;i<_5a.components.length;i++){
_5d=_5a.components[i];
x=_5d.x/_5b;
y=_5d.y/_5b;
if((i%3)==0&&(i/3)==0){
_5c.push("m");
}else{
if((i%3)==1){
_5c.push(" c");
}
}
_5c.push(" "+x+","+y);
}
_5c.push(" x e");
_59.path=_5c.join("");
},CLASS_NAME:"OpenLayers.Renderer.VML"});

OpenLayers.Layer.Vector=OpenLayers.Class(OpenLayers.Layer,{EVENT_TYPES:["beforefeatureadded","featureadded","featuresadded","beforefeatureremoved","featureremoved","featuresremoved","featureselected","featureunselected","beforefeaturemodified","featuremodified","afterfeaturemodified"],isBaseLayer:false,isFixed:false,isVector:true,features:null,selectedFeatures:null,reportError:true,style:null,styleMap:null,renderers:["SVG","VML"],renderer:null,geometryType:null,drawn:false,initialize:function(_1,_2){
this.EVENT_TYPES=OpenLayers.Layer.Vector.prototype.EVENT_TYPES.concat(OpenLayers.Layer.prototype.EVENT_TYPES);
OpenLayers.Layer.prototype.initialize.apply(this,arguments);
if(!this.renderer||!this.renderer.supported()){
this.assignRenderer();
}
if(!this.renderer||!this.renderer.supported()){
this.renderer=null;
this.displayError();
}
if(!this.styleMap){
this.styleMap=new OpenLayers.StyleMap();
}
this.features=[];
this.selectedFeatures=[];
},destroy:function(){
this.destroyFeatures();
this.features=null;
this.selectedFeatures=null;
if(this.renderer){
this.renderer.destroy();
}
this.renderer=null;
this.geometryType=null;
this.drawn=null;
OpenLayers.Layer.prototype.destroy.apply(this,arguments);
},assignRenderer:function(){
for(var i=0;i<this.renderers.length;i++){
var _4=OpenLayers.Renderer[this.renderers[i]];
if(_4&&_4.prototype.supported()){
this.renderer=new _4(this.div);
break;
}
}
},displayError:function(){
if(this.reportError){
alert(OpenLayers.i18n("browserNotSupported",{"renderers":this.renderers.join("\n")}));
}
},setMap:function(_5){
OpenLayers.Layer.prototype.setMap.apply(this,arguments);
if(!this.renderer){
this.map.removeLayer(this);
}else{
this.renderer.map=this.map;
this.renderer.setSize(this.map.getSize());
}
},onMapResize:function(){
OpenLayers.Layer.prototype.onMapResize.apply(this,arguments);
this.renderer.setSize(this.map.getSize());
},moveTo:function(_6,_7,_8){
OpenLayers.Layer.prototype.moveTo.apply(this,arguments);
if(!_8){
this.renderer.root.style.visibility="hidden";
if(navigator.userAgent.toLowerCase().indexOf("gecko")!=-1){
this.div.scrollLeft=this.div.scrollLeft;
}
this.div.style.left=-parseInt(this.map.layerContainerDiv.style.left)+"px";
this.div.style.top=-parseInt(this.map.layerContainerDiv.style.top)+"px";
var _9=this.map.getExtent();
this.renderer.setExtent(_9);
this.renderer.root.style.visibility="visible";
}
if(!this.drawn||_7){
this.drawn=true;
for(var i=0;i<this.features.length;i++){
var _b=this.features[i];
this.drawFeature(_b);
}
}
},addFeatures:function(_c,_d){
if(!(_c instanceof Array)){
_c=[_c];
}
var _e=!_d||!_d.silent;
for(var i=0;i<_c.length;i++){
var _10=_c[i];
if(this.geometryType&&!(_10.geometry instanceof this.geometryType)){
var _11=OpenLayers.i18n("componentShouldBe",{"geomType":this.geometryType.prototype.CLASS_NAME});
throw _11;
}
this.features.push(_10);
_10.layer=this;
if(!_10.style&&this.style){
_10.style=OpenLayers.Util.extend({},this.style);
}
if(_e){
this.events.triggerEvent("beforefeatureadded",{feature:_10});
this.preFeatureInsert(_10);
}
if(this.drawn){
this.drawFeature(_10);
}
if(_e){
this.events.triggerEvent("featureadded",{feature:_10});
this.onFeatureInsert(_10);
}
}
if(_e){
this.events.triggerEvent("featuresadded",{features:_c});
}
},removeFeatures:function(_12,_13){
if(!(_12 instanceof Array)){
_12=[_12];
}
if(_12.length<=0){
return;
}
var _14=!_13||!_13.silent;
for(var i=_12.length-1;i>=0;i--){
var _16=_12[i];
if(_14){
this.events.triggerEvent("beforefeatureremoved",{feature:_16});
}
this.features=OpenLayers.Util.removeItem(this.features,_16);
if(_16.geometry){
this.renderer.eraseGeometry(_16.geometry);
}
if(OpenLayers.Util.indexOf(this.selectedFeatures,_16)!=-1){
OpenLayers.Util.removeItem(this.selectedFeatures,_16);
}
if(_14){
this.events.triggerEvent("featureremoved",{feature:_16});
}
}
if(_14){
this.events.triggerEvent("featuresremoved",{features:_12});
}
},destroyFeatures:function(_17){
var all=(_17==undefined);
if(all){
_17=this.features;
this.selectedFeatures=[];
}
this.eraseFeatures(_17);
var _19;
for(var i=_17.length-1;i>=0;i--){
_19=_17[i];
if(!all){
OpenLayers.Util.removeItem(this.selectedFeatures,_19);
}
_19.destroy();
}
},drawFeature:function(_1b,_1c){
if(typeof _1c!="object"){
var _1d=typeof _1c=="string"?_1c:_1b.renderIntent;
_1c=_1b.style||this.style;
if(!_1c){
_1c=this.styleMap.createSymbolizer(_1b,_1d);
}
}
this.renderer.drawFeature(_1b,_1c);
},eraseFeatures:function(_1e){
this.renderer.eraseFeatures(_1e);
},getFeatureFromEvent:function(evt){
if(!this.renderer){
OpenLayers.Console.error(OpenLayers.i18n("getFeatureError"));
return null;
}
var _20=this.renderer.getFeatureIdFromEvent(evt);
return this.getFeatureById(_20);
},getFeatureById:function(_21){
var _22=null;
for(var i=0;i<this.features.length;++i){
if(this.features[i].id==_21){
_22=this.features[i];
break;
}
}
return _22;
},onFeatureInsert:function(_24){
},preFeatureInsert:function(_25){
},getDataExtent:function(){
var _26=null;
if(this.features&&(this.features.length>0)){
var _26=this.features[0].geometry.getBounds();
for(var i=0;i<this.features.length;i++){
_26.extend(this.features[i].geometry.getBounds());
}
}
return _26;
},CLASS_NAME:"OpenLayers.Layer.Vector"});

OpenLayers.Layer.PointTrack=OpenLayers.Class(OpenLayers.Layer.Vector,{dataFrom:null,initialize:function(_1,_2){
OpenLayers.Layer.Vector.prototype.initialize.apply(this,arguments);
},addNodes:function(_3){
if(_3.length<2){
OpenLayers.Console.error("At least two point features have to be added to create"+"a line from");
return;
}
var _4=new Array(_3.length-1);
var _5,startPoint,endPoint;
for(var i=0;i<_3.length;i++){
_5=_3[i];
endPoint=_5.geometry;
if(!endPoint){
var _7=_5.lonlat;
endPoint=new OpenLayers.Geometry.Point(_7.lon,_7.lat);
}else{
if(endPoint.CLASS_NAME!="OpenLayers.Geometry.Point"){
OpenLayers.Console.error("Only features with point geometries are supported.");
return;
}
}
if(i>0){
var _8=(this.dataFrom!=null)?(_3[i+this.dataFrom].data||_3[i+this.dataFrom].attributes):null;
var _9=new OpenLayers.Geometry.LineString([startPoint,endPoint]);
_4[i-1]=new OpenLayers.Feature.Vector(_9,_8);
}
startPoint=endPoint;
}
this.addFeatures(_4);
},CLASS_NAME:"OpenLayers.Layer.PointTrack"});
OpenLayers.Layer.PointTrack.dataFrom={"SOURCE_NODE":-1,"TARGET_NODE":0};

OpenLayers.Layer.GML=OpenLayers.Class(OpenLayers.Layer.Vector,{loaded:false,format:null,formatOptions:null,initialize:function(_1,_2,_3){
var _4=[];
_4.push(_1,_3);
OpenLayers.Layer.Vector.prototype.initialize.apply(this,_4);
this.url=_2;
},setVisibility:function(_5,_6){
OpenLayers.Layer.Vector.prototype.setVisibility.apply(this,arguments);
if(this.visibility&&!this.loaded){
this.loadGML();
}
},moveTo:function(_7,_8,_9){
OpenLayers.Layer.Vector.prototype.moveTo.apply(this,arguments);
if(this.visibility&&!this.loaded){
this.events.triggerEvent("loadstart");
this.loadGML();
}
},loadGML:function(){
if(!this.loaded){
OpenLayers.Request.GET({url:this.url,success:this.requestSuccess,failure:this.requestFailure,scope:this});
this.loaded=true;
}
},setUrl:function(_a){
this.url=_a;
this.destroyFeatures();
this.loaded=false;
this.events.triggerEvent("loadstart");
this.loadGML();
},requestSuccess:function(_b){
var _c=_b.responseXML;
if(!_c||!_c.documentElement){
_c=_b.responseText;
}
var _d={};
OpenLayers.Util.extend(_d,this.formatOptions);
if(this.map&&!this.projection.equals(this.map.getProjectionObject())){
_d.externalProjection=this.projection;
_d.internalProjection=this.map.getProjectionObject();
}
var _e=this.format?new this.format(_d):new OpenLayers.Format.GML(_d);
this.addFeatures(_e.read(_c));
this.events.triggerEvent("loadend");
},requestFailure:function(_f){
alert(OpenLayers.i18n("errorLoadingGML",{"url":this.url}));
this.events.triggerEvent("loadend");
},CLASS_NAME:"OpenLayers.Layer.GML"});

OpenLayers.Style=OpenLayers.Class({name:null,title:null,description:null,layerName:null,isDefault:false,rules:null,context:null,defaultStyle:null,propertyStyles:null,initialize:function(_1,_2){
this.rules=[];
this.setDefaultStyle(_1||OpenLayers.Feature.Vector.style["default"]);
OpenLayers.Util.extend(this,_2);
},destroy:function(){
for(var i=0;i<this.rules.length;i++){
this.rules[i].destroy();
this.rules[i]=null;
}
this.rules=null;
this.defaultStyle=null;
},createSymbolizer:function(_4){
var _5=this.createLiterals(OpenLayers.Util.extend({},this.defaultStyle),_4);
var _6=this.rules;
var _7,context;
var _8=[];
var _9=false;
for(var i=0;i<_6.length;i++){
_7=_6[i];
var _b=_7.evaluate(_4);
if(_b){
if(_7 instanceof OpenLayers.Rule&&_7.elseFilter){
_8.push(_7);
}else{
_9=true;
this.applySymbolizer(_7,_5,_4);
}
}
}
if(_9==false&&_8.length>0){
_9=true;
for(var i=0;i<_8.length;i++){
this.applySymbolizer(_8[i],_5,_4);
}
}
if(_6.length>0&&_9==false){
_5.display="none";
}else{
_5.display="";
}
return _5;
},applySymbolizer:function(_c,_d,_e){
var _f=_e.geometry?this.getSymbolizerPrefix(_e.geometry):OpenLayers.Style.SYMBOLIZER_PREFIXES[0];
var _10=_c.symbolizer[_f]||_c.symbolizer;
return this.createLiterals(OpenLayers.Util.extend(_d,_10),_e);
},createLiterals:function(_11,_12){
var _13=this.context||_12.attributes||_12.data;
for(var i in this.propertyStyles){
_11[i]=OpenLayers.Style.createLiteral(_11[i],_13,_12);
}
return _11;
},findPropertyStyles:function(){
var _15={};
var _16=this.defaultStyle;
this.addPropertyStyles(_15,_16);
var _17=this.rules;
var _18,value;
for(var i=0;i<_17.length;i++){
var _18=_17[i].symbolizer;
for(var key in _18){
value=_18[key];
if(typeof value=="object"){
this.addPropertyStyles(_15,value);
}else{
this.addPropertyStyles(_15,_18);
break;
}
}
}
return _15;
},addPropertyStyles:function(_1b,_1c){
var _1d;
for(var key in _1c){
_1d=_1c[key];
if(typeof _1d=="string"&&_1d.match(/\$\{\w+\}/)){
_1b[key]=true;
}
}
return _1b;
},addRules:function(_1f){
this.rules=this.rules.concat(_1f);
this.propertyStyles=this.findPropertyStyles();
},setDefaultStyle:function(_20){
this.defaultStyle=_20;
this.propertyStyles=this.findPropertyStyles();
},getSymbolizerPrefix:function(_21){
var _22=OpenLayers.Style.SYMBOLIZER_PREFIXES;
for(var i=0;i<_22.length;i++){
if(_21.CLASS_NAME.indexOf(_22[i])!=-1){
return _22[i];
}
}
},CLASS_NAME:"OpenLayers.Style"});
OpenLayers.Style.createLiteral=function(_24,_25,_26){
if(typeof _24=="string"&&_24.indexOf("${")!=-1){
_24=OpenLayers.String.format(_24,_25,[_26]);
_24=(isNaN(_24)||!_24)?_24:parseFloat(_24);
}
return _24;
};
OpenLayers.Style.SYMBOLIZER_PREFIXES=["Point","Line","Polygon","Text"];

OpenLayers.StyleMap=OpenLayers.Class({styles:null,extendDefault:true,initialize:function(_1,_2){
this.styles={"default":new OpenLayers.Style(OpenLayers.Feature.Vector.style["default"]),"select":new OpenLayers.Style(OpenLayers.Feature.Vector.style["select"]),"temporary":new OpenLayers.Style(OpenLayers.Feature.Vector.style["temporary"])};
if(_1 instanceof OpenLayers.Style){
this.styles["default"]=_1;
this.styles["select"]=_1;
this.styles["temporary"]=_1;
}else{
if(typeof _1=="object"){
for(var _3 in _1){
if(_1[_3] instanceof OpenLayers.Style){
this.styles[_3]=_1[_3];
}else{
if(typeof _1[_3]=="object"){
this.styles[_3]=new OpenLayers.Style(_1[_3]);
}else{
this.styles["default"]=new OpenLayers.Style(_1);
this.styles["select"]=new OpenLayers.Style(_1);
this.styles["temporary"]=new OpenLayers.Style(_1);
break;
}
}
}
}
}
OpenLayers.Util.extend(this,_2);
},destroy:function(){
for(var _4 in this.styles){
this.styles[_4].destroy();
}
this.styles=null;
},createSymbolizer:function(_5,_6){
if(!_5){
_5=new OpenLayers.Feature.Vector();
}
if(!this.styles[_6]){
_6="default";
}
_5.renderIntent=_6;
var _7={};
if(this.extendDefault&&_6!="default"){
_7=this.styles["default"].createSymbolizer(_5);
}
return OpenLayers.Util.extend(_7,this.styles[_6].createSymbolizer(_5));
},addUniqueValueRules:function(_8,_9,_a,_b){
var _c=[];
for(var _d in _a){
_c.push(new OpenLayers.Rule({symbolizer:_a[_d],context:_b,filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO,property:_9,value:_d})}));
}
this.styles[_8].addRules(_c);
},CLASS_NAME:"OpenLayers.StyleMap"});

OpenLayers.Rule=OpenLayers.Class({id:null,name:"default",title:null,description:null,context:null,filter:null,elseFilter:false,symbolizer:null,minScaleDenominator:null,maxScaleDenominator:null,initialize:function(_1){
this.id=OpenLayers.Util.createUniqueID(this.CLASS_NAME+"_");
this.symbolizer={};
OpenLayers.Util.extend(this,_1);
},destroy:function(){
for(var i in this.symbolizer){
this.symbolizer[i]=null;
}
this.symbolizer=null;
},evaluate:function(_3){
var _4=this.getContext(_3);
var _5=true;
if(this.minScaleDenominator||this.maxScaleDenominator){
var _6=_3.layer.map.getScale();
}
if(this.minScaleDenominator){
_5=_6>=OpenLayers.Style.createLiteral(this.minScaleDenominator,_4);
}
if(_5&&this.maxScaleDenominator){
_5=_6<OpenLayers.Style.createLiteral(this.maxScaleDenominator,_4);
}
if(_5&&this.filter){
if(this.filter.CLASS_NAME=="OpenLayers.Filter.FeatureId"){
_5=this.filter.evaluate(_3);
}else{
_5=this.filter.evaluate(_4);
}
}
return _5;
},getContext:function(_7){
var _8=this.context;
if(!_8){
_8=_7.attributes||_7.data;
}
if(typeof this.context=="function"){
_8=this.context(_7);
}
return _8;
},CLASS_NAME:"OpenLayers.Rule"});

OpenLayers.Filter=OpenLayers.Class({initialize:function(_1){
OpenLayers.Util.extend(this,_1);
},destroy:function(){
},evaluate:function(_2){
return true;
},CLASS_NAME:"OpenLayers.Filter"});

OpenLayers.Filter.FeatureId=OpenLayers.Class(OpenLayers.Filter,{fids:null,initialize:function(_1){
this.fids=[];
OpenLayers.Filter.prototype.initialize.apply(this,[_1]);
},evaluate:function(_2){
for(var i=0;i<this.fids.length;i++){
var _4=_2.fid||_2.id;
if(_4==this.fids[i]){
return true;
}
}
return false;
},CLASS_NAME:"OpenLayers.Filter.FeatureId"});

OpenLayers.Filter.Logical=OpenLayers.Class(OpenLayers.Filter,{filters:null,type:null,initialize:function(_1){
this.filters=[];
OpenLayers.Filter.prototype.initialize.apply(this,[_1]);
},destroy:function(){
this.filters=null;
OpenLayers.Filter.prototype.destroy.apply(this);
},evaluate:function(_2){
switch(this.type){
case OpenLayers.Filter.Logical.AND:
for(var i=0;i<this.filters.length;i++){
if(this.filters[i].evaluate(_2)==false){
return false;
}
}
return true;
case OpenLayers.Filter.Logical.OR:
for(var i=0;i<this.filters.length;i++){
if(this.filters[i].evaluate(_2)==true){
return true;
}
}
return false;
case OpenLayers.Filter.Logical.NOT:
return (!this.filters[0].evaluate(_2));
}
},CLASS_NAME:"OpenLayers.Filter.Logical"});
OpenLayers.Filter.Logical.AND="&&";
OpenLayers.Filter.Logical.OR="||";
OpenLayers.Filter.Logical.NOT="!";

OpenLayers.Filter.Comparison=OpenLayers.Class(OpenLayers.Filter,{type:null,property:null,value:null,lowerBoundary:null,upperBoundary:null,initialize:function(_1){
OpenLayers.Filter.prototype.initialize.apply(this,[_1]);
},evaluate:function(_2){
switch(this.type){
case OpenLayers.Filter.Comparison.EQUAL_TO:
case OpenLayers.Filter.Comparison.LESS_THAN:
case OpenLayers.Filter.Comparison.GREATER_THAN:
case OpenLayers.Filter.Comparison.LESS_THAN_OR_EQUAL_TO:
case OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO:
return this.binaryCompare(_2,this.property,this.value);
case OpenLayers.Filter.Comparison.BETWEEN:
var _3=_2[this.property]>=this.lowerBoundary;
_3=_3&&_2[this.property]<=this.upperBoundary;
return _3;
case OpenLayers.Filter.Comparison.LIKE:
var _4=new RegExp(this.value,"gi");
return _4.test(_2[this.property]);
}
},value2regex:function(_5,_6,_7){
if(_5=="."){
var _8="'.' is an unsupported wildCard character for "+"OpenLayers.Filter.Comparison";
OpenLayers.Console.error(_8);
return null;
}
_5=_5?_5:"*";
_6=_6?_6:".";
_7=_7?_7:"!";
this.value=this.value.replace(new RegExp("\\"+_7,"g"),"\\");
this.value=this.value.replace(new RegExp("\\"+_6,"g"),".");
this.value=this.value.replace(new RegExp("\\"+_5,"g"),".*");
this.value=this.value.replace(new RegExp("\\\\.\\*","g"),"\\"+_5);
this.value=this.value.replace(new RegExp("\\\\\\.","g"),"\\"+_6);
return this.value;
},regex2value:function(){
var _9=this.value;
_9=_9.replace(/!/g,"!!");
_9=_9.replace(/(\\)?\\\./g,function($0,$1){
return $1?$0:"!.";
});
_9=_9.replace(/(\\)?\\\*/g,function($0,$1){
return $1?$0:"!*";
});
_9=_9.replace(/\\\\/g,"\\");
_9=_9.replace(/\.\*/g,"*");
return _9;
},binaryCompare:function(_e,_f,_10){
switch(this.type){
case OpenLayers.Filter.Comparison.EQUAL_TO:
return _e[_f]==_10;
case OpenLayers.Filter.Comparison.NOT_EQUAL_TO:
return _e[_f]!=_10;
case OpenLayers.Filter.Comparison.LESS_THAN:
return _e[_f]<_10;
case OpenLayers.Filter.Comparison.GREATER_THAN:
return _e[_f]>_10;
case OpenLayers.Filter.Comparison.LESS_THAN_OR_EQUAL_TO:
return _e[_f]<=_10;
case OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO:
return _e[_f]>=_10;
}
},CLASS_NAME:"OpenLayers.Filter.Comparison"});
OpenLayers.Filter.Comparison.EQUAL_TO="==";
OpenLayers.Filter.Comparison.NOT_EQUAL_TO="!=";
OpenLayers.Filter.Comparison.LESS_THAN="<";
OpenLayers.Filter.Comparison.GREATER_THAN=">";
OpenLayers.Filter.Comparison.LESS_THAN_OR_EQUAL_TO="<=";
OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO=">=";
OpenLayers.Filter.Comparison.BETWEEN="..";
OpenLayers.Filter.Comparison.LIKE="~";

OpenLayers.Format=OpenLayers.Class({externalProjection:null,internalProjection:null,initialize:function(_1){
OpenLayers.Util.extend(this,_1);
},read:function(_2){
alert(OpenLayers.i18n("readNotImplemented"));
},write:function(_3){
alert(OpenLayers.i18n("writeNotImplemented"));
},CLASS_NAME:"OpenLayers.Format"});

OpenLayers.Format.XML=OpenLayers.Class(OpenLayers.Format,{xmldom:null,initialize:function(_1){
if(window.ActiveXObject){
this.xmldom=new ActiveXObject("Microsoft.XMLDOM");
}
OpenLayers.Format.prototype.initialize.apply(this,[_1]);
},read:function(_2){
var _3=_2.indexOf("<");
if(_3>0){
_2=_2.substring(_3);
}
var _4=OpenLayers.Util.Try(OpenLayers.Function.bind((function(){
var _5;
if(window.ActiveXObject&&!this.xmldom){
_5=new ActiveXObject("Microsoft.XMLDOM");
}else{
_5=this.xmldom;
}
_5.loadXML(_2);
return _5;
}),this),function(){
return new DOMParser().parseFromString(_2,"text/xml");
},function(){
var _6=new XMLHttpRequest();
_6.open("GET","data:"+"text/xml"+";charset=utf-8,"+encodeURIComponent(_2),false);
if(_6.overrideMimeType){
_6.overrideMimeType("text/xml");
}
_6.send(null);
return _6.responseXML;
});
return _4;
},write:function(_7){
var _8;
if(this.xmldom){
_8=_7.xml;
}else{
var _9=new XMLSerializer();
if(_7.nodeType==1){
var _a=document.implementation.createDocument("","",null);
if(_a.importNode){
_7=_a.importNode(_7,true);
}
_a.appendChild(_7);
_8=_9.serializeToString(_a);
}else{
_8=_9.serializeToString(_7);
}
}
return _8;
},createElementNS:function(_b,_c){
var _d;
if(this.xmldom){
if(typeof _b=="string"){
_d=this.xmldom.createNode(1,_c,_b);
}else{
_d=this.xmldom.createNode(1,_c,"");
}
}else{
_d=document.createElementNS(_b,_c);
}
return _d;
},createTextNode:function(_e){
var _f;
if(this.xmldom){
_f=this.xmldom.createTextNode(_e);
}else{
_f=document.createTextNode(_e);
}
return _f;
},getElementsByTagNameNS:function(_10,uri,_12){
var _13=[];
if(_10.getElementsByTagNameNS){
_13=_10.getElementsByTagNameNS(uri,_12);
}else{
var _14=_10.getElementsByTagName("*");
var _15,fullName;
for(var i=0;i<_14.length;++i){
_15=_14[i];
fullName=(_15.prefix)?(_15.prefix+":"+_12):_12;
if((_12=="*")||(fullName==_15.nodeName)){
if((uri=="*")||(uri==_15.namespaceURI)){
_13.push(_15);
}
}
}
}
return _13;
},getAttributeNodeNS:function(_17,uri,_19){
var _1a=null;
if(_17.getAttributeNodeNS){
_1a=_17.getAttributeNodeNS(uri,_19);
}else{
var _1b=_17.attributes;
var _1c,fullName;
for(var i=0;i<_1b.length;++i){
_1c=_1b[i];
if(_1c.namespaceURI==uri){
fullName=(_1c.prefix)?(_1c.prefix+":"+_19):_19;
if(fullName==_1c.nodeName){
_1a=_1c;
break;
}
}
}
}
return _1a;
},getAttributeNS:function(_1e,uri,_20){
var _21="";
if(_1e.getAttributeNS){
_21=_1e.getAttributeNS(uri,_20)||"";
}else{
var _22=this.getAttributeNodeNS(_1e,uri,_20);
if(_22){
_21=_22.nodeValue;
}
}
return _21;
},getChildValue:function(_23,def){
var _25;
try{
_25=_23.firstChild.nodeValue;
}
catch(e){
_25=(def!=undefined)?def:"";
}
return _25;
},concatChildValues:function(_26,def){
var _28="";
var _29=_26.firstChild;
var _2a;
while(_29){
_2a=_29.nodeValue;
if(_2a){
_28+=_2a;
}
_29=_29.nextSibling;
}
if(_28==""&&def!=undefined){
_28=def;
}
return _28;
},hasAttributeNS:function(_2b,uri,_2d){
var _2e=false;
if(_2b.hasAttributeNS){
_2e=_2b.hasAttributeNS(uri,_2d);
}else{
_2e=!!this.getAttributeNodeNS(_2b,uri,_2d);
}
return _2e;
},setAttributeNS:function(_2f,uri,_31,_32){
if(_2f.setAttributeNS){
_2f.setAttributeNS(uri,_31,_32);
}else{
if(this.xmldom){
if(uri){
var _33=_2f.ownerDocument.createNode(2,_31,uri);
_33.nodeValue=_32;
_2f.setAttributeNode(_33);
}else{
_2f.setAttribute(_31,_32);
}
}else{
throw "setAttributeNS not implemented";
}
}
},CLASS_NAME:"OpenLayers.Format.XML"});

OpenLayers.Format.GML=OpenLayers.Class(OpenLayers.Format.XML,{featureNS:"http://mapserver.gis.umn.edu/mapserver",featurePrefix:"feature",featureName:"featureMember",layerName:"features",geometryName:"geometry",collectionName:"FeatureCollection",gmlns:"http://www.opengis.net/gml",extractAttributes:true,xy:true,initialize:function(_1){
this.regExes={trimSpace:(/^\s*|\s*$/g),removeSpace:(/\s*/g),splitSpace:(/\s+/),trimComma:(/\s*,\s*/g)};
OpenLayers.Format.XML.prototype.initialize.apply(this,[_1]);
},read:function(_2){
if(typeof _2=="string"){
_2=OpenLayers.Format.XML.prototype.read.apply(this,[_2]);
}
var _3=this.getElementsByTagNameNS(_2.documentElement,this.gmlns,this.featureName);
var _4=[];
for(var i=0;i<_3.length;i++){
var _6=this.parseFeature(_3[i]);
if(_6){
_4.push(_6);
}
}
return _4;
},parseFeature:function(_7){
var _8=["MultiPolygon","Polygon","MultiLineString","LineString","MultiPoint","Point","Envelope"];
var _9,nodeList,geometry,parser;
for(var i=0;i<_8.length;++i){
_9=_8[i];
nodeList=this.getElementsByTagNameNS(_7,this.gmlns,_9);
if(nodeList.length>0){
var _b=this.parseGeometry[_9.toLowerCase()];
if(_b){
geometry=_b.apply(this,[nodeList[0]]);
if(this.internalProjection&&this.externalProjection){
geometry.transform(this.externalProjection,this.internalProjection);
}
}else{
OpenLayers.Console.error(OpenLayers.i18n("unsupportedGeometryType",{"geomType":_9}));
}
break;
}
}
var _c;
if(this.extractAttributes){
_c=this.parseAttributes(_7);
}
var _d=new OpenLayers.Feature.Vector(geometry,_c);
var _e=_7.firstChild;
var _f;
while(_e){
if(_e.nodeType==1){
_f=_e.getAttribute("fid")||_e.getAttribute("id");
if(_f){
break;
}
}
_e=_e.nextSibling;
}
_d.fid=_f;
return _d;
},parseGeometry:{point:function(_10){
var _11,coordString;
var _12=[];
var _11=this.getElementsByTagNameNS(_10,this.gmlns,"pos");
if(_11.length>0){
coordString=_11[0].firstChild.nodeValue;
coordString=coordString.replace(this.regExes.trimSpace,"");
_12=coordString.split(this.regExes.splitSpace);
}
if(_12.length==0){
_11=this.getElementsByTagNameNS(_10,this.gmlns,"coordinates");
if(_11.length>0){
coordString=_11[0].firstChild.nodeValue;
coordString=coordString.replace(this.regExes.removeSpace,"");
_12=coordString.split(",");
}
}
if(_12.length==0){
_11=this.getElementsByTagNameNS(_10,this.gmlns,"coord");
if(_11.length>0){
var _13=this.getElementsByTagNameNS(_11[0],this.gmlns,"X");
var _14=this.getElementsByTagNameNS(_11[0],this.gmlns,"Y");
if(_13.length>0&&_14.length>0){
_12=[_13[0].firstChild.nodeValue,_14[0].firstChild.nodeValue];
}
}
}
if(_12.length==2){
_12[2]=null;
}
if(this.xy){
return new OpenLayers.Geometry.Point(_12[0],_12[1],_12[2]);
}else{
return new OpenLayers.Geometry.Point(_12[1],_12[0],_12[2]);
}
},multipoint:function(_15){
var _16=this.getElementsByTagNameNS(_15,this.gmlns,"Point");
var _17=[];
if(_16.length>0){
var _18;
for(var i=0;i<_16.length;++i){
_18=this.parseGeometry.point.apply(this,[_16[i]]);
if(_18){
_17.push(_18);
}
}
}
return new OpenLayers.Geometry.MultiPoint(_17);
},linestring:function(_1a,_1b){
var _1c,coordString;
var _1d=[];
var _1e=[];
_1c=this.getElementsByTagNameNS(_1a,this.gmlns,"posList");
if(_1c.length>0){
coordString=this.concatChildValues(_1c[0]);
coordString=coordString.replace(this.regExes.trimSpace,"");
_1d=coordString.split(this.regExes.splitSpace);
var dim=parseInt(_1c[0].getAttribute("dimension"));
var j,x,y,z;
for(var i=0;i<_1d.length/dim;++i){
j=i*dim;
x=_1d[j];
y=_1d[j+1];
z=(dim==2)?null:_1d[j+2];
if(this.xy){
_1e.push(new OpenLayers.Geometry.Point(x,y,z));
}else{
_1e.push(new OpenLayers.Geometry.Point(y,x,z));
}
}
}
if(_1d.length==0){
_1c=this.getElementsByTagNameNS(_1a,this.gmlns,"coordinates");
if(_1c.length>0){
coordString=this.concatChildValues(_1c[0]);
coordString=coordString.replace(this.regExes.trimSpace,"");
coordString=coordString.replace(this.regExes.trimComma,",");
var _22=coordString.split(this.regExes.splitSpace);
for(var i=0;i<_22.length;++i){
_1d=_22[i].split(",");
if(_1d.length==2){
_1d[2]=null;
}
if(this.xy){
_1e.push(new OpenLayers.Geometry.Point(_1d[0],_1d[1],_1d[2]));
}else{
_1e.push(new OpenLayers.Geometry.Point(_1d[1],_1d[0],_1d[2]));
}
}
}
}
var _23=null;
if(_1e.length!=0){
if(_1b){
_23=new OpenLayers.Geometry.LinearRing(_1e);
}else{
_23=new OpenLayers.Geometry.LineString(_1e);
}
}
return _23;
},multilinestring:function(_24){
var _25=this.getElementsByTagNameNS(_24,this.gmlns,"LineString");
var _26=[];
if(_25.length>0){
var _27;
for(var i=0;i<_25.length;++i){
_27=this.parseGeometry.linestring.apply(this,[_25[i]]);
if(_27){
_26.push(_27);
}
}
}
return new OpenLayers.Geometry.MultiLineString(_26);
},polygon:function(_29){
var _2a=this.getElementsByTagNameNS(_29,this.gmlns,"LinearRing");
var _2b=[];
if(_2a.length>0){
var _2c;
for(var i=0;i<_2a.length;++i){
_2c=this.parseGeometry.linestring.apply(this,[_2a[i],true]);
if(_2c){
_2b.push(_2c);
}
}
}
return new OpenLayers.Geometry.Polygon(_2b);
},multipolygon:function(_2e){
var _2f=this.getElementsByTagNameNS(_2e,this.gmlns,"Polygon");
var _30=[];
if(_2f.length>0){
var _31;
for(var i=0;i<_2f.length;++i){
_31=this.parseGeometry.polygon.apply(this,[_2f[i]]);
if(_31){
_30.push(_31);
}
}
}
return new OpenLayers.Geometry.MultiPolygon(_30);
},envelope:function(_33){
var _34=[];
var _35;
var _36;
var _37=this.getElementsByTagNameNS(_33,this.gmlns,"lowerCorner");
if(_37.length>0){
var _38=[];
if(_37.length>0){
_35=_37[0].firstChild.nodeValue;
_35=_35.replace(this.regExes.trimSpace,"");
_38=_35.split(this.regExes.splitSpace);
}
if(_38.length==2){
_38[2]=null;
}
if(this.xy){
var _39=new OpenLayers.Geometry.Point(_38[0],_38[1],_38[2]);
}else{
var _39=new OpenLayers.Geometry.Point(_38[1],_38[0],_38[2]);
}
}
var _3a=this.getElementsByTagNameNS(_33,this.gmlns,"upperCorner");
if(_3a.length>0){
var _38=[];
if(_3a.length>0){
_35=_3a[0].firstChild.nodeValue;
_35=_35.replace(this.regExes.trimSpace,"");
_38=_35.split(this.regExes.splitSpace);
}
if(_38.length==2){
_38[2]=null;
}
if(this.xy){
var _3b=new OpenLayers.Geometry.Point(_38[0],_38[1],_38[2]);
}else{
var _3b=new OpenLayers.Geometry.Point(_38[1],_38[0],_38[2]);
}
}
if(_39&&_3b){
_34.push(new OpenLayers.Geometry.Point(_39.x,_39.y));
_34.push(new OpenLayers.Geometry.Point(_3b.x,_39.y));
_34.push(new OpenLayers.Geometry.Point(_3b.x,_3b.y));
_34.push(new OpenLayers.Geometry.Point(_39.x,_3b.y));
_34.push(new OpenLayers.Geometry.Point(_39.x,_39.y));
var _3c=new OpenLayers.Geometry.LinearRing(_34);
_36=new OpenLayers.Geometry.Polygon([_3c]);
}
return _36;
}},parseAttributes:function(_3d){
var _3e={};
var _3f=_3d.firstChild;
var _40,i,child,grandchildren,grandchild,name,value;
while(_3f){
if(_3f.nodeType==1){
_40=_3f.childNodes;
for(i=0;i<_40.length;++i){
child=_40[i];
if(child.nodeType==1){
grandchildren=child.childNodes;
if(grandchildren.length==1){
grandchild=grandchildren[0];
if(grandchild.nodeType==3||grandchild.nodeType==4){
name=(child.prefix)?child.nodeName.split(":")[1]:child.nodeName;
value=grandchild.nodeValue.replace(this.regExes.trimSpace,"");
_3e[name]=value;
}
}
}
}
break;
}
_3f=_3f.nextSibling;
}
return _3e;
},write:function(_41){
if(!(_41 instanceof Array)){
_41=[_41];
}
var gml=this.createElementNS("http://www.opengis.net/wfs","wfs:"+this.collectionName);
for(var i=0;i<_41.length;i++){
gml.appendChild(this.createFeatureXML(_41[i]));
}
return OpenLayers.Format.XML.prototype.write.apply(this,[gml]);
},createFeatureXML:function(_44){
var _45=_44.geometry;
var _46=this.buildGeometryNode(_45);
var _47=this.createElementNS(this.featureNS,this.featurePrefix+":"+this.geometryName);
_47.appendChild(_46);
var _48=this.createElementNS(this.gmlns,"gml:"+this.featureName);
var _49=this.createElementNS(this.featureNS,this.featurePrefix+":"+this.layerName);
var fid=_44.fid||_44.id;
_49.setAttribute("fid",fid);
_49.appendChild(_47);
for(var _4b in _44.attributes){
var _4c=this.createTextNode(_44.attributes[_4b]);
var _4d=_4b.substring(_4b.lastIndexOf(":")+1);
var _4e=this.createElementNS(this.featureNS,this.featurePrefix+":"+_4d);
_4e.appendChild(_4c);
_49.appendChild(_4e);
}
_48.appendChild(_49);
return _48;
},buildGeometryNode:function(_4f){
if(this.externalProjection&&this.internalProjection){
_4f=_4f.clone();
_4f.transform(this.internalProjection,this.externalProjection);
}
var _50=_4f.CLASS_NAME;
var _51=_50.substring(_50.lastIndexOf(".")+1);
var _52=this.buildGeometry[_51.toLowerCase()];
return _52.apply(this,[_4f]);
},buildGeometry:{point:function(_53){
var gml=this.createElementNS(this.gmlns,"gml:Point");
gml.appendChild(this.buildCoordinatesNode(_53));
return gml;
},multipoint:function(_55){
var gml=this.createElementNS(this.gmlns,"gml:MultiPoint");
var _57=_55.components;
var _58,pointGeom;
for(var i=0;i<_57.length;i++){
_58=this.createElementNS(this.gmlns,"gml:pointMember");
pointGeom=this.buildGeometry.point.apply(this,[_57[i]]);
_58.appendChild(pointGeom);
gml.appendChild(_58);
}
return gml;
},linestring:function(_5a){
var gml=this.createElementNS(this.gmlns,"gml:LineString");
gml.appendChild(this.buildCoordinatesNode(_5a));
return gml;
},multilinestring:function(_5c){
var gml=this.createElementNS(this.gmlns,"gml:MultiLineString");
var _5e=_5c.components;
var _5f,lineGeom;
for(var i=0;i<_5e.length;++i){
_5f=this.createElementNS(this.gmlns,"gml:lineStringMember");
lineGeom=this.buildGeometry.linestring.apply(this,[_5e[i]]);
_5f.appendChild(lineGeom);
gml.appendChild(_5f);
}
return gml;
},linearring:function(_61){
var gml=this.createElementNS(this.gmlns,"gml:LinearRing");
gml.appendChild(this.buildCoordinatesNode(_61));
return gml;
},polygon:function(_63){
var gml=this.createElementNS(this.gmlns,"gml:Polygon");
var _65=_63.components;
var _66,ringGeom,type;
for(var i=0;i<_65.length;++i){
type=(i==0)?"outerBoundaryIs":"innerBoundaryIs";
_66=this.createElementNS(this.gmlns,"gml:"+type);
ringGeom=this.buildGeometry.linearring.apply(this,[_65[i]]);
_66.appendChild(ringGeom);
gml.appendChild(_66);
}
return gml;
},multipolygon:function(_68){
var gml=this.createElementNS(this.gmlns,"gml:MultiPolygon");
var _6a=_68.components;
var _6b,polyGeom;
for(var i=0;i<_6a.length;++i){
_6b=this.createElementNS(this.gmlns,"gml:polygonMember");
polyGeom=this.buildGeometry.polygon.apply(this,[_6a[i]]);
_6b.appendChild(polyGeom);
gml.appendChild(_6b);
}
return gml;
}},buildCoordinatesNode:function(_6d){
var _6e=this.createElementNS(this.gmlns,"gml:coordinates");
_6e.setAttribute("decimal",".");
_6e.setAttribute("cs",",");
_6e.setAttribute("ts"," ");
var _6f=(_6d.components)?_6d.components:[_6d];
var _70=[];
for(var i=0;i<_6f.length;i++){
_70.push(_6f[i].x+","+_6f[i].y);
}
var _72=this.createTextNode(_70.join(" "));
_6e.appendChild(_72);
return _6e;
},CLASS_NAME:"OpenLayers.Format.GML"});

OpenLayers.Format.KML=OpenLayers.Class(OpenLayers.Format.XML,{kmlns:"http://earth.google.com/kml/2.0",placemarksDesc:"No description available",foldersName:"OpenLayers export",foldersDesc:"Exported on "+new Date(),extractAttributes:true,extractStyles:false,internalns:null,features:null,styles:null,styleBaseUrl:"",fetched:null,maxDepth:0,initialize:function(_1){
this.regExes={trimSpace:(/^\s*|\s*$/g),removeSpace:(/\s*/g),splitSpace:(/\s+/),trimComma:(/\s*,\s*/g),kmlColor:(/(\w{2})(\w{2})(\w{2})(\w{2})/),kmlIconPalette:(/root:\/\/icons\/palette-(\d+)(\.\w+)/),straightBracket:(/\$\[(.*?)\]/g)};
OpenLayers.Format.XML.prototype.initialize.apply(this,[_1]);
},read:function(_2){
this.features=[];
this.styles={};
this.fetched={};
var _3={depth:this.maxDepth,styleBaseUrl:this.styleBaseUrl};
return this.parseData(_2,_3);
},parseData:function(_4,_5){
if(typeof _4=="string"){
_4=OpenLayers.Format.XML.prototype.read.apply(this,[_4]);
}
var _6=["Link","NetworkLink","Style","StyleMap","Placemark"];
for(var i=0;i<_6.length;++i){
var _8=_6[i];
var _9=this.getElementsByTagNameNS(_4,"*",_8);
if(_9.length==0){
continue;
}
switch(_8.toLowerCase()){
case "link":
case "networklink":
this.parseLinks(_9,_5);
break;
case "style":
if(this.extractStyles){
this.parseStyles(_9,_5);
}
break;
case "stylemap":
if(this.extractStyles){
this.parseStyleMaps(_9,_5);
}
break;
case "placemark":
this.parseFeatures(_9,_5);
break;
}
}
return this.features;
},parseLinks:function(_a,_b){
if(_b.depth>=this.maxDepth){
return false;
}
var _c=OpenLayers.Util.extend({},_b);
_c.depth++;
for(var i=0;i<_a.length;i++){
var _e=this.parseProperty(_a[i],"*","href");
if(_e&&!this.fetched[_e]){
this.fetched[_e]=true;
var _f=this.fetchLink(_e);
if(_f){
this.parseData(_f,_c);
}
}
}
},fetchLink:function(_10){
var _11=OpenLayers.Request.GET({url:_10,async:false});
if(_11){
return _11.responseText;
}
},parseStyles:function(_12,_13){
for(var i=0;i<_12.length;i++){
var _15=this.parseStyle(_12[i]);
if(_15){
styleName=(_13.styleBaseUrl||"")+"#"+_15.id;
this.styles[styleName]=_15;
}
}
},parseStyle:function(_16){
var _17={};
var _18=["LineStyle","PolyStyle","IconStyle","BalloonStyle"];
var _19,nodeList,geometry,parser;
for(var i=0;i<_18.length;++i){
_19=_18[i];
styleTypeNode=this.getElementsByTagNameNS(_16,"*",_19)[0];
if(!styleTypeNode){
continue;
}
switch(_19.toLowerCase()){
case "linestyle":
var _1b=this.parseProperty(styleTypeNode,"*","color");
if(_1b){
var _1c=(_1b.toString()).match(this.regExes.kmlColor);
var _1d=_1c[1];
_17["strokeOpacity"]=parseInt(_1d,16)/255;
var b=_1c[2];
var g=_1c[3];
var r=_1c[4];
_17["strokeColor"]="#"+r+g+b;
}
var _21=this.parseProperty(styleTypeNode,"*","width");
if(_21){
_17["strokeWidth"]=_21;
}
case "polystyle":
var _1b=this.parseProperty(styleTypeNode,"*","color");
if(_1b){
var _1c=(_1b.toString()).match(this.regExes.kmlColor);
var _1d=_1c[1];
_17["fillOpacity"]=parseInt(_1d,16)/255;
var b=_1c[2];
var g=_1c[3];
var r=_1c[4];
_17["fillColor"]="#"+r+g+b;
}
break;
case "iconstyle":
var _22=parseFloat(this.parseProperty(styleTypeNode,"*","scale")||1);
var _21=32*_22;
var _23=32*_22;
var _24=this.getElementsByTagNameNS(styleTypeNode,"*","Icon")[0];
if(_24){
var _25=this.parseProperty(_24,"*","href");
if(_25){
var w=this.parseProperty(_24,"*","w");
var h=this.parseProperty(_24,"*","h");
var _28="http://maps.google.com/mapfiles/kml";
if(OpenLayers.String.startsWith(_25,_28)&&!w&&!h){
w=64;
h=64;
_22=_22/2;
}
w=w||h;
h=h||w;
if(w){
_21=parseInt(w)*_22;
}
if(h){
_23=parseInt(h)*_22;
}
var _1c=_25.match(this.regExes.kmlIconPalette);
if(_1c){
var _29=_1c[1];
var _2a=_1c[2];
var x=this.parseProperty(_24,"*","x");
var y=this.parseProperty(_24,"*","y");
var _2d=x?x/32:0;
var _2e=y?(7-y/32):7;
var pos=_2e*8+_2d;
_25="http://maps.google.com/mapfiles/kml/pal"+_29+"/icon"+pos+_2a;
}
_17["graphicOpacity"]=1;
_17["externalGraphic"]=_25;
}
}
var _30=this.getElementsByTagNameNS(styleTypeNode,"*","hotSpot")[0];
if(_30){
var x=parseFloat(_30.getAttribute("x"));
var y=parseFloat(_30.getAttribute("y"));
var _31=_30.getAttribute("xunits");
if(_31=="pixels"){
_17["graphicXOffset"]=-x*_22;
}else{
if(_31=="insetPixels"){
_17["graphicXOffset"]=-_21+(x*_22);
}else{
if(_31=="fraction"){
_17["graphicXOffset"]=-_21*x;
}
}
}
var _32=_30.getAttribute("yunits");
if(_32=="pixels"){
_17["graphicYOffset"]=-_23+(y*_22)+1;
}else{
if(_32=="insetPixels"){
_17["graphicYOffset"]=-(y*_22)+1;
}else{
if(_32=="fraction"){
_17["graphicYOffset"]=-_23*(1-y)+1;
}
}
}
}
_17["graphicWidth"]=_21;
_17["graphicHeight"]=_23;
break;
case "balloonstyle":
var _33=OpenLayers.Util.getXmlNodeValue(styleTypeNode);
if(_33){
_17["balloonStyle"]=_33.replace(this.regExes.straightBracket,"${$1}");
}
break;
default:
}
}
if(!_17["strokeColor"]&&_17["fillColor"]){
_17["strokeColor"]=_17["fillColor"];
}
var id=_16.getAttribute("id");
if(id&&_17){
_17.id=id;
}
return _17;
},parseStyleMaps:function(_35,_36){
for(var i=0;i<_35.length;i++){
var _38=_35[i];
var _39=this.getElementsByTagNameNS(_38,"*","Pair");
var id=_38.getAttribute("id");
for(var j=0;j<_39.length;j++){
var _3c=_39[j];
var key=this.parseProperty(_3c,"*","key");
var _3e=this.parseProperty(_3c,"*","styleUrl");
if(_3e&&key=="normal"){
this.styles[(_36.styleBaseUrl||"")+"#"+id]=this.styles[(_36.styleBaseUrl||"")+_3e];
}
if(_3e&&key=="highlight"){
}
}
}
},parseFeatures:function(_3f,_40){
var _41=new Array(_3f.length);
for(var i=0;i<_3f.length;i++){
var _43=_3f[i];
var _44=this.parseFeature.apply(this,[_43]);
if(_44){
if(this.extractStyles&&_44.attributes&&_44.attributes.styleUrl){
_44.style=this.getStyle(_44.attributes.styleUrl);
}
var _45=this.getElementsByTagNameNS(_43,"*","Style")[0];
if(_45){
var _46=this.parseStyle(_45);
if(_46){
_44.style=OpenLayers.Util.extend(_44.style,_46);
}
}
_41[i]=_44;
}else{
throw "Bad Placemark: "+i;
}
}
this.features=this.features.concat(_41);
},parseFeature:function(_47){
var _48=["MultiGeometry","Polygon","LineString","Point"];
var _49,nodeList,geometry,parser;
for(var i=0;i<_48.length;++i){
_49=_48[i];
this.internalns=_47.namespaceURI?_47.namespaceURI:this.kmlns;
nodeList=this.getElementsByTagNameNS(_47,this.internalns,_49);
if(nodeList.length>0){
var _4b=this.parseGeometry[_49.toLowerCase()];
if(_4b){
geometry=_4b.apply(this,[nodeList[0]]);
if(this.internalProjection&&this.externalProjection){
geometry.transform(this.externalProjection,this.internalProjection);
}
}else{
OpenLayers.Console.error(OpenLayers.i18n("unsupportedGeometryType",{"geomType":_49}));
}
break;
}
}
var _4c;
if(this.extractAttributes){
_4c=this.parseAttributes(_47);
}
var _4d=new OpenLayers.Feature.Vector(geometry,_4c);
var fid=_47.getAttribute("id")||_47.getAttribute("name");
if(fid!=null){
_4d.fid=fid;
}
return _4d;
},getStyle:function(_4f,_50){
var _51=OpenLayers.Util.removeTail(_4f);
var _52=OpenLayers.Util.extend({},_50);
_52.depth++;
_52.styleBaseUrl=_51;
if(!this.styles[_4f]&&!OpenLayers.String.startsWith(_4f,"#")&&_52.depth<=this.maxDepth&&!this.fetched[_51]){
var _53=this.fetchLink(_51);
if(_53){
this.parseData(_53,_52);
}
}
var _54=this.styles[_4f];
return _54;
},parseGeometry:{point:function(_55){
var _56=this.getElementsByTagNameNS(_55,this.internalns,"coordinates");
var _57=[];
if(_56.length>0){
var _58=_56[0].firstChild.nodeValue;
_58=_58.replace(this.regExes.removeSpace,"");
_57=_58.split(",");
}
var _59=null;
if(_57.length>1){
if(_57.length==2){
_57[2]=null;
}
_59=new OpenLayers.Geometry.Point(_57[0],_57[1],_57[2]);
}else{
throw "Bad coordinate string: "+_58;
}
return _59;
},linestring:function(_5a,_5b){
var _5c=this.getElementsByTagNameNS(_5a,this.internalns,"coordinates");
var _5d=null;
if(_5c.length>0){
var _5e=this.concatChildValues(_5c[0]);
_5e=_5e.replace(this.regExes.trimSpace,"");
_5e=_5e.replace(this.regExes.trimComma,",");
var _5f=_5e.split(this.regExes.splitSpace);
var _60=_5f.length;
var _61=new Array(_60);
var _62,numCoords;
for(var i=0;i<_60;++i){
_62=_5f[i].split(",");
numCoords=_62.length;
if(numCoords>1){
if(_62.length==2){
_62[2]=null;
}
_61[i]=new OpenLayers.Geometry.Point(_62[0],_62[1],_62[2]);
}else{
throw "Bad LineString point coordinates: "+_5f[i];
}
}
if(_60){
if(_5b){
_5d=new OpenLayers.Geometry.LinearRing(_61);
}else{
_5d=new OpenLayers.Geometry.LineString(_61);
}
}else{
throw "Bad LineString coordinates: "+_5e;
}
}
return _5d;
},polygon:function(_64){
var _65=this.getElementsByTagNameNS(_64,this.internalns,"LinearRing");
var _66=_65.length;
var _67=new Array(_66);
if(_66>0){
var _68;
for(var i=0;i<_65.length;++i){
_68=this.parseGeometry.linestring.apply(this,[_65[i],true]);
if(_68){
_67[i]=_68;
}else{
throw "Bad LinearRing geometry: "+i;
}
}
}
return new OpenLayers.Geometry.Polygon(_67);
},multigeometry:function(_6a){
var _6b,parser;
var _6c=[];
var _6d=_6a.childNodes;
for(var i=0;i<_6d.length;++i){
_6b=_6d[i];
if(_6b.nodeType==1){
var _6f=(_6b.prefix)?_6b.nodeName.split(":")[1]:_6b.nodeName;
var _70=this.parseGeometry[_6f.toLowerCase()];
if(_70){
_6c.push(_70.apply(this,[_6b]));
}
}
}
return new OpenLayers.Geometry.Collection(_6c);
}},parseAttributes:function(_71){
var _72={};
var _73,grandchildren,grandchild;
var _74=_71.childNodes;
for(var i=0;i<_74.length;++i){
_73=_74[i];
if(_73.nodeType==1){
grandchildren=_73.childNodes;
if(grandchildren.length==1||grandchildren.length==3){
var _76;
switch(grandchildren.length){
case 1:
_76=grandchildren[0];
break;
case 3:
default:
_76=grandchildren[1];
break;
}
if(_76.nodeType==3||_76.nodeType==4){
var _77=(_73.prefix)?_73.nodeName.split(":")[1]:_73.nodeName;
var _78=OpenLayers.Util.getXmlNodeValue(_76);
if(_78){
_78=_78.replace(this.regExes.trimSpace,"");
_72[_77]=_78;
}
}
}
}
}
return _72;
},parseProperty:function(_79,_7a,_7b){
var _7c;
var _7d=this.getElementsByTagNameNS(_79,_7a,_7b);
try{
_7c=OpenLayers.Util.getXmlNodeValue(_7d[0]);
}
catch(e){
_7c=null;
}
return _7c;
},write:function(_7e){
if(!(_7e instanceof Array)){
_7e=[_7e];
}
var kml=this.createElementNS(this.kmlns,"kml");
var _80=this.createFolderXML();
for(var i=0;i<_7e.length;++i){
_80.appendChild(this.createPlacemarkXML(_7e[i]));
}
kml.appendChild(_80);
return OpenLayers.Format.XML.prototype.write.apply(this,[kml]);
},createFolderXML:function(){
var _82=this.createElementNS(this.kmlns,"name");
var _83=this.createTextNode(this.foldersName);
_82.appendChild(_83);
var _84=this.createElementNS(this.kmlns,"description");
var _85=this.createTextNode(this.foldersDesc);
_84.appendChild(_85);
var _86=this.createElementNS(this.kmlns,"Folder");
_86.appendChild(_82);
_86.appendChild(_84);
return _86;
},createPlacemarkXML:function(_87){
var _88=this.createElementNS(this.kmlns,"name");
var _89=(_87.attributes.name)?_87.attributes.name:_87.id;
_88.appendChild(this.createTextNode(_89));
var _8a=this.createElementNS(this.kmlns,"description");
var _8b=(_87.attributes.description)?_87.attributes.description:this.placemarksDesc;
_8a.appendChild(this.createTextNode(_8b));
var _8c=this.createElementNS(this.kmlns,"Placemark");
if(_87.fid!=null){
_8c.setAttribute("id",_87.fid);
}
_8c.appendChild(_88);
_8c.appendChild(_8a);
var _8d=this.buildGeometryNode(_87.geometry);
_8c.appendChild(_8d);
return _8c;
},buildGeometryNode:function(_8e){
if(this.internalProjection&&this.externalProjection){
_8e=_8e.clone();
_8e.transform(this.internalProjection,this.externalProjection);
}
var _8f=_8e.CLASS_NAME;
var _90=_8f.substring(_8f.lastIndexOf(".")+1);
var _91=this.buildGeometry[_90.toLowerCase()];
var _92=null;
if(_91){
_92=_91.apply(this,[_8e]);
}
return _92;
},buildGeometry:{point:function(_93){
var kml=this.createElementNS(this.kmlns,"Point");
kml.appendChild(this.buildCoordinatesNode(_93));
return kml;
},multipoint:function(_95){
return this.buildGeometry.collection.apply(this,[_95]);
},linestring:function(_96){
var kml=this.createElementNS(this.kmlns,"LineString");
kml.appendChild(this.buildCoordinatesNode(_96));
return kml;
},multilinestring:function(_98){
return this.buildGeometry.collection.apply(this,[_98]);
},linearring:function(_99){
var kml=this.createElementNS(this.kmlns,"LinearRing");
kml.appendChild(this.buildCoordinatesNode(_99));
return kml;
},polygon:function(_9b){
var kml=this.createElementNS(this.kmlns,"Polygon");
var _9d=_9b.components;
var _9e,ringGeom,type;
for(var i=0;i<_9d.length;++i){
type=(i==0)?"outerBoundaryIs":"innerBoundaryIs";
_9e=this.createElementNS(this.kmlns,type);
ringGeom=this.buildGeometry.linearring.apply(this,[_9d[i]]);
_9e.appendChild(ringGeom);
kml.appendChild(_9e);
}
return kml;
},multipolygon:function(_a0){
return this.buildGeometry.collection.apply(this,[_a0]);
},collection:function(_a1){
var kml=this.createElementNS(this.kmlns,"MultiGeometry");
var _a3;
for(var i=0;i<_a1.components.length;++i){
_a3=this.buildGeometryNode.apply(this,[_a1.components[i]]);
if(_a3){
kml.appendChild(_a3);
}
}
return kml;
}},buildCoordinatesNode:function(_a5){
var _a6=this.createElementNS(this.kmlns,"coordinates");
var _a7;
var _a8=_a5.components;
if(_a8){
var _a9;
var _aa=_a8.length;
var _ab=new Array(_aa);
for(var i=0;i<_aa;++i){
_a9=_a8[i];
_ab[i]=_a9.x+","+_a9.y;
}
_a7=_ab.join(" ");
}else{
_a7=_a5.x+","+_a5.y;
}
var _ad=this.createTextNode(_a7);
_a6.appendChild(_ad);
return _a6;
},CLASS_NAME:"OpenLayers.Format.KML"});

OpenLayers.Format.GeoRSS=OpenLayers.Class(OpenLayers.Format.XML,{rssns:"http://backend.userland.com/rss2",featureNS:"http://mapserver.gis.umn.edu/mapserver",georssns:"http://www.georss.org/georss",geons:"http://www.w3.org/2003/01/geo/wgs84_pos#",featureTitle:"Untitled",featureDescription:"No Description",gmlParser:null,xy:false,initialize:function(_1){
OpenLayers.Format.XML.prototype.initialize.apply(this,[_1]);
},createGeometryFromItem:function(_2){
var _3=this.getElementsByTagNameNS(_2,this.georssns,"point");
var _4=this.getElementsByTagNameNS(_2,this.geons,"lat");
var _5=this.getElementsByTagNameNS(_2,this.geons,"long");
var _6=this.getElementsByTagNameNS(_2,this.georssns,"line");
var _7=this.getElementsByTagNameNS(_2,this.georssns,"polygon");
var _8=this.getElementsByTagNameNS(_2,this.georssns,"where");
if(_3.length>0||(_4.length>0&&_5.length>0)){
var _9;
if(_3.length>0){
_9=OpenLayers.String.trim(_3[0].firstChild.nodeValue).split(/\s+/);
if(_9.length!=2){
_9=OpenLayers.String.trim(_3[0].firstChild.nodeValue).split(/\s*,\s*/);
}
}else{
_9=[parseFloat(_4[0].firstChild.nodeValue),parseFloat(_5[0].firstChild.nodeValue)];
}
var _a=new OpenLayers.Geometry.Point(parseFloat(_9[1]),parseFloat(_9[0]));
}else{
if(_6.length>0){
var _b=OpenLayers.String.trim(_6[0].firstChild.nodeValue).split(/\s+/);
var _c=[];
var _3;
for(var i=0;i<_b.length;i+=2){
_3=new OpenLayers.Geometry.Point(parseFloat(_b[i+1]),parseFloat(_b[i]));
_c.push(_3);
}
_a=new OpenLayers.Geometry.LineString(_c);
}else{
if(_7.length>0){
var _b=OpenLayers.String.trim(_7[0].firstChild.nodeValue).split(/\s+/);
var _c=[];
var _3;
for(var i=0;i<_b.length;i+=2){
_3=new OpenLayers.Geometry.Point(parseFloat(_b[i+1]),parseFloat(_b[i]));
_c.push(_3);
}
_a=new OpenLayers.Geometry.Polygon([new OpenLayers.Geometry.LinearRing(_c)]);
}else{
if(_8.length>0){
if(!this.gmlParser){
this.gmlParser=new OpenLayers.Format.GML({"xy":this.xy});
}
var _e=this.gmlParser.parseFeature(_8[0]);
_a=_e.geometry;
}
}
}
}
if(_a&&this.internalProjection&&this.externalProjection){
_a.transform(this.externalProjection,this.internalProjection);
}
return _a;
},createFeatureFromItem:function(_f){
var _10=this.createGeometryFromItem(_f);
var _11=this.getChildValue(_f,"*","title",this.featureTitle);
var _12=this.getChildValue(_f,"*","description",this.getChildValue(_f,"*","content",this.featureDescription));
var _13=this.getChildValue(_f,"*","link");
if(!_13){
try{
_13=this.getElementsByTagNameNS(_f,"*","link")[0].getAttribute("href");
}
catch(e){
_13=null;
}
}
var id=this.getChildValue(_f,"*","id",null);
var _15={"title":_11,"description":_12,"link":_13};
var _16=new OpenLayers.Feature.Vector(_10,_15);
_16.fid=id;
return _16;
},getChildValue:function(_17,_18,_19,def){
var _1b;
var _1c=this.getElementsByTagNameNS(_17,_18,_19);
if(_1c&&_1c[0]&&_1c[0].firstChild&&_1c[0].firstChild.nodeValue){
_1b=_1c[0].firstChild.nodeValue;
}else{
_1b=(def==undefined)?"":def;
}
return _1b;
},read:function(doc){
if(typeof doc=="string"){
doc=OpenLayers.Format.XML.prototype.read.apply(this,[doc]);
}
var _1e=null;
_1e=this.getElementsByTagNameNS(doc,"*","item");
if(_1e.length==0){
_1e=this.getElementsByTagNameNS(doc,"*","entry");
}
var _1f=_1e.length;
var _20=new Array(_1f);
for(var i=0;i<_1f;i++){
_20[i]=this.createFeatureFromItem(_1e[i]);
}
return _20;
},write:function(_22){
var _23;
if(_22 instanceof Array){
_23=this.createElementNS(this.rssns,"rss");
for(var i=0;i<_22.length;i++){
_23.appendChild(this.createFeatureXML(_22[i]));
}
}else{
_23=this.createFeatureXML(_22);
}
return OpenLayers.Format.XML.prototype.write.apply(this,[_23]);
},createFeatureXML:function(_25){
var _26=this.buildGeometryNode(_25.geometry);
var _27=this.createElementNS(this.rssns,"item");
var _28=this.createElementNS(this.rssns,"title");
_28.appendChild(this.createTextNode(_25.attributes.title?_25.attributes.title:""));
var _29=this.createElementNS(this.rssns,"description");
_29.appendChild(this.createTextNode(_25.attributes.description?_25.attributes.description:""));
_27.appendChild(_28);
_27.appendChild(_29);
if(_25.attributes.link){
var _2a=this.createElementNS(this.rssns,"link");
_2a.appendChild(this.createTextNode(_25.attributes.link));
_27.appendChild(_2a);
}
for(var _2b in _25.attributes){
if(_2b=="link"||_2b=="title"||_2b=="description"){
continue;
}
var _2c=this.createTextNode(_25.attributes[_2b]);
var _2d=_2b;
if(_2b.search(":")!=-1){
_2d=_2b.split(":")[1];
}
var _2e=this.createElementNS(this.featureNS,"feature:"+_2d);
_2e.appendChild(_2c);
_27.appendChild(_2e);
}
_27.appendChild(_26);
return _27;
},buildGeometryNode:function(_2f){
if(this.internalProjection&&this.externalProjection){
_2f=_2f.clone();
_2f.transform(this.internalProjection,this.externalProjection);
}
var _30;
if(_2f.CLASS_NAME=="OpenLayers.Geometry.Polygon"){
_30=this.createElementNS(this.georssns,"georss:polygon");
_30.appendChild(this.buildCoordinatesNode(_2f.components[0]));
}else{
if(_2f.CLASS_NAME=="OpenLayers.Geometry.LineString"){
_30=this.createElementNS(this.georssns,"georss:line");
_30.appendChild(this.buildCoordinatesNode(_2f));
}else{
if(_2f.CLASS_NAME=="OpenLayers.Geometry.Point"){
_30=this.createElementNS(this.georssns,"georss:point");
_30.appendChild(this.buildCoordinatesNode(_2f));
}else{
throw "Couldn't parse "+_2f.CLASS_NAME;
}
}
}
return _30;
},buildCoordinatesNode:function(_31){
var _32=null;
if(_31.components){
_32=_31.components;
}
var _33;
if(_32){
var _34=_32.length;
var _35=new Array(_34);
for(var i=0;i<_34;i++){
_35[i]=_32[i].y+" "+_32[i].x;
}
_33=_35.join(" ");
}else{
_33=_31.y+" "+_31.x;
}
return this.createTextNode(_33);
},CLASS_NAME:"OpenLayers.Format.GeoRSS"});

OpenLayers.Format.OSM=OpenLayers.Class(OpenLayers.Format.XML,{checkTags:false,interestingTagsExclude:null,areaTags:null,initialize:function(_1){
var _2={"interestingTagsExclude":["source","source_ref","source:ref","history","attribution","created_by"],"areaTags":["area","building","leisure","tourism","ruins","historic","landuse","military","natural","sport"]};
_2=OpenLayers.Util.extend(_2,_1);
var _3={};
for(var i=0;i<_2.interestingTagsExclude.length;i++){
_3[_2.interestingTagsExclude[i]]=true;
}
_2.interestingTagsExclude=_3;
var _5={};
for(var i=0;i<_2.areaTags.length;i++){
_5[_2.areaTags[i]]=true;
}
_2.areaTags=_5;
OpenLayers.Format.XML.prototype.initialize.apply(this,[_2]);
},read:function(_6){
if(typeof _6=="string"){
_6=OpenLayers.Format.XML.prototype.read.apply(this,[_6]);
}
var _7=this.getNodes(_6);
var _8=this.getWays(_6);
var _9=new Array(_8.length);
for(var i=0;i<_8.length;i++){
var _b=new Array(_8[i].nodes.length);
var _c=this.isWayArea(_8[i])?1:0;
for(var j=0;j<_8[i].nodes.length;j++){
var _e=_7[_8[i].nodes[j]];
var _f=new OpenLayers.Geometry.Point(_e.lon,_e.lat);
_f.osm_id=parseInt(_8[i].nodes[j]);
_b[j]=_f;
_e.used=true;
}
var _10=null;
if(_c){
_10=new OpenLayers.Geometry.Polygon(new OpenLayers.Geometry.LinearRing(_b));
}else{
_10=new OpenLayers.Geometry.LineString(_b);
}
if(this.internalProjection&&this.externalProjection){
_10.transform(this.externalProjection,this.internalProjection);
}
var _11=new OpenLayers.Feature.Vector(_10,_8[i].tags);
_11.osm_id=parseInt(_8[i].id);
_11.fid="way."+_11.osm_id;
_9[i]=_11;
}
for(var _12 in _7){
var _e=_7[_12];
if(!_e.used||this.checkTags){
var _13=null;
if(this.checkTags){
var _14=this.getTags(_e.node,true);
if(_e.used&&!_14[1]){
continue;
}
_13=_14[0];
}else{
_13=this.getTags(_e.node);
}
var _11=new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(_e["lon"],_e["lat"]),_13);
if(this.internalProjection&&this.externalProjection){
_11.geometry.transform(this.externalProjection,this.internalProjection);
}
_11.osm_id=parseInt(_12);
_11.fid="node."+_11.osm_id;
_9.push(_11);
}
_e.node=null;
}
return _9;
},getNodes:function(doc){
var _16=doc.getElementsByTagName("node");
var _17={};
for(var i=0;i<_16.length;i++){
var _19=_16[i];
var id=_19.getAttribute("id");
_17[id]={"lat":_19.getAttribute("lat"),"lon":_19.getAttribute("lon"),"node":_19};
}
return _17;
},getWays:function(doc){
var _1c=doc.getElementsByTagName("way");
var _1d=[];
for(var i=0;i<_1c.length;i++){
var way=_1c[i];
var _20={id:way.getAttribute("id")};
_20.tags=this.getTags(way);
var _21=way.getElementsByTagName("nd");
_20.nodes=new Array(_21.length);
for(var j=0;j<_21.length;j++){
_20.nodes[j]=_21[j].getAttribute("ref");
}
_1d.push(_20);
}
return _1d;
},getTags:function(_23,_24){
var _25=_23.getElementsByTagName("tag");
var _26={};
var _27=false;
for(var j=0;j<_25.length;j++){
var key=_25[j].getAttribute("k");
_26[key]=_25[j].getAttribute("v");
if(_24){
if(!this.interestingTagsExclude[key]){
_27=true;
}
}
}
return _24?[_26,_27]:_26;
},isWayArea:function(way){
var _2b=false;
var _2c=false;
if(way.nodes[0]==way.nodes[way.nodes.length-1]){
_2b=true;
}
if(this.checkTags){
for(var key in way.tags){
if(this.areaTags[key]){
_2c=true;
break;
}
}
}
return _2b&&(this.checkTags?_2c:true);
},write:function(_2e){
if(!(_2e instanceof Array)){
_2e=[_2e];
}
this.osm_id=1;
this.created_nodes={};
var _2f=this.createElementNS(null,"osm");
_2f.setAttribute("version","0.5");
_2f.setAttribute("generator","OpenLayers "+OpenLayers.VERSION_NUMBER);
for(var i=_2e.length-1;i>=0;i--){
var _31=this.createFeatureNodes(_2e[i]);
for(var j=0;j<_31.length;j++){
_2f.appendChild(_31[j]);
}
}
return OpenLayers.Format.XML.prototype.write.apply(this,[_2f]);
},createFeatureNodes:function(_33){
var _34=[];
var _35=_33.geometry.CLASS_NAME;
var _36=_35.substring(_35.lastIndexOf(".")+1);
_36=_36.toLowerCase();
var _37=this.createXML[_36];
if(_37){
_34=_37.apply(this,[_33]);
}
return _34;
},createXML:{"point":function(_38){
var id=null;
var _3a=_38.geometry?_38.geometry:_38;
var _3b=false;
if(_38.osm_id){
id=_38.osm_id;
if(this.created_nodes[id]){
_3b=true;
}
}else{
id=-this.osm_id;
this.osm_id++;
}
if(_3b){
node=this.created_nodes[id];
}else{
var _3c=this.createElementNS(null,"node");
}
this.created_nodes[id]=_3c;
_3c.setAttribute("id",id);
_3c.setAttribute("lon",_3a.x);
_3c.setAttribute("lat",_3a.y);
if(_38.attributes){
this.serializeTags(_38,_3c);
}
this.setState(_38,_3c);
return _3b?[]:[_3c];
},linestring:function(_3d){
var _3e=[];
var _3f=_3d.geometry;
if(_3d.osm_id){
id=_3d.osm_id;
}else{
id=-this.osm_id;
this.osm_id++;
}
var way=this.createElementNS(null,"way");
way.setAttribute("id",id);
for(var i=0;i<_3f.components.length;i++){
var _42=this.createXML["point"].apply(this,[_3f.components[i]]);
if(_42.length){
_42=_42[0];
var _43=_42.getAttribute("id");
_3e.push(_42);
}else{
_43=_3f.components[i].osm_id;
_42=this.created_nodes[_43];
}
this.setState(_3d,_42);
var _44=this.createElementNS(null,"nd");
_44.setAttribute("ref",_43);
way.appendChild(_44);
}
this.serializeTags(_3d,way);
_3e.push(way);
return _3e;
},polygon:function(_45){
var _46=OpenLayers.Util.extend({"area":"yes"},_45.attributes);
var _47=new OpenLayers.Feature.Vector(_45.geometry.components[0],_46);
_47.osm_id=_45.osm_id;
return this.createXML["linestring"].apply(this,[_47]);
}},serializeTags:function(_48,_49){
for(var key in _48.attributes){
var tag=this.createElementNS(null,"tag");
tag.setAttribute("k",key);
tag.setAttribute("v",_48.attributes[key]);
_49.appendChild(tag);
}
},setState:function(_4c,_4d){
if(_4c.state){
var _4e=null;
switch(_4c.state){
case OpenLayers.State.UPDATE:
_4e="modify";
case OpenLayers.State.DELETE:
_4e="delete";
}
if(_4e){
_4d.setAttribute("action",_4e);
}
}
},CLASS_NAME:"OpenLayers.Format.OSM"});

OpenLayers.Format.SLD=OpenLayers.Class(OpenLayers.Format.XML,{defaultVersion:"1.0.0",version:null,parser:null,initialize:function(_1){
OpenLayers.Format.XML.prototype.initialize.apply(this,[_1]);
},write:function(_2,_3){
var _4=(_3&&_3.version)||this.version||this.defaultVersion;
if(!this.parser||this.parser.VERSION!=_4){
var _5=OpenLayers.Format.SLD["v"+_4.replace(/\./g,"_")];
if(!_5){
throw "Can't find a SLD parser for version "+_4;
}
this.parser=new _5(this.options);
}
var _6=this.parser.write(_2);
return OpenLayers.Format.XML.prototype.write.apply(this,[_6]);
},read:function(_7){
if(typeof _7=="string"){
_7=OpenLayers.Format.XML.prototype.read.apply(this,[_7]);
}
var _8=_7.documentElement;
var _9=this.version;
if(!_9){
_9=_8.getAttribute("version");
if(!_9){
_9=this.defaultVersion;
}
}
if(!this.parser||this.parser.VERSION!=_9){
var _a=OpenLayers.Format.SLD["v"+_9.replace(/\./g,"_")];
if(!_a){
throw "Can't find a SLD parser for version "+_9;
}
this.parser=new _a(this.options);
}
var _b=this.parser.read(_7);
return _b;
},CLASS_NAME:"OpenLayers.Format.SLD"});

OpenLayers.Format.SLD.v1=OpenLayers.Class(OpenLayers.Format.XML,{namespaces:{sld:"http://www.opengis.net/sld",ogc:"http://www.opengis.net/ogc",xlink:"http://www.w3.org/1999/xlink",xsi:"http://www.w3.org/2001/XMLSchema-instance"},defaultPrefix:"sld",schemaLocation:null,defaultSymbolizer:{fillColor:"#808080",fillOpacity:1,strokeColor:"#000000",strokeOpacity:1,strokeWidth:1,pointRadius:6},initialize:function(_1){
OpenLayers.Format.XML.prototype.initialize.apply(this,[_1]);
},read:function(_2){
var _3={namedLayers:{}};
this.readChildNodes(_2,_3);
return _3;
},readers:{"sld":{"StyledLayerDescriptor":function(_4,_5){
_5.version=_4.getAttribute("version");
this.readChildNodes(_4,_5);
},"Name":function(_6,_7){
_7.name=this.getChildValue(_6);
},"Title":function(_8,_9){
_9.title=this.getChildValue(_8);
},"Abstract":function(_a,_b){
_b.description=this.getChildValue(_a);
},"NamedLayer":function(_c,_d){
var _e={userStyles:[],namedStyles:[]};
this.readChildNodes(_c,_e);
for(var i=0;i<_e.userStyles.length;++i){
_e.userStyles[i].layerName=_e.name;
}
_d.namedLayers[_e.name]=_e;
},"NamedStyle":function(_10,_11){
_11.namedStyles.push(this.getChildName(_10.firstChild));
},"UserStyle":function(_12,_13){
var _14=new OpenLayers.Style(this.defaultSymbolizer);
this.readChildNodes(_12,_14);
_13.userStyles.push(_14);
},"IsDefault":function(_15,_16){
if(this.getChildValue(_15)=="1"){
_16.isDefault=true;
}
},"FeatureTypeStyle":function(_17,_18){
var obj={rules:[]};
this.readChildNodes(_17,obj);
_18.rules=obj.rules;
},"Rule":function(_1a,obj){
var _1c=new OpenLayers.Rule();
this.readChildNodes(_1a,_1c);
obj.rules.push(_1c);
},"ElseFilter":function(_1d,_1e){
_1e.elseFilter=true;
},"MinScaleDenominator":function(_1f,_20){
_20.minScaleDenominator=this.getChildValue(_1f);
},"MaxScaleDenominator":function(_21,_22){
_22.maxScaleDenominator=this.getChildValue(_21);
},"LineSymbolizer":function(_23,_24){
var _25=_24.symbolizer["Line"]||{};
this.readChildNodes(_23,_25);
_24.symbolizer["Line"]=_25;
},"PolygonSymbolizer":function(_26,_27){
var _28=_27.symbolizer["Polygon"]||{};
this.readChildNodes(_26,_28);
_27.symbolizer["Polygon"]=_28;
},"PointSymbolizer":function(_29,_2a){
var _2b=_2a.symbolizer["Point"]||{};
this.readChildNodes(_29,_2b);
_2a.symbolizer["Point"]=_2b;
},"Stroke":function(_2c,_2d){
this.readChildNodes(_2c,_2d);
},"Fill":function(_2e,_2f){
this.readChildNodes(_2e,_2f);
},"CssParameter":function(_30,_31){
var _32=_30.getAttribute("name");
var _33=this.cssMap[_32];
if(_33){
var _34=this.readOgcExpression(_30);
if(_34){
_31[_33]=_34;
}
}
},"Graphic":function(_35,_36){
var _37={};
this.readChildNodes(_35,_37);
var _38=["strokeColor","strokeWidth","strokeOpacity","strokeLinecap","fillColor","fillOpacity","graphicName","rotation","graphicFormat"];
var _39,value;
for(var i=0;i<_38.length;++i){
_39=_38[i];
value=_37[_39];
if(value!=undefined){
_36[_39]=value;
}
}
if(_37.opacity!=undefined){
_36.graphicOpacity=_37.opacity;
}
if(_37.size!=undefined){
_36.pointRadius=_37.size/2;
}
if(_37.href!=undefined){
_36.externalGraphic=_37.href;
}
if(_37.rotation!=undefined){
_36.rotation=_37.rotation;
}
},"ExternalGraphic":function(_3b,_3c){
this.readChildNodes(_3b,_3c);
},"Mark":function(_3d,_3e){
this.readChildNodes(_3d,_3e);
},"WellKnownName":function(_3f,_40){
_40.graphicName=this.getChildValue(_3f);
},"Opacity":function(_41,obj){
var _43=this.getChildValue(_41);
if(_43){
obj.opacity=_43;
}
},"Size":function(_44,obj){
var _46=this.getChildValue(_44);
if(_46){
obj.size=_46;
}
},"Rotation":function(_47,obj){
var _49=this.getChildValue(_47);
if(_49){
obj.rotation=_49;
}
},"OnlineResource":function(_4a,obj){
obj.href=this.getAttributeNS(_4a,this.namespaces.xlink,"href");
},"Format":function(_4c,_4d){
_4d.graphicFormat=this.getChildValue(_4c);
}},"ogc":{"Filter":function(_4e,_4f){
var obj={fids:[],filters:[]};
this.readChildNodes(_4e,obj);
if(obj.fids.length>0){
_4f.filter=new OpenLayers.Filter.FeatureId({fids:obj.fids});
}else{
if(obj.filters.length>0){
_4f.filter=obj.filters[0];
}
}
},"FeatureId":function(_51,obj){
var fid=_51.getAttribute("fid");
if(fid){
obj.fids.push(fid);
}
},"And":function(_54,obj){
var _56=new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND});
this.readChildNodes(_54,_56);
obj.filters.push(_56);
},"Or":function(_57,obj){
var _59=new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.OR});
this.readChildNodes(_57,_59);
obj.filters.push(_59);
},"Not":function(_5a,obj){
var _5c=new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.NOT});
this.readChildNodes(_5a,_5c);
obj.filters.push(_5c);
},"PropertyIsEqualTo":function(_5d,obj){
var _5f=new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO});
this.readChildNodes(_5d,_5f);
obj.filters.push(_5f);
},"PropertyIsNotEqualTo":function(_60,obj){
var _62=new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.NOT_EQUAL_TO});
this.readChildNodes(_60,_62);
obj.filters.push(_62);
},"PropertyIsLessThan":function(_63,obj){
var _65=new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.LESS_THAN});
this.readChildNodes(_63,_65);
obj.filters.push(_65);
},"PropertyIsGreaterThan":function(_66,obj){
var _68=new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.GREATER_THAN});
this.readChildNodes(_66,_68);
obj.filters.push(_68);
},"PropertyIsLessThanOrEqualTo":function(_69,obj){
var _6b=new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.LESS_THAN_OR_EQUAL_TO});
this.readChildNodes(_69,_6b);
obj.filters.push(_6b);
},"PropertyIsGreaterThanOrEqualTo":function(_6c,obj){
var _6e=new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO});
this.readChildNodes(_6c,_6e);
obj.filters.push(_6e);
},"PropertyIsBetween":function(_6f,obj){
var _71=new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.BETWEEN});
this.readChildNodes(_6f,_71);
obj.filters.push(_71);
},"PropertyIsLike":function(_72,obj){
var _74=new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.LIKE});
this.readChildNodes(_72,_74);
var _75=_72.getAttribute("wildCard");
var _76=_72.getAttribute("singleChar");
var esc=_72.getAttribute("escape");
_74.value2regex(_75,_76,esc);
obj.filters.push(_74);
},"Literal":function(_78,obj){
obj.value=this.getChildValue(_78);
},"PropertyName":function(_7a,_7b){
_7b.property=this.getChildValue(_7a);
},"LowerBoundary":function(_7c,_7d){
_7d.lowerBoundary=this.readOgcExpression(_7c);
},"UpperBoundary":function(_7e,_7f){
_7f.upperBoundary=this.readOgcExpression(_7e);
}}},readOgcExpression:function(_80){
var obj={};
this.readChildNodes(_80,obj);
var _82=obj.value;
if(!_82){
_82=this.getChildValue(_80);
}
return _82;
},cssMap:{"stroke":"strokeColor","stroke-opacity":"strokeOpacity","stroke-width":"strokeWidth","stroke-linecap":"strokeLinecap","fill":"fillColor","fill-opacity":"fillOpacity","font-family":"fontFamily","font-size":"fontSize"},getCssProperty:function(sym){
var css=null;
for(var _85 in this.cssMap){
if(this.cssMap[_85]==sym){
css=_85;
break;
}
}
return css;
},getGraphicFormat:function(_86){
var _87,regex;
for(var key in this.graphicFormats){
if(this.graphicFormats[key].test(_86)){
_87=key;
break;
}
}
return _87||this.defautlGraphicFormat;
},defaultGraphicFormat:"image/png",graphicFormats:{"image/jpeg":/\.jpe?g$/i,"image/gif":/\.gif$/i,"image/png":/\.png$/i},write:function(sld){
return this.writers.sld.StyledLayerDescriptor.apply(this,[sld]);
},writers:{"sld":{"StyledLayerDescriptor":function(sld){
var _8b=this.createElementNSPlus("StyledLayerDescriptor",{attributes:{"version":this.VERSION,"xsi:schemaLocation":this.schemaLocation}});
if(sld.name){
this.writeNode(_8b,"Name",sld.name);
}
if(sld.title){
this.writeNode(_8b,"Title",sld.title);
}
if(sld.description){
this.writeNode(_8b,"Abstract",sld.description);
}
for(var _8c in sld.namedLayers){
this.writeNode(_8b,"NamedLayer",sld.namedLayers[_8c]);
}
return _8b;
},"Name":function(_8d){
return this.createElementNSPlus("Name",{value:_8d});
},"Title":function(_8e){
return this.createElementNSPlus("Title",{value:_8e});
},"Abstract":function(_8f){
return this.createElementNSPlus("Abstract",{value:_8f});
},"NamedLayer":function(_90){
var _91=this.createElementNSPlus("NamedLayer");
this.writeNode(_91,"Name",_90.name);
if(_90.namedStyles){
for(var i=0;i<_90.namedStyles.length;++i){
this.writeNode(_91,"NamedStyle",_90.namedStyles[i]);
}
}
if(_90.userStyles){
for(var i=0;i<_90.userStyles.length;++i){
this.writeNode(_91,"UserStyle",_90.userStyles[i]);
}
}
return _91;
},"NamedStyle":function(_93){
var _94=this.createElementNSPlus("NamedStyle");
this.writeNode(_94,"Name",_93);
return _94;
},"UserStyle":function(_95){
var _96=this.createElementNSPlus("UserStyle");
if(_95.name){
this.writeNode(_96,"Name",_95.name);
}
if(_95.title){
this.writeNode(_96,"Title",_95.title);
}
if(_95.description){
this.writeNode(_96,"Abstract",_95.description);
}
if(_95.isDefault){
this.writeNode(_96,"IsDefault",_95.isDefault);
}
this.writeNode(_96,"FeatureTypeStyle",_95);
return _96;
},"IsDefault":function(_97){
return this.createElementNSPlus("IsDefault",{value:(_97)?"1":"0"});
},"FeatureTypeStyle":function(_98){
var _99=this.createElementNSPlus("FeatureTypeStyle");
for(var i=0;i<_98.rules.length;++i){
this.writeNode(_99,"Rule",_98.rules[i]);
}
return _99;
},"Rule":function(_9b){
var _9c=this.createElementNSPlus("Rule");
if(_9b.name){
this.writeNode(_9c,"Name",_9b.name);
}
if(_9b.title){
this.writeNode(_9c,"Title",_9b.title);
}
if(_9b.description){
this.writeNode(_9c,"Abstract",_9b.description);
}
if(_9b.elseFilter){
this.writeNode(_9c,"ElseFilter");
}else{
if(_9b.filter){
this.writeNode(_9c,"ogc:Filter",_9b.filter);
}
}
if(_9b.minScaleDenominator!=undefined){
this.writeNode(_9c,"MinScaleDenominator",_9b.minScaleDenominator);
}
if(_9b.maxScaleDenominator!=undefined){
this.writeNode(_9c,"MaxScaleDenominator",_9b.maxScaleDenominator);
}
var _9d=OpenLayers.Style.SYMBOLIZER_PREFIXES;
var _9e,symbolizer;
for(var i=0;i<_9d.length;++i){
_9e=_9d[i];
symbolizer=_9b.symbolizer[_9e];
if(symbolizer){
this.writeNode(_9c,_9e+"Symbolizer",symbolizer);
}
}
return _9c;
},"ElseFilter":function(){
return this.createElementNSPlus("ElseFilter");
},"MinScaleDenominator":function(_a0){
return this.createElementNSPlus("MinScaleDenominator",{value:_a0});
},"MaxScaleDenominator":function(_a1){
return this.createElementNSPlus("MaxScaleDenominator",{value:_a1});
},"LineSymbolizer":function(_a2){
var _a3=this.createElementNSPlus("LineSymbolizer");
this.writeNode(_a3,"Stroke",_a2);
return _a3;
},"Stroke":function(_a4){
var _a5=this.createElementNSPlus("Stroke");
if(_a4.strokeColor!=undefined){
this.writeNode(_a5,"CssParameter",{symbolizer:_a4,key:"strokeColor"});
}
if(_a4.strokeOpacity!=undefined){
this.writeNode(_a5,"CssParameter",{symbolizer:_a4,key:"strokeOpacity"});
}
if(_a4.strokeWidth!=undefined){
this.writeNode(_a5,"CssParameter",{symbolizer:_a4,key:"strokeWidth"});
}
return _a5;
},"CssParameter":function(obj){
return this.createElementNSPlus("CssParameter",{attributes:{name:this.getCssProperty(obj.key)},value:obj.symbolizer[obj.key]});
},"TextSymbolizer":function(_a7){
var _a8=this.createElementNSPlus("TextSymbolizer");
if(_a7.label!=null){
this.writeNode(_a8,"Label",_a7.label);
}
if(_a7.fontFamily!=null||_a7.fontSize!=null){
this.writeNode(_a8,"Font",_a7);
}
if(_a7.fillColor!=null||_a7.fillOpacity!=null){
this.writeNode(_a8,"Fill",_a7);
}
return _a8;
},"Font":function(_a9){
var _aa=this.createElementNSPlus("Font");
if(_a9.fontFamily){
this.writeNode(_aa,"CssParameter",{symbolizer:_a9,key:"fontFamily"});
}
if(_a9.fontSize){
this.writeNode(_aa,"CssParameter",{symbolizer:_a9,key:"fontSize"});
}
return _aa;
},"Label":function(_ab){
var _ac=this.createElementNSPlus("Label");
var _ad=_ab.split("${");
_ac.appendChild(this.createTextNode(_ad[0]));
var _ae,last;
for(var i=1;i<_ad.length;i++){
_ae=_ad[i];
last=_ae.indexOf("}");
if(last>0){
this.writeNode(_ac,"ogc:PropertyName",{property:_ae.substring(0,last)});
_ac.appendChild(this.createTextNode(_ae.substring(++last)));
}else{
_ac.appendChild(this.createTextNode("${"+_ae));
}
}
return _ac;
},"PolygonSymbolizer":function(_b0){
var _b1=this.createElementNSPlus("PolygonSymbolizer");
this.writeNode(_b1,"Fill",_b0);
this.writeNode(_b1,"Stroke",_b0);
return _b1;
},"Fill":function(_b2){
var _b3=this.createElementNSPlus("Fill");
if(_b2.fillColor){
this.writeNode(_b3,"CssParameter",{symbolizer:_b2,key:"fillColor"});
}
if(_b2.fillOpacity){
this.writeNode(_b3,"CssParameter",{symbolizer:_b2,key:"fillOpacity"});
}
return _b3;
},"PointSymbolizer":function(_b4){
var _b5=this.createElementNSPlus("PointSymbolizer");
this.writeNode(_b5,"Graphic",_b4);
return _b5;
},"Graphic":function(_b6){
var _b7=this.createElementNSPlus("Graphic");
if(_b6.externalGraphic!=undefined){
this.writeNode(_b7,"ExternalGraphic",_b6);
}else{
if(_b6.graphicName){
this.writeNode(_b7,"Mark",_b6);
}
}
if(_b6.graphicOpacity!=undefined){
this.writeNode(_b7,"Opacity",_b6.graphicOpacity);
}
if(_b6.pointRadius!=undefined){
this.writeNode(_b7,"Size",_b6.pointRadius*2);
}
if(_b6.rotation!=undefined){
this.writeNode(_b7,"Rotation",_b6.rotation);
}
return _b7;
},"ExternalGraphic":function(_b8){
var _b9=this.createElementNSPlus("ExternalGraphic");
this.writeNode(_b9,"OnlineResource",_b8.externalGraphic);
var _ba=_b8.graphicFormat||this.getGraphicFormat(_b8.externalGraphic);
this.writeNode(_b9,"Format",_ba);
return _b9;
},"Mark":function(_bb){
var _bc=this.createElementNSPlus("Mark");
this.writeNode(_bc,"WellKnownName",_bb.graphicName);
this.writeNode(_bc,"Fill",_bb);
this.writeNode(_bc,"Stroke",_bb);
return _bc;
},"WellKnownName":function(_bd){
return this.createElementNSPlus("WellKnownName",{value:_bd});
},"Opacity":function(_be){
return this.createElementNSPlus("Opacity",{value:_be});
},"Size":function(_bf){
return this.createElementNSPlus("Size",{value:_bf});
},"Rotation":function(_c0){
return this.createElementNSPlus("Rotation",{value:_c0});
},"OnlineResource":function(_c1){
return this.createElementNSPlus("OnlineResource",{attributes:{"xlink:type":"simple","xlink:href":_c1}});
},"Format":function(_c2){
return this.createElementNSPlus("Format",{value:_c2});
}},"ogc":{"Filter":function(_c3){
var _c4=this.createElementNSPlus("ogc:Filter");
var sub=_c3.CLASS_NAME.split(".").pop();
if(sub=="FeatureId"){
for(var i=0;i<_c3.fids.length;++i){
this.writeNode(_c4,"FeatureId",_c3.fids[i]);
}
}else{
this.writeNode(_c4,this.getFilterType(_c3),_c3);
}
return _c4;
},"FeatureId":function(fid){
return this.createElementNSPlus("ogc:FeatureId",{attributes:{fid:fid}});
},"And":function(_c8){
var _c9=this.createElementNSPlus("ogc:And");
var _ca;
for(var i=0;i<_c8.filters.length;++i){
_ca=_c8.filters[i];
this.writeNode(_c9,this.getFilterType(_ca),_ca);
}
return _c9;
},"Or":function(_cc){
var _cd=this.createElementNSPlus("ogc:Or");
var _ce;
for(var i=0;i<_cc.filters.length;++i){
_ce=_cc.filters[i];
this.writeNode(_cd,this.getFilterType(_ce),_ce);
}
return _cd;
},"Not":function(_d0){
var _d1=this.createElementNSPlus("ogc:Not");
var _d2=_d0.filters[0];
this.writeNode(_d1,this.getFilterType(_d2),_d2);
return _d1;
},"PropertyIsEqualTo":function(_d3){
var _d4=this.createElementNSPlus("ogc:PropertyIsEqualTo");
this.writeNode(_d4,"PropertyName",_d3);
this.writeNode(_d4,"Literal",_d3.value);
return _d4;
},"PropertyIsNotEqualTo":function(_d5){
var _d6=this.createElementNSPlus("ogc:PropertyIsNotEqualTo");
this.writeNode(_d6,"PropertyName",_d5);
this.writeNode(_d6,"Literal",_d5.value);
return _d6;
},"PropertyIsLessThan":function(_d7){
var _d8=this.createElementNSPlus("ogc:PropertyIsLessThan");
this.writeNode(_d8,"PropertyName",_d7);
this.writeNode(_d8,"Literal",_d7.value);
return _d8;
},"PropertyIsGreaterThan":function(_d9){
var _da=this.createElementNSPlus("ogc:PropertyIsGreaterThan");
this.writeNode(_da,"PropertyName",_d9);
this.writeNode(_da,"Literal",_d9.value);
return _da;
},"PropertyIsLessThanOrEqualTo":function(_db){
var _dc=this.createElementNSPlus("ogc:PropertyIsLessThanOrEqualTo");
this.writeNode(_dc,"PropertyName",_db);
this.writeNode(_dc,"Literal",_db.value);
return _dc;
},"PropertyIsGreaterThanOrEqualTo":function(_dd){
var _de=this.createElementNSPlus("ogc:PropertyIsGreaterThanOrEqualTo");
this.writeNode(_de,"PropertyName",_dd);
this.writeNode(_de,"Literal",_dd.value);
return _de;
},"PropertyIsBetween":function(_df){
var _e0=this.createElementNSPlus("ogc:PropertyIsBetween");
this.writeNode(_e0,"PropertyName",_df);
this.writeNode(_e0,"LowerBoundary",_df);
this.writeNode(_e0,"UpperBoundary",_df);
return _e0;
},"PropertyIsLike":function(_e1){
var _e2=this.createElementNSPlus("ogc:PropertyIsLike",{attributes:{wildCard:"*",singleChar:".",escape:"!"}});
this.writeNode(_e2,"PropertyName",_e1);
this.writeNode(_e2,"Literal",_e1.regex2value());
return _e2;
},"PropertyName":function(_e3){
return this.createElementNSPlus("ogc:PropertyName",{value:_e3.property});
},"Literal":function(_e4){
return this.createElementNSPlus("ogc:Literal",{value:_e4});
},"LowerBoundary":function(_e5){
var _e6=this.createElementNSPlus("ogc:LowerBoundary");
this.writeNode(_e6,"Literal",_e5.lowerBoundary);
return _e6;
},"UpperBoundary":function(_e7){
var _e8=this.createElementNSPlus("ogc:UpperBoundary");
this.writeNode(_e8,"Literal",_e7.upperBoundary);
return _e8;
}}},getFilterType:function(_e9){
var _ea=this.filterMap[_e9.type];
if(!_ea){
throw "SLD writing not supported for rule type: "+_e9.type;
}
return _ea;
},filterMap:{"&&":"And","||":"Or","!":"Not","==":"PropertyIsEqualTo","!=":"PropertyIsNotEqualTo","<":"PropertyIsLessThan",">":"PropertyIsGreaterThan","<=":"PropertyIsLessThanOrEqualTo",">=":"PropertyIsGreaterThanOrEqualTo","..":"PropertyIsBetween","~":"PropertyIsLike"},getNamespacePrefix:function(uri){
var _ec=null;
if(uri==null){
_ec=this.namespaces[this.defaultPrefix];
}else{
var _ed=false;
for(_ec in this.namespaces){
if(this.namespaces[_ec]==uri){
_ed=true;
break;
}
}
if(!_ed){
_ec=null;
}
}
return _ec;
},readChildNodes:function(_ee,obj){
var _f0=_ee.childNodes;
var _f1,group,reader,prefix,local;
for(var i=0;i<_f0.length;++i){
_f1=_f0[i];
if(_f1.nodeType==1){
prefix=this.getNamespacePrefix(_f1.namespaceURI);
local=_f1.nodeName.split(":").pop();
group=this.readers[prefix];
if(group){
reader=group[local];
if(reader){
reader.apply(this,[_f1,obj]);
}
}
}
}
},writeNode:function(_f3,_f4,obj){
var _f6,local;
var _f7=_f4.indexOf(":");
if(_f7>0){
_f6=_f4.substring(0,_f7);
local=_f4.substring(_f7+1);
}else{
_f6=this.getNamespacePrefix(_f3.namespaceURI);
local=_f4;
}
var _f8=this.writers[_f6][local].apply(this,[obj]);
_f3.appendChild(_f8);
return _f8;
},createElementNSPlus:function(_f9,_fa){
_fa=_fa||{};
var loc=_f9.indexOf(":");
var uri=_fa.uri||this.namespaces[_fa.prefix];
if(!uri){
loc=_f9.indexOf(":");
uri=this.namespaces[_f9.substring(0,loc)];
}
if(!uri){
uri=this.namespaces[this.defaultPrefix];
}
var _fd=this.createElementNS(uri,_f9);
if(_fa.attributes){
this.setAttributes(_fd,_fa.attributes);
}
if(_fa.value){
_fd.appendChild(this.createTextNode(_fa.value));
}
return _fd;
},setAttributes:function(_fe,obj){
var _100,loc,alias,uri;
for(var name in obj){
_100=obj[name].toString();
uri=this.namespaces[name.substring(0,name.indexOf(":"))]||null;
this.setAttributeNS(_fe,uri,name,_100);
}
},CLASS_NAME:"OpenLayers.Format.SLD.v1"});

OpenLayers.Format.SLD.v1_0_0=OpenLayers.Class(OpenLayers.Format.SLD.v1,{VERSION:"1.0.0",schemaLocation:"http://www.opengis.net/sld http://schemas.opengis.net/sld/1.0.0/StyledLayerDescriptor.xsd",initialize:function(_1){
OpenLayers.Format.SLD.v1.prototype.initialize.apply(this,[_1]);
},CLASS_NAME:"OpenLayers.Format.SLD.v1_0_0"});

OpenLayers.Format.Text=OpenLayers.Class(OpenLayers.Format,{initialize:function(_1){
OpenLayers.Format.prototype.initialize.apply(this,[_1]);
},read:function(_2){
var _3=_2.split("\n");
var _4;
var _5=[];
for(var _6=0;_6<(_3.length-1);_6++){
var _7=_3[_6].replace(/^\s*/,"").replace(/\s*$/,"");
if(_7.charAt(0)!="#"){
if(!_4){
_4=_7.split("\t");
}else{
var _8=_7.split("\t");
var _9=new OpenLayers.Geometry.Point(0,0);
var _a={};
var _b={};
var _c,iconSize,iconOffset,overflow;
var _d=false;
for(var _e=0;_e<_8.length;_e++){
if(_8[_e]){
if(_4[_e]=="point"){
var _f=_8[_e].split(",");
_9.y=parseFloat(_f[0]);
_9.x=parseFloat(_f[1]);
_d=true;
}else{
if(_4[_e]=="lat"){
_9.y=parseFloat(_8[_e]);
_d=true;
}else{
if(_4[_e]=="lon"){
_9.x=parseFloat(_8[_e]);
_d=true;
}else{
if(_4[_e]=="title"){
_a["title"]=_8[_e];
}else{
if(_4[_e]=="image"||_4[_e]=="icon"){
_b["externalGraphic"]=_8[_e];
}else{
if(_4[_e]=="iconSize"){
var _10=_8[_e].split(",");
_b["graphicWidth"]=parseFloat(_10[0]);
_b["graphicHeight"]=parseFloat(_10[1]);
}else{
if(_4[_e]=="iconOffset"){
var _11=_8[_e].split(",");
_b["graphicXOffset"]=parseFloat(_11[0]);
_b["graphicYOffset"]=parseFloat(_11[1]);
}else{
if(_4[_e]=="description"){
_a["description"]=_8[_e];
}else{
if(_4[_e]=="overflow"){
_a["overflow"]=_8[_e];
}
}
}
}
}
}
}
}
}
}
}
if(_d){
if(this.internalProjection&&this.externalProjection){
_9.transform(this.externalProjection,this.internalProjection);
}
var _12=new OpenLayers.Feature.Vector(_9,_a,_b);
_5.push(_12);
}
}
}
}
return _5;
},CLASS_NAME:"OpenLayers.Format.Text"});

OpenLayers.Format.JSON=OpenLayers.Class(OpenLayers.Format,{indent:"    ",space:" ",newline:"\n",level:0,pretty:false,initialize:function(_1){
OpenLayers.Format.prototype.initialize.apply(this,[_1]);
},read:function(_2,_3){
try{
if(/^[\],:{}\s]*$/.test(_2.replace(/\\["\\\/bfnrtu]/g,"@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,"]").replace(/(?:^|:|,)(?:\s*\[)+/g,""))){
var _4=eval("("+_2+")");
if(typeof _3==="function"){
function walk(k,v){
if(v&&typeof v==="object"){
for(var i in v){
if(v.hasOwnProperty(i)){
v[i]=walk(i,v[i]);
}
}
}
return _3(k,v);
}
_4=walk("",_4);
}
return _4;
}
}
catch(e){
}
return null;
},write:function(_8,_9){
this.pretty=!!_9;
var _a=null;
var _b=typeof _8;
if(this.serialize[_b]){
_a=this.serialize[_b].apply(this,[_8]);
}
return _a;
},writeIndent:function(){
var _c=[];
if(this.pretty){
for(var i=0;i<this.level;++i){
_c.push(this.indent);
}
}
return _c.join("");
},writeNewline:function(){
return (this.pretty)?this.newline:"";
},writeSpace:function(){
return (this.pretty)?this.space:"";
},serialize:{"object":function(_e){
if(_e==null){
return "null";
}
if(_e.constructor==Date){
return this.serialize.date.apply(this,[_e]);
}
if(_e.constructor==Array){
return this.serialize.array.apply(this,[_e]);
}
var _f=["{"];
this.level+=1;
var key,keyJSON,valueJSON;
var _11=false;
for(key in _e){
if(_e.hasOwnProperty(key)){
keyJSON=OpenLayers.Format.JSON.prototype.write.apply(this,[key,this.pretty]);
valueJSON=OpenLayers.Format.JSON.prototype.write.apply(this,[_e[key],this.pretty]);
if(keyJSON!=null&&valueJSON!=null){
if(_11){
_f.push(",");
}
_f.push(this.writeNewline(),this.writeIndent(),keyJSON,":",this.writeSpace(),valueJSON);
_11=true;
}
}
}
this.level-=1;
_f.push(this.writeNewline(),this.writeIndent(),"}");
return _f.join("");
},"array":function(_12){
var _13;
var _14=["["];
this.level+=1;
for(var i=0;i<_12.length;++i){
_13=OpenLayers.Format.JSON.prototype.write.apply(this,[_12[i],this.pretty]);
if(_13!=null){
if(i>0){
_14.push(",");
}
_14.push(this.writeNewline(),this.writeIndent(),_13);
}
}
this.level-=1;
_14.push(this.writeNewline(),this.writeIndent(),"]");
return _14.join("");
},"string":function(_16){
var m={"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r","\"":"\\\"","\\":"\\\\"};
if(/["\\\x00-\x1f]/.test(_16)){
return "\""+_16.replace(/([\x00-\x1f\\"])/g,function(a,b){
var c=m[b];
if(c){
return c;
}
c=b.charCodeAt();
return "\\u00"+Math.floor(c/16).toString(16)+(c%16).toString(16);
})+"\"";
}
return "\""+_16+"\"";
},"number":function(_1b){
return isFinite(_1b)?String(_1b):"null";
},"boolean":function(_1c){
return String(_1c);
},"date":function(_1d){
function format(_1e){
return (_1e<10)?"0"+_1e:_1e;
}
return "\""+_1d.getFullYear()+"-"+format(_1d.getMonth()+1)+"-"+format(_1d.getDate())+"T"+format(_1d.getHours())+":"+format(_1d.getMinutes())+":"+format(_1d.getSeconds())+"\"";
}},CLASS_NAME:"OpenLayers.Format.JSON"});

OpenLayers.Format.GeoJSON=OpenLayers.Class(OpenLayers.Format.JSON,{initialize:function(_1){
OpenLayers.Format.JSON.prototype.initialize.apply(this,[_1]);
},read:function(_2,_3,_4){
_3=(_3)?_3:"FeatureCollection";
var _5=null;
var _6=null;
if(typeof _2=="string"){
_6=OpenLayers.Format.JSON.prototype.read.apply(this,[_2,_4]);
}else{
_6=_2;
}
if(!_6){
OpenLayers.Console.error("Bad JSON: "+_2);
}else{
if(typeof (_6.type)!="string"){
OpenLayers.Console.error("Bad GeoJSON - no type: "+_2);
}else{
if(this.isValidType(_6,_3)){
switch(_3){
case "Geometry":
try{
_5=this.parseGeometry(_6);
}
catch(err){
OpenLayers.Console.error(err);
}
break;
case "Feature":
try{
_5=this.parseFeature(_6);
_5.type="Feature";
}
catch(err){
OpenLayers.Console.error(err);
}
break;
case "FeatureCollection":
_5=[];
switch(_6.type){
case "Feature":
try{
_5.push(this.parseFeature(_6));
}
catch(err){
_5=null;
OpenLayers.Console.error(err);
}
break;
case "FeatureCollection":
for(var i=0;i<_6.features.length;++i){
try{
_5.push(this.parseFeature(_6.features[i]));
}
catch(err){
_5=null;
OpenLayers.Console.error(err);
}
}
break;
default:
try{
var _8=this.parseGeometry(_6);
_5.push(new OpenLayers.Feature.Vector(_8));
}
catch(err){
_5=null;
OpenLayers.Console.error(err);
}
}
break;
}
}
}
}
return _5;
},isValidType:function(_9,_a){
var _b=false;
switch(_a){
case "Geometry":
if(OpenLayers.Util.indexOf(["Point","MultiPoint","LineString","MultiLineString","Polygon","MultiPolygon","Box","GeometryCollection"],_9.type)==-1){
OpenLayers.Console.error("Unsupported geometry type: "+_9.type);
}else{
_b=true;
}
break;
case "FeatureCollection":
_b=true;
break;
default:
if(_9.type==_a){
_b=true;
}else{
OpenLayers.Console.error("Cannot convert types from "+_9.type+" to "+_a);
}
}
return _b;
},parseFeature:function(_c){
var _d,geometry,attributes;
attributes=(_c.properties)?_c.properties:{};
_d=new OpenLayers.Feature(attributes.layer,new OpenLayers.LonLat(_c.coordinates[0],_c.coordinates[1]),attributes);
if(_c.id){
_d.fid=_c.id;
}
return _d;
},parseGeometry:function(_e){
var _f;
if(_e.type=="GeometryCollection"){
if(!(_e.geometries instanceof Array)){
throw "GeometryCollection must have geometries array: "+_e;
}
var _10=_e.geometries.length;
var _11=new Array(_10);
for(var i=0;i<_10;++i){
_11[i]=this.parseGeometry.apply(this,[_e.geometries[i]]);
}
_f=new OpenLayers.Geometry.Collection(_11);
}else{
if(!(_e.coordinates instanceof Array)){
throw "Geometry must have coordinates array: "+_e;
}
if(!this.parseCoords[_e.type.toLowerCase()]){
throw "Unsupported geometry type: "+_e.type;
}
try{
_f=this.parseCoords[_e.type.toLowerCase()].apply(this,[_e.coordinates]);
}
catch(err){
throw err;
}
}
if(this.internalProjection&&this.externalProjection){
_f.transform(this.externalProjection,this.internalProjection);
}
return _f;
},parseCoords:{"point":function(_13){
if(_13.length!=2){
throw "Only 2D points are supported: "+_13;
}
return new OpenLayers.Geometry.Point(_13[0],_13[1]);
},"multipoint":function(_14){
var _15=[];
var p=null;
for(var i=0;i<_14.length;++i){
try{
p=this.parseCoords["point"].apply(this,[_14[i]]);
}
catch(err){
throw err;
}
_15.push(p);
}
return new OpenLayers.Geometry.MultiPoint(_15);
},"linestring":function(_18){
var _19=[];
var p=null;
for(var i=0;i<_18.length;++i){
try{
p=this.parseCoords["point"].apply(this,[_18[i]]);
}
catch(err){
throw err;
}
_19.push(p);
}
return new OpenLayers.Geometry.LineString(_19);
},"multilinestring":function(_1c){
var _1d=[];
var l=null;
for(var i=0;i<_1c.length;++i){
try{
l=this.parseCoords["linestring"].apply(this,[_1c[i]]);
}
catch(err){
throw err;
}
_1d.push(l);
}
return new OpenLayers.Geometry.MultiLineString(_1d);
},"polygon":function(_20){
var _21=[];
var r,l;
for(var i=0;i<_20.length;++i){
try{
l=this.parseCoords["linestring"].apply(this,[_20[i]]);
}
catch(err){
throw err;
}
r=new OpenLayers.Geometry.LinearRing(l.components);
_21.push(r);
}
return new OpenLayers.Geometry.Polygon(_21);
},"multipolygon":function(_24){
var _25=[];
var p=null;
for(var i=0;i<_24.length;++i){
try{
p=this.parseCoords["polygon"].apply(this,[_24[i]]);
}
catch(err){
throw err;
}
_25.push(p);
}
return new OpenLayers.Geometry.MultiPolygon(_25);
},"box":function(_28){
if(_28.length!=2){
throw "GeoJSON box coordinates must have 2 elements";
}
return new OpenLayers.Geometry.Polygon([new OpenLayers.Geometry.LinearRing([new OpenLayers.Geometry.Point(_28[0][0],_28[0][1]),new OpenLayers.Geometry.Point(_28[1][0],_28[0][1]),new OpenLayers.Geometry.Point(_28[1][0],_28[1][1]),new OpenLayers.Geometry.Point(_28[0][0],_28[1][1]),new OpenLayers.Geometry.Point(_28[0][0],_28[0][1])])]);
}},write:function(obj,_2a){
var _2b={"type":null};
if(obj instanceof Array){
_2b.type="FeatureCollection";
var _2c=obj.length;
_2b.features=new Array(_2c);
for(var i=0;i<_2c;++i){
var _2e=obj[i];
if(!_2e instanceof OpenLayers.Feature.Vector){
var msg="FeatureCollection only supports collections "+"of features: "+_2e;
throw msg;
}
_2b.features[i]=this.extract.feature.apply(this,[_2e]);
}
}else{
if(obj.CLASS_NAME.indexOf("OpenLayers.Geometry")==0){
_2b=this.extract.geometry.apply(this,[obj]);
}else{
if(obj instanceof OpenLayers.Feature.Vector){
_2b=this.extract.feature.apply(this,[obj]);
if(obj.layer&&obj.layer.projection){
_2b.crs=this.createCRSObject(obj);
}
}
}
}
return OpenLayers.Format.JSON.prototype.write.apply(this,[_2b,_2a]);
},createCRSObject:function(_30){
var _31=_30.layer.projection.toString();
var crs={};
if(_31.match(/epsg:/i)){
var _33=parseInt(_31.substring(_31.indexOf(":")+1));
if(_33==4326){
crs={"type":"OGC","properties":{"urn":"urn:ogc:def:crs:OGC:1.3:CRS84"}};
}else{
crs={"type":"EPSG","properties":{"code":_33}};
}
}
return crs;
},extract:{"feature":function(_34){
var _35=this.extract.geometry.apply(this,[_34.geometry]);
return {"type":"Feature","id":_34.fid==null?_34.id:_34.fid,"properties":_34.attributes,"geometry":_35};
},"geometry":function(_36){
if(this.internalProjection&&this.externalProjection){
_36=_36.clone();
_36.transform(this.internalProjection,this.externalProjection);
}
var _37=_36.CLASS_NAME.split(".")[2];
var _38=this.extract[_37.toLowerCase()].apply(this,[_36]);
var _39;
if(_37=="Collection"){
_39={"type":"GeometryCollection","geometries":_38};
}else{
_39={"type":_37,"coordinates":_38};
}
return _39;
},"point":function(_3a){
return [_3a.x,_3a.y];
},"multipoint":function(_3b){
var _3c=[];
for(var i=0;i<_3b.components.length;++i){
_3c.push(this.extract.point.apply(this,[_3b.components[i]]));
}
return _3c;
},"linestring":function(_3e){
var _3f=[];
for(var i=0;i<_3e.components.length;++i){
_3f.push(this.extract.point.apply(this,[_3e.components[i]]));
}
return _3f;
},"multilinestring":function(_41){
var _42=[];
for(var i=0;i<_41.components.length;++i){
_42.push(this.extract.linestring.apply(this,[_41.components[i]]));
}
return _42;
},"polygon":function(_44){
var _45=[];
for(var i=0;i<_44.components.length;++i){
_45.push(this.extract.linestring.apply(this,[_44.components[i]]));
}
return _45;
},"multipolygon":function(_47){
var _48=[];
for(var i=0;i<_47.components.length;++i){
_48.push(this.extract.polygon.apply(this,[_47.components[i]]));
}
return _48;
},"collection":function(_4a){
var len=_4a.components.length;
var _4c=new Array(len);
for(var i=0;i<len;++i){
_4c[i]=this.extract.geometry.apply(this,[_4a.components[i]]);
}
return _4c;
}},CLASS_NAME:"OpenLayers.Format.GeoJSON"});

OpenLayers.Layer.WFS=OpenLayers.Class(OpenLayers.Layer.Vector,OpenLayers.Layer.Markers,{isBaseLayer:false,tile:null,ratio:2,DEFAULT_PARAMS:{service:"WFS",version:"1.0.0",request:"GetFeature"},featureClass:null,format:null,formatObject:null,formatOptions:null,vectorMode:true,encodeBBOX:false,extractAttributes:false,initialize:function(_1,_2,_3,_4){
if(_4==undefined){
_4={};
}
if(_4.featureClass||!OpenLayers.Layer.Vector||!OpenLayers.Feature.Vector){
this.vectorMode=false;
}
OpenLayers.Util.extend(_4,{"reportError":false});
var _5=[];
_5.push(_1,_4);
OpenLayers.Layer.Vector.prototype.initialize.apply(this,_5);
if(!this.renderer||!this.vectorMode){
this.vectorMode=false;
if(!_4.featureClass){
_4.featureClass=OpenLayers.Feature.WFS;
}
OpenLayers.Layer.Markers.prototype.initialize.apply(this,_5);
}
if(this.params&&this.params.typename&&!this.options.typename){
this.options.typename=this.params.typename;
}
if(!this.options.geometry_column){
this.options.geometry_column="the_geom";
}
this.params=OpenLayers.Util.applyDefaults(_3,OpenLayers.Util.upperCaseObject(this.DEFAULT_PARAMS));
this.url=_2;
},destroy:function(){
if(this.vectorMode){
OpenLayers.Layer.Vector.prototype.destroy.apply(this,arguments);
}else{
OpenLayers.Layer.Markers.prototype.destroy.apply(this,arguments);
}
if(this.tile){
this.tile.destroy();
}
this.tile=null;
this.ratio=null;
this.featureClass=null;
this.format=null;
if(this.formatObject&&this.formatObject.destroy){
this.formatObject.destroy();
}
this.formatObject=null;
this.formatOptions=null;
this.vectorMode=null;
this.encodeBBOX=null;
this.extractAttributes=null;
},setMap:function(_6){
if(this.vectorMode){
OpenLayers.Layer.Vector.prototype.setMap.apply(this,arguments);
var _7={"extractAttributes":this.extractAttributes};
OpenLayers.Util.extend(_7,this.formatOptions);
if(this.map&&!this.projection.equals(this.map.getProjectionObject())){
_7.externalProjection=this.projection;
_7.internalProjection=this.map.getProjectionObject();
}
this.formatObject=this.format?new this.format(_7):new OpenLayers.Format.GML(_7);
}else{
OpenLayers.Layer.Markers.prototype.setMap.apply(this,arguments);
}
},moveTo:function(_8,_9,_a){
if(this.vectorMode){
OpenLayers.Layer.Vector.prototype.moveTo.apply(this,arguments);
}else{
OpenLayers.Layer.Markers.prototype.moveTo.apply(this,arguments);
}
if(_a){
return false;
}
if(_9){
if(this.vectorMode){
this.renderer.clear();
}
}
if(this.options.minZoomLevel){
OpenLayers.Console.warn(OpenLayers.i18n("minZoomLevelError"));
if(this.map.getZoom()<this.options.minZoomLevel){
return null;
}
}
if(_8==null){
_8=this.map.getExtent();
}
var _b=(this.tile==null);
var _c=(!_b&&!this.tile.bounds.containsBounds(_8));
if(_9||_b||(!_a&&_c)){
var _d=_8.getCenterLonLat();
var _e=_8.getWidth()*this.ratio;
var _f=_8.getHeight()*this.ratio;
var _10=new OpenLayers.Bounds(_d.lon-(_e/2),_d.lat-(_f/2),_d.lon+(_e/2),_d.lat+(_f/2));
var _11=this.map.getSize();
_11.w=_11.w*this.ratio;
_11.h=_11.h*this.ratio;
var ul=new OpenLayers.LonLat(_10.left,_10.top);
var pos=this.map.getLayerPxFromLonLat(ul);
var url=this.getFullRequestString();
var _15={BBOX:this.encodeBBOX?_10.toBBOX():_10.toArray()};
if(this.map&&!this.projection.equals(this.map.getProjectionObject())){
var _16=_10.clone();
_16.transform(this.map.getProjectionObject(),this.projection);
_15.BBOX=this.encodeBBOX?_16.toBBOX():_16.toArray();
}
url+="&"+OpenLayers.Util.getParameterString(_15);
if(!this.tile){
this.tile=new OpenLayers.Tile.WFS(this,pos,_10,url,_11);
this.addTileMonitoringHooks(this.tile);
this.tile.draw();
}else{
if(this.vectorMode){
this.destroyFeatures();
this.renderer.clear();
}else{
this.clearMarkers();
}
this.removeTileMonitoringHooks(this.tile);
this.tile.destroy();
this.tile=null;
this.tile=new OpenLayers.Tile.WFS(this,pos,_10,url,_11);
this.addTileMonitoringHooks(this.tile);
this.tile.draw();
}
}
},addTileMonitoringHooks:function(_17){
_17.onLoadStart=function(){
if(this==this.layer.tile){
this.layer.events.triggerEvent("loadstart");
}
};
_17.events.register("loadstart",_17,_17.onLoadStart);
_17.onLoadEnd=function(){
if(this==this.layer.tile){
this.layer.events.triggerEvent("tileloaded");
this.layer.events.triggerEvent("loadend");
}
};
_17.events.register("loadend",_17,_17.onLoadEnd);
_17.events.register("unload",_17,_17.onLoadEnd);
},removeTileMonitoringHooks:function(_18){
_18.unload();
_18.events.un({"loadstart":_18.onLoadStart,"loadend":_18.onLoadEnd,"unload":_18.onLoadEnd,scope:_18});
},onMapResize:function(){
if(this.vectorMode){
OpenLayers.Layer.Vector.prototype.onMapResize.apply(this,arguments);
}else{
OpenLayers.Layer.Markers.prototype.onMapResize.apply(this,arguments);
}
},mergeNewParams:function(_19){
var _1a=OpenLayers.Util.upperCaseObject(_19);
var _1b=[_1a];
return OpenLayers.Layer.HTTPRequest.prototype.mergeNewParams.apply(this,_1b);
},clone:function(obj){
if(obj==null){
obj=new OpenLayers.Layer.WFS(this.name,this.url,this.params,this.options);
}
if(this.vectorMode){
obj=OpenLayers.Layer.Vector.prototype.clone.apply(this,[obj]);
}else{
obj=OpenLayers.Layer.Markers.prototype.clone.apply(this,[obj]);
}
return obj;
},getFullRequestString:function(_1d,_1e){
var _1f=this.projection.getCode()||this.map.getProjection();
this.params.SRS=(_1f=="none")?null:_1f;
return OpenLayers.Layer.Grid.prototype.getFullRequestString.apply(this,arguments);
},commit:function(){
if(!this.writer){
var _20={};
if(this.map&&!this.projection.equals(this.map.getProjectionObject())){
_20.externalProjection=this.projection;
_20.internalProjection=this.map.getProjectionObject();
}
this.writer=new OpenLayers.Format.WFS(_20,this);
}
var _21=this.writer.write(this.features);
OpenLayers.Request.POST({url:this.url,data:_21,success:this.commitSuccess,failure:this.commitFailure,scope:this});
},commitSuccess:function(_22){
var _23=_22.responseText;
if(_23.indexOf("SUCCESS")!=-1){
this.commitReport(OpenLayers.i18n("commitSuccess",{"response":_23}));
for(var i=0;i<this.features.length;i++){
this.features[i].state=null;
}
}else{
if(_23.indexOf("FAILED")!=-1||_23.indexOf("Exception")!=-1){
this.commitReport(OpenLayers.i18n("commitFailed",{"response":_23}));
}
}
},commitFailure:function(_25){
},commitReport:function(_26,_27){
alert(_26);
},refresh:function(){
if(this.tile){
if(this.vectorMode){
this.renderer.clear();
this.features.length=0;
}else{
this.clearMarkers();
this.markers.length=0;
}
this.tile.draw();
}
},CLASS_NAME:"OpenLayers.Layer.WFS"});

OpenLayers.Control.MouseToolbar=OpenLayers.Class(OpenLayers.Control.MouseDefaults,{mode:null,buttons:null,direction:"vertical",buttonClicked:null,initialize:function(_1,_2){
OpenLayers.Control.prototype.initialize.apply(this,arguments);
this.position=new OpenLayers.Pixel(OpenLayers.Control.MouseToolbar.X,OpenLayers.Control.MouseToolbar.Y);
if(_1){
this.position=_1;
}
if(_2){
this.direction=_2;
}
this.measureDivs=[];
},destroy:function(){
for(var _3 in this.buttons){
var _4=this.buttons[_3];
_4.map=null;
_4.events.destroy();
}
OpenLayers.Control.MouseDefaults.prototype.destroy.apply(this,arguments);
},draw:function(){
OpenLayers.Control.prototype.draw.apply(this,arguments);
OpenLayers.Control.MouseDefaults.prototype.draw.apply(this,arguments);
this.buttons={};
var sz=new OpenLayers.Size(28,28);
var _6=new OpenLayers.Pixel(OpenLayers.Control.MouseToolbar.X,0);
this._addButton("zoombox","drag-rectangle-off.png","drag-rectangle-on.png",_6,sz,"Shift->Drag to zoom to area");
_6=_6.add((this.direction=="vertical"?0:sz.w),(this.direction=="vertical"?sz.h:0));
this._addButton("pan","panning-hand-off.png","panning-hand-on.png",_6,sz,"Drag the map to pan.");
_6=_6.add((this.direction=="vertical"?0:sz.w),(this.direction=="vertical"?sz.h:0));
this.switchModeTo("pan");
return this.div;
},_addButton:function(id,_8,_9,xy,sz,_c){
var _d=OpenLayers.Util.getImagesLocation()+_8;
var _e=OpenLayers.Util.getImagesLocation()+_9;
var _f=OpenLayers.Util.createAlphaImageDiv("OpenLayers_Control_MouseToolbar_"+id,xy,sz,_d,"absolute");
this.div.appendChild(_f);
_f.imgLocation=_d;
_f.activeImgLocation=_e;
_f.events=new OpenLayers.Events(this,_f,null,true);
_f.events.on({"mousedown":this.buttonDown,"mouseup":this.buttonUp,"dblclick":OpenLayers.Event.stop,scope:this});
_f.action=id;
_f.title=_c;
_f.alt=_c;
_f.map=this.map;
this.buttons[id]=_f;
return _f;
},buttonDown:function(evt){
if(!OpenLayers.Event.isLeftClick(evt)){
return;
}
this.buttonClicked=evt.element.action;
OpenLayers.Event.stop(evt);
},buttonUp:function(evt){
if(!OpenLayers.Event.isLeftClick(evt)){
return;
}
if(this.buttonClicked!=null){
if(this.buttonClicked==evt.element.action){
this.switchModeTo(evt.element.action);
}
OpenLayers.Event.stop(evt);
this.buttonClicked=null;
}
},defaultDblClick:function(evt){
this.switchModeTo("pan");
this.performedDrag=false;
var _13=this.map.getLonLatFromViewPortPx(evt.xy);
this.map.setCenter(_13,this.map.zoom+1);
OpenLayers.Event.stop(evt);
return false;
},defaultMouseDown:function(evt){
if(!OpenLayers.Event.isLeftClick(evt)){
return;
}
this.mouseDragStart=evt.xy.clone();
this.performedDrag=false;
this.startViaKeyboard=false;
if(evt.shiftKey&&this.mode!="zoombox"){
this.switchModeTo("zoombox");
this.startViaKeyboard=true;
}else{
if(evt.altKey&&this.mode!="measure"){
this.switchModeTo("measure");
}else{
if(!this.mode){
this.switchModeTo("pan");
}
}
}
switch(this.mode){
case "zoombox":
this.map.div.style.cursor="crosshair";
this.zoomBox=OpenLayers.Util.createDiv("zoomBox",this.mouseDragStart,null,null,"absolute","2px solid red");
this.zoomBox.style.backgroundColor="white";
this.zoomBox.style.filter="alpha(opacity=50)";
this.zoomBox.style.opacity="0.50";
this.zoomBox.style.fontSize="1px";
this.zoomBox.style.zIndex=this.map.Z_INDEX_BASE["Popup"]-1;
this.map.viewPortDiv.appendChild(this.zoomBox);
this.performedDrag=true;
break;
case "measure":
var _15="";
if(this.measureStart){
var _16=this.map.getLonLatFromViewPortPx(this.mouseDragStart);
_15=OpenLayers.Util.distVincenty(this.measureStart,_16);
_15=Math.round(_15*100)/100;
_15=_15+"km";
this.measureStartBox=this.measureBox;
}
this.measureStart=this.map.getLonLatFromViewPortPx(this.mouseDragStart);
this.measureBox=OpenLayers.Util.createDiv(null,this.mouseDragStart.add(-2-parseInt(this.map.layerContainerDiv.style.left),-2-parseInt(this.map.layerContainerDiv.style.top)),null,null,"absolute");
this.measureBox.style.width="4px";
this.measureBox.style.height="4px";
this.measureBox.style.fontSize="1px";
this.measureBox.style.backgroundColor="red";
this.measureBox.style.zIndex=this.map.Z_INDEX_BASE["Popup"]-1;
this.map.layerContainerDiv.appendChild(this.measureBox);
if(_15){
this.measureBoxDistance=OpenLayers.Util.createDiv(null,this.mouseDragStart.add(-2-parseInt(this.map.layerContainerDiv.style.left),2-parseInt(this.map.layerContainerDiv.style.top)),null,null,"absolute");
this.measureBoxDistance.innerHTML=_15;
this.measureBoxDistance.style.zIndex=this.map.Z_INDEX_BASE["Popup"]-1;
this.map.layerContainerDiv.appendChild(this.measureBoxDistance);
this.measureDivs.push(this.measureBoxDistance);
}
this.measureBox.style.zIndex=this.map.Z_INDEX_BASE["Popup"]-1;
this.map.layerContainerDiv.appendChild(this.measureBox);
this.measureDivs.push(this.measureBox);
break;
default:
this.map.div.style.cursor="move";
break;
}
document.onselectstart=function(){
return false;
};
OpenLayers.Event.stop(evt);
},switchModeTo:function(_17){
if(_17!=this.mode){
if(this.mode&&this.buttons[this.mode]){
OpenLayers.Util.modifyAlphaImageDiv(this.buttons[this.mode],null,null,null,this.buttons[this.mode].imgLocation);
}
if(this.mode=="measure"&&_17!="measure"){
for(var i=0;i<this.measureDivs.length;i++){
if(this.measureDivs[i]){
this.map.layerContainerDiv.removeChild(this.measureDivs[i]);
}
}
this.measureDivs=[];
this.measureStart=null;
}
this.mode=_17;
if(this.buttons[_17]){
OpenLayers.Util.modifyAlphaImageDiv(this.buttons[_17],null,null,null,this.buttons[_17].activeImgLocation);
}
switch(this.mode){
case "zoombox":
this.map.div.style.cursor="crosshair";
break;
default:
this.map.div.style.cursor="";
break;
}
}
},leaveMode:function(){
this.switchModeTo("pan");
},defaultMouseMove:function(evt){
if(this.mouseDragStart!=null){
switch(this.mode){
case "zoombox":
var _1a=Math.abs(this.mouseDragStart.x-evt.xy.x);
var _1b=Math.abs(this.mouseDragStart.y-evt.xy.y);
this.zoomBox.style.width=Math.max(1,_1a)+"px";
this.zoomBox.style.height=Math.max(1,_1b)+"px";
if(evt.xy.x<this.mouseDragStart.x){
this.zoomBox.style.left=evt.xy.x+"px";
}
if(evt.xy.y<this.mouseDragStart.y){
this.zoomBox.style.top=evt.xy.y+"px";
}
break;
default:
var _1a=this.mouseDragStart.x-evt.xy.x;
var _1b=this.mouseDragStart.y-evt.xy.y;
var _1c=this.map.getSize();
var _1d=new OpenLayers.Pixel(_1c.w/2+_1a,_1c.h/2+_1b);
var _1e=this.map.getLonLatFromViewPortPx(_1d);
this.map.setCenter(_1e,null,true);
this.mouseDragStart=evt.xy.clone();
}
this.performedDrag=true;
}
},defaultMouseUp:function(evt){
if(!OpenLayers.Event.isLeftClick(evt)){
return;
}
switch(this.mode){
case "zoombox":
this.zoomBoxEnd(evt);
if(this.startViaKeyboard){
this.leaveMode();
}
break;
case "pan":
if(this.performedDrag){
this.map.setCenter(this.map.center);
}
}
document.onselectstart=null;
this.mouseDragStart=null;
this.map.div.style.cursor="default";
},defaultMouseOut:function(evt){
if(this.mouseDragStart!=null&&OpenLayers.Util.mouseLeft(evt,this.map.div)){
if(this.zoomBox){
this.removeZoomBox();
if(this.startViaKeyboard){
this.leaveMode();
}
}
this.mouseDragStart=null;
this.map.div.style.cursor="default";
}
},defaultClick:function(evt){
if(this.performedDrag){
this.performedDrag=false;
return false;
}
},CLASS_NAME:"OpenLayers.Control.MouseToolbar"});
OpenLayers.Control.MouseToolbar.X=6;
OpenLayers.Control.MouseToolbar.Y=300;

OpenLayers.Control.NavToolbar=OpenLayers.Class(OpenLayers.Control.Panel,{initialize:function(_1){
OpenLayers.Control.Panel.prototype.initialize.apply(this,[_1]);
var _2=[];
if(_1.zoomIn){
_2.push(new OpenLayers.Control.ZoomBox());
}
if(_1.zoomOut){
_2.push(new OpenLayers.Control.ZoomBoxOut());
}
if(_1.pan){
if(_1.panEffect){
_2.push(new OpenLayers.Control.Navigation({"animatedPanEnabled":true}));
}else{
_2.push(new OpenLayers.Control.Navigation());
}
}
if(_1.zoomMaxExtent){
_2.push(new OpenLayers.Control.ZoomToMaxExtent());
}
if(_1.history){
_2.push(nav.previous);
_2.push(nav.next);
}
if(_2.length>0){
this.addControls(_2);
}
},draw:function(){
var _3=OpenLayers.Control.Panel.prototype.draw.apply(this,arguments);
this.activateControl(this.controls[0]);
return _3;
},CLASS_NAME:"OpenLayers.Control.NavToolbar"});

OpenLayers.Control.EditingToolbar=OpenLayers.Class(OpenLayers.Control.Panel,{initialize:function(_1,_2){
OpenLayers.Control.Panel.prototype.initialize.apply(this,[_2]);
this.addControls([new OpenLayers.Control.Navigation()]);
var _3=[new OpenLayers.Control.DrawFeature(_1,OpenLayers.Handler.Point,{"displayClass":"olControlDrawFeaturePoint"}),new OpenLayers.Control.DrawFeature(_1,OpenLayers.Handler.Path,{"displayClass":"olControlDrawFeaturePath"}),new OpenLayers.Control.DrawFeature(_1,OpenLayers.Handler.Polygon,{"displayClass":"olControlDrawFeaturePolygon"})];
for(var i=0;i<_3.length;i++){
_3[i].featureAdded=function(_5){
_5.state=OpenLayers.State.INSERT;
};
}
this.addControls(_3);
},draw:function(){
var _6=OpenLayers.Control.Panel.prototype.draw.apply(this,arguments);
this.activateControl(this.controls[0]);
return _6;
},CLASS_NAME:"OpenLayers.Control.EditingToolbar"});

OpenLayers.Lang={code:null,defaultCode:"en",getCode:function(){
if(!OpenLayers.Lang.code){
OpenLayers.Lang.setCode();
}
return OpenLayers.Lang.code;
},setCode:function(_1){
var _2;
if(!_1){
_1=(OpenLayers.Util.getBrowserName()=="msie")?navigator.userLanguage:navigator.language;
}
var _3=_1.split("-");
_3[0]=_3[0].toLowerCase();
if(typeof OpenLayers.Lang[_3[0]]=="object"){
_2=_3[0];
}
if(_3[1]){
var _4=_3[0]+"-"+_3[1].toUpperCase();
if(typeof OpenLayers.Lang[_4]=="object"){
_2=_4;
}
}
if(!_2){
OpenLayers.Console.warn("Failed to find OpenLayers.Lang."+_3.join("-")+" dictionary, falling back to default language");
_2=OpenLayers.Lang.defaultCode;
}
OpenLayers.Lang.code=_2;
},translate:function(_5,_6){
var _7=OpenLayers.Lang[OpenLayers.Lang.getCode()];
var _8=_7[_5];
if(!_8){
_8=_5;
}
if(_6){
_8=OpenLayers.String.format(_8,_6);
}
return _8;
}};
OpenLayers.i18n=OpenLayers.Lang.translate;

OpenLayers.Lang.en={"unhandledRequest":"Unhandled request return ${statusText}","permalink":"Permalink","overlays":"Overlays","baseLayer":"Base Layer","sameProjection":"The overview map only works when it is in the same projection as the main map","readNotImplemented":"Read not implemented.","writeNotImplemented":"Write not implemented.","noFID":"Can't update a feature for which there is no FID.","errorLoadingGML":"Error in loading GML file ${url}","browserNotSupported":"Your browser does not support vector rendering. Currently supported renderers are:\n${renderers}","componentShouldBe":"addFeatures : component should be an ${geomType}","getFeatureError":"getFeatureFromEvent called on layer with no renderer. This usually means you "+"destroyed a layer, but not some handler which is associated with it.","minZoomLevelError":"The minZoomLevel property is only intended for use "+"with the FixedZoomLevels-descendent layers. That this "+"wfs layer checks for minZoomLevel is a relic of the"+"past. We cannot, however, remove it without possibly "+"breaking OL based applications that may depend on it."+" Therefore we are deprecating it -- the minZoomLevel "+"check below will be removed at 3.0. Please instead "+"use min/max resolution setting as described here: "+"http://trac.openlayers.org/wiki/SettingZoomLevels","commitSuccess":"WFS Transaction: SUCCESS ${response}","commitFailed":"WFS Transaction: FAILED ${response}","googleWarning":"The Google Layer was unable to load correctly.<br><br>"+"To get rid of this message, select a new BaseLayer "+"in the layer switcher in the upper-right corner.<br><br>"+"Most likely, this is because the Google Maps library "+"script was either not included, or does not contain the "+"correct API key for your site.<br><br>"+"Developers: For help getting this working correctly, "+"<a href='http://trac.openlayers.org/wiki/Google' "+"target='_blank'>click here</a>","getLayerWarning":"The ${layerType} Layer was unable to load correctly.<br><br>"+"To get rid of this message, select a new BaseLayer "+"in the layer switcher in the upper-right corner.<br><br>"+"Most likely, this is because the ${layerLib} library "+"script was not correctly included.<br><br>"+"Developers: For help getting this working correctly, "+"<a href='http://trac.openlayers.org/wiki/${layerLib}' "+"target='_blank'>click here</a>","scale":"Scale = 1 : ${scaleDenom}","layerAlreadyAdded":"You tried to add the layer: ${layerName} to the map, but it has already been added","reprojectDeprecated":"You are using the 'reproject' option "+"on the ${layerName} layer. This option is deprecated: "+"its use was designed to support displaying data over commercial "+"basemaps, but that functionality should now be achieved by using "+"Spherical Mercator support. More information is available from "+"http://trac.openlayers.org/wiki/SphericalMercator.","methodDeprecated":"This method has been deprecated and will be removed in 3.0. "+"Please use ${newMethod} instead.","boundsAddError":"You must pass both x and y values to the add function.","lonlatAddError":"You must pass both lon and lat values to the add function.","pixelAddError":"You must pass both x and y values to the add function.","unsupportedGeometryType":"Unsupported geometry type: ${geomType}","pagePositionFailed":"OpenLayers.Util.pagePosition failed: element with id ${elemId} may be misplaced.","end":""};

