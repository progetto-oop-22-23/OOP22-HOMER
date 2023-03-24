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
     * @param temperature The temperature in farenheit degrees.
     * @return Temperature class instance.
     */
    public static Temperature fromFarenheit(final double temperature) {
        return fromKelvin((temperature + Temperature.DELTA_FARENHEIT_KELVIN) * Temperature.FIVE_NINES);
    }

    /**
     * @param temperature The temperature in kelvin degrees.
     * @return Temperature class instance.
     */
    public static Temperature fromKelvin(final double temperature) {
        return new KelvinTemperature(temperature);
    }
}
