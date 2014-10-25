package jsfunction.gwt.types;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Wraps JavaScript "Error" object or an object created inline from a Java Throwable.
 * @author richkadel
 */
public final class JsError extends JavaScriptObject {
  
  protected JsError() {}
  
  public static JsError create(Throwable t) {
    return create(t.getClass().getSimpleName(), t.getMessage(), stackTraceAsString(t));
  }
  
  private static native JsError create(String name, String message, String stack) /*-{
    return {
      name : name,
      message : message,
      stack : stack
    }
  }-*/;

  private static String stackTraceAsString(Throwable t) {
    StringBuffer sb = new StringBuffer();
    for (StackTraceElement ste : t.getStackTrace()) {
      sb.append(ste.toString());
      sb.append('\n');
    }
    return sb.toString();
  }

  public native String getName() /*-{ return this.name }-*/;
  public native String getMessage() /*-{ return this.message }-*/;
  
  public native String getStack() /*-{ 
    if (this.stack) {
      return this.stack 
    } else {
      return ""
    }
  }-*/;
}
