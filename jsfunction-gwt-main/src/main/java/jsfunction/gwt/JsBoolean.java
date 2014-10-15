package jsfunction.gwt;

import com.google.gwt.core.client.JavaScriptObject;

public final class JsBoolean extends JavaScriptObject {

  protected JsBoolean() {}
  
  public static native JsBoolean create(boolean value) /*-{
    return {
      value : value
    }
  }-*/;

  public native boolean booleanValue() /*-{ return this.value }-*/;
}
