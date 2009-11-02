<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf-base" uri="http://mapfaces.org/taglib-base"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>MapFaces Datatable sorting demo</title>
        </head>
        <body>
            <h1><h:outputText value="MapFaces Datatable sorting demo"/></h1>
            <h:form id="mainform">
                <mf-base:Datatable id="mfdatatable"
                              value="#{datatablebean.allContactsModel}"
                              var="_row"
                              style="text-align:center;" sortable="true" rules="all" >
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


                <hr/>

                <h:dataTable id="jsfdatatable"
                             value="#{datatablebean.allContactsModel}"
                             var="_row2"
                             style="text-align:center;" rules="all">
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
            </h:form>
        </body>
    </html>
</f:view>



