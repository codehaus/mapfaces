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

package org.mapfaces.component;

import javax.faces.context.FacesContext;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class UIMapSize extends UIWidgetBase{
    
    public static final String FAMILIY = "org.mapfaces.MapSize";
    
    /**
     * Add extra parameter like this
     * 
     */
    private String title = null;
    private String itemsLabels = "300*150/300*300/600*300";
    private String itemsValues = "300,150/300,300/600,300";
    
    public UIMapSize() {
        super();
        if(isDebug())
            System.out.println("[UIMapSize] constructor----------------------");
        setRendererType("org.mapfaces.renderkit.html.MapSize"); // this component has a renderer
    }
    
    @Override
    public String getFamily() {
        return FAMILIY;
    }
    
    @Override
     public Object saveState(FacesContext context) {
        Object values[] = new Object[4];
        values[0] = super.saveState(context); 
        values[1] = title; 
        values[2] = itemsLabels; 
        values[3] = itemsValues; 
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]); 
        title =  (String) values[1];
        itemsLabels =  (String) values[2];
        itemsValues =  (String) values[3];
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemsLabels() {
        return itemsLabels;
    }

    public void setItemsLabels(String itemsLabels) {
        this.itemsLabels = itemsLabels;
    }

    public String getItemsValues() {
        return itemsValues;
    }

    public void setItemsValues(String itemsValues) {
        this.itemsValues = itemsValues;
    }
    
}
