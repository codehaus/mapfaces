package org.mapfaces.demo.bean;

import com.vividsolutions.jts.io.ParseException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.mapfaces.models.Feature;
import org.mapfaces.share.utils.FacesUtils;
import org.mapfaces.util.FacesMapUtils;
import org.opengis.feature.IllegalAttributeException;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.operation.TransformException;

/**
 * This class is used to map the model values to the User Interface.
 * @author leopratlong
 */
public class SvgLayerBean {

    private static final Logger LOGGER = Logger.getLogger(SvgLayerBean.class.getName());

    //The SVGLayer component uses a list of SimpleFeature to
    // draw OpenLayers Features on the SVGLayer.
    private List<SimpleFeature> simpleFeatures = new ArrayList<SimpleFeature>();
    
    // This members allows to CRUD the SimpleFeatures in the DataBase. They are setted
    // by the MapFaces SVGLayer component.
    private SimpleFeature featureAdded;
    private SimpleFeature featureRemoved;
    private SimpleFeature featureBeforeUpdate;
    private SimpleFeature featureAfterUpdate;


    //Datable display a list a features model, these Features represents the SimpleFeature displays on map
    private List<Feature> features = new ArrayList<Feature>();
    //The Feature selected on the datatable
    private Feature featureSelected;


    /**
     * Constructor. Initialize all the service and the default values.
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public SvgLayerBean() throws SQLException, IOException, ClassNotFoundException {
        try {
            long id = 1;
            String wkt = "POLYGON((1.05517578125 49.0322265625,8.92138671875 44.505859375,-1.31787109375 42.2646484375,-8.30517578125 47.5380859375,1.05517578125 49.0322265625))";
            this.features.add(FacesMapUtils.getFeatureFromWKT(String.valueOf(id), "polygon", wkt, null));
            id +=5;
            wkt = "LINESTRING(-8.70068359375 50.1748046875,15.38134765625 47.6259765625,2.85693359375 42.748046875,-12.47998046875 45.0771484375)";
            this.features.add(FacesMapUtils.getFeatureFromWKT(String.valueOf(id), "line", wkt, null));
            id +=5;
            wkt = "POINT(-10.15087890625 41.078125)";
            this.features.add(FacesMapUtils.getFeatureFromWKT(String.valueOf(id), "point", wkt, null));

            for (int i = 0; i <  this.features.size(); i++) {
                final Feature tmp = this.features.get(i);
                this.simpleFeatures.add(FacesMapUtils.getSimpleFeatureFromFeature(tmp, Long.valueOf(tmp.getId()).longValue()));
            }
            
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, "WKT can't be parsed !!!", ex);
        } 
    }

    /**
     * This method is called by the SVGLayer Component as an action. It allows
     * to CRUD the Haie according to the result of the SVGLayer component.
     * @throws IOException
     * @throws ParseException
     * @throws SQLException
     * @throws IOException
     * @throws IOException
     * @throws IllegalAttributeException
     * @throws ClassNotFoundException
     * @throws TransformException
     * @throws NoSuchAuthorityCodeException
     * @throws FactoryException
     */
    public void updateFeatures() throws IOException, ParseException, SQLException, IOException, IOException, IllegalAttributeException, ClassNotFoundException, TransformException, NoSuchAuthorityCodeException, FactoryException {
        if (getFeatureAdded() != null) {
            System.out.println("Feature added");
            this.simpleFeatures.add(featureAdded);
            featureAdded = null;

        } else if(getFeatureRemoved() != null) {
            System.out.println("Feature removed");
            int i = 0;
            boolean find = false;

            while ((i < this.simpleFeatures.size()) && !find) {
                final SimpleFeature sf = this.simpleFeatures.get(i);
                
                if (sf.getDefaultGeometry().toString().equals(featureRemoved.getDefaultGeometry().toString())) {
                    this.simpleFeatures.remove(sf);
                    find = true;
                }
                i++;
            }
            featureRemoved = null;
            
        } else if((getFeatureBeforeUpdate() != null) && (getFeatureAfterUpdate() != null)) {
            System.out.println("Feature before and after update");
            int i = 0;
            boolean find = false;
             while ((i < this.getSimpleFeatures().size()) && !find) {
                final SimpleFeature sf = this.simpleFeatures.get(i);

                 if (sf.getDefaultGeometry().toString().equals(featureBeforeUpdate.getDefaultGeometry().toString())) {
                    featureBeforeUpdate = sf;
                    sf.setDefaultGeometry(featureAfterUpdate.getDefaultGeometry());
                    find = true;
                }
                i++;
            }
            featureBeforeUpdate = null;
            featureAfterUpdate = null;
        }
    }

    /**
     * This method is called as an action by the delete A4J commanLink from the
     * DataTable. It allows to delete a SimpleFeature with the specified ID.
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws TransformException
     * @throws NoSuchAuthorityCodeException
     * @throws FactoryException
     */
    public void deleteFeature() throws SQLException, IOException, ClassNotFoundException, TransformException, NoSuchAuthorityCodeException, FactoryException {

        System.out.println("Delete feature");
        Object id = FacesUtils.getRequestParameterValue(FacesContext.getCurrentInstance(), "simpleFeatureId");
        if (id instanceof String) {
            boolean find = false;
            int i = 0;
             while ((i < this.simpleFeatures.size()) && !find) {
                if (this.simpleFeatures.get(i).getID().equals(id)) {
                    System.out.println("Real Delete feature");
                    find = true;
                    this.simpleFeatures.remove(this.simpleFeatures.get(i));
                }
                i++;
            }
        }
    }

    /**
     * This method is called as an action by the Edition button from the DataTable.
     * It saves the SelectedHaies and the current Tree type to display good value
     * in the pop-up.
     */
    public void updateSelectedFeature() {
            System.out.println("Update selected feature");
//        Object haieId = FacesUtils.getRequestParameterValue(FacesContext.getCurrentInstance(), "haieId");
//        if (haieId instanceof String) {
//            boolean find = false;
//            int i = 0;
//            while (!find && (i < haiesDto.size())) {
//                if (haiesDto.get(i).getId().equals(haieId)) {
//                    selectedHaie = haiesDto.get(i);
//                    chosenTree = selectedHaie.getTypeArbre();
//                    find = true;
//                }
//                i++;
//            }
//        }
    }

   

    /**
     * This method delete a Haie, update the Risque after the delete, then update the
     * lists used to display the User Interface.
     * @param sf The SimpleFeature to delete.
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws TransformException
     * @throws NoSuchAuthorityCodeException
     * @throws FactoryException
     */
    private void deleteFeatureAndUpdate(SimpleFeature sf) throws SQLException, IOException, ClassNotFoundException, TransformException, NoSuchAuthorityCodeException, FactoryException {
        
        System.out.println("Delete feature and update");
        //        this.haieService.deleteHaie(sf.getID());
//        final List<SimpleFeature> sfList = this.haieService.getNearestParcelles(sf);
//        this.parcelleService.updateListWhenDeleteHaie(sfList, sf);
//        this.haies.remove(sf);
//        this.setHaiesDto(this.haieService.getDtoList(haies));
    }
    
    /**
     * @return the SimpleFeatures
     */
    public List<SimpleFeature> getSimpleFeatures() {
        return simpleFeatures;
    }


    /**
     * @param simpleFeatures the SimpleFeatures to set
     */
    public void setSimpleFeatures(List<SimpleFeature> simpleFeatures) {
        this.simpleFeatures = simpleFeatures;
    }

    /**
    * @return the Features
    */
    public List<Feature> getFeatures() {
        return features;
    }

    /**
     * @param features the Features to set
     */
    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    /**
     * @return the featureAdded
     */
    public SimpleFeature getFeatureAdded() {
        return featureAdded;
    }

    /**
     * @param featureAdded the featureAdded to set
     */
    public void setFeatureAdded(SimpleFeature featureAdded) {
        this.featureAdded = featureAdded;
    }

    /**
     * @return the featureRemoved
     */
    public SimpleFeature getFeatureRemoved() {
        return featureRemoved;
    }

    /**
     * @param featureRemoved the featureRemoved to set
     */
    public void setFeatureRemoved(SimpleFeature featureRemoved) {
        this.featureRemoved = featureRemoved;
    }

    /**
     * @return the featureBeforeUpdate
     */
    public SimpleFeature getFeatureBeforeUpdate() {
        return featureBeforeUpdate;
    }

    /**
     * @param featureBeforeUpdate the featureBeforeUpdate to set
     */
    public void setFeatureBeforeUpdate(SimpleFeature featureBeforeUpdate) {
        this.featureBeforeUpdate = featureBeforeUpdate;
    }

    /**
     * @return the featureAfterUpdate
     */
    public SimpleFeature getFeatureAfterUpdate() {
        return featureAfterUpdate;
    }

    /**
     * @param featureAfterUpdate the featureAfterUpdate to set
     */
    public void setFeatureAfterUpdate(SimpleFeature featureAfterUpdate) {
        this.featureAfterUpdate = featureAfterUpdate;
    }


    /**
     * @return the selectedHaie
     */
    public Feature getFeatureSelected() {
        return featureSelected;
    }

    /**
     * @param selectedHaie the selectedHaie to set
     */
    public void setFeatureSelected(Feature featureSelected) {
        this.featureSelected = featureSelected;
    }
}
