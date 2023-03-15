package homer.model.temperaturechangers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;
import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import homer.model.environment.Environment;
import homer.model.environment.HomeEnvironment;

class AirConditioningTest {
    private static final double DELTA = 0.001f;
    private static final DeviceInfo INFO = new DeviceInfoImpl(new DeviceIdImpl(), "AIRCONDITIONING");

    @Test 
    void minTemperatureTest() {
        final Temperature temperature = TemperatureFactory.fromCelsius(0);
        final Temperature highTemperature = TemperatureFactory.fromCelsius(100);
        final Environment environment = new HomeEnvironment(temperature, null);
        final TemperatureChanger airConditioning = new AirConditioning(1, 10, environment, INFO);
        airConditioning.setMinTemperature(temperature);
        airConditioning.setMaxTemperature(highTemperature);
        airConditioning.updateTick(Duration.ofSeconds(1));
        assertEquals(0, environment.getTemperature().getCelsius());
    }

    @Test 
    void oneHourUpdateTest() {
        final Temperature minTemperature = TemperatureFactory.fromCelsius(20);
        final Temperature maxTemperature = TemperatureFactory.fromCelsius(100);
        final Environment environment = new HomeEnvironment(maxTemperature, null);
        final TemperatureChanger airConditioning = new AirConditioning(1, 10, environment, INFO);
        final double expected = 99;
        airConditioning.setMinTemperature(minTemperature);
        airConditioning.setMaxTemperature(maxTemperature);
        airConditioning.updateTick(Duration.ofHours(1));
        assertEquals(expected, environment.getTemperature().getCelsius(), AirConditioningTest.DELTA);
    }

    @Test 
    void tenHoursUpdateTest() {
        final Temperature minTemperature = TemperatureFactory.fromCelsius(20);
        final Temperature maxTemperature = TemperatureFactory.fromCelsius(100);
        final Environment environment = new HomeEnvironment(maxTemperature, null);
        final TemperatureChanger airConditioning = new AirConditioning(1, 10, environment, INFO);
        airConditioning.setMinTemperature(minTemperature);
        airConditioning.setMaxTemperature(maxTemperature);
        airConditioning.updateTick(Duration.ofHours(10));
        assertEquals(90, environment.getTemperature().getCelsius(), AirConditioningTest.DELTA);
    }


}
