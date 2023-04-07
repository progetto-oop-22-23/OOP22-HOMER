package homer.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import homer.api.AdjustableDevice;
import homer.api.Device;
import homer.api.DeviceId;
import homer.api.DeviceIdImpl;
import homer.api.DeviceState;
import homer.api.ToggleableDevice;
import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import homer.controller.command.createdevicecommand.CreateAirConditioning;
import homer.controller.command.createdevicecommand.CreateAirQualitySensor;
import homer.controller.command.createdevicecommand.CreateBlinds;
import homer.controller.command.createdevicecommand.CreateDeviceCommand;
import homer.controller.command.createdevicecommand.CreateDoor;
import homer.controller.command.createdevicecommand.CreateHeating;
import homer.controller.command.createdevicecommand.CreateLight;
import homer.controller.command.createdevicecommand.CreateLock;
import homer.controller.command.createdevicecommand.CreateThermometer;
import homer.controller.command.createdevicecommand.CreateWindow;
import homer.model.airquality.AirQualityState;
import homer.model.airquality.AirQualityStateFactory;
import homer.model.environment.Environment;
import homer.model.environment.HomeEnvironment;

/**
 * DeviceManager implementation.
 */
public final class DeviceManagerImpl implements DeviceManager {
    private final Map<DeviceId, Device<?>> deviceMap = new LinkedHashMap<>();
    private final Temperature temperature = TemperatureFactory.fromCelsius(0);
    private final AirQualityState airQualityState = AirQualityStateFactory.normalAirQuality();
    private final Environment environment = new HomeEnvironment(temperature, airQualityState);
    private final Controller controller;

    /**
     * Creates a new {@link DeviceManagerImpl}.
     * 
     * @param controller the corresponding controller.
     */
    public DeviceManagerImpl(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public void removeAllDevices() {
        this.deviceMap.keySet().stream().forEach(k -> removeFromView(k));
        this.deviceMap.clear();
    }

    private boolean isDeviceConnected(final DeviceId deviceId) {
        return deviceMap.containsKey(deviceId);
    }

    @Override
    public void removeDevice(final DeviceId deviceId) {
        if (this.isDeviceConnected(deviceId)) {
            deviceMap.remove(deviceId);
            removeFromView(deviceId);
        }
    }

    @Override
    public void toggleDevice(final DeviceId deviceId) {
        final Device<?> targetDevice = deviceMap.get(deviceId);
        if (targetDevice instanceof ToggleableDevice) {
            final ToggleableDevice<?> toggleableDevice = (ToggleableDevice<?>) targetDevice;
            toggleableDevice.toggle();
            updateToView(deviceId);
        } else {
            throw new IllegalArgumentException("Device is not toggleable");
        }

    }

    @Override
    public void addDevice(final Device<?> device) {
        final DeviceId newId = new DeviceIdImpl();
        this.deviceMap.put(newId, device);
        updateToView(newId);
    }

    @Override
    public Set<CreateDeviceCommand> getValidCreateDeviceCommands() {
        return Set.of(new CreateAirConditioning(this.environment), new CreateHeating(this.environment),
                new CreateThermometer(this.environment), new CreateLock(),
                new CreateWindow(), new CreateDoor(), new CreateBlinds(),
                new CreateAirQualitySensor(this.environment), new CreateLight());
    }

    @Override
    public void updateDeviceState(final DeviceId deviceId, final DeviceState state) {
        final Device<?> targetDevice = this.deviceMap.get(deviceId);
        if (targetDevice instanceof AdjustableDevice) {
            final AdjustableDevice<?> adjustableDevice = (AdjustableDevice<?>) targetDevice;
            adjustableDevice.setState(state);
            updateToView(deviceId);
        }
    }

    @Override
    public Map<DeviceId, Device<?>> getDevices() {
        return Map.copyOf(this.deviceMap);
    }

    private void updateToView(final DeviceId deviceId) {
        this.controller.getViewManager().updateDeviceState(deviceId, this.deviceMap.get(deviceId).getState());
    }

    private void removeFromView(final DeviceId deviceId) {
        this.controller.getViewManager().removeDevice(deviceId);
    }
}
