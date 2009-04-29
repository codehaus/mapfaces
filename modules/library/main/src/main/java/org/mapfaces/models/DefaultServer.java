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
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.util.SoftValueHashMap;
import org.mapfaces.util.FacesUtils;

/**
 *
 * @author Olivier Terral.
 */
public class DefaultServer implements Server {
    
    private static final Map<String, WMSCapabilities> cache = new SoftValueHashMap<String, WMSCapabilities>(50);
    private static final AtomicInteger incr = new AtomicInteger();
    private final String getcapaId = FacesUtils.getCurrentSessionId()+"-"+incr.incrementAndGet();

    private static final long serialVersionUID = 7526471155622776147L;
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
    
    public static Map<String, WMSCapabilities> getCache() {
        return cache;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getHref() {
        return this.href;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setHref(final String href) {
        this.href = href;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getService() {
        return this.service;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setService(final String service) {
        this.service = service;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getVersion() {
        return this.version;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setGTCapabilities(final WMSCapabilities capabilities) {
        this.gtCapabilities = capabilities;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public WMSCapabilities getGTCapabilities() {
        return gtCapabilities;
    }

    Object writeReplace() throws ObjectStreamException {
        WMSCapabilities gtcapa = getGTCapabilities();
        if (gtcapa != null) {
            getCache().put(getcapaId, gtcapa);
        }
        return this;
    }

    Object readResolve() throws ObjectStreamException {
        WMSCapabilities gtcapa = getCache().get(getcapaId);
        
        //@TODO  The cache must be cleared after the session timeout.
        //cache.remove(getcapaId);
        
        setGTCapabilities(gtcapa);
        
        return this;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public String getTitle() {
       return title;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setTitle(final String title) {
        this.title = title;
    }
    
    /**
     * This method clear the hashMap cache of all DefaultServer object, it is called when the session is closed.
     */
    public static void restoreCache() {
        getCache().clear();
    }
}
