/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.share.listener;

import java.beans.Introspector;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Mehdi Sidhoum
 */
public class CleanupListener implements ServletContextListener { 
  public void contextInitialized(ServletContextEvent event) {
  } 
  public void contextDestroyed(ServletContextEvent event) {
    try { 
      Introspector.flushCaches();
      for (Enumeration e = DriverManager.getDrivers(); e.hasMoreElements();) {
        Driver driver = (Driver) e.nextElement(); 
        if (driver.getClass().getClassLoader() == getClass().getClassLoader()) {
          DriverManager.deregisterDriver(driver);          
        } 
      } 
    } catch (Throwable e) { 
      System.err.println("Failled to cleanup ClassLoader for webapp"); 
      e.printStackTrace(); 
    } 
  }
}