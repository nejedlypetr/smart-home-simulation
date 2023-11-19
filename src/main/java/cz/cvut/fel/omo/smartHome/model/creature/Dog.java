package cz.cvut.fel.omo.smartHome.model.creature;

import cz.cvut.fel.omo.smartHome.model.event.Event;

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
            case 1 -> Decision.EVENT;
            default -> Decision.ACTIVITY;
        };
    }
}
