<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<%@ taglib uri="http://mapfaces.org/taglib" prefix="mf"%>
<%@ taglib uri="http://mapfaces.org/taglib-models" prefix="mf-model"%>


<f:view>
    
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSF Page</title>
        </head>
        
        <body>
            <h:form id="metadataform">
                
                
                <mf:TreeBuilder id="treeTable_md" value="#{treebean.tree}" var="treeItem" style="width:100%;">
                    <mf:BuilderPanel 	header="true" 
                                      id="panel1" 
                                      title="Template"
                                      rowId="true"
                                      frame="false"
                                      showRoot="false"
                                      enableDragDrop="false" 
                                      styleOdd="background:#f3fbff;"
                                      styleEven="background:#d6e3f2;" 
                                      loadAll="false" 
                                      debug="false"
                                      styleClass="x-tree-builder x-tree-builder-left"
                                      target="panel2">
                        
                        <mf:BuilderToolbar id="toolbar">
                            <h:commandButton value="Test" />
                            <h:commandButton value="Test" />
                            <h:commandButton value="Test" />
                            <h:commandButton value="Test" />
                            <h:commandButton value="Test" />
                        </mf:BuilderToolbar>
                        
                        <mf:BuilderCheckColumn id="chcol" width="25" value="false"/>
                        
                        <mf:BuilderTreeColumn id="btcol" headerTitle="Tree Items" value="#{treeItem.name}" style="width:40%;" styleHeader="width:40%;"/>
                        
                    </mf:BuilderPanel>
                    <mf:BuilderPanel 	header="true" 
                                      id="panel2" 
                                      title="Tree build" 
                                      frame="false" 
                                      showRoot="false" 
                                      enableDragDrop="false" 
                                      loadAll="false" 
                                      debug="false"
                                      styleClass="x-tree-builder x-tree-builder-right" 
                                      cloneView="true">
                        
                        <mf:BuilderToolbar id="toolbar">
                            <h:commandButton value="Test" />
                            <h:commandButton value="Test" />
                            <h:commandButton value="Test" />
                            <h:commandButton value="Test" />
                            <h:commandButton value="Test" />
                        </mf:BuilderToolbar>
                        
                        <mf:BuilderTreeColumn id="btcol2" headerTitle="Tree Items" value="#{treeItem.name}" style="width:40%;" styleHeader="width:40%;"/>
                        
                    </mf:BuilderPanel>                  
                </mf:TreeBuilder>
            </h:form>
        </body>
    </html>
</f:view>