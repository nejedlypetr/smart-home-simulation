package cz.cvut.fel.omo.smartHome.model.event;

import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.model.house.Floor;
import cz.cvut.fel.omo.smartHome.model.house.Room;

public abstract class Event {
    private Room room;
    private Floor floor;
    private String description;

    private String handleDescription;

    public Event(Room room, Floor floor, String description, String handleDescription) {
        this.room = room;
        this.floor = floor;
        this.description = description;
        this.handleDescription = handleDescription;
    }

    public abstract void handleBy(Creature creature);

    public String getHandleDescription() {
        return handleDescription;
    }
}
