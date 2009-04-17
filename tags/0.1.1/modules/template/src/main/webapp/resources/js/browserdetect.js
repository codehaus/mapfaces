/**
 * Same logic as before. Have removed some detection capabilities in an effort
 * to reduce reliance on browser detection in general.
 * 
 * If there is a particualr detection you feel you need please file a ticket
 * with the request or add the detection yourself with the exact reason you
 * need said detection. Hopefully we'll be able to keep the number of
 * detection utility functions to a minimum in favor of behavioral testing.
 * 
 * This may also be the place for very common behavioral functions.
 * 
 */

if (typeof(AC) == "undefined") { AC = {}; }

AC.Detector = {

	getAgent: function() {
		return navigator.userAgent.toLowerCase();
	},
	
	// detect platform
	isMac: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/mac/i);
	},
	
	isWin: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/win/i);
	},
	
	isWin2k: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return this.isWin(agent) && (agent.match(/nt\s*5/i));
	},
	
	isWinVista: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return this.isWin(agent) && (agent.match(/nt\s*6/i));
	},
	
	// detect browser
	isWebKit: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/AppleWebKit/i);
	},
	
	isOpera: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/opera/i);
	},
	
	isIE: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/msie/i);
	},
	
	isIEStrict: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/msie/i) && !this.isOpera(agent);
	},
	
	isFirefox: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/firefox/i);
	},
	isChrome: function(userAgent) {
		var agent = userAgent || this.getAgent();
		return agent.match(/chrome/i);
	}

	
};

