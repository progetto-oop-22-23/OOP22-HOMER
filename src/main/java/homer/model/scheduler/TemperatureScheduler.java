package homer.model.scheduler;

import java.time.LocalTime;
import java.util.Set;

import homer.common.temperature.Temperature;

public class TemperatureScheduler implements TimeScheduler<Temperature> {

    @Override
    public void addSchedule(TimeSchedule<Temperature> schedule) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addSchedule'");
    }

    @Override
    public void removeSchedule(TimeSchedule<Temperature> schedule) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeSchedule'");
    }

    @Override
    public Set<TimeSchedule<Temperature>> getSchedules() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSchedules'");
    }

    @Override
    public void checkSchedule(LocalTime currentTime, Temperature currentParameter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkSchedule'");
    }

}
