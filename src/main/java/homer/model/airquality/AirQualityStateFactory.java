package homer.model.airquality;

public class AirQualityStateFactory {

    public static AirQualityState normalAirQualityStte()  {
        return new AirQualityStateImpl(0, 0, 0, 0); // TODO fix
    }

    public static AirQualityState badAirQualityState() {
        return new AirQualityStateImpl(0, 0, 0, 0); // TODO fix
    }
}
