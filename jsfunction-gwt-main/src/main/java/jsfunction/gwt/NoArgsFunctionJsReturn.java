package jsfunction.gwt;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class NoArgsFunctionJsReturn<T extends JavaScriptObject> extends NoArgsFunctionReturn<T> {
  
  @SuppressWarnings("unchecked")
  public JsReturnValue<T> callAndJsReturnValue() {
    return (JsReturnValue<T>) JsReturnValue.create(callback());
  }
}
