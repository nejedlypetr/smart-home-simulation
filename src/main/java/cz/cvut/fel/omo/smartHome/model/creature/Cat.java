package cz.cvut.fel.omo.smartHome.model.creature;

import cz.cvut.fel.omo.smartHome.model.event.CreatureEvent;
import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

import java.util.List;
import java.util.Random;

public class Cat extends Creature {

    public Cat(String name) {
        super(name);
    }

    @Override
    public Decision makeDecision(List<Event> events) {
        int decision = new Random().nextInt(2);
        return switch (decision) {
            case 1 -> Decision.GENERATE_EVENT;
            default -> Decision.ACTIVITY;
        };
    }

    @Override
    public Event generateEvent(Floor floor, Room room) {
        Event event;
        int decision = new Random().nextInt(3);

        switch (decision) {
            case 0:
                event = new CreatureEvent(this, floor, room, " is hungry.", " is giving food to " + this.getName() + ".");
                break;
            case 1:
                event = new CreatureEvent(this, floor, room, " is pooping everywhere.", " is cleaning up " + this.getName() + "'s poops.");
                break;
            default:
                event = new CreatureEvent(this, floor, room, " needs water.", " is giving water to " + this.getName() + ".");
        }

        Reporter.getInstance().log("\n" + this + "is in " + room.getName() + " in " + floor.getName() + ". " + getName() + event.getDescription());
        return event;
    }
}
