package cz.cvut.fel.omo.smartHome.model.event;

import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;

public class DeviceEvent extends Event {
    private final Device device;

    public DeviceEvent(Room room, Floor floor, String description, Device device) {
        super(room, floor, description);
        this.device = device;
    }
}
