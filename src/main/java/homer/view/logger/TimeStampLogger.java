package homer.view.logger;

import java.io.OutputStream;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.common.time.Clock;
import homer.controller.Controller;

public class TimeStampLogger implements Logger {

    private Logger logger;
    private Clock clock;

    public TimeStampLogger(Logger logger, Clock clock) {
        this.logger = logger;
        this.clock = clock;
    }

    @Override
    public void updateDeviceState(DeviceId deviceId, DeviceState deviceState) {
        logTimeZone();
        logger.updateDeviceState(deviceId, deviceState);
    }

    @Override
    public void removeDevice(DeviceId deviceId) {
        logger.removeDevice(deviceId);
    }

    @Override
    public void start(Controller controller) {
        logger.start(controller);
    }

    @Override
    public void setOutputStream(OutputStream outputStream) {
        logger.setOutputStream(outputStream);
    }

    @Override
    public void log(String string) {
        logger.log(string);
    }

    private void logTimeZone() {
        logger.log(clock.getDateTime().toString());
    }
    
}
