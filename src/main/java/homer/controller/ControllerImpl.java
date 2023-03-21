package homer.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import homer.DeviceInfoImpl;
import homer.api.AdjustableDevice;
import homer.api.Device;
import homer.api.DeviceId;
import homer.api.DeviceIdImpl;
import homer.api.DeviceType;
import homer.api.DeviceView;
import homer.api.ToggleableDevice;
import homer.controller.command.Command;
import homer.controller.command.ConnectDevice;
import homer.controller.command.DisconnectAllDevices;
import homer.controller.command.DisconnectDevice;
import homer.controller.command.ToggleDevice;
import homer.controller.command.UpdateDeviceState;
import homer.model.outlets.OutletFactory;

/**
 * Controller Implementation.
 */
public final class ControllerImpl implements Controller {

    private final Map<DeviceId, Device<?>> devices = new LinkedHashMap<>();
    private final List<Command> commands = new LinkedList<>();

    @Override
    public Set<DeviceView<?>> getDevices() {
        throw new UnsupportedOperationException("Unimplemented method 'getDevices'");
    }

    @Override
    public void receiveCommand(final Command command) {
        commands.add(command);
    }

    /**
     * this should be called by the scheduler, which is not implemented yet.
     * @param command
     */
    @SuppressWarnings("PMD.UnusedPrivateMethod") 
    private void execute(final Command command) {
        if (command instanceof ConnectDevice) { // TODO still not finished
            final ConnectDevice connect = (ConnectDevice) command;
            final DeviceType deviceType = connect.deviceType();
            final DeviceId deviceId = new DeviceIdImpl();
            devices.put(deviceId, OutletFactory.cOutlet(new DeviceInfoImpl(deviceId, deviceType), 0));
        } else if (command instanceof DisconnectDevice) {
            final DisconnectDevice remove = (DisconnectDevice) command;
            devices.remove(remove.deviceId());
        } else if (command instanceof DisconnectAllDevices) {
            devices.clear();
        } else if (command instanceof UpdateDeviceState) {
            final UpdateDeviceState updateState = (UpdateDeviceState) command;
            final Device<?> targetDevice = devices.get(updateState.deviceId());
            if (targetDevice instanceof AdjustableDevice) {
                final Class<?> adjustableClass = targetDevice.getClass();
                final ParameterizedType parameterizedType = (ParameterizedType) adjustableClass.getGenericSuperclass();
                final Type type = parameterizedType.getActualTypeArguments()[0];
                if (type.equals(updateState.state().getClass()))  {
                    final AdjustableDevice adjustableDevice = (AdjustableDevice) targetDevice;
                    adjustableDevice.setState(updateState.state());
                }
            } 
        } else if (command instanceof ToggleDevice) {
            final Device<?> targetDevice = devices.get(((ToggleDevice) command).deviceId());
            if (targetDevice instanceof ToggleableDevice) {
                ((ToggleableDevice<?>) targetDevice).toggle();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

}
