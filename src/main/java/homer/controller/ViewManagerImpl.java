package homer.controller;

import java.util.ArrayList;
import java.util.List;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.view.DeviceViewer;

/**
 * {@link ViewManager} implementation.
 */
public final class ViewManagerImpl implements ViewManager {
    private final List<DeviceViewer> views = new ArrayList<>();

    @Override
    public void addView(final DeviceViewer view) {
        views.add(view);
    }

    @Override
    public void updateDeviceState(final DeviceId deviceId, final DeviceState serializedState) {
        views.forEach(x -> x.updateDeviceState(deviceId, serializedState));
    }


    @Override
    public void removeDevice(final DeviceId deviceId) {
        views.forEach(x -> x.removeDevice(deviceId));
    }

    @Override
    public void start(final Controller controller) {
        views.forEach(x -> x.start(controller));
    }

}
