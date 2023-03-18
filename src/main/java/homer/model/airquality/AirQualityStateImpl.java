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
        requireGreaterOrEqualThanZero(co2); 
        requireGreaterOrEqualThanZero(pm10); 
        requireGreaterOrEqualThanZero(toxicGasPercentage);
        requireGreaterOrEqualThanZero(pm25); 
        this.co2 =  co2;
        this.pm10 = pm10;
        this.toxicGasPercentage = toxicGasPercentage;
        this.pm25 = pm25;
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

}
