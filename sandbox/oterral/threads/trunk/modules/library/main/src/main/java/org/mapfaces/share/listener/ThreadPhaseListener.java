/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.share.listener;

/**
 *
 * @author olivier
 */

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseListener;
import javax.faces.context.FacesContext;
import org.ajax4jsf.ajax.html.AjaxPoll;
import org.mapfaces.util.FacesUtils;


public class ThreadPhaseListener implements PhaseListener {

    private static final String PHASE_PARAM ="org.mapfaces.share.listener.ThreadPhaseListener";
    private static final Logger logger = Logger.getLogger("org.exadel.helper");
    private static String cphase = null;    
    private static ConcurrentHashMap<String, String> POOL = new ConcurrentHashMap<String, String>();
    private static String pollId;
    private static String pollClientId;
    
    private boolean reRenderHasBegun = false;
    private int nbAjaxPollRequest = 0;
    private static int nbLayers = 0;
    private int nbLayersReRendered = 0;
    
    public static int getNbLayers() {
        return nbLayers;
    }
    public static void setNbLayers(int i) {
        nbLayers = i;
    }

    
    public static void setPollClientId(String clientId) {
        pollClientId = clientId;
    }
    
    public static void setImgUrl(String id, String b) {
        System.out.println("%%%%%%%%%%%%%%%%%%ù "+b);
        String oldValue = POOL.putIfAbsent(id, b);
        if(oldValue != null && !oldValue.equals(b)){
            POOL.remove(id,oldValue);
            POOL.put(id, b);
        }
          System.out.println("%%%%%%%%%%%%%%%%%%ù "+POOL.get(id));
    }

    public static void setPollId(String id) {
       pollId = id;
    }

    public void setPhase(String newValue) { cphase = newValue; }
   
   
    public PhaseId getPhaseId() {
        PhaseId phaseId = PhaseId.RENDER_RESPONSE;
          return phaseId;
    }
    
    public void beforePhase(PhaseEvent e) {
        boolean isAjaxPollRequest = false;
        FacesContext context = FacesContext.getCurrentInstance();        
        if (context.getExternalContext().getRequestParameterMap() != null) {
            Map params = context.getExternalContext().getRequestParameterMap();
            System.out.println(pollClientId);
            System.out.println(params);
            if(pollClientId != null && params.get(pollClientId) != null){
                isAjaxPollRequest = true;
            }
        }
        //If an AjaxPollRequest has been send 
        if(isAjaxPollRequest){
            
            System.out.println("######################  isAjaxPollRequest");
            AjaxPoll pollComp = null;
            
            //If the AjaxPoll component exist, it must be activate
            if(pollId != null){
                pollComp =(AjaxPoll) FacesUtils.findComponentById(context, context.getViewRoot(), pollId);
                if(pollComp != null){                    
                        pollComp.setEnabled(true);
                }
            }
            nbAjaxPollRequest++;
            
            //If the AjaxSupportRequests are finished (All  threads are send to the portrayal)
            if(reRenderHasBegun || POOL.size()==nbLayers){
                if(!POOL.isEmpty()){
                    reRenderHasBegun = true;
                    System.out.println("##############  Le POOL  n'est pas empty "+POOL.size()+" nbLayerqsRendered = "+nbLayersReRendered);
                    String imgIdsToReRender = pollId;
                    Set set = POOL.entrySet();
                    Iterator iterator = set.iterator();
                    while(iterator.hasNext()){
                        Entry entry = (Entry) iterator.next();
                        if(!entry.getValue().equals("noImage")){
                            System.out.println("##############  Une nouvelle image àété généré par le thread "+ (String) entry.getKey()+ " "+entry.getValue()+" "+POOL.size());
                            HtmlGraphicImage comp =(HtmlGraphicImage) FacesUtils.findComponentById(context, context.getViewRoot(), (String)entry.getKey());
                            if(comp != null){
                                imgIdsToReRender=imgIdsToReRender+","+(String) entry.getKey();
                                ((HtmlGraphicImage)  comp).setUrl((String) entry.getValue()); 
                                nbLayersReRendered++;
                                nbAjaxPollRequest = 0;            
                                POOL.remove(entry.getKey(),entry.getValue());
                                System.out.println("############## Le composant HtmlGraphicImage n'est pas null "+ (String) entry.getKey()+ " "+entry.getValue()+" "+POOL.size());
                            }else{
                                System.out.println("############## Le composant HtmlGraphicImage est null "+ (String) entry.getKey()+ " "+entry.getValue()+" "+POOL.size());
                            }
                        }
                    }
                    System.out.println("REFRESH : POOL.size = "+POOL.size()+"imgIdsToReRender = "+imgIdsToReRender+", reRenderHasBegun = "+reRenderHasBegun+", nbLayers = "+nbLayers+",nbLayersReRendered = "+nbLayersReRendered );

                    if(pollComp != null){                    
                        pollComp.setReRender(imgIdsToReRender);
                    }

                }else{

                    if(pollComp != null && reRenderHasBegun && nbLayers == nbLayersReRendered){
                        System.out.println("reRenderHasBegun = "+reRenderHasBegun+", nbLayers = "+nbLayers+",nbLayersReRendered = "+nbLayersReRendered);
                        nbAjaxPollRequest = 0;
                        nbLayersReRendered = 0;
                        reRenderHasBegun = false;
                        pollComp.setEnabled(false);
                    }

                    System.out.println("############## J'arrete le poll"+POOL.size() + " "+ pollComp.getReRender());
                }
            }
            
            
            //Stop AjaxPollRequest when It doesn't stop or when it bugs.
            System.out.println("############## J'arrete  le POOL  ? reRenderHasBegun = "+reRenderHasBegun+", nbLayers = "+nbLayers+",nbLayersReRendered = "+nbLayersReRendered+" "+nbAjaxPollRequest);
            if(nbAjaxPollRequest>15 || nbLayersReRendered > nbLayers){
                System.out.println("############## J'arrete et je vide le POOL reRenderHasBegun = "+reRenderHasBegun+", nbLayers = "+nbLayers+",nbLayersReRendered = "+nbLayersReRendered);

                nbAjaxPollRequest = 0;
                nbLayersReRendered = 0;
                reRenderHasBegun = false;
                pollComp.setEnabled(false);
                Set set = POOL.entrySet();
                Iterator iterator = set.iterator();
                while(iterator.hasNext()){
                    Entry entry = (Entry) iterator.next();
                    POOL.remove(entry.getKey());
                }
                System.out.println("############## J'arrete et je vide le POOL size = "+POOL.size()+", nbLayers = "+nbLayers+",nbLayersReRendered = "+nbLayersReRendered);

            }
        }
    }
    public void afterPhase(PhaseEvent e) {
    }
   
}


