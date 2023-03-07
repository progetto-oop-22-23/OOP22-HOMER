package homer.controller.api.electricalmeter;

import java.util.ArrayList;

import homer.model.outlets.Outlet;

/**
 * An interface that models an electrical meter.
 * The meter can check the global consumption in the house and cut power
 * to the outlets if their consumption is too high.
 */
public interface ElectricalMeter {

    /**
     * Returns all the outlets controlled by the meter.
     * 
     * @return And ArrayList of Outlet
     */
    ArrayList<Outlet> getOutlets();

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
     * Checks the value of the global consumption.
     */
    void checkConsumption();

    /**
     * Sorts the list of outlets from the highest-consuming to the least-consuming.
     */
    void sortForConsumption();

    /**
     * Cuts power to a {@link homer.model.outlets.Outlet}.
     * @param outlet The Outlet which has to be shut.
     */
    void cutPowerTo(Outlet outlet);

    /**
     * Restore the power supply to a {@link homer.model.outlets.Outlet}.
     * @param outlet The outlet which has to be restored.
     */
    void restorePowerTo(Outlet outlet);

}
