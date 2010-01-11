Proj4js.scriptName = "r=/org/mapfaces/resources/";//Proj4js.js  //Find the first resource load by the ResourcePhaseListener
Proj4js.getScriptLocation = function () {
        if (this.libPath) return this.libPath;
        var scriptName = this.scriptName;
        var scriptNameLen = scriptName.length;

        var scripts = document.getElementsByTagName('script');
        for (var i = 0; i < scripts.length; i++) {
            var src = scripts[i].getAttribute('src');
            if (src) {
                var index = src.lastIndexOf(scriptName);
                // is it found, at the end of the URL?
                if ((index > -1)) { //&& (index + scriptNameLen == src.length)) {
                    this.libPath = src.slice(0, index+scriptNameLen)+"proj4js/";//-scriptNameLen);
                    break;
                }
            }
        }
        return this.libPath||"";
    };