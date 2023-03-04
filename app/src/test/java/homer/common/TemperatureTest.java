package homer.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests Temperature conversions from (and to) farenheit, kelvin
 * and celsius.
 */
public final class TemperatureTest {
    private final TemperatureFactory factory = new TemperatureFactoryImpl();

    @Test
    void testCelsius() {
        final double value = 0;
        final double celsius = 0;
        final double kelvin = 273.15;
        final double farenheit = 32;
        final Temperature t = factory.fromCelsius(value);
        assertEquals(celsius, t.getCelsius());
        assertEquals(kelvin, t.getKelvin());
        assertEquals(farenheit, t.getFarenheit());
    }


    void testFarenheit() {
        final double value = 53;
        final double celsius = 11.6667;
        final double kelvin = 284.6667; 
        final double farenheit = 53;
        final Temperature t = factory.fromCelsius(value);
        assertEquals(celsius, t.getCelsius());
        assertEquals(kelvin, t.getKelvin());
        assertEquals(farenheit, t.getFarenheit());
    }

    void testKelvin() {
        final double value = 20;
        final double kelvin = 20;
        final double celsius = -253.15;
        final double farenheit = -423.67;
        final Temperature t = factory.fromKelvin(value);
        assertEquals(celsius, t.getCelsius());
        assertEquals(kelvin, t.getKelvin());
        assertEquals(farenheit, t.getFarenheit());
    }
}
