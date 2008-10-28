/*==================================================
 *  Custom Themes
 *==================================================
 */


Timeline.ArcheoTheme = new Object();

Timeline.ArcheoTheme.implementations = [];

Timeline.ArcheoTheme.create = function(locale) {
    if (locale == null) {
        locale = Timeline.Platform.getDefaultLocale();
    }
    
    var f = Timeline.ArcheoTheme.implementations[locale];
    if (f == null) {
        f = Timeline.ArcheoTheme._Impl;
    }
    return new f();
};


Timeline.ArcheoTheme._Impl = function() {
    this.firstDayOfWeek = 0; // Sunday
    
    this.ether = {
        backgroundColors: [
            "#C3E7F3",
            "#BAD3E8",
            "#FFF1C7",
            "#FFFFFF"
        ],
        highlightColor:     "#FFF887",
        highlightOpacity:   50,
        interval: {
            line: {
                show:       true,
                color:      "blue",
                opacity:    25
            },
            weekend: {
                color:      "white",
                opacity:    30
            },
            marker: {
                hAlign:     "Bottom",
                hBottomStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-bottom";
                },
                hBottomEmphasizedStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-bottom-emphasized";
                },
                hTopStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-top";
                },
                hTopEmphasizedStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-top-emphasized";
                },
                    
                vAlign:     "Right",
                vRightStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-right";
                },
                vRightEmphasizedStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-right-emphasized";
                },
                vLeftStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-left";
                },
                vLeftEmphasizedStyler:function(elmt) {
                    elmt.className = "timeline-ether-marker-left-emphasized";
                }
            }
        }
    };
    
    this.event = {
        track: {
            offset:         0.5, // em
            height:         1.5, // em
            gap:            0.5  // em
        },
        instant: {
            icon:           Timeline.urlPrefix + "images/dull-blue-circle.png",
            lineColor:      "#58A0DC",
            impreciseColor: "#58A0DC",
            impreciseOpacity: 20,
            showLineForNoText: true
        },
        duration: {
            color:          "#b4daff",
            opacity:        100,
            impreciseColor: "#58A0DC",
            impreciseOpacity: 20
        },
        label: {
            insideColor:    "white",
            outsideColor:   "black",
            width:         14 // px
        },
        highlightColors: [
            "#FFFF00",
            "#FFC000",
            "#FF0000",
            "#0000FF"
        ],
        bubble: {
            width:          250, // px
            height:         50, // px
            titleStyler: function(elmt) {
                elmt.className = "timeline-event-bubble-title";
            },
            bodyStyler: function(elmt) {
                elmt.className = "timeline-event-bubble-body";
            },
            imageStyler: function(elmt) {
                elmt.className = "timeline-event-bubble-image";
            },
            wikiStyler: function(elmt) {
                elmt.className = "timeline-event-bubble-wiki";
            },
            timeStyler: function(elmt) {
                elmt.className = "timeline-event-bubble-time";
            }
        }
    };
};


Timeline.OLanceTheme = new Object();

Timeline.OLanceTheme.implementations = [];

Timeline.OLanceTheme.create = function(locale) {
    if (locale == null) {
        locale = Timeline.Platform.getDefaultLocale();
    }
    
    var f = Timeline.OLanceTheme.implementations[locale];
    if (f == null) {
        f = Timeline.OLanceTheme._Impl;
    }
    return new f();
};

Timeline.OLanceTheme._Impl = function() {
    this.firstDayOfWeek = 0; // Sunday
    
    this.ether = {
        backgroundColors: [
            "#f6fbfe",
            "#e6ebed",
            "#CCC",
            "#AAA"
        ],
        highlightColor:     "#f6fbfe",
        highlightOpacity:   50,
        interval: {
            line: {
                show:       true,
                color:      "#90a2b3",
                opacity:    25
            },
            weekend: {
                color:      "#FFFFE0",
                opacity:    30
            },
            marker: {
                hAlign:     "Bottom",
                hBottomStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-bottom";
                },
                hBottomEmphasizedStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-bottom-emphasized";
                },
                hTopStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-top";
                },
                hTopEmphasizedStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-top-emphasized";
                },
                    
                vAlign:     "Right",
                vRightStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-right";
                },
                vRightEmphasizedStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-right-emphasized";
                },
                vLeftStyler: function(elmt) {
                    elmt.className = "timeline-ether-marker-left";
                },
                vLeftEmphasizedStyler:function(elmt) {
                    elmt.className = "timeline-ether-marker-left-emphasized";
                }
            }
        }
    };
    
    this.event = {
        track: {
            offset:         0.5, // em
            height:         1.5, // em
            gap:            0.5  // em
        },
        instant: {
            icon:           Timeline.urlPrefix + "images/dull-blue-circle.png",
            lineColor:      "#58A0DC",
            impreciseColor: "#58A0DC",
            impreciseOpacity: 20,
            showLineForNoText: true
        },
        duration: {
            color:          "#b4daff",
            opacity:        100,
            impreciseColor: "#58A0DC",
            impreciseOpacity: 20
        },
        label: {
            insideColor:    "white",
            outsideColor:   "black",
            width:         14 // px
        },
        highlightColors: [
            "#FFFF00",
            "#FFC000",
            "#FF0000",
            "#0000FF"
        ],
        bubble: {
            width:          250, // px
            height:         50, // px
            titleStyler: function(elmt) {
                elmt.className = "timeline-event-bubble-title";
            },
            bodyStyler: function(elmt) {
                elmt.className = "timeline-event-bubble-body";
            },
            imageStyler: function(elmt) {
                elmt.className = "timeline-event-bubble-image";
            },
            wikiStyler: function(elmt) {
                elmt.className = "timeline-event-bubble-wiki";
            },
            timeStyler: function(elmt) {
                elmt.className = "timeline-event-bubble-time";
            }
        }
    };
};

//if needed it is easily to change styles of a theme created below.
var themeArcheo = Timeline.ArcheoTheme.create();
themeArcheo.event.bubble.width = 500;
themeArcheo.event.bubble.height = 500;

