package homer.api;

import java.time.Duration;

/**
 * {@link homer.api.PoweredDevice} implementation.
 * 
 */

public class PoweredDeviceImpl implements PoweredDevice {

    private final double minConsumption;
    private final double maxConsumption;
    private double istantConsumption;

    public PoweredDeviceImpl(final double minConsumption, final double maxConsumption) {
        this.minConsumption = minConsumption;
        this.maxConsumption = maxConsumption;
        this.istantConsumption = 0.0;
    }

    @Override
    public void updateTick(Duration deltaTime) {
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
        return this.istantConsumption;
    }

    @Override
    public void setIstantConsumption(double istantConsumption) {
        this.istantConsumption = istantConsumption;
    }

}
