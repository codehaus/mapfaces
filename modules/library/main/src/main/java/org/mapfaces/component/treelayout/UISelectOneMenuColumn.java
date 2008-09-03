package org.mapfaces.component.treelayout;

import org.mapfaces.component.abstractTree.UIAbstractColumn;

public class UISelectOneMenuColumn extends UIAbstractColumn{

    private final String RENDERER_TYPE = "org.mapfaces.renderkit.treelayout.HTMLSelectOneMenuColumn";
    private final String FAMILY = "org.mapfaces.treelayout.Column";
    
     /**
     * Add extra parameter like this
     * 
     */
    private String title = null;
    private String itemsLabels = "300*150/300*300/600*300";
    private String itemsValues = "300,150/300,300/600,300";    
     private String separator="/";
//
     @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public String getRendererType() {
        return RENDERER_TYPE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemsLabels() {
        return itemsLabels;
    }

    public void setItemsLabels(String itemsLabels) {
        this.itemsLabels = itemsLabels;
    }

    public String getItemsValues() {
        return itemsValues;
    }

    public void setItemsValues(String itemsValues) {
        this.itemsValues = itemsValues;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
