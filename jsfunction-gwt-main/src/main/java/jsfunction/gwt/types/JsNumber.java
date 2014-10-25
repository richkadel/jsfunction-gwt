package jsfunction.gwt.types;

import com.google.gwt.core.client.JavaScriptObject;

public final class JsNumber extends JavaScriptObject {

  protected JsNumber() {}

  public static native JsNumber create(int value) /*-{
    return {
      value : value
    }
  }-*/;

  public static native JsNumber create(double value) /*-{
    return {
      value : value
    }
  }-*/;

  public native double doubleValue() /*-{ return this.value }-*/;
}
