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

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
/**
 *
 * @author Mehdi Sidhoum (Geomatys)
 */
public class UIBubble extends UIComponentBase {
    
    public static final String FAMILIY = "org.mapfaces.component.Bubble";
    
    private int width;
    private int height;
    private boolean topArrow;
    private boolean bottomArrow;
    private String style;
    private String styleClass;
    private boolean spryEffect;
    private boolean loadSpryJs;
    
    @Override
    public String getFamily() {
        return FAMILIY;
    }
    
    public UIBubble() {
        super();
        setRendererType("org.mapfaces.renderkit.HTMLBubble"); // this component has a renderer
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object[] values = new Object[10];
        values[0] = super.saveState(context);
        values[1] = width;
        values[2] = height;
        values[3] = topArrow;
        values[4] = bottomArrow;
        values[5] = style;
        values[6] = styleClass;
        values[7] = spryEffect;
        values[8] = loadSpryJs;

        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object[] values = (Object[]) state;
        super.restoreState(context, values[0]);
        width = (Integer) values[1];
        height = (Integer) values[2];
        topArrow = (Boolean) values[3];
        bottomArrow = (Boolean) values[4];
        style = (String) values[5];
        styleClass = (String) values[6];
        spryEffect = (Boolean) values[7];
        loadSpryJs = (Boolean) values[8];
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isTopArrow() {
        return topArrow;
    }

    public void setTopArrow(boolean topArrow) {
        this.topArrow = topArrow;
    }

    public boolean isBottomArrow() {
        return bottomArrow;
    }

    public void setBottomArrow(boolean bottomArrow) {
        this.bottomArrow = bottomArrow;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public boolean isSpryEffect() {
        return spryEffect;
    }

    public void setSpryEffect(boolean spryEffect) {
        this.spryEffect = spryEffect;
    }

    public boolean isLoadSpryJs() {
        return loadSpryJs;
    }

    public void setLoadSpryJs(boolean loadSpryJs) {
        this.loadSpryJs = loadSpryJs;
    }
}
