<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://mapfaces.org/taglib" prefix="mf"%>
<%@ taglib uri="http://mapfaces.org/taglib-models" prefix="mf-model"%>

<%@ page import="javax.el.ValueExpression"%>
<%@ page import="javax.faces.context.FacesContext"%>


<f:view >
    <%--h:outputText id="outputid" value="param.id = #{param.id}" style="color:red;display:none;"/>
    <h:outputText id="outputid2" value="  this.id = #{metadatapopup.id}" style="color:red;display:none;"/>

    <t:saveState id="save2" value="#{metadatapopup.id}"/>
    <t:saveState id="save6" value="#{metadatapopup.url}"/>
    <t:saveState id="save3" value="#{metadatapopup.title}"/>
    <t:saveState id="save4" value="#{metadatapopup.treeModel}"--%>

    <html>
        <head>
            <title>Metadata</title>
        </head>
        <body bgcolor="#ececf9">

            <h:form id="metadataform">
                <h2>General Info</h2>
                <h:panelGrid columns="2" border="1">

                    url:<h:outputText id="logo-orga_md" value="#{metadata.url}"/>
                    fileIdentifier: <h:outputText id="logo-fileIdentifier" value="#{metadata.metaData.fileIdentifier}"/>
                    parentIdentifier: <h:outputText id="logo-parentIdentifier" value="#{metadata.metaData.parentIdentifier}"/>
                    datasetURI: <h:outputText id="logo-datasetURI" value="#{metadata.metaData.dataSetUri}"/>

                    mdStandardName: <h:outputText id="logo-metadataStandardName" value="#{metadata.metaData.metadataStandardName}"/>
                    mdStandardVersion: <h:outputText id="logo-metadataStandardVersion" value="#{metadata.metaData.metadataStandardVersion}"/>
                    dateStamp: <h:outputText id="logo-dateStamp" value="#{metadata.metaData.dateStamp}" >
                        <f:convertDateTime type="date" dateStyle="medium"/>
                    </h:outputText>
                    language: <h:outputText id="logo-language" value="#{metadata.metaData.language}"/>
                    characterSet: <h:outputText id="logo-characterSet" value="#{metadata.metaData.characterSet}"/>
                </h:panelGrid>
                <%--h:inputText value="hy" size="7">
                    <a4j:support action="#{metadata.change}"
                                 event="onchange" reRender="margin1" />
                </h:inputText--%>
                <%--h:outputText id="margin1" value="fgdf">
                </h:outputText--%>
                <h2>Contact Info</h2>

                <a4j:repeat value="#{metadata.metaData.contacts}" var="contact" binding="#{metadata.repeater}" ajaxKeys="#{metadata.keys}">
                    
                    <h:outputText id="margin" value="yh">
                    </h:outputText>
                    <h:panelGrid columns="2" border="1">
                        individualName: <h:outputText id="logo-individualName" value="#{contact.individualName}"/>
                        organisationName: <h:outputText id="logo-organisationName" value="#{contact.organisationName}"/>
                        positionName: <h:outputText id="logo-positionName" value="#{contact.positionName}"/>
                        role: <h:outputText id="logo-role" value="#{contact.role}"/>
                        contact info:
                        <h:panelGrid columns="2">
                            administrativeArea : <h:outputText id="logo-administrativeArea" value="#{contact.contactInfo.address.administrativeArea}"/>
                            deliveryPoints:
                            <a4j:repeat value="#{contact.contactInfo.address.deliveryPoints}" var="deliveryPoint">
                                <h:outputText id="logo-deliveryPoints" value="#{deliveryPoint}"/>
                            </a4j:repeat>
                            postalcode-city-country: <h:outputText id="logo-postalCode" value="#{contact.contactInfo.address.postalCode} #{contact.contactInfo.address.city} #{contact.contactInfo.address.country}"/>
                            phone:
                            <a4j:repeat value="#{contact.contactInfo.phone.voices}" var="voice">
                                <h:outputText id="logo-email" value="#{voice}"/>
                            </a4j:repeat>
                            fax:
                            <a4j:repeat value="#{contact.contactInfo.phone.facsimiles}" var="fax">
                                <h:outputText id="logo-email" value="#{fax}"/>
                            </a4j:repeat>
                            email:
                            <a4j:repeat value="#{contact.contactInfo.address.electronicMailAddresses}" var="email">
                                <h:outputText id="logo-email" value="#{email}"/>
                            </a4j:repeat>
                            hoursOfService: <h:outputText id="logo-hoursOfService" value="#{contact.contactInfo.hoursOfService}"/>
                            contactInstructions: <h:outputText id="logo-contactInstructions" value="#{contact.contactInfo.contactInstructions}"/>

                            onLineResource name: <h:outputText value="#{contact.contactInfo.onLineResource.name}"/>
                            onLineResource applicationProfile : <h:outputText value="#{contact.contactInfo.onLineResource.applicationProfile}"/>
                            onLineResource protocol : <h:outputText value="#{contact.contactInfo.onLineResource.protocol}"/>
                            onLineResource description : <h:outputText value="#{contact.contactInfo.onLineResource.description}"/>
                            onLineResource linkage : <h:outputText value="#{contact.contactInfo.onLineResource.linkage}"/>
                        </h:panelGrid>
                    </h:panelGrid>
                </a4j:repeat>

                <h2>Reference System Info</h2>
                <a4j:repeat value="#{metadata.metaData.referenceSystemInfo}" var="referencesystem">
                    <h:panelGrid columns="2">
                        domainOfValidity :  <h:outputText value="#{referencesystem.domainOfValidity}"/>
                        Scope :  <h:outputText value="#{referencesystem.scope}"/>
                    </h:panelGrid>
                </a4j:repeat>
                <h2>Identification Info</h2>
                <%--a4j:repeat value="#{metadata.metaData.identificationInfo}" binding="#{metadata.repeater}" var="identification">
                    <h:panelGrid columns="2">
                        Citation:
                        <h:panelGrid columns="2" border="1">
                            Title :  <h:outputText value="#{identification.citation.title}"/>
                            alternative titles :<ul>
                                <a4j:repeat value="#{identification.citation.alternateTitles}" var="title">
                                    <li><h:outputText value="#{title}"/></li>
                            </a4j:repeat></ul>
                            Collective Title :  <h:outputText value="#{identification.citation.collectiveTitle}"/>
                            Edition :  <h:outputText value="#{identification.citation.edition}"/>
                            Edition date:  <h:outputText value="#{identification.citation.editionDate}"/>
                            Other citation details :  <h:outputText value="#{identification.citation.otherCitationDetails}"/>
                            ISBN :  <h:outputText value="#{identification.citation.ISBN}"/>
                            ISSN :  <h:outputText value="#{identification.citation.ISSN}"/>
                            Dates:
                            <a4j:repeat value="#{identification.citation.dates}" var="date">
                                <h:panelGrid columns="2" border="1">
                                    date <h:outputText value="#{date}"/>
                                </h:panelGrid>

                            </a4j:repeat>
                            Series name :  <h:outputText value="#{identification.citation.series.name}"/>
                            Series issueidentification :  <h:outputText value="#{identification.citation.series.issueidentification}"/>
                            Series page :  <h:outputText value="#{identification.citation.series.page}"/>

                            Contacts:
                            <a4j:repeat value="#{identification.citation.citedResponsibleParties}" var="contact">
                                <h:panelGrid columns="2" border="1">
                                    individualName: <h:outputText id="logo-individualName" value="#{contact.individualName}"/>
                                    organisationName: <h:outputText id="logo-organisationName" value="#{contact.organisationName}"/>
                                    positionName: <h:outputText id="logo-positionName" value="#{contact.positionName}"/>
                                    role: <h:outputText id="logo-role" value="#{contact.role}"/>
                                    contact info:
                                    <h:panelGrid columns="2">
                                        administrativeArea : <h:outputText id="logo-administrativeArea" value="#{contact.contactInfo.address.administrativeArea}"/>
                                        deliveryPoints:
                                        <a4j:repeat value="#{contact.contactInfo.address.deliveryPoints}" var="deliveryPoint">
                                            <h:outputText id="logo-deliveryPoints" value="#{deliveryPoint}"/>
                                        </a4j:repeat>
                                        postalcode-city-country: <h:outputText id="logo-postalCode" value="#{contact.contactInfo.address.postalCode} #{contact.contactInfo.address.city} #{contact.contactInfo.address.country}"/>
                                        phone:
                                        <a4j:repeat value="#{contact.contactInfo.phone.voices}" var="voice">
                                            <h:outputText id="logo-email" value="#{voice}"/>
                                        </a4j:repeat>
                                        fax:
                                        <a4j:repeat value="#{contact.contactInfo.phone.facsimiles}" var="fax">
                                            <h:outputText id="logo-email" value="#{fax}"/>
                                        </a4j:repeat>
                                        email:
                                        <a4j:repeat value="#{contact.contactInfo.address.electronicMailAddresses}" var="email">
                                            <h:outputText id="logo-email" value="#{email}"/>
                                        </a4j:repeat>
                                        hoursOfService: <h:outputText id="logo-hoursOfService" value="#{contact.contactInfo.hoursOfService}"/>
                                        contactInstructions: <h:outputText id="logo-contactInstructions" value="#{contact.contactInfo.contactInstructions}"/>

                                        onLineResource name: <h:outputText value="#{contact.contactInfo.onLineResource.name}"/>
                                        onLineResource applicationProfile : <h:outputText value="#{contact.contactInfo.onLineResource.applicationProfile}"/>
                                        onLineResource protocol : <h:outputText value="#{contact.contactInfo.onLineResource.protocol}"/>
                                        onLineResource description : <h:outputText value="#{contact.contactInfo.onLineResource.description}"/>
                                        onLineResource linkage : <h:outputText value="#{contact.contactInfo.onLineResource.linkage}"/>
                                    </h:panelGrid>
                                </h:panelGrid>
                            </a4j:repeat>
                        </h:panelGrid>
                        Abstract :  <h:outputText value="#{identification.abstract}"/>
                        Purpose :  <h:outputText value="#{identification.purpose}"/>

                        Credits :
                        <ul>
                            <a4j:repeat value="#{identification.credits}" var="credit">
                                <li><h:outputText value="#{credit}"/></li>
                            </a4j:repeat>
                        </ul>
                        Keywords:
                        <ul>
                            <a4j:repeat value="#{identification.descriptiveKeywords}" var="keyword">
                                <li>list : <h:outputText value="#{keyword.keywords}"/></li>
                                <li>type: <h:outputText value="#{keyword.type}"/></li>
                                <li>thesaurus name : <h:outputText value="#{keyword.thesaurusName}"/></li>
                            </a4j:repeat>
                        </ul>
                        Contacts:
                        <a4j:repeat value="#{identification.pointOfContacts}" var="contact">
                            <h:panelGrid columns="2" border="1">
                                individualName: <h:outputText id="logo-individualName" value="#{contact.individualName}"/>
                                organisationName: <h:outputText id="logo-organisationName" value="#{contact.organisationName}"/>
                                positionName: <h:outputText id="logo-positionName" value="#{contact.positionName}"/>
                                role: <h:outputText id="logo-role" value="#{contact.role}"/>
                                contact info:
                                <h:panelGrid columns="2">
                                    administrativeArea : <h:outputText id="logo-administrativeArea" value="#{contact.contactInfo.address.administrativeArea}"/>
                                    deliveryPoints:
                                    <a4j:repeat value="#{contact.contactInfo.address.deliveryPoints}" var="deliveryPoint">
                                        <h:outputText id="logo-deliveryPoints" value="#{deliveryPoint}"/>
                                    </a4j:repeat>
                                    postalcode-city-country: <h:outputText id="logo-postalCode" value="#{contact.contactInfo.address.postalCode} #{contact.contactInfo.address.city} #{contact.contactInfo.address.country}"/>
                                    phone:
                                    <a4j:repeat value="#{contact.contactInfo.phone.voices}" var="voice">
                                        <h:outputText id="logo-email" value="#{voice}"/>
                                    </a4j:repeat>
                                    fax:
                                    <a4j:repeat value="#{contact.contactInfo.phone.facsimiles}" var="fax">
                                        <h:outputText id="logo-email" value="#{fax}"/>
                                    </a4j:repeat>
                                    email:
                                    <a4j:repeat value="#{contact.contactInfo.address.electronicMailAddresses}" var="email">
                                        <h:outputText id="logo-email" value="#{email}"/>
                                    </a4j:repeat>
                                    hoursOfService: <h:outputText id="logo-hoursOfService" value="#{contact.contactInfo.hoursOfService}"/>
                                    contactInstructions: <h:outputText id="logo-contactInstructions" value="#{contact.contactInfo.contactInstructions}"/>

                                    onLineResource name: <h:outputText value="#{contact.contactInfo.onLineResource.name}"/>
                                    onLineResource applicationProfile : <h:outputText value="#{contact.contactInfo.onLineResource.applicationProfile}"/>
                                    onLineResource protocol : <h:outputText value="#{contact.contactInfo.onLineResource.protocol}"/>
                                    onLineResource description : <h:outputText value="#{contact.contactInfo.onLineResource.description}"/>
                                    onLineResource linkage : <h:outputText value="#{contact.contactInfo.onLineResource.linkage}"/>
                                </h:panelGrid>
                            </h:panelGrid>
                        </a4j:repeat>
                        Constraints :
                        <h:panelGrid columns="2" border="1">
                            <a4j:repeat value="#{identification.resourceConstraints}" var="constraint">
                                use limitation :
                                <ul>
                                    <a4j:repeat value="#{constraint.useLimitations}" var="limitation">
                                        <li><h:outputText value="#{limitation}"/></li>
                                    </a4j:repeat>
                                </ul>
                                <h:panelGrid rendered="#{constraint.class.name == 'org.geotoolkit.metadata.iso.constraint.DefaultSecurityConstraints'}" columns="2" border="1">
                                    classification : <h:outputText value="#{constraint.classification}"/>
                                    user note : <h:outputText  value="#{constraint.userNote}"/>
                                    classification system : <h:outputText value="#{constraint.classificationSystem}"/>
                                    handling description system : <h:outputText value="#{constraint.handlingDescription}"/>

                                </h:panelGrid>
                                <h:panelGrid rendered="#{constraint.class.name == 'org.geotoolkit.metadata.iso.constraint.DefaultLegalConstraints'}" columns="2" border="1">
                                    access constraints :
                                    <ul>
                                        <a4j:repeat value="#{constraint.accessConstraints}" var="access">
                                            <li><h:outputText value="#{access}"/></li>
                                        </a4j:repeat>
                                    </ul>
                                    use constraints :
                                    <ul>
                                        <a4j:repeat value="#{constraint.useConstraints}" var="use">
                                            <li><h:outputText value="#{use}"/></li>
                                        </a4j:repeat>
                                    </ul>
                                    other constraints :
                                    <ul>
                                        <a4j:repeat value="#{constraint.otherConstraints}" var="other">
                                            <li><h:outputText value="#{other}"/></li>
                                        </a4j:repeat>
                                    </ul>
                                </h:panelGrid>
                            </a4j:repeat>

                        </h:panelGrid>
                        Overview :
                        <a4j:repeat value="#{identification.graphicOverviews}" var="overview">
                            <h:panelGrid columns="2" border="1">
                                file type : <h:outputText value="#{overview.fileType}"/>
                                file description : <h:outputText value="#{overview.fileDescription}"/>
                                file name: <h:graphicImage value="#{overview.fileName}"/>
                            </h:panelGrid>
                        </a4j:repeat>
                        Spatial resolutions :
                        <ul>
                            <a4j:repeat value="#{identification.spatialResolutions}" var="res">
                                <li><h:outputText value="#{res}"/></li>
                            </a4j:repeat>
                        </ul>
                        Languages :
                        <ul>
                            <a4j:repeat value="#{identification.languages}" var="lang">
                                <li><h:outputText value="#{lang}"/></li>
                            </a4j:repeat>
                        </ul>
                        Character sets :
                        <ul>
                            <a4j:repeat value="#{identification.characterSets}" var="charset">
                                <li><h:outputText value="#{charset}"/></li>
                            </a4j:repeat>
                        </ul>
                        Topic category:
                        <ul>
                            <a4j:repeat value="#{identification.topicCategories}" var="topic">
                                <li><h:outputText value="#{topic}"/></li>
                            </a4j:repeat>
                        </ul>
                        Environment description:
                        <h:outputText id="envDesc" value="#{identification.environmentDescription}"/>
                        Supplemental information
                        <h:outputText value="#{identification.supplementalInformation}"/>
                        Extent:

                        <a4j:repeat value="#{identification.extents}" var="extent">
                            <h:panelGrid rendered="#{extent.class.name == 'org.geotoolkit.metadata.iso.extent.DefaultExtent'}" columns="2" border="1">
                                Description : <h:outputText value="#{extent.description}"/>
                                Geo elements :
                                <a4j:repeat value="#{extent.geographicElements}" var="geo">
                                    <h:panelGrid rendered="#{geo.class.name == 'org.geotoolkit.metadata.iso.extent.DefaultBoundingPolygon'}" columns="2" border="1">
                                    </h:panelGrid>
                                    <h:panelGrid rendered="#{geo.class.name == 'org.geotoolkit.metadata.iso.extent.DefaultGeographicBoundingBox'}" columns="2" border="1">
                                        West : <h:outputText value="#{geo.westBoundLongitude}"/>
                                        East : <h:outputText value="#{geo.eastBoundLongitude}"/>
                                        North : <h:outputText value="#{geo.northBoundLatitude}"/>
                                        South : <h:outputText value="#{geo.southBoundLatitude}"/>

                                    </h:panelGrid>
                                    <h:panelGrid rendered="#{geo.class.name == 'org.geotoolkit.metadata.iso.extent.DefaultGeographicDescription'}" columns="2" border="1">
                                        Geographic identifier : <h:outputText value="#{geo.geographicIdentifier.code}"/>
                                    </h:panelGrid>
                                </a4j:repeat>
                            </h:panelGrid>
                            <h:panelGrid rendered="#{extent.class.name == 'org.geotoolkit.metadata.iso.extent.DefaultTemporalExtent'}" columns="2" border="1">
                            </h:panelGrid>
                            <h:panelGrid rendered="#{extent.class.name == 'org.geotoolkit.metadata.iso.extent.DefaultVerticalExtent'}" columns="2" border="1">
                            </h:panelGrid>
                            <h:panelGrid rendered="#{extent.class.name == 'org.geotoolkit.metadata.iso.extent.DefaultSpatialTemporalExtent'}" columns="2" border="1">
                            </h:panelGrid>
                        </a4j:repeat>
                    </h:panelGrid>
                    <mf-model:Context id="context" minifyJS="false" debug="true" service="data/context/owc030.xml">
                        <mf:MapPane navigation="true" debug="true"></mf:MapPane>
                        <mf:ButtonBar></mf:ButtonBar>
                    </mf-model:Context>

                </a4j:repeat--%>

                <%--mf-model:Context minifyJS="false" debug="true" service="data/context/owc030.xml">
                             <mf:MapPane navigation="true" debug="true"></mf:MapPane>
                             <mf:ButtonBar></mf:ButtonBar>
                         </mf-model:Context--%>
                <a4j:log popup="false" width="1200" height="1000" ></a4j:log>
            </h:form>
        </body>
    </html>
</f:view>
