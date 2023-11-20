package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Baby;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Fridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomBuilder {
    private String name;
    private List<Activity> activities;

    private List<Device> devices;

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
//                new TV(),
                new Fridge()
//                new Phone()
        ));
        return this;
    }

    public Room build() {
        if (activities == null || devices == null || name == "") {
            throw new IllegalArgumentException("Incomplete set of required arguments!");
        }
        if (activities.isEmpty() || devices.isEmpty()) {
            throw new IllegalArgumentException("Empty required argument!");
        }
        Room room = new Room(name,activities,devices);
        for (Device device : devices) {
            device.setRoom(room);
        }
        return room;
    }
}
