package cz.cvut.fel.omo.smartHome.model.usable.devices.states;

import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;

public class ActiveDeviceState extends DeviceState {
    public ActiveDeviceState(Device device) {
        super(device);
    }

    @Override
    public int updateDevice() {
        device.updateLifespan(-3);
        return device.getElectricityConsumption();
    }
}
