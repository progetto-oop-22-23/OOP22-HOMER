package homer.controller.api.electricalmeter;

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
     * Sorts the list of outlets from the highest-consuming to the least-consuming.
     */
    void sortForConsumption();

    /**
     * Cuts power to a {@link homer.model.outlets.Outlet}.
     * 
     * @param outlet The Outlet which has to be shut.
     */
    void cutPowerTo(Outlet outlet);

    /**
     * 
     */
    void checkConsumption();

}
