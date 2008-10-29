/*
 *    Mapfaces - 
 *    http://www.mapfaces.org
 *
 *    (C) 2007 - 2008, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General 
 *    License as published by the Free Software Foundation; either
 *    version 3 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General  License for more details.
 */

package org.mapfaces.models;

import java.io.ObjectStreamException;
import org.geotools.data.ows.WMSCapabilities;

/**
 *
 * @author Olivier Terral.
 */
public class DefaultServer implements Server {

    private static final long serialVersionUID = 7526471155622776147L;
    static private DefaultServer singleton = null;
    private String href;
    private String service;
    private String version;
    private transient WMSCapabilities gtCapabilities;
    private String title = null;

    /**
     * Empty constructor used for Serialization.
     */
    public DefaultServer() {
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setGTCapabilities(WMSCapabilities capabilities) {
        this.gtCapabilities = capabilities;
    }

    public WMSCapabilities getGTCapabilities() {
        return gtCapabilities;
    }
//    public String getCpabilities() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public void setGetCapabilities(String caps) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
    Object writeReplace() throws ObjectStreamException {
        WMSCapabilities gtcapa = getGTCapabilities();
        DefaultServer tmp = getSingleton();
        tmp.setGTCapabilities(gtcapa);
        DefaultServer ds = this;

        return ds;
    }

    Object readResolve() throws ObjectStreamException {
        DefaultServer ds = this;
        WMSCapabilities gtcapa = getSingleton().getGTCapabilities();
        ds.setGTCapabilities(gtcapa);

        return ds;
    }

    static public synchronized DefaultServer getSingleton() {
        if (singleton == null) {
            singleton = new DefaultServer();
        }
        return singleton;
    }

    public String getTitle() {
       return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
