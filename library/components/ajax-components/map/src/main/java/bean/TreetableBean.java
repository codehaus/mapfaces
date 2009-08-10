/*
 *    Mapfaces - http://www.mapfaces.org
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
package bean;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.mapfaces.adapter.owc.Adapter;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Kevin Delfour (IRD)
 */
public class TreetableBean {

    private static final Logger LOGGER = Logger.getLogger(TreetableBean.class.getName());

    private DefaultTreeModel tree;

    public TreetableBean() {
        String fileUrl = "data/context/owc030Cut.xml";
        try {
            tree = Adapter.OWC2Tree(fileUrl);
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTreeModel getTree() {
        return this.tree;
    }

    public void setTree(DefaultTreeModel newvalue) {
        this.tree = newvalue;
    }
}
