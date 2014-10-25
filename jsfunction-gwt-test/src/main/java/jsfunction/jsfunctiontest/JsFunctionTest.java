package jsfunction.jsfunctiontest;

import jsfunction.gwt.EventListener;
import jsfunction.gwt.JsError;
import jsfunction.gwt.JsFunction;
import jsfunction.gwt.JsResultOrError;
import jsfunction.gwt.JsReturn;
import jsfunction.gwt.NoArgsFunction;
import jsfunction.gwt.VarArgsFunction;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class JsFunctionTest implements EntryPoint {
  
  private static int count = 1;

  @Override
  public void onModuleLoad() {
    
    testWindowOnClick(new NoArgsFunction() {
      public void callback() {
        log("Made it", count++);
        log("Now we will test an AJAX callback function and we SHOULD see two sets of: 401 \"Login Required\"");
        
        makeGWTAjaxCall(new JsReturn<GoogleError>() {
          public void onReturn(GoogleError returnValue) {
            log(returnValue.getCode(), returnValue.getMessage());
          }
          public void onError(JsError jsError) {
            log("Caught an error!: ", jsError.getMessage());
          }
        });
        
        makeJavaScriptAjaxCall(new JsReturn<GoogleError>() {
          public void onReturn(GoogleError returnValue) {
            log(returnValue.getCode(), returnValue.getMessage());
          }
          public void onError(JsError jsError) {
            log("Caught an error!: ", jsError.getMessage());
          }
        });
      }
    });
    
    testWindowOnMouseMove(new VarArgsFunction() {
      public void callback(JsArrayMixed args) {
        MouseEvent mouseEvent = args.getObject(0);
        log("mouse move", "x="+mouseEvent.getX(), "y="+mouseEvent.getY());
      }
    });
    
    testMouseDownWithEvent(new EventListener<MouseEvent>() {
      public void callback(MouseEvent mouseEvent) {
        log("mouse down event", "x="+mouseEvent.getX(), "y="+mouseEvent.getY());
      }
    });
    
    RootPanel.get().add(new Label("Open the browser console and click this window to see test output."));
  }
  
  /**
   * This method takes a Java-oriented NoArgsFunction callback handle and
   * converts it into a JsFunction, which is actually a JavaScript-ready
   * pointer to a JavaScript function.
   * @param noArgsFunction
   */
  private void testWindowOnClick(NoArgsFunction noArgsFunction) {
    nativeWindowOnClick(JsFunction.create(noArgsFunction));
  }

  private native void nativeWindowOnClick(JsFunction callback) /*-{
    $wnd.onclick = callback
  }-*/;

  private void testWindowOnMouseMove(VarArgsFunction varArgsFunction) {
    nativeWindowOnMouseMove(JsFunction.create(varArgsFunction));
  }

  private native void nativeWindowOnMouseMove(JsFunction varArgsCallback) /*-{
    $wnd.onmousemove = varArgsCallback
  }-*/;

  private void testMouseDownWithEvent(EventListener<MouseEvent> eventListener) {
    nativeMouseDownWithEvent(JsFunction.create(eventListener));
  }

  private native void nativeMouseDownWithEvent(JsFunction eventListener) /*-{
    $wnd.onmousedown = eventListener
  }-*/;

  public void log(Object... varargs) {
    nativeLog(JsFunction.varArgsToMixedArray(varargs));
  }
  
  public native void nativeLog(JsArrayMixed arguments) /*-{
    console.log.apply(console, arguments);
  }-*/;
  
  public void makeGWTAjaxCall(final JsReturn<GoogleError> result) {
    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "https://www.googleapis.com/appstate/v1/states/");
      // This will normally return an error without an authenticated client id
      // but that's OK for testing.  The error is also in JSON, so we'll pretend
      // it is our expected response.
  
    try {
			builder.sendRequest(null, new RequestCallback() {
        
        public void onError(Request request, Throwable caught) {
          result.onError(JsError.create(caught));
        }
  
        public void onResponseReceived(Request request, Response response) {
          if (response.getStatusCode() == 200) { // normally this is what we want, but not for this test
            result.onError(JsError.create(new Throwable("gwt: Was not expecting success")));
          } else if (response.getStatusCode() == 401){
						GoogleError googleError = JsonUtils.safeEval(response.getText());
            result.onReturn(googleError);
          } else {
            result.onError(JsError.create(new Throwable("gwt: Unexpected error status code "+response.getStatusCode())));
          }
        }
      });
    } catch (RequestException e) {
      result.onError(JsError.create(e));
    }
  }
  
  public void makeJavaScriptAjaxCall(final JsReturn<GoogleError> result) {
    makeNativeJavaScriptAjaxCall(JsFunction.create(result));
  }

  private native void makeNativeJavaScriptAjaxCall(JsResultOrError jsResult) /*-{
    
    var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onload = function() {
      try {
        if (xmlhttp.status == 200) {
          throw new Error("js: Was not expecting success");
        } else if (xmlhttp.status == 401) {
          var googleError = JSON.parse(xmlhttp.responseText);
          jsResult.result(googleError);
        } else {
          throw new Error("js: Unexpected error status code "+xmlhttp.status);
        }
      } catch (e) {
        jsResult.error(e)
      }
    }
    
    xmlhttp.onerror = function() {
      jsResult.error(new Error("XMLHttpRequest error"));
    }
    
    xmlhttp.open("GET", "https://www.googleapis.com/appstate/v1/states/", true);
    xmlhttp.send();
  }-*/;
}
