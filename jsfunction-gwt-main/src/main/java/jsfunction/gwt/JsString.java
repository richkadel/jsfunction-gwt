package jsfunction.gwt;

import com.google.gwt.core.client.JavaScriptObject;

public final class JsString extends JavaScriptObject {

  protected JsString() {}
  
  public static native JsString create(String value) /*-{
    return {
      value : value
    }
  }-*/;
  
  public native String stringValue() /*-{ return this.value }-*/;
}
