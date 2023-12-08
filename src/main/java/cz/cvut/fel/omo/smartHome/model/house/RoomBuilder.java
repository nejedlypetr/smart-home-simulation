package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.*;
import cz.cvut.fel.omo.smartHome.model.sensors.SensorInterface;
import cz.cvut.fel.omo.smartHome.model.usable.device.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RoomBuilder {
    private String name;
    private List<Activity> activities;

    private List<Device> devices;

    private SensorInterface sensor;

    public RoomBuilder() {}

    public RoomBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RoomBuilder withActivities(List<Activity> activities) {
        this.activities = activities;
        return this;
    }

    public RoomBuilder withDevices(List<Device> devices) {
        this.devices = devices;
        return this;
    }

    public RoomBuilder withSensor(SensorInterface sensor) {
        this.sensor = sensor;
        return this;
    }

    public RoomBuilder withActivities() {
        this.activities = new ArrayList<>(Arrays.asList(
            new Activity(" is sleeping. ", Creature.class),
            new Activity(" is eating. ", Creature.class),
            new Activity(" is thinking about the future of this family.", Adult.class),
            new Activity(" is dancing. ", Child.class),
            new Activity(" is thinking about Design patterns. ", Child.class),
            new Activity(" is barking happily. ", Dog.class),
            new Activity(" is meowing happily. ", Cat.class),
            new Activity(" is hiding. ", Baby.class),
            new Activity(" is hiding. ", Dog.class),
            new Activity(" is hiding. ", Cat.class),
            new Activity(" is giggling. ", Baby.class)
        ));
        return this;
    }

    public RoomBuilder withDevices() {
        this.devices = new ArrayList<>(Arrays.asList(
                new TV(),
                new Laptop(),
                new Fridge(),
                new Phone(),
                new LightBulb()
        ));
        return this;
    }

    public Room build() {
        if (activities == null || devices == null || Objects.equals(name, "")) {
            throw new IllegalArgumentException("Incomplete set of required arguments!");
        }
        if (activities.isEmpty() || devices.isEmpty()) {
            throw new IllegalArgumentException("Empty required argument!");
        }

        Room room = new Room(name, activities, devices, sensor);
        for (Device device : devices) {
            device.setRoom(room);
        }
        if (sensor != null) {
            sensor.setRoom(room);
        }
        return room;
    }
}
