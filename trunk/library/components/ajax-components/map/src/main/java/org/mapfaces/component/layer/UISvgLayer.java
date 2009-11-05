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

package org.mapfaces.component.layer;

import java.util.List;
import org.mapfaces.component.*;
import javax.faces.context.FacesContext;
import org.opengis.feature.simple.SimpleFeature;

/**
 *
 * @author leopratlong (Geomatys)
 */
public class UISvgLayer extends UILayer {

    public static final String FAMILIY = "org.mapfaces.SvgLayer";
    private boolean cliToServOnly;
    private List<SimpleFeature> featuresAdded;
    private List<SimpleFeature> featuresRemoved;
    private List<SimpleFeature> featuresUpdated;

    /** Creates a new instance of UIEditionBar */
    public UISvgLayer() {
        super();
        setRendererType("org.mapfaces.renderkit.html.SvgLayer"); // this component has a renderer
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
        final Object values[] = new Object[5];
        values[0] = super.saveState(context);
        values[1] = cliToServOnly;
        values[2] = getFeaturesAdded();
        values[3] = getFeaturesRemoved();
        values[4] = getFeaturesUpdated();
        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setCliToServOnly((Boolean) values[1]);
        setFeaturesAdded((List<SimpleFeature>) values[2]);
        setFeaturesRemoved((List<SimpleFeature>) values[3]);
        setFeaturesUpdated((List<SimpleFeature>) values[4]);
    }

    /**
     * @return the cliToServOnly
     */
    public boolean isCliToServOnly() {
        return cliToServOnly;
    }

    /**
     * @param cliToServOnly the cliToServOnly to set
     */
    public void setCliToServOnly(boolean cliToServOnly) {
        this.cliToServOnly = cliToServOnly;
    }

    /**
     * @return the featuresAdded
     */
    public List<SimpleFeature> getFeaturesAdded() {
        return featuresAdded;
    }

    /**
     * @param featuresAdded the featuresAdded to set
     */
    public void setFeaturesAdded(List<SimpleFeature> featuresAdded) {
        this.featuresAdded = featuresAdded;
    }

    /**
     * @return the featuresRemoved
     */
    public List<SimpleFeature> getFeaturesRemoved() {
        return featuresRemoved;
    }

    /**
     * @param featuresRemoved the featuresRemoved to set
     */
    public void setFeaturesRemoved(List<SimpleFeature> featuresRemoved) {
        this.featuresRemoved = featuresRemoved;
    }

    /**
     * @return the featuresUpdated
     */
    public List<SimpleFeature> getFeaturesUpdated() {
        return featuresUpdated;
    }

    /**
     * @param featuresUpdated the featuresUpdated to set
     */
    public void setFeaturesUpdated(List<SimpleFeature> featuresUpdated) {
        this.featuresUpdated = featuresUpdated;
    }

}
