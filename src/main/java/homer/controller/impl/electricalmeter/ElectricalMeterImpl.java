package homer.controller.impl.electricalmeter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import homer.controller.api.electricalmeter.ElectricalMeter;
import homer.model.outlets.Outlet;

/**
 * Implements {@link homer.controller.api.electricalmeter.ElectricalMeter}.
 * 
 * @author Alessandro Monticelli
 */
public final class ElectricalMeterImpl implements ElectricalMeter {
    private List<Outlet> outlets;
    private double globalConsumption;
    private static final double MAX_GLOBAL_CONSUMPTION = 4.0; // kW

    /**
     * Constructor for
     * {@link homer.controller.impl.electricalmeter.ElectricalMeterImpl}.
     * 
     * @param outlets The list of outlets to control.
     */
    public ElectricalMeterImpl(final List<Outlet> outlets) {
        this.globalConsumption = 0.0;
        this.outlets = new CopyOnWriteArrayList<>(outlets);
    }

    @Override
    public List<Outlet> getOutlets() {
        return new CopyOnWriteArrayList<>(this.outlets);
    }

    @Override
    public void setOutlets(final List<Outlet> outlets) {
        this.outlets = new CopyOnWriteArrayList<>(outlets);
    }

    @Override
    public void addOutlet(final Outlet outlet) {
        Objects.requireNonNull(outlet);
        this.outlets.add(outlet);
    }

    @Override
    public void removeOutlet(final Outlet outlet) {
        Objects.requireNonNull(outlet);
        if (this.outlets.contains(outlet)) {
            this.outlets.remove(outlet);
        } else {
            throw new IllegalArgumentException(outlet.getInfo() + " not in 'outlets'");
        }
    }

    @Override
    public void computeConsumption() {
        this.globalConsumption = outlets.stream()
                .mapToDouble(Outlet::getState)
                .sum();
    }

    private void sortForConsumption() {
        this.outlets.sort(Comparator.comparingDouble(Outlet::getState).reversed());
    }

    @Override
    public void cutPowerTo(final Outlet outlet) {
        Objects.requireNonNull(outlet);
        outlet.unplug();
    }

    @Override
    public void checkConsumption() {
        ListIterator<Outlet> iterator = outlets.listIterator();
        this.sortForConsumption();
        while (this.getGlobalConsumption() >= ElectricalMeterImpl.MAX_GLOBAL_CONSUMPTION && iterator.hasNext()) {
            this.cutPowerTo(iterator.next());
        }
    }

    @Override
    public double getGlobalConsumption() {
        this.computeConsumption();
        return this.globalConsumption;
    }
}
