package cz.cvut.fel.omo.smartHome.model.event;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;

public class CreatureEvent extends Event {
    private final Creature creature;

    public CreatureEvent(Room room, Floor floor, String description, Creature creature) {
        super(room, floor, description);
        this.creature = creature;
    }
}
