package cz.cvut.fel.omo.smartHome.model.event;

import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;

public class Event {
    private Room room;
    private Floor floor;
    private String description;

    public Event(Room room, Floor floor, String description) {
        this.room = room;
        this.floor = floor;
        this.description = description;
    }
}
