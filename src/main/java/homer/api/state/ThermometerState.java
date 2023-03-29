package homer.api.state;

import homer.api.DeviceState;
import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;

/**
 * Implementation of {@link DeviceState} for a {@link Thermometer}.
 */
public final class ThermometerState implements DeviceState {

    private final Temperature temperature;

    /**
     * Creates a new {@link ThermometerState}
     * 
     * @param temperature the sensed temperature.
     */
    public ThermometerState(final Temperature temperature) {
        this.temperature = TemperatureFactory.fromKelvin(temperature.getKelvin());
    }

    /**
     * Returns the sensed temperature.
     * 
     * @return the sensed temperature.
     */
    public Temperature getTemperature() {
        return this.temperature;
    }

}
