package homer.common.temperature;

/**
 * Represents a fixed temperature value, that can be represented
 * as kelvin, Fahrenheit, or celsius.
 */
public interface Temperature extends Comparable<Temperature> {
    /**
     * Delta from kelvin to celsius.
     */
    double DELTA_KELVIN_CELSIUS = 273.15;
    /**
     * Delta from fahrenheit to kelvin.
     */
    double DELTA_FAHRENHEIT_KELVIN = 459.67;
    /**
     * Needed to compute conversions.
     */
    double FIVE_NINES = 5 / 9f;
    /**
     * The delta between kelvin and fahrenheit.
     */
    double DELTA_KELVIN_FAHRENHEIT = 32;

    /**
     * Returns the temperature in kelvin.
     * @return kelvin temperature
     */
    double getKelvin();

    /**
     * Returns the temperature in fahrenheit.
     * @return fahrenheit temperature
     */
    double getFahrenheit();

    /**
     * Returns the temperature in Celsius.
     * @return celsius temperature
     */
    double getCelsius();
}
