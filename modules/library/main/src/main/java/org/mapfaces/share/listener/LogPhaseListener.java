package org.mapfaces.share.listener;


import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author kdelfour
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
