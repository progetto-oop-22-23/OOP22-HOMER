package homer.common;

/**
 * 
 */
public final class TemperatureFactoryImpl implements TemperatureFactory {

    @Override
    public Temperature fromCelsius(final double temperature) {
        return fromKelvin(temperature + Temperature.DELTA_KELVIN_CELSIUS);
    }

    @Override
    public Temperature fromFarenheit(final double temperature) {
        return fromKelvin((temperature + Temperature.DELTA_FARENHEIT_KELVIN) * Temperature.FIVE_NINES);
    }

    @Override
    public Temperature fromKelvin(final double temperature) {
        return new KelvinTemperature(temperature);
    }
}
