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

package org.mapfaces.util;

import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBException;
import net.opengis.context.v110.ViewContextType;
import org.mapfaces.models.Context;

/**
 * @author olivier Terral
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
