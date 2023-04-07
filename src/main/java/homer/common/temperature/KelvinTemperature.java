package homer.common.temperature;

/**
 * Temperature implementation that uses kelvin as a base measurement unit.
 */
public final class KelvinTemperature implements Temperature {

    private final double temperature;

    /**
     * 
     * @param temperature the kelvin temperature.
     */
    public KelvinTemperature(final double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double getKelvin() {
        return temperature;
    }

    @Override
    public double getFahrenheit() {
        return this.getCelsius() / Temperature.FIVE_NINES + Temperature.DELTA_KELVIN_FAHRENHEIT;
    }

    @Override
    public double getCelsius() {
        return temperature - Temperature.DELTA_KELVIN_CELSIUS;
    }

    @Override
    public int compareTo(final Temperature arg0) {
        return Double.compare(this.getKelvin(), arg0.getKelvin());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(temperature);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } 
        if (obj == null) {
            return false;
        }
        if (obj instanceof Temperature) {
            final Temperature temperature = (Temperature) obj;
            return this.getKelvin() == temperature.getKelvin();
        }
        return false;
    }

}
