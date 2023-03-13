package homer.model.airquality;

import java.time.Duration;
import java.util.Random;

import homer.model.environment.Environment;

public class AirQualityImpl implements AirQuality{

    private Environment environment;
    private Random random = new Random();
    private double CO2Delta;
    private double pm10Delta;
    private double toxicDelta;
    private double pm25Delta;

    public AirQualityImpl(final Environment environment) {
        this.environment = environment;
    }

    @Override
    public void updateTick(Duration deltaTime) {
        var old  = this.environment.getAirQualityState();
        var newco2 = (random.nextGaussian() -0.5) * CO2Delta +  old.getCO2();
        var newpm10 = (random.nextGaussian() -0.5) * pm10Delta +  old.getPM10();
        var newtoxic = (random.nextGaussian() -0.5) * toxicDelta +  old.getToxicGasPercentage();
        var newpm25 = (random.nextGaussian() -0.5) * pm25Delta +  old.getPM25();
        this.environment.setAirQualityState(new AirQualityStateImpl(newco2, newpm10, newtoxic, newpm25));
    }
}
