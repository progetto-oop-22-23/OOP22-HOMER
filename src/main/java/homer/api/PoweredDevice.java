package homer.api;

import homer.core.DiscreteObject;

/**
 * Models a {@link homer.api.Device} that consumes power.
 * Every PoweredDevice must be plugged into a {@link homer.model.outlets.Outlet}.
 * 
 * @param <S> The device state.
 * 
 * @author Alessandro Monticelli
 */
public interface PoweredDevice<S> extends Device<S>, DiscreteObject {

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
     * Returns the istant device's consumption.
     * 
     * @return istantConsumption
     */
    double getIstantConsumption();

    /**
     * Sets the istant device's consumption.
     * 
     * @param istantConsumption The new istantConsumption value.
     */
    void setIstantConsumption(double istantConsumption);
}
