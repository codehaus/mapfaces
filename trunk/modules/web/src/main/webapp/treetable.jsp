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
                <mf:TreeTable value="#{treebean.tree}" var="layer" id="Treetable" width="500">
                <mf:TreePanel header="true" id="panel1" title="My tree" rowId="true" >
                        
                        <mf:TreeColumn header="Tree Items" width="300" value="#{layer.name}"/>
                        
                        <mf:CheckColumn
                            icon="/org/mapfaces/resources/treetable/images/default/layout/stuck.gif" 
                            id="visible" 
                            value="#{layer.hidden}"
                            width="30"/>
                        
                        <mf:CheckColumn 
                            icon="/org/mapfaces/resources/treetable/images/default/dd/drop-yes.gif" 
                            id="edit" 
                            value="#{layer.edit}"
                            width="30"/>
                            
                        <mf:CheckColumn 
                            icon="/org/mapfaces/resources/treetable/images/default/grid/hmenu-lock.gif" 
                            id="lock" 
                            value="#{layer.lock}"
                            width="30"/>
                        
                        <mf:CheckColumn 
                            icon="/org/mapfaces/resources/treetable/images/default/grid/group-by.gif" 
                            id="group" 
                            value="#{layer.group}"
                            width="30"/>
                            
                            <mf:TreeNodeInfo header="title" >
                            <h:outputLabel value="TITLE : #{layer.title}"/>
                            <h:outputLabel value="TYPE : #{layer.type}"/>
                            <h:outputLabel value="ID : #{layer.id}"/>
                            <h:outputLabel value="Group ID : #{layer.groupId}"/>
                        </mf:TreeNodeInfo>
                        
                    </mf:TreePanel>
                    
                </mf:TreeTable>
            </h:form>
        </f:view>
    </body>
</html>
