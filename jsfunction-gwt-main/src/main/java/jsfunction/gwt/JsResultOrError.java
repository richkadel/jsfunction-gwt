package jsfunction.gwt;

import com.google.gwt.core.client.JavaScriptObject;

public final class JsResultOrError extends JavaScriptObject {
  
  protected JsResultOrError() {}
  
  public native JsFunction resultFunction() /*-{ return this.result }-*/;
  public native JsFunction failureFunction() /*-{ return this.error }-*/;
}
