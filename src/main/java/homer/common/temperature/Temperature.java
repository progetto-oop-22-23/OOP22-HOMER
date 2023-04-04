package homer.common.temperature;

/**
 * Represents a fixed temperature value, that can be represented
 * as kelvin, farenheit, or celsius.
 */
public interface Temperature extends Comparable<Temperature> {
    //CHECKSTYLE: JavadocVariable OFF
    // These names are self-explanatory, and have no reason to have an associated docstring
    double DELTA_KELVIN_CELSIUS = 273.15;
    double DELTA_FARENHEIT_KELVIN = 459.67;
    double FIVE_NINES = 5 / 9f;
    double DELTA_KELVIN_FARENHEIT = 32;
    //CHECKSTYLE: JavadocVariable ON

    /**
     * Returns the temperature in kelvin.
     * @return kelvin temperature
     */
    double getKelvin();

    /**
     * Returns the temperature in fahrenheit.
     * @return fahrenheit temperature
     */
    double getFarenheit();

    /**
     * Returns the temperature in Celsius.
     * @return celsius temperature
     */
    double getCelsius();
}
