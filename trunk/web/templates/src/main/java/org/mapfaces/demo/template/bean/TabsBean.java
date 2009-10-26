
package org.mapfaces.demo.template.bean;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;

/**
 * This is a managed bean for the tabs component to navigate in all pages.
 * @author Mehdi Sidhoum (Geomatys).
 */
public class TabsBean {

    private boolean displayHome = true;
    private boolean displayApp = true;
    private static final Logger LOGGER = Logger.getLogger(TabsBean.class.getName());

    public void printState() {
        
        LOGGER.log(Level.INFO,
                new StringBuilder("------------------------------------\n").
                    append("==== home = ").append(displayHome).append("\n").
                    append("==== app = ").append(displayApp).append("\n").
                    append("------------------------------------").toString());
    }

    public boolean isDisplayHome() {
        return displayHome;
    }

    public void setDisplayHome(boolean displayHome) {
        this.displayHome = displayHome;
    }

    public boolean isDisplayApp() {
        return displayApp;
    }

    public void setDisplayApp(boolean displayApp) {
        this.displayApp = displayApp;
    }


    public void goHome() {
        setDisplayApp(false);
        setDisplayHome(true);
    }

    public void goHomeActionEvent(ActionEvent actionEvent) {
        setDisplayApp(false);
        setDisplayHome(true);
    }

    public void goApp() {
        setDisplayApp(true);
        setDisplayHome(false);
    }

    public void goAppActionEvent(ActionEvent actionEvent) {
        setDisplayApp(true);
        setDisplayHome(false);
    }

}
