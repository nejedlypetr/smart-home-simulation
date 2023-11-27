package cz.cvut.fel.omo.smartHome.model.house;

import cz.cvut.fel.omo.smartHome.exceptions.NoValidActivitiesException;
import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.utils.RandomPicker;

import java.util.List;

public class Floor {
    private String name;
    private List<Room> rooms;
    private House house;

    public Floor(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public Activity getRandomActivityFor(Creature creature) throws NoValidActivitiesException {
        Room room = RandomPicker.pickRandomElementFromList(rooms);
        System.out.print(creature.getName() + " is in " + room.getName() + ". ");
        return room.getRandomActivityFor(creature);
    }

    public void addRoom(Room room) {
        rooms.add(room);
        room.setFloor(this);
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

    @Override
    public String toString() {
        return name;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
