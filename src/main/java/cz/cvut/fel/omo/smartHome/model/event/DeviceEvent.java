package cz.cvut.fel.omo.smartHome.model.event;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.device.Device;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

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
        if (getHandleDescription() == null || device.isBroken()) {
            Reporter.getInstance().log("\n" + creature + " is repairing " + device.getClass().getSimpleName() + " in " + device.getRoom().getName() + " in " + device.getRoom().getFloor().getName() + ". The documentation says: " + device.getDocumentation() + device.getClass().getSimpleName() + " repaired.");
            device.repair(creature);
        } else {
            Reporter.getInstance().log("\n" + creature + getHandleDescription() + ".");
            device.handleEvent(this);
        }
    }
}
