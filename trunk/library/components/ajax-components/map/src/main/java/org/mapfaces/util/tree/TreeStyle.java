/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
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
package org.mapfaces.util.tree;

/**
 * CSS definitions for tree components
 * Definitions from treeW3Cn2 css file : This document is valid in accordance with the recommendation CSS version 2.1!
 * @see treeW3Cn2.css
 *
 * @author Kevin Delfour
 */
public final class TreeStyle {

    /**
     * Default Css Files URL
     * @see /org/mapfaces/resources/tree/css/tree.css
     */
    public static final String default_cssFilesUrl = "/org/mapfaces/resources/tree/css/treeW3Cn2.css";
    /**
     * Default Css Files URL for Drag n Drop
     * @see /org/mapfaces/resources/treetable/css/dragndrop.css
     */
    public static final String default_cssDndFilesUrl = "/org/mapfaces/resources/treetable/css/dragndrop.css";
    /* Main style class */
    public static final String default_mainStyle = "x-panel x-column-tree x-tree";
    public static final String default_mainHeaderStyle = "x-panel-header x-unselectable";
    public static final String default_mainNoHeaderStyle = "x-panel-body x-panel-body-noheader x-unselectable";
    public static final String default_mainHeaderTextStyle = "x-panel-header-text";
    public static final String default_mainBwrapStyle = "x-panel-bwrap";
    public static final String default_mainBodyStyle = "x-panel-body";
    /* Frame style Class */
    public static final String default_frameTlStyle = "x-panel-tl";
    public static final String default_frameTrStyle = "x-panel-tr";
    public static final String default_frameTcStyle = "x-panel-tc";
    public static final String default_frameMlStyle = "x-panel-ml";
    public static final String default_frameMrStyle = "x-panel-mr";
    public static final String default_frameMcStyle = "x-panel-mc";
    public static final String default_frameBlStyle = "x-panel-bl";
    public static final String default_frameBrStyle = "x-panel-br";
    public static final String default_frameBcStyle = "x-panel-bc";
    /* Headers style class */
    public static final String default_headersStyle = "x-tree-headers";
    public static final String default_headerStyle = "x-tree-hd";
    public static final String default_headerTextStyle = "x-tree-hd-text";
    /* Toolbar style class */
    public static final String default_toolbarStyle = "x-toolbar";
    /* Clean style class */
    public static final String default_clearStyle = "x-clear";
    /* Line style class */
    public static final String default_rootStyle = "x-tree-root-ct x-tree-no-lines";
    public static final String default_rootBwrapStyle = "x-tree-root-node";
    /* Column style*/
    public static final String default_columnStyle = "x-tree-col";
    public static final String default_columnTextStyle = "x-tree-col-text";
    /* Node style class */
    public static final String default_lineStyle = "x-tree-node";
    public static final String default_nodeExpandedStyle = "x-tree-node-el x-tree-node-expanded";
    public static final String default_nodeCollapsedStyle = "x-tree-node-el x-tree-node-collapsed";
    public static final String default_nodeIdentStyle = "x-tree-node-ident";
    public static final String default_nodeMinusIconStyle = "x-tree-ec-icon x-tree-elbow-minus";
    public static final String default_nodePlusIconStyle = "x-tree-ec-icon x-tree-elbow-plus";
    public static final String default_nodeRepresentationStyle = "x-tree-icon";
    public static final String default_nodeAnchorStyle = "x-tree-node-anchor";
    public static final String default_nodeChildStyle = "x-tree-node-ct";
    /* Leaf style class */
    static final String default_leafStyle = "x-tree-node-el x-tree-node-leaf";
    static final String default_leafRepresentationStyle = "x-tree-ec-icon";
    /*
     * Css Style customize
     */
    /* Row customize style */
    public static final String default_rowStyle = "position:relative; position:inherit; list-style-type:none;"; //@TODO the conditionnal css must be applied instead this hack for Internet Explorer.
    private static String rowStyle="";
    private static String rowClass = "x-tree-node x-tree-lines";

    /* Methods and Accessors */
    public static String getRowStyle() {
        return rowStyle + default_rowStyle;
    }

    public static void setRowStyle(String style) {
        rowStyle = style;
    }

    public static void initRowStyle() {
        rowStyle = "";
        setRowStyle(default_rowStyle);
    }

    public static String getRowClass() {
        return rowClass;
    }
}
