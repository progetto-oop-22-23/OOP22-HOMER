package homer.model.temperaturechangers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;
import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import homer.model.environment.Environment;
import homer.model.environment.HomeEnvironment;

class AbstractTemperatureTest {
    private static final DeviceInfo INFO = new DeviceInfoImpl(new DeviceIdImpl(), "HEATING");
    private static final Temperature TEMPERATURE = TemperatureFactory.fromCelsius(0);
    private static final Environment ENVIRONMENT = new HomeEnvironment(TEMPERATURE);

    @Test
    void noMaximumTemperatureByDefault() {
        final AbstractTemperatureChanger defaultHeating = new Heating(1, 10, ENVIRONMENT, INFO);
        assertTrue(defaultHeating.getMaxTemperature().isEmpty());
    }
}
