package homer.view.logger;

import java.io.OutputStream;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.controller.Controller;
import homer.model.environment.Environment;

/**
 * Decorates a {@link Logger} with additional warnings.
 * 
 */
public final class WarningLogger implements Logger {

    private final Logger logger;
    private final Set<DeviceId> activeDevices = new LinkedHashSet<>();

    /**
     * 
     * @param logger the base logger.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Reference is needed in order for this to work")
    public WarningLogger(final Logger logger) {
        Objects.requireNonNull(logger);
        this.logger = logger;
    }

    @Override
    public void updateDeviceState(final DeviceId deviceId, final DeviceState deviceState) {
        activeDevices.add(deviceId);
        logger.updateDeviceState(deviceId, deviceState);
    }

    @Override
    public void removeDevice(final DeviceId deviceId) {
        if (!activeDevices.contains(deviceId)) {
            logger.log("WARNING: Tried to remove non-existent device with id " + deviceId.toString());
        } else {
            logger.removeDevice(deviceId);
        }
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

    @Override
    public void updateEnvironment(final Environment environment) {
        logger.updateEnvironment(environment);
    }

}
