/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mapfaces.util;

import java.io.FileReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * @TODO this class will Unmarshalles the given XML Document from multiple types.
 * 
 * @author Mehdi Sidhoum
 */

public class XML2Java {
    
    /**
     * The main method.
     * @param args the path to file to read.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        
        //String fileName = "GetCapabilities.xml";
        //String fileName = "GetResult.xml";
        //String fileName = "GetObservation.xml";
        //String fileName = "DescribeSensor.xml";
        String fileName = "registerSensor.xml";
        //String fileName = "InsertObservation.xml";
        //String fileName = "getObservation3.xml";
        //String fileName = "SOSCapabilities1.0.0.xml";
         // String fileName = "csw.xml";  
        // Unmarshalles the given XML file to objects
        JAXBContext context;
        /*context = JAXBContext.newInstance(MetaDataImpl.class, Capabilities.class, DescribeRecordType.class
                        ,DistributedSearchType.class, ElementSetNameType.class, ElementSetType.class
                        ,GetCapabilities.class, GetDomainType.class, GetRecordByIdType.class
                        ,GetRecordsType.class, HarvestType.class, QueryConstraintType.class
                        ,QueryType.class, ResultType.class, TransactionType.class
                        ,GetRecordsResponseType.class, GetRecordByIdResponseType.class
                        ,DescribeRecordResponseType.class, GetDomainResponseType.class
                        ,TransactionResponseType.class, HarvestResponseType.class
                        ,ExceptionReport.class, org.constellation.ows.v110.ExceptionReport.class
                        ,ObjectFactory.class);*/
        context = JAXBContext.newInstance("org.constellation.sos:org.constellation.gml:org.constellation.swe:org.constellation.gml:org.constellation.observation");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object request =  unmarshaller.unmarshal(new FileReader(fileName));
        
        System.out.println(request.toString());
        
        /*if(request instanceof GetCapabilities ){
            GetCapabilities gc = (GetCapabilities) request;
            
            AcceptVersionsType av = gc.getAcceptVersions();
            List<String> avs      = av.getVersion();
            System.out.println("Accepted version :");
            for(String s : avs){
                System.out.println(s);
            }
            
            SectionsType section  = gc.getSections();
            List<String> sections = section.getSection();
            System.out.println("sections :");
            for(String s : sections){
                System.out.println(s);
            }
            
            AcceptFormatsType format = gc.getAcceptFormats();
            System.out.println("formats accept�s :");
            List<String> formats = format.getOutputFormat();
            for(String s : formats){
                System.out.println(s);
            }
        } else 
        
        if(request instanceof GetResult ){
            GetResult gr = (GetResult) request;
            
            System.out.println(gr.getObservationTemplateId());
            
            List<EventTime> times = gr.getEventTime();
            
            for(EventTime et : times){
            }
           /* TimeInstantType time = gr.getTimeInstant();
            TimePositionType tp = time.getTimePosition();
            System.out.println(tp.toString());
            
            System.out.println();
        } else 
        
        if(request instanceof GetObservation ){
            GetObservation go = (GetObservation) request;
            
            System.out.println("offering: " + go.getOffering());
            
            System.out.println("observedProperty: ");
            List<String> ops = go.getObservedProperty();
            for(String s : ops){
                System.out.println(s);
            }
           
            System.out.println("responseFormat: " + go.getResponseFormat());
            
            System.out.println("resultModel: " + go.getResultModel());
            
            System.out.println("responseMode: " + go.getResponseMode());
            
            GetObservation.FeatureOfInterest foi = go.getFeatureOfInterest() ;
            System.out.println(foi.getBBOX().getEnvelope().getLowerCorner().toString());
            
            System.out.println();
        } else 
          
        if (request instanceof RegisterSensor) {
            RegisterSensor ds = (RegisterSensor) request;
            SensorDescription process = ds.getSensorDescription();
            System.out.println("SensorDescription : " +  process);
            ObservationEntry obs = ds.getObservationTemplate().getObservation();
            
            System.out.println("class: " + obs.getResult().getClass().getSimpleName() + " toString:" + obs.getResult());
        } else 
        
        if (request instanceof InsertObservation) {
            InsertObservation io = (InsertObservation) request;
            
            System.out.println("insertId=" + io.getAssignedSensorId());
            if(io.getObservation() != null)
                System.out.println("obs non null");
            else 
             System.out.println("obs null");
           
        } else {
            System.out.println("aucun bon type trouvé type = " + request.getClass().getSimpleName());
            if (request instanceof JAXBElement){
                JAXBElement jb = (JAXBElement) request;
                System.out.println("JAXBELEMENT class: " + jb.getDeclaredType().getSimpleName());
            }
        }*/
         
    }
}
