/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.util;

import org.mapfaces.models.Context;
import org.mapfaces.models.DefaultContext;
import org.mapfaces.models.DefaultLayer;

/**
 *
 * @author olivier
 */
public class DefaultContextFactory implements ContextFactory{
    
    public Context createDefaultContext() {
        return new DefaultContext();
    }

    public DefaultLayer createLayer() {
        return new DefaultLayer();
    }

//    public DefaultServer createServer() {
//        return new DefaultMutableUserLayer();
//    }
    
//    public DefaultDimension createDimension() {
//        return new DefaultMutableUserLayer();
//    }
}
