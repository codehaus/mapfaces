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
package org.widgetfaces.share.listener;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.*;
import org.widgetfaces.share.interfaces.AjaxInterface;
import org.widgetfaces.share.utils.AjaxUtils;
import org.widgetfaces.share.utils.Utils;

/**
 * A simple phase listener to filter Ajax requests.
 * 
 * @author Kevin Delfour
 */
public class AjaxListener implements PhaseListener {

    private static final transient Log log = LogFactory.getLog(AjaxListener.class);
    
    /**
     * Handling the any potential Ajax component requests after the Restore View phase makes the restored view
     * available to us.  Therefore, we can get the component (which made the request) from the view,
     * and let it respond to the request. 
     * @param event 
     */
    @Override
    public void afterPhase(final PhaseEvent event) {
//        FacesContext context = event.getFacesContext();
        final FacesContext context = FacesContext.getCurrentInstance();
        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        final String ajaxParam = request.getParameter(AjaxUtils.AJAX_REQUEST_PARAM_KEY);
        final String ajaxRenderChild = request.getParameter(AjaxUtils.AJAX_RENDERCHILD_ID_KEY);

        // Check for the existence of the Ajax param
        if (ajaxParam != null && ajaxParam.equals("true")) {
            context.responseComplete();// Let JSF know to skip the rest of the lifecycle
            final String componentId = request.getParameter(AjaxUtils.AJAX_CONTAINER_ID_KEY);
            if (componentId == null) {
                if (log.isWarnEnabled()) {
                    log.warn("No client ID found under key : " + componentId);
                } else {
                    System.out.println("[WARN] No client ID found under key : " + componentId);
                }
            } else {
                handleAjaxRequest(context, componentId);
            }

            //Save the state of the page
            context.getApplication().getStateManager().saveView(context);

        }
    }

    private void handleAjaxRequest(final FacesContext context, final String componentId) {

        final UIViewRoot viewroot = context.getViewRoot();
        AjaxInterface ajaxcomponent = null;

        try {
            ajaxcomponent = (AjaxInterface) Utils.findComponentById(context, viewroot, componentId);
            if (ajaxcomponent == null) {
                ajaxcomponent = (AjaxInterface) Utils.findComponent(context, componentId);
            }
        } catch (ClassCastException cce) {
            throw new IllegalArgumentException("Component found under Ajax key was not of expected type");
        }

        if (ajaxcomponent == null) {
            throw new NullPointerException("No component found under specified client Id : " + componentId);
        }
        ajaxcomponent.handleAjaxRequest(context);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void beforePhase(PhaseEvent event) {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
