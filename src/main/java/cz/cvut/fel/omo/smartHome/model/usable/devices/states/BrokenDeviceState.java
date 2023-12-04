package cz.cvut.fel.omo.smartHome.model.usable.devices.states;

import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

public class BrokenDeviceState extends DeviceState {
    public BrokenDeviceState(Device device) {
        super(device);
    }

    @Override
    public int updateDevice() {
        Reporter.getInstance().log("\n" + device.getClass().getSimpleName() + " in " + device.getRoom().getName() + " in " + device.getRoom().getFloor().getName() + " is still broken and needs to be repaired!");
        return 0;
    }
}
