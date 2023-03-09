package homer.temperaturechangers;

import java.time.Duration;

import homer.common.TemperatureFactory;
import homer.model.environment.Environment;

public class AC extends AbstractTemperatureChanger implements TemperatureChanger {

    public AC(double minIntensity, double maxIntensity, double currentIntensity, Environment environment) {
        super(minIntensity, maxIntensity, currentIntensity, environment);
    }

    @Override
    public void updateTick(Duration deltaTime) {
        var oldTemp = this.environment.getTemperature().getCelsius();
        var newTemp = Math.max(this.minTemperature.getCelsius(), oldTemp - this.intensity * deltaTime.toMillis() * this.STEP);
        this.environment.setTemperature(TemperatureFactory.fromCelsius(newTemp));
    }

}
