/*
 * Copyright 2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mapfaces.share.listener;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;
import org.geotools.display.exception.PortrayalException;
import org.geotools.display.service.DefaultPortrayalService;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.MapContext;
import org.geotools.referencing.CRS;
import org.mapfaces.models.Context;
import java.awt.Dimension;
import java.util.Date;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class MfLayerListener implements PhaseListener {

    private static final Logger LOGGER = Logger.getLogger("org.mapfaces.share.listener.MFLayerListener");

    public void afterPhase(PhaseEvent phaseEvent) {
        FacesContext context = phaseEvent.getFacesContext();
        String compId = (String) context.getExternalContext().getRequestParameterMap().get("mfLayerId");
        String dateFilter = (String) context.getExternalContext().getRequestParameterMap().get("ts");
        Date datevalue;
        if (dateFilter != null) {
            long timeInMs = Long.valueOf(dateFilter);
            datevalue = new Date(timeInMs);
        } else {
            datevalue = new Date();
        }

        if (compId != null) {
            handleMfLayerRequest(phaseEvent, compId, datevalue);
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    private void handleMfLayerRequest(PhaseEvent phaseEvent, String compId, Date datevalue) {
        FacesContext context = phaseEvent.getFacesContext();
        ExternalContext externalContext = context.getExternalContext();
        if (compId != null) {
            try {
                if (externalContext.getResponse() instanceof HttpServletResponse) {
                    writeChartWithServletResponse(context, compId, (HttpServletResponse) externalContext.getResponse(), datevalue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                context.responseComplete();
            }
        }
    }

    public void beforePhase(PhaseEvent phaseEvent) {
    }

    private void writeChartWithServletResponse(FacesContext context, String id, HttpServletResponse response, Date datevalue) throws IOException {
        OutputStream stream = response.getOutputStream();
        writeLayer(context, id, stream, datevalue);
    }

    /**
     * This method portray for every Layers
     * @TODO the synchronized keyword must be removed after a fix in gt-working due to an Exception RecursiveSearchException.
     * @param context
     * @param id
     * @param stream
     * @throws java.io.IOException
     */
    private void writeLayer(FacesContext context, String id, OutputStream stream, Date datevalue) throws IOException {
        Map sessionMap = context.getExternalContext().getSessionMap();
        Context model = (Context) sessionMap.get(id + "_model");
        if (model != null) {
            final String srs = model.getSrs();
            final CoordinateReferenceSystem crs;
            try {
                crs = CRS.decode(srs);
            } catch (FactoryException ex) {
                LOGGER.log(Level.SEVERE, "Invalid SRS definition : " + srs, ex);
                return;
            }
            final Dimension dim = new Dimension(
                    Integer.parseInt(model.getWindowWidth()),
                    Integer.parseInt(model.getWindowHeight()));

            // test if Dimension is not valid, width and height must be > 0.
            if (dim.width <= 0) {
                dim.width = 1;
            }
            if (dim.height <= 0) {
                dim.height = 1;
            }

            final ReferencedEnvelope env = new ReferencedEnvelope(
                    new Double(model.getMinx()), new Double(model.getMaxx()),
                    new Double(model.getMiny()), new Double(model.getMaxy()),
                    crs);

            MapContext mapContext = (MapContext) sessionMap.get(id + "_mapContext");

            if (mapContext != null) {
                try {
//                    System.out.println("[PORTRAYING] mapContext = " + mapContext + "   env = " + env + "   dim = " + dim);
//                    long start = (new Date()).getTime();
                    System.out.println("[MfLayerListener] filter for datevalue = " + datevalue);
                    DefaultPortrayalService.portray(mapContext, env, stream, "image/png", dim, true);
//                    System.out.println("[PORTRAYING] mapContext = " + mapContext + "   env = " + env + "   dim = " + dim);
//                    long end = (new Date()).getTime();
//                    System.out.println("[PORTRAYING END] time : "+(end-start) +" ms");          
                } catch (PortrayalException ex) {
                    Logger.getLogger(MfLayerListener.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception exp) {//catch all other exception to clean the logs because it can be some flood in portraying process.
                    System.out.println("[MfLayerListener] exception : "+exp.getMessage());
                } finally {
                    emptySession(sessionMap, id);
                }
            } else {
                LOGGER.log(Level.WARNING, "No MapContext in session map for the layer\n id : " + id + "\n MapContext : " + mapContext);
            }
        } else {
            LOGGER.log(Level.WARNING, "No Model in session map for the layer\n id: " + id + "\n Model : " + model);
        }
    }

    private void emptySession(Map sessionMap, String id) {
        sessionMap.remove(id);
    }
}