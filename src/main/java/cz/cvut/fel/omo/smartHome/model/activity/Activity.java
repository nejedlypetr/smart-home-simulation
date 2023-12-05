package cz.cvut.fel.omo.smartHome.model.activity;

import com.github.cliftonlabs.json_simple.JsonObject;
import cz.cvut.fel.omo.smartHome.model.creature.*;

public class Activity {
    private String description;
    private Class<? extends Creature> creatureType;

    public Activity(String description, Class<? extends Creature> creatureType) {
        this.description = description;
        this.creatureType = creatureType;
    }

    public static Activity fromJson(JsonObject json) {
        String description = (String) json.get("description");
        String type = (String) json.get("type");

        switch (type) {
            case "Creature": return new Activity(description, Creature.class);
            case "Adult": return new Activity(description, Adult.class);
            case "Child": return new Activity(description, Child.class);
            case "Baby": return new Activity(description, Baby.class);
            case "Cat": return new Activity(description, Cat.class);
            case "Dog": return new Activity(description, Dog.class);
            default: throw new RuntimeException("Invalid activity creature type.");
        }
    }

    public String getDescription() {
        return description;
    }

    public Class<? extends Creature> getCreatureType() {
        return creatureType;
    }

    @Override
    public String toString() {
        return creatureType.getSimpleName() + description;
    }
}
