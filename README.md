jsfunction-gwt
==============

Extensive support for passing GWT Java functions to JavaScript APIs that require JavaScript functions.

This library used to be part of the [Cesium-GWT](http://richkadel.github.io/cesium-gwt/) repository, 
but it became too useful to other non-Cesium projects (such as my
[OZONE Widget Framework GWT wrappers](https://github.com/richkadel/owf-gwt)),
so I made it an independent project. 

OWF required extensive enhancements to JsFunction, ultimately
forcing me to cover a wide array of cases of function call interactions between GWT and JavaScript.

A key tenet of JsFunction is to provide support for the strong type checking built into Java,
which not only makes the software more reliable, but it also makes development faster by
ensuring the code that compiles is more likely to work the first time, and by allowing Integrated
Development Environments (IDEs) like Eclipse or NetBeans to provide automated code completion and
other syntax checking and highlighting. JsFunction makes extensive use of Java Generics to support
developer-provided classes as well as standard JavaScript types and primitives.

The library now supports:

* Passing Java functions to javascript for future invocation (e.g., callbacks)
* Calling JavaScript functions that return results asynchronously (that is, the JavaScript method
will invoke a Java result handler when the result is available)
* No-argument functions and functions with variable length argument lists 
* Optional support for passing non-JavaScript Java Objects as types or return values (which can be 
handy, but use with caution as these objects cannot be serialized,such as with JSON)

Examples
--------

### The following callback can be passed into a JavaScript "onclick" handler:

    testWindowOnClick(new NoArgsFunction() {
      public void callback() {
        log("Made it", count++);
      }
    });
    
_To turn the Java callback into an actual JavaScript function, we call JsFunction.create()._
_Typically, we implement a wrapper method to hide that detail from an API user:_

    private void testWindowOnClick(NoArgsFunction noArgsFunction) {
      nativeWindowOnClick(JsFunction.create(noArgsFunction));
    }

_Then we invoke the JavaScript from GWT:_

    private native void nativeWindowOnClick(JsFunction callback) /*-{
      $wnd.onclick = callback
    }-*/;


Also review the [test cases](https://github.com/richkadel/jsfunction-gwt/blob/master/jsfunction-gwt-test/src/main/java/jsfunction/jsfunctiontest/JsFunctionTest.java).
