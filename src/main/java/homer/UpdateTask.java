package homer;

import java.time.Duration;
import java.util.HashMap;
import java.util.TimerTask;

import homer.api.DeviceId;
import homer.core.DiscreteObject;

public class UpdateTask extends TimerTask{
    private long lastUpdateTime;
    private HashMap<DeviceId, DiscreteObject> devices = new HashMap<DeviceId, DiscreteObject>();
    public UpdateTask(HashMap<DeviceId,DiscreteObject> devices) {
        lastUpdateTime = System.currentTimeMillis();
        this.devices = devices;
    }
    @Override
    public void run() {
        final long currentTime = System.currentTimeMillis();
        final long dt = currentTime - this.lastUpdateTime;
        final Duration deltaTime = Duration.ofMillis(dt);
        this.lastUpdateTime = currentTime;
        for (DiscreteObject device : devices.values()) {
            device.updateTick(deltaTime);
        }
    }
    
}
