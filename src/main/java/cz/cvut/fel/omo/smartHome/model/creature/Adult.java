package cz.cvut.fel.omo.smartHome.model.creature;

import cz.cvut.fel.omo.smartHome.model.event.Event;

import java.util.List;
import java.util.Random;

public class Adult extends Creature {
    public Adult(String name) {
        super(name);
    }

    @Override
    public Decision makeDecision(List<Event> events) {
        if (!events.isEmpty()) return Decision.HANDLE_EVENT;

        int decision = new Random().nextInt(3);
        return switch (decision) {
            case 1 -> Decision.DEVICE;
            case 2 -> Decision.SPORT_DEVICE;
            default -> Decision.ACTIVITY;
        };
    }
}
