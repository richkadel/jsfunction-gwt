package jsfunction.gwt.returns.advanced;

import jsfunction.gwt.types.JsBoolean;
import jsfunction.gwt.types.JsNumber;
import jsfunction.gwt.types.JsString;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * This class is for internal JsFunction API use or for more advanced
 * use cases (see the OWF-GWT library on GitHub, and the WidgetProxyFunction
 * class for how we use this class to mimic a native JavaScript version
 * of a JavaScript library).
 * 
 * This wraps the given argument into a JavaScriptObject with the property
 * "value", so both given JavaScriptObject return values and primitive
 * (boolean, number, etc.) values are accessed via "somevar.value".
 * Use JsReturn<T> to create a handler for a deferred result (like 
 * an AJAX request, for example) and this class for implementing your
 * own JavaScript method that will return a value to a JsReturn<T>
 * callback.
 * 
 * This class only allows JavaScriptObject classes (and wrapped primitives
 * like JsBoolean, JsNumber, JsString, etc.) to be returned from JavaScript
 * functions. GWT does allow any Java object to be an argument or return value,
 * but only if that value is handled opaquely. If there is a chance the 
 * invoked method will be transforming objects
 * to/from JSON strings (e.g., to pass objects between different browser
 * scopes or to/from servers) then use this class to implement your return
 * value. If you want to be more lenient to allow other Object Class types,
 * and you are sure they won't be converted to JSON or introspected, us
 * ReturnValue.
 * 
 * Though type-wise, JsReturnValue could be a subclass of ReturnValue<T>,
 * and inherit a lot of the same methods, we would also be inheriting
 * the static "create(Object)" method, which we must avoid. So we do
 * not want to make JsReturnValue a subclass of ReturnValue.
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
