
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<f:view>

    <head>

    </head>

    <body>
    <h:form id="main_form">

        <mf:Div id="treetableDiv" style="margin:0pt auto;height:300px;overflow:auto;margin-top:10pt;width:600px;">
            <mf:TreeTable id="treetable" value="#{map.exampleModel}" var="itemExample" style="width:100%;height:100%;overflow:auto;" debug="false" collapsed="true" collapseDepth="1">
                <mf:TreePanel id="treepanel"
               header="false"
               rowId="false"
               frame="false"
               showRoot="false"
               enableDragDrop="false"
               styleLeaf="background:#f3fbff;"
               styleNode="background:#d6e3f2;"
               loadAll="true"
               debug="false">

                    <mf:TreeColumn  id="tree_column"
                     headerTitle="Equipment"
                     value="#{itemExample.name}"
                     style="width:200px;"
                     styleHeader="width:200px;">
                    </mf:TreeColumn>
                    <mf:TreeLayoutCheckColumn id="readrightsRoleCheckbox" value="#{itemExample.read}" style="width:50px;overflow-x:auto;" headerTitle="R">
                    </mf:TreeLayoutCheckColumn>
                    <mf:TreeLayoutCheckColumn id="writerightsRoleCheckbox" value="#{itemExample.write}" style="width:50px;overflow-x:auto;" headerTitle="W">
                    </mf:TreeLayoutCheckColumn>


                </mf:TreePanel>
            </mf:TreeTable>
        </mf:Div>



    </h:form>
    <body>
</f:view>