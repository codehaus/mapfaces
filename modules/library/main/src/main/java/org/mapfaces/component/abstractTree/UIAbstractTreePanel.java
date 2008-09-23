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
public abstract class UIAbstractTreePanel extends UITreeBase implements AjaxInterface, Cloneable {

    private boolean init = false;
    private boolean TREEPANEL_EXPAND_ALL = true;
    private TreeTableModel view;    // =========== ATTRIBUTES ================================================== //
    private String border;
    private boolean check;
    private boolean collapsible;
    private boolean enableDragDrop;
    private boolean frame;
    private boolean header;
    private String height;
    private boolean rowId;
    private boolean showLines;
    private boolean showRoot;
    private String title;

    // =========== ATTRIBUTES ACCESSORS ======================================== //
    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
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

    // =========== FONCTIONS ======================================== //
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

    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[14];
        values[0] = super.saveState(context);
        values[1] = getBorder();
        values[2] = isCheck();
        values[3] = isCollapsible();
        values[4] = isFrame();
        values[5] = isHeader();
        values[6] = getHeight();
        values[7] = isRowId();
        values[8] = isShowLines();
        values[9] = getTitle();
        values[10] = isInit();
        values[11] = getView();
        values[12] = isShowRoot();
        values[13] = isEnableDragDrop();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setBorder((String) values[1]);
        setCheck((boolean) (Boolean) values[2]);
        setCollapsible((boolean) (Boolean) values[3]);
        setFrame((boolean) (Boolean) values[4]);
        setHeader((boolean) (Boolean) values[5]);
        setHeight((String) values[6]);
        setRowId((boolean) (Boolean) values[7]);
        setShowLines((boolean) (Boolean) values[8]);
        setTitle((String) values[9]);
        setInit((Boolean) values[10]);
        setView((TreeTableModel) values[11]);
        setShowRoot((Boolean) values[12]);
        setEnableDragDrop((Boolean) values[13]);
    }

    @Override
    public void handleAjaxRequest(FacesContext context) {
        //Delegate to the renderer
        AjaxRendererInterface renderer = (AjaxRendererInterface) this.getRenderer(context);
        renderer.handleAjaxRequest(context, this);
    }

    public UIAbstractTreeNodeInfo getInstance() {
        try {
            return (UIAbstractTreeNodeInfo) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(UIAbstractTreeNodeInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    // =========== ABSTRACTS METHODS ================================== //
    @Override
    public abstract String getFamily();

    @Override
    public abstract String getRendererType();
}
