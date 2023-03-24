package homer.controller.impl.electricalmeter;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import homer.controller.api.electricalmeter.ElectricalMeter;
import homer.core.DiscreteObject;
import homer.model.outlets.Outlet;

/**
 * Implements {@link homer.controller.api.electricalmeter.ElectricalMeter}.
 * 
 * @author Alessandro Monticelli
 */
public final class ElectricalMeterImpl implements ElectricalMeter, DiscreteObject {
    private List<Outlet> outlets;
    private double globalConsumption;
    private double averagePower;
    private static final double MAX_GLOBAL_POWER = 4000; // Watts

    /**
     * Constructor for
     * {@link homer.controller.impl.electricalmeter.ElectricalMeterImpl}.
     * 
     * @param outlets The list of outlets to control.
     */
    public ElectricalMeterImpl(final List<Outlet> outlets) {
        this.globalConsumption = 0.0;
        this.averagePower = 0.0;
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
            throw new IllegalArgumentException("Outlet not in 'outlets'");
        }
    }

    @Override
    public void computeConsumption() {
        this.globalConsumption = outlets.stream()
                .mapToDouble(Outlet::getState)
                .sum();
    }

    private void sortOutletsForConsumption() {
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
        this.sortOutletsForConsumption();
        while (this.getGlobalConsumption() >= ElectricalMeterImpl.MAX_GLOBAL_POWER && iterator.hasNext()) {
            this.cutPowerTo(iterator.next());
        }
    }

    @Override
    public double getGlobalConsumption() {
        this.computeConsumption();
        return this.globalConsumption;
    }

    @Override
    public double getAveragePower() {
        return this.averagePower;
    }

    @Override
    public void setAveragePower(final double averagePower) {
        this.averagePower = averagePower;
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        final double deltaHours = deltaTime.toSeconds() / 3600;
        this.checkConsumption();
        final double globalConsumption = this.getGlobalConsumption();
        final double averagePower = globalConsumption / deltaHours;
        this.setAveragePower(averagePower);
    }
}
