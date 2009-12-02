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

package org.mapfaces.share.utils;

import com.sun.faces.util.FacesLogger;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;

/**
 * This class extends the RendererUtils from A4J project
 * @author Olivier Terral (Geomatys)
 * @author Mehdi Sidhoum (Geomatys)
 */
public class RendererUtils extends org.ajax4jsf.framework.renderer.RendererUtils {

    // Log instance for this class
    protected static final Logger logger = FacesLogger.RENDERKIT.getLogger();

    public static interface HTML extends org.ajax4jsf.framework.renderer.RendererUtils.HTML {

        public static final String IMG_ELEM = "img";
        public static final String SRC_ATTRIBUTE = "src";
        public static final String TEXTJAVASCRIPT_VALUE = "text/javascript";
        public static final String INPUT_TYPE_BUTTON = "button";
        public static final String BR_ELEM = "br";
        public static final String DATAFLD_ATTR = "datafld";
        public static final String DATASRC_ATTR = "datasrc";
        public static final String DATAFORMATAS_ATTR = "dataformatas";
        public static final String LABEL_ATTR = "label";
        public static final String OPTION_ELEM = "option";
        public static final String SELECTED_ATTR = "selected";
        public static final String TABLE_ELEMENT = "table";
        public static final String SELECT_ELEMENT = "select";
        public static final String MULTIPLE_ATTRIBUTE = "multiple";
    }
    /**
     * This object is used to compare a submitted value with an object that can not be a string
     */
    public static final Object NOTHING = new Serializable() {

        public boolean equals(final Object o) {
            if (o != null) {
                if (o.getClass().equals(this.getClass())) {
                    return true;
                }
            }
            return false;
        }
    };

    /**
     *
     * @param component
     * @return
     */
    public static boolean shouldEncode(UIComponent component) {

        // suppress rendering if "rendered" property on the component is
        // false.
        if (!component.isRendered()) {
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE,
                        "End encoding component {0} since rendered attribute is set to false",
                        component.getId());
            }
            return false;
        }
        return true;

    }

    /**
     * 
     * @param component
     * @return
     */
    public static boolean shouldEncodeChildren(UIComponent component) {

        // suppress rendering if "rendered" property on the component is
        // false.
        if (!component.isRendered()) {
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE,
                        "Children of component {0} will not be encoded since this component's rendered attribute is false",
                        component.getId());
            }
            return false;
        }
        return true;

    }

    /**
     * Returns the path of a component from the view root
     * used for debugging purpose
     * @param component
     * @return
     */
    public static String getPathToComponent(UIComponent component) {
        StringBuffer buf = new StringBuffer();

        if (component == null) {
            buf.append("{Component-Path : ");
            buf.append("[null]}");
            return buf.toString();
        }

        getPathToComponent(component, buf);

        buf.insert(0, "{Component-Path : ");
        buf.append("}");

        return buf.toString();
    }

    private static void getPathToComponent(UIComponent component, StringBuffer buf) {
        if (component == null) {
            return;
        }

        StringBuffer intBuf = new StringBuffer();

        intBuf.append("[Class: ");
        intBuf.append(component.getClass().getName());
        if (component instanceof UIViewRoot) {
            intBuf.append(",ViewId: ");
            intBuf.append(((UIViewRoot) component).getViewId());
        } else {
            intBuf.append(",Id: ");
            intBuf.append(component.getId());
        }
        intBuf.append("]");

        buf.insert(0, intBuf.toString());

        getPathToComponent(component.getParent(), buf);
    }
}
