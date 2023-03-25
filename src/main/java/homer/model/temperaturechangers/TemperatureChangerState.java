package homer.model.temperaturechangers;

import homer.api.DeviceState;

public record TemperatureChangerState(double min, double max, double intensity) implements DeviceState {
    
}
