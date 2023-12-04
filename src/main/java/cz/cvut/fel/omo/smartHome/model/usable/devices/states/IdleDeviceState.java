package cz.cvut.fel.omo.smartHome.model.usable.devices.states;

import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;

public class IdleDeviceState extends DeviceState {
    private static final int IDLE_ELECTRICITY_CONSUMPTION = 1;

    public IdleDeviceState(Device device) {
        super(device);
    }


    @Override
    public int updateDevice() {
        device.updateLifespan(-1);
        return IDLE_ELECTRICITY_CONSUMPTION;
    }
}
