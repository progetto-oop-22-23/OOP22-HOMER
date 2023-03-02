package homer.impl;


import java.util.HashMap;

import homer.api.Device;
import homer.api.ElectricalMeter;

public class ElectricalMeterImpl implements ElectricalMeter<HashMap<?, Device<?>>> {
    
    @Override
    public void checkConsumption() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkConsumption'");
    }

    @Override
    public double getGlobalConsumption() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGlobalConsumption'");
    }

    @Override
    public void setGlobalConsumption(double globalConsumption) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGlobalConsumption'");
    }

    @Override
    public double getMaxConsumption() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxConsumption'");
    }

    @Override
    public void setMaxConsumption(double maxConsumption) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMaxConsumption'");
    }

    @Override
    public void cutPowerTo(HashMap<?, Device<?>> devices) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cutPowerTo'");
    }

}
