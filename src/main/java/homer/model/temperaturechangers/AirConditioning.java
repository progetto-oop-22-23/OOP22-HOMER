package homer.model.temperaturechangers;

import java.time.Duration;

import homer.common.Temperature;
import homer.common.TemperatureFactory;
import homer.model.environment.Environment;

public class AirConditioning extends AbstractTemperatureChanger {


    public AirConditioning(double minIntensity, double maxIntensity, double currentIntensity, Environment environment,
            Temperature minTemperature, Temperature maxTemperature) {
        super(minIntensity, maxIntensity, currentIntensity, environment, minTemperature, maxTemperature);
    }

    @Override
    public void updateTick(Duration deltaTime) {
        var oldTemp = this.environment.getTemperature().getCelsius();
        var newTemp = Math.max(this.minTemperature.getCelsius(), oldTemp - this.intensity * deltaTime.toMillis() * this.STEP);
        this.environment.setTemperature(TemperatureFactory.fromCelsius(newTemp));
    }

}
