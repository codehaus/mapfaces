/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.share.interfaces;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author kdelfour
 */
public interface CustomizeTreeComponentRenderer {
    
    /**
     * 
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    public void beforeEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    /**
     * 
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    public void afterEncodeBegin(FacesContext context, UIComponent component) throws IOException;

    /**
     * 
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    public void beforeEncodeEnd(FacesContext context, UIComponent component) throws IOException;

    /**
     * s
     * @param context
     * @param component
     * @throws java.io.IOException
     */
    public void afterEncodeEnd(FacesContext context, UIComponent component) throws IOException;
}
