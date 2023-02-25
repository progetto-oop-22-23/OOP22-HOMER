package homer.api;

public interface ToggleableDevice<S> extends Device<S> {

    /**
     * 
     * @return Whether the device is on or off.
     */
    boolean isToggled();

    /**
     * Toggles the device state between on and off.
     * If the device was off, it is turned on.
     * If the device was on, it is turned off.
     */
    void toggle();

}
