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

package org.mapfaces.component.treetable;

import javax.faces.context.FacesContext;
import org.mapfaces.component.html.HtmlTreeTable;

/**
 * 
 * @author Kevin Delfour
 */
public class UITreeTable extends HtmlTreeTable {

    private static final String FAMILY = "org.mapfaces.component.tree.TreeTable";
    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.tree.TreeTable";

    // ---------------------------------------------------------- Additionals fields
    // Displays attributes
    private boolean collapse;
    private int collapseDepth = 1;
    // Styles attributes
    private String nodeStyle;
    private String nodeClass;
    private String leafStyle;
    private String leafClass;
    private String oddLineStyle;
    private String oddLineClass;
    private String evenLineStyle;
    private String evenLineClass;
    /**
     * <p>The activateAjaxLoading attribute associated with this component
     * allow to know if the model use by tree table will be fully loaded
     * </p>
     */
    private boolean activateAjaxLoading = true;
    /**
     * To disable css loading. True value by default.
     */
    private boolean loadCss = true;
    /**
     * To disable js loading. True value by default.
     */
    private boolean loadJs = true;

    // ---------------------------------------------------------- Methods
    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {

        final Object values[] = new Object[13];
        values[0] = super.saveState(context);
        values[1] = isCollapse();
        values[2] = getCollapseDepth();
        values[3] = getNodeStyle();
        values[4] = getNodeClass();
        values[5] = getLeafStyle();
        values[6] = getLeafClass();
        values[7] = getOddLineStyle();
        values[8] = getOddLineClass();
        values[9] = getEvenLineStyle();
        values[10]= getEvenLineClass();
        values[11]= isLoadCss();
        values[12]= isLoadJs();
        return values;

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {

        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setCollapse((Boolean) values[1]);
        setCollapseDepth((Integer) values[2]);
        setNodeStyle((String)values[3]);
        setNodeClass((String)values[4]);
        setLeafStyle((String)values[5]);
        setLeafClass((String)values[6]);
        setOddLineStyle((String)values[7]);
        setOddLineClass((String)values[8]);
        setEvenLineStyle((String)values[9]);
        setEvenLineClass((String)values[10]);
        setLoadCss((Boolean) values[11]);
        setLoadJs((Boolean) values[12]);
        
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    // ---------------------------------------------------------- Accessors Methods
    /**
     * @return the collapse
     */
    public boolean isCollapse() {
        return this.collapse;
    }

    /**
     * @param aCollapse the collapse to set
     */
    public void setCollapse(boolean aCollapse) {
        this.collapse = aCollapse;
    }

    /**
     * @return the collapseDepth
     */
    public int getCollapseDepth() {
        return collapseDepth;
    }

    /**
     * @param collapseDepth the collapseDepth to set
     */
    public void setCollapseDepth(int collapseDepth) {
        this.collapseDepth = collapseDepth;
    }

    /**
     * @return the nodeStyle
     */
    public String getNodeStyle() {
        return nodeStyle;
    }

    /**
     * @param nodeStyle the nodeStyle to set
     */
    public void setNodeStyle(String nodeStyle) {
        this.nodeStyle = nodeStyle;
    }

    /**
     * @return the nodeClass
     */
    public String getNodeClass() {
        return nodeClass;
    }

    /**
     * @param nodeClass the nodeClass to set
     */
    public void setNodeClass(String nodeClass) {
        this.nodeClass = nodeClass;
    }

    /**
     * @return the leafStyle
     */
    public String getLeafStyle() {
        return leafStyle;
    }

    /**
     * @param leafStyle the leafStyle to set
     */
    public void setLeafStyle(String leafStyle) {
        this.leafStyle = leafStyle;
    }

    /**
     * @return the leafClass
     */
    public String getLeafClass() {
        return leafClass;
    }

    /**
     * @param leafClass the leafClass to set
     */
    public void setLeafClass(String leafClass) {
        this.leafClass = leafClass;
    }

    /**
     * @return the oddLineStyle
     */
    public String getOddLineStyle() {
        return oddLineStyle;
    }

    /**
     * @param oddLineStyle the oddLineStyle to set
     */
    public void setOddLineStyle(String oddLineStyle) {
        this.oddLineStyle = oddLineStyle;
    }

    /**
     * @return the oddLineClass
     */
    public String getOddLineClass() {
        return oddLineClass;
    }

    /**
     * @param oddLineClass the oddLineClass to set
     */
    public void setOddLineClass(String oddLineClass) {
        this.oddLineClass = oddLineClass;
    }

    /**
     * @return the evenLineStyle
     */
    public String getEvenLineStyle() {
        return evenLineStyle;
    }

    /**
     * @param evenLineStyle the evenLineStyle to set
     */
    public void setEvenLineStyle(String evenLineStyle) {
        this.evenLineStyle = evenLineStyle;
    }

    /**
     * @return the evenLineClass
     */
    public String getEvenLineClass() {
        return evenLineClass;
    }

    /**
     * @param evenLineClass the evenLineClass to set
     */
    public void setEvenLineClass(String evenLineClass) {
        this.evenLineClass = evenLineClass;
    }

    /**
     * @return the activateAjaxLoading
     */
    public boolean isActivateAjaxLoading() {
        return activateAjaxLoading;
    }

    /**
     * @param activateAjaxLoading the activateAjaxLoading to set
     */
    public void setActivateAjaxLoading(boolean activateAjaxLoading) {
        this.activateAjaxLoading = activateAjaxLoading;
    }

    /**
     * @return the loadCss
     */
    public boolean isLoadCss() {
        return loadCss;
    }

    /**
     * @param loadCss the loadCss to set
     */
    public void setLoadCss(boolean loadCss) {
        this.loadCss = loadCss;
    }

    /**
     * @return the loadJs
     */
    public boolean isLoadJs() {
        return loadJs;
    }

    /**
     * @param loadJs the loadJs to set
     */
    public void setLoadJs(boolean loadJs) {
        this.loadJs = loadJs;
    }

}
