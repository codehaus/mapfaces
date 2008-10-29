package org.mapfaces.adapter.owc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.mapfaces.models.Context;
import org.mapfaces.models.Layer;
import org.mapfaces.models.tree.TreeItem;
import org.mapfaces.models.tree.TreeNodeModel;
import org.mapfaces.util.XMLContextUtilities;

/**
 *
 * @author kdelfour
 */
public class Adapter { 

    /**
     * 
     * @param form
     * @return
     */
   public static DefaultTreeModel OWC2Tree(String fileUrl) {

        Context model = null;
        FacesContext context = FacesContext.getCurrentInstance();

        //First we get the OWC model
        try {
            ServletContext sc = (ServletContext) context.getExternalContext().getContext();
            model = (new XMLContextUtilities()).readContext(sc.getRealPath(fileUrl));
        } catch (JAXBException ex) {
            Logger.getLogger(Adapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Adapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultTreeModel tree = new DefaultTreeModel(null);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");

        DefaultMutableTreeNode contextOwc = new DefaultMutableTreeNode(model);
        

        int id = 0;
        int depth = 1;
        //Then we get a list layers to construct the tree
        List<Layer> listLayers = model.getLayers();
        for (Layer layer : listLayers) {
            id++;
            TreeNodeModel item = new TreeNodeModel(layer, id, depth, id, false);
            contextOwc.add(item);
        }

        root.add(contextOwc);
        tree.setRoot(root);
        return tree;
    }

    /**
     * 
     * @param form
     * @return
     */
    public static DefaultTreeModel context2Tree(FacesContext context, Context model) {

        DefaultTreeModel tree = new DefaultTreeModel(null);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        TreeItem treeItemRoot = new TreeItem(model);
        DefaultMutableTreeNode contextOwc = new DefaultMutableTreeNode(treeItemRoot);
        root.add(contextOwc);

        int id = 0;
        int depth = 1;
        //Then we get a list layers to construct the tree
        List<Layer> listLayers = model.getLayers();
        for (Layer layer : listLayers) {
            id++;
            TreeItem treeItem = new TreeItem(layer);
            TreeNodeModel item = new TreeNodeModel(treeItem, id, depth, id, false);
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
