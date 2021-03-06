<%@taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>


<div id="desktopHeader">
    <!-- Banniere -->
    <div id="desktopTitlebarWrapper" style="height:65px;border-bottom:1px solid black;" >
        <div style="width: 500px; height: 100%; position: absolute; top: 0px; left: 0px;" id="headerLeft">
            <!--div style="height: 100%; left: 60px; position: absolute; top: 0pt;" class="logo"></div-->
        </div>
        <center>
            <div id="headerMiddle">
                <div style="padding: 15px 0px 5px 15px; font-size: 18px; font-weight: bold; color: #23678C;">Title project</div>
                <div style="padding: 0px 0px 0px 15px; color: #58595B; font-size: 12px; font-weight: bold;">Subtitle project</div>
            </div>
        </center>
        <div id="headerRight"
             style="width: 100px; 
             position: absolute; top: 20px; right: 0px;
             <%--background:transparent url(resources/skin/geobs/img/bg_right.jpg) no-repeat scroll 0% 0%;--%>
            ">
             <ul class="menu-right">
                    <li><a href="#" onclick="MochaUI.notification('Do Something');return false;">Login</a></li>
             </ul>
        </div>
    </div>
    
    <!-- navigation bar -->    
    <div id="desktopNavbar">
        <ul>
            <li>
                <a4j:commandLink id="homea4jlink" onclick="setTabs('_Home_Tabs');"
                                 oncomplete="homePageWrapper();" 
                                 reRender="toRefresh" 
                                 actionListener="#{switchTab.goHomeActionEvent}">
                    <h:outputText id="homelinklabel" value="Home"/>
                </a4j:commandLink>
            </li>
            
            <li>
                <a4j:commandLink id="geovisualizationa4jlink" onclick="setTabs('_App_Tabs');"
                                 oncomplete="webAppPageWrapper();"
                                 reRender="toRefresh" 
                                 actionListener="#{switchTab.goAppActionEvent}">
                    <h:outputText id="geovizualizationlinklabel" value="Application"/>
                </a4j:commandLink>
            </li>
            
            <li><a class="returnFalse" href="">Tools</a>
                <ul>
                    <li>
                        <a class="returnFalse arrow-right" href="">Workspace</a>
                        <ul>
                            <li><a id="saveWorkspaceLink" href="">Save Workspace</a></li>
                            <li><a id="loadWorkspaceLink" href="">Load Workspace</a></li>
                        </ul>
                    </li>
                    <li><a class="returnFalse arrow-right" href="">View</a>
                        <ul>
                            <li><a id="cascadeLink" href="">Cascade Windows</a></li>
                            <li><a id="tileLink" href="">Tile Windows</a></li>
                            <li><a id="minimizeLink" href="">Minimize All Windows</a></li>
                            <li><a id="closeLink" href="">Close All Windows</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li><a class="returnFalse" href="">Help</a>
                <ul>
                    <li class="divider"><a class="returnFalse" href="#">Documentation</a></li>
                    <li><a id="aboutGeomatysLink" href="AboutGeomatys.jsf">About Geomatys</a></li>
 		    <li><a id="loggerpopup" href="">Show logs panel</a></li>
                </ul>
            </li>
        </ul>
        
        <div class="toolbox divider2">
            <div id="spinnerWrapper"><div id="spinner"></div></div>
        </div>
        
        <div class="toolbox divider2">
            <div onclick="MochaUI.notification('Do Something');" class="cog"></div>
            <div onclick="MochaUI.notification('Do Something');" class="windows"></div>
            <div onclick="MochaUI.notification('Do Something');" class="sheet"></div>
        </div>
        
    </div>
    
</div>

<!-- float bar -->
<div id="dockWrapper" style="background:#4C4C4C none repeat scroll 0%">
    <div id="dock">
        <div id="dockPlacement" style="display:none"></div>
        <div id="dockAutoHide" style="display:none"></div>
        <div id="dockSort">
		<div id="dockClear" class="clear">
		</div>
	</div>
    </div>
</div>

<style>
#dockCanvas{display:none;}
</style>






