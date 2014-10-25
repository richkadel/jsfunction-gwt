package jsfunction.jsfunctiontest;

import jsfunction.gwt.functions.EventListener;
import jsfunction.gwt.functions.NoArgsFunction;
import jsfunction.gwt.functions.VarArgsFunction;
import jsfunction.gwt.returns.JsReturn;
import jsfunction.gwt.types.JsError;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * This is the test harness and shows an example of what a typical API use might use
 * when passing function references into a GWT JavaScript Native Interface (JSNI)
 * wrapper API that requires JavaScript function callbacks.
 * 
 * @author richkadel
 */
public class JsFunctionTest implements EntryPoint {
  
  ExampleJSNIAPI api = new ExampleJSNIAPI();
    
  private static int count = 1;

  @Override
  public void onModuleLoad() {
    
    // FIRST the simplest case. Passing in a function that takes no arguments and returns no results,
    // as a simple callback. (Note window.onclick() does have an optional event, but we don't need
    // to look at it. See the other mouse event handlers below for an example of using the event.)
    
    api.testWindowOnClick(new NoArgsFunction() { // window.onclick() callback
      public void callback() {
        api.log("Made it", count++); // console.log() takes any number of arguments. We wrap JS varargs in Object... when needed
        api.log("Now we will test an AJAX callback function and we SHOULD see two sets of: 401 \"Login Required\"");
        
        api.makeGWTAjaxCall(new JsReturn<GoogleError>() { // We show AJAX twice. 
                                                          // Once with pure GWT Java, but leveraging JsFunction 
                                                          // for a smarter API that knows the JSON object to be returned
          public void onReturn(GoogleError returnValue) {
            api.log(returnValue.getCode(), returnValue.getMessage());
          }
          public void onError(JsError jsError) {
            api.log("Caught an error!: ", jsError.getMessage());
          }
        });
        
        api.makeJavaScriptAjaxCall(new JsReturn<GoogleError>() { // This one is going directly to JavaScript XMLHttpRequest.
                                                                 // Looks the same here, but the differences are in ExampleJSNIAPI
          public void onReturn(GoogleError returnValue) {
            api.log(returnValue.getCode(), returnValue.getMessage());
          }
          public void onError(JsError jsError) {
            api.log("Caught an error!: ", jsError.getMessage());
          }
        });
      }
    });
    
    api.testWindowOnMouseMove(new VarArgsFunction() { // Depending on the JavaScript library, a callback may take any number of arguments.
                                                      // We convert JavaScripts vararg-style "arguments" array into a GWT JsArrayMixed.
      public void callback(JsArrayMixed args) {
        MouseEvent mouseEvent = args.getObject(0);
        api.log("mouse move", "x="+mouseEvent.getX(), "y="+mouseEvent.getY());
      }
    });
    
    api.testMouseDownWithEvent(new EventListener<MouseEvent>() { // Alternatively, callbacks with exactly one argument are often the pattern for EventListeners
                                                                 // so the JsFunction EventListener<> function requires no vararg extraction and no casting.
      public void callback(MouseEvent mouseEvent) {
        api.log("mouse down event", "x="+mouseEvent.getX(), "y="+mouseEvent.getY());
      }
    });
    
    RootPanel.get().add(new Label("Open the browser console and click this window to see test output."));
  }
}
