package cz.cvut.fel.omo.smartHome.model.usable.device.state;

import cz.cvut.fel.omo.smartHome.model.usable.device.Device;

public abstract class DeviceState {
    protected Device device;

    public DeviceState(Device device) {
        this.device = device;
    }

    public abstract int updateDevice();
}
