# NappBugsnag

This is a native module that captures exceptions thrown in Titanium and send it to Bugsnag https://bugsnag.com. The module is developed for `iOS` and `Android`. 

## How to use

Add the following code to `alloy.js` to bootup Bugsnag at app launch. 

```javascript
var NappBugsnag = require('dk.napp.bugsnag');

NappBugsnag.startBugsnagWithConfiguration({
	apikey: "YOUR-BUGSNAG-API-KEY"
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
});

```

## API

### notify(object) 
Send a custom non fatal exception to bugsnag.

```javascript
NappBugsnag.notify({
	name: "MyException",
	reason: "Just for fun!"
});
```

### addAttribute(object)
Add an attribute to a new tab of your choice. This is very helpful to debug why an exception is thrown.

```javascript
NappBugsnag.addAttribute({
	tabName: "User",
	attribute: "username",
	data: "bob-hoskins"
});
NappBugsnag.addAttribute({
	tabName: "User",
	attribute: "email",
	data: "bob@example.com"
});
```

### clearTabWithName(string)
Clears a tab. e.g. when the user log out of the app, you want to clear the User tab.

```javascript
NappBugsnag.clearTabWithName("User");
```

### setContext(string)
Bugsnag uses the concept of "contexts" to help display and group your
errors. Contexts represent what was happening in your application at the time an error occurs.

```javascript
NappBugsnag.setContext("myActivity");
```

## Changelog

* 1.0.0
  * Initial version. 

## Author

**Mads Møller**  
web: http://www.napp.dk  
email: mm@napp.dk  
twitter: @nappdev  


## License

	MIT

    Copyright (c) 2010-2015 Mads Møller

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
	

