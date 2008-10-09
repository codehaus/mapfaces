try{
    if (MooTools != null){
        console.log('mootools already loaded');
    }
}catch(err){
    console.log('no mootools');
    document.write('<script type="text/javascript" src="resource.jsf?r=/org/mapfaces/resources/treetable/js/moo1.2.js"></script>');
}