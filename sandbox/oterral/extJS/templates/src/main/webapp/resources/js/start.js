Ext.onReady(function(){

    // NOTE: This is an example showing simple state management. During development,
    // it is generally best to disable state management as dynamically-generated ids
    // can change across page loads, leading to unpredictable results.  The developer
    // should ensure that stable state ids are set for stateful components in real apps.
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    var panel = new Ext.Panel({
        title:"title",
        id:'simplestbl',
        layout:'border',
        width:"100%",
        height:600,
        renderTo:Ext.getDom('main_form'),
        items:[
        {
            region:'west',
            contentEl: 'west',
            layout:'fit',
            frame:true,
            border:false,
            width:240,
            split:true,
            collapsible:true,
            collapseMode:'mini'
        },
        {
            region:'center',
            contentEl: 'center',
            layout:'fit',
            frame:true,
            border:false
        },
        {
            region:'east',
            contentEl: 'east',
            layout:'fit',
            frame:true,
            border:false,
            width:300,
            split:true,
            collapsible:true,
            collapseMode:'mini'
        }
        ]
    });

    panel.on('resize', function() {
        if (window.maps) {
            for (var i in window.maps) {
                if (i.CLASS_NAME == "OpenLayers.Map") {
                    i.updateSize();
                }
            }
        }
    });



    //    var viewport = new Ext.Panel({
    //        layout: 'border',
    //        items: [
    //        // create instance immediately
    //        new Ext.BoxComponent({
    //            region: 'north',
    //            height: 32, // give north and south regions a height
    //            autoEl: {
    //                tag: 'div',
    //                html:'<p>north - generally for menus, toolbars and/or advertisements</p>'
    //            }
    //        }),// create instance immediately
    //        new Ext.BoxComponent({
    //            region: 'south',
    //            height: 32, // give north and south regions a height
    //            autoEl: {
    //                tag: 'div',
    //                html:'<p>&copy; 2008-2009 <a target="_blank" href="#">Geomatys</a></p>'
    //            }
    //        }), {
    //            region: 'east',
    //            title: 'East Side',
    //            collapsible: true,
    //            split: true,
    //            width: 225, // give east and west regions a width
    //            minSize: 175,
    //            maxSize: 400,
    //            margins: '0 5 0 0',
    //            layout: 'fit', // specify layout manager for items
    //            items:            // this TabPanel is wrapped by another Panel so the title will be applied
    //            new Ext.TabPanel({
    //                border: false, // already wrapped so don't add another border
    //                activeTab: 1, // second tab initially active
    //                tabPosition: 'bottom',
    //                items: [{
    //                    contentEl: 'east',
    //                    title: 'A Tab',
    //                    autoScroll: true
    //                }, new Ext.grid.PropertyGrid({
    //                    title: 'Property Grid',
    //                    closable: true,
    //                    source: {
    //                        "(name)": "Properties Grid",
    //                        "grouping": false,
    //                        "autoFitColumns": true,
    //                        "productionQuality": false,
    //                        "created": new Date(Date.parse('10/15/2006')),
    //                        "tested": false,
    //                        "version": 0.01,
    //                        "borderWidth": 1
    //                    }
    //                })]
    //            })
    //        }, {
    //            region: 'west',
    //            id: 'west-panel', // see Ext.getCmp() below
    //            title: 'West',
    //            split: true,
    //            width: 200,
    //            minSize: 175,
    //            maxSize: 400,
    //            collapsible: true,
    //            margins: '0 0 0 5',
    //            layout: {
    //                type: 'accordion',
    //                animate: true
    //            },
    //            items: [{
    //                contentEl: 'west',
    //                title: 'Navigation',
    //                border: false,
    //                iconCls: 'nav' // see the HEAD section for style used
    //            }, {
    //                title: 'Settings',
    //                html: '<p>Some settings in here.</p>',
    //                border: false,
    //                iconCls: 'settings'
    //            }]
    //        },
    //        // in this instance the TabPanel is not wrapped by another panel
    //        // since no title is needed, this Panel is added directly
    //        // as a Container
    //        new Ext.TabPanel({
    //            region: 'center', // a center region is ALWAYS required for border layout
    //            deferredRender: false,
    //            activeTab: 0,     // first tab initially active
    //            items: [{
    //                contentEl: 'center2',
    //                title: 'Map Panel',
    //                autoScroll: true
    //            }, {
    //                contentEl: 'center1',
    //                title: 'Home Panel',
    //                closable: true,
    //                autoScroll: true
    //            }]
    //        })
    //        ]
    //    });

    if (window.reloadAllMaps)
        window.reloadAllMaps();
//        // get a reference to the HTML element with id "hideit" and add a click listener to it
//        Ext.get("hideit").on('click', function(){
//            // get a reference to the Panel that was created with id = 'west-panel'
//            var w = Ext.getCmp('west-panel');
//            // expand or collapse that Panel based on its collapsed property state
//            w.collapsed ? w.expand() : w.collapse();
//        });
});
