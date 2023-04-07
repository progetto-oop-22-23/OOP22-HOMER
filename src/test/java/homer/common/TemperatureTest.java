package homer.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;

/**
 * Tests Temperature conversions from (and to) Fahrenheit, kelvin
 * and Celsius.
 */
final class TemperatureTest {
    private static final double DELTA = 0.01f;

    @Test
    void testCelsius() {
        final double value = 0;
        final double celsius = 0;
        final double kelvin = 273.15;
        final double fahrenheit = 32;
        final Temperature t = TemperatureFactory.fromCelsius(value);
        assertEquals(celsius, t.getCelsius(), DELTA);
        assertEquals(kelvin, t.getKelvin(), DELTA);
        assertEquals(fahrenheit, t.getFahrenheit(), DELTA);
    }


    @Test
    void testFahrenheit() {
        final double value = 53;
        final double celsius = 11.6667;
        final double kelvin = 284.81; 
        final double fahrenheit = 53;
        final Temperature t = TemperatureFactory.fromFahrenheit(value);
        assertEquals(celsius, t.getCelsius(), DELTA);
        assertEquals(kelvin, t.getKelvin(), DELTA);
        assertEquals(fahrenheit, t.getFahrenheit(), DELTA);
    }

    @Test
    void testKelvin() {
        final double value = 20;
        final double kelvin = 20;
        final double celsius = -253.15;
        final double fahrenheit = -423.6699;
        final Temperature t = TemperatureFactory.fromKelvin(value);
        assertEquals(celsius, t.getCelsius(), DELTA);
        assertEquals(kelvin, t.getKelvin(), DELTA);
        assertEquals(fahrenheit, t.getFahrenheit(), DELTA);
    }

    @Test 
    void testComparison() {
        final Temperature absoluteZero = TemperatureFactory.fromKelvin(0);
        final Temperature zeroCelsius = TemperatureFactory.fromCelsius(0);
        final Temperature oneHundredCelsius = TemperatureFactory.fromCelsius(100);
        assertEquals(-1, absoluteZero.compareTo(zeroCelsius));
        assertEquals(-1, zeroCelsius.compareTo(oneHundredCelsius));
        assertEquals(-1, absoluteZero.compareTo(oneHundredCelsius));
    }
}
