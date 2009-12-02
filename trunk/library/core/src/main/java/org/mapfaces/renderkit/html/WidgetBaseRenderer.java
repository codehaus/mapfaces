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

package org.mapfaces.renderkit.html;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.share.utils.FacesUtils;

/**
 * @author Olivier Terral (Geomatys).
 * @author Mehdi Sidhoum (Geomatys).
 *
 * @since 0.1
 */

public class WidgetBaseRenderer extends Renderer {

    protected ResponseWriter writer = null;
    public boolean debug = false;
    private String clientId;
    private String style = null;
    private String styleClass = null;
    private static final Logger LOGGER = Logger.getLogger(WidgetBaseRenderer.class.getName());

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        // suppress rendering if "rendered" property on the component is false.
        if (!component.isRendered()) {
            return;
        }
        assertValid(context, component);
        this.writer = context.getResponseWriter();
        this.clientId = component.getClientId(context);
        final UIWidgetBase comp = (UIWidgetBase) component;
        if (comp.isDebug()) {
            this.debug = true;
            LOGGER.log(Level.INFO, "[DEBUG] ENCODE BEGIN "+comp.isDebug());
        } else {
             this.debug = false;
        }
      
        if (FacesUtils.getParentUIModelBase(context, component) == null) {
            throw new NullPointerException("UIModelBase should not be null, you should declare an UIModelBase component as the mapfaces component  wrapper.");
        } else if (comp.getModel() == null) {
            comp.setModel(FacesUtils.getParentUIModelBase(context, component).getModel());
        }

        setStyle((String) comp.getAttributes().get("style"));

        if (getStyleClass() == null) {
            setStyleClass((String) comp.getAttributes().get("styleClass"));
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] ENCODE CHILDREN ");
        }
        final List<UIComponent> childrens = component.getChildren();
        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] THe component " + component.getFamily() + " has " + childrens.size() + " children :");
        }
        for (final UIComponent tmp : childrens) {
            if (this.debug) {
                LOGGER.log(Level.INFO, "[DEBUG]  Child family's " + tmp.getFamily());
            }
            FacesUtils.encodeRecursive(context, tmp);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component) throws IOException {
        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] ENCODE END ");
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        if (this.debug) {
            LOGGER.log(Level.INFO, "[DEBUG] DECODE PROCESS");
        }
    }
    /**
     * Ensure that the FacesContext and UIComponent instances are not null, otherwise NullPointerException is throwed.
     * @param context
     * @param component
     */
    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null) {
            throw new NullPointerException("context should not be null");
        }
        if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }
    
    public ResponseWriter getWriter() {
        return writer;
    }

    public void setWriter(final ResponseWriter writer) {
        this.writer = writer;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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
}
