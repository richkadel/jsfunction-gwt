package jsfunction.gwt;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * This class theoretically allows Java Objects to be returned from JavaScript methods, 
 * which is allowed in GWT but only if those values are handled opaquely.
 * If there is a chance the invoked method will be transforming objects
 * to/from JSON strings (e.g., to pass objects between different browser
 * scopes or to/from servers) then you should restrict your JsReturnValue
 * implementations to JavaScriptObject or accepted primitives.
 * It is difficult to construct a generic type that can accept
 * JavaScriptObject, String, Boolean, and Number but not any Object.
 * To try to avoid Java Objects in THIS class, the create(Object) method
 * is excluded, but made available in a subclass "ReturnValue<T>" that
 * can be used when the ReturnValues are known to NOT be JSON serialized.
 */
public class JsReturnValue<T extends JavaScriptObject> extends JavaScriptObject {
  
  protected JsReturnValue() {}

  public static final native JsReturnValue<JavaScriptObject> create(JavaScriptObject value) /*-{
    return {
      value : value
    }
  }-*/;
  
  public static final JsReturnValue<JsString> create(String value) {
    return create(JsString.create(value));
  }
  
  public static final native JsReturnValue<JsString> create(JsString value) /*-{
    return {
      value : value
    }
  }-*/;
  
  
  public static JsReturnValue<JsNumber> create(Number value) {
    return create(value.doubleValue());
  }
  
  public static final native JsReturnValue<JsBoolean> create(JsBoolean value) /*-{
    return {
      value : value
    }
  }-*/;
  
  public static final JsReturnValue<JsBoolean> create(boolean value) {
    return JsReturnValue.create(JsBoolean.create(value));
  }
  
  public static final native JsReturnValue<JsNumber> create(JsNumber value) /*-{
    return {
      value : value
    }
  }-*/;
  
  public static final JsReturnValue<JsNumber> create(int value) {
    return JsReturnValue.create(JsNumber.create(value));
  }
  
  public static final JsReturnValue<JsNumber> create(double value) {
    return JsReturnValue.create(JsNumber.create(value));
  }
  
  public final native T get() /*-{
    return this.value
  }-*/;
  
  public final native JavaScriptObject asJavaScriptObject() /*-{
    // I don't know what this will do to Java Objects, and get() cannot be overridden, so I will just return the object reference
//    return this.value != null ? Object(this.value) : null;
    return this.value;
  }-*/;
  
  public final native String asString() /*-{
    if (this.value.value) {
      return String(this.value.value);
    } else {
      return this.value;
    }
  }-*/;
  
  public final native double asDouble() /*-{
    return Number(this.value.value);
  }-*/;
  
  public final native int asInt() /*-{
    return Number(this.value.value);
  }-*/;
  
  public final native boolean asBoolean() /*-{
    return Boolean(this.value.value);
  }-*/;
  
  protected final native boolean isNumber() /*-{
    return this.value.value && typeof this.value.value === 'number';
  }-*/;

  protected final native boolean isBoolean() /*-{
    return this.value.value && typeof this.value.value === 'boolean';
  }-*/;

  protected final native boolean isString() /*-{
    return this.value.value && typeof this.value.value === 'string';
  }-*/;
}
