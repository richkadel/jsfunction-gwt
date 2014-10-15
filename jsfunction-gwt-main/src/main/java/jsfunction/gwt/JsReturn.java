package jsfunction.gwt;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class JsReturn<T extends JavaScriptObject> {

  public void onReturn(JsReturnValue<T> returnValue) {
    onReturn(returnValue.get());
  }

  public abstract void onReturn(T returnValue);
  
  public abstract void onError(JsError jsError);
}
