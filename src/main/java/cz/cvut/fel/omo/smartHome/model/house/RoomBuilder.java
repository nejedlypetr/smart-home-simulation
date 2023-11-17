package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Baby;
import cz.cvut.fel.omo.smartHome.model.creature.Dog;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Phone;
import cz.cvut.fel.omo.smartHome.model.usable.devices.TV;
import cz.cvut.fel.omo.smartHome.model.usable.devices.Device;

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
            new Activity(" is sleeping.", Dog.class.toString()),
            new Activity(" is eating.", Baby.class.toString())
        ));
        return this;
    }

    public RoomBuilder withDevices() {
        this.devices = new ArrayList<>(Arrays.asList(
                new TV(),
                new Phone()
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
        return new Room(name,activities,devices);
    }
}
