package org.mapfaces.demo.bean;

import com.vividsolutions.jts.io.ParseException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mapfaces.models.Feature;
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
    // The list of HaiesFictives. The SVGLayer component uses this List to
    // draw OpenLayers Features on the SVGLayer.
    private List<SimpleFeature> simpleFeatures = new ArrayList<SimpleFeature>();
    private List<Feature> features = new ArrayList<Feature>();
    
    // This members allows to CRUD the HaiesFictives in the DataBase. They are setted
    // by the MapFaces SVGLayer component.
    private SimpleFeature featureAdded;
    private SimpleFeature featureRemoved;
    private SimpleFeature featureBeforeUpdate;
    private SimpleFeature featureAfterUpdate;

    /**
     * Constructor. Initialize all the service and the default values.
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public SvgLayerBean() throws SQLException, IOException, ClassNotFoundException {
        try {
            String wkt = "POLYGON((1.05517578125 49.0322265625,8.92138671875 44.505859375,-1.31787109375 42.2646484375,-8.30517578125 47.5380859375,1.05517578125 49.0322265625))";
            this.features.add(FacesMapUtils.getFeatureFromWKT("polygon1", wkt, null));

            wkt = "LINESTRING(-8.70068359375 50.1748046875,15.38134765625 47.6259765625,2.85693359375 42.748046875,-12.47998046875 45.0771484375)";
            this.features.add(FacesMapUtils.getFeatureFromWKT("line1", wkt, null));

            wkt = "POINT(-10.15087890625 41.078125)";
            this.features.add(FacesMapUtils.getFeatureFromWKT("point1", wkt, null));

            for (int i = 0; i <  this.features.size(); i++) {
                final Feature tmp = this.features.get(i);
                this.simpleFeatures.add(FacesMapUtils.getSimpleFeatureFromFeature(tmp, Integer.valueOf(tmp.getId())));
            }
            
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, "WKT can't be parsed !!!", ex);
        } catch (NoSuchAuthorityCodeException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (FactoryException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
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
//            haieService.saveHaie(featureAdded);
//            this.haies = haieService.getListSimpleFeature();
//            this.setHaiesDto(haieService.getDtoList(haies));
//            int i = 0;
//            boolean find = false;
//            SimpleFeature sf = null;
//            while ((i < haies.size()) && !find) {
//                sf = haies.get(i);
//                if (this.haieService.isGeometriesEquals(sf, featureAdded)) {
//                    find = true;
//                }
//                i++;
//            }
//            if (find) {
//                final List<SimpleFeature> sfList = this.haieService.getNearestParcelles(sf);
//                this.parcelleService.updateList(sfList, sf);
//            }
            featureAdded = null;
        } else if(getFeatureRemoved() != null) {
//            int i = 0;
//            boolean find = false;
//            while ((i < haies.size()) && !find) {
//                final SimpleFeature sf = haies.get(i);
//                if (this.haieService.isGeometriesEquals(sf, getFeatureRemoved())) {
//                    deleteHaieAndUpdate(sf);
//                    find = true;
//                }
//                i++;
//            }
            featureRemoved = null;
        } else if((getFeatureBeforeUpdate() != null) && (getFeatureAfterUpdate() != null)) {
//            int i = 0;
//            boolean find = false;
//            while ((i < haies.size()) && !find) {
//                final SimpleFeature sf = haies.get(i);
//                if (this.haieService.isGeometriesEquals(sf, getFeatureBeforeUpdate())) {
//                    List<SimpleFeature> sfList = this.haieService.getNearestParcelles(sf);
//                    featureBeforeUpdate = sf;
//                    sf.setDefaultGeometry(getFeatureAfterUpdate().getDefaultGeometry());
//                    this.haieService.updateHaie(sf);
//                    this.parcelleService.updateListWhenDeleteHaie(sfList, featureBeforeUpdate);
//                    sfList = this.haieService.getNearestParcelles(sf);
//                    this.parcelleService.updateList(sfList, sf);
//                    find = true;
//                }
//                i++;
//            }
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
//        Object haieId = FacesUtils.getRequestParameterValue(FacesContext.getCurrentInstance(), "haieId");
//        if (haieId instanceof String) {
//            boolean find = false;
//            int i = 0;
//            while (!find && (i < haies.size())) {
//                if (haies.get(i).getID().equals(haieId)) {
//                    find = true;
//                    deleteHaieAndUpdate(haies.get(i));
//                }
//                i++;
//            }
//            this.haieService.deleteHaie((String) haieId);
//            this.haies = this.haieService.getListSimpleFeature();
//            this.setHaiesDto(this.haieService.getDtoList(haies));
//        }
    }

    /**
     * This method is called as an action by the Edition button from the DataTable.
     * It saves the SelectedHaies and the current Tree type to display good value
     * in the pop-up.
     */
    public void updateSelectedFeature() {
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
    private void deleteHaieAndUpdate(SimpleFeature sf) throws SQLException, IOException, ClassNotFoundException, TransformException, NoSuchAuthorityCodeException, FactoryException {
//        this.haieService.deleteHaie(sf.getID());
//        final List<SimpleFeature> sfList = this.haieService.getNearestParcelles(sf);
//        this.parcelleService.updateListWhenDeleteHaie(sfList, sf);
//        this.haies.remove(sf);
//        this.setHaiesDto(this.haieService.getDtoList(haies));
    }

    /**
     * This method is called as an action by the "Reinitialisation" submit button. It
     * recompute the Risque value for all the Parcelles. This method sould take
     * a few time to complete.
     * @throws IOException
     * @throws IllegalAttributeException
     * @throws ClassNotFoundException
     * @throws TransformException
     * @throws NoSuchAuthorityCodeException
     * @throws FactoryException
     * @throws SQLException
     */
    public void updateParcelles() throws IOException, IllegalAttributeException, ClassNotFoundException, TransformException, NoSuchAuthorityCodeException, FactoryException, SQLException {
//        this.haieService.deleteAll();
//        this.haies = new ArrayList<SimpleFeature>();
//        this.haiesDto = new ArrayList<HaieDto>();
//        this.parcelleService.updateAll();
    }

    /**
     * This method returns the total length of all the displayed Haies.
     * @return BigDecimal : The total, in meter.
     */
    public BigDecimal getTotalLength() {
        BigDecimal totalLength = BigDecimal.ZERO;
//        for (HaieDto dto : haiesDto) {
//            totalLength = totalLength.add(dto.getLength());
//        }
        return totalLength;
    }

    /**
     * Return the table length (displayed on the datatable).
     * @return int Table length.
     */
    public int getTableLength() {
        return simpleFeatures.size();
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

//    /**
//    * @return the Features
//    */
//    public List<Feature> getFeatures() {
//        return features;
//    }
//
//    /**
//     * @param features the Features to set
//     */
//    public void setFeatures(List<Feature> features) {
//        this.features = features;
//    }

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


//    /**
//     * @return the selectedHaie
//     */
//    public HaieDto getFeatureSelected() {
//        return selectedHaie;
//    }
//
//    /**
//     * @param selectedHaie the selectedHaie to set
//     */
//    public void setFeatureSelected(HaieDto selectedHaie) {
//        this.selectedHaie = selectedHaie;
//    }
//
//    /**
//     * @return the layerNames
//     */
//    public List<String> getLayerNames() {
//        return layerNames;
//    }
//
//    /**
//     * @param layerNames the layerNames to set
//     */
//    public void setLayerNames(List<String> layerNames) {
//        this.layerNames = layerNames;
//    }
}
