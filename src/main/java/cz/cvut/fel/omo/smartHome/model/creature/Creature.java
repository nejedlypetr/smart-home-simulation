package cz.cvut.fel.omo.smartHome.model.creature;

import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.event.Event;

import java.util.List;

public abstract class Creature {
    private String name;

    public Creature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void doActivity(Activity activity) {
        System.out.print(name + activity.getDescription());
    }

    public Decision makeDecision(List<Event> events) {
        throw new UnsupportedOperationException("Method is not implemented in the subclass.");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.getName() + " ";
    }

    public boolean canUseDevice() {
        return getClass().equals(Child.class) || getClass().equals(Adult.class);
    }
}
