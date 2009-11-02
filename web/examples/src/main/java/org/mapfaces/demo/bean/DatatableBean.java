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
import java.util.List;
import javax.faces.model.ListDataModel;
import org.mapfaces.demo.model.DataModelRow;

/**
 *
 * @author Mehdi Sidhoum (Geomatys)
 * @since 0.3
 */
public class DatatableBean {

    private ListDataModel allContactsModel = new ListDataModel();

    /**
     * Creates a new instance of datatablebean
     */
    public DatatableBean() {
        List<DataModelRow> modelsList = new ArrayList<DataModelRow>();
        for (int i = 0; i < 10; i++) {
            modelsList.add(new DataModelRow(i, "contact_" + i));
        }
        allContactsModel = new ListDataModel(modelsList);
    }

    /**
     * @return the allContactsModel
     */
    public ListDataModel getAllContactsModel() {
        return allContactsModel;
    }

    /**
     * @param allContactsModel the allContactsModel to set
     */
    public void setAllContactsModel(ListDataModel allContactsModel) {
        this.allContactsModel = allContactsModel;
    }
}
