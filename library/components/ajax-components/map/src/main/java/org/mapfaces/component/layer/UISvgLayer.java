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
    private SimpleFeature featureAdded;
    private SimpleFeature featureRemoved;
    private SimpleFeature featureBeforeUpdate;
    private SimpleFeature featureAfterUpdate;
    private String title;
    private double opacity;
    private String reRender;

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
        final Object values[] = new Object[9];
        values[0] = super.saveState(context);
        values[1] = cliToServOnly;
        values[2] = getFeatureAdded();
        values[3] = getFeatureRemoved();
        values[4] = getFeatureBeforeUpdate();
        values[5] = getFeatureAfterUpdate();
        values[6] = getTitle();
        values[7] = getOpacity();
        values[8] = getReRender();
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
        setFeatureAdded((SimpleFeature) values[2]);
        setFeatureRemoved((SimpleFeature) values[3]);
        setFeatureBeforeUpdate((SimpleFeature) values[4]);
        setFeatureAfterUpdate((SimpleFeature) values[5]);
        setTitle((String) values[6]);
        setOpacity((Double) values[7]);
        setReRender((String) values[8]);
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the opacity
     */
    public double getOpacity() {
        return opacity;
    }

    /**
     * @param opacity the opacity to set
     */
    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    /**
     * @return the reRender
     */
    public String getReRender() {
        return reRender;
    }

    /**
     * @param reRender the reRender to set
     */
    public void setReRender(String reRender) {
        this.reRender = reRender;
    }

}
