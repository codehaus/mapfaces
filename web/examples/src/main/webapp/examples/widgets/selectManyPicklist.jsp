<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mfb" uri="http://mapfaces.org/taglib-base"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head></head>
        <body>
            <h1>SelectManyPickList component demo</h1>
            <br/>
            <h:form id="mainform">
                <h:panelGrid columns="3" cellspacing="0" border="1" width="75%">

                    <h:outputText value="Invisible:"/>
                    <f:verbatim> </f:verbatim>
                    <h:outputText value="Visible:"/>

                    <h:selectManyListbox value="#{pickListBean.selectedInvisibleItems}" size="5" style="width: 300px;">
                        <f:selectItems value="#{pickListBean.invisibleItems}" />
                    </h:selectManyListbox>

                    <h:panelGrid columns="1">
                        <h:commandButton value=">" actionListener="#{pickListBean.moveSelectedToVisible}" style="width: 50px;" />
                        <h:commandButton value=">>" actionListener="#{pickListBean.moveAllToVisible}" style="width: 50px;" />
                        <h:commandButton value="<" actionListener="#{pickListBean.moveSelectedToInvisible}" style="width: 50px;" />
                        <h:commandButton value="<<" actionListener="#{pickListBean.moveAllToInvisible}" style="width: 50px;" />
                        <h:commandButton value="" type="button" onclick="add();" style="width: 50px;" />
                    </h:panelGrid>

                    <h:selectManyListbox id="poly_select" value="#{pickListBean.selectedVisibleItems}" size="5" style="width: 300px;">
                        <f:selectItems id="visibleItems" value="#{pickListBean.visibleItems}" />
                    </h:selectManyListbox>

                    
                </h:panelGrid>
                <br/>
                <mfb:selectManyPicklist   id="picklistMF"
                                            size="5"
                                            style="font-size:10px;margin-top:13px;"
                                            value="#{pickListBean.selectedItem}"
                                            ondblclick="mapfaces_picklist_addToSelected('mainform:picklistMF_AVAILABLE','mainform:picklistMF_SELECTED','mainform:picklistMF_HIDDEN')"
                                            addAllButtonStyle="display:none"
                                            removeAllButtonStyle="display:none"
                                            addButtonStyle="top:15px;position:relative;cursor:pointer;"
                                            removeButtonStyle="bottom:3px;position:relative;cursor:pointer;">

                        <f:selectItems value="#{pickListBean.servicesItems}" />
                </mfb:selectManyPicklist>
            </h:form>
        </body>
    </html>
</f:view>