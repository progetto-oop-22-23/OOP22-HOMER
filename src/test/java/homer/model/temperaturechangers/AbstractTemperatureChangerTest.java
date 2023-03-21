package homer.model.temperaturechangers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;
import homer.api.DeviceType;
import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import homer.model.airquality.AirQualityStateFactory;
import homer.model.environment.Environment;
import homer.model.environment.HomeEnvironment;

class AbstractTemperatureChangerTest {
    private static final DeviceInfo INFO = new DeviceInfoImpl(new DeviceIdImpl(), DeviceType.HEATING);
    private static final Temperature TEMPERATURE = TemperatureFactory.fromCelsius(0);
    private static final Environment ENVIRONMENT = new HomeEnvironment(TEMPERATURE, AirQualityStateFactory.normalAirQuality());

    @Test
    void noMaximumTemperatureByDefault() {
        final AbstractTemperatureChanger defaultChanger = new Heating(1, 10, ENVIRONMENT, INFO);
        assertTrue(defaultChanger.getMaxTemperature().isEmpty());
    }
}
