package jsfunction.gwt;

public abstract class NoArgsFunctionReturn<T> {

  @SuppressWarnings("unchecked")
  public ReturnValue<T> callAndReturnValue() {
    return (ReturnValue<T>) ReturnValue.create(callback());
  }

  public abstract T callback();
}
