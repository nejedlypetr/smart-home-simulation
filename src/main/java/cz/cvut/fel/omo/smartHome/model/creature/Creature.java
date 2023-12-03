package cz.cvut.fel.omo.smartHome.model.creature;

import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

import java.util.List;

public abstract class Creature {
    private String name;

    public Creature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Event generateEvent(Floor floor, Room room) {
        throw new UnsupportedOperationException("Method is not implemented in the subclass.");
    }

    public void doActivity(Activity activity) {
        Reporter.getInstance().log(name + activity.getDescription());
    }

    public Decision makeDecision(List<Event> events) {
        throw new UnsupportedOperationException("Method is not implemented in the subclass.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + this.getName();
    }
}
