/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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
package org.mapfaces.demo.web.bean;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBException;
import org.mapfaces.util.XMLMetadataUtilities;
import org.opengis.metadata.Metadata;

public class MetadataBean {

    private Metadata metadata;
    private String id;
    private String url;
    private FacesContext context;
    private static final Logger LOGGER = Logger.getLogger(MetadataBean.class.getName());


    public FacesContext getContext() {
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }

    public MetadataBean()
            throws Exception {
        metadata = null;
        url = null;
        context = FacesContext.getCurrentInstance();
        //url = (String) context.getExternalContext().getRequestParameterMap().get("url");
        url = "http://localhost:8084/mf/data/metadata/sage_thau.xml";
        if (metadata == null && url != null) {
            loadMetadata(url, "ISO19139");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String aId) {
        id = aId;
    }

    public String getUrl() {
        return url;
    }

    public void seturl(String url) {
        this.url = url;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    private void loadMetadata(String url, String type) {
        try {
            setMetadata(XMLMetadataUtilities.readMetadata(new URL(url), type));
        } catch (MalformedURLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        if (metadata == null) {
            LOGGER.log(Level.SEVERE, "The Metadata file is null !!!");
        }
    }

}
