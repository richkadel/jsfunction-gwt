package jsfunction.gwt.functions;

import jsfunction.gwt.returns.advanced.ReturnValue;

import com.google.gwt.core.client.JsArrayMixed;

public abstract class VarArgsFunctionReturn<T> {

  @SuppressWarnings("unchecked")
  public ReturnValue<T> callAndReturnValue(JsArrayMixed args) {
    return (ReturnValue<T>) ReturnValue.create(callback(args));
  }

  public abstract T callback(JsArrayMixed args);
}
