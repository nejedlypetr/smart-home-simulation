package cz.cvut.fel.omo.smartHome.model.creature;

import cz.cvut.fel.omo.smartHome.model.activity.Activity;

public abstract class Creature {
    private String name;

    public Creature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void doActivity(Activity activity) {
        System.out.println(name + activity.getDescription());
    }

    public void findActivity() {
        System.out.println("Find activity error.");
    }

    public void generateEvent() {
        System.out.println("Generate activity error.");
    }

    @Override
    public String toString() {
        return this.getName() + "(" + this.getClass().getName() + ")";
    }
}
