package cz.cvut.fel.omo.smartHome.model.activity;

import com.sun.jdi.ClassType;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;

public class Activity {
    private String description;
    private String creatureType;

    public Activity(String description, String creatureType) {
        this.description = description;
        this.creatureType = creatureType;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatureType() {
        return creatureType;
    }
}
