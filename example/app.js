
var win = Ti.UI.createWindow({
	backgroundColor:'white'
});
win.open();


var NappBugsnag = require('dk.napp.bugsnag');

NappBugsnag.startBugsnagWithConfiguration({
	apikey: "YOUR-BUGSNAG-API-KEY",
	notifyReleaseStages: ["production", "development"] //optional
});

// listen for Ti exceptions
Ti.App.addEventListener('uncaughtException', function (e) {
	if(ENV_PROD || ENV_TEST){
		NappBugsnag.addAttribute({
			tabName: "Titanium",
			attribute: "JSStack",
			data: JSON.stringify(e)
		});
	}

	Ti.API.error("Ti uncaughtException...");
});


var customExceptionBtn = Ti.UI.createButton({
	top: 100,
	height: 50,
	width: Ti.UI.SIZE,
	title: "Custom Exception"
});
customExceptionBtn.addEventListener("click", function(){
	// send an exception to Bugsnag
	NappBugsnag.notify({
		name: "My dummy Exception",
		reason: "Test test"
	});
});
win.add(customExceptionBtn);


var appCrashBtn = Ti.UI.createButton({
	top: 200,
	height: 50,
	width: Ti.UI.SIZE,
	title: "Make the app crash"
});
appCrashBtn.addEventListener("click", function(){
	// this will crash the app
	unknownMethod();
});
win.add(appCrashBtn);
