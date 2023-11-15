package cz.cvut.fel.omo.smartHome.model.activity;

import com.sun.jdi.ClassType;

public class Activity {
    private String description;
    private ClassType creatureType;

    public Activity(String description, ClassType creatureType) {
        this.description = description;
        this.creatureType = creatureType;
    }

    public String getDescription() {
        return description;
    }

    public ClassType getCreatureType() {
        return creatureType;
    }
}
