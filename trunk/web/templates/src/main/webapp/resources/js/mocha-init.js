// Functions
initializeWindows = function(){

    // Windows aboutGeomatys construction
    MochaUI.aboutGeomatys = function(){
        new MochaUI.Window({
            id:"aboutGeomatys",
            title:"About Geomatys",
            loadMethod:"xhr",
            contentURL:"AboutGeomatys.jsf",
            width:400,
            height:500
        }
        )
    };
    // AddEvent on aboutGeomatys links
    if($("aboutGeomatysLink")){
        $("aboutGeomatysLink").addEvent("click",function(a){
            new Event(a).stop();
            MochaUI.aboutGeomatys()
        })
    }


    // Windows logger construction
    MochaUI.loggerPopup = function(){
        new MochaUI.Window({
            id:"sdnlogger",
            title:"Logger",
            loadMethod:"iframe",
            contentURL:"logger.jsf",
            width:450,
            height:500
        }
        )
    };
    // AddEvent on logger link
    if($("loggerpopup")){
        $("loggerpopup").addEvent("click",function(a){
            new Event(a).stop();
            MochaUI.loggerPopup()
        })
    }



    // View
    if ($('sidebarLinkCheck')){
        $('sidebarLinkCheck').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.Desktop.sidebarToggle();
        });
    }

    if ($('cascadeLink')){
        $('cascadeLink').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.arrangeCascade();
        });
    }

    if ($('tileLink')){
        $('tileLink').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.arrangeTile();
        });
    }

    if ($('closeLink')){
        $('closeLink').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.closeAll();
        });
    }

    if ($('minimizeLink')){
        $('minimizeLink').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.minimizeAll();
        });
    }
	
    // Workspaces
    if ($('saveWorkspaceLink')){
        $('saveWorkspaceLink').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.saveWorkspace();
        });
    }

    if ($('loadWorkspaceLink')){
        $('loadWorkspaceLink').addEvent('click', function(e){
            new Event(e).stop();
            MochaUI.loadWorkspace();
        });
    }

    // Deactivate menu header links
    $$('a.returnFalse').each(function(el){
        el.addEvent('click', function(e){
            new Event(e).stop();
        });
    });
	
}
