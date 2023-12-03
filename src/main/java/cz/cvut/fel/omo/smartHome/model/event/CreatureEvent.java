package cz.cvut.fel.omo.smartHome.model.event;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

public class CreatureEvent extends Event {
    private final Creature creature;

    public CreatureEvent(Creature creature, Floor floor, Room room, String description, String handleDescription) {
        super(room, floor, description, handleDescription);
        this.creature = creature;
    }

    @Override
    public void handleBy(Creature creature) {
        Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getName() + " in " + getFloor().getName() + ". " + creature.getName() + getHandleDescription());
    }

    public Creature getCreature() {
        return creature;
    }

    public void print() {
        Reporter.getInstance().log("\n" + creature + " is in " + getRoom().getName() + " in " + getFloor().getName() + ". " + creature.getName() + getDescription());
    }
}
