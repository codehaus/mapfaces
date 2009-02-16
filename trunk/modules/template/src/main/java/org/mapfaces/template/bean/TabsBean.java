
package org.mapfaces.template.bean;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * This is a managed bean for the tabs component to navigate in all pages.
 * @author Mehdi Sidhoum (Geomatys).
 */
public class TabsBean {

    private boolean displayHome = true;
    private boolean displayApp = false;

    public TabsBean() {
    }

    public void printState() {
        System.out.println("------------------------------------");
        System.out.println("==== home = " + displayHome);
        System.out.println("==== APP = " + displayApp);
        System.out.println("------------------------------------");
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
        return;
    }

    public void goHomeActionEvent(ActionEvent actionEvent) {
        setDisplayApp(false);
        setDisplayHome(true);
    }

    public void goApp() {
        setDisplayApp(true);
        setDisplayHome(false);
        return;
    }

    public void goAppActionEvent(ActionEvent actionEvent) {
        setDisplayApp(true);
        setDisplayHome(false);
    }

}
