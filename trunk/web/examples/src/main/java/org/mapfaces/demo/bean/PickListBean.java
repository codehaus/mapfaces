/*
 *    Mapfaces -
 *    http://www.mapfaces.org
 *
 *    (C) 2009, Geomatys
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

package org.mapfaces.demo.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author Mehdi Sidhoum (Geomatys)
 */
public class PickListBean {
//array of values of selected items in "Invisible" list

    private String[] selectedInvisibleItems;

    //array of values of selected items in the "Visible" list
    private String[] selectedVisibleItems;

    //List of SelectItem instances
    private List invisibleItems;

    //List of SelectItem instances
    private List visibleItems;
    /**
     * this hashmap will separe the string organism label with the value attribute of a selectItem.
     */
    private final Map<String, String> mapServices = new HashMap<String, String>();

    private String[] selectedItem = new String[]{"value1"};
    private List<SelectItem> servicesItems = new ArrayList<SelectItem>();

    public PickListBean() {

        invisibleItems = new ArrayList();
        visibleItems = new ArrayList();

        List<String> items = Arrays.asList("New york","what's new","geomatys","heavy world champion","Uranus","Filtre","World wide web","Jumbo","Sangoku");
        int i = 0;
        for (String org : items) {
            mapServices.put(org, String.valueOf(i));
            i++;
        }

        List<SelectItem> availableItems = new ArrayList<SelectItem>();
        for (String s : items) {
            String label = s;
            if (label.length() > 35) {
                String temp = label.substring(0, 35);
                temp = temp.concat("...");
                label = temp;
            }
            String key = (String) mapServices.get(s);
            availableItems.add(new SelectItem(key, label));
        }
        setInvisibleItems(availableItems);


        servicesItems.add(new SelectItem("value1", "Myfaces Tomahawk"));
        servicesItems.add(new SelectItem("value2", "JBoss Richfaces"));
        servicesItems.add(new SelectItem("value3", "Ajax4jsf"));
        servicesItems.add(new SelectItem("value4", "Mapfaces"));
        servicesItems.add(new SelectItem("value5", "IceFaces"));
        servicesItems.add(new SelectItem("value6", "Mojarra"));
        servicesItems.add(new SelectItem("value7", "MDweb2"));
    }

    public void doAction(){
        System.out.println("doAction>>>>>>>>> selectedItem.length = "+selectedItem.length);
        for (String s : selectedItem){
            System.out.println("doAction==> "+s);
        }
    }

    public void setSelectedInvisibleItems(String[] items) {
        selectedInvisibleItems = items;
    }

    public String[] getSelectedInvisibleItems() {
        return selectedInvisibleItems;
    }

    public void setSelectedVisibleItems(String[] items) {
        selectedVisibleItems = items;
    }

    public String[] getSelectedVisibleItems() {
        return selectedVisibleItems;
    }

    public void setInvisibleItems(List items) {
        invisibleItems = items;
    }

    public List getInvisibleItems() {
        return invisibleItems;
    }

    public void setVisibleItems(List items) {
        visibleItems = items;
    }

    public List getVisibleItems() {
        return visibleItems;
    }

    public void moveAllToVisible(ActionEvent actionEvent) {
        getVisibleItems().addAll(getInvisibleItems());
        getInvisibleItems().clear();
    }

    public void moveAllToInvisible(ActionEvent actionEvent) {
        getInvisibleItems().addAll(getVisibleItems());
        getVisibleItems().clear();
    }

    public void moveSelectedToVisible(ActionEvent actionEvent) {
        String[] values = getSelectedInvisibleItems();

        int length = values.length;
        for (int i = 0; i < length; i++) {
            String value = values[i];
            getVisibleItems().add(removeItem(value, getInvisibleItems()));
        }
    }

    public void moveSelectedToInvisible(ActionEvent actionEvent) {
        String[] values = getSelectedVisibleItems();

        int length = values.length;
        for (int i = 0; i < length; i++) {
            String value = values[i];
            getInvisibleItems().add(removeItem(value, getVisibleItems()));
        }
    }

    private SelectItem removeItem(String value, List items) {
        SelectItem result = null;

        int size = items.size();
        for (int i = 0; i < size; i++) {
            SelectItem item = (SelectItem) items.get(i);
            if (value.equals(item.getValue())) {
                result = (SelectItem) items.remove(i);
                break;
            }
        }

        return result;
    }

    /**
     * @return the servicesItems
     */
    public List<SelectItem> getServicesItems() {
        return servicesItems;
    }

    /**
     * @param servicesItems the servicesItems to set
     */
    public void setServicesItems(List<SelectItem> servicesItems) {
        this.servicesItems = servicesItems;
    }

    /**
     * @return the selectedItem
     */
    public String[] getSelectedItem() {
        return selectedItem;
    }

    /**
     * @param selectedItem the selectedItem to set
     */
    public void setSelectedItem(String[] selectedItem) {
        this.selectedItem = selectedItem;
    }

}
