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

import java.beans.Introspector;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * This is a servlet context listener that cleans ClassLoader and intercept the deployment context phases.
 * @author Mehdi Sidhoum (Geomatys).
 */
public class DefaultServletContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(DefaultServletContextListener.class.getName());

    public void contextInitialized(ServletContextEvent event) {
    }

    public void contextDestroyed(ServletContextEvent event) {
        try {
            Introspector.flushCaches();
            for (final Enumeration e = DriverManager.getDrivers(); e.hasMoreElements();) {
                final Driver driver = (Driver) e.nextElement();
                if (driver.getClass().getClassLoader() == getClass().getClassLoader()) {
                    DriverManager.deregisterDriver(driver);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"Failed to cleanup ClassLoader for webapp",e);
        }
    }
}