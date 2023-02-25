package homer.api;

public interface AdjustableDevice<S> extends Device<S> {

    S getMinValue();

    S getMaxValue();

    S getValue();

    void setValue(S value);

}
