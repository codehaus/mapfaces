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
        <title>treeLayout Page</title>
    </head>
    <body>
        <f:view>
            <h:form>
                <mf:TreeLayoutTable value="#{treebean.tree}" minifyJS="false" var="layer" id="Treetable" width="450" style="background-color:#fff;">
                    <%--mf:TabPanel width="700">
                        <mf:TabItem title="Tree Vue 1" --%>
                            <mf:TreeLayoutPanel header="true" id="panel1" rowId="true" title="Vue 1 de la treetable..."  showRoot="true" >
                                
                                <mf:TreeLayoutTColumn headerTitle="Tree Items" width="200"  value="#{layer.title}"/>

                                <mf:TreeLayoutCheckColumn
                                    headerIcon="/org/mapfaces/resources/tree/images/default/layout/stuck.gif" 
                                    id="visible" 
                                    value="#{layer.hidden}"
                                    width="40" />
                                
                                <mf:TreeLayoutCheckColumn 
                                    headerIcon="/org/mapfaces/resources/tree/images/default/dd/drop-yes.gif" 
                                    id="edit" 
                                    value="#{layer.edit}"
                                    width="30"/>
                                
                                <mf:TreeLayoutCheckColumn 
                                    headerIcon="/org/mapfaces/resources/tree/images/default/grid/hmenu-lock.gif" 
                                    id="lock" 
                                    value="#{layer.lock}"
                                    width="30"/>
                                
                                <mf:TreeLayoutCheckColumn 
                                    headerIcon="/org/mapfaces/resources/tree/images/default/grid/group-by.gif" 
                                    id="group" 
                                    value="#{layer.group}"
                                    width="30"/>
                                
                                <mf:TreeLayoutNodeInfo title="title" >
                                    <h:outputLabel value="TITLE : #{layer.title}"/>
                                    <h:outputLabel value="ID : #{layer.id}"/>
                                    <h:outputLabel value="Group ID : #{layer.groupId}"/>
                                </mf:TreeLayoutNodeInfo>
                                
                            </mf:TreeLayoutPanel>
                        <%--/mf:TabItem>
                        <mf:TabItem title="Tree Vue 2">
                            <mf:TreeLayoutPanel header="true" id="panel2" rowId="true" title="Vue 1 de la treetable..." >
                                
                                <mf:TreeLayoutTColumn headerTitle="Tree Items" width="300" value="#{layer.name}"/>
                                
                                <mf:TreeLayoutCheckColumn
                                    headerIcon="/org/mapfaces/resources/tree/images/default/layout/stuck.gif" 
                                    id="visible2" 
                                    value="#{layer.visible}"
                                    width="40"/>
                                
                                <mf:TreeLayoutNodeInfo title="title" >
                                    <h:outputLabel value="TITLE : #{layer.title}"/>
                                    <h:outputLabel value="ID : #{layer.id}"/>
                                    <h:outputLabel value="Group ID : #{layer.groupId}"/>
                                </mf:TreeLayoutNodeInfo>
                                
                            </mf:TreeLayoutPanel>
                        </mf:TabItem>
                    </mf:TabPanel--%>
                </mf:TreeLayoutTable>
            </h:form>
        </f:view>
    </body>
</html>
