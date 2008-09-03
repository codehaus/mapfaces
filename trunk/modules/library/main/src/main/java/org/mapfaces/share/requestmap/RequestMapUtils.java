package org.mapfaces.share.requestmap;

import java.util.Enumeration;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author kdelfour
 */
public class RequestMapUtils {

    /**
     * 
     * @param key
     * @param value
     */
    @SuppressWarnings("unchecked")
    public static void put(Object key, Object value) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map requestMap = ec.getRequestMap();

        if (key != null) {
            requestMap.put(key, value);
        }
    }

    /**
     * 
     * @param key
     * @return
     */
    public static Object getbyKey(Object key) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map requestMap = ec.getRequestMap();
        if (key != null) {
            if (requestMap.containsKey(key)) {
                return requestMap.get(key);
            }
        }
        return null;
    }

    public static void showRequestMap(HttpServletRequest request) {
        Enumeration map = request.getAttributeNames();
        while (map.hasMoreElements()) {
            System.out.println("ATTRIBUTES" + map.nextElement());
        }
    }
}
