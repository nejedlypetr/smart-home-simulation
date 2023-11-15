package cz.cvut.fel.omo.smartHome.model.house;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FloorBuilder {
    private String name;
    private List<Room> rooms;

    public FloorBuilder() {}

    public FloorBuilder withRooms(List<Room> rooms) {
        this.rooms = rooms;
        return this;
    }

    public FloorBuilder withRooms() {
        Room room1 = new RoomBuilder()
                .withActivities().withDevices().withName("Living room").build();
        Room room2 = new RoomBuilder()
                .withActivities().withDevices().withName("Kitchen").build();

        this.rooms = new ArrayList<>(Arrays.asList(room1,room2));
        return this;
    }

    public FloorBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Floor build() {
        if (rooms == null || name == "") {
            throw  new IllegalArgumentException("List of rooms and name needed to build a floor");
        }
        if (rooms.isEmpty()) {
            throw new IllegalArgumentException("Empty required argument!");
        }
        return new Floor(name, rooms);
    }
}
