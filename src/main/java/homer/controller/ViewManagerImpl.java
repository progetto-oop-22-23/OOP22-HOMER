package homer.controller;

import java.util.ArrayList;
import java.util.List;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.view.DeviceViewer;

public final class ViewManagerImpl implements ViewManager {
    private final List<DeviceViewer> views = new ArrayList<>();

    @Override
    public void addView(DeviceViewer view) {
        views.add(view);
    }

    @Override
    public void removeView(DeviceViewer view) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeView'");
    }

    @Override
    public void updateDeviceState(DeviceId deviceId, DeviceState serializedState) {
        views.forEach(x -> x.updateDeviceState(deviceId, serializedState));
    }

    @Override
    public void removeDevice(DeviceId deviceId) {
        views.forEach(x -> x.removeDevice(deviceId));
    }

    @Override
    public void start(Controller controller) {
        views.forEach(x -> x.start(controller));
    }
    
}
