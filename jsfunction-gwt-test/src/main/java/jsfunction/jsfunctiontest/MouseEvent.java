package jsfunction.jsfunctiontest;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A subset of the JavaScript mouse event fields
 */
final class MouseEvent extends JavaScriptObject {
  
  protected MouseEvent() {}
  
  public native int getX() /*-{ return this.x }-*/;

  public native int getY() /*-{ return this.y }-*/;
}
