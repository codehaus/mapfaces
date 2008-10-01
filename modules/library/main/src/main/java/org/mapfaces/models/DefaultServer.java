/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.models;

import org.geotools.data.ows.WMSCapabilities;

/**
 *
 * @author olivier
 */
public class DefaultServer implements Server{
    
    private String href;
    private String service;
    private String version;
    private WMSCapabilities gtCapabilities;

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

}
