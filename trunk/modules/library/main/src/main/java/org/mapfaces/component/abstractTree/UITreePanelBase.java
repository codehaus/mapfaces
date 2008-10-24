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
package org.mapfaces.component.abstractTree;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.mapfaces.models.tree.TreeTableModel;
import org.mapfaces.share.interfaces.AjaxInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;

/**
 *
 * @author kdelfour
 */
public abstract class UITreePanelBase extends UITreeBase implements AjaxInterface, Cloneable {

    /* Fields */
    private boolean init;
    private boolean TREEPANEL_EXPAND_ALL = true;
    private TreeTableModel view;
    private int oddEvenCountLine;
    private String border;
    private boolean collapsible;
    private boolean enableDragDrop;
    private boolean frame;
    private boolean header;
    private String height;
    private boolean rowId;
    private boolean showLines;
    private boolean showRoot;
    private String title;
    private String styleLeaf;
    private String styleNode;
    private String styleOdd;
    private String styleEven;
    private boolean loadAll;

    /* Accessors */
    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public boolean isCollapsible() {
        return collapsible;
    }

    public void setCollapsible(boolean collapsible) {
        this.collapsible = collapsible;
    }

    public boolean isFrame() {
        return frame;
    }

    public void setFrame(boolean frame) {
        this.frame = frame;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public boolean isRowId() {
        return rowId;
    }

    public void setRowId(boolean rowId) {
        this.rowId = rowId;
    }

    public boolean isShowLines() {
        return showLines;
    }

    public void setShowLines(boolean showLines) {
        this.showLines = showLines;
    }

    public boolean isShowRoot() {
        return showRoot;
    }

    public void setShowRoot(boolean showRoot) {
        this.showRoot = showRoot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEnableDragDrop() {
        return enableDragDrop;
    }

    public void setEnableDragDrop(boolean enableDragDrop) {
        this.enableDragDrop = enableDragDrop;
    }

    public boolean isLoadAll() {
        return loadAll;
    }

    public void setLoadAll(boolean loadAll) {
        this.loadAll = loadAll;
    }

    public boolean isTREEPANEL_EXPAND_ALL() {
        return TREEPANEL_EXPAND_ALL;
    }

    public void setTREEPANEL_EXPAND_ALL(boolean TREEPANEL_EXPAND_ALL) {
        this.TREEPANEL_EXPAND_ALL = TREEPANEL_EXPAND_ALL;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public TreeTableModel getView() {
        return view;
    }

    public void setView(TreeTableModel View) {
        this.view = View;
    }

    public String getStyleLeaf() {
        return styleLeaf;
    }

    public void setStyleLeaf(String styleLeaf) {
        this.styleLeaf = styleLeaf;
    }

    public String getStyleNode() {
        return styleNode;
    }

    public void setStyleNode(String styleNode) {
        this.styleNode = styleNode;
    }

    public String getStyleOdd() {
        return styleOdd;
    }

    public void setStyleOdd(String styleOdd) {
        this.styleOdd = styleOdd;
    }

    public String getStyleEven() {
        return styleEven;
    }

    public void setStyleEven(String styleEven) {
        this.styleEven = styleEven;
    }

    public int getOddEvenCountLine() {
        return oddEvenCountLine;
    }

    public void setOddEvenCountLine(int oddEvenCountLine) {
        this.oddEvenCountLine = oddEvenCountLine;
    }

    /* Methods */
    /**
     * <p>Gets the state of the instance as a Serializable Object.</p>
     * <p>If the class that implements this interface has references to instances that implement StateHolder
     * (such as a UIComponent with event handlers, validators, etc.) this method must call the StateHolder.</p>
     * <p>saveState(javax.faces.context.FacesContext) method on all those instances as well.</p>
     * <p>This method must not save the state of children and facets. That is done via the StateManager</p>
     * @param context The FacesContext for the current request 
     * @return a Serializable Object
     */
    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[19];
        values[0] = super.saveState(context);
        values[1] = getBorder();
        values[2] = isCollapsible();
        values[3] = isFrame();
        values[4] = isHeader();
        values[5] = getHeight();
        values[6] = isRowId();
        values[7] = isShowLines();
        values[8] = getTitle();
        values[9] = isInit();
        values[10] = getView();
        values[11] = isShowRoot();
        values[12] = isEnableDragDrop();
        values[13] = getStyleLeaf();
        values[14] = getStyleNode();
        values[15] = isLoadAll();
        values[16] = getStyleOdd();
        values[17] = getStyleEven();
        values[18] = getOddEvenCountLine();
        return values;
    }

    /**
     * <p>Perform any processing required to restore the state from the entries in the state Object.</p>
     * <p>If the class that implements this interface has references to instances that also implement StateHolder 
     * (such as a UIComponent with event handlers, validators, etc.) this method must call the StateHolder.</p>
     * <p>restoreState(javax.faces.context.FacesContext, java.lang.Object) method on all those instances as well.</p>
     * @param context The FacesContext for the current request 
     * @param state the state Object
     */
    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setBorder((String) values[1]);
        setCollapsible((boolean) (Boolean) values[2]);
        setFrame((boolean) (Boolean) values[3]);
        setHeader((boolean) (Boolean) values[4]);
        setHeight((String) values[5]);
        setRowId((boolean) (Boolean) values[6]);
        setShowLines((boolean) (Boolean) values[7]);
        setTitle((String) values[8]);
        setInit((Boolean) values[9]);
        setView((TreeTableModel) values[10]);
        setShowRoot((Boolean) values[11]);
        setEnableDragDrop((Boolean) values[12]);
        setStyleLeaf((String) values[13]);
        setStyleNode((String) values[14]);
        setLoadAll((Boolean) values[15]);
        setStyleOdd((String) values[16]);
        setStyleEven((String) values[17]);
        setOddEvenCountLine((Integer) values[18]);
    }

    /**
     * <p>Delegate to the renderer</p>
     * @param context The FacesContext for the current request 
     * @param component 
     */
    @Override
    public void handleAjaxRequest(FacesContext context) {
        AjaxRendererInterface renderer = (AjaxRendererInterface) this.getRenderer(context);
        renderer.handleAjaxRequest(context, this);
    }

    /**
     * UIAbstractTreePanel class implements the Cloneable interface to indicate to the Object.clone() method that it is legal 
     * for that method to make a field-for-field copy of instances of that class. 
     * @return a clone of this component
     */
    public UITreePanelBase getInstance() {
        try {
            return (UITreePanelBase) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(UITreePanelBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /* Abstracts methods*/
    /**
     * <p>Return the identifier of the component family to which this component belongs.</p>
     * <p>This identifier, in conjunction with the value of the rendererType property, may be used to select the 
     * appropriate Renderer for this component instance.</p>
     * @return the identifier of the component family as a String
     */
    @Override
    public abstract String getFamily();

    /**
     * @return the Renderer type for this UIComponent  (if any)
     */
    @Override
    public abstract String getRendererType();
}
