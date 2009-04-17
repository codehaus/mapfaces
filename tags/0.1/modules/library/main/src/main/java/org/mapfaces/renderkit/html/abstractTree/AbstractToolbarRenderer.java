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
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mapfaces.component.abstractTree.UITreePanelBase;
import org.mapfaces.component.abstractTree.UITreeToolbarBase;
import org.mapfaces.share.interfaces.AjaxRendererInterface;
import org.mapfaces.share.interfaces.CustomizeTreeComponentRenderer;
import org.mapfaces.share.utils.Utils;

/**
 * @author Kevin Delfour.
 */
public abstract class AbstractToolbarRenderer extends Renderer implements AjaxRendererInterface, CustomizeTreeComponentRenderer {

    private boolean debug = true;
    private static final transient Log log = LogFactory.getLog(AbstractToolbarRenderer.class);

    /**
     * This method returns the parent form of this element.
     * If this element is a form then it simply returns itself.
     * @param component -
     * @return
     */
    private static UITreePanelBase getForm(final UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null) {
            if (parent instanceof UITreePanelBase) {
                break;
            }
            parent = parent.getParent();
        }
        if (parent == null) {
            throw new IllegalStateException("Not nested inside a tree panel!");
        }
        return (UITreePanelBase) parent;
    }

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
        final String styleUser;
        final String styleClass;

        if (!component.isRendered()) {
            return;
        }

        assertValid(context, component);

        //Method to apply before encodeBegin
        final Boolean obj = (Boolean) component.getAttributes().get("debug") ;
        if (obj != null) debug = obj;

        if (debug) log.info("beforeEncodeBegin : " + AbstractToolbarRenderer.class.getName());

        beforeEncodeBegin(context, component);

        //Start encodeBegin
        if (debug) log.info("encodeBegin : " + AbstractToolbarRenderer.class.getName());

        styleUser  = (toolbar.getStyle() != null)      ? toolbar.getStyle()      : "";
        styleClass = (toolbar.getStyleClass() != null) ? toolbar.getStyleClass() : "";

        writer.startElement("div", component);
        writer.writeAttribute("style", styleUser, null);
        writer.writeAttribute("class", "x-btn-wrap x-btn " + styleClass, null);

        //Method to apply before encodeBegin
        if (debug) log.info("afterEncodeBegin : " + AbstractToolbarRenderer.class.getName());

        afterEncodeBegin(context, component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeChildren(final FacesContext context, final UIComponent component) throws IOException {
        final UITreeToolbarBase toolbar = (UITreeToolbarBase) component;
        final ResponseWriter writer     = context.getResponseWriter();
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
                    final Method method = getSetterMethod(tmp, "style");
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
                    final Method method = getSetterMethod(tmp, "styleClass");
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

                Utils.encodeRecursive(context, tmp);

            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeEnd(final FacesContext context, final UIComponent component)
            throws IOException {

        if (debug) log.info("beforeEncodeEnd : " + AbstractToolbarRenderer.class.getName());

        beforeEncodeEnd(context, component);

        final ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");

        if (debug) log.info("afterEncodeEnd : " + AbstractToolbarRenderer.class.getName());

        afterEncodeEnd(context, component);
    }

    private void assertValid(final FacesContext context, final UIComponent component) {
        if (context == null)    throw new NullPointerException("FacesContext should not be null");
        if (component == null)  throw new NullPointerException("component should not be null");
    }

    public Method getSetterMethod(final Object base, final Object property) {
        // Fisrt capitalize PropName
        final String propName = "Set" + StringUtils.capitalize(property.toString());
        final Class Classe    = base.getClass();
        // Search in base class methods the getter correspond to the attribut
        for (final Method method : Classe.getMethods()) {
            if (method.getName().equals(propName)) {
                return method;
            }
        }
        return null;
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
