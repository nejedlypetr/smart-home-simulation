package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.exceptions.NoValidActivitiesException;
import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.sensors.SensorInterface;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;
import cz.cvut.fel.omo.smartHome.utils.RandomPicker;

import java.util.List;

public class Room {
    private String name;
    private List<Activity> activities;
    private List<Device> devices;
    private Floor floor;
    private SensorInterface sensor;

    public Room(String name, List<Activity> activities, List<Device> devices, SensorInterface sensor) {
        this.name = name;
        this.activities = activities;
        this.devices = devices;
        this.sensor = sensor;
    }

    public Activity getRandomActivityFor(Creature creature) throws NoValidActivitiesException {
        List<Activity> validActivities = activities
                .stream()
                .filter(activity -> activity.getCreatureType() == creature.getClass() || activity.getCreatureType() == Creature.class)
                .toList();
        if (validActivities.isEmpty()) {
            throw new NoValidActivitiesException("There are no valid activities for " + name);
        }
        return RandomPicker.pickRandomElementFromList(validActivities);
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

    public void setSensor(SensorInterface sensor) {
        this.sensor = sensor;
    }

    public SensorInterface getSensor() {
        return sensor;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(name).append(":");
        if (!activities.isEmpty()) {
            stringBuilder.append("\n\t\t\tActivities:");
            for (Activity activity : activities) {
                stringBuilder.append(String.format("%n\t\t\t\t%s", activity));
            }
        }
        if (!devices.isEmpty()) {
            stringBuilder.append("\n\t\t\tDevices:");
            for (Device device : devices) {
                stringBuilder.append(String.format("%n\t\t\t\t%s", device));
            }
        }
        if (sensor != null) {
            stringBuilder.append("\n\t\t\tSensor: ").append(sensor.getClass().getSimpleName());
        }

        return stringBuilder.toString();
    }
}
