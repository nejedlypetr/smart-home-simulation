package cz.cvut.fel.omo.smartHome.model.event;

import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.usable.sport.SportEquipment;

public class SportEquipmentEvent extends Event {
    private final SportEquipment sportEquipment;

    public SportEquipmentEvent(Room room, Floor floor, String description, SportEquipment sportEquipment) {
        super(room, floor, description);
        this.sportEquipment = sportEquipment;
    }
}
