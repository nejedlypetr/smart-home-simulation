package cz.cvut.fel.omo.smartHome.model.creature;

import cz.cvut.fel.omo.smartHome.model.event.CreatureEvent;
import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;

import java.util.List;
import java.util.Random;

public class Baby extends Creature {
    public Baby(String name) {
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
        Random random = new Random();
        int num = random.nextInt(3);

        switch (num) {
            case 0 -> new CreatureEvent(floor, room, "is crying.")
            case 1 -> new CreatureEvent(floor, room, "is crying adsf.")
            case 2 -> new CreatureEvent(floor, room, "is crying asdfaf.")
        }

    }
}
