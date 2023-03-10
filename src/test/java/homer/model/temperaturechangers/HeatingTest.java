package homer.model.temperaturechangers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import homer.common.Temperature;
import homer.common.TemperatureFactory;
import homer.model.environment.Environment;
import homer.model.environment.HomeEnvironment;

class HeatingTest {
    private Temperature temperature;
    private Temperature highTemperature;
    private Environment environment; 
    private TemperatureChanger heating;
    private static final int ONE_BILLION = 1_000_000_000;
    private static final double DELTA = 0.001;

    @BeforeEach
    void setup() {
        temperature = TemperatureFactory.fromCelsius(0);
        this.highTemperature =  TemperatureFactory.fromCelsius(100);
        this.environment = new HomeEnvironment(temperature);
        this.heating = new Heating(0, 10, 1, environment, temperature, highTemperature);
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
