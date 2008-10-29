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
 *
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
    private static UITreePanelBase getForm(UIComponent component) {
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

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        UITreeToolbarBase toolbar = (UITreeToolbarBase) component;
        ResponseWriter writer = context.getResponseWriter();
        String styleUser = "",
                styleClass = "";
        if (!component.isRendered()) {
            return;
        }

        assertValid(context, component);

        //Method to apply before encodeBegin
        if (component.getAttributes().get("debug") != null) {
            debug = (Boolean) component.getAttributes().get("debug");
        }

        if (debug) {
            log.info("beforeEncodeBegin : " + AbstractToolbarRenderer.class.getName());
        }
        beforeEncodeBegin(context, component);

        //Start encodeBegin
        if (debug) {
            log.info("encodeBegin : " + AbstractToolbarRenderer.class.getName());
        }


        if (toolbar.getStyle() != null) {
            styleUser = toolbar.getStyle();
        }
        if (toolbar.getStyleClass() != null) {
            styleClass = toolbar.getStyleClass();
        }

        writer.startElement("div", component);
        writer.writeAttribute("style", styleUser, null);
        writer.writeAttribute("class", "x-btn-wrap x-btn " + styleClass, null);

        //Method to apply before encodeBegin
        if (debug) {
            log.info("afterEncodeBegin : " + AbstractToolbarRenderer.class.getName());
        }
        afterEncodeBegin(context, component);
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        UITreeToolbarBase toolbar = (UITreeToolbarBase) component;
        ResponseWriter writer = context.getResponseWriter();
        boolean tuneTools = false,
                tuneClassTools = false;

        String styleTools = "",
                styleClassTools = "";

        if (toolbar.getStyleTools() != null) {
            styleTools = toolbar.getStyleTools();
            tuneTools = true;
        }
        if (toolbar.getStyleClassTools() != null) {
            styleClassTools = toolbar.getStyleClassTools();
            tuneClassTools = true;
        }

        if (component.getChildCount() != 0) {
            List<UIComponent> children = component.getChildren();
            for (UIComponent tmp : children) {
                if (tuneTools || tuneClassTools) {
                    Class tmpClass = tmp.getClass();
                    Method method = null;
                    if (tuneTools) {

                        method = getSetterMethod(tmp, "style");
                        try {
                            if (method != null) {
                                method.invoke(tmp, styleTools);
                            }
                        } catch (IllegalAccessException ex) {
                            System.out.println("[WARNING] IllegalAccessException for this method " + method.getName() + " - " + ex);
                        } catch (InvocationTargetException ex) {
                            System.out.println("[WARNING] InvocationTargetException for this method " + method.getName() + " - " + ex);
                        }

                    }

                    method = null;
                    if (tuneClassTools) {
                        method = getSetterMethod(tmp, "styleClass");
                        try {
                            if (method != null) {
                                method.invoke(tmp, styleClassTools);
                            }
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

    @Override
    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException {

        if (debug) {
            log.info("beforeEncodeEnd : " + AbstractToolbarRenderer.class.getName());
        }
        beforeEncodeEnd(context, component);

        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");

        if (debug) {
            log.info("afterEncodeEnd : " + AbstractToolbarRenderer.class.getName());
        }
        afterEncodeEnd(context, component);
    }

    private void assertValid(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("FacesContext should not be null");
        } else if (component == null) {
            throw new NullPointerException("component should not be null");
        }
    }

    public Method getSetterMethod(Object base, Object property) {
        // Fisrt capitalize PropName
        String propName = StringUtils.capitalize(property.toString());
        Class Classe = base.getClass();
        // Search in base class methods the getter correspond to the attribut
        for (Method method : Classe.getMethods()) {
            if (method.getName().equals("set" + propName)) {
                return method;
            }
        }
        return null;
    }

    @Override
    public void handleAjaxRequest(FacesContext context, UIComponent component) {
        return;
    }

    @Override
    public void decode(FacesContext context, UIComponent component) {
        return;
    }
}
