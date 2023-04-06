package homer.common.temperature;

/**
 * Returns a Temperature object. 
 */
public final class TemperatureFactory {

    private TemperatureFactory() {

    }

    /**
     * 
     * @param temperature The temperature in celsius degrees.
     * @return Temperature class instance.
     */
    public static Temperature fromCelsius(final double temperature) {
        return fromKelvin(temperature + Temperature.DELTA_KELVIN_CELSIUS);
    }

    /**
     * 
     * @param temperature The temperature in fahrenheit degrees.
     * @return Temperature class instance.
     */
    public static Temperature fromFahrenheit(final double temperature) {
        return fromKelvin((temperature + Temperature.DELTA_FAHRENHEIT_KELVIN) * Temperature.FIVE_NINES);
    }

    /**
     * @param temperature The temperature in kelvin degrees.
     * @return Temperature class instance.
     */
    public static Temperature fromKelvin(final double temperature) {
        return new KelvinTemperature(temperature);
    }
}
