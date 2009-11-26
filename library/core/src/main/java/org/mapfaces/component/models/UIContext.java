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

package org.mapfaces.component.models;

import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBException;

/**
 * @author Olivier Terral.
 * @author Mehdi Sidhoum.
 */
public class UIContext extends UIModelBase {

    public static final String FAMILIY = "org.mapfaces.model.Context";
    public static final String jaxbInstance = "org.geotoolkit.owc.xml.v030:org.geotoolkit.wmc.xml.v110";

    private boolean scriptaculous = false;
    private boolean mootools = false;
    private boolean openlayers = true;
    private boolean minifyJS = true;
    private String service = "";
    private String ajaxRegion = "";

    /** Creates a new instance of UIAbstract */
    public UIContext(){
        super();
        setRendererType("org.mapfaces.renderkit.html.models.Context"); // this component has a renderer
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getFamily() {
        return FAMILIY;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object saveState(final FacesContext context) {
        final Object values[] = new Object[11];
        values[0] = super.saveState(context);
        values[1] = scriptaculous;
        values[2] = mootools;
        values[3] = minifyJS;
        values[4] = service;
        values[5] = openlayers;
        values[6] = ajaxRegion;
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        scriptaculous = (Boolean) values[1];
        mootools = (Boolean) values[2];
        minifyJS = (Boolean) values[3];
        service = (String) values[4];
        openlayers = (Boolean) values[5];
        ajaxRegion = (String) values[6];
    }

    /**
     * This method saves the model into a xml file in a temporary folder.
     * @param context
     * @return
     * @throws javax.xml.bind.JAXBException
     */
    public String saveModel(final FacesContext context) throws JAXBException {
        //TODO
//        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
//        String fileUrl = sc.getRealPath("tmp/owc.xml");
//        File t = new File(fileUrl);
//
//        //casting the model to Context for this UIContext component.
//        Context modelContext = (Context) getModel();
//        JAXBContext.newInstance(jaxbInstance).createMarshaller().marshal(modelContext.getDoc(),new File(fileUrl));
//        return fileUrl;
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isScriptaculous() {
        return scriptaculous;
    }

    public void setScriptaculous(final boolean scriptaculous) {
        this.scriptaculous = scriptaculous;
    }

    public boolean isMootools() {
        return mootools;
    }

    public void setMootools(final boolean mootools) {
        this.mootools = mootools;
    }
    
    public boolean isOpenlayers() {
        return openlayers;
    }

    public void setOpenlayers(final boolean ol) {
        this.openlayers = ol;
    }
    
    public boolean isMinifyJS() {
        return minifyJS;
    }

    public void setMinifyJS(final boolean minifyJS) {
        this.minifyJS = minifyJS;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return the ajaxRegion
     */
    public String getAjaxRegion() {
        return ajaxRegion;
    }

    /**
     * @param ajaxRegion the ajaxRegion to set
     */
    public void setAjaxRegion(String ajaxRegion) {
        this.ajaxRegion = ajaxRegion;
    }

}
