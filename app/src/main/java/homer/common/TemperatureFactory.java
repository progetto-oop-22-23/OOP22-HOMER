package homer.common;

/**
 * Returns a Temperature object. 
 */
public interface TemperatureFactory {
    /**
     * 
     * @param celsiusTemperature The temperature in celsius degrees.
     * @return Temperature class instance.
     */
    Temperature fromCelsius(double celsiusTemperature);

    /**
     * 
     * @param farenheitTemperature The temperature in farenheit degrees.
     * @return Temperature class instance.
     */
    Temperature fromFarenheit(double farenheitTemperature);

    /**
     * @param temperature The temperature in kelvin degrees.
     * @return Temperature class instance.
     */
    Temperature fromKelvin(double temperature);
}
