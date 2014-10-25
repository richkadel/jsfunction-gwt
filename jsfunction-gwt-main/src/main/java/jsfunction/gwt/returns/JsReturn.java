package jsfunction.gwt.returns;

import jsfunction.gwt.returns.advanced.JsReturnValue;
import jsfunction.gwt.types.JsError;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * This class encapsulates a callback to handle a deferred return
 * result, such as an AJAX callback, for example. It is typically passed
 * in as an additional argument to an API method, and used to return the
 * value, or return an encountered Error exception. For primitive return
 * types (e.g., boolean, number), use the JsFunction primitive wrappers
 * such as JsBoolean, JsNumber, JsString.
 * 
 * Note, to catch exceptions with functions that don't return values,
 * use JsReturnVoid.
 * 
 * To pass this callback into a JavaScript function, you call
 * JsFunction.create(JsReturn), which returns a JsResultOrError
 * JavaScript object. The JavaScript function then calls
 * the given JsResultOrError "argument" via "argument.result(someResult)"
 * or "argument.error(someError)", causing the Java onReturn() or
 * onError() callback methods in this class to be invoked.
 * 
 * @author richkadel
 */
public abstract class JsReturn<T extends JavaScriptObject> {

  public void onReturn(JsReturnValue<T> returnValue) {
    onReturn(returnValue.get());
  }

  public abstract void onReturn(T returnValue);
  
  public abstract void onError(JsError jsError);
}
