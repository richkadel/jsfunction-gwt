package jsfunction.jsfunctiontest;

import jsfunction.gwt.JsFunction;
import jsfunction.gwt.NoArgsFunction;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class JsFunctionTest implements EntryPoint {
  
  private static int count = 1;

  @Override
  public void onModuleLoad() {
    
    testWindowOnclick(new NoArgsFunction() {
      public void callback() {
        log("Made it", count++);
      }
    });
    
    RootPanel.get().add(new Label("Open the browser console and click this window to see test output."));
  }
  
  private void testWindowOnclick(NoArgsFunction noArgsFunction) {
    nativeWindowOnClick(JsFunction.create(noArgsFunction));
  }

  private native void nativeWindowOnClick(JsFunction callback) /*-{
    $wnd.onclick = callback
  }-*/;

  public void log(Object... varargs) {
    nativeLog(JsFunction.varArgsToMixedArray(varargs));
  }
  
  public native void nativeLog(JsArrayMixed arguments) /*-{
    console.log.apply(console, arguments);
  }-*/;
}
