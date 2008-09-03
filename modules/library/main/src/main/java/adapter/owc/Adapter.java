package adapter.owc;

import bean.TimeLineBean;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.geotools.map.MapLayer;
import org.mapfaces.component.UIWidgetBase;
import org.mapfaces.component.models.UIContext;
import org.mapfaces.models.AbstractContext;
import org.mapfaces.models.Layer;
import org.mapfaces.models.OWC_v030;
import org.mapfaces.models.tree.TreeNodeModel;

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

        AbstractContext model = null;
        FacesContext context = FacesContext.getCurrentInstance();

        //First we get the OWC model
        try {
            JAXBContext Jcontext;
            Jcontext = JAXBContext.newInstance("net.opengis.owc.v030:net.opengis.context.v110");
            Unmarshaller unmarshaller = Jcontext.createUnmarshaller();
            ServletContext sc = (ServletContext) context.getExternalContext().getContext();
            JAXBElement elt = (JAXBElement) unmarshaller.unmarshal(new FileReader(sc.getRealPath(fileUrl)));
            model = new OWC_v030(elt.getValue());
        } catch (JAXBException ex) {
            Logger.getLogger(Adapter.class.getName()).log(Level.SEVERE, null, "JAXBException" + ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Adapter.class.getName()).log(Level.SEVERE, null, "FileNotFoundException" + ex);
        }

        DefaultTreeModel tree = new DefaultTreeModel(null);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");

        DefaultMutableTreeNode contextOwc = new DefaultMutableTreeNode(model);
        root.add(contextOwc);

        int id = 0;
        int depth = 1;
        //Then we get a list layers to construct the tree
        Layer[] listLayers = model.getLayers();
        for (Layer layer : listLayers) {
            id++;
            TreeNodeModel item = new TreeNodeModel(layer.getMapLayer(), id, depth, id, false);
            contextOwc.add(item);
        }

        tree.setRoot(root);
        return tree;
    }

    /**
     * 
     * @param form
     * @return
     */
    public static DefaultTreeModel abstractContext2Tree(FacesContext context, AbstractContext model) {

        DefaultTreeModel tree = new DefaultTreeModel(null);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");

        DefaultMutableTreeNode contextOwc = new DefaultMutableTreeNode(model);
        root.add(contextOwc);

        int id = 0;
        int depth = 1;
        //Then we get a list layers to construct the tree
        Layer[] listLayers = model.getLayers();
        for (Layer layer : listLayers) {
            System.out.println("ID :" + id);
            id++;
            TreeNodeModel item = new TreeNodeModel(layer, id, depth, id, false);
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
