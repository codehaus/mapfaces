package org.mapfaces.share.interfaces;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author kdelfour
 */
public interface  AjaxRendererInterface {
    public void handleAjaxRequest(FacesContext context, UIComponent component);
}
