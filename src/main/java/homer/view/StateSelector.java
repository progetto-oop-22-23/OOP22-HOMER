package homer.view;

/**
 * Models a component that has a variable state.
 * @param <T> the type that is returned and set.
 */
public interface StateSelector<T> {

    /**
     * 
     * Returns the component's state.
     * 
     * @return the component's state.
     */
    T getState();

    /**
     * 
     * @param state the component's new state.
     */
    void setState(T state);
}
