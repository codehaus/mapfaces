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
package org.mapfaces.taglib;

import org.widgetfaces.taglib.autocompletion.AutocompletionTag;

public class AutocompletionAndZoomTag extends AutocompletionTag {

    /* Fields */
    private static final String COMP_TYPE = "org.mapfaces.Autocompletion";
    private static final String RENDERER_TYPE = "org.mapfaces.renderkit.HTMLAutocompletionAndZoom";

    /* Methods*/
    /**
     * @see getComponentType in class BaseElTag
     * @return component type
     */
    @Override
    public String getComponentType() {
        return COMP_TYPE;
    }

    /**
     * @see getComponentType in class BaseElTag
     * @return component type
     */
    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

}

