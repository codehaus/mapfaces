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

package org.mapfaces.component;

import javax.faces.component.html.HtmlSelectManyListbox;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mehdi Sidhoum (Geomatys)
 * @since 0.3
 */
public class UISelectManyPicklist extends HtmlSelectManyListbox {

    public static final String FAMILIY = "org.mapfaces.SelectManyPicklist";

    private boolean debug;
    private String style = "";
    private String styleClass = "";
    private String addButtonText = "";
    private String addAllButtonText = "";
    private String removeButtonText = "";
    private String removeAllButtonText = "";
    private String addButtonStyle = "";
    private String addAllButtonStyle = "";
    private String removeButtonStyle = "";
    private String removeAllButtonStyle = "";
    private String addButtonStyleClass = "";
    private String addAllButtonStyleClass = "";
    private String removeButtonStyleClass = "";
    private String removeAllButtonStyleClass = "";
    private boolean loadJs = true;

    public UISelectManyPicklist() {
        super();
        setRendererType("org.mapfaces.renderkit.html.SelectManyPicklist");    // this component has a renderer
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
        final Object values[] = new Object[17];
        values[0] = super.saveState(context);
        values[1] = style;
        values[2] = styleClass;
        values[3] = debug;
        values[4] = addButtonText;
        values[5] = addAllButtonText;
        values[6] = removeButtonText;
        values[7] = removeAllButtonText;
        values[8] = addButtonStyle;
        values[9] = addAllButtonStyle;
        values[10] = removeButtonStyle;
        values[11] = removeAllButtonStyle;
        values[12] = addButtonStyleClass;
        values[13] = addAllButtonStyleClass;
        values[14] = removeButtonStyleClass;
        values[15] = removeAllButtonStyleClass;
        values[16] = loadJs;

        return values;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setStyle((String) values[1]);
        setStyleClass((String) values[2]);
        setDebug((Boolean) values[3]);
        setAddButtonText((String) values[4]);
        setAddAllButtonText((String) values[5]);
        setRemoveButtonText((String) values[6]);
        setRemoveAllButtonText((String) values[7]);
        setAddButtonStyle((String) values[8]);
        setAddAllButtonStyle((String) values[9]);
        setRemoveButtonStyle((String) values[10]);
        setRemoveAllButtonStyle((String) values[11]);
        setAddButtonStyleClass((String) values[12]);
        setAddAllButtonStyleClass((String) values[13]);
        setRemoveButtonStyleClass((String) values[14]);
        setRemoveAllButtonStyleClass((String) values[15]);
        setLoadJs((Boolean) values[16]);
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * @return the addButtonText
     */
    public String getAddButtonText() {
        return addButtonText;
    }

    /**
     * @param addButtonText the addButtonText to set
     */
    public void setAddButtonText(String addButtonText) {
        this.addButtonText = addButtonText;
    }

    /**
     * @return the addAllButtonText
     */
    public String getAddAllButtonText() {
        return addAllButtonText;
    }

    /**
     * @param addAllButtonText the addAllButtonText to set
     */
    public void setAddAllButtonText(String addAllButtonText) {
        this.addAllButtonText = addAllButtonText;
    }

    /**
     * @return the removeButtonText
     */
    public String getRemoveButtonText() {
        return removeButtonText;
    }

    /**
     * @param removeButtonText the removeButtonText to set
     */
    public void setRemoveButtonText(String removeButtonText) {
        this.removeButtonText = removeButtonText;
    }

    /**
     * @return the removeAllButtonText
     */
    public String getRemoveAllButtonText() {
        return removeAllButtonText;
    }

    /**
     * @param removeAllButtonText the removeAllButtonText to set
     */
    public void setRemoveAllButtonText(String removeAllButtonText) {
        this.removeAllButtonText = removeAllButtonText;
    }

    /**
     * @return the addButtonStyle
     */
    public String getAddButtonStyle() {
        return addButtonStyle;
    }

    /**
     * @param addButtonStyle the addButtonStyle to set
     */
    public void setAddButtonStyle(String addButtonStyle) {
        this.addButtonStyle = addButtonStyle;
    }

    /**
     * @return the addAllButtonStyle
     */
    public String getAddAllButtonStyle() {
        return addAllButtonStyle;
    }

    /**
     * @param addAllButtonStyle the addAllButtonStyle to set
     */
    public void setAddAllButtonStyle(String addAllButtonStyle) {
        this.addAllButtonStyle = addAllButtonStyle;
    }

    /**
     * @return the removeButtonStyle
     */
    public String getRemoveButtonStyle() {
        return removeButtonStyle;
    }

    /**
     * @param removeButtonStyle the removeButtonStyle to set
     */
    public void setRemoveButtonStyle(String removeButtonStyle) {
        this.removeButtonStyle = removeButtonStyle;
    }

    /**
     * @return the removeAllButtonStyle
     */
    public String getRemoveAllButtonStyle() {
        return removeAllButtonStyle;
    }

    /**
     * @param removeAllButtonStyle the removeAllButtonStyle to set
     */
    public void setRemoveAllButtonStyle(String removeAllButtonStyle) {
        this.removeAllButtonStyle = removeAllButtonStyle;
    }

    /**
     * @return the addButtonStyleClass
     */
    public String getAddButtonStyleClass() {
        return addButtonStyleClass;
    }

    /**
     * @param addButtonStyleClass the addButtonStyleClass to set
     */
    public void setAddButtonStyleClass(String addButtonStyleClass) {
        this.addButtonStyleClass = addButtonStyleClass;
    }

    /**
     * @return the addAllButtonStyleClass
     */
    public String getAddAllButtonStyleClass() {
        return addAllButtonStyleClass;
    }

    /**
     * @param addAllButtonStyleClass the addAllButtonStyleClass to set
     */
    public void setAddAllButtonStyleClass(String addAllButtonStyleClass) {
        this.addAllButtonStyleClass = addAllButtonStyleClass;
    }

    /**
     * @return the removeButtonStyleClass
     */
    public String getRemoveButtonStyleClass() {
        return removeButtonStyleClass;
    }

    /**
     * @param removeButtonStyleClass the removeButtonStyleClass to set
     */
    public void setRemoveButtonStyleClass(String removeButtonStyleClass) {
        this.removeButtonStyleClass = removeButtonStyleClass;
    }

    /**
     * @return the removeAllButtonStyleClass
     */
    public String getRemoveAllButtonStyleClass() {
        return removeAllButtonStyleClass;
    }

    /**
     * @param removeAllButtonStyleClass the removeAllButtonStyleClass to set
     */
    public void setRemoveAllButtonStyleClass(String removeAllButtonStyleClass) {
        this.removeAllButtonStyleClass = removeAllButtonStyleClass;
    }

    /**
     * @return the loadJs
     */
    public boolean isLoadJs() {
        return loadJs;
    }

    /**
     * @param loadJs the loadJs to set
     */
    public void setLoadJs(boolean loadJs) {
        this.loadJs = loadJs;
    }

}
