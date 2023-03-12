package homer.common.temperature;

/**
 * Temperature implementation that uses kelvin as a base measurement unit.
 */
public final class KelvinTemperature implements Temperature {

    private final double temperature;

    /**
     * 
     * @param temperature
     */
    public KelvinTemperature(final double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double getKelvin() {
        return temperature;
    }

    @Override
    public double getFarenheit() {
        return this.getCelsius() / Temperature.FIVE_NINES + Temperature.DELTA_KELVIN_FARENHEIT;
    }

    @Override
    public double getCelsius() {
        return temperature - Temperature.DELTA_KELVIN_CELSIUS;
    }

    @Override
    public int compareTo(final Temperature arg0) {
        return Double.compare(this.getKelvin(), arg0.getKelvin());
    }

}
