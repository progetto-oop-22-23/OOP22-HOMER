package homer.model.airquality;

/**
 * Creates useful configurations of air quality state.
 */
public final class AirQualityStateFactory {
    private static final double NORMAL_CO2 = 23;
    private static final double NORMAL_PM10 = 40;
    private static final double NORMAL_TOXIC = 0;
    private static final double NORMAL_PM25 = 12;
    private static final double BAD_CO2 = 30;
    private static final double BAD_PM10 = 120;
    private static final double BAD_TOXIC = 10;
    private static final double BAD_PM25 = 35;

    private AirQualityStateFactory() { }

    /**
     * 
     * @return a healthy air quality state.
     */
    public static AirQualityState normalAirQuality()  {
        return new AirQualityStateImpl(NORMAL_CO2, NORMAL_PM10, NORMAL_TOXIC, NORMAL_PM25); 
    }

    /**
     * 
     * @return an unhealthy air quality state.
     */
    public static AirQualityState badAirQualityState() {
        return new AirQualityStateImpl(BAD_CO2, BAD_PM10, BAD_TOXIC, BAD_PM25); 
    }
}
