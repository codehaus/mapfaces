
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<f:view>

    <head>

    </head>

    <body>
    <h:form id="main_form">
        <a4j:region id="stat1">

            <mf:Div id="treetableDiv" style="margin:0pt auto;height:300px;overflow:auto;margin-top:10pt;width:600px;">
                <mf:TreeTable id="treetable" value="#{map.exampleModel}" var="itemExample" style="width:100%;height:100%;overflow:auto;" debug="false">
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
                        <mf:TreeLayoutCheckColumn id="readrightsRoleCheckbox" value="#{itemExample.userObject.read}" style="width:50px;overflow-x:auto;" headerTitle="R">
                        </mf:TreeLayoutCheckColumn>
                        <mf:TreeLayoutCheckColumn id="writerightsRoleCheckbox" value="#{itemExample.userObject.write}" style="width:50px;overflow-x:auto;" headerTitle="W">
                        </mf:TreeLayoutCheckColumn>


                    </mf:TreePanel>
                </mf:TreeTable>


            </mf:Div>

            <br/>
            <a4j:commandLink id="submitter" reRender="treetableDiv,repeaterDiv" value="Submit" />

            <mf:Div id="repeaterDiv" style="margin:0pt auto;">
                <a4j:repeat id="a4jrepeat" value="#{map.rowList}" var="rowTree">
                    <h:outputText value="#{rowTree}"/>
                    <br/>
                </a4j:repeat>
            </mf:Div>

        </a4j:region>


    </h:form>

    <a4j:status for="main_form:stat1" forceId="true" id="ajaxStatus">
        <f:facet name="start">
            <mf:Div id="panelloadingstatus">
                <div id="loadinghide" style="height:300px;width:100%;background:transparent;position:absolute;top:0px;left:0px;">
                </div>
                <h:graphicImage id="loading_img" value="http://www.spigo.fr/_gfx/_ikoner/loader.gif" style="position:fixed;top:50%;left:50%;z-index:1001;" />
                <div id="loading-modal">
                </div>
            </mf:Div>
        </f:facet>
    </a4j:status>
    <body>
</f:view>