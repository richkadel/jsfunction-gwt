package jsfunction.gwt.returns.advanced;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * This class is for internal JsFunction API use or for more advanced
 * use cases (see the OWF-GWT library on GitHub, and the WidgetProxyFunction
 * class for how we use this class to mimic a native JavaScript version
 * of a JavaScript library).
 * 
 * This class allows Java Objects to be returned from JavaScript methods, 
 * which is allowed in GWT but only if those values are handled opaquely.
 * If there is a chance the invoked method will be transforming objects
 * to/from JSON strings (e.g., to pass objects between different browser
 * scopes or to/from servers) then you should use JsReturnValue
 * to restrict return values to JavaScriptObject or accepted primitives.
 */
public final class ReturnValue<T> extends JavaScriptObject {
  
  protected ReturnValue() {}

  public static native ReturnValue<Object> create(Object value) /*-{
    return {
      value : value
    }
  }-*/;
  
  public static native ReturnValue<JavaScriptObject> create(JavaScriptObject value) /*-{
    return {
      value : value
    }
  }-*/;
  
  public static native ReturnValue<String> create(String value) /*-{
    return {
      value : value
    }
  }-*/;
  
  public static ReturnValue<Boolean> create(Boolean value) {
    return create(value.booleanValue());
  }
  
  public static ReturnValue<Integer> create(Byte value) {
    return create(value.intValue());
  }
  
  public static ReturnValue<Integer> create(Short value) {
    return create(value.intValue());
  }
  
  public static ReturnValue<Integer> create(Integer value) {
    return create(value.intValue());
  }
  
  public static ReturnValue<Double> create(Number value) {
    return create(value.doubleValue());
  }
  
  public static native ReturnValue<Boolean> create(boolean value) /*-{
    return {
      value : value
    }
  }-*/;
  
  public static native ReturnValue<Integer> create(int value) /*-{
    return {
      value : value
    }
  }-*/;
  
  public static native ReturnValue<Double> create(double value) /*-{
    return {
      value : value
    }
  }-*/;
  
  @SuppressWarnings("unchecked")
  public T get() {
    if (isNumber()) {
      return (T)Double.valueOf(asDouble());
    } else if (isBoolean()) {
      return (T)Boolean.valueOf(asBoolean());
    } else if (isString()) {
      return (T)String.valueOf(asString());
    } else {
      return (T)asJavaScriptObject();
    }
  }
  
  public native Object asObject() /*-{
    return this.value;
  }-*/;
  
  public native JavaScriptObject asJavaScriptObject() /*-{
    // I don't know what this will do to Java Objects, and get() cannot be overridden, so I will just return the object reference
//    return this.value != null ? Object(this.value) : null;
    return this.value;
  }-*/;
  
  public native String asString() /*-{
    return String(this.value);
  }-*/;
  
  public native double asDouble() /*-{
    return Number(this.value);
  }-*/;
  
  public native int asInt() /*-{
    return Number(this.value);
  }-*/;
  
  public native boolean asBoolean() /*-{
    return Boolean(this.value);
  }-*/;
  
  protected native boolean isNumber() /*-{
    return typeof this.value === 'number';
  }-*/;

  protected native boolean isBoolean() /*-{
    return typeof this.value === 'boolean';
  }-*/;

  protected native boolean isString() /*-{
    return typeof this.value === 'string';
  }-*/;
}
