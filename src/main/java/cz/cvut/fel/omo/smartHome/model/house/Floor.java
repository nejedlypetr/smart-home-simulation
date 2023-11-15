package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.utils.RandomListElementPicker;

import java.util.List;

public class Floor {
    private String name;
    private List<Room> rooms;

    public Floor(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public Activity getRandomActivityFor(Creature creature) {
        Room room = RandomListElementPicker.pickRandomElement(rooms);
        System.out.print(creature.getName() + " is in " + room.getName() + ". ");
        return room.getRandomActivityFor(creature);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
