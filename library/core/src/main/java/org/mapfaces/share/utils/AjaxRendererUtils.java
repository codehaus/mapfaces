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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.ajax4jsf.framework.util.message.Messages;
import org.ajax4jsf.framework.util.javascript.ScriptUtils;
import org.mapfaces.component.UIWidgetBase;

/**
 * This class should be extend AjaxRendererUtils class from A4J project but 
 * constructior is in private access.
 * Build javascript to make A4J request.
 *
 * @author Olivier Terral (Geomatys)
 * @since 0.3
 */
public class AjaxRendererUtils {

        private static final Logger LOGGER = Logger.getLogger(AjaxRendererUtils.class.getName());

        public static StringBuffer buildDefaultOptions(UIComponent uiComponent,
			FacesContext facesContext) {

                //TODO omitDefaultActionUrl is a parameter very useful when we are in a Portlet environnment
                //it's used in buildEventOptions function but the current Ajax4jsf library we used doesn't have the same function declaration.
                //See RichFaces branch AjaxRendererUtils class to see what I mean
                boolean omitDefaultActionUrl = false;

                List<Object> parameters = new ArrayList<Object>();
                parameters.add(org.ajax4jsf.framework.renderer.AjaxRendererUtils.buildEventOptions(facesContext, uiComponent));
                boolean first = true;
		StringBuffer onEvent = new StringBuffer();
                
		if (null != parameters) {

			for (Iterator<?> param = parameters.iterator(); param.hasNext();) {
				Object element = param.next();

				if (!first) {
					onEvent.append(',');
				}

				if (null != element) {
					onEvent.append(ScriptUtils.toScript(element));
                                        
				} else {
					onEvent.append("null");
				}
				first = false;
			}

		}
            if (uiComponent instanceof UIWidgetBase && ((UIWidgetBase) uiComponent).isDebug())
                LOGGER.log(Level.INFO, Messages.getMessage(Messages.BUILD_ONCLICK_INFO,
                        uiComponent.getId(), onEvent.toString()));

		return onEvent;

	}
}
