package homer.view;


public interface StateSelector<T> {
    T getState();
    void setState(T state);
}
