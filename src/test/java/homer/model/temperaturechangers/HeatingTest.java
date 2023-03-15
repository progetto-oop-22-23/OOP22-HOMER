package homer.model.temperaturechangers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;
import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import homer.model.airquality.AirQualityState;
import homer.model.airquality.AirQualityStateFactory;
import homer.model.environment.Environment;
import homer.model.environment.HomeEnvironment;

@SuppressWarnings("PMD")
class HeatingTest {
    private static final DeviceInfo INFO = new DeviceInfoImpl(new DeviceIdImpl(), "HEATING");
    private Temperature temperature = TemperatureFactory.fromCelsius(0);
    private Temperature highTemperature = TemperatureFactory.fromCelsius(100);
    private Environment environment; 
    private TemperatureChanger heating;
    private static final int ONE_BILLION = 1_000_000_000;
    private static final double DELTA = 0.001;
    private static final AirQualityState AIR_QUALITY_STATE = AirQualityStateFactory.normalAirQuality();

    @BeforeEach
    void setup() {
        temperature = TemperatureFactory.fromCelsius(0);
        this.environment = new HomeEnvironment(temperature, AIR_QUALITY_STATE);
        this.heating = new Heating(1, 10, environment, INFO);
        this.heating.setMinTemperature(temperature);
        this.heating.setMaxTemperature(highTemperature);
    }

    @Test
    void initialValueTest() {
        assertEquals(0, environment.getTemperature().getCelsius());
    }

    @Test 
    void oneHourUpdateTest() {
        heating.updateTick(Duration.ofHours(1));
        assertEquals(1, environment.getTemperature().getCelsius(), HeatingTest.DELTA);
    }

    @Test 
    void reachesMaxTemperatureTest() {
        heating.updateTick(Duration.ofSeconds(ONE_BILLION));
        assertEquals(100, environment.getTemperature().getCelsius());
    }
}
