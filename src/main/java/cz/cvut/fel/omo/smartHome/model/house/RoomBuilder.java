package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Baby;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.sensors.SensorInterface;
import cz.cvut.fel.omo.smartHome.model.usable.devices.*;

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
            new Activity(" is crying. ", Baby.class)
        ));
        return this;
    }

    public RoomBuilder withDevices() {
        this.devices = new ArrayList<>(Arrays.asList(
                new TV(),
                new Laptop(),
                new Fridge(),
                new Dishwasher(),
                new WashingMachine(),
                new Phone(),
                new LightBulb(),
                new Car()
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
