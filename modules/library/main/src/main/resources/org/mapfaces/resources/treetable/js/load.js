try{
    if (MooTools != null){
        console.log('mootools already loaded');
    }
}catch(err){
    console.log('no mootools');
    document.write('<script type="text/javascript" src="/mapfaces/resource.jsf?r=/org/mapfaces/resources/js/mootools.1.2.js"></script>')
}