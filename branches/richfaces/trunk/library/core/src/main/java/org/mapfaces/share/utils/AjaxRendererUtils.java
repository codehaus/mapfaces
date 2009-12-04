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
import org.ajax4jsf.Messages;
import org.ajax4jsf.component.AjaxSupport;
import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.javascript.ScriptUtils;

/**
 * This class should be extend AjaxRendererUtils class from A4J project but constructior is in private access
 * Build javascript to make A4J request
 * @author Olivier Terral (Geomatys)
 */
public class AjaxRendererUtils {

        private static final Logger LOGGER = Logger.getLogger(AjaxRendererUtils.class.getName());
        /**
	 * Build JavaScript event for component
	 *
	 * @param uiComponent -
	 *            component for build event
	 * @param facesContext
	 * @param eventName -
	 *            name of event
	 * @param omitDefaultActionUrl - default action URL is not encoded if parameter is true
	 *
	 * @return <code>StringBuffer</code> with Javascript code
	 */
	public static StringBuffer buildOnEvent(UIComponent uiComponent,
			FacesContext facesContext, String eventName, boolean omitDefaultActionUrl) {
		StringBuffer onEvent = new StringBuffer();
		if (null != eventName) {
			String commandOnEvent = (String) uiComponent.getAttributes().get(
					eventName);
			if (commandOnEvent != null) {
				onEvent.append(commandOnEvent);
				onEvent.append(';');
			}
		}
		JSFunction ajaxFunction = org.ajax4jsf.renderkit.AjaxRendererUtils.buildAjaxFunction(uiComponent, facesContext);
		// Create formal parameter for non-input elements ???
		// Link Control pseudo-object
		// Options map. Possible options for function call :
		// control - name of form control for submit.
		// name - name for link control \
		// value - value of control. - possible replace by parameters ?
		// single true/false - submit all form or only one control.
		// affected - array of element's ID for update on responce.
		// oncomplete - function for call after complete request.
		// status - id of request status component.
		// parameters - map of parameters name/value for append on request.
		// ..........
		ajaxFunction.addParameter(org.ajax4jsf.renderkit.AjaxRendererUtils.buildEventOptions(facesContext, uiComponent, omitDefaultActionUrl));

		// appendAjaxSubmitParameters(facesContext, uiComponent, onEvent);
		ajaxFunction.appendScript(onEvent);
                
		if (uiComponent instanceof AjaxSupport) {
			AjaxSupport support = (AjaxSupport) uiComponent;
			if (support.isDisableDefault()) {
				onEvent.append("; return false;");
			}
		}
		LOGGER.log(Level.INFO, Messages.getMessage(Messages.BUILD_ONCLICK_INFO, uiComponent
				.getId(), onEvent.toString()));

		return onEvent;

	}
        public static StringBuffer buildDefaultOptions(UIComponent uiComponent,
			FacesContext facesContext) {
            
                boolean omitDefaultActionUrl = false;

                List<Object> parameters = new ArrayList<Object>();
                parameters.add(org.ajax4jsf.renderkit.AjaxRendererUtils.buildEventOptions(facesContext, uiComponent, omitDefaultActionUrl));
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
		LOGGER.log(Level.INFO, Messages.getMessage(Messages.BUILD_ONCLICK_INFO, uiComponent
				.getId(), onEvent.toString()));

		return onEvent;

	}
}
