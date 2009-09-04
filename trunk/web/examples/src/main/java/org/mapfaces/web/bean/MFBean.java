

package org.mapfaces.web.bean;

import org.mapfaces.util.FacesMapUtils;

/**
 * This is a test for map responses and jsf staff.
 * @author Mehdi Sidhoum (Geomatys)
 */
public class MFBean {

    private String layerName;
    private String format;
    private double north = 90.0;
    private double south = -90.0;
    private double est = 180.0;
    private double west = -180.0;

    private int width = 800;
    private int height = 600;

    private String url;

    public MFBean(){
        url = "http://cronos.geomatys.com/constellation/WS/wms?" +
                "BBOX=-180,-90.0,180,90.0&CRS=CRS:84&TRANSPARENT=TRUE&" +
                "EXCEPTIONS=application/vnd.ogc.se_inimage&" +
                "VERSION=1.3.0&FORMAT=image/png&SERVICE=WMS&" +
                "HEIGHT=600&" +
                "LAYERS=BlueMarble&" +
                "REQUEST=GetMap&" +
                "STYLES=&" +
                "WIDTH=800";
        layerName = "BlueMarble";
        format="image/png";
    }

    public void applyChanges() {
        url = FacesMapUtils.setParameterValueAndGetUrl("WIDTH", String.valueOf(width), url);
        url = FacesMapUtils.setParameterValueAndGetUrl("HEIGHT", String.valueOf(height), url);
        url = FacesMapUtils.setParameterValueAndGetUrl("LAYERS", layerName, url);
        url = FacesMapUtils.setParameterValueAndGetUrl("FORMAT", format, url);
        String bbox = "";
        bbox += west+ "," + south + "," + est+ "," + north;
        url = FacesMapUtils.setParameterValueAndGetUrl("BBOX", bbox, url);

    }

    /**
     * @return the north
     */
    public double getNorth() {
        return north;
    }

    /**
     * @param north the north to set
     */
    public void setNorth(double north) {
        this.north = north;
    }

    /**
     * @return the south
     */
    public double getSouth() {
        return south;
    }

    /**
     * @param south the south to set
     */
    public void setSouth(double south) {
        this.south = south;
    }

    /**
     * @return the est
     */
    public double getEst() {
        return est;
    }

    /**
     * @param est the est to set
     */
    public void setEst(double est) {
        this.est = est;
    }

    /**
     * @return the west
     */
    public double getWest() {
        return west;
    }

    /**
     * @param west the west to set
     */
    public void setWest(double west) {
        this.west = west;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the layerName
     */
    public String getLayerName() {
        return layerName;
    }

    /**
     * @param layerName the layerName to set
     */
    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }

    

}
