
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>

<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<f:view>

    <head>

    </head>

    <body>
    <h:form id="main_form">

        <mf:Div id="treetableDiv" style="margin:0pt auto;height:300px;overflow:auto;margin-top:10pt;width:600px;">
            <mf:TreeTable id="treetable" collapsed="true" collapseDepth="1" value="#{map.exampleModel}" var="itemExample" style="width:100%;height:100%;overflow:auto;" debug="false">
                <mf:TreePanel id="treepanel"
               header="false"
               rowId="false"
               frame="false"
               showRoot="false"
               enableDragDrop="false"
               styleLeaf="background:#f3fbff;"
               styleNode="background:#d6e3f2;"
               loadAll="true"
               debug="false"
               >

                    <mf:TreeColumn  id="tree_column"
                     headerTitle="Equipment"
                     value="#{itemExample.name}"
                     style="width:200px;"
                     styleHeader="width:200px;">
                    </mf:TreeColumn>
                    <mf:Column id="readrightsRoleCheckbox" style="width:50px;overflow-x:auto;" styleHeader="width:50px" headerTitle="R">
                        <h:selectBooleanCheckbox id="readrightchecboxInput" value="true" />
                    </mf:Column>
                    <mf:Column id="writerightsRoleCheckbox" style="width:50px;overflow-x:auto;" styleHeader="width:50px" headerTitle="W">
                        <h:selectBooleanCheckbox id="writerightchecboxInput" value="true" />
                    </mf:Column>


                </mf:TreePanel>
            </mf:TreeTable>
        </mf:Div>

        <a4j:commandButton reRender="treetableDiv" ajaxSingle="true" value="div"/>
        <a4j:commandButton reRender="treetable" ajaxSingle="true" value="treetable"/>
        <a4j:commandButton reRender="treepanel" ajaxSingle="true" value="treepanel"/>

    </h:form>
    <body>
</f:view>