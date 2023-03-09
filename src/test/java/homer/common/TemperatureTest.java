package homer.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests Temperature conversions from (and to) farenheit, kelvin
 * and celsius.
 */
public final class TemperatureTest {
    private static final double DELTA = 0.01f;

    @Test
    void testCelsius() {
        final double value = 0;
        final double celsius = 0;
        final double kelvin = 273.15;
        final double farenheit = 32;
        final Temperature t = TemperatureFactory.fromCelsius(value);
        assertEquals(celsius, t.getCelsius(), DELTA);
        assertEquals(kelvin, t.getKelvin(), DELTA);
        assertEquals(farenheit, t.getFarenheit(), DELTA);
    }


    @Test
    void testFarenheit() {
        final double value = 53;
        final double celsius = 11.6667;
        final double kelvin = 284.81; 
        final double farenheit = 53;
        final Temperature t = TemperatureFactory.fromFarenheit(value);
        assertEquals(celsius, t.getCelsius(), DELTA);
        assertEquals(kelvin, t.getKelvin(), DELTA);
        assertEquals(farenheit, t.getFarenheit(), DELTA);
    }

    @Test
    void testKelvin() {
        final double value = 20;
        final double kelvin = 20;
        final double celsius = -253.15;
        final double farenheit = -423.6699;
        final Temperature t = TemperatureFactory.fromKelvin(value);
        assertEquals(celsius, t.getCelsius(), DELTA);
        assertEquals(kelvin, t.getKelvin(), DELTA);
        assertEquals(farenheit, t.getFarenheit(), DELTA);
    }
}
