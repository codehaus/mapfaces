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

package org.mapfaces.share.listener;

import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.mapfaces.models.DefaultServer;

/**
 * this Listener must be declared in the web.xml file like this :
 * <listener>
 * 	<listener-class>org.mapfaces.share.listener.SessionListener</listener-class>
 * </listener>
 * @author Mehdi Sidhoum
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        //System.out.println(" Current Session created: " + event.getSession().getId() + "  at " + new Date());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        // get the destroying session…
        HttpSession session = event.getSession();
        System.out.println(" Current Session destroyed : " + session.getId() + " at "+ new Date());

        //destroy the serialization hashMap in the DefaultServer objetcs.
        DefaultServer.restoreCache();
    }
} 

