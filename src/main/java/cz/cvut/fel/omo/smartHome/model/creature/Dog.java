package cz.cvut.fel.omo.smartHome.model.creature;

import cz.cvut.fel.omo.smartHome.model.event.CreatureEvent;
import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

import java.util.List;
import java.util.Random;

public class Dog extends Creature {
    public Dog(String name) {
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

        event = switch (decision) {
            case 0 -> new CreatureEvent(this, floor, room, " is hungry.", " is giving food to " + this.getName() + ".");
            case 1 -> new CreatureEvent(this, floor, room, " is pooping everywhere.", " is cleaning up " + this.getName() + "'s poops.");
            default -> new CreatureEvent(this, floor, room, " wants to play with someone.", " is playing with " + this.getName() + ".");
        };

        Reporter.getInstance().log("\n" + this + " is in " + room.getName() + " in " + floor.getName() + ". " + getName() + event.getDescription());
        return event;
    }
}
