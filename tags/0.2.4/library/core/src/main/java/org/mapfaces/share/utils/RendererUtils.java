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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;

/**
 * This class extends the RendererUtils from A4J project
 * @author Olivier Terral (Geomatys)
 */
public class RendererUtils extends org.ajax4jsf.framework.renderer.RendererUtils {

    // Log instance for this class
    protected static final Logger logger = FacesLogger.RENDERKIT.getLogger();
    
    public static interface HTML extends org.ajax4jsf.framework.renderer.RendererUtils.HTML {

        public static final String IMG_ELEM = "img";
        public static final String src_ATTRIBUTE = "src";
        public static final String TEXTJAVASCRIPT_VALUE = "text/javascript";
    }

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
}
