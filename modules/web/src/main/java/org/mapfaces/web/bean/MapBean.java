
package org.mapfaces.web.bean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.geotools.map.MapBuilder;
import org.geotools.map.MapContext;
import org.geotools.referencing.CRS;
import org.mapfaces.models.Context;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class MapBean {
    
    private static final Logger LOGGER = Logger.getLogger("org.mapfaces.web.bean.MapBean");
    public MapContext mapContext = null;
    
    public MapContext getMapContext() {
        if (mapContext == null) {
            Context model = (Context) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("model");
            if (model != null) {
                final String srs = model.getSrs();
                final CoordinateReferenceSystem crs;
                try {
                    crs = CRS.decode(srs);
                } catch (FactoryException ex) {
                    LOGGER.log(Level.SEVERE, "Invalid SRS definition : " + srs, ex);
                    return null;
                }
                MapContext context = MapBuilder.getInstance().createContext(crs);
                return context;
            } else {
                return null;
            } 
        } else {
            return mapContext;
        }
            
    }
    public void addBasicMfLayer() {
        
    }
    public void clearCache() {
        System.out.println("Map bean : clear cache ... Done");
    }

    public void dispose() {
        System.out.println("Map bean : dispose ... Done");
    }

}
