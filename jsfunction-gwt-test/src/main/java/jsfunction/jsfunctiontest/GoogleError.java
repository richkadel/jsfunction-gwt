package jsfunction.jsfunctiontest;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A subset of the Google error fields
 */
final class GoogleError extends JavaScriptObject {
  
  protected GoogleError() {}
  
  public native int getCode() /*-{ return this.error.code }-*/;

  public native String getMessage() /*-{ return this.error.message }-*/;
}
