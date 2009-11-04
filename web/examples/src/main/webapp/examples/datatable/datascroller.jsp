<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mfb" uri="http://mapfaces.org/taglib-base"%>
<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>MapFaces Datatable sorting demo</title>
            <style>
                .selectedPager{background:LightBlue none repeat scroll 0 0;color:white;font-weight:bold;font-size:11px;}
                .dtscroller{color:black;font-size:11px;}
            </style>
        </head>
        <body>
            <h1><h:outputText value="MapFaces Datatable sorting by Mootools demo"/></h1>
            <h:form id="mainform">
		<h:panelGroup layout="block" style="width:600px;margin-left:60pt;">
		        <mfb:Datatable id="mfdatatable"
		                       value="#{datatablebean.allContactsModel}"
		                       var="_row"
		                       style="text-align:center;" sortable="true" rules="all" rows="10">
		            <mfb:DataTableColumn axis="number">
		                <f:facet name="header"><h:outputText value="ID"/></f:facet>
		                <h:outputText value="#{_row.doubleValue}" style="text-align:center;"/>
		                <f:facet name="footer"><h:outputText value=""/></f:facet>
		            </mfb:DataTableColumn>

		            <mfb:DataTableColumn axis="string">
		                <f:facet name="header"><h:outputText value="name"/></f:facet>
		                <h:outputText value="#{_row.value}" style="text-align:center;"/>
		                <f:facet name="footer"><h:outputText value=""/></f:facet>
		            </mfb:DataTableColumn>
		        </mfb:Datatable>
		        <mfb:DataScroller dataTableId="mfdatatable"
		                          id="datascrollerpager"
		                          selectedStyleClass="selectedPager"
		                          showpages="5"
		                          style="margin:0pt auto;text-align:center;"
		                          styleClass="dtscroller">
		        </mfb:DataScroller>
		</h:panelGroup>

                <hr/>

                <h1><h:outputText value="Mojarra default datatable "/></h1>
		<h:panelGroup layout="block" style="width:400px;margin-left:60pt;">
		        <h:dataTable id="jsfdatatable"
		                     value="#{datatablebean.allContactsModel}"
		                     var="_row2"
		                     style="text-align:center;width:100%" rules="all" rows="4">
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
		        <mfb:DataScroller dataTableId="jsfdatatable"
		                          id="datascrollerpager2"
		                          selectedStyleClass="selectedPager"
		                          showpages="5"
		                          style="margin:0pt auto;text-align:center;"
		                          styleClass="dtscroller">
		        </mfb:DataScroller>
		</h:panelGroup>
            </h:form>
        </body>
    </html>
</f:view>

