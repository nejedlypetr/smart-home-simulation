package cz.cvut.fel.omo.smartHome.model.event;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;

public abstract class Event {
    private Room room;
    private Floor floor;
    private String description;
    private final String handleDescription;

    public Event(Room room, Floor floor, String description, String handleDescription) {
        this.room = room;
        this.floor = floor;
        this.description = description;
        this.handleDescription = handleDescription;
    }

    public void handleBy(Creature creature) {
        throw new UnsupportedOperationException("Method is not implemented in the subclass.");
    }

    public String getHandleDescription() {
        return handleDescription;
    }

    public Room getRoom() {
        return room;
    }

    public Floor getFloor() {
        return floor;
    }

    public String getDescription() {
        return description;
    }

}
