package homer.common;

public final class TemperatureFactoryImpl implements TemperatureFactory {
    private static final double DELTA_KELVIN_CELSIUS = 273.15;
    private static final double DELTA_FARENHEIT_KELVIN = 459.67;
    private static final double FIVE_NINES = 5 / 9;

    @Override
    public Temperature fromCelsius(final double temperature) {
        return fromKelvin(temperature + DELTA_KELVIN_CELSIUS);
    }

    @Override
    public Temperature fromFarenheit(final double temperature) {
        return fromKelvin(temperature * FIVE_NINES + DELTA_FARENHEIT_KELVIN);
    }

    @Override
    public Temperature fromKelvin(final double temperature) {
        return new KelvinTemperature(temperature);
    }
}
