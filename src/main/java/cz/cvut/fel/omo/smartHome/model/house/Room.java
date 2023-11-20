package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.exceptions.NoValidActivitiesException;
import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;
import cz.cvut.fel.omo.smartHome.utils.RandomListElementPicker;

import java.util.List;

public class Room {
    private String name;
    private List<Activity> activities;
    private List<Device> devices;
    private Floor floor;

    public Room(String name, List<Activity> activities, List<Device> devices) {
        this.name = name;
        this.activities = activities;
        this.devices = devices;
    }

    public Activity getRandomActivityFor(Creature creature) throws NoValidActivitiesException {
        List<Activity> validActivities = activities
                .stream()
                .filter(activity -> activity.getCreatureType() == creature.getClass() || activity.getCreatureType() == Creature.class)
                .toList();
        if (validActivities.isEmpty()) {
            throw new NoValidActivitiesException("There are no valid activities for " + name);
        }
        return RandomListElementPicker.pickRandomElement(validActivities);
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

    public List<Device> getDevices() {
        return devices;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return name;
    }
}
