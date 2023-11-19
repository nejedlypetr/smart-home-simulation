package cz.cvut.fel.omo.smartHome.model.creature;

import cz.cvut.fel.omo.smartHome.model.event.Event;

import java.util.List;
import java.util.Random;

public class Child extends Creature {
    public Child(String name) {
        super(name);
    }

    @Override
    public Decision makeDecision(List<Event> events) {
        int decision = new Random().nextInt(3);
        return switch (decision) {
            case 1 -> Decision.DEVICE;
            case 2 -> Decision.SPORT_DEVICE;
            default -> Decision.ACTIVITY;
        };
    }
}
