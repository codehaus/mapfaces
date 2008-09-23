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
import org.mapfaces.share.interfaces.AjaxInterface;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevindelfour
 */
public abstract class UIAbstractTreeColumn extends UITreeBase implements AjaxInterface, Cloneable {

    // =========== ATTRIBUTES ================================================== //
    private String header;
    private String width;
    private boolean alreadyRender = false;
    private String styleHeader;

    // =========== ATTRIBUTES ACCESSORS ======================================== //
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getStyleHeader() {
        return styleHeader;
    }

    public void setStyleHeader(String styleHeader) {
        this.styleHeader = styleHeader;
    }
    // =========== FONCTIONS ======================================== //
    public boolean isAlreadyRender() {
        return alreadyRender;
    }

    public void setAlreadyRender(boolean alreadyRender) {
        this.alreadyRender = alreadyRender;
    }

    //Override methods
    @Override
    public Object saveState(FacesContext context) {
        Object values[] = new Object[5];
        values[0] = super.saveState(context);
        values[1] = isAlreadyRender();
        values[2] = getHeader();
        values[3] = getWidth();
        values[4] = getStyleHeader();
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        alreadyRender = (Boolean) values[1];
        header = ((String) values[2]);
        width = ((String) values[3]);
        styleHeader = ((String)values[4]);
    }

    @Override
    public void handleAjaxRequest(FacesContext context) {
        //Delegate to the renderer
        AjaxRendererInterface renderer = (AjaxRendererInterface) this.getRenderer(context);
        renderer.handleAjaxRequest(context, this);
    }

    public UIAbstractTreeColumn getInstance() {
        try {
            return (UIAbstractTreeColumn) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(UIAbstractTreeColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    // =========== ABSTRACTS METHODS ================================== //
    @Override
    public abstract String getFamily();

    @Override
    public abstract String getRendererType();
}
