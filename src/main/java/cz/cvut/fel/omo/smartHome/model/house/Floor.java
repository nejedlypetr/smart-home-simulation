package cz.cvut.fel.omo.smartHome.model.house;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import cz.cvut.fel.omo.smartHome.exceptions.NoValidActivitiesException;
import cz.cvut.fel.omo.smartHome.model.activity.Activity;
import cz.cvut.fel.omo.smartHome.model.creature.Creature;
import cz.cvut.fel.omo.smartHome.reporter.Reporter;
import cz.cvut.fel.omo.smartHome.utils.RandomPicker;

import java.util.ArrayList;
import java.util.List;

public class Floor implements RandomActivityFinderComposite{
    private String name;
    private List<Room> rooms;
    private House house;

    public Floor(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public static Floor fromJson(JsonObject json) {
        String name = (String) json.get("name");

        List<Room> rooms = new ArrayList<>();
        JsonArray roomsJsonArray = (JsonArray) json.get("rooms");
        roomsJsonArray.forEach(obj -> rooms.add(Room.fromJson((JsonObject) obj)));

        return new FloorBuilder()
                .withName(name)
                .withRooms(rooms)
                .build();
    }

    @Override
    public Activity getRandomActivityFor(Creature creature) throws NoValidActivitiesException {
        Room room = RandomPicker.pickRandomElementFromList(rooms);
        Reporter.getInstance().log(creature.getName() + " is in " + room.getName() + ". ");
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

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(name).append(":");
        for (Room room : rooms) {
            stringBuilder.append(String.format("%n\t\t%s", room));
        }

        return stringBuilder.toString();
    }
}
