/*
 *    Mapfaces - http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.mapfaces.demo.bean;

import java.io.File;

/**
 * Utility class to compress JavaScript files.
 *
 * @author Olivier Terral (Geomatys)
 */
public class Compress {

    public static void compressJS(String[] noFiles, String rhinoPath, String outputPath, String dirPath) {
        File dir = new File(dirPath);
        String[] cmd = new String[]{};
        Runtime run = Runtime.getRuntime();
        Process tmp;

        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith(".js") && !files[i].isDirectory()) {
                boolean breaked = false;
                for (int j = 0; j < noFiles.length; j++) {
                    if ((files[i].getPath().contains(noFiles[j])) || files[i].getPath().contains("GmlRendererWZ.js") || files[i].getPath().contains("MapbuilderCompressed.js") || files[i].getPath().contains("MapbuilderServerLoad.js")) {
                        breaked = true;
                        break;
                    }
                }
                if (!breaked) {
                    try {
                        cmd = new String[]{"cmd.exe", "/C", "java -jar " + rhinoPath + " -c " + files[i] + " >> " + outputPath + " 2>&1"};
                        // System.out.println(cmd);
                        tmp = run.exec(cmd);
                        tmp.waitFor();//si l'application doit attendre a ce que ce process fini
                        System.out.println(files[i].getPath());

                    } catch (Exception e) {
                        System.out.println("erreur d'execution " + cmd + e.toString());
                        e.printStackTrace();
                    }

                }
            }


        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory() && !files[i].getName().contains("overlib") && !files[i].getName().contains("graphics") && !files[i].getName().contains("xsl") && !files[i].getName().contains("wz_jsgraphics") && !files[i].getName().contains("overlib") && !files[i].getName().contains("schemas") && !files[i].getName().contains("skin") && !files[i].getName().contains("sarissa")) {
                compressJS(noFiles, rhinoPath, outputPath, files[i].getPath() + "\\");
            }

        }

    }

    public static void compressArrayOfFiles(String[] files, String rhinoPath, String outputPath, String libPath) {
        //Win
        //String[] cmd=new String[] {};
        String cmd = new String();
        Runtime run = Runtime.getRuntime();
        Process tmp;
        try {

            for (int i = 0; i < files.length; i++) {
                if (i == 0) {
                    //Win
                    //cmd =new String[] {"cmd.exe" , "/C" , "java -jar "+ rhinoPath +" -c "+ libPath + files[i] +" > "+outputPath+" 2>&1"};
                    cmd = new String("java -jar " + rhinoPath + " -c " + libPath + files[i] + " > " + outputPath + " 2>&1");

                } else {
                    //Win
                    //cmd =new String[] {"cmd.exe" , "/C" , "java -jar "+ rhinoPath +" -c "+ libPath + files[i] +" >> "+outputPath+" 2>&1"};
                    cmd = new String("java -jar " + rhinoPath + " -c " + libPath + files[i] + " >> " + outputPath + " 2>&1");

                }
                System.out.println(cmd);
                //System.out.println(libPath + files[i]+" >> "+outputPath);
                tmp = run.exec(cmd);
                tmp.waitFor();//si l'application doit attendre a ce que ce process fini

                //System.out.println(tmp.exitValue());

            }

        } catch (Exception e) {
            System.out.println("erreur d'execution " + cmd + e.toString());
            e.printStackTrace();
        }

    }

    /**
     * @param args
     */
    public static void main(String... s) {

        // TODO Auto-generated method stub
        //System.out.println(args[0]+" "+args[1]+" "+args[2]+" "+args[3]+" "+args[4]);
//		String rhinoPath =args[0];
//		String olCompressFilePath = args[1];
//		String mbCompressFilePath = args[2];
//		String mbPath = args[3];
//		String olPath = args[4];

//		String rhinoPath ="/home/olivier/custom_rhino.jar";
//		String olCompressFilePath ="/home/olivier/zip.js";
//		String olPath ="/home/olivier/svn/mapfaces/trunk/modules/library/main/src/main/resources/org/mapfaces/resources/";
        String rhinoPath = "D:\\custom_rhino.jar";
        String olCompressFilePath = "D:\\zip.js";
        String olPath = "D:\\svn/mapfaces/trunk/modules/library/main/src/main/resources/org/mapfaces/resources/";

        //String mbPath = "c:\\www\\geomatys\\trunk\\mapbuilder\\mapbuilder\\lib";
        //String olPath = args[4];
        String[] olFiles = new String[]{
            "openlayers/lib/OpenLayers/Util.js",
            "openlayers/lib/OpenLayers/BaseTypes.js",
            "openlayers/lib/OpenLayers/BaseTypes/Class.js",
            "openlayers/lib/OpenLayers/BaseTypes/Bounds.js",
            "openlayers/lib/OpenLayers/BaseTypes/Element.js",
            "openlayers/lib/OpenLayers/BaseTypes/LonLat.js",
            "openlayers/lib/OpenLayers/BaseTypes/Pixel.js",
            "openlayers/lib/OpenLayers/BaseTypes/Size.js",
            "openlayers/lib/OpenLayers/Console.js",
            "openlayers/lib/OpenLayers/Tween.js",
            "openlayers/lib/Rico/Corner.js",
            "openlayers/lib/Rico/Color.js",
            //            "openlayers/lib/Gears/gears_init.js",
            "openlayers/lib/OpenLayers/Ajax.js",
            "openlayers/lib/OpenLayers/Request.js",
            "openlayers/lib/OpenLayers/Request/XMLHttpRequest.js",
            "openlayers/lib/OpenLayers/Events.js",
            "openlayers/lib/OpenLayers/Projection.js",
            "openlayers/lib/OpenLayers/Map.js",
            "openlayers/lib/OpenLayers/Layer.js",
            "openlayers/lib/OpenLayers/Icon.js",
            "openlayers/lib/OpenLayers/Marker.js",
            "openlayers/lib/OpenLayers/Marker/Box.js",
            "openlayers/lib/OpenLayers/Popup.js",
            "openlayers/lib/OpenLayers/Tile.js",
            "openlayers/lib/OpenLayers/Tile/Image.js",
            "openlayers/lib/OpenLayers/Tile/WFS.js",
            "openlayers/lib/OpenLayers/Layer/Image.js",
            "openlayers/lib/OpenLayers/Layer/SphericalMercator.js",
            "openlayers/lib/OpenLayers/Layer/EventPane.js",
            "openlayers/lib/OpenLayers/Layer/FixedZoomLevels.js",
            "openlayers/lib/OpenLayers/Layer/Google.js",
            "openlayers/lib/OpenLayers/Layer/VirtualEarth.js",
            "openlayers/lib/OpenLayers/Layer/Yahoo.js",
            "openlayers/lib/OpenLayers/Layer/HTTPRequest.js",
            "openlayers/lib/OpenLayers/Layer/Grid.js",
            "openlayers/lib/OpenLayers/Layer/MapGuide.js",
            "openlayers/lib/OpenLayers/Layer/MapServer.js",
            "openlayers/lib/OpenLayers/Layer/MapServer/Untiled.js",
            "openlayers/lib/OpenLayers/Layer/KaMap.js",
            "openlayers/lib/OpenLayers/Layer/KaMapCache.js",
            "openlayers/lib/OpenLayers/Layer/MultiMap.js",
            "openlayers/lib/OpenLayers/Layer/Markers.js",
            "openlayers/lib/OpenLayers/Layer/Text.js",
            "openlayers/lib/OpenLayers/Layer/WorldWind.js",
            "openlayers/lib/OpenLayers/Layer/WMS.js",
            "openlayers/lib/OpenLayers/Layer/WMS/Untiled.js",
            "openlayers/lib/OpenLayers/Layer/GeoRSS.js",
            "openlayers/lib/OpenLayers/Layer/Boxes.js",
            "openlayers/lib/OpenLayers/Layer/TMS.js",
            "openlayers/lib/OpenLayers/Layer/TileCache.js",
            "openlayers/lib/OpenLayers/Popup/Anchored.js",
            "openlayers/lib/OpenLayers/Popup/AnchoredBubble.js",
            "openlayers/lib/OpenLayers/Popup/Framed.js",
            "openlayers/lib/OpenLayers/Popup/FramedCloud.js",
            "openlayers/lib/OpenLayers/Feature.js",
            "openlayers/lib/OpenLayers/Feature/Vector.js",
            "openlayers/lib/OpenLayers/Feature/WFS.js",
            "openlayers/lib/OpenLayers/Handler.js",
            "openlayers/lib/OpenLayers/Handler/Click.js",
            "openlayers/lib/OpenLayers/Handler/Hover.js",
            "openlayers/lib/OpenLayers/Handler/Point.js",
            "openlayers/lib/OpenLayers/Handler/Path.js",
            "openlayers/lib/OpenLayers/Handler/Polygon.js",
            "openlayers/lib/OpenLayers/Handler/Feature.js",
            "openlayers/lib/OpenLayers/Handler/Drag.js",
            "openlayers/lib/OpenLayers/Handler/RegularPolygon.js",
            "openlayers/lib/OpenLayers/Handler/Box.js",
            "openlayers/lib/OpenLayers/Handler/MouseWheel.js",
            "openlayers/lib/OpenLayers/Handler/Keyboard.js",
            "openlayers/lib/OpenLayers/Control.js",
            "openlayers/lib/OpenLayers/Control/Attribution.js",
            "openlayers/lib/OpenLayers/Control/Button.js",
            "openlayers/lib/OpenLayers/Control/ZoomBox.js",
            "openlayers/lib/OpenLayers/Control/ZoomToMaxExtent.js",
            "openlayers/lib/OpenLayers/Control/DragPan.js",
            "openlayers/lib/OpenLayers/Control/Navigation.js",
            "openlayers/lib/OpenLayers/Control/MouseDefaults.js",
            "openlayers/lib/OpenLayers/Control/MousePosition.js",
            "openlayers/lib/OpenLayers/Control/OverviewMap.js",
            "openlayers/lib/OpenLayers/Control/KeyboardDefaults.js",
            //            "openlayers/lib/OpenLayers/Control/PanZoom.js",
            //            "openlayers/lib/OpenLayers/Control/PanZoomBar.js",
            "openlayers/lib/OpenLayers/Control/ArgParser.js",
            //            "openlayers/lib/OpenLayers/Control/Permalink.js",
            "openlayers/lib/OpenLayers/Control/Scale.js",
            "openlayers/lib/OpenLayers/Control/ScaleLine.js",
            "openlayers/lib/OpenLayers/Control/LayerSwitcher.js",
            "openlayers/lib/OpenLayers/Control/DrawFeature.js",
            "openlayers/lib/OpenLayers/Control/DragFeature.js",
            "openlayers/lib/OpenLayers/Control/ModifyFeature.js",
            "openlayers/lib/OpenLayers/Control/Panel.js",
            "openlayers/lib/OpenLayers/Control/SelectFeature.js",
            "openlayers/lib/OpenLayers/Control/NavigationHistory.js",
            "openlayers/lib/OpenLayers/Control/Measure.js",
            "openlayers/lib/OpenLayers/Geometry.js",
            "openlayers/lib/OpenLayers/Geometry/Rectangle.js",
            "openlayers/lib/OpenLayers/Geometry/Collection.js",
            "openlayers/lib/OpenLayers/Geometry/Point.js",
            "openlayers/lib/OpenLayers/Geometry/MultiPoint.js",
            "openlayers/lib/OpenLayers/Geometry/Curve.js",
            "openlayers/lib/OpenLayers/Geometry/LineString.js",
            "openlayers/lib/OpenLayers/Geometry/LinearRing.js",
            "openlayers/lib/OpenLayers/Geometry/Polygon.js",
            "openlayers/lib/OpenLayers/Geometry/MultiLineString.js",
            "openlayers/lib/OpenLayers/Geometry/MultiPolygon.js",
            "openlayers/lib/OpenLayers/Geometry/Surface.js",
            "openlayers/lib/OpenLayers/Renderer.js",
            "openlayers/lib/OpenLayers/Renderer/Elements.js",
            "openlayers/lib/OpenLayers/Renderer/SVG.js",
            "openlayers/lib/OpenLayers/Renderer/Canvas.js",
            "openlayers/lib/OpenLayers/Renderer/VML.js",
            "openlayers/lib/OpenLayers/Layer/Vector.js",
            "openlayers/lib/OpenLayers/Strategy.js",
            "openlayers/lib/OpenLayers/Strategy/Fixed.js",
            "openlayers/lib/OpenLayers/Strategy/Cluster.js",
            "openlayers/lib/OpenLayers/Strategy/Paging.js",
            "openlayers/lib/OpenLayers/Strategy/BBOX.js",
            "openlayers/lib/OpenLayers/Protocol.js",
            "openlayers/lib/OpenLayers/Protocol/HTTP.js",
            "openlayers/lib/OpenLayers/Protocol/SQL.js",
            "openlayers/lib/OpenLayers/Protocol/SQL/Gears.js",
            "openlayers/lib/OpenLayers/Layer/PointTrack.js",
            "openlayers/lib/OpenLayers/Layer/GML.js",
            "openlayers/lib/OpenLayers/Style.js",
            "openlayers/lib/OpenLayers/StyleMap.js",
            "openlayers/lib/OpenLayers/Rule.js",
            "openlayers/lib/OpenLayers/Filter.js",
            "openlayers/lib/OpenLayers/Filter/FeatureId.js",
            "openlayers/lib/OpenLayers/Filter/Logical.js",
            "openlayers/lib/OpenLayers/Filter/Comparison.js",
            "openlayers/lib/OpenLayers/Filter/Spatial.js",
            "openlayers/lib/OpenLayers/Format.js",
            "openlayers/lib/OpenLayers/Format/XML.js",
            "openlayers/lib/OpenLayers/Format/GML.js",
            "openlayers/lib/OpenLayers/Format/GML/Base.js",
            "openlayers/lib/OpenLayers/Format/GML/v2.js",
            "openlayers/lib/OpenLayers/Format/GML/v3.js",
            "openlayers/lib/OpenLayers/Format/KML.js",
            "openlayers/lib/OpenLayers/Format/GeoRSS.js",
            "openlayers/lib/OpenLayers/Format/WFS.js",
            "openlayers/lib/OpenLayers/Format/WKT.js",
            "openlayers/lib/OpenLayers/Format/OSM.js",
            "openlayers/lib/OpenLayers/Format/GPX.js",
            "openlayers/lib/OpenLayers/Format/SLD.js",
            "openlayers/lib/OpenLayers/Format/SLD/v1.js",
            "openlayers/lib/OpenLayers/Format/SLD/v1_0_0.js",
            "openlayers/lib/OpenLayers/Format/SLD/v1.js",
            "openlayers/lib/OpenLayers/Format/Filter.js",
            "openlayers/lib/OpenLayers/Format/Filter/v1.js",
            "openlayers/lib/OpenLayers/Format/Filter/v1_0_0.js",
            "openlayers/lib/OpenLayers/Format/Text.js",
            "openlayers/lib/OpenLayers/Format/JSON.js",
            "openlayers/lib/OpenLayers/Format/GeoJSON.js",
            "openlayers/lib/OpenLayers/Format/WMC.js",
            "openlayers/lib/OpenLayers/Format/WMC/v1.js",
            "openlayers/lib/OpenLayers/Format/WMC/v1_0_0.js",
            "openlayers/lib/OpenLayers/Format/WMC/v1_1_0.js",
            "openlayers/lib/OpenLayers/Layer/WFS.js",
            "openlayers/lib/OpenLayers/Control/MouseToolbar.js",
            "openlayers/lib/OpenLayers/Control/NavToolbar.js",
            //            "openlayers/lib/OpenLayers/Control/PanPanel.js",
            //            "openlayers/lib/OpenLayers/Control/Pan.js",
            "openlayers/lib/OpenLayers/Control/ZoomIn.js",
            "openlayers/lib/OpenLayers/Control/ZoomOut.js",
            //            "openlayers/lib/OpenLayers/Control/ZoomPanel.js",
            "openlayers/lib/OpenLayers/Control/EditingToolbar.js",
            "openlayers/lib/OpenLayers/Lang.js",
            "openlayers/lib/OpenLayers/Lang/en.js",
            /**File addes or modified for MapFaces*/
            "proj4js/proj4js-combined.js",
            "openlayers/custom/OpenLayers/Map.js",
            "openlayers/custom/OpenLayers/Handler/MouseWheel.js",
            "openlayers/custom/OpenLayers/Control/ZoomBoxOut.js",
            "openlayers/custom/OpenLayers/Control/Graticule.js",
            "openlayers/custom/OpenLayers/Control/Save.js",
            "openlayers/custom/OpenLayers/Control/GetFeatureInfo.js",
            "openlayers/custom/OpenLayers/Control/Navigation.js",
            "openlayers/custom/OpenLayers/Control/MousePosition.js",
            "openlayers/custom/OpenLayers/Control/OverviewMapExt.js",
            "openlayers/custom/OpenLayers/Control/MouseWheelDefaults.js",
            "openlayers/custom/OpenLayers/Control/ScaleExt.js",
            "openlayers/custom/OpenLayers/Control/ScaleBar.js",
            "openlayers/custom/OpenLayers/Format/GeoJSON.js",
            "openlayers/custom/OpenLayers/Control/NavToolbar.js",
            "openlayers/lib/OpenLayers/Lang/fr.js",
            "openlayers/custom/OpenLayers/Lang/enExt.js",
            "openlayers/custom/OpenLayers/Lang/frExt.js"
        };

        System.out.println("\\Debut compression ol");
        compressArrayOfFiles(olFiles, rhinoPath, olCompressFilePath, olPath);
        System.out.println("\\Fin compression ol");


    }
}
        

