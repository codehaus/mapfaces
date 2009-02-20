package org.mapfaces.web.bean;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import net.opengis.owc.v030.LayerType;
import org.geotools.map.MapBuilder;
import org.geotools.map.MapContext;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.Context;
import org.mapfaces.models.DefaultFeature;
import org.mapfaces.models.DefaultLayer;
import org.mapfaces.models.Feature;
import org.mapfaces.models.Server;
import org.mapfaces.share.listener.ResourcePhaseListener;
import org.mapfaces.util.ContextFactory;
import org.mapfaces.util.DefaultContextFactory;
import org.mapfaces.util.FacesUtils;
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

    public static List<Feature> buildFeatureList(String srs) {
        List<Feature> result = new ArrayList<Feature>();
        GeometryFactory geomBuilder = new GeometryFactory();


        for (int i = 0; i < 100; i++) {
            DefaultFeature feature = new DefaultFeature();
            feature.setName("marker" + i);
            try {
                feature.setCrs((DefaultGeographicCRS) CRS.decode(srs));
            } catch (Exception exp) {
                exp.printStackTrace();
            }

            Map<String, Object> attributes = new HashMap<String, Object>();
            List<Object> objects = new ArrayList<Object>();
            double x1 = (Math.random() - Math.random()) * 180;
            double x2 = (Math.random() - Math.random()) * 180;
            double y1 = (Math.random() - Math.random()) * 90;
            double y2 = (Math.random() - Math.random()) * 90;
            double minx = Math.min(x1, x2);
            double miny = Math.min(y1, y2);
            double maxx = Math.max(x1, x2);
            double maxy = Math.max(y1, y2);
            Coordinate[] coords = new Coordinate[]{
                new Coordinate(minx, miny),
                new Coordinate(minx, maxy),
                new Coordinate(maxx, maxy),
                new Coordinate(maxx, miny),
                new Coordinate(minx, miny),
            };
            LinearRing linear = geomBuilder.createLinearRing(coords);
            Geometry geometry = geomBuilder.createPolygon(linear, new LinearRing[0]);

            final String featuretype;

            if (geometry.getArea() == 0) {
                featuretype = Feature.POINT;
                geometry = geomBuilder.createPoint(coords[0]);
            } else {
                featuretype = Feature.POLYGON;
            }

            objects.add(geometry);

            attributes.put("geometry", geometry);
            attributes.put("type", featuretype);
            attributes.put("toponym", "feature name");
            attributes.put("title", "title feature");
            attributes.put("object", "object attached for this feature ie: Result object");
            //  you can add more attributes for this feature.
            feature.setAttributes(attributes);
            feature.setGeometry(geometry);

            result.add(feature);
        }

        return result;
    }

    public void addBasicMfLayer() {
        FacesContext facesCtxt = FacesContext.getCurrentInstance();
        if (facesCtxt != null) {

            UIContext uiContext = (UIContext) FacesUtils.findComponentById(facesCtxt, facesCtxt.getViewRoot(), "contextId");
            if (uiContext == null) {
                return; // if there is no context of mapfaces then this method do nothing.
            }
            UIMapPane mappane = null;
            //getting the mappane from this UIContext
            for (UIComponent comp : uiContext.getChildren()) {
                if (comp instanceof UIMapPane) {
                    mappane = (UIMapPane) comp;
                    break;
                }
            }
            int indexLayer = 0;
            if (mappane != null) {
                int nblayers = mappane.getChildCount();
                indexLayer = nblayers + 1;
            } else {
                indexLayer++;
            }

            Context ctx = (Context) uiContext.getModel();
            final String contextSrs = ctx.getSrs();
            List<Feature> features = buildFeatureList(contextSrs);


            if (features.size() != 0) {
                //adding the feature collection into the new layer.
                final ContextFactory contextFactory = new DefaultContextFactory();
                Server wms = contextFactory.createDefaultServer();
                wms.setHref("");
                wms.setService("mapfaces_service");
                wms.setVersion("1.0");

                LayerType layerType = new LayerType();
                layerType.setId("MapFaces_Layer_MFS_" + indexLayer);
                layerType.setGroup("mapfaces_group");
                layerType.setName("markers");
                layerType.setHidden(false);
                layerType.setOpacity(new BigDecimal(1));

                HttpServletRequest request = (HttpServletRequest) facesCtxt.getExternalContext().getRequest();

                DefaultLayer layer = (DefaultLayer) contextFactory.createDefaultLayer();

                layer.setGroupId(indexLayer);

                layer.setId(layerType.getId());
                layer.setGroup(layerType.getGroup());
                layer.setName(layerType.getName());
                layer.setHidden(layerType.isHidden());
                layer.setOpacity(layerType.getOpacity().toString());
                layer.setTitle("mapfaces_title");
                layer.setServer(wms);
                layer.setType("mapfaces");
                layer.setOutputFormat("image/gif");
                layer.setQueryable(true);
                //@TODO temporary hack due to the number of png is limited, use instead a large of png set.
                if (indexLayer > 9) {
                    indexLayer = 1;
                }
                layer.setImage(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + ResourcePhaseListener.getURL(facesCtxt, "/org/mapfaces/resources/img/markers/" + indexLayer + ".png", null));
                layer.setSize(18);
                layer.setCompId(FacesUtils.getParentUIModelBase(facesCtxt, mappane).getId() + "_" + mappane.getId() + "_" + layer.getId());
                layer.setFeatures(features);

                ctx.removeLayerFromId(layer.getId());
                ctx.addLayer(layer);

            } else {
                //if there is no features to display we remove the attached layer
                ctx.removeLayerFromId("MapFaces_Layer_MFS_" + indexLayer);
            }
        }
    }

    public void clearCache() {
        System.out.println("Map bean : clear cache ... Done");
    }

    public void dispose() {
        System.out.println("Map bean : dispose ... Done");
    }
}
