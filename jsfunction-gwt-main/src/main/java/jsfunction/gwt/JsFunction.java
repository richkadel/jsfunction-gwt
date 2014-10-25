package jsfunction.gwt;

import jsfunction.gwt.functions.EventListener;
import jsfunction.gwt.functions.NoArgsFunction;
import jsfunction.gwt.functions.NoArgsFunctionJsReturn;
import jsfunction.gwt.functions.NoArgsFunctionReturn;
import jsfunction.gwt.functions.VarArgsFunction;
import jsfunction.gwt.functions.VarArgsFunctionJsReturn;
import jsfunction.gwt.functions.VarArgsFunctionReturn;
import jsfunction.gwt.returns.JsResultOrError;
import jsfunction.gwt.returns.JsReturn;
import jsfunction.gwt.returns.advanced.JsReturnValue;
import jsfunction.gwt.returns.advanced.ReturnValue;
import jsfunction.gwt.types.JsError;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayMixed;

public final class JsFunction extends JavaScriptObject {

  protected JsFunction() {}
  
  private static void invoke(NoArgsFunction noArgCallback) {
    noArgCallback.callback();
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private static void invoke(EventListener eventListener, Object event) {
    eventListener.callback(event);
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  private static void invoke(JsReturn deferredFunctionResult, JsReturnValue result) {
    deferredFunctionResult.onReturn(result);
  }
  
  @SuppressWarnings({ "rawtypes" })
  private static void error(JsReturn deferredFunctionResult, JsError error) {
    deferredFunctionResult.onError(error);
  }
  
  private static void invoke(VarArgsFunction varArgsFunction, JsArrayMixed args) {
    varArgsFunction.callback(args);
  }
  
  @SuppressWarnings({ "rawtypes" })
  private static ReturnValue invoke(VarArgsFunctionReturn varArgsFunctionReturn, JsArrayMixed args) {
    return varArgsFunctionReturn.callAndReturnValue(args);
  }
  
  @SuppressWarnings({ "rawtypes" })
  private static ReturnValue invoke(NoArgsFunctionReturn noArgsFunctionReturn) {
    return noArgsFunctionReturn.callAndReturnValue();
  }
  
  @SuppressWarnings({ "rawtypes" })
  private static JsReturnValue invoke(VarArgsFunctionJsReturn varArgsFunctionJsReturn, JsArrayMixed args) {
    return varArgsFunctionJsReturn.callAndJsReturnValue(args);
  }
  
  @SuppressWarnings({ "rawtypes" })
  private static JsReturnValue invoke(NoArgsFunctionJsReturn noArgsFunctionJsReturn) {
    return noArgsFunctionJsReturn.callAndJsReturnValue();
  }
  
  /**
   * @param func The instance function to call from JSNI
   * @return a GWT reference to an actual JavaScript function pointer
   */
  public static native JsFunction create(NoArgsFunction func) /*-{
    return function() {
      @jsfunction.gwt.JsFunction::invoke(Ljsfunction/gwt/functions/NoArgsFunction;)(func)
    }
  }-*/;
  
  /**
   * Important! The created function, when invoked by JavaScript, returns a ReturnValue,
   * which is an object that wraps the actual return value, accessed by the property
   * name "value". So to get the value, you must do something like the following:
   * 
   * funcPointer().value
   * 
   * @param func The instance function to call from JSNI
   * @return a GWT reference to an actual JavaScript function pointer
   */
  public static native JsFunction create(NoArgsFunctionReturn<?> func) /*-{
    return function() {
      return @jsfunction.gwt.JsFunction::invoke(Ljsfunction/gwt/functions/NoArgsFunctionReturn;)(func)
    }
  }-*/;
  
  /**
   * Important! The created function, when invoked by JavaScript, returns a JsReturnValue,
   * which is an object that wraps the actual return value, accessed by the property
   * name "value". So to get the value, you must do something like the following:
   * 
   * funcPointer().value
   * 
   * @param func The instance function to call from JSNI
   * @return a GWT reference to an actual JavaScript function pointer
   */
  public static native JsFunction create(NoArgsFunctionJsReturn<?> func) /*-{
    return function() {
      return @jsfunction.gwt.JsFunction::invoke(Ljsfunction/gwt/functions/NoArgsFunctionJsReturn;)(func)
    }
  }-*/;
  
  /**
   * @param func The instance function to call from JSNI, which may be called
   * with multiple arguments. When invoked, JsFunction packages the arguments into a JavaScript 
   * array.
   * @return a GWT reference to an actual JavaScript function pointer
   */
  public static native JsFunction create(VarArgsFunction func) /*-{
    return function() {
      var argumentsArray = Array.prototype.slice.apply(arguments);
      @jsfunction.gwt.JsFunction::invoke(Ljsfunction/gwt/functions/VarArgsFunction;Lcom/google/gwt/core/client/JsArrayMixed;)(func, argumentsArray)
    }
  }-*/;
  
  /**
   * Important! The created function, when invoked by JavaScript, returns a ReturnValue,
   * which is an object that wraps the actual return value, accessed by the property
   * name "value". So to get the value, you must do something like the following:
   * 
   * funcPointer(args,...).value
   * 
   * @param func The instance function to call from JSNI, which may be called
   * with multiple arguments and should return a ReturnValue. When invoked, 
   * JsFunction packages the arguments into a JavaScript array.
   * @return a GWT reference to an actual JavaScript function pointer
   */
  public static native JsFunction create(VarArgsFunctionReturn<?> func) /*-{
    return function() {
      var argumentsArray = Array.prototype.slice.apply(arguments);
      return @jsfunction.gwt.JsFunction::invoke(Ljsfunction/gwt/functions/VarArgsFunctionReturn;Lcom/google/gwt/core/client/JsArrayMixed;)(func, argumentsArray)
    }
  }-*/;
  
  /**
   * Important! The created function, when invoked by JavaScript, returns a JsReturnValue,
   * which is an object that wraps the actual return value, accessed by the property
   * name "value". So to get the value, you must do something like the following:
   * 
   * funcPointer(args,...).value
   * 
   * @param func The instance function to call from JSNI, which may be called
   * with multiple arguments and should return a JsReturnValue. When invoked, 
   * JsFunction packages the arguments into a JavaScript array.
   * @return a GWT reference to an actual JavaScript function pointer
   */
  public static native JsFunction create(VarArgsFunctionJsReturn<?> func) /*-{
    return function() {
      var argumentsArray = Array.prototype.slice.apply(arguments);
      return @jsfunction.gwt.JsFunction::invoke(Ljsfunction/gwt/functions/VarArgsFunctionJsReturn;Lcom/google/gwt/core/client/JsArrayMixed;)(func, argumentsArray)
    }
  }-*/;
  
  /**
   * @param eventListener The instance eventListener to call from JSNI, taking a single event object, when invoked
   * @return a GWT reference to an actual JavaScript function pointer
   */
  public static native JsFunction create(EventListener<?> eventListener) /*-{
    return function(event) {
      @jsfunction.gwt.JsFunction::invoke(Ljsfunction/gwt/functions/EventListener;Ljava/lang/Object;)(eventListener, event)
    }
  }-*/;
  
  /**
   * @param deferredFunctionResult The instance deferredFunctionResult to call from JSNI, taking a single result object, when invoked.
   * Use JsReturnVoid to be informed of function completion, or to catch any Exception.
   * Typical methods that take a JsReturn<T> argument have accompanying versions that
   * take no JsResult parameter for void methods that don't return a value. These are
   * "fire and forget" methods that are expected to succeed, but if they don't,
   * use the version that takes a JsReturn with JsReturn<Void> to catch exceptions.
   * @return a GWT reference to an actual JavaScript function pointer
   */
  public static native JsResultOrError create(JsReturn<?> deferredFunctionResult) /*-{
    return { // Constructs a JsResultOrError object
      result : function(result) {
        @jsfunction.gwt.JsFunction::invoke(Ljsfunction/gwt/returns/JsReturn;Ljsfunction/gwt/returns/advanced/JsReturnValue;)(deferredFunctionResult, {value:result})
      },
      error : function(error) {
        @jsfunction.gwt.JsFunction::error(Ljsfunction/gwt/returns/JsReturn;Ljsfunction/gwt/types/JsError;)(deferredFunctionResult, error)
      }
    }
  }-*/;
}
