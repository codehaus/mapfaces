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

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Kevin Delfour
 */
public class LogPhaseListener implements javax.faces.event.PhaseListener {

    private static final long serialVersionUID = -4395863677889457550L;
    private static final transient Log log = LogFactory.getLog(LogPhaseListener.class);

    /**
     * 
     * @return  Identifier that indicates an interest in events, no matter which request
     * processing phase is being performed.
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    /**
     * 
     * @param e - represents the beginning or ending of processing for a particular phase of the request 
     * processing lifecycle, for the request encapsulated by the specified FacesContext.
     */
    @Override
    public void beforePhase(PhaseEvent e) {
        log.debug("beforePhase." + e.getPhaseId());
    }

    /**
     * 
     * @param e - represents the beginning or ending of processing for a particular phase of the request 
     * processing lifecycle, for the request encapsulated by the specified FacesContext.
     */
    @Override
    public void afterPhase(PhaseEvent e) {
        log.debug("afterPhase." + e.getPhaseId());
    }
}
