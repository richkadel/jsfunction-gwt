package jsfunction.gwt.functions;

import jsfunction.gwt.returns.advanced.JsReturnValue;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayMixed;

public abstract class VarArgsFunctionJsReturn<T extends JavaScriptObject> extends VarArgsFunctionReturn<T> {

  @SuppressWarnings("unchecked")
  public JsReturnValue<T> callAndJsReturnValue(JsArrayMixed args) {
    return (JsReturnValue<T>) JsReturnValue.create(callback(args));
  }
}
