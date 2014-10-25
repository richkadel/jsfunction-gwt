package jsfunction.gwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jsfunction.gwt.types.JsArrayMixedWithObject;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayMixed;

public class JsFunctionUtils {

  /**
   * Utility method
   * @param functionArgs a var args of objects (which can be called with primitives as well, using Java auto boxing).
   * The only valid argument types are those that match the JsArrayMixed "set()" methods, that is:
   * double, boolean, string, and JavaScriptObject
   * @return a JsArrayMixed
   */
  public static JsArrayMixed varArgsToMixedArray(Object[] functionArgs) {
    int len = functionArgs.length;
    JsArrayMixed mixedArray = JsArrayMixed.createArray(len).cast();
    for (int i = 0; i < len; i++) {
      Object arg = functionArgs[i];
      if (arg instanceof Number) {
        mixedArray.set(i, ((Number)arg).doubleValue());
      } else if (arg instanceof Boolean) {
        mixedArray.set(i, ((Boolean)arg).booleanValue());
      } else if (arg instanceof String) {
        mixedArray.set(i, (String)arg);
      } else {
        mixedArray.set(i, (JavaScriptObject)arg); 
      }
    }
    return mixedArray;
  }

  /**
   * Utility method
   * @param mixedArray 
   * @return an Object array
   */
  public static Object[] mixedArrayToObjects(JsArrayMixed mixedArray) {
    int len = mixedArray.length();
    List<Object> objects = new ArrayList<Object>(len);
    for (int i = 0; i < len; i++) {
      if (elementIsNumber(mixedArray, i)) {
        objects.add(Double.valueOf(mixedArray.getNumber(i)));
      } else if (elementIsBoolean(mixedArray, i)) {
        objects.add(Boolean.valueOf(mixedArray.getBoolean(i)));
      } else if (elementIsString(mixedArray, i)) {
        objects.add(String.valueOf(mixedArray.getString(i)));
      } else {
        objects.add(String.valueOf(mixedArray.getObject(i)));
      }
    }
    return objects.toArray();
  }

  /**
   * Utility method
   * @param functionArgs a var args of objects (which can be called with primitives as well, using Java auto boxing).
   * The only valid argument types are those that match the JsArrayMixed "set()" methods, that is:
   * double, boolean, string, and JavaScriptObject
   * @return a JsArrayMixed
   */
  public static JsArrayMixedWithObject varArgsToMixedArrayWithObject(Object[] functionArgs) {
    int len = functionArgs.length;
    JsArrayMixedWithObject mixedArray = JsArrayMixed.createArray(len).cast();
    for (int i = 0; i < len; i++) {
      Object arg = functionArgs[i];
      if (arg instanceof Number) {
        mixedArray.set(i, ((Number)arg).doubleValue());
      } else if (arg instanceof Boolean) {
        mixedArray.set(i, ((Boolean)arg).booleanValue());
      } else if (arg instanceof String) {
        mixedArray.set(i, (String)arg);
      } else {
        mixedArray.set(i, arg); // does not force conversion to JavaScriptObject
      }
    }
    return mixedArray;
  }

  /**
   * Utility method
   * @param mixedArray 
   * @return an Object array
   */
  public static Object[] mixedArrayToObjects(JsArrayMixedWithObject mixedArray) {
    int len = mixedArray.length();
    List<Object> objects = new ArrayList<Object>(len);
    for (int i = 0; i < len; i++) {
      if (elementIsNumber(mixedArray, i)) {
        objects.add(Double.valueOf(mixedArray.getNumber(i)));
      } else if (elementIsBoolean(mixedArray, i)) {
        objects.add(Boolean.valueOf(mixedArray.getBoolean(i)));
      } else if (elementIsString(mixedArray, i)) {
        objects.add(String.valueOf(mixedArray.getString(i)));
      } else {
        objects.add(String.valueOf(mixedArray.getJavaObject(i))); // less type checking but should work for JavaScriptObject as well
      }
    }
    return objects.toArray();
  }

  public static <T extends JavaScriptObject> JsArray<T> toJsArray(Collection<T> collection) {
    JsArray<T> jsArray = JsArray.createArray(collection.size()).cast();
    int i = 0;
    for (T item : collection) {
      jsArray.set(i++, item);
    }
    return jsArray;
  }

  private static native boolean elementIsNumber(JsArrayMixed mixedArray, int i) /*-{
    return typeof mixedArray[i] === 'number';
  }-*/;

  private static native boolean elementIsBoolean(JsArrayMixed mixedArray, int i) /*-{
    return typeof mixedArray[i] === 'boolean';
  }-*/;

  private static native boolean elementIsString(JsArrayMixed mixedArray, int i) /*-{
    return typeof mixedArray[i] === 'string';
  }-*/;
}
