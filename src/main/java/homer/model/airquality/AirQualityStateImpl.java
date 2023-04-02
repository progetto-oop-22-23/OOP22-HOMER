package homer.model.airquality;

/**
 * Implementation of AirQualityState. 
 */
public final class AirQualityStateImpl implements AirQualityState {

    private double co2;
    private double pm10;
    private double toxicGasPercentage;
    private double pm25;

    /**
     * 
     * @param co2
     * @param pm10
     * @param toxicGasPercentage
     * @param pm25
     */
    public AirQualityStateImpl(final double co2, final double pm10, final double toxicGasPercentage, final double pm25) {
        this.setCO2(co2);
        this.setPM10(pm10);
        this.setToxicGasPercentage(toxicGasPercentage);
        this.setPM25(pm25);
    }

    /**
     * 
     * @param airQualityState the state to copy
     */
    public AirQualityStateImpl(final AirQualityState airQualityState) {
        this.co2 = airQualityState.getCO2();
        this.pm10 = airQualityState.getPM10();
        this.toxicGasPercentage = airQualityState.getToxicGasPercentage();
        this.pm25 = airQualityState.getPM25();
    }

    @Override
    public double getCO2() {
        return this.co2;
    }

    @Override
    public void setCO2(final double co2) {
        requireGreaterOrEqualThanZero(co2);
        this.co2 = co2;
    }

    @Override
    public double getPM10() {
        return this.pm10;
    }

    @Override
    public void setPM10(final double pm10) {
        requireGreaterOrEqualThanZero(pm10);
        this.pm10 = pm10;
    }

    @Override
    public double getToxicGasPercentage() {
        return this.toxicGasPercentage;
    }

    @Override
    public void setToxicGasPercentage(final double toxicGasPercentage) {
        requireGreaterOrEqualThanZero(toxicGasPercentage);
        this.toxicGasPercentage = toxicGasPercentage;
    }

    @Override
    public double getPM25() {
        return this.pm25;
    }

    @Override
    public void setPM25(final double pm25) {
        requireGreaterOrEqualThanZero(pm25);
        this.pm25 = pm25;
    }

    private void requireGreaterOrEqualThanZero(final double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be greater than 0");
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(co2);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pm10);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(toxicGasPercentage);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pm25);
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AirQualityStateImpl other = (AirQualityStateImpl) obj;
        if (Double.doubleToLongBits(co2) != Double.doubleToLongBits(other.co2)) {
            return false;
        }
        if (Double.doubleToLongBits(pm10) != Double.doubleToLongBits(other.pm10)) {
            return false;
        }
        if (Double.doubleToLongBits(toxicGasPercentage) != Double.doubleToLongBits(other.toxicGasPercentage)) {
            return false;
        }
        return Double.doubleToLongBits(pm25) == Double.doubleToLongBits(other.pm25);
    }

    @Override
    public String toString() {
        return "AirQualityStateImpl [co2=" + co2 + ", pm10=" + pm10 + ", toxicGasPercentage=" + toxicGasPercentage
                + ", pm25=" + pm25 + "]";
    }

}
