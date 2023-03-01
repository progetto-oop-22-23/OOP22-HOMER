package homer.api;

public interface ElectricalMeter<S> {

    /**
     * Cuts power to the selected device.
     * 
     * @param Device The device(s) whose power should be cut off.
     */
    void cutPowerTo(S Device);

    /**
     * Checks if the global consumption of all devices
     * is still under maxConsumption value.
     */
    void checkConsumption();

    /**
     * Returns the value of globalConsumption, which indicates the
     * total electrical absorption of all currently active devices.
     * 
     * @return value of globalConsumption .
     */
    double getGlobalConsumption();

    /**
     * Sets the value of globalConsumption
     * 
     * @param globalConsumption The new value for globalConsumption
     */
    void setGlobalConsumption(double globalConsumption);
    /**
     * Returns the value of maxConsumption, which indicates the
     * upper bound for globalConsumption.
     * @return value of maxConsumption.
     */
    double getMaxConsumption();

    /**
     * Sets the value of maxConsumption
     * @param maxConsumption The new value for globalConsumption
     */
    void setMaxConsumption(double maxConsumption);
}
