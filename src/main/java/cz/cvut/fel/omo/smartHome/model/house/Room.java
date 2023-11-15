package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.usable.Device;
import cz.cvut.fel.omo.smartHome.utils.RandomListElementPicker;

import java.util.List;

public class Room {

    private String name;
    private List<Activity> activities;
    private List<Device> devices;

    public Room(String name, List<Activity> activities, List<Device> devices) {
        this.name = name;
        this.activities = activities;
        this.devices = devices;
    }

    public Activity getRandomActivityFor(Creature creature) {
        return RandomListElementPicker.pickRandomElement(activities);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

}
