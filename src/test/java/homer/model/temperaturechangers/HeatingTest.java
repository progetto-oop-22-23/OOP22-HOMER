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

    @BeforeEach void setup() {
        this.temperature = TemperatureFactory.fromCelsius(0);
        this.highTemperature =  TemperatureFactory.fromCelsius(100);
        this.environment = new HomeEnvironment(temperature);
        this.heating = new Heating(0, 10, 1, environment, temperature, highTemperature);
    }

    void initialValueTest() {
        assertEquals(0, environment.getTemperature().getCelsius());
    }

    @Test 
    void minTemperatureTest() {
        heating.updateTick(Duration.ofSeconds(1));
        assertEquals(100, environment.getTemperature().getCelsius());
    }

    @Test 
    void maxTemperatureTest() {
        heating.updateTick(Duration.ofSeconds(10000));
        assertEquals(100, environment.getTemperature().getCelsius());
    }
}