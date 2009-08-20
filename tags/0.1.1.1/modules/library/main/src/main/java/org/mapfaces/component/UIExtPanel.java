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

import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mehdi Sidhoum.
 */
public class UIExtPanel extends UIPanel {

    public static final String FAMILIY = "org.mapfaces.ExtPanel";
    /**
     * The style class of the overall div that surrounds this component.
     */
    private String styleClass;
    /**
     * The style of the overall div that surrounds this component.
     */
    private String style;
    /**
     * Option to see debug messages.
     */
    private boolean debug;
    private String title;
    private String headerStyle;
    private String headerStyleClass;
    private String bodyStyle;
    private String bodyStyleClass;
    
    private int width;
    private int height;

    public UIExtPanel() {
        super();
        setRendererType("org.mapfaces.renderkit.html.ExtPanel");    // this component has a renderer
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILIY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[12];
        values[0] = super.saveState(context);
        
        values[1] = style;
        values[2] = styleClass;
        values[3] = debug;
        values[4] = title;
        values[5] = headerStyle;
        values[6] = headerStyleClass;
        values[7] = bodyStyle;
        values[8] = bodyStyleClass;
        values[9] = width;
        values[10] = height;

        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        
        style = (String) values[1];
        styleClass = (String) values[2];
        debug = (Boolean) values[3];
        title = (String) values[4];
        headerStyle = (String) values[5];
        headerStyleClass = (String) values[6];
        bodyStyle = (String) values[7];
        bodyStyleClass = (String) values[8];
        width = (Integer) values[9];
        height = (Integer) values[10];

    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeaderStyle() {
        return headerStyle;
    }

    public void setHeaderStyle(String headerStyle) {
        this.headerStyle = headerStyle;
    }

    public String getHeaderStyleClass() {
        return headerStyleClass;
    }

    public void setHeaderStyleClass(String headerStyleClass) {
        this.headerStyleClass = headerStyleClass;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public String getBodyStyleClass() {
        return bodyStyleClass;
    }

    public void setBodyStyleClass(String bodyStyleClass) {
        this.bodyStyleClass = bodyStyleClass;
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
}
