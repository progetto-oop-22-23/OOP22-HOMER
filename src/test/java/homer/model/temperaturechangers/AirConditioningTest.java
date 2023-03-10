package homer.model.temperaturechangers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import homer.common.Temperature;
import homer.common.TemperatureFactory;
import homer.model.environment.Environment;
import homer.model.environment.HomeEnvironment;

class AirConditioningTest {
    private static final double DELTA = 0.001f;

    @Test 
    void minTemperatureTest() {
        final Temperature temperature = TemperatureFactory.fromCelsius(0);
        final Temperature highTemperature = TemperatureFactory.fromCelsius(100);
        final Environment environment = new HomeEnvironment(temperature);
        final TemperatureChanger airConditioning = new AirConditioning(0, 10, 1, environment, temperature, highTemperature);
        airConditioning.updateTick(Duration.ofSeconds(1));
        assertEquals(0, environment.getTemperature().getCelsius());
    }

    @Test 
    void oneHourUpdateTest() {
        final Temperature minTemperature = TemperatureFactory.fromCelsius(20);
        final Temperature maxTemperature = TemperatureFactory.fromCelsius(100);
        final Environment environment = new HomeEnvironment(maxTemperature);
        final TemperatureChanger airConditioning = new AirConditioning(0, 10, 1, environment, minTemperature, maxTemperature);
        final double expected = 99;
        airConditioning.updateTick(Duration.ofHours(1));
        assertEquals(expected, environment.getTemperature().getCelsius(), AirConditioningTest.DELTA);
    }

    @Test 
    void tenHoursUpdateTest() {
        final Temperature minTemperature = TemperatureFactory.fromCelsius(20);
        final Temperature maxTemperature = TemperatureFactory.fromCelsius(100);
        final Environment environment = new HomeEnvironment(maxTemperature);
        final TemperatureChanger airConditioning = new AirConditioning(0, 10, 1, environment, minTemperature, maxTemperature);
        airConditioning.updateTick(Duration.ofHours(10));
        assertEquals(90, environment.getTemperature().getCelsius(), AirConditioningTest.DELTA);
    }


}
