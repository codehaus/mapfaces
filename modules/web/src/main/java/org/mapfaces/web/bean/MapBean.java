package org.mapfaces.web.bean;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureCollections;
import org.geotools.feature.simple.SimpleFeatureImpl;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.filter.identity.FeatureIdImpl;
import org.geotools.map.FeatureMapLayer;
import org.geotools.map.MapBuilder;
import org.geotools.map.MapContext;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.style.MutableStyle;
import org.mapfaces.component.UIMapPane;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.Context;
import org.mapfaces.models.DefaultFeature;
import org.mapfaces.models.Feature;
import org.mapfaces.util.FacesUtils;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class MapBean {

    private static final Logger LOGGER = Logger.getLogger("org.mapfaces.web.bean.MapBean");
    public MapContext mapContext = null;
    private List<Feature> features = null;

    public MapContext getMapContext() {
        if (mapContext == null) {
            Context model = (Context) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("model");
            System.out.println("##################### model ? " + model);
            if (model != null) {
                final String srs = model.getSrs();
                final CoordinateReferenceSystem crs;
                try {
                    crs = CRS.decode(srs);
                } catch (FactoryException ex) {
                    LOGGER.log(Level.SEVERE, "Invalid SRS definition : " + srs, ex);
                    return null;
                }
               mapContext = MapBuilder.createContext(crs);               
            } 
        }
        
            System.out.println("##################### mapContext ? " + mapContext);
        return mapContext;
    }

    public void addMapContextLayer(ActionEvent actionEvent) {
        try {

            final MutableStyle mutableStyle;
            //building a FeatureCollection for this layer.
            FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection = FeatureCollections.newCollection();
            long featureId = 0;
            SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
            List<Feature> features = buildFeatureList("EPSG:4326");
            mutableStyle = FacesUtils.createStyle("http://localhost:8084/mf/resource/skin/default/img/europa.gif", 10, 0, new Integer(String.valueOf(Math.round(Math.random())*10)));
            if (features != null && features.size() != 0) {
                Feature f = features.get(0);
                builder.setName(f.getName());
                builder.setCRS(f.getCrs());
                for (String key : f.getAttributes().keySet()) {
                    if (key.equals("geometry")) {
                        builder.add(key, Geometry.class);
                    } else {
                        builder.add(key, f.getAttributes().get(key).getClass());
                    }
                }
            }
            SimpleFeatureType sft = builder.buildFeatureType();
            for (Feature f : features) {
                List<Object> objects = new ArrayList<Object>();
                for (String key : f.getAttributes().keySet()) {
                    objects.add(f.getAttributes().get(key));
                }

                SimpleFeature sf = new SimpleFeatureImpl(objects, sft, new FeatureIdImpl(String.valueOf(featureId)));
                featureCollection.add(sf);
                featureId++;
            }
            FeatureMapLayer layer = MapBuilder.createFeatureLayer(featureCollection, mutableStyle);
            if (mapContext != null) {
                mapContext.layers().add(layer);
                System.out.println("Le mapConetxt a " +  mapContext.layers().size());
            } else {
                System.out.println("MapContext is null");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(MapBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public  void addFeatureLayer(ActionEvent actionEvent) {
        features = buildFeatureList("EPSG:4326");
    }
    public static List<Feature> buildFeatureList(String srs) {
        List<Feature> result = new ArrayList<Feature>();
        GeometryFactory geomBuilder = new GeometryFactory();

        for (int i = 0; i < 100; i++) {
            DefaultFeature feature = new DefaultFeature();
            feature.setName("Feature " + i);
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


    public void clearCache() {
        System.out.println("Map bean : clear cache ... Done");
    }

    public void dispose() {
        System.out.println("Map bean : dispose ... Done");
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}
