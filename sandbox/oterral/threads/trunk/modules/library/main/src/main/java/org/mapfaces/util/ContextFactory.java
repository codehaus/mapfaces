/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.util;

import org.mapfaces.models.Context;
import org.mapfaces.models.DefaultLayer;

/**
 *
 * @author olivier
 */
public interface ContextFactory {
    
    public Context createDefaultContext();

//    public WMC_v110 createWMC_v110();
    
    public DefaultLayer createLayer();

//    public DefaultServer createServer() {
//        return new DefaultMutableUserLayer();
//    }
}
