package jsfunction.gwt;

/**
 * Similar to VarArgsFunction, but this method takes only one argument--the "event object"--following
 * typical design patterns for Java and JavaScript event listeners. VarArgsFunction
 * could also be used for these kinds of methods, but the developer would have to extract
 * the first element (element 0) from the JsArrayMixed when returned.
 * @param <T>
 */
public interface EventListener<T> {

  void callback(T event);
}
