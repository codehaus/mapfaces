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

import java.util.Date;
import java.util.logging.Logger;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseListener;
import javax.faces.event.PhaseId;

public class PhaseTracker implements PhaseListener {

    private static final Logger logger = Logger.getLogger("org.mapfaces.share.listener.PhaseTracker");
    private Date dateBegin;
    private Date dateEnd;
    private boolean flag = false;

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    public void beforePhase(PhaseEvent e) {
        if (e.getPhaseId() == PhaseId.RESTORE_VIEW && ! flag) {
            flag = true;
            dateBegin = new Date();
        }
    }

    public void afterPhase(PhaseEvent e) {
        if (e.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            dateEnd = new Date();
            long time = dateEnd.getTime() - dateBegin.getTime();
            logger.info("END OF " + e.getPhaseId() + " timeout =  " + time + "ms");
            
            flag = false;
        }
    }
}