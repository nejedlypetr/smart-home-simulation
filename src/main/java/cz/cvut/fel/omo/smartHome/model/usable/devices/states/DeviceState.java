package cz.cvut.fel.omo.smartHome.model.usable.devices.states;

import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;

public abstract class DeviceState {
    protected Device device;

    public DeviceState(Device device) {
        this.device = device;
    }

    public abstract int updateDevice();
}
