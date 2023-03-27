package homer.controller;

import java.util.ArrayList;
import java.util.List;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.view.View;

public final class ViewManagerImpl implements ViewManager {
    private final List<View> views = new ArrayList<>();

    @Override
    public void addView(View view) {
        views.add(view);
    }

    @Override
    public void removeView(View view) {
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

    
}
