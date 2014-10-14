package jsfunction.jsfunctiontest;

import jsfunction.JsFunction;
import jsfunction.NoArgsFunction;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArrayMixed;
//import com.google.gwt.user.client.ui.RootPanel;

public class JsFunctionTest implements EntryPoint {
  
  private static int count = 1;

  @Override
  public void onModuleLoad() {
    
    testWindowOnload(new NoArgsFunction() {
      public void callback() {
        log("Made it ", count++);
      }
    });
    
    //TabPanel tabs = new TabPanel();
    //RootPanel.get().add(tabs);
  }
  
  private void testWindowOnload(NoArgsFunction noArgsFunction) {
    nativeWindowOnLoad(JsFunction.create(noArgsFunction));
  }

  private native void nativeWindowOnLoad(JsFunction callback) /*-{
    $wnd.onload(callback);
  }-*/;

  public void log(Object... varargs) {
    nativeLog(JsFunction.varArgsToMixedArray(varargs));
  }
  
  public native void nativeLog(JsArrayMixed arguments) /*-{
    console.log.apply(null, arguments);
  }-*/;
}
