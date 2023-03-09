package homer.temperaturechangers;

import java.time.Duration;

import homer.common.TemperatureFactory;
import homer.model.environment.Environment;

public final class Heater extends AbstractTemperatureChanger implements TemperatureChanger {

    public Heater(double minIntensity, double maxIntensity, double currentIntensity, Environment environment) {
        super(minIntensity, maxIntensity, currentIntensity, environment);
    }

    @Override
    public void updateTick(Duration deltaTime) {
        var oldTemp = this.environment.getTemperature().getCelsius();
        var newTemp = Math.min(this.maxTemperature.getCelsius(), oldTemp + this.intensity * deltaTime.toMillis() * this.STEP);
        this.environment.setTemperature(TemperatureFactory.fromCelsius(newTemp));
    }

}