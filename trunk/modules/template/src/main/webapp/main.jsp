
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="a4j"uri="https://ajax4jsf.dev.java.net/ajax"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="mf" uri="http://mapfaces.org/taglib"%>
<%@taglib prefix="mf-model" uri="http://mapfaces.org/taglib-models"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<f:view>
    <html xmlns="http://www.w3.org/1999/xhtml">
        
        <head>
            <link rel="icon" type="image/x-icon" href="" />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            
            <title><h:outputText value="Template MochaUI #{init.locale}"/></title>

            <link rel="stylesheet" href="resources/css/all.css" type="text/css" ></link>
            <!--link rel="stylesheet" href="resources/css/content.css" type="text/css" />
            <link rel="stylesheet" href="resources/css/ui.css" type="text/css" /-->
            
            
            <!--[if IE]>
            <script type="text/javascript" src="resources/js/excanvas-compressed.js"></script>
            <link type="text/css" href="/seadatanet/resource.jsf?r=/org/mapfaces/resources/css/widget.css" rel="stylesheet"></link>
            <![endif]-->

	    <script src="resources/js/all.js" type="text/javascript"></script>

	    <!--script src="/seadatanet/resource.jsf?r=/org/mapfaces/resources/openlayers/custom/OpenLayers.js" type="text/javascript"></script-->
            <!--link type="text/css" href="/seadatanet/resource.jsf?r=/org/mapfaces/resources/css/widget.css" rel="stylesheet"></link-->

	    <!--script type="text/javascript" src="resources/js/mootools-1.2-core.js"></script>
            <script type="text/javascript" src="resources/js/mootools-1.2-more.js"></script>
            <script type="text/javascript" src="resources/js/mocha.js"></script>
            <script type="text/javascript" src="resources/js/mocha-init.js"></script>
            <script type="text/javascript" src="resources/js/start.js"></script>
            <script src="resources/js/browserdetect.js" type="text/javascript" charset="utf-8"></script-->

            
        </head>
        
        <body>
            <h:form id="main_form">
                <a4j:region id="stat1">

                    <div id="desktop">
                        
                        <%@ include file="desktopNavbar.jsp" %>
                        
                        <div id="pageWrapper"></div>
                        
                        <div id="bindingWrapper" style="height:300px;width:600px;display:none;">
                            <h:panelGroup id="toRefresh" layout="block">
                                
                                <h:panelGroup id="homePanel" rendered="#{switchTab.displayHome}" layout="block">
                                    <%@ include file="home.jsp" %>
                                </h:panelGroup>
                                
                                <h:panelGroup id="webPanel" rendered="#{switchTab.displayApp}" layout="block">
                                    <%@ include file="application.jsp" %>
                                </h:panelGroup>
                                
                            </h:panelGroup>
                        </div>
                        
                        <!-- footer -->
                        <div id="desktopFooterWrapper">
                            <div id="desktopFooter">
                                &copy; 2008-2009 <a target="_blank" href="#">Geomatys</a>
                            </div>
                        </div>
                        
                    </div><!-- desktop end -->
</a4j:region>
</h:form>

        <a4j:status for="main_form:stat1" forceId="true" id="ajaxStatus">
            <f:facet name="start"> 
                <t:div id="panelloadingstatus">
                    <h:graphicImage id="loading_img" value="resources/img/loadinfo_spinner.gif" style="position:fixed;top:50%;left:50%;z-index:1001;" />
                        <div id="loading-modal">
                        </div>
                    </t:div>
                </f:facet>
            </a4j:status>
        
            <%@ include file="footer.jsp"%>
            <script type="text/javascript" charset="utf-8">
                /*
                 * This piece of code is necessary when we use IE6
                 * if we don't use this, the Browser.Engine property thinks we use IE7  while we use IE6
                 * because, to define IE6, Mootools uses the property window.XMLHttpRequest who doesn't exist in IE6
                 * but the Sarissa library used with Ajax4JSF redefines this property  
                 * so in fact she exists. 
                 */
                window.addEvent('load',function(){
                    if(navigator.userAgent.indexOf("MSIE 6.0") != -1)
                        Browser.Engine.trident4 = true;
                });
            </script>
        </body>
    </html>
</f:view>
