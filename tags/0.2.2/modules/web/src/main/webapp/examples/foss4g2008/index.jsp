<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>
<%@taglib prefix="mf-model" uri="http://mapfaces.org/taglib-models"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html> 
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" href="../../resource/skin/foss4g2008/style.css" type="text/css"  />
            <style type="text/css">
                <!--
                body {
                    margin-left: 0px;
                    margin-top: 0px;
                    margin-right: 0px;
                    margin-bottom: 0px;
                    background-color: #F8F4F1;
                }
                a:link {
                    color: #333333;
                }
                a:visited {
                    color: #333333;
                }
                -->
            </style>
        </head>
        <body>
            <iframe id='target_upload' name='target_upload' src='' style='display: none'></iframe>
            <div id="wrap">
                <div id="header-main">
                    <div id="header-left"></div>
                    <div id="header-right">
                        <h2 id="logo-slogan">Free and Open Source Software for Geospatial</h2>
                    </div>
                </div>
                <div id="separate"></div>
                <div id="content-wrap" style="position:absolute;top:85px;left:5px;right:5px;bottom:5px;">
                    <h:form id="form">
                        
                        <mf-model:Context id="owsContext" service="data/context/ifremer.xml">
                            
                            <f:verbatim><div id="verbatim-mappane" style="border: 3px ridge rgb(214, 227, 242); position: absolute; right: 0px; top: 0px; left: 336px;bottom: 180px; cursor: pointer;  z-index: 0; "></f:verbatim>
                                <mf:MapPane id="mappane" style="width:100%;height:100%;position:absolute;z-index:0;"></mf:MapPane>
                            <f:verbatim></div></f:verbatim>
                            
                            <mf:ButtonBar id="bar" styleClass="mfButtonBar horizontal"  style="left:370px;"></mf:ButtonBar>
                            
                            <f:verbatim><div id="verbatim-cursortrack" style="position: absolute;left: 370px;bottom: 183px;height:50px;width:320px; cursor: pointer;  z-index: 0; "></f:verbatim>                            
                                <mf:CursorTrack id="cursorTrack" style="position:absolute; background-color:white;padding:5px;opacity:0.7;margin:5px;" showDMS="true" showLatLon="false" ></mf:CursorTrack>
                            <f:verbatim></div></f:verbatim>
                            
                            <f:verbatim><div id="verbatim-timeline" style="right: 6px; left: 0px; bottom: 0px; position: absolute; z-index: 0;"></f:verbatim>
                                <mf:TimeLine id="timeline" style="border: 3px ridge rgb(214, 227, 242); font-size: 0.8em; position: absolute; float: left; left:202px;right:0px;" height="170" inputDate="true" theme="ArcheoTheme" synchronizeBands="true" dynamicBands="true" activeControl="true" styleControlPanel="border: 3px ridge rgb(214, 227, 242); border-right:none; background: white; height: 60px; float: left; width: 200px;" minifyJS="false" enableBandsInput="true">
                                </mf:TimeLine>
                            <f:verbatim></div></f:verbatim>
                            
                            <f:verbatim><div id="verbatim-layercontrol" style="border: 3px ridge rgb(214, 227, 242); width:327px; left:0px;top:0px; bottom: 180px;  position: absolute; z-index: 0;"> </f:verbatim>
                                <mf:LayerControl minifyJS="false" id="lcc" style="height:100%;left:0px;top:0px;position:relative;width:327px;" debug="false" styleTreeTable="height:100%; width:327px;background:#ECF1F8;" styleTreePanel="background:#D6E3F2;height:100%;overflow:hidden;" widthOpacityColumn="50" widthElevationColumn="50" titlePanel="Layers control panel" headerTreeColumn="MapContext layers" styleEvenLines="background:#d6e3f3;" styleOddLines="background:#fff1c7;" elevationColumn="true"></mf:LayerControl>
                                
                            <f:verbatim></div></f:verbatim>
                            
                        </mf-model:Context>
                        
                        
                    </h:form>   
                </div>
            </div>
            
            <script>
                function resizeDivs(){
                    document.getElementById("verbatim-layercontrol").style.bottom = (document.getElementById("form:timeline-wrap").offsetHeight + 2)+"px";
                    document.getElementById("verbatim-mappane").style.bottom = (document.getElementById("form:timeline-wrap").offsetHeight + 2)+"px";
                    document.getElementById("verbatim-cursortrack").style.bottom = (document.getElementById("form:timeline-wrap").offsetHeight + 2)+"px";
                }
                resizeDivs();

                var height = document.getElementById('form:lcc').offsetHeight - 50;
                document.getElementById("panel_lines:form:TreeTable_TreePanel").style.maxHeight=height+'px';

                function updateLayerControlSize() {
                    var height = document.getElementById('form:lcc').offsetHeight - 50;
                    document.getElementById("panel_lines:form:TreeTable_TreePanel").style.maxHeight=height+'px';}
                /*OpenLayers.Event.observe(window, 'resize',
                                            updateLayerControlSize());
                 */

            </script>
            
            <style>
                .x-tree-node-info{background:white;}
                .timeline-ether-marker-bottom{color:black;}
                
            </style>
            
        </body>
    </html>
</f:view>
