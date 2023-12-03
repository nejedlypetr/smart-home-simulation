package cz.cvut.fel.omo.smartHome.model.activity;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;

public class Activity {
    private String description;
    private Class<? extends Creature> creatureType;

    public Activity(String description, Class<? extends Creature> creatureType) {
        this.description = description;
        this.creatureType = creatureType;
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
