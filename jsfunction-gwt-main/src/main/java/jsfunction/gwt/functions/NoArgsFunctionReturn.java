package jsfunction.gwt.functions;

import jsfunction.gwt.returns.advanced.ReturnValue;

public abstract class NoArgsFunctionReturn<T> {

  @SuppressWarnings("unchecked")
  public ReturnValue<T> callAndReturnValue() {
    return (ReturnValue<T>) ReturnValue.create(callback());
  }

  public abstract T callback();
}
