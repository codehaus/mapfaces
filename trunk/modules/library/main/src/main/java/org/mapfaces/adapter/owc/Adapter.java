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
package org.mapfaces.adapter.owc;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.bind.JAXBException;

import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.models.tree.TreeItem;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.util.XMLContextUtilities;

/**
 * JaxB adapter.
 *
 * @author Kevin Delfour (Geomatys)
 */
public class Adapter {

   public static DefaultTreeModel OWC2Tree(final String fileUrl) throws JAXBException,UnsupportedEncodingException{

        final FacesContext context              = FacesContext.getCurrentInstance();
        final ServletContext sc                 = (ServletContext) context.getExternalContext().getContext();
        final Context model                     = new XMLContextUtilities().readContext(sc.getRealPath(fileUrl));
        final DefaultTreeModel tree             = new DefaultTreeModel(null);
        final DefaultMutableTreeNode root       = new DefaultMutableTreeNode("root");
        final DefaultMutableTreeNode contextOwc = new DefaultMutableTreeNode(model);
        final List<Layer> layers                = model.getLayers();
        final int depth                         = 1;

        //Then we get a list layers to construct the tree
        for(int id=0, n=layers.size(); id<n; id++){
            final Layer layer = layers.get(id);
            contextOwc.add( new TreeNodeModel(layer, id+1, depth, id+1, false) );
        }

        root.add(contextOwc);
        tree.setRoot(root);
        return tree;
    }

    public static DefaultTreeModel context2Tree(final FacesContext context, final Context model) {

        final DefaultTreeModel tree             = new DefaultTreeModel(null);
        final DefaultMutableTreeNode root       = new DefaultMutableTreeNode("root");
        final TreeItem treeItemRoot             = new TreeItem(model);
        final DefaultMutableTreeNode contextOwc = new DefaultMutableTreeNode(treeItemRoot);
        final List<Layer> layers                = model.getLayers();
        final int depth                         = 1;

        root.add(contextOwc);

        //Then we get a list layers to construct the tree
        for(int id=0, n=layers.size(); id<n; id++){
            final Layer layer        = layers.get(id);
            final TreeItem treeItem  = new TreeItem(layer);
            final TreeNodeModel item = new TreeNodeModel(treeItem, id+1, depth, id+1, false);
            contextOwc.add(item);
        }

        tree.setRoot(root);
        return tree;
    }

//public static TimeLineBean string2TimeLineBean(FacesContext context, AbstractContext model) {
//     Calendar cal = Calendar.getInstance(TimeZone.getDefault());
//        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
//        SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
//
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
//        sdf.setTimeZone(TimeZone.getDefault());
//
//        SortedSet<Date> dates = PeriodUtilities.getDatesFromPeriodDescription("2004-06-06T12:00:00Z/2005-06-20T12:00:00Z/P1D,2007-06-04T12:00:00Z,2007-06-03T12:00:00Z",sdf);
//        Date dateBegin = dates.first();
//        Date dateEnd = dates.last();
//        for (Iterator it = dates.iterator(); it.hasNext();){
//            Date crrt  = (Date)it.next();
//            Event e = new Event(crrt,
//                                       null,
//                                        null,
//                                        false,
//                                        "Time available for the layer : ",
//                                        "This is the duration of the jsf implementation for the component TimeLine",
//                                        "http://simile.mit.edu/images/csail-logo.gif",
//                                        "http://travel.yahoo.com/",
//                                        "",
//                                        Priority.NORMAL,
//                                        "",
//                                        Status.IN_PROGRESS,
//                                        "",
//                                        null);
//            System.out.println(">> Event = "+e);
//                    events.add(e);
//        }
//        System.out.println("eveeeeentes"+events.size());
//        System.out.println("  datebegin = "+dateBegin+"  date2 = "+sdf.parse("2007-06-29T12:00:00Z"));
//
//
//
//        cal.set(2007, 5, 11, 15, 30, 25);
//        centerDate =cal.getTime();
//}

}
