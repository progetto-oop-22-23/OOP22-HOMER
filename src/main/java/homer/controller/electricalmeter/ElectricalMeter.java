package homer.controller.electricalmeter;

import java.util.List;

import homer.model.outlets.Outlet;

/**
 * An interface that models an electrical meter.
 * The meter can check the global consumption in the house and cut power
 * to the outlets if their consumption is too high.
 * 
 * @author Alessandro Monticelli
 */
public interface ElectricalMeter {

    /**
     * Returns all the outlets controlled by the meter.
     * 
     * @return And ArrayList of Outlet
     */
    List<Outlet> getOutlets();

    /**
     * Sets the outlets to the new list of outlets.
     * @param outlets The new list of outlets.
     */
    void setOutlets(List<Outlet> outlets);

    /**
     * Adds an outlet to the list of outlets controlled by the meter.
     * 
     * @param outlet The Outlet to add.
     * 
     * @throws IllegalArgumentException The Outlet must not be in he Outlets list.
     */
    void addOutlet(Outlet outlet);

    /**
     * Removes an outlet to the list of outlets controlled by the meter.
     * 
     * @param outlet The Outlet to remove.
     */
    void removeOutlet(Outlet outlet);

    /**
     * Computes and sets the value of the global consumption.
     */
    void computeConsumption();

    /**
     * Cuts power to a {@link homer.model.outlets.Outlet}.
     * 
     * @param outlet The Outlet which has to be shut.
     */
    void cutPowerTo(Outlet outlet);

    /**
     * Checks the value of global consumption. If it's above {@code ElectricalMeterImpl.MAX_GLOBAL_CONSUMPTION}
     * then cuts the power to the most consuming {@code Outlet}.
     */
    void checkConsumption();

    /**
     * Returns the value of the global consumption (in Wh).
     * @return The value of {@code globalConsumption}.
     */
    double getGlobalConsumption();

    /**
     * Returns the value of the average power consumption (in W).
     * @return The value of {@code averagePower}.
     */
    double getAveragePower();

    /**
     * Sets the value of {@code averagePower}.
     * The {@code averagePower} is calculated in the {@code updateTick()} method,
     * by dividing the {@code globalConsumption} value for the number of hours
     * passed to the method.
     * 
     * @param averagePower the new value of {@code averagePower} 
     */
    void setAveragePower(double averagePower);
}
