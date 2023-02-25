package homer.api;

public interface ToggleableDevice<S> extends Device<S> {

    boolean isToggled();

    void toggle();

}
