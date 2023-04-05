package homer.controller.impl.electricalmeter;

import java.time.Duration;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import homer.api.PoweredDevice;
import homer.common.time.DurationConverter;
import homer.controller.DeviceManager;
import homer.controller.DeviceManagerImpl;
import homer.controller.api.electricalmeter.ElectricalMeter;
import homer.core.DiscreteObject;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletState;

/**
 * Implements {@link homer.controller.api.electricalmeter.ElectricalMeter}.
 * 
 * @author Alessandro Monticelli
 */
public final class ElectricalMeterImpl implements ElectricalMeter, DiscreteObject {
    private CopyOnWriteArrayList<Outlet> outlets;
    private double globalConsumption;
    private double averagePower;
    private DeviceManager deviceManager;
    private static final double MAX_GLOBAL_CONSUMPTION = 4000; // Watts

    /**
     * Constructor for
     * {@link homer.controller.impl.electricalmeter.ElectricalMeterImpl}.
     * 
     * @param outlets The list of outlets to control.
     * @param deviceManager The device manager.
     */
    public ElectricalMeterImpl(final List<Outlet> outlets, final DeviceManagerImpl deviceManager) {
        this.globalConsumption = 0.0;
        this.averagePower = 0.0;
        this.outlets = new CopyOnWriteArrayList<>(outlets);
        this.deviceManager = deviceManager;
    }

    /**
     * Constructor for ElectricalMeterImpl.
     */
    public ElectricalMeterImpl() {
        this.globalConsumption = 0.0;
        this.averagePower = 0.0;
        this.outlets = new CopyOnWriteArrayList<>();
    }

    /**
     * Sets the {@code deviceManager}.
     * @param deviceManager the new {@code deviceManager}.
     */
    public void setDeviceManger(final DeviceManager deviceManager) {
        this.deviceManager = deviceManager;
    }

    /**
     * Returns the {@code deviceManager}.
     * @return the {@code deviceManager}.
     */
    public DeviceManager getDeviceManager() {
        return this.deviceManager;
    }

    /**
     * Gathers all the outlets from the {@code PoweredDevices}.
     */
    private synchronized void setPoweredDeviceOutlets() {
        final Set<Outlet> existingOutlets = new HashSet<>(this.outlets); // Create a copy of existing outlets to avoid
                                                                         // mutating the original list
        final List<Outlet> newOutlets = this.deviceManager.getDevices().values().stream()
                .filter(device -> device instanceof PoweredDevice)
                .map(device -> ((PoweredDevice) device).getPowerInfo().getOutlet())
                .filter(outlet -> !existingOutlets.contains(outlet))
                .collect(Collectors.toList()); // Collect new outlets into a list
        this.outlets.addAllAbsent(newOutlets);
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
        this.globalConsumption = this.getOutlets().stream()
                .mapToDouble(outlet -> outlet.getState().getPower().get())
                .sum();
    }

    /**
     * Sorts {@code this.outlets} from the most consuming Outlet to the least one.
     */
    private void sortOutletsForConsumption() {
        outlets.sort(Comparator.comparingDouble(
                outlet -> ((Outlet) outlet).getState().getPower().get()).reversed());
    }

    @Override
    public void cutPowerTo(final Outlet outlet) {
        Objects.requireNonNull(outlet);
        outlet.setState(new OutletState().addValue(0.0));
    }

    @Override
    public void checkConsumption() {
        final ListIterator<Outlet> iterator = outlets.listIterator();
        double globalConsumption = this.getGlobalConsumption();
        if (globalConsumption >= ElectricalMeterImpl.MAX_GLOBAL_CONSUMPTION) {
            this.sortOutletsForConsumption();
            while (globalConsumption >= ElectricalMeterImpl.MAX_GLOBAL_CONSUMPTION && iterator.hasNext()) {
                this.cutPowerTo(iterator.next());
                globalConsumption = this.getGlobalConsumption();
            }
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

    private void computeAveragePower(final Duration deltaTime) {
        final double hours = DurationConverter.toHours(deltaTime);
        this.setAveragePower(this.getGlobalConsumption() / hours);
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        this.setPoweredDeviceOutlets();
        this.checkConsumption();
        this.computeAveragePower(deltaTime);
    }

}
