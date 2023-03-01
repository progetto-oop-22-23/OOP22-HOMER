package homer.temperaturechangers;

import java.util.function.Function;

public class HeatingDevice extends TemperatureChangerDevice {

    public HeatingDevice(double minValue, double maxValue, double currentValue, Function<Long, Double> function) {
        super(minValue, maxValue, currentValue, function);
    }

    @Override
    public Double getState() {
        this.currentValue = Math.min(maxValue, currentValue + function.apply(System.currentTimeMillis() - lastCall));
        lastCall = System.currentTimeMillis();
        return this.currentValue;
    }
    
}
