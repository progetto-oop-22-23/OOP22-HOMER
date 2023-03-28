package homer.model.temperaturechangers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import homer.model.airquality.AirQualityStateFactory;
import homer.model.environment.Environment;
import homer.model.environment.HomeEnvironment;

class AbstractTemperatureChangerTest {
    private static final Temperature TEMPERATURE = TemperatureFactory.fromCelsius(0);
    private static final Environment ENVIRONMENT = new HomeEnvironment(TEMPERATURE, AirQualityStateFactory.normalAirQuality());
    private static final Integer ONE_THOUSAND = 1000; 
    private static final Double DELTA = 0.0001;

    @Test
    void noMaximumTemperatureByDefault() {
        final AbstractTemperatureChanger defaultChanger = new Heating(1, 10, ENVIRONMENT);
        assertTrue(defaultChanger.getMaxTemperature().isEmpty());
    }

    @Test
    void testNoConsumptionWithZeroIntensity() {
        final AbstractTemperatureChanger temperatureChanger = new Heating(0, 0, ENVIRONMENT);
        final var duration = Duration.ofDays(1);
        temperatureChanger.updateTick(duration);
        assertEquals(0, temperatureChanger.getInstantConsumption());
    }

    @Test 
    void testLogarithmicConsumption() {
        final AbstractTemperatureChanger temperatureChanger = new Heating(1, 10, ENVIRONMENT);
        final var duration = Duration.ofHours(1);
        temperatureChanger.updateTick(duration);
        assertEquals(ONE_THOUSAND, temperatureChanger.getInstantConsumption(), DELTA);
    }
}
