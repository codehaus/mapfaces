<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <f:view>
            <h:form>
                <mf:TreeLayoutTable value="#{treebean.tree}" var="layer" id="Treetable" width="700">
                    <mf:TabPanel width="700" >
                        <mf:TabItem title="Tree Vue 1" >
                            Vue 1 de la treetable...
                            <mf:TreeLayoutPanel header="false" id="panel1" rowId="true" >
                                
                                <mf:TreeLayoutTColumn header="Tree Items" width="300" value="#{layer.name}"/>

                                <mf:TreeLayoutCheckColumn
                                    icon="/org/mapfaces/resources/treetable/images/default/layout/stuck.gif" 
                                    id="visible" 
                                    value="#{!layer.hidden}"
                                    width="40"/>
                                
                                <mf:TreeLayoutCheckColumn 
                                    icon="/org/mapfaces/resources/treetable/images/default/dd/drop-yes.gif" 
                                    id="edit" 
                                    value="#{layer.edit}"
                                    width="30"/>
                                
                                <mf:TreeLayoutCheckColumn 
                                    icon="/org/mapfaces/resources/treetable/images/default/grid/hmenu-lock.gif" 
                                    id="lock" 
                                    value="#{layer.lock}"
                                    width="30"/>
                                
                                <mf:TreeLayoutCheckColumn 
                                    icon="/org/mapfaces/resources/treetable/images/default/grid/group-by.gif" 
                                    id="group" 
                                    value="#{layer.group}"
                                    width="30"/>
                                
                                <mf:TreeLayoutNodeInfo header="title" >
                                    <h:outputLabel value="TITLE : #{layer.title}"/>
                                    <h:outputLabel value="ID : #{layer.id}"/>
                                    <h:outputLabel value="Group ID : #{layer.groupId}"/>
                                </mf:TreeLayoutNodeInfo>
                                
                            </mf:TreeLayoutPanel>
                        </mf:TabItem>
                        <mf:TabItem title="Tree Vue 2">
                            Vue 2 de la treetable...
                            <mf:TreeLayoutPanel header="false" id="panel2" rowId="true" >
                                
                                <mf:TreeLayoutTColumn header="Tree Items" width="300" value="#{layer.name}"/>
                                
                                <mf:TreeLayoutCheckColumn
                                    icon="/org/mapfaces/resources/treetable/images/default/layout/stuck.gif" 
                                    id="visible2" 
                                    value="#{layer.visible}"
                                    width="40"/>
                                
                                <mf:TreeLayoutNodeInfo header="title" >
                                    <h:outputLabel value="TITLE : #{layer.title}"/>
                                    <h:outputLabel value="ID : #{layer.id}"/>
                                    <h:outputLabel value="Group ID : #{layer.groupId}"/>
                                </mf:TreeLayoutNodeInfo>
                                
                            </mf:TreeLayoutPanel>
                        </mf:TabItem>
                    </mf:TabPanel>
                </mf:TreeLayoutTable>
            </h:form>
        </f:view>
    </body>
</html>
