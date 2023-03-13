package homer.model.airquality;

public class AirQualityStateImpl implements AirQualityState {

    private double co2;
    private double pm10;
    private double toxicGasPercentage;
    private double pm25;

    public AirQualityStateImpl(final double co2, final double pm10, final double toxicGasPercentage, final double pm25) {
        this.co2 = co2;
        this.pm10 = pm10;
        this.toxicGasPercentage = toxicGasPercentage;
        this.pm25 = pm25;
    }

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
    public void setCO2(double co2) {
        this.co2 = co2;
    }

    @Override
    public double getPM10() {
        return this.pm10;
    }

    @Override
    public void setPM10(double PM10) {
        this.pm10 = PM10;
    }

    @Override
    public double getToxicGasPercentage() {
        return this.toxicGasPercentage;
    }

    @Override
    public void setToxicGasPercentage(double toxicGasPercentage) {
        this.toxicGasPercentage = toxicGasPercentage;
    }

    @Override
    public double getPM25() {
        return this.pm25;
    }

    @Override
    public void setPM25(double PM25) {
        this.pm25 = PM25;
    }
    
}
