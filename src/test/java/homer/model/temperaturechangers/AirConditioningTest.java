package homer.model.temperaturechangers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import homer.model.airquality.AirQualityState;
import homer.model.airquality.AirQualityStateFactory;
import homer.model.environment.Environment;
import homer.model.environment.HomeEnvironment;

class AirConditioningTest {
    private static final double DELTA = 0.001f;
    private static final AirQualityState AIR_QUALITY_STATE = AirQualityStateFactory.normalAirQuality();

    @Test 
    void minTemperatureTest() {
        final Temperature temperature = TemperatureFactory.fromCelsius(0);
        final Temperature highTemperature = TemperatureFactory.fromCelsius(100);
        final Environment environment = new HomeEnvironment(temperature, AIR_QUALITY_STATE);
        final TemperatureChanger airConditioning = new AirConditioning(1, 10, environment);
        airConditioning.setMinTemperature(temperature);
        airConditioning.setMaxTemperature(highTemperature);
        airConditioning.updateTick(Duration.ofSeconds(1));
        assertEquals(0, environment.getTemperature().getCelsius());
    }

    @Test 
    void oneHourUpdateTest() {
        final Temperature minTemperature = TemperatureFactory.fromCelsius(20);
        final Temperature maxTemperature = TemperatureFactory.fromCelsius(100);
        final Environment environment = new HomeEnvironment(maxTemperature, AIR_QUALITY_STATE);
        final TemperatureChanger airConditioning = new AirConditioning(1, 10, environment);
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
        final Environment environment = new HomeEnvironment(maxTemperature, AIR_QUALITY_STATE);
        final TemperatureChanger airConditioning = new AirConditioning(1, 10, environment);
        airConditioning.setMinTemperature(minTemperature);
        airConditioning.setMaxTemperature(maxTemperature);
        airConditioning.updateTick(Duration.ofHours(10));
        assertEquals(90, environment.getTemperature().getCelsius(), AirConditioningTest.DELTA);
    }

    @Test 
    void halfHourUpdateTest() {
        final long half_hour_in_minutes = 30;
        final Temperature minTemperature = TemperatureFactory.fromCelsius(20);
        final Temperature maxTemperature = TemperatureFactory.fromCelsius(100);
        final Environment environment = new HomeEnvironment(maxTemperature, AIR_QUALITY_STATE);
        final TemperatureChanger airConditioning = new AirConditioning(1, 10, environment);
        final double expected = 99.5;
        airConditioning.setMinTemperature(minTemperature);
        airConditioning.setMaxTemperature(maxTemperature);
        airConditioning.updateTick(Duration.ofMinutes(half_hour_in_minutes));
        assertEquals(expected, environment.getTemperature().getCelsius(), AirConditioningTest.DELTA);
    }


}
