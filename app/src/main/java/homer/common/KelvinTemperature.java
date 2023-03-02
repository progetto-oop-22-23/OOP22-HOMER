package homer.common;

public final class KelvinTemperature implements Temperature {

    final static double DELTA_KELVIN_CELSIUS = 273.15;
    final static double DELTA_FARENHEIT_KELVIN = 459.67;
    private double temperature;

    public KelvinTemperature(final double temperature) {
        this.temperature = temperature;

    }

    @Override
    public double getKelvin() {
        return temperature;
    }

    @Override
    public double getFarenheit() {
        return 1.8 * (temperature - DELTA_KELVIN_CELSIUS) + 32;
    }

    @Override
    public double getCelsius() {
        return temperature - DELTA_KELVIN_CELSIUS;
    }

}
