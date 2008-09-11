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

package org.mapfaces.share.listener;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.*;

import org.mapfaces.share.interfaces.AjaxInterface;
import org.mapfaces.share.utils.Utils;
import org.mapfaces.util.AjaxUtils;

/**
 *
 * @author kdelfour
 * A simple phase listener to filter Ajax requests.
 */
public class AjaxListener implements PhaseListener {

    private static final long serialVersionUID = -4395863677889457550L;
    private static final transient Log log = LogFactory.getLog(AjaxListener.class);

    /**
     * Handling the any potential Ajax component requests after the Restore View phase makes the restored view
     * available to us.  Therefore, we can get the component (which made the request) from the view,
     * and let it respond to the request. 
     * @param event 
     */
    @Override
    public void afterPhase(PhaseEvent event) {
        AjaxUtils ajaxtools = new AjaxUtils();
//        FacesContext context = event.getFacesContext();
        FacesContext context = FacesContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String ajaxParam = request.getParameter(ajaxtools.getAJAX_REQUEST_PARAM_KEY());
        String ajaxRenderChild = request.getParameter(ajaxtools.getAJAX_RENDERCHILD_ID_KEY());
        // Check for the existence of the Ajax param
        if (ajaxParam != null && ajaxParam.equals("true")) {
            context.responseComplete();// Let JSF know to skip the rest of the lifecycle
            String componentId = request.getParameter(ajaxtools.getAJAX_CONTAINER_ID_KEY());
            log.info("Component ID. " + componentId);
            if (componentId == null) {
                if (log.isWarnEnabled()) {
                    log.warn("No client ID found under key : " + componentId);
                }
            } else {
                handleAjaxRequest(context, componentId);
            }

            //Save the state of the page
            context.getApplication().getStateManager().saveView(context);

        }
    }

    /**
     * 
     * @param context
     * @param componentId
     */
    private void handleAjaxRequest(FacesContext context, String componentId) {
        UIViewRoot viewroot = context.getViewRoot();
        AjaxInterface ajaxcomponent = null;
        System.out.println(componentId);
        try {
            ajaxcomponent = (AjaxInterface) Utils.findComponentById(context, viewroot, componentId);
        } catch (ClassCastException cce) {
            throw new IllegalArgumentException("Component foud under Ajax key was not of expected type");
        }

        if (ajaxcomponent == null) {
            throw new NullPointerException("No component found under specified client Id; " + componentId);
        }
        ajaxcomponent.handleAjaxRequest(context);

    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
