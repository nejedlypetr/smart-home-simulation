package cz.cvut.fel.omo.smartHome.model.event;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;

public class CreatureEvent extends Event {
    private final Creature creature;

    public CreatureEvent(Creature creature, Floor floor, Room room, String description, String handleDescription) {
        super(room, floor, description, handleDescription);
        this.creature = creature;
    }

    @Override
    public void handleBy(Creature creature) {
        System.out.println(creature + "is in " + getRoom().getName() + " in " + getFloor().getName() + ". " + creature.getName() + getHandleDescription());
        super.handleBy(creature);
    }
}
