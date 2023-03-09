package homer.model.temperaturechangers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import homer.common.Temperature;
import homer.common.TemperatureFactory;
import homer.model.environment.Environment;
import homer.model.environment.HomeEnvironment;

class AirConditioningTest {
    final Temperature temperature = TemperatureFactory.fromCelsius(0);
    final Temperature highTemperature = TemperatureFactory.fromCelsius(100);
    final Environment environment = new HomeEnvironment(temperature);
    final TemperatureChanger airConditioning = new AirConditioning(0, 10, 1, environment, temperature, highTemperature);

    @Test 
    void minTemperatureTest() {
        airConditioning.updateTick(Duration.ofSeconds(1));
        assertEquals(0, environment.getTemperature().getCelsius());
    }
}
