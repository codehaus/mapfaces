/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.util;

import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBException;
import net.opengis.context.v110.ViewContextType;
import org.mapfaces.models.Context;

/**
 *
 * @author olivier
 */
class WMCv110toMFTransformer {

    protected final ContextFactory contextFactory = new DefaultContextFactory();
    
    public Context visit(ViewContextType doc) throws UnsupportedEncodingException, JAXBException {
        Context ctx = contextFactory.createDefaultContext();
        ctx.setType(doc.getClass().toString());
        ctx.setVersion(doc.getVersion());        
        ctx.setId(doc.getId());
        ctx.setTitle(doc.getGeneral().getTitle());
//        BoundingBoxType bbox = doc.getGeneral().getBoundingBox().getValue();
//        ctx.setSrs(bbox.getCrs());
//        System.out.println(bbox.toString());
//        ctx.setBoundingBox(String.valueOf(bbox.getLowerCorner().get(0).doubleValue()),
//                            String.valueOf(bbox.getLowerCorner().get(1).doubleValue()),
//                            String.valueOf(bbox.getUpperCorner().get(0).doubleValue()),
//                            String.valueOf(bbox.getUpperCorner().get(1).doubleValue()));
//        ctx.setWindowSize(doc.getGeneral().getWindow().getWidth().toString(),doc.getGeneral().getWindow().getHeight().toString());
//        List array= visitResourceList(doc.getResourceList().getLayer());
//        ctx.setLayers((List<Layer>) array.get(0));
//        ctx.setWmsServers((HashMap<String, Server>) array.get(1));
        return ctx;
    }

}
