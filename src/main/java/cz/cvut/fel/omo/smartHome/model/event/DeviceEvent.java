package cz.cvut.fel.omo.smartHome.model.event;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;

public class DeviceEvent extends Event {
    private final Device device;

    public DeviceEvent(Room room, Floor floor, String description, Device device, String handleDescription) {
        super(room, floor, description, handleDescription);
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }

    @Override
    public void handleBy(Creature creature) {
        if (getHandleDescription() == null) {
            System.out.print("\n" + creature + "is repairing " + device.getClass().getSimpleName() + " in " + device.getRoom() + " in " + device.getRoom().getFloor() + ". The documentation says: " + device.getDocumentation() + device.getClass().getSimpleName() + " repaired.");
            device.repair(creature);
        } else {
            System.out.print("\n" + creature + getHandleDescription() + device.getClass().getSimpleName() + " in " + device.getRoom() + " in " + device.getRoom().getFloor() + ".");
        }
    }
}
