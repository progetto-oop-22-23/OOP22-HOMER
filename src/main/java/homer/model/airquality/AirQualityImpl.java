package homer.model.airquality;

import java.time.Duration;
import java.util.Random;

import homer.api.PoweredDeviceInfo;
import homer.api.PoweredDeviceInfoImpl;
import homer.common.limit.Limit;
import homer.common.time.DurationConverter;
import homer.model.environment.Environment;
import homer.model.environment.HomeEnvironment;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;

/**
 * Every value is updated with random gaussian values, and will always be above zero.
 */
public final class AirQualityImpl implements AirQuality {

    private final Environment environment;
    private final Random random = new Random();
    private double instantConsumption = 0.0;
    private PoweredDeviceInfo power;

    /**
     * 
     * @param environment the environment to associate to the device.
     */
    public AirQualityImpl(final Environment environment) {
        this.power = new PoweredDeviceInfoImpl(10.0, OutletFactory.cOutlet(0));
        this.environment = new HomeEnvironment(environment);
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        final AirQualityState old  = this.environment.getAirQualityState();
        final double newco2 = updatedValue(old.getCO2(), deltaTime);
        final double newpm10 = updatedValue(old.getPM10(), deltaTime);
        final double newtoxic = updatedValue(old.getToxicGasPercentage(), deltaTime);
        final double newpm25 = updatedValue(old.getPM25(), deltaTime);
        this.environment.setAirQualityState(new AirQualityStateImpl(
            Math.max(newco2, 0), Math.max(0, newpm10), Math.max(0, newtoxic), Math.max(0, newpm25)));
    }

    /**
     * 
     * @param previous previous value
     * @param deltaTime time elapsed since last update
     * @return value after time elapsed. Will be greater or equal than 0
     */
    private double updatedValue(final double previous, final Duration deltaTime) {
        return Math.max(0, random.nextGaussian() * deltaTime.toHours() + previous);
    }

    @Override
    public double getInstantConsumption() {
        return this.instantConsumption;
    }

    @Override
    public void setInstantConsumption(double instantConsumption) {
        this.instantConsumption = instantConsumption;
    }

    @Override
    public void plug(Outlet outlet) {
        this.power.setOutlet(outlet);
    }

    @Override
    public PoweredDeviceInfo getPowerInfo() {
        return new PoweredDeviceInfoImpl(this.power.getMaxConsumption(), this.power.getOutlet());
    }

    /**
     * 
     * @param deltaTime the delta time elapsed from last tick.
     */
    protected void updateConsumption(final Duration deltaTime) {
        final double maxConsumption = this.getPowerInfo().getMaxConsumption();
        final double hours = DurationConverter.toHours(deltaTime) / 2;
        final double intensity = Math.sin(hours * 0.1); 
        final double newConsumption = instantConsumption + intensity * hours;
        this.instantConsumption = Limit.clamp(newConsumption, this.getPowerInfo().getMinConsumption(), maxConsumption);
    }
}
