package homer.api;

import java.time.Duration;

/**
 * {@link homer.api.PoweredDevice} implementation.
 * 
 */

public final class PoweredDeviceImpl implements PoweredDevice {

    private final double minConsumption;
    private final double maxConsumption;
    private double instantConsumption;

    /**
     * PoweredDeviceImpl constructor.
     * @param minConsumption min consumption
     * @param maxConsumption max consumption
     */
    public PoweredDeviceImpl(final double minConsumption, final double maxConsumption) {
        this.minConsumption = minConsumption;
        this.maxConsumption = maxConsumption;
        this.instantConsumption = 0.0;
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTick'");
    }

    @Override
    public double getMinConsumption() {
        return this.minConsumption;
    }

    @Override
    public double getMaxConsumption() {
        return this.maxConsumption;
    }

    @Override
    public double getIstantConsumption() {
        return this.instantConsumption;
    }

    @Override
    public void setIstantConsumption(final double instantConsumption) {
        this.instantConsumption = instantConsumption;
    }

}
