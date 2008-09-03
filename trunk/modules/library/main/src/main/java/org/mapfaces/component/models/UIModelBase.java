/*
 * UIModelBase.java
 *
 * Created on 28 décembre 2007, 15:16
 */

package org.mapfaces.component.models;

import javax.faces.component.UICommand;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author Mehdi Sidhoum
 */
public abstract class UIModelBase extends UICommand {
    
    private String title;
    private String defaultModelUrl;
    private String method;
    private String namespace;
    
     /* 
     *  Debug property
     */
    private boolean debug;
    
    /* 
     *  Ajax component id 
     *
     */
    private String ajaxCompId;
    
    /*
     * JAXB rootElement
     * 
     */
    private JAXBElement JAXBElt;
    
    
    //private Object doc;    
    /** Creates a new instance of UIModelBase */
    public UIModelBase() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDefaultModelUrl() {
        return defaultModelUrl;
    }

    public void setDefaultModelUrl(String defaultModelUrl) {
        this.defaultModelUrl = defaultModelUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    @Override
     public Object saveState(FacesContext context) {
    /*private String title;
    private String defaultModelUrl;
    private String method;
    private String scriptFile;
    private String namespace;;*/
        Object values[] = new Object[7];
        values[0] = super.saveState(context); 
        values[1] = title;
        values[2] = defaultModelUrl;
        values[3] = method;
        values[4] = namespace;
        values[5] = JAXBElt;
        values[6] = ajaxCompId;
        return values;
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]); 
        title = (String) values[1];
        defaultModelUrl = (String) values[2];
        method = (String) values[3];
        namespace = (String) values[4];
        JAXBElt = (JAXBElement) values[5];
        ajaxCompId = (String) values[6];
    }
    
    public String getAjaxCompId() {
        return ajaxCompId;
    }

    public void setAjaxCompId(String ajaxCompId) {
        this.ajaxCompId = ajaxCompId;
    }
    public JAXBElement getJAXBElt() {
        return JAXBElt;
    }

    public void setJAXBElt(JAXBElement JAXBElt) {
        this.JAXBElt = JAXBElt;
    }
    
    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

}
