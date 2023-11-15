package cz.cvut.fel.omo.smartHome.model.sensors;

import cz.cvut.fel.omo.smartHome.model.house.Room;

public class SensorA {
    private Room room;

    public SensorA(Room room) {
        this.room = room;
    }

    public void measure() {}
    public void notifyHouseHot() {}
    public void notifyHouseCold() {}

}
