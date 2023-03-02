package homer.common;

public interface TemperatureFactory {
    Temperature fromCelsius(final double temperature);
    Temperature fromFarenheit(final double temperature);
    Temperature fromKelvin(final double temperature);
}
