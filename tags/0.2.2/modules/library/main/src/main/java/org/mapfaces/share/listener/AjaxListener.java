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

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

import org.mapfaces.share.interfaces.A4JInterface;
import org.mapfaces.share.interfaces.AjaxInterface;
import org.mapfaces.util.AjaxUtils;
import org.mapfaces.util.FacesUtils;

/**
 * A simple phase listener to filter Ajax requests.
 * 
 * @author Kevin Delfour
 */
public class AjaxListener implements PhaseListener {

    private static final long serialVersionUID = -4395863677889457550L;
    private static final Logger LOGGER = Logger.getLogger(AjaxListener.class.getName());

    /**
     * Handling the any potential Ajax component requests after the Restore View phase makes the restored view
     * available to us.  Therefore, we can get the component (which made the request) from the view,
     * and let it respond to the request. 
     * @param event 
     */
    @Override
    public void afterPhase(final PhaseEvent event) {
//        FacesContext context = event.getFacesContext();
        final FacesContext context       = FacesContext.getCurrentInstance();
        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        final String a4jrequest          = request.getParameter("AJAXREQUEST");
        final String ajaxParam           = request.getParameter(AjaxUtils.AJAX_REQUEST_PARAM_KEY);
        final String ajaxRenderChild     = request.getParameter(AjaxUtils.AJAX_RENDERCHILD_ID_KEY);
        
        // Check for the existence of the Ajax param
        if (ajaxParam != null && ajaxParam.equals("true")) {
            context.responseComplete();// Let JSF know to skip the rest of the lifecycle
            final String componentId = request.getParameter(AjaxUtils.AJAX_CONTAINER_ID_KEY);
            if (componentId == null) {
                if (LOGGER.isLoggable(Level.WARNING)) {
                    //LOGGER.warning("[WARNING] [AjaxListener] No client ID found under key : " + componentId);
                } else {
                    //LOGGER.warning("[WARNING] [AjaxListener] No client ID found under key : " + componentId);
                }
            } else {
                handleAjaxRequest(context, componentId);
            }

            //Save the state of the page
            context.getApplication().getStateManager().saveView(context);

        } else if (a4jrequest != null) {
            final Enumeration<String> listParameters = request.getParameterNames();
            while (listParameters.hasMoreElements()) {
                String param = listParameters.nextElement();
                if (param.equals(request.getParameter(param))) {
                    A4JPostRequest(context, param);
                }
            }
        }


    }

    private void handleAjaxRequest(final FacesContext context, final String componentId) {
        final UIViewRoot viewroot = context.getViewRoot();
        UIComponent candidate = FacesUtils.findComponentById(context, viewroot, componentId);

        if(candidate == null){
            candidate = FacesUtils.findComponentByClientId(context, viewroot, componentId);
        }

        if (candidate == null) {
            //throw new NullPointerException("[WARNING] [AjaxListener] No component found under specified client Id : " + componentId);
        }

        if(!(candidate instanceof AjaxInterface)){
            //throw new IllegalArgumentException("[WARNING] [AjaxListener] Component found under Ajax key was not of expected type");
        }

        final AjaxInterface ajaxcomponent = (AjaxInterface) candidate;

        ajaxcomponent.handleAjaxRequest(context);
    }

    private void A4JPostRequest(final FacesContext context, final String componentId) {
        final UIViewRoot viewroot = context.getViewRoot();
        final A4JInterface ajaxcomponent;
        UIComponent AjaxSupport = null;

        AjaxSupport = FacesUtils.findComponentById(context, viewroot, componentId);
        if (AjaxSupport == null) {
            AjaxSupport = FacesUtils.findComponentByClientId(context, viewroot, componentId);
        }
        if (AjaxSupport == null) {
            //LOGGER.warning("[WARNING] [AjaxListener] No component found under specified client Id : " + componentId);
        } else {
            final UIComponent JSFComponent = AjaxSupport.getParent();
            if (JSFComponent == null) {
               // LOGGER.warning("[WARNING] [AjaxListener] No parent  found under specified client Id : " + AjaxSupport.getId());
            } else {
                if (JSFComponent.getParent() instanceof A4JInterface) {
                    ajaxcomponent = (A4JInterface) JSFComponent.getParent();
                    ajaxcomponent.A4JPostRequest(context);
                }
            }
        }
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
