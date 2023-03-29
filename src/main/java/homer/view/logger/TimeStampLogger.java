package homer.view.logger;

import java.io.OutputStream;
import java.util.Objects;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.common.time.Clock;
import homer.controller.Controller;

public class TimeStampLogger implements Logger {

    private Logger logger;
    private Clock clock;

    public TimeStampLogger(final Logger logger,final Clock clock) {
        Objects.requireNonNull(logger);
        Objects.requireNonNull(clock);
        this.logger = logger;
        this.clock = clock;
    }

    @Override
    public void updateDeviceState(final DeviceId deviceId, final DeviceState deviceState) {
        logTimeZone();
        logger.updateDeviceState(deviceId, deviceState);
    }

    @Override
    public void removeDevice(final DeviceId deviceId) {
        logTimeZone();
        logger.removeDevice(deviceId);
    }

    @Override
    public void start(final Controller controller) {
        logger.start(controller);
    }

    @Override
    public void setOutputStream(final OutputStream outputStream) {
        logger.setOutputStream(outputStream);
    }

    @Override
    public void log(final String string) {
        logger.log(string);
    }

    private void logTimeZone() {
        logger.log(clock.getDateTime().toString());
    }
    
}
