/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.util;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.io.File;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.FacesContext;
import org.geotools.display.service.DefaultPortrayalService;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.DefaultMapContext;
import org.mapfaces.component.UILayer;
import org.mapfaces.renderkit.html.LayerRenderer;
import org.mapfaces.share.listener.ThreadPhaseListener;

/**
 *
 * @author olivier
 */
public class portrayThread  extends Thread{
    public DefaultPortrayalService portray;
    public DefaultMapContext defaultMapContext;
    public ReferencedEnvelope env;
    public File dst;
    public String outputFormat;
    public Dimension dimension;
    public boolean b;
    public FacesContext context;
    public UIComponent comp; 
    
    public portrayThread(FacesContext ctx, UIComponent comp, DefaultPortrayalService portray, DefaultMapContext defaultMapContext, ReferencedEnvelope env, File dst, String outputFormat, Dimension dimension, boolean b) {
        this.context = ctx;
        this.comp = comp;
        this.portray = portray;
        this.defaultMapContext = defaultMapContext;
        this.env = env;
        this.dst = dst;
        this.outputFormat = outputFormat;
        this.dimension = dimension;
        this.b = b;
    }
    
    @Override
    public void run() {  

        HtmlGraphicImage imgComp=(HtmlGraphicImage)  comp.getChildren().get(0);
        try {
            Date testBegin = new Date();
            this.portray.portray(defaultMapContext, env, dst, outputFormat, dimension, b);
            Date testEnd = new Date();
            Long timeout = testEnd.getTime() - testBegin.getTime();
            System.out.println("Durée d'execution du portrayal :"+ timeout);
            ThreadPhaseListener.setImgUrl(imgComp.getId(),"/" + ((UILayer) comp).getDir().getName() + "/" + dst.getName());  
        } catch (Exception e) {                                        
            try {                                            
                Exception exp = e;
                Date begin = new Date();
                Robot robocop = new Robot();
                while (exp != null) {
                    try {                                    
                        robocop.delay(1000);
                        this.portray.portray(defaultMapContext, env, dst, outputFormat, dimension, b);
                        exp = null;
                        //ThreadPhaseListener.setPollEnabled(((AjaxPoll)  comp.getChildren().get(0)).getId(),false);
                        ThreadPhaseListener.setImgUrl(imgComp.getId(),"/" + ((UILayer) comp).getDir().getName() + "/" + dst.getName());
                    } catch (Exception exception) {
                        exp = exception;
                        ThreadPhaseListener.setImgUrl(imgComp.getId(),"/resource.jsf?r=/org/mapfaces/resources/img/Spacer.gif");
                    }
                    Date end = new Date();
                    Long timeout = end.getTime() - begin.getTime();
                    if (timeout > 10000) {
                        ThreadPhaseListener.setImgUrl(imgComp.getId(),"/resource.jsf?r=/org/mapfaces/resources/img/Spacer.gif");
                        break;
                    }
                }
             }catch (AWTException ex) {
                 ThreadPhaseListener.setImgUrl(imgComp.getId(),"/resource.jsf?r=/org/mapfaces/resources/img/Spacer.gif");
                 Logger.getLogger(LayerRenderer.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }  
}
