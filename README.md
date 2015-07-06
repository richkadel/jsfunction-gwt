**Note that this library is now maintained in the _Harmonia Holdings Group_ [GitHub site for JSFunction-GWT](https://github.com/Harmonia-Holdings-Group/jsfunction-gwt)**

jsfunction-gwt
==============

See the [full project page](http://richkadel.github.io/jsfunction-gwt) and [JavaDoc](http://richkadel.github.io/jsfunction-gwt/apidocs).

Extensive support for passing GWT Java functions to JavaScript APIs that require JavaScript functions.

The library supports:

* Passing Java functions to javascript for future invocation (e.g., callbacks)
* Calling JavaScript functions that return results asynchronously (that is, the JavaScript method
will invoke a Java result handler when the result is available)
* No-argument functions and functions with variable length argument lists 
* Optional support for passing non-JavaScript Java Objects as types or return values (which can be 
handy, but use with caution as these objects cannot be serialized,such as with JSON)
