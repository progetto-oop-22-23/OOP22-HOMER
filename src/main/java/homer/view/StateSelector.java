package homer.view;


/**
 * Models a component that has a variable state
 */
public interface StateSelector<T> {
    /**
     * 
     * Returns the component's state.
     * @return the component's state.
     */
    T getState();

    /**
     * Sets the component's state.
     * @param state the component's state.
     */
    void setState(T state);
}
