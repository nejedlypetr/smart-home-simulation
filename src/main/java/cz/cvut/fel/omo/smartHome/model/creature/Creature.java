package cz.cvut.fel.omo.smartHome.model.creature;

import com.github.cliftonlabs.json_simple.JsonObject;
import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.event.Event;
import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;

import java.util.List;

public abstract class Creature {
    private final String name;

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

    public static Creature fromJson(JsonObject json) {
        String name = (String) json.get("name");
        String type = (String) json.get("type");

        return switch (type) {
            case "Adult" -> new Adult(name);
            case "Child" -> new Child(name);
            case "Baby" -> new Baby(name);
            case "Cat" -> new Cat(name);
            case "Dog" -> new Dog(name);
            default -> throw new RuntimeException("Invalid creature type.");
        };
    }
}
