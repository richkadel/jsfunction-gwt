package jsfunction.gwt;

/**
 * Allows you to call an asynchronous method but still handle potential exceptions
 * via an "onError" callback. If you override "onReturn" (not required), you can be
 * notified after the method successfully executes.
 */
public abstract class JsReturnVoid extends JsReturn<JsVoid> {

  @Override
  public void onReturn(JsVoid returnValue) {
  }
}
