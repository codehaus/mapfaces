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
package org.mapfaces.renderkit.html.abstractTree;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.mapfaces.component.abstractTree.UITreeToolbarBase;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.util.FacesUtils;
import org.mapfaces.util.ReflectionUtils;

/**
 * @author Kevin Delfour.
 */
public abstract class AbstractToolbarRenderer extends Renderer implements AjaxRendererInterface, CustomizeTreeComponentRenderer {

    private static final Logger LOGGER = Logger.getLogger(AbstractToolbarRenderer.class.getName());

    private boolean debug = true;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeBegin(final FacesContext context, final UIComponent component) throws IOException {
        final UITreeToolbarBase toolbar = (UITreeToolbarBase) component;
        final ResponseWriter writer     = context.getResponseWriter();

        if (!component.isRendered()) {
            return;
        }

        if (context == null)    throw new NullPointerException("FacesContext should not be null");
        if (component == null)  throw new NullPointerException("component should not be null");

        //Method to apply before encodeBegin
        final Boolean obj = (Boolean) component.getAttributes().get("debug") ;
        if (obj != null) debug = obj;

        if (debug)
            LOGGER.info("beforeEncodeBegin : " + AbstractToolbarRenderer.class.getName());

        beforeEncodeBegin(context, component);

        if (debug)
            LOGGER.info("encodeBegin : " + AbstractToolbarRenderer.class.getName());

        String styleUser = toolbar.getStyle();
        if(styleUser == null) styleUser = "";

        String styleClass = toolbar.getStyleClass();
        if(styleClass == null) styleClass = "";

        writer.startElement("div", component);
        writer.writeAttribute("style", styleUser, null);
        writer.writeAttribute("class", "x-btn-wrap x-btn " + styleClass, null);

        if (debug)
            LOGGER.info("afterEncodeBegin : " + AbstractToolbarRenderer.class.getName());

        afterEncodeBegin(context, component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        final UITreeToolbarBase toolbar = (UITreeToolbarBase) component;
        boolean tuneTools               = false;
        boolean tuneClassTools          = false;
        String styleTools               = "";
        String styleClassTools          = "";

        if (toolbar.getStyleTools() != null) {
            styleTools = toolbar.getStyleTools();
            tuneTools = true;
        }
        if (toolbar.getStyleClassTools() != null) {
            styleClassTools = toolbar.getStyleClassTools();
            tuneClassTools = true;
        }

        if (component.getChildCount() != 0) {
            for (final UIComponent tmp : component.getChildren()) {

                if (tuneTools) {
                    final Method method = ReflectionUtils.lookupSetter(tmp.getClass(), "style");
                    if(method != null){
                        try {
                            method.invoke(tmp, styleTools);
                        } catch (IllegalAccessException ex) {
                            System.out.println("[WARNING] IllegalAccessException for this method " + method.getName() + " - " + ex);
                        } catch (InvocationTargetException ex) {
                            System.out.println("[WARNING] InvocationTargetException for this method " + method.getName() + " - " + ex);
                        }
                    }

                }

                if (tuneClassTools) {
                    final Method method = ReflectionUtils.lookupSetter(tmp.getClass(), "styleClass");
                    if(method != null){
                        try {
                            method.invoke(tmp, styleClassTools);
                        } catch (IllegalAccessException ex) {
                            System.out.println("[WARNING] IllegalAccessException for this method " + method.getName() + " - " + ex);
                        } catch (InvocationTargetException ex) {
                            System.out.println("[WARNING] InvocationTargetException for this method " + method.getName() + " - " + ex);
                        }
                    }

                }

                FacesUtils.encodeRecursive(context, tmp);

            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component)
            throws IOException {

        if (debug) LOGGER.info("beforeEncodeEnd : " + AbstractToolbarRenderer.class.getName());

        beforeEncodeEnd(context, component);

        final ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");

        if (debug) LOGGER.info("afterEncodeEnd : " + AbstractToolbarRenderer.class.getName());

        afterEncodeEnd(context, component);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void handleAjaxRequest(final FacesContext context, final UIComponent component) {
        return;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decode(final FacesContext context, final UIComponent component) {
        return;
    }
}
