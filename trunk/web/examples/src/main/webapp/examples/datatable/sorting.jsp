<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf-base" uri="http://mapfaces.org/taglib-base"%>
<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>MapFaces Datatable sorting demo</title>
            <style>
                .selectedPager{color: red;background: cyan;}
            </style>
        </head>
        <body>
            <h1><h:outputText value="MapFaces Datatable sorting demo"/></h1>
            <h:form id="mainform">
                <mf-base:Datatable id="mfdatatable"
                                   value="#{datatablebean.allContactsModel}"
                                   var="_row"
                                   style="text-align:center;" sortable="true" rules="all" rows="10">
                    <mf-base:DataTableColumn axis="number">
                        <f:facet name="header"><h:outputText value="ID"/></f:facet>
                        <h:outputText value="#{_row.doubleValue}" style="text-align:center;"/>
                        <f:facet name="footer"><h:outputText value=""/></f:facet>
                    </mf-base:DataTableColumn>

                    <mf-base:DataTableColumn axis="string">
                        <f:facet name="header"><h:outputText value="name"/></f:facet>
                        <h:outputText value="#{_row.value}" style="text-align:center;"/>
                        <f:facet name="footer"><h:outputText value=""/></f:facet>
                    </mf-base:DataTableColumn>
                </mf-base:Datatable>
                <mf-base:DataScroller dataTableId="mfdatatable"
                                      id="datascrollerpager"
                                      selectedStyleClass="selectedPager"
                                      showpages="5"
                                      style="margin:0pt auto;text-align:center;"
                                      styleClass="dtscroller"/>
                                                                   

                <hr/>
                <a4j:commandLink value="a4jCommandLink   datascrollerpager_div" reRender="datascrollerpager_div"/><br/>
                <a4j:commandLink value="a4jCommandLink   datascrollerpager" reRender="datascrollerpager"/><br/>
                <a4j:commandLink value="a4jCommandLink   mfdatatable" reRender="mfdatatable"/><br/>
                <h:commandButton value="Submit"/><br/>

                <h1><h:outputText value="Mojarra default datatable "/></h1>
                <h:dataTable id="jsfdatatable"
                             value="#{datatablebean.allContactsModel}"
                             var="_row2"
                             style="text-align:center;" rules="all" rows="4">
                    <h:column>
                        <f:facet name="header"><h:outputText value="ID"/></f:facet>
                        <h:outputText value="#{_row2.doubleValue}" style="text-align:center;"/>
                        <f:facet name="footer"><h:outputText value=""/></f:facet>
                    </h:column>

                    <h:column>
                        <f:facet name="header"><h:outputText value="name"/></f:facet>
                        <h:outputText value="#{_row2.value}" style="text-align:center;"/>
                        <f:facet name="footer"><h:outputText value=""/></f:facet>
                    </h:column>
                </h:dataTable>
                <%/*mf-base:DataScroller dataTableId="jsfdatatable"
        id="datascrollerpager2"
        selectedStyleClass="selectedPager"
        showpages="3"
        style="margin:0pt auto;text-align:center;"
        styleClass="dtscroller"/*/%>
            </h:form>
        </body>
    </html>
</f:view>
