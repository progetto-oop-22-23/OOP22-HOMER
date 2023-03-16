package homer.api;

/**
 * Models a {@link homer.api.Device} that consumes power.
 * Every PoweredDevice must be plugged into a {@link homer.model.outlets.Outlet}.
 * 
 * @author Alessandro Monticelli
 */
public interface PoweredDevice {

    /**
     * Returns the minimum device's consumption.
     * 
     * @return minConsumption
     */
    double getMinConsumption();

    /**
     * Returns the maximum device's consumption.
     * 
     * @return maxConsumption
     */
    double getMaxConsumption();

    /**
     * Returns the instant device's consumption.
     * 
     * @return instantConsumption
     */
    double getIstantConsumption();

    /**
     * Sets the instant device's consumption.
     * 
     * @param instantConsumption The new instantConsumption value.
     */
    void setIstantConsumption(double instantConsumption);
}
