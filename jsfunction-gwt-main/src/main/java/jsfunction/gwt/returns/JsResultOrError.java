package jsfunction.gwt.returns;

import jsfunction.gwt.JsFunction;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * This is the JavaScriptObject type returned from JsFunction.create(JsReturn).
 * It can be passed as an argument to a JavaScript function, which then returns
 * its results asynchronously by calling "arg.result(someResult)". If the
 * JavaScript function encounters an error or exception, it can return that
 * as a JavaScript "Error" class via "arg.error(new Error('message'))" (or simply
 * "arg.error(caughtError)").
 * 
 * @author richkadel
 */
public final class JsResultOrError extends JavaScriptObject {
  
  protected JsResultOrError() {}
  
  public native JsFunction resultFunction() /*-{ return this.result }-*/;
  public native JsFunction errorFunction() /*-{ return this.error }-*/;
}
