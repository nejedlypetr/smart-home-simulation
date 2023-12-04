package cz.cvut.fel.omo.smartHome.model.usable.devices.states;

import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;

public class OffDeviceState extends DeviceState {
    public OffDeviceState(Device device) {
        super(device);
    }

    @Override
    public int updateDevice() {
        return 0;
    }
}
