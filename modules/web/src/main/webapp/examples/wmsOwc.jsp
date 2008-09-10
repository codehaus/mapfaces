<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>
<%@taglib prefix="mf-model" uri="http://mapfaces.org/taglib-models"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <f:view>
       <html>
           <head>
               <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
               
               
           </head>
           <body>   
                <h1><h:outputText value="MapFaces" /></h1>                
               <br/>
               <h:form id="idDuForm">
               <h:outputText   value="Modify map title :"/>                    
               <h:inputText id="title" value="World map">
                    <a4j:support event="onkeyup" reRender="abstract"/> 
               </h:inputText>
               <br/>
               <h:outputText   value="Zoom to :"/>
               <h:selectOneMenu id="bbox">
                    <f:selectItem itemLabel="world" itemValue="-180,-90,180,90"></f:selectItem>
                    <f:selectItem itemLabel="North West" itemValue="-180,0,0,90"></f:selectItem>
                    <f:selectItem itemLabel="North East" itemValue="0,0,180,90"></f:selectItem>
                    <f:selectItem itemLabel="South East" itemValue="0,-90,180,0"></f:selectItem>
                       <f:selectItem itemLabel="South West" itemValue="-180,-90,0,0"></f:selectItem>
                    <a4j:support event="onchange" reRender="mappane"/> 
               </h:selectOneMenu>
               <br/>
                <mf-model:Context id="owsContext" service="data/context/owc030Cut.xml">
                   <mf:Abstract id="abstract" styleClass="abstract"></mf:Abstract>
                   <mf:MapPane id="mappane" styleClass="mappane" navigation="true" debug="true"></mf:MapPane>                   
               </mf-model:Context>
           </h:form>
          </body>
       </html>
   </f:view>